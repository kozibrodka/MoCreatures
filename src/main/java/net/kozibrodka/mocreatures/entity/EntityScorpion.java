package net.kozibrodka.mocreatures.entity;

import net.fabricmc.api.EnvType;
import net.kozibrodka.mocreatures.events.mod_mocreatures;
import net.kozibrodka.mocreatures.mocreatures.MoCTools;
import net.kozibrodka.mocreatures.mocreatures.MoCreatureRacial;
import net.kozibrodka.mocreatures.network.PoisonPacket;
import net.minecraft.block.material.Material;
import net.minecraft.entity.Entity;
import net.minecraft.entity.mob.MonsterEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.Item;
import net.minecraft.nbt.NbtCompound;
import net.minecraft.util.math.MathHelper;
import net.minecraft.world.World;
import net.modificationstation.stationapi.api.network.packet.PacketHelper;
import net.modificationstation.stationapi.api.server.entity.MobSpawnDataProvider;
import net.modificationstation.stationapi.api.util.Identifier;

public class EntityScorpion extends MonsterEntity implements MobSpawnDataProvider, MoCreatureRacial {

    public EntityScorpion(World world)
    {
        super(world);
        setBoundingBoxSpacing(1.4F, 0.9F);
        movementSpeed = 0.8F;
        health = 15;
        poisontimer = 0;
        typechosen = false;
    }

    public void tickMovement() {
        if(!typechosen && world.isRemote && getType() != 0){
            typechosen = true;
            chooseType(getType());
        }

        if(world.isRemote){
            super.tickMovement();
            return;
        }

        if(getIsPoisoning()) {
            ++poisontimer;
            if(poisontimer == 1) {
                world.playSound(this, "mocreatures:scorpionsting", 1.0F, 1.0F + (random.nextFloat() - random.nextFloat()) * 0.2F);
                sendSound(world, "mocreatures:scorpionsting", 1.0F, 1.0F + (random.nextFloat() - random.nextFloat()) * 0.2F);
            }

            if(poisontimer > 50) {
                poisontimer = 0;
                setIsPoisoning(false);
            }
        }

        if(random.nextInt(50) == 0) {
            swingArm();
        }

        if(!getAdult() && random.nextInt(200) == 0) {
            setAge(getAge() + 0.01F);
            if(getAge() >= 1.1F) {
                setAdult(true);
            }
        }

        if(climbing()){
            setClimbing(true);
        }else{
            setClimbing(false);
        }

        super.tickMovement();
    }

    public boolean damage(Entity damagesource, int i) {
        if(super.damage(damagesource, i)) {
            if(damagesource != null && damagesource instanceof PlayerEntity) {
                return false;
            } else {
                if(damagesource != null && damagesource != this && world.difficulty > 0 && getAdult()) {
                    target = damagesource;
                }

                return true;
            }
        } else {
            return false;
        }
    }

    protected Entity getTargetInRange() {
        if(world.difficulty > 0 && !world.canMonsterSpawn() && getAdult()) {
            PlayerEntity entityliving = world.getClosestPlayer(this, 12.0D); /// ovveride podobny do pająka? przez sciany aggro?
                if(entityliving != null) {
                    return entityliving;
                }
        }

        return null;
    }

    protected void attack(Entity entity, float f) {
        if(f > 2.0F && f < 6.0F && random.nextInt(50) == 0) {
            if(onGround) {
                double flag1 = entity.x - x;
                double d1 = entity.z - z;
                float f1 = MathHelper.sqrt(flag1 * flag1 + d1 * d1);
                velocityX = flag1 / (double)f1 * 0.5D * 0.8D + velocityX * 0.2D;
                velocityZ = d1 / (double)f1 * 0.5D * 0.8D + velocityZ * 0.2D;
                velocityZ = 0.4D;
            }
        } else if(attackCooldown <= 0 && (double)f < 3.0D && entity.boundingBox.maxY > boundingBox.minY && entity.boundingBox.minY < boundingBox.maxY) {
            attackCooldown = 20;
            boolean flag = entity instanceof PlayerEntity;
            if(!getIsPoisoning() && random.nextInt(8) == 0) {
                setIsPoisoning(true);
                if(getType() <= 2) {
                    if(flag) {
                        PacketHelper.sendTo((PlayerEntity) entity, new PoisonPacket(1));
                    }
                } else if(getType() == 4) {
                    if(flag) {
                        PacketHelper.sendTo((PlayerEntity) entity, new PoisonPacket(2));
                    }
                } else if(getType() == 3) {
                    if(flag) {
                        /// czerwony haze na podpalenie gdybym chcial
                    }
                    entity.fireTicks = 30;
                }
            } else {
                entity.damage((this), 1);
                swingArm();
            }
        }

    }

    public void swingArm() {
        if(!getIsSwinging()) {
            setIsSwinging(true);
            swingProgress = 0.0F;
        }

    }

    public void tick() {
        if(getIsSwinging()) {
            swingProgress += 0.2F;
            if(swingProgress == 0.6F) { // 0.6D
                world.playSound(this, "mocreatures:scorpionclaw", 1.0F, 1.0F + (random.nextFloat() - random.nextFloat()) * 0.2F);
            }

            if(swingProgress > 1.2F) {
                setIsSwinging(false);
                swingProgress = 0.0F;
            }
        }

        if(world.isRemote){
            if(!getIsSwinging() && swingProgress > 0.0F){
                swingProgress = 0.0F;
            }
        }

        super.tick();
    }

    public void onKilledBy(Entity damagesource) {
        if(!world.isRemote) {
            if (getAdult() && random.nextInt(5) == 0) {
                int k = random.nextInt(5);

                for (int i = 0; i < k; ++i) {
                    EntityScorpion entityscorpy = new EntityScorpion(world);
                    entityscorpy.setPosition(x, y, z);
                    world.spawnEntity(entityscorpy);

                    entityscorpy.setAdult(false); ///Czy małe powinny atakować??
                    entityscorpy.setAge(0.2F);
                    entityscorpy.setType(getType());

                    world.playSound(this, "mob.chickenplop", 1.0F, (random.nextFloat() - random.nextFloat()) * 0.2F + 1.0F);
                    sendSound(world, "mob.chickenplop", 1.0F, (random.nextFloat() - random.nextFloat()) * 0.2F + 1.0F);
                }
            }
        }
        super.onKilledBy(damagesource);
    }

    public boolean swingingTail() {
        return getIsPoisoning() && poisontimer < 15;
    }

    public boolean isOnLadder() {
        return horizontalCollision;
    }

    public boolean climbing() {
        return !onGround && isOnLadder();
    }

    public int getLimitPerChunk()
    {
        return 4;
    }

    protected int getDroppedItemId()
    {
        return Item.STRING.id;
    }

    protected String getRandomSound()
    {
        return "mocreatures:scorpiongrunt";
    }

    protected String getHurtSound()
    {
        return "mocreatures:scorpionhurt";
    }

    protected String getDeathSound()
    {
        return "mocreatures:scorpiondying";
    }

    public boolean canSpawn()
    {
        return mod_mocreatures.mocGlass.hostilemobs.scorpionfreq > 0 && super.canSpawn();
    }


    private int poisontimer;
    public boolean typechosen;
    public float swingProgress;


    @Override
    public Identifier getHandlerIdentifier() {return Identifier.of(mod_mocreatures.MOD_ID, "Scorpion");}

    @Override
    public void setTypeSpawn() {
        if(world.isRemote) {
            return;
        }
        byte i = 0;
        if(MoCTools.NearMaterialWithDistance(this, Double.valueOf(1.0D), Material.SNOW_LAYER)) {
            i = 4;
        } else if(world.dimension.isNether) {
            i = 3;
        }else if(MoCTools.NearMaterialWithDistance(this, Double.valueOf(1.0D), Material.SAND)){
            i = 1;
        }else{
            i = 2;
        }
        setAdult(true);
        setAge(1.1F);
        setType(i);
    }

    public void chooseType(int typeint)
    {
        if(typeint == 1)
        {
            texture = "/assets/mocreatures/stationapi/textures/mob/scorpiona.png";
        } else
        if(typeint == 2)
        {
            texture = "/assets/mocreatures/stationapi/textures/mob/scorpionb.png";
        } else
        if(typeint == 3)
        {
            texture = "/assets/mocreatures/stationapi/textures/mob/scorpionc.png";
        } else
        if(typeint == 4)
        {
            texture = "/assets/mocreatures/stationapi/textures/mob/scorpiond.png";
        }
    }

    protected void initDataTracker()
    {
        super.initDataTracker();
        dataTracker.startTracking(16, (byte) 0); //TypeInt
        dataTracker.startTracking(17, (int) 0); //Age
        dataTracker.startTracking(18, (byte) 0); //ADULT

        dataTracker.startTracking(19, (int) 0); //SwingProgress
        dataTracker.startTracking(20, (byte) 0); //isPoisoning
        dataTracker.startTracking(21, (byte) 0); //isSwinging
        dataTracker.startTracking(22, (byte) 0); //isClimbing
    }

    public void writeNbt(NbtCompound nbttagcompound)
    {
        super.writeNbt(nbttagcompound);
        nbttagcompound.putInt("TypeInt", getType());
        nbttagcompound.putFloat("Edad", getAge());
        nbttagcompound.putBoolean("Adult", getAdult());
    }

    public void readNbt(NbtCompound nbttagcompound)
    {
        super.readNbt(nbttagcompound);
        setType(nbttagcompound.getInt("TypeInt"));
        setAge(nbttagcompound.getFloat("Edad"));
        setAdult(nbttagcompound.getBoolean("Adult"));
    }

    public void sendSound(World world, String name, float vol, float pit){
        if (net.fabricmc.loader.FabricLoader.INSTANCE.getEnvironmentType() == EnvType.SERVER){
            mod_mocreatures.voicePacket(world, name, this.id, vol, pit);
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
            dataTracker.set(18, Byte.valueOf((byte)1));
        } else
        {
            dataTracker.set(18, Byte.valueOf((byte)0));
        }
    }

    //SWING PROGRESS
    public void setSwingProgress(float biteProgress)
    {
        dataTracker.set(19, Float.floatToRawIntBits(biteProgress));
    }

    public float getSwingProgress()
    {
        return Float.intBitsToFloat(dataTracker.getInt(19));
    }

    //IS POISONING
    public boolean getIsPoisoning()
    {
        return (dataTracker.getByte(20) & 1) != 0;
    }

    public void setIsPoisoning(boolean flag)
    {
        if(flag)
        {
            dataTracker.set(20, Byte.valueOf((byte)1));
        } else
        {
            dataTracker.set(20, Byte.valueOf((byte)0));
        }
    }

    //IS SWINGING
    public boolean getIsSwinging()
    {
        return (dataTracker.getByte(21) & 1) != 0;
    }

    public void setIsSwinging(boolean flag)
    {
        if(flag)
        {
            dataTracker.set(21, Byte.valueOf((byte)1));
        } else
        {
            dataTracker.set(21, Byte.valueOf((byte)0));
        }
    }

    //Climbing
    public boolean getClimbing()
    {
        return (dataTracker.getByte(22) & 1) != 0;
    }

    public void setClimbing(boolean flag)
    {
        if(flag)
        {
            dataTracker.set(22, (byte) 1);
        } else
        {
            dataTracker.set(22, (byte) 0);
        }
    }
}
