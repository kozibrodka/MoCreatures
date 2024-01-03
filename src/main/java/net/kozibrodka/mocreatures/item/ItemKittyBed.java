// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) braces deadcode 

package net.kozibrodka.mocreatures.item;

import net.kozibrodka.mocreatures.entity.EntityKittyBed;
import net.minecraft.entity.player.PlayerBase;
import net.minecraft.item.ItemInstance;
import net.minecraft.level.Level;
import net.modificationstation.stationapi.api.util.Identifier;
import net.modificationstation.stationapi.api.template.item.TemplateItem;

public class ItemKittyBed extends TemplateItem
{

    public ItemKittyBed(Identifier i)
    {
        super(i);
        maxStackSize = 8;
        setHasSubItems(true);
    }

    public ItemKittyBed(Identifier i, int j)
    {
        this(i);
        itemcolor = j;
    }

    public ItemInstance use(ItemInstance itemstack, Level world, PlayerBase entityplayer)
    {
        itemstack.count--;
        if(world.isServerSide){
            return itemstack;
        }

            EntityKittyBed entitykittybed = new EntityKittyBed(world, itemstack.getDamage());
//            EntityKittyBed entitykittybed = new EntityKittyBed(world, itemcolor);
            entitykittybed.setPosition(entityplayer.x, entityplayer.y, entityplayer.z);
            world.spawnEntity(entitykittybed);
            entitykittybed.velocityY += world.rand.nextFloat() * 0.05F;
            entitykittybed.velocityX += (world.rand.nextFloat() - world.rand.nextFloat()) * 0.3F;
            entitykittybed.velocityZ += (world.rand.nextFloat() - world.rand.nextFloat()) * 0.3F;

        return itemstack;
    }

    public String getTranslationKey(ItemInstance itemstack)
    {
        return (new StringBuilder()).append(super.getTranslationKey()).append(".").append(itemstack.getDamage()).toString();
    }

    public int itemcolor;
}
