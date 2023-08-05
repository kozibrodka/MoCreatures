// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) braces deadcode 

package net.kozibrodka.mocreatures.entity;

import net.kozibrodka.mocreatures.events.mod_mocreatures;
import net.minecraft.block.BlockBase;
import net.minecraft.class_61;
import net.minecraft.entity.EntityBase;
import net.minecraft.entity.monster.MonsterBase;
import net.minecraft.entity.player.PlayerBase;
import net.minecraft.level.Level;
import net.minecraft.util.maths.MathHelper;
import net.minecraft.util.maths.Vec3f;
import net.modificationstation.stationapi.api.registry.Identifier;
import net.modificationstation.stationapi.api.server.entity.MobSpawnDataProvider;

import java.util.Random;


public class EntityFlyerMob extends MonsterBase
{

    public EntityFlyerMob(Level world)
    {
        super(world);
        field_1625 = false;
        speedModifier = 0.029999999999999999D;
        setSize(1.5F, 1.5F);
        attackStrength = 3;
        health = 10;
    }

    protected void handleFallDamage(float f)
    {
    }

    public void travel(float f, float f1)
    {
        if(method_1393())
        {
            double d = y;
            movementInputToVelocity(f, f1, 0.02F);
            move(velocityX, velocityY, velocityZ);
            velocityX *= 0.80000001192092896D;
            velocityY *= 0.80000001192092896D;
            velocityZ *= 0.80000001192092896D;
        } else
        if(method_1335())
        {
            double d1 = y;
            movementInputToVelocity(f, f1, 0.02F);
            move(velocityX, velocityY, velocityZ);
            velocityX *= 0.5D;
            velocityY *= 0.5D;
            velocityZ *= 0.5D;
        } else
        {
            float f2 = 0.91F;
            if(onGround)
            {
                f2 = 0.5460001F;
                int i = level.getTileId(MathHelper.floor(x), MathHelper.floor(boundingBox.minY) - 1, MathHelper.floor(z));
                if(i > 0)
                {
                    f2 = BlockBase.BY_ID[i].slipperiness * 0.91F;
                }
            }
            float f3 = 0.162771F / (f2 * f2 * f2);
            movementInputToVelocity(f, f1, onGround ? 0.1F * f3 : 0.02F);
            f2 = 0.91F;
            if(onGround)
            {
                f2 = 0.5460001F;
                int j = level.getTileId(MathHelper.floor(x), MathHelper.floor(boundingBox.minY) - 1, MathHelper.floor(z));
                if(j > 0)
                {
                    f2 = BlockBase.BY_ID[j].slipperiness * 0.91F;
                }
            }
            move(velocityX, velocityY, velocityZ);
            velocityX *= f2;
            velocityY *= f2;
            velocityZ *= f2;
            if(field_1624)
            {
                velocityY = 0.20000000000000001D;
            }
            if(rand.nextInt(30) == 0)
            {
                velocityY = -0.25D;
            }
        }
        field_1048 = limbDistance;
        double d2 = x - prevX;
        double d3 = z - prevZ;
        float f4 = MathHelper.sqrt(d2 * d2 + d3 * d3) * 4F;
        if(f4 > 1.0F)
        {
            f4 = 1.0F;
        }
        limbDistance += (f4 - limbDistance) * 0.4F;
        field_1050 += limbDistance;
    }

    public boolean method_932()
    {
        return false;
    }

    protected EntityBase getAttackTarget()
    {
        PlayerBase entityplayer = level.getClosestPlayerTo(this, 20D);
        if(entityplayer != null && method_928(entityplayer))
        {
            return entityplayer;
        } else
        {
            return null;
        }
    }

    protected void tickHandSwing()
    {
        field_663 = false;
        float f = 16F;
        if(entity == null)
        {
            entity = getAttackTarget();
            if(entity != null)
            {
                entitypath = level.method_192(this, entity, f);
            }
        } else
        if(!entity.isAlive())
        {
            entity = null;
        } else
        {
            float f1 = entity.distanceTo(this);
            if(method_928(entity))
            {
                tryAttack(entity, f1);
            }
        }
        if(!field_663 && entity != null && (entitypath == null || rand.nextInt(10) == 0))
        {
            entitypath = level.method_192(this, entity, f);
        } else
        if(entitypath == null && rand.nextInt(80) == 0 || rand.nextInt(80) == 0)
        {
            boolean flag = false;
            int j = -1;
            int k = -1;
            int l = -1;
            float f2 = -99999F;
            for(int i1 = 0; i1 < 10; i1++)
            {
                int j1 = MathHelper.floor((x + (double)rand.nextInt(13)) - 6D);
                int k1 = MathHelper.floor((y + (double)rand.nextInt(7)) - 3D);
                int l1 = MathHelper.floor((z + (double)rand.nextInt(13)) - 6D);
                float f3 = getPathfindingFavour(j1, k1, l1);
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
                entitypath = level.method_189(this, j, k, l, 10F);
            }
        }
        int i = MathHelper.floor(boundingBox.minY);
        boolean flag1 = method_1393();
        boolean flag2 = method_1335();
        pitch = 0.0F;
        if(entitypath == null || rand.nextInt(100) == 0)
        {
            super.tickHandSwing();
            entitypath = null;
            return;
        }
        Vec3f vec3d = entitypath.method_2041(this);
        for(double d = width * 2.0F; vec3d != null && vec3d.method_1303(x, vec3d.y, z) < d * d;)
        {
            entitypath.method_2040();
            if(entitypath.method_2042())
            {
                vec3d = null;
                entitypath = null;
            } else
            {
                vec3d = entitypath.method_2041(this);
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
            if(field_663 && entity != null)
            {
                double d4 = entity.x - x;
                double d5 = entity.z - z;
                float f6 = yaw;
                yaw = (float)((Math.atan2(d5, d4) * 180D) / 3.1415927410125728D) - 90F;
                float f7 = (((f6 - yaw) + 90F) * 3.141593F) / 180F;
                field_1060 = -MathHelper.sin(f7) * field_1029 * 1.0F;
                field_1029 = MathHelper.cos(f7) * field_1029 * 1.0F;
            }
            if(d3 > 0.0D)
            {
                jumping = true;
            }
        }
        if(entity != null)
        {
            method_924(entity, 30F, 30F);
        }
        if(field_1624)
        {
            jumping = true;
        }
        if(rand.nextFloat() < 0.8F && (flag1 || flag2))
        {
            jumping = true;
        }
    }

    protected void tryAttack(EntityBase entity, float f)
    {
        if((double)f < 2.5D && entity.boundingBox.maxY > boundingBox.minY && entity.boundingBox.minY < boundingBox.maxY)
        {
            attackTime = 20;
            entity.damage(this, attackStrength);
        }
    }

    public boolean canSpawn()
    {
        return super.canSpawn();
    }

    protected int attackStrength;
    private class_61 entitypath;
    public double speedModifier;

}
