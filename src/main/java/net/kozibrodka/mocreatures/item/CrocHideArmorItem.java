package net.kozibrodka.mocreatures.item;

import net.kozibrodka.mocreatures.events.mod_mocreatures;
import net.minecraft.item.ArmorItem;
import net.modificationstation.stationapi.api.client.item.ArmorTextureProvider;
import net.modificationstation.stationapi.api.template.item.TemplateArmorItem;
import net.modificationstation.stationapi.api.util.Identifier;

public class CrocHideArmorItem extends TemplateArmorItem implements ArmorTextureProvider {

    public CrocHideArmorItem(Identifier identifier, int armorType) {
        super(identifier, 1, 1, armorType);
//        setMaxDamage(576);
    }


    @Override
    public Identifier getTexture(ArmorItem armour) {
        return mod_mocreatures.MOD_ID.id("croc");
    }
}
