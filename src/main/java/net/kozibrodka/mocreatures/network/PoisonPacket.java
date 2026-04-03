package net.kozibrodka.mocreatures.network;

import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.fabricmc.loader.FabricLoader;
import net.kozibrodka.mocreatures.entity.EntityCrocodile;
import net.kozibrodka.mocreatures.events.mod_mocreatures;
import net.kozibrodka.mocreatures.mocreatures.MocTick;
import net.minecraft.client.Minecraft;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.player.ClientPlayerEntity;
import net.minecraft.entity.player.ServerPlayerEntity;
import net.minecraft.network.NetworkHandler;
import net.minecraft.network.packet.Packet;
import net.minecraft.world.ClientWorld;
import net.modificationstation.stationapi.api.entity.player.PlayerHelper;
import net.modificationstation.stationapi.api.network.packet.ManagedPacket;
import net.modificationstation.stationapi.api.network.packet.PacketType;
import org.jetbrains.annotations.NotNull;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;

public class PoisonPacket extends Packet implements ManagedPacket<PoisonPacket> {

    public static final PacketType<PoisonPacket> TYPE = PacketType.builder(true, true, PoisonPacket::new).build();

    private int action;
//    private int entityID;
//    private int victimID;
//    private double VelX;
//    private double VelZ;

    public PoisonPacket() {
    }

    public PoisonPacket(int code) {
        this.action = code;
    }



    @Override
    public void read(DataInputStream stream) {
        try {
            this.action = stream.readInt();
//            this.entityID = stream.readInt();
//            this.victimID = stream.readInt();
//            this.VelX = stream.readDouble();
//            this.VelZ = stream.readDouble();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void write(DataOutputStream stream) {
        try {
            stream.writeInt(this.action);
//            stream.writeInt(this.entityID);
//            stream.writeInt(this.victimID);
//            stream.writeDouble(this.VelX);
//            stream.writeDouble(this.VelZ);
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
        if(action == 1){
            MocTick.poisoned = true;
        }
        if(action == 2){
            MocTick.freezed = true;
        }
    }

    @Environment(EnvType.SERVER)
    public void handleServer(NetworkHandler networkHandler) {
        ServerPlayerEntity player = (ServerPlayerEntity) PlayerHelper.getPlayerFromPacketHandler(networkHandler);
        if(player == null){
            return;
        }
        if(action == 3){
            mod_mocreatures.particlePacket(player.world, "slime", player.x, player.y + 0.5D, player.z, 0.0D, 0.5D, 0.0D);
            player.damage(null,1);
        }
        if(action == 4){
            mod_mocreatures.particlePacket(player.world, "snowballpoof", player.x, player.y + 0.5D, player.z, 0.0D, 0.5D, 0.0D);
            player.damage(null,1);
        }
    }


    @Override
    public int size() {
        return 3;
    }

    @Override
    public @NotNull PacketType<PoisonPacket> getType() {
        return TYPE;
    }
}
