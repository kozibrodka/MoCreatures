package net.kozibrodka.mocreatures.entity;

import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.kozibrodka.mocreatures.events.mod_mocreatures;
import net.kozibrodka.mocreatures.mocreatures.MoCTools;
import net.kozibrodka.mocreatures.mocreatures.MoCreatureRacial;
import net.kozibrodka.mocreatures.network.PoisonPacket;
import net.minecraft.block.material.Material;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.Item;
import net.minecraft.nbt.NbtCompound;
import net.minecraft.world.World;
import net.modificationstation.stationapi.api.network.packet.PacketHelper;
import net.modificationstation.stationapi.api.server.entity.MobSpawnDataProvider;
import net.modificationstation.stationapi.api.util.Identifier;

public class EntityRay extends EntityCustomAquaM implements MobSpawnDataProvider {
    ///extends EntityCustomWM or EntityCustomAquaM

    public EntityRay(World world)
    {
        super(world);
        setBoundingBoxSpacing(0.6F, 0.5F);
        health = 10; ///
        movementSpeed = 0.3F;
        if(!world.isRemote){
            setTypeSpawn();
        }
    }

    @Override
    public void tickMovement() {
        super.tickMovement();
        if(!world.isRemote) {
            if(!adult && random.nextInt(50) == 0) {
                setAge(getAge() + 0.01F);
                if(getType() == 1 && getAge() >= 1.8F || getType() > 1 && getAge() >= 0.9F) {
                    adult = true;
                }
            }

            ++poisoncounter;
            if(poisoncounter > 100) {
                setAttacking(false);
            }

            if(getType() > 1 && poisoncounter > 250 && world.difficulty > 0) {
                PlayerEntity entityplayertarget = world.getClosestPlayer(x, y, z, 2.0D);
                if(entityplayertarget != null) {
                    PacketHelper.sendTo(entityplayertarget, new PoisonPacket(1));
                    poisoncounter = 0;
                    setAttacking(true);
                }
            }
        }

    }

    @Override
    public boolean damage(Entity damagesource, int i) {
        if(super.damage(damagesource, i)) {
            if(getType() != 1 && world.difficulty != 0) {
                if(damagesource != this) {
                    target = damagesource;
                }
                return true;
            } else {
                return true;
            }
        } else {
            return false;
        }
    }

    @Override
    public int getLimitPerChunk()
    {
        return 1;
    }

    @Override
    protected int getDroppedItemId()
    {
        return Item.RAW_FISH.id;
    }

    @Override
    protected String getRandomSound()
    {
        return "mocreatures:XXX";
    }

    @Override
    protected String getHurtSound()
    {
        return "mocreatures:XXX";
    }

    @Override
    protected String getDeathSound()
    {
        return "mocreatures:XXX";
    }

    @Override
    public boolean canSpawn()
    {
        return mod_mocreatures.mocGlass.watermobs.rayfreq > 0 && !MoCTools.isSharkUnderIce(this) && super.canSpawn();
    }

    @Override
    public boolean isSwimming() {
        return this.isInFluid(Material.WATER);
    }

    public int maxhealth;
    public int poisoncounter;
    public boolean adult;

    @Override
    public Identifier getHandlerIdentifier() {return Identifier.of(mod_mocreatures.MOD_ID, "Ray");}


    @Override
    protected void initDataTracker()
    {
        super.initDataTracker();
        dataTracker.startTracking(16, (byte) 0); //Type
        dataTracker.startTracking(17, 0); //Age
        dataTracker.startTracking(18, (byte) 0); //Attacking
    }

    @Override
    public void writeNbt(NbtCompound nbttagcompound)
    {
        super.writeNbt(nbttagcompound);
        nbttagcompound.putInt("TypeInt", getType());
        nbttagcompound.putFloat("Edad", getAge());
        nbttagcompound.putBoolean("Adult", adult);
    }

    @Override
    public void readNbt(NbtCompound nbttagcompound)
    {
        super.readNbt(nbttagcompound);
        setType(nbttagcompound.getInt("TypeInt"));
        setAge(nbttagcompound.getFloat("Edad"));
        adult = (nbttagcompound.getBoolean("Adult"));
    }

    public void setTypeSpawn() {
        if(!world.isRemote){
            int type = getRandomRace();
            setType(type);
            health = maxhealth;
            if(getType() == 1){
                setAge(0.8F + random.nextFloat());
            }
            if(getType() == 2){
                setAge(0.7F);
            }
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

    //ATTACK
    public boolean getAttacking()
    {
        return (dataTracker.getByte(18) & 1) != 0;
    }

    public void setAttacking(boolean flag)
    {
        if(flag)
        {
            dataTracker.set(18, (byte) 1);
        } else
        {
            dataTracker.set(18, (byte) 0);
        }
    }

    public int getRandomRace(){
        int i = random.nextInt(100);
        if(i <= 35)
        {
            return 2;
        } else
            return 1;

    }

    public void chooseType(int typeint)
    {
        if(typeint == 1)
        {
            texture = "/assets/mocreatures/stationapi/textures/mob/mantray.png";
            maxhealth = 10;
        } else
        if(typeint == 2)
        {
            texture = "/assets/mocreatures/stationapi/textures/mob/stingray.png";
            maxhealth = 15;
        }
    }

    @Override
    @Environment(EnvType.CLIENT)
    public String getTexture() {
        return switch (getType()) {
            case 1 -> "/assets/mocreatures/stationapi/textures/mob/mantray.png";
            case 2 -> "/assets/mocreatures/stationapi/textures/mob/stingray.png";
            default -> "";
        };
    }
}
