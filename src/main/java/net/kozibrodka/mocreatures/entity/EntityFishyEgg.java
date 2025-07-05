// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) braces deadcode 

package net.kozibrodka.mocreatures.entity;

import net.kozibrodka.mocreatures.events.mod_mocreatures;
import net.minecraft.block.material.Material;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.world.World;
import net.modificationstation.stationapi.api.util.Identifier;
import net.modificationstation.stationapi.api.server.entity.MobSpawnDataProvider;

public class EntityFishyEgg extends LivingEntity implements MobSpawnDataProvider
{

    public EntityFishyEgg(World world)
    {
        super(world);
        setBoundingBoxSpacing(0.25F, 0.25F);
        tCounter = 0;
        lCounter = 0;
        texture = "/assets/mocreatures/stationapi/textures/mob/fishyeggt.png";
    }

    protected void initDataTracker()
    {
    }

    public void tickMovement()
    {
        sidewaysSpeed = 0.0F;
        forwardSpeed = 0.0F;
        rotationSpeed = 0.0F;
        travel(sidewaysSpeed, forwardSpeed);
    }

    public void onPlayerInteraction(PlayerEntity entityplayer)
    {
        if(world.isRemote)
        {
            return;
        }
        if(lCounter > 10 && entityplayer.inventory.addStack(new ItemStack(mod_mocreatures.fishyegg, 1)))
        {
            world.playSound(this, "random.pop", 0.2F, ((random.nextFloat() - random.nextFloat()) * 0.7F + 1.0F) * 2.0F);
            entityplayer.sendPickup(this, 1);
            markDead();
        }
    }

    public void tick()
    {
        super.tick();
        if(random.nextInt(20) == 0)
        {
            lCounter++;
        }
        if(submergedInWater && random.nextInt(20) == 0)
        {
            tCounter++;
            if(tCounter >= 50)
            {
                EntityFishy entityfishy = new EntityFishy(world);
                entityfishy.setPosition(x, y, z);
                entityfishy.setTypeSpawn();
                entityfishy.setAge(0.3F);
                world.spawnEntity(entityfishy);
                world.playSound(this, "mob.chickenplop", 1.0F, (random.nextFloat() - random.nextFloat()) * 0.2F + 1.0F);
                entityfishy.setTamed(true);
                markDead();
            }
        }
    }

    public boolean canBreatheInWater()
    {
        return true;
    }

    public boolean checkWaterCollisions()
    {
        return world.updateMovementInFluid(boundingBox, Material.WATER, this);
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

    private int tCounter;
    private int lCounter;

    @Override
    public Identifier getHandlerIdentifier() {
        return Identifier.of(mod_mocreatures.MOD_ID, "FishyEgg");
    }
}
