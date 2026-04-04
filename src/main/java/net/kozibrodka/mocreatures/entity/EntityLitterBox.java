
package net.kozibrodka.mocreatures.entity;

import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.fabricmc.loader.FabricLoader;
import net.kozibrodka.mocreatures.events.mod_mocreatures;
import net.kozibrodka.mocreatures.mocreatures.MoCTools;
import net.kozibrodka.mocreatures.network.RopePacket;
import net.minecraft.block.Block;
import net.minecraft.entity.Entity;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.mob.CreeperEntity;
import net.minecraft.entity.mob.MonsterEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.item.PickaxeItem;
import net.minecraft.nbt.NbtCompound;
import net.minecraft.world.World;
import net.modificationstation.stationapi.api.network.packet.PacketHelper;
import net.modificationstation.stationapi.api.util.Identifier;
import net.modificationstation.stationapi.api.server.entity.HasTrackingParameters;
import net.modificationstation.stationapi.api.server.entity.MobSpawnDataProvider;

import java.util.List;
import java.util.Objects;

@HasTrackingParameters(trackingDistance = 160, updatePeriod = 2)
public class EntityLitterBox extends LivingEntity implements MobSpawnDataProvider
{

    public EntityLitterBox(World world)
    {
        super(world);
        setBoundingBoxSpacing(1.0F, 0.3F);
    }

    public void tick()
    {
        super.tick();
        if(world.isRemote){
            return;
        }
        if(onGround)
        {
            pickedUp = false;
        }
        if(getUsedLitter())
        {
            littertime++;
            world.addParticle("smoke", x, y, z, 0.0D, 0.0D, 0.0D);
            world.broadcastEntityEvent(this, (byte)6);
            List list = world.getEntities(this, boundingBox.expand(12D, 4D, 12D));
            for(int i = 0; i < list.size(); i++)
            {
                Entity entity = (Entity)list.get(i);
                if(!(entity instanceof MonsterEntity))
                {
                    continue;
                }
                MonsterEntity entitymob = (MonsterEntity)entity;
                entitymob.target = (this);
                if(entitymob instanceof CreeperEntity)
                {
                    ((CreeperEntity)entitymob).fuseTime = 5;
                }
                if(entitymob instanceof EntityOgre)
                {
                    ((EntityOgre)entitymob).ogrehasenemy = false;
                }
            }

        }
        if(littertime > 5000)
        {
            setUsedLitter(false);
        }
    }

    public void move(double d, double d1, double d2)
    {
        if(vehicle != null || !onGround || !mod_mocreatures.mocGlass.othersettings.staticlitter)
        {
            super.move(d, d1, d2);
        }
    }

    public void onCollision(Entity otherEntity) {
        if(vehicle instanceof PlayerEntity && otherEntity == vehicle.vehicle){
            return;
        }else {
            super.onCollision(otherEntity);
        }
    }

    protected void initDataTracker()
    {
        super.initDataTracker();
        dataTracker.startTracking(16, (byte) 0); //Used
    }

    public void writeNbt(NbtCompound nbttagcompound)
    {
        nbttagcompound.putBoolean("UsedLitter", getUsedLitter());
        nbttagcompound.putInt("LitterTime", littertime);
    }

    public void readNbt(NbtCompound nbttagcompound)
    {
        setUsedLitter(nbttagcompound.getBoolean("UsedLitter"));
        littertime = nbttagcompound.getInt("LitterTime");
    }

    public boolean isCollidable()
    {
        return !dead;
    }

    public boolean isPushable()
    {
        return !dead;
    }

    protected boolean canDespawn()
    {
        return false;
    }


    protected void tickLiving()
    {
    }

    protected void onLanding(float f)
    {
    }

    public boolean damage(Entity entity, int i)
    {
        return false;
    }

    public String getTexture()
    {
        return "/assets/mocreatures/stationapi/textures/mob/litterbox.png";
    }

    public boolean interact(PlayerEntity entityplayer)
    {
        ItemStack itemstack = entityplayer.inventory.getSelectedItem();
        if(world.isRemote){
            return false;
        }
        if(entityplayer.passenger != null && entityplayer.passenger != this){
            return false;
        }
        if(vehicle instanceof PlayerEntity && !Objects.equals(((PlayerEntity) vehicle).name, entityplayer.name)){
            return false;
        }
        if(itemstack != null && itemstack.getItem() instanceof PickaxeItem)
        {
            entityplayer.inventory.addStack(new ItemStack(mod_mocreatures.litterbox));
            world.playSound(this, "random.pop", 0.2F, ((random.nextFloat() - random.nextFloat()) * 0.7F + 1.0F) * 2.0F);
            world.broadcastEntityEvent(this, (byte)7);
            markDead();
            return true;
        }
        if(itemstack != null && itemstack.itemId == Block.SAND.id)
        {
            if(--itemstack.count == 0)
            {
                entityplayer.inventory.setStack(entityplayer.inventory.selectedSlot, null);
            }
            setUsedLitter(false);
            littertime = 0;
            return true;
        } else
        {
            yaw = entityplayer.yaw;
            setVehicle(entityplayer);
            if (net.fabricmc.loader.FabricLoader.INSTANCE.getEnvironmentType() == EnvType.SERVER) {
                PacketHelper.sendTo(entityplayer, new RopePacket("box", this.id, entityplayer.name));
            }
            world.playSound(this, "mob.chickenplop", 1.0F, (random.nextFloat() - random.nextFloat()) * 0.2F + 1.0F);
            world.broadcastEntityEvent(this, (byte)8);
            return true;
        }
    }

    public double getStandingEyeHeight()
    {
        if(vehicle instanceof PlayerEntity)
        {
            pickedUp = true;
            if (FabricLoader.INSTANCE.getEnvironmentType() == EnvType.SERVER){
                return (double)(standingEyeHeight + 0.49F); //+0
            }else{
                return (double)(standingEyeHeight - 1.15F);
            }
        } else
        {
            return (double)standingEyeHeight;
        }
    }

    protected float getSoundVolume()
    {
        return 0.0F;
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

    public boolean canBreatheInWater()
    {
        return true;
    }

    public boolean pickedUp; ///useless?
    public int littertime;

    @Override
    public Identifier getHandlerIdentifier() {
        return Identifier.of(mod_mocreatures.MOD_ID, "LitterBox");
    }

    @Environment(EnvType.CLIENT)
    public void processServerEntityStatus(byte status) {
        if (status == 6) {
            world.addParticle("smoke", x, y, z, 0.0D, 0.0D, 0.0D);
        } else if (status == 7) {
            world.playSound(this, "random.pop", 0.2F, ((random.nextFloat() - random.nextFloat()) * 0.7F + 1.0F) * 2.0F);
        } else if (status == 8) {
            world.playSound(this, "mob.chickenplop", 1.0F, 1.0F + (random.nextFloat() - random.nextFloat()) * 0.2F);
        }  else {
            super.processServerEntityStatus(status);
        }
    }

    //Used
    public boolean getUsedLitter()
    {
        return (dataTracker.getByte(16) & 1) != 0;
    }

    public void setUsedLitter(boolean flag)
    {
        if(flag)
        {
            dataTracker.set(16, (byte) 1);
        } else
        {
            dataTracker.set(16, (byte) 0);
        }
    }
}
