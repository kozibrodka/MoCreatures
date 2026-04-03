package net.kozibrodka.mocreatures.entity;

import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.kozibrodka.mocreatures.events.mod_mocreatures;
import net.minecraft.entity.Entity;
import net.minecraft.entity.ai.pathing.Path;
import net.minecraft.entity.passive.WolfEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.DyeItem;
import net.minecraft.item.FoodItem;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.world.World;
import net.modificationstation.stationapi.api.server.entity.MobSpawnDataProvider;
import net.modificationstation.stationapi.api.util.Identifier;


public class EntityCollie_old extends WolfEntity implements MobSpawnDataProvider {

    public EntityCollie_old(World world) {
        super(world);
        texture = "/assets/mocreatures/stationapi/textures/mob/collie_tame.png";
    }

    public void tickMovement() {
        super.tickMovement();
        this.begging = false;
        if (this.hasLookTarget() && !this.hasPath() && !this.isAngry()) {
            Entity var1 = this.getLookTarget();
            if (var1 instanceof PlayerEntity) {
                PlayerEntity var2 = (PlayerEntity)var1;
                ItemStack var3 = var2.inventory.getSelectedItem();
                if (var3 != null) {
                    if (!this.isTamed() && var3.itemId == mod_mocreatures.elephanttusk.id) {
                        this.begging = true;
                    } else if (this.isTamed() && Item.ITEMS[var3.itemId] instanceof FoodItem) {
                        this.begging = ((FoodItem)Item.ITEMS[var3.itemId]).isMeat();
                    }
                }
            }
        }

        if (!this.interpolateOnly && this.furWet && !this.shakingWaterOff && this.onGround) {
            this.shakingWaterOff = true;
            this.shakeProgress = 0.0F;
            this.lastShakeProgress = 0.0F;
            this.world.broadcastEntityEvent(this, (byte)8);
        }

    }


    protected void tickLiving() {
        super.tickLiving();
        if (!this.movementBlocked && random.nextInt(1) == 0 &&  this.isTamed() && this.vehicle == null) { //&& !this.hasPath()
            PlayerEntity player = this.world.getPlayer(this.getOwnerName());
            if (player != null) {
                float var2 = player.getDistance(this);
                Path pathentity1 = world.findPath(this, player, 16.0F);
                setPath(pathentity1);
//                if (var2 > 1.0F) {
//                    getPathOrWalkableBlock(player, var2);
//                }
            } else if (!this.isSubmergedInWater()) {
                this.setSittingCollie(true);
            }
        }

        if (this.isSubmergedInWater()) {
            this.setSittingCollie(false);
        }

        if (!this.world.isRemote) {
            this.dataTracker.set(18, this.health);
        }

    }

    @Environment(EnvType.CLIENT)
    public String getTexture() {
//        if (this.isTamed()) {
            return "/assets/mocreatures/stationapi/textures/mob/collie_tame.png";
//        } else {
//            return "/assets/mocreatures/stationapi/textures/mob/collie.png";
//        }
    }

    public boolean interact(PlayerEntity player) {
        ItemStack itemstack = player.inventory.getSelectedItem();
        if (!this.isTamed()) {
            if (itemstack != null && itemstack.itemId == mod_mocreatures.elephanttusk.id && !this.isAngry()) {
                --itemstack.count;
                if (itemstack.count <= 0) {
                    player.inventory.setStack(player.inventory.selectedSlot, (ItemStack)null);
                }

                if (!this.world.isRemote) {
                        this.setTamed(true);
                        this.setPath((Path)null);
                        this.setSittingCollie(true);
                        this.health = 20;
                        this.setOwnerName(player.name);
                        this.showFeedParticles(true);
                        this.world.broadcastEntityEvent(this, (byte)7);
                }

                return true;
            }
        } else {
            if (itemstack != null && Item.ITEMS[itemstack.itemId] instanceof FoodItem) {
                FoodItem var3 = (FoodItem)Item.ITEMS[itemstack.itemId];
                if (var3.isMeat() && this.dataTracker.getInt(18) < 20) {
                    --itemstack.count;
                    if (itemstack.count <= 0) {
                        player.inventory.setStack(player.inventory.selectedSlot, (ItemStack)null);
                    }

                    this.heal(((FoodItem)Item.RAW_PORKCHOP).getHealthRestored());
                    return true;
                }
            }

            if (itemstack != null && Item.ITEMS[itemstack.itemId] instanceof DyeItem) {
//                DyeItem var3 = (DyeItem)Item.ITEMS[itemstack.getDamage()];
                int color = itemstack.getDamage();
                --itemstack.count;
                if (itemstack.count <= 0) {
                    player.inventory.setStack(player.inventory.selectedSlot, (ItemStack)null);
                }
                /// var3 - kolor.
                    return true;
            }



            if (player.name.equalsIgnoreCase(this.getOwnerName())) {
                if (!this.world.isRemote) {
                    this.setSittingCollie(!this.isInSittingPose());
                    this.jumping = false;
                    this.setPath((Path)null);
                }

                return true;
            }
        }

        return false;
    }

//    public boolean damage(Entity damageSource, int amount) {
//        this.setSitting(false);
//        if (damageSource != null && !(damageSource instanceof PlayerEntity) && !(damageSource instanceof ArrowEntity)) {
//            amount = (amount + 1) / 2;
//        }
//
//        if (!damageLiving(damageSource, amount)) {
//            return false;
//        } else {
//            if (!this.isTamed() && !this.isAngry()) {
//                if (damageSource instanceof PlayerEntity) {
//                    this.setAngry(true);
//                    this.target = damageSource;
//                }
//
//                if (damageSource instanceof ArrowEntity && ((ArrowEntity)damageSource).owner != null) {
//                    damageSource = ((ArrowEntity)damageSource).owner;
//                }
//
//                if (damageSource instanceof LivingEntity) {
//                    this.target = damageSource;
////                    for(Object var5 : this.world.collectEntitiesByClass(WolfEntity.class, Box.createCached(this.x, this.y, this.z, this.x + (double)1.0F, this.y + (double)1.0F, this.z + (double)1.0F).expand((double)16.0F, (double)4.0F, (double)16.0F))) {
////                        WolfEntity var6 = (WolfEntity)var5;
////                        if (!var6.isTamed() && var6.target == null) {
////                            var6.target = damageSource;
////                            if (damageSource instanceof PlayerEntity) {
////                                var6.setAngry(true);
////                            }
////                        }
////                    }
//                }
//            } else if (damageSource != this && damageSource != null) {
//                if (this.isTamed() && damageSource instanceof PlayerEntity && ((PlayerEntity)damageSource).name.equalsIgnoreCase(this.getOwnerName())) {
//                    return true;
//                }
//
//                this.target = damageSource;
//            }
//
//            return true;
//        }
//    }
//
//    public boolean damageLiving(Entity damageSource, int amount) {
//        if (this.world.isRemote) {
//            return false;
//        } else {
//            this.despawnCounter = 0;
//            if (this.health <= 0) {
//                return false;
//            } else {
//                this.walkAnimationSpeed = 1.5F;
//                boolean var3 = true;
//                if ((float)this.hearts > (float)this.maxHealth / 2.0F) {
//                    if (amount <= this.prevHealth) {
//                        return false;
//                    }
//
//                    this.applyDamage(amount - this.prevHealth);
//                    this.prevHealth = amount;
//                    var3 = false;
//                } else {
//                    this.prevHealth = amount;
//                    this.lastHealth = this.health;
//                    this.hearts = this.maxHealth;
//                    this.applyDamage(amount);
//                    this.hurtTime = this.damagedTime = 10;
//                }
//
//                this.damagedSwingDir = 0.0F;
//                if (var3) {
//                    this.world.broadcastEntityEvent(this, (byte)2);
//                    this.scheduleVelocityUpdate();
//                    if (damageSource != null) {
//                        double var4 = damageSource.x - this.x;
//
//                        double var6;
//                        for(var6 = damageSource.z - this.z; var4 * var4 + var6 * var6 < 1.0E-4; var6 = (Math.random() - Math.random()) * 0.01) {
//                            var4 = (Math.random() - Math.random()) * 0.01;
//                        }
//
//                        this.damagedSwingDir = (float)(Math.atan2(var6, var4) * (double)180.0F / (double)(float)Math.PI) - this.yaw;
//                        this.applyKnockback(damageSource, amount, var4, var6);
//                    } else {
//                        this.damagedSwingDir = (float)((int)(Math.random() * (double)2.0F) * 180);
//                    }
//                }
//
//                if (this.health <= 0) {
//                    if (var3) {
//                        this.world.playSound(this, this.getDeathSound(), this.getSoundVolume(), (this.random.nextFloat() - this.random.nextFloat()) * 0.2F + 1.0F);
//                    }
//
//                    this.onKilledBy(damageSource);
//                } else if (var3) {
//                    this.world.playSound(this, this.getHurtSound(), this.getSoundVolume(), (this.random.nextFloat() - this.random.nextFloat()) * 0.2F + 1.0F);
//                }
//
//                return true;
//            }
//        }
//    }

    public int getLimitPerChunk() {
        return 1;
    }

    public boolean canSpawn()
    {
        return mod_mocreatures.mocGlass.animals.colliefreq > 0 && super.canSpawn();
    }

    @Override
    public Identifier getHandlerIdentifier() {
        return Identifier.of(mod_mocreatures.MOD_ID, "Collie");
    }

    public boolean isAngry() {
        return false;
    }

    public void setAngry(boolean angry) {
    }

    public void setTarget(Entity target) {
//        if(target instanceof SheepEntity){
//            return;
//        }
//        this.target = target;
    }

    public void setSitting(boolean inSittingPose) {
        return;
    }

    public void setSittingCollie(boolean inSittingPose) {
        byte var2 = this.dataTracker.getByte(16);
        if (inSittingPose) {
            this.dataTracker.set(16, (byte)(var2 | 1));
        } else {
            this.dataTracker.set(16, (byte)(var2 & -2));
        }

    }
}
