package net.kozibrodka.mocreatures.events;

import net.fabricmc.loader.api.FabricLoader;
import net.kozibrodka.mocreatures.glasscfg.MocreaturesCFG;
import net.kozibrodka.mocreatures.item.*;
import net.glasslauncher.mods.api.gcapi.api.GConfig;
import net.mine_diver.unsafeevents.listener.EventListener;
import net.mine_diver.unsafeevents.listener.ListenerPriority;
import net.minecraft.achievement.Achievements;
import net.minecraft.block.BlockBase;
import net.minecraft.client.Minecraft;
import net.minecraft.item.ItemBase;
import net.minecraft.item.ItemInstance;
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


public class mod_mocreatures {

    @GConfig(value = "MocreaturesCFG", visibleName = "Mo' Creatures Config")
    public static final MocreaturesCFG mocreaturesGlass = new MocreaturesCFG();

    @Entrypoint.ModID
    public static final ModID MOD_ID = Null.get();

    public static Achievement Indiana;
    public static Achievement BunnyKilla;
    public static Achievement WilfFlyingWest;
    public static Achievement RobertMaklowicz;



    @EventListener(priority = ListenerPriority.HIGHEST)
    public void registerAchievements(AchievementRegisterEvent event) {
//        AchievementPage page = new AchievementPage(MOD_ID, "minecraft");
//        page.addAchievements(Indiana);
//        page.addAchievements(BunnyKilla);
        Indiana = new Achievement(77, MOD_ID.id("indiana").toString(), -4, -4, mod_mocreatures.whip, Achievements.OPEN_INVENTORY).method_1041();
        BunnyKilla = new Achievement(78, MOD_ID.id("bunnykilla").toString(), -5, -5, mod_mocreatures.whip, Achievements.OPEN_INVENTORY).method_1041();
        WilfFlyingWest = new Achievement(79, MOD_ID.id("wildflyingwest").toString(), -6, -6, mod_mocreatures.horsesaddle, Achievements.OPEN_INVENTORY).method_1041();
        RobertMaklowicz = new Achievement(80, MOD_ID.id("robertmaklowicz").toString(), -7, -7, BlockBase.FLOWING_WATER, Achievements.OPEN_INVENTORY).method_1041();


        BunnyKilla.setUnusual();
        Indiana.setUnusual();
        WilfFlyingWest.setUnusual();
        RobertMaklowicz.setUnusual();


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

        for(int j = 0; j < 16; j++)
        {
            CraftingRegistry.addShapelessRecipe(new ItemInstance(BlockBase.WOOL, 8, 15 - j), new ItemInstance(ItemBase.dyePowder, 1, j), new ItemInstance(wildleather, 1));
        }

        for(int j = 0; j < 16; j++)
        {
            CraftingRegistry.addShapelessRecipe(new ItemInstance(BlockBase.WOOL, 16, 15 - j), new ItemInstance(ItemBase.dyePowder, 1, j), new ItemInstance(polarleather, 1));
        }

        CraftingRegistry.addShapelessRecipe(new ItemInstance(ItemBase.dyePowder, 16, 0), new ItemInstance(ItemBase.dyePowder, 1, 0), new ItemInstance(sharkoil, 1));
        CraftingRegistry.addShapelessRecipe(new ItemInstance(ItemBase.dyePowder, 8, 3), new ItemInstance(ItemBase.dyePowder, 1, 3), new ItemInstance(sharkoil, 1));
        CraftingRegistry.addShapelessRecipe(new ItemInstance(ItemBase.dyePowder, 4, 4), new ItemInstance(ItemBase.dyePowder, 1, 4), new ItemInstance(sharkoil, 1));
        CraftingRegistry.addShapelessRecipe(new ItemInstance(bigcatfood, 3), new ItemInstance(ItemBase.rawPorkchop, 1), new ItemInstance(ItemBase.egg, 1), new ItemInstance(ItemBase.rawFish, 1));
        CraftingRegistry.addShapelessRecipe(new ItemInstance(sharkfood, 3), new ItemInstance(ItemBase.rawFish, 1), new ItemInstance(fishyegg, 1), new ItemInstance(ItemBase.rawFish, 1));

        if(!FabricLoader.getInstance().isModLoaded("aether"))
        {
            CraftingRegistry.addShapedRecipe(new ItemInstance(horsesaddle, 1), "X", "#", 'X', ItemBase.saddle, '#', ItemBase.ironIngot);
        }
    }

//    @EventListener
//    private static void onCrafted(ItemUsedInCraftingEvent event) {
////        event.craftingMatrix.getInventorySize();
//        if (event.itemUsed.itemId == goldenshears.id) {
//            event.itemUsed.applyDamage(1, event.player);
//        }
//    }

    public static int colorize(int i)
    {
        return ~i & 0xf;
    }

    @SuppressWarnings("deprecation")
    public static Minecraft mc = Minecraft.class.cast(FabricLoader.getInstance().getGameInstance());


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

        sharkoil = new TemplateItemBase(Identifier.of(MOD_ID, "sharkoil")).setTranslationKey(MOD_ID, "sharkoil");
        wildleather = new TemplateItemBase(Identifier.of(MOD_ID, "wildleather")).setTranslationKey(MOD_ID, "wildleather");
        polarleather = new TemplateItemBase(Identifier.of(MOD_ID, "polarleather")).setTranslationKey(MOD_ID, "polarleather");
        greenapple = new GreenApple(Identifier.of(MOD_ID, "greenapple")).setTranslationKey(MOD_ID, "greenapple");
        bigcatfood = new WildFood(Identifier.of(MOD_ID, "bigcatfood")).setTranslationKey(MOD_ID, "bigcatfood");
        sharkfood = new WildFood(Identifier.of(MOD_ID, "sharkfood")).setTranslationKey(MOD_ID, "sharkfood");
//        goldenshears = new GoldenShears(Identifier.of(MOD_ID, "goldenshears")).setTranslationKey(MOD_ID, "goldenshears").setContainerItem(mod_mocreatures.goldenshears);

    }

    public static TemplateItemBase horsesaddle;
    public static TemplateItemBase haystack;
    public static TemplateItemBase sugarlump;
    public static TemplateItemBase sharkteeth;
    public static TemplateItemBase sharkegg;
    public static TemplateItemBase fishyegg;
    public static TemplateItemBase bigcatclaw;
    public static TemplateItemBase whip;
    public static TemplateItemBase medallion;
    public static TemplateItemBase litterbox;
    public static TemplateItemBase woolball;
    public static TemplateItemBase rope;
    public static TemplateItemBase petfood ;
    public static TemplateItemBase kittybed;

    public static TemplateItemBase sharkoil;
    public static TemplateItemBase wildleather;
    public static TemplateItemBase polarleather;
    public static TemplateItemBase greenapple;
    public static TemplateItemBase bigcatfood;
    public static TemplateItemBase sharkfood;
    public static TemplateItemBase goldenshears;


}
