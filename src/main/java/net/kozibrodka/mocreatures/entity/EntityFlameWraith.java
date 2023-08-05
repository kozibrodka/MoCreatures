// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) braces deadcode 

package net.kozibrodka.mocreatures.entity;

import net.kozibrodka.mocreatures.events.mod_mocreatures;
import net.minecraft.entity.EntityBase;
import net.minecraft.entity.monster.MonsterEntityType;
import net.minecraft.entity.player.PlayerBase;
import net.minecraft.item.ItemBase;
import net.minecraft.level.Level;
import net.minecraft.util.io.CompoundTag;
import net.minecraft.util.maths.MathHelper;
import net.modificationstation.stationapi.api.registry.Identifier;
import net.modificationstation.stationapi.api.server.entity.MobSpawnDataProvider;

public class EntityFlameWraith extends EntityWraith
    implements MonsterEntityType, MobSpawnDataProvider
{

    public EntityFlameWraith(Level world)
    {
        super(world);
        texture = "/assets/mocreatures/stationapi/textures/mob/flamewraith.png";
        setSize(1.5F, 1.5F);
        immuneToFire = true;
        burningTime = 30;
        health = 15;
        movementSpeed = 1.1F;
    }

    protected int getMobDrops()
    {
        return ItemBase.redstoneDust.id;
    }

    public void updateDespawnCounter()
    {
        if(rand.nextInt(40) == 0)
        {
            fire = 2;
        }
        if(level.isDaylight())
        {
            float f = getBrightnessAtEyes(1.0F);
            if(f > 0.5F && level.isAboveGroundCached(MathHelper.floor(x), MathHelper.floor(y), MathHelper.floor(z)) && rand.nextFloat() * 30F < (f - 0.4F) * 2.0F)
            {
                health -= 2;
            }
        }
        super.updateDespawnCounter();
    }

    protected void tryAttack(EntityBase entity, float f)
    {
        if((double)f < 2.5D && entity.boundingBox.maxY > boundingBox.minY && entity.boundingBox.minY < boundingBox.maxY)
        {
            attackTime = 20;
            entity.damage(this, 2);
            entity.fire = burningTime;
        }
    }

    protected void tickHandSwing(){
        if(this.entity instanceof PlayerBase){
            PlayerBase uciekinier = level.getClosestPlayerTo(this, 20D);
            if(uciekinier == null && entity.isAlive()){
                if(rand.nextInt(30) == 0)
                {
                    entity = null;
                }
            }
        }
        super.tickHandSwing();
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
        return mocr.mocreaturesGlass.hostilemobs.fwraithfreq > 0 && level.difficulty >= mocr.mocreaturesGlass.hostilemobs.fwraithSpawnDifficulty + 1 && super.d2();
    }

    mod_mocreatures mocr = new mod_mocreatures();
    protected int burningTime;

    @Override
    public Identifier getHandlerIdentifier() {
        return Identifier.of(mod_mocreatures.MOD_ID, "FlameWraith");
    }
}
