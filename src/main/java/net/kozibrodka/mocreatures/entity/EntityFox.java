// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) braces deadcode 

package net.kozibrodka.mocreatures.entity;

import net.kozibrodka.mocreatures.events.mod_mocreatures;
import net.minecraft.entity.EntityBase;
import net.minecraft.entity.Item;
import net.minecraft.entity.Living;
import net.minecraft.entity.animal.AnimalBase;
import net.minecraft.entity.monster.MonsterBase;
import net.minecraft.entity.player.PlayerBase;
import net.minecraft.item.ItemBase;
import net.minecraft.item.ItemInstance;
import net.minecraft.level.Level;
import net.minecraft.util.io.CompoundTag;
import net.modificationstation.stationapi.api.registry.Identifier;
import net.modificationstation.stationapi.api.server.entity.MobSpawnDataProvider;

import java.util.List;

public class EntityFox extends AnimalBase implements MobSpawnDataProvider
{

    public EntityFox(Level world)
    {
        super(world);
        foxboolean = false;
        texture = "/assets/mocreatures/stationapi/textures/mob/fox.png";
        setSize(0.9F, 1.3F);
        health = 15;
        force = 2;
        attackRange = 4D;
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
        if(rand.nextInt(80) == 0 && level.difficulty > 0)
        {
            Living entityliving = getClosestTarget(this, 8D);
            return entityliving;
        } else
        {
            return null;
        }
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
        nbttagcompound.put("FoxBoolean", foxboolean);
    }

    public void readCustomDataFromTag(CompoundTag nbttagcompound)
    {
        super.readCustomDataFromTag(nbttagcompound);
        foxboolean = nbttagcompound.getBoolean("FoxBoolean");
    }

    protected float getSoundVolume()
    {
        return 0.3F;
    }

    protected String getAmbientSound()
    {
        return "mocreatures:foxcall";
    }

    protected String getHurtSound()
    {
        return "mocreatures:foxhurt";
    }

    protected String getDeathSound()
    {
        return "mocreatures:foxdying";
    }

    protected void getDrops()
    {
        int i = rand.nextInt(3);
        for(int j = 0; j < i; j++)
        {
            dropItem(new ItemInstance(getMobDrops(), 1, 0), 0.0F);
        }
        if(mocr.mocreaturesGlass.balancesettings.balance_drop) {
            int a = rand.nextInt(10);
            if (a < 8) {
                int k = rand.nextInt(2);
                for (int j = 0; j < k; j++) {
                    dropItem(new ItemInstance(mod_mocreatures.wildleather, 1, 0), 0.0F);
                }
            }
        }
    }

    protected int getMobDrops()
    {
        return ItemBase.leather.id;
    }

    public int getLimitPerChunk()
    {
        return 1;
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

    public boolean canSpawn()
    {
        return mocr.mocreaturesGlass.huntercreatures.foxfreq > 0 && super.canSpawn();
    }

    mod_mocreatures mocr = new mod_mocreatures();
    protected double attackRange;
    public boolean foxboolean;
    protected int force;

    @Override
    public Identifier getHandlerIdentifier() {
        return Identifier.of(mod_mocreatures.MOD_ID, "Fox");
    }
}
