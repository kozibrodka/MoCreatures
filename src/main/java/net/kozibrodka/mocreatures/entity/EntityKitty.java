
package net.kozibrodka.mocreatures.entity;

import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.kozibrodka.mocreatures.events.mod_mocreatures;
import net.kozibrodka.mocreatures.mocreatures.*;
import net.kozibrodka.mocreatures.network.NamePacket;
import net.kozibrodka.mocreatures.network.RopePacket;
import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.entity.Entity;
import net.minecraft.entity.ItemEntity;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.ai.pathing.Path;
import net.minecraft.entity.passive.AnimalEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.item.PickaxeItem;
import net.minecraft.nbt.NbtCompound;
import net.minecraft.sound.BlockSoundGroup;
import net.minecraft.util.math.Box;
import net.minecraft.util.math.MathHelper;
import net.minecraft.world.World;
import net.modificationstation.stationapi.api.network.packet.PacketHelper;
import net.modificationstation.stationapi.api.util.Identifier;
import net.modificationstation.stationapi.api.server.entity.MobSpawnDataProvider;

import java.util.List;
import java.util.Objects;

public class EntityKitty extends AnimalEntity implements MobSpawnDataProvider,  MoCreatureNamed
{

    public EntityKitty(World world)
    {
        super(world);
        setBoundingBoxSpacing(0.7F, 0.5F);
        killedByOtherEntity = true;
        inBed = false;
        sleepy = false;
        hungry = false;
        kittytimer = 0;
        health = 15;
        madtimer = random.nextInt(5);
        maxhealth = 15;
        foundTree = false;
        if(!world.isRemote){
            setTypeSpawn();
        }
    }

    @Override
    public boolean shouldRender(double distance) {
        if(getPicked()){
            return distance < mod_mocreatures.fullRenderDist;
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

    public boolean renderName()
    {
        return getDisplayName() && getKittyState() != 14 && getKittyState() != 15 && getKittyState() > 1;
    }

    public boolean upsideDown()
    {
        return getKittyState() == 14;
    }

    public boolean onMaBack()
    {
        return getKittyState() == 15;
    }

    public boolean climbingTree()
    {
        return getKittyState() == 16 && isOnLadder() && !onGround;
    }

    @Override
    protected void tickLiving() {
        super.tickLiving();
        this.dataTracker.set(29, (byte) health);
    }

    @Override
    public double getStandingEyeHeight()
    {
        if(vehicle instanceof PlayerEntity)
        {
            if(getKittyState() == 10)
            {
                if (net.fabricmc.loader.FabricLoader.INSTANCE.getEnvironmentType() == EnvType.SERVER){
                    return standingEyeHeight + 0.54F;
                }else{
                    return standingEyeHeight - 1.1F;
                }
            }
            if(upsideDown())
            {
                if (net.fabricmc.loader.FabricLoader.INSTANCE.getEnvironmentType() == EnvType.SERVER){
                    return standingEyeHeight - 0.06F;
                }else{
                    return standingEyeHeight - 1.7F;
                }
            }
            if(onMaBack())
            {
                if (net.fabricmc.loader.FabricLoader.INSTANCE.getEnvironmentType() == EnvType.SERVER){
                    return standingEyeHeight + 0.14F;
                }else{
                    return standingEyeHeight - 1.5F;
                }
            }
        }
        return standingEyeHeight;
    }

    public int getRandomRace(){
        int i = random.nextInt(100);
        if(i <= 15)
        {
            return 1;
        } else
        if(i <= 30)
        {
            return 2;
        } else
        if(i <= 45)
        {
            return 3;
        } else
        if(i <= 60)
        {
            return 4;
        } else
        if(i <= 70)
        {
            return 5;
        } else
        if(i <= 80)
        {
            return 6;
        } else
        if(i <= 90)
        {
            return 7;
        } else
        {
            return 8;
        }
    }

    @Override
    @Environment(EnvType.CLIENT)
    public String getTexture() {
        return switch (getType()) {
            case 1 -> "/assets/mocreatures/stationapi/textures/mob/pussycata.png";
            case 2 -> "/assets/mocreatures/stationapi/textures/mob/pussycatb.png";
            case 3 -> "/assets/mocreatures/stationapi/textures/mob/pussycatc.png";
            case 4 -> "/assets/mocreatures/stationapi/textures/mob/pussycatd.png";
            case 5 -> "/assets/mocreatures/stationapi/textures/mob/pussycate.png";
            case 6 -> "/assets/mocreatures/stationapi/textures/mob/pussycatf.png";
            case 7 -> "/assets/mocreatures/stationapi/textures/mob/pussycatg.png";
            case 8 -> "/assets/mocreatures/stationapi/textures/mob/pussycath.png";
            default -> "";
        };
    }

    @Override
    public boolean interact(PlayerEntity entityplayer)
    {
        ItemStack itemstack = entityplayer.inventory.getSelectedItem();
        if(world.isRemote){
            return false;
        }
        if(getKittyState() == 2 && itemstack != null && itemstack.itemId == mod_mocreatures.medallion.id)
        {
            if(--itemstack.count == 0)
            {
                entityplayer.inventory.setStack(entityplayer.inventory.selectedSlot, null);
            }
            changeKittyState(3);
            health = maxhealth;
            setNameWithGui(this, entityplayer);
            setOwner(entityplayer.name);
            return true;
        }
        if(getKittyState() == 7 && itemstack != null && itemstack.itemId == Item.CAKE.id)
        {
            if(--itemstack.count == 0)
            {
                entityplayer.inventory.setStack(entityplayer.inventory.selectedSlot, null);
            }
            world.playSound(this, "moreatures:kittyeatingf", 1.0F, 1.0F + (random.nextFloat() - random.nextFloat()) * 0.2F);
            world.broadcastEntityEvent(this, (byte)8);
            changeKittyState(9);
            return true;
        }
        if(getKittyState() == 11 && itemstack != null && itemstack.itemId == mod_mocreatures.woolball.id)
        {
            if(--itemstack.count == 0)
            {
                entityplayer.inventory.setStack(entityplayer.inventory.selectedSlot, null);
            }
            setKittyState(8);
            ItemEntity entityitem = new ItemEntity(world, x, y + 1.0D, z, new ItemStack(mod_mocreatures.woolball, 1));
            entityitem.pickupDelay = 30;
            entityitem.itemAge = -10000;
            world.spawnEntity(entityitem);
            entityitem.velocityY += world.random.nextFloat() * 0.05F;
            entityitem.velocityX += (world.random.nextFloat() - world.random.nextFloat()) * 0.3F;
            entityitem.velocityZ += (world.random.nextFloat() - world.random.nextFloat()) * 0.3F;
            target = entityitem;
            return true;
        }
        if(getKittyState() == 13 && itemstack != null && (itemstack.itemId == Item.RAW_FISH.id || itemstack.itemId == Item.COOKED_FISH.id))
        {
            if(--itemstack.count == 0)
            {
                entityplayer.inventory.setStack(entityplayer.inventory.selectedSlot, null);
            }
            world.playSound(this, "mocreatures:kittyeatingf", 1.0F, 1.0F + (random.nextFloat() - random.nextFloat()) * 0.2F);
            world.broadcastEntityEvent(this, (byte)8);
            changeKittyState(7);
            return true;
        }
        if(itemstack != null && getKittyState() > 2 && itemstack.getItem() instanceof PickaxeItem)
        {
            setDisplayName(!getDisplayName());
            return true;
        }
        if(itemstack != null && getKittyState() > 2 && (itemstack.itemId == mod_mocreatures.medallion.id || itemstack.itemId == Item.BOOK.id))
        {
            setNameWithGui(this, entityplayer);
            return true;
        }
        if(itemstack != null && getKittyState() > 2 && whipeable() && itemstack.itemId == mod_mocreatures.whip.id)
        {
            setSitting(!getSitting());
            return true;
        }
        /// Powinno byc dobre miejsce ale nie ma pewnosci
        if(entityplayer.passenger != null && entityplayer.passenger != this){
            return false;
        }
        if(vehicle instanceof PlayerEntity && !Objects.equals(((PlayerEntity) vehicle).name, entityplayer.name)){
            return false;
        }
        /// Wydaje sie dzialac
        if(itemstack != null && getKittyState() > 2 && pickable() && itemstack.itemId == mod_mocreatures.rope.id)
        {
            changeKittyState(14);
            setVehicle(entityplayer);
            sendRoper(entityplayer, entityplayer.name);
            setPicked(true);
            return true;
        }
        if(itemstack == null && getKittyState() == 10 && vehicle != null)
        {
            vehicle = null;
            sendRoper(entityplayer, "");
            setPicked(false);
            return true;
        }
        if(itemstack == null && getKittyState() > 2 && pickable())
        {
            changeKittyState(15);
            setVehicle(entityplayer);
            sendRoper(entityplayer, entityplayer.name);
            setPicked(true);
            return true;
        }
        if(itemstack == null && getKittyState() == 15)
        {
            changeKittyState(7);
            sendRoper(entityplayer, "");
            setPicked(false);
            return true;
        } else
        {
            return false;
        }
    }

    private boolean pickable()
    {
        return getKittyState() != 13 && getKittyState() != 14 && getKittyState() != 15 && getKittyState() != 19 && getKittyState() != 20 && getKittyState() != 21;
    }

    public boolean whipeable()
    {
        return getKittyState() != 13;
    }

    @Override
    public void tickMovement()
    {
        if(world.isRemote){
            super.tickMovement();
            if(getPicked()){
                lastWalkAnimationSpeed = 0.0F;
                walkAnimationSpeed = 0.0F;
                walkAnimationProgress = 0.0F;
            }
            if(!getSwinging() && swingAnimationProgress > 0.0F){ /// Added logic for short bugged swing.
                swingAnimationProgress = 0;
            }
            return;
        }
        if(!getAdult() && getKittyState() != 10)
        {
            setKittyState(10);
        }
        if(getPicked() && this.vehicle == null)
        {
            setPicked(false);
        }
        if(getKittyState() != 12)
        {
            super.tickMovement();
        }
        if(random.nextInt(200) == 0)
        {
            setDisplayEmoticon(!getDisplayEmoticon());
        }
        if(!getAdult() && random.nextInt(200) == 0)
        {
            setAge(getAge() + 0.005F);
            if(getAge() >= 1.0F)
            {
                setAdult(true);
                killedByOtherEntity = false;
            }
        }
        if(!hungry && !getSitting() && random.nextInt(100) == 0)
        {
            hungry = true;
        }
        setClimbing(climbingTree());
label0:
        switch(getKittyState())
        {
        case -1:
        case 23: // '\027'
            break;

        case 1: // '\001'
            if(random.nextInt(10) == 0)
            {
                LivingEntity entityliving = getBoogey(6D, true);
                if(entityliving != null)
                {
                    runLikeHell(entityliving);
                }
                break;
            }
            if(!hungry || random.nextInt(10) != 0)
            {
                break;
            }
            ItemEntity entityitem = getClosestItem(this, 10D, Item.COOKED_FISH.id, Item.COOKED_FISH.id);
            if(entityitem == null)
            {
                break;
            }
            float f = entityitem.getDistance(this);
            if(f > 2.0F)
            {
                getMyOwnPath(entityitem, f);
            }
            if(f < 2.0F && entityitem != null && deathTime == 0)
            {
                entityitem.markDead();
                world.playSound(this, "mocreatures:kittyeatingf", 1.0F, 1.0F + (random.nextFloat() - random.nextFloat()) * 0.2F);
                world.broadcastEntityEvent(this, (byte)8);
                hungry = false;
                setKittyState(2);
            }
            break;

        case 2: // '\002'
            LivingEntity entityliving1 = getBoogey(6D, false);
            if(entityliving1 != null)
            {
                runLikeHell(entityliving1);
            }
            break;

        case 3: // '\003'
            kittytimer++;
            if(kittytimer > 500)
            {
                if(random.nextInt(200) == 0)
                {
                    changeKittyState(13);
                    break;
                }
                if(random.nextInt(500) == 0)
                {
                    changeKittyState(7);
                    break;
                }
            }
            if(random.nextInt(20) != 0)
            {
                break;
            }
            EntityKittyBed entitykittybed = (EntityKittyBed)getKittyStuff(this, 18D, false);
            if(entitykittybed == null || entitykittybed.passenger != null || !entitykittybed.getHasMilk() && !entitykittybed.getHasFood())
            {
                break;
            }
            float f5 = entitykittybed.getDistance(this);
            if(f5 > 2.0F)
            {
                getMyOwnPath(entitykittybed, f5);
            }
            if(f5 < 2.0F)
            {
                changeKittyState(4);
                setVehicle(entitykittybed);
                setSitting(true);
            }
            break;

        case 4: // '\004'
            if(vehicle != null)
            {
                EntityKittyBed entitykittybed1 = (EntityKittyBed)vehicle;
                if(entitykittybed1 != null && !entitykittybed1.getHasMilk() && !entitykittybed1.getHasFood())
                {
                    health = maxhealth;
                    changeKittyState(5);
                }
            } else
            {
                health = maxhealth;
                changeKittyState(5);
            }
            if(random.nextInt(2500) == 0)
            {
                health = maxhealth;
                changeKittyState(7);
            }
            break;

        case 5: // '\005'
            kittytimer++;
            if(kittytimer > 2000 && random.nextInt(1000) == 0)
            {
                changeKittyState(13);
                break;
            }
            if(random.nextInt(20) != 0)
            {
                break;
            }
            EntityLitterBox entitylitterbox = (EntityLitterBox)getKittyStuff(this, 18D, true);
            if(entitylitterbox == null || entitylitterbox.passenger != null || entitylitterbox.getUsedLitter())
            {
                break;
            }
            float f6 = entitylitterbox.getDistance(this);
            if(f6 > 2.0F)
            {
                getMyOwnPath(entitylitterbox, f6);
            }
            if(f6 < 2.0F)
            {
                changeKittyState(6);
                setVehicle(entitylitterbox);
            }
            break;

        case 6: // '\006'
            kittytimer++;
            if(kittytimer <= 300)
            {
                break;
            }
            world.playSound(this, "mocreatures:kittypoo", 1.0F, 1.0F + (random.nextFloat() - random.nextFloat()) * 0.2F);
            world.broadcastEntityEvent(this, (byte)9);
            EntityLitterBox entitylitterbox1 = (EntityLitterBox)vehicle;
            if(entitylitterbox1 != null)
            {
                entitylitterbox1.setUsedLitter(true);
                entitylitterbox1.littertime = 0;
            }
            changeKittyState(7);
            break;

        case 7: // '\007'
            if(getSitting())
            {
                break;
            }
            if(random.nextInt(20) == 0)
            {
                PlayerEntity entityplayer = world.getClosestPlayer(this, 12D);
                if(entityplayer != null)
                {
                    ItemStack itemstack = entityplayer.inventory.getSelectedItem();
                    if(itemstack != null && itemstack.itemId == mod_mocreatures.woolball.id)
                    {
                        changeKittyState(11);
                        break;
                    }
                }
            }
            if(submergedInWater && random.nextInt(500) == 0)
            {
                changeKittyState(13);
                break;
            }
            if(random.nextInt(500) == 0 && !world.canMonsterSpawn())
            {
                changeKittyState(12);
                break;
            }
            if(random.nextInt(2000) == 0)
            {
                changeKittyState(3);
                break;
            }
            if(random.nextInt(4000) == 0)
            {
                changeKittyState(16);
            }
            break;

        case 8: // '\b'
            if(submergedInWater)
            {
                changeKittyState(13);
                break;
            }
            if(target != null && (target instanceof ItemEntity))
            {
                float f1 = getDistance(target);
                if(f1 < 1.5F)
                {
                    swingArm();
                    if(random.nextInt(10) == 0)
                    {
                        kittySmack(this, target);
                    }
                }
            }
            if(target == null || random.nextInt(1000) == 0)
            {
                changeKittyState(7);
            }
            break;

        case 9: // '\t'
            kittytimer++;
            if(random.nextInt(50) == 0)
            {
                List list = world.getEntities(this, boundingBox.expand(16D, 6D, 16D));
                int j = 0;
                do
                {
                    if(j >= list.size())
                    {
                        break;
                    }
                    Entity entity = (Entity)list.get(j);
                    if((entity instanceof EntityKitty) && (entity instanceof EntityKitty) && ((EntityKitty)entity).getKittyState() == 9)
                    {
                        changeKittyState(18);
                        entity = entity;
                        ((EntityKitty)entity).changeKittyState(18);
                        ((EntityKitty)entity).target = this;
                        break;
                    }
                    j++;
                } while(true);
            }
            if(kittytimer > 2000)
            {
                changeKittyState(7);
            }
            break;

        case 10: // '\n'
            if(getAdult())
            {
                changeKittyState(7);
                break;
            }
            if(random.nextInt(50) == 0)
            {
                List list1 = world.getEntities(this, boundingBox.expand(16D, 6D, 16D));
                for(int k = 0; k < list1.size(); k++)
                {
                    Entity entity1 = (Entity)list1.get(k);
                    if(!(entity1 instanceof EntityKitty) || ((EntityKitty)entity1).getKittyState() != 21)
                    {
                        continue;
                    }
                    float f9 = getDistance(entity1);
                    if(f9 > 12F)
                    {
                        target = entity1;
                    }
                }

            }
            if(target == null && random.nextInt(100) == 0)
            {
                int i = random.nextInt(10);
                if(i < 7)
                {
                    target = getClosestItem(this, 10D, -1, -1);
                } else
                {
                    target = world.getClosestPlayer(this, 18D);
                }
            }
            if(target != null && random.nextInt(400) == 0)
            {
                target = null;
            }
            if(target != null && (target instanceof ItemEntity))
            {
                float f2 = getDistance(target);
                if(f2 < 1.5F)
                {
                    swingArm();
                    if(random.nextInt(10) == 0)
                    {
                        kittySmack(this, target);
                    }
                }
            }
            if(target != null && (target instanceof EntityKitty) && random.nextInt(20) == 0)
            {
                float f3 = getDistance(target);
                if(f3 < 2.0F)
                {
                    swingArm();
                    setPath(null);
                }
            }
            if(target == null || !(target instanceof PlayerEntity))
            {
                break;
            }
            float f4 = getDistance(target);
            if(f4 < 2.0F && random.nextInt(20) == 0)
            {
                swingArm();
            }
            break;

        case 11: // '\013'
            PlayerEntity entityplayer1 = world.getClosestPlayer(this, 18D);
            if(entityplayer1 == null || random.nextInt(10) != 0)
            {
                break;
            }
            ItemStack itemstack1 = entityplayer1.inventory.getSelectedItem();
            if(itemstack1 == null || itemstack1 != null && itemstack1.itemId != mod_mocreatures.woolball.id)
            {
                changeKittyState(7);
                break;
            }
            float f8 = entityplayer1.getDistance(this);
            if(f8 > 5F)
            {
                getPathOrWalkableBlock(entityplayer1, f8);
            }
            break;

        case 12: // '\f'
            kittytimer++;
            if(world.canMonsterSpawn() || kittytimer > 500 && random.nextInt(500) == 0)
            {
                changeKittyState(7);
                break;
            }
            setSitting(true);
            if(random.nextInt(80) == 0 || !onGround)
            {
                super.tickMovement();
            }
            break;

        case 13: // '\r'
            hungry = false;
            target = world.getClosestPlayer(this, 18D);
            if(target != null)
            {
                float f7 = getDistance(target);
                if(f7 < 1.5F)
                {
                    swingArm();
                    if(random.nextInt(20) == 0)
                    {
                        madtimer--;
                        target.damage(this, 1);
                        if(madtimer < 1)
                        {
                            changeKittyState(7);
                            madtimer = random.nextInt(5);
                        }
                    }
                }
                if(random.nextInt(1000) == 0)
                {
                    changeKittyState(7);
                }
            } else
            {
                changeKittyState(7);
            }
            break;

        case 14: // '\016'
            if(onGround)
            {
                changeKittyState(13);
                break;
            }
            if(random.nextInt(50) == 0)
            {
                swingArm();
            }
            if(vehicle == null)
            {
                break;
            }
            yaw = vehicle.yaw + 90F;
            PlayerEntity entityplayer2 = (PlayerEntity)vehicle;
            if(entityplayer2 == null)
            {
                break;
            }
            ItemStack itemstack2 = entityplayer2.inventory.getSelectedItem();
            if(itemstack2 != null && itemstack2.itemId != mod_mocreatures.rope.id)
            {
                changeKittyState(13);
                sendRoper(entityplayer2, "");
                setPicked(false);
            }
            break;

        case 15: // '\017'
            if(onGround)
            {
                changeKittyState(7);
            }
            if(vehicle != null)
            {
                yaw = vehicle.yaw + 90F;
            }
            break;

        case 16: // '\020'
            kittytimer++;
            if(kittytimer > 500 && !onTree)
            {
                changeKittyState(7);
            }
            if(!onTree)
            {
                if(!foundTree && random.nextInt(50) == 0)
                {
                    int[] ai = ReturnNearestMaterialCoord(this, Material.WOOD, Double.valueOf(18D));
                    if(ai[1] != -1)
                    {
                        int i1 = 0;
                        do
                        {
                            if(i1 >= 20)
                            {
                                break;
                            }
                            int k1 = world.getBlockId(ai[0], ai[1] + i1, ai[2]);
                            if(k1 != 0 && Block.BLOCKS[k1].material != Material.WOOD && k1 != 0 && Block.BLOCKS[k1].material == Material.LEAVES)
                            {
                                foundTree = true;
                                treeCoord[0] = ai[0];
                                treeCoord[1] = ai[1];
                                treeCoord[2] = ai[2];
                                break;
                            }
                            i1++;
                        } while(true);
                    }
                }
                if(!foundTree || random.nextInt(10) != 0)
                {
                    break;
                }
                Path pathentity = world.findPath(this, treeCoord[0], treeCoord[1], treeCoord[2], 24F);
                if(pathentity != null)
                {
                    setPath(pathentity);
                }
                Double double1 = Double.valueOf(getSquaredDistance(treeCoord[0], treeCoord[1], treeCoord[2]));
                if(double1.doubleValue() < 7D)
                {
                    onTree = true;
                }
                break;
            }
            if(!onTree)
            {
                break;
            }
            int l = treeCoord[0];
            int j1 = treeCoord[1];
            int l1 = treeCoord[2];
            faceTreeTop(l, j1, l1, 30F);
            if(j1 - MathHelper.floor(y) > 2)
            {
                velocityY += 0.029999999999999999D;
            }
            boolean flag = false;
            boolean flag1 = false;
            if(x < (double)l)
            {
                int j2 = l - MathHelper.floor(x);
                velocityX += 0.01D;
            } else
            {
                int k2 = MathHelper.floor(x) - l;
                velocityX -= 0.01D;
            }
            if(z < (double)l1)
            {
                int j3 = l1 - MathHelper.floor(z);
                velocityZ += 0.01D;
            } else
            {
                int k3 = MathHelper.floor(x) - l1;
                velocityZ -= 0.01D;
            }
            if(onGround || !horizontalCollision || !verticalCollision)
            {
                break;
            }
            int i4 = 0;
            do
            {
                if(i4 >= 30)
                {
                    break label0;
                }
                int j4 = world.getBlockId(treeCoord[0], treeCoord[1] + i4, treeCoord[2]);
                if(j4 == 0)
                {
                    setPositionAndAnglesKeepPrevAngles(treeCoord[0], treeCoord[1] + i4, treeCoord[2], yaw, pitch);
                    changeKittyState(17);
                    treeCoord[0] = -1;
                    treeCoord[1] = -1;
                    treeCoord[2] = -1;
                    break label0;
                }
                i4++;
            } while(true);

        case 17: // '\021'
            PlayerEntity entityplayer3 = world.getClosestPlayer(this, 2D);
            if(entityplayer3 != null)
            {
                changeKittyState(7);
            }
            break;

        case 18: // '\022'
            if(target == null || !(target instanceof EntityKitty entitykitty))
            {
                changeKittyState(9);
                break;
            }
            if(entitykitty != null && entitykitty.getKittyState() == 18)
            {
                if(random.nextInt(50) == 0)
                {
                    swingArm();
                }
                float f10 = getDistance(entitykitty);
                if(f10 < 5F)
                {
                    kittytimer++;
                }
                if(kittytimer > 500 && random.nextInt(50) == 0)
                {
                    ((EntityKitty)target).changeKittyState(7);
                    changeKittyState(19);
                }
            } else
            {
                changeKittyState(9);
            }
            break;

        case 19: // '\023'
            if(random.nextInt(20) != 0)
            {
                break;
            }
            EntityKittyBed entitykittybed2 = (EntityKittyBed)getKittyStuff(this, 18D, false);
            if(entitykittybed2 == null || entitykittybed2.passenger != null)
            {
                break;
            }
            float f11 = entitykittybed2.getDistance(this);
            if(f11 > 2.0F)
            {
                getMyOwnPath(entitykittybed2, f11);
            }
            if(f11 < 2.0F)
            {
                changeKittyState(20);
                setVehicle(entitykittybed2);
            }
            break;

        case 20: // '\024'
            if(vehicle == null)
            {
                changeKittyState(19);
                break;
            }
            yaw = 180F;
            kittytimer++;
            if(kittytimer <= 1000)
            {
                break;
            }
            int i2 = random.nextInt(3) + 1;
            for(int l2 = 0; l2 < i2; l2++)
            {
                EntityKitty entitykitty1 = new EntityKitty(world);
                entitykitty1.setPosition(x, y, z);
                world.spawnEntity(entitykitty1);
                world.playSound(this, "mob.chickenplop", 1.0F, (random.nextFloat() - random.nextFloat()) * 0.2F + 1.0F);
                world.broadcastEntityEvent(this, (byte)10);
                entitykitty1.setTypeSpawn();
                entitykitty1.setAdult(false);
                entitykitty1.setOwner(this.getOwner());
                entitykitty1.changeKittyState(10);
                damage(null, 1);
            }

            changeKittyState(21);
            break;

        case 21: // '\025'
            kittytimer++;
            if(kittytimer > 2000)
            {
                List list2 = world.getEntities(this, boundingBox.expand(24D, 8D, 24D));
                int i3 = 0;
                for(int l3 = 0; l3 < list2.size(); l3++)
                {
                    Entity entity2 = (Entity)list2.get(l3);
                    if((entity2 instanceof EntityKitty) && ((EntityKitty)entity2).getKittyState() == 10)
                    {
                        i3++;
                    }
                }

                if(i3 < 1)
                {
                    changeKittyState(7);
                    break;
                }
                kittytimer = 1000;
            }
            if(target != null && (target instanceof PlayerEntity) && random.nextInt(300) == 0)
            {
                target = null;
            }
            break;

        case 0: // '\0'
        case 22: // '\026'
        default:
            changeKittyState(7);
            break;
        }
    }

    private void changeKittyState(int i)
    {
        setKittyState(i);
        setVehicle(null);
        setSitting(false);
        kittytimer = 0;
        onTree = false;
        foundTree = false;
        target = null;
    }

    @Override
    public boolean isOnLadder()
    {
        if(getKittyState() == 16)
        {
            return horizontalCollision && onTree;
        } else
        {
            return super.isOnLadder();
        }
    }

    public void faceTreeTop(int i, int j, int k, float f)
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

    private float b(float f, float f1, float f2)
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

    public int[] ReturnNearestMaterialCoord(Entity entity, Material material, Double double1)
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
                    if(j2 != 0 && Block.BLOCKS[j2].material == material)
                    {
                        return (new int[] {
                            k1, l1, i2
                        });
                    }
                }

            }

        }

        return (new int[] {
            0, -1, 0
        });
    }

    @Override
    protected Entity getTargetInRange()
    {
        if(world.difficulty > 0 && getKittyState() != 8 && getKittyState() != 10 && getKittyState() != 15 && getKittyState() != 18 && getKittyState() != 19 && !isMovementBlocked() && hungry)
        {
            LivingEntity entityliving = getClosestTarget(this, 10D);
            return entityliving;
        } else
        {
            return null;
        }
    }

    public LivingEntity getClosestTarget(Entity entity, double d)
    {
        double d1 = -1D;
        LivingEntity entityliving = null;
        List list = world.getEntities(this, boundingBox.expand(d, d, d));
        for(int i = 0; i < list.size(); i++)
        {
            Entity entity1 = (Entity)list.get(i);
            if(privateToIgnore(this, entity1) || MoCTools.entitiesToIgnore(this, entity1) || MoCTools.entitiesTamedIgnore(this, entity1))
            {
                continue;
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
        return ((victim instanceof EntityKitty) || (victim instanceof EntityBigCat) && !mod_mocreatures.mocGlass.huntercreatures.attackbigcat || (double)victim.width > 0.5D && (double)victim.height > 0.5D);
    } /// Wydaje mi się, ze wykluczając rozmiar 0.5F, kot nie zaatakuje nigdy BigCatsa. Dziwny kod

    public ItemEntity getClosestItem(Entity entity, double d, int i, int j)
    {
        double d1 = -1D;
        ItemEntity entityitem = null;
        List list = world.getEntities(this, boundingBox.expand(d, d, d));
        for(int k = 0; k < list.size(); k++)
        {
            Entity entity1 = (Entity)list.get(k);
            if(!(entity1 instanceof ItemEntity entityitem1))
            {
                continue;
            }
            if(i != -1 && j != -1 && entityitem1.stack.itemId != i && j != 0 && entityitem1.stack.itemId != j)
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

    private void getMyOwnPath(Entity entity, float f)
    {
        Path pathentity = world.findPath(this, entity, 16F);
        if(pathentity != null)
        {
            setPath(pathentity);
        }
    }

    @Override
    public boolean damage(Entity entity, int i)
    {
        if(super.damage(entity, i))
        {
            if(entity != this)
            {
                if(getKittyState() == 10)
                {
                    List list = world.getEntities(this, boundingBox.expand(16D, 6D, 16D));
                    for(int j = 0; j < list.size(); j++)
                    {
                        Entity entity1 = (Entity)list.get(j);
                        if((entity1 instanceof EntityKitty) && ((EntityKitty)entity1).getKittyState() == 21)
                        {
                            ((EntityKitty)entity1).target = entity;
                            return true;
                        }
                    }

                    return true;
                }
                if(entity instanceof PlayerEntity)
                {
                    if(getKittyState() < 2)
                    {
                        entity = entity;
                        setKittyState(-1);
                    }
                    if(getKittyState() == 19 || getKittyState() == 20 || getKittyState() == 21)
                    {
                        entity = entity;
                        setSitting(false);
                        return true;
                    }
                    if(getKittyState() > 1 && getKittyState() != 10 && getKittyState() != 19 && getKittyState() != 20 && getKittyState() != 21)
                    {
                        setKittyState(13);
                        setSitting(false);
                    }
                    return true;
                }
                entity = entity;
            }
            return true;
        } else
        {
            return false;
        }
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

    public LivingEntity getKittyStuff(Entity entity, double d, boolean flag)
    {
        double d1 = -1D;
        Object obj = null;
        List list = world.getEntities(entity, boundingBox.expand(d, d, d));
        for(int i = 0; i < list.size(); i++)
        {
            Entity entity1 = (Entity)list.get(i);
            if(flag)
            {
                if(!(entity1 instanceof EntityLitterBox entitylitterbox))
                {
                    continue;
                }
                if(entitylitterbox.getUsedLitter())
                {
                    continue;
                }
                double d2 = entity1.getSquaredDistance(entity.x, entity.y, entity.z);
                if((d < 0.0D || d2 < d * d) && (d1 == -1D || d2 < d1) && entitylitterbox.canSee(entity))
                {
                    d1 = d2;
                    obj = entitylitterbox;
                }
                continue;
            }
            if(!(entity1 instanceof EntityKittyBed entitykittybed))
            {
                continue;
            }
            double d3 = entity1.getSquaredDistance(entity.x, entity.y, entity.z);
            if((d < 0.0D || d3 < d * d) && (d1 == -1D || d3 < d1) && entitykittybed.canSee(entity))
            {
                d1 = d3;
                obj = entitykittybed;
            }
        }

        return ((LivingEntity) (obj));
    }

    @Override
    public void tick()
    {
        super.tick();
        if(getSwinging())
        {
            swingAnimationProgress += 0.2F;
            if(swingAnimationProgress > 2.0F)
            {
                setSwinging(false);
                swingAnimationProgress = 0.0F;
            }
        }
    }

    @Override
    protected boolean isMovementBlocked()
    {
        return getSitting() || getKittyState() == 6 || getKittyState() == 16 && onTree || getKittyState() == 12 || getKittyState() == 17 || getKittyState() == 14 || getKittyState() == 20 || getKittyState() == 23;
    }

    public void swingArm()
    {
        if(!getSwinging())
        {
            setSwinging(true);
            swingAnimationProgress = 0.0F;
        }
    }

    @Override
    protected void attack(Entity entity, float f)
    {
        if(f > 2.0F && f < 6F && random.nextInt(30) == 0 && onGround)
        {
            double d = entity.x - x;
            double d1 = entity.z - z;
            float f1 = MathHelper.sqrt(d * d + d1 * d1);
            velocityX = (d / (double)f1) * 0.5D * 0.80000000000000004D + velocityX * 0.20000000000000001D;
            velocityZ = (d1 / (double)f1) * 0.5D * 0.80000000000000004D + velocityZ * 0.20000000000000001D;
            velocityY = 0.40000000000000002D;
        }
        if((double)f < 2D && entity.boundingBox.maxY > boundingBox.minY && entity.boundingBox.minY < boundingBox.maxY)
        {
            attackCooldown = 20;
            if(getKittyState() != 18 && getKittyState() != 10)
            {
                swingArm();
            }
            if(getKittyState() == 13 && (entity instanceof PlayerEntity) || getKittyState() == 8 && (entity instanceof ItemEntity) || getKittyState() == 18 && (entity instanceof EntityKitty) || getKittyState() == 10)
            {
                return;
            }
            entity.damage(this, 1);
        }
    }

    public LivingEntity getBoogey(double d, boolean flag)
    {
        double d1 = -1D;
        LivingEntity entityliving = null;
        List list = world.getEntities(this, boundingBox.expand(d, 4D, d));
        for(int i = 0; i < list.size(); i++)
        {
            Entity entity = (Entity)list.get(i);
            if((entity instanceof LivingEntity) && !(entity instanceof EntityDeer) && !(entity instanceof EntityHorse) && ((double)entity.width >= 0.5D || (double)entity.height >= 0.5D) && (flag || !(entity instanceof PlayerEntity)))
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

    @Override
    protected void onLanding(float f)
    {
    }

    @Override
    protected String getRandomSound()
    {
        if(getKittyState() == 4)
        {
            if(vehicle != null)
            {
                EntityKittyBed entitykittybed = (EntityKittyBed)vehicle;
                if(entitykittybed != null && !entitykittybed.getHasMilk())
                {
                    return "mocreatures:kittyeatingm";
                }
                if(entitykittybed != null && !entitykittybed.getHasFood())
                {
                    return "mocreatures:kittyeatingf";
                }
            }
            return null;
        }
        if(getKittyState() == 6)
        {
            return "mocreatures:kittylitter";
        }
        if(getKittyState() == 3)
        {
            return "mocreatures:kittyfood";
        }
        if(getKittyState() == 10)
        {
            return "mocreatures:kittengrunt";
        }
        if(getKittyState() == 13)
        {
            return "mocreatures:kittyupset";
        }
        if(getKittyState() == 17)
        {
            return "mocreatures:kittytrapped";
        }
        if(getKittyState() == 18 || getKittyState() == 12)
        {
            return "mocreatures:kittypurr";
        } else
        {
            return "mocreatures:kittygrunt";
        }
    }

    @Override
    protected String getHurtSound()
    {
        if(getKittyState() == 10)
        {
            return "mocreatures:kittenhurt";
        } else
        {
            return "mocreatures:kittyhurt";
        }
    }

    @Override
    protected String getDeathSound()
    {
        if(getKittyState() == 10)
        {
            return "mocreatures:kittendying";
        } else
        {
            return "mocreatures:kittydying";
        }
    }

    @Override
    protected int getDroppedItemId()
    {
        return 0;
    }

    @Override
    public int getLimitPerChunk()
    {
        return 2;
    }

    @Override
    protected boolean canDespawn()
    {
        return getKittyState() < 3;
    }

    @Override
    public void markDead()
    {
        if(getKittyState() > 2 && health > 0 && !world.isRemote)
        {
        } else
        {
            super.markDead();
        }
    }

    @Override
    public boolean canSpawn()
    {
        return mod_mocreatures.mocGlass.animals.kittyfreq > 0 && super.canSpawn();
    }

    public void kittySmack(Entity entity, Entity entity1)
    {
        double d = entity.x - entity1.x;
        double d1 = entity.z - entity1.z;
        for(d1 = entity.z - entity1.z; d * d + d1 * d1 < 0.0001D; d1 = (Math.random() - Math.random()) * 0.01D)
        {
            d = (Math.random() - Math.random()) * 0.01D;
        }

        float f = MathHelper.sqrt(d * d + d1 * d1);
        float f1 = 0.25F;
        if(getKittyState() == 10)
        {
            f1 = 0.1F;
        }
        entity1.velocityX /= 2D;
        entity1.velocityY /= 2D;
        entity1.velocityZ /= 2D;
        entity1.velocityX -= (d / (double)f) * (double)f1;
        entity1.velocityY += 0.300000005960465D;
        entity1.velocityZ -= (d1 / (double)f) * (double)f1;
        if(entity1.velocityY > 0.300000005960465D)
        {
            entity1.velocityY = 0.300000005960465D;
        }
    }

    public String getEmoticon()
    {
        switch(getKittyState())
        {
        case -1:
            return "/assets/mocreatures/stationapi/textures/mob/emoticon2.png";

        case 3: // '\003'
            return "/assets/mocreatures/stationapi/textures/mob/emoticon3.png";

        case 4: // '\004'
            return "/assets/mocreatures/stationapi/textures/mob/emoticon4.png";

        case 5: // '\005'
            return "/assets/mocreatures/stationapi/textures/mob/emoticon5.png";

        case 7: // '\007'
            return "/assets/mocreatures/stationapi/textures/mob/emoticon7.png";

        case 8: // '\b'
            return "/assets/mocreatures/stationapi/textures/mob/emoticon8.png";

        case 9: // '\t'
            return "/assets/mocreatures/stationapi/textures/mob/emoticon9.png";

        case 10: // '\n'
            return "/assets/mocreatures/stationapi/textures/mob/emoticon10.png";

        case 11: // '\013'
            return "/assets/mocreatures/stationapi/textures/mob/emoticon11.png";

        case 12: // '\f'
            return "/assets/mocreatures/stationapi/textures/mob/emoticon12.png";

        case 13: // '\r'
            return "/assets/mocreatures/stationapi/textures/mob/emoticon13.png";

        case 16: // '\020'
            return "/assets/mocreatures/stationapi/textures/mob/emoticon16.png";

        case 17: // '\021'
            return "/assets/mocreatures/stationapi/textures/mob/emoticon17.png";

        case 18: // '\022'
            return "/assets/mocreatures/stationapi/textures/mob/emoticon9.png";

        case 19: // '\023'
            return "/assets/mocreatures/stationapi/textures/mob/emoticon19.png";

        case 20: // '\024'
            return "/assets/mocreatures/stationapi/textures/mob/emoticon19.png";

        case 21: // '\025'
            return "/assets/mocreatures/stationapi/textures/mob/emoticon10.png";

        case 0: // '\0'
        case 1: // '\001'
        case 2: // '\002'
        case 6: // '\006'
        case 14: // '\016'
        case 15: // '\017'
        default:
            return "/mob/emoticon1.png";
        }
    }

    public static void setNameWithGui(EntityKitty entitykitty, PlayerEntity entityPlayer)
    {
        if (net.fabricmc.loader.FabricLoader.INSTANCE.getEnvironmentType() == EnvType.SERVER) {
            PacketHelper.sendTo(entityPlayer, new NamePacket(entitykitty.getName(), entitykitty.id));
            entitykitty.setDisplayName(true);
        }else{
            MoGuiOpener clientS = new MoGuiOpener();
            clientS.openTameGui(entitykitty, entitykitty.getName());
            entitykitty.setDisplayName(true);
        }
    }

    public boolean inBed;
    public boolean sleepy;
    public boolean hungry;
    private int kittytimer;
    private int madtimer;
    public int maxhealth;
    private boolean foundTree;
    private boolean onTree;
    private final int[] treeCoord = {
        -1, -1, -1
    };
    private int displaycount;


    @Override
    protected void initDataTracker()
    {
        super.initDataTracker();
        dataTracker.startTracking(16, (byte) 0); //Type
        dataTracker.startTracking(17, 0); //Age
        dataTracker.startTracking(18, (byte) 0); //KITTYSTATE
        dataTracker.startTracking(19, (byte) 0); //Adult
        dataTracker.startTracking(20, (byte) 0); //Swinging
        dataTracker.startTracking(21, (byte) 0); //Sitting
        dataTracker.startTracking(22, (byte) 0); //Display EMO
        dataTracker.startTracking(23, (byte) 0); //Display Name
        dataTracker.startTracking(24, (byte) 0); //Climbing
        dataTracker.startTracking(25, (byte) 0); //Picked
        dataTracker.startTracking(27, ""); //Owner
        dataTracker.startTracking(28, ""); //Name
        dataTracker.startTracking(29, (byte) 0); //HEALTH
    }

    @Override
    public void writeNbt(NbtCompound nbttagcompound)
    {
        super.writeNbt(nbttagcompound);
        nbttagcompound.putInt("TypeInt", getType());
        nbttagcompound.putBoolean("Adult", getAdult());
        nbttagcompound.putBoolean("Sitting", getSitting());
        nbttagcompound.putFloat("Edad", getAge());
        nbttagcompound.putInt("KittyState", getKittyState());
        nbttagcompound.putString("Name", getName());
        nbttagcompound.putBoolean("DisplayName", getDisplayName());
        nbttagcompound.putString("KittyOwner", getOwner());
        nbttagcompound.putBoolean("OnTree", onTree);
    }

    @Override
    public void readNbt(NbtCompound nbttagcompound)
    {
        super.readNbt(nbttagcompound);
        setAdult(nbttagcompound.getBoolean("Adult"));
        setType(nbttagcompound.getInt("TypeInt"));
        setAge(nbttagcompound.getFloat("Edad"));
        setSitting(nbttagcompound.getBoolean("Sitting"));
        setKittyState(nbttagcompound.getInt("KittyState"));
        setName(nbttagcompound.getString("Name"));
        setDisplayName(nbttagcompound.getBoolean("DisplayName"));
        setOwner(nbttagcompound.getString("KittyOwner"));
        onTree = nbttagcompound.getBoolean("OnTree");
    }

    @Override
    @Environment(EnvType.CLIENT)
    public void processServerEntityStatus(byte status) {
         if (status == 8) {
            world.playSound(this, "mocreatures:kittyeatingf", 1.0F, 1.0F + (random.nextFloat() - random.nextFloat()) * 0.2F);
        }  else if (status == 9){
            world.playSound(this, "mocreatures:kittypoo", 1.0F, 1.0F + (random.nextFloat() - random.nextFloat()) * 0.2F);
        }  else if (status == 10){
            world.playSound(this, "mob:chickenplop", 1.0F, 1.0F + (random.nextFloat() - random.nextFloat()) * 0.2F);
        }  else {
            super.processServerEntityStatus(status);
        }
    }

    public void sendRoper(PlayerEntity player, String nameS){
        if (net.fabricmc.loader.FabricLoader.INSTANCE.getEnvironmentType() == EnvType.SERVER) {
            PacketHelper.sendTo(player, new RopePacket("kitty", this.id, nameS));
        }
    }

    @Override
    public Identifier getHandlerIdentifier() {
        return Identifier.of(mod_mocreatures.MOD_ID, "Kitty");
    }

    public void setTypeSpawn() {
        if(!world.isRemote) {
            setType(getRandomRace());
            setAdult(true);
            setAge(0.4F);
            setKittyState(1);
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

    //STATE
    public int getKittyState()
    {
        return dataTracker.getByte(18);
    }

    public void setKittyState(int type)
    {
        dataTracker.set(18, (byte) type);
    }

    //ADULT
    public boolean getAdult()
    {
        return (dataTracker.getByte(19) & 1) != 0;
    }

    public void setAdult(boolean flag)
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

    //EMOTICON
    public boolean getDisplayEmoticon()
    {
        return (dataTracker.getByte(22) & 1) != 0;
    }

    public void setDisplayEmoticon(boolean flag)
    {
        if(flag)
        {
            dataTracker.set(22, Byte.valueOf((byte)1));
        } else
        {
            dataTracker.set(22, Byte.valueOf((byte)0));
        }
    }

    //DISPLAY NAME
    public boolean getDisplayName()
    {
        return (dataTracker.getByte(23) & 1) != 0;
    }

    public void setDisplayName(boolean flag)
    {
        if(flag)
        {
            dataTracker.set(23, Byte.valueOf((byte)1));
        } else
        {
            dataTracker.set(23, Byte.valueOf((byte)0));
        }
    }

    //Picked
    public boolean getPicked()
    {
        return (dataTracker.getByte(25) & 1) != 0;
    }

    public void setPicked(boolean flag)
    {
        if(flag)
        {
            dataTracker.set(25, (byte) 1);
        } else
        {
            dataTracker.set(25, (byte) 0);
        }
    }

    //Climbing
    public boolean getClimbing()
    {
        return (dataTracker.getByte(24) & 1) != 0;
    }

    public void setClimbing(boolean flag)
    {
        if(flag)
        {
            dataTracker.set(24, (byte) 1);
        } else
        {
            dataTracker.set(24, (byte) 0);
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
        this.dataTracker.set(27, owner);
    }

    @Override
    public String getOwner()
    {
        return this.dataTracker.getString(27);
    }

    //NAME
    @Override
    public void setName(String name)
    {
        this.dataTracker.set(28, name);
    }

    @Override
    public String getName()
    {
        return this.dataTracker.getString(28);
    }

    @Override
    public boolean getTamed() {
        return  getKittyState() > 2;
    }
}
