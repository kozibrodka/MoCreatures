package net.kozibrodka.mocreatures.events;



import net.fabricmc.api.Environment;
import net.kozibrodka.mocreatures.entity.*;
import net.kozibrodka.mocreatures.modelentity.*;
import net.kozibrodka.mocreatures.renderentity.*;
import net.mine_diver.unsafeevents.listener.EventListener;
import net.modificationstation.stationapi.api.client.event.render.entity.EntityRendererRegisterEvent;
import net.modificationstation.stationapi.api.event.entity.EntityRegister;
import net.modificationstation.stationapi.api.event.registry.MobHandlerRegistryEvent;
import net.modificationstation.stationapi.api.mod.entrypoint.Entrypoint;
import net.modificationstation.stationapi.api.util.Identifier;
import net.modificationstation.stationapi.api.util.Namespace;
import net.modificationstation.stationapi.api.util.Identifier;
import net.modificationstation.stationapi.api.registry.Registry;
import net.modificationstation.stationapi.api.util.Null;

public class CreaturesListener {
    @Entrypoint.Namespace
    public static final Namespace MOD_ID = Null.get();

    @EventListener
    private static void registerEntities(EntityRegister event) {
        event.register(EntityBear.class, String.valueOf(Identifier.of(MOD_ID, "Bear")));
        event.register(EntityBird.class, String.valueOf(Identifier.of(MOD_ID, "Bird")));
        event.register(EntityBoar.class, String.valueOf(Identifier.of(MOD_ID, "Boar")));
        event.register(EntityBunny.class, String.valueOf(Identifier.of(MOD_ID, "Bunny")));
        event.register(EntityCaveOgre.class, String.valueOf(Identifier.of(MOD_ID, "CaveOgre")));
        event.register(EntityDeer.class, String.valueOf(Identifier.of(MOD_ID, "Deer")));
        event.register(EntityDuck.class, String.valueOf(Identifier.of(MOD_ID, "Duck")));
        event.register(EntityFireOgre.class, String.valueOf(Identifier.of(MOD_ID, "FireOgre")));
        event.register(EntityFishy.class, String.valueOf(Identifier.of(MOD_ID, "Fishy")));
        event.register(EntityFishyEgg.class, String.valueOf(Identifier.of(MOD_ID, "FishyEgg")));
        event.register(EntityFlameWraith.class, String.valueOf(Identifier.of(MOD_ID, "FlameWraith")));
        event.register(EntityFox.class, String.valueOf(Identifier.of(MOD_ID, "Fox")));
        event.register(EntityHellRat.class, String.valueOf(Identifier.of(MOD_ID, "HellRat")));
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

        event.register(EntityBigCat.class, String.valueOf(Identifier.of(MOD_ID, "BigCat")));
        event.register(EntityDolphin.class, String.valueOf(Identifier.of(MOD_ID, "Dolphin")));
        event.register(EntityHorse.class, String.valueOf(Identifier.of(MOD_ID, "Horse")));
        event.register(EntityKitty.class, String.valueOf(Identifier.of(MOD_ID, "Kitty")));
    }

    @EventListener
    private static void registerMobHandlers(MobHandlerRegistryEvent event) {
        Registry.register(event.registry, MOD_ID.id("Bear"), EntityBear::new);
        Registry.register(event.registry, MOD_ID.id("Bird"), EntityBird::new);
        Registry.register(event.registry, MOD_ID.id("Boar"), EntityBoar::new);
        Registry.register(event.registry, MOD_ID.id("Bunny"), EntityBunny::new);
        Registry.register(event.registry, MOD_ID.id("CaveOgre"), EntityCaveOgre::new);
        Registry.register(event.registry, MOD_ID.id("Deer"), EntityDeer::new);
        Registry.register(event.registry, MOD_ID.id("Duck"), EntityDuck::new);
        Registry.register(event.registry, MOD_ID.id("FireOgre"), EntityFireOgre::new);
        Registry.register(event.registry, MOD_ID.id("Fishy"), EntityFishy::new);
        Registry.register(event.registry, MOD_ID.id("FishyEgg"), EntityFishyEgg::new);
        Registry.register(event.registry, MOD_ID.id("FlameWraith"), EntityFlameWraith::new);
        Registry.register(event.registry, MOD_ID.id("Fox"), EntityFox::new);
        Registry.register(event.registry, MOD_ID.id("HellRat"), EntityHellRat::new);
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

        Registry.register(event.registry, MOD_ID.id("BigCat"), EntityBigCat::new);
        Registry.register(event.registry, MOD_ID.id("Dolphin"), EntityDolphin::new);
        Registry.register(event.registry, MOD_ID.id("Horse"), EntityHorse::new);
        Registry.register(event.registry, MOD_ID.id("Kitty"), EntityKitty::new);
    }

}
