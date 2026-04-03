package net.kozibrodka.mocreatures.entity;

import net.fabricmc.api.EnvType;
import net.fabricmc.loader.FabricLoader;
import net.kozibrodka.mocreatures.events.mod_mocreatures;
import net.kozibrodka.mocreatures.network.ClientHorsePacket;
import net.kozibrodka.mocreatures.network.JokeyPacket;
import net.kozibrodka.mocreatures.network.RidingHorsePacket;
import net.kozibrodka.mocreatures.network.ServerRidingPacket;
import net.minecraft.block.Block;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.math.MathHelper;
import net.minecraft.world.World;
import net.modificationstation.stationapi.api.network.packet.PacketHelper;
import net.modificationstation.stationapi.api.server.entity.HasTrackingParameters;
import net.modificationstation.stationapi.api.server.entity.MobSpawnDataProvider;
import net.modificationstation.stationapi.api.util.Identifier;
import net.modificationstation.stationapi.api.util.TriState;

//@HasTrackingParameters(trackingDistance = 160, updatePeriod = 1, sendVelocity = TriState.TRUE)
public class EntityAirShipHorse extends EntityHorse implements MobSpawnDataProvider {
    public EntityAirShipHorse(World world) {
        super(world);
        setTamed(true);
        setSaddled(true);
        setDisplayName(true);
        setChested(true);
    }

    //TYPE
    public void setTypeSpawn() {
        if(!world.isRemote) {
            setAdult(true);
            int racism;
            if(random.nextInt(2) == 0){
                racism = 8;
            }else{
                racism = 4;
            }
            setType(racism);
            this.health = this.maxhealth;
        }
    }

    protected void dropItems()
    {
        dropItem(new ItemStack(Item.COOKIE.id, 1, 0), 0.0F);
    }

    public void travel(float f, float f1)
    {    /// possible rewrite OF client/server sycnhro while riding, Best i can do for now. Airship system doesnt work for horses (yaw,pitch issues)
        if (FabricLoader.INSTANCE.getEnvironmentType() == EnvType.SERVER && passenger != null) { //todo upewnij sie, ze client nie dostaje.
//            PlayerEntity entityplayer3 = (PlayerEntity)passenger;
//            PacketHelper.sendTo(entityplayer3, new ClientHorsePacket(this.prevX, this.prevZ, this.prevY));
        }
        if(world.isRemote && passenger != null && getTamed()){
//            PacketHelper.send(new RidingHorsePacket(passenger.velocityX, passenger.velocityY, passenger.velocityZ,passenger.yaw, passenger.pitch, ((PlayerEntity)passenger).jumping));
        }
        if(checkWaterCollisions())
        {
            if(passenger != null)
            {
                velocityX += passenger.velocityX * (HorseSpeed / 2D);
                velocityZ += passenger.velocityZ * (HorseSpeed / 2D);
                PlayerEntity entityplayer = (PlayerEntity)passenger;
                if(entityplayer.jumping && !isjumping)
                {
                    velocityY += 0.5D;
                    isjumping = true;
                }
                move(velocityX, velocityY, velocityZ);
                if(onGround)
                {
                    isjumping = false;
                }
                pitch = passenger.pitch * 0.5F;
                if(random.nextInt(20) == 0)
                {
                    yaw = passenger.yaw;
                }
                setRotation(yaw, pitch);
                if(!getTamed())
                {
                    entityplayer.setVehicle(null);
                    passenger = null;
                    setJokey(false);
                }
            }
            double d = y;
            moveNonSolid(f, f1, 0.02F);
            move(velocityX, velocityY, velocityZ);
            velocityX *= 0.80000001192092896D;
            velocityY *= 0.80000001192092896D;
            velocityZ *= 0.80000001192092896D;
            velocityY -= 0.02D;
            if(horizontalCollision && getEntitiesInside(velocityX, ((velocityY + 0.60000002384185791D) - y) + d, velocityZ))
            {
                velocityY = 0.30000001192092901D;
            }
        } else
        if(isTouchingLava())
        {
            if(passenger != null)
            {
                velocityX += passenger.velocityX * (HorseSpeed / 2D);
                velocityZ += passenger.velocityZ * (HorseSpeed / 2D);
                PlayerEntity entityplayer1 = (PlayerEntity)passenger;
                if(entityplayer1.jumping && !isjumping)
                {
                    velocityY += 0.5D;
                    isjumping = true;
                }
                move(velocityX, velocityY, velocityZ);
                if(onGround)
                {
                    isjumping = false;
                }
                pitch = passenger.pitch * 0.5F;
                if(random.nextInt(20) == 0)
                {
                    yaw = passenger.yaw;
                }
                setRotation(yaw, pitch);
                if(!getTamed())
                {
                    entityplayer1.setVehicle(null);
                    passenger = null;
                    setJokey(false);
                }
            }
            double d1 = y;
            moveNonSolid(f, f1, 0.02F);
            move(velocityX, velocityY, velocityZ);
            velocityX *= 0.5D;
            velocityY *= 0.5D;
            velocityZ *= 0.5D;
            velocityY -= 0.02D;
            if(horizontalCollision && getEntitiesInside(velocityX, ((velocityY + 0.60000002384185791D) - y) + d1, velocityZ))
            {
                velocityY = 0.30000001192092901D;
            }
        } else
        {
            float f2 = 0.91F;
            if(onGround)
            {
                f2 = 0.5460001F;
                int i = world.getBlockId(MathHelper.floor(x), MathHelper.floor(boundingBox.minY) - 1, MathHelper.floor(z));
                if(i > 0)
                {
                    f2 = Block.BLOCKS[i].slipperiness * 0.91F;
                }
            }
            float f3 = 0.162771F / (f2 * f2 * f2);
            moveNonSolid(f, f1, onGround ? 0.1F * f3 : 0.02F);
            f2 = 0.91F;
            if(onGround)
            {
                f2 = 0.5460001F;
                int j = world.getBlockId(MathHelper.floor(x), MathHelper.floor(boundingBox.minY) - 1, MathHelper.floor(z));
                if(j > 0)
                {
                    f2 = Block.BLOCKS[j].slipperiness * 0.91F;
                }
            }
            if(isOnLadder())
            {
                fallDistance = 0.0F;
                if(velocityY < -0.14999999999999999D)
                {
                    velocityY = -0.14999999999999999D;
                }
            }
            if(passenger != null && !getTamed() && !world.isRemote)
            {
                if(random.nextInt(5) == 0 && !isjumping)
                {
                    velocityY += 0.40000000000000002D;
                    isjumping = true;
                }
                if(random.nextInt(10) == 0)
                {
                    velocityX += random.nextDouble() / 30D;
                    velocityZ += random.nextDouble() / 10D;
                }
                move(velocityX, velocityY, velocityZ);
                if(random.nextInt(50) == 0)
                {
                    PlayerEntity entityplayer = (PlayerEntity)passenger;
                    world.playSound(this, "mocreatures:horsemad", 1.0F, 1.0F + (random.nextFloat() - random.nextFloat()) * 0.2F);
                    sendSound(world,"mocreatures:horsemad", 1.0F, 1.0F + (random.nextFloat() - random.nextFloat()) * 0.2F );
                    entityplayer.velocityY += 0.9D;
                    entityplayer.velocityZ -= 0.3D;
                    entityplayer.setVehicle(null);
                    passenger = null;
                    setJokey(false);
                    if (FabricLoader.INSTANCE.getEnvironmentType() == EnvType.SERVER){
                        PacketHelper.sendTo(entityplayer, new JokeyPacket(0));
                    }
                }
                if(onGround)
                {
                    isjumping = false;
                }
                if(random.nextInt(temper * 8) == 0)
                {
                    setTamed(true);
                    PlayerEntity milosc = (PlayerEntity)passenger;
                    setOwner(milosc.name);
                    setNameWithGui(this, (PlayerEntity)passenger);
                    if(getType() == 8)
                    {
                        milosc.incrementStat(mod_mocreatures.WilfFlyingWest);
                    }
                }
            }
            if(passenger != null && getTamed())
            {
                boundingBox.maxY = passenger.boundingBox.maxY;
                velocityX += passenger.velocityX * HorseSpeed;
                velocityZ += passenger.velocityZ * HorseSpeed;
                PlayerEntity entityplayer2 = (PlayerEntity)passenger;
                if(entityplayer2.jumping && !isjumping)
                {
                    velocityY += HorseJump;
                    isjumping = true;
                }
                if(entityplayer2.jumping && (getType() == 5 || getType() == 8))
                {
                    velocityY += 0.10000000000000001D;
                }
                move(velocityX, velocityY, velocityZ);
                if(onGround)
                {
                    isjumping = false;
                }
                prevYaw = yaw = passenger.yaw;
                pitch = passenger.pitch * 0.5F;
                setRotation(yaw, pitch);
            }
            move(velocityX, velocityY, velocityZ);
            if(horizontalCollision && isOnLadder())
            {
                velocityY = 0.20000000000000001D;
            }
            if((getType() == 5 || getType() == 8) && passenger != null && getTamed())
            {
                velocityY -= 0.080000000000000002D;
                velocityY *= 0.60000000000000009D;
            } else
            {
                velocityY -= 0.080000000000000002D;
                velocityY *= 0.98000001907348633D;
            }
            velocityX *= f2;
            velocityZ *= f2;
        }
        lastWalkAnimationSpeed = walkAnimationSpeed;
        double d2 = x - prevX;
        double d3 = z - prevZ;
        float f4 = MathHelper.sqrt(d2 * d2 + d3 * d3) * 4F;
        if(f4 > 1.0F)
        {
            f4 = 1.0F;
        }
        walkAnimationSpeed += (f4 - walkAnimationSpeed) * 0.4F;
        walkAnimationProgress += walkAnimationSpeed;
    }

    @Override
    public Identifier getHandlerIdentifier() {
        return Identifier.of(mod_mocreatures.MOD_ID, "AirShipHorse");
    }
}
