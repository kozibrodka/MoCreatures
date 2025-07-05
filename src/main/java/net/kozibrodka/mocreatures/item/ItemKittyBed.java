// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) braces deadcode 

package net.kozibrodka.mocreatures.item;

import net.kozibrodka.mocreatures.entity.EntityKittyBed;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.world.World;
import net.modificationstation.stationapi.api.util.Identifier;
import net.modificationstation.stationapi.api.template.item.TemplateItem;

public class ItemKittyBed extends TemplateItem
{

    public ItemKittyBed(Identifier i)
    {
        super(i);
        maxCount = 8;
        setHasSubtypes(true);
    }

    public ItemKittyBed(Identifier i, int j)
    {
        this(i);
        itemcolor = j;
    }

    public ItemStack use(ItemStack itemstack, World world, PlayerEntity entityplayer)
    {
        itemstack.count--;
        if(world.isRemote){
            return itemstack;
        }

            EntityKittyBed entitykittybed = new EntityKittyBed(world, itemstack.getDamage());
//            EntityKittyBed entitykittybed = new EntityKittyBed(world, itemcolor);
            entitykittybed.setPosition(entityplayer.x, entityplayer.y, entityplayer.z);
            world.spawnEntity(entitykittybed);
            entitykittybed.velocityY += world.random.nextFloat() * 0.05F;
            entitykittybed.velocityX += (world.random.nextFloat() - world.random.nextFloat()) * 0.3F;
            entitykittybed.velocityZ += (world.random.nextFloat() - world.random.nextFloat()) * 0.3F;

        return itemstack;
    }

    public String getTranslationKey(ItemStack itemstack)
    {
        return (new StringBuilder()).append(super.getTranslationKey()).append(".").append(itemstack.getDamage()).toString();
    }

    public int itemcolor;
}
