// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) braces deadcode 

package net.kozibrodka.mocreatures.entity;

import net.kozibrodka.mocreatures.mixin.EntityBaseAccesor;
import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.entity.Entity;
import net.minecraft.entity.ItemEntity;
import net.minecraft.entity.WaterCreatureEntity;
import net.minecraft.entity.ai.pathing.Path;
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

    public boolean checkWaterCollisions()
    {
        return world.updateMovementInFluid(boundingBox, Material.WATER, this);
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

    public boolean istamed() //TODO: Make dolhin overrite THOSE i elo.
    {
        return tamed;
    }

    public void setTame()
    {
        tamed = true;
    }

    public void travel(float f, float f1)
    {
        if(checkWaterCollisions())
        {
            if(passenger != null && !istamed())
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
                    passenger.velocityY += 0.90000000000000002D;
                    passenger.velocityZ -= 0.29999999999999999D;
                    passenger = null;
                }
                if(onGround)
                {
                    jumping = false;
                }
                if(random.nextInt(tametemper() * 8) == 0)
                {
                    setTame();
                }
            }
            if(passenger != null && istamed())
            {
                boundingBox.maxY = passenger.boundingBox.maxY;
                velocityX += passenger.velocityX * speed();
                velocityZ += passenger.velocityZ * speed();
                if(velocityY != 0.0D)
                {
                    velocityY = 0.0D;
                }
                move(velocityX, velocityY, velocityZ);
                pitch = passenger.pitch * 0.5F;
                prevYaw = yaw = passenger.yaw;
                setRotation(yaw, pitch);
            }
            moveNonSolid(f, f1, 0.02F);
            move(velocityX, velocityY, velocityZ);
            if(passenger == null)
            {
                velocityX *= 0.80000001192092896D;
                velocityZ *= 0.80000001192092896D;
            }
        } else
        if(isTouchingLava())
        {
            double d = y;
            moveNonSolid(f, f1, 0.02F);
            move(velocityX, velocityY, velocityZ);
            velocityX *= 0.5D;
            velocityY *= 0.5D;
            velocityZ *= 0.5D;
            velocityY -= 0.02D;
            if(horizontalCollision && getEntitiesInside(velocityX, ((velocityY + 0.60000002384185791D) - y) + d, velocityZ))
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
        moveNonSolid(f, f1, 0.1F * f3);
        f2 = 0.91F;
        f2 = 0.5460001F;
        int j = world.getBlockId(MathHelper.floor(x), MathHelper.floor(boundingBox.minY) - 1, MathHelper.floor(z));
        if(j > 0)
        {
            f2 = Block.BLOCKS[j].slipperiness * 0.91F;
        }
        if(isOnLadder())
        {
            fallDistance = 0.0F;
            if(velocityY < -0.14999999999999999D)
            {
                velocityY = -0.14999999999999999D;
            }
        }
        move(velocityX, velocityY, velocityZ);
        if(horizontalCollision && isOnLadder())
        {
            velocityY = 0.20000000000000001D;
        }
        velocityX *= f2;
        velocityZ *= f2;
        if(!checkWaterCollisions())
        {
            velocityY -= 0.080000000000000002D;
            velocityY *= 0.98000001907348633D;
        } else
        if(passenger == null)
        {
            velocityY -= 0.02D;
            velocityY *= 0.5D;
        }
        lastWalkAnimationSpeed = walkAnimationSpeed;
        double d1 = x - prevX;
        double d2 = z - prevZ;
        float f4 = MathHelper.sqrt(d1 * d1 + d2 * d2) * 4F;
        if(f4 > 1.0F)
        {
            f4 = 1.0F;
        }
        walkAnimationSpeed += (f4 - walkAnimationSpeed) * 0.4F;
        walkAnimationProgress += walkAnimationSpeed;
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

    protected void tickLiving()
    {
        if(passenger != null && tamed)
        {
            return;
        }
        movementBlocked = false;
        float f = 16F;
        if(target == null)
        {
            target = getTargetInRange();
            if(target != null && ((EntityBaseAccesor)target).getSubmergedInWater())
            {
                a = world.findPath(this, target, f);
            }
        } else
        if(!target.isAlive() || !((EntityBaseAccesor)target).getSubmergedInWater())
        {
            target = null;
        } else
        {
            float f1 = target.getDistance(this);
            if(canSee(target))
            {
                attack(target, f1);
            }
        }
        if(!movementBlocked && target != null && ((EntityBaseAccesor)target).getSubmergedInWater() && (a == null || random.nextInt(20) == 0))
        {
            a = world.findPath(this, target, f);
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
                float f3 = getPathfindingFavor(j1, k1, l1);
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
                a = world.findPath(this, j, k, l, 10F);
            }
        }
        int i = MathHelper.floor(boundingBox.minY);
        boolean flag1 = checkWaterCollisions();
        boolean flag2 = isTouchingLava();
        pitch = 0.0F;
        if(a == null || random.nextInt(100) == 0)
        {
            super.tickLiving();
            a = null;
            return;
        }
        Vec3d vec3d = a.getNodePosition(this);
        for(double d = width * 2.0F; vec3d != null && vec3d.squaredDistanceTo(x, vec3d.y, z) < d * d;)
        {
            a.next();
            if(a.isFinished())
            {
                vec3d = null;
                a = null;
            } else
            {
                vec3d = a.getNodePosition(this);
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
            forwardSpeed = movementSpeed;
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
            if(movementBlocked && target != null)
            {
                double d4 = target.x - x;
                double d5 = target.z - z;
                float f6 = yaw;
                yaw = (float)((Math.atan2(d5, d4) * 180D) / 3.1415927410125728D) - 90F;
                float f7 = (((f6 - yaw) + 90F) * 3.141593F) / 180F;
                sidewaysSpeed = -MathHelper.sin(f7) * forwardSpeed * 1.0F;
                forwardSpeed = MathHelper.cos(f7) * forwardSpeed * 1.0F;
            }
            if(d3 > 0.0D && target != null && ((EntityBaseAccesor)target).getSubmergedInWater())
            {
                jumping = true;
            }
        }
        if(target != null)
        {
            lookAt(target, 30F, 30F);
        }
        if(horizontalCollision)
        {
            jumping = true;
        }
        if(random.nextFloat() < 0.8F && (flag1 || flag2))
        {
            jumping = true;
        }
    }

    protected void onLanding(float f)
    {
        if(!submergedInWater)
        {
            super.onLanding(f);
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
            if(entityitem1.stack.itemId != Item.RAW_FISH.id || !((EntityBaseAccesor)entityitem1).getSubmergedInWater())
            {
                continue;
            }
            double d2 = entityitem1.getSquaredDistance(entity.x, entity.y, entity.z);
            if((d < 0.0D || d2 < d * d) && (d1 == -1D || d2 < d1))
            {
                d1 = d2;
                entityitem = entityitem1;
            }
        }
        return entityitem;
    }

    public void tickMovement()
    {
        if(onGround && submergedInWater && !gettingOutOfWater())
        {
            velocityY += 0.029999999999999999D;
        }
        if(!submergedInWater && random.nextInt(20) == 0 && passenger == null)
        {
            outOfWater++;
            y += outOfWater / 30;
            damage(this, 1);
        }
        if(health <= 0 || !submergedInWater && passenger == null)
        {
            jumping = false;
            sidewaysSpeed = 0.0F;
            forwardSpeed = 0.0F;
            rotationSpeed = 0.0F;
        } else
        if(!interpolateOnly)
        {
            tickLiving();
        }
        boolean flag = checkWaterCollisions();
        boolean flag1 = gettingOutOfWater();
        if(jumping && flag && !flag1)
        {
            velocityY += 0.02D;
        }
        sidewaysSpeed *= 0.98F;
        forwardSpeed *= 0.98F;
        rotationSpeed *= 0.9F;
        travel(sidewaysSpeed, forwardSpeed);
        List list = world.getEntities(this, boundingBox.expand(0.20000000298023221D, 0.0D, 0.20000000298023221D));
        if(list != null && list.size() > 0)
        {
            for(int i = 0; i < list.size(); i++)
            {
                Entity entity = (Entity)list.get(i);
                if(entity.isPushable())
                {
                    entity.onCollision(this);
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

    protected String getRandomSound()
    {
        return null;
    }

    protected String getHurtSound()
    {
        return null;
    }

    protected String getDeathSound()
    {
        return null;
    }

    protected float getSoundVolume()
    {
        return 0.4F;
    }

    protected String getUpsetSound()
    {
        return null;
    }

    private Path a;
    private int outOfWater;
    private boolean tamed;
    private int temper;
}
