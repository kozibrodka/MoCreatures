package net.kozibrodka.mocreatures.item;

import net.modificationstation.stationapi.api.util.Identifier;
import net.minecraft.block.Block;
import net.minecraft.entity.LivingEntity;
import net.minecraft.item.ItemStack;
import net.modificationstation.stationapi.api.template.item.TemplateItem;

public class GoldenShears extends TemplateItem {

    public GoldenShears(Identifier i) {
        super(i);
        this.setMaxCount(1);
        this.setMaxDamage(238);
    }

    public boolean postMine(ItemStack arg, int i, int j, int k, int l, LivingEntity arg2) {
        if (i == Block.LEAVES.id || i == Block.COBWEB.id) {
            arg.damage(1, arg2);
        }

        return super.postMine(arg, i, j, k, l, arg2);
    }

    public boolean isSuitableFor(Block arg) {
        return arg.id == Block.COBWEB.id;
    }

    public float getMiningSpeedMultiplier(ItemStack arg, Block arg2) {
        if (arg2.id != Block.COBWEB.id && arg2.id != Block.LEAVES.id) {
            return arg2.id == Block.WOOL.id ? 5.0F : super.getMiningSpeedMultiplier(arg, arg2);
        } else {
            return 15.0F;
        }
    }
}
