

package net.kozibrodka.mocreatures.entity;

import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.fabricmc.loader.FabricLoader;
import net.kozibrodka.mocreatures.events.mod_mocreatures;
import net.kozibrodka.mocreatures.mocreatures.MoCTools;
import net.kozibrodka.mocreatures.mocreatures.MoCreatureNamed;
import net.kozibrodka.mocreatures.mocreatures.MoCreatureRacial;
import net.kozibrodka.mocreatures.mocreatures.MoGuiOpener;
import net.kozibrodka.mocreatures.network.NamePacket;
import net.minecraft.entity.Entity;
import net.minecraft.entity.ItemEntity;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.passive.SquidEntity;
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
        ///extends EntityCustomWM or EntityCustomAquaM

        ///Nie rozumiem czemu Shark nie działa w systemie AquaM a Pirania działa??? wtf
{

    public EntityShark(World world)
    {
        super(world);
        texture = "/assets/mocreatures/stationapi/textures/mob/shark.png";
        setBoundingBoxSpacing(1.8F, 1.3F);
        health = 25;
        maxhealth = 25;
        typechosen = false;
    }

    public void tickMovement()
    {
        super.tickMovement();
        if(!typechosen && world.isRemote && getType() != 0){
            typechosen = true;
            chooseType(getType());
        }
        if(!getAdult() && random.nextInt(50) == 0)
        {
            setAge(getAge()+0.01F);
            if(getAge() >= 2.0F)
            {
                setAdult(true);
            }
        }
        if(target != null && target.y < y - 0.5D && getDistance(target) < 5.0F) {
            velocityY -= 0.1D; /// Lekkie ala nurkowanie, troche buggy
        }
        if(target != null && (this.y - target.y > 6.0D) ){
            target = null; /// Odpuszcza jak target jest na dnie, zeby nie stal zablokowany
        }
    }

    protected void tickLiving() {
        super.tickLiving();
        if(random.nextInt(300) == 0 && health < maxhealth && deathTime == 0 && getTamed())
        {
            health++;
        }
        this.dataTracker.set(29, (byte) health);
    }

    protected Entity getTargetInRange()
    {
        if(world.difficulty > 0 && getAge() >= 1.0F)
        {
            PlayerEntity entityplayer = world.getClosestPlayer(this, 16D);
            if(entityplayer != null && entityplayer.submergedInWater && !getTamed() && (this.y - entityplayer.y < 6.0D))
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
        return ((victim instanceof EntityShark) || (victim instanceof EntitySharkEgg) || (victim instanceof SquidEntity) || (hunter.y - victim.y > 6.0D) || (victim instanceof EntityDolphin) && !mod_mocreatures.mocGlass.watermobs.attackdolphins || victim instanceof EntityElephant && ((EntityElephant) victim).getAdult() || victim instanceof EntityHippo || (victim instanceof EntityCrocodile) && ((EntityCrocodile) victim).getAge() > 1.2F);
    } /// Dziki rekin jest zbalansowanie agresywny.

    public boolean damage(Entity entitybase, int i)
    {
        if(super.damage(entitybase, i) && world.difficulty > 0)
        {
            if(passenger == entitybase || (vehicle == entitybase && !(vehicle instanceof EntityCrocodile)))
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
                MoCTools.destroyDrops(this, 3D);
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
        dataTracker.startTracking(29, (byte) 0); //HEALTH
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
                dropItem(new ItemStack(mod_mocreatures.sharkteeth.id, 1, 0), 0.0F);
            }

        } else if(world.difficulty > 0 && getAge() > 1.5F)
        {
            int k = random.nextInt(3) + 1;
            for(int i1 = 0; i1 < k; i1++)
            {
                dropItem(new ItemStack(mod_mocreatures.sharkegg.id, 1, 0), 0.0F);
            }

        }
        if(getType() == 2 && world.difficulty > 0){
            dropItem(new ItemStack(mod_mocreatures.megalodonteeth.id, 1, 0), 0.0F);
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
                MoCTools.addHeartParticles(world,this);
                world.broadcastEntityEvent(this, (byte)6);
            }else{
                setProtect(true);
                MoCTools.addFlameParticles(world,this);
                world.broadcastEntityEvent(this, (byte)7);
            }
            return true;
        }
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
        if(getTamed() && health > 0  && !world.isRemote)
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

    public boolean canSpawn() //TODO spawning on ICE??
    {
        return mod_mocreatures.mocGlass.watermobs.sharkfreq > 0 && !MoCTools.isNearTorch(this) && world.difficulty >= mod_mocreatures.mocGlass.watermobs.sharkSpawnDifficulty.ordinal() + 1 && super.canSpawn();
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

    @Environment(EnvType.CLIENT)
    public void processServerEntityStatus(byte status) {
        if (status == 6) {
            MoCTools.addHeartParticles(world, this);
        } else if (status == 7) {
            MoCTools.addFlameParticles(world, this);
        } else {
            super.processServerEntityStatus(status);
        }
    }

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
            maxhealth = 60;
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
