
package net.kozibrodka.mocreatures.entity;

import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.kozibrodka.mocreatures.events.mod_mocreatures;
import net.minecraft.block.material.Material;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NbtCompound;
import net.minecraft.world.World;
import net.modificationstation.stationapi.api.server.entity.HasTrackingParameters;
import net.modificationstation.stationapi.api.util.Identifier;
import net.modificationstation.stationapi.api.server.entity.MobSpawnDataProvider;
import net.modificationstation.stationapi.api.util.TriState;

@HasTrackingParameters(trackingDistance = 160, updatePeriod = 2, sendVelocity = TriState.TRUE)
public class EntitySharkEgg extends LivingEntity implements MobSpawnDataProvider
{

    public EntitySharkEgg(World world)
    {
        super(world);
        setBoundingBoxSpacing(0.25F, 0.25F);
        tCounter = 0;
        lCounter = 0;
        texture = "/assets/mocreatures/stationapi/textures/mob/sharkeggt.png";
    }

    public EntitySharkEgg(World world, String graczol)
    {
        super(world);
        setBoundingBoxSpacing(0.25F, 0.25F);
        tCounter = 0;
        lCounter = 0;
        texture = "/assets/mocreatures/stationapi/textures/mob/sharkeggt.png";
        ktoKto = graczol;
    }

    @Override
    public void writeNbt(NbtCompound nbttagcompound)
    {
        nbttagcompound.putString("Opiekun", ktoKto);
    }

    @Override
    public void readNbt(NbtCompound nbttagcompound)
    {
        ktoKto = nbttagcompound.getString("Opiekun");
    }

    @Override
    protected void initDataTracker()
    {
    }

    @Override
    public void tickMovement()
    {
        if(world.isRemote){
            super.tickMovement();
        }
        sidewaysSpeed = 0.0F;
        forwardSpeed = 0.0F;
        rotationSpeed = 0.0F;
        travel(sidewaysSpeed, forwardSpeed);
    }

    @Override
    public void onPlayerInteraction(PlayerEntity entityplayer)
    {
        if(world.isRemote)
        {
            return;
        }
        if(lCounter > 10 && entityplayer.inventory.addStack(new ItemStack(mod_mocreatures.sharkegg, 1)))
        {
            world.playSound(this, "random.pop", 0.2F, ((random.nextFloat() - random.nextFloat()) * 0.7F + 1.0F) * 2.0F);
            world.broadcastEntityEvent(this, (byte)6);
            entityplayer.sendPickup(this, 1);
            markDead();
        }
    }

    @Override
    public void tick()
    {
        super.tick();
        if(world.isRemote){
            return;
        }
        if(random.nextInt(20) == 0)
        {
            lCounter++;
        }
        if(submergedInWater && random.nextInt(20) == 0)
        {
            tCounter++;
            if(tCounter >= 50)
            {
                EntityShark entityshark = new EntityShark(world);
                entityshark.setAge(0.3F);
                entityshark.setTamed(true);
                entityshark.setOwner(ktoKto);
                entityshark.protectMyOwner = true;
                entityshark.setType(1);
                entityshark.setPosition(x, y, z);
                world.spawnEntity(entityshark);
                world.playSound(this, "mob.chickenplop", 1.0F, (random.nextFloat() - random.nextFloat()) * 0.2F + 1.0F);
                world.broadcastEntityEvent(this, (byte)7);
                markDead();
            }
        }
    }

    @Override
    public boolean canBreatheInWater()
    {
        return true;
    }

    @Override
    public boolean checkWaterCollisions()
    {
        return world.updateMovementInFluid(boundingBox, Material.WATER, this);
    }

    @Override
    protected String getRandomSound()
    {
        return null;
    }

    @Override
    protected String getHurtSound()
    {
        return null;
    }

    @Override
    protected String getDeathSound()
    {
        return null;
    }

    @Override
    protected float getSoundVolume()
    {
        return 0.4F;
    }

    public String ktoKto;
    private int tCounter;
    private int lCounter;

    @Override
    public Identifier getHandlerIdentifier() {
        return Identifier.of(mod_mocreatures.MOD_ID, "SharkEgg");
    }

    @Override
    @Environment(EnvType.CLIENT)
    public void processServerEntityStatus(byte status) {
        if (status == 6) {
            world.playSound(this, "random.pop", 0.2F, ((random.nextFloat() - random.nextFloat()) * 0.7F + 1.0F) * 2.0F);
        }  else if (status == 7){
            world.playSound(this, "mob:chickenplop", 1.0F, 1.0F + (random.nextFloat() - random.nextFloat()) * 0.2F);
        }  else {
            super.processServerEntityStatus(status);
        }
    }

}
