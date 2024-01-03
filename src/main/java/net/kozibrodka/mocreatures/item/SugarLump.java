// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) braces deadcode 

package net.kozibrodka.mocreatures.item;


import net.kozibrodka.mocreatures.events.mod_mocreatures;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.world.World;
import net.modificationstation.stationapi.api.util.Identifier;
import net.modificationstation.stationapi.api.template.item.TemplateItem;

public class SugarLump extends TemplateItem
{

    public SugarLump(Identifier i)
    {
        super(i);
        maxCount = 32;
        a = 1;
    }

    public ItemStack use(ItemStack itemstack, World world, PlayerEntity entityplayer)
    {
        if(mod_mocreatures.mocreaturesGlass.balancesettings.sugar_lump) {
            itemstack.count--;
            entityplayer.method_939(a);
        }
        return itemstack;
    }

    private int a;
}
