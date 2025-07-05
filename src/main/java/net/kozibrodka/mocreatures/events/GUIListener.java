package net.kozibrodka.mocreatures.events;

import net.minecraft.client.gui.screen.Screen;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.kozibrodka.mocreatures.mocreatures.MoCGUI;
import net.mine_diver.unsafeevents.listener.EventListener;
import net.minecraft.inventory.Inventory;
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

        Registry.register(
                GuiHandlerRegistry.INSTANCE,
                Identifier.of(MOD_ID, "openTamePaper"),
                new GuiHandler(
                        this::openTamePaper, null
                )
        );
    }

    @Environment(EnvType.CLIENT)
    private Screen openTamePaper(PlayerEntity playerEntity, Inventory inventory, MessagePacket messagePacket) {
        return new MoCGUI(tempLiving, tempString);
    }

    public static LivingEntity tempLiving;
    public static String tempString;

}
