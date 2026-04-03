package net.kozibrodka.mocreatures.entity;

import net.fabricmc.api.EnvType;
import net.fabricmc.loader.FabricLoader;
import net.kozibrodka.mocreatures.events.mod_mocreatures;
import net.kozibrodka.mocreatures.mocreatures.AnimalChest;
import net.kozibrodka.mocreatures.mocreatures.MoCTools;
import net.kozibrodka.mocreatures.mocreatures.MoCreatureNamed;
import net.kozibrodka.mocreatures.mocreatures.MoGuiOpener;
import net.kozibrodka.mocreatures.network.NamePacket;
import net.kozibrodka.mocreatures.network.RopePacket;
import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.entity.Entity;
import net.minecraft.entity.ItemEntity;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.MobEntity;
import net.minecraft.entity.ai.pathing.Path;
import net.minecraft.entity.mob.MonsterEntity;
import net.minecraft.entity.passive.AnimalEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.inventory.Inventory;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.item.PickaxeItem;
import net.minecraft.item.SwordItem;
import net.minecraft.nbt.NbtCompound;
import net.minecraft.nbt.NbtList;
import net.minecraft.util.math.MathHelper;
import net.minecraft.world.World;
import net.modificationstation.stationapi.api.network.packet.PacketHelper;
import net.modificationstation.stationapi.api.server.entity.MobSpawnDataProvider;
import net.modificationstation.stationapi.api.util.Identifier;

import java.util.List;
import java.util.Objects;

public class EntityTurtle extends AnimalEntity implements MobSpawnDataProvider, MoCreatureNamed {

    public EntityTurtle(World world)
    {
        super(world);
        texture = "/assets/mocreatures/stationapi/textures/mob/turtle.png";
        setBoundingBoxSpacing(0.6F, 0.4F);
        movementSpeed = 0.3F;
        health = 15;
        maxhealth = 15;
        setAge(1.1F);
        localstack = new ItemStack[54];
    }

    public double getStandingEyeHeight() {

        if(vehicle instanceof PlayerEntity)
        {
            if (FabricLoader.INSTANCE.getEnvironmentType() == EnvType.SERVER){
                return (double)(standingEyeHeight - (-0.64F + getAge() / 6.0F));
            }else{
                return (double)(standingEyeHeight - (1.0F + getAge() / 6.0F));
            }
        } else
        {
            return (double)standingEyeHeight;
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

    public void onCollision(Entity otherEntity) {
        if(vehicle instanceof PlayerEntity && otherEntity == vehicle.vehicle){
            return;
        }else {
            super.onCollision(otherEntity);
        }
    }

    public int getTurtleChestSize(){
        /// Oryginalny rozmiar zostawiam kod
//        if(getAge() <= 1.4F){
//            return 9;
//        } else
//        if(getAge() <= 1.75F){
//            return 18;
//        } else
//        if(getAge() <= 2.1F){
//            return 27;
//        } else
//        if(getAge() <= 2.45F){
//            return 36;
//        } else
//        if(getAge() <= 2.8F){
//            return 45;
//        } else {
//            return 54;
//        }

        if(getAge() <= 1.3F){
            return 9;
        } else
        if(getAge() <= 1.5F){
            return 18;
        } else
        if(getAge() <= 1.7F){
            return 27;
        } else
        if(getAge() <= 1.9F){
            return 36;
        } else
        if(getAge() <= 2.1F){
            return 45;
        } else {
            return 54;
        }
    }

    public boolean interact(PlayerEntity entityplayer) {
        if(world.isRemote){
            return false;
        }
        if(getTamed() && getProtect() && !entityplayer.name.equals(getOwner())) /// owner lock
        {
            return false;
        }
        if(entityplayer.passenger != null && entityplayer.passenger != this){  /// anty dwa zolwie na glowie
            return false;
        }
        if(vehicle instanceof PlayerEntity && !Objects.equals(((PlayerEntity) vehicle).name, entityplayer.name)){ /// anty podbieranie
            return false;
        }
        if(getTamed()) {
            ItemStack itemstack = entityplayer.inventory.getSelectedItem();
            if(itemstack != null && entityplayer.name.equals(getOwner()) && itemstack.getItem() instanceof SwordItem && entityplayer.isSneaking())
            {
                if(getProtect()){
                    setProtect(false);
                    for(int var3 = 0; var3 < 7; ++var3) {
                        double var4 = this.random.nextGaussian() * 0.02D;
                        double var6 = this.random.nextGaussian() * 0.02D;
                        double var8 = this.random.nextGaussian() * 0.02D;
                        world.addParticle("heart", this.x + (double) (this.random.nextFloat() * this.width * 2.0F) - (double) this.width, this.y + 0.7D + (double) (this.random.nextFloat() * this.height), this.z + (double) (this.random.nextFloat() * this.width * 2.0F) - (double) this.width, var4, var6, var8);
                        sendParticle(world, "heart", this.x + (double) (this.random.nextFloat() * this.width * 2.0F) - (double) this.width, this.y + 0.7D + (double) (this.random.nextFloat() * this.height), this.z + (double) (this.random.nextFloat() * this.width * 2.0F) - (double) this.width, var4, var6, var8);
                    }
                }else{
                    setProtect(true);
                    for(int var3 = 0; var3 < 7; ++var3) {
                        double var4 = this.random.nextGaussian() * 0.02D;
                        double var6 = this.random.nextGaussian() * 0.02D;
                        double var8 = this.random.nextGaussian() * 0.02D;
                        world.addParticle("flame", this.x + (double) (this.random.nextFloat() * this.width * 2.0F) - (double) this.width, this.y + 1.0D + (double) (this.random.nextFloat() * this.height), this.z + (double) (this.random.nextFloat() * this.width * 2.0F) - (double) this.width, var4, var6, var8);
                        sendParticle(world, "flame", this.x + (double) (this.random.nextFloat() * this.width * 2.0F) - (double) this.width, this.y + 1.0D + (double) (this.random.nextFloat() * this.height), this.z + (double) (this.random.nextFloat() * this.width * 2.0F) - (double) this.width, var4, var6, var8);

                    }
                }
                return true;
            }
            /// ŻÓŁW wyłączony na razie - żeby śmiesznie chodził
//            if (itemstack != null  && itemstack.itemId == mod_mocreatures.whip.id) {
//                if (FabricLoader.INSTANCE.getEnvironmentType() == EnvType.SERVER){
//                    PacketHelper.sendTo(entityplayer, new JokeyPacket(1));
//                }
//                setSitting(!getSitting());
//                entityplayer.swingHand();
//                return true;
//            }
            /// Debug Age
//            if(itemstack != null && itemstack.itemId == Item.DIAMOND.id){
//                setAge(maxAge);
//                return true;
//            }

            if(getUpsideDown()) {
                flipflop(false);
                return true;
            }
            if(itemstack != null && (itemstack.itemId == mod_mocreatures.medallion.id || itemstack.itemId == Item.BOOK.id))
            {
                if(Objects.equals(getOwner(), "")){
                    setOwner(entityplayer.name);
                }
                setNameWithGui(this, entityplayer);
                return true;
            }
            if(itemstack != null && (itemstack.itemId == Item.SUGAR_CANE.id))
            {
                if(--itemstack.count == 0)
                {
                    entityplayer.inventory.setStack(entityplayer.inventory.selectedSlot, null);
                }
                if((health += 10) > maxhealth)
                {
                    health = maxhealth;
                }
                world.playSound(this, "mocreatures:eating", 1.0F, 1.0F + (random.nextFloat() - random.nextFloat()) * 0.2F);
                sendSound(world, "mocreatures:eating", 1.0F, 1.0F + (random.nextFloat() - random.nextFloat()) * 0.2F);
                return true;
            }
            if(itemstack != null && itemstack.getItem() instanceof PickaxeItem)
            {
                setDisplayName(!getDisplayName());
                return true;
            }
            if(entityplayer.isSneaking()){
                localturtlechest = new AnimalChest(localstack, "Turtle chest", getTurtleChestSize());
                entityplayer.openChestScreen(localturtlechest);
                return true;
            }
            if(vehicle != null){
                setVehicle((Entity)null);
                velocityX = entityplayer.velocityX * 5D;
                velocityY = entityplayer.velocityY / 2D + 0.2D;
                velocityZ = entityplayer.velocityZ * 5D;
                setPicked(false);
                if (net.fabricmc.loader.FabricLoader.INSTANCE.getEnvironmentType() == EnvType.SERVER) {
                    PacketHelper.sendTo(entityplayer, new RopePacket("turtle", this.id, entityplayer.name));
                }
                } else{
                yaw = entityplayer.yaw;
                world.playSound(this, "mob.chickenplop", 1.0F, (random.nextFloat() - random.nextFloat()) * 0.2F + 1.0F);
                sendSound(world, "mob.chickenplop", 1.0F, (random.nextFloat() - random.nextFloat()) * 0.2F + 1.0F);
                setVehicle(entityplayer);
                setPicked(true);
                if (net.fabricmc.loader.FabricLoader.INSTANCE.getEnvironmentType() == EnvType.SERVER) {
                    PacketHelper.sendTo(entityplayer, new RopePacket("turtle", this.id, entityplayer.name));
                }
                }

            return true;
        } else {
            flipflop(!getUpsideDown());
            return true;
        }
    }

    protected void jump() {
        if(isInFluid(Material.WATER)) {
            velocityY = 0.3D;
        }
    }

    public void tickMovement() {
        if(world.isRemote){
            super.tickMovement();
            if(getPicked()){
                lastWalkAnimationSpeed = 0.0F;
                walkAnimationSpeed = 0.0F;
                walkAnimationProgress = 0.0F;
            }
            return;
        }

        if(!getUpsideDown()) {
            LivingEntity entityplayer = getClosestEntityLiving(this, 4.0D);
            if(entityplayer != null && canSee(entityplayer) && !MoCTools.entitiesTamedIgnore(this, entityplayer)) {
                if(!getHiding()) {
                    world.playSound(this, "mocreatures:turtlehissing", 1.0F, 1.0F + (random.nextFloat() - random.nextFloat()) * 0.2F);
                    sendSound(world, "mocreatures:turtlehissing", 1.0F, 1.0F + (random.nextFloat() - random.nextFloat()) * 0.2F);
                    setHiding(true);
                }

                setPath((Path)null);
            } else {
                setHiding(false);
                if(!hasPath() && random.nextInt(50) == 0 && !getTamed()) {
                    ItemEntity pathentity = MoCTools.getClosestItem(this, 10.0D, Item.SUGAR_CANE.id, -1);
                    if(pathentity != null) {
                        float f = pathentity.getDistance(this);
                        if(f > 2.0F) {
                            getMyOwnPath(pathentity, f);
                        }

                        if(f < 2.0F && pathentity != null && deathTime == 0) {
                            pathentity.markDead();
                            world.playSound(this, "mocreatures:eating", 1.0F, 1.0F + (random.nextFloat() - random.nextFloat()) * 0.2F);
                            sendSound(world, "mocreatures:eating", 1.0F, 1.0F + (random.nextFloat() - random.nextFloat()) * 0.2F);
                            setTamed(true);
                            PlayerEntity ownerP = world.getClosestPlayer(this, 8.0D);
                            if(ownerP != null){
                                setOwner(ownerP.name);
                                setNameWithGui(this, ownerP);
                            }
                        }
                    }
                }
            }
        }

        if(!getUpsideDown() && getTamed() && random.nextInt(20) == 0) {
            PlayerEntity entityplayer1 = world.getClosestPlayer(this, 12.0D);
            if(entityplayer1 != null && !getSitting() && Objects.equals(entityplayer1.name, getOwner())) {
                Path pathentity1 = world.findPath(this, entityplayer1, 16.0F);
                setPath(pathentity1);
            }
        }
        if(isSwimming() && swimmerEntity()) {
            floating();
        }
        super.tickMovement();
    }

    public static void setNameWithGui(EntityTurtle turtie, PlayerEntity entityPlayer)
    {
        if (net.fabricmc.loader.FabricLoader.INSTANCE.getEnvironmentType() == EnvType.SERVER) {
            PacketHelper.sendTo(entityPlayer, new NamePacket(turtie.getName(), turtie.id));
            turtie.setDisplayName(true);
        }else{
            MoGuiOpener clientS = new MoGuiOpener();
            clientS.openTameGui(turtie, turtie.getName());
            turtie.setDisplayName(true);
        }
    }

    public void getMyOwnPath(Entity entity, float f) {
        Path pathentity = this.world.findPath(this, entity, 16.0F);
        if(pathentity != null) {
            this.setPath(pathentity);
        }

    }

    protected LivingEntity getClosestEntityLiving(Entity entity, double d) {
        double d1 = -1.0D;
        LivingEntity entityliving = null;
        List list = world.getEntities(this, boundingBox.expand(d, d, d));

        for(int i = 0; i < list.size(); ++i) {
            Entity entity1 = (Entity)list.get(i);
            if(!privateToIgnore(this, entity1)) {
                double d2 = entity1.getSquaredDistance(entity.x, entity.y, entity.z);
                if((d < 0.0D || d2 < d * d) && (d1 == -1.0D || d2 < d1) && ((LivingEntity)entity1).canSee(entity)) {
                    d1 = d2;
                    entityliving = (LivingEntity)entity1;
                }
            }
        }

        return entityliving;
    }

    public boolean privateToIgnore(Entity hunter, Entity victim) {
        return !(victim instanceof LivingEntity) || (victim instanceof MonsterEntity) ||victim instanceof EntityTurtle || victim.height <= this.height && victim.width <= this.width || victim instanceof PlayerEntity && getTamed() && Objects.equals(((PlayerEntity) victim).name, getOwner()) || (victim instanceof EntityKittyBed) || (victim instanceof EntityLitterBox);
    } /// ZOLW uzywa troche innej logiki, m in. poniewaz nie boi sie z graczy jedynie wlasciciela - aby trudniej było go ograbić.

    public boolean swimmerEntity() {
        return true;
    }

    public boolean isSwimming() {
        return isInFluid(Material.WATER);
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
            int distY = (int) MoCTools.distanceToSurface(this);
            if(distY > 1) {
                velocityY += (double)distY * 0.07D;
            }

            if(hasPath() && horizontalCollision) {
                jump();
            }
        }

    }

    public boolean canBreatheInWater() {
        return true;
    }

    public boolean damage(Entity damagesource, int i) {
        if (world.isRemote) {
            return false;
        }
        if(getHiding()) {
            if(damagesource instanceof PlayerEntity){
                if(random.nextInt(10) == 0) {
                    flipflop(true);
                }
            }else{
                if(random.nextInt(250) == 0 && !getTamed()) {
                    flipflop(true);
                }
                if(random.nextInt(100) == 0 && getTamed() && damagesource instanceof MobEntity){ /// zabawa z hunterem
                    ((MobEntity)damagesource).target = null;
                }
            }
            return false;
        } else {
            boolean flag = super.damage(damagesource, i);
            if(random.nextInt(3) == 0) {
                flipflop(true);
            }

            return flag;
        }
    }

    public void flipflop(boolean flip) {
        setUpsideDown(flip);
        setHiding(false);
        setPath((Path)null);
    }

    protected void tickLiving() {
        if(vehicle != null && vehicle instanceof PlayerEntity) {
            PlayerEntity entityplayer = (PlayerEntity)vehicle;
            yaw = entityplayer.yaw;
        } else {
            super.tickLiving();
        }
        if(vehicle == null && getPicked())
        {
            setPicked(false);
        }
        this.dataTracker.set(29, (byte) health);
    }

    public void tick() {
        if(getTamed() && getAge() < maxAge && random.nextInt(800) == 0 && !world.isRemote) { /// Oryginalny rozmiar max = 3.0F
            setAge(getAge()+0.01F);
        }
        if(getUpsideDown() && vehicle == null && random.nextInt(20) == 0) {
//            setSwinging(true);
            isSwinging = true;
            ++flopcounter;
        }
        if(isSwinging) { //getSwinging()
            swingProgress += 0.2F;
            boolean flag = flopcounter > random.nextInt(3) + 8;
            if(swingProgress > 2.0F && (!flag || random.nextInt(20) == 0)) {
//                setSwinging(false);
                isSwinging = false;
                swingProgress = 0.0F;
                if(random.nextInt(2) == 0) {
                    setTwistright(!getTwistright());
                }
            } else if(swingProgress > 9.0F && flag) {
//                setSwinging(false);
                isSwinging = false;
                swingProgress = 0.0F;
                if(!world.isRemote) {
                    world.playSound(this, "mocreatures:rabbitland", 1.0F, 1.0F + (random.nextFloat() - random.nextFloat()) * 0.2F);
                    sendSound(world, "mocreatures:rabbitland", 1.0F, 1.0F + (random.nextFloat() - random.nextFloat()) * 0.2F);
                    setUpsideDown(false);
                    flopcounter = 0;
                }
//                flopcounter = 0;
            }
//            dataTracker.set(24, Float.floatToRawIntBits(swingProgress));
        }
        /// oryginalnie BARDZO CHUJOWY KOD POD multiplayer.
        /// Zrobiłem, że client sam oblicza animacje, a serwer daje znać kiedy obrócić, Alternatywą jest ZLAGOWANA animacja, ale skordynowana z serwerem
        /// Na razie zostawiam kod datatrackingu zamazany.

        super.tick();
    }

    public void onKilledBy(Entity entity)
    {
        if(getTamed() && !world.isRemote){
            int i = MathHelper.floor(x);
            int j = MathHelper.floor(boundingBox.minY);
            int k = MathHelper.floor(z);
            TurtleRemoval(world,i,j,k);
        }
        super.onKilledBy(entity);
    }

    public void TurtleRemoval(World world, int i, int j, int k)
    {
        if(localstack != null)
        {
            localturtlechest = new AnimalChest(localstack, "TurtleChest", localstack.length);
            label0:
            for(int l = 0; l < localturtlechest.size(); l++)
            {
                ItemStack itemstack = localturtlechest.getStack(l);
                if(itemstack == null)
                {
                    continue;
                }
                float f = random.nextFloat() * 0.8F + 0.1F;
                float f1 = random.nextFloat() * 0.8F + 0.1F;
                float f2 = random.nextFloat() * 0.8F + 0.1F;
                do
                {
                    if(itemstack.count <= 0)
                    {
                        continue label0;
                    }
                    int i1 = random.nextInt(21) + 10;
                    if(i1 > itemstack.count)
                    {
                        i1 = itemstack.count;
                    }
                    itemstack.count -= i1;
                    ItemEntity entityitem = new ItemEntity(world, (float)i + f, (float)j + f1, (float)k + f2, new ItemStack(itemstack.itemId, i1, itemstack.getDamage()));
                    float f3 = 0.05F;
                    entityitem.velocityX = (float)random.nextGaussian() * f3;
                    entityitem.velocityY = (float)random.nextGaussian() * f3 + 0.2F;
                    entityitem.velocityZ = (float)random.nextGaussian() * f3;
                    world.spawnEntity(entityitem);
                } while(true);
            }
        }
    }

    public void markDead() /// Czy to ma jakikolwiek sens??? - bez checku remote, to powoduje duplikaty modelu na client
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

    protected boolean canDespawn()
    {
        return !getTamed();
    }

    protected boolean isMovementBlocked() {
        return getUpsideDown() || getHiding() || getSitting();
    }

    public boolean renderName() {
        return getDisplayName() && !getPicked();
    }

    public int getFlipDirection() {
        return getTwistright() ? 1 : -1;
    }

    public int getLimitPerChunk()
    {
        return 3;
    }

    protected int getDroppedItemId()
    {
        return Block.CHEST.id;
    }

    protected String getrandomomSound()
    {
        return "mocreatures:turtlegrunt";
    }

    protected String getHurtSound()
    {
        return "mocreatures:turtlehurt";
    }

    protected String getDeathSound()
    {
        return "mocreatures:turtledying";
    }

    public boolean canSpawn()
    {
        return mod_mocreatures.mocGlass.animals.turtlefreq > 0 && super.canSpawn();
    }

    private boolean isSwinging;
    private boolean twistright;
    private int flopcounter;
    public float maxAge = 2.2F;
    public int maxhealth;
    public float swingProgress;
    private Inventory localturtlechest;
    public ItemStack localstack[];

    @Override
    public Identifier getHandlerIdentifier() {return Identifier.of(mod_mocreatures.MOD_ID, "Turtle");}

    protected void initDataTracker()
    {
        super.initDataTracker();
        dataTracker.startTracking(16, (byte) 0); //Tamed
        dataTracker.startTracking(17, (int) 0); //Age
        dataTracker.startTracking(18, (byte) 0); //UpsideDown
        dataTracker.startTracking(19, (byte) 0); //Hiding
        dataTracker.startTracking(20, (byte) 0); //isSwinging
        dataTracker.startTracking(21, (byte) 0); //Protect From Players/Public
        dataTracker.startTracking(22, (byte) 0); //Display Name
        dataTracker.startTracking(23, (byte) 0); //Picked
        dataTracker.startTracking(24, (int) 0); //Swinging
        dataTracker.startTracking(25, (byte) 0); //TwistRight
        dataTracker.startTracking(26, (byte) 0); //Sitting

        dataTracker.startTracking(29, (byte) 0); //HEALTH
        dataTracker.startTracking(30, ""); //Owner
        dataTracker.startTracking(31, ""); //Name
    }

    public void writeNbt(NbtCompound nbttagcompound)
    {
        super.writeNbt(nbttagcompound);
        nbttagcompound.putBoolean("Tamed", getTamed());
        nbttagcompound.putFloat("Edad", getAge());
        nbttagcompound.putBoolean("UpsideDown", getUpsideDown());
        nbttagcompound.putString("Name", getName());
        nbttagcompound.putBoolean("DisplayName", getDisplayName());
        nbttagcompound.putString("TurtleOwner", getOwner());
        nbttagcompound.putBoolean("Hiding", getHiding());
        nbttagcompound.putBoolean("Sitting", getSitting());

        NbtList nbttaglist = new NbtList();
        for(int i = 0; i < localstack.length; i++)
        {
            if(localstack[i] != null)
            {
                NbtCompound nbttagcompound1 = new NbtCompound();
                nbttagcompound1.putByte("Slot", (byte)i);
                localstack[i].writeNbt(nbttagcompound1);
                nbttaglist.add(nbttagcompound1);
            }
        }

        nbttagcompound.put("Items", nbttaglist);
    }

    public void readNbt(NbtCompound nbttagcompound)
    {
        super.readNbt(nbttagcompound);
        setTamed(nbttagcompound.getBoolean("Tamed"));
        setAge(nbttagcompound.getFloat("Edad"));
        setUpsideDown(nbttagcompound.getBoolean("UpsideDown"));
        setName(nbttagcompound.getString("Name"));
        setDisplayName(nbttagcompound.getBoolean("DisplayName"));
        setOwner(nbttagcompound.getString("TurtleOwner"));
        setHiding(nbttagcompound.getBoolean("Hiding"));
        setSitting(nbttagcompound.getBoolean("Sitting"));

        NbtList nbttaglist = nbttagcompound.getList("Items");
        localstack = new ItemStack[54];
        for(int i = 0; i < nbttaglist.size(); i++)
        {
            NbtCompound nbttagcompound1 = (NbtCompound)nbttaglist.get(i);
            int j = nbttagcompound1.getByte("Slot") & 0xff;
            if(j >= 0 && j < localstack.length)
            {
                localstack[j] = new ItemStack(nbttagcompound1);
            }
        }
    }

    //TAMED
    public boolean getTamed()
    {
        return (dataTracker.getByte(16) & 1) != 0;
    }

    public void setTamed(boolean flag)
    {
        if(flag)
        {
            dataTracker.set(16, Byte.valueOf((byte)1));
        } else
        {
            dataTracker.set(16, Byte.valueOf((byte)0));
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

    //UPSITE DOWN
    public boolean getUpsideDown()
    {
        return (dataTracker.getByte(18) & 1) != 0;
    }

    public void setUpsideDown(boolean flag)
    {
        if(flag)
        {
            dataTracker.set(18, Byte.valueOf((byte)1));
        } else
        {
            dataTracker.set(18, Byte.valueOf((byte)0));
        }
    }

    //HIDING
    public boolean getHiding()
    {
        return (dataTracker.getByte(19) & 1) != 0;
    }

    public void setHiding(boolean flag)
    {
        if(flag)
        {
            dataTracker.set(19, Byte.valueOf((byte)1));
        } else
        {
            dataTracker.set(19, Byte.valueOf((byte)0));
        }
    }

    //SWINGING
    public boolean getSwinging()
    {
        return (dataTracker.getByte(20) & 1) != 0;
    }

    public void setSwinging(boolean flag)
    {
        if(flag)
        {
            dataTracker.set(20, Byte.valueOf((byte)1));
        } else
        {
            dataTracker.set(20, Byte.valueOf((byte)0));
        }
    }

    //PROTECT FROM PLAYERES
    public boolean getProtect()
    {
        return (dataTracker.getByte(21) & 1) != 0;
    }

    public void setProtect(boolean flag)
    {
        if(flag)
        {
            dataTracker.set(21, Byte.valueOf((byte)1));
        } else
        {
            dataTracker.set(21, Byte.valueOf((byte)0));
        }
    }

    //DISPLAY NAME
    public boolean getDisplayName()
    {
        return (dataTracker.getByte(22) & 1) != 0;
    }

    public void setDisplayName(boolean flag)
    {
        if(flag)
        {
            dataTracker.set(22, Byte.valueOf((byte)1));
        } else
        {
            dataTracker.set(22, Byte.valueOf((byte)0));
        }
    }

    //Picked
    public boolean getPicked()
    {
        return (dataTracker.getByte(23) & 1) != 0;
    }

    public void setPicked(boolean flag)
    {
        if(flag)
        {
            dataTracker.set(23, (byte) 1);
        } else
        {
            dataTracker.set(23, (byte) 0);
        }
    }

    // Swinging
    public float getSwingProgress()
    {
        return Float.intBitsToFloat(dataTracker.getInt(24));
    }


    //TWISTRIGHT
    public boolean getTwistright()
    {
//        return (dataTracker.getByte(25) & 1) != 0;
        return twistright;
    }

    public void setTwistright(boolean flag)
    {
        twistright = flag;
//        if(flag)
//        {
//            dataTracker.set(25, (byte) 1);
//        } else
//        {
//            dataTracker.set(25, (byte) 0);
//        }
    }

    //SITTING
    public boolean getSitting()
    {
        return (dataTracker.getByte(26) & 1) != 0;
    }

    public void setSitting(boolean flag)
    {
        if(flag)
        {
            dataTracker.set(26, (byte) 1);
        } else
        {
            dataTracker.set(26, (byte) 0);
        }
    }

    /// HEALTH
    public int getHealth()
    {
        return dataTracker.getByte(29);
    }

    //OWNER
    public void setOwner(String owner)
    {
        this.dataTracker.set(30, owner);
    }

    public String getOwner()
    {
        return this.dataTracker.getString(30);
    }

    //NAME
    public void setName(String name)
    {
        this.dataTracker.set(31, name);
    }

    public String getName()
    {
        return this.dataTracker.getString(31);
    }

    public void sendParticle(World world, String name, double x, double y, double z, double i, double j, double k){
        if (net.fabricmc.loader.FabricLoader.INSTANCE.getEnvironmentType() == EnvType.SERVER){
            mod_mocreatures.particlePacket(world,name,x,y,z,i,j,k);
        }
    }

    public void sendSound(World world, String name, float vol, float pit){
        if (net.fabricmc.loader.FabricLoader.INSTANCE.getEnvironmentType() == EnvType.SERVER){
            mod_mocreatures.voicePacket(world, name, this.id, vol, pit);
        }
    }
}
