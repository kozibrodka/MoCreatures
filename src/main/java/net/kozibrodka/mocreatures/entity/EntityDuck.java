// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) braces deadcode 

package net.kozibrodka.mocreatures.entity;

import net.kozibrodka.mocreatures.events.mod_mocreatures;
import net.minecraft.entity.animal.Chicken;
import net.minecraft.level.Level;
import net.minecraft.util.io.CompoundTag;
import net.modificationstation.stationapi.api.registry.Identifier;
import net.modificationstation.stationapi.api.server.entity.MobSpawnDataProvider;

public class EntityDuck extends Chicken implements MobSpawnDataProvider
{

    public EntityDuck(Level world)
    {
        super(world);
        texture = "/assets/mocreatures/stationapi/textures/mob/duck.png";
        setSize(0.3F, 0.4F);
        health = 4;
        field_2165 = rand.nextInt(6000) + 6000;
    }

    protected String getAmbientSound()
    {
        return "mocreatures:duck";
    }

    protected String getHurtSound()
    {
        return "mocreatures:duckhurt";
    }

    protected String getDeathSound()
    {
        return "mocreatures:duckhurt";
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
        return mocr.mocreaturesGlass.animals.duckfreq > 0 && super.canSpawn();
    }

    mod_mocreatures mocr = new mod_mocreatures();

    @Override
    public Identifier getHandlerIdentifier() {
        return Identifier.of(mod_mocreatures.MOD_ID, "Duck");
    }
}
