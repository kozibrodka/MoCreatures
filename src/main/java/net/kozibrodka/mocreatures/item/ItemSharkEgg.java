// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) braces deadcode 

package net.kozibrodka.mocreatures.item;

import net.kozibrodka.mocreatures.entity.EntitySharkEgg;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.world.World;
import net.modificationstation.stationapi.api.util.Identifier;
import net.modificationstation.stationapi.api.util.Identifier;
import net.modificationstation.stationapi.api.template.item.TemplateItem;

public class ItemSharkEgg extends TemplateItem
{

    public ItemSharkEgg(Identifier i)
    {
        super(i);
        maxCount = 16;
    }

    public ItemStack use(ItemStack itemstack, World world, PlayerEntity entityplayer)
    {
        itemstack.count--;
        if(!world.isRemote)
        {
            EntitySharkEgg entitysharkegg = new EntitySharkEgg(world, entityplayer.name);
            entitysharkegg.setPosition(entityplayer.x, entityplayer.y, entityplayer.z);
            world.spawnEntity(entitysharkegg);
            entitysharkegg.velocityY += world.random.nextFloat() * 0.05F;
            entitysharkegg.velocityX += (world.random.nextFloat() - world.random.nextFloat()) * 0.3F;
            entitysharkegg.velocityZ += (world.random.nextFloat() - world.random.nextFloat()) * 0.3F;
        }
        return itemstack;
    }
}
