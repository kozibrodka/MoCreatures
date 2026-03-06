package net.kozibrodka.mocreatures.network;

import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.fabricmc.loader.FabricLoader;
import net.kozibrodka.mocreatures.entity.*;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.player.ClientPlayerEntity;
import net.minecraft.entity.player.PlayerEntity;
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
import java.util.Objects;

public class RopePacket extends Packet implements ManagedPacket<RopePacket> {

    public static final PacketType<RopePacket> TYPE = PacketType.builder(true, true, RopePacket::new).build();

    private int entityId;
    private String entityRoper;
    private String entityName;

    public RopePacket() {
    }

    public RopePacket(String name, int id, String roper) {
        this.entityName = name;
        this.entityId = id;
        this.entityRoper = roper;
    }

    @Override
    public void read(DataInputStream stream) {
        try {
            this.entityName = stream.readUTF();
            this.entityId = stream.readInt();
            this.entityRoper = stream.readUTF();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void write(DataOutputStream stream) {
        try {
            stream.writeUTF(this.entityName);
            stream.writeInt(this.entityId);
            stream.writeUTF(this.entityRoper);
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

        if(Objects.equals(entityName, "horse")){
                EntityHorse horse1 = (EntityHorse) ((ClientWorld)player.world).getEntity(this.entityId);
                if(horse1 != null){
                    horse1.roper = horse1.world.getPlayer(this.entityRoper);
                }
        }

        if(Objects.equals(entityName, "tiger")){
            EntityBigCat tiger1 = (EntityBigCat) ((ClientWorld)player.world).getEntity(this.entityId);
            if(tiger1 != null){
                LivingEntity roper1 = tiger1.world.getPlayer(this.entityRoper);
                tiger1.roper = roper1;
            }
        }

        if(Objects.equals(entityName, "bird")){
            EntityBird bird1 = (EntityBird) ((ClientWorld)player.world).getEntity(this.entityId);
            if(bird1 != null){
                LivingEntity roper2 = bird1.world.getPlayer(this.entityRoper);
                bird1.setVehicle(roper2);
            }
        }

        if(Objects.equals(entityName, "bunny")){
            EntityBunny bunny1 = (EntityBunny) ((ClientWorld)player.world).getEntity(this.entityId);
            if(bunny1 != null){
                LivingEntity roper3 = bunny1.world.getPlayer(this.entityRoper);
                bunny1.setVehicle(roper3);
            }
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
    public @NotNull PacketType<RopePacket> getType() {
        return TYPE;
    }
}
