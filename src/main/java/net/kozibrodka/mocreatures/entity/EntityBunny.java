package net.kozibrodka.mocreatures.entity;


import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.fabricmc.loader.FabricLoader;
import net.kozibrodka.mocreatures.events.mod_mocreatures;
import net.kozibrodka.mocreatures.mocreatures.MoCreatureNamed;
import net.kozibrodka.mocreatures.mocreatures.MoCreatureRacial;
import net.kozibrodka.mocreatures.network.RopePacket;
import net.minecraft.entity.Entity;
import net.minecraft.entity.mob.MonsterEntity;
import net.minecraft.entity.passive.AnimalEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.nbt.NbtCompound;
import net.minecraft.world.World;
import net.modificationstation.stationapi.api.network.packet.PacketHelper;
import net.modificationstation.stationapi.api.util.Identifier;
import net.modificationstation.stationapi.api.server.entity.MobSpawnDataProvider;

import java.util.List;
import java.util.Objects;


public class EntityBunny extends AnimalEntity implements MobSpawnDataProvider,  MoCreatureNamed
{

    public EntityBunny(World world)
    {
        super(world);
        a = false;
        movementSpeed = 1.5F;
        standingEyeHeight = -0.16F;
        setBoundingBoxSpacing(0.4F, 0.4F);
        health = 4;
        j = random.nextInt(64);
        i = 0;
        killedByOtherEntity = true;
        if(!world.isRemote){
            setTypeSpawn();
        }
    }

    @Override
    public boolean shouldRender(double distance) {
        if(getPicked()){
//            return distance < mocr.fullRenderDist;
            return true;

        }else{
            return super.shouldRender(distance);
        }
    }

    @Override
    public void onCollision(Entity otherEntity) {
        if(vehicle instanceof PlayerEntity && otherEntity == vehicle.vehicle){
        }else {
            super.onCollision(otherEntity);
        }
    }

    @Override
    @Environment(EnvType.CLIENT)
    public void setPositionAndAnglesAvoidEntities(double x, double y, double z, float pitch, float yaw, int interpolationSteps) {
        this.standingEyeHeight = -0.16F;
        this.lerpX = x;
        this.lerpY = y;
        this.lerpZ = z;
        this.lerpYaw = pitch;
        this.lerpPitch = yaw;
        this.bodyTrackingIncrements = interpolationSteps;
    }

    @Override
    @Environment(EnvType.CLIENT)
    public String getTexture() {
        return switch (getType()) {
            case 1 -> "/assets/mocreatures/stationapi/textures/mob/bunny.png";
            case 2 -> "/assets/mocreatures/stationapi/textures/mob/bunnyb.png";
            case 3 -> "/assets/mocreatures/stationapi/textures/mob/bunnyc.png";
            case 4 -> "/assets/mocreatures/stationapi/textures/mob/bunnyd.png";
            default -> "";
        };
    }

    public int getRandomRace(){
        int k = random.nextInt(100);
        if(k <= 25)
        {
            return 1;
        } else
        if(k <= 50)
        {
            return 2;
        } else
        if(k <= 75)
        {
            return 3;
        } else
        {
            return 4;
        }
    }

    @Override
    public void tickMovement()
    {
        if(!getAdult() && random.nextInt(200) == 0 && !world.isRemote)
        {
            setAge(getAge()+0.01F);
            if(getAge() >= 1.0F)
            {
                setAdult(true);
            }
        }
        super.tickMovement();
        if(getPicked()){
            velocityY = 0.0D;
            lastWalkAnimationSpeed = 0.0F;
            walkAnimationSpeed = 0.0F;
            walkAnimationProgress = 0.0F;
        }
    }

    @Override
    public void tick()
    {
        super.tick();
        if(world.isRemote){
            return;
        }
        if(!getTamed() || !getAdult() || vehicle != null)
        {
            return;
        }
        if(j < 1023)
        {
            j++;
        } else
        if(i < 127)
        {
            i++;
        } else
        {
            int k = 0;
            List list = world.getEntities(this, boundingBox.expand(16D, 16D, 16D));
            for(int l = 0; l < list.size(); l++)
            {
                Entity entity = (Entity)list.get(l);
                if(entity instanceof EntityBunny)
                {
                    k++;
                }
            }

            if(k > 12)
            {
                proceed();
                return;
            }
            List list1 = world.getEntities(this, boundingBox.expand(1.0D, 1.0D, 1.0D));
            boolean flag = false;
            for(int i1 = 0; i1 < list.size(); i1++)
            {
                Entity entity1 = (Entity)list1.get(i1);
                if(!(entity1 instanceof EntityBunny entitybunny) || entity1 == this)
                {
                    continue;
                }
                if(entitybunny.vehicle != null || entitybunny.j < 1023 || !entitybunny.getAdult())
                {
                    continue;
                }
                EntityBunny entitybunny1 = new EntityBunny(world);
                entitybunny1.setPosition(x, y, z);
                entitybunny1.setAdult(false);
                entitybunny1.setTypeSpawn(); ///
                world.spawnEntity(entitybunny1);
                world.playSound(this, "mob.chickenplop", 1.0F, (random.nextFloat() - random.nextFloat()) * 0.2F + 1.0F);
                world.broadcastEntityEvent(this, (byte)8);
                proceed();
                entitybunny.proceed();
                flag = true;
                break;
            }

            if(!flag)
            {
                k = random.nextInt(16);
            }
        }
    }

    @Override
    protected void onLanding(float f)
    {
    }

    @Override
    protected void tickLiving()
    {
        if(onGround && (velocityX > 0.050000000000000003D || velocityZ > 0.050000000000000003D || velocityX < -0.050000000000000003D || velocityZ < -0.050000000000000003D))
        {
            velocityY = 0.45000000000000001D;
        }
        if(onGround && pickedSd)
        {
            pickedSd = false;
            world.playSound(this, "mocreatures:rabbitland", 1.0F, (random.nextFloat() - random.nextFloat()) * 0.2F + 1.0F);
            world.broadcastEntityEvent(this, (byte)7);
            List list = world.getEntities(this, boundingBox.expand(12D, 12D, 12D));
            for(int k = 0; k < list.size(); k++)
            {
                Entity entity = (Entity)list.get(k);
                if(entity instanceof MonsterEntity entitymob)
                {
                    entitymob.target = (this);
                }
            }

        }
        if(onGround && getPicked() && this.vehicle == null)
        {
            setPicked(false);
        }
        super.tickLiving();
    }

    @Override
    public boolean interact(PlayerEntity entityplayer)
    {
        if(world.isRemote){
            return false;
        }
        if(entityplayer.passenger != null && entityplayer.passenger != this){
            return false;
        }
        if(vehicle instanceof PlayerEntity && !Objects.equals(((PlayerEntity) vehicle).name, entityplayer.name)){
            return false;
        }
        if(vehicle == null)
        {
            yaw = entityplayer.yaw;
            setVehicle(entityplayer);
            setPicked(true);
            pickedSd = true;
            setTamed(true);

        } else
        {
            setVehicle(null);
            world.playSound(this, "mocreatures:rabbitlift", 1.0F, (random.nextFloat() - random.nextFloat()) * 0.2F + 1.0F);
            world.broadcastEntityEvent(this, (byte)6);
            setPicked(false);
        }
        if (net.fabricmc.loader.FabricLoader.INSTANCE.getEnvironmentType() == EnvType.SERVER) {
            PacketHelper.sendTo(entityplayer, new RopePacket("bunny", this.id, entityplayer.name));
        }
        velocityX = entityplayer.velocityX * 5D;
        velocityY = entityplayer.velocityY / 2D + 0.5D;
        velocityZ = entityplayer.velocityZ * 5D;
        return true;
    }

    @Override
    public double getStandingEyeHeight()
    {
        if(vehicle instanceof PlayerEntity)
        {
            if (FabricLoader.INSTANCE.getEnvironmentType() == EnvType.SERVER){
                return standingEyeHeight + 0.49F;
            }else{
                return standingEyeHeight - 1.15F;
            }
        } else
        {
            return standingEyeHeight;
        }
    }

    @Override
    protected String getRandomSound()
    {
        return null;
    }

    public void proceed()
    {
        i = 0;
        j = random.nextInt(64);
    }

    @Override
    protected String getHurtSound()
    {
        return "mocreatures:rabbithurt";
    }

    @Override
    public void applyKnockback(Entity entity, int k, double d, double d2)
    {
        super.applyKnockback(entity, k, d, d2);
    }

    @Override
    protected String getDeathSound()
    {
        return "mocreatures:rabbitdeath";
    }

    public boolean maxNumberReached()
    {
        int k = 0;
        for(int l = 0; l < world.entities.size(); l++)
        {
            Entity entity = (Entity)world.entities.get(l);
            if(entity instanceof EntityBunny)
            {
                k++;
            }
        }

        return false;
    }

    @Override
    public void writeNbt(NbtCompound nbttagcompound)
    {
        super.writeNbt(nbttagcompound);
        nbttagcompound.putInt("TypeInt", getType());
        nbttagcompound.putBoolean("Tamed", getTamed());
        nbttagcompound.putFloat("Edad", getAge());
        nbttagcompound.putBoolean("Adult", getAdult());
    }

    @Override
    public void readNbt(NbtCompound nbttagcompound)
    {
        super.readNbt(nbttagcompound);
        setType(nbttagcompound.getInt("TypeInt"));
        setAge(nbttagcompound.getFloat("Edad"));
        setTamed(nbttagcompound.getBoolean("Tamed"));
        setAdult(nbttagcompound.getBoolean("Adult"));
    }

    @Override
    protected void initDataTracker() {
        super.initDataTracker();
        dataTracker.startTracking(16, (byte) 0); //Type
        dataTracker.startTracking(17, 0); //Age
        dataTracker.startTracking(18, (byte) 0); //Adult
        dataTracker.startTracking(19, (byte) 0); //Tamed
        dataTracker.startTracking(20, (byte) 0); //Picked
    }

    @Override
    public boolean canSpawn()
    {
        return mod_mocreatures.mocGlass.animals.bunnyfreq > 0 && super.canSpawn();
    }

    @Override
    protected boolean canDespawn()
    {
        return !getTamed();
    }

    public boolean a;
    public float d1;
    public int j;
    public int i;
    public boolean pickedSd;

    @Override
    public Identifier getHandlerIdentifier() {
        return Identifier.of(mod_mocreatures.MOD_ID, "Bunny");
    }

    @Override
    @Environment(EnvType.CLIENT)
    public void processServerEntityStatus(byte status) {
        if (status == 6) {
            world.playSound(this, "mocreatures:rabbitlift", 1.0F, 1.0F + (random.nextFloat() - random.nextFloat()) * 0.2F);
        }  else if (status == 7){
            world.playSound(this, "mocreatures:rabbitland", 1.0F, 1.0F + (random.nextFloat() - random.nextFloat()) * 0.2F);
        }  else if (status == 8){
            world.playSound(this, "mob:chickenplop", 1.0F, 1.0F + (random.nextFloat() - random.nextFloat()) * 0.2F);
        }  else {
            super.processServerEntityStatus(status);
        }
    }

    //TYPE
    public void setTypeSpawn()
    {
        if(!world.isRemote){
            int type = getRandomRace();
            setType(type);
            setAge(0.5F);
        }
    }

    public void setType(int type)
    {
        if(!world.isRemote) {
            dataTracker.set(16, (byte) type);
        }
    }

    public int getType()
    {
        return dataTracker.getByte(16);
    }
    //AGE
    public void setAge(float age)
    {
        dataTracker.set(17, Float.floatToRawIntBits(age));
    }

    public float getAge()
    {
        return Float.intBitsToFloat(dataTracker.getInt(17));
    }
    //ADULT
    public boolean getAdult()
    {
        return (dataTracker.getByte(18) & 1) != 0;
    }

    public void setAdult(boolean flag)
    {
        if(flag)
        {
            dataTracker.set(18, (byte) 1);
        } else
        {
            dataTracker.set(18, (byte) 0);
        }
    }

    //TAMED
    @Override
    public boolean getTamed()
    {
        return (dataTracker.getByte(19) & 1) != 0;
    }

    public void setTamed(boolean flag)
    {
        if(flag)
        {
            dataTracker.set(19, (byte) 1);
        } else
        {
            dataTracker.set(19, (byte) 0);
        }
    }

    //Picked
    public boolean getPicked()
    {
        return (dataTracker.getByte(20) & 1) != 0;
    }

    public void setPicked(boolean flag)
    {
        if(flag)
        {
            dataTracker.set(20, (byte) 1);
        } else
        {
            dataTracker.set(20, (byte) 0);
        }
    }


    @Override
    public void setName(String name) {}

    @Override
    public String getName() {return "";}

    @Override
    public String getOwner() {return "";}
}
