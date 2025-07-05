package net.kozibrodka.mocreatures.entity;


import net.kozibrodka.mocreatures.events.mod_mocreatures;
import net.kozibrodka.mocreatures.mixin.EntityBaseAccesor;
import net.kozibrodka.mocreatures.mocreatures.MoCreatureRacial;
import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.entity.Entity;
import net.minecraft.entity.ItemEntity;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.passive.AnimalEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.Item;
import net.minecraft.nbt.NbtCompound;
import net.minecraft.util.math.Box;
import net.minecraft.util.math.MathHelper;
import net.minecraft.world.World;
import net.modificationstation.stationapi.api.util.Identifier;
import net.modificationstation.stationapi.api.server.entity.MobSpawnDataProvider;

import java.util.List;


public class EntityBird extends AnimalEntity implements MobSpawnDataProvider, MoCreatureRacial
{

    public EntityBird(World world)
    {
        super(world);
//        texture = "/assets/mocreatures/stationapi/textures/mob/birdblue.png";
        setBoundingBoxSpacing(0.4F, 0.3F);
        health = 2;
        verticalCollision = true;
        wingb = 0.0F;
        wingc = 0.0F;
        wingh = 1.0F;
//        fleeing = false;
//        tamed = false;
//        typeint = 0;
//        typechosen = false;
//        hasreproduced = false;
        killedByOtherEntity = true;
    }

    protected void onLanding(float f)
    {
    }

    protected void initDataTracker()
    {
        super.initDataTracker();
        dataTracker.startTracking(16, (byte) 0); //Type
        dataTracker.startTracking(17, (byte) 0); //Tamed
        dataTracker.startTracking(18, (byte) 0); //Picked
        dataTracker.startTracking(19, (byte) 0); //HasReproduced
        dataTracker.startTracking(20, (byte) 0); //Fleeing
    }

    public int getLimitPerChunk()
    {
        return 6;
    }

    public void tickMovement()
    {
        super.tickMovement();
        if(!typechosen && world.isRemote && getType() != 0){
            typechosen = true;
            chooseType(getType());
        }
        winge = wingb;
        wingd = wingc;
        wingc = (float)((double)wingc + (double)(onGround ? -1 : 4) * 0.29999999999999999D);
        if(wingc < 0.0F)
        {
            wingc = 0.0F;
        }
        if(wingc > 1.0F)
        {
            wingc = 1.0F;
        }
        if(!onGround && wingh < 1.0F)
        {
            wingh = 1.0F;
        }
        wingh = (float)((double)wingh * 0.90000000000000002D);
        if(!onGround && velocityY < 0.0D)
        {
            velocityY *= 0.80000000000000004D;
        }
        wingb += wingh * 2.0F;
        LivingEntity entityliving = getClosestEntityLiving(this, 4D);
        if(entityliving != null && !getTamed() && canSee(entityliving))
        {
            setFleeing(true);
        }
        if(random.nextInt(300) == 0)
        {
            setFleeing(true);
        }
        if(getFleeing())
        {
            if(FlyToNextTree())
            {
                setFleeing(false);
            }
            int ai[] = ReturnNearestMaterialCoord(this, Material.LEAVES, Double.valueOf(16D));
            if(ai[0] == -1)
            {
                for(int i = 0; i < 2; i++)
                {
                    WingFlap();
                }

                setFleeing(false);
            }
            if(random.nextInt(50) == 0)
            {
                setFleeing(false);
            }
        }
        if(!getFleeing())
        {
            ItemEntity entityitem = getClosestSeeds(this, 12D);
            if(entityitem != null)
            {
                FlyToNextEntity(entityitem);
                ItemEntity entityitem1 = getClosestSeeds(this, 1.0D);
                if(random.nextInt(50) == 0 && entityitem1 != null)
                {
                    entityitem1.markDead();
                    setTamed(true);
                }
            }
        }
    }

    protected void tickLiving()
    {
        if(onGround && random.nextInt(10) == 0 && (velocityX > 0.050000000000000003D || velocityZ > 0.050000000000000003D || velocityX < -0.050000000000000003D || velocityZ < -0.050000000000000003D))
        {
            velocityY = 0.25D;
        }
        if(vehicle != null && (vehicle instanceof PlayerEntity))
        {
            PlayerEntity entityplayer = (PlayerEntity)vehicle;
            if(entityplayer != null)
            {
                yaw = entityplayer.yaw;
                ((EntityBaseAccesor)entityplayer).setFallDistance(0.0F);
                if(entityplayer.velocityY < -0.10000000000000001D)
                {
                    entityplayer.velocityY = -0.10000000000000001D;
                }
            }
        }
        if(!getFleeing() || !getPicked())
        {
            super.tickLiving();
        } else
        if(onGround)
        {
            setPicked(false);
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

    public boolean interact(PlayerEntity entityplayer)
    {
        if(!getTamed())
        {
            return false;
        }
        yaw = entityplayer.yaw;
        setVehicle(entityplayer);
        if(vehicle != null)
        {
            setPicked(true);
        } else
        {
            world.playSound(this, "mob.chickenplop", 1.0F, (random.nextFloat() - random.nextFloat()) * 0.2F + 1.0F);
        }
        velocityX = entityplayer.velocityX * 5D;
        velocityY = entityplayer.velocityY / 2D + 0.5D;
        velocityZ = entityplayer.velocityZ * 5D;
        return true;
    }

    public double getStandingEyeHeight()
    {
        if(vehicle instanceof PlayerEntity)
        {
            return (double)(standingEyeHeight - 1.15F);
        } else
        {
            return (double)standingEyeHeight;
        }
    }

    private ItemEntity getClosestSeeds(Entity entity, double d)
    {
        double d1 = -1D;
        ItemEntity entityitem = null;
        List list = world.getEntities(this, boundingBox.expand(d, d, d));
        for(int i = 0; i < list.size(); i++)
        {
            Entity entity1 = (Entity)list.get(i);
            if(!(entity1 instanceof ItemEntity))
            {
                continue;
            }
            ItemEntity entityitem1 = (ItemEntity)entity1;
            if(entityitem1.stack.itemId != Item.SEEDS.id)
            {
                continue;
            }
            double d2 = entityitem1.getSquaredDistance(entity.x, entity.y, entity.z);
            if((d < 0.0D || d2 < d * d) && (d1 == -1D || d2 < d1))
            {
                d1 = d2;
                entityitem = entityitem1;
            }
        }

        return entityitem;
    }

    private LivingEntity getClosestEntityLiving(Entity entity, double d)
    {
        double d1 = -1D;
        LivingEntity entityliving = null;
        List list = world.getEntities(this, boundingBox.expand(d, d, d));
        for(int i = 0; i < list.size(); i++)
        {
            Entity entity1 = (Entity)list.get(i);
            if(!(entity1 instanceof LivingEntity) || (entity1 instanceof EntityBird))
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

    private boolean FlyToNextEntity(Entity entity)
    {
        if(entity != null)
        {
            int i = MathHelper.floor(entity.x);
            int j = MathHelper.floor(entity.y);
            int k = MathHelper.floor(entity.z);
            faceTreeTop(i, j, k, 30F);
            if(MathHelper.floor(y) < j)
            {
                velocityY += 0.14999999999999999D;
            }
            if(x < entity.x)
            {
                double d = entity.x - x;
                if(d > 0.5D)
                {
                    velocityX += 0.050000000000000003D;
                }
            } else
            {
                double d1 = x - entity.x;
                if(d1 > 0.5D)
                {
                    velocityX -= 0.050000000000000003D;
                }
            }
            if(z < entity.z)
            {
                double d2 = entity.z - z;
                if(d2 > 0.5D)
                {
                    velocityZ += 0.050000000000000003D;
                }
            } else
            {
                double d3 = z - entity.z;
                if(d3 > 0.5D)
                {
                    velocityZ -= 0.050000000000000003D;
                }
            }
            return true;
        } else
        {
            return false;
        }
    }

    private void WingFlap()
    {
        velocityY += 0.050000000000000003D;
        if(random.nextInt(30) == 0)
        {
            velocityX += 0.20000000000000001D;
        }
        if(random.nextInt(30) == 0)
        {
            velocityX -= 0.20000000000000001D;
        }
        if(random.nextInt(30) == 0)
        {
            velocityZ += 0.20000000000000001D;
        }
        if(random.nextInt(30) == 0)
        {
            velocityZ -= 0.20000000000000001D;
        }
    }

    private boolean FlyToNextTree()
    {
        int ai[] = ReturnNearestMaterialCoord(this, Material.LEAVES, Double.valueOf(20D));
        int ai1[] = FindTreeTop(ai[0], ai[1], ai[2]);
        if(ai1[1] != 0)
        {
            int i = ai1[0];
            int j = ai1[1];
            int k = ai1[2];
            faceTreeTop(i, j, k, 30F);
            if(j - MathHelper.floor(y) > 2)
            {
                velocityY += 0.14999999999999999D;
            }
            int l = 0;
            int i1 = 0;
            if(x < (double)i)
            {
                l = i - MathHelper.floor(x);
                velocityX += 0.050000000000000003D;
            } else
            {
                l = MathHelper.floor(x) - i;
                velocityX -= 0.050000000000000003D;
            }
            if(z < (double)k)
            {
                i1 = k - MathHelper.floor(z);
                velocityZ += 0.050000000000000003D;
            } else
            {
                i1 = MathHelper.floor(x) - k;
                velocityZ -= 0.050000000000000003D;
            }
            double d = l + i1;
            if(d < 3D)
            {
                return true;
            }
        }
        return false;
    }

    public void chooseType(int typeint)
    {
            if(typeint == 1)
            {
                texture = "/assets/mocreatures/stationapi/textures/mob/birdwhite.png";
            } else
            if(typeint == 2)
            {
                texture = "/assets/mocreatures/stationapi/textures/mob/birdblack.png";
            } else
            if(typeint == 3)
            {
                texture = "/assets/mocreatures/stationapi/textures/mob/birdgreen.png";
            } else
            if(typeint == 4)
            {
                texture = "/assets/mocreatures/stationapi/textures/mob/birdblue.png";
            } else
            if(typeint == 5)
            {
                texture = "/assets/mocreatures/stationapi/textures/mob/birdyellow.png";
            } else
            if(typeint == 6)
            {
                texture = "/assets/mocreatures/stationapi/textures/mob/birdred.png";
            }
    }

    public int getRandomRace(){
        int i = random.nextInt(100);
        if(i <= 15)
        {
            return 1;
        } else
        if(i <= 30)
        {
            return 2;
        } else
        if(i <= 45)
        {
            return 3;
        } else
        if(i <= 60)
        {
            return 4;
        } else
        if(i <= 75)
        {
            return 5;
        } else
        if(i <= 90)
        {
            return 6;
        } else
        {
            return 2;
        }
    }

    public void faceTreeTop(int i, int j, int k, float f)
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

    private float b(float f, float f1, float f2)
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

    private int[] FindTreeTop(int i, int j, int k)
    {
        int l = i - 5;
        int i1 = k - 5;
        int j1 = i + 5;
        int k1 = j + 7;
        int l1 = k + 5;
        for(int i2 = l; i2 < j1; i2++)
        {
            label0:
            for(int j2 = i1; j2 < l1; j2++)
            {
                int k2 = world.getBlockId(i2, j, j2);
                if(k2 == 0 || Block.BLOCKS[k2].material != Material.WOOD)
                {
                    continue;
                }
                int l2 = j;
                do
                {
                    if(l2 >= k1)
                    {
                        continue label0;
                    }
                    int i3 = world.getBlockId(i2, l2, j2);
                    if(i3 == 0)
                    {
                        return (new int[] {
                                i2, l2 + 2, j2
                        });
                    }
                    l2++;
                } while(true);
            }

        }

        return (new int[] {
                0, 0, 0
        });
    }

    public int[] ReturnNearestMaterialCoord(Entity entity, Material material, Double double1)
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
                    if(j2 != 0 && Block.BLOCKS[j2].material == material)
                    {
                        return (new int[] {
                                k1, l1, i2
                        });
                    }
                }

            }

        }

        return (new int[] {
                -1, 0, 0
        });
    }

    protected int getDroppedItemId()
    {
        if(random.nextInt(2) == 0)
        {
            return Item.FEATHER.id;
        } else
        {
            return Item.SEEDS.id;
        }
    }

    public void writeNbt(NbtCompound nbttagcompound)
    {
        super.writeNbt(nbttagcompound);
        nbttagcompound.putInt("TypeInt", getType());
        nbttagcompound.putBoolean("HasReproduced", getReproduced());
        nbttagcompound.putBoolean("Tamed", getTamed());
    }

    public void readNbt(NbtCompound nbttagcompound)
    {
        super.readNbt(nbttagcompound);
        setReproduced(nbttagcompound.getBoolean("HasReproduced"));
        setTamed(nbttagcompound.getBoolean("Tamed"));
        setType(nbttagcompound.getInt("TypeInt"));
    }

    protected String getRandomSound()
    {
        if(getType() == 1)
        {
            return "mocreatures:birdwhite";
        }
        if(getType() == 2)
        {
            return "mocreatures:birdblack";
        }
        if(getType() == 3)
        {
            return "mocreatures:birdgreen";
        }
        if(getType() == 4)
        {
            return "mocreatures:birdblue";
        }
        if(getType() == 5)
        {
            return "mocreatures:birdyellow";
        } else
        {
            return "mocreatures:birdred";
        }
    }

    protected String getHurtSound()
    {
        return "mocreatures:birdhurt";
    }

    protected String getDeathSound()
    {
        return "mocreatures:birddying";
    }

    public boolean canSpawn()
    {
        return mocr.mocreaturesGlass.animals.birdfreq > 0 && super.canSpawn();
    }

    mod_mocreatures mocr = new mod_mocreatures();

    public float wingb;
    public float wingc;
    public float wingd;
    public float winge;
    public float wingh;

//    public boolean tamed;
//    public boolean picked;
//    private boolean hasreproduced;
//    public int typeint;
//    private boolean fleeing;

    public boolean typechosen;

    @Override
    public Identifier getHandlerIdentifier() {
        return Identifier.of(mod_mocreatures.MOD_ID, "Bird");
    }

    //TYPE
    public void setTypeSpawn()
    {
        if(!world.isRemote){
            int type = getRandomRace();
            setType(type);
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

    //Tamed
    public boolean getTamed()
    {
        return (dataTracker.getByte(17) & 1) != 0;
    }

    public void setTamed(boolean flag)
    {
        if(flag)
        {
            dataTracker.set(17, (byte) 1);
        } else
        {
            dataTracker.set(17, (byte) 0);
        }
    }
    //Picked
    public boolean getPicked()
    {
        return (dataTracker.getByte(18) & 1) != 0;
    }

    public void setPicked(boolean flag)
    {
        if(flag)
        {
            dataTracker.set(18, (byte) 1);
        } else
        {
            dataTracker.set(18, (byte) 0);
        }
    }
    //Picked
    public boolean getReproduced()
    {
        return (dataTracker.getByte(19) & 1) != 0;
    }

    public void setReproduced(boolean flag)
    {
        if(flag)
        {
            dataTracker.set(19, (byte) 1);
        } else
        {
            dataTracker.set(19, (byte) 0);
        }
    }
    //Picked
    public boolean getFleeing()
    {
        return (dataTracker.getByte(20) & 1) != 0;
    }

    public void setFleeing(boolean flag)
    {
        if(flag)
        {
            dataTracker.set(20, (byte) 1);
        } else
        {
            dataTracker.set(20, (byte) 0);
        }
    }
}

