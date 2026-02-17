package net.kozibrodka.mocreatures.network;

import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.fabricmc.loader.FabricLoader;
import net.kozibrodka.mocreatures.entity.EntityDeer;
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

public class AdultPacket extends Packet implements ManagedPacket<AdultPacket> {

    public static final PacketType<AdultPacket> TYPE = PacketType.builder(true, true, AdultPacket::new).build();

    private int entityId;
    private int entityType;
    private String entityName;

    public AdultPacket() {
    }

    public AdultPacket(String name, int id, int type) {
        this.entityName = name;
        this.entityId = id;
        this.entityType = type;
    }

    @Override
    public void read(DataInputStream stream) {
        try {
            this.entityName = stream.readUTF();
            this.entityId = stream.readInt();
            this.entityType = stream.readInt();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void write(DataOutputStream stream) {
        try {
            stream.writeUTF(this.entityName);
            stream.writeInt(this.entityId);
            stream.writeInt(this.entityType);
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
        if(Objects.equals(entityName, "deer")){
            ClientPlayerEntity player = (ClientPlayerEntity) PlayerHelper.getPlayerFromPacketHandler(networkHandler);
            if(player != null) {
                EntityDeer entity1 = (EntityDeer) ((ClientWorld)player.world).getEntity(this.entityId);
                    if(entity1 != null){
                        System.out.println("DOJRZEWAM CLIENT" + entityType);
                        entity1.setMyTexture(entityType);
                    }
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
    public @NotNull PacketType<AdultPacket> getType() {
        return TYPE;
    }
}
