package net.kozibrodka.mocreatures.entity;

import net.kozibrodka.mocreatures.events.mod_mocreatures;
import net.minecraft.block.Block;
import net.minecraft.entity.Entity;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.ai.pathing.Path;
import net.minecraft.entity.passive.AnimalEntity;
import net.minecraft.item.Item;
import net.minecraft.nbt.NbtCompound;
import net.minecraft.util.math.MathHelper;
import net.minecraft.world.World;
import net.modificationstation.stationapi.api.server.entity.MobSpawnDataProvider;
import net.modificationstation.stationapi.api.util.Identifier;

import java.util.List;

public class EntityZebra extends AnimalEntity implements MobSpawnDataProvider {

    public EntityZebra(World world)
    {
        super(world);
        texture = "/assets/mocreatures/stationapi/textures/mob/zebra.png";
        setBoundingBoxSpacing(2.0F, 2.0F);
        health = 25;
        movementSpeed = 0.45F;

    }

    public void tickMovement()
    {
        super.tickMovement();
        if(random.nextInt(5) == 0 && !world.isRemote)
        {
            LivingEntity entityliving = getBoogey(10D);
            if(entityliving != null)
            {
                setMySpeed(true);
                runLikeHell(entityliving);
            } else
            {
                setMySpeed(false);
            }
        }
    }

    public void setMySpeed(boolean flag)
    {
        float f = 0.45F;
        if(flag)
        {
            f *= 2.0F;
        }
        movementSpeed = f;
    }

    public LivingEntity getBoogey(double d)
    {
        double d1 = -1D;
        LivingEntity entityliving = null;
        List list = world.getEntities(this, boundingBox.expand(d, 4D, d));
        for(int i = 0; i < list.size(); i++)
        {
            Entity entity = (Entity)list.get(i);
            if((entity instanceof LivingEntity) && !(entity instanceof EntityZebra) && ((double)entity.width >= 0.5D || (double)entity.height >= 0.5D))
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

    public void writeNbt(NbtCompound nbttagcompound)
    {
        super.writeNbt(nbttagcompound);
    }

    public void readNbt(NbtCompound nbttagcompound)
    {
        super.readNbt(nbttagcompound);
    }

    public int getLimitPerChunk()
    {
        return 6;
    }

    protected int getDroppedItemId()
    {
        return Item.RAW_PORKCHOP.id;
    }

    protected String getRandomSound()
    {
        return "mocreatures:zebra";
    }

    protected String getHurtSound()
    {
        return "mocreatures:zebrahurt";
    }

    protected String getDeathSound()
    {
        return "mocreatures:zebradeath";
    }

    public boolean canSpawn()
    {
        return mocr.mocreaturesGlass.animals.zebrafreq > 0 && super.canSpawn();
    }

    mod_mocreatures mocr = new mod_mocreatures();

    @Override
    public Identifier getHandlerIdentifier() {return Identifier.of(mod_mocreatures.MOD_ID, "Zebra");}
}
