// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) braces deadcode 

package net.kozibrodka.mocreatures.item;

import net.kozibrodka.mocreatures.entity.EntityFishyEgg;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.world.World;
import net.modificationstation.stationapi.api.util.Identifier;
import net.modificationstation.stationapi.api.template.item.TemplateItem;

public class ItemFishyEgg extends TemplateItem
{

    public ItemFishyEgg(Identifier i)
    {
        super(i);
        maxCount = 16;
    }

    public ItemStack use(ItemStack itemstack, World world, PlayerEntity entityplayer)
    {
        itemstack.count--;
        if(!world.isRemote)
        {
            EntityFishyEgg entityfishyegg = new EntityFishyEgg(world);
            entityfishyegg.setPosition(entityplayer.x, entityplayer.y, entityplayer.z);
            world.spawnEntity(entityfishyegg);
            entityfishyegg.velocityY += world.random.nextFloat() * 0.05F;
            entityfishyegg.velocityX += (world.random.nextFloat() - world.random.nextFloat()) * 0.3F;
            entityfishyegg.velocityZ += (world.random.nextFloat() - world.random.nextFloat()) * 0.3F;
        }
        return itemstack;
    }
}
