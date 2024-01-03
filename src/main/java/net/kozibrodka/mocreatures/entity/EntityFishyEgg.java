// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) braces deadcode 

package net.kozibrodka.mocreatures.entity;

import net.kozibrodka.mocreatures.events.mod_mocreatures;
import net.minecraft.block.Material;
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

    public void method_937()
    {
        field_1060 = 0.0F;
        field_1029 = 0.0F;
        field_1030 = 0.0F;
        method_945(field_1060, field_1029);
    }

    public void onPlayerInteraction(PlayerEntity entityplayer)
    {
        if(world.isRemote)
        {
            return;
        }
        if(lCounter > 10 && entityplayer.inventory.method_671(new ItemStack(mod_mocreatures.fishyegg, 1)))
        {
            world.playSound(this, "random.pop", 0.2F, ((random.nextFloat() - random.nextFloat()) * 0.7F + 1.0F) * 2.0F);
            entityplayer.method_491(this, 1);
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
        if(field_1612 && random.nextInt(20) == 0)
        {
            tCounter++;
            if(tCounter >= 50)
            {
                EntityFishy entityfishy = new EntityFishy(world);
                entityfishy.method_1340(x, y, z);
                entityfishy.chooseType();
                entityfishy.b = 0.3F;
                world.method_210(entityfishy);
                world.playSound(this, "mob.chickenplop", 1.0F, (random.nextFloat() - random.nextFloat()) * 0.2F + 1.0F);
                entityfishy.tamed = true;
                markDead();
            }
        }
    }

    public boolean canBreatheInWater()
    {
        return true;
    }

    public boolean isSubmergedInWater()
    {
        return world.method_170(boundingBox, Material.WATER, this);
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

    protected float method_915()
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
