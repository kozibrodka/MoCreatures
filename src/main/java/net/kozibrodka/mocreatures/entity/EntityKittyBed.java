
package net.kozibrodka.mocreatures.entity;

import net.fabricmc.api.EnvType;
import net.fabricmc.loader.FabricLoader;
import net.kozibrodka.mocreatures.events.mod_mocreatures;
import net.kozibrodka.mocreatures.network.RopePacket;
import net.minecraft.entity.Entity;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.item.PickaxeItem;
import net.minecraft.nbt.NbtCompound;
import net.minecraft.util.math.Vec3d;
import net.minecraft.world.World;
//import net.modificationstation.stationapi.api.packet.Message;
import net.modificationstation.stationapi.api.network.packet.PacketHelper;
import net.modificationstation.stationapi.api.util.Identifier;
import net.modificationstation.stationapi.api.server.entity.HasTrackingParameters;
import net.modificationstation.stationapi.api.server.entity.MobSpawnDataProvider;

import java.util.Objects;

@HasTrackingParameters(trackingDistance = 160, updatePeriod = 2)
public class EntityKittyBed extends LivingEntity implements MobSpawnDataProvider
{

    public EntityKittyBed(World world)
    {
        super(world);
        setHasMilk(false);
        setBoundingBoxSpacing(1.0F, 0.3F);
        setMilkLevel(0.0F);
        setHasFood(false);
    }

    public EntityKittyBed(World world, int i)
    {
        this(world);
        setSheetColour(i);
    }

    public void move(double d, double d1, double d2)
    {
        if(vehicle != null || !onGround || !mod_mocreatures.mocGlass.othersettings.staticbed)
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

    public void tick()
    {
        super.tick();
        if(world.isRemote){
            return;
        }
        if(onGround)
        {
            setPicked(false);
        }
        if((getHasMilk() || getHasFood()) && passenger != null)
        {
            setMilkLevel(getMilkLevel() + 0.003F);
            if(getMilkLevel() > 2.0F)
            {
                setMilkLevel(0.0F);
                setHasMilk(false);
                setHasFood(false);
            }
        }
    }

    protected void initDataTracker()
    {
        super.initDataTracker();
        dataTracker.startTracking(16, (byte) 0); //SheetColour
        dataTracker.startTracking(17, (int) 0); //MilkLevel
        dataTracker.startTracking(18, (byte) 0); //HasFood
        dataTracker.startTracking(19, (byte) 0); //HasMilk
        dataTracker.startTracking(20, (byte) 0); //Picked
    }

    public void writeNbt(NbtCompound nbttagcompound)
    {
        nbttagcompound.putBoolean("HasMilk", getHasMilk());
        nbttagcompound.putInt("SheetColour", getSheetColour());
        nbttagcompound.putBoolean("HasFood", getHasFood());
        nbttagcompound.putFloat("MilkLevel", getMilkLevel());
    }

    public void readNbt(NbtCompound nbttagcompound)
    {
        setHasMilk(nbttagcompound.getBoolean("HasMilk"));
        setSheetColour(nbttagcompound.getInt("SheetColour"));
        setHasFood(nbttagcompound.getBoolean("HasFood"));
        setMilkLevel(nbttagcompound.getFloat("MilkLevel"));
    }

    public boolean canSee(Entity entity)
    {
//        return world.raycast(Vec3d.createCached(x, y + (double)getShadowRadius(), z), Vec3d.createCached(entity.x, entity.y + (double)entity.getShadowRadius(), entity.z)) == null;
        return world.raycast(Vec3d.createCached(x, y + (double)(this.height / 2.0F), z), Vec3d.createCached(entity.x, entity.y + (double)(entity.height / 2.0F), entity.z)) == null;
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

    public void processServerEntityStatus(byte byte0)
    {
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
        return "/assets/mocreatures/stationapi/textures/mob/kittybed.png";
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
        if(itemstack != null && itemstack.itemId == Item.MILK_BUCKET.id)
        {
            world.playSound(this, "mocreatures:pouringmilk", 1.0F, 1.0F + (random.nextFloat() - random.nextFloat()) * 0.2F);
            sendSound(world, "mocreatures:pouringmilk", 1.0F, 1.0F + (random.nextFloat() - random.nextFloat()) * 0.2F);
            setHasMilk(true);
            setHasFood(false);
            return true;
        }
        if(itemstack != null && itemstack.itemId == mod_mocreatures.petfood.id)
        {
            if(--itemstack.count == 0)
            {
                entityplayer.inventory.setStack(entityplayer.inventory.selectedSlot, null);
            }
            world.playSound(this, "mocreatures:pouringfood", 1.0F, 1.0F + (random.nextFloat() - random.nextFloat()) * 0.2F);
            sendSound(world, "mocreatures:pouringfood", 1.0F, 1.0F + (random.nextFloat() - random.nextFloat()) * 0.2F);
            setHasMilk(false);
            setHasFood(true);
            return true;
        }
        if(itemstack != null && itemstack.getItem() instanceof PickaxeItem)
        {
            entityplayer.inventory.addStack(new ItemStack(mod_mocreatures.kittybed, 1, getSheetColour()));
            world.playSound(this, "random.pop", 0.2F, ((random.nextFloat() - random.nextFloat()) * 0.7F + 1.0F) * 2.0F);
            sendSound(world, "random.pop", 0.2F, ((random.nextFloat() - random.nextFloat()) * 0.7F + 1.0F) * 2.0F);
            markDead();
            return true;
        } else
        {
            yaw = entityplayer.yaw;
            setVehicle(entityplayer);
            if (net.fabricmc.loader.FabricLoader.INSTANCE.getEnvironmentType() == EnvType.SERVER) {
                PacketHelper.sendTo(entityplayer, new RopePacket("bed", this.id, entityplayer.name));
            }
            world.playSound(this, "mob.chickenplop", 1.0F, (random.nextFloat() - random.nextFloat()) * 0.2F + 1.0F);
            sendSound(world, "mob.chickenplop", 1.0F, (random.nextFloat() - random.nextFloat()) * 0.2F + 1.0F);
            return true;
        }
    }

    public double getStandingEyeHeight()
    {
        if(vehicle instanceof PlayerEntity)
        {
            setPicked(true);
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


    public void sendSound(World world, String name, float vol, float pit){
        if (net.fabricmc.loader.FabricLoader.INSTANCE.getEnvironmentType() == EnvType.SERVER){
            mod_mocreatures.voicePacket(world, name, this.id, vol, pit);
        }
    }

    @Override
    public Identifier getHandlerIdentifier() {
        return Identifier.of(mod_mocreatures.MOD_ID, "KittyBed");
    }

    //COLOR
    public void setSheetColour(int type)
    {
        dataTracker.set(16, (byte) type);
    }

    public int getSheetColour()
    {
        return dataTracker.getByte(16);
    }

    //MIlk Level
    public void setMilkLevel(float age)
    {
        dataTracker.set(17, Float.floatToRawIntBits(age));
    }

    public float getMilkLevel()
    {
        return Float.intBitsToFloat(dataTracker.getInt(17));
    }

    //Has Food
    public boolean getHasFood()
    {
        return (dataTracker.getByte(18) & 1) != 0;
    }

    public void setHasFood(boolean flag)
    {
        if(flag)
        {
            dataTracker.set(18, Byte.valueOf((byte)1));
        } else
        {
            dataTracker.set(18, Byte.valueOf((byte)0));
        }
    }

    //Has Milk
    public boolean getHasMilk()
    {
        return (dataTracker.getByte(19) & 1) != 0;
    }

    public void setHasMilk(boolean flag)
    {
        if(flag)
        {
            dataTracker.set(19, Byte.valueOf((byte)1));
        } else
        {
            dataTracker.set(19, Byte.valueOf((byte)0));
        }
    }

    //Picked
    public boolean getPicked()
    {
        return (dataTracker.getByte(20) & 1) != 0;
    }

    public void setPicked(boolean flag)
    {
        if(flag)
        {
            dataTracker.set(20, (byte) 1);
        } else
        {
            dataTracker.set(20, (byte) 0);
        }
    }
}
