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
        event.registry.register(Identifier.of(MOD_ID, "Bear"), EntityBear::new);
        event.registry.register(Identifier.of(MOD_ID, "BigCat"), EntityBigCat::new);
        event.registry.register(Identifier.of(MOD_ID, "Bird"), EntityBird::new);
        event.registry.register(Identifier.of(MOD_ID, "Boar"), EntityBoar::new);
        event.registry.register(Identifier.of(MOD_ID, "Bunny"), EntityBunny::new);
        event.registry.register(Identifier.of(MOD_ID, "CaveOgre"), EntityCaveOgre::new);
        event.registry.register(Identifier.of(MOD_ID, "Deer"), EntityDeer::new);
        event.registry.register(Identifier.of(MOD_ID, "Dolphin"), EntityDolphin::new);
        event.registry.register(Identifier.of(MOD_ID, "Duck"), EntityDuck::new);
        event.registry.register(Identifier.of(MOD_ID, "FireOgre"), EntityFireOgre::new);
        event.registry.register(Identifier.of(MOD_ID, "Fishy"), EntityFishy::new);
        event.registry.register(Identifier.of(MOD_ID, "FishyEgg"), EntityFishyEgg::new);
        event.registry.register(Identifier.of(MOD_ID, "FlameWraith"), EntityFlameWraith::new);
        event.registry.register(Identifier.of(MOD_ID, "Fox"), EntityFox::new);
        event.registry.register(Identifier.of(MOD_ID, "HellRat"), EntityHellRat::new);
        event.registry.register(Identifier.of(MOD_ID, "Horse"), EntityHorse::new);
        event.registry.register(Identifier.of(MOD_ID, "Kitty"), EntityKitty::new);
        event.registry.register(Identifier.of(MOD_ID, "KittyBed"), EntityKittyBed::new);
        event.registry.register(Identifier.of(MOD_ID, "LitterBox"), EntityLitterBox::new);
        event.registry.register(Identifier.of(MOD_ID, "Mouse"), EntityMouse::new);
        event.registry.register(Identifier.of(MOD_ID, "Ogre"), EntityOgre::new);
        event.registry.register(Identifier.of(MOD_ID, "PolarBear"), EntityPolarBear::new);
        event.registry.register(Identifier.of(MOD_ID, "Rat"), EntityRat::new);
        event.registry.register(Identifier.of(MOD_ID, "Shark"), EntityShark::new);
        event.registry.register(Identifier.of(MOD_ID, "SharkEgg"), EntitySharkEgg::new);
        event.registry.register(Identifier.of(MOD_ID, "WereWolf"), EntityWerewolf::new);
        event.registry.register(Identifier.of(MOD_ID, "Wraith"), EntityWraith::new);
        event.registry.register(Identifier.of(MOD_ID, "WildWolf"), EntityWWolf::new);
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
