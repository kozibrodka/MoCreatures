// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) braces deadcode 

package net.kozibrodka.mocreatures.entity;

import net.kozibrodka.mocreatures.events.mod_mocreatures;
import net.kozibrodka.mocreatures.mocreatures.MoCreatureRacial;
import net.minecraft.block.Block;
import net.minecraft.class_61;
import net.minecraft.entity.Entity;
import net.minecraft.entity.LivingEntity;
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
        texture = "/assets/mocreatures/stationapi/textures/mob/miceg.png";
        setBoundingBoxSpacing(0.3F, 0.3F);
        health = 4;
        picked = false;
    }

    public void chooseType()
    {
        if(typeint == 0)
        {
            int i = random.nextInt(100);
            if(i <= 50)
            {
                typeint = 1;
            } else
            if(i <= 80)
            {
                typeint = 2;
            } else
            {
                typeint = 3;
            }
        }
        if(!typechosen)
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
        typechosen = true;
    }

    public void method_937()
    {
        super.method_937();
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
        if(!field_1623 && field_1595 != null)
        {
            yaw = field_1595.yaw;
        }
    }

    private void checkFertility()
    {
        int i = 0;
        List list = world.method_175(EntityMouse.class, Box.createCached(x, y, z, x + 1.0D, y + 1.0D, z + 1.0D).expand(16D, 4D, 16D));
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

    public boolean method_1323(PlayerEntity entityplayer)
    {
        yaw = entityplayer.yaw;
        method_1376(entityplayer);
        if(field_1595 != null)
        {
            picked = true;
        } else
        {
            world.playSound(this, "mob.chickenplop", 1.0F, (random.nextFloat() - random.nextFloat()) * 0.2F + 1.0F);
            picked = false;
        }
        velocityX = entityplayer.velocityX * 5D;
        velocityY = entityplayer.velocityY / 2D + 0.5D;
        velocityZ = entityplayer.velocityZ * 5D;
        return true;
    }

    public double method_1385()
    {
        if(field_1595 instanceof PlayerEntity)
        {
            return (double)(eyeHeight - 1.7F);
        } else
        {
            return (double)eyeHeight;
        }
    }

    public int method_916()
    {
        return 6;
    }

    public void writeNbt(NbtCompound nbttagcompound)
    {
        super.writeNbt(nbttagcompound);
        nbttagcompound.putInt("TypeInt", typeint);
    }

    public void readNbt(NbtCompound nbttagcompound)
    {
        super.readNbt(nbttagcompound);
        typeint = nbttagcompound.getInt("TypeInt");
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
                class_61 pathentity = world.method_189(this, i1, j1, k1, 16F);
                method_635(pathentity);
                break;
            }
            l++;
        } while(true);
    }

    public boolean climbing()
    {
        return !field_1623 && isOnLadder();
    }

    public boolean isOnLadder()
    {
        return field_1624;
    }

    public boolean upsideDown()
    {
        return picked;
    }

    public boolean canSpawn()
    {
        int i = MathHelper.floor(x);
        int j = MathHelper.floor(boundingBox.minY);
        int k = MathHelper.floor(z);
        return mocr.mocreaturesGlass.animals.micefreq > 0 && world.canSpawnEntity(boundingBox) && world.method_190(this, boundingBox).size() == 0 && !world.method_218(boundingBox) && world.getBlockId(i, j - 1, k) == Block.COBBLESTONE.id && mocr.mocreaturesGlass.animals.mouseinhouse || world.getBlockId(i, j - 1, k) == Block.PLANKS.id && mocr.mocreaturesGlass.animals.mouseinhouse || world.getBlockId(i, j - 1, k) == Block.DIRT.id || world.getBlockId(i, j - 1, k) == Block.STONE.id  && mocr.mocreaturesGlass.animals.mouseinhouse || world.getBlockId(i, j - 1, k) == Block.GRASS_BLOCK.id;
    }

    protected String method_911()
    {
        return "mocreatures:micegrunt";
    }

    protected String method_912()
    {
        return "mocreatures:micehurt";
    }

    protected String method_913()
    {
        return "mocreatures:micedying";
    }

    protected int method_914()
    {
        return Item.SEEDS.id;
    }

    mod_mocreatures mocr = new mod_mocreatures();
    public int typeint;
    public boolean typechosen;
    public boolean picked;
    private boolean fertile;
    private int micetimer;

    @Override
    public Identifier getHandlerIdentifier() {
        return Identifier.of(mod_mocreatures.MOD_ID, "Mouse");
    }
}
