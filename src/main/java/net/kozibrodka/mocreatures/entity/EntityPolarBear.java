// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) braces deadcode 

package net.kozibrodka.mocreatures.entity;

import net.kozibrodka.mocreatures.events.mod_mocreatures;
import net.minecraft.block.BlockBase;
import net.minecraft.block.material.Material;
import net.minecraft.entity.EntityBase;
import net.minecraft.entity.Living;
import net.minecraft.entity.player.PlayerBase;
import net.minecraft.item.ItemInstance;
import net.minecraft.level.Level;
import net.minecraft.util.io.CompoundTag;
import net.minecraft.util.maths.Box;
import net.minecraft.util.maths.MathHelper;

public class EntityPolarBear extends EntityBear
{

    public EntityPolarBear(Level world)
    {
        super(world);
        texture = "/assets/mocreatures/stationapi/textures/mob/polarbear.png";
        attackRange = 1.0D;
        health = 30;
    }

    protected EntityBase getAttackTarget()
    {
        if(level.difficulty > 0)
        {
            PlayerBase entityplayer = level.getClosestPlayerTo(this, attackRange);
            if(entityplayer != null && level.difficulty > 0)
            {
                return entityplayer;
            }
            if(rand.nextInt(20) == 0)
            {
                Living entityliving = getClosestTarget(this, 8D);
                return entityliving;
            }
        }
        return null;
    }

    public void updateDespawnCounter()
    {
        if(level.difficulty == 1)
        {
            attackRange = 5D;
            force = 3;
        } else
        if(level.difficulty > 1)
        {
            attackRange = 8D;
            force = 5;
        }
        super.updateDespawnCounter();
    }

    public boolean NearSnowWithDistance(EntityBase entity, Double double1)
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
                    int j2 = level.getTileId(k1, l1, i2);
                    if(j2 != 0 && BlockBase.BY_ID[j2].material == Material.SNOW)
                    {
                        return true;
                    }
                }

            }

        }

        return false;
    }

    protected void getDrops()
    {
        int i = rand.nextInt(3);
        for(int j = 0; j < i; j++)
        {
            dropItem(new ItemInstance(getMobDrops(), 1, 0), 0.0F);
        }

        int k = rand.nextInt(2);
        for(int j = 0; j < k; j++)
        {
            dropItem(new ItemInstance(mod_mocreatures.polarleather, 1, 0), 0.0F);
        }
    }

    public int getLimitPerChunk()
    {
        return 2;
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
//        int i = MathHelper.floor(y);
//        int j = MathHelper.floor(boundingBox.minY);
//        int k = MathHelper.floor(velocityX);
//        return (level.getTileId(i, j - 1, k) == BlockBase.SNOW.id || level.getTileId(i, j, k) == BlockBase.SNOW.id || level.getTileId(i, j - 1, k) == BlockBase.SNOW_BLOCK.id || level.getTileId(i, j, k) == BlockBase.SNOW_BLOCK.id) && mocr.mocreaturesGlass.huntercreatures.pbearfreq > 0 && super.cS2();
        return NearSnowWithDistance(this, Double.valueOf(1.0D)) && mocr.mocreaturesGlass.huntercreatures.pbearfreq > 0 && super.cS2();
    }

    mod_mocreatures mocr = new mod_mocreatures();
}
