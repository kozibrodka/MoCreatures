
package net.kozibrodka.mocreatures.entity;

import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
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
import net.minecraft.entity.mob.MonsterEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.item.PickaxeItem;
import net.minecraft.item.SwordItem;
import net.minecraft.nbt.NbtCompound;
import net.minecraft.world.World;
import net.modificationstation.stationapi.api.network.packet.PacketHelper;
import net.modificationstation.stationapi.api.server.entity.HasTrackingParameters;
import net.modificationstation.stationapi.api.util.Identifier;
import net.modificationstation.stationapi.api.server.entity.MobSpawnDataProvider;
import net.modificationstation.stationapi.api.util.TriState;

import java.util.List;

@HasTrackingParameters(trackingDistance = 160, updatePeriod = 1, sendVelocity = TriState.TRUE)
public class EntityDolphin extends EntityCustomAquaM implements MobSpawnDataProvider, MoCreatureRacial, MoCreatureNamed
        ///extends EntityCustomWM or EntityCustomAquaM
{

    public EntityDolphin(World world)
    {
        super(world);
//        texture = "/assets/mocreatures/stationapi/textures/mob/dolphin.png";
        setBoundingBoxSpacing(1.5F, 0.8F);
//        setAge(0.8F + random.nextFloat());
//        adult = false;
//        tamed = false;
        dolphinspeed = 1.3D;
        health = 12;
        maxhealth = 20; /// Delfin zaczyna z 10 i mozna dokarmić. (oryg. 30)
        temper = 50;
        killedByOtherEntity = true;
//        name = "";
//        displayname = false;
//        isDolphinPublic = false;
//        dolphinOwner = "";
    }

//    @Environment(EnvType.CLIENT)
//    public void setPositionAndAnglesAvoidEntities(double x, double y, double z, float pitch, float yaw, int interpolationSteps) {
//        this.standingEyeHeight = 0.0F;
//        this.lerpX = x;
//        this.lerpY = y;
//        this.lerpZ = z;
//        this.lerpYaw = (double)pitch;
//        this.lerpPitch = (double)yaw;
//        this.bodyTrackingIncrements = interpolationSteps;
//        setPosition(x,y,z);
//    }

    public double speed()
    {
        return dolphinspeed;
    }

    public int tametemper()
    {
        return temper;
    }

    protected void tickLiving() {
        super.tickLiving();
        this.dataTracker.set(29, (byte) health);
        if(target == null){
            return;
        }
        if(!target.isAlive() || !target.submergedInWater) /// LOL delfin atakuje pod wodą ostro
        {
            target = null;
        }
    }

    public int getRandomRace()
    {
        int i = random.nextInt(100);
        if(i <= 35)
        {
            return  1;
        } else
        if(i <= 60)
        {
            return 2;
        } else
        if(i <= 85)
        {
            return 3;
        } else
        if(i <= 96)
        {
            return 4;
        } else
        if(i <= 98)
        {
            return 5;
        } else
        {
            return 6;
        }
    }

    public void chooseType(int typeint)
    {
            if(typeint == 1)
            {
                texture = "/assets/mocreatures/stationapi/textures/mob/dolphin.png";
                dolphinspeed = 1.5D;
                temper = 50;
                maxhealth = 20;
            } else
            if(typeint == 2)
            {
                texture = "/assets/mocreatures/stationapi/textures/mob/dolphin2.png";
                dolphinspeed = 2.5D;
                temper = 100;
                maxhealth = 25;
            } else
            if(typeint == 3)
            {
                texture = "/assets/mocreatures/stationapi/textures/mob/dolphin3.png";
                dolphinspeed = 3.5D;
                temper = 150;
                maxhealth = 30;
            } else
            if(typeint == 4)
            {
                texture = "/assets/mocreatures/stationapi/textures/mob/dolphin4.png";
                dolphinspeed = 4.5D;
                temper = 200;
                maxhealth = 35;
            } else
            if(typeint == 5)
            {
                texture = "/assets/mocreatures/stationapi/textures/mob/dolphin5.png";
                dolphinspeed = 5.5D;
                temper = 250;
                maxhealth = 40;
            } else
            if(typeint == 6)
            {
                texture = "/assets/mocreatures/stationapi/textures/mob/dolphin6.png";
                dolphinspeed = 6.5D;
                temper = 300;
                maxhealth = 45;
            }
    }

    public boolean interact(PlayerEntity entityplayer)
    {
        ItemStack itemstack = entityplayer.inventory.getSelectedItem();
        if(world.isRemote){
            return false;
        }
        if(getTamed() && !getPublic() && !entityplayer.name.equals(getOwner()))
        {
            return false;
        }
        if(itemstack != null && itemstack.itemId == Item.RAW_FISH.id)
        {
            if(--itemstack.count == 0)
            {
                entityplayer.inventory.setStack(entityplayer.inventory.selectedSlot, null);
            }
            if((temper -= 25) < 1)
            {
                temper = 1;
            }
            if((health += 15) > maxhealth)
            {
                health = maxhealth;
            }
            world.playSound(this, "mocreatures:eating", 1.0F, 1.0F + (random.nextFloat() - random.nextFloat()) * 0.2F);
            sendSound(world, "mocreatures:eating", 1.0F, 1.0F + (random.nextFloat() - random.nextFloat()) * 0.2F);
            if(!getAdult())
            {
                setAge(getAge()+0.01F); //TODO bardzo niewiele niby, ale kon tak samo prawie
            }
            return true;
        }
        if(itemstack != null && itemstack.itemId == Item.COOKED_FISH.id && getTamed() && getAdult())
        {
            if(--itemstack.count == 0)
            {
                entityplayer.inventory.setStack(entityplayer.inventory.selectedSlot, null);
            }
            if((health += 25) > maxhealth)
            {
                health = maxhealth;
            }
            setEaten(true);
            world.playSound(this, "mocreatures:eating", 1.0F, 1.0F + (random.nextFloat() - random.nextFloat()) * 0.2F);
            sendSound(world, "mocreatures:eating", 1.0F, 1.0F + (random.nextFloat() - random.nextFloat()) * 0.2F);
            return true;
        }
        if(itemstack != null && getTamed() && entityplayer.name.equals(getOwner()) && itemstack.getItem() instanceof SwordItem && entityplayer.isSneaking())
        {
            if(getPublic()){
                setPublic(false);
                for(int var3 = 0; var3 < 7; ++var3) {
                    double var4 = this.random.nextGaussian() * 0.02D;
                    double var6 = this.random.nextGaussian() * 0.02D;
                    double var8 = this.random.nextGaussian() * 0.02D;
                    world.addParticle("heart", this.x + (double) (this.random.nextFloat() * this.width * 2.0F) - (double) this.width, this.y + 0.5D + (double) (this.random.nextFloat() * this.height), this.z + (double) (this.random.nextFloat() * this.width * 2.0F) - (double) this.width, var4, var6, var8);
                    sendParticle(world, "heart", this.x + (double) (this.random.nextFloat() * this.width * 2.0F) - (double) this.width, this.y + 0.5D + (double) (this.random.nextFloat() * this.height), this.z + (double) (this.random.nextFloat() * this.width * 2.0F) - (double) this.width, var4, var6, var8);
                }
            }else{
                setPublic(true);
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
        if(itemstack != null && getTamed() && (itemstack.itemId == mod_mocreatures.medallion.id || itemstack.itemId == Item.BOOK.id))
        {
            setNameWithGui(this, entityplayer);
            return true;
        }
        if(itemstack != null && getTamed() && itemstack.getItem() instanceof PickaxeItem)
        {
            setDisplayName(!getDisplayName());
            return true;
        }
        if(getAdult() && passenger == null)
        {
            entityplayer.yaw = yaw;
            entityplayer.pitch = pitch;
            entityplayer.y = y;
            entityplayer.setVehicle(this);
            setJokey(true);
            if(getType() == 6)
            {
                entityplayer.incrementStat(mod_mocreatures.RobertMaklowicz);
            }
            return true;
        } else
        {
            return false;
        }
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
                        damage(entity, ((MonsterEntity)entity).attackDamage);
                    }
                }

            }
            if(entityplayer.isSneaking())
            {
                entityplayer.setVehicle(null);
                passenger = null; //TODO: dodatek czy bedzie ok? bez bylo ok chyba
                setJokey(false);
            }
        }
        if(passenger == null && getJokey()){
            setJokey(false);
        }
    }

    public boolean isCollidable()
    {
        return passenger == null;
    }

    public void tickMovement()
    {
//        System.out.println("velox:  " + velocityX + "    veloz:  " + velocityZ);
        super.tickMovement();
        if(!typechosen && world.isRemote && getType() != 0){
            typechosen = true;
            chooseType(getType());
        }
        if(world.isRemote){
            return;
        }
        if(!getAdult() && random.nextInt(50) == 0 && !world.isRemote)
        {
            setAge(getAge()+0.01F);
            if(getAge() >= 1.5F)
            {
                setAdult(true);
            }
        }
        if(!hungry && random.nextInt(100) == 0)
        {
            hungry = true;
        }
        Riding();
        if(passenger != null && deathTime == 0 && (!getTamed() || hungry)) //TODO: SPRAWDŹ O CO CHODZI? jak jeździ ktos na nim??? + może dodaj SHARKOWI
        {
            ItemEntity entityitem = getClosestFish(this, 12D);
            if(entityitem != null)
            {
                MoveToNextEntity(entityitem);
                ItemEntity entityitem1 = getClosestFish(this, 2D);
                if(random.nextInt(20) == 0 && entityitem1 != null && deathTime == 0)
                {
                    entityitem1.markDead();
                    if((temper -= 25) < 1)
                    {
                        temper = 1;
                    }
                    health = maxhealth;
                }
            }
        }
        if(!ReadyforParenting(this))
        {
            return;
        }
        int i = 0;
        List list = world.getEntities(this, boundingBox.expand(8D, 2D, 8D));
        for(int j = 0; j < list.size(); j++)
        {
            Entity entity = (Entity)list.get(j);
            if(entity instanceof EntityDolphin)
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
            if(!(entity1 instanceof EntityDolphin) || entity1 == this)
            {
                continue;
            }
            EntityDolphin entitydolphin = (EntityDolphin)entity1;
            if(!ReadyforParenting(this) || !ReadyforParenting(entitydolphin))
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
            EntityDolphin entitydolphin1 = new EntityDolphin(world);
            entitydolphin1.setPosition(x, y, z);
            world.spawnEntity(entitydolphin1);
            world.playSound(this, "mob.chickenplop", 1.0F, (random.nextFloat() - random.nextFloat()) * 0.2F + 1.0F);
            sendSound(world, "mob.chickenplop", 1.0F, (random.nextFloat() - random.nextFloat()) * 0.2F + 1.0F);
            setEaten(false);
            entitydolphin.setEaten(false);
            gestationtime = 0;
            entitydolphin.gestationtime = 0;
            int l = Genetics(this, entitydolphin);
            entitydolphin1.setBred(true);
            entitydolphin1.setAge(0.35F);
            entitydolphin1.setAdult(false);
            entitydolphin1.setType(l);
            break;
        }

    }

    public boolean ReadyforParenting(EntityDolphin entitydolphin)
    {
        return entitydolphin.passenger == null && entitydolphin.vehicle == null && entitydolphin.getTamed() && entitydolphin.getEaten() && entitydolphin.getAdult();
    }

    private int Genetics(EntityDolphin entitydolphin, EntityDolphin entitydolphin1)
    { //TODO: No EASY dolphin breeding OPTION. Hard to get pink/albino right now.
        if(entitydolphin.getType() == entitydolphin1.getType())
        {
            return entitydolphin.getType();
        }
        int i = entitydolphin.getType() + entitydolphin1.getType();
        boolean flag = random.nextInt(3) == 0;
        boolean flag1 = random.nextInt(10) == 0;
        if(i < 5 && flag)
        {
            return i;
        }
        if((i == 5 || i == 6) && flag1)
        {
            return i;
        } else
        {
            return getRandomRace(); ///return zero oryginalnei stworzy mutanta
        }
    }

    public void writeNbt(NbtCompound nbttagcompound)
    {
        super.writeNbt(nbttagcompound);
        nbttagcompound.putBoolean("Tamed", getTamed());
        nbttagcompound.putInt("TypeInt", getType());
        nbttagcompound.putBoolean("Adult", getAdult());
        nbttagcompound.putBoolean("Bred", getBred());
        nbttagcompound.putFloat("Age", getAge());
        nbttagcompound.putString("Name", getName());
        nbttagcompound.putBoolean("DisplayName", getDisplayName());
        nbttagcompound.putBoolean("PublicDolphin", getPublic());
        nbttagcompound.putString("DolphinOwner", getOwner());
        nbttagcompound.putBoolean("HasJokey", getJokey());
        nbttagcompound.putInt("GestationTime", gestationtime);
    }

    public void readNbt(NbtCompound nbttagcompound)
    {
        super.readNbt(nbttagcompound);
        setTamed(nbttagcompound.getBoolean("Tamed"));
        setType(nbttagcompound.getInt("TypeInt"));
        setAdult(nbttagcompound.getBoolean("Adult"));
        setBred(nbttagcompound.getBoolean("Bred"));
        setAge(nbttagcompound.getFloat("Age"));
        setName(nbttagcompound.getString("Name"));
        setDisplayName(nbttagcompound.getBoolean("DisplayName"));
        setPublic(nbttagcompound.getBoolean("PublicDolphin"));
        setOwner(nbttagcompound.getString("DolphinOwner"));
        setJokey(nbttagcompound.getBoolean("HasJokey"));
        gestationtime = nbttagcompound.getInt("GestationTime");
    }

    protected Entity getTargetInRange()
    {
        if(world.difficulty > 0 && getAge() >= 1.0F && mocr.mocreaturesGlass.watermobs.attackdolphins && random.nextInt(50) == 0)
        {
            LivingEntity entityliving = FindTarget(this, 12D);
            if(entityliving != null && entityliving.submergedInWater)
            {
                return entityliving;
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
            if(!(entity1 instanceof EntityShark) || !(mocr.mocreaturesGlass.watermobs.attackdolphins))
            {
                continue;
            }

            if (getTamed() && ((EntityShark)entity1).getTamed())
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
        }
    }

    protected int getDroppedItemId()
    {
        return Item.RAW_FISH.id;
    }

    public boolean canSpawn()
    {
        return mocr.mocreaturesGlass.watermobs.dolphinfreq > 0 && super.canSpawn();
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

    protected boolean canDespawn()
    {
        return !getTamed();
    }

    protected String getRandomSound()
    {
        return "mocreatures:dolphin";
    }

    protected String getHurtSound()
    {
        return "mocreatures:dolphinhurt";
    }

    protected String getDeathSound()
    {
        return "mocreatures:dolphindying";
    }

    protected float getSoundVolume()
    {
        return 0.4F;
    }

    protected String getUpsetSound()
    {
        return "mocreatures:dolphinupset";
    }

    public boolean renderName()
    {
        return getDisplayName() && !getJokey();
    }

    public void setNameWithGui(EntityDolphin entitydolphin, PlayerEntity entityPlayer)
    {
        if (FabricLoader.INSTANCE.getEnvironmentType() == EnvType.SERVER) {
            PacketHelper.sendTo(entityPlayer, new NamePacket(entitydolphin.getName(), entitydolphin.id));
            setDisplayName(true);
        }else{
            MoGuiOpener clientS = new MoGuiOpener();
            clientS.openTameGui(entitydolphin, entitydolphin.getName());
            setDisplayName(true);
        }
    }

    mod_mocreatures mocr = new mod_mocreatures();
    public int gestationtime;
    private double dolphinspeed;
    public int maxhealth;
    private int temper;
    public boolean hungry;
//    public boolean displayname;

//    public int typeint;
//    public float b;
//    public boolean adult;
//    private boolean eaten;
//    public boolean tamed;
//    public boolean isDolphinPublic;
//    public boolean bred;
//    public String dolphinOwner;
//    public String name;

    public boolean typechosen;

    protected void initDataTracker()
    {
        super.initDataTracker();
        dataTracker.startTracking(16, (byte) 0); //Type
        dataTracker.startTracking(17, (int) 0); //Age
        dataTracker.startTracking(18, (byte) 0); //Adult
        dataTracker.startTracking(19, (byte) 0); //Eaten
        dataTracker.startTracking(20, (byte) 0); //Tamed
        dataTracker.startTracking(21, (byte) 0); //Public
        dataTracker.startTracking(22, (byte) 0); //Bred
        dataTracker.startTracking(23, (byte) 0); //RenderName
        dataTracker.startTracking(24, (byte) 0); //Has Jokey
        dataTracker.startTracking(29, (byte) 0); //HEALTH
        dataTracker.startTracking(30, ""); //Owner
        dataTracker.startTracking(31, ""); //Name
    }

    @Override
    public Identifier getHandlerIdentifier() {
        return Identifier.of(mod_mocreatures.MOD_ID, "Dolphin");
    }

    public void sendParticle(World world, String name, double x, double y, double z, double i, double j, double k){
        if (net.fabricmc.loader.FabricLoader.INSTANCE.getEnvironmentType() == EnvType.SERVER){
            mocr.particlePacket(world,name,x,y,z,i,j,k);
        }
    }

    public void sendSound(World world, String name, float vol, float pit){
        if (net.fabricmc.loader.FabricLoader.INSTANCE.getEnvironmentType() == EnvType.SERVER){
            mocr.voicePacket(world, name, this.id, vol, pit);
        }
    }

    //TYPE
    public void setTypeSpawn() {
        if(!world.isRemote) {
            setType(getRandomRace());
            setAge(0.8F + random.nextFloat());
//            this.health = this.maxhealth; //TODO:
        }
    }

    public void setType(int type)
    {
        if(!world.isRemote) {
            dataTracker.set(16, (byte) type);
            chooseType(type);
        }
    }

    public void setTypeBred(int type) /// czy dobrze sie zgadza? bo usunalem
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

    //PUBLIC
    public boolean getPublic()
    {
        return (dataTracker.getByte(21) & 1) != 0;
    }

    public void setPublic(boolean flag)
    {
        if(flag)
        {
            dataTracker.set(21, (byte) 1);
        } else
        {
            dataTracker.set(21, (byte) 0);
        }
    }

    //BRED
    public boolean getBred()
    {
        return (dataTracker.getByte(22) & 1) != 0;
    }

    public void setBred(boolean flag)
    {
        if(flag)
        {
            dataTracker.set(22, (byte) 1);
        } else
        {
            dataTracker.set(22, (byte) 0);
        }
    }

    //RENDER NAME
    public boolean getDisplayName()
    {
        return (dataTracker.getByte(23) & 1) != 0;
    }

    public void setDisplayName(boolean flag)
    {
        if(flag)
        {
            dataTracker.set(23, (byte) 1);
        } else
        {
            dataTracker.set(23, (byte) 0);
        }
    }

    //HAS JOKEY
    public boolean getJokey()
    {
        return (dataTracker.getByte(24) & 1) != 0;
    }

    public void setJokey(boolean flag)
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
