// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) braces deadcode 

package net.kozibrodka.mocreatures.item;

import net.kozibrodka.mocreatures.entity.EntitySharkEgg;
import net.minecraft.entity.player.PlayerBase;
import net.minecraft.item.ItemInstance;
import net.minecraft.level.Level;
import net.modificationstation.stationapi.api.util.Identifier;
import net.modificationstation.stationapi.api.util.Identifier;
import net.modificationstation.stationapi.api.template.item.TemplateItem;

public class ItemSharkEgg extends TemplateItem
{

    public ItemSharkEgg(Identifier i)
    {
        super(i);
        maxStackSize = 16;
    }

    public ItemInstance use(ItemInstance itemstack, Level world, PlayerBase entityplayer)
    {
        itemstack.count--;
        if(!world.isServerSide)
        {
            EntitySharkEgg entitysharkegg = new EntitySharkEgg(world, entityplayer.name);
            entitysharkegg.setPosition(entityplayer.x, entityplayer.y, entityplayer.z);
            world.spawnEntity(entitysharkegg);
            entitysharkegg.velocityY += world.rand.nextFloat() * 0.05F;
            entitysharkegg.velocityX += (world.rand.nextFloat() - world.rand.nextFloat()) * 0.3F;
            entitysharkegg.velocityZ += (world.rand.nextFloat() - world.rand.nextFloat()) * 0.3F;
        }
        return itemstack;
    }
}
