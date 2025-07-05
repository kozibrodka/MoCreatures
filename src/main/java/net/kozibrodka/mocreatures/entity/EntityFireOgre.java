// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) braces deadcode 

package net.kozibrodka.mocreatures.entity;

import net.kozibrodka.mocreatures.events.mod_mocreatures;
import net.minecraft.block.Block;
import net.minecraft.entity.Monster;
import net.minecraft.nbt.NbtCompound;
import net.minecraft.util.math.MathHelper;
import net.minecraft.world.World;
import net.modificationstation.stationapi.api.util.Identifier;
import net.modificationstation.stationapi.api.server.entity.MobSpawnDataProvider;

public class EntityFireOgre extends EntityOgre implements MobSpawnDataProvider
    , Monster
{

    public EntityFireOgre(World world)
    {
        super(world);
        attackDamage = 3;
        attackRange = mocr.mocreaturesGlass.hostilemobs.ogrerange;
        ogreboolean = false;
        texture = "/assets/mocreatures/stationapi/textures/mob/fireogre.png";
        setBoundingBoxSpacing(1.5F, 4F);
        health = 35;
        bogrefire = true;
        destroyForce = mocr.mocreaturesGlass.hostilemobs.fogreStrength;
        fireImmune = true;
        frequencyA = 35;
    }

    protected int getDroppedItemId()
    {
        return Block.FIRE.id;
    }

    public void tickMovement()
    {
        getTargetInRange();
        destroyForce = mocr.mocreaturesGlass.hostilemobs.ogreStrength;
        attackRange = mocr.mocreaturesGlass.hostilemobs.ogrerange;
        if(ogrehasenemy && random.nextInt(frequencyA) == 0)
        {
            ogreattack = true;
            attackCooldown = 15;
        }
        if(world.canMonsterSpawn())
        {
            float f = getBrightnessAtEyes(1.0F);
            if(f > 0.5F && world.hasSkyLight(MathHelper.floor(x), MathHelper.floor(y), MathHelper.floor(z)) && random.nextFloat() * 30F < (f - 0.4F) * 2.0F)
            {
                health -= 5;
            }
        }
        super.onLivingUpdate2();
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
        return mocr.mocreaturesGlass.hostilemobs.fogrefreq > 0 && world.difficulty >= mocr.mocreaturesGlass.hostilemobs.fogreSpawnDifficulty.ordinal() + 1 && super.d2();
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
