// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) braces deadcode 

package net.kozibrodka.mocreatures.mocreatures;

import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.inventory.Inventory;
import net.minecraft.item.ItemStack;

public class AnimalChest
    implements Inventory
{

    public AnimalChest(ItemStack aitemstack[], String s, int sizeUj)
    {
        cheststack = aitemstack;
        localstring = s;
        setSize = sizeUj;
    }

    public int size()
    {
        return setSize;
    }

    public ItemStack getStack(int i)
    {
        return cheststack[i];
    }

    public ItemStack removeStack(int i, int j)
    {
        if(cheststack[i] != null)
        {
            if(cheststack[i].count <= j)
            {
                ItemStack itemstack = cheststack[i];
                cheststack[i] = null;
                markDirty();
                return itemstack;
            }
            ItemStack itemstack1 = cheststack[i].split(j);
            if(cheststack[i].count == 0)
            {
                cheststack[i] = null;
            }
            markDirty();
            return itemstack1;
        } else
        {
            return null;
        }
    }

    public void setStack(int i, ItemStack itemstack)
    {
        cheststack[i] = itemstack;
        if(itemstack != null && itemstack.count > getMaxCountPerStack())
        {
            itemstack.count = getMaxCountPerStack();
        }
        markDirty();
    }

    public String getName()
    {
        return localstring;
    }


    public int getMaxCountPerStack()
    {
        return 64;
    }

    public void markDirty()
    {
    }

    public boolean canPlayerUse(PlayerEntity entityplayer)
    {
        return true;
    }


    public int setSize;
    private String localstring;
    public ItemStack cheststack[];
}
