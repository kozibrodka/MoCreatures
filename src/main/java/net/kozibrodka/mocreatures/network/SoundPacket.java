package net.kozibrodka.mocreatures.network;

import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.fabricmc.loader.FabricLoader;
import net.kozibrodka.mocreatures.entity.EntityHorse;
import net.minecraft.client.Minecraft;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.player.ClientPlayerEntity;
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

public class SoundPacket extends Packet implements ManagedPacket<SoundPacket> {

    public static final PacketType<SoundPacket> TYPE = PacketType.builder(true, true, SoundPacket::new).build();

    private String soundToPlay;
    private double x;
    private double y;
    private double z;
    private float volume;
    private float pitch;
    private int livingID;

    public SoundPacket() {
    }

    public SoundPacket(String soundName, double posX, double posY, double posZ, float argX, float argY) {
        this.soundToPlay = soundName;
        this.x = posX;
        this.y = posY;
        this.z = posZ;
        this.volume = argX;
        this.pitch = argY;
    }

    public SoundPacket(String soundName, int entityId, float argX, float argY) {
        this.soundToPlay = soundName;
        this.livingID = entityId;
        this.volume = argX;
        this.pitch = argY;
    }

    @Override
    public void read(DataInputStream stream) {
        try {
            this.soundToPlay = stream.readUTF();
            this.x = stream.readDouble();
            this.y = stream.readDouble();
            this.z = stream.readDouble();
            this.volume = stream.readFloat();
            this.pitch = stream.readFloat();
            this.livingID = stream.readInt();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void write(DataOutputStream stream) {
        try {
            stream.writeUTF(this.soundToPlay);
            stream.writeDouble(this.x);
            stream.writeDouble(this.y);
            stream.writeDouble(this.z);
            stream.writeFloat(this.volume);
            stream.writeFloat(this.pitch);
            stream.writeInt(this.livingID);
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
        LivingEntity entityLiv = (LivingEntity) ((ClientWorld)player.world).getEntity(this.livingID);
        if(entityLiv == null){
            player.world.playSound(this.x, this.y, this.z, this.soundToPlay, this.volume, this.pitch);
        }else{
            player.world.playSound(entityLiv, this.soundToPlay, this.volume, this.pitch);
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
    public @NotNull PacketType<SoundPacket> getType() {
        return TYPE;
    }
}
