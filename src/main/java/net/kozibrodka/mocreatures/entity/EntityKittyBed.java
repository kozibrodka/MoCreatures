
package net.kozibrodka.mocreatures.entity;

import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
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

    @Override
    public void move(double d, double d1, double d2)
    {
        if(vehicle != null || !onGround || !mod_mocreatures.mocGlass.othersettings.staticbed)
        {
            super.move(d, d1, d2);
        }
    }

    @Override
    public void onCollision(Entity otherEntity) {
        if(vehicle instanceof PlayerEntity && otherEntity == vehicle.vehicle){
        }else {
            super.onCollision(otherEntity);
        }
    }

    @Override
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

    @Override
    protected void initDataTracker()
    {
        super.initDataTracker();
        dataTracker.startTracking(16, (byte) 0); //SheetColour
        dataTracker.startTracking(17, 0); //MilkLevel
        dataTracker.startTracking(18, (byte) 0); //HasFood
        dataTracker.startTracking(19, (byte) 0); //HasMilk
        dataTracker.startTracking(20, (byte) 0); //Picked
    }

    @Override
    public void writeNbt(NbtCompound nbttagcompound)
    {
        nbttagcompound.putBoolean("HasMilk", getHasMilk());
        nbttagcompound.putInt("SheetColour", getSheetColour());
        nbttagcompound.putBoolean("HasFood", getHasFood());
        nbttagcompound.putFloat("MilkLevel", getMilkLevel());
    }

    @Override
    public void readNbt(NbtCompound nbttagcompound)
    {
        setHasMilk(nbttagcompound.getBoolean("HasMilk"));
        setSheetColour(nbttagcompound.getInt("SheetColour"));
        setHasFood(nbttagcompound.getBoolean("HasFood"));
        setMilkLevel(nbttagcompound.getFloat("MilkLevel"));
    }

    @Override
    public boolean canSee(Entity entity)
    {
//        return world.raycast(Vec3d.createCached(x, y + (double)getShadowRadius(), z), Vec3d.createCached(entity.x, entity.y + (double)entity.getShadowRadius(), entity.z)) == null;
        return world.raycast(Vec3d.createCached(x, y + (double)(this.height / 2.0F), z), Vec3d.createCached(entity.x, entity.y + (double)(entity.height / 2.0F), entity.z)) == null;
    }

    @Override
    public boolean isCollidable()
    {
        return !dead;
    }

    @Override
    public boolean isPushable()
    {
        return !dead;
    }

    @Override
    protected boolean canDespawn()
    {
        return false;
    }

    @Override
    protected void tickLiving()
    {
    }

    @Override
    protected void onLanding(float f)
    {
    }

    @Override
    public boolean damage(Entity entity, int i)
    {
        return false;
    }

    @Override
    public String getTexture()
    {
        return "/assets/mocreatures/stationapi/textures/mob/kittybed.png";
    }

    @Override
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
            world.broadcastEntityEvent(this, (byte)8);
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
            world.broadcastEntityEvent(this, (byte)9);
            setHasMilk(false);
            setHasFood(true);
            return true;
        }
        if(itemstack != null && itemstack.getItem() instanceof PickaxeItem)
        {
            entityplayer.inventory.addStack(new ItemStack(mod_mocreatures.kittybed, 1, getSheetColour()));
            world.playSound(this, "random.pop", 0.2F, ((random.nextFloat() - random.nextFloat()) * 0.7F + 1.0F) * 2.0F);
            world.broadcastEntityEvent(this, (byte)6);
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
            world.broadcastEntityEvent(this, (byte)7);
            return true;
        }
    }

    @Override
    public double getStandingEyeHeight()
    {
        if(vehicle instanceof PlayerEntity)
        {
            setPicked(true);
            if (FabricLoader.INSTANCE.getEnvironmentType() == EnvType.SERVER){
                return standingEyeHeight + 0.49F; //+0
            }else{
                return standingEyeHeight - 1.15F;
            }
        } else
        {
            return standingEyeHeight;
        }
    }

    @Override
    protected float getSoundVolume()
    {
        return 0.0F;
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
    public boolean canBreatheInWater()
    {
        return true;
    }

    @Override
    @Environment(EnvType.CLIENT)
    public void processServerEntityStatus(byte status) {
        if (status == 6) {
            world.playSound(this, "random.pop", 0.2F, ((random.nextFloat() - random.nextFloat()) * 0.7F + 1.0F) * 2.0F);
        }  else if (status == 7){
            world.playSound(this, "mob:chickenplop", 1.0F, 1.0F + (random.nextFloat() - random.nextFloat()) * 0.2F);
        }  else if (status == 8){
            world.playSound(this, "mocreatures:pouringmilk", 1.0F, 1.0F + (random.nextFloat() - random.nextFloat()) * 0.2F);
        }  else if (status == 9){
            world.playSound(this, "mocreatures:pouringfood", 1.0F, 1.0F + (random.nextFloat() - random.nextFloat()) * 0.2F);
        }  else {
            super.processServerEntityStatus(status);
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
