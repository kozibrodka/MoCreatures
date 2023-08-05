// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) braces deadcode 

package net.kozibrodka.mocreatures.entity;

import net.kozibrodka.mocreatures.events.mod_mocreatures;
import net.minecraft.entity.EntityBase;
import net.minecraft.entity.monster.MonsterEntityType;
import net.minecraft.item.ItemBase;
import net.minecraft.level.Level;
import net.minecraft.util.io.CompoundTag;
import net.minecraft.util.maths.MathHelper;
import net.modificationstation.stationapi.api.registry.Identifier;
import net.modificationstation.stationapi.api.server.entity.MobSpawnDataProvider;

public class EntityCaveOgre extends EntityOgre
    implements MonsterEntityType, MobSpawnDataProvider
{

    public EntityCaveOgre(Level world)
    {
        super(world);
        attackDamage = 3;
        attackRange = mocr.mocreaturesGlass.hostilemobs.ogrerange;
        ogreboolean = false;
        texture = "/assets/mocreatures/stationapi/textures/mob/caveogre.png";
        setSize(1.5F, 4F);
        health = 50;
        bogrefire = false;
        destroyForce = mocr.mocreaturesGlass.hostilemobs.cogreStrength;
        immuneToFire = false;
        frequencyA = 35;
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

    public boolean maxNumberReached()
    {
        int i = 0;
        for(int j = 0; j < level.entities.size(); j++)
        {
            EntityBase entity = (EntityBase)level.entities.get(j);
            if(entity instanceof EntityCaveOgre)
            {
                i++;
            }
        }

        return false;
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
        return mocr.mocreaturesGlass.hostilemobs.cogrefreq > 0 && level.difficulty >= mocr.mocreaturesGlass.hostilemobs.cogreSpawnDifficulty + 1 && !level.isAboveGroundCached(MathHelper.floor(x), MathHelper.floor(y), MathHelper.floor(z)) && y < 50D && super.d2();
    }

    protected int getMobDrops()
    {
        return ItemBase.diamond.id;
    }

    mod_mocreatures mocr = new mod_mocreatures();

    @Override
    public Identifier getHandlerIdentifier() {
        return Identifier.of(mod_mocreatures.MOD_ID, "CaveOgre");
    }
}
