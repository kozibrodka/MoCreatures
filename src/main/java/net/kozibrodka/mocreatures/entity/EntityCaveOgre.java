// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) braces deadcode 

package net.kozibrodka.mocreatures.entity;

import net.kozibrodka.mocreatures.events.mod_mocreatures;
import net.minecraft.entity.Entity;
import net.minecraft.entity.Monster;
import net.minecraft.item.Item;
import net.minecraft.nbt.NbtCompound;
import net.minecraft.util.math.MathHelper;
import net.minecraft.world.World;
import net.modificationstation.stationapi.api.util.Identifier;
import net.modificationstation.stationapi.api.server.entity.MobSpawnDataProvider;

public class EntityCaveOgre extends EntityOgre
    implements Monster, MobSpawnDataProvider
{

    public EntityCaveOgre(World world)
    {
        super(world);
        attackDamage = 3;
        attackRange = mocr.mocreaturesGlass.hostilemobs.ogrerange;
        ogreboolean = false;
        texture = "/assets/mocreatures/stationapi/textures/mob/caveogre.png";
        setBoundingBoxSpacing(1.5F, 4F);
        health = 50;
        bogrefire = false;
        destroyForce = mocr.mocreaturesGlass.hostilemobs.cogreStrength;
        fireImmune = false;
        frequencyA = 35;
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

    public boolean maxNumberReached()
    {
        int i = 0;
        for(int j = 0; j < world.entities.size(); j++)
        {
            Entity entity = (Entity)world.entities.get(j);
            if(entity instanceof EntityCaveOgre)
            {
                i++;
            }
        }

        return false;
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
        return mocr.mocreaturesGlass.hostilemobs.cogrefreq > 0 && world.difficulty >= mocr.mocreaturesGlass.hostilemobs.cogreSpawnDifficulty.ordinal() + 1 && !world.hasSkyLight(MathHelper.floor(x), MathHelper.floor(y), MathHelper.floor(z)) && y < 50D && super.d2();
    }

    protected int getDroppedItemId()
    {
        return Item.DIAMOND.id;
    }

    mod_mocreatures mocr = new mod_mocreatures();

    @Override
    public Identifier getHandlerIdentifier() {
        return Identifier.of(mod_mocreatures.MOD_ID, "CaveOgre");
    }
}
