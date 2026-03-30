
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
        if(mod_mocreatures.mocreaturesGlass.othersettings.sugar_lump) {
            itemstack.count--;
            entityplayer.heal(a);
        }
        return itemstack;
    }

    private int a;
}
