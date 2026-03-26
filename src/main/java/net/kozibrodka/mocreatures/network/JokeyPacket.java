package net.kozibrodka.mocreatures.network;

import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.fabricmc.loader.FabricLoader;
import net.kozibrodka.mocreatures.entity.EntityBunny;
import net.kozibrodka.mocreatures.entity.EntityCrocodile;
import net.kozibrodka.mocreatures.entity.EntityGiraffe;
import net.kozibrodka.mocreatures.entity.EntityHorse;
import net.minecraft.client.Minecraft;
import net.minecraft.entity.Entity;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.player.ClientPlayerEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.network.NetworkHandler;
import net.minecraft.network.packet.Packet;
import net.minecraft.util.math.MathHelper;
import net.minecraft.world.ClientWorld;
import net.modificationstation.stationapi.api.entity.player.PlayerHelper;
import net.modificationstation.stationapi.api.network.packet.ManagedPacket;
import net.modificationstation.stationapi.api.network.packet.PacketType;
import org.jetbrains.annotations.NotNull;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;

public class JokeyPacket extends Packet implements ManagedPacket<JokeyPacket> {

    public static final PacketType<JokeyPacket> TYPE = PacketType.builder(true, true, JokeyPacket::new).build();

    private int action;
    private int entityID;
    private int victimID;
    private double VelX;
    private double VelZ;

    public JokeyPacket() {
    }

    public JokeyPacket(int code) {
        this.action = code;
    }

    public JokeyPacket(int code, double a, double b) {
        this.action = code;
        this.VelX = a;
        this.VelZ = b;
    }

    public JokeyPacket(int code, int a, int b) {
        this.action = code;
        this.entityID = a;
        this.victimID = b;
    }

    public JokeyPacket(int code, int b) {
        this.action = code;
        this.victimID = b;
    }

    @Override
    public void read(DataInputStream stream) {
        try {
            this.action = stream.readInt();
            this.entityID = stream.readInt();
            this.victimID = stream.readInt();
            this.VelX = stream.readDouble();
            this.VelZ = stream.readDouble();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void write(DataOutputStream stream) {
        try {
            stream.writeInt(this.action);
            stream.writeInt(this.entityID);
            stream.writeInt(this.victimID);
            stream.writeDouble(this.VelX);
            stream.writeDouble(this.VelZ);
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
        if(action == 0) {
            double c = 0.8D;
            player.velocityY += (0.9D);
            player.velocityZ -= (0.3D);
//            System.out.println("DOSZŁO CLIENT.");
            //todo dlaczego to wyzej niz na singlepl?
        }
        if(action == 1){
            player.swingHand();
        }
        if(action == 2){
                if(player.velocityY < 0.3D){
                    player.velocityY += 0.2D;
                }
                player.velocityX += (this.VelX  * 0.1D);
                player.velocityZ += (this.VelZ  * 0.1D);
                /// Żyrafa na serwie nie jest idealna, ale cieżko cokolwiek zrobić z tym.

        }
        if(action == 3){
            Minecraft.INSTANCE.options.thirdPerson = true;
        }
        if(action == 4){
            player.deathTime = 0;
//            player.bodyYaw = 0.0F;
        }
        if(action == 5){
            EntityCrocodile croc = (EntityCrocodile) ((ClientWorld)player.world).getEntity(this.entityID);
            LivingEntity victim = (LivingEntity) ((ClientWorld)player.world).getEntity(this.victimID);
            if(croc != null){
//                croc.passenger = victim;
                victim.setVehicle(croc);
            }
        }
        if(action == 6){
            LivingEntity victim = (LivingEntity) ((ClientWorld)player.world).getEntity(this.victimID);
            if(victim != null){
                victim.setVehicle(null);
                victim.deathTime = 0;
//                victim.bodyYaw = 0.0F;
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
    public @NotNull PacketType<JokeyPacket> getType() {
        return TYPE;
    }
}
