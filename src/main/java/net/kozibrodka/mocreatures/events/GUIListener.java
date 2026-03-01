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
import org.jetbrains.annotations.Contract;
import org.jetbrains.annotations.NotNull;

public class GUIListener {

    @Entrypoint.Namespace
    public static Namespace NAMESPACE;

    @Environment(EnvType.CLIENT)
    @EventListener
    public void registerScreenHandlers(@NotNull GuiHandlerRegistryEvent event) {
        event.register(NAMESPACE.id("openHorseFuel"), new GuiHandler((GuiHandler.ScreenFactoryNoMessage) this::openAirship, () -> null));
    }

    @Environment(EnvType.CLIENT)
    @Contract("_, _ -> new")
    private @NotNull Screen openAirship(@NotNull PlayerEntity player, Inventory inventory) {
        return new GuiHorseFuel(player.inventory, (EntityHorse) player.vehicle);
    }

}
