// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) braces deadcode 

package net.kozibrodka.mocreatures.entity;

import net.kozibrodka.mocreatures.events.mod_mocreatures;
import net.minecraft.block.BlockBase;
import net.minecraft.entity.monster.MonsterEntityType;
import net.minecraft.level.Level;
import net.minecraft.util.io.CompoundTag;
import net.minecraft.util.maths.MathHelper;
import net.modificationstation.stationapi.api.util.Identifier;
import net.modificationstation.stationapi.api.server.entity.MobSpawnDataProvider;

public class EntityFireOgre extends EntityOgre implements MobSpawnDataProvider
    , MonsterEntityType
{

    public EntityFireOgre(Level world)
    {
        super(world);
        attackDamage = 3;
        attackRange = mocr.mocreaturesGlass.hostilemobs.ogrerange;
        ogreboolean = false;
        texture = "/assets/mocreatures/stationapi/textures/mob/fireogre.png";
        setSize(1.5F, 4F);
        health = 35;
        bogrefire = true;
        destroyForce = mocr.mocreaturesGlass.hostilemobs.fogreStrength;
        immuneToFire = true;
        frequencyA = 35;
    }

    protected int getMobDrops()
    {
        return BlockBase.FIRE.id;
    }

    public void updateDespawnCounter()
    {
        getAttackTarget();
        destroyForce = mocr.mocreaturesGlass.hostilemobs.ogreStrength;
        attackRange = mocr.mocreaturesGlass.hostilemobs.ogrerange;
        if(ogrehasenemy && rand.nextInt(frequencyA) == 0)
        {
            ogreattack = true;
            attackTime = 15;
        }
        if(level.isDaylight())
        {
            float f = getBrightnessAtEyes(1.0F);
            if(f > 0.5F && level.isAboveGroundCached(MathHelper.floor(x), MathHelper.floor(y), MathHelper.floor(z)) && rand.nextFloat() * 30F < (f - 0.4F) * 2.0F)
            {
                health -= 5;
            }
        }
        super.onLivingUpdate2();
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
        return mocr.mocreaturesGlass.hostilemobs.fogrefreq > 0 && level.difficulty >= mocr.mocreaturesGlass.hostilemobs.fogreSpawnDifficulty + 1 && super.d2();
    }

    public int getLimitPerChunk()
    {
        return 2;
    }

    mod_mocreatures mocr = new mod_mocreatures();

    @Override
    public Identifier getHandlerIdentifier() {
        return Identifier.of(mod_mocreatures.MOD_ID, "FireOgre");
    }
}
