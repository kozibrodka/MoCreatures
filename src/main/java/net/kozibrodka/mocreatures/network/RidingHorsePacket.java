package net.kozibrodka.mocreatures.network;

import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.fabricmc.loader.FabricLoader;
import net.kozibrodka.mocreatures.entity.EntityDeer;
import net.kozibrodka.mocreatures.entity.EntityHorse;
import net.minecraft.client.Minecraft;
import net.minecraft.entity.player.ClientPlayerEntity;
import net.minecraft.entity.player.ServerPlayerEntity;
import net.minecraft.network.NetworkHandler;
import net.minecraft.network.packet.Packet;
import net.minecraft.world.ClientWorld;
import net.minecraft.world.ServerWorld;
import net.modificationstation.stationapi.api.entity.player.PlayerHelper;
import net.modificationstation.stationapi.api.network.packet.ManagedPacket;
import net.modificationstation.stationapi.api.network.packet.PacketType;
import org.jetbrains.annotations.NotNull;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.util.Objects;

public class RidingHorsePacket extends Packet implements ManagedPacket<RidingHorsePacket> {

    public static final PacketType<RidingHorsePacket> TYPE = PacketType.builder(true, true, RidingHorsePacket::new).build();

    private int horseID;
    private double VelX;
    private double VelY;
    private double VelZ;
    private double PosX;
    private double PosY;
    private double PosZ;
    private float Yaw;
    private float Pitch;
    private double prePosX;
    private double prePosZ;
    private boolean skok;
    private double prePosY;

    public RidingHorsePacket() {
    }

    /// Ten Pakiet nie jest używany już. do testów
//    public RidingHorsePacket(int entityID, double veloX, double veloY, double veloZ) {
//        this.horseID = entityID;
//        this.VelX = veloX;
//        this.VelY = veloY;
//        this.VelZ = veloZ;
//    }
//
//    public RidingHorsePacket(int entityID, double veloX, double veloY, double veloZ, double posX, double posY, double posZ, float yaw, float pitch, double preX, double preZ, boolean jumps, double preY) {
//        this.horseID = entityID;
//        this.VelX = veloX;
//        this.VelY = veloY;
//        this.VelZ = veloZ;
//        this.PosX = posX;
//        this.PosY = posY;
//        this.PosZ = posZ;
//        this.Yaw = yaw;
//        this.Pitch = pitch;
//        this.prePosX = preX;
//        this.prePosZ = preZ;
//        this.skok = jumps;
//        this.prePosY = preY;
//    }

    public RidingHorsePacket(double veloX, double veloY, double veloZ, float yaw, float pitch, boolean jumps) {
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
//            this.horseID = stream.readInt();
            this.VelX = stream.readDouble();
            this.VelY = stream.readDouble();
            this.VelZ = stream.readDouble();
//            this.PosX = stream.readDouble();
//            this.PosY = stream.readDouble();
//            this.PosZ = stream.readDouble();
            this.Yaw = stream.readFloat();
            this.Pitch = stream.readFloat();
//            this.prePosX = stream.readDouble();
//            this.prePosZ = stream.readDouble();
            this.skok = stream.readBoolean();
//            this.prePosY = stream.readDouble();

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void write(DataOutputStream stream) {
        try {
//            stream.writeInt(this.horseID);
            stream.writeDouble(this.VelX);
            stream.writeDouble(this.VelY);
            stream.writeDouble(this.VelZ);
//            stream.writeDouble(this.PosX);
//            stream.writeDouble(this.PosY);
//            stream.writeDouble(this.PosZ);
            stream.writeFloat(this.Yaw);
            stream.writeFloat(this.Pitch);
//            stream.writeDouble(this.prePosX);
//            stream.writeDouble(this.prePosZ);
            stream.writeBoolean(this.skok);
//            stream.writeDouble(this.prePosY);
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
//        ClientPlayerEntity player = (ClientPlayerEntity) PlayerHelper.getPlayerFromPacketHandler(networkHandler);
//        if(player.vehicle != null) {
//            player.vehicle.prevX = this.prePosX;
//            player.vehicle.prevY = this.prePosY;
//            player.vehicle.prevZ = this.prePosZ;
//            }
    }

    @Environment(EnvType.SERVER)
    public void handleServer(NetworkHandler networkHandler) {
        ServerPlayerEntity player = (ServerPlayerEntity) PlayerHelper.getPlayerFromPacketHandler(networkHandler);
        if(player == null){
            return;
        }
//        EntityHorse horse1 = (EntityHorse) ((ServerWorld)player.world).getEntity(this.horseID);
//        if(horse1 == null){
//            return;
//        }
            player.jumping = skok;
//            player.yaw = this.Yaw;
//            player.pitch = this.Pitch;
//            player.velocityX = this.VelX;
//            player.velocityY = this.VelY;
//            player.velocityZ = this.VelZ;
    }

    @Override
    public int size() {
        return 6;
    }

    @Override
    public @NotNull PacketType<RidingHorsePacket> getType() {
        return TYPE;
    }
}
