// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) braces deadcode 

package net.kozibrodka.mocreatures.mocreatures;


import net.kozibrodka.mocreatures.entity.EntityCaveOgre;
import net.kozibrodka.mocreatures.entity.EntityFireOgre;
import net.kozibrodka.mocreatures.entity.EntityOgre;
import net.kozibrodka.mocreatures.events.mod_mocreatures;
import net.kozibrodka.mocreatures.mixin.BlockBaseAccesor;
import net.minecraft.block.Block;
import net.minecraft.entity.Entity;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Box;
import net.minecraft.util.math.MathHelper;
import net.minecraft.util.math.Vec3d;
import net.minecraft.world.World;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;


public class Destroyer
{

    public Destroyer()
    {
    }

    public static void DestroyBlast(World world, Entity entity, double d, double d1, double d2,
                                    float f, boolean flag, boolean igniteogre, boolean explodeogre, boolean explodecaveogre, boolean explodefireogre)
    {
        world.playSound(d, d1, d2, "mocreatures:destroy", 4F, (1.0F + (world.random.nextFloat() - world.random.nextFloat()) * 0.2F) * 0.7F);
        HashSet hashset = new HashSet();
        float f1 = f;
        int i = 16;
        for(int j = 0; j < i; j++)
        {
            for(int l = 0; l < i; l++)
            {
label0:
                for(int j1 = 0; j1 < i; j1++)
                {
                    if(j != 0 && j != i - 1 && l != 0 && l != i - 1 && j1 != 0 && j1 != i - 1)
                    {
                        continue;
                    }
                    double d3 = ((float)j / ((float)i - 1.0F)) * 2.0F - 1.0F;
                    double d4 = ((float)l / ((float)i - 1.0F)) * 2.0F - 1.0F;
                    double d5 = ((float)j1 / ((float)i - 1.0F)) * 2.0F - 1.0F;
                    double d6 = Math.sqrt(d3 * d3 + d4 * d4 + d5 * d5);
                    d3 /= d6;
                    d4 /= d6;
                    d5 /= d6;
                    float f2 = f * (0.7F + world.random.nextFloat() * 0.6F);
                    double d8 = d;
                    double d10 = d1;
                    double d12 = d2;
                    float f3 = 0.3F;
                    float f4 = 5F;
                    do
                    {
                        if(f2 <= 0.0F)
                        {
                            continue label0;
                        }
                        int k5 = MathHelper.floor(d8);
                        int l5 = MathHelper.floor(d10);
                        int i6 = MathHelper.floor(d12);
                        int j6 = world.getBlockId(k5, l5, i6);
                        if(j6 > 0)
                        {
                            f4 = ((BlockBaseAccesor)Block.BLOCKS[j6]).getHardness();
//                            f4 = BlockBase.BY_ID[j6].hardness;
                            f2 -= (Block.BLOCKS[j6].getBlastResistance(entity) + 0.3F) * (f3 / 10F);
                        }
                        if(f2 > 0.0F && d10 > entity.y && f4 < 3F)
                        {
                            hashset.add(new BlockPos(k5, l5, i6));
                        }
                        d8 += d3 * (double)f3;
                        d10 += d4 * (double)f3;
                        d12 += d5 * (double)f3;
                        f2 -= f3 * 0.75F;
                    } while(true);
                }

            }

        }

        f *= 2.0F;
        int k = MathHelper.floor(d - (double)f - 1.0D);
        int i1 = MathHelper.floor(d + (double)f + 1.0D);
        int k1 = MathHelper.floor(d1 - (double)f - 1.0D);
        int l1 = MathHelper.floor(d1 + (double)f + 1.0D);
        int i2 = MathHelper.floor(d2 - (double)f - 1.0D);
        int j2 = MathHelper.floor(d2 + (double)f + 1.0D);
        List list = world.getEntities(entity, Box.createCached(k, k1, i2, i1, l1, j2));
        Vec3d vec3d = Vec3d.createCached(d, d1, d2);
        for(int k2 = 0; k2 < list.size(); k2++)
        {
            Entity entity1 = (Entity)list.get(k2);
            double d7 = entity1.getDistance(d, d1, d2) / (double)f;
            if(d7 > 1.0D)
            {
                continue;
            }
            double d9 = entity1.x - d;
            double d11 = entity1.y - d1;
            double d13 = entity1.z - d2;
            double d15 = MathHelper.sqrt(d9 * d9 + d11 * d11 + d13 * d13);
            d9 /= d15;
            d11 /= d15;
            d13 /= d15;
            double d17 = world.getVisibilityRatio(vec3d, entity1.boundingBox);
            double d19 = (1.0D - d7) * d17;
            if(!(entity1 instanceof EntityOgre))
            {
                entity1.damage(entity, (int)(((d19 * d19 + d19) / 2D) * 3D * (double)f + 1.0D));
                double d21 = d19;
                entity1.velocityX += d9 * d21;
                entity1.velocityY += d11 * d21;
                entity1.velocityZ += d13 * d21;
            }
        }

        f = f1;
        ArrayList arraylist = new ArrayList();
        arraylist.addAll(hashset);
        for(int l2 = arraylist.size() - 1; l2 >= 0; l2--)
        {
            BlockPos chunkposition = (BlockPos)arraylist.get(l2);
            int j3 = chunkposition.x;
            int l3 = chunkposition.y;
            int j4 = chunkposition.z;
            int l4 = world.getBlockId(j3, l3, j4);
            for(int j5 = 0; j5 < 5; j5++)
            {
                double d14 = (float)j3 + world.random.nextFloat();
                double d16 = (float)l3 + world.random.nextFloat();
                double d18 = (float)j4 + world.random.nextFloat();
                double d20 = d14 - d;
                double d22 = d16 - d1;
                double d23 = d18 - d2;
                double d24 = MathHelper.sqrt(d20 * d20 + d22 * d22 + d23 * d23);
                d20 /= d24;
                d22 /= d24;
                d23 /= d24;
                double d25 = 0.5D / (d24 / (double)f + 0.10000000000000001D);
                d25 *= world.random.nextFloat() * world.random.nextFloat() + 0.3F;
                d25--;
                d20 *= d25;
                d22 *= d25 - 1.0D;
                d23 *= d25;
                world.addParticle("explode", (d14 + d * 1.0D) / 2D, (d16 + d1 * 1.0D) / 2D, (d18 + d2 * 1.0D) / 2D, d20, d22, d23);
                entity.velocityX -= 0.0010000000474974511D;
                entity.velocityY -= 0.0010000000474974511D;
            }

            if(l4 > 0)
            {
                if(!(entity instanceof EntityCaveOgre) && !(entity instanceof EntityFireOgre) && explodeogre || entity instanceof EntityCaveOgre && explodecaveogre || entity instanceof EntityFireOgre && explodefireogre)
                {
                    Block.BLOCKS[l4].dropStacks(world, j3, l3, j4, world.getBlockMeta(j3, l3, j4), 0.3F);
                    world.setBlock(j3, l3, j4, 0);
                    Block.BLOCKS[l4].onDestroyedByExplosion(world, j3, l3, j4);
                }
            }
        }

        if(flag && igniteogre)
        {
            for(int i3 = arraylist.size() - 1; i3 >= 0; i3--)
            {
                BlockPos chunkposition1 = (BlockPos)arraylist.get(i3);
                int k3 = chunkposition1.x;
                int i4 = chunkposition1.y;
                int k4 = chunkposition1.z;
                int i5 = world.getBlockId(k3, i4, k4);
                if(i5 == 0 && world.random.nextInt(8) == 0)
                {
                    world.setBlock(k3, i4, k4, Block.FIRE.id);
                }
            }

        }
    }

}
