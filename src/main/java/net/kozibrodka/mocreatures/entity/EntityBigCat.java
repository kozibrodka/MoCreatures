package net.kozibrodka.mocreatures.entity;


import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.fabricmc.loader.FabricLoader;
import net.kozibrodka.mocreatures.events.mod_mocreatures;
import net.kozibrodka.mocreatures.mocreatures.MoCTools;
import net.kozibrodka.mocreatures.mocreatures.MoCreatureNamed;
import net.kozibrodka.mocreatures.mocreatures.MoCreatureRacial;
import net.kozibrodka.mocreatures.mocreatures.MoGuiOpener;
import net.kozibrodka.mocreatures.network.AskPacket;
import net.kozibrodka.mocreatures.network.JokeyPacket;
import net.kozibrodka.mocreatures.network.NamePacket;
import net.kozibrodka.mocreatures.network.RopePacket;
import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.entity.Entity;
import net.minecraft.entity.ItemEntity;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.MobEntity;
import net.minecraft.entity.ai.pathing.Path;
import net.minecraft.entity.mob.CreeperEntity;
import net.minecraft.entity.mob.GhastEntity;
import net.minecraft.entity.mob.MonsterEntity;
import net.minecraft.entity.passive.AnimalEntity;
import net.minecraft.entity.passive.WolfEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.player.ServerPlayerEntity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.item.PickaxeItem;
import net.minecraft.item.SwordItem;
import net.minecraft.nbt.NbtCompound;
import net.minecraft.util.math.Box;
import net.minecraft.util.math.MathHelper;
import net.minecraft.world.World;
import net.modificationstation.stationapi.api.network.packet.PacketHelper;
import net.modificationstation.stationapi.api.util.Identifier;
import net.modificationstation.stationapi.api.server.entity.MobSpawnDataProvider;

import java.util.List;
import java.util.Objects;


public class EntityBigCat extends AnimalEntity implements MobSpawnDataProvider, MoCreatureRacial, MoCreatureNamed
{

    public EntityBigCat(World world)
    {
        super(world);
        setAge(0.35F);
        setHungry(true);
        setBoundingBoxSpacing(0.9F, 1.3F);
        health = 25;
        force = 1;
        attackRange = 1.0D;
        maxhealth = 25;
        typechosen = false;
    }

    protected void initDataTracker()
    {
        super.initDataTracker();
        dataTracker.startTracking(16, (byte) 0); //Type
        dataTracker.startTracking(17, (int) 0); //Age
        dataTracker.startTracking(18, (byte) 0); //Adult
        dataTracker.startTracking(19, (byte) 0); //Eaten
        dataTracker.startTracking(20, (byte) 0); //Tamed
        dataTracker.startTracking(21, (byte) 0); //Sitting
        dataTracker.startTracking(22, (byte) 0); //Hungry
        dataTracker.startTracking(23, (byte) 0); //Protect From Players
        dataTracker.startTracking(24, (byte) 0); //Display Name
        dataTracker.startTracking(29, (byte) 0); //HEALTH
        dataTracker.startTracking(30, ""); //Owner
        dataTracker.startTracking(31, ""); //Name
    }


    public void chooseType(int type)
    {
            if(type == 1)
            {
                texture = "/assets/mocreatures/stationapi/textures/mob/lionf.png";
                widthF = 1.0F;
                heightF = 1.0F;
                lengthF = 1.0F;
                movementSpeed = 1.4F;
                attackRange = 8D;
                force = 5;
                maxhealth = 25;
            } else
            if(type == 2)
            {
                texture = "/assets/mocreatures/stationapi/textures/mob/lionf.png";
                widthF = 1.1F;
                heightF = 1.1F;
                lengthF = 1.0F;
                movementSpeed = 1.4F;
                attackRange = 4D;
                force = 5;
                maxhealth = 30;
            }
            if(type == 3)
            {
                texture = "/assets/mocreatures/stationapi/textures/mob/panther.png";
                widthF = 0.9F;
                heightF = 0.9F;
                lengthF = 0.9F;
                movementSpeed = 1.6F;
                attackRange = 6D;
                force = 4;
                maxhealth = 20;
            } else
            if(type == 4)
            {
                texture = "/assets/mocreatures/stationapi/textures/mob/cheetah.png";
                widthF = 0.8F;
                heightF = 0.8F;
                lengthF = 1.0F;
                movementSpeed = 1.9F;
                attackRange = 6D;
                force = 3;
                maxhealth = 20;
            } else
            if(type == 5)
            {
                texture = "/assets/mocreatures/stationapi/textures/mob/tiger.png";
                widthF = 1.1F;
                heightF = 1.1F;
                lengthF = 1.1F;
                movementSpeed = 1.6F;
                attackRange = 8D;
                force = 6;
                maxhealth = 35;
            } else
            if(type == 6)
            {
                texture = "/assets/mocreatures/stationapi/textures/mob/leopard.png";
                widthF = 0.8F;
                heightF = 0.8F;
                lengthF = 0.9F;
                movementSpeed = 1.7F;
                attackRange = 4D;
                force = 3;
                maxhealth = 25;
            } else
            if(type == 7)
            {
                texture = "/assets/mocreatures/stationapi/textures/mob/tigerw.png";
                widthF = 1.2F;
                heightF = 1.2F;
                lengthF = 1.2F;
                movementSpeed = 1.7F;
                attackRange = 10D;
                force = 8;
                maxhealth = 40;
            }
    }

    public int getRandomRace()
    {
        int i = random.nextInt(100);
        if(i <= 5)
        {
            return 1;
        } else
        if(i <= 25)
        {
            return 2;
        } else
        if(i <= 50)
        {
            return 3;
        } else
        if(i <= 70)
        {
            return 4;
        } else
        if(i <= 75)
        {
            return 7;
        } else
        {
            return 5;
        }
    }

    protected void tickLiving() {
        super.tickLiving();
        this.dataTracker.set(29, (byte) health);
    }

    public void tickMovement()
    {
        super.tickMovement();
        if(!typechosen && world.isRemote && getType() != 0){
            typechosen = true;
            chooseType(getType());
            PacketHelper.send(new AskPacket(this.id, "tiger"));
        }
        if(!world.isRemote) {
            if (!getAdult() && random.nextInt(250) == 0 && !world.isRemote) {
                setAge(getAge() + 0.01F);
                if (getAge() >= 1.0F) {
                    setAdult(true);
                    setAge(1.0F);
                    killedByOtherEntity = false;
                }
            }
            if(!getHungry()){
                if((this.health < this.maxhealth) && getTamed()){
                    if(random.nextInt(50) == 0){
                        setHungry(true);
                    }
                }else{
                    if(random.nextInt(200) == 0){
                        setHungry(true);
                    }
                }
            }
            if (roper != null && random.nextInt(20) == 0) {
                float f = roper.getDistance(this);
                if (f > 8F && !getSitting()) {
                    getPathOrWalkableBlock(roper, f);
                }
                if ((f > 18F) & getSitting()) {
                    roper = null;
                    if (net.fabricmc.loader.FabricLoader.INSTANCE.getEnvironmentType() == EnvType.SERVER) {
                        sendRopePacket(world, "tiger", this.id, "");
                    }
                    ropeRemoval(world, this.x,this.y,this.z);
                }
            }
            if (deathTime == 0 && getHungry()) {
                ItemEntity entityitem = getClosestItem(this, 12D, Item.RAW_PORKCHOP.id, Item.RAW_FISH.id);
                if (entityitem != null) {
                    if(!getSitting()){
                        MoveToNextEntity(entityitem);
                    }
                    ItemEntity entityitem1 = getClosestItem(this, 2D, Item.RAW_PORKCHOP.id, Item.RAW_FISH.id);
                    if (random.nextInt(80) == 0 && entityitem1 != null && deathTime == 0) {
                        entityitem1.markDead();
                        if (health + 10 > maxhealth) {
                            health = maxhealth;
                        } else {
                            health += 10;
                        }
                        if (!getAdult() && getAge() < 0.8F) {
                            setEaten(true);
                        }
                        world.playSound(this, "mocreatures:eating", 1.0F, 1.0F + (random.nextFloat() - random.nextFloat()) * 0.2F);
                        sendSound(world, "mocreatures:eating", 1.0F, 1.0F + (random.nextFloat() - random.nextFloat()) * 0.2F);
                        setHungry(false);
                    }
                }
            }
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

    protected boolean isMovementBlocked()
    {
        return getSitting();
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
                    velocityX += 0.029999999999999999D;
                }
            } else
            {
                double d1 = x - entity.x;
                if(d1 > 0.5D)
                {
                    velocityX -= 0.029999999999999999D;
                }
            }
            if(z < (double)k)
            {
                double d2 = entity.z - z;
                if(d2 > 0.5D)
                {
                    velocityZ += 0.029999999999999999D;
                }
            } else
            {
                double d3 = z - entity.z;
                if(d3 > 0.5D)
                {
                    velocityZ -= 0.029999999999999999D;
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

    public float b(float f, float f1, float f2)
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

    public ItemEntity getClosestItem(Entity entity, double d, int i, int j)
    {
        double d1 = -1D;
        ItemEntity entityitem = null;
        List list = world.getEntities(this, boundingBox.expand(d, d, d));
        for(int k = 0; k < list.size(); k++)
        {
            Entity entity1 = (Entity)list.get(k);
            if(!(entity1 instanceof ItemEntity))
            {
                continue;
            }
            ItemEntity entityitem1 = (ItemEntity)entity1;
            if(entityitem1.stack.itemId != i && j != 0 && entityitem1.stack.itemId != j)
            {
                continue;
            }
            double d2 = entityitem1.getSquaredDistance(entity.x, entity.y, entity.z);
            if((d < 0.0D || d2 < d * d) && (d1 == -1D || d2 < d1))
            {
                d1 = d2;
                entityitem = entityitem1;
            }
        }

        return entityitem;
    }

    protected Entity getTargetInRange()
    {
        if(roper != null && getProtect())
        {
            return getMastersEnemy((PlayerEntity)roper, 12D);
        }
        if(world.difficulty > 0)
        {
            PlayerEntity entityplayer = world.getClosestPlayer(this, 12D);
            if(!getTamed() && entityplayer != null && getAdult() && getHungry())
            {
                if(getType() == 1 || getType() == 5 || getType() == 7)
                {
                    setHungry(false);
                    return entityplayer;
                }
                if(random.nextInt(30) == 0)
                {
                    setHungry(false);
                    return entityplayer;
                }
            }
            if(random.nextInt(80) == 0 && getHungry() && !getSitting())
            {
                LivingEntity entityliving = getClosestTarget(this, 10D);
                setHungry(false);
                return entityliving;
            }
        }
        return null;
    }

    public LivingEntity getClosestTarget(Entity entity, double d)
    {
        double d1 = -1D;
        LivingEntity entityliving = null;
        List list = world.getEntities(this, boundingBox.expand(d, d, d));
        for(int i = 0; i < list.size(); i++)
        {
            Entity entity1 = (Entity)list.get(i);
            if(privateToIgnore(this, entity1) || MoCTools.entitiesToIgnore(this, entity1) || MoCTools.entitiesTamedIgnore(this, entity1) || monstersToIgnore(this,entity1))
            {
                continue;
            }
            if(entity1 instanceof EntityBigCat)
            {
                if(!getAdult())
                {
                    continue;
                }
                EntityBigCat entitybigcat = (EntityBigCat)entity1;
                if(getTamed() && entitybigcat.getTamed() || entitybigcat.getType() == 7 || getType() != 2 && getType() == entitybigcat.getType() || getType() == 2 && entitybigcat.getType() == 1 || health < entitybigcat.health)
                {
                    continue;
                }
            }
            double d2 = entity1.getSquaredDistance(entity.x, entity.y, entity.z);
            if((d < 0.0D || d2 < d * d) && (d1 == -1D || d2 < d1) && ((LivingEntity)entity1).canSee(entity))
            {
                d1 = d2;
                entityliving = (LivingEntity)entity1;
            }
        }

        return entityliving;
    }

    public boolean privateToIgnore(Entity hunter, Entity victim) {
        return (!getAdult() && ((double)victim.width > 0.5D || (double)victim.height > 0.5D) || (victim instanceof EntityShark) || (victim instanceof MonsterEntity) && (!getTamed() || !getAdult()) || (victim instanceof EntityHippo) || (victim instanceof EntityCrocodile) && ((EntityCrocodile) victim).getAge() > 1.1F || victim instanceof EntityElephant && ((EntityElephant) victim).getAdult());
    } ///Według kodu oswojony BigCat ADult ma atakowac potwory. BiCat jeżeli siedzi lub jest na Lince nie atakuje randomowo, jest w miare grzeczny.

    public boolean monstersToIgnore(Entity hunter, Entity victim) {
        return ((victim instanceof CreeperEntity) || (victim instanceof GhastEntity) || (victim instanceof EntityWerewolf) && !((EntityWerewolf) victim).getHumanForm() || (victim instanceof EntityOgre));
    } /// Dodałem wyjątki potworów, bo przegięcie by było gdyby Agrował na Wybuchowe itd.

    public MobEntity getMastersEnemy(PlayerEntity entityplayer, double d)
    {
        double d1 = -1D;
        MobEntity entitycreature = null;
        List list = world.getEntities(entityplayer, boundingBox.expand(d, 4D, d));
        for(int i = 0; i < list.size(); i++)
        {
            Entity entity = (Entity)list.get(i);
            if(!(entity instanceof MobEntity) || entity == this || (entity instanceof CreeperEntity) || (entity instanceof GhastEntity))
            {
                continue;
            }
            MobEntity entitycreature1 = (MobEntity)entity;
            if(entitycreature1 != null && entitycreature1.target == entityplayer)
            {
                return entitycreature1;
            }
        }

        return entitycreature;
    }

    public boolean damage(Entity entitybase, int i)
    {
        if(super.damage(entitybase, i))
        {
            if(passenger == entitybase || vehicle == entitybase)
            {
                return true;
            }
            if(getTamed())
            {
                if(entitybase instanceof PlayerEntity) {
                    PlayerEntity gracz = (PlayerEntity) entitybase;
                    if (!gracz.name.equals(getOwner())) {
                        target = entitybase;
                    }
                }else if(entitybase != this){
                    setSitting(false);
                    target = entitybase;
                }

                if(target != null && target instanceof LivingEntity && !(target instanceof GhastEntity)){ /// wyłączenie ghasta na wszelki
                    notifyAdultLions(this, (LivingEntity) target);
                }
            }
            if(entitybase != this && world.difficulty > 0 && !getTamed())
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
        if(f > 2.0F && f < 6F && random.nextInt(50) == 0)
        {
            if(onGround)
            {
                double d = entity.x - x;
                double d1 = entity.z - z;
                float f1 = MathHelper.sqrt(d * d + d1 * d1);
                velocityX = (d / (double)f1) * 0.5D * 0.80000000000000004D + velocityX * 0.20000000000000001D;
                velocityZ = (d1 / (double)f1) * 0.5D * 0.80000000000000004D + velocityZ * 0.20000000000000001D;
                velocityY = 0.40000000000000002D;
            }
        } else
        if((double)f < 2.5D && entity.boundingBox.maxY > boundingBox.minY && entity.boundingBox.minY < boundingBox.maxY)
        {
            attackCooldown = 20;
            entity.damage(this, force);
            if(!(entity instanceof PlayerEntity))
            {
                destroyDrops(this, 3D);
            }
        }
    }

    public void notifyAdultLions(EntityBigCat assaulted, LivingEntity attacker){
        for(Object var5 : this.world.collectEntitiesByClass(EntityBigCat.class, Box.createCached(this.x, this.y, this.z, this.x + (double)1.0F, this.y + (double)1.0F, this.z + (double)1.0F).expand((double)16.0F, (double)4.0F, (double)16.0F))) {
            EntityBigCat tiger = (EntityBigCat)var5;
            if(Objects.equals(assaulted.getOwner(), tiger.getOwner()) && tiger.getAdult() && tiger.getTamed() && tiger.target == null) {
                tiger.setSitting(false);
                tiger.target = attacker;
            }
        }
    }

    public void writeNbt(NbtCompound nbttagcompound)
    {
        super.writeNbt(nbttagcompound);
        nbttagcompound.putInt("TypeInt", getType());
        nbttagcompound.putBoolean("Adult", getAdult());
        nbttagcompound.putBoolean("Tamed", getTamed());
        nbttagcompound.putBoolean("Sitting", getSitting());
        nbttagcompound.putFloat("Edad", getAge());
        nbttagcompound.putString("Name", getName());
        nbttagcompound.putBoolean("DisplayName", getDisplayName());
        nbttagcompound.putString("TigerOwner", getOwner());
        nbttagcompound.putBoolean("ProtectFromPlayers", getProtect());
        nbttagcompound.putBoolean("Hungry", getHungry());
        nbttagcompound.putBoolean("Eaten", getEaten());
    }

    public void readNbt(NbtCompound nbttagcompound)
    {
        super.readNbt(nbttagcompound);
        setType(nbttagcompound.getInt("TypeInt"));
        setAdult(nbttagcompound.getBoolean("Adult"));
        setTamed(nbttagcompound.getBoolean("Tamed"));
        setSitting(nbttagcompound.getBoolean("Sitting"));
        setAge(nbttagcompound.getFloat("Edad"));
        setTamed(nbttagcompound.getBoolean("Tamed"));
        setName(nbttagcompound.getString("Name"));
        setDisplayName(nbttagcompound.getBoolean("DisplayName"));
        setOwner(nbttagcompound.getString("TigerOwner"));
        setProtect(nbttagcompound.getBoolean("ProtectFromPlayers"));
        setHungry(nbttagcompound.getBoolean("Hungry"));
        setEaten(nbttagcompound.getBoolean("Eaten"));
    }

    public boolean interact(PlayerEntity entityplayer)
    {
        if(world.isRemote)
        {
            return false;
        }
            ItemStack itemstack = entityplayer.inventory.getSelectedItem();
            if(getTamed() && !entityplayer.name.equals(getOwner())){
                return false;
            }
            if (itemstack != null && getTamed() && itemstack.getItem() instanceof SwordItem) {
                if (getProtect()) {
                    setProtect(false);
                    for (int var3 = 0; var3 < 7; ++var3) {
                        double var4 = this.random.nextGaussian() * 0.02D;
                        double var6 = this.random.nextGaussian() * 0.02D;
                        double var8 = this.random.nextGaussian() * 0.02D;
                        world.addParticle("heart", this.x + (double) (this.random.nextFloat() * this.width * 2.0F) - (double) this.width, this.y + 0.5D + (double) (this.random.nextFloat() * this.height), this.z + (double) (this.random.nextFloat() * this.width * 2.0F) - (double) this.width, var4, var6, var8);
                        sendParticle(world, "heart", this.x + (double) (this.random.nextFloat() * this.width * 2.0F) - (double) this.width, this.y + 0.5D + (double) (this.random.nextFloat() * this.height), this.z + (double) (this.random.nextFloat() * this.width * 2.0F) - (double) this.width, var4, var6, var8);
                    }
                } else {
                    setProtect(true);
                    for (int var3 = 0; var3 < 7; ++var3) {
                        double var4 = this.random.nextGaussian() * 0.02D;
                        double var6 = this.random.nextGaussian() * 0.02D;
                        double var8 = this.random.nextGaussian() * 0.02D;
                        world.addParticle("flame", this.x + (double) (this.random.nextFloat() * this.width * 2.0F) - (double) this.width, this.y + 0.5D + (double) (this.random.nextFloat() * this.height), this.z + (double) (this.random.nextFloat() * this.width * 2.0F) - (double) this.width, var4, var6, var8);
                        sendParticle(world, "flame", this.x + (double) (this.random.nextFloat() * this.width * 2.0F) - (double) this.width, this.y + 0.5D + (double) (this.random.nextFloat() * this.height), this.z + (double) (this.random.nextFloat() * this.width * 2.0F) - (double) this.width, var4, var6, var8);
                    }
                }
                return true;
            }
            if (itemstack != null && !getTamed() && getEaten() && itemstack.itemId == mod_mocreatures.medallion.id) {
                if (--itemstack.count == 0) {
                    entityplayer.inventory.setStack(entityplayer.inventory.selectedSlot, null);
                }
                setTamed(true);
                setProtect(true);
                setOwner(entityplayer.name);
                setNameWithGui(this, entityplayer);
                return true;
            }
            if (itemstack != null  && getTamed() && itemstack.itemId == mod_mocreatures.whip.id) {
                if (FabricLoader.INSTANCE.getEnvironmentType() == EnvType.SERVER){
                    PacketHelper.sendTo(entityplayer, new JokeyPacket(1));
                }
                setSitting(!getSitting());
                entityplayer.swingHand();
                return true;
            }
            if (itemstack != null  && getTamed() && itemstack.getItem() instanceof PickaxeItem) {
                setDisplayName(!getDisplayName());
                return true;
            }
            if (itemstack != null  && getTamed() && (itemstack.itemId == mod_mocreatures.medallion.id || itemstack.itemId == Item.BOOK.id)) {
                setNameWithGui(this, entityplayer);
                return true;
            }
            if (itemstack != null  && passenger == null && roper == null && getTamed() && itemstack.itemId == mod_mocreatures.rope.id) {
                if (--itemstack.count == 0) {
                    entityplayer.inventory.setStack(entityplayer.inventory.selectedSlot, null);
                }
                world.playSound(this, "mocreatures:roping", 1.0F, 1.0F + (random.nextFloat() - random.nextFloat()) * 0.2F);
                sendSound(world, "mocreatures:roping", 1.0F, 1.0F + (random.nextFloat() - random.nextFloat()) * 0.2F);
                roper = entityplayer;
                if (net.fabricmc.loader.FabricLoader.INSTANCE.getEnvironmentType() == EnvType.SERVER) {
                    sendRopePacket(world, "tiger", this.id, entityplayer.name);
                }
                return true;
            }
            if (roper != null && getTamed()) {
                entityplayer.inventory.addStack(new ItemStack(mod_mocreatures.rope));
                world.playSound(this, "mocreatures:roping", 1.0F, 1.0F + (random.nextFloat() - random.nextFloat()) * 0.2F);
                sendSound(world, "mocreatures:roping", 1.0F, 1.0F + (random.nextFloat() - random.nextFloat()) * 0.2F);
                roper = null;
                if (net.fabricmc.loader.FabricLoader.INSTANCE.getEnvironmentType() == EnvType.SERVER) {
                    sendRopePacket(world, "tiger", this.id, "");
                }
                return true;
            } else {
                return false;
            }
    }

    protected String getRandomSound()
    {
        if(getAdult())
        {
            return "mocreatures:liongrunt";
        } else
        {
            return "mocreatures:cubgrunt";
        }
    }

    protected String getHurtSound()
    {
        if(getAdult())
        {
            return "mocreatures:lionhurt";
        } else
        {
            return "mocreatures:cubhurt";
        }
    }

    protected String getDeathSound()
    {
        if(getAdult())
        {
            return "mocreatures:liondeath";
        } else
        {
            return "mocreatures:cubdying";
        }
    }

    protected int getDroppedItemId()
    {
        return mod_mocreatures.bigcatclaw.id;
    }

    public void destroyDrops(Entity entity, double d)
    {
        if(getTamed())
        {
            return;
        }
        List list = world.getEntities(entity, entity.boundingBox.expand(d, d, d));
        for(int i = 0; i < list.size(); i++)
        {
            Entity entity1 = (Entity)list.get(i);
            if(!(entity1 instanceof ItemEntity))
            {
                continue;
            }
            ItemEntity entityitem = (ItemEntity)entity1;
            if(entityitem != null && entityitem.itemAge < 50 && mocr.mocreaturesGlass.huntercreatures.destroyitems)
            {
                entityitem.markDead();
            }
        }

    }

    public int getLimitPerChunk()
    {
        return 4;
    }

    protected boolean canDespawn()
    {
        return !getTamed();
    }

    public void markDead()
    {
        if(getTamed() && health > 0)
        {
            return;
        } else
        {
            super.markDead();
            return;
        }
    }

    public int checkNearBigKitties(double d)
    {
        boolean flag = false;
        List list = world.getEntities(this, boundingBox.expand(d, d, d));
        for(int j = 0; j < list.size(); j++)
        {
            Entity entity = (Entity)list.get(j);
            if(entity != this && (entity instanceof EntityBigCat))
            {
                EntityBigCat entitybigcat = (EntityBigCat)entity;
                int i = entitybigcat.getType();
                if(i == 2)
                {
                    i = 1;
                }
                return i;
            }
        }

        return 0;
    }

    public boolean canSpawn()
    {
        return mocr.mocreaturesGlass.huntercreatures.lionfreq > 0 && !MoCTools.isNearTorch(this) && super.canSpawn(); /// Opcja z pochodniami dodatkowo dla Hunters??? //todo obczaj KOD
    }

    public boolean renderName()
    {
        return !getName().isEmpty() && getDisplayName() && mocr.mocreaturesGlass.othersettings.displayname;
    }

    public Entity ustawCel(LivingEntity gracz)
    {
        this.target = gracz;
        return null;
    }

    public void wstanSzybko()
    {
        setSitting(false);
    }

    public static void setNameWithGui(EntityBigCat entitybigcat, PlayerEntity entityPlayer)
    {
        if (net.fabricmc.loader.FabricLoader.INSTANCE.getEnvironmentType() == EnvType.SERVER) {
            PacketHelper.sendTo(entityPlayer, new NamePacket(entitybigcat.getName(), entitybigcat.id));
            entitybigcat.setDisplayName(true);
        }else{
            MoGuiOpener clientS = new MoGuiOpener();
            clientS.openTameGui(entitybigcat, entitybigcat.getName());
            entitybigcat.setDisplayName(true);
        }
    }

    mod_mocreatures mocr = new mod_mocreatures();
    protected int force;
    protected double attackRange;
    public boolean typechosen;
    public float heightF;
    public float widthF;
    public float lengthF;
    public int maxhealth;
    public LivingEntity roper;

    @Override
    public Identifier getHandlerIdentifier() {
        return Identifier.of(mod_mocreatures.MOD_ID, "BigCat");
    }

    public void sendParticle(World world, String name, double x, double y, double z, double i, double j, double k){
        if (net.fabricmc.loader.FabricLoader.INSTANCE.getEnvironmentType() == EnvType.SERVER){
            mocr.particlePacket(world,name,x,y,z,i,j,k);
        }
    }

    public void sendSound(World world, String name, float vol, float pit){
        if (net.fabricmc.loader.FabricLoader.INSTANCE.getEnvironmentType() == EnvType.SERVER){
            mocr.voicePacket(world, name, this.id, vol, pit);
        }
    }

    @Environment(EnvType.SERVER)
    public void sendRopePacket(World world, String typeName, int entityID, String roperID) {
        List list2 = world.players;
        if (list2.size() != 0) {
            for (int k = 0; k < list2.size(); k++) {
                ServerPlayerEntity player1 = (ServerPlayerEntity) list2.get(k);
                PacketHelper.sendTo(player1, new RopePacket(typeName, entityID, roperID));
            }
        }
    }

    //TYPE
    public void setTypeSpawn() {
        if (!world.isRemote) {
            int i = 0;
            //
            if(MoCTools.NearMaterialWithDistance(this, Double.valueOf(1.0D), Material.SNOW_LAYER))
            {
                i = 6;
            } else
            {
                i = checkNearBigKitties(12D);
                if(i == 7)
                {
                    i = 5;
                }
            }
            if(i == 0){
                i = getRandomRace();
            }
            //
            if (random.nextInt(4) == 0) {
                setAdult(false);
                killedByOtherEntity = true;
            }else{
                setAdult(true);
                setAge(1.0F);
            }
            setType(i);
            setProtect(true); ///Broni Domyślnie
            this.health = this.maxhealth;
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

    //TAMED
    public boolean getTamed()
    {
        return (dataTracker.getByte(20) & 1) != 0;
    }

    public void setTamed(boolean flag)
    {
        if(flag)
        {
            dataTracker.set(20, Byte.valueOf((byte)1));
        } else
        {
            dataTracker.set(20, Byte.valueOf((byte)0));
        }
    }

    //SITTING
    public boolean getSitting()
    {
        return (dataTracker.getByte(21) & 1) != 0;
    }

    public void setSitting(boolean flag)
    {
        if(flag)
        {
            dataTracker.set(21, Byte.valueOf((byte)1));
        } else
        {
            dataTracker.set(21, Byte.valueOf((byte)0));
        }
    }

    //HUNGRY
    public boolean getHungry()
    {
        return (dataTracker.getByte(22) & 1) != 0;
    }

    public void setHungry(boolean flag)
    {
        if(flag)
        {
            dataTracker.set(22, Byte.valueOf((byte)1));
        } else
        {
            dataTracker.set(22, Byte.valueOf((byte)0));
        }
    }

    //EATEN
    public boolean getEaten()
    {
        return (dataTracker.getByte(19) & 1) != 0;
    }

    public void setEaten(boolean flag)
    {
        if(flag)
        {
            dataTracker.set(19, Byte.valueOf((byte)1));
        } else
        {
            dataTracker.set(19, Byte.valueOf((byte)0));
        }
    }

    //PROTECT FROM PLAYERES
    public boolean getProtect()
    {
        return (dataTracker.getByte(23) & 1) != 0;
    }

    public void setProtect(boolean flag)
    {
        if(flag)
        {
            dataTracker.set(23, Byte.valueOf((byte)1));
        } else
        {
            dataTracker.set(23, Byte.valueOf((byte)0));
        }
    }

    //DISPLAY NAME
    public boolean getDisplayName()
    {
        return (dataTracker.getByte(24) & 1) != 0;
    }

    public void setDisplayName(boolean flag)
    {
        if(flag)
        {
            dataTracker.set(24, Byte.valueOf((byte)1));
        } else
        {
            dataTracker.set(24, Byte.valueOf((byte)0));
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
}

