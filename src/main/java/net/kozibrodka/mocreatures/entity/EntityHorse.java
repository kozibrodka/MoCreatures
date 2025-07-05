
package net.kozibrodka.mocreatures.entity;

import net.fabricmc.loader.api.FabricLoader;
import net.kozibrodka.mocreatures.events.KeybindListener;
import net.kozibrodka.mocreatures.events.mod_mocreatures;
import net.kozibrodka.mocreatures.fuelsystem.GuiHorseFuel;
import net.kozibrodka.mocreatures.mixin.LivingAccesor;
import net.kozibrodka.mocreatures.mixin.MonsterBaseAccesor;
import net.kozibrodka.mocreatures.mixin.WalkingBaseAccesor;
import net.kozibrodka.mocreatures.mocreatures.AnimalChest;
import net.kozibrodka.mocreatures.mocreatures.MoCGUI;
import net.kozibrodka.mocreatures.mocreatures.MoCreatureRacial;
import net.minecraft.block.Block;
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
import net.minecraft.inventory.Inventory;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NbtCompound;
import net.minecraft.nbt.NbtList;
import net.minecraft.sound.BlockSoundGroup;
import net.minecraft.util.math.MathHelper;
import net.minecraft.world.World;
import net.modificationstation.stationapi.api.util.Identifier;
import net.modificationstation.stationapi.api.server.entity.MobSpawnDataProvider;
import org.lwjgl.input.Keyboard;

import java.util.List;

public class EntityHorse extends AnimalEntity implements Inventory, MobSpawnDataProvider, MoCreatureRacial
{

    public EntityHorse(World world)
    {
        super(world);
        horseboolean = false;
        setBoundingBoxSpacing(1.4F, 1.6F);
        health = 20;
//        rideable = false;
        isjumping = false;
//        tamed = false;
//        texture = "/assets/mocreatures/stationapi/textures/mob/horseb.png";
//        typeint = 0;
        HorseSpeed = 0.80000000000000004D;
        HorseJump = 0.40000000000000002D;
        temper = 100;
        typechosen = false;
        fwingb = 0.0F;
        fwingc = 0.0F;
        fwingh = 1.0F;
        localstack = new ItemStack[27];
        localstackx54 = new ItemStack[54];
        cargoItems = new ItemStack[1];
        maxhealth = 20;
        hasreproduced = false;
        gestationtime = 0;
//        eatenpumpkin = false;
//        bred = false;
        nightmareInt = 0;
        fireImmune = false;
//        adult = true;
        setAge(0.35F);
        roped = false;
        roper = null;
        killedByOtherEntity = true;
//        name = "";
        displayname = false;
//        isHorsePublic = false;
//        horseOwner = "";
    }

    public void markDead()
    {
        if((getTamed() || getBred()) && health > 0)
        {
            return;
        } else
        {
            super.markDead();
            return;
        }
    }

    public int getRandomRace(){
        int i = mocr.mocreaturesGlass.animals.pegasusChanceS;
        int j = random.nextInt(100);
        if(j <= 51 - i)
        {
            return 1;
        } else
        if(j <= 86 - i)
        {
            return 2;
        } else
        if(j <= 95 - i)
        {
            return 3;
        } else
        if(j <= 99 - i)
        {
            return 4;
        } else
        {
            return 5;
        }
    }

    public void chooseType(int typeint)
    {
            if(typeint == 1)
            {
                HorseSpeed = 0.90000000000000002D;
                texture = "/assets/mocreatures/stationapi/textures/mob/horseb.png";
                maxhealth = 25;
            } else
            if(typeint == 2)
            {
                HorseSpeed = 1.0D;
                temper = 200;
                HorseJump = 0.5D;
                texture = "/assets/mocreatures/stationapi/textures/mob/horsebrownb.png";
                maxhealth = 30;
            } else
            if(typeint == 3)
            {
                HorseSpeed = 1.1000000000000001D;
                temper = 300;
                HorseJump = 0.59999999999999998D;
                texture = "/assets/mocreatures/stationapi/textures/mob/horseblackb.png";
                maxhealth = 35;
            } else
            if(typeint == 4)
            {
                HorseSpeed = 1.3D;
                HorseJump = 0.59999999999999998D;
                temper = 400;
                texture = "/assets/mocreatures/stationapi/textures/mob/horsegoldb.png";
                maxhealth = 40;
            } else
            if(typeint == 5)
            {
                HorseSpeed = 1.2D;
                temper = 500;
                texture = "/assets/mocreatures/stationapi/textures/mob/horsewhiteb.png";
                maxhealth = 40;
            } else
            if(typeint == 6)
            {
                HorseSpeed = 0.90000000000000002D;
                temper = 600;
                texture = "/assets/mocreatures/stationapi/textures/mob/horsepackb.png";
                maxhealth = 40;
            } else
            if(typeint == 7)
            {
                HorseSpeed = 1.3D;
                temper = 700;
                HorseJump = 0.59999999999999998D;
                texture = "/assets/mocreatures/stationapi/textures/mob/horsenightb.png";
                maxhealth = 50;
                fireImmune = true;
            } else
            if(typeint == 8)
            {
                HorseSpeed = 1.3D;
                temper = 800;
                texture = "/assets/mocreatures/stationapi/textures/mob/horsebpb.png";
                maxhealth = 50;
                fireImmune = true;
            }
            health = maxhealth;
    }

    public void Riding()
    {
        if(passenger != null && (passenger instanceof PlayerEntity))
        {
            PlayerEntity entityplayer = (PlayerEntity)passenger;
            List list = world.getEntities(this, boundingBox.expand(1.0D, 0.0D, 1.0D));
            if(list != null)
            {
                for(int i = 0; i < list.size(); i++)
                {
                    Entity entity = (Entity)list.get(i);
                    if(entity.dead)
                    {
                        continue;
                    }
                    entity.onPlayerInteraction(entityplayer);
                    if(!(entity instanceof MonsterEntity))
                    {
                        continue;
                    }
                    float f = getDistance(entity);
                    if(f < 2.0F && random.nextInt(10) == 0)
                    {
                        damage(entity, ((MonsterBaseAccesor)entity).getAttackDamage());
                    }
                }

            }
            if(entityplayer.isSneaking())
            {
                entityplayer.setVehicle(null);
            }
        }
    }

    public void tickMovement()
    {
        if(!typechosen && world.isRemote && getType() != 0){
            typechosen = true;
            chooseType(getType());
        }
        if(random.nextInt(300) == 0 && health < maxhealth && deathTime == 0)
        {
            health++;
        }
        Riding();
        if(getType() == 5 || getType() == 8)
        {
            fwinge = fwingb;
            fwingd = fwingc;
            fwingc = (float)((double)fwingc + (double)(onGround ? -1 : 4) * 0.29999999999999999D);
            if(fwingc < 0.0F)
            {
                fwingc = 0.0F;
            }
            if(fwingc > 1.0F)
            {
                fwingc = 1.0F;
            }
            if(!onGround && fwingh < 1.0F)
            {
                fwingh = 0.3F;
            }
            fwingh = (float)((double)fwingh * 0.90000000000000002D);
            if(!onGround && velocityY < 0.0D)
            {
                velocityY *= 0.59999999999999998D;
            }
            fwingb += fwingh * 2.0F;
        }
        super.tickMovement();
        if(getType() == 7 && passenger != null && nightmareInt > 0 && random.nextInt(2) == 0)
        {
            NightmareEffect();
        }
        if(!getAdult() && random.nextInt(200) == 0)
        {

            setAge(getAge()+0.01F);
            if(getAge() >= 1.0F)
            {
                setAdult(true);
            }
        }
        if(!ReadyforParenting(this))
        {
            return;
        }
        int i = 0;
        List list = world.getEntities(this, boundingBox.expand(8D, 3D, 8D));
        for(int j = 0; j < list.size(); j++)
        {
            Entity entity = (Entity)list.get(j);
            if(entity instanceof EntityHorse)
            {
                i++;
            }
        }

        if(i > 1)
        {
            return;
        }
        List list1 = world.getEntities(this, boundingBox.expand(4D, 2D, 4D));
        for(int k = 0; k < list.size(); k++)
        {
            Entity entity1 = (Entity)list1.get(k);
            if(!(entity1 instanceof EntityHorse) || entity1 == this)
            {
                continue;
            }
            EntityHorse entityhorse = (EntityHorse)entity1;
            if(!ReadyforParenting(this) || !ReadyforParenting(entityhorse))
            {
                continue;
            }
            if(random.nextInt(100) == 0)
            {
                gestationtime++;
            }
            if(gestationtime <= 50)
            {
                continue;
            }
            EntityHorse entityhorse1 = new EntityHorse(world);
            entityhorse1.setPosition(x, y, z);
            world.spawnEntity(entityhorse1);
            world.playSound(this, "mob.chickenplop", 1.0F, (random.nextFloat() - random.nextFloat()) * 0.2F + 1.0F);
            setEaten(false);
            if(!(mocr.mocreaturesGlass.animals.easybreeding))
            {
                hasreproduced = true;
            }
            entityhorse.setEaten(false);
            gestationtime = 0;
            entityhorse.gestationtime = 0;
            int l = HorseGenetics(this, entityhorse);
            entityhorse1.setBred(true);
            entityhorse1.setAdult(false);
            entityhorse1.setType(l);
            break;
        }

    }

    protected void tickLiving()
    {
        if(!interpolateOnly && passenger == null)
        {
            super.tickLiving();
        }
        if(getTamed() && vehicle == null && roper != null)
        {
            float f = roper.getDistance(this);
            if(f > 5F)
            {
                method_429(roper, f);
            }
        }
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

    private void NightmareEffect()
    {
        int i = MathHelper.floor(x);
        int j = MathHelper.floor(boundingBox.minY);
        int k = MathHelper.floor(z);
        world.setBlock(i - 1, j, k - 1, Block.FIRE.id);
        PlayerEntity entityplayer = (PlayerEntity)passenger;
        if(entityplayer != null && entityplayer.fireTicks > 0)
        {
            entityplayer.fireTicks = 0;
        }
        nightmareInt--;
    }

    public boolean ReadyforParenting(EntityHorse entityhorse)
    {
        return entityhorse.passenger == null && entityhorse.vehicle == null && entityhorse.getTamed() && entityhorse.getEaten() && !entityhorse.hasreproduced && entityhorse.getAdult();
    }

    private int HorseGenetics(EntityHorse entityhorse, EntityHorse entityhorse1)
    {
        if(entityhorse.getType() == entityhorse1.getType())
        {
            return entityhorse.getType();
        }
        int i = entityhorse.getType() + entityhorse1.getType();
        boolean flag = mocr.mocreaturesGlass.animals.easybreeding;
        boolean flag1 = random.nextInt(3) == 0;
        if(i == 7 && (flag || flag1))
        {
            return 6;
        }
        if(i == 9 && (flag || flag1))
        {
            return 7;
        }
        if(i == 10 && (flag || flag1))
        {
            return 5;
        } else
        {
            return i == 12 && (flag || flag1) ? 8 : 0;
        }
    }

    public boolean damage(Entity entity, int i)
    {
        if(passenger != null && entity == passenger)
        {
            return false;
        }
        if(entity instanceof WolfEntity)
        {
            MobEntity entitycreature = (MobEntity)entity;
            ((WalkingBaseAccesor)entitycreature).setTarget(null);

            return false;
        } else
        {
            return super.damage(entity, i);
        }
    }

    public void travel(float f, float f1)
    {
        if(checkWaterCollisions())
        {
            if(passenger != null)
            {
                velocityX += passenger.velocityX * (HorseSpeed / 2D);
                velocityZ += passenger.velocityZ * (HorseSpeed / 2D);
                PlayerEntity entityplayer = (PlayerEntity)passenger;
                if(((LivingAccesor)entityplayer).getJumping() && !isjumping)
                {
                    velocityY += 0.5D;
                    isjumping = true;
                }
                move(velocityX, velocityY, velocityZ);
                if(onGround)
                {
                    isjumping = false;
                }
                pitch = passenger.pitch * 0.5F;
                if(random.nextInt(20) == 0)
                {
                    yaw = passenger.yaw;
                }
                setRotation(yaw, pitch);
                if(!getTamed())
                {
                    passenger = null;
                }
            }
            double d = y;
            moveNonSolid(f, f1, 0.02F);
            move(velocityX, velocityY, velocityZ);
            velocityX *= 0.80000001192092896D;
            velocityY *= 0.80000001192092896D;
            velocityZ *= 0.80000001192092896D;
            velocityY -= 0.02D;
            if(horizontalCollision && getEntitiesInside(velocityX, ((velocityY + 0.60000002384185791D) - y) + d, velocityZ))
            {
                velocityY = 0.30000001192092901D;
            }
        } else
        if(isTouchingLava())
        {
            if(passenger != null)
            {
                velocityX += passenger.velocityX * (HorseSpeed / 2D);
                velocityZ += passenger.velocityZ * (HorseSpeed / 2D);
                PlayerEntity entityplayer1 = (PlayerEntity)passenger;
                if(((LivingAccesor)entityplayer1).getJumping() && !isjumping)
                {
                    velocityY += 0.5D;
                    isjumping = true;
                }
                move(velocityX, velocityY, velocityZ);
                if(onGround)
                {
                    isjumping = false;
                }
                pitch = passenger.pitch * 0.5F;
                if(random.nextInt(20) == 0)
                {
                    yaw = passenger.yaw;
                }
                setRotation(yaw, pitch);
                if(!getTamed())
                {
                    passenger = null;
                }
            }
            double d1 = y;
            moveNonSolid(f, f1, 0.02F);
            move(velocityX, velocityY, velocityZ);
            velocityX *= 0.5D;
            velocityY *= 0.5D;
            velocityZ *= 0.5D;
            velocityY -= 0.02D;
            if(horizontalCollision && getEntitiesInside(velocityX, ((velocityY + 0.60000002384185791D) - y) + d1, velocityZ))
            {
                velocityY = 0.30000001192092901D;
            }
        } else
        {
            float f2 = 0.91F;
            if(onGround)
            {
                f2 = 0.5460001F;
                int i = world.getBlockId(MathHelper.floor(x), MathHelper.floor(boundingBox.minY) - 1, MathHelper.floor(z));
                if(i > 0)
                {
                    f2 = Block.BLOCKS[i].slipperiness * 0.91F;
                }
            }
            float f3 = 0.162771F / (f2 * f2 * f2);
            moveNonSolid(f, f1, onGround ? 0.1F * f3 : 0.02F);
            f2 = 0.91F;
            if(onGround)
            {
                f2 = 0.5460001F;
                int j = world.getBlockId(MathHelper.floor(x), MathHelper.floor(boundingBox.minY) - 1, MathHelper.floor(z));
                if(j > 0)
                {
                    f2 = Block.BLOCKS[j].slipperiness * 0.91F;
                }
            }
            if(isOnLadder())
            {
                fallDistance = 0.0F;
                if(velocityY < -0.14999999999999999D)
                {
                    velocityY = -0.14999999999999999D;
                }
            }
            if(passenger != null && !getTamed())
            {
                if(random.nextInt(5) == 0 && !isjumping)
                {
                    velocityY += 0.40000000000000002D;
                    isjumping = true;
                }
                if(random.nextInt(10) == 0)
                {
                    velocityX += random.nextDouble() / 30D;
                    velocityZ += random.nextDouble() / 10D;
                }
                move(velocityX, velocityY, velocityZ);
                if(random.nextInt(50) == 0)
                {
                    world.playSound(this, "mocreatures:horsemad", 1.0F, 1.0F + (random.nextFloat() - random.nextFloat()) * 0.2F);
                    passenger.velocityY += 0.90000000000000002D;
                    passenger.velocityZ -= 0.29999999999999999D;
                    passenger = null;
                }
                if(onGround)
                {
                    isjumping = false;
                }
                if(random.nextInt(temper * 8) == 0)
                {
                    setTamed(true);
                    PlayerEntity milosc = (PlayerEntity)passenger;
                    setOwner(milosc.name);
                    setName(this);
                }
            }
            if(passenger != null && getTamed())
            {
                boundingBox.maxY = passenger.boundingBox.maxY;
                velocityX += passenger.velocityX * HorseSpeed;
                velocityZ += passenger.velocityZ * HorseSpeed;
                PlayerEntity entityplayer2 = (PlayerEntity)passenger;
                if(((LivingAccesor)entityplayer2).getJumping() && !isjumping)
                {
                    velocityY += HorseJump;
                    isjumping = true;
                }
                if(((LivingAccesor)entityplayer2).getJumping() && (getType() == 5 || getType() == 8))
                {
                    velocityY += 0.10000000000000001D;
                }
                move(velocityX, velocityY, velocityZ);
                if(onGround)
                {
                    isjumping = false;
                }
                prevYaw = yaw = passenger.yaw;
                pitch = passenger.pitch * 0.5F;
                setRotation(yaw, pitch);
            }
            move(velocityX, velocityY, velocityZ);
            if(horizontalCollision && isOnLadder())
            {
                velocityY = 0.20000000000000001D;
            }
            if((getType() == 5 || getType() == 8) && passenger != null && getTamed())
            {
                velocityY -= 0.080000000000000002D;
                velocityY *= 0.60000000000000009D;
            } else
            {
                velocityY -= 0.080000000000000002D;
                velocityY *= 0.98000001907348633D;
            }
            velocityX *= f2;
            velocityZ *= f2;
        }
        lastWalkAnimationSpeed = walkAnimationSpeed;
        double d2 = x - prevX;
        double d3 = z - prevZ;
        float f4 = MathHelper.sqrt(d2 * d2 + d3 * d3) * 4F;
        if(f4 > 1.0F)
        {
            f4 = 1.0F;
        }
        walkAnimationSpeed += (f4 - walkAnimationSpeed) * 0.4F;
        walkAnimationProgress += walkAnimationSpeed;
        if(mocr.mocreaturesGlass.balancesettings.horse_fuel && mc.currentScreen == null && Keyboard.isKeyDown(KeybindListener.keyBinding_horseFuel.code) && passenger != null)
        {
            openFuelGui();
        }
        burnFuel(1);
        addFuel();
    }

    //TODO:EXTRA FUEL
    public boolean isFuelled()
    {
        if(mocr.mocreaturesGlass.balancesettings.horse_fuel)
        {
            return animalFuel > 0;
        }else{
            return true;
        }
    }

    public void burnFuel(int a){
        if(mocr.mocreaturesGlass.balancesettings.horse_fuel)
        {
            animalFuel = animalFuel - a;
        }
    }

    public void addFuel(){
        if(mocr.mocreaturesGlass.balancesettings.horse_fuel)
        {
            if(animalFuel <= 0 && passenger != null && !world.isRemote)
            {
                if(cargoItems[0] != null && cargoItems[0].itemId == Item.COAL.id)  //PALIWO
                {
                    animalFuel = 200; //plane.planeFuelAdd
                    removeStack(0, 1);
                }
            }
        }
    }

    public ItemStack removeStack(int i, int j)
    {
        if(cargoItems[i] != null)
        {
            if(cargoItems[i].count <= j)
            {
                ItemStack itemstack = cargoItems[i];
                cargoItems[i] = null;
                return itemstack;
            }
            ItemStack itemstack1 = cargoItems[i].split(j);
            if(cargoItems[i].count == 0)
            {
                cargoItems[i] = null;
            }
            return itemstack1;
        } else
        {
            return null;
        }
    }

    public void openFuelGui(){
        if(!world.isRemote) {
            if (mc.currentScreen instanceof GuiHorseFuel) {
                mc.setScreen(null);
            } else if (passenger.vehicle instanceof EntityHorse) {
                mc.setScreen(new GuiHorseFuel(((PlayerEntity)passenger).inventory, (EntityHorse)passenger.vehicle));
            }
        }
    }


    public int getBurnTimeRemainingScaled(int i)
    {
        return (animalFuel * i) / 200;//return (planeFuel * i) / plane.planeFuelAdd;
    }

    public int size()
    {
        return 1;
    }

    public ItemStack getStack(int i)
    {
        return cargoItems[i];
    }


    public int getMaxCountPerStack()
    {
        return 64;
    }

    public void markDirty()
    {
    }

    public void setStack(int i, ItemStack itemstack)
    {
        cargoItems[i] = itemstack;
        if(itemstack != null && itemstack.count > getMaxCountPerStack())
        {
            itemstack.count = getMaxCountPerStack();
        }
    }

    public boolean canPlayerUse(PlayerEntity entityplayer)
    {
        if(dead)
        {
            return false;
        } else
        {
            return entityplayer.getSquaredDistance(this) <= 64D;
        }
    }

    //TODO:EXTRA FUEL end

    protected void onLanding(float f)
    {
        int i = (int)Math.ceil(f - 3F);
        if(i > 0 && getType() != 5 && getType() != 8)
        {
            if(getType() >= 3)
            {
                i /= 3;
            }
            if(i > 0)
            {
                damage(this, i);
            }
            if(passenger != null && i > 0)
            {
                passenger.damage(null, i);
            }
            if(getType() == 5 || getType() == 8)
            {
                return;
            }
            int j = world.getBlockId(MathHelper.floor(x), MathHelper.floor(y - 0.20000000298023221D - (double)prevPitch), MathHelper.floor(z));
            if(j > 0)
            {
                BlockSoundGroup stepsound = Block.BLOCKS[j].soundGroup;
                world.playSound(this, stepsound.getSound(), stepsound.getVolume() * 0.5F, stepsound.getPitch() * 0.75F);
            }
        }
    }

    protected float getSoundVolume()
    {
        return 0.4F;
    }

    public boolean isCollidable()
    {
        return passenger == null;
    }

    public boolean interact(PlayerEntity entityplayer)
    {
        ItemStack itemstack = entityplayer.inventory.getSelectedItem();
        if(getTamed() && !getProtect() && !entityplayer.name.equals(getOwner()))
        {
            return false;
        }
        if(itemstack != null && getTamed() && entityplayer.name.equals(getOwner()) && (itemstack.itemId == Item.GOLDEN_SWORD.id || itemstack.itemId == Item.STONE_SWORD.id || itemstack.itemId == Item.WOODEN_SWORD.id || itemstack.itemId == Item.IRON_SWORD.id || itemstack.itemId == Item.DIAMOND_SWORD.id))
        {
            if(getProtect()){
                setProtect(false);
                for(int var3 = 0; var3 < 7; ++var3) {
                    double var4 = this.random.nextGaussian() * 0.02D;
                    double var6 = this.random.nextGaussian() * 0.02D;
                    double var8 = this.random.nextGaussian() * 0.02D;
                    world.addParticle("heart", this.x + (double) (this.random.nextFloat() * this.width * 2.0F) - (double) this.width, this.y + 0.5D + (double) (this.random.nextFloat() * this.height), this.z + (double) (this.random.nextFloat() * this.width * 2.0F) - (double) this.width, var4, var6, var8);
                }
            }else{
                setProtect(true);
                for(int var3 = 0; var3 < 7; ++var3) {
                    double var4 = this.random.nextGaussian() * 0.02D;
                    double var6 = this.random.nextGaussian() * 0.02D;
                    double var8 = this.random.nextGaussian() * 0.02D;
                    world.addParticle("flame", this.x + (double) (this.random.nextFloat() * this.width * 2.0F) - (double) this.width, this.y + 0.5D + (double) (this.random.nextFloat() * this.height), this.z + (double) (this.random.nextFloat() * this.width * 2.0F) - (double) this.width, var4, var6, var8);
                }
            }
            return true;
        }
        if(itemstack != null && itemstack.itemId == Item.WHEAT.id)
        {
            if(--itemstack.count == 0)
            {
                entityplayer.inventory.setStack(entityplayer.inventory.selectedSlot, null);
            }
            if((temper -= 25) < 25)
            {
                temper = 25;
            }
            if((health += 5) > maxhealth)
            {
                health = maxhealth;
            }
            world.playSound(this, "mocreatures:eating", 1.0F, 1.0F + (random.nextFloat() - random.nextFloat()) * 0.2F);
            if(!getAdult() && getAge() < 1.0F)
            {
                setAge(getAge()+0.01F);
            }
            return true;
        }
        if(itemstack != null && itemstack.itemId == mod_mocreatures.sugarlump.id)
        {
            if(--itemstack.count == 0)
            {
                entityplayer.inventory.setStack(entityplayer.inventory.selectedSlot, null);
            }
            if((temper -= 50) < 25)
            {
                temper = 25;
            }
            if((health += 10) > maxhealth)
            {
                health = maxhealth;
            }
            world.playSound(this, "mocreatures:eating", 1.0F, 1.0F + (random.nextFloat() - random.nextFloat()) * 0.2F);
            if(!getAdult() && getAge() < 1.0F)
            {
                setAge(getAge()+0.02F);
            }
            return true;
        }
        if(itemstack != null && itemstack.itemId == Item.BREAD.id)
        {
            if(--itemstack.count == 0)
            {
                entityplayer.inventory.setStack(entityplayer.inventory.selectedSlot, null);
            }
            if((temper -= 100) < 25)
            {
                temper = 25;
            }
            if((health += 20) > maxhealth)
            {
                health = maxhealth;
            }
            world.playSound(this, "mocreatures:eating", 1.0F, 1.0F + (random.nextFloat() - random.nextFloat()) * 0.2F);
            if(!getAdult() && getAge() < 1.0F)
            {
                setAge(getAge()+0.03F);
            }
            return true;
        }
        if(itemstack != null && (((mocr.mocreaturesGlass.balancesettings.balance_drop) && itemstack.itemId == mod_mocreatures.greenapple.id)  ||  (((itemstack.itemId == Item.APPLE.id)||(itemstack.itemId == Item.GOLDEN_APPLE.id)) && !mocr.mocreaturesGlass.balancesettings.balance_drop)))
        {
            if(--itemstack.count == 0)
            {
                entityplayer.inventory.setStack(entityplayer.inventory.selectedSlot, null);
            }
            setTamed(true);
            health = maxhealth;
            world.playSound(this, "mocreatures:eating", 1.0F, 1.0F + (random.nextFloat() - random.nextFloat()) * 0.2F);
            if(!getAdult() && getAge() < 1.0F)
            {
                setAge(getAge()+0.05F);
            }
            setOwner(entityplayer.name);
            setName(this);
            return true;
        }
        if(itemstack != null && getTamed() && itemstack.itemId == Block.CHEST.id && (getType() == 6 || getType() == 8))
        {
            if(getChested())
            {
                return false;
            }
            if(--itemstack.count == 0)
            {
                entityplayer.inventory.setStack(entityplayer.inventory.selectedSlot, null);
            }
            setChested(true);
            world.playSound(this, "mob.chickenplop", 1.0F, (random.nextFloat() - random.nextFloat()) * 0.2F + 1.0F);
            return true;
        }
        if(itemstack != null && getTamed() && itemstack.itemId == mod_mocreatures.haystack.id)
        {
            if(--itemstack.count == 0)
            {
                entityplayer.inventory.setStack(entityplayer.inventory.selectedSlot, null);
            }
            setSitting(true);
            world.playSound(this, "mocreatures:eating", 1.0F, 1.0F + (random.nextFloat() - random.nextFloat()) * 0.2F);
            health = maxhealth; //TODO: maXHealth logic...
            return true;
        }
        if(itemstack != null && (itemstack.itemId == Item.STONE_SHOVEL.id || itemstack.itemId == Block.TORCH.id) && getChested())
        {
            if(getType() == 8)
            {
                localhorsechest = new AnimalChest(localstack, "Dark Pegasus chest", localstack.length);
                entityplayer.openChestScreen(localhorsechest);
                return true;
            }
            if(getType() == 6)
            {
                localhorsechestx54 = new AnimalChest(localstackx54, "Pack Horse chest", localstackx54.length);
                entityplayer.openChestScreen(localhorsechestx54);
                return true;
            }
        }
        if(itemstack != null && (itemstack.itemId == Block.PUMPKIN.id || itemstack.itemId == Item.MUSHROOM_STEW.id || itemstack.itemId == Item.CAKE.id))
        {
            if(hasreproduced || !getAdult())
            {
                return false;
            }
            if(itemstack.itemId == Item.MUSHROOM_STEW.id)
            {
                itemstack.count--;
                entityplayer.inventory.addStack(new ItemStack(Item.BOWL));
            } else
            if(--itemstack.count == 0)
            {
                entityplayer.inventory.setStack(entityplayer.inventory.selectedSlot, null);
            }
            setEaten(true);
            health = maxhealth;
            world.playSound(this, "mocreatures:eating", 1.0F, 1.0F + (random.nextFloat() - random.nextFloat()) * 0.2F);
            return true;
        }
        if(itemstack != null && getTamed() && itemstack.itemId == Item.REDSTONE.id && getType() == 7)
        {
            if(nightmareInt > 500)
            {
                return false;
            }
            if(--itemstack.count == 0)
            {
                entityplayer.inventory.setStack(entityplayer.inventory.selectedSlot, null);
            }
            nightmareInt = 500;
            world.playSound(this, "mocreatures:eating", 1.0F, 1.0F + (random.nextFloat() - random.nextFloat()) * 0.2F);
            return true;
        }
        if(itemstack != null && itemstack.itemId == mod_mocreatures.whip.id && getTamed() && passenger == null)
        {
            setSitting(!getSitting());
            return true;
        }
        if(itemstack != null && passenger == null && roper == null && getTamed() && itemstack.itemId == mod_mocreatures.rope.id)
        {
            if(--itemstack.count == 0)
            {
                entityplayer.inventory.setStack(entityplayer.inventory.selectedSlot, null);
            }
            world.playSound(this, "mocreatures:roping", 1.0F, 1.0F + (random.nextFloat() - random.nextFloat()) * 0.2F);
            roper = entityplayer;
            return true;
        }
        if(roper != null && getTamed())
        {
            entityplayer.inventory.addStack(new ItemStack(mod_mocreatures.rope));
            world.playSound(this, "mocreatures:roping", 1.0F, 1.0F + (random.nextFloat() - random.nextFloat()) * 0.2F);
            roper = null;
            return true;
        }
        if(itemstack != null && getTamed() && (itemstack.itemId == mod_mocreatures.medallion.id || itemstack.itemId == Item.BOOK.id))
        {
            setName(this);
            return true;
        }
        if(itemstack != null && getTamed() && (itemstack.itemId == Item.DIAMOND_PICKAXE.id || itemstack.itemId == Item.WOODEN_PICKAXE.id || itemstack.itemId == Item.STONE_PICKAXE.id || itemstack.itemId == Item.IRON_PICKAXE.id || itemstack.itemId == Item.GOLDEN_PICKAXE.id))
        {
            displayname = !displayname;
            return true;
        }
        if(getSaddled() && getAdult())
        {
            entityplayer.yaw = yaw;
            entityplayer.pitch = pitch;
            setSitting(false);
            entityplayer.setVehicle(this);
            gestationtime = 0;
            if(getType() == 8)
            {
                entityplayer.incrementStat(mod_mocreatures.WilfFlyingWest);
            }
            return true;
        } else
        {
            return false;
        }
    }

    public void onKilledBy(Entity entity)
    {
        if(scoreAmount >= 0 && entity != null)
        {
            entity.updateKilledAchievement(this, scoreAmount);
        }
        if(entity != null)
        {
            entity.onKilledOther(this);
        }
        killedByOtherEntity = true;
        if(!world.isRemote)
        {
            dropItems();
        }
        world.getSkyColor(this, 3F);
        if(getChested() && (getType() == 6 || getType() == 8))
        {
            int i = MathHelper.floor(x);
            int j = MathHelper.floor(boundingBox.minY);
            int k = MathHelper.floor(z);
            HorseRemoval(world, i, j, k);
        }
    }

    public void HorseRemoval(World world, int i, int j, int k)
    {
        if(localstack != null)
        {
            localhorsechest = new AnimalChest(localstack, "HorseChest", localstack.length);
            label0:
            for(int l = 0; l < localhorsechest.size(); l++)
            {
                ItemStack itemstack = localhorsechest.getStack(l);
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
        if(localstackx54 != null)
        {
            localhorsechestx54 = new AnimalChest(localstackx54, "HorseChest", localstackx54.length);
            label0:
            for(int l = 0; l < localhorsechestx54.size(); l++)
            {
                ItemStack itemstack = localhorsechestx54.getStack(l);
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

    public void writeNbt(NbtCompound nbttagcompound)
    {
        super.writeNbt(nbttagcompound);
        nbttagcompound.putBoolean("Saddle", getSaddled());
        nbttagcompound.putBoolean("EatingHaystack", getSitting());
        nbttagcompound.putBoolean("Tamed", getTamed());
        nbttagcompound.putBoolean("HorseBoolean", horseboolean);
        nbttagcompound.putInt("TypeInt", getType());
        nbttagcompound.putBoolean("ChestedHorse", getChested());
        nbttagcompound.putBoolean("HasReproduced", hasreproduced);
        nbttagcompound.putBoolean("Bred", getBred());
        nbttagcompound.putBoolean("Adult", getAdult());
        nbttagcompound.putFloat("Age", getAge());
        nbttagcompound.putString("Name", getName());
        nbttagcompound.putBoolean("DisplayName", displayname);
        nbttagcompound.putInt("GestationTime", gestationtime);
        nbttagcompound.putBoolean("EatenPumpkin", getEaten());
        nbttagcompound.putBoolean("PublicHorse", getProtect());
        nbttagcompound.putString("HorseOwner", getOwner());
        if(getType() == 8)
        {
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

        if(getType() == 6)
        {
            NbtList nbttaglist = new NbtList();
            for(int i = 0; i < localstackx54.length; i++)
            {
                if(localstackx54[i] != null)
                {
                    NbtCompound nbttagcompound1 = new NbtCompound();
                    nbttagcompound1.putByte("Slot", (byte)i);
                    localstackx54[i].writeNbt(nbttagcompound1);
                    nbttaglist.add(nbttagcompound1);
                }
            }

            nbttagcompound.put("Items", nbttaglist);
        }
        if(mocr.mocreaturesGlass.balancesettings.horse_fuel) {
            NbtList fuelList = new NbtList();
            for (int i = 0; i < cargoItems.length; i++) {
                if (cargoItems[i] != null) {
                    NbtCompound comp2 = new NbtCompound();
                    comp2.putByte("SlotFuel", (byte) i);
                    cargoItems[i].writeNbt(comp2);
                    fuelList.add(comp2);
                }
            }
            nbttagcompound.put("Fuels", fuelList);
        }

    }

    public void readNbt(NbtCompound nbttagcompound)
    {
        super.readNbt(nbttagcompound);
        setSaddled(nbttagcompound.getBoolean("Saddle"));
        setTamed(nbttagcompound.getBoolean("Tamed"));
        setSitting(nbttagcompound.getBoolean("EatingHaystack"));
        setBred(nbttagcompound.getBoolean("Bred"));
        setAdult(nbttagcompound.getBoolean("Adult"));
        horseboolean = nbttagcompound.getBoolean("HorseBoolean");
        setChested(nbttagcompound.getBoolean("ChestedHorse"));
        hasreproduced = nbttagcompound.getBoolean("HasReproduced");
        setType(nbttagcompound.getInt("TypeInt"));
        setAge(nbttagcompound.getFloat("Age"));
        setName(nbttagcompound.getString("Name"));
        displayname = nbttagcompound.getBoolean("DisplayName");
        gestationtime = nbttagcompound.getInt("GestationTime");
        setEaten(nbttagcompound.getBoolean("EatenPumpkin"));
        setProtect(nbttagcompound.getBoolean("PublicHorse"));
        setOwner(nbttagcompound.getString("HorseOwner"));
        if(getType() == 8)
        {
            NbtList nbttaglist = nbttagcompound.getList("Items");
            localstack = new ItemStack[27];
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

        if(getType() == 6)
        {
            NbtList nbttaglist = nbttagcompound.getList("Items");
            localstackx54 = new ItemStack[54];
            for(int i = 0; i < nbttaglist.size(); i++)
            {
                NbtCompound nbttagcompound1 = (NbtCompound)nbttaglist.get(i);
                int j = nbttagcompound1.getByte("Slot") & 0xff;
                if(j >= 0 && j < localstackx54.length)
                {
                    localstackx54[j] = new ItemStack(nbttagcompound1);
                }
            }
        }

        if(mocr.mocreaturesGlass.balancesettings.horse_fuel) {
            NbtList nbttaglist2 = nbttagcompound.getList("Fuels");
            cargoItems = new ItemStack[1];
            for(int i = 0; i < nbttaglist2.size(); i++)
            {
                NbtCompound nbttagcompound1 = (NbtCompound)nbttaglist2.get(i);
                int k = nbttagcompound1.getByte("SlotFuel") & 0xff;
                if(k >= 0 && k < cargoItems.length)
                {
                    cargoItems[k] = new ItemStack(nbttagcompound1);
                }
            }
        }


    }

    protected boolean isMovementBlocked()
    {
        return getSitting() || passenger != null;
    }

    protected String getRandomSound()
    {
        return "mocreatures:horsegrunt";
    }

    protected String getHurtSound()
    {
        return "mocreatures:horsehurt";
    }

    protected String getDeathSound()
    {
        return "mocreatures:horsedying";
    }

    protected void dropItems()
    {
        int i = random.nextInt(3);
        for(int j = 0; j < i; j++)
        {
            dropItem(new ItemStack(getDroppedItemId(), 1, 0), 0.0F);
        }

        if(getSaddled() && mocr.mocreaturesGlass.balancesettings.horse_saddle) {
            int k = random.nextInt(2);
            for (int j = 0; j < k; j++) {
                dropItem(new ItemStack(mod_mocreatures.horsesaddle, 1, 0), 0.0F);
            }
        }
    }

    protected int getDroppedItemId()
    {
        return Item.LEATHER.id;
    }

    public int getLimitPerChunk()
    {
        return 6;
    }

    public boolean canSpawn()
    {
        return mocr.mocreaturesGlass.animals.horsefreq > 0 && super.canSpawn();
    }

    public boolean renderName()
    {
        return displayname && passenger == null;
    }

    public String dajTexture(){
        return texture;
    }

    public static void setName(EntityHorse entityhorse)
    {
        entityhorse.displayname = true;
        mc.setScreen(new MoCGUI(entityhorse, entityhorse.getName()));
    }

    public static Minecraft mc = Minecraft.class.cast(FabricLoader.getInstance().getGameInstance());
    mod_mocreatures mocr = new mod_mocreatures();
    private int nightmareInt;
    private int gestationtime;
    public boolean hasreproduced;
    public int maxhealth;
    private int temper;
    private double HorseSpeed;
    private double HorseJump;
    public boolean horseboolean;
    public boolean isjumping;
    public float fwingb;
    public float fwingc;
    public float fwingd;
    public float fwinge;
    public float fwingh;
    private Inventory localhorsechest;
    public ItemStack localstack[];
    private Inventory localhorsechestx54;
    public ItemStack localstackx54[];
    public boolean roped;
    public LivingEntity roper;
    public boolean displayname;
    public ItemStack cargoItems[];
    private int animalFuel;

//    public int typeint;
//    public float b; //AGE
//    public boolean adult;
//    public boolean eatenpumpkin; //eaten
//    public boolean tamed;
//    public boolean eatinghaystack; //sitting
//    public boolean rideable; //saddle
//    public boolean isHorsePublic; //public
//    private boolean bred;
//    public boolean chestedhorse;

//    public String horseOwner;
//    public String name;

    public boolean typechosen;

    protected void initDataTracker()
    {
        super.initDataTracker();
        dataTracker.startTracking(16, (byte) 0); //Type
        dataTracker.startTracking(17, (int) 0); //Age
        dataTracker.startTracking(18, (byte) 0); //Adult
        dataTracker.startTracking(19, (byte) 0); //EatenHayStack
        dataTracker.startTracking(20, (byte) 0); //Tamed
        dataTracker.startTracking(21, (byte) 0); //Sitting
        dataTracker.startTracking(22, (byte) 0); //Saddle
        dataTracker.startTracking(23, (byte) 0); //Protect From Players/Public
        dataTracker.startTracking(24, (byte) 0); //Bred
        dataTracker.startTracking(25, (byte) 0); //Chested
        dataTracker.startTracking(30, ""); //Owner
        dataTracker.startTracking(31, ""); //Name
    }

    @Override
    public Identifier getHandlerIdentifier() {
        return Identifier.of(mod_mocreatures.MOD_ID, "Horse");
    }

    //TYPE
    public void setTypeSpawn() {
        if(!world.isRemote) {
            if (random.nextInt(5) == 0) {
                setAdult(false);
            }
            setType(getRandomRace());
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
            dataTracker.set(18, (byte) 1);
        } else
        {
            dataTracker.set(18, (byte) 0);
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
            dataTracker.set(19, (byte) 1);
        } else
        {
            dataTracker.set(19, (byte) 0);
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
            dataTracker.set(20, (byte) 1);
        } else
        {
            dataTracker.set(20, (byte) 0);
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
            dataTracker.set(21, (byte) 1);
        } else
        {
            dataTracker.set(21, (byte) 0);
        }
    }

    //HUNGRY
    public boolean getSaddled()
    {
        return (dataTracker.getByte(22) & 1) != 0;
    }

    public void setSaddled(boolean flag)
    {
        if(flag)
        {
            dataTracker.set(22, (byte) 1);
        } else
        {
            dataTracker.set(22, (byte) 0);
        }
    }

    //PROTECT FROM PLAYERES / PUBLIC
    public boolean getProtect()
    {
        return (dataTracker.getByte(23) & 1) != 0;
    }

    public void setProtect(boolean flag)
    {
        if(flag)
        {
            dataTracker.set(23, (byte) 1);
        } else
        {
            dataTracker.set(23, (byte) 0);
        }
    }

    //BRED
    public boolean getBred()
    {
        return (dataTracker.getByte(24) & 1) != 0;
    }

    public void setBred(boolean flag)
    {
        if(flag)
        {
            dataTracker.set(24, (byte) 1);
        } else
        {
            dataTracker.set(24, (byte) 0);
        }
    }

    //CHESTED
    public boolean getChested()
    {
        return (dataTracker.getByte(25) & 1) != 0;
    }

    public void setChested(boolean flag)
    {
        if(flag)
        {
            dataTracker.set(25, (byte) 1);
        } else
        {
            dataTracker.set(25, (byte) 0);
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
