// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) braces deadcode 

package net.kozibrodka.mocreatures.entity;

import net.kozibrodka.mocreatures.events.mod_mocreatures;
import net.kozibrodka.mocreatures.item.ItemKittyBed;
import net.minecraft.entity.EntityBase;
import net.minecraft.entity.Living;
import net.minecraft.entity.player.PlayerBase;
import net.minecraft.item.ItemBase;
import net.minecraft.item.ItemInstance;
import net.minecraft.level.Level;
import net.minecraft.util.io.CompoundTag;
import net.minecraft.util.maths.Vec3f;
import net.modificationstation.stationapi.api.packet.Message;
import net.modificationstation.stationapi.api.registry.Identifier;
import net.modificationstation.stationapi.api.server.entity.HasTrackingParameters;
import net.modificationstation.stationapi.api.server.entity.MobSpawnDataProvider;

@HasTrackingParameters(trackingDistance = 160, updatePeriod = 2)
public class EntityKittyBed extends Living implements MobSpawnDataProvider
{

    public EntityKittyBed(Level world)
    {
        super(world);
        hasMilk = false;
        setSize(1.0F, 0.3F);
        milklevel = 0.0F;
        hasFood = false;
    }

    public EntityKittyBed(Level world, int i)
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

    public void writeCustomDataToTag(CompoundTag nbttagcompound)
    {
        nbttagcompound.put("HasMilk", hasMilk);
        nbttagcompound.put("SheetColour", sheetcolour);
        nbttagcompound.put("HasFood", hasFood);
    }

    public void readCustomDataFromTag(CompoundTag nbttagcompound)
    {
        hasMilk = nbttagcompound.getBoolean("HasMilk");
        sheetcolour = nbttagcompound.getInt("SheetColour");
        hasFood = nbttagcompound.getBoolean("HasFood");
    }

    public boolean method_928(EntityBase entity)
    {
        return level.method_160(Vec3f.from(x, y + (double)getEyeHeight(), z), Vec3f.from(entity.x, entity.y + (double)entity.getEyeHeight(), entity.z)) == null;
    }

    public boolean method_1356()
    {
        return !removed;
    }

    public boolean method_1380()
    {
        return !removed;
    }

    protected boolean method_940()
    {
        return false;
    }

    public void handleStatus(byte byte0)
    {
    }

    protected void tickHandSwing()
    {
    }

    protected void handleFallDamage(float f)
    {
    }

    public boolean damage(EntityBase entity, int i)
    {
        return false;
    }

    public String getTextured()
    {
        return "/assets/mocreatures/stationapi/textures/mob/kittybed.png";
    }

    public boolean interact(PlayerBase entityplayer)
    {
        ItemInstance itemstack = entityplayer.inventory.getHeldItem();
        if(itemstack != null && itemstack.itemId == ItemBase.milk.id)
        {
            level.playSound(this, "mocreatures:pouringmilk", 1.0F, 1.0F + (rand.nextFloat() - rand.nextFloat()) * 0.2F);
            hasMilk = true;
            hasFood = false;
            return true;
        }
        if(itemstack != null && itemstack.itemId == mod_mocreatures.petfood.id)
        {
            if(--itemstack.count == 0)
            {
                entityplayer.inventory.setInventoryItem(entityplayer.inventory.selectedHotbarSlot, null);
            }
            level.playSound(this, "mocreatures:pouringfood", 1.0F, 1.0F + (rand.nextFloat() - rand.nextFloat()) * 0.2F);
            hasMilk = false;
            hasFood = true;
            return true;
        }
        if(itemstack != null && (itemstack.itemId == ItemBase.stonePickaxe.id || itemstack.itemId == ItemBase.woodPickaxe.id || itemstack.itemId == ItemBase.ironPickaxe.id || itemstack.itemId == ItemBase.goldPickaxe.id || itemstack.itemId == ItemBase.diamondPickaxe.id))
        {
            entityplayer.inventory.addStack(new ItemInstance(mod_mocreatures.kittybed, 1, sheetcolour));
            level.playSound(this, "random.pop", 0.2F, ((rand.nextFloat() - rand.nextFloat()) * 0.7F + 1.0F) * 2.0F);
            remove();
            return true;
        } else
        {
            yaw = entityplayer.yaw;
            startRiding(entityplayer);
            level.playSound(this, "mob.chickenplop", 1.0F, (rand.nextFloat() - rand.nextFloat()) * 0.2F + 1.0F);
            return true;
        }
    }

    public double getHeightOffset()
    {
        if(vehicle instanceof PlayerBase)
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

    protected String getAmbientSound()
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

    public boolean method_934()
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

    @Override
    public void writeToMessage(Message message)
    {
        System.out.println("SERVER " + this.x + "  " + this.z);
    }

    @Override
    public void readFromMessage(Message message)
    {
        System.out.println("CLIENT " + this.x + "  " + this.z);
    }
}
