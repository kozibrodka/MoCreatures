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
                tiger1.roper = tiger1.world.getPlayer(this.entityRoper);
            }
        }

        if(Objects.equals(entityName, "sheep")){
            EntitySheep sheep1 = (EntitySheep) ((ClientWorld)player.world).getEntity(this.entityId);
            if(sheep1 != null){
                sheep1.roper = sheep1.world.getPlayer(this.entityRoper);
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

        if(Objects.equals(entityName, "mouse")){
            EntityMouse mouse1 = (EntityMouse) ((ClientWorld)player.world).getEntity(this.entityId);
            if(mouse1 != null){
                LivingEntity roper4 = mouse1.world.getPlayer(this.entityRoper);
                mouse1.setVehicle(roper4);
            }
        }

        if(Objects.equals(entityName, "box")){
            EntityLitterBox box1 = (EntityLitterBox) ((ClientWorld)player.world).getEntity(this.entityId);
            if(box1 != null){
                LivingEntity roper5 = box1.world.getPlayer(this.entityRoper);
                box1.setVehicle(roper5);
            }
        }

        if(Objects.equals(entityName, "bed")){
            EntityKittyBed bed1 = (EntityKittyBed) ((ClientWorld)player.world).getEntity(this.entityId);
            if(bed1 != null){
                LivingEntity roper6 = bed1.world.getPlayer(this.entityRoper);
                bed1.setVehicle(roper6);
            }
        }

        if(Objects.equals(entityName, "kitty")){
            EntityKitty kitty1 = (EntityKitty) ((ClientWorld)player.world).getEntity(this.entityId);
            if(kitty1 != null){
                LivingEntity roper6 = kitty1.world.getPlayer(this.entityRoper);
                kitty1.setVehicle(roper6);
            }
        }

        if(Objects.equals(entityName, "turtle")){
            EntityTurtle turtle1 = (EntityTurtle) ((ClientWorld)player.world).getEntity(this.entityId);
            if(turtle1 != null){
                LivingEntity roper6 = turtle1.world.getPlayer(this.entityRoper);
                turtle1.setVehicle(roper6);
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
