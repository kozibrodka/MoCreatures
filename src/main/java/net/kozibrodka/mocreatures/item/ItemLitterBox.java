// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) braces deadcode 

package net.kozibrodka.mocreatures.item;

import net.kozibrodka.mocreatures.entity.EntityLitterBox;
import net.minecraft.entity.player.PlayerBase;
import net.minecraft.item.ItemInstance;
import net.minecraft.level.Level;
import net.modificationstation.stationapi.api.registry.Identifier;
import net.modificationstation.stationapi.api.template.item.TemplateItemBase;


public class ItemLitterBox extends TemplateItemBase
{

    public ItemLitterBox(Identifier i)
    {
        super(i);
        maxStackSize = 16;
    }

    public ItemInstance use(ItemInstance itemstack, Level world, PlayerBase entityplayer)
    {
        itemstack.count--;
        EntityLitterBox entitylitterbox = new EntityLitterBox(world);
        entitylitterbox.setPosition(entityplayer.x, entityplayer.y, entityplayer.z);
        world.spawnEntity(entitylitterbox);
        entitylitterbox.velocityY += world.rand.nextFloat() * 0.05F;
        entitylitterbox.velocityX += (world.rand.nextFloat() - world.rand.nextFloat()) * 0.3F;
        entitylitterbox.velocityZ += (world.rand.nextFloat() - world.rand.nextFloat()) * 0.3F;
        return itemstack;
    }
}
