package net.kozibrodka.mocreatures.entity;

import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.fabricmc.loader.FabricLoader;
import net.kozibrodka.mocreatures.events.mod_mocreatures;
import net.kozibrodka.mocreatures.mocreatures.MoCTools;
import net.kozibrodka.mocreatures.mocreatures.MoCreatureNamed;
import net.kozibrodka.mocreatures.mocreatures.MocTick;
import net.kozibrodka.mocreatures.network.RopePacket;
import net.minecraft.block.Block;
import net.minecraft.block.PlantBlock;
import net.minecraft.entity.Entity;
import net.minecraft.entity.ItemEntity;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.ai.pathing.Path;
import net.minecraft.entity.passive.SheepEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.player.ServerPlayerEntity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NbtCompound;
import net.minecraft.util.math.MathHelper;
import net.minecraft.util.math.Vec3d;
import net.minecraft.world.World;
import net.modificationstation.stationapi.api.network.packet.PacketHelper;
import net.modificationstation.stationapi.api.server.entity.MobSpawnDataProvider;
import net.modificationstation.stationapi.api.util.Identifier;

import java.util.List;
import java.util.Objects;

public class EntitySheep extends SheepEntity implements MobSpawnDataProvider, MoCreatureNamed {

    public boolean hasRopeOnNeck;
    public LivingEntity roper;
    public boolean eatenWheat;
    public int sheepClock;
    public int grassClock;
    public int debugClock;
    public int sheepFood;
    public int foodBlockType; /// 1- tallgrass, 2-flower, 3-grassblock
    public boolean foundGrass;
    public boolean runningFromCollie;
    public int[] grasscoords;

    final int maxClockSheep = 12000; /// 10 minut  -12000
    final int maxClockDebug = 400; /// 20 sekund
    final int satietyBush = 34;
    final int satietyFlower = 25;
    final int satietyGrassB = 9;



    public EntitySheep(World world) {
        super(world);
        texture = "/assets/mocreatures/stationapi/textures/mob/sheep.png";
        grasscoords = new int[]{-1, -1, -1};
    }

    protected void initDataTracker() {
        super.initDataTracker();
        dataTracker.startTracking(17, (byte) 0); //Tamed
        dataTracker.startTracking(18, (byte) 0); //isEating

        dataTracker.startTracking(19, (int) -1); //Found X
        dataTracker.startTracking(20, (int) -1); //Found Y
        dataTracker.startTracking(21, (int) -1); //Found Z
    }

    public void writeNbt(NbtCompound nbt) {
        super.writeNbt(nbt);
        nbt.putBoolean("Tamed", getTamed());
        nbt.putBoolean("Eaten", eatenWheat);
        nbt.putBoolean("Roper", hasRopeOnNeck);
        nbt.putInt("Clock", sheepClock);
        nbt.putInt("Food", sheepFood);
    }

    public void readNbt(NbtCompound nbt) {
        super.readNbt(nbt);
        setTamed(nbt.getBoolean("Tamed"));
        eatenWheat = (nbt.getBoolean("Eaten"));
        hasRopeOnNeck = (nbt.getBoolean("Roper"));
        sheepClock = (nbt.getInt("Clock"));
        sheepFood = (nbt.getInt("Food"));
    }

    public void tickMovement() {
        if(world.isRemote){
            super.tickMovement();
            return;
        }
        if(isSheared() && getTamed() && sheepClock <= maxClockSheep){///  1s = 20 ticks.    10s = 200t.    1m = 1200t.
            sheepClock++;
        }
        if(sheepClock >= (maxClockSheep) && !foundGrass && roper == null && !runningFromCollie) { /// JAKI RANDOM w szukaniu?  && random.nextInt(1) == 0

            int[] ai = MoCTools.ReturnNearestBlockCoord(this, "tile.tallgrass", Double.valueOf(20.0D), Double.valueOf(2.0D));
            if (ai[1] != -1 && canSeeBlock(ai[0],ai[1],ai[2], 0.85D)) {
                foundGrass = true;
                grasscoords[0] = ai[0];
                grasscoords[1] = ai[1];
                grasscoords[2] = ai[2];
                foodBlockType = 1;
            }
            if(grasscoords[1] == -1){
                int[] bi = MoCTools.ReturnNearestBlockCoord(this, "tile.flower", Double.valueOf(20.0D), Double.valueOf(2.0D)); ///DYSTANS???
                if (bi[1] != -1 && canSeeBlock(bi[0],bi[1],bi[2],0.65D)) {
                    foundGrass = true;
                    grasscoords[0] = bi[0];
                    grasscoords[1] = bi[1];
                    grasscoords[2] = bi[2];
                    foodBlockType = 2;
                }
            }
            if(grasscoords[1] == -1){
                int[] ci = MoCTools.ReturnNearestBlockCoord(this, "tile.grass", Double.valueOf(20.0D), Double.valueOf(2.0D)); ///DYSTANS???
                Block theory = Block.BLOCKS[world.getBlockId(ci[0],ci[1] + 1,ci[2])];
                if (ci[1] != -1 && (canSeeBlock(ci[0],ci[1],ci[2], 1.05D)   ||  (theory instanceof PlantBlock) && canSeeBlock(ci[0],ci[1] + 1,ci[2], 0.65D) )) { ///zostawiają róże
                    foundGrass = true;
                    grasscoords[0] = ci[0];
                    grasscoords[1] = ci[1];
                    grasscoords[2] = ci[2];
                    foodBlockType = 3;
                }
            }
        }
        if(foundGrass && !getIsEating()){
            if(roper == null && !runningFromCollie) {
                MoCTools.MoveCreatureToXYZ(this, grasscoords[0], grasscoords[1], grasscoords[2], 24.0F); ///DYSTANS??? ^^
            }
            debugClock++;
            if(getDistance(grasscoords[0], grasscoords[1], grasscoords[2]) < 1.0D && foodBlockType < 3){ //3.0
                setIsEating(true);
                debugClock = 0;
                faceItem(grasscoords[0], grasscoords[1], grasscoords[2], 30F);
            }
            if(getDistance(grasscoords[0], grasscoords[1], grasscoords[2]) < 2.0D && foodBlockType == 3){ //3.0
                setIsEating(true);
                debugClock = 0;
                faceItem(grasscoords[0], grasscoords[1], grasscoords[2], 30F);
            }
            if(debugClock > maxClockDebug){ /// PO JAKIM CZASIE ODPUSCZA?
                debugClock = 0;
                foundGrass = false;
                sheepClock = maxClockSheep - 200; ///daje 10 sekund na namysł
                grasscoords[0] = -1;
                grasscoords[1] = -1;
                grasscoords[2] = -1;
            }
        }
        if(getIsEating()){
            faceItem(grasscoords[0], grasscoords[1], grasscoords[2], 30F);
            jumping = false;
            grassClock++;

            if(grassClock == 80){ ///  Upewnić się czy 1 sekunda starcza zawsze
                dataTracker.set(19, (int)grasscoords[0]);
                dataTracker.set(20, (int)grasscoords[1]);
                dataTracker.set(21, (int)grasscoords[2]);
            }

            if((grassClock % 20 == 0)) { /// DEBUG żeby nie marnowała czasu.
                if(foodBlockType == 1){
                    if(world.getBlockId(grasscoords[0], grasscoords[1], grasscoords[2]) == 0){
                        grassClock -= 25; /// nie zeruje, zeby kilka razy pod rząd nie czekała 5 sekund przykładowo
                        setIsEating(false);
                        foundGrass = false;
                        grasscoords[0] = -1;
                        grasscoords[1] = -1;
                        grasscoords[2] = -1;
                    }
                }
                if(foodBlockType == 2){
                    if(world.getBlockId(grasscoords[0], grasscoords[1], grasscoords[2]) == 0){
                        grassClock -= 25;
                        setIsEating(false);
                        foundGrass = false;
                        grasscoords[0] = -1;
                        grasscoords[1] = -1;
                        grasscoords[2] = -1;
                    }
                }
                if(foodBlockType == 3){
                    if(world.getBlockId(grasscoords[0], grasscoords[1], grasscoords[2]) == Block.DIRT.id){
                        grassClock -= 25;
                        setIsEating(false);
                        foundGrass = false;
                        grasscoords[0] = -1;
                        grasscoords[1] = -1;
                        grasscoords[2] = -1;
                    }
                }
            }

            if(grassClock>100){ /// czas jedzenia 5 sekund
                grassClock = 0;
                setIsEating(false);
                foundGrass = false;
                if(world.getBlockId(grasscoords[0], grasscoords[1], grasscoords[2]) != 0 && world.getBlockId(grasscoords[0], grasscoords[1], grasscoords[2]) != Block.DIRT.id) { /// Z tym może być problem ewentualnie
                    if(foodBlockType == 1){
                        Block newBlock = Block.GRASS;
                        if (net.fabricmc.loader.FabricLoader.INSTANCE.getEnvironmentType() == EnvType.SERVER) {
                            world.broadcastEntityEvent(this, (byte)10);
                        }else{
                            world.playSound((float) grasscoords[0] + 0.5F, (float) grasscoords[1] + 0.5F, (float) grasscoords[2] + 0.5F, newBlock.soundGroup.getSound(), (newBlock.soundGroup.getVolume() + 1.0F) / 2.0F, newBlock.soundGroup.getPitch() * 0.8F);
                            MocTick.mc.particleManager.addBlockBreakParticles(grasscoords[0], grasscoords[1], grasscoords[2], newBlock.id, world.getBlockMeta(grasscoords[0], grasscoords[1], grasscoords[2]));
                        }
                        world.setBlock(grasscoords[0], grasscoords[1], grasscoords[2], 0);
                        world.setBlock(grasscoords[0], grasscoords[1] - 1, grasscoords[2], Block.DIRT.id);
                        sheepFood += satietyBush;
                    }
                    if(foodBlockType == 2){
                        Block newBlock = Block.DANDELION;
                        if (net.fabricmc.loader.FabricLoader.INSTANCE.getEnvironmentType() == EnvType.SERVER) {
                            world.broadcastEntityEvent(this, (byte)10);
                        }else{
                            world.playSound((float) grasscoords[0] + 0.5F, (float) grasscoords[1] + 0.5F, (float) grasscoords[2] + 0.5F, newBlock.soundGroup.getSound(), (newBlock.soundGroup.getVolume() + 1.0F) / 2.0F, newBlock.soundGroup.getPitch() * 0.8F);
                            MocTick.mc.particleManager.addBlockBreakParticles(grasscoords[0], grasscoords[1], grasscoords[2], newBlock.id, 0);
                        }
                        world.setBlock(grasscoords[0], grasscoords[1], grasscoords[2], 0);
                        world.setBlock(grasscoords[0], grasscoords[1] - 1, grasscoords[2], Block.DIRT.id);
                        sheepFood += satietyFlower;
                    }
                    if(foodBlockType == 3){
                        Block newBlock = Block.GRASS_BLOCK;
                        if (net.fabricmc.loader.FabricLoader.INSTANCE.getEnvironmentType() == EnvType.SERVER) {
                            world.broadcastEntityEvent(this, (byte)10);
                        }else{
                            world.playSound((float) grasscoords[0] + 0.5F, (float) grasscoords[1] + 0.5F, (float) grasscoords[2] + 0.5F, newBlock.soundGroup.getSound(), (newBlock.soundGroup.getVolume() + 1.0F) / 2.0F, newBlock.soundGroup.getPitch() * 0.8F);
                            MocTick.mc.particleManager.addBlockBreakParticles(grasscoords[0], grasscoords[1], grasscoords[2], newBlock.id, 0);
                        }

                        world.setBlock(grasscoords[0], grasscoords[1], grasscoords[2], Block.DIRT.id);
                        sheepFood += satietyGrassB;
                    }
                }else{
                    /// JEDZENIE ZOSTAŁO MI ZABRANE :(
                }
                grasscoords[0] = -1;
                grasscoords[1] = -1;
                grasscoords[2] = -1;
            }
        }

        if(sheepFood >= 100){
            sheepClock = 0;
            grassClock = 0;
            debugClock = 0;
            sheepFood = 0;
            foundGrass = false;
            health = 10;
            setSheared(false);
            ///  RESET CAŁKOWITY + heal
        }

        if(random.nextInt(5) == 0 && getTamed() && roper == null)
        {
            LivingEntity entityliving = getBoogey(10D);
            if(entityliving != null)
            {
                runningFromCollie = true;
                runLikeHell(entityliving);
            } else
            {
                runningFromCollie = false;
            }
        }

        super.tickMovement();
    }

    public LivingEntity getBoogey(double d)
    {
        double d1 = -1D;
        LivingEntity entityliving = null;
        List list = world.getEntities(this, boundingBox.expand(d, 4D, d));
        for(int i = 0; i < list.size(); i++)
        {
            Entity entity = (Entity)list.get(i);
            if(entity instanceof EntityCollie && ((EntityCollie) entity).getTamed())
            {
                entityliving = (LivingEntity)entity;
            }
        }
        return entityliving;
    }

    public void runLikeHell(Entity entity)
    {
        double d = x - entity.x;
        double d1 = z - entity.z;
        double d2 = Math.atan2(d, d1);
        d2 += (double)(random.nextFloat() - random.nextFloat()) * 0.75D;
        double d3 = x + Math.sin(d2) * 8D;
        double d4 = z + Math.cos(d2) * 8D;
        int i = MathHelper.floor(d3);
        int j = MathHelper.floor(boundingBox.minY);
        int k = MathHelper.floor(d4);
        int l = 0;
        do
        {
            if(l >= 16)
            {
                break;
            }
            int i1 = (i + random.nextInt(4)) - random.nextInt(4);
            int j1 = (j + random.nextInt(3)) - random.nextInt(3);
            int k1 = (k + random.nextInt(4)) - random.nextInt(4);
            if(j1 > 4 && (world.getBlockId(i1, j1, k1) == 0 || world.getBlockId(i1, j1, k1) == Block.SNOW.id) && world.getBlockId(i1, j1 - 1, k1) != 0)
            {
                Path pathentity = world.findPath(this, i1, j1, k1, 16F);
                setPath(pathentity);
                break;
            }
            l++;
        } while(true);
    }

    public boolean canSeeBlock(int x, int y, int z, double customHeight) {
        return this.world.raycast(Vec3d.createCached(this.x, this.y + (double)this.getEyeHeight(), this.z), Vec3d.createCached((double) x + 0.5D, (double) y + customHeight, (double) z + 0.5D)) == null;
    }

    protected boolean isMovementBlocked() {
        return getIsEating();
    }

    protected void tickLiving()
    {
        super.tickLiving();
        if(world.isRemote){
            return;
        }
        if(getTamed() && roper != null)
        {
            float f = roper.getDistance(this);
            if(f > 5F)
            {
                getPathOrWalkableBlock(roper, f);
            }
        }
        if(roper == null && hasRopeOnNeck){
            if (net.fabricmc.loader.FabricLoader.INSTANCE.getEnvironmentType() == EnvType.SERVER) {
                sendRopePacket(world, "sheep", this.id, "");
            }
            ropeRemoval(world, this.x,this.y,this.z);
            hasRopeOnNeck = false;
        }
        if(roper!=null && !roper.isAlive() && hasRopeOnNeck){
            if (net.fabricmc.loader.FabricLoader.INSTANCE.getEnvironmentType() == EnvType.SERVER) {
                sendRopePacket(world, "sheep", this.id, "");
            }
            ropeRemoval(world, this.x,this.y,this.z);
            hasRopeOnNeck = false;
            roper = null;
        }
    }

    public boolean interact(PlayerEntity player) {
        ItemStack itemstack = player.inventory.getSelectedItem();
        if(world.isRemote){
            return false;
        }
        if(roper instanceof PlayerEntity && !Objects.equals(((PlayerEntity) roper).name, player.name)){ ///  Anty zabieracz Linki
            return false;
        }
        
        if (itemstack != null && itemstack.itemId == Item.SHEARS.id && !isSheared()) {
                setSheared(true);
                sheepClock = 0;
                int var3 = 2 + random.nextInt(3);

                for(int var4 = 0; var4 < var3; ++var4) {
                    ItemEntity var5 = dropItem(new ItemStack(Block.WOOL.id, 1, getColor()), 1.0F);
                    var5.velocityY += (double)(random.nextFloat() * 0.05F);
                    var5.velocityX += (double)((random.nextFloat() - random.nextFloat()) * 0.1F);
                    var5.velocityZ += (double)((random.nextFloat() - random.nextFloat()) * 0.1F);
                }

            itemstack.damage(1, player);
            return true;
        }
        if (itemstack != null && itemstack.itemId == Item.WHEAT.id && !getTamed() && !eatenWheat) {
            --itemstack.count;
            if (itemstack.count <= 0) {
                player.inventory.setStack(player.inventory.selectedSlot, (ItemStack)null);
            }

                if (random.nextInt(1) == 0) { /// ile zbóż śrenio potrzeba?? (pies 3) - 9 raczej / 1 - DEBUG
                    eatenWheat = true;
                    world.playSound(this, "mocreatures:eating", 1.0F, 1.0F + (random.nextFloat() - random.nextFloat()) * 0.2F);
                    world.broadcastEntityEvent(this, (byte)8);
                    setPath((Path)null);
                    health = 10;
                } else {
                    showFeedParticles(false);
                    world.broadcastEntityEvent(this, (byte)6);
                }

            return true;
        }
        if (itemstack != null && itemstack.itemId == mod_mocreatures.sheepbell.id && !getTamed() && eatenWheat) {
            --itemstack.count;
            if (itemstack.count <= 0) {
                player.inventory.setStack(player.inventory.selectedSlot, (ItemStack) null);
            }
            setTamed(true);
            showFeedParticles(true);
            world.broadcastEntityEvent(this, (byte)7);
            return true;
        }
        if(itemstack != null && roper == null && getTamed() && itemstack.itemId == mod_mocreatures.rope.id)
        {
            if(--itemstack.count == 0)
            {
                player.inventory.setStack(player.inventory.selectedSlot, null);
            }
            world.playSound(this, "mocreatures:roping", 1.0F, 1.0F + (random.nextFloat() - random.nextFloat()) * 0.2F);
            world.broadcastEntityEvent(this, (byte)9);
            roper = player;
            hasRopeOnNeck = true;
            if (FabricLoader.INSTANCE.getEnvironmentType() == EnvType.SERVER) {
                sendRopePacket(world, "sheep", this.id, player.name);
            }
            return true;
        }
        if(roper != null && getTamed())
        {
            player.inventory.addStack(new ItemStack(mod_mocreatures.rope));
            world.playSound(this, "mocreatures:roping", 1.0F, 1.0F + (random.nextFloat() - random.nextFloat()) * 0.2F);
            world.broadcastEntityEvent(this, (byte)9);
            roper = null;
            hasRopeOnNeck = false;
            if (FabricLoader.INSTANCE.getEnvironmentType() == EnvType.SERVER) {
                sendRopePacket(world, "sheep", this.id, "");
            }
            return true;
        }


        return false;
    }

    private void getPathOrWalkableBlock(Entity entity, float f)
    {
        Path pathentity = world.findPath(this, entity, 16F);
        if(pathentity == null && f > 12F)
        {
            int i = MathHelper.floor(entity.x) - 2;
            int j = MathHelper.floor(entity.z) - 2;
            int k = MathHelper.floor(entity.boundingBox.minY);
            for(int l = 0; l <= 4; l++)
            {
                for(int i1 = 0; i1 <= 4; i1++)
                {
                    if((l < 1 || i1 < 1 || l > 3 || i1 > 3) && world.shouldSuffocate(i + l, k - 1, j + i1) && !world.shouldSuffocate(i + l, k, j + i1) && !world.shouldSuffocate(i + l, k + 1, j + i1))
                    {
                        setPositionAndAnglesKeepPrevAngles((float)(i + l) + 0.5F, k, (float)(j + i1) + 0.5F, yaw, pitch);
                        return;
                    }
                }
            }

        } else
        {
            setPath(pathentity);
        }
    }

    public void ropeRemoval(World world, double i, double j, double k)
    {
        float f = random.nextFloat() * 0.8F + 0.1F;
        float f1 = random.nextFloat() * 0.8F + 0.1F;
        float f2 = random.nextFloat() * 0.8F + 0.1F;
        ItemEntity entityitem = new ItemEntity(world, (float)i + f, (float)j + f1, (float)k + f2, new ItemStack(mod_mocreatures.rope.id, 1, 0));
        float f3 = 0.05F;
        entityitem.velocityX = (float)random.nextGaussian() * f3;
        entityitem.velocityY = (float)random.nextGaussian() * f3 + 0.2F;
        entityitem.velocityZ = (float)random.nextGaussian() * f3;
        world.spawnEntity(entityitem);
    }

    public void faceItem(double i, double j, double k, float f)
    {
        double d = (double)i - x;
        double d1 = (double)k - z;
        double d2 = (double)j - y;
        double d3 = MathHelper.sqrt(d * d + d1 * d1);
        float f1 = (float)((Math.atan2(d1, d) * 180D) / 3.1415927410125728D) - 90F;
        float f2 = (float)((Math.atan2(d2, d3) * 180D) / 3.1415927410125728D);
        pitch = -realAngle(pitch, f2, f);
        yaw = realAngle(yaw, f1, f);
    }

    public float realAngle(float f, float f1, float f2)
    {
        float f3 = f1;
        for(f3 = f1 - f; f3 < -180F; f3 += 360F) { }
        for(; f3 >= 180F; f3 -= 360F) { }
        if(f3 > f2)
        {
            f3 = f2;
        }
        if(f3 < -f2)
        {
            f3 = -f2;
        }
        return f + f3;
    }

    @Environment(EnvType.CLIENT)
    public void processServerEntityStatus(byte status) {
        if (status == 7) {
            showFeedParticles(true);
        } else if (status == 6) {
            showFeedParticles(false);
        } else if (status == 8) {
            world.playSound(this, "mocreatures:eating", 1.0F, 1.0F + (random.nextFloat() - random.nextFloat()) * 0.2F);
        }  else if (status == 9){
            world.playSound(this, "mocreatures:roping", 1.0F, 1.0F + (random.nextFloat() - random.nextFloat()) * 0.2F);
        } else if (status == 10){
            int clientX = dataTracker.getInt(19);
            int clientY = dataTracker.getInt(20);
            int clientZ = dataTracker.getInt(21);
            int bID = world.getBlockId(clientX,clientY,clientZ);
            int bMET = world.getBlockMeta(clientX,clientY,clientZ);
            Block newBlock = Block.BLOCKS[bID];
            if(newBlock == null || clientY == -1){
                return;
            }
            world.playSound(clientX+0.5D,clientY+0.5D,clientZ+0.5D, newBlock.soundGroup.getSound(), (newBlock.soundGroup.getVolume() + 1.0F) / 2.0F, newBlock.soundGroup.getPitch() * 0.8F);
            MocTick.mc.particleManager.addBlockBreakParticles(clientX,clientY,clientZ, newBlock.id, bMET);
        }
        else {
            super.processServerEntityStatus(status);
        }

    }

    public void showFeedParticles(boolean hearts) {
        String var2 = "heart";
        if (!hearts) {
            var2 = "smoke";
        }

        for(int var3 = 0; var3 < 7; ++var3) {
            double var4 = random.nextGaussian() * 0.02;
            double var6 = random.nextGaussian() * 0.02;
            double var8 = random.nextGaussian() * 0.02;
            world.addParticle(var2, x + (double)(random.nextFloat() * width * 2.0F) - (double)width, y + (double)0.5F + (double)(random.nextFloat() * height), z + (double)(random.nextFloat() * width * 2.0F) - (double)width, var4, var6, var8);
        }

    }

    public void markDead() /// Czy to ma jakikolwiek sens???
    {
        if(getTamed() && health > 0  && !world.isRemote)
        {
            return;
        } else
        {
            super.markDead();
            return;
        }
    }

    protected boolean canDespawn() {
        return !this.getTamed();
    }

    @Override
    public Identifier getHandlerIdentifier() {
        return Identifier.of(mod_mocreatures.MOD_ID, "Sheep");
    }

    //TAMED
    public boolean getTamed()
    {
        return (dataTracker.getByte(17) & 1) != 0;
    }

    public void setTamed(boolean flag)
    {
        if(flag)
        {
            dataTracker.set(17, (byte) 1);
        } else
        {
            dataTracker.set(17, (byte) 0);
        }
    }

    //TAMED
    public boolean getIsEating()
    {
        return (dataTracker.getByte(18) & 1) != 0;
    }

    public void setIsEating(boolean flag)
    {
        if(flag)
        {
            dataTracker.set(18, (byte) 1);
        } else
        {
            dataTracker.set(18, (byte) 0);
        }
    }

    @Environment(EnvType.SERVER)
    public void sendRopePacket(World world, String typeName, int entityID, String roperID) {
        List list2 = world.players; /// ask about list players.
        if (list2.size() != 0) {
            for (int k = 0; k < list2.size(); k++) {
                ServerPlayerEntity player1 = (ServerPlayerEntity) list2.get(k);
                PacketHelper.sendTo(player1, new RopePacket(typeName, entityID, roperID));
            }
        }
    }

    @Override
    public void setName(String name) {}

    @Override
    public String getName() {return "";}

    @Override
    public String getOwner() {return "";}
}
