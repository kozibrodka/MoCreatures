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

public class ClientHorsePacket extends Packet implements ManagedPacket<ClientHorsePacket> {

    public static final PacketType<ClientHorsePacket> TYPE = PacketType.builder(true, true, ClientHorsePacket::new).build();

    private double prePosX;
    private double prePosZ;
    private double prePosY;

    public ClientHorsePacket() {
    }

    public ClientHorsePacket(double preX, double preY, double preZ) {
        this.prePosX = preX;
        this.prePosZ = preY;
        this.prePosY = preZ;
    }

    @Override
    public void read(DataInputStream stream) {
        try {
            this.prePosX = stream.readDouble();
            this.prePosZ = stream.readDouble();
            this.prePosY = stream.readDouble();

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void write(DataOutputStream stream) {
        try {
            stream.writeDouble(this.prePosX);
            stream.writeDouble(this.prePosZ);
            stream.writeDouble(this.prePosY);
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
        if(player.vehicle != null) {
            //TODO: Raz mi Koń zniknął, jakby przyszły złe kordy. Nie udało mi się odwzorować na DUŻYCH prędkościach i poza granicą załadowanego świata.
//            System.out.println(this.prePosX + " " + this.prePosY + " " + this.prePosZ);
            player.vehicle.prevX = this.prePosX;
            player.vehicle.prevY = this.prePosY;
            player.vehicle.prevZ = this.prePosZ;
            }
    }

    @Environment(EnvType.SERVER)
    public void handleServer(NetworkHandler networkHandler) {

    }

    @Override
    public int size() {
        return 6;
    }

    @Override
    public @NotNull PacketType<ClientHorsePacket> getType() {
        return TYPE;
    }
}
