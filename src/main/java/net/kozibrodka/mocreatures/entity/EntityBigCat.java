package net.kozibrodka.mocreatures.entity;


import net.fabricmc.loader.api.FabricLoader;
import net.kozibrodka.mocreatures.events.GUIListener;
import net.kozibrodka.mocreatures.events.mod_mocreatures;
import net.kozibrodka.mocreatures.mixin.DataTrackerAccessor;
import net.kozibrodka.mocreatures.mixin.WalkingBaseAccesor;
import net.kozibrodka.mocreatures.mocreatures.MoCGUI;
import net.kozibrodka.mocreatures.mocreatures.MoCreatureRacial;
import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.client.Minecraft;
import net.minecraft.entity.Entity;
import net.minecraft.entity.ItemEntity;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.MobEntity;
import net.minecraft.entity.ai.pathing.Path;
import net.minecraft.entity.mob.MonsterEntity;
import net.minecraft.entity.passive.AnimalEntity;
import net.minecraft.entity.passive.WolfEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NbtCompound;
import net.minecraft.util.math.Box;
import net.minecraft.util.math.MathHelper;
import net.minecraft.world.World;
import net.modificationstation.stationapi.api.gui.screen.container.GuiHelper;
import net.modificationstation.stationapi.api.util.Identifier;
import net.modificationstation.stationapi.api.server.entity.MobSpawnDataProvider;

import java.util.List;


public class EntityBigCat extends AnimalEntity implements MobSpawnDataProvider, MoCreatureRacial
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
            health = maxhealth;
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

    public void tickMovement()
    {
        super.tickMovement();
        if(!typechosen && world.isRemote && getType() != 0){
            typechosen = true;
            chooseType(getType());
        }
        if(!world.isRemote) {
            if (!getAdult() && random.nextInt(250) == 0 && !world.isRemote) {
                setAge(getAge() + 0.01F);
                if (getAge() >= 1.0F) {
                    setAdult(true);
                    killedByOtherEntity = false;
                }
            }
            if (!getHungry() && !getSitting() && random.nextInt(200) == 0) {
                setHungry(true);
            }
            if (roper != null && random.nextInt(20) == 0) {
                float f = roper.getDistance(this);
                if (f > 8F && !getSitting()) {
                    method_429(roper, f);
                }
                if ((f > 18F) & getSitting()) {
                    roper = null;
                }
            }
            if (deathTime == 0 && getHungry() && !getSitting()) {
                ItemEntity entityitem = getClosestItem(this, 12D, Item.RAW_PORKCHOP.id, Item.RAW_FISH.id);
                if (entityitem != null) {
                    MoveToNextEntity(entityitem);
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
                        setHungry(true);
                    }
                }
            }
        }
    }

    protected boolean isMovementBlocked()
    {
        return getSitting();
    }

    private void method_429(Entity entity, float f)
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
    protected void tickLiving(){
        if(this.target instanceof LivingEntity){
            PlayerEntity uciekinier = world.getClosestPlayer(this, 16D);
            if(uciekinier == null && target.isAlive()){
                if(random.nextInt(30) == 0)
                {
                    target = null;
                }
            }
        }
        super.tickLiving();
    }

    protected Entity getTargetInRange()
    {
        if(roper != null)
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
            if(random.nextInt(80) == 0 && getHungry())
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
            if(!(entity1 instanceof LivingEntity) || entity1 == entity || entity1 == entity.passenger || entity1 == entity.vehicle || (entity1 instanceof PlayerEntity) || !getAdult() && ((double)entity1.width > 0.5D || (double)entity1.height > 0.5D) || (entity1 instanceof EntityKittyBed) || (entity1 instanceof EntityLitterBox) || (entity1 instanceof MonsterEntity) && (!getTamed() || !getAdult()) || getTamed() && (entity1 instanceof EntityKitty) && ((EntityKitty)entity1).kittystate > 2 || (entity1 instanceof EntityHorse) && !mocr.mocreaturesGlass.huntercreatures.attackhorses || (entity1 instanceof EntityHorse) && getTamed() && ((EntityHorse) entity1).getTamed() || (entity1 instanceof EntityShark) && ((EntityShark)entity1).tamed && getTamed() || (entity1 instanceof EntityDolphin) && ((EntityDolphin)entity1).getTamed() && getTamed() || (entity1 instanceof WolfEntity) && !mocr.mocreaturesGlass.huntercreatures.attackwolves)
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

    public MobEntity getMastersEnemy(PlayerEntity entityplayer, double d)
    {
        double d1 = -1D;
        MobEntity entitycreature = null;
        List list = world.getEntities(entityplayer, boundingBox.expand(d, 4D, d));
        for(int i = 0; i < list.size(); i++)
        {
            Entity entity = (Entity)list.get(i);
            if(!(entity instanceof MobEntity) || entity == this)
            {
                continue;
            }
            MobEntity entitycreature1 = (MobEntity)entity;
            if(entitycreature1 != null && ((WalkingBaseAccesor)entitycreature1).getTarget() == entityplayer)
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
            if(getTamed() && entitybase instanceof PlayerEntity)
            {
                PlayerEntity gracz = (PlayerEntity)entitybase;
                if(!gracz.name.equals(getOwner()) && getProtect())
                {
                    target = entitybase;
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
        nbttagcompound.putBoolean("Hungry", getProtect());
        nbttagcompound.putBoolean("Eaten", getProtect());
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
        setDisplayName(nbttagcompound.getBoolean("DisplayName"));
        setOwner(nbttagcompound.getString("TigerOwner"));
        setProtect(nbttagcompound.getBoolean("ProtectFromPlayers"));
        setHungry(nbttagcompound.getBoolean("Hungry"));
        setEaten(nbttagcompound.getBoolean("Eaten"));
    }

    public boolean interact(PlayerEntity entityplayer)
    {
        if(!world.isRemote) {
            ItemStack itemstack = entityplayer.inventory.getSelectedItem();
            if (itemstack != null && mocr.mocreaturesGlass.balancesettings.balance_drop && entityplayer.name.equals(getOwner()) && getTamed() && itemstack.itemId == mod_mocreatures.bigcatfood.id) {
                if (--itemstack.count == 0) {
                    entityplayer.inventory.setStack(entityplayer.inventory.selectedSlot, null);
                }
                if (health + 15 > maxhealth) {
                    health = maxhealth;
                } else {
                    health += 15;
                }
                return true;
            }
            if (itemstack != null && getTamed() && entityplayer.name.equals(getOwner()) && (itemstack.itemId == Item.GOLDEN_SWORD.id || itemstack.itemId == Item.STONE_SWORD.id || itemstack.itemId == Item.WOODEN_SWORD.id || itemstack.itemId == Item.IRON_SWORD.id || itemstack.itemId == Item.DIAMOND_SWORD.id)) {
                if (getProtect()) {
                    setProtect(false);
                    for (int var3 = 0; var3 < 7; ++var3) {
                        double var4 = this.random.nextGaussian() * 0.02D;
                        double var6 = this.random.nextGaussian() * 0.02D;
                        double var8 = this.random.nextGaussian() * 0.02D;
                        world.addParticle("heart", this.x + (double) (this.random.nextFloat() * this.width * 2.0F) - (double) this.width, this.y + 0.5D + (double) (this.random.nextFloat() * this.height), this.z + (double) (this.random.nextFloat() * this.width * 2.0F) - (double) this.width, var4, var6, var8);
                    }
                } else {
                    setProtect(true);
                    for (int var3 = 0; var3 < 7; ++var3) {
                        double var4 = this.random.nextGaussian() * 0.02D;
                        double var6 = this.random.nextGaussian() * 0.02D;
                        double var8 = this.random.nextGaussian() * 0.02D;
                        world.addParticle("flame", this.x + (double) (this.random.nextFloat() * this.width * 2.0F) - (double) this.width, this.y + 0.5D + (double) (this.random.nextFloat() * this.height), this.z + (double) (this.random.nextFloat() * this.width * 2.0F) - (double) this.width, var4, var6, var8);
                    }
                }
                return true;
            }
            if (itemstack != null && !getTamed() && getEaten() && itemstack.itemId == mod_mocreatures.medallion.id) {
                if (--itemstack.count == 0) {
                    entityplayer.inventory.setStack(entityplayer.inventory.selectedSlot, null);
                }
                setTamed(true);
                setOwner(entityplayer.name);
                setNameWithGui(this, entityplayer);
                return true;
            }
            if (itemstack != null && entityplayer.name.equals(getOwner()) && getTamed() && itemstack.itemId == mod_mocreatures.whip.id) {
                setSitting(!getSitting());
                return true;
            }
            if (itemstack != null && entityplayer.name.equals(getOwner()) && getTamed() && (itemstack.itemId == Item.DIAMOND_PICKAXE.id || itemstack.itemId == Item.WOODEN_PICKAXE.id || itemstack.itemId == Item.STONE_PICKAXE.id || itemstack.itemId == Item.IRON_PICKAXE.id || itemstack.itemId == Item.GOLDEN_PICKAXE.id)) {
                setDisplayName(!getDisplayName());
                return true;
            }
            if (itemstack != null && entityplayer.name.equals(getOwner()) && getTamed() && (itemstack.itemId == mod_mocreatures.medallion.id || itemstack.itemId == Item.BOOK.id)) {
                setNameWithGui(this, entityplayer);
                return true;
            }
            if (itemstack != null && entityplayer.name.equals(getOwner()) && passenger == null && roper == null && getTamed() && itemstack.itemId == mod_mocreatures.rope.id) {
                if (--itemstack.count == 0) {
                    entityplayer.inventory.setStack(entityplayer.inventory.selectedSlot, null);
                }
                world.playSound(this, "mocreatures:roping", 1.0F, 1.0F + (random.nextFloat() - random.nextFloat()) * 0.2F);
                roper = entityplayer;
                return true;
            }
            //
            if(itemstack != null && itemstack.itemId == Item.DIAMOND_HOE.id)
            {
                System.out.println("TYPE: " + getType());
                System.out.println("ADULT? " + getAdult());
                System.out.println("AGE: " + getAge());
                return true;
            }
            //
            if (roper != null && getTamed()) {
                entityplayer.inventory.addStack(new ItemStack(mod_mocreatures.rope));
                world.playSound(this, "mocreatures:roping", 1.0F, 1.0F + (random.nextFloat() - random.nextFloat()) * 0.2F);
                roper = null;
                return true;
            } else {
                return false;
            }
        }
        return false;
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

    public boolean NearSnowWithDistance(Entity entity, Double double1)
    {
        Box axisalignedbb = entity.boundingBox.expand(double1.doubleValue(), double1.doubleValue(), double1.doubleValue());
        int i = MathHelper.floor(axisalignedbb.minX);
        int j = MathHelper.floor(axisalignedbb.maxX + 1.0D);
        int k = MathHelper.floor(axisalignedbb.minY);
        int l = MathHelper.floor(axisalignedbb.maxY + 1.0D);
        int i1 = MathHelper.floor(axisalignedbb.minZ);
        int j1 = MathHelper.floor(axisalignedbb.maxZ + 1.0D);
        for(int k1 = i; k1 < j; k1++)
        {
            for(int l1 = k; l1 < l; l1++)
            {
                for(int i2 = i1; i2 < j1; i2++)
                {
                    int j2 = world.getBlockId(k1, l1, i2);
                    if(j2 != 0 && Block.BLOCKS[j2].material == Material.SNOW_LAYER)
                    {
                        return true;
                    }
                }

            }

        }

        return false;
    }

    public boolean canSpawn()
    {
        return mocr.mocreaturesGlass.huntercreatures.lionfreq > 0 && super.canSpawn();
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
        entitybigcat.setDisplayName(true);
//        mc.setScreen(new MoCGUI(entitybigcat, entitybigcat.getName()));
//        GuiHelper.openGUI(entityPlayer, Identifier.of("mocreatures:openTamePaper"), null, null, entitybigcat.name);
        GUIListener.tempLiving = entitybigcat;
        GUIListener.tempString = entitybigcat.getName();
        GuiHelper.openGUI(entityPlayer, Identifier.of("mocreatures:openTamePaper"), entityPlayer.inventory, null);
    }

    @SuppressWarnings("deprecation")
    public static Minecraft mc = Minecraft.class.cast(FabricLoader.getInstance().getGameInstance());
    mod_mocreatures mocr = new mod_mocreatures();
    protected int force;
    protected double attackRange;
    public boolean typechosen;
    public float heightF;
    public float widthF;
    public float lengthF;
    public int maxhealth;
    public LivingEntity roper;

//    public boolean protectFromPlayers;
//    public String tigerOwner;
//    public boolean lionboolean;
//    public int typeint;
//    public boolean adult;
//    public float edad;
//    public boolean tamed;
//    public boolean sitting;
//    public String name;
//    public boolean displayname;
//    public boolean eaten;
//    public boolean hungry;



    @Override
    public Identifier getHandlerIdentifier() {
        return Identifier.of(mod_mocreatures.MOD_ID, "BigCat");
    }

    //TYPE
    public void setTypeSpawn() {
        if (!world.isRemote) {
            int i = 0;
            //
            if(NearSnowWithDistance(this, Double.valueOf(1.0D)))
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
            }
            setType(i);
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

