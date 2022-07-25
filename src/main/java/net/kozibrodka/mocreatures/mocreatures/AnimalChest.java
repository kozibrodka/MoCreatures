// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) braces deadcode 

package net.kozibrodka.mocreatures.mocreatures;

import net.minecraft.entity.player.PlayerBase;
import net.minecraft.inventory.InventoryBase;
import net.minecraft.item.ItemInstance;

public class AnimalChest
    implements InventoryBase
{

    public AnimalChest(ItemInstance aitemstack[], String s, int sizeUj)
    {
        cheststack = aitemstack;
        localstring = s;
        setSize = sizeUj;
    }

    public int getInventorySize()
    {
        return setSize;
    }

    public ItemInstance getInventoryItem(int i)
    {
        return cheststack[i];
    }

    public ItemInstance takeInventoryItem(int i, int j)
    {
        if(cheststack[i] != null)
        {
            if(cheststack[i].count <= j)
            {
                ItemInstance itemstack = cheststack[i];
                cheststack[i] = null;
                markDirty();
                return itemstack;
            }
            ItemInstance itemstack1 = cheststack[i].split(j);
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

    public void setInventoryItem(int i, ItemInstance itemstack)
    {
        cheststack[i] = itemstack;
        if(itemstack != null && itemstack.count > getMaxItemCount())
        {
            itemstack.count = getMaxItemCount();
        }
        markDirty();
    }

    public String getContainerName()
    {
        return localstring;
    }


    public int getMaxItemCount()
    {
        return 64;
    }

    public void markDirty()
    {
    }

    public boolean canPlayerUse(PlayerBase entityplayer)
    {
        return true;
    }


    public int setSize;
    private String localstring;
    public ItemInstance cheststack[];
}
