
package net.kozibrodka.mocreatures.entity;

import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.fabricmc.loader.FabricLoader;
import net.kozibrodka.mocreatures.events.mod_mocreatures;
import net.kozibrodka.mocreatures.mocreatures.MoCreatureRacial;
import net.minecraft.block.Block;
import net.minecraft.entity.Entity;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.ai.pathing.Path;
import net.minecraft.entity.passive.AnimalEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.player.ServerPlayerEntity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NbtCompound;
import net.minecraft.util.math.MathHelper;
import net.minecraft.world.World;
import net.modificationstation.stationapi.api.network.packet.PacketHelper;
import net.modificationstation.stationapi.api.util.Identifier;
import net.modificationstation.stationapi.api.server.entity.HasTrackingParameters;
import net.modificationstation.stationapi.api.server.entity.MobSpawnDataProvider;

import java.util.List;

public class EntityDeer extends AnimalEntity implements MobSpawnDataProvider
{

    public EntityDeer(World world)
    {
        super(world);
        setBoundingBoxSpacing(0.9F, 1.3F);
        if(!world.isRemote){
            setTypeSpawn();
        }
    }

    @Override
    protected void initDataTracker() {
        super.initDataTracker();
        dataTracker.startTracking(16, (byte) 0); //Type
        dataTracker.startTracking(17, 0); //Age
        dataTracker.startTracking(18, (byte) 0); //Adult
    }

    public void chooseType(int type)
    {
            if(type == 1)
            {
                texture = "/assets/mocreatures/stationapi/textures/mob/deer.png";
                maxhealth = 15;
                setAdult(true);
                setAge(2.0F);
            } else
            if(type == 2)
            {
                texture = "/assets/mocreatures/stationapi/textures/mob/deerf.png";
                maxhealth = 15;
                setAdult(true);
                setAge(2.0F);
            }
            if(type == 3)
            {
                texture = "/assets/mocreatures/stationapi/textures/mob/deerb.png";
                maxhealth = 5;
                setAdult(false);
                setAge(0.75F);
            }

    }

    @Override
    @Environment(EnvType.CLIENT)
    public String getTexture() {
        return switch (getType()) {
            case 1 -> "/assets/mocreatures/stationapi/textures/mob/deer.png";
            case 2 -> "/assets/mocreatures/stationapi/textures/mob/deerf.png";
            case 3 -> "/assets/mocreatures/stationapi/textures/mob/deerb.png";
            default -> "";
        };
    }

    public void setMySpeed(boolean flag, int type)
    {
        float f = 1.0F;
        if(type == 1)
        {
            f = 1.7F;
        } else
        if(type == 2)
        {
            f = 1.9F;
        } else
        {
            f = 1.3F;
        }
        if(flag)
        {
            f *= 2.0F;
        }
        movementSpeed = f;
    }

    @Override
    protected void onLanding(float f)
    {
    }

    @Override
    public void tickMovement()
    {
        super.tickMovement();
        if(getType() == 3 && !getAdult() && random.nextInt(250) == 0 && !world.isRemote)
        {
            setAge(getAge()+0.01F);
            if(getAge() >= 1.3F)
            {
                int newType = getRandomAdultRace();
                setType(newType);
                health = 15;
            }
        }
        if(random.nextInt(5) == 0 && !world.isRemote)
        {
            LivingEntity entityliving = getBoogey(10D);
            if(entityliving != null)
            {
                setMySpeed(true, getType());
                runLikeHell(entityliving);
            } else
            {
                setMySpeed(false, getType());
            }
        }
    }

    public LivingEntity getBoogey(double d)
    {
        double d1 = -1D;
        LivingEntity entityliving = null;
        List list = world.getEntities(this, boundingBox.expand(d, 4D, d));
        for(int i = 0; i < list.size(); i++)
        {
            Entity entity = (Entity)list.get(i);
            if((entity instanceof LivingEntity) && !(entity instanceof EntityDeer) && ((double)entity.width >= 0.5D || (double)entity.height >= 0.5D))
            {
                entityliving = (LivingEntity)entity;
            }
        }

        return entityliving;
    }

    public void runLikeHell(Entity entity)
    {
        double d = x - entity.x;
        double d1 = z - entity.z;
        double d2 = Math.atan2(d, d1);
        d2 += (double)(random.nextFloat() - random.nextFloat()) * 0.75D;
        double d3 = x + Math.sin(d2) * 8D;
        double d4 = z + Math.cos(d2) * 8D;
        int i = MathHelper.floor(d3);
        int j = MathHelper.floor(boundingBox.minY);
        int k = MathHelper.floor(d4);
        int l = 0;
        do
        {
            if(l >= 16)
            {
                break;
            }
            int i1 = (i + random.nextInt(4)) - random.nextInt(4);
            int j1 = (j + random.nextInt(3)) - random.nextInt(3);
            int k1 = (k + random.nextInt(4)) - random.nextInt(4);
            if(j1 > 4 && (world.getBlockId(i1, j1, k1) == 0 || world.getBlockId(i1, j1, k1) == Block.SNOW.id) && world.getBlockId(i1, j1 - 1, k1) != 0)
            {
                Path pathentity = world.findPath(this, i1, j1, k1, 16F);
                setPath(pathentity);
                break;
            }
            l++;
        } while(true);
    }

    @Override
    protected void tickLiving()
    {
        if(movementSpeed > 2.0F && onGround && random.nextInt(30) == 0 && (velocityX > 0.10000000000000001D || velocityZ > 0.10000000000000001D || velocityX < -0.10000000000000001D || velocityZ < -0.10000000000000001D))
        {
            velocityY = 0.59999999999999998D;
        }
        super.tickLiving();
    }

    @Override
    public void writeNbt(NbtCompound nbttagcompound)
    {
        super.writeNbt(nbttagcompound);
        nbttagcompound.putInt("TypeInt", getType());
        nbttagcompound.putBoolean("Adult", getAdult());
        nbttagcompound.putFloat("Edad", getAge());
    }

    @Override
    public void readNbt(NbtCompound nbttagcompound)
    {
        super.readNbt(nbttagcompound);
        setAdult(nbttagcompound.getBoolean("Adult"));
        setType(nbttagcompound.getInt("TypeInt"));
        setAge(nbttagcompound.getFloat("Edad"));
    }

    @Override
    protected String getRandomSound()
    {
        if(!getAdult())
        {
            return "mocreatures:deerbgrunt";
        } else
        {
            return "mocreatures:deerfgrunt";
        }
    }

    @Override
    protected String getHurtSound()
    {
        return "mocreatures:deerhurt";
    }

    @Override
    protected String getDeathSound()
    {
        return "mocreatures:deerdying";
    }

    @Override
    protected int getDroppedItemId()
    {
        return Item.RAW_PORKCHOP.id;
    }

    public float dajszybkosc(){
        return movementSpeed;
    }

    public int getRandomRace()
    {
        int i = random.nextInt(100);
        if(i <= 20)
        {
            return 1;
        } else
        if(i <= 70)
        {
            return 2;
        } else
        {
            return 3;
        }
    }

    public int getRandomAdultRace()
    {
        int i = random.nextInt(10);
        if(i <= 3)
        {
            return 1;
        } else
        {
            return 2;
        }
    }

    //TYPE
    public void setType(int type)
    {
        if(!world.isRemote){
            dataTracker.set(16, (byte)(type));
            chooseType(type);
            setMySpeed(false, type);
        }
    }

    public void setTypeSpawn()
    {
        if(!world.isRemote){
            int type = getRandomRace();
            setType(type);
            health = maxhealth;
        }
    }

    public int getType()
    {
        return dataTracker.getByte(16) & 0xF;
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

    @Override
    public Identifier getHandlerIdentifier() {
        return Identifier.of(mod_mocreatures.MOD_ID, "Deer");
    }

    @Override
    public boolean canSpawn()
    {
        return mod_mocreatures.mocGlass.animals.deerfreq > 0 && super.canSpawn();
    }

    public int maxhealth;

}
