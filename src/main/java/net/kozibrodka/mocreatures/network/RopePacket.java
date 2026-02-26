package net.kozibrodka.mocreatures.network;

import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.fabricmc.loader.FabricLoader;
import net.kozibrodka.mocreatures.entity.EntityDeer;
import net.kozibrodka.mocreatures.entity.EntityHorse;
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
//        System.out.println("hej 0: " + entityRoper);
        if(Objects.equals(entityName, "horse")){
            ClientPlayerEntity player = (ClientPlayerEntity) PlayerHelper.getPlayerFromPacketHandler(networkHandler);
            if(player != null) {
                EntityHorse horse1 = (EntityHorse) ((ClientWorld)player.world).getEntity(this.entityId);
//                LivingEntity entityRAK = (LivingEntity) ((ClientWorld)player.world).getEntity(this.entityRoper);
                if(horse1 != null){
                    LivingEntity roper1 = horse1.world.getPlayer(this.entityRoper);
                    horse1.roper = roper1;
                }
//                System.out.println(horse1);
//                System.out.println(entityRAKOWISKO);
//                System.out.println(player.name);

//                LivingEntity entityR;
//                if(entityRoper == 0){
//                    horse1.roper = null;
//                }else{
//                    entityR = (LivingEntity) ((ClientWorld)player.world).getEntity(this.entityRoper);
//                    System.out.println(entityR);
//                    if(entityRoper == player.id){
////                        horse1.roper = entityR;
//                        horse1.roper = entityRAKOWISKO;
//                    }else{
////                        horse1.roper = entityR;
//                        horse1.roper = entityRAKOWISKO;
//                    }
//                }
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
