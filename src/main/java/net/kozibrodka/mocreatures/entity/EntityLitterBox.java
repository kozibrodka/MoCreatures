// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) braces deadcode 

package net.kozibrodka.mocreatures.entity;

import net.kozibrodka.mocreatures.events.mod_mocreatures;
import net.kozibrodka.mocreatures.mixin.CreeperAccesor;
import net.kozibrodka.mocreatures.mixin.WalkingBaseAccesor;
import net.minecraft.block.Block;
import net.minecraft.entity.Entity;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.mob.CreeperEntity;
import net.minecraft.entity.mob.MonsterEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NbtCompound;
import net.minecraft.world.World;
import net.modificationstation.stationapi.api.util.Identifier;
import net.modificationstation.stationapi.api.server.entity.HasTrackingParameters;
import net.modificationstation.stationapi.api.server.entity.MobSpawnDataProvider;

import java.util.List;

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
        if(field_1623)
        {
            pickedUp = false;
        }
        if(usedlitter)
        {
            littertime++;
            world.addParticle("smoke", x, y, z, 0.0D, 0.0D, 0.0D);
            List list = world.getEntities(this, boundingBox.expand(12D, 4D, 12D));
            for(int i = 0; i < list.size(); i++)
            {
                Entity entity = (Entity)list.get(i);
                if(!(entity instanceof MonsterEntity))
                {
                    continue;
                }
                MonsterEntity entitymob = (MonsterEntity)entity;
//                entitymob.entity = this;
                ((WalkingBaseAccesor)entitymob).setTarget(this);
                if(entitymob instanceof CreeperEntity)
                {
//                    ((Creeper)entitymob).currentFuseTime = 5;
                    ((CreeperAccesor)(CreeperEntity)entitymob).setField_350(5);
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
        if(field_1595 != null || !field_1623 || !mocr.mocreaturesGlass.othersettings.staticlitter)
        {
            super.move(d, d1, d2);
        }
    }

    public void writeNbt(NbtCompound nbttagcompound)
    {
        nbttagcompound.putBoolean("UsedLitter", usedlitter);
    }

    public void readNbt(NbtCompound nbttagcompound)
    {
        usedlitter = nbttagcompound.getBoolean("UsedLitter");
    }

    public boolean method_1356()
    {
        return !dead;
    }

    public boolean method_1380()
    {
        return !dead;
    }

    protected boolean method_940()
    {
        return false;
    }

    public void method_1313(byte byte0)
    {
    }

    protected void method_910()
    {
    }

    protected void method_1389(float f)
    {
    }

    public boolean damage(Entity entity, int i)
    {
        return false;
    }

    public String method_1314()
    {
        return "/assets/mocreatures/stationapi/textures/mob/litterbox.png";
    }

    public boolean method_1323(PlayerEntity entityplayer)
    {
        ItemStack itemstack = entityplayer.inventory.getSelectedItem();
        if(itemstack != null && (itemstack.itemId == Item.STONE_PICKAXE.id || itemstack.itemId == Item.WOODEN_PICKAXE.id || itemstack.itemId == Item.IRON_PICKAXE.id || itemstack.itemId == Item.GOLDEN_PICKAXE.id || itemstack.itemId == Item.DIAMOND_PICKAXE.id))
        {
            entityplayer.inventory.method_671(new ItemStack(mod_mocreatures.litterbox));
            world.playSound(this, "random.pop", 0.2F, ((random.nextFloat() - random.nextFloat()) * 0.7F + 1.0F) * 2.0F);
            markDead();
            return true;
        }
        if(itemstack != null && itemstack.itemId == Block.SAND.id)
        {
            if(--itemstack.count == 0)
            {
                entityplayer.inventory.setStack(entityplayer.inventory.selectedSlot, null);
            }
            usedlitter = false;
            littertime = 0;
            return true;
        } else
        {
            yaw = entityplayer.yaw;
            method_1376(entityplayer);
            world.playSound(this, "mob.chickenplop", 1.0F, (random.nextFloat() - random.nextFloat()) * 0.2F + 1.0F);
            return true;
        }
    }

    public double method_1385()
    {
        if(field_1595 instanceof PlayerEntity)
        {
            pickedUp = true;
            return (double)(eyeHeight - 1.15F);
        } else
        {
            return (double)eyeHeight;
        }
    }

    protected float method_915()
    {
        return 0.0F;
    }

    protected String method_911()
    {
        return null;
    }

    protected String method_912()
    {
        return null;
    }

    protected String method_913()
    {
        return null;
    }

    public boolean canBreatheInWater()
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
