package net.kozibrodka.mocreatures.entity;

import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.fabricmc.loader.FabricLoader;
import net.kozibrodka.mocreatures.events.mod_mocreatures;
import net.kozibrodka.mocreatures.mocreatures.MoCTools;
import net.kozibrodka.mocreatures.mocreatures.MoCreatureRacial;
import net.kozibrodka.mocreatures.network.JokeyPacket;
import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.client.Minecraft;
import net.minecraft.entity.Entity;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.ai.pathing.Path;
import net.minecraft.entity.passive.AnimalEntity;
import net.minecraft.entity.passive.SquidEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.player.ServerPlayerEntity;
import net.minecraft.nbt.NbtCompound;
import net.minecraft.util.math.MathHelper;
import net.minecraft.world.World;
import net.modificationstation.stationapi.api.network.packet.PacketHelper;
import net.modificationstation.stationapi.api.server.entity.MobSpawnDataProvider;
import net.modificationstation.stationapi.api.util.Identifier;

import java.util.List;
import java.util.Objects;

public class EntityCrocodile extends AnimalEntity implements MobSpawnDataProvider, MoCreatureRacial {

    public EntityCrocodile(World world)
    {
        super(world);
        texture = "/assets/mocreatures/stationapi/textures/mob/crocodile.png";
        setBoundingBoxSpacing(2.0F, 0.6F);
        health = 35; ///oryg: 25, dodałem mechanice wyzwalania sie z paszczy dla zwierząt, wiec health+
        movementSpeed = 0.5F;
    }

    protected void jump() {
        if(isInFluid(Material.WATER)) {
            if(getCaughtPrey() || target == null && random.nextInt(20) != 0) {
                return;
            }
            velocityY = 0.3D;
        } else if(target != null || getCaughtPrey()) {
            super.jump();
        }

    }

//    /// ANTY despawn w wodzie - disabled
//    protected boolean canDespawn() {
//        return !this.isInFluid(Material.WATER);
//    }

    protected boolean isMovementBlocked() {
        return getIsResting();
    }

    protected void tickLiving() {
        if(!getIsResting()) {
            super.tickLiving();
        }
        if(this.target instanceof PlayerEntity){
            PlayerEntity uciekinier = world.getClosestPlayer(this, 16D);
            if(uciekinier == null && target.isAlive()){
                deAgroClock++;
                if(random.nextInt(200) == 0 && deAgroClock > 300) /// De-Agro mechanic - for Slow Agrresive animals
                {
                    target = null;
                    deAgroClock = 0;
                }
            }
        }
    }

    public boolean swimmerEntity() {
        return true;
    }

    public boolean isSubmergedInWater() {
        return swimmerEntity() ? false : super.isSubmergedInWater();
    }

    public boolean canBreatheInWater() {
        return swimmerEntity();
    }

    public void getMyOwnPath(Entity entity, float f) {
        Path pathentity = world.findPath(this, entity, 16.0F);
        if(pathentity != null) {
            setPath(pathentity);
        }

    }

    public void tickMovement() {

        if(world.isRemote){
            tickClientMovement();
            super.tickMovement();
            return;
        }

        if(getIsResting()) {
            pitch = -5.0F;
            if(!isInFluid(Material.WATER) && getBiteProgress() < 0.3F && random.nextInt(5) == 0) {
                setBiteProgress(getBiteProgress()+0.005F);
            }

            target = getTargetInRange();
            if(target != null) {
                setIsResting(false);
                getMyOwnPath(target, 16.0F);
            }

            if(target != null || getCaughtPrey() || random.nextInt(500) == 0) {
                setIsResting(false);
                setBiteProgress(0.0F);
                hunting = 1;
            }
        } else if(random.nextInt(500) == 0 && target == null && !getCaughtPrey()) {
            setIsResting(true);
            setPath((Path)null);
        }

        if(isInFluid(Material.WATER)) {
            movementSpeed = 0.8F;
        } else {
            movementSpeed = 0.4F;
        }

        if(hunting > 0) {
            ++hunting;
            if(hunting > 120) {
                hunting = 0;
                movementSpeed = 0.5F;
            } else {
                movementSpeed = 1.0F;
            }

            if(target == null) {
                hunting = 0;
                movementSpeed = 0.5F;
            }
        }

        if(random.nextInt(80) == 0 && !getCaughtPrey() && !getIsResting()) {
            crocBite();
        }

        if(random.nextInt(500) == 0 && !waterbound && !getIsResting() && !isInFluid(Material.WATER)) {
            MoCTools.MoveToWater(this);
        }

        if(getAge() < 1.3F && random.nextInt(200) == 0) {
            setAge(getAge() + 0.02F); //0.005F
//            if(getAge() >= 0.9F) { // wyjebałem boolean adult - nieuzyteczny
//                setAdult(true);
//            }
        }
        if(waterbound) {
            if(!isInFluid(Material.WATER)) {
                MoCTools.MoveToWater(this);
            } else {
                waterbound = false;
            }
        }

        if(getCaughtPrey()) {
            if(passenger != null) {
                target = null;
                setBiteProgress(0.4F);
                setIsResting(false);
                if(!isInFluid(Material.WATER)) {
                    waterbound = true;
                    if(passenger instanceof LivingEntity && ((LivingEntity)passenger).health > 0) {
                        ((LivingEntity)passenger).deathTime = 0;
                    }

                    if(random.nextInt(50) == 0) {
                        passenger.damage((this), 2);
                        if(!(passenger instanceof PlayerEntity)) {
                            MoCTools.destroyDrops(this, 3.0D);
                        }
                    }
                }
            } else {
                setCaughtPrey(false);
                setBiteProgress(0.0F);
                waterbound = false;
            }

            if(isSpinning()) {
                spinInt += 3;
                if(spinInt % 20 == 0) {
                    world.playSound(this, "mocreatures:crocroll", 1.0F, 1.0F + (random.nextFloat() - random.nextFloat()) * 0.2F);
                }

                if(spinInt > 80) {
                    spinInt = 0;
                    passenger.damage((this), 4);
                    if(!(passenger instanceof PlayerEntity)) {
                        MoCTools.destroyDrops(this, 3.0D);
                    }
                }

                if(passenger != null && passenger instanceof PlayerEntity) {
                    if(FabricLoader.INSTANCE.getEnvironmentType() == EnvType.SERVER){
                        PacketHelper.sendTo((PlayerEntity)passenger, new JokeyPacket(3));
                    }else{
                        clientToThirdPerson((PlayerEntity)passenger);
                    }
                }
            }
        }
        if(isSwimming() && swimmerEntity()) {
            floating();
        }
        super.tickMovement();
    }

    public void tickClientMovement(){
        if(isSpinning()) {
            spinInt += 3;
            if(spinInt % 20 == 0) {
                world.playSound(this, "mocreatures:crocroll", 1.0F, 1.0F + (random.nextFloat() - random.nextFloat()) * 0.2F);
            }
            if(spinInt > 80) {
                spinInt = 0;
            }
        }
    }

    @Environment(EnvType.CLIENT)
    public void clientToThirdPerson(PlayerEntity particle){
        Minecraft.INSTANCE.options.thirdPerson = true;
    }

    public boolean isSwimming() {
        return isInFluid(Material.WATER);
    }

    public boolean getIsSitting() {
        double d1 = 0.01D;
        return getIsResting();
    }

    public void crocBite() {
        if(!getIsBitting()) {
            setIsBitting(true);
            setBiteProgress(0.0F);
        }

    }

    public void tick() {
        if(world.isRemote){
            if(getIsBitting() && !getCaughtPrey()){
                biteProgress += 0.1F;
                    if(biteProgress == 0.4F) {
                        world.playSound(this, "mocreatures:crocjawsnap", 1.0F, 1.0F + (random.nextFloat() - random.nextFloat()) * 0.2F);
                    }
                    if(biteProgress > 0.6F) {
                        biteProgress = 0.0F;
                    }
            }else{
                biteProgress = getBiteProgress();
            }
        }else{
            if(getIsBitting() && !getCaughtPrey()) {
                setBiteProgress(getBiteProgress()+0.1F);
                if(getBiteProgress() == 0.4F) {
                    world.playSound(this, "mocreatures:crocjawsnap", 1.0F, 1.0F + (random.nextFloat() - random.nextFloat()) * 0.2F);
                }

                if(getBiteProgress() > 0.6F) {
                    setIsBitting(false);
                    setBiteProgress(0.0F);
                }
            }
        }
        super.tick();
    }

    protected void attack(Entity entity, float f) {
        if(!getCaughtPrey()) {
            if(attackCooldown <= 0 && f < 3.0F && entity.boundingBox.maxY > boundingBox.minY && entity.boundingBox.minY < boundingBox.maxY) {
                attackCooldown = 20;
                if(entity.vehicle == null && random.nextInt(3) == 0) {
                    entity.setVehicle(this);
                    setCaughtPrey(true);
                    if (net.fabricmc.loader.FabricLoader.INSTANCE.getEnvironmentType() == EnvType.SERVER) {
                        sendJokeyPacket(world, this.id, entity.id);
                    }
                } else {
                    entity.damage((this), 2);
                    if(!(entity instanceof PlayerEntity)) {
                        MoCTools.destroyDrops(this, 3.0D);
                    }
                    crocBite();
                    setCaughtPrey(false);
                }
            }

        }
    }

    public boolean damage(Entity entity, int i) {
        if(world.isRemote){
            return false;
        }
        if(passenger instanceof PlayerEntity) {
            if(entity != null && passenger == entity) {
                if(random.nextInt(10) != 0) {
                    return false;
                }
                unMount();
            }
        } else {
            if (passenger instanceof LivingEntity) {
                if (entity != null && passenger == entity) {
                    if (random.nextInt(200) != 0) { /// 200 - daje przyzwoity random.
                        return false;
                    }
                    unMount();
                }
            }
        }

        if(super.damage(entity, i)) {
            if(passenger != null && passenger == entity){
                return false;
            }
            if(entity != this && world.difficulty > 0){
                target = entity;
            }
            return true;
        } else {
            return false;
        }
    }

    protected Entity getTargetInRange() {
        if(getCaughtPrey()) {
            return null;
        } else {
            if(world.difficulty > 0 && target == null) { //&& target == null
                LivingEntity entityliving;
                if(getIsResting()) {
                    PlayerEntity entityplayer = world.getClosestPlayer(this, 6.0D);
                    if(entityplayer != null){
                        return entityplayer;
                    }
                    entityliving = getClosestEntityLiving(this, 6.0D);
                    return entityliving;
                }

                if(random.nextInt(80) == 0) {
                    PlayerEntity entityplayer = world.getClosestPlayer(this, 12.0D);
                    if(entityplayer != null){
                        return entityplayer;
                    }
                    entityliving = getClosestEntityLiving(this, 12.0D);
                    return entityliving;
                }
            }

            return null;
        }
    }

    protected LivingEntity getClosestEntityLiving(Entity entity, double d) {
        double d1 = -1.0D;
        LivingEntity entityliving = null;
        List list = world.getEntities(this, boundingBox.expand(d, d, d));

        for(int i = 0; i < list.size(); ++i) {
            Entity entity1 = (Entity)list.get(i);
                if(privateToIgnore(this, entity1) || MoCTools.entitiesToIgnore(this, entity1))
                {
                    continue;
                }
                double d2 = entity1.getSquaredDistance(entity.x, entity.y, entity.z);
                if((d < 0.0D || d2 < d * d) && (d1 == -1.0D || d2 < d1) && ((LivingEntity)entity1).canSee(entity)) {
                    d1 = d2;
                    entityliving = (LivingEntity)entity1;
                }

        }

        return entityliving;
    }

    public boolean privateToIgnore(Entity hunter, Entity victim) {
        return (victim instanceof EntityCustomWM || victim instanceof EntityCustomAquaM || victim instanceof SquidEntity || victim instanceof EntityHippo || victim instanceof EntityCrocodile || victim instanceof EntityElephant && ((EntityElephant) victim).getAdult() || victim.height < this.height && victim.width < this.width);
    } ///Żeby bez sensu nie wpierdalał się do wody, ma zaciągać do wody lądowe.

    public boolean entitiesToIgnore(Entity entity) {
        return MoCTools.entitiesToIgnore(this, entity) || entity instanceof EntityCrocodile || entity.height < this.height && entity.width < this.width;
    }

    public void updatePassengerPosition() {
        if(passenger != null) {
            boolean direction = true;
            double dist = (double)(getAge() + passenger.width) - 0.4D;
            double newPosX = x - dist * Math.cos((double)(MoCTools.realAngle(yaw - 90.0F) / 57.29578F));
            double newPosZ = z - dist * Math.sin((double)(MoCTools.realAngle(yaw - 90.0F) / 57.29578F));
            passenger.setPosition(newPosX, y + getPassengerRidingHeight() + passenger.getStandingEyeHeight(), newPosZ);
            byte direction1;
            if(spinInt > 40) {
                direction1 = -1;
            } else {
                direction1 = 1;
            }

            ((LivingEntity)passenger).bodyYaw = yaw * (float)direction1;
            ((LivingEntity)passenger).lastBodyYaw = yaw * (float)direction1;
        }
    }

    public double getPassengerRidingHeight() {
        return (double)height * 0.35D;
    }

    public void floating() {
        if(target != null && target.y < y - 0.5D && getDistance(target) < 10.0F) {
            if(velocityY < -0.1D) {
                velocityY = -0.1D;
            }
        } else {
            if(velocityY < 0.0D) {
                velocityY = 0.0D;
            }

            velocityY += 0.001D;
            int distY = (int)MoCTools.distanceToSurface(this);
            if(distY > 1) {
                velocityY += (double)distY * 0.07D;
            }

            if(hasPath() && horizontalCollision) {
                jump();
            }
        }

    }

    public void onKilledBy(Entity damagesource) {
        unMount();
        MoCTools.checkForTwistedEntities(world);
        super.onKilledBy(damagesource);
    }

    public void unMount() {
        if(passenger != null) {
            if(passenger instanceof LivingEntity && ((LivingEntity)passenger).health > 0) {
                ((LivingEntity)passenger).deathTime = 0;
                if(FabricLoader.INSTANCE.getEnvironmentType() == EnvType.SERVER){
                    if(passenger instanceof PlayerEntity){
                        PacketHelper.sendTo((PlayerEntity)passenger, new JokeyPacket(4));
                    }
                    sendJokeyPacketUnMount(world, passenger.id);
                }

            }
            passenger.setVehicle((Entity)null);
            setCaughtPrey(false);
            setBiteProgress(0.0F);
        }

    }


    public boolean isSpinning() {
        return getCaughtPrey() && isInFluid(Material.WATER);
    }


    public int getLimitPerChunk()
    {
        return 6;
    }

    public int getMinAmbientSoundDelay() {
        return 120;
    }

    protected int getDroppedItemId()
    {
        return mod_mocreatures.crochide.id;
    }

    protected String getRandomSound()
    {
        return getIsResting() ? "mocreatures:crocresting" : "mocreatures:crocgrunt";
    }

    protected String getHurtSound()
    {
        return "mocreatures:crochurt";
    }

    protected String getDeathSound()
    {
        return "mocreatures:crocdying";
    }

    public boolean canSpawn()
    {
        return mod_mocreatures.mocGlass.huntercreatures.crocodilefreq > 0 && !MoCTools.isNearTorch(this) && MoCTools.isNearWater(this) && canSpawnCroc();
    }

    public boolean canSpawnCroc() /// Spawni róznież na piasku.
    {
        int var1 = MathHelper.floor(x);
        int var2 = MathHelper.floor(boundingBox.minY);
        int var3 = MathHelper.floor(z);
        return (world.getBlockId(var1, var2 - 1, var3) == Block.SAND.id || world.getBlockId(var1, var2 - 1, var3) == Block.GRASS_BLOCK.id) && world.getBrightness(var1, var2, var3) > 8 && getPathfindingFavor(var1, var2, var3) >= 0.0F && world.canSpawnEntity(boundingBox) && world.getEntityCollisions(this, boundingBox).isEmpty() && !world.isBoxSubmergedInFluid(boundingBox);
    }

    public float biteProgress;
    public int spinInt;
    private boolean waterbound;
    private int hunting;
    public int deAgroClock;

    @Environment(EnvType.SERVER)
    public void sendJokeyPacket(World world, int entityID, int roperID) {
        List list2 = world.players;
        if (list2.size() != 0) {
            for (int k = 0; k < list2.size(); k++) {
                ServerPlayerEntity player1 = (ServerPlayerEntity) list2.get(k);
                if(!(passenger instanceof PlayerEntity && Objects.equals(((PlayerEntity) passenger).name, player1.name))){
                    PacketHelper.sendTo(player1, new JokeyPacket(5, entityID, roperID));
                }
            }
        }
    }

    @Environment(EnvType.SERVER)
    public void sendJokeyPacketUnMount(World world, int roperID) {
        List list2 = world.players;
        if (list2.size() != 0) {
            for (int k = 0; k < list2.size(); k++) {
                ServerPlayerEntity player1 = (ServerPlayerEntity) list2.get(k);
                if(!(passenger instanceof PlayerEntity && Objects.equals(((PlayerEntity) passenger).name, player1.name))){
                    PacketHelper.sendTo(player1, new JokeyPacket(6, roperID));
                }
            }
        }
    }

    @Override
    public void setTypeSpawn() {
        if(random.nextInt(4) == 0){
            setAge(0.7F + (random.nextFloat() / 5F));
        }else{
            setAge(1.3F);
            health = 30;
        }
    }

    @Override
    public Identifier getHandlerIdentifier() {return Identifier.of(mod_mocreatures.MOD_ID, "Crocodile");}

    protected void initDataTracker()
    {
        super.initDataTracker();
        dataTracker.startTracking(16, (byte) 0); //Resting
        dataTracker.startTracking(17, (int) 0); //Age
        dataTracker.startTracking(18, (int) 0); //BiteProgress
        dataTracker.startTracking(19, (byte) 0); //CaughtPrey
        dataTracker.startTracking(20, (byte) 0); //isBITTING
    }

    public void writeNbt(NbtCompound nbttagcompound)
    {
        super.writeNbt(nbttagcompound);
        nbttagcompound.putFloat("Edad", getAge());
        nbttagcompound.putBoolean("Resting", getIsResting());
    }

    public void readNbt(NbtCompound nbttagcompound)
    {
        super.readNbt(nbttagcompound);
        setAge(nbttagcompound.getFloat("Edad"));
        setIsResting(nbttagcompound.getBoolean("Resting"));
    }


    //RESTING
    public boolean getIsResting() {
        return (dataTracker.getByte(16) & 1) != 0;
    }

    public void setIsResting(boolean flag) {
        if(flag)
        {
            dataTracker.set(16, (byte) 1);
        } else
        {
            dataTracker.set(16, (byte) 0);
        }
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

    //BITE PROGRESS
    public void setBiteProgress(float biteProgress)
    {
        dataTracker.set(18, Float.floatToRawIntBits(biteProgress));
    }

    public float getBiteProgress()
    {
        return Float.intBitsToFloat(dataTracker.getInt(18));
    }

    //CAUGHT PREY
    public boolean getCaughtPrey() {
        return (dataTracker.getByte(19) & 1) != 0;
    }

    public void setCaughtPrey(boolean flag) {
        if(flag)
        {
            dataTracker.set(19, (byte) 1);
        } else
        {
            dataTracker.set(19, (byte) 0);
        }
    }

    //IS BITTING
    public boolean getIsBitting()
    {
        return (dataTracker.getByte(20) & 1) != 0;
    }

    public void setIsBitting(boolean flag)
    {
        if(flag)
        {
            dataTracker.set(20, Byte.valueOf((byte)1));
        } else
        {
            dataTracker.set(20, Byte.valueOf((byte)0));
        }
    }

}
