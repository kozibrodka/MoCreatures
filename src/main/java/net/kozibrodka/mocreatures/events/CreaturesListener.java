package net.kozibrodka.mocreatures.events;

import net.kozibrodka.mocreatures.entity.*;
import net.mine_diver.unsafeevents.listener.EventListener;
import net.modificationstation.stationapi.api.event.entity.EntityRegister;
import net.modificationstation.stationapi.api.event.registry.MobHandlerRegistryEvent;
import net.modificationstation.stationapi.api.mod.entrypoint.Entrypoint;
import net.modificationstation.stationapi.api.mod.entrypoint.EntrypointManager;
import net.modificationstation.stationapi.api.util.Identifier;
import net.modificationstation.stationapi.api.util.Namespace;
import net.modificationstation.stationapi.api.registry.Registry;
import net.modificationstation.stationapi.api.util.Null;

import java.lang.invoke.MethodHandles;

public class CreaturesListener {
    static {
        EntrypointManager.registerLookup(MethodHandles.lookup());
    }

    @Entrypoint.Namespace
    public static Namespace MOD_ID = Null.get();

    @EventListener
    private static void registerEntities(EntityRegister event) { //TODO - MULTIPLAYER
        event.register(EntityBear.class, String.valueOf(Identifier.of(MOD_ID, "Bear")));  //TODO
        event.register(EntityBird.class, String.valueOf(Identifier.of(MOD_ID, "Bird")));  //TODO
        event.register(EntityBoar.class, String.valueOf(Identifier.of(MOD_ID, "Boar")));  //TODO
        event.register(EntityBunny.class, String.valueOf(Identifier.of(MOD_ID, "Bunny")));  //TODO
        event.register(EntityCaveOgre.class, String.valueOf(Identifier.of(MOD_ID, "CaveOgre")));  //TODO
        event.register(EntityDeer.class, String.valueOf(Identifier.of(MOD_ID, "Deer")));  //TODO
        event.register(EntityDuck.class, String.valueOf(Identifier.of(MOD_ID, "Duck")));  //TODO
        event.register(EntityFireOgre.class, String.valueOf(Identifier.of(MOD_ID, "FireOgre")));  //TODO
        event.register(EntityFishy.class, String.valueOf(Identifier.of(MOD_ID, "Fishy")));  //TODO
        event.register(EntityFishyEgg.class, String.valueOf(Identifier.of(MOD_ID, "FishyEgg")));  //TODO
        event.register(EntityFlameWraith.class, String.valueOf(Identifier.of(MOD_ID, "FlameWraith")));  //TODO
        event.register(EntityFox.class, String.valueOf(Identifier.of(MOD_ID, "Fox")));  //TODO
        event.register(EntityHellRat.class, String.valueOf(Identifier.of(MOD_ID, "HellRat")));  //TODO
        event.register(EntityKittyBed.class, String.valueOf(Identifier.of(MOD_ID, "KittyBed")));  //TODO
        event.register(EntityLitterBox.class, String.valueOf(Identifier.of(MOD_ID, "LitterBox")));  //TODO
        event.register(EntityMouse.class, String.valueOf(Identifier.of(MOD_ID, "Mouse")));  //TODO
        event.register(EntityOgre.class, String.valueOf(Identifier.of(MOD_ID, "Ogre")));  //TODO
        event.register(EntityPolarBear.class, String.valueOf(Identifier.of(MOD_ID, "PolarBear")));  //TODO
        event.register(EntityRat.class, String.valueOf(Identifier.of(MOD_ID, "Rat")));  //TODO
        event.register(EntityShark.class, String.valueOf(Identifier.of(MOD_ID, "Shark")));  //TODO
        event.register(EntitySharkEgg.class, String.valueOf(Identifier.of(MOD_ID, "SharkEgg")));  //TODO
        event.register(EntityWerewolf.class, String.valueOf(Identifier.of(MOD_ID, "WereWolf")));  //TODO
        event.register(EntityWraith.class, String.valueOf(Identifier.of(MOD_ID, "Wraith")));  //TODO
        event.register(EntityWWolf.class, String.valueOf(Identifier.of(MOD_ID, "WildWolf")));  //TODO

        event.register(EntityBigCat.class, String.valueOf(Identifier.of(MOD_ID, "BigCat")));  //TODO
        event.register(EntityDolphin.class, String.valueOf(Identifier.of(MOD_ID, "Dolphin")));  //TODO
        event.register(EntityHorse.class, String.valueOf(Identifier.of(MOD_ID, "Horse")));  //TODO
        event.register(EntityKitty.class, String.valueOf(Identifier.of(MOD_ID, "Kitty")));  //TODO
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
