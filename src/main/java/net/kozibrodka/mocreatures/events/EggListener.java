package net.kozibrodka.mocreatures.events;

import net.kozibrodka.mocreatures.item.MocreaturesEggItem;
import net.mine_diver.unsafeevents.listener.EventListener;
import net.modificationstation.stationapi.api.event.registry.ItemRegistryEvent;
import net.modificationstation.stationapi.api.mod.entrypoint.Entrypoint;
import net.modificationstation.stationapi.api.registry.Identifier;
import net.modificationstation.stationapi.api.registry.ModID;
import net.modificationstation.stationapi.api.template.item.TemplateItemBase;
import net.modificationstation.stationapi.api.util.Null;

public class EggListener {

    public static TemplateItemBase egg1;
    public static TemplateItemBase egg2;
    public static TemplateItemBase egg3;
    public static TemplateItemBase egg4;
    public static TemplateItemBase egg5;
    public static TemplateItemBase egg6;
    public static TemplateItemBase egg7;
    public static TemplateItemBase egg8;
    public static TemplateItemBase egg9;
    public static TemplateItemBase egg10;
    public static TemplateItemBase egg11;
    public static TemplateItemBase egg12;
    public static TemplateItemBase egg13;
    public static TemplateItemBase egg14;
    public static TemplateItemBase egg15;
    public static TemplateItemBase egg16;
    public static TemplateItemBase egg17;
    public static TemplateItemBase egg18;
    public static TemplateItemBase egg19;
    public static TemplateItemBase egg20;
    public static TemplateItemBase egg21;
    public static TemplateItemBase egg22;
    public static TemplateItemBase egg23;
    public static TemplateItemBase egg24;
    public static TemplateItemBase egg25;
    public static TemplateItemBase egg26;
    public static TemplateItemBase egg27;
    public static TemplateItemBase egg28;





    @Entrypoint.ModID
    public static final ModID MOD_ID = Null.get();
    @EventListener
    public void registerItems(ItemRegistryEvent event) {
        egg1 = new MocreaturesEggItem(Identifier.of(MOD_ID, "Bear egg"), 1).setTranslationKey(MOD_ID, "Bear egg");
        egg2 = new MocreaturesEggItem(Identifier.of(MOD_ID, "BigCat egg"), 2).setTranslationKey(MOD_ID, "BigCat egg");
        egg3 = new MocreaturesEggItem(Identifier.of(MOD_ID, "Bird egg"), 3).setTranslationKey(MOD_ID, "Bird egg");
        egg4 = new MocreaturesEggItem(Identifier.of(MOD_ID, "Boar egg"), 4).setTranslationKey(MOD_ID, "Boar egg");
        egg5 = new MocreaturesEggItem(Identifier.of(MOD_ID, "Bunny egg"), 5).setTranslationKey(MOD_ID, "Bunny egg");
        egg6 = new MocreaturesEggItem(Identifier.of(MOD_ID, "CaveOgre egg"), 6).setTranslationKey(MOD_ID, "CaveOgre egg");
        egg7 = new MocreaturesEggItem(Identifier.of(MOD_ID, "Deer egg"), 7).setTranslationKey(MOD_ID, "Deer egg");
        egg8 = new MocreaturesEggItem(Identifier.of(MOD_ID, "Dolphin egg"), 8).setTranslationKey(MOD_ID, "Dolphin egg");
        egg9 = new MocreaturesEggItem(Identifier.of(MOD_ID, "Duck egg"), 9).setTranslationKey(MOD_ID, "Duck egg");
        egg10 = new MocreaturesEggItem(Identifier.of(MOD_ID, "FireOgre egg"), 10).setTranslationKey(MOD_ID, "FireOgre egg");
        egg11 = new MocreaturesEggItem(Identifier.of(MOD_ID, "Fishy egg"), 11).setTranslationKey(MOD_ID, "Fishy egg");
//        egg12 = new MocreaturesEggItem(Identifier.of(MOD_ID, "FishyEgg egg"), 12).setTranslationKey(MOD_ID, "FishyEgg egg");
        egg13 = new MocreaturesEggItem(Identifier.of(MOD_ID, "FlameWraith egg"), 13).setTranslationKey(MOD_ID, "FlameWraith egg");
        egg14 = new MocreaturesEggItem(Identifier.of(MOD_ID, "Fox egg"), 14).setTranslationKey(MOD_ID, "Fox egg");
        egg15 = new MocreaturesEggItem(Identifier.of(MOD_ID, "HellRat egg"), 15).setTranslationKey(MOD_ID, "HellRat egg");
        egg16 = new MocreaturesEggItem(Identifier.of(MOD_ID, "Horse egg"), 16).setTranslationKey(MOD_ID, "Horse egg");
        egg17 = new MocreaturesEggItem(Identifier.of(MOD_ID, "Kitty egg"), 17).setTranslationKey(MOD_ID, "Kitty egg");
//        egg18 = new MocreaturesEggItem(Identifier.of(MOD_ID, "KittyBed egg"), 18).setTranslationKey(MOD_ID, "KittyBed egg");
//        egg19 = new MocreaturesEggItem(Identifier.of(MOD_ID, "LitterBox egg"), 19).setTranslationKey(MOD_ID, "LitterBox egg");
        egg20 = new MocreaturesEggItem(Identifier.of(MOD_ID, "Mouse egg"), 20).setTranslationKey(MOD_ID, "Mouse egg");
        egg21 = new MocreaturesEggItem(Identifier.of(MOD_ID, "Ogre egg"), 21).setTranslationKey(MOD_ID, "Ogre egg");
        egg22 = new MocreaturesEggItem(Identifier.of(MOD_ID, "PolarBear egg"), 22).setTranslationKey(MOD_ID, "PolarBear egg");
        egg23 = new MocreaturesEggItem(Identifier.of(MOD_ID, "Rat egg"), 23).setTranslationKey(MOD_ID, "Rat egg");
        egg24 = new MocreaturesEggItem(Identifier.of(MOD_ID, "Shark egg"), 24).setTranslationKey(MOD_ID, "Shark egg");
//        egg25 = new MocreaturesEggItem(Identifier.of(MOD_ID, "SharkEgg egg"), 25).setTranslationKey(MOD_ID, "SharkEgg egg");
        egg26 = new MocreaturesEggItem(Identifier.of(MOD_ID, "WereWolf egg"), 26).setTranslationKey(MOD_ID, "WereWolf egg");
        egg27 = new MocreaturesEggItem(Identifier.of(MOD_ID, "Wraith egg"), 27).setTranslationKey(MOD_ID, "Wraith egg");
        egg28 = new MocreaturesEggItem(Identifier.of(MOD_ID, "WildWolf egg"), 28).setTranslationKey(MOD_ID, "WildWolf egg");

    }

}
