package net.kozibrodka.mocreatures.entity;


import net.kozibrodka.mocreatures.events.mod_mocreatures;
import net.kozibrodka.mocreatures.mixin.WalkingBaseAccesor;
import net.minecraft.entity.Entity;
import net.minecraft.entity.mob.MonsterEntity;
import net.minecraft.entity.passive.AnimalEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.nbt.NbtCompound;
import net.minecraft.world.World;
import net.modificationstation.stationapi.api.util.Identifier;
import net.modificationstation.stationapi.api.server.entity.MobSpawnDataProvider;

import java.util.List;


public class EntityBunny extends AnimalEntity implements MobSpawnDataProvider
{

    public EntityBunny(World world)
    {
        super(world);
        a = false;
        adult = true;
        edad = 0.5F;
        movementSpeed = 1.5F;
        texture = "/assets/mocreatures/stationapi/textures/mob/bunny.png";
        eyeHeight = -0.16F;
        setBoundingBoxSpacing(0.4F, 0.4F);
        health = 4;
        j = random.nextInt(64);
        i = 0;
        typeint = 0;
        typechosen = false;
        field_1045 = true;
    }

    public void setType(int k)
    {
        typeint = k;
        typechosen = false;
        chooseType();
    }

    public void chooseType()
    {
        if(typeint == 0)
        {
            int k = random.nextInt(100);
            if(k <= 25)
            {
                typeint = 1;
            } else
            if(k <= 50)
            {
                typeint = 2;
            } else
            if(k <= 75)
            {
                typeint = 3;
            } else
            {
                typeint = 4;
            }
        }
        if(!typechosen)
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
        typechosen = true;
    }

    public void method_937()
    {
        if(!adult && random.nextInt(200) == 0)
        {
            edad += 0.01F;
            if(edad >= 1.0F)
            {
                adult = true;
            }
        }
        super.method_937();
    }

    public void tick()
    {
        super.tick();
        if(!tamed || !adult || field_1595 != null)
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
                if(entitybunny.field_1595 != null || entitybunny.j < 1023 || !entitybunny.adult)
                {
                    continue;
                }
                EntityBunny entitybunny1 = new EntityBunny(world);
                entitybunny1.method_1340(x, y, z);
                entitybunny1.adult = false;
                world.method_210(entitybunny1);
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

    protected void method_1389(float f)
    {
    }

    protected void method_910()
    {
        if(field_1623 && (velocityX > 0.050000000000000003D || velocityZ > 0.050000000000000003D || velocityX < -0.050000000000000003D || velocityZ < -0.050000000000000003D))
        {
            velocityY = 0.45000000000000001D;
        }
        if(!h)
        {
            super.method_910();
        } else
        if(field_1623)
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

    public boolean method_1323(PlayerEntity entityplayer)
    {
//        ItemInstance itemstack = entityplayer.inventory.getHeldItem();
        yaw = entityplayer.yaw;
        method_1376(entityplayer);
        if(field_1595 == null)
        {
            h = true;
        } else
        {
            world.playSound(this, "mocreatures:rabbitlift", 1.0F, (random.nextFloat() - random.nextFloat()) * 0.2F + 1.0F);
        }
        velocityX = entityplayer.velocityX * 5D;
        velocityY = entityplayer.velocityY / 2D + 0.5D;
        velocityZ = entityplayer.velocityZ * 5D;
        tamed = true;
        return true;
    }

    public double method_1385()
    {
        if(field_1595 instanceof PlayerEntity)
        {
            return (double)(eyeHeight - 1.15F);
        } else
        {
            return (double)eyeHeight;
        }
    }

    protected String method_911()
    {
        return null;
    }

    public void proceed()
    {
        i = 0;
        j = random.nextInt(64);
    }

    protected String method_912()
    {
        return "mocreatures:rabbithurt";
    }

    public void method_925(Entity entity, int k, double d, double d2)
    {
        super.method_925(entity, k, d, d2);
    }

    protected String method_913()
    {
        return "mocreatures:rabbitdeath";
    }

    public boolean maxNumberReached()
    {
        int k = 0;
        for(int l = 0; l < world.field_198.size(); l++)
        {
            Entity entity = (Entity)world.field_198.get(l);
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
        nbttagcompound.putInt("TypeInt", typeint);
        nbttagcompound.putBoolean("Tamed", tamed);
        nbttagcompound.putFloat("Edad", edad);
        nbttagcompound.putBoolean("Adult", adult);
    }

    public void readNbt(NbtCompound nbttagcompound)
    {
        super.readNbt(nbttagcompound);
        typeint = nbttagcompound.getInt("TypeInt");
        edad = nbttagcompound.getFloat("Edad");
        tamed = nbttagcompound.getBoolean("Tamed");
        adult = nbttagcompound.getBoolean("Adult");
    }

    public boolean canSpawn()
    {
        return mocr.mocreaturesGlass.animals.bunnyfreq > 0 && super.canSpawn();
    }

    protected boolean method_940()
    {
        return !tamed;
    }

    mod_mocreatures mocr = new mod_mocreatures();
    public boolean a;
    public float d1;
    public boolean h;
    public int j;
    public int i;
    public int typeint;
    public boolean typechosen;
    public boolean tamed;
    public boolean adult;
    public float edad;

    @Override
    public Identifier getHandlerIdentifier() {
        return Identifier.of(mod_mocreatures.MOD_ID, "Bunny");
    }
}
