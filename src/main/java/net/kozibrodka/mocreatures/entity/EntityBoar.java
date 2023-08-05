// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) braces deadcode 

package net.kozibrodka.mocreatures.entity;

import net.kozibrodka.mocreatures.events.mod_mocreatures;
import net.minecraft.entity.EntityBase;
import net.minecraft.entity.Item;
import net.minecraft.entity.Living;
import net.minecraft.entity.animal.Pig;

import net.minecraft.entity.monster.MonsterBase;
import net.minecraft.entity.player.PlayerBase;
import net.minecraft.level.Level;
import net.minecraft.util.io.CompoundTag;
import net.modificationstation.stationapi.api.registry.Identifier;
import net.modificationstation.stationapi.api.server.entity.MobSpawnDataProvider;

import java.util.List;


public class EntityBoar extends Pig implements MobSpawnDataProvider
{

    public EntityBoar(Level world)
    {
        super(world);
        texture = "/assets/mocreatures/stationapi/textures/mob/boar.png";
        setSize(0.9F, 0.9F);
        health = 10;
        force = 1;
        attackRange = 1.0D;
    }

    public void updateDespawnCounter()
    {
        if(level.difficulty == 1)
        {
            attackRange = 2D;
            force = 1;
        } else
        if(level.difficulty > 1)
        {
            attackRange = 3D;
            force = 2;
        }
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
            PlayerBase entityplayer = level.getClosestPlayerTo(this, attackRange);
            if(entityplayer != null && rand.nextInt(50) == 0)
            {
                return entityplayer;
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
            if(!(entity1 instanceof Living) || entity1 == entity || entity1 == entity.passenger || entity1 == entity.vehicle || (entity1 instanceof PlayerBase) || (entity1 instanceof MonsterBase) || height <= entity1.height || width <= entity1.width)
            {
                continue;
            }
            double d2 = entity1.squaredDistanceTo(entity.y, entity.z, entity.velocityX);
            if((d < 0.0D || d2 < d * d) && (d1 == -1D || d2 < d1) && ((Living)entity1).method_928(entity))
            {
                d1 = d2;
                entityliving = (Living)entity1;
            }
        }

        return entityliving;
    }

    public boolean damage(EntityBase entityBase, int i)
    {
        if(super.damage(entityBase, i))
        {
            if(passenger == entityBase || vehicle == entityBase)
            {
                return true;
            }
            if(entityBase != this && level.difficulty > 0)
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

    public void writeCustomDataToTag(CompoundTag nbttagcompound)
    {
        super.writeCustomDataToTag(nbttagcompound);
    }

    public void readCustomDataFromTag(CompoundTag nbttagcompound)
    {
        super.readCustomDataFromTag(nbttagcompound);
    }

    public boolean canSpawn()
    {
        return mocr.mocreaturesGlass.huntercreatures.boarfreq > 0 && super.canSpawn();
    }

    mod_mocreatures mocr = new mod_mocreatures();
    protected int force;
    protected double attackRange;

    @Override
    public Identifier getHandlerIdentifier() {
        return Identifier.of(mod_mocreatures.MOD_ID, "Boar");
    }
}
