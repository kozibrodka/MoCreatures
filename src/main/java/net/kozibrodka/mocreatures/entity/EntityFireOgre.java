// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) braces deadcode 

package net.kozibrodka.mocreatures.entity;

import net.kozibrodka.mocreatures.events.mod_mocreatures;
import net.minecraft.block.Block;
import net.minecraft.class_65;
import net.minecraft.nbt.NbtCompound;
import net.minecraft.util.math.MathHelper;
import net.minecraft.world.World;
import net.modificationstation.stationapi.api.util.Identifier;
import net.modificationstation.stationapi.api.server.entity.MobSpawnDataProvider;

public class EntityFireOgre extends EntityOgre implements MobSpawnDataProvider
    , class_65
{

    public EntityFireOgre(World world)
    {
        super(world);
        field_547 = 3;
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

    protected int method_914()
    {
        return Block.FIRE.id;
    }

    public void method_937()
    {
        method_638();
        destroyForce = mocr.mocreaturesGlass.hostilemobs.ogreStrength;
        attackRange = mocr.mocreaturesGlass.hostilemobs.ogrerange;
        if(ogrehasenemy && random.nextInt(frequencyA) == 0)
        {
            ogreattack = true;
            field_1042 = 15;
        }
        if(world.method_220())
        {
            float f = method_1394(1.0F);
            if(f > 0.5F && world.method_249(MathHelper.floor(x), MathHelper.floor(y), MathHelper.floor(z)) && random.nextFloat() * 30F < (f - 0.4F) * 2.0F)
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
        return mocr.mocreaturesGlass.hostilemobs.fogrefreq > 0 && world.field_213 >= mocr.mocreaturesGlass.hostilemobs.fogreSpawnDifficulty + 1 && super.d2();
    }

    public int method_916()
    {
        return 2;
    }

    mod_mocreatures mocr = new mod_mocreatures();

    @Override
    public Identifier getHandlerIdentifier() {
        return Identifier.of(mod_mocreatures.MOD_ID, "FireOgre");
    }
}
