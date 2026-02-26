package net.kozibrodka.mocreatures.network;

import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.fabricmc.loader.FabricLoader;
import net.minecraft.entity.player.ClientPlayerEntity;
import net.minecraft.network.NetworkHandler;
import net.minecraft.network.packet.Packet;
import net.modificationstation.stationapi.api.entity.player.PlayerHelper;
import net.modificationstation.stationapi.api.network.packet.ManagedPacket;
import net.modificationstation.stationapi.api.network.packet.PacketType;
import org.jetbrains.annotations.NotNull;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;

public class JokeyPacket extends Packet implements ManagedPacket<JokeyPacket> {

    public static final PacketType<JokeyPacket> TYPE = PacketType.builder(true, true, JokeyPacket::new).build();

    private int action;

    public JokeyPacket() {
    }

    public JokeyPacket(int code) {
        this.action = code;
    }

    @Override
    public void read(DataInputStream stream) {
        try {
            this.action = stream.readInt();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void write(DataOutputStream stream) {
        try {
            stream.writeInt(this.action);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void apply(NetworkHandler arg) {
        switch (FabricLoader.INSTANCE.getEnvironmentType()) {
            case CLIENT -> handleClient(arg);
            case SERVER -> handleServer(arg);
        }
    }

    @Environment(EnvType.CLIENT)
    public void handleClient(NetworkHandler networkHandler) {
        ClientPlayerEntity player = (ClientPlayerEntity) PlayerHelper.getPlayerFromPacketHandler(networkHandler);
        if(player == null){
            return;
        }
        if(action == 0) {
            player.velocityY += 0.90000000000000002D;
            player.velocityZ -= 0.29999999999999999D;
            System.out.println("DOSZŁO CLIENT."); //todo dlaczego to wyzej niz na singlepl?
        }
        if(action == 1){
            player.swingHand();
        }

    }

    @Environment(EnvType.SERVER)
    public void handleServer(NetworkHandler networkHandler) {
    }

    @Override
    public int size() {
        return 3;
    }

    @Override
    public @NotNull PacketType<JokeyPacket> getType() {
        return TYPE;
    }
}
