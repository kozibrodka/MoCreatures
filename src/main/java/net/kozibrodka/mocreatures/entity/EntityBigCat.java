package net.kozibrodka.mocreatures.entity;


import net.fabricmc.loader.api.FabricLoader;
import net.kozibrodka.mocreatures.events.mod_mocreatures;
import net.kozibrodka.mocreatures.mixin.WalkingBaseAccesor;
import net.kozibrodka.mocreatures.mocreatures.MoCGUI;
import net.minecraft.block.BlockBase;
import net.minecraft.block.material.Material;
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
import net.minecraft.item.tool.Sword;
import net.minecraft.level.Level;
import net.minecraft.util.io.CompoundTag;
import net.minecraft.util.maths.Box;
import net.minecraft.util.maths.MathHelper;
import net.modificationstation.stationapi.api.gui.screen.container.GuiHelper;
import net.modificationstation.stationapi.api.registry.Identifier;
import net.modificationstation.stationapi.api.server.entity.MobSpawnDataProvider;

import java.util.List;


public class EntityBigCat extends AnimalBase implements MobSpawnDataProvider
{

    public EntityBigCat(Level world)
    {
        super(world);
        lionboolean = false;
        texture = "/assets/mocreatures/stationapi/textures/mob/lionf.png";
        edad = 0.35F;
        setSize(0.9F, 1.3F);
        health = 25;
        force = 1;
        attackRange = 1.0D;
        adult = true;
        hungry = true;
        tamed = false;
        sitting = false;
        name = "";
        displayname = false;
        maxhealth = 25;
        tigerOwner = "";
        protectFromPlayers = true;
    }

    protected void initDataTracker()
    {
        super.initDataTracker();
        dataTracker.startTracking(16, Byte.valueOf((byte)0)); //Type
        dataTracker.startTracking(17, Byte.valueOf((byte)0)); //Age
        dataTracker.startTracking(18, Byte.valueOf((byte)0)); //Adult

        dataTracker.startTracking(20, Byte.valueOf((byte)0)); //Tamed
        dataTracker.startTracking(21, Byte.valueOf((byte)0)); //Sitting
        dataTracker.startTracking(22, Byte.valueOf((byte)0)); //Hungry
        dataTracker.startTracking(23, Byte.valueOf((byte)0)); //Render Health
    }

    public void setType(int i)
    {
        typeint = i;
        typechosen = false;
        if(rand.nextInt(4) == 0)
        {
            adult = false;
            field_1045 = true;
        }
        chooseType();
    }

    public void chooseType()
    {
        if(typeint == 0)
        {
            if(rand.nextInt(4) == 0)
            {
                adult = false;
                field_1045 = true;
            }
            int i = rand.nextInt(100);
            if(i <= 5)
            {
                typeint = 1;
            } else
            if(i <= 25)
            {
                typeint = 2;
            } else
            if(i <= 50)
            {
                typeint = 3;
            } else
            if(i <= 70)
            {
                typeint = 4;
            } else
            if(i <= 75)
            {
                typeint = 7;
            } else
            {
                typeint = 5;
            }
        }
        if(!typechosen)
        {
            if(typeint == 1)
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
            if(typeint == 2)
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
            if(typeint == 3)
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
            if(typeint == 4)
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
            if(typeint == 5)
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
            if(typeint == 6)
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
            if(typeint == 7)
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
        typechosen = true;
    }

    public void updateDespawnCounter()
    {
        super.updateDespawnCounter();
        if(!adult && rand.nextInt(250) == 0)
        {
            edad += 0.01F;
            if(edad >= 1.0F)
            {
                adult = true;
                field_1045 = false;
            }
        }
        if(!hungry && !sitting && rand.nextInt(200) == 0)
        {
            hungry = true;
        }
        if(roper != null && rand.nextInt(20) == 0)
        {
            float f = roper.distanceTo(this);
            if(f > 8F && !sitting)
            {
                method_429(roper, f);
            }
            if((f > 18F) & sitting)
            {
                roper = null;
            }
        }
        if(deathTime == 0 && hungry && !sitting)
        {
            Item entityitem = getClosestItem(this, 12D, ItemBase.rawPorkchop.id, ItemBase.rawFish.id);
            if(entityitem != null)
            {
                MoveToNextEntity(entityitem);
                Item entityitem1 = getClosestItem(this, 2D, ItemBase.rawPorkchop.id, ItemBase.rawFish.id);
                if(rand.nextInt(80) == 0 && entityitem1 != null && deathTime == 0)
                {
                    entityitem1.remove();
                    if(health + 10 > maxhealth)
                    {
                        health = maxhealth;
                    }else{
                        health += 10;
                    }
                    if(!adult && edad < 0.8F)
                    {
                        eaten = true;
                    }
                    level.playSound(this, "mocreatures:eating", 1.0F, 1.0F + (rand.nextFloat() - rand.nextFloat()) * 0.2F);
                    hungry = false;
                }
            }
        }
    }

    protected boolean method_640()
    {
        return sitting;
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

    protected boolean MoveToNextEntity(EntityBase entity)
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

    public Item getClosestItem(EntityBase entity, double d, int i, int j)
    {
        double d1 = -1D;
        Item entityitem = null;
        List list = level.getEntities(this, boundingBox.expand(d, d, d));
        for(int k = 0; k < list.size(); k++)
        {
            EntityBase entity1 = (EntityBase)list.get(k);
            if(!(entity1 instanceof Item))
            {
                continue;
            }
            Item entityitem1 = (Item)entity1;
            if(entityitem1.item.itemId != i && j != 0 && entityitem1.item.itemId != j)
            {
                continue;
            }
            double d2 = entityitem1.squaredDistanceTo(entity.x, entity.y, entity.z);
            if((d < 0.0D || d2 < d * d) && (d1 == -1D || d2 < d1))
            {
                d1 = d2;
                entityitem = entityitem1;
            }
        }

        return entityitem;
    }
    protected void tickHandSwing(){
        if(this.entity instanceof Living){
            PlayerBase uciekinier = level.getClosestPlayerTo(this, 16D);
            if(uciekinier == null && entity.isAlive()){
                if(rand.nextInt(30) == 0)
                {
                    entity = null;
                }
            }
        }
        super.tickHandSwing();
    }

    protected EntityBase getAttackTarget()
    {
        if(roper != null)
        {
            return getMastersEnemy((PlayerBase)roper, 12D);
        }
        if(level.difficulty > 0)
        {
            PlayerBase entityplayer = level.getClosestPlayerTo(this, 12D);
            if(!tamed && entityplayer != null && adult && hungry)
            {
                if(typeint == 1 || typeint == 5 || typeint == 7)
                {
                    hungry = false;
                    return entityplayer;
                }
                if(rand.nextInt(30) == 0)
                {
                    hungry = false;
                    return entityplayer;
                }
            }
            if(rand.nextInt(80) == 0 && hungry)
            {
                Living entityliving = getClosestTarget(this, 10D);
                hungry = false;
                return entityliving;
            }
        }
        return null;
    }

    public Living getClosestTarget(EntityBase entity, double d)
    {
        double d1 = -1D;
        Living entityliving = null;
        List list = level.getEntities(this, boundingBox.expand(d, d, d));
        for(int i = 0; i < list.size(); i++)
        {
            EntityBase entity1 = (EntityBase)list.get(i);
            if(!(entity1 instanceof Living) || entity1 == entity || entity1 == entity.passenger || entity1 == entity.vehicle || (entity1 instanceof PlayerBase) || !adult && ((double)entity1.width > 0.5D || (double)entity1.height > 0.5D) || (entity1 instanceof EntityKittyBed) || (entity1 instanceof EntityLitterBox) || (entity1 instanceof MonsterBase) && (!tamed || !adult) || tamed && (entity1 instanceof EntityKitty) && ((EntityKitty)entity1).kittystate > 2 || (entity1 instanceof EntityHorse) && !mocr.mocreaturesGlass.huntercreatures.attackhorses || (entity1 instanceof EntityHorse) && tamed && ((EntityHorse) entity1).tamed || (entity1 instanceof EntityShark) && ((EntityShark)entity1).tamed && tamed || (entity1 instanceof EntityDolphin) && ((EntityDolphin)entity1).tamed && tamed || (entity1 instanceof Wolf) && !mocr.mocreaturesGlass.huntercreatures.attackwolves)
            {
                continue;
            }
            if(entity1 instanceof EntityBigCat)
            {
                if(!adult)
                {
                    continue;
                }
                EntityBigCat entitybigcat = (EntityBigCat)entity1;
                if(tamed && entitybigcat.tamed || entitybigcat.typeint == 7 || typeint != 2 && typeint == entitybigcat.typeint || typeint == 2 && entitybigcat.typeint == 1 || health < entitybigcat.health)
                {
                    continue;
                }
            }
            double d2 = entity1.squaredDistanceTo(entity.x, entity.y, entity.z);
            if((d < 0.0D || d2 < d * d) && (d1 == -1D || d2 < d1) && ((Living)entity1).method_928(entity))
            {
                d1 = d2;
                entityliving = (Living)entity1;
            }
        }

        return entityliving;
    }

    public WalkingBase getMastersEnemy(PlayerBase entityplayer, double d)
    {
        double d1 = -1D;
        WalkingBase entitycreature = null;
        List list = level.getEntities(entityplayer, boundingBox.expand(d, 4D, d));
        for(int i = 0; i < list.size(); i++)
        {
            EntityBase entity = (EntityBase)list.get(i);
            if(!(entity instanceof WalkingBase) || entity == this)
            {
                continue;
            }
            WalkingBase entitycreature1 = (WalkingBase)entity;
            if(entitycreature1 != null && ((WalkingBaseAccesor)entitycreature1).getEntity() == entityplayer)
            {
                return entitycreature1;
            }
        }

        return entitycreature;
    }

    public boolean damage(EntityBase entitybase, int i)
    {
        if(super.damage(entitybase, i))
        {
            if(passenger == entitybase || vehicle == entitybase)
            {
                return true;
            }
            if(tamed && entitybase instanceof PlayerBase)
            {
                PlayerBase gracz = (PlayerBase)entitybase;
                if(!gracz.name.equals(tigerOwner) && protectFromPlayers)
                {
                    entity = entitybase;
                }
            }
            if(entitybase != this && level.difficulty > 0 && !tamed)
            {
                entity = entitybase;
            }
            return true;
        } else
        {
            return false;
        }
    }

    protected void tryAttack(EntityBase entity, float f)
    {
        if(f > 2.0F && f < 6F && rand.nextInt(50) == 0)
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
            attackTime = 20;
            entity.damage(this, force);
            if(!(entity instanceof PlayerBase))
            {
                destroyDrops(this, 3D);
            }
        }
    }

    public void writeCustomDataToTag(CompoundTag nbttagcompound)
    {
        super.writeCustomDataToTag(nbttagcompound);
        nbttagcompound.put("LionBoolean", lionboolean);
        nbttagcompound.put("TypeInt", typeint);
        nbttagcompound.put("Adult", adult);
        nbttagcompound.put("Tamed", tamed);
        nbttagcompound.put("Sitting", sitting);
        nbttagcompound.put("Edad", edad);
        nbttagcompound.put("Name", name);
        nbttagcompound.put("DisplayName", displayname);
        nbttagcompound.put("TigerOwner", tigerOwner);
        nbttagcompound.put("ProtectFromPlayers", protectFromPlayers);
    }

    public void readCustomDataFromTag(CompoundTag nbttagcompound)
    {
        super.readCustomDataFromTag(nbttagcompound);
        lionboolean = nbttagcompound.getBoolean("LionBoolean");
        adult = nbttagcompound.getBoolean("Adult");
        typeint = nbttagcompound.getInt("TypeInt");
        edad = nbttagcompound.getFloat("Edad");
        tamed = nbttagcompound.getBoolean("Tamed");
        sitting = nbttagcompound.getBoolean("Sitting");
        name = nbttagcompound.getString("Name");
        displayname = nbttagcompound.getBoolean("DisplayName");
        tigerOwner = nbttagcompound.getString("TigerOwner");
        protectFromPlayers = nbttagcompound.getBoolean("ProtectFromPlayers");
    }

    public boolean interact(PlayerBase entityplayer)
    {
        ItemInstance itemstack = entityplayer.inventory.getHeldItem();
        if(itemstack !=null && mocr.mocreaturesGlass.balancesettings.balance_drop && entityplayer.name.equals(tigerOwner) && tamed && itemstack.itemId == mod_mocreatures.bigcatfood.id)
        {
            if(--itemstack.count == 0)
            {
                entityplayer.inventory.setInventoryItem(entityplayer.inventory.selectedHotbarSlot, null);
            }
            if(health + 15 > maxhealth)
            {
                health = maxhealth;
            }else{
                health += 15;
            }
            return true;
        }
        if(itemstack != null && tamed && entityplayer.name.equals(tigerOwner) && (itemstack.itemId == ItemBase.goldSword.id || itemstack.itemId == ItemBase.stoneSword.id || itemstack.itemId == ItemBase.woodSword.id || itemstack.itemId == ItemBase.ironSword.id || itemstack.itemId == ItemBase.diamondSword.id))
        {
            if(protectFromPlayers == true){
                protectFromPlayers = false;
                for(int var3 = 0; var3 < 7; ++var3) {
                    double var4 = this.rand.nextGaussian() * 0.02D;
                    double var6 = this.rand.nextGaussian() * 0.02D;
                    double var8 = this.rand.nextGaussian() * 0.02D;
                    level.addParticle("heart", this.x + (double) (this.rand.nextFloat() * this.width * 2.0F) - (double) this.width, this.y + 0.5D + (double) (this.rand.nextFloat() * this.height), this.z + (double) (this.rand.nextFloat() * this.width * 2.0F) - (double) this.width, var4, var6, var8);
                }
            }else{
                protectFromPlayers = true;
                for(int var3 = 0; var3 < 7; ++var3) {
                    double var4 = this.rand.nextGaussian() * 0.02D;
                    double var6 = this.rand.nextGaussian() * 0.02D;
                    double var8 = this.rand.nextGaussian() * 0.02D;
                    level.addParticle("flame", this.x + (double) (this.rand.nextFloat() * this.width * 2.0F) - (double) this.width, this.y + 0.5D + (double) (this.rand.nextFloat() * this.height), this.z + (double) (this.rand.nextFloat() * this.width * 2.0F) - (double) this.width, var4, var6, var8);
                }
            }
            return true;
        }
        if(itemstack != null && !tamed && eaten && itemstack.itemId == mod_mocreatures.medallion.id)
        {
            if(--itemstack.count == 0)
            {
                entityplayer.inventory.setInventoryItem(entityplayer.inventory.selectedHotbarSlot, null);
            }
            tamed = true;
            tigerOwner = entityplayer.name;
            setName(this);
            return true;
        }
        if(itemstack != null && entityplayer.name.equals(tigerOwner) && tamed && itemstack.itemId == mod_mocreatures.whip.id)
        {
            sitting = !sitting;
            return true;
        }
        if(itemstack != null  && entityplayer.name.equals(tigerOwner) &&  tamed && (itemstack.itemId == ItemBase.diamondPickaxe.id || itemstack.itemId == ItemBase.woodPickaxe.id || itemstack.itemId == ItemBase.stonePickaxe.id || itemstack.itemId == ItemBase.ironPickaxe.id || itemstack.itemId == ItemBase.goldPickaxe.id))
        {
            displayname = !displayname;
            return true;
        }
        if(itemstack != null && entityplayer.name.equals(tigerOwner) && tamed && (itemstack.itemId == mod_mocreatures.medallion.id || itemstack.itemId == ItemBase.book.id))
        {
            setName(this);
            return true;
        }
        if(itemstack != null && entityplayer.name.equals(tigerOwner) && passenger == null && roper == null && tamed && itemstack.itemId == mod_mocreatures.rope.id)
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
        } else
        {
            return false;
        }
    }

    protected String getAmbientSound()
    {
        if(adult)
        {
            return "mocreatures:liongrunt";
        } else
        {
            return "mocreatures:cubgrunt";
        }
    }

    protected String getHurtSound()
    {
        if(adult)
        {
            return "mocreatures:lionhurt";
        } else
        {
            return "mocreatures:cubhurt";
        }
    }

    protected String getDeathSound()
    {
        if(adult)
        {
            return "mocreatures:liondeath";
        } else
        {
            return "mocreatures:cubdying";
        }
    }

    protected int getMobDrops()
    {
        return mod_mocreatures.bigcatclaw.id;
    }

    public void destroyDrops(EntityBase entity, double d)
    {
        if(tamed)
        {
            return;
        }
        List list = level.getEntities(entity, entity.boundingBox.expand(d, d, d));
        for(int i = 0; i < list.size(); i++)
        {
            EntityBase entity1 = (EntityBase)list.get(i);
            if(!(entity1 instanceof Item))
            {
                continue;
            }
            Item entityitem = (Item)entity1;
            if(entityitem != null && entityitem.age < 50 && mocr.mocreaturesGlass.huntercreatures.destroyitems)
            {
                entityitem.remove();
            }
        }

    }

    public int getLimitPerChunk()
    {
        return 4;
    }

    protected boolean method_940()
    {
        return !tamed;
    }

    public void remove()
    {
        if(tamed && health > 0)
        {
            return;
        } else
        {
            super.remove();
            return;
        }
    }

    public int checkNearBigKitties(double d)
    {
        boolean flag = false;
        List list = level.getEntities(this, boundingBox.expand(d, d, d));
        for(int j = 0; j < list.size(); j++)
        {
            EntityBase entity = (EntityBase)list.get(j);
            if(entity != this && (entity instanceof EntityBigCat))
            {
                EntityBigCat entitybigcat = (EntityBigCat)entity;
                int i = entitybigcat.typeint;
                if(i == 2)
                {
                    i = 1;
                }
                return i;
            }
        }

        return 0;
    }

    public boolean NearSnowWithDistance(EntityBase entity, Double double1)
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
                    int j2 = level.getTileId(k1, l1, i2);
                    if(j2 != 0 && BlockBase.BY_ID[j2].material == Material.SNOW)
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
        int i = 0;
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
        setType(i);
        return mocr.mocreaturesGlass.huntercreatures.lionfreq > 0 && super.canSpawn();
    }

    public boolean renderName()
    {
        return !name.isEmpty() && displayname && mocr.mocreaturesGlass.othersettings.displayname;
    }

    public EntityBase ustawCel(Living gracz)
    {
        this.entity = gracz;
        return null;
    }

    public void wstanSzybko()
    {
        this.sitting = false;
    }

    public static void setName(EntityBigCat entitybigcat)
    {
        entitybigcat.displayname = true;
        mc.openScreen(new MoCGUI(entitybigcat, entitybigcat.name));
//        GuiHelper.openGUI(entityPlayer, Identifier.of("mocreatures:openTamePaper"), null, null, entitybigcat.name);
    }

    @SuppressWarnings("deprecation")
    public static Minecraft mc = Minecraft.class.cast(FabricLoader.getInstance().getGameInstance());
    mod_mocreatures mocr = new mod_mocreatures();
    public boolean protectFromPlayers;
    public String tigerOwner;
    public boolean lionboolean;
    protected int force;
    protected double attackRange;
    public int typeint;
    public boolean typechosen;
    public boolean adult;
    public float edad;
    public float heightF;
    public float widthF;
    public float lengthF;
    public boolean hungry;
    public boolean tamed;
    public boolean sitting;
    public boolean eaten;
    public String name;
    public boolean displayname;
    public int maxhealth;
    public Living roper;

    @Override
    public Identifier getHandlerIdentifier() {
        return Identifier.of(mod_mocreatures.MOD_ID, "BigCat");
    }
}

