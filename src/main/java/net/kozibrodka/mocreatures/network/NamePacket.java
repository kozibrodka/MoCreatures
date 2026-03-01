package net.kozibrodka.mocreatures.network;

import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.fabricmc.loader.FabricLoader;
import net.kozibrodka.mocreatures.entity.EntityBigCat;
import net.kozibrodka.mocreatures.entity.EntityDeer;
import net.kozibrodka.mocreatures.entity.EntityHorse;
import net.kozibrodka.mocreatures.mocreatures.MoCreatureNamed;
import net.kozibrodka.mocreatures.mocreatures.MoGuiOpener;
import net.minecraft.entity.LivingEntity;
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

public class NamePacket extends Packet implements ManagedPacket<NamePacket> {

    public static final PacketType<NamePacket> TYPE = PacketType.builder(true, true, NamePacket::new).build();

    private int entityId;
    private String entityName;

    public NamePacket() {
    }

    public NamePacket(String name, int id) {
        this.entityName = name;
        this.entityId = id;
    }

    @Override
    public void read(DataInputStream stream) {
        try {
            this.entityName = stream.readUTF();
            this.entityId = stream.readInt();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void write(DataOutputStream stream) {
        try {
            stream.writeUTF(this.entityName);
            stream.writeInt(this.entityId);
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
        MoGuiOpener clientS = new MoGuiOpener();
        ClientPlayerEntity player = (ClientPlayerEntity) PlayerHelper.getPlayerFromPacketHandler(networkHandler);
        if(player == null){
            return;
        }
        LivingEntity entity1 = (LivingEntity) ((ClientWorld)player.world).getEntity(this.entityId);
        if(entity1 instanceof MoCreatureNamed){
            clientS.openTameGui(entity1, ((MoCreatureNamed)entity1).getName());
        }

//        if(Objects.equals(entityType, "horse")){
//            EntityHorse entity1 = (EntityHorse) ((ClientWorld)player.world).getEntity(this.entityId);
//            if(entity1 != null){
//                clientS.openTameGui(entity1, entity1.getName());
//            }
//        }

    }

    @Environment(EnvType.SERVER)
    public void handleServer(NetworkHandler networkHandler) {
        ServerPlayerEntity player = (ServerPlayerEntity) PlayerHelper.getPlayerFromPacketHandler(networkHandler);
        if(player == null){
            return;
        }

        LivingEntity entity1 = (LivingEntity) ((ServerWorld)player.world).getEntity(this.entityId);
        if(entity1 instanceof MoCreatureNamed){
            ((MoCreatureNamed)entity1).setName(this.entityName);
        }

//        if(Objects.equals(entityType, "horse")){
//            EntityHorse entity1 = (EntityHorse) ((ServerWorld)player.world).getEntity(this.entityId);
//            if(entity1 != null){
//                entity1.setName(this.entityName);
//            }
//        }
    }

    @Override
    public int size() {
        return 3;
    }

    @Override
    public @NotNull PacketType<NamePacket> getType() {
        return TYPE;
    }
}
