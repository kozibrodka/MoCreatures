package net.kozibrodka.mocreatures.events;

import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.fabricmc.loader.api.FabricLoader;
import net.glasslauncher.mods.gcapi3.api.ConfigRoot;
import net.kozibrodka.mocreatures.glasscfg.MocreaturesCFG;
import net.kozibrodka.mocreatures.item.*;
import net.kozibrodka.mocreatures.network.*;
import net.kozibrodka.wolves.events.ItemListener;
import net.kozibrodka.wolves.items.RefinedArmorItem;
import net.mine_diver.unsafeevents.listener.EventListener;
import net.mine_diver.unsafeevents.listener.ListenerPriority;
import net.minecraft.achievement.Achievements;
import net.minecraft.block.Block;
import net.minecraft.entity.player.ServerPlayerEntity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.stat.Stat;
import net.minecraft.world.World;
import net.modificationstation.stationapi.api.client.gui.screen.achievement.AchievementPage;
import net.modificationstation.stationapi.api.event.achievement.AchievementRegisterEvent;
import net.modificationstation.stationapi.api.event.network.packet.PacketRegisterEvent;
import net.modificationstation.stationapi.api.event.recipe.RecipeRegisterEvent;
import net.modificationstation.stationapi.api.event.registry.ItemRegistryEvent;
import net.modificationstation.stationapi.api.mod.entrypoint.Entrypoint;
import net.modificationstation.stationapi.api.network.packet.PacketHelper;
import net.modificationstation.stationapi.api.recipe.CraftingRegistry;
import net.modificationstation.stationapi.api.registry.PacketTypeRegistry;
import net.modificationstation.stationapi.api.registry.Registry;
import net.modificationstation.stationapi.api.template.item.TemplateItem;
import net.modificationstation.stationapi.api.util.Identifier;
import net.modificationstation.stationapi.api.util.Namespace;
import net.modificationstation.stationapi.api.util.Null;
import net.minecraft.achievement.Achievement;

import java.util.List;

public class mod_mocreatures {

    @ConfigRoot(value = "MocreaturesCFG", visibleName = "Mo' Creatures Config")
    public static final MocreaturesCFG mocreaturesGlass = new MocreaturesCFG();

    @Entrypoint.Namespace
    public static Namespace MOD_ID = Null.get();

    //TODO: horse faliing DMG crazy speed boost,


    @Environment(EnvType.SERVER)
    public void particlePacket(World world, String name, double x, double y, double z, double i, double j, double k) {
        List list2 = world.players;
        if (list2.size() != 0) {
            for (int k1 = 0; k1 < list2.size(); k1++) {
                ServerPlayerEntity player1 = (ServerPlayerEntity) list2.get(k1);
                PacketHelper.sendTo(player1, new ParticlePacket(name, x, y, z, i, j, k));
            }
        }
    }

    @Environment(EnvType.SERVER)
    public void voicePacket(World world, String name, double x, double y, double z, float g, float h) {
        List list2 = world.players;
        if (list2.size() != 0) {
            for (int k = 0; k < list2.size(); k++) {
                ServerPlayerEntity player1 = (ServerPlayerEntity) list2.get(k);
                PacketHelper.sendTo(player1, new SoundPacket(name, x, y, z, g, h));
            }
        }
    }

    @Environment(EnvType.SERVER)
    public void voicePacket(World world, String name, int id, float vol, float pit) {
        List list2 = world.players;
        if (list2.size() != 0) {
            for (int k = 0; k < list2.size(); k++) {
                ServerPlayerEntity player1 = (ServerPlayerEntity) list2.get(k);
                PacketHelper.sendTo(player1, new SoundPacket(name, id, vol, pit));
            }
        }
    }

    @EventListener
    public void registerPacket(PacketRegisterEvent event) {
        Registry.register(PacketTypeRegistry.INSTANCE, MOD_ID.id("adult"), AdultPacket.TYPE);
        Registry.register(PacketTypeRegistry.INSTANCE, MOD_ID.id("name"), NamePacket.TYPE);
        Registry.register(PacketTypeRegistry.INSTANCE, MOD_ID.id("rope"), RopePacket.TYPE);
        Registry.register(PacketTypeRegistry.INSTANCE, MOD_ID.id("horse_riding"), RidingHorsePacket.TYPE);
        Registry.register(PacketTypeRegistry.INSTANCE, MOD_ID.id("jokey"), JokeyPacket.TYPE);
        Registry.register(PacketTypeRegistry.INSTANCE, MOD_ID.id("poison"), PoisonPacket.TYPE);
        Registry.register(PacketTypeRegistry.INSTANCE, MOD_ID.id("sound"), SoundPacket.TYPE);
        Registry.register(PacketTypeRegistry.INSTANCE, MOD_ID.id("particle"), ParticlePacket.TYPE);
        Registry.register(PacketTypeRegistry.INSTANCE, MOD_ID.id("custom_particle"), CustomParticlePacket.TYPE);
        Registry.register(PacketTypeRegistry.INSTANCE, MOD_ID.id("health"), HealthPacket.TYPE);
        Registry.register(PacketTypeRegistry.INSTANCE, MOD_ID.id("ask"), AskPacket.TYPE);
        Registry.register(PacketTypeRegistry.INSTANCE, MOD_ID.id("server_riding"), ServerRidingPacket.TYPE);
        Registry.register(PacketTypeRegistry.INSTANCE, MOD_ID.id("client_riding"), ClientHorsePacket.TYPE);
        Registry.register(PacketTypeRegistry.INSTANCE, MOD_ID.id("horse_fuel"), HorseFuelOpenGUIPacket.TYPE);

    }

    public static Achievement Indiana;
    public static Achievement BunnyKilla;
    public static Achievement WilfFlyingWest;
    public static Achievement RobertMaklowicz;


    @EventListener //(priority = ListenerPriority.HIGHEST)
    public void registerAchievements(AchievementRegisterEvent event) {
        Indiana = new Achievement(77, MOD_ID.id("indiana").toString(), -4, -4, mod_mocreatures.whip, Achievements.OPEN_INVENTORY).addStat();
        BunnyKilla = new Achievement(78, MOD_ID.id("bunnykilla").toString(), -5, -5, mod_mocreatures.whip, Achievements.OPEN_INVENTORY).addStat();
        //TODO: extra example achievement, make not retarded names
//        WilfFlyingWest = new Achievement(79, MOD_ID.id("wildflyingwest").toString(), -6, -6, mod_mocreatures.horsesaddle, Achievements.OPEN_INVENTORY).method_1041();
//        RobertMaklowicz = new Achievement(80, MOD_ID.id("robertmaklowicz").toString(), -7, -7, BlockBase.FLOWING_WATER, Achievements.OPEN_INVENTORY).method_1041();

        BunnyKilla.challenge();
        Indiana.challenge();



        event.achievements.add(BunnyKilla);
        event.achievements.add(Indiana);

        Achievements.initialize();
//        WilfFlyingWest.setUnusual();
//        RobertMaklowicz.setUnusual();

        /* A few options below for adding achievements */
//        event.achievements.add(Indiana);
//        event.achievements.add(BunnyKilla);

//        AchievementPage achievementPage = new MoCreaturesAchievementPage(MOD_ID.id("mocreaturesAchievements"));
//        event.achievements.addAll(MoCreaturesAchievementPage.ACHIEVEMENTS);
//        achievementPage.addAchievements(MoCreaturesAchievementPage.ACHIEVEMENTS.toArray(Achievement[]::new));
//        MoCreaturesAchievementPage.ACHIEVEMENTS.forEach(Stat::addStat);
    }

    @EventListener
    public void registerItems(ItemRegistryEvent event){
        horsesaddle = new HorseSaddle(Identifier.of(MOD_ID, "horsesaddle")).setTranslationKey(MOD_ID, "horsesaddle");
        haystack = new HayStack(Identifier.of(MOD_ID, "haystack")).setTranslationKey(MOD_ID, "haystack");
        sugarlump = new SugarLump(Identifier.of(MOD_ID, "sugarlump")).setTranslationKey(MOD_ID, "sugarlump");
        sharkteeth = new TemplateItem(Identifier.of(MOD_ID, "sharkteeth")).setTranslationKey(MOD_ID, "sharkteeth");
        sharkegg = new ItemSharkEgg(Identifier.of(MOD_ID, "sharkegg")).setTranslationKey(MOD_ID, "sharkegg");
        fishyegg = new ItemFishyEgg(Identifier.of(MOD_ID, "fishyegg")).setTranslationKey(MOD_ID, "fishyegg");
        bigcatclaw = new TemplateItem(Identifier.of(MOD_ID, "bigcatclaw")).setTranslationKey(MOD_ID, "bigcatclaw");
        whip = new ItemWhip(Identifier.of(MOD_ID, "whip")).setTranslationKey(MOD_ID, "whip");
        medallion = new TemplateItem(Identifier.of(MOD_ID, "medallion")).setTranslationKey(MOD_ID, "medallion");
        kittybed = new ItemKittyBed(Identifier.of(MOD_ID, "kittybed")).setTranslationKey(MOD_ID, "kittybed");
        litterbox = new ItemLitterBox(Identifier.of(MOD_ID, "litterbox")).setTranslationKey(MOD_ID, "litterbox");
        woolball = new TemplateItem(Identifier.of(MOD_ID, "woolball")).setTranslationKey(MOD_ID, "woolball");
        rope = new TemplateItem(Identifier.of(MOD_ID, "rope")).setTranslationKey(MOD_ID, "rope");
        petfood = new TemplateItem(Identifier.of(MOD_ID, "petfood")).setTranslationKey(MOD_ID, "petfood");
        greenapple = new GreenApple(Identifier.of(MOD_ID, "greenapple")).setTranslationKey(MOD_ID, "greenapple");
        elephanttusk = new TemplateItem(Identifier.of(MOD_ID, "elephanttusk")).setTranslationKey(MOD_ID, "elephanttusk").setMaxCount(8);
        megalodonteeth = new TemplateItem(Identifier.of(MOD_ID, "megalodonteeth")).setTranslationKey(MOD_ID, "megalodonteeth").setMaxCount(16);
        polarleather = new TemplateItem(Identifier.of(MOD_ID, "polarleather")).setTranslationKey(MOD_ID, "polarleather");
//        baobabfruit = new FruitBaobab(Identifier.of(MOD_ID, "baobabfruit")).setTranslationKey(MOD_ID, "baobabfruit");

        crochide = new FruitBaobab(Identifier.of(MOD_ID, "crochide")).setTranslationKey(MOD_ID, "crochide");
        helmetcroc = new CrocHideArmorItem(MOD_ID.id("helmetcroc"), 0).setTranslationKey(MOD_ID, "helmetcroc");
        platecroc = new CrocHideArmorItem(MOD_ID.id("platecroc"), 1).setTranslationKey(MOD_ID, "platecroc");
        legscroc = new CrocHideArmorItem(MOD_ID.id("legscroc"), 2).setTranslationKey(MOD_ID, "legscroc");
        bootscroc = new CrocHideArmorItem(MOD_ID.id("bootscroc"), 3).setTranslationKey(MOD_ID, "bootscroc");


//            wildleather = new TemplateItem(Identifier.of(MOD_ID, "wildleather")).setTranslationKey(MOD_ID, "wildleather");

    }

    @EventListener
    public void registerRecipes(RecipeRegisterEvent event) {

        CraftingRegistry.addShapedRecipe(new ItemStack(mod_mocreatures.kittybed, 1, 14), "###", "#X#", "Z  ", '#', Block.PLANKS, 'X', new ItemStack(Block.WOOL, 1, 1), 'Z', Item.IRON_INGOT);
        CraftingRegistry.addShapedRecipe(new ItemStack(mod_mocreatures.kittybed, 1, 13), "###", "#X#", "Z  ", '#', Block.PLANKS, 'X', new ItemStack(Block.WOOL, 1, 2), 'Z', Item.IRON_INGOT);
        CraftingRegistry.addShapedRecipe(new ItemStack(mod_mocreatures.kittybed, 1, 12), "###", "#X#", "Z  ", '#', Block.PLANKS, 'X', new ItemStack(Block.WOOL, 1, 3), 'Z', Item.IRON_INGOT);
        CraftingRegistry.addShapedRecipe(new ItemStack(mod_mocreatures.kittybed, 1, 11), "###", "#X#", "Z  ", '#', Block.PLANKS, 'X', new ItemStack(Block.WOOL, 1, 4), 'Z', Item.IRON_INGOT);
        CraftingRegistry.addShapedRecipe(new ItemStack(mod_mocreatures.kittybed, 1, 10), "###", "#X#", "Z  ", '#', Block.PLANKS, 'X', new ItemStack(Block.WOOL, 1, 5), 'Z', Item.IRON_INGOT);
        CraftingRegistry.addShapedRecipe(new ItemStack(mod_mocreatures.kittybed, 1, 9), "###", "#X#", "Z  ", '#', Block.PLANKS, 'X', new ItemStack(Block.WOOL, 1, 6), 'Z', Item.IRON_INGOT);
        CraftingRegistry.addShapedRecipe(new ItemStack(mod_mocreatures.kittybed, 1, 8), "###", "#X#", "Z  ", '#', Block.PLANKS, 'X', new ItemStack(Block.WOOL, 1, 7), 'Z', Item.IRON_INGOT);
        CraftingRegistry.addShapedRecipe(new ItemStack(mod_mocreatures.kittybed, 1, 7), "###", "#X#", "Z  ", '#', Block.PLANKS, 'X', new ItemStack(Block.WOOL, 1, 8), 'Z', Item.IRON_INGOT);
        CraftingRegistry.addShapedRecipe(new ItemStack(mod_mocreatures.kittybed, 1, 6), "###", "#X#", "Z  ", '#', Block.PLANKS, 'X', new ItemStack(Block.WOOL, 1, 9), 'Z', Item.IRON_INGOT);
        CraftingRegistry.addShapedRecipe(new ItemStack(mod_mocreatures.kittybed, 1, 5), "###", "#X#", "Z  ", '#', Block.PLANKS, 'X', new ItemStack(Block.WOOL, 1, 10), 'Z', Item.IRON_INGOT);
        CraftingRegistry.addShapedRecipe(new ItemStack(mod_mocreatures.kittybed, 1, 4), "###", "#X#", "Z  ", '#', Block.PLANKS, 'X', new ItemStack(Block.WOOL, 1, 11), 'Z', Item.IRON_INGOT);
        CraftingRegistry.addShapedRecipe(new ItemStack(mod_mocreatures.kittybed, 1, 3), "###", "#X#", "Z  ", '#', Block.PLANKS, 'X', new ItemStack(Block.WOOL, 1, 12), 'Z', Item.IRON_INGOT);
        CraftingRegistry.addShapedRecipe(new ItemStack(mod_mocreatures.kittybed, 1, 2), "###", "#X#", "Z  ", '#', Block.PLANKS, 'X', new ItemStack(Block.WOOL, 1, 13), 'Z', Item.IRON_INGOT);
        CraftingRegistry.addShapedRecipe(new ItemStack(mod_mocreatures.kittybed, 1, 1), "###", "#X#", "Z  ", '#', Block.PLANKS, 'X', new ItemStack(Block.WOOL, 1, 14), 'Z', Item.IRON_INGOT);
        CraftingRegistry.addShapedRecipe(new ItemStack(mod_mocreatures.kittybed, 1, 0), "###", "#X#", "Z  ", '#', Block.PLANKS, 'X', new ItemStack(Block.WOOL, 1, 15), 'Z', Item.IRON_INGOT);
        CraftingRegistry.addShapedRecipe(new ItemStack(mod_mocreatures.kittybed, 1, 15), "###", "#X#", "Z  ", '#', Block.PLANKS, 'X', new ItemStack(Block.WOOL, 1, 0), 'Z', Item.IRON_INGOT);

        CraftingRegistry.addShapedRecipe(new ItemStack(rope, 1), "# #", " # ", "# #", '#', Item.STRING);
        CraftingRegistry.addShapelessRecipe(new ItemStack(petfood, 4), new ItemStack(Item.RAW_FISH, 1), new ItemStack(Item.RAW_PORKCHOP, 1));
        CraftingRegistry.addShapedRecipe(new ItemStack(woolball, 1), " # ", "# #", " # ", '#', Item.STRING);
        CraftingRegistry.addShapedRecipe(new ItemStack(litterbox, 1), "###", "#X#", "###", '#', Block.PLANKS, 'X', Block.SAND);
        CraftingRegistry.addShapedRecipe(new ItemStack(whip, 1), "#X#", "X X", "# Z", '#', bigcatclaw, 'X', Item.LEATHER, 'Z', Item.IRON_INGOT);
        CraftingRegistry.addShapedRecipe(new ItemStack(haystack, 1), "XXX", "XXX", 'X', Item.WHEAT);
        CraftingRegistry.addShapedRecipe(new ItemStack(Item.WHEAT, 6), "X", 'X', haystack);
        CraftingRegistry.addShapedRecipe(new ItemStack(sugarlump, 1), "XX", "##", 'X', Item.SUGAR, '#', Item.SUGAR);
        CraftingRegistry.addShapedRecipe(new ItemStack(Item.CHAIN_CHESTPLATE, 1), "X X", "XXX", "XXX", 'X', sharkteeth);
        CraftingRegistry.addShapedRecipe(new ItemStack(Item.CHAIN_HELMET, 1), "XXX", "X X", 'X', sharkteeth);
        CraftingRegistry.addShapedRecipe(new ItemStack(Item.CHAIN_LEGGINGS, 1), "XXX", "X X", "X X", 'X', sharkteeth);
        CraftingRegistry.addShapedRecipe(new ItemStack(Item.CHAIN_BOOTS, 1), "X X", "X X", 'X', sharkteeth);

        CraftingRegistry.addShapedRecipe(new ItemStack(horsesaddle, 1), "X", "#", 'X', Item.SADDLE, '#', Item.IRON_INGOT);
        if(mocreaturesGlass.balancesettings.easy_saddle_recipe) {
            CraftingRegistry.addShapedRecipe(new ItemStack(horsesaddle, 1), "XXX", "X#X", "# #", '#', Item.IRON_INGOT, 'X', Item.LEATHER);
        }
        CraftingRegistry.addShapedRecipe(new ItemStack(medallion, 1), "# #", "XZX", " X ", '#', Item.LEATHER, 'Z', Item.DIAMOND, 'X', Item.GOLD_INGOT);
        if(mocreaturesGlass.balancesettings.easy_medallion_recipe) {
            CraftingRegistry.addShapedRecipe(new ItemStack(medallion, 1), "# #", " X ", '#', Item.LEATHER, 'X', Item.GOLD_INGOT);
        }

        CraftingRegistry.addShapedRecipe(new ItemStack(helmetcroc, 1), "XXX", "X X", 'X', crochide);
        CraftingRegistry.addShapedRecipe(new ItemStack(platecroc, 1), "X X", "XXX", "XXX", 'X', crochide);
        CraftingRegistry.addShapedRecipe(new ItemStack(legscroc, 1), "XXX", "X X", "X X", 'X', crochide);
        CraftingRegistry.addShapedRecipe(new ItemStack(bootscroc, 1), "X X", "X X", 'X', crochide);


//            for (int j = 0; j < 16; j++) {
//                CraftingRegistry.addShapelessRecipe(new ItemInstance(BlockBase.WOOL, 8, 15 - j), new ItemInstance(ItemBase.dyePowder, 1, j), new ItemInstance(wildleather, 1));
//            }



    }

    public static int colorize(int i)
    {
        return ~i & 0xf;
    }

    public static Item horsesaddle;
    public static Item haystack;
    public static Item sugarlump;
    public static Item sharkteeth;
    public static Item sharkegg;
    public static Item fishyegg;
    public static Item bigcatclaw;
    public static Item whip;
    public static Item medallion;
    public static Item litterbox;
    public static Item woolball;
    public static Item rope;
    public static Item petfood ;
    public static Item kittybed;

    public static Item polarleather;
    public static Item greenapple;
    public static Item elephanttusk;
    public static Item megalodonteeth;
//    public static Item baobabfruit;

    public static Item crochide;
    public static Item platecroc;
    public static Item helmetcroc;
    public static Item legscroc;
    public static Item bootscroc;

}
