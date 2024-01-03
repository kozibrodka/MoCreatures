package net.kozibrodka.mocreatures.events;

import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.fabricmc.loader.api.FabricLoader;
import net.kozibrodka.mocreatures.glasscfg.MocreaturesCFG;
import net.kozibrodka.mocreatures.item.*;
import net.glasslauncher.mods.api.gcapi.api.GConfig;
import net.mine_diver.unsafeevents.listener.EventListener;
import net.mine_diver.unsafeevents.listener.ListenerPriority;
import net.minecraft.achievement.Achievements;
import net.minecraft.block.BlockBase;
import net.minecraft.client.Minecraft;
import net.minecraft.client.options.KeyBinding;
import net.minecraft.item.ItemBase;
import net.minecraft.item.ItemInstance;
import net.modificationstation.stationapi.api.client.event.option.KeyBindingRegisterEvent;
import net.modificationstation.stationapi.api.event.achievement.AchievementRegisterEvent;
import net.modificationstation.stationapi.api.event.container.slot.ItemUsedInCraftingEvent;
import net.modificationstation.stationapi.api.event.recipe.RecipeRegisterEvent;
import net.modificationstation.stationapi.api.event.registry.ItemRegistryEvent;
import net.modificationstation.stationapi.api.mod.entrypoint.Entrypoint;
import net.modificationstation.stationapi.api.recipe.CraftingRegistry;
import net.modificationstation.stationapi.api.registry.Identifier;
import net.modificationstation.stationapi.api.registry.ModID;
import net.modificationstation.stationapi.api.template.item.TemplateItemBase;
import net.modificationstation.stationapi.api.util.Null;
import net.minecraft.achievement.Achievement;
import org.lwjgl.input.Keyboard;

import java.util.List;


public class mod_mocreatures {

    @GConfig(value = "MocreaturesCFG", visibleName = "Mo' Creatures Config")
    public static final MocreaturesCFG mocreaturesGlass = new MocreaturesCFG();

    @Entrypoint.ModID
    public static final ModID MOD_ID = Null.get();

    public static Achievement Indiana;
    public static Achievement BunnyKilla;
    public static Achievement WilfFlyingWest;
    public static Achievement RobertMaklowicz;

    //TODO: horse faliing DMG crazy speed boost

    @EventListener(priority = ListenerPriority.HIGHEST)
    public void registerAchievements(AchievementRegisterEvent event) {
        Indiana = new Achievement(77, MOD_ID.id("indiana").toString(), -4, -4, mod_mocreatures.whip, Achievements.OPEN_INVENTORY).method_1041();
        BunnyKilla = new Achievement(78, MOD_ID.id("bunnykilla").toString(), -5, -5, mod_mocreatures.whip, Achievements.OPEN_INVENTORY).method_1041();
        //TODO: extra example achievement, make not retarded names
//        WilfFlyingWest = new Achievement(79, MOD_ID.id("wildflyingwest").toString(), -6, -6, mod_mocreatures.horsesaddle, Achievements.OPEN_INVENTORY).method_1041();
//        RobertMaklowicz = new Achievement(80, MOD_ID.id("robertmaklowicz").toString(), -7, -7, BlockBase.FLOWING_WATER, Achievements.OPEN_INVENTORY).method_1041();

        BunnyKilla.setUnusual();
        Indiana.setUnusual();
//        WilfFlyingWest.setUnusual();
//        RobertMaklowicz.setUnusual();
    }

    @EventListener
    public void registerItems(ItemRegistryEvent event){
        horsesaddle = new HorseSaddle(Identifier.of(MOD_ID, "horsesaddle")).setTranslationKey(MOD_ID, "horsesaddle");
        haystack = new HayStack(Identifier.of(MOD_ID, "haystack")).setTranslationKey(MOD_ID, "haystack");
        sugarlump = new SugarLump(Identifier.of(MOD_ID, "sugarlump")).setTranslationKey(MOD_ID, "sugarlump");
        sharkteeth = new TemplateItemBase(Identifier.of(MOD_ID, "sharkteeth")).setTranslationKey(MOD_ID, "sharkteeth");
        sharkegg = new ItemSharkEgg(Identifier.of(MOD_ID, "sharkegg")).setTranslationKey(MOD_ID, "sharkegg");
        fishyegg = new ItemFishyEgg(Identifier.of(MOD_ID, "fishyegg")).setTranslationKey(MOD_ID, "fishyegg");
        bigcatclaw = new TemplateItemBase(Identifier.of(MOD_ID, "bigcatclaw")).setTranslationKey(MOD_ID, "bigcatclaw");
        whip = new ItemWhip(Identifier.of(MOD_ID, "whip")).setTranslationKey(MOD_ID, "whip");
        medallion = new TemplateItemBase(Identifier.of(MOD_ID, "medallion")).setTranslationKey(MOD_ID, "medallion");
        kittybed = new ItemKittyBed(Identifier.of(MOD_ID, "kittybed")).setTranslationKey(MOD_ID, "kittybed");
        litterbox = new ItemLitterBox(Identifier.of(MOD_ID, "litterbox")).setTranslationKey(MOD_ID, "litterbox");
        woolball = new TemplateItemBase(Identifier.of(MOD_ID, "woolball")).setTranslationKey(MOD_ID, "woolball");
        rope = new TemplateItemBase(Identifier.of(MOD_ID, "rope")).setTranslationKey(MOD_ID, "rope");
        petfood = new TemplateItemBase(Identifier.of(MOD_ID, "petfood")).setTranslationKey(MOD_ID, "petfood");

        //TODO: extra items, optional for balance propably going to remove some of it
        if(mocreaturesGlass.balancesettings.balance_drop) {
            sharkoil = new TemplateItemBase(Identifier.of(MOD_ID, "sharkoil")).setTranslationKey(MOD_ID, "sharkoil");
            wildleather = new TemplateItemBase(Identifier.of(MOD_ID, "wildleather")).setTranslationKey(MOD_ID, "wildleather");
            polarleather = new TemplateItemBase(Identifier.of(MOD_ID, "polarleather")).setTranslationKey(MOD_ID, "polarleather");
            greenapple = new GreenApple(Identifier.of(MOD_ID, "greenapple")).setTranslationKey(MOD_ID, "greenapple");
            bigcatfood = new WildFood(Identifier.of(MOD_ID, "bigcatfood")).setTranslationKey(MOD_ID, "bigcatfood");
            sharkfood = new WildFood(Identifier.of(MOD_ID, "sharkfood")).setTranslationKey(MOD_ID, "sharkfood");
//        goldenshears = new GoldenShears(Identifier.of(MOD_ID, "goldenshears")).setTranslationKey(MOD_ID, "goldenshears").setContainerItem(mod_mocreatures.goldenshears);
        }
    }

    @EventListener
    public void registerRecipes(RecipeRegisterEvent event) {

        CraftingRegistry.addShapedRecipe(new ItemInstance(mod_mocreatures.kittybed, 1, 14), "###", "#X#", "Z  ", '#', BlockBase.WOOD, 'X', new ItemInstance(BlockBase.WOOL, 1, 1), 'Z', ItemBase.ironIngot);
        CraftingRegistry.addShapedRecipe(new ItemInstance(mod_mocreatures.kittybed, 1, 13), "###", "#X#", "Z  ", '#', BlockBase.WOOD, 'X', new ItemInstance(BlockBase.WOOL, 1, 2), 'Z', ItemBase.ironIngot);
        CraftingRegistry.addShapedRecipe(new ItemInstance(mod_mocreatures.kittybed, 1, 12), "###", "#X#", "Z  ", '#', BlockBase.WOOD, 'X', new ItemInstance(BlockBase.WOOL, 1, 3), 'Z', ItemBase.ironIngot);
        CraftingRegistry.addShapedRecipe(new ItemInstance(mod_mocreatures.kittybed, 1, 11), "###", "#X#", "Z  ", '#', BlockBase.WOOD, 'X', new ItemInstance(BlockBase.WOOL, 1, 4), 'Z', ItemBase.ironIngot);
        CraftingRegistry.addShapedRecipe(new ItemInstance(mod_mocreatures.kittybed, 1, 10), "###", "#X#", "Z  ", '#', BlockBase.WOOD, 'X', new ItemInstance(BlockBase.WOOL, 1, 5), 'Z', ItemBase.ironIngot);
        CraftingRegistry.addShapedRecipe(new ItemInstance(mod_mocreatures.kittybed, 1, 9), "###", "#X#", "Z  ", '#', BlockBase.WOOD, 'X', new ItemInstance(BlockBase.WOOL, 1, 6), 'Z', ItemBase.ironIngot);
        CraftingRegistry.addShapedRecipe(new ItemInstance(mod_mocreatures.kittybed, 1, 8), "###", "#X#", "Z  ", '#', BlockBase.WOOD, 'X', new ItemInstance(BlockBase.WOOL, 1, 7), 'Z', ItemBase.ironIngot);
        CraftingRegistry.addShapedRecipe(new ItemInstance(mod_mocreatures.kittybed, 1, 7), "###", "#X#", "Z  ", '#', BlockBase.WOOD, 'X', new ItemInstance(BlockBase.WOOL, 1, 8), 'Z', ItemBase.ironIngot);
        CraftingRegistry.addShapedRecipe(new ItemInstance(mod_mocreatures.kittybed, 1, 6), "###", "#X#", "Z  ", '#', BlockBase.WOOD, 'X', new ItemInstance(BlockBase.WOOL, 1, 9), 'Z', ItemBase.ironIngot);
        CraftingRegistry.addShapedRecipe(new ItemInstance(mod_mocreatures.kittybed, 1, 5), "###", "#X#", "Z  ", '#', BlockBase.WOOD, 'X', new ItemInstance(BlockBase.WOOL, 1, 10), 'Z', ItemBase.ironIngot);
        CraftingRegistry.addShapedRecipe(new ItemInstance(mod_mocreatures.kittybed, 1, 4), "###", "#X#", "Z  ", '#', BlockBase.WOOD, 'X', new ItemInstance(BlockBase.WOOL, 1, 11), 'Z', ItemBase.ironIngot);
        CraftingRegistry.addShapedRecipe(new ItemInstance(mod_mocreatures.kittybed, 1, 3), "###", "#X#", "Z  ", '#', BlockBase.WOOD, 'X', new ItemInstance(BlockBase.WOOL, 1, 12), 'Z', ItemBase.ironIngot);
        CraftingRegistry.addShapedRecipe(new ItemInstance(mod_mocreatures.kittybed, 1, 2), "###", "#X#", "Z  ", '#', BlockBase.WOOD, 'X', new ItemInstance(BlockBase.WOOL, 1, 13), 'Z', ItemBase.ironIngot);
        CraftingRegistry.addShapedRecipe(new ItemInstance(mod_mocreatures.kittybed, 1, 1), "###", "#X#", "Z  ", '#', BlockBase.WOOD, 'X', new ItemInstance(BlockBase.WOOL, 1, 14), 'Z', ItemBase.ironIngot);
        CraftingRegistry.addShapedRecipe(new ItemInstance(mod_mocreatures.kittybed, 1, 0), "###", "#X#", "Z  ", '#', BlockBase.WOOD, 'X', new ItemInstance(BlockBase.WOOL, 1, 15), 'Z', ItemBase.ironIngot);
        CraftingRegistry.addShapedRecipe(new ItemInstance(mod_mocreatures.kittybed, 1, 15), "###", "#X#", "Z  ", '#', BlockBase.WOOD, 'X', new ItemInstance(BlockBase.WOOL, 1, 0), 'Z', ItemBase.ironIngot);

        CraftingRegistry.addShapedRecipe(new ItemInstance(rope, 1), "# #", " # ", "# #", '#', ItemBase.string);
        CraftingRegistry.addShapelessRecipe(new ItemInstance(petfood, 4), new ItemInstance(ItemBase.rawFish, 1), new ItemInstance(ItemBase.rawPorkchop, 1));
        CraftingRegistry.addShapedRecipe(new ItemInstance(woolball, 1), " # ", "# #", " # ", '#', ItemBase.string);
        CraftingRegistry.addShapedRecipe(new ItemInstance(litterbox, 1), "###", "#X#", "###", '#', BlockBase.WOOD, 'X', BlockBase.SAND);
        CraftingRegistry.addShapedRecipe(new ItemInstance(medallion, 1), "# #", "XZX", " X ", '#', ItemBase.leather, 'Z', ItemBase.diamond, 'X', ItemBase.goldIngot);
        CraftingRegistry.addShapedRecipe(new ItemInstance(medallion, 1), "# #", " X ", '#', ItemBase.leather, 'X', ItemBase.goldIngot);
        CraftingRegistry.addShapedRecipe(new ItemInstance(whip, 1), "#X#", "X X", "# Z", '#', bigcatclaw, 'X', ItemBase.leather, 'Z', ItemBase.ironIngot);
        CraftingRegistry.addShapedRecipe(new ItemInstance(haystack, 1), "XXX", "XXX", 'X', ItemBase.wheat);
        CraftingRegistry.addShapedRecipe(new ItemInstance(ItemBase.wheat, 6), "X", 'X', haystack);
        CraftingRegistry.addShapedRecipe(new ItemInstance(sugarlump, 1), "XX", "##", 'X', ItemBase.sugar, '#', ItemBase.sugar);
        CraftingRegistry.addShapedRecipe(new ItemInstance(ItemBase.chainChestplate, 1), "X X", "XXX", "XXX", 'X', sharkteeth);
        CraftingRegistry.addShapedRecipe(new ItemInstance(ItemBase.chainHelmet, 1), "XXX", "X X", 'X', sharkteeth);
        CraftingRegistry.addShapedRecipe(new ItemInstance(ItemBase.chainLeggings, 1), "XXX", "X X", "X X", 'X', sharkteeth);
        CraftingRegistry.addShapedRecipe(new ItemInstance(ItemBase.chainBoots, 1), "X X", "X X", 'X', sharkteeth);

        CraftingRegistry.addShapedRecipe(new ItemInstance(horsesaddle, 1), "X", "#", 'X', ItemBase.saddle, '#', ItemBase.ironIngot);
        CraftingRegistry.addShapedRecipe(new ItemInstance(horsesaddle, 1), "XXX", "X#X", "# #", '#', ItemBase.ironIngot, 'X', ItemBase.leather);

        //TODO: extra, now doesnt make sense, also make recipes for all items (delete json)
        if(mocreaturesGlass.balancesettings.balance_drop) {
//            for (int j = 0; j < 16; j++) {
//                CraftingRegistry.addShapelessRecipe(new ItemInstance(BlockBase.WOOL, 8, 15 - j), new ItemInstance(ItemBase.dyePowder, 1, j), new ItemInstance(wildleather, 1));
//            }
//
//            for (int j = 0; j < 16; j++) {
//                CraftingRegistry.addShapelessRecipe(new ItemInstance(BlockBase.WOOL, 16, 15 - j), new ItemInstance(ItemBase.dyePowder, 1, j), new ItemInstance(polarleather, 1));
//            }
//
//            CraftingRegistry.addShapelessRecipe(new ItemInstance(ItemBase.dyePowder, 16, 0), new ItemInstance(ItemBase.dyePowder, 1, 0), new ItemInstance(sharkoil, 1));
//            CraftingRegistry.addShapelessRecipe(new ItemInstance(ItemBase.dyePowder, 8, 3), new ItemInstance(ItemBase.dyePowder, 1, 3), new ItemInstance(sharkoil, 1));
//            CraftingRegistry.addShapelessRecipe(new ItemInstance(ItemBase.dyePowder, 4, 4), new ItemInstance(ItemBase.dyePowder, 1, 4), new ItemInstance(sharkoil, 1));
            CraftingRegistry.addShapelessRecipe(new ItemInstance(bigcatfood, 3), new ItemInstance(ItemBase.rawPorkchop, 1), new ItemInstance(ItemBase.egg, 1), new ItemInstance(ItemBase.rawFish, 1));
            CraftingRegistry.addShapelessRecipe(new ItemInstance(sharkfood, 3), new ItemInstance(ItemBase.rawFish, 1), new ItemInstance(fishyegg, 1), new ItemInstance(ItemBase.rawFish, 1));

            if (!FabricLoader.getInstance().isModLoaded("aether")) {
                CraftingRegistry.addShapedRecipe(new ItemInstance(horsesaddle, 1), "X", "#", 'X', ItemBase.saddle, '#', ItemBase.ironIngot);
            }
        }
    }

    public static int colorize(int i)
    {
        return ~i & 0xf;
    }

    public static ItemBase horsesaddle;
    public static ItemBase haystack;
    public static ItemBase sugarlump;
    public static ItemBase sharkteeth;
    public static ItemBase sharkegg;
    public static ItemBase fishyegg;
    public static ItemBase bigcatclaw;
    public static ItemBase whip;
    public static ItemBase medallion;
    public static ItemBase litterbox;
    public static ItemBase woolball;
    public static ItemBase rope;
    public static ItemBase petfood ;
    public static ItemBase kittybed;

    public static ItemBase sharkoil;
    public static ItemBase wildleather;
    public static ItemBase polarleather;
    public static ItemBase greenapple;
    public static ItemBase bigcatfood;
    public static ItemBase sharkfood;
    public static ItemBase goldenshears;


}
