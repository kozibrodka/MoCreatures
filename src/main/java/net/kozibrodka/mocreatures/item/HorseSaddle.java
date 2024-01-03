// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) braces deadcode 

package net.kozibrodka.mocreatures.item;

import net.kozibrodka.mocreatures.entity.EntityHorse;
import net.minecraft.entity.Living;
import net.minecraft.item.ItemInstance;
import net.modificationstation.stationapi.api.template.item.TemplateItem;
import net.modificationstation.stationapi.api.util.Identifier;

public class HorseSaddle extends TemplateItem
{

    public HorseSaddle(Identifier i)
    {
        super(i);
        maxStackSize = 32;
    }

    public void interactWithEntity(ItemInstance itemstack, Living entityliving)
    {
        if(entityliving instanceof EntityHorse)
        {
            EntityHorse entityhorse = (EntityHorse)entityliving;
            if(!entityhorse.rideable && entityhorse.adult)
            {
                entityhorse.rideable = true;
                itemstack.count--;
            }
        }
    }

    public void postHit(ItemInstance itemstack, Living entityliving)
    {
        interactWithEntity(itemstack, entityliving);
    }
}
