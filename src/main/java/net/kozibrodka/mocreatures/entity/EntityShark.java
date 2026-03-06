// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) braces deadcode 

package net.kozibrodka.mocreatures.entity;

import net.fabricmc.api.EnvType;
import net.fabricmc.loader.FabricLoader;
import net.kozibrodka.mocreatures.events.mod_mocreatures;
import net.kozibrodka.mocreatures.mocreatures.MoCreatureNamed;
import net.kozibrodka.mocreatures.mocreatures.MoCreatureRacial;
import net.kozibrodka.mocreatures.mocreatures.MoGuiOpener;
import net.kozibrodka.mocreatures.network.AskPacket;
import net.kozibrodka.mocreatures.network.NamePacket;
import net.minecraft.entity.Entity;
import net.minecraft.entity.ItemEntity;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.passive.WolfEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.item.PickaxeItem;
import net.minecraft.item.SwordItem;
import net.minecraft.nbt.NbtCompound;
import net.minecraft.world.World;
import net.modificationstation.stationapi.api.network.packet.PacketHelper;
import net.modificationstation.stationapi.api.util.Identifier;
import net.modificationstation.stationapi.api.server.entity.MobSpawnDataProvider;

import java.util.List;


public class EntityShark extends EntityCustomWM implements MobSpawnDataProvider, MoCreatureRacial, MoCreatureNamed
{

    public EntityShark(World world)
    {
        super(world);
        texture = "/assets/mocreatures/stationapi/textures/mob/shark.png";
        setBoundingBoxSpacing(1.8F, 1.3F);
//        b = 1.0F + random.nextFloat();
//        adult = false;
//        tamed = false;
        health = 25;
        maxhealth = 25;
//        name = "";
//        displayname = false;
//        sharkOwner = "";
//        protectFromPlayers = true;
        typechosen = false; //TODO shark healt+pacticle packets
    }

    public void tickMovement()
    {
        super.tickMovement();
        if(!typechosen && world.isRemote && getType() != 0){
            typechosen = true;
            chooseType(getType());
            PacketHelper.send(new AskPacket(this.id, "shark"));
        }
        if(!getAdult() && random.nextInt(50) == 0)
        {
            setAge(getAge()+0.01F);
            if(getAge() >= 2.0F)
            {
                setAdult(true);
            }
        }
        //TODO: szukanie ryby + heal?
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
        if(world.difficulty > 0 && getAge() >= 1.0F)
        {
            PlayerEntity entityplayer = world.getClosestPlayer(this, 16D);
            if(entityplayer != null && entityplayer.submergedInWater && !getTamed())
            {
                return entityplayer;
            }
            if(random.nextInt(30) == 0)
            {
                LivingEntity entityliving = FindTarget(this, 16D);
                if(entityliving != null && entityliving.submergedInWater)
                {
                    return entityliving;
                }
            }
        }
        return null;
    }

    public LivingEntity FindTarget(Entity entity, double d)
    {
        double d1 = -1D;
        LivingEntity entityliving = null;
        List list = world.getEntities(this, boundingBox.expand(d, d, d));
        for(int i = 0; i < list.size(); i++)
        {
            Entity entity1 = (Entity)list.get(i);
            if(!(entity1 instanceof LivingEntity) || (entity1 instanceof EntityShark) || (entity1 instanceof EntitySharkEgg) || (entity1 instanceof PlayerEntity) || (entity1 instanceof WolfEntity) && !mocr.mocreaturesGlass.huntercreatures.attackwolves || (entity1 instanceof EntityHorse) && !mocr.mocreaturesGlass.huntercreatures.attackhorses || (entity1 instanceof EntityDolphin) && (getTamed() || !mocr.mocreaturesGlass.watermobs.attackdolphins) || (entity1 instanceof EntityHorse) && getTamed() && ((EntityHorse) entity1).getTamed() || (entity1 instanceof EntityBigCat) && getTamed() && ((EntityBigCat) entity1).getTamed())
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

    public boolean damage(Entity entitybase, int i)
    {
        if(super.damage(entitybase, i) && world.difficulty > 0)
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
            if(entitybase != this)
            {
                target = entitybase;
            }
            if(getTamed()){
                sendHealth(world, health);
            }
            return true;
        } else
        {
            return false;
        }
    }

    protected void attack(Entity entity, float f)
    {
        if((double)f < 3.5D && entity.boundingBox.maxY > boundingBox.minY && entity.boundingBox.minY < boundingBox.maxY && getAge() >= 1.0F)
        {
            attackCooldown = 20;
            entity.damage(this, 5);
            if(!(entity instanceof PlayerEntity))
            {
                destroyDrops(this, 3D);
            }
        }
    }

    protected void initDataTracker() {
        super.initDataTracker();
        dataTracker.startTracking(16, (byte) 0); //Type
        dataTracker.startTracking(17, (int) 0); //Age
        dataTracker.startTracking(18, (byte) 0); //Adult
        dataTracker.startTracking(19, (byte) 0); //Tamed
        dataTracker.startTracking(20, (byte) 0); //Protect From Players
        dataTracker.startTracking(21, (byte) 0); //Display Name
        dataTracker.startTracking(30, ""); //Owner
        dataTracker.startTracking(31, ""); //Name
    }

    public void writeNbt(NbtCompound nbttagcompound)
    {
        super.writeNbt(nbttagcompound);
        nbttagcompound.putBoolean("Tamed", getTamed());
        nbttagcompound.putInt("TypeInt", getType());
        nbttagcompound.putFloat("Age", getAge());
        nbttagcompound.putBoolean("Adult", getAdult());
        nbttagcompound.putString("Name", getName());
        nbttagcompound.putBoolean("DisplayName", getDisplayName());
        nbttagcompound.putString("SharkOwner", getOwner());
        nbttagcompound.putBoolean("ProtectFromPlayers", getProtect());
    }

    public void readNbt(NbtCompound nbttagcompound)
    {
        super.readNbt(nbttagcompound);
        setTamed(nbttagcompound.getBoolean("Tamed"));
        setType(nbttagcompound.getInt("TypeInt"));
        setAge(nbttagcompound.getFloat("Age"));
        setAdult(nbttagcompound.getBoolean("Adult"));
        setName(nbttagcompound.getString("Name"));
        setDisplayName(nbttagcompound.getBoolean("DisplayName"));
        setOwner(nbttagcompound.getString("SharkOwner"));
        setProtect(nbttagcompound.getBoolean("ProtectFromPlayers"));
    }

    protected boolean canDespawn()
    {
        return !getTamed();
    }

    protected void dropItems()
    {
        int i = random.nextInt(100);
        if(i < 90)
        {
            int j = random.nextInt(3) + 1;
            for(int l = 0; l < j; l++)
            {
                dropItem(new ItemStack(mod_mocreatures.sharkteeth, 1, 0), 0.0F);
            }
            if(mocr.mocreaturesGlass.balancesettings.balance_drop) {
                int j1 = random.nextInt(2);
                for (int l1 = 0; l1 < j1; l1++) {
                    dropItem(new ItemStack(mod_mocreatures.sharkoil, 1, 0), 0.0F);
                }
            }

        } else if(world.difficulty > 0 && getAge() > 1.5F)
        {
            int k = random.nextInt(3) + 1;
            for(int i1 = 0; i1 < k; i1++)
            {
                dropItem(new ItemStack(mod_mocreatures.sharkegg, 1, 0), 0.0F);
            }

        }
        if(getType() == 2){
            //TODO: mega drop
        }
    }

    public void destroyDrops(Entity entity, double d)
    {
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

    public boolean interact(PlayerEntity entityplayer)
    {
        ItemStack itemstack = entityplayer.inventory.getSelectedItem();
        if(getTamed() && !entityplayer.name.equals(getOwner()))
        {
            return false;
        }
        if(itemstack != null && getTamed() && entityplayer.name.equals(getOwner()) && itemstack.getItem() instanceof SwordItem)
        {
            if(getProtect()){
                setProtect(false);
                for(int var3 = 0; var3 < 7; ++var3) {
                    double var4 = this.random.nextGaussian() * 0.02D;
                    double var6 = this.random.nextGaussian() * 0.02D;
                    double var8 = this.random.nextGaussian() * 0.02D;
                    world.addParticle("heart", this.x + (double) (this.random.nextFloat() * this.width * 2.0F) - (double) this.width, this.y + 0.5D + (double) (this.random.nextFloat() * this.height), this.z + (double) (this.random.nextFloat() * this.width * 2.0F) - (double) this.width, var4, var6, var8);
                    sendParticle(world, "heart", this.x + (double) (this.random.nextFloat() * this.width * 2.0F) - (double) this.width, this.y + 0.5D + (double) (this.random.nextFloat() * this.height), this.z + (double) (this.random.nextFloat() * this.width * 2.0F) - (double) this.width, var4, var6, var8);
                }
            }else{
                setProtect(true);
                for(int var3 = 0; var3 < 7; ++var3) {
                    double var4 = this.random.nextGaussian() * 0.02D;
                    double var6 = this.random.nextGaussian() * 0.02D;
                    double var8 = this.random.nextGaussian() * 0.02D;
                    world.addParticle("flame", this.x + (double) (this.random.nextFloat() * this.width * 2.0F) - (double) this.width, this.y + 0.5D + (double) (this.random.nextFloat() * this.height), this.z + (double) (this.random.nextFloat() * this.width * 2.0F) - (double) this.width, var4, var6, var8);
                    sendParticle(world, "flame", this.x + (double) (this.random.nextFloat() * this.width * 2.0F) - (double) this.width, this.y + 0.5D + (double) (this.random.nextFloat() * this.height), this.z + (double) (this.random.nextFloat() * this.width * 2.0F) - (double) this.width, var4, var6, var8);
                }
            }
            return true;
        }
//        if(itemstack !=null && entityplayer.name.equals(getOwner()) && getTamed() && itemstack.itemId == mod_mocreatures.sharkfood.id)
//        {
//            if(--itemstack.count == 0)
//            {
//                entityplayer.inventory.setStack(entityplayer.inventory.selectedSlot, null);
//            }
//            if(health + 15 > maxhealth)
//            {
//                health = maxhealth;
//            }else{
//                health += 15;
//            }
//            return true;
//        }
        if(itemstack != null && getTamed() && (itemstack.itemId == mod_mocreatures.medallion.id || itemstack.itemId == Item.BOOK.id))
        {
            setNameWithGui(this, entityplayer);
            return true;
        }
        if(itemstack != null && getTamed() && itemstack.getItem() instanceof PickaxeItem)
        {
            setDisplayName(!getDisplayName());
            return true;
        } else
        {
            return false;
        }
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

    protected String getHurtSound()
    {
        return "mocreatures:sharkhurt";
    }

    protected String getDeathSound()
    {
        return "mocreatures:sharkhurt";
    }


    public boolean renderName()
    {
        return getDisplayName();
    }

    public boolean canSpawn()
    {
        return mocr.mocreaturesGlass.watermobs.sharkfreq > 0 && world.difficulty >= mocr.mocreaturesGlass.watermobs.sharkSpawnDifficulty.ordinal() + 1 && super.canSpawn();
    }

    public void setNameWithGui(EntityShark entityShark, PlayerEntity entityPlayer)
    {
        if (FabricLoader.INSTANCE.getEnvironmentType() == EnvType.SERVER) {
            PacketHelper.sendTo(entityPlayer, new NamePacket(entityShark.getName(), entityShark.id));
            setDisplayName(true);
        }else{
            MoGuiOpener clientS = new MoGuiOpener();
            clientS.openTameGui(entityShark, entityShark.getName());
            setDisplayName(true);
        }
    }

    public void sendHealth(World world, int hp){
        if (net.fabricmc.loader.FabricLoader.INSTANCE.getEnvironmentType() == EnvType.SERVER){
            mocr.healthPacket(world, this.id, hp);
        }
    }

    public void sendParticle(World world, String name, double x, double y, double z, double i, double j, double k){
        if (net.fabricmc.loader.FabricLoader.INSTANCE.getEnvironmentType() == EnvType.SERVER){
            mocr.particlePacket(world,name,x,y,z,i,j,k);
        }
    }



    mod_mocreatures mocr = new mod_mocreatures();
//    public boolean protectFromPlayers;
//    public String sharkOwner;
//    public String name;
//    public boolean displayname;
//    public float b;
//    public boolean adult;
//    public boolean tamed;
    public int maxhealth;
    public boolean typechosen;

    @Override
    public Identifier getHandlerIdentifier() {
        return Identifier.of(mod_mocreatures.MOD_ID, "Shark");
    }

    public int getRandomRace()
    {
        if(random.nextInt(2137) == 0) /// 1111
        {
            return 2;
        } else
            return 1;
    }

    public void chooseType(int typeint)
    {
        if(typeint == 1)
        {
            texture = "/assets/mocreatures/stationapi/textures/mob/shark.png";
            maxhealth = 25;
        } else
        if(typeint == 2)
        {
            texture = "/assets/mocreatures/stationapi/textures/mob/sharkmega.png";
            maxhealth = 50;
            setAge(6.0F); /// 6.0F na bukkit było
        }
    }

    //TYPE
    public void setTypeSpawn()
    {
        if(!world.isRemote){
            setAge(1.0F + random.nextFloat());
            int type = getRandomRace();
            setType(type);
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
            dataTracker.set(18, (byte) 1);
        } else
        {
            dataTracker.set(18, (byte) 0);
        }
    }
    //TAMED
    public boolean getTamed()
    {
        return (dataTracker.getByte(19) & 1) != 0;
    }

    public void setTamed(boolean flag)
    {
        if(flag)
        {
            dataTracker.set(19, (byte) 1);
        } else
        {
            dataTracker.set(19, (byte) 0);
        }
    }

    //PROTECT FROM PLAYERES
    public boolean getProtect()
    {
        return (dataTracker.getByte(20) & 1) != 0;
    }

    public void setProtect(boolean flag)
    {
        if(flag)
        {
            dataTracker.set(20, Byte.valueOf((byte)1));
        } else
        {
            dataTracker.set(20, Byte.valueOf((byte)0));
        }
    }

    //DISPLAY NAME
    public boolean getDisplayName()
    {
        return (dataTracker.getByte(21) & 1) != 0;
    }

    public void setDisplayName(boolean flag)
    {
        if(flag)
        {
            dataTracker.set(21, Byte.valueOf((byte)1));
        } else
        {
            dataTracker.set(21, Byte.valueOf((byte)0));
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
