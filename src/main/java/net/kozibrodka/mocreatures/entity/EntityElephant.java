package net.kozibrodka.mocreatures.entity;

import net.kozibrodka.mocreatures.events.mod_mocreatures;
import net.kozibrodka.mocreatures.mocreatures.MoCreatureRacial;
import net.minecraft.entity.Entity;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.passive.AnimalEntity;
import net.minecraft.entity.passive.WolfEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NbtCompound;
import net.minecraft.util.math.Box;
import net.minecraft.util.math.MathHelper;
import net.minecraft.world.World;
import net.modificationstation.stationapi.api.server.entity.MobSpawnDataProvider;
import net.modificationstation.stationapi.api.util.Identifier;

import java.util.Random;

public class EntityElephant extends AnimalEntity implements MobSpawnDataProvider, MoCreatureRacial {

    public EntityElephant(World world)
    {
        super(world);
        texture = "/assets/mocreatures/stationapi/textures/mob/elephant.png";
        setBoundingBoxSpacing(2.5F, 2.8F);
        health = 70; ///
        movementSpeed = 1.1F;
    }

    public void tickMovement() {
        super.tickMovement();
        if(world.isRemote){
            return;
        }
        if (!getAdult() && random.nextInt(250) == 0) {
            setAge(getAge() + 0.03F);
            if (getAge() >= 2.8F) {
                setAdult(true);
                setAge(2.8F);
                health = 70;
            }
        }
        if(getMad() && target == null && random.nextInt(30) == 0){
            setMad(false);
        }
        if(target != null && !target.isAlive() && random.nextInt(10) == 0){
            setMad(false);
        }
    }

    public boolean damage(Entity entitybase, int i)
    {
        if(super.damage(entitybase, i))
        {
            if(passenger == entitybase || (vehicle == entitybase && !(vehicle instanceof EntityCrocodile)))
            {
                return true;
            }
            if(entitybase != this && world.difficulty > 0)
            {
                if(getAdult()){
                    target = entitybase;
                    setMad(true);
                }else{
                    notifyAdultElephants(entitybase);
                }
            }
            return true;
        } else
        {
            return false;
        }
    }

    public void notifyAdultElephants(Entity target1){
        if (target1 instanceof LivingEntity) {
            for(Object var5 : this.world.collectEntitiesByClass(EntityElephant.class, Box.createCached(this.x, this.y, this.z, this.x + (double)1.0F, this.y + (double)1.0F, this.z + (double)1.0F).expand((double)16.0F, (double)4.0F, (double)16.0F))) {
                EntityElephant var6 = (EntityElephant)var5;
                if(var6.getAdult()) {
                    var6.target = target1;
                    if (target1 instanceof PlayerEntity) {
                        var6.setMad(true);
                    }
                }
            }
        }
    }

    protected void attack(Entity entity, float f)
    {
        if(f > 2.0F && f < 6F && random.nextInt(25) == 0) //10
        {
            if(onGround)
            {
                double d = entity.x - x;
                double d1 = entity.z - z;
                float f1 = MathHelper.sqrt(d * d + d1 * d1);
                velocityX = (d / (double)f1) * 0.5D * 1.800000011920929D + velocityX * 0.20000000298023224D; //2.800000011920929D
                velocityZ = (d1 / (double)f1) * 0.5D * 1.800000011920929D + velocityZ * 0.20000000298023224D; //2.800000011920929D
                velocityY = 0.40000000596046448D;
            }
        } else
        if((double)f < 1.5D && entity.boundingBox.maxY > boundingBox.minY && entity.boundingBox.minY < boundingBox.maxY)
        {
            attackCooldown = 20;
            byte byte0 = 12;
            if(!getMad())
            {
                byte0 = 18;
            }
            entity.damage(this, byte0);
        }
    }

    public void ustawTexture(String tex){
        this.texture = tex;
    }

    protected void initDataTracker() {
        super.initDataTracker();
        dataTracker.startTracking(16, (byte) 0); //MAD
        dataTracker.startTracking(17, (int) 0); //Age
        dataTracker.startTracking(18, (byte) 0); //Adult
    }

    public void writeNbt(NbtCompound nbttagcompound)
    {
        super.writeNbt(nbttagcompound);
        nbttagcompound.putBoolean("IsMad", getMad());
        nbttagcompound.putBoolean("Adult", getAdult());
        nbttagcompound.putFloat("Edad", getAge());
    }

    public void readNbt(NbtCompound nbttagcompound)
    {
        super.readNbt(nbttagcompound);
        setMad(nbttagcompound.getBoolean("IsMad"));
        setAdult(nbttagcompound.getBoolean("Adult"));
        setAge(nbttagcompound.getFloat("Edad"));
    }

    public int getLimitPerChunk()
    {
        return 6;
    }

    protected int getDroppedItemId()
    {
        return Item.BONE.id;
    }

    protected void dropItems()
    {
        int i = random.nextInt(5);
        for(int j = 0; j < i; j++)
        {
            dropItem(new ItemStack(getDroppedItemId(), 1, 0), 0.0F);
        }
        int k = random.nextInt(2);
        for (int j = 0; j < k; j++) {
            if(world.difficulty > 0) {
                dropItem(new ItemStack(mod_mocreatures.elephanttusk.id, 1, 0), 0.0F);
            }
        }
    }

    protected String getRandomSound()
    {
        return "mocreatures:elephant";
    }

    protected String getHurtSound()
    {
        return "mocreatures:elephanthurt";
    }

    protected String getDeathSound()
    {
        return "mocreatures:elephantdeath";
    }

    public boolean canSpawn()
    {
        return mocr.mocreaturesGlass.animals.elephantfreq > 0 && super.canSpawn();
    }

    mod_mocreatures mocr = new mod_mocreatures();

    @Override
    public Identifier getHandlerIdentifier() {return Identifier.of(mod_mocreatures.MOD_ID, "Elephant");}

    @Override
    public void setTypeSpawn() {
        if (!world.isRemote) {
            if (random.nextInt(5) == 0) {
                setAdult(false);
                health = 20;
                setAge(1.0F);
            }else{
                setAdult(true);
                setAge(2.8F);
            }
        }
    }

    //MAD
    public boolean getMad()
    {
        return (dataTracker.getByte(16) & 1) != 0;
    }

    public void setMad(boolean flag)
    {
        if(flag)
        {
            dataTracker.set(16, Byte.valueOf((byte)1));
        } else
        {
            dataTracker.set(16, Byte.valueOf((byte)0));
        }
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
            dataTracker.set(18, Byte.valueOf((byte)1));
        } else
        {
            dataTracker.set(18, Byte.valueOf((byte)0));
        }
    }
}
