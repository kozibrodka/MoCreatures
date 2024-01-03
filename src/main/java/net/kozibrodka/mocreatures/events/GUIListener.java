package net.kozibrodka.mocreatures.events;

import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.kozibrodka.mocreatures.mocreatures.MoCGUI;
import net.mine_diver.unsafeevents.listener.EventListener;
import net.minecraft.client.gui.screen.ScreenBase;
import net.minecraft.entity.player.PlayerBase;
import net.minecraft.inventory.InventoryBase;
import net.modificationstation.stationapi.api.event.registry.GuiHandlerRegistryEvent;
import net.modificationstation.stationapi.api.mod.entrypoint.Entrypoint;
import net.modificationstation.stationapi.api.registry.GuiHandlerRegistry;
import net.modificationstation.stationapi.api.registry.Identifier;
import net.modificationstation.stationapi.api.registry.ModID;
import net.modificationstation.stationapi.api.util.Null;
import uk.co.benjiweber.expressions.tuple.BiTuple;

public class GUIListener {

    @Entrypoint.ModID
    public static final ModID MOD_ID = Null.get();

//    @Environment(EnvType.CLIENT)
//    @EventListener
//    public void registerGuiHandlers(GuiHandlerRegistryEvent event) {
//        GuiHandlerRegistry registry = event.registry;
//        registry.registerValueNoMessage(Identifier.of(MOD_ID, "openTamePaper"), BiTuple.of(this::openTamePaper, null)); //BAD ONE
//    }
//
//
//    @Environment(EnvType.CLIENT)
//    public ScreenBase openTamePaper(PlayerBase player, InventoryBase inventoryBase) {
//        return new MoCGUI(player.inventory);
//    }

}
