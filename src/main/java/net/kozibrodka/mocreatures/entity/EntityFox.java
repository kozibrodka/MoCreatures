
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

    @Override
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

    @Override
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
    protected float getSoundVolume()
    {
        return 0.3F;
    }

    @Override
    protected String getRandomSound()
    {
        return "mocreatures:foxcall";
    }

    @Override
    protected String getHurtSound()
    {
        return "mocreatures:foxhurt";
    }

    @Override
    protected String getDeathSound()
    {
        return "mocreatures:foxdying";
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
        return Item.LEATHER.id;
    }

    @Override
    public int getLimitPerChunk()
    {
        return 1;
    }

    @Override
    public boolean canSpawn()
    {
        return mod_mocreatures.mocGlass.huntercreatures.foxfreq > 0 && !MoCTools.isNearTorch(this) && super.canSpawn();
    }

    protected double attackRange;
    protected int force;

    @Override
    public Identifier getHandlerIdentifier() {
        return Identifier.of(mod_mocreatures.MOD_ID, "Fox");
    }
}
