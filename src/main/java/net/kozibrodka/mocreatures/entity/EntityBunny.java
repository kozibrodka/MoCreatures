package net.kozibrodka.mocreatures.entity;


import net.kozibrodka.mocreatures.events.mod_mocreatures;
import net.kozibrodka.mocreatures.mixin.WalkingBaseAccesor;
import net.minecraft.entity.EntityBase;
import net.minecraft.entity.animal.AnimalBase;
import net.minecraft.entity.monster.MonsterBase;
import net.minecraft.entity.player.PlayerBase;
import net.minecraft.item.ItemInstance;
import net.minecraft.level.Level;
import net.minecraft.util.io.CompoundTag;
import net.modificationstation.stationapi.api.util.Identifier;
import net.modificationstation.stationapi.api.server.entity.MobSpawnDataProvider;

import java.util.List;


public class EntityBunny extends AnimalBase implements MobSpawnDataProvider
{

    public EntityBunny(Level world)
    {
        super(world);
        a = false;
        adult = true;
        edad = 0.5F;
        movementSpeed = 1.5F;
        texture = "/assets/mocreatures/stationapi/textures/mob/bunny.png";
        standingEyeHeight = -0.16F;
        setSize(0.4F, 0.4F);
        health = 4;
        j = rand.nextInt(64);
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
            int k = rand.nextInt(100);
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

    public void updateDespawnCounter()
    {
        if(!adult && rand.nextInt(200) == 0)
        {
            edad += 0.01F;
            if(edad >= 1.0F)
            {
                adult = true;
            }
        }
        super.updateDespawnCounter();
    }

    public void tick()
    {
        super.tick();
        if(!tamed || !adult || vehicle != null)
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
            List list = level.getEntities(this, boundingBox.expand(16D, 16D, 16D));
            for(int l = 0; l < list.size(); l++)
            {
                EntityBase entity = (EntityBase)list.get(l);
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
            List list1 = level.getEntities(this, boundingBox.expand(1.0D, 1.0D, 1.0D));
            boolean flag = false;
            for(int i1 = 0; i1 < list.size(); i1++)
            {
                EntityBase entity1 = (EntityBase)list1.get(i1);
                if(!(entity1 instanceof EntityBunny) || entity1 == this)
                {
                    continue;
                }
                EntityBunny entitybunny = (EntityBunny)entity1;
                if(entitybunny.vehicle != null || entitybunny.j < 1023 || !entitybunny.adult)
                {
                    continue;
                }
                EntityBunny entitybunny1 = new EntityBunny(level);
                entitybunny1.setPosition(x, y, z);
                entitybunny1.adult = false;
                level.spawnEntity(entitybunny1);
                level.playSound(this, "mob.chickenplop", 1.0F, (rand.nextFloat() - rand.nextFloat()) * 0.2F + 1.0F);
                proceed();
                entitybunny.proceed();
                flag = true;
                break;
            }

            if(!flag)
            {
                k = rand.nextInt(16);
            }
        }
    }

    protected void handleFallDamage(float f)
    {
    }

    protected void tickHandSwing()
    {
        if(onGround && (velocityX > 0.050000000000000003D || velocityZ > 0.050000000000000003D || velocityX < -0.050000000000000003D || velocityZ < -0.050000000000000003D))
        {
            velocityY = 0.45000000000000001D;
        }
        if(!h)
        {
            super.tickHandSwing();
        } else
        if(onGround)
        {
            h = false;
            level.playSound(this, "mocreatures:rabbitland", 1.0F, (rand.nextFloat() - rand.nextFloat()) * 0.2F + 1.0F);
            List list = level.getEntities(this, boundingBox.expand(12D, 12D, 12D));
            for(int k = 0; k < list.size(); k++)
            {
                EntityBase entity = (EntityBase)list.get(k);
                if(entity instanceof MonsterBase)
                {
                    MonsterBase entitymob = (MonsterBase)entity;
                    ((WalkingBaseAccesor)entitymob).setEntity(this);
                }
            }

        }
    }

    public boolean interact(PlayerBase entityplayer)
    {
//        ItemInstance itemstack = entityplayer.inventory.getHeldItem();
        yaw = entityplayer.yaw;
        startRiding(entityplayer);
        if(vehicle == null)
        {
            h = true;
        } else
        {
            level.playSound(this, "mocreatures:rabbitlift", 1.0F, (rand.nextFloat() - rand.nextFloat()) * 0.2F + 1.0F);
        }
        velocityX = entityplayer.velocityX * 5D;
        velocityY = entityplayer.velocityY / 2D + 0.5D;
        velocityZ = entityplayer.velocityZ * 5D;
        tamed = true;
        return true;
    }

    public double getHeightOffset()
    {
        if(vehicle instanceof PlayerBase)
        {
            return (double)(standingEyeHeight - 1.15F);
        } else
        {
            return (double)standingEyeHeight;
        }
    }

    protected String getAmbientSound()
    {
        return null;
    }

    public void proceed()
    {
        i = 0;
        j = rand.nextInt(64);
    }

    protected String getHurtSound()
    {
        return "mocreatures:rabbithurt";
    }

    public void method_925(EntityBase entity, int k, double d, double d2)
    {
        super.method_925(entity, k, d, d2);
    }

    protected String getDeathSound()
    {
        return "mocreatures:rabbitdeath";
    }

    public boolean maxNumberReached()
    {
        int k = 0;
        for(int l = 0; l < level.entities.size(); l++)
        {
            EntityBase entity = (EntityBase)level.entities.get(l);
            if(entity instanceof EntityBunny)
            {
                k++;
            }
        }

        return false;
    }

    public void remove()
    {
        super.remove();
    }

    public void writeCustomDataToTag(CompoundTag nbttagcompound)
    {
        super.writeCustomDataToTag(nbttagcompound);
        nbttagcompound.put("TypeInt", typeint);
        nbttagcompound.put("Tamed", tamed);
        nbttagcompound.put("Edad", edad);
        nbttagcompound.put("Adult", adult);
    }

    public void readCustomDataFromTag(CompoundTag nbttagcompound)
    {
        super.readCustomDataFromTag(nbttagcompound);
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
