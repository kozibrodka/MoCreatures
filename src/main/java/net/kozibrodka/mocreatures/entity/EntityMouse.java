// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) braces deadcode 

package net.kozibrodka.mocreatures.entity;

import net.kozibrodka.mocreatures.events.mod_mocreatures;
import net.kozibrodka.mocreatures.mocreatures.MoCreatureRacial;
import net.minecraft.block.Block;
import net.minecraft.entity.Entity;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.ai.pathing.Path;
import net.minecraft.entity.passive.AnimalEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.Item;
import net.minecraft.nbt.NbtCompound;
import net.minecraft.util.math.Box;
import net.minecraft.util.math.MathHelper;
import net.minecraft.world.World;
import net.modificationstation.stationapi.api.util.Identifier;
import net.modificationstation.stationapi.api.server.entity.MobSpawnDataProvider;

import java.util.List;

public class EntityMouse extends AnimalEntity implements MobSpawnDataProvider, MoCreatureRacial
{

    public EntityMouse(World world)
    {
        super(world);
//        texture = "/assets/mocreatures/stationapi/textures/mob/miceg.png";
        setBoundingBoxSpacing(0.3F, 0.3F);
        health = 4;
//        picked = false;
    }

    public int getRandomRace(){
        int i = random.nextInt(100);
        if(i <= 50)
        {
            return  1;
        } else
        if(i <= 80)
        {
            return 2;
        } else
        {
            return 3;
        }
    }

    public void chooseType(int typeint)
    {
            if(typeint == 1)
            {
                texture = "/assets/mocreatures/stationapi/textures/mob/miceg.png";
            } else
            if(typeint == 2)
            {
                texture = "/assets/mocreatures/stationapi/textures/mob/miceb.png";
            } else
            if(typeint == 3)
            {
                texture = "/assets/mocreatures/stationapi/textures/mob/micew.png";
            }
    }

    public void tickMovement()
    {
        super.tickMovement();
        if(!typechosen && world.isRemote && getType() != 0){
            typechosen = true;
            chooseType(getType());
        }
        if(random.nextInt(15) == 0)
        {
            LivingEntity entityliving = getBoogey(6D);
            if(entityliving != null)
            {
                runLikeHell(entityliving);
            }
        }
        if(!onGround && vehicle != null)
        {
            yaw = vehicle.yaw;
        }
    }

    private void checkFertility()
    {
        int i = 0;
        List list = world.collectEntitiesByClass(EntityMouse.class, Box.createCached(x, y, z, x + 1.0D, y + 1.0D, z + 1.0D).expand(16D, 4D, 16D));
        for(int j = 0; j < list.size(); j++)
        {
            i++;
        }

        if(i > 10)
        {
            fertile = false;
        }
    }

    private void reproduce()
    {
    }

    private boolean checkNearCats()
    {
        return true;
    }

    private boolean checkNearRock()
    {
        return true;
    }

    public boolean interact(PlayerEntity entityplayer)
    {
        yaw = entityplayer.yaw;
        setVehicle(entityplayer);
        if(vehicle != null)
        {
            setPicked(true);
        } else
        {
            world.playSound(this, "mob.chickenplop", 1.0F, (random.nextFloat() - random.nextFloat()) * 0.2F + 1.0F);
            setPicked(false);
        }
        velocityX = entityplayer.velocityX * 5D;
        velocityY = entityplayer.velocityY / 2D + 0.5D;
        velocityZ = entityplayer.velocityZ * 5D;
        return true;
    }

    public double getStandingEyeHeight()
    {
        if(vehicle instanceof PlayerEntity)
        {
            return (double)(standingEyeHeight - 1.7F);
        } else
        {
            return (double)standingEyeHeight;
        }
    }

    public int getLimitPerChunk()
    {
        return 6;
    }

    public void writeNbt(NbtCompound nbttagcompound)
    {
        super.writeNbt(nbttagcompound);
        nbttagcompound.putInt("TypeInt", getType());
    }

    public void readNbt(NbtCompound nbttagcompound)
    {
        super.readNbt(nbttagcompound);
        setType(nbttagcompound.getInt("TypeInt"));
    }

    public LivingEntity getBoogey(double d)
    {
        double d1 = -1D;
        LivingEntity entityliving = null;
        List list = world.getEntities(this, boundingBox.expand(d, 4D, d));
        for(int i = 0; i < list.size(); i++)
        {
            Entity entity = (Entity)list.get(i);
            if((entity instanceof LivingEntity) && !(entity instanceof EntityMouse))
            {
                entityliving = (LivingEntity)entity;
            }
        }

        return entityliving;
    }

    public void runLikeHell(Entity entity)
    {
        double d = x - entity.x;
        double d1 = z - entity.z;
        double d2 = Math.atan2(d, d1);
        d2 += (double)(random.nextFloat() - random.nextFloat()) * 0.75D;
        double d3 = x + Math.sin(d2) * 8D;
        double d4 = z + Math.cos(d2) * 8D;
        int i = MathHelper.floor(d3);
        int j = MathHelper.floor(boundingBox.minY);
        int k = MathHelper.floor(d4);
        int l = 0;
        do
        {
            if(l >= 16)
            {
                break;
            }
            int i1 = (i + random.nextInt(4)) - random.nextInt(4);
            int j1 = (j + random.nextInt(3)) - random.nextInt(3);
            int k1 = (k + random.nextInt(4)) - random.nextInt(4);
            if(j1 > 4 && (world.getBlockId(i1, j1, k1) == 0 || world.getBlockId(i1, j1, k1) == Block.SNOW.id) && world.getBlockId(i1, j1 - 1, k1) != 0)
            {
                Path pathentity = world.findPath(this, i1, j1, k1, 16F);
                setPath(pathentity);
                break;
            }
            l++;
        } while(true);
    }

    public boolean climbing()
    {
        return !onGround && isOnLadder();
    }

    public boolean isOnLadder()
    {
        return horizontalCollision;
    }

    public boolean upsideDown()
    {
        return getPicked();
    }

    public boolean canSpawn()
    {
        int i = MathHelper.floor(x);
        int j = MathHelper.floor(boundingBox.minY);
        int k = MathHelper.floor(z);
        return mocr.mocreaturesGlass.animals.micefreq > 0 && world.canSpawnEntity(boundingBox) && world.getEntityCollisions(this, boundingBox).size() == 0 && !world.isBoxSubmergedInFluid(boundingBox) && world.getBlockId(i, j - 1, k) == Block.COBBLESTONE.id && mocr.mocreaturesGlass.animals.mouseinhouse || world.getBlockId(i, j - 1, k) == Block.PLANKS.id && mocr.mocreaturesGlass.animals.mouseinhouse || world.getBlockId(i, j - 1, k) == Block.DIRT.id || world.getBlockId(i, j - 1, k) == Block.STONE.id  && mocr.mocreaturesGlass.animals.mouseinhouse || world.getBlockId(i, j - 1, k) == Block.GRASS_BLOCK.id;
    }

    protected String getRandomSound()
    {
        return "mocreatures:micegrunt";
    }

    protected String getHurtSound()
    {
        return "mocreatures:micehurt";
    }

    protected String getDeathSound()
    {
        return "mocreatures:micedying";
    }

    protected int getDroppedItemId()
    {
        return Item.SEEDS.id;
    }

    protected void initDataTracker() {
        super.initDataTracker();
        dataTracker.startTracking(16, (byte) 0); //Type
        dataTracker.startTracking(17, (byte) 0); //Picked
    }

    mod_mocreatures mocr = new mod_mocreatures();
    private boolean fertile;
    private int micetimer;

//    public int typeint;
//    public boolean picked;

    public boolean typechosen;

    @Override
    public Identifier getHandlerIdentifier() {
        return Identifier.of(mod_mocreatures.MOD_ID, "Mouse");
    }

    //TYPE
    public void setTypeSpawn()
    {
        if(!world.isRemote){
            int type = getRandomRace();
            setType(type);
        }
    }

    public void setType(int type)
    {
        if(!world.isRemote) {
            dataTracker.set(16, (byte) type);
            chooseType(type);
        }
    }

    public int getType()
    {
        return dataTracker.getByte(16);
    }
    //ADULT
    public boolean getPicked()
    {
        return (dataTracker.getByte(17) & 1) != 0;
    }

    public void setPicked(boolean flag)
    {
        if(flag)
        {
            dataTracker.set(17, (byte) 1);
        } else
        {
            dataTracker.set(17, (byte) 0);
        }
    }
}
