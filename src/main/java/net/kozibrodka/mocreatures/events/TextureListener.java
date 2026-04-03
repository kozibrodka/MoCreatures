package net.kozibrodka.mocreatures.events;

import net.fabricmc.loader.api.FabricLoader;
import net.kozibrodka.mocreatures.entity.*;
import net.kozibrodka.mocreatures.modelentity.*;
import net.kozibrodka.mocreatures.renderentity.*;
import net.mine_diver.unsafeevents.listener.EventListener;
import net.minecraft.client.Minecraft;
import net.minecraft.client.render.entity.*;
import net.minecraft.client.render.entity.model.*;
import net.modificationstation.stationapi.api.client.event.render.entity.EntityRendererRegisterEvent;
import net.modificationstation.stationapi.api.client.event.texture.TextureRegisterEvent;
import net.modificationstation.stationapi.api.client.texture.atlas.Atlases;
import net.modificationstation.stationapi.api.mod.entrypoint.Entrypoint;
import net.modificationstation.stationapi.api.mod.entrypoint.EntrypointManager;
import net.modificationstation.stationapi.api.util.Identifier;
import net.modificationstation.stationapi.api.util.Namespace;
import net.modificationstation.stationapi.api.util.Null;

import java.lang.invoke.MethodHandles;

public class TextureListener {
    static {
        EntrypointManager.registerLookup(MethodHandles.lookup());
    }

    @Entrypoint.Namespace
    public static Namespace MOD_ID = Null.get();

    public static int bubble_particle;
    public static int grass_particle;
    public static int bush_particle;

    @EventListener
    public void registerTextures(TextureRegisterEvent event) {
        mod_mocreatures.horsesaddle.setTexture(Identifier.of(MOD_ID, "item/horsesaddle"));
        mod_mocreatures.haystack.setTexture(Identifier.of(MOD_ID, "item/haystack"));
        mod_mocreatures.sugarlump.setTexture(Identifier.of(MOD_ID, "item/sugarlump"));
        mod_mocreatures.sharkteeth.setTexture(Identifier.of(MOD_ID, "item/sharkteeth"));
        mod_mocreatures.sharkegg.setTexture(Identifier.of(MOD_ID, "item/sharkegg"));
        mod_mocreatures.fishyegg.setTexture(Identifier.of(MOD_ID, "item/fishyegg"));
        mod_mocreatures.bigcatclaw.setTexture(Identifier.of(MOD_ID, "item/bigcatclaw"));
        mod_mocreatures.whip.setTexture(Identifier.of(MOD_ID, "item/whip"));
        mod_mocreatures.medallion.setTexture(Identifier.of(MOD_ID, "item/medallion"));
        mod_mocreatures.litterbox.setTexture(Identifier.of(MOD_ID, "item/boxlitter"));
        mod_mocreatures.woolball.setTexture(Identifier.of(MOD_ID, "item/woolball"));
        mod_mocreatures.rope.setTexture(Identifier.of(MOD_ID, "item/rope"));
        mod_mocreatures.petfood.setTexture(Identifier.of(MOD_ID, "item/petfood"));
        mod_mocreatures.kittybed.setTexture(Identifier.of(MOD_ID, "item/bedkitty"));
        mod_mocreatures.greenapple.setTexture(Identifier.of(MOD_ID, "item/greenapple"));
        mod_mocreatures.elephanttusk.setTexture(Identifier.of(MOD_ID, "item/tusk"));
        mod_mocreatures.megalodonteeth.setTexture(Identifier.of(MOD_ID, "item/megateeth"));
        mod_mocreatures.polarleather.setTexture(Identifier.of(MOD_ID, "item/polarleather"));
        mod_mocreatures.sheepbell.setTexture(Identifier.of(MOD_ID, "item/bell"));
//        mod_mocreatures.baobabfruit.setTexture(Identifier.of(MOD_ID, "item/baobab"));

        mod_mocreatures.crochide.setTexture(Identifier.of(MOD_ID, "item/crochide"));
        mod_mocreatures.helmetcroc.setTexture(Identifier.of(MOD_ID, "item/crochelmet"));
        mod_mocreatures.platecroc.setTexture(Identifier.of(MOD_ID, "item/crocplate"));
        mod_mocreatures.legscroc.setTexture(Identifier.of(MOD_ID, "item/croclegs"));
        mod_mocreatures.bootscroc.setTexture(Identifier.of(MOD_ID, "item/crocboots"));




//            mod_mocreatures.wildleather.setTexture(Identifier.of(MOD_ID, "item/wildleather"));


        if(mod_mocreatures.mocGlass.othersettings.spawn_eggs) {
            EggListener.egg1.setTexture(Identifier.of(MOD_ID, "item/egg/bear"));
            EggListener.egg2.setTexture(Identifier.of(MOD_ID, "item/egg/bigcat"));
            EggListener.egg3.setTexture(Identifier.of(MOD_ID, "item/egg/bird"));
            EggListener.egg4.setTexture(Identifier.of(MOD_ID, "item/egg/boar"));
            EggListener.egg5.setTexture(Identifier.of(MOD_ID, "item/egg/bunny"));
            EggListener.egg6.setTexture(Identifier.of(MOD_ID, "item/egg/caveogre"));
            EggListener.egg7.setTexture(Identifier.of(MOD_ID, "item/egg/deer"));
            EggListener.egg8.setTexture(Identifier.of(MOD_ID, "item/egg/dolphin"));
            EggListener.egg9.setTexture(Identifier.of(MOD_ID, "item/egg/duck"));
            EggListener.egg10.setTexture(Identifier.of(MOD_ID, "item/egg/fireogre"));
            EggListener.egg11.setTexture(Identifier.of(MOD_ID, "item/egg/fishy"));
            EggListener.egg13.setTexture(Identifier.of(MOD_ID, "item/egg/flamewraith"));
            EggListener.egg14.setTexture(Identifier.of(MOD_ID, "item/egg/fox"));
            EggListener.egg15.setTexture(Identifier.of(MOD_ID, "item/egg/hellrat"));
            EggListener.egg16.setTexture(Identifier.of(MOD_ID, "item/egg/horse"));
            EggListener.egg17.setTexture(Identifier.of(MOD_ID, "item/egg/kitty"));
            EggListener.egg20.setTexture(Identifier.of(MOD_ID, "item/egg/mouse"));
            EggListener.egg21.setTexture(Identifier.of(MOD_ID, "item/egg/ogre"));
            EggListener.egg22.setTexture(Identifier.of(MOD_ID, "item/egg/polarbear"));
            EggListener.egg23.setTexture(Identifier.of(MOD_ID, "item/egg/rat"));
            EggListener.egg24.setTexture(Identifier.of(MOD_ID, "item/egg/shark"));
            EggListener.egg26.setTexture(Identifier.of(MOD_ID, "item/egg/werewolf"));
            EggListener.egg27.setTexture(Identifier.of(MOD_ID, "item/egg/wraith"));
            EggListener.egg28.setTexture(Identifier.of(MOD_ID, "item/egg/wildwolf"));

            EggListener.egg30.setTexture(Identifier.of(MOD_ID, "item/egg/horse"));
            EggListener.egg31.setTexture(Identifier.of(MOD_ID, "item/egg/horse1"));
            EggListener.egg32.setTexture(Identifier.of(MOD_ID, "item/egg/horse2"));
            EggListener.egg33.setTexture(Identifier.of(MOD_ID, "item/egg/horse3"));
            EggListener.egg34.setTexture(Identifier.of(MOD_ID, "item/egg/horse4"));
            EggListener.egg35.setTexture(Identifier.of(MOD_ID, "item/egg/horse5"));
            EggListener.egg36.setTexture(Identifier.of(MOD_ID, "item/egg/horse6"));
            EggListener.egg37.setTexture(Identifier.of(MOD_ID, "item/egg/horse7"));

            EggListener.egg40.setTexture(Identifier.of(MOD_ID, "item/egg/MobFemaleLion"));
            EggListener.egg41.setTexture(Identifier.of(MOD_ID, "item/egg/MobMaleLion"));
            EggListener.egg42.setTexture(Identifier.of(MOD_ID, "item/egg/MobPanther"));
            EggListener.egg43.setTexture(Identifier.of(MOD_ID, "item/egg/MobCheetah"));
            EggListener.egg44.setTexture(Identifier.of(MOD_ID, "item/egg/MobTiger"));
            EggListener.egg45.setTexture(Identifier.of(MOD_ID, "item/egg/MobSnowLeopard"));
            EggListener.egg46.setTexture(Identifier.of(MOD_ID, "item/egg/MobWhiteTiger"));

            EggListener.egg50.setTexture(Identifier.of(MOD_ID, "item/egg/Blue_dolphin"));
            EggListener.egg51.setTexture(Identifier.of(MOD_ID, "item/egg/Green_dolphin"));
            EggListener.egg52.setTexture(Identifier.of(MOD_ID, "item/egg/Purple_dolphin"));
            EggListener.egg53.setTexture(Identifier.of(MOD_ID, "item/egg/Dark_dolphin"));
            EggListener.egg54.setTexture(Identifier.of(MOD_ID, "item/egg/Pink_dolphin"));
            EggListener.egg55.setTexture(Identifier.of(MOD_ID, "item/egg/Albino_dolphin"));

            EggListener.egg60.setTexture(Identifier.of(MOD_ID, "item/egg/zebra"));
            EggListener.egg61.setTexture(Identifier.of(MOD_ID, "item/egg/elephant"));
            EggListener.egg62.setTexture(Identifier.of(MOD_ID, "item/egg/hippo"));
            EggListener.egg63.setTexture(Identifier.of(MOD_ID, "item/egg/giraffe"));
            EggListener.egg64.setTexture(Identifier.of(MOD_ID, "item/egg/crocodile"));
            EggListener.egg65.setTexture(Identifier.of(MOD_ID, "item/egg/camel"));
            EggListener.egg66.setTexture(Identifier.of(MOD_ID, "item/egg/mummy"));
            EggListener.egg67.setTexture(Identifier.of(MOD_ID, "item/egg/scorpion"));
            EggListener.egg68.setTexture(Identifier.of(MOD_ID, "item/egg/turtle"));
            EggListener.egg69.setTexture(Identifier.of(MOD_ID, "item/egg/ray"));
            EggListener.egg70.setTexture(Identifier.of(MOD_ID, "item/egg/collie"));
            EggListener.egg71.setTexture(Identifier.of(MOD_ID, "item/egg/sheep"));
            EggListener.egg72.setTexture(Identifier.of(MOD_ID, "item/egg/squid"));
//            EggListener.egg68.setTexture(Identifier.of(MOD_ID, "item/egg/XXXXXX"));
        }

//        bubble_particle = registerItemTexture("item/bubble");
        // Particles
        if (FabricLoader.getInstance().getGameInstance() instanceof Minecraft minecraft) {
            bubble_particle = minecraft.textureManager.getTextureId("/assets/mocreatures/stationapi/textures/particle/bubble.png");
            grass_particle = minecraft.textureManager.getTextureId("/assets/mocreatures/stationapi/textures/particle/grass.png");
            bush_particle = minecraft.textureManager.getTextureId("/assets/mocreatures/stationapi/textures/particle/bush.png");
        }

    }

    @EventListener
    private static void registerEntityRenderers(EntityRendererRegisterEvent event) {
        event.renderers.put(EntityHorse.class, new RenderHorse(new ModelHorse2(), new ModelHorse1()));
        event.renderers.put(EntityFireOgre.class, new RenderFireOgre(new ModelOgre2(), new ModelOgre1(), 1.5F));
        event.renderers.put(EntityCaveOgre.class, new RenderCaveOgre(new ModelOgre2(), new ModelOgre1(), 1.5F));
        event.renderers.put(EntityOgre.class, new RenderOgre(new ModelOgre2(), new ModelOgre1(), 1.5F));
        event.renderers.put(EntityBoar.class, new PigEntityRenderer(new PigEntityModel(), new PigEntityModel(0.5F), 0.7F));
        event.renderers.put(EntityBear.class, new RenderBear(new ModelBear2(), new ModelBear1(), 0.7F));
        event.renderers.put(EntityDuck.class, new ChickenEntityRenderer(new ChickenEntityModel(), 0.3F));
        event.renderers.put(EntityBigCat.class, new RenderBigCat(new ModelBigCat2(), new ModelBigCat1(), 0.7F));
        event.renderers.put(EntityDeer.class, new RenderDeer(new ModelDeer(), 0.5F));
        event.renderers.put(EntityWWolf.class, new RenderWWolf(new ModelWolf2(), new ModelWolf1(), 0.7F));
        event.renderers.put(EntityPolarBear.class, new RenderPolarBear(new ModelBear2(), new ModelBear1(), 0.7F));
        event.renderers.put(EntityWraith.class, new UndeadEntityRenderer(new ModelWraith(), 0.5F));
        event.renderers.put(EntityFlameWraith.class, new UndeadEntityRenderer(new ModelWraith(), 0.5F));
        event.renderers.put(EntityBunny.class, new RenderBunny(new ModelBunny(), 0.3F));
        event.renderers.put(EntityBird.class, new RenderBird(new ModelBird(), 0.3F));
        event.renderers.put(EntityFox.class, new RenderFox(new ModelFox()));
        event.renderers.put(EntityWerewolf.class, new RenderWerewolf(new ModelWereHuman(), new ModelWerewolf(), 0.7F));
        event.renderers.put(EntityShark.class, new RenderShark(new ModelShark(), 0.6F));
        event.renderers.put(EntitySharkEgg.class, new RenderSharkEgg(new ModelSharkEgg(), 0.1F));
        event.renderers.put(EntityDolphin.class, new RenderDolphin(new ModelDolphin(), 0.6F));
        event.renderers.put(EntityFishy.class, new RenderFishy(new ModelFishy(), 0.2F));
        event.renderers.put(EntityFishyEgg.class, new RenderFishyEgg(new ModelSharkEgg(), 0.1F));
        event.renderers.put(EntityKitty.class, new RenderKitty(new ModelKitty(0.0F, 15F), 0.4F));
        event.renderers.put(EntityKittyBed.class, new RenderKittyBed(new ModelKittyBed(), new ModelKittyBed2(), 0.3F));
        event.renderers.put(EntityLitterBox.class, new RenderLitterBox(new ModelLitterBox(), 0.3F));
        event.renderers.put(EntityRat.class, new RenderRat(new ModelRat(), 0.2F));
        event.renderers.put(EntityMouse.class, new RenderMouse(new ModelMouse(), 0.1F));
        event.renderers.put(EntityHellRat.class, new RenderHellRat(new ModelRat(), 0.4F));

        event.renderers.put(EntityZebra.class, new RenderZebra(new ModelZebra(), 0.5F));
        event.renderers.put(EntityHippo.class, new RenderHippo(new ModelHippo(), 0.7F));
        event.renderers.put(EntityElephant.class, new RenderElephant(new ModelElephant(), 0.7F, 2.8F));
        event.renderers.put(EntityGiraffe.class, new RenderGiraffe(new ModelGiraffe(), 0.5F));
        event.renderers.put(EntityCamel.class, new RenderCamel(new ModelCamel(), 0.5F));
        event.renderers.put(EntityMummy.class, new UndeadEntityRenderer(new ZombieEntityModel(), 0.5F));
        event.renderers.put(EntityCrocodile.class, new RenderCrocodile(new ModelCrocodile(), 0.5F));
        event.renderers.put(EntityScorpion.class, new RenderScorpion(new ModelScorpion(), 0.6F));
        event.renderers.put(EntityTurtle.class, new RenderTurtle(new ModelTurtle(), 0.4F));
        event.renderers.put(EntityRay.class, new RenderRay(new ModelRay(), 0.4F));
        event.renderers.put(EntityCollie.class, new RenderCollie(new ModelCollie(), 0.5F));
//        event.renderers.put(EntitySheep.class, new SheepEntityRenderer(new SheepEntityModel(), new SheepWoolEntityModel(), 0.7F));
        event.renderers.put(EntitySheep.class, new RenderSheep(new ModelSheep(), new SheepWoolEntityModel(), 0.7F));

        event.renderers.put(EntityAirShipHorse.class, new RenderHorse(new ModelHorse2(), new ModelHorse1()));
    }

    private int registerItemTexture(String s) {
        if (s == null) {
            return 0;
        }
        return Atlases.getGuiItems().addTexture(Identifier.of(MOD_ID, s)).index;
    }


    public int getItemTexture(String path) {
        return Atlases.getGuiItems().addTexture(Identifier.of(MOD_ID, path)).index;
    }
}
