package net.kozibrodka.mocreatures.network;

import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.fabricmc.loader.FabricLoader;
import net.kozibrodka.mocreatures.events.TextureListener;
import net.kozibrodka.mocreatures.mocreatures.CREEPSFxSpit;
import net.minecraft.client.Minecraft;
import net.minecraft.entity.player.ClientPlayerEntity;
import net.minecraft.network.NetworkHandler;
import net.minecraft.network.packet.Packet;
import net.modificationstation.stationapi.api.entity.player.PlayerHelper;
import net.modificationstation.stationapi.api.network.packet.ManagedPacket;
import net.modificationstation.stationapi.api.network.packet.PacketType;
import org.jetbrains.annotations.NotNull;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;

public class CustomParticlePacket extends Packet implements ManagedPacket<CustomParticlePacket> {

    public static final PacketType<CustomParticlePacket> TYPE = PacketType.builder(true, true, CustomParticlePacket::new).build();

    private int action;
    private double x;
    private double y;
    private double z;


    public CustomParticlePacket() {
    }

    public CustomParticlePacket(int particleName, double posX, double posY, double posZ) {
        this.action = particleName;
        this.x = posX;
        this.y = posY;
        this.z = posZ;
    }

    @Override
    public void read(DataInputStream stream) {
        try {
            this.action = stream.readInt();
            this.x = stream.readDouble();
            this.y = stream.readDouble();
            this.z = stream.readDouble();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void write(DataOutputStream stream) {
        try {
            stream.writeInt(this.action);
            stream.writeDouble(this.x);
            stream.writeDouble(this.y);
            stream.writeDouble(this.z);
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
            CREEPSFxSpit creepsfxspit = new CREEPSFxSpit(player.world, this.x, this.y, this.z, TextureListener.bubble_particle);
            creepsfxspit.renderDistanceMultiplier = 10D;
            Minecraft.INSTANCE.particleManager.addParticle(creepsfxspit);
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
    public @NotNull PacketType<CustomParticlePacket> getType() {
        return TYPE;
    }
}
