
package net.kozibrodka.mocreatures.entity;

import net.kozibrodka.mocreatures.events.mod_mocreatures;
import net.kozibrodka.mocreatures.mocreatures.MoCTools;
import net.minecraft.entity.Entity;
import net.minecraft.entity.ItemEntity;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.passive.PigEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.nbt.NbtCompound;
import net.minecraft.world.World;
import net.modificationstation.stationapi.api.util.Identifier;
import net.modificationstation.stationapi.api.server.entity.MobSpawnDataProvider;

import java.util.List;


public class EntityBoar extends PigEntity implements MobSpawnDataProvider
{

    public EntityBoar(World world)
    {
        super(world);
        texture = "/assets/mocreatures/stationapi/textures/mob/boar.png";
        setBoundingBoxSpacing(0.9F, 0.9F);
        health = 10;
        force = 1;
        attackRange = 1.0D;
    }

    @Override
    public void tickMovement()
    {
        if(world.difficulty == 1)
        {
            attackRange = 2D;
            force = 1;
        } else
        if(world.difficulty > 1)
        {
            attackRange = 3D;
            force = 2;
        }
        super.tickMovement();
    }

    @Override
    protected Entity getTargetInRange()
    {
        if(world.difficulty > 0)
        {
            PlayerEntity entityplayer = world.getClosestPlayer(this, attackRange);
            if(entityplayer != null && random.nextInt(50) == 0)
            {
                return entityplayer;
            }
            if(random.nextInt(80) == 0)
            {
                /// Nie znajduje nigdy ofiary, prawdopodobnie przez Extend EntityPig, ale imo nawet dobrze w praktyce.
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
            double d2 = entity1.getSquaredDistance(entity.y, entity.z, entity.velocityX);
            if((d < 0.0D || d2 < d * d) && (d1 == -1D || d2 < d1) && ((LivingEntity)entity1).canSee(entity))
            {
                d1 = d2;
                entityliving = (LivingEntity)entity1;
            }
        }

        return entityliving;
    }

    public boolean privateToIgnore(Entity hunter, Entity victim) {
        return (height <= victim.height || width <= victim.width || victim instanceof EntityBoar);
    }

    @Override
    public boolean damage(Entity entityBase, int i)
    {
        if(super.damage(entityBase, i))
        {
            if(passenger == entityBase || vehicle == entityBase)
            {
                return true;
            }
            if(entityBase != this && world.difficulty > 0)
            {
                target = entityBase;
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
    public boolean canSpawn()
    {
        return mod_mocreatures.mocGlass.huntercreatures.boarfreq > 0 && super.canSpawn();
    }

    protected int force;
    protected double attackRange;

    @Override
    public Identifier getHandlerIdentifier() {
        return Identifier.of(mod_mocreatures.MOD_ID, "Boar");
    }
}
