package net.kozibrodka.mocreatures.entity;


import net.kozibrodka.mocreatures.events.mod_mocreatures;
import net.minecraft.entity.EntityBase;
import net.minecraft.entity.Item;
import net.minecraft.entity.Living;
import net.minecraft.entity.animal.AnimalBase;
import net.minecraft.entity.animal.Wolf;
import net.minecraft.entity.monster.MonsterBase;
import net.minecraft.entity.player.PlayerBase;
import net.minecraft.item.ItemBase;
import net.minecraft.item.ItemInstance;
import net.minecraft.level.Level;
import net.minecraft.util.io.CompoundTag;

import java.util.List;


public class EntityBear extends AnimalBase
{

    public EntityBear(Level world)
    {
        super(world);
        bearboolean = false;
        texture = "/assets/mocreatures/stationapi/textures/mob/bear.png";
        setSize(0.9F, 1.3F);
        health = 25;
        force = 5;
        attackRange = 16D;
    }

    public void updateDespawnCounter()
    {
        super.updateDespawnCounter();
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
        if(level.difficulty > 0)
        {
            float f = getBrightnessAtEyes(1.0F);
            if(f < 0.5F)
            {
                PlayerBase entityplayer = level.getClosestPlayerTo(this, attackRange);
                if(entityplayer != null)
                {
                    return entityplayer;
                }
            }
            if(rand.nextInt(80) == 0)
            {
                Living entityliving = getClosestTarget(this, 10D);
                return entityliving;
            }
        }
        return null;
    }

    public Living getClosestTarget(EntityBase entity, double d)
    {
        double d1 = -1D;
        Living entityliving = null;
        List list = level.getEntities(this, boundingBox.expand(d, d, d));
        for(int i = 0; i < list.size(); i++)
        {
            EntityBase entity1 = (EntityBase)list.get(i);
            if(!(entity1 instanceof Living) || entity1 == entity || entity1 == entity.passenger || entity1 == entity.vehicle || (entity1 instanceof PlayerBase) || (entity1 instanceof MonsterBase) || (entity1 instanceof EntityBear) || (entity1 instanceof EntityBigCat) || (entity1 instanceof EntityKittyBed) || (entity1 instanceof EntityLitterBox) || (entity1 instanceof Wolf) && !mocr.mocreaturesGlass.huntercreatures.attackwolves || (entity1 instanceof EntityHorse) && !mocr.mocreaturesGlass.huntercreatures.attackhorses)
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

    public boolean damage(EntityBase entitybase, int i)
    {
        if(super.damage(entitybase, i))
        {
            if(passenger == entitybase || vehicle == entitybase)
            {
                return true;
            }
            if(entitybase != this && level.difficulty > 0)
            {
                entity = entitybase;
            }
            return true;
        } else
        {
            return false;
        }
    }

    protected void tryAttack(EntityBase entity, float f)
    {
        if((double)f < 2.5D && entity.boundingBox.maxY > boundingBox.minY && entity.boundingBox.minY < boundingBox.maxY)
        {
            attackTime = 20;
            entity.damage(this, force);
            if(!(entity instanceof PlayerBase))
            {
                destroyDrops(this, 3D);
            }
        }
    }

    public void writeCustomDataToTag(CompoundTag nbttagcompound)
    {
        super.writeCustomDataToTag(nbttagcompound);
        nbttagcompound.put("BearBoolean", bearboolean);
    }

    public void readCustomDataFromTag(CompoundTag nbttagcompound)
    {
        super.readCustomDataFromTag(nbttagcompound);
        bearboolean = nbttagcompound.getBoolean("BearBoolean");
    }

    protected String getAmbientSound()
    {
        return "mocreatures:beargrunt";
    }

    protected String getHurtSound()
    {
        return "mocreatures:bearhurt";
    }

    protected String getDeathSound()
    {
        return "mocreatures:beardying";
    }

    protected void getDrops()
    {
        int i = rand.nextInt(3);
        for(int j = 0; j < i; j++)
        {
            dropItem(new ItemInstance(getMobDrops(), 1, 0), 0.0F);
        }

        int k = rand.nextInt(2);
        for(int j = 0; j < k; j++)
        {
            dropItem(new ItemInstance(mod_mocreatures.wildleather, 1, 0), 0.0F);
        }
    }

    protected int getMobDrops()
    {
        return ItemBase.rawFish.id;
    }

    public int getLimitPerChunk()
    {
        return 2;
    }

    public void destroyDrops(EntityBase entity, double d)
    {
        List list = level.getEntities(entity, entity.boundingBox.expand(d, d, d));
        for(int i = 0; i < list.size(); i++)
        {
            EntityBase entity1 = (EntityBase)list.get(i);
            if(!(entity1 instanceof Item))
            {
                continue;
            }
            Item entityitem = (Item)entity1;
            if(entityitem != null && entityitem.age < 50 && mocr.mocreaturesGlass.huntercreatures.destroyitems)
            {
                entityitem.remove();
            }
        }

    }

    public void remove()
    {
        super.remove();
    }

    public boolean cS2()
    {
        return super.canSpawn();
    }

    public boolean canSpawn()
    {
        return mocr.mocreaturesGlass.huntercreatures.bearfreq > 0 && super.canSpawn();
    }

    mod_mocreatures mocr = new mod_mocreatures();
    protected double attackRange;
    public boolean bearboolean;
    protected int force;
}

