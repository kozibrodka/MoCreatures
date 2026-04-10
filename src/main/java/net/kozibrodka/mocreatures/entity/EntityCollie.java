package net.kozibrodka.mocreatures.entity;

import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.kozibrodka.mocreatures.events.mod_mocreatures;
import net.kozibrodka.mocreatures.mocreatures.MoCreatureNamed;
import net.minecraft.block.WoolBlock;
import net.minecraft.entity.Entity;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.ai.pathing.Path;
import net.minecraft.entity.passive.AnimalEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.projectile.ArrowEntity;
import net.minecraft.item.DyeItem;
import net.minecraft.item.FoodItem;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NbtCompound;
import net.minecraft.util.math.Box;
import net.minecraft.util.math.MathHelper;
import net.minecraft.world.World;
import net.modificationstation.stationapi.api.server.entity.MobSpawnDataProvider;
import net.modificationstation.stationapi.api.util.Identifier;

public class EntityCollie extends AnimalEntity implements MobSpawnDataProvider, MoCreatureNamed {

    public boolean begging = false;
    private float begAnimationProgress;
    private float lastBegAnimationProcess;
    public boolean furWet;
    public boolean shakingWaterOff;
    public float shakeProgress;
    public float lastShakeProgress;

    public EntityCollie(World world) {
        super(world);
        texture = "/assets/mocreatures/stationapi/textures/mob/febi.png";
        setBoundingBoxSpacing(0.8F, 0.8F);
        movementSpeed = 1.1F;
        health = 8;
    }

    @Override
    protected void initDataTracker() {
        super.initDataTracker();
        dataTracker.startTracking(16, (byte)0);
        dataTracker.startTracking(17, "");
        dataTracker.startTracking(18, (byte) (health));
        dataTracker.startTracking(19, (byte)0); //COLOR
    }

    @Override
    protected boolean bypassesSteppingEffects() {
        return false;
    }

    // TODO: Czy nie powinienm poprostu uzyc getTexture() w mobach??? wszystkich
    @Override
    @Environment(EnvType.CLIENT)
    public String getTexture() {
        if (isTamed()) {
            return "/assets/mocreatures/stationapi/textures/mob/collie_tamed.png";
        } else {
            return "/assets/mocreatures/stationapi/textures/mob/collie.png";
        }
    }

    @Override
    public void writeNbt(NbtCompound nbt) {
        super.writeNbt(nbt);
        nbt.putBoolean("Angry", isAngry());
        nbt.putBoolean("Sitting", isInSittingPose());
        if (getOwnerName() == null) {
            nbt.putString("Owner", "");
        } else {
            nbt.putString("Owner", getOwnerName());
        }
        nbt.putInt("Color", getColor());
    }

    @Override
    public void readNbt(NbtCompound nbt) {
        super.readNbt(nbt);
        setAngry(nbt.getBoolean("Angry"));
        setSitting(nbt.getBoolean("Sitting"));
        String var2 = nbt.getString("Owner");
        if (var2.length() > 0) {
            setOwnerName(var2);
            setTamed(true);
        }
        setColor(nbt.getInt("Color"));
    }

    @Override
    protected boolean canDespawn() {
        return !isTamed();
    }

    @Override
    protected String getRandomSound() {
        if (isAngry()) {
            return "mob.wolf.growl";
        } else if (random.nextInt(3) == 0) {
            return isTamed() && dataTracker.getByte(18) < 10 ? "mob.wolf.whine" : "mob.wolf.panting";
        } else {
            return "mob.wolf.bark";
        }
    }

    @Override
    protected String getHurtSound() {
        return "mob.wolf.hurt";
    }

    @Override
    protected String getDeathSound() {
        return "mob.wolf.death";
    }

    @Override
    protected float getSoundVolume() {
        return 0.4F;
    }

    @Override
    protected int getDroppedItemId() {
        return -1;
    }

    @Override
    protected void tickLiving() {
        super.tickLiving();
        if (!movementBlocked &&  isTamed() && vehicle == null) { //&& !hasPath()
            PlayerEntity player = world.getPlayer(getOwnerName());
            if (player != null) {
                float var2 = player.getDistance(this); ///
                Path pathentity1 = world.findPath(this, player, 16.0F);
                setPath(pathentity1);
            } else if (!isSubmergedInWater()) {
                setSitting(true);
            }
        }

        if (isSubmergedInWater()) {
            setSitting(false);
        }

        if (!world.isRemote) {
            dataTracker.set(18, (byte)health);
        }

    }

    @Override
    public void tickMovement() {
        super.tickMovement();
        begging = false;
        if (hasLookTarget() && !hasPath() && !isAngry()) {
            Entity var1 = getLookTarget();
            if (var1 instanceof PlayerEntity var2) {
                ItemStack var3 = var2.inventory.getSelectedItem();
                if (var3 != null) {
                    if (!isTamed() && var3.itemId == mod_mocreatures.elephanttusk.id) {
                        begging = true;
                    } else if (isTamed() && Item.ITEMS[var3.itemId] instanceof FoodItem) {
                        begging = ((FoodItem)Item.ITEMS[var3.itemId]).isMeat();
                    }
                }
            }
        }

        if (!interpolateOnly && furWet && !shakingWaterOff && !hasPath() && onGround) {
            shakingWaterOff = true;
            shakeProgress = 0.0F;
            lastShakeProgress = 0.0F;
            world.broadcastEntityEvent(this, (byte)8);
        }

    }

    @Override
    public void tick() {
        super.tick();
        lastBegAnimationProcess = begAnimationProgress;
        if (begging) {
            begAnimationProgress += (1.0F - begAnimationProgress) * 0.4F;
        } else {
            begAnimationProgress += (0.0F - begAnimationProgress) * 0.4F;
        }

        if (begging) {
            lookTimer = 10;
        }

        if (isWet()) {
            furWet = true;
            shakingWaterOff = false;
            shakeProgress = 0.0F;
            lastShakeProgress = 0.0F;
        } else if ((furWet || shakingWaterOff) && shakingWaterOff) {
            if (shakeProgress == 0.0F) {
                world.playSound(this, "mob.wolf.shake", getSoundVolume(), (random.nextFloat() - random.nextFloat()) * 0.2F + 1.0F);
            }

            lastShakeProgress = shakeProgress;
            shakeProgress += 0.05F;
            if (lastShakeProgress >= 2.0F) {
                furWet = false;
                shakingWaterOff = false;
                lastShakeProgress = 0.0F;
                shakeProgress = 0.0F;
            }

            if (shakeProgress > 0.4F) {
                float var1 = (float)boundingBox.minY;
                int var2 = (int)(MathHelper.sin((shakeProgress - 0.4F) * (float)Math.PI) * 7.0F);

                for(int var3 = 0; var3 < var2; ++var3) {
                    float var4 = (random.nextFloat() * 2.0F - 1.0F) * width * 0.5F;
                    float var5 = (random.nextFloat() * 2.0F - 1.0F) * width * 0.5F;
                    world.addParticle("splash", x + (double)var4, var1 + 0.8F, z + (double)var5, velocityX, velocityY, velocityZ);
                }
            }
        }

    }

    @Environment(EnvType.CLIENT)
    public boolean isFurWet() {
        return furWet;
    }

    @Environment(EnvType.CLIENT)
    public float getFurBrightnessMultiplier(float tickDelta) {
        return 0.75F + (lastShakeProgress + (shakeProgress - lastShakeProgress) * tickDelta) / 2.0F * 0.25F;
    }

    @Environment(EnvType.CLIENT)
    public float getShakeAnimationProgress(float tickDelta, float offset) {
        float var3 = (lastShakeProgress + (shakeProgress - lastShakeProgress) * tickDelta + offset) / 1.8F;
        if (var3 < 0.0F) {
            var3 = 0.0F;
        } else if (var3 > 1.0F) {
            var3 = 1.0F;
        }

        return MathHelper.sin(var3 * (float)Math.PI) * MathHelper.sin(var3 * (float)Math.PI * 11.0F) * 0.15F * (float)Math.PI;
    }

    @Environment(EnvType.CLIENT)
    public float getBegAnimationProgress(float tickDelta) {
        return (lastBegAnimationProcess + (begAnimationProgress - lastBegAnimationProcess) * tickDelta) * 0.15F * (float)Math.PI;
    }

    @Override
    public float getEyeHeight() {
        return height * 0.8F;
    }

    @Override
    protected int getMaxLookPitchChange() {
        return isInSittingPose() ? 20 : super.getMaxLookPitchChange();
    }

    @Override
    protected boolean isMovementBlocked() {
        return isInSittingPose() || shakingWaterOff;
    }

    @Override
    public boolean damage(Entity damageSource, int amount) {
        setSitting(false);
        if (damageSource != null && !(damageSource instanceof PlayerEntity) && !(damageSource instanceof ArrowEntity)) {
            amount = (amount + 1) / 2;
        }

        if (!super.damage(damageSource, amount)) {
            return false;
        } else {
            if (!isTamed() && !isAngry()) {
                if (damageSource instanceof PlayerEntity) {
                    setAngry(true);
                    target = damageSource;
                }

                if (damageSource instanceof ArrowEntity && ((ArrowEntity)damageSource).owner != null) {
                    damageSource = ((ArrowEntity)damageSource).owner;
                }

                if (damageSource instanceof LivingEntity) {
                    for(Object var5 : world.collectEntitiesByClass(EntityCollie.class, Box.createCached(x, y, z, x + (double)1.0F, y + (double)1.0F, z + (double)1.0F).expand(16.0F, 4.0F, 16.0F))) {
                        EntityCollie var6 = (EntityCollie)var5;
                        if (!var6.isTamed() && var6.target == null) {
                            var6.target = damageSource;
                            if (damageSource instanceof PlayerEntity) {
                                var6.setAngry(true);
                            }
                        }
                    }
                }
            } else if (damageSource != this && damageSource != null) {
                if (isTamed() && damageSource instanceof PlayerEntity && ((PlayerEntity)damageSource).name.equalsIgnoreCase(getOwnerName())) {
                    return true;
                }

                target = damageSource;
            }

            return true;
        }
    }

    @Override
    protected Entity getTargetInRange() {
        return isAngry() ? world.getClosestPlayer(this, 16.0F) : null;
    }

    @Override
    protected void attack(Entity other, float distance) {
        if (distance > 2.0F && distance < 6.0F && random.nextInt(10) == 0) {
            if (onGround) {
                double var8 = other.x - x;
                double var5 = other.z - z;
                float var7 = MathHelper.sqrt(var8 * var8 + var5 * var5);
                velocityX = var8 / (double)var7 * (double)0.5F * (double)0.8F + velocityX * (double)0.2F;
                velocityZ = var5 / (double)var7 * (double)0.5F * (double)0.8F + velocityZ * (double)0.2F;
                velocityY = 0.4F;
            }
        } else if ((double)distance < (double)1.5F && other.boundingBox.maxY > boundingBox.minY && other.boundingBox.minY < boundingBox.maxY) {
            attackCooldown = 20;
            byte var3 = 2;
            if (isTamed()) {
                var3 = 4;
            }

            other.damage(this, var3);
        }

    }

    @Override
    public boolean interact(PlayerEntity player) {
        ItemStack itemstack = player.inventory.getSelectedItem();
        if (!isTamed()) {
            if (itemstack != null && itemstack.itemId == mod_mocreatures.elephanttusk.id && !isAngry()) {
                --itemstack.count;
                if (itemstack.count <= 0) {
                    player.inventory.setStack(player.inventory.selectedSlot, null);
                }

                if (!world.isRemote) {
                    setTamed(true);
                    setPath(null);
                    setSitting(true);
                    health = 20;
                    setOwnerName(player.name);
                    showFeedParticles(true);
                    world.broadcastEntityEvent(this, (byte)7);
                }

                return true;
            }
        } else {
            if (itemstack != null && Item.ITEMS[itemstack.itemId] instanceof FoodItem var3) {
                if (var3.isMeat() && dataTracker.getInt(18) < 20) {
                    --itemstack.count;
                    if (itemstack.count <= 0) {
                        player.inventory.setStack(player.inventory.selectedSlot, null);
                    }

                    heal(((FoodItem)Item.RAW_PORKCHOP).getHealthRestored());
                    return true;
                }
            }

            if (itemstack != null && Item.ITEMS[itemstack.itemId] instanceof DyeItem) {
                int color = WoolBlock.getBlockMeta(itemstack.getDamage());
                --itemstack.count;
                if (itemstack.count <= 0) {
                    player.inventory.setStack(player.inventory.selectedSlot, null);
                }
                setColor(color);
                return true;
            }



            if (player.name.equalsIgnoreCase(getOwnerName())) {
                if (!world.isRemote) {
                    setSitting(!isInSittingPose());
                    jumping = false;
                    setPath(null);
                }

                return true;
            }
        }

        return false;
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

    @Override
    @Environment(EnvType.CLIENT)
    public void processServerEntityStatus(byte status) {
        if (status == 7) {
            showFeedParticles(true);
        } else if (status == 6) {
            showFeedParticles(false);
        } else if (status == 8) {
            shakingWaterOff = true;
            shakeProgress = 0.0F;
            lastShakeProgress = 0.0F;
        } else {
            super.processServerEntityStatus(status);
        }

    }

    @Environment(EnvType.CLIENT)
    public float getTailAngle() {
        if (isAngry()) {
            return 1.5393804F;
        } else {
            return isTamed() ? (0.55F - (float)(20 - dataTracker.getByte(18)) * 0.02F) * (float)Math.PI : ((float)Math.PI / 5F);
        }
    }

    @Override
    public int getLimitPerChunk() {
        return 1;
    }

    @Override
    public boolean canSpawn()
    {
        return world.hasSkyLight(MathHelper.floor(x), MathHelper.floor(y), MathHelper.floor(z)) && y > 100D && mod_mocreatures.mocGlass.animals.colliefreq > 0 && super.canSpawn();
    }

    @Override
    public Identifier getHandlerIdentifier() {
        return Identifier.of(mod_mocreatures.MOD_ID, "Collie");
    }

    public String getOwnerName() {
        return dataTracker.getString(17);
    }

    public void setOwnerName(String owner) {
        dataTracker.set(17, owner);
    }

    public boolean isInSittingPose() {
        return (dataTracker.getByte(16) & 1) != 0;
    }

    public void setSitting(boolean inSittingPose) {
        byte var2 = dataTracker.getByte(16);
        if (inSittingPose) {
            dataTracker.set(16, (byte)(var2 | 1));
        } else {
            dataTracker.set(16, (byte)(var2 & -2));
        }

    }

    public boolean isAngry() {
        return (dataTracker.getByte(16) & 2) != 0;
    }

    public void setAngry(boolean angry) {
        byte var2 = dataTracker.getByte(16);
        if (angry) {
            dataTracker.set(16, (byte)(var2 | 2));
        } else {
            dataTracker.set(16, (byte)(var2 & -3));
        }

    }

    public boolean isTamed() {
        return (dataTracker.getByte(16) & 4) != 0;
    }

    public void setTamed(boolean tamed) {
        byte var2 = dataTracker.getByte(16);
        if (tamed) {
            dataTracker.set(16, (byte)(var2 | 4));
        } else {
            dataTracker.set(16, (byte)(var2 & -5));
        }

    }

    public void setColor(int type)
    {
            dataTracker.set(19, (byte) type);

    }

    public int getColor()
    {
        return dataTracker.getByte(19);
    }

    @Override
    public void setName(String name) {}

    @Override
    public String getName() {return "";}

    @Override
    public boolean getTamed() {
        return (dataTracker.getByte(16) & 4) != 0;
    }

    @Override
    public String getOwner() {
        return dataTracker.getString(17);
    }
}
