// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) braces deadcode 

package net.kozibrodka.mocreatures.entity;

import net.kozibrodka.mocreatures.events.mod_mocreatures;
import net.kozibrodka.mocreatures.item.ItemKittyBed;
import net.minecraft.entity.Entity;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NbtCompound;
import net.minecraft.util.math.Vec3d;
import net.minecraft.world.World;
//import net.modificationstation.stationapi.api.packet.Message;
import net.modificationstation.stationapi.api.util.Identifier;
import net.modificationstation.stationapi.api.server.entity.HasTrackingParameters;
import net.modificationstation.stationapi.api.server.entity.MobSpawnDataProvider;

@HasTrackingParameters(trackingDistance = 160, updatePeriod = 2)
public class EntityKittyBed extends LivingEntity implements MobSpawnDataProvider
{

    public EntityKittyBed(World world)
    {
        super(world);
        hasMilk = false;
        setBoundingBoxSpacing(1.0F, 0.3F);
        milklevel = 0.0F;
        hasFood = false;
    }

    public EntityKittyBed(World world, int i)
    {
        this(world);
        sheetcolour = i;
    }

    public void move(double d, double d1, double d2)
    {
        if(vehicle != null || !onGround || !mocr.mocreaturesGlass.othersettings.staticbed)
        {
            super.move(d, d1, d2);
        }
    }

    public void tick()
    {
        super.tick();
        if(onGround)
        {
            pickedUp = false;
        }
        if((hasMilk || hasFood) && passenger != null)
        {
            milklevel += 0.003F;
            if(milklevel > 2.0F)
            {
                milklevel = 0.0F;
                hasMilk = false;
                hasFood = false;
            }
        }
    }

    public void writeNbt(NbtCompound nbttagcompound)
    {
        nbttagcompound.putBoolean("HasMilk", hasMilk);
        nbttagcompound.putInt("SheetColour", sheetcolour);
        nbttagcompound.putBoolean("HasFood", hasFood);
    }

    public void readNbt(NbtCompound nbttagcompound)
    {
        hasMilk = nbttagcompound.getBoolean("HasMilk");
        sheetcolour = nbttagcompound.getInt("SheetColour");
        hasFood = nbttagcompound.getBoolean("HasFood");
    }

    public boolean canSee(Entity entity)
    {
        return world.raycast(Vec3d.createCached(x, y + (double)getShadowRadius(), z), Vec3d.createCached(entity.x, entity.y + (double)entity.getShadowRadius(), entity.z)) == null;
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
        if(itemstack != null && itemstack.itemId == Item.MILK_BUCKET.id)
        {
            world.playSound(this, "mocreatures:pouringmilk", 1.0F, 1.0F + (random.nextFloat() - random.nextFloat()) * 0.2F);
            hasMilk = true;
            hasFood = false;
            return true;
        }
        if(itemstack != null && itemstack.itemId == mod_mocreatures.petfood.id)
        {
            if(--itemstack.count == 0)
            {
                entityplayer.inventory.setStack(entityplayer.inventory.selectedSlot, null);
            }
            world.playSound(this, "mocreatures:pouringfood", 1.0F, 1.0F + (random.nextFloat() - random.nextFloat()) * 0.2F);
            hasMilk = false;
            hasFood = true;
            return true;
        }
        if(itemstack != null && (itemstack.itemId == Item.STONE_PICKAXE.id || itemstack.itemId == Item.WOODEN_PICKAXE.id || itemstack.itemId == Item.IRON_PICKAXE.id || itemstack.itemId == Item.GOLDEN_PICKAXE.id || itemstack.itemId == Item.DIAMOND_PICKAXE.id))
        {
            entityplayer.inventory.addStack(new ItemStack(mod_mocreatures.kittybed, 1, sheetcolour));
            world.playSound(this, "random.pop", 0.2F, ((random.nextFloat() - random.nextFloat()) * 0.7F + 1.0F) * 2.0F);
            markDead();
            return true;
        } else
        {
            yaw = entityplayer.yaw;
            setVehicle(entityplayer);
            world.playSound(this, "mob.chickenplop", 1.0F, (random.nextFloat() - random.nextFloat()) * 0.2F + 1.0F);
            return true;
        }
    }

    public double getStandingEyeHeight()
    {
        if(vehicle instanceof PlayerEntity)
        {
            pickedUp = true;
            return (double)(standingEyeHeight - 1.15F);
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

    mod_mocreatures mocr = new mod_mocreatures();
    public boolean hasMilk;
    public boolean hasFood;
    public boolean pickedUp;
    public float milklevel;
    public int sheetcolour;

    @Override
    public Identifier getHandlerIdentifier() {
        return Identifier.of(mod_mocreatures.MOD_ID, "KittyBed");
    }

//    @Override
//    public void writeToMessage(Message message)
//    {
//        System.out.println("SERVER " + this.x + "  " + this.z);
//    }
//
//    @Override
//    public void readFromMessage(Message message)
//    {
//        System.out.println("CLIENT " + this.x + "  " + this.z);
//    }
}
