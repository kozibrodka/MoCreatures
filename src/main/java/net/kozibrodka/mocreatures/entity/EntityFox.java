
package net.kozibrodka.mocreatures.entity;

import net.kozibrodka.mocreatures.events.mod_mocreatures;
import net.kozibrodka.mocreatures.mocreatures.MoCTools;
import net.minecraft.entity.Entity;
import net.minecraft.entity.ItemEntity;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.mob.MonsterEntity;
import net.minecraft.entity.passive.AnimalEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NbtCompound;
import net.minecraft.world.World;
import net.modificationstation.stationapi.api.util.Identifier;
import net.modificationstation.stationapi.api.server.entity.MobSpawnDataProvider;

import java.util.List;

public class EntityFox extends AnimalEntity implements MobSpawnDataProvider
{

    public EntityFox(World world)
    {
        super(world);
        texture = "/assets/mocreatures/stationapi/textures/mob/fox.png";
        setBoundingBoxSpacing(0.9F, 1.3F);
        health = 15;
        force = 2;
        attackRange = 4D;
    }

    protected Entity getTargetInRange()
    {
        if(random.nextInt(80) == 0 && world.difficulty > 0)
        {
            LivingEntity entityliving = getClosestTarget(this, 8D);
            return entityliving;
        } else
        {
            return null;
        }
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
        return (height <= victim.height || width <= victim.width || victim instanceof EntityFox);
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

    protected void attack(Entity entity, float f)
    {
        if((double)f < 2.5D && entity.boundingBox.maxY > boundingBox.minY && entity.boundingBox.minY < boundingBox.maxY)
        {
            attackCooldown = 20;
            entity.damage(this, force);
            if(!(entity instanceof PlayerEntity))
            {
                destroyDrops(this, 3D);
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

    protected float getSoundVolume()
    {
        return 0.3F;
    }

    protected String getRandomSound()
    {
        return "mocreatures:foxcall";
    }

    protected String getHurtSound()
    {
        return "mocreatures:foxhurt";
    }

    protected String getDeathSound()
    {
        return "mocreatures:foxdying";
    }

    protected void dropItems()
    {
        int i = random.nextInt(3);
        for(int j = 0; j < i; j++)
        {
            dropItem(new ItemStack(getDroppedItemId(), 1, 0), 0.0F);
        }
    }

    protected int getDroppedItemId()
    {
        return Item.LEATHER.id;
    }

    public int getLimitPerChunk()
    {
        return 1;
    }

    public void destroyDrops(Entity entity, double d)
    {
        List list = world.getEntities(entity, entity.boundingBox.expand(d, d, d));
        for(int i = 0; i < list.size(); i++)
        {
            Entity entity1 = (Entity)list.get(i);
            if(!(entity1 instanceof ItemEntity))
            {
                continue;
            }
            ItemEntity entityitem = (ItemEntity)entity1;
            if(entityitem.itemAge < 50 && mocr.mocreaturesGlass.huntercreatures.destroyitems)
            {
                entityitem.markDead();
            }
        }

    }

    public boolean canSpawn()
    {
        return mocr.mocreaturesGlass.huntercreatures.foxfreq > 0 && !MoCTools.isNearTorch(this) && super.canSpawn();
    }

    mod_mocreatures mocr = new mod_mocreatures();
    protected double attackRange;
    protected int force;

    @Override
    public Identifier getHandlerIdentifier() {
        return Identifier.of(mod_mocreatures.MOD_ID, "Fox");
    }
}
