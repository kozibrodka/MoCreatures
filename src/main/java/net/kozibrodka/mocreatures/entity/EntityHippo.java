package net.kozibrodka.mocreatures.entity;

import net.kozibrodka.mocreatures.events.mod_mocreatures;
import net.minecraft.block.Block;
import net.minecraft.entity.Entity;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.mob.MonsterEntity;
import net.minecraft.entity.passive.AnimalEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.Item;
import net.minecraft.nbt.NbtCompound;
import net.minecraft.util.math.MathHelper;
import net.minecraft.world.World;
import net.modificationstation.stationapi.api.server.entity.MobSpawnDataProvider;
import net.modificationstation.stationapi.api.util.Identifier;

import java.util.List;

public class EntityHippo extends AnimalEntity implements MobSpawnDataProvider {

    public EntityHippo(World world)
    {
        super(world);
        texture = "/assets/mocreatures/stationapi/textures/mob/hippo.png";
        setBoundingBoxSpacing(2.0F, 1.4F);
        health = 55;
        movementSpeed = 0.45F;
    }

    public void tickMovement() {
        super.tickMovement();
    }

    protected void tickLiving(){
        if(this.target instanceof PlayerEntity){
            PlayerEntity uciekinier = world.getClosestPlayer(this, 16D);
            if(uciekinier == null && target.isAlive()){
                if(random.nextInt(30) == 0)
                {
                    target = null;
                }
            }
        }
        if(checkWaterCollisions())
        {
            standingEyeHeight = -0.76F;
        } else
        if((double)standingEyeHeight < 0.0050000000000000001D)
        {
            standingEyeHeight += 0.005F;
        }
        super.tickLiving();
    }

    protected float getPathfindingFavor(int i, int j, int k)
    {
        if(world.getBlockId(i, j - 1, k) == Block.WATER.id || world.getBlockId(i, j - 1, k) == Block.FLOWING_WATER.id)
        {
            return 10F; ///SZUKA WODY
        } else
        {
            return -(float)j;
        }
    }

    protected Entity getTargetInRange()
    {
        if(world.difficulty > 0 && false)
        {
            PlayerEntity entityplayer = world.getClosestPlayer(this, 8D);
            if(entityplayer != null)
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

    public LivingEntity getClosestTarget(Entity entity, double d)
    {
        double d1 = -1D;
        LivingEntity entityliving = null;
        List list = world.getEntities(this, boundingBox.expand(d, d, d));
        for(int i = 0; i < list.size(); i++)
        {
            Entity entity1 = (Entity)list.get(i);
            if(!(entity1 instanceof LivingEntity) || entity1 == entity || entity1 == entity.passenger || entity1 == entity.vehicle || (entity1 instanceof EntityHippo) || (entity1 instanceof MonsterEntity))
            {
                continue;
            }
            double d2 = entity1.getSquaredDistance(entity.x, entity.y, entity.z);
            if((d < 0.0D || d2 < d * d) && (d1 == -1D || d2 < d1) && ((LivingEntity)entity1).canSee(entity))
            {
                d1 = d2;
                entityliving = (LivingEntity)entity1;
            }
        }

        return entityliving;
    }

    protected void attack(Entity entity, float f)
    {
        if(onGround)
        {
            double d = entity.x - x;
            double d1 = entity.z - z;
            float f1 = MathHelper.sqrt(d * d + d1 * d1);
            velocityX = (d / (double)f1) * 0.20000000000000001D * (0.850000011920929D + velocityX * 0.20000000298023224D);
            velocityZ = (d1 / (double)f1) * 0.20000000000000001D * (0.80000001192092896D + velocityZ * 0.20000000298023224D);
            velocityY = 0.10000000596246449D;
            fallDistance = -25F;
        }
        if((double)f < 3.1000000000000001D && entity.boundingBox.maxY > boundingBox.minY && entity.boundingBox.minY < boundingBox.maxY)
        {
            attackCooldown = 20;
            entity.damage(this, 2); //todo
        }
    }

    public boolean damage(Entity entitybase, int i)
    {
        if(super.damage(entitybase, i))
        {
            if(passenger == entitybase || vehicle == entitybase)
            {
                return true;
            }
            if(entitybase != this && world.difficulty > 0)
            {
                target = entitybase;
            }
            return true;
        } else
        {
            return false;
        }
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

    public int getLimitPerChunk()
    {
        return 2;
    }

    protected int getDroppedItemId()
    {
        return Item.RAW_PORKCHOP.id;
    }

    protected String getRandomSound()
    {
        return "mocreatures:hippo";
    }

    protected String getHurtSound()
    {
        return "mocreatures:hippohurt";
    }

    protected String getDeathSound()
    {
        return "mocreatures:hippodeath";
    }

    public boolean canSpawn()
    {
        return mocr.mocreaturesGlass.huntercreatures.hippofreq > 0 && super.canSpawn();
    }

    mod_mocreatures mocr = new mod_mocreatures();

    @Override
    public Identifier getHandlerIdentifier() {return Identifier.of(mod_mocreatures.MOD_ID, "Hippo");}
}
