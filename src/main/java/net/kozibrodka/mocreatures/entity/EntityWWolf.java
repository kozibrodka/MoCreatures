// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) braces deadcode 

package net.kozibrodka.mocreatures.entity;

import net.kozibrodka.mocreatures.events.mod_mocreatures;
import net.minecraft.entity.EntityBase;
import net.minecraft.entity.Item;
import net.minecraft.entity.Living;
import net.minecraft.entity.animal.Cow;
import net.minecraft.entity.animal.Wolf;
import net.minecraft.entity.monster.MonsterBase;
import net.minecraft.entity.monster.MonsterEntityType;
import net.minecraft.entity.player.PlayerBase;
import net.minecraft.item.ItemBase;
import net.minecraft.level.Level;
import net.minecraft.util.io.CompoundTag;
import net.minecraft.util.maths.MathHelper;
import net.modificationstation.stationapi.api.registry.Identifier;
import net.modificationstation.stationapi.api.server.entity.MobSpawnDataProvider;

import java.util.List;


public class EntityWWolf extends MonsterBase
    implements MonsterEntityType, MobSpawnDataProvider
{

    public EntityWWolf(Level world)
    {
        super(world);
        wolfboolean = false;
        texture = "/assets/mocreatures/stationapi/textures/mob/wolfa.png";
        setSize(0.9F, 1.3F);
        attackDamage = 1;
    }

    public void updateDespawnCounter()
    {
        if(level.difficulty == 1)
        {
            attackDamage = 3;
        } else
        if(level.difficulty > 1)
        {
            attackDamage = 5;
        }
        super.updateDespawnCounter();
    }

    public int getLimitPerChunk()
    {
        return 6;
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
        float f = getBrightnessAtEyes(1.0F);
        if(f < 0.5F)
        {
            double d = 16D;
            return level.getClosestPlayerTo(this, d);
        }
        if(rand.nextInt(80) == 0)
        {
            Living entityliving = getClosestTarget(this, 10D);
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
            if(!(entity1 instanceof Living) || entity1 == entity || entity1 == entity.passenger || entity1 == entity.vehicle || (entity1 instanceof PlayerBase) || (entity1 instanceof MonsterBase) || (entity1 instanceof EntityBigCat) || (entity1 instanceof EntityBear) || (entity1 instanceof Cow) || (entity1 instanceof Wolf) && !mocr.mocreaturesGlass.huntercreatures.attackwolves || (entity1 instanceof EntityHorse) && !mocr.mocreaturesGlass.huntercreatures.attackhorses)
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

    protected void tryAttack(EntityBase entity, float f)
    {
        if((double)f < 2.5D && entity.boundingBox.maxY > boundingBox.minY && entity.boundingBox.minY < boundingBox.maxY)
        {
            attackTime = 20;
            entity.damage(this, attackDamage);
            if(!(entity instanceof PlayerBase))
            {
                destroyDrops(this, 3D);
            }
        }
    }

    public void writeCustomDataToTag(CompoundTag nbttagcompound)
    {
        super.writeCustomDataToTag(nbttagcompound);
        nbttagcompound.put("WolfBoolean", wolfboolean);
    }

    public void readCustomDataFromTag(CompoundTag nbttagcompound)
    {
        super.readCustomDataFromTag(nbttagcompound);
        wolfboolean = nbttagcompound.getBoolean("WolfBoolean");
    }

    protected String getAmbientSound()
    {
        return "mocreatures:wolfgrunt";
    }

    protected String getHurtSound()
    {
        return "mocreatures:wolfhurt";
    }

    protected String getDeathSound()
    {
        return "mocreatures:wolfdeath";
    }

    protected int getMobDrops()
    {
        return ItemBase.leather.id;
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
        return level.isAboveGroundCached(MathHelper.floor(x), MathHelper.floor(y), MathHelper.floor(z)) && mocr.mocreaturesGlass.hostilemobs.wwolffreq > 0 && super.canSpawn();
    }

    mod_mocreatures mocr = new mod_mocreatures();
    public boolean wolfboolean;

    @Override
    public Identifier getHandlerIdentifier() {
        return Identifier.of(mod_mocreatures.MOD_ID, "WildWolf");
    }
}
