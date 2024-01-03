package net.kozibrodka.mocreatures.entity;


import net.kozibrodka.mocreatures.events.mod_mocreatures;
import net.kozibrodka.mocreatures.mixin.EntityBaseAccesor;
import net.minecraft.block.BlockBase;
import net.minecraft.block.material.Material;
import net.minecraft.entity.EntityBase;
import net.minecraft.entity.Item;
import net.minecraft.entity.Living;
import net.minecraft.entity.animal.AnimalBase;
import net.minecraft.entity.player.PlayerBase;
import net.minecraft.item.ItemBase;
import net.minecraft.level.Level;
import net.minecraft.util.io.CompoundTag;
import net.minecraft.util.maths.Box;
import net.minecraft.util.maths.MathHelper;
import net.modificationstation.stationapi.api.util.Identifier;
import net.modificationstation.stationapi.api.server.entity.MobSpawnDataProvider;

import java.util.List;


public class EntityBird extends AnimalBase implements MobSpawnDataProvider
{

    public EntityBird(Level world)
    {
        super(world);
        texture = "/assets/mocreatures/stationapi/textures/mob/birdblue.png";
        setSize(0.4F, 0.3F);
        health = 2;
        field_1625 = true;
        wingb = 0.0F;
        wingc = 0.0F;
        wingh = 1.0F;
        fleeing = false;
        tamed = false;
        typeint = 0;
        typechosen = false;
        hasreproduced = false;
        field_1045 = true;
    }

    protected void handleFallDamage(float f)
    {
    }

    public int getLimitPerChunk()
    {
        return 6;
    }

    public void updateDespawnCounter()
    {
        super.updateDespawnCounter();
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
        Living entityliving = getClosestEntityLiving(this, 4D);
        if(entityliving != null && !tamed && method_928(entityliving))
        {
            fleeing = true;
        }
        if(rand.nextInt(300) == 0)
        {
            fleeing = true;
        }
        if(fleeing)
        {
            if(FlyToNextTree())
            {
                fleeing = false;
            }
            int ai[] = ReturnNearestMaterialCoord(this, Material.LEAVES, Double.valueOf(16D));
            if(ai[0] == -1)
            {
                for(int i = 0; i < 2; i++)
                {
                    WingFlap();
                }

                fleeing = false;
            }
            if(rand.nextInt(50) == 0)
            {
                fleeing = false;
            }
        }
        if(!fleeing)
        {
            Item entityitem = getClosestSeeds(this, 12D);
            if(entityitem != null)
            {
                FlyToNextEntity(entityitem);
                Item entityitem1 = getClosestSeeds(this, 1.0D);
                if(rand.nextInt(50) == 0 && entityitem1 != null)
                {
                    entityitem1.remove();
                    tamed = true;
                }
            }
        }
    }

    protected void tickHandSwing()
    {
        if(onGround && rand.nextInt(10) == 0 && (velocityX > 0.050000000000000003D || velocityZ > 0.050000000000000003D || velocityX < -0.050000000000000003D || velocityZ < -0.050000000000000003D))
        {
            velocityY = 0.25D;
        }
        if(vehicle != null && (vehicle instanceof PlayerBase))
        {
            PlayerBase entityplayer = (PlayerBase)vehicle;
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
        if(!fleeing || !picked)
        {
            super.tickHandSwing();
        } else
        if(onGround)
        {
            picked = false;
        }
    }

    public void remove()
    {
        if(tamed && health > 0)
        {
            return;
        } else
        {
            super.remove();
            return;
        }
    }

    public boolean interact(PlayerBase entityplayer)
    {
        if(!tamed)
        {
            return false;
        }
        yaw = entityplayer.yaw;
        startRiding(entityplayer);
        if(vehicle != null)
        {
            picked = true;
        } else
        {
            level.playSound(this, "mob.chickenplop", 1.0F, (rand.nextFloat() - rand.nextFloat()) * 0.2F + 1.0F);
        }
        velocityX = entityplayer.velocityX * 5D;
        velocityY = entityplayer.velocityY / 2D + 0.5D;
        velocityZ = entityplayer.velocityZ * 5D;
        return true;
    }

    public double getHeightOffset()
    {
        if(vehicle instanceof PlayerBase)
        {
            return (double)(standingEyeHeight - 1.15F);
        } else
        {
            return (double)standingEyeHeight;
        }
    }

    private Item getClosestSeeds(EntityBase entity, double d)
    {
        double d1 = -1D;
        Item entityitem = null;
        List list = level.getEntities(this, boundingBox.expand(d, d, d));
        for(int i = 0; i < list.size(); i++)
        {
            EntityBase entity1 = (EntityBase)list.get(i);
            if(!(entity1 instanceof Item))
            {
                continue;
            }
            Item entityitem1 = (Item)entity1;
            if(entityitem1.item.itemId != ItemBase.seeds.id)
            {
                continue;
            }
            double d2 = entityitem1.squaredDistanceTo(entity.x, entity.y, entity.z);
            if((d < 0.0D || d2 < d * d) && (d1 == -1D || d2 < d1))
            {
                d1 = d2;
                entityitem = entityitem1;
            }
        }

        return entityitem;
    }

    private Living getClosestEntityLiving(EntityBase entity, double d)
    {
        double d1 = -1D;
        Living entityliving = null;
        List list = level.getEntities(this, boundingBox.expand(d, d, d));
        for(int i = 0; i < list.size(); i++)
        {
            EntityBase entity1 = (EntityBase)list.get(i);
            if(!(entity1 instanceof Living) || (entity1 instanceof EntityBird))
            {
                continue;
            }
            double d2 = entity1.squaredDistanceTo(entity.x, entity.y, entity.z);
            if((d < 0.0D || d2 < d * d) && (d1 == -1D || d2 < d1) && ((Living)entity1).method_928(entity))
            {
                d1 = d2;
                entityliving = (Living)entity1;
            }
        }

        return entityliving;
    }

    private boolean FlyToNextEntity(EntityBase entity)
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
        if(rand.nextInt(30) == 0)
        {
            velocityX += 0.20000000000000001D;
        }
        if(rand.nextInt(30) == 0)
        {
            velocityX -= 0.20000000000000001D;
        }
        if(rand.nextInt(30) == 0)
        {
            velocityZ += 0.20000000000000001D;
        }
        if(rand.nextInt(30) == 0)
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

    public void setType(int i)
    {
        typeint = i;
        typechosen = false;
        chooseType();
    }

    public void chooseType()
    {
        if(typeint == 0)
        {
            int i = rand.nextInt(100);
            if(i <= 15)
            {
                typeint = 1;
            } else
            if(i <= 30)
            {
                typeint = 2;
            } else
            if(i <= 45)
            {
                typeint = 3;
            } else
            if(i <= 60)
            {
                typeint = 4;
            } else
            if(i <= 75)
            {
                typeint = 5;
            } else
            if(i <= 90)
            {
                typeint = 6;
            } else
            {
                typeint = 2;
            }
        }
        if(!typechosen)
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
        typechosen = true;
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
                int k2 = level.getTileId(i2, j, j2);
                if(k2 == 0 || BlockBase.BY_ID[k2].material != Material.WOOD)
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
                    int i3 = level.getTileId(i2, l2, j2);
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

    public int[] ReturnNearestMaterialCoord(EntityBase entity, Material material, Double double1)
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
                    int j2 = level.getTileId(k1, l1, i2);
                    if(j2 != 0 && BlockBase.BY_ID[j2].material == material)
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

    protected int getMobDrops()
    {
        if(rand.nextInt(2) == 0)
        {
            return ItemBase.feather.id;
        } else
        {
            return ItemBase.seeds.id;
        }
    }

    public void writeCustomDataToTag(CompoundTag nbttagcompound)
    {
        super.writeCustomDataToTag(nbttagcompound);
        nbttagcompound.put("TypeInt", typeint);
        nbttagcompound.put("HasReproduced", hasreproduced);
        nbttagcompound.put("Tamed", tamed);
    }

    public void readCustomDataFromTag(CompoundTag nbttagcompound)
    {
        super.readCustomDataFromTag(nbttagcompound);
        hasreproduced = nbttagcompound.getBoolean("HasReproduced");
        tamed = nbttagcompound.getBoolean("Tamed");
        typeint = nbttagcompound.getInt("TypeInt");
    }

    protected String getAmbientSound()
    {
        if(typeint == 1)
        {
            return "mocreatures:birdwhite";
        }
        if(typeint == 2)
        {
            return "mocreatures:birdblack";
        }
        if(typeint == 3)
        {
            return "mocreatures:birdgreen";
        }
        if(typeint == 4)
        {
            return "mocreatures:birdblue";
        }
        if(typeint == 5)
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
    private boolean hasreproduced;
    public int typeint;
    public boolean typechosen;
    private boolean fleeing;
    public float wingb;
    public float wingc;
    public float wingd;
    public float winge;
    public float wingh;
    public boolean tamed;
    public boolean picked;

    @Override
    public Identifier getHandlerIdentifier() {
        return Identifier.of(mod_mocreatures.MOD_ID, "Bird");
    }
}

