package net.kozibrodka.mocreatures.events;

import net.fabricmc.loader.api.FabricLoader;
import net.kozibrodka.mocreatures.entity.EntityHorse;
import net.kozibrodka.mocreatures.fuelsystem.GuiHorseFuel;
import net.minecraft.client.gui.screen.Screen;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.kozibrodka.mocreatures.mocreatures.MoCGUI;
import net.mine_diver.unsafeevents.listener.EventListener;
import net.minecraft.inventory.Inventory;
import net.minecraft.world.World;
import net.modificationstation.stationapi.api.client.gui.screen.GuiHandler;
import net.modificationstation.stationapi.api.client.registry.GuiHandlerRegistry;
import net.modificationstation.stationapi.api.event.registry.GuiHandlerRegistryEvent;
import net.modificationstation.stationapi.api.mod.entrypoint.Entrypoint;
import net.modificationstation.stationapi.api.network.packet.MessagePacket;
import net.modificationstation.stationapi.api.registry.Registry;
import net.modificationstation.stationapi.api.util.Identifier;
import net.modificationstation.stationapi.api.util.Namespace;
import net.modificationstation.stationapi.api.util.Null;

public class GUIListener {

    @Entrypoint.Namespace
    public static Namespace MOD_ID = Null.get();

    @Environment(EnvType.CLIENT)
    @EventListener
    public void registerGuiHandlers(GuiHandlerRegistryEvent event) {
        GuiHandlerRegistry registry = event.registry;

//        Registry.register(registry, MOD_ID.id("openHorseFuel"), new GuiHandler((GuiHandler.ScreenFactoryNoMessage) this::openHorseFuel, () -> null));

        Registry.register(registry, MOD_ID.id("openHorseFuel"), new GuiHandler((GuiHandler.ScreenFactoryNoMessage) this::openHorseFuel, EntityHorse::new));
    }


    @Environment(EnvType.CLIENT)
    public Screen openHorseFuel(PlayerEntity player, Inventory inventoryBase) {
        return new GuiHorseFuel(player.inventory, tempHorse);
    }

    public static EntityHorse tempHorse;
    public static LivingEntity tempLiving;
    public static String tempString;

    World world = (World) FabricLoader.getInstance().getGameInstance();

}
