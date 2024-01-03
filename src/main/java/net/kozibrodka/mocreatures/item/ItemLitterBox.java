// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) braces deadcode 

package net.kozibrodka.mocreatures.item;

import net.kozibrodka.mocreatures.entity.EntityLitterBox;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.world.World;
import net.modificationstation.stationapi.api.util.Identifier;
import net.modificationstation.stationapi.api.template.item.TemplateItem;


public class ItemLitterBox extends TemplateItem
{

    public ItemLitterBox(Identifier i)
    {
        super(i);
        maxCount = 16;
    }

    public ItemStack use(ItemStack itemstack, World world, PlayerEntity entityplayer)
    {
        if(world.isRemote){
            return itemstack;
        }
        itemstack.count--;
        EntityLitterBox entitylitterbox = new EntityLitterBox(world);
        entitylitterbox.method_1340(entityplayer.x, entityplayer.y, entityplayer.z);
        world.method_210(entitylitterbox);
        entitylitterbox.velocityY += world.field_214.nextFloat() * 0.05F;
        entitylitterbox.velocityX += (world.field_214.nextFloat() - world.field_214.nextFloat()) * 0.3F;
        entitylitterbox.velocityZ += (world.field_214.nextFloat() - world.field_214.nextFloat()) * 0.3F;
        return itemstack;
    }
}
