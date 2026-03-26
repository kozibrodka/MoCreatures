package net.kozibrodka.mocreatures.entity;

import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.fabricmc.loader.FabricLoader;
import net.kozibrodka.mocreatures.events.TextureListener;
import net.kozibrodka.mocreatures.events.mod_mocreatures;
import net.kozibrodka.mocreatures.mocreatures.CREEPSFxSpit;
import net.kozibrodka.mocreatures.mocreatures.MoCreatureRacial;
import net.kozibrodka.mocreatures.network.CustomParticlePacket;
import net.kozibrodka.mocreatures.network.RopePacket;
import net.minecraft.block.Block;
import net.minecraft.client.Minecraft;
import net.minecraft.client.particle.Particle;
import net.minecraft.entity.Entity;
import net.minecraft.entity.passive.AnimalEntity;
import net.minecraft.entity.player.ClientPlayerEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.player.ServerPlayerEntity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NbtCompound;
import net.minecraft.util.math.MathHelper;
import net.minecraft.world.World;
import net.modificationstation.stationapi.api.network.packet.PacketHelper;
import net.modificationstation.stationapi.api.server.entity.MobSpawnDataProvider;
import net.modificationstation.stationapi.api.util.Identifier;

import java.util.List;

public class EntityCamel extends AnimalEntity implements MobSpawnDataProvider, MoCreatureRacial {

    public EntityCamel(World world)
    {
        super(world);
        texture = "/assets/mocreatures/stationapi/textures/mob/camel.png";
        setBoundingBoxSpacing(1.5F, 3F);
        health = 30;
        attack = 2;
        attackrange = 16D;
        movementSpeed = 0.45F;
        spittimer = 30;
    }

    protected float getPathfindingFavor(int i, int j, int k)
    {
        if(world.getBlockId(i, j - 1, k) == Block.SAND.id || world.getBlockId(i, j - 1, k) == Block.GRAVEL.id)
        {
            return 10F;
        } else
        {
            return -(float)j;
        }
    }

    public void tickMovement() {
        if(!typechosen && world.isRemote && getType() != 0){
            typechosen = true;
            chooseType(getType());
        }
        super.tickMovement();
    }

    public boolean damage(Entity entitybase, int i)
    {
        if(super.damage(entitybase, i))
        {
            if(passenger == entitybase || vehicle == entitybase)
            {
                return true;
            }
            if(entitybase != this && world.difficulty > 0)
            {
                target = entitybase;

            }
            return true;
        } else
        {
            return false;
        }
    }

    protected void attack(Entity entity, float f)
    {
        if(onGround)
        {
            double d = entity.x - x;
            double d2 = entity.z - z;
            float f1 = MathHelper.sqrt(d * d + d2 * d2);
            velocityX = (d / (double)f1) * 0.20000000000000001D * (0.850000011920929D + velocityX * 0.20000000298023224D);
            velocityZ = (d2 / (double)f1) * 0.20000000000000001D * (0.80000001192092896D + velocityZ * 0.20000000298023224D);
            velocityY = 0.10000000596246449D;
            fallDistance = -25F;
        }
        if((double)f > 2D && (double)f < 7D && (entity instanceof PlayerEntity) && spittimer-- < 0)
        {
            world.playSound(this, "mocreatures:camelspits", 1.0F, (random.nextFloat() - random.nextFloat()) * 0.2F + 1.0F);
            if (net.fabricmc.loader.FabricLoader.INSTANCE.getEnvironmentType() == EnvType.SERVER){
                mocr.voicePacket(world, "mocreatures:camelspits", this.id, 1.0F, (random.nextFloat() - random.nextFloat()) * 0.2F + 1.0F);
            }
            spittimer = 30;
            lookAt(entity, 360F, 0.0F);
            double d1 = -MathHelper.sin((yaw * 3.141593F) / 180F);
            double d3 = MathHelper.cos((yaw * 3.141593F) / 180F);
            for(int i = 0; i < 40; i++)
            {
//                world.addParticle();
                if(FabricLoader.INSTANCE.getEnvironmentType() == EnvType.SERVER){
                    sendPartPacket(world, x + d1 * 3.5D, y + 2.4000000953674316D, z + d3 * 3.5D);
                }else{
                    sendPartClient(world, x + d1 * 3.5D, y + 2.4000000953674316D, z + d3 * 3.5D);

//                    CREEPSFxSpit creepsfxspit = new CREEPSFxSpit(world, x + d1 * 4.5D, y + 2.4000000953674316D, z + d3 * 4.5D, TextureListener.bubble_particle);

//                    CREEPSFxSpit creepsfxspit = new CREEPSFxSpit(world, x + d1 * 3.5D, y + 2.4000000953674316D, z + d3 * 3.5D, TextureListener.bubble_particle);
//                    creepsfxspit.renderDistanceMultiplier = 10D;
//                    addClientParticle(creepsfxspit);
                }
            }

            entity.damage(this, 1);
        }
        if((double)f < 3.2999999999999998D && entity.boundingBox.maxY > boundingBox.minY && entity.boundingBox.minY < boundingBox.maxY)
        {
            attackCooldown = 20;
            entity.damage(this, attack);
        }
    }

    @Environment(EnvType.CLIENT)
    public void addClientParticle(Particle particle){
        Minecraft.INSTANCE.particleManager.addParticle(particle);
    }

    @Environment(EnvType.SERVER)
    public void sendPartPacket(World world, double x, double y, double z) {
        List list2 = world.players;
        if (list2.size() != 0) {
            for (int k = 0; k < list2.size(); k++) {
                ServerPlayerEntity player1 = (ServerPlayerEntity) list2.get(k);
                PacketHelper.sendTo(player1, new CustomParticlePacket(1, x, y, z));
            }
        }
    }

    @Environment(EnvType.CLIENT)
    public void sendPartClient(World world, double x, double y, double z) {
        List list2 = world.players;
        if (list2.size() != 0) {
            for (int k = 0; k < list2.size(); k++) {
                ClientPlayerEntity player1 = (ClientPlayerEntity) list2.get(k);
                PacketHelper.sendTo(player1, new CustomParticlePacket(1, x, y, z));
            }
        }
    }

    protected void dropItems()
    {
        int i = random.nextInt(3);
        for(int j = 0; j < i; j++)
        {
            dropItem(new ItemStack(getDroppedItemId(), 1, 0), 0.0F);
        }

        int i2 = random.nextInt(2);
        for(int j2 = 0; j2 < i2; j2++)
        {
            dropItem(new ItemStack(Block.WATER.id, 1, 0), 0.0F);
        }

    }

    protected int getDroppedItemId()
    {
        return Item.LEATHER.id;
    }

    public void markDead()
    {
        super.markDead();
    }

    protected void initDataTracker()
    {
        super.initDataTracker();
        dataTracker.startTracking(16, (byte) 0); //Type
    }

    public void writeNbt(NbtCompound nbttagcompound)
    {
        super.writeNbt(nbttagcompound);
        nbttagcompound.putInt("TypeInt", getType());
    }

    public void readNbt(NbtCompound nbttagcompound)
    {
        super.readNbt(nbttagcompound);
        setType(nbttagcompound.getInt("TypeInt"));
    }

    public int getLimitPerChunk()
    {
        return 4;
    }

    protected String getRandomSound()
    {
        return "mocreatures:camel";
    }

    protected String getHurtSound()
    {
        return "mocreatures:camelhurt";
    }

    protected String getDeathSound()
    {
        return "mocreatures:cameldeath";
    }

    public boolean canSpawn()
    {
        return mocr.mocreaturesGlass.animals.camelfreq > 0 && super.canSpawn();
    }

    mod_mocreatures mocr = new mod_mocreatures();
    public int spittimer;
    protected int attack;
    protected double attackrange;
    public boolean typechosen;

    @Override
    public Identifier getHandlerIdentifier() {return Identifier.of(mod_mocreatures.MOD_ID, "Camel");}

    //TYPE
    public void setTypeSpawn()
    {
        if(!world.isRemote){
            int type = getRandomRace();
            setType(type);
        }
    }

    public void setType(int type)
    {
        if(!world.isRemote) {
            dataTracker.set(16, (byte) type);
            chooseType(type);
        }
    }

    public int getType()
    {
        return dataTracker.getByte(16);
    }

    public void chooseType(int typeint)
    {
        if(typeint == 1)
        {
            texture = "/assets/mocreatures/stationapi/textures/mob/camel.png";
        } else
        if(typeint == 2)
        {
            texture = "/assets/mocreatures/stationapi/textures/mob/camelbrown.png";
        } else
        if(typeint == 3)
        {
            texture = "/assets/mocreatures/stationapi/textures/mob/camelgrey.png";
        } else
        if(typeint == 4)
        {
            texture = "/assets/mocreatures/stationapi/textures/mob/camelblack.png";
        } else
        if(typeint == 5)
        {
            texture = "/assets/mocreatures/stationapi/textures/mob/camelwhite.png";
        } else
        if(typeint == 6)
        {
            texture = "/assets/mocreatures/stationapi/textures/mob/camel256.png";
        }
    }

    public int getRandomRace(){
        int i = random.nextInt(100);
        if(i <= 40)
        {
            return 1;
        } else
        if(i <= 55)
        {
            return 2;
        } else
        if(i <= 70)
        {
            return 3;
        } else
        if(i <= 85)
        {
            return 4;
        } else
        if(i <= 99)
        {
            return 5;
        } else
        {
            return 6;
        }
    }
}
