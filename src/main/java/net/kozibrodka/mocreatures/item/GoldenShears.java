package net.kozibrodka.mocreatures.item;

//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by FernFlower decompiler)
//


import net.minecraft.block.BlockBase;
import net.minecraft.entity.Living;
import net.minecraft.item.ItemBase;
import net.minecraft.item.ItemInstance;
import net.modificationstation.stationapi.api.registry.Identifier;
import net.modificationstation.stationapi.api.template.item.TemplateItemBase;

public class GoldenShears extends TemplateItemBase {

    public GoldenShears(Identifier i) {
        super(i);
        this.setMaxStackSize(1);
        this.setDurability(238);
    }

    public boolean postMine(ItemInstance arg, int i, int j, int k, int l, Living arg2) {
        if (i == BlockBase.LEAVES.id || i == BlockBase.COBWEB.id) {
            arg.applyDamage(1, arg2);
        }

        return super.postMine(arg, i, j, k, l, arg2);
    }

    public boolean isEffectiveOn(BlockBase arg) {
        return arg.id == BlockBase.COBWEB.id;
    }

    public float getStrengthOnBlock(ItemInstance arg, BlockBase arg2) {
        if (arg2.id != BlockBase.COBWEB.id && arg2.id != BlockBase.LEAVES.id) {
            return arg2.id == BlockBase.WOOL.id ? 5.0F : super.getStrengthOnBlock(arg, arg2);
        } else {
            return 15.0F;
        }
    }
}
