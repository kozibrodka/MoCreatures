package net.kozibrodka.mocreatures.network;

import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.fabricmc.loader.FabricLoader;
import net.kozibrodka.mocreatures.entity.EntityHorse;
import net.minecraft.entity.player.ClientPlayerEntity;
import net.minecraft.entity.player.ServerPlayerEntity;
import net.minecraft.network.NetworkHandler;
import net.minecraft.network.packet.Packet;
import net.minecraft.world.ServerWorld;
import net.modificationstation.stationapi.api.entity.player.PlayerHelper;
import net.modificationstation.stationapi.api.network.packet.ManagedPacket;
import net.modificationstation.stationapi.api.network.packet.PacketType;
import org.jetbrains.annotations.NotNull;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;

public class ServerRidingPacket extends Packet implements ManagedPacket<ServerRidingPacket> {

    public static final PacketType<ServerRidingPacket> TYPE = PacketType.builder(true, true, ServerRidingPacket::new).build();

    private double VelX;
    private double VelY;
    private double VelZ;
    private float Yaw;
    private float Pitch;
    private boolean skok;

    public ServerRidingPacket() {
    }

    public ServerRidingPacket(double veloX, double veloY, double veloZ, float yaw, float pitch, boolean jumps) {
        this.VelX = veloX;
        this.VelY = veloY;
        this.VelZ = veloZ;
        this.Yaw = yaw;
        this.Pitch = pitch;
        this.skok = jumps;
    }

    @Override
    public void read(DataInputStream stream) {
        try {
            this.VelX = stream.readDouble();
            this.VelY = stream.readDouble();
            this.VelZ = stream.readDouble();
            this.Yaw = stream.readFloat();
            this.Pitch = stream.readFloat();
            this.skok = stream.readBoolean();

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void write(DataOutputStream stream) {
        try {
            stream.writeDouble(this.VelX);
            stream.writeDouble(this.VelY);
            stream.writeDouble(this.VelZ);
            stream.writeFloat(this.Yaw);
            stream.writeFloat(this.Pitch);
            stream.writeBoolean(this.skok);
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
    }

    @Environment(EnvType.SERVER)
    public void handleServer(NetworkHandler networkHandler) {
        ServerPlayerEntity player = (ServerPlayerEntity) PlayerHelper.getPlayerFromPacketHandler(networkHandler);
        if(player == null){
            return;
        }
            player.jumping = skok;
            player.yaw = this.Yaw;
            player.pitch = this.Pitch;
            player.velocityX = this.VelX;
            player.velocityY = this.VelY;
            player.velocityZ = this.VelZ;
    }

    @Override
    public int size() {
        return 6;
    }

    @Override
    public @NotNull PacketType<ServerRidingPacket> getType() {
        return TYPE;
    }
}
