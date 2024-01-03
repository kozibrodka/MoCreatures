// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) braces deadcode 

package net.kozibrodka.mocreatures.entity;

import net.kozibrodka.mocreatures.events.mod_mocreatures;
import net.minecraft.block.Block;
import net.minecraft.class_61;
import net.minecraft.entity.Entity;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.passive.AnimalEntity;
import net.minecraft.item.Item;
import net.minecraft.nbt.NbtCompound;
import net.minecraft.util.math.MathHelper;
import net.minecraft.world.World;
//import net.modificationstation.stationapi.api.packet.Message;
import net.modificationstation.stationapi.api.util.Identifier;
import net.modificationstation.stationapi.api.server.entity.HasTrackingParameters;
import net.modificationstation.stationapi.api.server.entity.MobSpawnDataProvider;

import java.util.Arrays;
import java.util.List;

@HasTrackingParameters(trackingDistance = 160, updatePeriod = 2)
public class EntityDeer extends AnimalEntity implements MobSpawnDataProvider
{

    public EntityDeer(World world)
    {
        super(world);
        texture = "/assets/mocreatures/stationapi/textures/mob/deer.png";
        setAge(0.75F);
        setBoundingBoxSpacing(0.9F, 1.3F);
        health = 10;
        setType(getRandomRace());
        movementSpeed = 1.7F;
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
            } else
            if(type == 2)
            {
                texture = "/assets/mocreatures/stationapi/textures/mob/deerf.png";
                health = 15;
                setAdult(true);
            } else
            {
                texture = "/assets/mocreatures/stationapi/textures/mob/deerb.png";
                health = 5;
                setAdult(false);
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
        if(getType() == 3 && !getAdult() && random.nextInt(250) == 0)
        {
            setAge(getAge()+0.01F);
            if(getAge() >= 1.3F)
            {
                setAdult(true);
                this.dataTracker.method_1509(16, (byte)(0));
                setType(getRandomRace());
//                System.out.println("DORASTAM");;
            }
        }
        if(random.nextInt(5) == 0)
        {
            LivingEntity entityliving = getBoogey(10D);
            if(entityliving != null)
            {
                setMySpeed(true, getType());
                runLikeHell(entityliving);
            } else
            {
                setMySpeed(false, getType());
            }
        }
        if(world.isRemote){
//            System.out.println("HEJO " + this.y + " client: " + (double)this.clientY/32);
//            this.y = (double)this.clientY/32;
        }else{
//            System.out.println("HOSCIK: " + this.y);
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

//    public boolean typechosen;

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
        final byte by = this.dataTracker.method_1501(16);
//        System.out.println("TEST DATA first: " + by);
        this.dataTracker.method_1509(16, (byte)(by & 0xF0 | type & 0xF));

        chooseType(type);
        setMySpeed(false, type);

//        System.out.println(this.entityId + "RASA: " + type +" AGE: " +getAge());
    }

    public int getType()
    {
        return dataTracker.method_1501(16) & 0xF;
    }

    //AGE
    public void setAge(float age)
    {
        dataTracker.method_1509(17, (byte) ((int) 100F * age));
    }

    public float getAge()
    {
        return ((float) dataTracker.method_1501(17)) / 100F;
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

//    @Override
//    public void writeToMessage(Message message)
//    {
//        // message.ints.length is 4, so let's add a fifth
//        int[] newInts = Arrays.copyOf(message.ints, message.ints.length + 1);
//        newInts[newInts.length - 1] = getType();
//        message.ints = newInts;
//
////        System.out.println("TEST MESSEGA");
//    }
//
//    @Override
//    public void readFromMessage(Message message)
//    {
////        System.out.println("MESSAGE: " + message.ints[4]);
//        this.dataTracker.setInt(16, (byte)(message.ints[4]));
//        chooseType(message.ints[4]);
//        setMySpeed(false, message.ints[4]);
//    }
}
