package net.kozibrodka.mocreatures.events;

import net.kozibrodka.mocreatures.item.MocreaturesEggItem;
import net.mine_diver.unsafeevents.listener.EventListener;
import net.minecraft.item.Item;
import net.modificationstation.stationapi.api.event.registry.ItemRegistryEvent;
import net.modificationstation.stationapi.api.mod.entrypoint.Entrypoint;
import net.modificationstation.stationapi.api.util.Identifier;
import net.modificationstation.stationapi.api.util.Namespace;
import net.modificationstation.stationapi.api.util.Null;

public class EggListener {

    public static Item egg1;
    public static Item egg2;
    public static Item egg3;
    public static Item egg4;
    public static Item egg5;
    public static Item egg6;
    public static Item egg7;
    public static Item egg8;
    public static Item egg9;
    public static Item egg10;
    public static Item egg11;
    public static Item egg12;
    public static Item egg13;
    public static Item egg14;
    public static Item egg15;
    public static Item egg16;
    public static Item egg17;
    public static Item egg18;
    public static Item egg19;
    public static Item egg20;
    public static Item egg21;
    public static Item egg22;
    public static Item egg23;
    public static Item egg24;
    public static Item egg25;
    public static Item egg26;
    public static Item egg27;
    public static Item egg28;

    public static Item egg30;
    public static Item egg31;
    public static Item egg32;
    public static Item egg33;
    public static Item egg34;
    public static Item egg35;
    public static Item egg36;
    public static Item egg37;

    public static Item egg40;
    public static Item egg41;
    public static Item egg42;
    public static Item egg43;
    public static Item egg44;
    public static Item egg45;
    public static Item egg46;

    public static Item egg50;
    public static Item egg51;
    public static Item egg52;
    public static Item egg53;
    public static Item egg54;
    public static Item egg55;

    public static Item egg60;
    public static Item egg61;
    public static Item egg62;
    public static Item egg63;
    public static Item egg64;
    public static Item egg65;
    public static Item egg66;
    public static Item egg67;
    public static Item egg68;
    public static Item egg69;

    @Entrypoint.Namespace
    public static Namespace MOD_ID = Null.get();

    @EventListener
    public void registerItems(ItemRegistryEvent event) {
        if(mod_mocreatures.mocreaturesGlass.balancesettings.spawn_eggs){
            egg1 = new MocreaturesEggItem(Identifier.of(MOD_ID, "Bear_egg"), 1).setTranslationKey(MOD_ID, "Bear_egg");
            egg2 = new MocreaturesEggItem(Identifier.of(MOD_ID, "BigCat_egg"), 2).setTranslationKey(MOD_ID, "BigCat_egg");
            egg3 = new MocreaturesEggItem(Identifier.of(MOD_ID, "Bird_egg"), 3).setTranslationKey(MOD_ID, "Bird_egg");
            egg4 = new MocreaturesEggItem(Identifier.of(MOD_ID, "Boar_egg"), 4).setTranslationKey(MOD_ID, "Boar_egg");
            egg5 = new MocreaturesEggItem(Identifier.of(MOD_ID, "Bunny_egg"), 5).setTranslationKey(MOD_ID, "Bunny_egg");
            egg6 = new MocreaturesEggItem(Identifier.of(MOD_ID, "CaveOgre_egg"), 6).setTranslationKey(MOD_ID, "CaveOgre_egg");
            egg7 = new MocreaturesEggItem(Identifier.of(MOD_ID, "Deer_egg"), 7).setTranslationKey(MOD_ID, "Deer_egg");
            egg8 = new MocreaturesEggItem(Identifier.of(MOD_ID, "Dolphin_egg"), 8).setTranslationKey(MOD_ID, "Dolphin_egg");
            egg9 = new MocreaturesEggItem(Identifier.of(MOD_ID, "Duck_egg"), 9).setTranslationKey(MOD_ID, "Duck_egg");
            egg10 = new MocreaturesEggItem(Identifier.of(MOD_ID, "FireOgre_egg"), 10).setTranslationKey(MOD_ID, "FireOgre_egg");
            egg11 = new MocreaturesEggItem(Identifier.of(MOD_ID, "Fishy_egg"), 11).setTranslationKey(MOD_ID, "Fishy_egg");
//        egg12 = new MocreaturesEggItem(Identifier.of(MOD_ID, "FishyEgg_egg"), 12).setTranslationKey(MOD_ID, "FishyEgg_egg");
            egg13 = new MocreaturesEggItem(Identifier.of(MOD_ID, "FlameWraith_egg"), 13).setTranslationKey(MOD_ID, "FlameWraith_egg");
            egg14 = new MocreaturesEggItem(Identifier.of(MOD_ID, "Fox_egg"), 14).setTranslationKey(MOD_ID, "Fox_egg");
            egg15 = new MocreaturesEggItem(Identifier.of(MOD_ID, "HellRat_egg"), 15).setTranslationKey(MOD_ID, "HellRat_egg");
            egg16 = new MocreaturesEggItem(Identifier.of(MOD_ID, "Horse_egg"), 16).setTranslationKey(MOD_ID, "Horse_egg");
            egg17 = new MocreaturesEggItem(Identifier.of(MOD_ID, "Kitty_egg"), 17).setTranslationKey(MOD_ID, "Kitty_egg");
//        egg18 = new MocreaturesEggItem(Identifier.of(MOD_ID, "KittyBed_egg"), 18).setTranslationKey(MOD_ID, "KittyBed_egg");
//        egg19 = new MocreaturesEggItem(Identifier.of(MOD_ID, "LitterBox_egg"), 19).setTranslationKey(MOD_ID, "LitterBox_egg");
            egg20 = new MocreaturesEggItem(Identifier.of(MOD_ID, "Mouse_egg"), 20).setTranslationKey(MOD_ID, "Mouse_egg");
            egg21 = new MocreaturesEggItem(Identifier.of(MOD_ID, "Ogre_egg"), 21).setTranslationKey(MOD_ID, "Ogre_egg");
            egg22 = new MocreaturesEggItem(Identifier.of(MOD_ID, "PolarBear_egg"), 22).setTranslationKey(MOD_ID, "PolarBear_egg");
            egg23 = new MocreaturesEggItem(Identifier.of(MOD_ID, "Rat_egg"), 23).setTranslationKey(MOD_ID, "Rat_egg");
            egg24 = new MocreaturesEggItem(Identifier.of(MOD_ID, "Shark_egg"), 24).setTranslationKey(MOD_ID, "Shark_egg");
//        egg25 = new MocreaturesEggItem(Identifier.of(MOD_ID, "SharkEgg_egg"), 25).setTranslationKey(MOD_ID, "SharkEgg_egg");
            egg26 = new MocreaturesEggItem(Identifier.of(MOD_ID, "WereWolf_egg"), 26).setTranslationKey(MOD_ID, "WereWolf_egg");
            egg27 = new MocreaturesEggItem(Identifier.of(MOD_ID, "Wraith_egg"), 27).setTranslationKey(MOD_ID, "Wraith_egg");
            egg28 = new MocreaturesEggItem(Identifier.of(MOD_ID, "WildWolf_egg"), 28).setTranslationKey(MOD_ID, "WildWolf_egg");

            egg30 = new MocreaturesEggItem(Identifier.of(MOD_ID, "horse1_egg"), 30).setTranslationKey(MOD_ID, "horse1_egg");
            egg31 = new MocreaturesEggItem(Identifier.of(MOD_ID, "horse2_egg"), 31).setTranslationKey(MOD_ID, "horse2_egg");
            egg32 = new MocreaturesEggItem(Identifier.of(MOD_ID, "horse3_egg"), 32).setTranslationKey(MOD_ID, "horse3_egg");
            egg33 = new MocreaturesEggItem(Identifier.of(MOD_ID, "horse4_egg"), 33).setTranslationKey(MOD_ID, "horse4_egg");
            egg34 = new MocreaturesEggItem(Identifier.of(MOD_ID, "horse5_egg"), 34).setTranslationKey(MOD_ID, "horse5_egg");
            egg35 = new MocreaturesEggItem(Identifier.of(MOD_ID, "horse6_egg"), 35).setTranslationKey(MOD_ID, "horse6_egg");
            egg36 = new MocreaturesEggItem(Identifier.of(MOD_ID, "horse7_egg"), 36).setTranslationKey(MOD_ID, "horse7_egg");
            egg37 = new MocreaturesEggItem(Identifier.of(MOD_ID, "horse8_egg"), 37).setTranslationKey(MOD_ID, "horse8_egg");

            egg40 = new MocreaturesEggItem(Identifier.of(MOD_ID, "bigcat1_egg"), 40).setTranslationKey(MOD_ID, "bigcat1_egg");
            egg41 = new MocreaturesEggItem(Identifier.of(MOD_ID, "bigcat2_egg"), 41).setTranslationKey(MOD_ID, "bigcat2_egg");
            egg42 = new MocreaturesEggItem(Identifier.of(MOD_ID, "bigcat3_egg"), 42).setTranslationKey(MOD_ID, "bigcat3_egg");
            egg43 = new MocreaturesEggItem(Identifier.of(MOD_ID, "bigcat4_egg"), 43).setTranslationKey(MOD_ID, "bigcat4_egg");
            egg44 = new MocreaturesEggItem(Identifier.of(MOD_ID, "bigcat5_egg"), 44).setTranslationKey(MOD_ID, "bigcat5_egg");
            egg45 = new MocreaturesEggItem(Identifier.of(MOD_ID, "bigcat6_egg"), 45).setTranslationKey(MOD_ID, "bigcat6_egg");
            egg46 = new MocreaturesEggItem(Identifier.of(MOD_ID, "bigcat7_egg"), 46).setTranslationKey(MOD_ID, "bigcat7_egg");

            egg50 = new MocreaturesEggItem(Identifier.of(MOD_ID, "dolphin1_egg"), 50).setTranslationKey(MOD_ID, "dolphin1_egg");
            egg51 = new MocreaturesEggItem(Identifier.of(MOD_ID, "dolphin2_egg"), 51).setTranslationKey(MOD_ID, "dolphin2_egg");
            egg52 = new MocreaturesEggItem(Identifier.of(MOD_ID, "dolphin3_egg"), 52).setTranslationKey(MOD_ID, "dolphin3_egg");
            egg53 = new MocreaturesEggItem(Identifier.of(MOD_ID, "dolphin4_egg"), 53).setTranslationKey(MOD_ID, "dolphin4_egg");
            egg54 = new MocreaturesEggItem(Identifier.of(MOD_ID, "dolphin5_egg"), 54).setTranslationKey(MOD_ID, "dolphin5_egg");
            egg55 = new MocreaturesEggItem(Identifier.of(MOD_ID, "dolphin6_egg"), 55).setTranslationKey(MOD_ID, "dolphin6_egg");

            egg60 = new MocreaturesEggItem(Identifier.of(MOD_ID, "zebra_egg"), 60).setTranslationKey(MOD_ID, "zebra_egg");
            egg61 = new MocreaturesEggItem(Identifier.of(MOD_ID, "elephant_egg"), 61).setTranslationKey(MOD_ID, "elephant_egg");
            egg62 = new MocreaturesEggItem(Identifier.of(MOD_ID, "hippo_egg"), 62).setTranslationKey(MOD_ID, "hippo_egg");
            egg63 = new MocreaturesEggItem(Identifier.of(MOD_ID, "giraffe_egg"), 63).setTranslationKey(MOD_ID, "giraffe_egg");
            egg64 = new MocreaturesEggItem(Identifier.of(MOD_ID, "crocodile_egg"), 64).setTranslationKey(MOD_ID, "crocodile_egg");
//            egg60 = new MocreaturesEggItem(Identifier.of(MOD_ID, "_egg"), 60).setTranslationKey(MOD_ID, "_egg");
        }
    }

}
