// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) braces deadcode 

package net.kozibrodka.mocreatures.entity;

import net.kozibrodka.mocreatures.events.mod_mocreatures;
import net.kozibrodka.mocreatures.mixin.DataTrackerAccessor;
import net.kozibrodka.mocreatures.mocreatures.MoCreatureRacial;
import net.minecraft.block.Block;
import net.minecraft.class_61;
import net.minecraft.entity.Entity;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.passive.AnimalEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NbtCompound;
import net.minecraft.util.math.MathHelper;
import net.minecraft.world.World;
//import net.modificationstation.stationapi.api.packet.Message;
import net.modificationstation.stationapi.api.util.Identifier;
import net.modificationstation.stationapi.api.server.entity.HasTrackingParameters;
import net.modificationstation.stationapi.api.server.entity.MobSpawnDataProvider;

import java.util.List;

@HasTrackingParameters(trackingDistance = 160, updatePeriod = 2)
public class EntityDeer extends AnimalEntity implements MobSpawnDataProvider, MoCreatureRacial
{

    public EntityDeer(World world)
    {
        super(world);
        setAge(1.29F); //0.75F
        setBoundingBoxSpacing(0.9F, 1.3F);
        health = 10;
//        System.out.println("JESTEM+ " +  this.getType());
//        setType(getRandomRace());
        movementSpeed = 1.7F;
        typechosen = false;
    }

    protected void initDataTracker() {
        super.initDataTracker();
        dataTracker.method_1502(16, (byte) 0); //Type
        dataTracker.method_1502(17, (byte) 0); //Age
        dataTracker.method_1502(18, (byte) 0); //Adult
    }

    public void chooseType(int type)
    {
            if(type == 1)
            {
                texture = "/assets/mocreatures/stationapi/textures/mob/deer.png";
                health = 15;
                setAdult(true);
                setAge(0.75F);
            } else
            if(type == 2)
            {
                texture = "/assets/mocreatures/stationapi/textures/mob/deerf.png";
                health = 15;
                setAdult(true);
                setAge(0.75F);
            }
            if(type == 3)
            {
                texture = "/assets/mocreatures/stationapi/textures/mob/deerb.png";
                health = 5;
                setAdult(false);
            }

    }

    public void setMyTexture(int i){
        if(i == 1){
            texture = "/assets/mocreatures/stationapi/textures/mob/deer.png";
        }else if(i == 2){
            texture = "/assets/mocreatures/stationapi/textures/mob/deerf.png";
        }else if(i == 3){
            texture = "/assets/mocreatures/stationapi/textures/mob/deerb.png";
        }
    }

    public void setMySpeed(boolean flag, int type)
    {
        float f = 1.0F;
        if(type == 1)
        {
            f = 1.7F;
        } else
        if(type == 2)
        {
            f = 1.9F;
        } else
        {
            f = 1.3F;
        }
        if(flag)
        {
            f *= 2.0F;
        }
        movementSpeed = f;
    }

    protected void method_1389(float f)
    {
    }

    public void method_937()
    {
        super.method_937();
        if(!typechosen && world.isRemote && getType() != 0){
            typechosen = true;
            setMyTexture(getType()); //TODO: POMYSL Z RENDEREM od tigera
        }
        if(getType() == 3 && !getAdult() && random.nextInt(250) == 0 && !world.isRemote)
        {
            setAge(getAge()+0.01F);
            if(getAge() >= 1.3F)
            {
//                System.out.println("DOROSLEM");
//                setAdult(true);
//                this.dataTracker.method_1509(16, (byte)(0));
                setType(getRandomRace());
            }
        }
        if(random.nextInt(5) == 0 && !world.isRemote)
        {
            LivingEntity entityliving = getBoogey(10D);
            if(entityliving != null)
            {
                setMySpeed(true, getType());
//                runLikeHell(entityliving); //TODO:DDDDDd
            } else
            {
                setMySpeed(false, getType());
            }
        }
    }

    public LivingEntity getBoogey(double d)
    {
        double d1 = -1D;
        LivingEntity entityliving = null;
        List list = world.getEntities(this, boundingBox.expand(d, 4D, d));
        for(int i = 0; i < list.size(); i++)
        {
            Entity entity = (Entity)list.get(i);
            if((entity instanceof LivingEntity) && !(entity instanceof EntityDeer) && ((double)entity.spacingXZ >= 0.5D || (double)entity.spacingY >= 0.5D))
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

    protected void method_910()
    {
        if(movementSpeed > 2.0F && field_1623 && random.nextInt(30) == 0 && (velocityX > 0.10000000000000001D || velocityZ > 0.10000000000000001D || velocityX < -0.10000000000000001D || velocityZ < -0.10000000000000001D))
        {
            velocityY = 0.59999999999999998D;
        }
        super.method_910();
    }

    public void writeNbt(NbtCompound nbttagcompound)
    {
        super.writeNbt(nbttagcompound);
        nbttagcompound.putInt("TypeInt", getType());
        nbttagcompound.putBoolean("Adult", getAdult());
        nbttagcompound.putFloat("Edad", getAge());
    }

    public void readNbt(NbtCompound nbttagcompound)
    {
        super.readNbt(nbttagcompound);
        setAdult(nbttagcompound.getBoolean("Adult"));
        setType(nbttagcompound.getInt("TypeInt"));
        setAge(nbttagcompound.getFloat("Edad"));
//        if(!world.isRemote){
//            System.out.println("servi: " + (nbttagcompound.getInt("TypeInt")) + "  ID:" + id);
//        }else{
//            System.out.println("client: " + (nbttagcompound.getInt("TypeInt")) + "  ID:" + id);
//        }
    }

    protected String method_911()
    {
        if(!getAdult())
        {
            return "mocreatures:deerbgrunt";
        } else
        {
            return "mocreatures:deerfgrunt";
        }
    }

    protected String method_912()
    {
        return "mocreatures:deerhurt";
    }

    protected String method_913()
    {
        return "mocreatures:deerdying";
    }

    protected int method_914()
    {
        return Item.RAW_PORKCHOP.id;
    }

    public float dajszybkosc(){
        return movementSpeed;
    }

    public int getRandomRace()
    {
        int i = random.nextInt(100);
        if(i <= 20)
        {
            return 1;
        } else
        if(i <= 70)
        {
            return 2;
        } else
        {
            return 3;
        }
    }

    //TYPE
    public void setType(int type)
    {
        if(!world.isRemote){
            /**
            Zostaw jak jest bo ładnie działa, dodaj dodatkowe TYPEINT DLA DZIECI i elo. wszędzie jak u jelenia
             */
//            System.out.println("LADUJE SIE + " + getType() + "  id:" + id);
//            final byte by = this.dataTracker.method_1501(16);
//            this.dataTracker.method_1509(16, (byte)(by & 0xF0 | type & 0xF));
            this.dataTracker.method_1509(16, (byte)(type));
            chooseType(type);
            setMySpeed(false, type);
        }
    }

    public void setTypeSpawn()
    {
        if(!world.isRemote){
            int type = getRandomRace();
            setType(type);
        }
    }

    public int getType()
    {
        return dataTracker.method_1501(16) & 0xF;
    }

    //AGE
    public void setAge(float age)
    {
//        dataTracker.method_1509(17, (byte) ((int) 100F * age));
//        ((DataTrackerAccessor) dataTracker).getField_1741().get(17).method_961(age);
        dataTracker.method_1509(17, (float)(age));
    }

    public float getAge()
    {
//        return ((float) dataTracker.method_1501(17)) / 100F;
       return (float) ((DataTrackerAccessor) dataTracker).getField_1741().get(17).method_963();
    }

    //ADULT
    public boolean getAdult()
    {
        return (dataTracker.method_1501(18) & 1) != 0;
    }

    public void setAdult(boolean flag)
    {
        if(flag)
        {
            dataTracker.method_1509(18, (byte) 1);
        } else
        {
            dataTracker.method_1509(18, (byte) 0);
        }
    }

    @Override
    public Identifier getHandlerIdentifier() {
        return Identifier.of(mod_mocreatures.MOD_ID, "Deer");
    }

    public boolean method_1323(PlayerEntity entityplayer)
    {
        ItemStack itemstack = entityplayer.inventory.getSelectedItem();
        if(itemstack != null && itemstack.itemId == Item.DIAMOND_HOE.id)
        {
            System.out.println("TYPE: " + getType());
            System.out.println("ADULT? " + getAdult());
            System.out.println("AGE: " + getAge());
//            System.out.println("AGE: " + typechoosen);
            return true;
        }
        if(itemstack != null && itemstack.itemId == Item.GOLDEN_HOE.id && !world.isRemote)
        {
//            typechoosen = true;
        }
        return false;
    }
    public boolean canSpawn()
    {
        return mocr.mocreaturesGlass.animals.deerfreq > 0 && super.canSpawn();
    }

    mod_mocreatures mocr = new mod_mocreatures();
    public boolean typechosen;
}
