// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) braces deadcode 

package net.kozibrodka.mocreatures.entity;

import net.kozibrodka.mocreatures.events.mod_mocreatures;
import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.entity.Entity;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NbtCompound;
import net.minecraft.util.math.Box;
import net.minecraft.util.math.MathHelper;
import net.minecraft.world.World;
import net.modificationstation.stationapi.api.util.Identifier;
import net.modificationstation.stationapi.api.server.entity.MobSpawnDataProvider;

public class EntityPolarBear extends EntityBear implements MobSpawnDataProvider
{

    public EntityPolarBear(World world)
    {
        super(world);
        texture = "/assets/mocreatures/stationapi/textures/mob/polarbear.png";
        attackRange = 1.0D;
        health = 30;
    }

    protected Entity getTargetInRange()
    {
        if(world.difficulty > 0)
        {
            PlayerEntity entityplayer = world.getClosestPlayer(this, attackRange);
            if(entityplayer != null && world.difficulty > 0)
            {
                return entityplayer;
            }
            if(random.nextInt(20) == 0)
            {
                LivingEntity entityliving = getClosestTarget(this, 8D);
                return entityliving;
            }
        }
        return null;
    }

    public void tickMovement()
    {
        if(world.difficulty == 1)
        {
            attackRange = 5D;
            force = 3;
        } else
        if(world.difficulty > 1)
        {
            attackRange = 8D;
            force = 5;
        }
        super.tickMovement();
    }

    public boolean NearSnowWithDistance(Entity entity, Double double1)
    {
        Box axisalignedbb = entity.boundingBox.expand(double1.doubleValue(), double1.doubleValue(), double1.doubleValue());
        int i = MathHelper.floor(axisalignedbb.minX);
        int j = MathHelper.floor(axisalignedbb.maxX + 1.0D);
        int k = MathHelper.floor(axisalignedbb.minY);
        int l = MathHelper.floor(axisalignedbb.maxY + 1.0D);
        int i1 = MathHelper.floor(axisalignedbb.minZ);
        int j1 = MathHelper.floor(axisalignedbb.maxZ + 1.0D);
        for(int k1 = i; k1 < j; k1++)
        {
            for(int l1 = k; l1 < l; l1++)
            {
                for(int i2 = i1; i2 < j1; i2++)
                {
                    int j2 = world.getBlockId(k1, l1, i2);
                    if(j2 != 0 && Block.BLOCKS[j2].material == Material.SNOW_LAYER)
                    {
                        return true;
                    }
                }

            }

        }

        return false;
    }

    protected void dropItems()
    {
        int i = random.nextInt(3);
        for(int j = 0; j < i; j++)
        {
            dropItem(new ItemStack(getDroppedItemId(), 1, 0), 0.0F);
        }
        if(mocr.mocreaturesGlass.balancesettings.balance_drop) {
            int k = random.nextInt(2);
            for (int j = 0; j < k; j++) {
                dropItem(new ItemStack(mod_mocreatures.polarleather, 1, 0), 0.0F);
            }
        }
    }

    public int getLimitPerChunk()
    {
        return 2;
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
//        int i = MathHelper.floor(y);
//        int j = MathHelper.floor(boundingBox.minY);
//        int k = MathHelper.floor(velocityX);
//        return (level.getTileId(i, j - 1, k) == BlockBase.SNOW.id || level.getTileId(i, j, k) == BlockBase.SNOW.id || level.getTileId(i, j - 1, k) == BlockBase.SNOW_BLOCK.id || level.getTileId(i, j, k) == BlockBase.SNOW_BLOCK.id) && mocr.mocreaturesGlass.huntercreatures.pbearfreq > 0 && super.cS2();
        return NearSnowWithDistance(this, Double.valueOf(1.0D)) && mocr.mocreaturesGlass.huntercreatures.pbearfreq > 0 && super.cS2();
    }

    mod_mocreatures mocr = new mod_mocreatures();

    @Override
    public Identifier getHandlerIdentifier() {
        return Identifier.of(mod_mocreatures.MOD_ID, "PolarBear");
    }
}
