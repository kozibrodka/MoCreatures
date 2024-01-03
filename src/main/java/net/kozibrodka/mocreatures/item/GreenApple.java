package net.kozibrodka.mocreatures.item;


import net.minecraft.entity.player.PlayerBase;
import net.minecraft.item.ItemInstance;
import net.minecraft.level.Level;
import net.modificationstation.stationapi.api.util.Identifier;
import net.modificationstation.stationapi.api.template.item.TemplateItem;

public class GreenApple extends TemplateItem
{

    public GreenApple(Identifier i)
    {
        super(i);
        maxStackSize = 1;
        a = 42;
    }

    public ItemInstance use(ItemInstance itemstack, Level world, PlayerBase entityplayer)
    {
        itemstack.count--;
        entityplayer.addHealth(a);
        return itemstack;
    }

    private int a;
}
