// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) braces deadcode 

package net.kozibrodka.mocreatures.item;


import net.kozibrodka.mocreatures.events.mod_mocreatures;
import net.minecraft.entity.player.PlayerBase;
import net.minecraft.item.ItemBase;
import net.minecraft.item.ItemInstance;
import net.minecraft.level.Level;
import net.modificationstation.stationapi.api.registry.Identifier;
import net.modificationstation.stationapi.api.template.item.TemplateItemBase;

public class SugarLump extends TemplateItemBase
{

    public SugarLump(Identifier i)
    {
        super(i);
        maxStackSize = 32;
        a = 1;
    }

    public ItemInstance use(ItemInstance itemstack, Level world, PlayerBase entityplayer)
    {
        if(!mod_mocreatures.mocreaturesGlass.balancesettings.sugar_lump) {
            itemstack.count--;
            entityplayer.addHealth(a);
        }
        return itemstack;
    }

    private int a;
}
