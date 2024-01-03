// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) braces deadcode 

package net.kozibrodka.mocreatures.entity;

import net.kozibrodka.mocreatures.mixin.EntityBaseAccesor;
import net.minecraft.block.Block;
import net.minecraft.block.Material;
import net.minecraft.class_61;
import net.minecraft.entity.Entity;
import net.minecraft.entity.ItemEntity;
import net.minecraft.entity.mob.WaterCreatureEntity;
import net.minecraft.item.Item;
import net.minecraft.nbt.NbtCompound;
import net.minecraft.util.math.MathHelper;
import net.minecraft.util.math.Vec3d;
import net.minecraft.world.World;
import net.modificationstation.stationapi.api.server.entity.MobSpawnDataProvider;

import java.util.List;
import java.util.Random;


public class EntityCustomWM extends WaterCreatureEntity
{

    public EntityCustomWM(World world)
    {
        super(world);
        outOfWater = 0;
        tamed = true;
        temper = 50;
    }

    public boolean isSubmergedInWater()
    {
        return world.method_170(boundingBox, Material.WATER, this);
    }

    public boolean gettingOutOfWater()
    {
        int i = (int)x;
        int j = (int)y;
        int k = (int)z;
        int l = 1;
        l = world.getBlockId(i, j + 1, k);
        return l == 0;
    }

    public double speed()
    {
        return 1.5D;
    }

    public int tametemper()
    {
        return temper;
    }

    public boolean istamed()
    {
        return tamed;
    }

    public void setTame()
    {
        tamed = true;
    }

    public void method_945(float f, float f1)
    {
        if(isSubmergedInWater())
        {
            if(field_1594 != null && !istamed())
            {
                if(random.nextInt(5) == 0 && !jumping)
                {
                    velocityY += 0.40000000000000002D;
                    jumping = true;
                }
                if(random.nextInt(10) == 0)
                {
                    velocityX += random.nextDouble() / 30D;
                    velocityZ += random.nextDouble() / 10D;
                }
                move(velocityX, velocityY, velocityZ);
                if(random.nextInt(50) == 0)
                {
                    world.playSound(this, getUpsetSound(), 1.0F, 1.0F + (random.nextFloat() - random.nextFloat()) * 0.2F);
                    field_1594.velocityY += 0.90000000000000002D;
                    field_1594.velocityZ -= 0.29999999999999999D;
                    field_1594 = null;
                }
                if(field_1623)
                {
                    jumping = false;
                }
                if(random.nextInt(tametemper() * 8) == 0)
                {
                    setTame();
                }
            }
            if(field_1594 != null && istamed())
            {
                boundingBox.maxY = field_1594.boundingBox.maxY;
                velocityX += field_1594.velocityX * speed();
                velocityZ += field_1594.velocityZ * speed();
                if(velocityY != 0.0D)
                {
                    velocityY = 0.0D;
                }
                move(velocityX, velocityY, velocityZ);
                pitch = field_1594.pitch * 0.5F;
                prevYaw = yaw = field_1594.yaw;
                method_1342(yaw, pitch);
            }
            method_1324(f, f1, 0.02F);
            move(velocityX, velocityY, velocityZ);
            if(field_1594 == null)
            {
                velocityX *= 0.80000001192092896D;
                velocityZ *= 0.80000001192092896D;
            }
        } else
        if(method_1335())
        {
            double d = y;
            method_1324(f, f1, 0.02F);
            move(velocityX, velocityY, velocityZ);
            velocityX *= 0.5D;
            velocityY *= 0.5D;
            velocityZ *= 0.5D;
            velocityY -= 0.02D;
            if(field_1624 && method_1344(velocityX, ((velocityY + 0.60000002384185791D) - y) + d, velocityZ))
            {
                velocityY = 0.30000001192092901D;
            }
        }
        float f2 = 0.91F;
        f2 = 0.5460001F;
        int i = world.getBlockId(MathHelper.floor(x), MathHelper.floor(boundingBox.minY) - 1, MathHelper.floor(z));
        if(i > 0)
        {
            f2 = Block.BLOCKS[i].slipperiness * 0.91F;
        }
        float f3 = 0.162771F / (f2 * f2 * f2);
        method_1324(f, f1, 0.1F * f3);
        f2 = 0.91F;
        f2 = 0.5460001F;
        int j = world.getBlockId(MathHelper.floor(x), MathHelper.floor(boundingBox.minY) - 1, MathHelper.floor(z));
        if(j > 0)
        {
            f2 = Block.BLOCKS[j].slipperiness * 0.91F;
        }
        if(method_932())
        {
            field_1636 = 0.0F;
            if(velocityY < -0.14999999999999999D)
            {
                velocityY = -0.14999999999999999D;
            }
        }
        move(velocityX, velocityY, velocityZ);
        if(field_1624 && method_932())
        {
            velocityY = 0.20000000000000001D;
        }
        velocityX *= f2;
        velocityZ *= f2;
        if(!isSubmergedInWater())
        {
            velocityY -= 0.080000000000000002D;
            velocityY *= 0.98000001907348633D;
        } else
        if(field_1594 == null)
        {
            velocityY -= 0.02D;
            velocityY *= 0.5D;
        }
        field_1048 = field_1049;
        double d1 = x - prevX;
        double d2 = z - prevZ;
        float f4 = MathHelper.sqrt(d1 * d1 + d2 * d2) * 4F;
        if(f4 > 1.0F)
        {
            f4 = 1.0F;
        }
        field_1049 += (f4 - field_1049) * 0.4F;
        field_1050 += field_1049;
    }

    protected boolean MoveToNextEntity(Entity entity)
    {
        if(entity != null)
        {
            int i = MathHelper.floor(entity.x);
            int j = MathHelper.floor(entity.y);
            int k = MathHelper.floor(entity.z);
            faceItem(i, j, k, 30F);
            if(x < (double)i)
            {
                double d = entity.x - x;
                if(d > 0.5D)
                {
                    velocityX += 0.050000000000000003D;
                }
            } else
            {
                double d1 = x - entity.x;
                if(d1 > 0.5D)
                {
                    velocityX -= 0.050000000000000003D;
                }
            }
            if(z < (double)k)
            {
                double d2 = entity.z - z;
                if(d2 > 0.5D)
                {
                    velocityZ += 0.050000000000000003D;
                }
            } else
            {
                double d3 = z - entity.z;
                if(d3 > 0.5D)
                {
                    velocityZ -= 0.050000000000000003D;
                }
            }
            return true;
        } else
        {
            return false;
        }
    }

    public void faceItem(int i, int j, int k, float f)
    {
        double d = (double)i - x;
        double d1 = (double)k - z;
        double d2 = (double)j - y;
        double d3 = MathHelper.sqrt(d * d + d1 * d1);
        float f1 = (float)((Math.atan2(d1, d) * 180D) / 3.1415927410125728D) - 90F;
        float f2 = (float)((Math.atan2(d2, d3) * 180D) / 3.1415927410125728D);
        pitch = -b(pitch, f2, f);
        yaw = b(yaw, f1, f);
    }

    public float b(float f, float f1, float f2)
    {
        float f3 = f1;
        for(f3 = f1 - f; f3 < -180F; f3 += 360F) { }
        for(; f3 >= 180F; f3 -= 360F) { }
        if(f3 > f2)
        {
            f3 = f2;
        }
        if(f3 < -f2)
        {
            f3 = -f2;
        }
        return f + f3;
    }

    protected void method_910()
    {
        if(field_1594 != null && tamed)
        {
            return;
        }
        field_663 = false;
        float f = 16F;
        if(target == null)
        {
            target = method_638();
            if(target != null && ((EntityBaseAccesor)target).getField_1612())
            {
                a = world.method_192(this, target, f);
            }
        } else
        if(!target.isAlive() || !((EntityBaseAccesor)target).getField_1612())
        {
            target = null;
        } else
        {
            float f1 = target.method_1351(this);
            if(method_928(target))
            {
                method_637(target, f1);
            }
        }
        if(!field_663 && target != null && ((EntityBaseAccesor)target).getField_1612() && (a == null || random.nextInt(20) == 0))
        {
            a = world.method_192(this, target, f);
        } else
        if(a == null && random.nextInt(80) == 0 || random.nextInt(80) == 0)
        {
            boolean flag = false;
            int j = -1;
            int k = -1;
            int l = -1;
            float f2 = -99999F;
            for(int i1 = 0; i1 < 10; i1++)
            {
                int j1 = MathHelper.floor((x + (double)random.nextInt(13)) - 6D);
                int k1 = MathHelper.floor((y + (double)random.nextInt(7)) - 3D);
                int l1 = MathHelper.floor((z + (double)random.nextInt(13)) - 6D);
                float f3 = method_641(j1, k1, l1);
                if(f3 > f2)
                {
                    f2 = f3;
                    j = j1;
                    k = k1;
                    l = l1;
                    flag = true;
                }
            }

            if(flag)
            {
                a = world.method_189(this, j, k, l, 10F);
            }
        }
        int i = MathHelper.floor(boundingBox.minY);
        boolean flag1 = isSubmergedInWater();
        boolean flag2 = method_1335();
        pitch = 0.0F;
        if(a == null || random.nextInt(100) == 0)
        {
            super.method_910();
            a = null;
            return;
        }
        Vec3d vec3d = a.method_2041(this);
        for(double d = spacingXZ * 2.0F; vec3d != null && vec3d.squaredDistanceTo(x, vec3d.y, z) < d * d;)
        {
            a.method_2040();
            if(a.method_2042())
            {
                vec3d = null;
                a = null;
            } else
            {
                vec3d = a.method_2041(this);
            }
        }

        jumping = false;
        if(vec3d != null)
        {
            double d1 = vec3d.x - x;
            double d2 = vec3d.z - z;
            double d3 = vec3d.y - (double)i;
            float f4 = (float)((Math.atan2(d2, d1) * 180D) / 3.1415927410125728D) - 90F;
            float f5 = f4 - yaw;
            field_1029 = movementSpeed;
            for(; f5 < -180F; f5 += 360F) { }
            for(; f5 >= 180F; f5 -= 360F) { }
            if(f5 > 30F)
            {
                f5 = 30F;
            }
            if(f5 < -30F)
            {
                f5 = -30F;
            }
            yaw += f5;
            if(field_663 && target != null)
            {
                double d4 = target.x - x;
                double d5 = target.z - z;
                float f6 = yaw;
                yaw = (float)((Math.atan2(d5, d4) * 180D) / 3.1415927410125728D) - 90F;
                float f7 = (((f6 - yaw) + 90F) * 3.141593F) / 180F;
                field_1060 = -MathHelper.sin(f7) * field_1029 * 1.0F;
                field_1029 = MathHelper.cos(f7) * field_1029 * 1.0F;
            }
            if(d3 > 0.0D && target != null && ((EntityBaseAccesor)target).getField_1612())
            {
                jumping = true;
            }
        }
        if(target != null)
        {
            method_924(target, 30F, 30F);
        }
        if(field_1624)
        {
            jumping = true;
        }
        if(random.nextFloat() < 0.8F && (flag1 || flag2))
        {
            jumping = true;
        }
    }

    protected void method_1389(float f)
    {
        if(!field_1612)
        {
            super.method_1389(f);
        }
    }

    public ItemEntity getClosestFish(Entity entity, double d)
    {
        double d1 = -1D;
        ItemEntity entityitem = null;
        List list = world.getEntities(this, boundingBox.expand(d, d, d));
        for(int i = 0; i < list.size(); i++)
        {
            Entity entity1 = (Entity)list.get(i);
            if(!(entity1 instanceof ItemEntity))
            {
                continue;
            }
            ItemEntity entityitem1 = (ItemEntity)entity1;
            if(entityitem1.stack.itemId != Item.RAW_FISH.id || !((EntityBaseAccesor)entityitem1).getField_1612())
            {
                continue;
            }
            double d2 = entityitem1.method_1347(entity.x, entity.y, entity.z);
            if((d < 0.0D || d2 < d * d) && (d1 == -1D || d2 < d1))
            {
                d1 = d2;
                entityitem = entityitem1;
            }
        }
        return entityitem;
    }

    public void method_937()
    {
        if(field_1623 && field_1612 && !gettingOutOfWater())
        {
            velocityY += 0.029999999999999999D;
        }
        if(!field_1612 && random.nextInt(20) == 0 && field_1594 == null)
        {
            outOfWater++;
            y += outOfWater / 30;
            damage(this, 1);
        }
        if(health <= 0 || !field_1612 && field_1594 == null)
        {
            jumping = false;
            field_1060 = 0.0F;
            field_1029 = 0.0F;
            field_1030 = 0.0F;
        } else
        if(!field_1026)
        {
            method_910();
        }
        boolean flag = isSubmergedInWater();
        boolean flag1 = gettingOutOfWater();
        if(jumping && flag && !flag1)
        {
            velocityY += 0.02D;
        }
        field_1060 *= 0.98F;
        field_1029 *= 0.98F;
        field_1030 *= 0.9F;
        method_945(field_1060, field_1029);
        List list = world.getEntities(this, boundingBox.expand(0.20000000298023221D, 0.0D, 0.20000000298023221D));
        if(list != null && list.size() > 0)
        {
            for(int i = 0; i < list.size(); i++)
            {
                Entity entity = (Entity)list.get(i);
                if(entity.method_1380())
                {
                    entity.method_1353(this);
                }
            }

        }
    }

    public void writeNbt(NbtCompound nbttagcompound)
    {
        super.writeNbt(nbttagcompound);
    }

    public void readNbt(NbtCompound nbttagcompound)
    {
        super.readNbt(nbttagcompound);
    }

    protected String method_911()
    {
        return null;
    }

    protected String method_912()
    {
        return null;
    }

    protected String method_913()
    {
        return null;
    }

    protected float method_915()
    {
        return 0.4F;
    }

    protected String getUpsetSound()
    {
        return null;
    }

    private class_61 a;
    private int outOfWater;
    private boolean tamed;
    private int temper;
}
