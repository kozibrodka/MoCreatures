package net.kozibrodka.mocreatures.item;


import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.world.World;
import net.modificationstation.stationapi.api.template.item.TemplateItem;
import net.modificationstation.stationapi.api.util.Identifier;

public class FruitBaobab extends TemplateItem
{

    public FruitBaobab(Identifier i)
    {
        super(i);
        maxCount = 1;
        a = 4;
    }

    public ItemStack use(ItemStack itemstack, World world, PlayerEntity entityplayer)
    {
        itemstack.count--;
        entityplayer.heal(a);
        return itemstack;
    }

    private int a;
}
