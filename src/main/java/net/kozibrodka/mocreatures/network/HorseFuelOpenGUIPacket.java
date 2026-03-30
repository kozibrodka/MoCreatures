package net.kozibrodka.mocreatures.network;

import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.kozibrodka.mocreatures.entity.EntityHorse;
import net.kozibrodka.mocreatures.events.mod_mocreatures;
import net.kozibrodka.mocreatures.fuelsystem.ContainerHorseFuel;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.network.NetworkHandler;
import net.minecraft.network.packet.Packet;
import net.modificationstation.stationapi.api.entity.player.PlayerHelper;
import net.modificationstation.stationapi.api.gui.screen.container.GuiHelper;
import net.modificationstation.stationapi.api.network.packet.ManagedPacket;
import net.modificationstation.stationapi.api.network.packet.PacketType;
import net.modificationstation.stationapi.api.util.Identifier;
import net.modificationstation.stationapi.api.util.Namespace;
import net.modificationstation.stationapi.api.util.SideUtil;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.jetbrains.annotations.NotNull;

import java.io.DataInputStream;
import java.io.DataOutputStream;

/**
 * @author TheSharkHour
 * @since 01/25/2026
 * <p>GUI Packet handler for the Airship. Sends the UI to the client.</p>
 * <p>Not really sure what I'm doing here, honestly. Packets are new to me.</p>
 */
public class HorseFuelOpenGUIPacket extends Packet implements ManagedPacket<HorseFuelOpenGUIPacket> {
    public static final PacketType<HorseFuelOpenGUIPacket> TYPE = PacketType.builder(false, true, HorseFuelOpenGUIPacket::new).build();
    private static final Logger log = LogManager.getLogger("[MOCREATURES]");

    public HorseFuelOpenGUIPacket() {
    }

    @Override
    public void read(DataInputStream stream) {
        // No data
    }

    @Override
    public void write(DataOutputStream stream) {
        // No data
    }

    @Override
    public void apply(NetworkHandler networkHandler) {
        SideUtil.run(() -> handleClient(networkHandler), () -> handleServer(networkHandler));
    }

    @Environment(EnvType.CLIENT)
    private void handleClient(NetworkHandler networkHandler) {
        PlayerEntity player = PlayerHelper.getPlayerFromPacketHandler(networkHandler);
        if (player == null || !(player.vehicle instanceof EntityHorse) || !mocr.mocreaturesGlass.animals.horse_fuel) return;
        EntityHorse airship = (EntityHorse) player.vehicle;

        GuiHelper.openGUI(
                player,
                Identifier.of(Namespace.of("mocreatures"), "openHorseFuel"),
                airship,
                new ContainerHorseFuel(player.inventory, airship)
        );
    }

    @Environment(EnvType.SERVER)
    public void handleServer(NetworkHandler networkHandler) {
        PlayerEntity player = PlayerHelper.getPlayerFromPacketHandler(networkHandler);
        if (player == null || !(player.vehicle instanceof EntityHorse) || !mocr.mocreaturesGlass.animals.horse_fuel) return;
        EntityHorse airship = (EntityHorse) player.vehicle;

        GuiHelper.openGUI(
                player,
                Identifier.of(Namespace.of("mocreatures"), "openHorseFuel"),
                airship,
                new ContainerHorseFuel(player.inventory, airship)
        );
    }

    mod_mocreatures mocr = new mod_mocreatures();

    @Override
    public int size() {
        return 0;
    }

    @Override
    public @NotNull PacketType<HorseFuelOpenGUIPacket> getType() {
        return TYPE;
    }
}
