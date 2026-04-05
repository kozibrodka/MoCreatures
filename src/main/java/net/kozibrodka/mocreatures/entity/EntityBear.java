package net.kozibrodka.mocreatures.entity;


import net.kozibrodka.mocreatures.events.mod_mocreatures;
import net.kozibrodka.mocreatures.mocreatures.MoCTools;
import net.minecraft.entity.Entity;
import net.minecraft.entity.ItemEntity;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.passive.AnimalEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NbtCompound;
import net.minecraft.world.World;
import net.modificationstation.stationapi.api.server.entity.MobSpawnDataProvider;
import net.modificationstation.stationapi.api.util.Identifier;

import java.util.List;


public class EntityBear extends AnimalEntity implements MobSpawnDataProvider
{

    public EntityBear(World world)
    {
        super(world);
        texture = "/assets/mocreatures/stationapi/textures/mob/bear.png";
        setBoundingBoxSpacing(0.9F, 1.3F);
        health = 25;
        force = 5;
        attackRange = 16D;
    }

    @Override
    protected Entity getTargetInRange()
    {
        if(world.difficulty > 0)
        {
            float f = getBrightnessAtEyes(1.0F);
            if(f < 0.5F)
            {
                PlayerEntity entityplayer = world.getClosestPlayer(this, attackRange);
                if(entityplayer != null)
                {
                    return entityplayer;
                }
            }
            if(random.nextInt(80) == 0)
            {
                LivingEntity entityliving = getClosestTarget(this, 10D);
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
            if(privateToIgnore(this, entity1) || MoCTools.entitiesToIgnore(this, entity1))
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

    public boolean privateToIgnore(Entity hunter, Entity victim) {
        return ((victim instanceof EntityBear) || (victim instanceof EntityBigCat) || (victim instanceof EntityShark) || (victim instanceof EntityWWolf) || (victim instanceof EntityDolphin) || (victim instanceof EntityCrocodile) || (victim instanceof EntityHippo) || (victim instanceof EntityElephant));
    }

    @Override
    public boolean damage(Entity entitybase, int i)
    {
        if(super.damage(entitybase, i))
        {
            if(passenger == entitybase || (vehicle == entitybase && !(vehicle instanceof EntityCrocodile)))
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

    @Override
    protected void attack(Entity entity, float f)
    {
        if((double)f < 2.5D && entity.boundingBox.maxY > boundingBox.minY && entity.boundingBox.minY < boundingBox.maxY)
        {
            attackCooldown = 20;
            entity.damage(this, force);
            if(!(entity instanceof PlayerEntity))
            {
                MoCTools.destroyDrops(this, 3D);
            }
        }
    }

    @Override
    public void writeNbt(NbtCompound nbttagcompound)
    {
        super.writeNbt(nbttagcompound);
    }

    @Override
    public void readNbt(NbtCompound nbttagcompound)
    {
        super.readNbt(nbttagcompound);
    }

    @Override
    protected String getRandomSound()
    {
        return "mocreatures:beargrunt";
    }

    @Override
    protected String getHurtSound()
    {
        return "mocreatures:bearhurt";
    }

    @Override
    protected String getDeathSound()
    {
        return "mocreatures:beardying";
    }

    @Override
    protected void dropItems()
    {
        int i = random.nextInt(3);
        for(int j = 0; j < i; j++)
        {
            dropItem(new ItemStack(getDroppedItemId(), 1, 0), 0.0F);
        }
    }

    @Override
    protected int getDroppedItemId()
    {
        return Item.RAW_FISH.id;
    }

    @Override
    public int getLimitPerChunk()
    {
        return 2;
    }

    public boolean cS2()
    {
        return super.canSpawn();
    }

    @Override
    public boolean canSpawn()
    {
        return mod_mocreatures.mocGlass.huntercreatures.bearfreq > 0 && !MoCTools.isNearTorch(this) && super.canSpawn();
    }

    protected double attackRange;
    protected int force;

    @Override
    public Identifier getHandlerIdentifier() {
        return Identifier.of(mod_mocreatures.MOD_ID, "Bear");
    }
}

