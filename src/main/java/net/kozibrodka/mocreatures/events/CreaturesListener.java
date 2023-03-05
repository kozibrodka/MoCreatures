package net.kozibrodka.mocreatures.events;



import net.kozibrodka.mocreatures.entity.*;
import net.kozibrodka.mocreatures.modelentity.*;
import net.kozibrodka.mocreatures.renderentity.*;
import net.mine_diver.unsafeevents.listener.EventListener;

import net.minecraft.client.render.entity.BipedEntityRenderer;
import net.minecraft.client.render.entity.ChickenRenderer;
import net.minecraft.client.render.entity.PigRenderer;
import net.minecraft.client.render.entity.model.Chicken;
import net.minecraft.client.render.entity.model.Pig;
import net.modificationstation.stationapi.api.client.event.render.entity.EntityRendererRegisterEvent;
import net.modificationstation.stationapi.api.event.entity.EntityRegister;
import net.modificationstation.stationapi.api.event.registry.MobHandlerRegistryEvent;
import net.modificationstation.stationapi.api.mod.entrypoint.Entrypoint;
import net.modificationstation.stationapi.api.registry.Identifier;
import net.modificationstation.stationapi.api.registry.ModID;
import net.modificationstation.stationapi.api.registry.Registry;
import net.modificationstation.stationapi.api.util.Null;

public class CreaturesListener {
    @Entrypoint.ModID
    public static final ModID MOD_ID = Null.get();

    @EventListener
    private static void registerEntities(EntityRegister event) {
        event.register(EntityBear.class, String.valueOf(Identifier.of(MOD_ID, "Bear")));
        event.register(EntityBigCat.class, String.valueOf(Identifier.of(MOD_ID, "BigCat")));
        event.register(EntityBird.class, String.valueOf(Identifier.of(MOD_ID, "Bird")));
        event.register(EntityBoar.class, String.valueOf(Identifier.of(MOD_ID, "Boar")));
        event.register(EntityBunny.class, String.valueOf(Identifier.of(MOD_ID, "Bunny")));
        event.register(EntityCaveOgre.class, String.valueOf(Identifier.of(MOD_ID, "CaveOgre")));
        event.register(EntityDeer.class, String.valueOf(Identifier.of(MOD_ID, "Deer")));
        event.register(EntityDolphin.class, String.valueOf(Identifier.of(MOD_ID, "Dolphin")));
        event.register(EntityDuck.class, String.valueOf(Identifier.of(MOD_ID, "Duck")));
        event.register(EntityFireOgre.class, String.valueOf(Identifier.of(MOD_ID, "FireOgre")));
        event.register(EntityFishy.class, String.valueOf(Identifier.of(MOD_ID, "Fishy")));
        event.register(EntityFishyEgg.class, String.valueOf(Identifier.of(MOD_ID, "FishyEgg")));
        event.register(EntityFlameWraith.class, String.valueOf(Identifier.of(MOD_ID, "FlameWraith")));
        event.register(EntityFox.class, String.valueOf(Identifier.of(MOD_ID, "Fox")));
        event.register(EntityHellRat.class, String.valueOf(Identifier.of(MOD_ID, "HellRat")));
        event.register(EntityHorse.class, String.valueOf(Identifier.of(MOD_ID, "Horse")));
        event.register(EntityKitty.class, String.valueOf(Identifier.of(MOD_ID, "Kitty")));
        event.register(EntityKittyBed.class, String.valueOf(Identifier.of(MOD_ID, "KittyBed")));
        event.register(EntityLitterBox.class, String.valueOf(Identifier.of(MOD_ID, "LitterBox")));
        event.register(EntityMouse.class, String.valueOf(Identifier.of(MOD_ID, "Mouse")));
        event.register(EntityOgre.class, String.valueOf(Identifier.of(MOD_ID, "Ogre")));
        event.register(EntityPolarBear.class, String.valueOf(Identifier.of(MOD_ID, "PolarBear")));
        event.register(EntityRat.class, String.valueOf(Identifier.of(MOD_ID, "Rat")));
        event.register(EntityShark.class, String.valueOf(Identifier.of(MOD_ID, "Shark")));
        event.register(EntitySharkEgg.class, String.valueOf(Identifier.of(MOD_ID, "SharkEgg")));
        event.register(EntityWerewolf.class, String.valueOf(Identifier.of(MOD_ID, "WereWolf")));
        event.register(EntityWraith.class, String.valueOf(Identifier.of(MOD_ID, "Wraith")));
        event.register(EntityWWolf.class, String.valueOf(Identifier.of(MOD_ID, "WildWolf")));
    }

    @EventListener
    private static void registerMobHandlers(MobHandlerRegistryEvent event) {
        Registry.register(event.registry, MOD_ID.id("Bear"), EntityBear::new);
        Registry.register(event.registry, MOD_ID.id("BigCat"), EntityBigCat::new);
        Registry.register(event.registry, MOD_ID.id("Bird"), EntityBird::new);
        Registry.register(event.registry, MOD_ID.id("Boar"), EntityBoar::new);
        Registry.register(event.registry, MOD_ID.id("Bunny"), EntityBunny::new);
        Registry.register(event.registry, MOD_ID.id("CaveOgre"), EntityCaveOgre::new);
        Registry.register(event.registry, MOD_ID.id("Deer"), EntityDeer::new);
        Registry.register(event.registry, MOD_ID.id("Dolphin"), EntityDolphin::new);
        Registry.register(event.registry, MOD_ID.id("Duck"), EntityDuck::new);
        Registry.register(event.registry, MOD_ID.id("FireOgre"), EntityFireOgre::new);
        Registry.register(event.registry, MOD_ID.id("Fishy"), EntityFishy::new);
        Registry.register(event.registry, MOD_ID.id("FishyEgg"), EntityFishyEgg::new);
        Registry.register(event.registry, MOD_ID.id("FlameWraith"), EntityFlameWraith::new);
        Registry.register(event.registry, MOD_ID.id("Fox"), EntityFox::new);
        Registry.register(event.registry, MOD_ID.id("HellRat"), EntityHellRat::new);
        Registry.register(event.registry, MOD_ID.id("Horse"), EntityHorse::new);
        Registry.register(event.registry, MOD_ID.id("Kitty"), EntityKitty::new);
        Registry.register(event.registry, MOD_ID.id("KittyBed"), EntityKittyBed::new);
        Registry.register(event.registry, MOD_ID.id("LitterBox"), EntityLitterBox::new);
        Registry.register(event.registry, MOD_ID.id("Mouse"), EntityMouse::new);
        Registry.register(event.registry, MOD_ID.id("Ogre"), EntityOgre::new);
        Registry.register(event.registry, MOD_ID.id("PolarBear"), EntityPolarBear::new);
        Registry.register(event.registry, MOD_ID.id("Rat"), EntityRat::new);
        Registry.register(event.registry, MOD_ID.id("Shark"), EntityShark::new);
        Registry.register(event.registry, MOD_ID.id("SharkEgg"), EntitySharkEgg::new);
        Registry.register(event.registry, MOD_ID.id("WereWolf"), EntityWerewolf::new);
        Registry.register(event.registry, MOD_ID.id("Wraith"), EntityWraith::new);
        Registry.register(event.registry, MOD_ID.id("WildWolf"), EntityWWolf::new);
    }

    @EventListener
    private static void registerEntityRenderers(EntityRendererRegisterEvent event) {
        event.renderers.put(EntityHorse.class, new RenderHorse(new ModelHorse2(), new ModelHorse1()));
        event.renderers.put(EntityFireOgre.class, new RenderFireOgre(new ModelOgre2(), new ModelOgre1(), 1.5F));
        event.renderers.put(EntityCaveOgre.class, new RenderCaveOgre(new ModelOgre2(), new ModelOgre1(), 1.5F));
        event.renderers.put(EntityOgre.class, new RenderOgre(new ModelOgre2(), new ModelOgre1(), 1.5F));
        event.renderers.put(EntityBoar.class, new PigRenderer(new Pig(), new Pig(0.5F), 0.7F));
        event.renderers.put(EntityBear.class, new RenderBear(new ModelBear2(), new ModelBear1(), 0.7F));
        event.renderers.put(EntityDuck.class, new ChickenRenderer(new Chicken(), 0.3F));
        event.renderers.put(EntityBigCat.class, new RenderBigCat(new ModelBigCat2(), new ModelBigCat1(), 0.7F));
        event.renderers.put(EntityDeer.class, new RenderDeer(new ModelDeer(), 0.5F));
        event.renderers.put(EntityWWolf.class, new RenderWWolf(new ModelWolf2(), new ModelWolf1(), 0.7F));
        event.renderers.put(EntityPolarBear.class, new RenderPolarBear(new ModelBear2(), new ModelBear1(), 0.7F));
        event.renderers.put(EntityWraith.class, new BipedEntityRenderer(new ModelWraith(), 0.5F));
        event.renderers.put(EntityFlameWraith.class, new BipedEntityRenderer(new ModelWraith(), 0.5F));
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
    }

}
