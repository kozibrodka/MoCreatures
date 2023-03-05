// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) braces deadcode 

package net.kozibrodka.mocreatures.entity;

import net.fabricmc.loader.api.FabricLoader;
import net.kozibrodka.mocreatures.events.mod_mocreatures;
import net.kozibrodka.mocreatures.mixin.LivingAccesor;
import net.kozibrodka.mocreatures.mixin.MonsterBaseAccesor;
import net.kozibrodka.mocreatures.mixin.WalkingBaseAccesor;
import net.kozibrodka.mocreatures.mocreatures.AnimalChest;
import net.kozibrodka.mocreatures.mocreatures.MoCGUI;
import net.minecraft.block.BlockBase;
import net.minecraft.block.BlockSounds;
import net.minecraft.class_61;
import net.minecraft.client.Minecraft;
import net.minecraft.entity.EntityBase;
import net.minecraft.entity.Item;
import net.minecraft.entity.Living;
import net.minecraft.entity.WalkingBase;
import net.minecraft.entity.animal.AnimalBase;
import net.minecraft.entity.animal.Wolf;
import net.minecraft.entity.monster.MonsterBase;
import net.minecraft.entity.player.PlayerBase;
import net.minecraft.inventory.InventoryBase;
import net.minecraft.item.ItemBase;
import net.minecraft.item.ItemInstance;
import net.minecraft.level.Level;
import net.minecraft.util.io.CompoundTag;
import net.minecraft.util.io.ListTag;
import net.minecraft.util.maths.MathHelper;

import java.util.List;

public class EntityHorse extends AnimalBase
{

    public EntityHorse(Level world)
    {
        super(world);
        horseboolean = false;
        setSize(1.4F, 1.6F);
        health = 20;
        rideable = false;
        isjumping = false;
        tamed = false;
        texture = "/assets/mocreatures/stationapi/textures/mob/horseb.png";
        typeint = 0;
        HorseSpeed = 0.80000000000000004D;
        HorseJump = 0.40000000000000002D;
        temper = 100;
        typechosen = false;
        fwingb = 0.0F;
        fwingc = 0.0F;
        fwingh = 1.0F;
        localstack = new ItemInstance[27];
        localstackx54 = new ItemInstance[54];
        maxhealth = 20;
        hasreproduced = false;
        gestationtime = 0;
        eatenpumpkin = false;
        bred = false;
        nightmareInt = 0;
        immuneToFire = false;
        adult = true;
        b = 0.35F;
        roped = false;
        roper = null;
        field_1045 = true;
        name = "";
        displayname = false;
        isHorsePublic = false;
        horseOwner = "";
    }

    public void remove()
    {
        if((tamed || bred) && health > 0)
        {
            return;
        } else
        {
            super.remove();
            return;
        }
    }

    public void setType(int i)
    {
        typeint = i;
        typechosen = false;
        chooseType();
    }

    public void chooseType()
    {
        int i = mocr.mocreaturesGlass.animals.pegasusChanceS;
        if(typeint == 0)
        {
            if(rand.nextInt(5) == 0)
            {
                adult = false;
            }
            int j = rand.nextInt(100);
            if(j <= 51 - i)
            {
                typeint = 1;
            } else
            if(j <= 86 - i)
            {
                typeint = 2;
            } else
            if(j <= 95 - i)
            {
                typeint = 3;
            } else
            if(j <= 99 - i)
            {
                typeint = 4;
            } else
            {
                typeint = 5;
            }
        }
        if(!typechosen)
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
                immuneToFire = true;
            } else
            if(typeint == 8)
            {
                HorseSpeed = 1.3D;
                temper = 800;
                texture = "/assets/mocreatures/stationapi/textures/mob/horsebpb.png";
                maxhealth = 50;
                immuneToFire = true;
            }
            health = maxhealth;
        }
        typechosen = true;
    }

    public void Riding()
    {
        if(passenger != null && (passenger instanceof PlayerBase))
        {
            PlayerBase entityplayer = (PlayerBase)passenger;
            List list = level.getEntities(this, boundingBox.expand(1.0D, 0.0D, 1.0D));
            if(list != null)
            {
                for(int i = 0; i < list.size(); i++)
                {
                    EntityBase entity = (EntityBase)list.get(i);
                    if(entity.removed)
                    {
                        continue;
                    }
                    entity.onPlayerCollision(entityplayer);
                    if(!(entity instanceof MonsterBase))
                    {
                        continue;
                    }
                    float f = distanceTo(entity);
                    if(f < 2.0F && rand.nextInt(10) == 0)
                    {
                        damage(entity, ((MonsterBaseAccesor)entity).getAttackDamage());
                    }
                }

            }
            if(entityplayer.method_1373())
            {
                entityplayer.startRiding(null);
            }
        }
    }

    public void updateDespawnCounter()
    {
        if(rand.nextInt(300) == 0 && health < maxhealth && deathTime == 0)
        {
            health++;
        }
        Riding();
        if(typeint == 5 || typeint == 8)
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
        super.updateDespawnCounter();
        if(typeint == 7 && passenger != null && nightmareInt > 0 && rand.nextInt(2) == 0)
        {
            NightmareEffect();
        }
        if(!adult && rand.nextInt(200) == 0)
        {
            b += 0.01F;
            if(b >= 1.0F)
            {
                adult = true;
            }
        }
        if(!ReadyforParenting(this))
        {
            return;
        }
        int i = 0;
        List list = level.getEntities(this, boundingBox.expand(8D, 3D, 8D));
        for(int j = 0; j < list.size(); j++)
        {
            EntityBase entity = (EntityBase)list.get(j);
            if(entity instanceof EntityHorse)
            {
                i++;
            }
        }

        if(i > 1)
        {
            return;
        }
        List list1 = level.getEntities(this, boundingBox.expand(4D, 2D, 4D));
        for(int k = 0; k < list.size(); k++)
        {
            EntityBase entity1 = (EntityBase)list1.get(k);
            if(!(entity1 instanceof EntityHorse) || entity1 == this)
            {
                continue;
            }
            EntityHorse entityhorse = (EntityHorse)entity1;
            if(!ReadyforParenting(this) || !ReadyforParenting(entityhorse))
            {
                continue;
            }
            if(rand.nextInt(100) == 0)
            {
                gestationtime++;
            }
            if(gestationtime <= 50)
            {
                continue;
            }
            EntityHorse entityhorse1 = new EntityHorse(level);
            entityhorse1.setPosition(x, y, z);
            level.spawnEntity(entityhorse1);
            level.playSound(this, "mob.chickenplop", 1.0F, (rand.nextFloat() - rand.nextFloat()) * 0.2F + 1.0F);
            eatenpumpkin = false;
            if(!(mocr.mocreaturesGlass.animals.easybreeding))
            {
                hasreproduced = true;
            }
            entityhorse.eatenpumpkin = false;
            gestationtime = 0;
            entityhorse.gestationtime = 0;
            int l = HorseGenetics(this, entityhorse);
            entityhorse1.bred = true;
            entityhorse1.adult = false;
            entityhorse1.setType(l);
            break;
        }

    }

    protected void tickHandSwing()
    {
        if(!field_1026 && passenger == null)
        {
            super.tickHandSwing();
        }
        if(tamed && vehicle == null && roper != null)
        {
            float f = roper.distanceTo(this);
            if(f > 5F)
            {
                method_429(roper, f);
            }
        }
    }

    private void method_429(EntityBase entity, float f)
    {
        class_61 pathentity = level.method_192(this, entity, 16F);
        if(pathentity == null && f > 12F)
        {
            int i = MathHelper.floor(entity.x) - 2;
            int j = MathHelper.floor(entity.z) - 2;
            int k = MathHelper.floor(entity.boundingBox.minY);
            for(int l = 0; l <= 4; l++)
            {
                for(int i1 = 0; i1 <= 4; i1++)
                {
                    if((l < 1 || i1 < 1 || l > 3 || i1 > 3) && level.canSuffocate(i + l, k - 1, j + i1) && !level.canSuffocate(i + l, k, j + i1) && !level.canSuffocate(i + l, k + 1, j + i1))
                    {
                        setPositionAndAngles((float)(i + l) + 0.5F, k, (float)(j + i1) + 0.5F, yaw, pitch);
                        return;
                    }
                }

            }

        } else
        {
            setTarget(pathentity);
        }
    }

    private void NightmareEffect()
    {
        int i = MathHelper.floor(x);
        int j = MathHelper.floor(boundingBox.minY);
        int k = MathHelper.floor(z);
        level.setTile(i - 1, j, k - 1, BlockBase.FIRE.id);
        PlayerBase entityplayer = (PlayerBase)passenger;
        if(entityplayer != null && entityplayer.fire > 0)
        {
            entityplayer.fire = 0;
        }
        nightmareInt--;
    }

    public boolean ReadyforParenting(EntityHorse entityhorse)
    {
        return entityhorse.passenger == null && entityhorse.vehicle == null && entityhorse.tamed && entityhorse.eatenpumpkin && !entityhorse.hasreproduced && entityhorse.adult;
    }

    private int HorseGenetics(EntityHorse entityhorse, EntityHorse entityhorse1)
    {
        if(entityhorse.typeint == entityhorse1.typeint)
        {
            return entityhorse.typeint;
        }
        int i = entityhorse.typeint + entityhorse1.typeint;
        boolean flag = mocr.mocreaturesGlass.animals.easybreeding;
        boolean flag1 = rand.nextInt(3) == 0;
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

    public boolean damage(EntityBase entity, int i)
    {
        if(passenger != null && entity == passenger)
        {
            return false;
        }
        if(entity instanceof Wolf)
        {
            WalkingBase entitycreature = (WalkingBase)entity;
            ((WalkingBaseAccesor)entitycreature).setEntity(null);

            return false;
        } else
        {
            return super.damage(entity, i);
        }
    }

    public void travel(float f, float f1)
    {
        if(method_1393())
        {
            if(passenger != null)
            {
                velocityX += passenger.velocityX * (HorseSpeed / 2D);
                velocityZ += passenger.velocityZ * (HorseSpeed / 2D);
                PlayerBase entityplayer = (PlayerBase)passenger;
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
                if(rand.nextInt(20) == 0)
                {
                    yaw = passenger.yaw;
                }
                setRotation(yaw, pitch);
                if(!tamed)
                {
                    passenger = null;
                }
            }
            double d = y;
            movementInputToVelocity(f, f1, 0.02F);
            move(velocityX, velocityY, velocityZ);
            velocityX *= 0.80000001192092896D;
            velocityY *= 0.80000001192092896D;
            velocityZ *= 0.80000001192092896D;
            velocityY -= 0.02D;
            if(field_1624 && method_1344(velocityX, ((velocityY + 0.60000002384185791D) - y) + d, velocityZ))
            {
                velocityY = 0.30000001192092901D;
            }
        } else
        if(method_1335())
        {
            if(passenger != null)
            {
                velocityX += passenger.velocityX * (HorseSpeed / 2D);
                velocityZ += passenger.velocityZ * (HorseSpeed / 2D);
                PlayerBase entityplayer1 = (PlayerBase)passenger;
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
                if(rand.nextInt(20) == 0)
                {
                    yaw = passenger.yaw;
                }
                setRotation(yaw, pitch);
                if(!tamed)
                {
                    passenger = null;
                }
            }
            double d1 = y;
            movementInputToVelocity(f, f1, 0.02F);
            move(velocityX, velocityY, velocityZ);
            velocityX *= 0.5D;
            velocityY *= 0.5D;
            velocityZ *= 0.5D;
            velocityY -= 0.02D;
            if(field_1624 && method_1344(velocityX, ((velocityY + 0.60000002384185791D) - y) + d1, velocityZ))
            {
                velocityY = 0.30000001192092901D;
            }
        } else
        {
            float f2 = 0.91F;
            if(onGround)
            {
                f2 = 0.5460001F;
                int i = level.getTileId(MathHelper.floor(x), MathHelper.floor(boundingBox.minY) - 1, MathHelper.floor(z));
                if(i > 0)
                {
                    f2 = BlockBase.BY_ID[i].slipperiness * 0.91F;
                }
            }
            float f3 = 0.162771F / (f2 * f2 * f2);
            movementInputToVelocity(f, f1, onGround ? 0.1F * f3 : 0.02F);
            f2 = 0.91F;
            if(onGround)
            {
                f2 = 0.5460001F;
                int j = level.getTileId(MathHelper.floor(x), MathHelper.floor(boundingBox.minY) - 1, MathHelper.floor(z));
                if(j > 0)
                {
                    f2 = BlockBase.BY_ID[j].slipperiness * 0.91F;
                }
            }
            if(method_932())
            {
                fallDistance = 0.0F;
                if(velocityY < -0.14999999999999999D)
                {
                    velocityY = -0.14999999999999999D;
                }
            }
            if(passenger != null && !tamed)
            {
                if(rand.nextInt(5) == 0 && !isjumping)
                {
                    velocityY += 0.40000000000000002D;
                    isjumping = true;
                }
                if(rand.nextInt(10) == 0)
                {
                    velocityX += rand.nextDouble() / 30D;
                    velocityZ += rand.nextDouble() / 10D;
                }
                move(velocityX, velocityY, velocityZ);
                if(rand.nextInt(50) == 0)
                {
                    level.playSound(this, "mocreatures:horsemad", 1.0F, 1.0F + (rand.nextFloat() - rand.nextFloat()) * 0.2F);
                    passenger.velocityY += 0.90000000000000002D;
                    passenger.velocityZ -= 0.29999999999999999D;
                    passenger = null;
                }
                if(onGround)
                {
                    isjumping = false;
                }
                if(rand.nextInt(temper * 8) == 0)
                {
                    tamed = true;
                    PlayerBase milosc = (PlayerBase)passenger;
                    horseOwner = milosc.name;
                    setName(this);
                }
            }
            if(passenger != null && tamed)
            {
                boundingBox.maxY = passenger.boundingBox.maxY;
                velocityX += passenger.velocityX * HorseSpeed;
                velocityZ += passenger.velocityZ * HorseSpeed;
                PlayerBase entityplayer2 = (PlayerBase)passenger;
                if(((LivingAccesor)entityplayer2).getJumping() && !isjumping)
                {
                    velocityY += HorseJump;
                    isjumping = true;
                }
                if(((LivingAccesor)entityplayer2).getJumping() && (typeint == 5 || typeint == 8))
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
            if(field_1624 && method_932())
            {
                velocityY = 0.20000000000000001D;
            }
            if((typeint == 5 || typeint == 8) && passenger != null && tamed)
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
        field_1048 = limbDistance;
        double d2 = x - prevX;
        double d3 = z - prevZ;
        float f4 = MathHelper.sqrt(d2 * d2 + d3 * d3) * 4F;
        if(f4 > 1.0F)
        {
            f4 = 1.0F;
        }
        limbDistance += (f4 - limbDistance) * 0.4F;
        field_1050 += limbDistance;
    }

    protected void handleFallDamage(float f)
    {
        int i = (int)Math.ceil(f - 3F);
        if(i > 0 && typeint != 5 && typeint != 8)
        {
            if(typeint >= 3)
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
            if(typeint == 5 || typeint == 8)
            {
                return;
            }
            int j = level.getTileId(MathHelper.floor(x), MathHelper.floor(y - 0.20000000298023221D - (double)prevPitch), MathHelper.floor(z));
            if(j > 0)
            {
                BlockSounds stepsound = BlockBase.BY_ID[j].sounds;
                level.playSound(this, stepsound.getWalkSound(), stepsound.getVolume() * 0.5F, stepsound.getPitch() * 0.75F);
            }
        }
    }

    protected float getSoundVolume()
    {
        return 0.4F;
    }

    public boolean method_1356()
    {
        return passenger == null;
    }

    public boolean interact(PlayerBase entityplayer)
    {
        ItemInstance itemstack = entityplayer.inventory.getHeldItem();
        if(tamed && !isHorsePublic && !entityplayer.name.equals(horseOwner))
        {
            return false;
        }
        if(itemstack != null && tamed && entityplayer.name.equals(horseOwner) && (itemstack.itemId == ItemBase.goldSword.id || itemstack.itemId == ItemBase.stoneSword.id || itemstack.itemId == ItemBase.woodSword.id || itemstack.itemId == ItemBase.ironSword.id || itemstack.itemId == ItemBase.diamondSword.id))
        {
            if(isHorsePublic == true){
                isHorsePublic = false;
                for(int var3 = 0; var3 < 7; ++var3) {
                    double var4 = this.rand.nextGaussian() * 0.02D;
                    double var6 = this.rand.nextGaussian() * 0.02D;
                    double var8 = this.rand.nextGaussian() * 0.02D;
                    level.addParticle("heart", this.x + (double) (this.rand.nextFloat() * this.width * 2.0F) - (double) this.width, this.y + 0.5D + (double) (this.rand.nextFloat() * this.height), this.z + (double) (this.rand.nextFloat() * this.width * 2.0F) - (double) this.width, var4, var6, var8);
                }
            }else{
                isHorsePublic = true;
                for(int var3 = 0; var3 < 7; ++var3) {
                    double var4 = this.rand.nextGaussian() * 0.02D;
                    double var6 = this.rand.nextGaussian() * 0.02D;
                    double var8 = this.rand.nextGaussian() * 0.02D;
                    level.addParticle("flame", this.x + (double) (this.rand.nextFloat() * this.width * 2.0F) - (double) this.width, this.y + 0.5D + (double) (this.rand.nextFloat() * this.height), this.z + (double) (this.rand.nextFloat() * this.width * 2.0F) - (double) this.width, var4, var6, var8);
                }
            }
            return true;
        }
        if(itemstack != null && itemstack.itemId == ItemBase.wheat.id)
        {
            if(--itemstack.count == 0)
            {
                entityplayer.inventory.setInventoryItem(entityplayer.inventory.selectedHotbarSlot, null);
            }
            if((temper -= 25) < 25)
            {
                temper = 25;
            }
            if((health += 5) > maxhealth)
            {
                health = maxhealth;
            }
            level.playSound(this, "mocreatures:eating", 1.0F, 1.0F + (rand.nextFloat() - rand.nextFloat()) * 0.2F);
            if(!adult && b < 1.0F)
            {
                b += 0.01F;
            }
            return true;
        }
        if(itemstack != null && itemstack.itemId == mod_mocreatures.sugarlump.id)
        {
            if(--itemstack.count == 0)
            {
                entityplayer.inventory.setInventoryItem(entityplayer.inventory.selectedHotbarSlot, null);
            }
            if((temper -= 50) < 25)
            {
                temper = 25;
            }
            if((health += 10) > maxhealth)
            {
                health = maxhealth;
            }
            level.playSound(this, "mocreatures:eating", 1.0F, 1.0F + (rand.nextFloat() - rand.nextFloat()) * 0.2F);
            if(!adult && b < 1.0F)
            {
                b += 0.02F;
            }
            return true;
        }
        if(itemstack != null && itemstack.itemId == ItemBase.bread.id)
        {
            if(--itemstack.count == 0)
            {
                entityplayer.inventory.setInventoryItem(entityplayer.inventory.selectedHotbarSlot, null);
            }
            if((temper -= 100) < 25)
            {
                temper = 25;
            }
            if((health += 20) > maxhealth)
            {
                health = maxhealth;
            }
            level.playSound(this, "mocreatures:eating", 1.0F, 1.0F + (rand.nextFloat() - rand.nextFloat()) * 0.2F);
            if(!adult && b < 1.0F)
            {
                b += 0.03F;
            }
            return true;
        }
        if(itemstack != null && (((mocr.mocreaturesGlass.balancesettings.balance_drop) && itemstack.itemId == mod_mocreatures.greenapple.id)  ||  (((itemstack.itemId == ItemBase.apple.id)||(itemstack.itemId == ItemBase.goldenApple.id)) && !mocr.mocreaturesGlass.balancesettings.balance_drop)))
        {
            if(--itemstack.count == 0)
            {
                entityplayer.inventory.setInventoryItem(entityplayer.inventory.selectedHotbarSlot, null);
            }
            tamed = true;
            health = maxhealth;
            level.playSound(this, "mocreatures:eating", 1.0F, 1.0F + (rand.nextFloat() - rand.nextFloat()) * 0.2F);
            if(!adult && b < 1.0F)
            {
                b += 0.05F;
            }
            horseOwner = entityplayer.name;
            setName(this);
            return true;
        }
        if(itemstack != null && tamed && itemstack.itemId == BlockBase.CHEST.id && (typeint == 6 || typeint == 8))
        {
            if(chestedhorse)
            {
                return false;
            }
            if(--itemstack.count == 0)
            {
                entityplayer.inventory.setInventoryItem(entityplayer.inventory.selectedHotbarSlot, null);
            }
            chestedhorse = true;
            level.playSound(this, "mob.chickenplop", 1.0F, (rand.nextFloat() - rand.nextFloat()) * 0.2F + 1.0F);
            return true;
        }
        if(itemstack != null && tamed && itemstack.itemId == mod_mocreatures.haystack.id)
        {
            if(--itemstack.count == 0)
            {
                entityplayer.inventory.setInventoryItem(entityplayer.inventory.selectedHotbarSlot, null);
            }
            eatinghaystack = true;
            level.playSound(this, "mocreatures:eating", 1.0F, 1.0F + (rand.nextFloat() - rand.nextFloat()) * 0.2F);
            health = maxhealth;
            return true;
        }
        if(itemstack != null && (itemstack.itemId == ItemBase.stoneShovel.id || itemstack.itemId == BlockBase.TORCH.id) && chestedhorse)
        {
            if(typeint == 8)
            {
                localhorsechest = new AnimalChest(localstack, "Dark Pegasus chest", localstack.length);
                entityplayer.openChestScreen(localhorsechest);
                return true;
            }
            if(typeint == 6)
            {
                localhorsechestx54 = new AnimalChest(localstackx54, "Pack Horse chest", localstackx54.length);
                entityplayer.openChestScreen(localhorsechestx54);
                return true;
            }
        }
        if(itemstack != null && (itemstack.itemId == BlockBase.PUMPKIN.id || itemstack.itemId == ItemBase.mushroomStew.id || itemstack.itemId == ItemBase.cake.id))
        {
            if(hasreproduced || !adult)
            {
                return false;
            }
            if(itemstack.itemId == ItemBase.mushroomStew.id)
            {
                itemstack.count--;
                entityplayer.inventory.addStack(new ItemInstance(ItemBase.bowl));
            } else
            if(--itemstack.count == 0)
            {
                entityplayer.inventory.setInventoryItem(entityplayer.inventory.selectedHotbarSlot, null);
            }
            eatenpumpkin = true;
            health = maxhealth;
            level.playSound(this, "mocreatures:eating", 1.0F, 1.0F + (rand.nextFloat() - rand.nextFloat()) * 0.2F);
            return true;
        }
        if(itemstack != null && tamed && itemstack.itemId == ItemBase.redstoneDust.id && typeint == 7)
        {
            if(nightmareInt > 500)
            {
                return false;
            }
            if(--itemstack.count == 0)
            {
                entityplayer.inventory.setInventoryItem(entityplayer.inventory.selectedHotbarSlot, null);
            }
            nightmareInt = 500;
            level.playSound(this, "mocreatures:eating", 1.0F, 1.0F + (rand.nextFloat() - rand.nextFloat()) * 0.2F);
            return true;
        }
        if(itemstack != null && itemstack.itemId == mod_mocreatures.whip.id && tamed && passenger == null)
        {
            eatinghaystack = !eatinghaystack;
            return true;
        }
        if(itemstack != null && passenger == null && roper == null && tamed && itemstack.itemId == mod_mocreatures.rope.id)
        {
            if(--itemstack.count == 0)
            {
                entityplayer.inventory.setInventoryItem(entityplayer.inventory.selectedHotbarSlot, null);
            }
            level.playSound(this, "mocreatures:roping", 1.0F, 1.0F + (rand.nextFloat() - rand.nextFloat()) * 0.2F);
            roper = entityplayer;
            return true;
        }
        if(roper != null && tamed)
        {
            entityplayer.inventory.addStack(new ItemInstance(mod_mocreatures.rope));
            level.playSound(this, "mocreatures:roping", 1.0F, 1.0F + (rand.nextFloat() - rand.nextFloat()) * 0.2F);
            roper = null;
            return true;
        }
        if(itemstack != null && tamed && (itemstack.itemId == mod_mocreatures.medallion.id || itemstack.itemId == ItemBase.book.id))
        {
            setName(this);
            return true;
        }
        if(itemstack != null && tamed && (itemstack.itemId == ItemBase.diamondPickaxe.id || itemstack.itemId == ItemBase.woodPickaxe.id || itemstack.itemId == ItemBase.stonePickaxe.id || itemstack.itemId == ItemBase.ironPickaxe.id || itemstack.itemId == ItemBase.goldPickaxe.id))
        {
            displayname = !displayname;
            return true;
        }
        if(rideable && adult)
        {
            entityplayer.yaw = yaw;
            entityplayer.pitch = pitch;
            eatinghaystack = false;
            entityplayer.startRiding(this);
            gestationtime = 0;
            if(typeint == 8)
            {
                entityplayer.incrementStat(mod_mocreatures.WilfFlyingWest);
            }
            return true;
        } else
        {
            return false;
        }
    }

    public void onKilledBy(EntityBase entity)
    {
        if(field_1024 >= 0 && entity != null)
        {
            entity.onKilledOther(this, field_1024);
        }
        if(entity != null)
        {
            entity.handleKilledEntity(this);
        }
        field_1045 = true;
        if(!level.isServerSide)
        {
            getDrops();
        }
        level.method_279(this, 3F);
        if(chestedhorse && (typeint == 6 || typeint == 8))
        {
            int i = MathHelper.floor(x);
            int j = MathHelper.floor(boundingBox.minY);
            int k = MathHelper.floor(z);
            HorseRemoval(level, i, j, k);
        }
    }

    public void HorseRemoval(Level world, int i, int j, int k)
    {
        if(localstack != null)
        {
            localhorsechest = new AnimalChest(localstack, "HorseChest", localstack.length);
            label0:
            for(int l = 0; l < localhorsechest.getInventorySize(); l++)
            {
                ItemInstance itemstack = localhorsechest.getInventoryItem(l);
                if(itemstack == null)
                {
                    continue;
                }
                float f = rand.nextFloat() * 0.8F + 0.1F;
                float f1 = rand.nextFloat() * 0.8F + 0.1F;
                float f2 = rand.nextFloat() * 0.8F + 0.1F;
                do
                {
                    if(itemstack.count <= 0)
                    {
                        continue label0;
                    }
                    int i1 = rand.nextInt(21) + 10;
                    if(i1 > itemstack.count)
                    {
                        i1 = itemstack.count;
                    }
                    itemstack.count -= i1;
                    Item entityitem = new Item(level, (float)i + f, (float)j + f1, (float)k + f2, new ItemInstance(itemstack.itemId, i1, itemstack.getDamage()));
                    float f3 = 0.05F;
                    entityitem.velocityX = (float)rand.nextGaussian() * f3;
                    entityitem.velocityY = (float)rand.nextGaussian() * f3 + 0.2F;
                    entityitem.velocityZ = (float)rand.nextGaussian() * f3;
                    level.spawnEntity(entityitem);
                } while(true);
            }
        }
        if(localstackx54 != null)
        {
            localhorsechestx54 = new AnimalChest(localstackx54, "HorseChest", localstackx54.length);
            label0:
            for(int l = 0; l < localhorsechestx54.getInventorySize(); l++)
            {
                ItemInstance itemstack = localhorsechestx54.getInventoryItem(l);
                if(itemstack == null)
                {
                    continue;
                }
                float f = rand.nextFloat() * 0.8F + 0.1F;
                float f1 = rand.nextFloat() * 0.8F + 0.1F;
                float f2 = rand.nextFloat() * 0.8F + 0.1F;
                do
                {
                    if(itemstack.count <= 0)
                    {
                        continue label0;
                    }
                    int i1 = rand.nextInt(21) + 10;
                    if(i1 > itemstack.count)
                    {
                        i1 = itemstack.count;
                    }
                    itemstack.count -= i1;
                    Item entityitem = new Item(level, (float)i + f, (float)j + f1, (float)k + f2, new ItemInstance(itemstack.itemId, i1, itemstack.getDamage()));
                    float f3 = 0.05F;
                    entityitem.velocityX = (float)rand.nextGaussian() * f3;
                    entityitem.velocityY = (float)rand.nextGaussian() * f3 + 0.2F;
                    entityitem.velocityZ = (float)rand.nextGaussian() * f3;
                    level.spawnEntity(entityitem);
                } while(true);
            }
        }
    }

    public void writeCustomDataToTag(CompoundTag nbttagcompound)
    {
        super.writeCustomDataToTag(nbttagcompound);
        nbttagcompound.put("Saddle", rideable);
        nbttagcompound.put("EatingHaystack", eatinghaystack);
        nbttagcompound.put("Tamed", tamed);
        nbttagcompound.put("HorseBoolean", horseboolean);
        nbttagcompound.put("TypeInt", typeint);
        nbttagcompound.put("ChestedHorse", chestedhorse);
        nbttagcompound.put("HasReproduced", hasreproduced);
        nbttagcompound.put("Bred", bred);
        nbttagcompound.put("Adult", adult);
        nbttagcompound.put("Age", b);
        nbttagcompound.put("Name", name);
        nbttagcompound.put("DisplayName", displayname);
        nbttagcompound.put("GestationTime", gestationtime);
        nbttagcompound.put("EatenPumpkin", eatenpumpkin);
        nbttagcompound.put("PublicHorse", isHorsePublic);
        nbttagcompound.put("HorseOwner", horseOwner);
        if(typeint == 8)
        {
            ListTag nbttaglist = new ListTag();
            for(int i = 0; i < localstack.length; i++)
            {
                if(localstack[i] != null)
                {
                    CompoundTag nbttagcompound1 = new CompoundTag();
                    nbttagcompound1.put("Slot", (byte)i);
                    localstack[i].toTag(nbttagcompound1);
                    nbttaglist.add(nbttagcompound1);
                }
            }

            nbttagcompound.put("Items", nbttaglist);
        }

        if(typeint == 6)
        {
            ListTag nbttaglist = new ListTag();
            for(int i = 0; i < localstackx54.length; i++)
            {
                if(localstackx54[i] != null)
                {
                    CompoundTag nbttagcompound1 = new CompoundTag();
                    nbttagcompound1.put("Slot", (byte)i);
                    localstackx54[i].toTag(nbttagcompound1);
                    nbttaglist.add(nbttagcompound1);
                }
            }

            nbttagcompound.put("Items", nbttaglist);
        }
    }

    public void readCustomDataFromTag(CompoundTag nbttagcompound)
    {
        super.readCustomDataFromTag(nbttagcompound);
        rideable = nbttagcompound.getBoolean("Saddle");
        tamed = nbttagcompound.getBoolean("Tamed");
        eatinghaystack = nbttagcompound.getBoolean("EatingHaystack");
        bred = nbttagcompound.getBoolean("Bred");
        adult = nbttagcompound.getBoolean("Adult");
        horseboolean = nbttagcompound.getBoolean("HorseBoolean");
        chestedhorse = nbttagcompound.getBoolean("ChestedHorse");
        hasreproduced = nbttagcompound.getBoolean("HasReproduced");
        typeint = nbttagcompound.getInt("TypeInt");
        b = nbttagcompound.getFloat("Age");
        name = nbttagcompound.getString("Name");
        displayname = nbttagcompound.getBoolean("DisplayName");
        gestationtime = nbttagcompound.getInt("GestationTime");
        eatenpumpkin = nbttagcompound.getBoolean("EatenPumpkin");
        isHorsePublic = nbttagcompound.getBoolean("PublicHorse");
        horseOwner = nbttagcompound.getString("HorseOwner");
        if(typeint == 8)
        {
            ListTag nbttaglist = nbttagcompound.getListTag("Items");
            localstack = new ItemInstance[27];
            for(int i = 0; i < nbttaglist.size(); i++)
            {
                CompoundTag nbttagcompound1 = (CompoundTag)nbttaglist.get(i);
                int j = nbttagcompound1.getByte("Slot") & 0xff;
                if(j >= 0 && j < localstack.length)
                {
                    localstack[j] = new ItemInstance(nbttagcompound1);
                }
            }

        }

        if(typeint == 6)
        {
            ListTag nbttaglist = nbttagcompound.getListTag("Items");
            localstackx54 = new ItemInstance[54];
            for(int i = 0; i < nbttaglist.size(); i++)
            {
                CompoundTag nbttagcompound1 = (CompoundTag)nbttaglist.get(i);
                int j = nbttagcompound1.getByte("Slot") & 0xff;
                if(j >= 0 && j < localstackx54.length)
                {
                    localstackx54[j] = new ItemInstance(nbttagcompound1);
                }
            }

        }
    }

    protected boolean method_640()
    {
        return eatinghaystack || passenger != null;
    }

    protected String getAmbientSound()
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

    protected void getDrops()
    {
        int i = rand.nextInt(3);
        for(int j = 0; j < i; j++)
        {
            dropItem(new ItemInstance(getMobDrops(), 1, 0), 0.0F);
        }

        if(rideable) {
            int k = rand.nextInt(2);
            for (int j = 0; j < k; j++) {
                dropItem(new ItemInstance(mod_mocreatures.horsesaddle, 1, 0), 0.0F);
            }
        }
    }

    protected int getMobDrops()
    {
        return ItemBase.leather.id;
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
        mc.openScreen(new MoCGUI(entityhorse, entityhorse.name));
    }

    public static Minecraft mc = Minecraft.class.cast(FabricLoader.getInstance().getGameInstance());
    mod_mocreatures mocr = new mod_mocreatures();
    public String horseOwner;
    public boolean isHorsePublic;
    private int nightmareInt;
    private boolean bred;
    public boolean eatenpumpkin;
    private int gestationtime;
    public boolean hasreproduced;
    public int maxhealth;
    private int temper;
    private double HorseSpeed;
    private double HorseJump;
    public int typeint;
    public boolean typechosen;
    public boolean tamed;
    public boolean horseboolean;
    public boolean rideable;
    public boolean isjumping;
    public float fwingb;
    public float fwingc;
    public float fwingd;
    public float fwinge;
    public float fwingh;
    public boolean chestedhorse;
    private InventoryBase localhorsechest;
    public ItemInstance localstack[];
    private InventoryBase localhorsechestx54;
    public ItemInstance localstackx54[];
    public boolean eatinghaystack;
    public float b;
    public boolean adult;
    public boolean roped;
    public Living roper;
    public String name;
    public boolean displayname;
}
