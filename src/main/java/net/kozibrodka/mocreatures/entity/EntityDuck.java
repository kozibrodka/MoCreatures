// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) braces deadcode 

package net.kozibrodka.mocreatures.entity;

import net.kozibrodka.mocreatures.events.mod_mocreatures;
import net.minecraft.entity.passive.ChickenEntity;
import net.minecraft.nbt.NbtCompound;
import net.minecraft.world.World;
import net.modificationstation.stationapi.api.util.Identifier;
import net.modificationstation.stationapi.api.server.entity.MobSpawnDataProvider;

public class EntityDuck extends ChickenEntity implements MobSpawnDataProvider
{

    public EntityDuck(World world)
    {
        super(world);
        texture = "/assets/mocreatures/stationapi/textures/mob/duck.png";
        setBoundingBoxSpacing(0.3F, 0.4F);
        health = 4;
        field_2165 = random.nextInt(6000) + 6000;
    }

    protected String method_911()
    {
        return "mocreatures:duck";
    }

    protected String method_912()
    {
        return "mocreatures:duckhurt";
    }

    protected String method_913()
    {
        return "mocreatures:duckhurt";
    }

    public void markDead()
    {
        super.markDead();
    }

    public void writeNbt(NbtCompound nbttagcompound)
    {
        super.writeNbt(nbttagcompound);
    }

    public void readNbt(NbtCompound nbttagcompound)
    {
        super.readNbt(nbttagcompound);
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
