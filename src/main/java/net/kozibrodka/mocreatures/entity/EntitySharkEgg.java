// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) braces deadcode 

package net.kozibrodka.mocreatures.entity;

import net.kozibrodka.mocreatures.events.mod_mocreatures;
import net.minecraft.block.material.Material;
import net.minecraft.entity.Living;
import net.minecraft.entity.player.PlayerBase;
import net.minecraft.item.ItemInstance;
import net.minecraft.level.Level;
import net.minecraft.util.io.CompoundTag;
import net.modificationstation.stationapi.api.util.Identifier;
import net.modificationstation.stationapi.api.server.entity.MobSpawnDataProvider;


public class EntitySharkEgg extends Living implements MobSpawnDataProvider
{

    public EntitySharkEgg(Level world)
    {
        super(world);
        setSize(0.25F, 0.25F);
        tCounter = 0;
        lCounter = 0;
        texture = "/assets/mocreatures/stationapi/textures/mob/sharkeggt.png";
    }

    public EntitySharkEgg(Level world, String graczol)
    {
        super(world);
        setSize(0.25F, 0.25F);
        tCounter = 0;
        lCounter = 0;
        texture = "/assets/mocreatures/stationapi/textures/mob/sharkeggt.png";
        ktoKto = graczol;
    }

    public void writeCustomDataToTag(CompoundTag nbttagcompound)
    {
        nbttagcompound.put("Opiekun", ktoKto);
    }

    public void readCustomDataFromTag(CompoundTag nbttagcompound)
    {
        ktoKto = nbttagcompound.getString("Opiekun");
    }

    protected void initDataTracker()
    {
    }

    public void updateDespawnCounter()
    {
        field_1060 = 0.0F;
        field_1029 = 0.0F;
        field_1030 = 0.0F;
        travel(field_1060, field_1029);
    }

    public void onPlayerCollision(PlayerBase entityplayer)
    {
        if(level.isServerSide)
        {
            return;
        }
        if(lCounter > 10 && entityplayer.inventory.addStack(new ItemInstance(mod_mocreatures.sharkegg, 1)))
        {
            level.playSound(this, "random.pop", 0.2F, ((rand.nextFloat() - rand.nextFloat()) * 0.7F + 1.0F) * 2.0F);
            entityplayer.onItemPickup(this, 1);
            remove();
        }
    }

    public void tick()
    {
        super.tick();
        if(rand.nextInt(20) == 0)
        {
            lCounter++;
        }
        if(field_1612 && rand.nextInt(20) == 0)
        {
            tCounter++;
            if(tCounter >= 50)
            {
                EntityShark entityshark = new EntityShark(level);
                entityshark.b = 0.3F;
                entityshark.tamed = true;
                entityshark.sharkOwner = ktoKto;
                entityshark.setPosition(x, y, z);
                level.spawnEntity(entityshark);
                level.playSound(this, "mob.chickenplop", 1.0F, (rand.nextFloat() - rand.nextFloat()) * 0.2F + 1.0F);
                remove();
            }
        }
    }

    public boolean method_934()
    {
        return true;
    }

    public boolean method_1393()
    {
        return level.method_170(boundingBox, Material.WATER, this);
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
}
