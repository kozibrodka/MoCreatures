// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) braces deadcode 

package net.kozibrodka.mocreatures.entity;

import net.kozibrodka.mocreatures.events.mod_mocreatures;
import net.kozibrodka.mocreatures.mixin.CreeperAccesor;
import net.kozibrodka.mocreatures.mixin.WalkingBaseAccesor;
import net.minecraft.block.BlockBase;
import net.minecraft.entity.EntityBase;
import net.minecraft.entity.Living;
import net.minecraft.entity.monster.Creeper;
import net.minecraft.entity.monster.MonsterBase;
import net.minecraft.entity.player.PlayerBase;
import net.minecraft.item.ItemBase;
import net.minecraft.item.ItemInstance;
import net.minecraft.level.Level;
import net.minecraft.util.io.CompoundTag;
import net.modificationstation.stationapi.api.util.Identifier;
import net.modificationstation.stationapi.api.server.entity.HasTrackingParameters;
import net.modificationstation.stationapi.api.server.entity.MobSpawnDataProvider;

import java.util.List;

@HasTrackingParameters(trackingDistance = 160, updatePeriod = 2)
public class EntityLitterBox extends Living implements MobSpawnDataProvider
{

    public EntityLitterBox(Level world)
    {
        super(world);
        setSize(1.0F, 0.3F);
    }

    public void tick()
    {
        super.tick();
        if(onGround)
        {
            pickedUp = false;
        }
        if(usedlitter)
        {
            littertime++;
            level.addParticle("smoke", x, y, z, 0.0D, 0.0D, 0.0D);
            List list = level.getEntities(this, boundingBox.expand(12D, 4D, 12D));
            for(int i = 0; i < list.size(); i++)
            {
                EntityBase entity = (EntityBase)list.get(i);
                if(!(entity instanceof MonsterBase))
                {
                    continue;
                }
                MonsterBase entitymob = (MonsterBase)entity;
//                entitymob.entity = this;
                ((WalkingBaseAccesor)entitymob).setEntity(this);
                if(entitymob instanceof Creeper)
                {
//                    ((Creeper)entitymob).currentFuseTime = 5;
                    ((CreeperAccesor)(Creeper)entitymob).setCurrentFuseTime(5);
                }
                if(entitymob instanceof EntityOgre)
                {
                    ((EntityOgre)entitymob).ogrehasenemy = false;
                }
            }

        }
        if(littertime > 5000)
        {
            usedlitter = false;
        }
    }

    public void move(double d, double d1, double d2)
    {
        if(vehicle != null || !onGround || !mocr.mocreaturesGlass.othersettings.staticlitter)
        {
            super.move(d, d1, d2);
        }
    }

    public void writeCustomDataToTag(CompoundTag nbttagcompound)
    {
        nbttagcompound.put("UsedLitter", usedlitter);
    }

    public void readCustomDataFromTag(CompoundTag nbttagcompound)
    {
        usedlitter = nbttagcompound.getBoolean("UsedLitter");
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
        return "/assets/mocreatures/stationapi/textures/mob/litterbox.png";
    }

    public boolean interact(PlayerBase entityplayer)
    {
        ItemInstance itemstack = entityplayer.inventory.getHeldItem();
        if(itemstack != null && (itemstack.itemId == ItemBase.stonePickaxe.id || itemstack.itemId == ItemBase.woodPickaxe.id || itemstack.itemId == ItemBase.ironPickaxe.id || itemstack.itemId == ItemBase.goldPickaxe.id || itemstack.itemId == ItemBase.diamondPickaxe.id))
        {
            entityplayer.inventory.addStack(new ItemInstance(mod_mocreatures.litterbox));
            level.playSound(this, "random.pop", 0.2F, ((rand.nextFloat() - rand.nextFloat()) * 0.7F + 1.0F) * 2.0F);
            remove();
            return true;
        }
        if(itemstack != null && itemstack.itemId == BlockBase.SAND.id)
        {
            if(--itemstack.count == 0)
            {
                entityplayer.inventory.setInventoryItem(entityplayer.inventory.selectedHotbarSlot, null);
            }
            usedlitter = false;
            littertime = 0;
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
    public boolean usedlitter;
    public boolean pickedUp;
    public int littertime;

    @Override
    public Identifier getHandlerIdentifier() {
        return Identifier.of(mod_mocreatures.MOD_ID, "LitterBox");
    }
}
