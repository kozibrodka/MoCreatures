package net.kozibrodka.mocreatures.entity;


import net.kozibrodka.mocreatures.events.mod_mocreatures;
import net.kozibrodka.mocreatures.mixin.WalkingBaseAccesor;
import net.kozibrodka.mocreatures.mocreatures.MoCreatureRacial;
import net.minecraft.entity.Entity;
import net.minecraft.entity.mob.MonsterEntity;
import net.minecraft.entity.passive.AnimalEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.nbt.NbtCompound;
import net.minecraft.world.World;
import net.modificationstation.stationapi.api.util.Identifier;
import net.modificationstation.stationapi.api.server.entity.MobSpawnDataProvider;

import java.util.List;


public class EntityBunny extends AnimalEntity implements MobSpawnDataProvider, MoCreatureRacial
{

    public EntityBunny(World world)
    {
        super(world);
        a = false;
//        adult = true;
        setAge(0.5F);
        movementSpeed = 1.5F;
//        texture = "/assets/mocreatures/stationapi/textures/mob/bunny.png";
        standingEyeHeight = -0.16F;
        setBoundingBoxSpacing(0.4F, 0.4F);
        health = 4;
        j = random.nextInt(64);
        i = 0;
//        typeint = 0;
        typechosen = false;
        killedByOtherEntity = true;
    }

    public void chooseType(int typeint)
    {
            if(typeint == 1)
            {
                texture = "/assets/mocreatures/stationapi/textures/mob/bunny.png";
            } else
            if(typeint == 2)
            {
                texture = "/assets/mocreatures/stationapi/textures/mob/bunnyb.png";
            } else
            if(typeint == 3)
            {
                texture = "/assets/mocreatures/stationapi/textures/mob/bunnyc.png";
            } else
            if(typeint == 4)
            {
                texture = "/assets/mocreatures/stationapi/textures/mob/bunnyd.png";
            }
    }

    public int getRandomRace(){
        int k = random.nextInt(100);
        if(k <= 25)
        {
            return 1;
        } else
        if(k <= 50)
        {
            return 2;
        } else
        if(k <= 75)
        {
            return 3;
        } else
        {
            return 4;
        }
    }

    public void tickMovement()
    {
        if(!typechosen && world.isRemote && getType() != 0){
            typechosen = true;
            chooseType(getType());
        }
        if(!getAdult() && random.nextInt(200) == 0 && !world.isRemote)
        {
            setAge(getAge()+0.01F);
            if(getAge() >= 1.0F)
            {
                setAdult(true);
                //TODO: DORASTANIE? manioulate age for if statemtnt
            }
        }
        super.tickMovement();
    }

    public void tick()
    {
        super.tick();
        if(!getTamed() || !getAdult() || vehicle != null)
        {
            return;
        }
        if(j < 1023)
        {
            j++;
        } else
        if(i < 127)
        {
            i++;
        } else
        {
            int k = 0;
            List list = world.getEntities(this, boundingBox.expand(16D, 16D, 16D));
            for(int l = 0; l < list.size(); l++)
            {
                Entity entity = (Entity)list.get(l);
                if(entity instanceof EntityBunny)
                {
                    k++;
                }
            }

            if(k > 12)
            {
                proceed();
                return;
            }
            List list1 = world.getEntities(this, boundingBox.expand(1.0D, 1.0D, 1.0D));
            boolean flag = false;
            for(int i1 = 0; i1 < list.size(); i1++)
            {
                Entity entity1 = (Entity)list1.get(i1);
                if(!(entity1 instanceof EntityBunny) || entity1 == this)
                {
                    continue;
                }
                EntityBunny entitybunny = (EntityBunny)entity1;
                if(entitybunny.vehicle != null || entitybunny.j < 1023 || !entitybunny.getAdult())
                {
                    continue;
                }
                EntityBunny entitybunny1 = new EntityBunny(world);
                entitybunny1.setPosition(x, y, z);
                entitybunny1.setAdult(false);
                world.spawnEntity(entitybunny1);
                world.playSound(this, "mob.chickenplop", 1.0F, (random.nextFloat() - random.nextFloat()) * 0.2F + 1.0F);
                proceed();
                entitybunny.proceed();
                flag = true;
                break;
            }

            if(!flag)
            {
                k = random.nextInt(16);
            }
        }
    }

    protected void onLanding(float f)
    {
    }

    protected void tickLiving()
    {
        if(onGround && (velocityX > 0.050000000000000003D || velocityZ > 0.050000000000000003D || velocityX < -0.050000000000000003D || velocityZ < -0.050000000000000003D))
        {
            velocityY = 0.45000000000000001D;
        }
        if(!h)
        {
            super.tickLiving();
        } else
        if(onGround)
        {
            h = false;
            world.playSound(this, "mocreatures:rabbitland", 1.0F, (random.nextFloat() - random.nextFloat()) * 0.2F + 1.0F);
            List list = world.getEntities(this, boundingBox.expand(12D, 12D, 12D));
            for(int k = 0; k < list.size(); k++)
            {
                Entity entity = (Entity)list.get(k);
                if(entity instanceof MonsterEntity)
                {
                    MonsterEntity entitymob = (MonsterEntity)entity;
                    ((WalkingBaseAccesor)entitymob).setTarget(this);
                }
            }

        }
    }

    public boolean interact(PlayerEntity entityplayer)
    {
//        ItemInstance itemstack = entityplayer.inventory.getHeldItem();
        yaw = entityplayer.yaw;
        setVehicle(entityplayer);
        if(vehicle == null)
        {
            h = true;
        } else
        {
            world.playSound(this, "mocreatures:rabbitlift", 1.0F, (random.nextFloat() - random.nextFloat()) * 0.2F + 1.0F);
        }
        velocityX = entityplayer.velocityX * 5D;
        velocityY = entityplayer.velocityY / 2D + 0.5D;
        velocityZ = entityplayer.velocityZ * 5D;
        setTamed(true);
        return true;
    }

    public double getStandingEyeHeight()
    {
        if(vehicle instanceof PlayerEntity)
        {
            return (double)(standingEyeHeight - 1.15F);
        } else
        {
            return (double)standingEyeHeight;
        }
    }

    protected String getRandomSound()
    {
        return null;
    }

    public void proceed()
    {
        i = 0;
        j = random.nextInt(64);
    }

    protected String getHurtSound()
    {
        return "mocreatures:rabbithurt";
    }

    public void applyKnockback(Entity entity, int k, double d, double d2)
    {
        super.applyKnockback(entity, k, d, d2);
    }

    protected String getDeathSound()
    {
        return "mocreatures:rabbitdeath";
    }

    public boolean maxNumberReached()
    {
        int k = 0;
        for(int l = 0; l < world.entities.size(); l++)
        {
            Entity entity = (Entity)world.entities.get(l);
            if(entity instanceof EntityBunny)
            {
                k++;
            }
        }

        return false;
    }

    public void markDead()
    {
        super.markDead();
    }

    public void writeNbt(NbtCompound nbttagcompound)
    {
        super.writeNbt(nbttagcompound);
        nbttagcompound.putInt("TypeInt", getType());
        nbttagcompound.putBoolean("Tamed", getTamed());
        nbttagcompound.putFloat("Edad", getAge());
        nbttagcompound.putBoolean("Adult", getAdult());
    }

    public void readNbt(NbtCompound nbttagcompound)
    {
        super.readNbt(nbttagcompound);
        setType(nbttagcompound.getInt("TypeInt"));
        setAge(nbttagcompound.getFloat("Edad"));
        setTamed(nbttagcompound.getBoolean("Tamed"));
        setAdult(nbttagcompound.getBoolean("Adult"));
    }

    protected void initDataTracker() {
        super.initDataTracker();
        dataTracker.startTracking(16, (byte) 0); //Type
        dataTracker.startTracking(17, (int) 0); //Age
        dataTracker.startTracking(18, (byte) 0); //Adult
        dataTracker.startTracking(19, (byte) 0); //Tamed
    }

    public boolean canSpawn()
    {
        return mocr.mocreaturesGlass.animals.bunnyfreq > 0 && super.canSpawn();
    }

    protected boolean canDespawn()
    {
        return !getTamed();
    }

    mod_mocreatures mocr = new mod_mocreatures();
    public boolean a;
    public float d1;
    public boolean h;
    public int j;
    public int i;

//    public int typeint;
//    public float edad;
//    public boolean tamed;
//    public boolean adult;

    public boolean typechosen;

    @Override
    public Identifier getHandlerIdentifier() {
        return Identifier.of(mod_mocreatures.MOD_ID, "Bunny");
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
