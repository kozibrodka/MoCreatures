// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) braces deadcode 

package net.kozibrodka.mocreatures.entity;

import net.kozibrodka.mocreatures.events.mod_mocreatures;
import net.minecraft.entity.monster.MonsterEntityType;
import net.minecraft.entity.player.PlayerBase;
import net.minecraft.item.ItemBase;
import net.minecraft.level.Level;
import net.minecraft.util.io.CompoundTag;
import net.minecraft.util.maths.MathHelper;
import net.modificationstation.stationapi.api.server.entity.MobSpawnDataProvider;
import net.modificationstation.stationapi.api.util.Identifier;

public class EntityWraith extends EntityFlyerMob
    implements MonsterEntityType, MobSpawnDataProvider
{

    public EntityWraith(Level world)
    {
        super(world);
        texture = "/assets/mocreatures/stationapi/textures/mob/wraith.png";
        setSize(1.5F, 1.5F);
        immuneToFire = false;
        attackStrength = 3;
        health = 10;
        movementSpeed = 1.3F;
    }

    protected String getAmbientSound()
    {
        return "mocreatures:wraith";
    }

    protected String getHurtSound()
    {
        return "mocreatures:wraithhurt";
    }

    protected String getDeathSound()
    {
        return "mocreatures:wraithdying";
    }

    protected int getMobDrops()
    {
        return ItemBase.gunpowder.id;
    }

    public void updateDespawnCounter()
    {
        if(level.difficulty == 1)
        {
            attackStrength = 2;
        } else
        if(level.difficulty > 1)
        {
            attackStrength = 3;
        }
        if(level.isDaylight())
        {
            float f = getBrightnessAtEyes(1.0F);
            if(f > 0.5F && level.isAboveGroundCached(MathHelper.floor(x), MathHelper.floor(y), MathHelper.floor(z)) && rand.nextFloat() * 30F < (f - 0.4F) * 2.0F)
            {
                fire = 300;
            }
        }
        super.updateDespawnCounter();
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
        return mocr.mocreaturesGlass.hostilemobs.wraithfreq > 0 && level.difficulty >= mocr.mocreaturesGlass.hostilemobs.wraithSpawnDifficulty + 1 && super.canSpawn();
    }

    public boolean d2()
    {
        return super.canSpawn();
    }

    mod_mocreatures mocr = new mod_mocreatures();

    @Override
    public Identifier getHandlerIdentifier() {
        return Identifier.of(mod_mocreatures.MOD_ID, "Wraith");
    }
}
