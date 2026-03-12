package net.kozibrodka.mocreatures.entity;

import net.kozibrodka.mocreatures.events.mod_mocreatures;
import net.minecraft.entity.Entity;
import net.minecraft.entity.passive.AnimalEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.Item;
import net.minecraft.nbt.NbtCompound;
import net.minecraft.util.math.MathHelper;
import net.minecraft.world.World;
import net.modificationstation.stationapi.api.server.entity.MobSpawnDataProvider;
import net.modificationstation.stationapi.api.util.Identifier;

import java.util.Random;

public class EntityElephant extends AnimalEntity implements MobSpawnDataProvider {

    public EntityElephant(World world)
    {
        super(world);
        texture = "/assets/mocreatures/stationapi/textures/mob/elephant.png";
        setBoundingBoxSpacing(2.5F, 2.8F);
        health = 65; ///
        movementSpeed = 1.1F;
    }

    protected void tickLiving(){
        if(this.target instanceof PlayerEntity){
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

    public boolean damage(Entity entitybase, int i)
    {
        if(super.damage(entitybase, i))
        {
            if(passenger == entitybase || vehicle == entitybase)
            {
                return true;
            }
            if(entitybase != this && world.difficulty > 0)
            {
                target = entitybase;
                setMad(true);
            }
            return true;
        } else
        {
            return false;
        }
    }

    protected void attack(Entity entity, float f)
    {
        if(f > 2.0F && f < 6F && random.nextInt(10) == 0)
        {
            if(onGround)
            {
                double d = entity.x - x;
                double d1 = entity.z - z;
                float f1 = MathHelper.sqrt(d * d + d1 * d1);
                velocityX = (d / (double)f1) * 0.5D * 2.800000011920929D + velocityX * 0.20000000298023224D;
                velocityZ = (d1 / (double)f1) * 0.5D * 2.800000011920929D + velocityZ * 0.20000000298023224D;
                velocityY = 0.40000000596046448D;
            }
        } else
        if((double)f < 1.5D && entity.boundingBox.maxY > boundingBox.minY && entity.boundingBox.minY < boundingBox.maxY)
        {
            attackCooldown = 20;
            byte byte0 = 10;
            if(!getMad())
            {
                byte0 = 20;
            }
            entity.damage(this, byte0);
        }
    }


    public void markDead()
    {
        super.markDead();
    }

    public void ustawTexture(String tex){
        this.texture = tex;
    }

    protected void initDataTracker() {
        super.initDataTracker();
        dataTracker.startTracking(16, (byte) 0); //MAD
    }

    public void writeNbt(NbtCompound nbttagcompound)
    {
        super.writeNbt(nbttagcompound);
        nbttagcompound.putBoolean("IsMad", getMad());
    }

    public void readNbt(NbtCompound nbttagcompound)
    {
        super.readNbt(nbttagcompound);
        setMad(nbttagcompound.getBoolean("IsMad"));
    }

    public int getLimitPerChunk()
    {
        return 6;
    }

    protected int getDroppedItemId()
    {
        return Item.BONE.id;
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
}
