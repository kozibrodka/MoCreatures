// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) braces deadcode 

package net.kozibrodka.mocreatures.entity;

import net.kozibrodka.mocreatures.events.mod_mocreatures;
import net.kozibrodka.mocreatures.mixin.EntityBaseAccesor;
import net.kozibrodka.mocreatures.mocreatures.MoCreatureRacial;
import net.minecraft.entity.Entity;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.passive.WolfEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NbtCompound;
import net.minecraft.world.World;
import net.modificationstation.stationapi.api.util.Identifier;
import net.modificationstation.stationapi.api.server.entity.MobSpawnDataProvider;

import java.util.List;

public class EntityFishy extends EntityCustomWM implements MobSpawnDataProvider, MoCreatureRacial
{

    public EntityFishy(World world)
    {
        super(world);
//        texture = "/assets/mocreatures/stationapi/textures/mob/fishy1.png";
        setBoundingBoxSpacing(0.3F, 0.3F);
        health = 4;
//        typeint = 0;
        typechosen = false;
        setAge(1.0F);
//        adult = false;
//        tamed = false;
        killedByOtherEntity = true;
    }

//    public void setTame()
//    {
//        setTamed(true);
//    }
//
//    public boolean istamed()
//    {
//        return getTamed();
//    }

    public void tickMovement()
    {
        super.tickMovement();
        if(!typechosen && world.isRemote && getType() != 0){
            typechosen = true;
            chooseType(getType());
        }
        if(!getAdult() && random.nextInt(100) == 0)
        {
            setAge(getAge()+0.02F);
            if(getAge() >= 1.0F)
            {
                setAdult(true);
            }
        }
        if(!ReadyforParenting(this))
        {
            return;
        }
        int i = 0;
        List list = world.getEntities(this, boundingBox.expand(4D, 3D, 4D));
        for(int j = 0; j < list.size(); j++)
        {
            Entity entity = (Entity)list.get(j);
            if(entity instanceof EntityFishy)
            {
                i++;
            }
        }

        if(i > 1)
        {
            return;
        }
        List list1 = world.getEntities(this, boundingBox.expand(4D, 2D, 4D));
        for(int k = 0; k < list.size(); k++)
        {
            Entity entity1 = (Entity)list1.get(k);
            if(!(entity1 instanceof EntityFishy) || entity1 == this)
            {
                continue;
            }
            EntityFishy entityfishy = (EntityFishy)entity1;
            if(!ReadyforParenting(this) || !ReadyforParenting(entityfishy) || getType() != entityfishy.getType())
            {
                continue;
            }
            if(random.nextInt(100) == 0)
            {
                gestationtime++;
            }
            if(gestationtime <= 50)
            {
                continue;
            }
            int l = random.nextInt(3) + 1;
            for(int i1 = 0; i1 < l; i1++)
            {
                EntityFishy entityfishy1 = new EntityFishy(world);
                entityfishy1.setPosition(x, y, z);
                world.spawnEntity(entityfishy1);
                world.playSound(this, "mob.chickenplop", 1.0F, (random.nextFloat() - random.nextFloat()) * 0.2F + 1.0F);
                eaten = false;
                entityfishy.eaten = false;
                gestationtime = 0;
                entityfishy.gestationtime = 0;
                entityfishy1.setTamed(true);
                entityfishy1.setAge(0.2F);
                entityfishy1.setAdult(false);
                entityfishy1.setType(getType());
            }

            break;
        }

    }

    public boolean ReadyforParenting(EntityFishy entityfishy)
    {
        return entityfishy.getTamed() && entityfishy.eaten && entityfishy.getAdult();
    }

    protected void tickLiving(){
        if(this.target instanceof PlayerEntity){
            PlayerEntity uciekinier = world.getClosestPlayer(this, 16D);
            if(uciekinier == null && target.isAlive()){
                if(random.nextInt(30) == 0)
                {
                    target = null;
                }
            }
        }
        super.tickLiving();
    }

    protected Entity getTargetInRange()
    {
        if(world.difficulty > 0 && getAge() >= 1.0F && getType() == 10)
        {
            PlayerEntity entityplayer = world.getClosestPlayer(this, 16D);
            if(entityplayer != null && ((EntityBaseAccesor)entityplayer).getSubmergedInWater() && !getTamed())
            {
                return entityplayer;
            }
            if(random.nextInt(30) == 0)
            {
                LivingEntity entityliving = FindTarget(this, 16D);
                if(entityliving != null && ((EntityBaseAccesor)entityliving).getSubmergedInWater())
                {
                    return entityliving;
                }
            }
        }
        return null;
    }

    public LivingEntity FindTarget(Entity entity, double d)
    {
        double d1 = -1D;
        LivingEntity entityliving = null;
        List list = world.getEntities(this, boundingBox.expand(d, d, d));
        for(int i = 0; i < list.size(); i++)
        {
            Entity entity1 = (Entity)list.get(i);
            if(!(entity1 instanceof LivingEntity) || (entity1 instanceof EntityCustomWM) || (entity1 instanceof EntitySharkEgg) || (entity1 instanceof EntityFishyEgg) || (entity1 instanceof PlayerEntity) || (entity1 instanceof EntityHorse) && !mocr.mocreaturesGlass.huntercreatures.attackhorses || (entity1 instanceof WolfEntity) && !mocr.mocreaturesGlass.huntercreatures.attackwolves)
            {
                continue;
            }
            double d2 = entity1.getSquaredDistance(entity.x, entity.y, entity.z);
            if((d < 0.0D || d2 < d * d) && (d1 == -1D || d2 < d1) && ((LivingEntity)entity1).canSee(entity))
            {
                d1 = d2;
                entityliving = (LivingEntity)entity1;
            }
        }

        return entityliving;
    }

    public void markDead()
    {
        if(getTamed() && health > 0)
        {
            return;
        } else
        {
            super.markDead();
            return;
        }
    }

    public boolean damage(Entity entityBase, int i)
    {
        if(super.damage(entityBase, i))
        {
            if(passenger == entityBase || vehicle == entityBase)
            {
                return true;
            }
            if(entityBase != this)
            {
                target = entityBase;
            }
            return true;
        } else
        {
            return false;
        }
    }

    protected void attack(Entity entity, float f)
    {
        if((double)f < 2D && entity.boundingBox.maxY > boundingBox.minY && entity.boundingBox.minY < boundingBox.maxY)
        {
            attackCooldown = 20;
            entity.damage(this, 1);
        }
    }

    public int getRandomRace()
    {
        int i = random.nextInt(100);
        if(i <= 9)
        {
            return 1;
        } else
        if(i <= 19)
        {
            return 2;
        } else
        if(i <= 29)
        {
            return 3;
        } else
        if(i <= 39)
        {
            return 4;
        } else
        if(i <= 49)
        {
            return 5;
        } else
        if(i <= 59)
        {
            return 6;
        } else
        if(i <= 69)
        {
            return 7;
        } else
        if(i <= 79)
        {
            return 8;
        } else
        if(i <= 89)
        {
            return 9;
        } else
        {
            if(mocr.mocreaturesGlass.watermobs.spawnpiranha){
                return 10;
            }else{
                return 1;
            }
        }
    }

    public void chooseType(int typeint)
    {
            if(typeint == 1)
            {
                texture = "/assets/mocreatures/stationapi/textures/mob/fishy1.png";
            } else
            if(typeint == 2)
            {
                texture = "/assets/mocreatures/stationapi/textures/mob/fishy2.png";
            } else
            if(typeint == 3)
            {
                texture = "/assets/mocreatures/stationapi/textures/mob/fishy3.png";
            } else
            if(typeint == 4)
            {
                texture = "/assets/mocreatures/stationapi/textures/mob/fishy4.png";
            } else
            if(typeint == 5)
            {
                texture = "/assets/mocreatures/stationapi/textures/mob/fishy5.png";
            } else
            if(typeint == 6)
            {
                texture = "/assets/mocreatures/stationapi/textures/mob/fishy6.png";
            } else
            if(typeint == 7)
            {
                texture = "/assets/mocreatures/stationapi/textures/mob/fishy7.png";
            } else
            if(typeint == 8)
            {
                texture = "/assets/mocreatures/stationapi/textures/mob/fishy8.png";
            } else
            if(typeint == 9)
            {
                texture = "/assets/mocreatures/stationapi/textures/mob/fishy9.png";
            } else
            if(typeint == 10)
            {
                texture = "/assets/mocreatures/stationapi/textures/mob/fishy10.png";
            }
    }

    protected void dropItems()
    {
        int i = random.nextInt(100);
        if(i < 70 && getAdult())
        {
            dropItem(new ItemStack(Item.RAW_FISH.id, 1, 0), 0.0F);
        } else
        {
            int j = random.nextInt(2) + 1;
            for(int k = 0; k < j; k++)
            {
                dropItem(new ItemStack(mod_mocreatures.fishyegg, 1, 0), 0.0F);
            }

        }
    }

    public void writeNbt(NbtCompound nbttagcompound)
    {
        super.writeNbt(nbttagcompound);
        nbttagcompound.putBoolean("Tamed", getTamed());
        nbttagcompound.putInt("TypeInt", getType());
        nbttagcompound.putFloat("Age", getAge());
        nbttagcompound.putBoolean("Adult", getAdult());
    }

    public void readNbt(NbtCompound nbttagcompound)
    {
        super.readNbt(nbttagcompound);
        setTamed(nbttagcompound.getBoolean("Tamed"));
        setType(nbttagcompound.getInt("TypeInt"));
        setAge(nbttagcompound.getFloat("Age"));
        setAdult(nbttagcompound.getBoolean("Adult"));
    }

    public boolean canSpawn()
    {
        return mocr.mocreaturesGlass.watermobs.fishfreq > 0 && super.canSpawn();
    }

    protected void initDataTracker() {
        super.initDataTracker();
        dataTracker.startTracking(16, (byte) 0); //Type
        dataTracker.startTracking(17, (int) 0); //Age
        dataTracker.startTracking(18, (byte) 0); //Adult
        dataTracker.startTracking(19, (byte) 0); //Tamed
    }

    mod_mocreatures mocr = new mod_mocreatures();

    public int gestationtime;
    public boolean eaten;

//    public int typeint;
//    public float b;
//    public boolean adult;
//    public boolean tamed;

    public boolean typechosen;

    @Override
    public Identifier getHandlerIdentifier() {
        return Identifier.of(mod_mocreatures.MOD_ID, "Fishy");
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
    //AGE
    public void setAge(float age)
    {
        dataTracker.set(17, Float.floatToRawIntBits(age));
    }

    public float getAge()
    {
        return Float.intBitsToFloat(dataTracker.getInt(17));
    }
    //ADULT
    public boolean getAdult()
    {
        return (dataTracker.getByte(18) & 1) != 0;
    }

    public void setAdult(boolean flag)
    {
        if(flag)
        {
            dataTracker.set(18, (byte) 1);
        } else
        {
            dataTracker.set(18, (byte) 0);
        }
    }
    //TAMED
    public boolean getTamed()
    {
        return (dataTracker.getByte(19) & 1) != 0;
    }

    public void setTamed(boolean flag)
    {
        if(flag)
        {
            dataTracker.set(19, (byte) 1);
        } else
        {
            dataTracker.set(19, (byte) 0);
        }
    }
}
