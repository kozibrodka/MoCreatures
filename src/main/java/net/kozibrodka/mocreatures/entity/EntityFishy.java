// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) braces deadcode 

package net.kozibrodka.mocreatures.entity;

import net.kozibrodka.mocreatures.events.mod_mocreatures;
import net.kozibrodka.mocreatures.mixin.EntityBaseAccesor;
import net.minecraft.entity.EntityBase;
import net.minecraft.entity.Living;
import net.minecraft.entity.animal.Wolf;
import net.minecraft.entity.player.PlayerBase;
import net.minecraft.item.ItemBase;
import net.minecraft.item.ItemInstance;
import net.minecraft.level.Level;
import net.minecraft.util.io.CompoundTag;

import java.util.List;

public class EntityFishy extends EntityCustomWM
{

    public EntityFishy(Level world)
    {
        super(world);
        texture = "/assets/mocreatures/stationapi/textures/mob/fishy1.png";
        setSize(0.3F, 0.3F);
        health = 4;
        typeint = 0;
        typechosen = false;
        b = 1.0F;
        adult = false;
        tamed = false;
        field_1045 = true;
    }

    public void setTame()
    {
        tamed = true;
    }

    public boolean istamed()
    {
        return tamed;
    }

    public void updateDespawnCounter()
    {
        super.updateDespawnCounter();
        if(!adult && rand.nextInt(100) == 0)
        {
            b += 0.02F;
            if(b >= 1.0F)
            {
                adult = true;
            }
        }
        if(!ReadyforParenting(this))
        {
            return;
        }
        int i = 0;
        List list = level.getEntities(this, boundingBox.expand(4D, 3D, 4D));
        for(int j = 0; j < list.size(); j++)
        {
            EntityBase entity = (EntityBase)list.get(j);
            if(entity instanceof EntityFishy)
            {
                i++;
            }
        }

        if(i > 1)
        {
            return;
        }
        List list1 = level.getEntities(this, boundingBox.expand(4D, 2D, 4D));
        for(int k = 0; k < list.size(); k++)
        {
            EntityBase entity1 = (EntityBase)list1.get(k);
            if(!(entity1 instanceof EntityFishy) || entity1 == this)
            {
                continue;
            }
            EntityFishy entityfishy = (EntityFishy)entity1;
            if(!ReadyforParenting(this) || !ReadyforParenting(entityfishy) || typeint != entityfishy.typeint)
            {
                continue;
            }
            if(rand.nextInt(100) == 0)
            {
                gestationtime++;
            }
            if(gestationtime <= 50)
            {
                continue;
            }
            int l = rand.nextInt(3) + 1;
            for(int i1 = 0; i1 < l; i1++)
            {
                EntityFishy entityfishy1 = new EntityFishy(level);
                entityfishy1.setPosition(x, y, z);
                level.spawnEntity(entityfishy1);
                level.playSound(this, "mob.chickenplop", 1.0F, (rand.nextFloat() - rand.nextFloat()) * 0.2F + 1.0F);
                eaten = false;
                entityfishy.eaten = false;
                gestationtime = 0;
                entityfishy.gestationtime = 0;
                entityfishy1.tamed = true;
                entityfishy1.b = 0.2F;
                entityfishy1.adult = false;
                entityfishy1.setType(typeint);
            }

            break;
        }

    }

    public boolean ReadyforParenting(EntityFishy entityfishy)
    {
        return entityfishy.tamed && entityfishy.eaten && entityfishy.adult;
    }

    protected void tickHandSwing(){
        if(this.entity instanceof PlayerBase){
            PlayerBase uciekinier = level.getClosestPlayerTo(this, 16D);
            if(uciekinier == null && entity.isAlive()){
                if(rand.nextInt(30) == 0)
                {
                    entity = null;
                }
            }
        }
        super.tickHandSwing();
    }

    protected EntityBase getAttackTarget()
    {
        if(level.difficulty > 0 && b >= 1.0F && typeint == 10)
        {
            PlayerBase entityplayer = level.getClosestPlayerTo(this, 16D);
            if(entityplayer != null && ((EntityBaseAccesor)entityplayer).getField_1612() && !tamed)
            {
                return entityplayer;
            }
            if(rand.nextInt(30) == 0)
            {
                Living entityliving = FindTarget(this, 16D);
                if(entityliving != null && ((EntityBaseAccesor)entityliving).getField_1612())
                {
                    return entityliving;
                }
            }
        }
        return null;
    }

    public Living FindTarget(EntityBase entity, double d)
    {
        double d1 = -1D;
        Living entityliving = null;
        List list = level.getEntities(this, boundingBox.expand(d, d, d));
        for(int i = 0; i < list.size(); i++)
        {
            EntityBase entity1 = (EntityBase)list.get(i);
            if(!(entity1 instanceof Living) || (entity1 instanceof EntityCustomWM) || (entity1 instanceof EntitySharkEgg) || (entity1 instanceof EntityFishyEgg) || (entity1 instanceof PlayerBase) || (entity1 instanceof EntityHorse) && !mocr.mocreaturesGlass.huntercreatures.attackhorses || (entity1 instanceof Wolf) && !mocr.mocreaturesGlass.huntercreatures.attackwolves)
            {
                continue;
            }
            double d2 = entity1.squaredDistanceTo(entity.x, entity.y, entity.z);
            if((d < 0.0D || d2 < d * d) && (d1 == -1D || d2 < d1) && ((Living)entity1).method_928(entity))
            {
                d1 = d2;
                entityliving = (Living)entity1;
            }
        }

        return entityliving;
    }

    public void remove()
    {
        if(tamed && health > 0)
        {
            return;
        } else
        {
            super.remove();
            return;
        }
    }

    public boolean damage(EntityBase entityBase, int i)
    {
        if(super.damage(entityBase, i))
        {
            if(passenger == entityBase || vehicle == entityBase)
            {
                return true;
            }
            if(entityBase != this)
            {
                entity = entityBase;
            }
            return true;
        } else
        {
            return false;
        }
    }

    protected void tryAttack(EntityBase entity, float f)
    {
        if((double)f < 2D && entity.boundingBox.maxY > boundingBox.minY && entity.boundingBox.minY < boundingBox.maxY)
        {
            attackTime = 20;
            entity.damage(this, 1);
        }
    }

    public void setType(int i)
    {
        typeint = i;
        typechosen = false;
        chooseType();
    }

    public void chooseType()
    {
        if(typeint == 0)
        {
            int i = rand.nextInt(100);
            if(i <= 9)
            {
                typeint = 1;
            } else
            if(i <= 19)
            {
                typeint = 2;
            } else
            if(i <= 29)
            {
                typeint = 3;
            } else
            if(i <= 39)
            {
                typeint = 4;
            } else
            if(i <= 49)
            {
                typeint = 5;
            } else
            if(i <= 59)
            {
                typeint = 6;
            } else
            if(i <= 69)
            {
                typeint = 7;
            } else
            if(i <= 79)
            {
                typeint = 8;
            } else
            if(i <= 89)
            {
                typeint = 9;
            } else
            {
                typeint = 10;
            }
            if(mocr.mocreaturesGlass.watermobs.spawnpiranha && typeint == 10)
            {
                typeint = 1;
            }
        }
        if(!typechosen)
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
        typechosen = true;
    }

    protected void getDrops()
    {
        int i = rand.nextInt(100);
        if(i < 70 && adult)
        {
            dropItem(new ItemInstance(ItemBase.rawFish.id, 1, 0), 0.0F);
        } else
        {
            int j = rand.nextInt(2) + 1;
            for(int k = 0; k < j; k++)
            {
                dropItem(new ItemInstance(mod_mocreatures.fishyegg, 1, 0), 0.0F);
            }

        }
    }

    public void writeCustomDataToTag(CompoundTag nbttagcompound)
    {
        super.writeCustomDataToTag(nbttagcompound);
        nbttagcompound.put("Tamed", tamed);
        nbttagcompound.put("TypeInt", typeint);
        nbttagcompound.put("Age", b);
        nbttagcompound.put("Adult", adult);
    }

    public void readCustomDataFromTag(CompoundTag nbttagcompound)
    {
        super.readCustomDataFromTag(nbttagcompound);
        tamed = nbttagcompound.getBoolean("Tamed");
        typeint = nbttagcompound.getInt("TypeInt");
        b = nbttagcompound.getFloat("Age");
        adult = nbttagcompound.getBoolean("Adult");
    }

    public boolean canSpawn()
    {
        return mocr.mocreaturesGlass.watermobs.fishfreq > 0 && super.canSpawn();
    }

    mod_mocreatures mocr = new mod_mocreatures();

    public int typeint;
    public boolean typechosen;
    public float b;
    public boolean adult;
    public boolean tamed;
    public int gestationtime;
    public boolean eaten;
    public boolean hungry;
}
