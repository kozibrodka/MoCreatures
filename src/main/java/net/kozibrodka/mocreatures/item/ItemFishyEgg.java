// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) braces deadcode 

package net.kozibrodka.mocreatures.item;

import net.kozibrodka.mocreatures.entity.EntityFishyEgg;
import net.minecraft.entity.player.PlayerBase;
import net.minecraft.item.ItemInstance;
import net.minecraft.level.Level;
import net.modificationstation.stationapi.api.util.Identifier;
import net.modificationstation.stationapi.api.template.item.TemplateItem;

public class ItemFishyEgg extends TemplateItem
{

    public ItemFishyEgg(Identifier i)
    {
        super(i);
        maxStackSize = 16;
    }

    public ItemInstance use(ItemInstance itemstack, Level world, PlayerBase entityplayer)
    {
        itemstack.count--;
        if(!world.isServerSide)
        {
            EntityFishyEgg entityfishyegg = new EntityFishyEgg(world);
            entityfishyegg.setPosition(entityplayer.x, entityplayer.y, entityplayer.z);
            world.spawnEntity(entityfishyegg);
            entityfishyegg.velocityY += world.rand.nextFloat() * 0.05F;
            entityfishyegg.velocityX += (world.rand.nextFloat() - world.rand.nextFloat()) * 0.3F;
            entityfishyegg.velocityZ += (world.rand.nextFloat() - world.rand.nextFloat()) * 0.3F;
        }
        return itemstack;
    }
}
