package net.kozibrodka.mocreatures.network;

import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.fabricmc.loader.FabricLoader;
import net.kozibrodka.mocreatures.entity.*;
import net.kozibrodka.mocreatures.mocreatures.MoGuiOpener;
import net.minecraft.entity.player.ClientPlayerEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.player.ServerPlayerEntity;
import net.minecraft.network.NetworkHandler;
import net.minecraft.network.packet.Packet;
import net.minecraft.world.ClientWorld;
import net.minecraft.world.ServerWorld;
import net.modificationstation.stationapi.api.entity.player.PlayerHelper;
import net.modificationstation.stationapi.api.network.packet.ManagedPacket;
import net.modificationstation.stationapi.api.network.packet.PacketHelper;
import net.modificationstation.stationapi.api.network.packet.PacketType;
import org.jetbrains.annotations.NotNull;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.util.Objects;

public class AskPacket extends Packet implements ManagedPacket<AskPacket> {

    public static final PacketType<AskPacket> TYPE = PacketType.builder(true, true, AskPacket::new).build();

    private int entityId;
    private String entityType;

    public AskPacket() {
    }

    public AskPacket(int id, String type) {
        this.entityId = id;
        this.entityType = type;
    }

    @Override
    public void read(DataInputStream stream) {
        try {
            this.entityId = stream.readInt();
            this.entityType = stream.readUTF();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void write(DataOutputStream stream) {
        try {
            stream.writeInt(this.entityId);
            stream.writeUTF(this.entityType);
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

        if(Objects.equals(entityType, "horse")){

            EntityHorse horse1 = (EntityHorse) ((ServerWorld)player.world).getEntity(this.entityId);
            if(horse1 != null) {
                PlayerEntity roper1 = (PlayerEntity) horse1.roper;
                if(roper1 != null) {
                    PacketHelper.sendTo(player, new RopePacket("horse", this.entityId, roper1.name));
                }

                PlayerEntity jokey1 = (PlayerEntity) horse1.passenger;
                if(jokey1 != null){
                    PacketHelper.sendTo(player, new PassengerPacket(this.entityId, jokey1.name));
                }
            }
        }

        if(Objects.equals(entityType, "tiger")){

            EntityBigCat tiger1 = (EntityBigCat) ((ServerWorld)player.world).getEntity(this.entityId);
            if(tiger1 != null) {
                PlayerEntity roper2 = (PlayerEntity) tiger1.roper;
                if(roper2 != null) {
                    PacketHelper.sendTo(player, new RopePacket("tiger", this.entityId, roper2.name));
                }
            }
        }

        if(Objects.equals(entityType, "dolphin")){

            EntityDolphin dolphin1 = (EntityDolphin) ((ServerWorld)player.world).getEntity(this.entityId);
            if(dolphin1 != null) {
                PlayerEntity jokey1 = (PlayerEntity) dolphin1.passenger;
                if(jokey1 != null){
                    PacketHelper.sendTo(player, new PassengerPacket(this.entityId, jokey1.name));
                }
            }
        }

//        if(Objects.equals(entityType, "bird")){
//
//            EntityBird bird1 = (EntityBird) ((ServerWorld)player.world).getEntity(this.entityId);
//            if(bird1 != null) {
//                PlayerEntity roper3 = (PlayerEntity) bird1.vehicle;
//                if(roper3 != null) {
//                    PacketHelper.sendTo(player, new RopePacket("bird", this.entityId, roper3.name));
//                }
//            }
//        }
//
//        if(Objects.equals(entityType, "shark")){
//
//            EntityShark shark1 = (EntityShark) ((ServerWorld)player.world).getEntity(this.entityId);
//            if(shark1 != null) {
//                PacketHelper.sendTo(player, new HealthPacket(this.entityId, shark1.health));
//            }
//        }
//
//        if(Objects.equals(entityType, "kitty")){
//
//            EntityKitty kitty1 = (EntityKitty) ((ServerWorld)player.world).getEntity(this.entityId);
//            if(kitty1 != null) {
//                PacketHelper.sendTo(player, new HealthPacket(this.entityId, kitty1.health));
//            }
//        }

//        if(Objects.equals(entityType, "bunny")){
//
//            EntityBunny bunny1 = (EntityBunny) ((ServerWorld)player.world).getEntity(this.entityId);
//            if(bunny1 != null) {
//                PlayerEntity roper4 = (PlayerEntity) bunny1.vehicle;
//                if(roper4 != null) {
//                    PacketHelper.sendTo(player, new RopePacket("bunny", this.entityId, roper4.name));
//                }
//            }
//        }
    }

    @Override
    public int size() {
        return 3;
    }

    @Override
    public @NotNull PacketType<AskPacket> getType() {
        return TYPE;
    }
}
