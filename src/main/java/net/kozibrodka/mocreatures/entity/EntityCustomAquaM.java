package net.kozibrodka.mocreatures.entity;

import net.fabricmc.api.EnvType;
import net.fabricmc.loader.FabricLoader;
import net.kozibrodka.mocreatures.events.mod_mocreatures;
import net.kozibrodka.mocreatures.mocreatures.MoCTools;
import net.kozibrodka.mocreatures.network.ClientHorsePacket;
import net.kozibrodka.mocreatures.network.JokeyPacket;
import net.kozibrodka.mocreatures.network.ServerRidingPacket;
import net.minecraft.block.material.Material;
import net.minecraft.entity.Entity;
import net.minecraft.entity.ItemEntity;
import net.minecraft.entity.WaterCreatureEntity;
import net.minecraft.entity.ai.pathing.Path;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.Item;
import net.minecraft.nbt.NbtCompound;
import net.minecraft.util.math.MathHelper;
import net.minecraft.world.World;
import net.modificationstation.stationapi.api.network.packet.PacketHelper;

import java.util.List;

public class EntityCustomAquaM extends WaterCreatureEntity {

    mod_mocreatures mocr = new mod_mocreatures();
    private Path pathEntity;
    private int outOfWater = 0;
    private int temper;
    private float edad;
    private boolean displayName;
    private boolean isAdult;
//    private boolean isTamed;
    private int type;
    private String myName = "";
    private boolean chosenType;
    private int maxHealth;
    private boolean diving;
    private int divingCount;
    private boolean maJump;
    private boolean tamed;
    
    public EntityCustomAquaM(World world) {
        super(world);
        tamed = false;
        temper = 50;
    }

    public boolean gettingOutOfWater() {
        int i = (int)x;
        int j = (int)y;
        int k = (int)z;
        int l = 1;
        l = world.getBlockId(i, j + 1, k);
        return l == 0;
    }

    public void travel(float f, float f1) {

        if (FabricLoader.INSTANCE.getEnvironmentType() == EnvType.SERVER && passenger != null) {
            PlayerEntity entityplayer3 = (PlayerEntity)passenger;
            PacketHelper.sendTo(entityplayer3, new ClientHorsePacket(this.prevX, this.prevZ, this.prevY));
        }
        if(world.isRemote && passenger != null && getTamed()){
            PlayerEntity entityplayer3 = (PlayerEntity)passenger;
            PacketHelper.send(new ServerRidingPacket(entityplayer3.velocityX, entityplayer3.velocityY, entityplayer3.velocityZ,entityplayer3.yaw, entityplayer3.pitch, entityplayer3.jumping));
        }

        if(passenger != null && !getTamed() && !world.isRemote) {
            if(random.nextInt(5) == 0 && !maJump) {
                velocityY += 0.4D;
                maJump = true;
            }

            if(random.nextInt(10) == 0) {
                velocityX += random.nextDouble() / 30.0D;
                velocityZ += random.nextDouble() / 10.0D;
            }

            move(velocityX, velocityY, velocityZ);
            if(random.nextInt(50) == 0) {
//                world.playSound(this, getUpsetSound(), 1.0F, 1.0F + (random.nextFloat() - random.nextFloat()) * 0.2F);
//                passenger.velocityY += 0.9D;
//                passenger.velocityZ -= 0.3D;
//                passenger = null;
                PlayerEntity entityplayer = (PlayerEntity)passenger;
                world.playSound(this, getUpsetSound(), 1.0F, 1.0F + (random.nextFloat() - random.nextFloat()) * 0.2F);
                sendSound(world, getUpsetSound(), 1.0F, 1.0F + (random.nextFloat() - random.nextFloat()) * 0.2F );
                entityplayer.velocityY += 0.9D;
                entityplayer.velocityZ -= 0.3D;
                entityplayer.setVehicle(null);
                passenger = null;
                setJokey(false);
                if (FabricLoader.INSTANCE.getEnvironmentType() == EnvType.SERVER){
                    PacketHelper.sendTo(entityplayer, new JokeyPacket(0));
                }
            }

            if(onGround) {
                maJump = false;
            }

            if(random.nextInt(tametemper() * 8) == 0) {
                setTamed(true);
                PlayerEntity milosc = (PlayerEntity)passenger;
                setOwner(milosc.name);
            }
        } else if(passenger != null && getTamed()) {
            boundingBox.maxY = passenger.boundingBox.maxY;
            velocityX += passenger.velocityX * (speed()); // (speed() / 10.0D
            velocityZ += passenger.velocityZ * (speed()); // (speed() / 10.0D
            if(!world.isRemote) {
                move(velocityX, velocityY, velocityZ);
            }

            pitch = passenger.pitch * 0.5F;
            prevYaw = yaw = passenger.yaw;
            setRotation(yaw, pitch);

            float f2 = 0.8F; //0.55F
            velocityX *= f2;
            velocityZ *= f2;
        }

        super.travel(f, f1);
    }

    protected boolean MoveToNextEntity(Entity entity)
    {
        if(entity != null)
        {
            int i = MathHelper.floor(entity.x);
            int j = MathHelper.floor(entity.y);
            int k = MathHelper.floor(entity.z);
            faceItem(i, j, k, 30F);
            if(x < (double)i)
            {
                double d = entity.x - x;
                if(d > 0.5D)
                {
                    velocityX += 0.05D;
                }
            } else
            {
                double d1 = x - entity.x;
                if(d1 > 0.5D)
                {
                    velocityX -= 0.05D;
                }
            }
            if(z < (double)k)
            {
                double d2 = entity.z - z;
                if(d2 > 0.5D)
                {
                    velocityZ += 0.05D;
                }
            } else
            {
                double d3 = z - entity.z;
                if(d3 > 0.5D)
                {
                    velocityZ -= 0.05D;
                }
            }
            return true;
        } else
        {
            return false;
        }
    }

    public void faceItem(int i, int j, int k, float f)
    {
        double d = (double)i - x;
        double d1 = (double)k - z;
        double d2 = (double)j - y;
        double d3 = MathHelper.sqrt(d * d + d1 * d1);
        float f1 = (float)((Math.atan2(d1, d) * 180D) / 3.1415927410125728D) - 90F;
        float f2 = (float)((Math.atan2(d2, d3) * 180D) / 3.1415927410125728D);
        pitch = -b(pitch, f2, f);
        yaw = b(yaw, f1, f);
    }

    public float b(float f, float f1, float f2) {
        float f3;
        for(f3 = f1 - f; f3 < -180.0F; f3 += 360.0F) {
        }

        while(f3 >= 180.0F) {
            f3 -= 360.0F;
        }

        if(f3 > f2) {
            f3 = f2;
        }

        if(f3 < -f2) {
            f3 = -f2;
        }

        return f + f3;
    }

    protected void tickLiving()
    {
        super.tickLiving();
        if(target == null){
            return;
        }
        if(!target.isAlive() || !target.submergedInWater) /// LOL delfin atakuje pod wodą ostro
        {
            target = null;
        }
    }

    public void floating() {
        float distY = MoCTools.distanceToSurface(this);
        if(passenger != null) {
            PlayerEntity ep = (PlayerEntity)passenger;
            if(ep.jumping) {
                velocityY += 0.09D;
            } else {
                velocityY = -0.008D;
            }

        } else if(target != null && target.y < y - 0.5D && getDistance(target) < 10.0F) {
            if(velocityY < -0.1D) {
                velocityY = -0.1D;
            }

        } else {
            if(distY >= 1.0F && !isDiving()) {
                if(velocityY < 0.0D) {
                    velocityY = 0.0D;
                }

                velocityY += 0.001D;
                if(distY > 1.0F) {
                    velocityY += (double)distY * 0.02D;
                    if(velocityY > 0.2D) {
                        velocityY = 0.2D;
                    }
                }
            } else if(velocityY < -0.05D) {
                velocityY = -0.05D;
            }

        }
    }

    protected void onLanding(float f) {
        if(!isSubmergedInWater()) {
            super.onLanding(f);
        }
    }

    public ItemEntity getClosestFish(Entity entity, double d) {
        double d1 = -1.0D;
        ItemEntity entityitem = null;
        List list = world.getEntities(this, boundingBox.expand(d, d, d));

        for(int i = 0; i < list.size(); ++i) {
            Entity entity1 = (Entity)list.get(i);
            if(entity1 instanceof ItemEntity) {
                ItemEntity entityitem1 = (ItemEntity)entity1;
                if(entityitem1.id == Item.RAW_FISH.id && entityitem1.isSubmergedInWater()) {
                    double d2 = entityitem1.getSquaredDistance(entity.x, entity.y, entity.z);
                    if((d < 0.0D || d2 < d * d) && (d1 == -1.0D || d2 < d1)) {
                        d1 = d2;
                        entityitem = entityitem1;
                    }
                }
            }
        }

        return entityitem;
    }


    public void tickMovement() {
        if(world.isRemote){
            super.tickMovement();
            return;
        }
        if(isSwimming()) {
            floating();
            outOfWater = 0;
        } else {
            ++outOfWater;
            if(outOfWater > 200 && outOfWater % 30 == 0) {
                velocityY += 0.3D;
                velocityX = (double)((float)(Math.random() * 0.2D - 0.1D));
                velocityZ = (double)((float)(Math.random() * 0.2D - 0.1D));
                damage(null, 1);
            }
        }

        if(!hasPath() && passenger == null && !isMovementBlocked()) {
            pathingUpdate();
        }

        if(!diving) {
            if(passenger == null && hasPath() && random.nextInt(500) == 0) {
                diving = true;
            }
        } else {
            ++divingCount;
            if(divingCount > 100 || passenger != null) {
                diving = false;
                divingCount = 0;
            }
        }

        super.tickMovement();
    }


    protected boolean isMovementBlocked() {
        return !isSwimming() && passenger == null;
    }

    public boolean checkWaterCollisions() {
        return world.updateMovementInFluid(boundingBox, Material.WATER, this);
    }

    public double speed() {
        return 1.5D;
    }

    public boolean isSubmergedInWater() {
        return false;
    }

    public boolean isDiving() {
        return diving;
    }

    protected void jump() {
    }

    public boolean isSwimming() {
        return isInFluid(Material.WATER);
    }

    public void writeNbt(NbtCompound nbttagcompound)
    {
        super.writeNbt(nbttagcompound);
    }

    public void readNbt(NbtCompound nbttagcompound) {super.readNbt(nbttagcompound);}

    protected String getDeathSound() {return null;}

    protected String getHurtSound() {return null;}

    protected String getRandomSound() {return null;}

    protected float getSoundVolume() {return 0.4F;}

    protected String getUpsetSound()
    {
        return null;
    }

    public int tametemper()
    {
        return temper;
    }

    public boolean getTamed()
    {
        return tamed;
    }

    public void setTamed(boolean flag)
    {
        tamed = flag;
    }

    public void setJokey(boolean flag) {
    }

    public void setOwner(String owner) {
    }

    public void setDisplayName(boolean flag) {
    }

    public void sendSound(World world, String name, float vol, float pit){
        if (net.fabricmc.loader.FabricLoader.INSTANCE.getEnvironmentType() == EnvType.SERVER){
            mocr.voicePacket(world, name, this.id, vol, pit);
        }
    }
    
}
