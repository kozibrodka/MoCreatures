
package net.kozibrodka.mocreatures.mocreatures;


import net.fabricmc.api.EnvType;

import net.kozibrodka.mocreatures.events.mod_mocreatures;
import net.minecraft.block.Block;
import net.minecraft.entity.Entity;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.MathHelper;
import net.minecraft.world.World;

import java.util.ArrayList;
import java.util.HashSet;


public class ClientDestroyer
{

    public ClientDestroyer()
    {
    }

    public static void DestroyBlast(World world, Entity entity, double d, double d1, double d2,
                                    float f)
    {
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
                            f4 = Block.BLOCKS[j6].getHardness();
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
                world.addParticle("explode", (d14 + d) / 2D, (d16 + d1) / 2D, (d18 + d2) / 2D, d20, d22, d23);
            }
        }
    }
}
