package net.kozibrodka.mocreatures.entity;


import net.fabricmc.loader.api.FabricLoader;
import net.kozibrodka.mocreatures.events.mod_mocreatures;
import net.kozibrodka.mocreatures.mixin.WalkingBaseAccesor;
import net.kozibrodka.mocreatures.mocreatures.MoCGUI;
import net.minecraft.block.Block;
import net.minecraft.block.Material;
import net.minecraft.class_61;
import net.minecraft.client.Minecraft;
import net.minecraft.entity.Entity;
import net.minecraft.entity.ItemEntity;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.mob.MobEntity;
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


public class EntityBigCat extends AnimalEntity implements MobSpawnDataProvider
{

    public EntityBigCat(World world)
    {
        super(world);
        lionboolean = false;
        texture = "/assets/mocreatures/stationapi/textures/mob/lionf.png";
        edad = 0.35F;
        setBoundingBoxSpacing(0.9F, 1.3F);
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
        dataTracker.method_1502(16, Byte.valueOf((byte)0)); //Type
        dataTracker.method_1502(17, Byte.valueOf((byte)0)); //Age
        dataTracker.method_1502(18, Byte.valueOf((byte)0)); //Adult

        dataTracker.method_1502(20, Byte.valueOf((byte)0)); //Tamed
        dataTracker.method_1502(21, Byte.valueOf((byte)0)); //Sitting
        dataTracker.method_1502(22, Byte.valueOf((byte)0)); //Hungry
        dataTracker.method_1502(23, Byte.valueOf((byte)0)); //Render Health
    }

    public void setType(int i)
    {
        typeint = i;
        typechosen = false;
        if(random.nextInt(4) == 0)
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
            if(random.nextInt(4) == 0)
            {
                adult = false;
                field_1045 = true;
            }
            int i = random.nextInt(100);
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

    public void method_937()
    {
        super.method_937();
        if(!adult && random.nextInt(250) == 0)
        {
            edad += 0.01F;
            if(edad >= 1.0F)
            {
                adult = true;
                field_1045 = false;
            }
        }
        if(!hungry && !sitting && random.nextInt(200) == 0)
        {
            hungry = true;
        }
        if(roper != null && random.nextInt(20) == 0)
        {
            float f = roper.method_1351(this);
            if(f > 8F && !sitting)
            {
                method_429(roper, f);
            }
            if((f > 18F) & sitting)
            {
                roper = null;
            }
        }
        if(field_1041 == 0 && hungry && !sitting)
        {
            ItemEntity entityitem = getClosestItem(this, 12D, Item.RAW_PORKCHOP.id, Item.RAW_FISH.id);
            if(entityitem != null)
            {
                MoveToNextEntity(entityitem);
                ItemEntity entityitem1 = getClosestItem(this, 2D, Item.RAW_PORKCHOP.id, Item.RAW_FISH.id);
                if(random.nextInt(80) == 0 && entityitem1 != null && field_1041 == 0)
                {
                    entityitem1.markDead();
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
                    world.playSound(this, "mocreatures:eating", 1.0F, 1.0F + (random.nextFloat() - random.nextFloat()) * 0.2F);
                    hungry = false;
                }
            }
        }
    }

    protected boolean method_640()
    {
        return sitting;
    }

    private void method_429(Entity entity, float f)
    {
        class_61 pathentity = world.method_192(this, entity, 16F);
        if(pathentity == null && f > 12F)
        {
            int i = MathHelper.floor(entity.x) - 2;
            int j = MathHelper.floor(entity.z) - 2;
            int k = MathHelper.floor(entity.boundingBox.minY);
            for(int l = 0; l <= 4; l++)
            {
                for(int i1 = 0; i1 <= 4; i1++)
                {
                    if((l < 1 || i1 < 1 || l > 3 || i1 > 3) && world.method_1780(i + l, k - 1, j + i1) && !world.method_1780(i + l, k, j + i1) && !world.method_1780(i + l, k + 1, j + i1))
                    {
                        method_1341((float)(i + l) + 0.5F, k, (float)(j + i1) + 0.5F, yaw, pitch);
                        return;
                    }
                }

            }

        } else
        {
            method_635(pathentity);
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
            double d2 = entityitem1.method_1347(entity.x, entity.y, entity.z);
            if((d < 0.0D || d2 < d * d) && (d1 == -1D || d2 < d1))
            {
                d1 = d2;
                entityitem = entityitem1;
            }
        }

        return entityitem;
    }
    protected void method_910(){
        if(this.target instanceof LivingEntity){
            PlayerEntity uciekinier = world.method_186(this, 16D);
            if(uciekinier == null && target.isAlive()){
                if(random.nextInt(30) == 0)
                {
                    target = null;
                }
            }
        }
        super.method_910();
    }

    protected Entity method_638()
    {
        if(roper != null)
        {
            return getMastersEnemy((PlayerEntity)roper, 12D);
        }
        if(world.field_213 > 0)
        {
            PlayerEntity entityplayer = world.method_186(this, 12D);
            if(!tamed && entityplayer != null && adult && hungry)
            {
                if(typeint == 1 || typeint == 5 || typeint == 7)
                {
                    hungry = false;
                    return entityplayer;
                }
                if(random.nextInt(30) == 0)
                {
                    hungry = false;
                    return entityplayer;
                }
            }
            if(random.nextInt(80) == 0 && hungry)
            {
                LivingEntity entityliving = getClosestTarget(this, 10D);
                hungry = false;
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
            if(!(entity1 instanceof LivingEntity) || entity1 == entity || entity1 == entity.field_1594 || entity1 == entity.field_1595 || (entity1 instanceof PlayerEntity) || !adult && ((double)entity1.spacingXZ > 0.5D || (double)entity1.spacingY > 0.5D) || (entity1 instanceof EntityKittyBed) || (entity1 instanceof EntityLitterBox) || (entity1 instanceof MonsterEntity) && (!tamed || !adult) || tamed && (entity1 instanceof EntityKitty) && ((EntityKitty)entity1).kittystate > 2 || (entity1 instanceof EntityHorse) && !mocr.mocreaturesGlass.huntercreatures.attackhorses || (entity1 instanceof EntityHorse) && tamed && ((EntityHorse) entity1).tamed || (entity1 instanceof EntityShark) && ((EntityShark)entity1).tamed && tamed || (entity1 instanceof EntityDolphin) && ((EntityDolphin)entity1).tamed && tamed || (entity1 instanceof WolfEntity) && !mocr.mocreaturesGlass.huntercreatures.attackwolves)
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
            double d2 = entity1.method_1347(entity.x, entity.y, entity.z);
            if((d < 0.0D || d2 < d * d) && (d1 == -1D || d2 < d1) && ((LivingEntity)entity1).method_928(entity))
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
            if(field_1594 == entitybase || field_1595 == entitybase)
            {
                return true;
            }
            if(tamed && entitybase instanceof PlayerEntity)
            {
                PlayerEntity gracz = (PlayerEntity)entitybase;
                if(!gracz.name.equals(tigerOwner) && protectFromPlayers)
                {
                    target = entitybase;
                }
            }
            if(entitybase != this && world.field_213 > 0 && !tamed)
            {
                target = entitybase;
            }
            return true;
        } else
        {
            return false;
        }
    }

    protected void method_637(Entity entity, float f)
    {
        if(f > 2.0F && f < 6F && random.nextInt(50) == 0)
        {
            if(field_1623)
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
            field_1042 = 20;
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
        nbttagcompound.putBoolean("LionBoolean", lionboolean);
        nbttagcompound.putInt("TypeInt", typeint);
        nbttagcompound.putBoolean("Adult", adult);
        nbttagcompound.putBoolean("Tamed", tamed);
        nbttagcompound.putBoolean("Sitting", sitting);
        nbttagcompound.putFloat("Edad", edad);
        nbttagcompound.putString("Name", name);
        nbttagcompound.putBoolean("DisplayName", displayname);
        nbttagcompound.putString("TigerOwner", tigerOwner);
        nbttagcompound.putBoolean("ProtectFromPlayers", protectFromPlayers);
    }

    public void readNbt(NbtCompound nbttagcompound)
    {
        super.readNbt(nbttagcompound);
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

    public boolean method_1323(PlayerEntity entityplayer)
    {
        ItemStack itemstack = entityplayer.inventory.getSelectedItem();
        if(itemstack !=null && mocr.mocreaturesGlass.balancesettings.balance_drop && entityplayer.name.equals(tigerOwner) && tamed && itemstack.itemId == mod_mocreatures.bigcatfood.id)
        {
            if(--itemstack.count == 0)
            {
                entityplayer.inventory.setStack(entityplayer.inventory.selectedSlot, null);
            }
            if(health + 15 > maxhealth)
            {
                health = maxhealth;
            }else{
                health += 15;
            }
            return true;
        }
        if(itemstack != null && tamed && entityplayer.name.equals(tigerOwner) && (itemstack.itemId == Item.GOLDEN_SWORD.id || itemstack.itemId == Item.STONE_SWORD.id || itemstack.itemId == Item.WOODEN_SWORD.id || itemstack.itemId == Item.IRON_SWORD.id || itemstack.itemId == Item.DIAMOND_SWORD.id))
        {
            if(protectFromPlayers == true){
                protectFromPlayers = false;
                for(int var3 = 0; var3 < 7; ++var3) {
                    double var4 = this.random.nextGaussian() * 0.02D;
                    double var6 = this.random.nextGaussian() * 0.02D;
                    double var8 = this.random.nextGaussian() * 0.02D;
                    world.addParticle("heart", this.x + (double) (this.random.nextFloat() * this.spacingXZ * 2.0F) - (double) this.spacingXZ, this.y + 0.5D + (double) (this.random.nextFloat() * this.spacingY), this.z + (double) (this.random.nextFloat() * this.spacingXZ * 2.0F) - (double) this.spacingXZ, var4, var6, var8);
                }
            }else{
                protectFromPlayers = true;
                for(int var3 = 0; var3 < 7; ++var3) {
                    double var4 = this.random.nextGaussian() * 0.02D;
                    double var6 = this.random.nextGaussian() * 0.02D;
                    double var8 = this.random.nextGaussian() * 0.02D;
                    world.addParticle("flame", this.x + (double) (this.random.nextFloat() * this.spacingXZ * 2.0F) - (double) this.spacingXZ, this.y + 0.5D + (double) (this.random.nextFloat() * this.spacingY), this.z + (double) (this.random.nextFloat() * this.spacingXZ * 2.0F) - (double) this.spacingXZ, var4, var6, var8);
                }
            }
            return true;
        }
        if(itemstack != null && !tamed && eaten && itemstack.itemId == mod_mocreatures.medallion.id)
        {
            if(--itemstack.count == 0)
            {
                entityplayer.inventory.setStack(entityplayer.inventory.selectedSlot, null);
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
        if(itemstack != null  && entityplayer.name.equals(tigerOwner) &&  tamed && (itemstack.itemId == Item.DIAMOND_PICKAXE.id || itemstack.itemId == Item.WOODEN_PICKAXE.id || itemstack.itemId == Item.STONE_PICKAXE.id || itemstack.itemId == Item.IRON_PICKAXE.id || itemstack.itemId == Item.GOLDEN_PICKAXE.id))
        {
            displayname = !displayname;
            return true;
        }
        if(itemstack != null && entityplayer.name.equals(tigerOwner) && tamed && (itemstack.itemId == mod_mocreatures.medallion.id || itemstack.itemId == Item.BOOK.id))
        {
            setName(this);
            return true;
        }
        if(itemstack != null && entityplayer.name.equals(tigerOwner) && field_1594 == null && roper == null && tamed && itemstack.itemId == mod_mocreatures.rope.id)
        {
            if(--itemstack.count == 0)
            {
                entityplayer.inventory.setStack(entityplayer.inventory.selectedSlot, null);
            }
            world.playSound(this, "mocreatures:roping", 1.0F, 1.0F + (random.nextFloat() - random.nextFloat()) * 0.2F);
            roper = entityplayer;
            return true;
        }
        if(roper != null && tamed)
        {
            entityplayer.inventory.method_671(new ItemStack(mod_mocreatures.rope));
            world.playSound(this, "mocreatures:roping", 1.0F, 1.0F + (random.nextFloat() - random.nextFloat()) * 0.2F);
            roper = null;
            return true;
        } else
        {
            return false;
        }
    }

    protected String method_911()
    {
        if(adult)
        {
            return "mocreatures:liongrunt";
        } else
        {
            return "mocreatures:cubgrunt";
        }
    }

    protected String method_912()
    {
        if(adult)
        {
            return "mocreatures:lionhurt";
        } else
        {
            return "mocreatures:cubhurt";
        }
    }

    protected String method_913()
    {
        if(adult)
        {
            return "mocreatures:liondeath";
        } else
        {
            return "mocreatures:cubdying";
        }
    }

    protected int method_914()
    {
        return mod_mocreatures.bigcatclaw.id;
    }

    public void destroyDrops(Entity entity, double d)
    {
        if(tamed)
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

    public int method_916()
    {
        return 4;
    }

    protected boolean method_940()
    {
        return !tamed;
    }

    public void markDead()
    {
        if(tamed && health > 0)
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
                    if(j2 != 0 && Block.BLOCKS[j2].material == Material.field_998)
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

    public Entity ustawCel(LivingEntity gracz)
    {
        this.target = gracz;
        return null;
    }

    public void wstanSzybko()
    {
        this.sitting = false;
    }

    public static void setName(EntityBigCat entitybigcat)
    {
        entitybigcat.displayname = true;
        mc.setScreen(new MoCGUI(entitybigcat, entitybigcat.name));
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
    public LivingEntity roper;

    @Override
    public Identifier getHandlerIdentifier() {
        return Identifier.of(mod_mocreatures.MOD_ID, "BigCat");
    }
}

