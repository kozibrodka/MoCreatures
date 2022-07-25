// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) braces deadcode 

package net.kozibrodka.mocreatures.entity;

import net.kozibrodka.mocreatures.events.mod_mocreatures;
import net.minecraft.block.BlockBase;
import net.minecraft.class_61;
import net.minecraft.entity.EntityBase;
import net.minecraft.entity.Living;
import net.minecraft.entity.animal.AnimalBase;
import net.minecraft.entity.player.PlayerBase;
import net.minecraft.item.ItemBase;
import net.minecraft.level.Level;
import net.minecraft.util.io.CompoundTag;
import net.minecraft.util.maths.Box;
import net.minecraft.util.maths.MathHelper;

import java.util.List;

public class EntityMouse extends AnimalBase
{

    public EntityMouse(Level world)
    {
        super(world);
        texture = "/assets/mocreatures/stationapi/textures/mob/miceg.png";
        setSize(0.3F, 0.3F);
        health = 4;
        picked = false;
    }

    public void chooseType()
    {
        if(typeint == 0)
        {
            int i = rand.nextInt(100);
            if(i <= 50)
            {
                typeint = 1;
            } else
            if(i <= 80)
            {
                typeint = 2;
            } else
            {
                typeint = 3;
            }
        }
        if(!typechosen)
        {
            if(typeint == 1)
            {
                texture = "/assets/mocreatures/stationapi/textures/mob/miceg.png";
            } else
            if(typeint == 2)
            {
                texture = "/assets/mocreatures/stationapi/textures/mob/miceb.png";
            } else
            if(typeint == 3)
            {
                texture = "/assets/mocreatures/stationapi/textures/mob/micew.png";
            }
        }
        typechosen = true;
    }

    public void updateDespawnCounter()
    {
        super.updateDespawnCounter();
        if(rand.nextInt(15) == 0)
        {
            Living entityliving = getBoogey(6D);
            if(entityliving != null)
            {
                runLikeHell(entityliving);
            }
        }
        if(!onGround && vehicle != null)
        {
            yaw = vehicle.yaw;
        }
    }

    private void checkFertility()
    {
        int i = 0;
        List list = level.getEntities(EntityMouse.class, Box.createButWasteMemory(x, y, z, x + 1.0D, y + 1.0D, z + 1.0D).expand(16D, 4D, 16D));
        for(int j = 0; j < list.size(); j++)
        {
            i++;
        }

        if(i > 10)
        {
            fertile = false;
        }
    }

    private void reproduce()
    {
    }

    private boolean checkNearCats()
    {
        return true;
    }

    private boolean checkNearRock()
    {
        return true;
    }

    public boolean interact(PlayerBase entityplayer)
    {
        yaw = entityplayer.yaw;
        startRiding(entityplayer);
        if(vehicle != null)
        {
            picked = true;
        } else
        {
            level.playSound(this, "mob.chickenplop", 1.0F, (rand.nextFloat() - rand.nextFloat()) * 0.2F + 1.0F);
            picked = false;
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
            return (double)(standingEyeHeight - 1.7F);
        } else
        {
            return (double)standingEyeHeight;
        }
    }

    public int getLimitPerChunk()
    {
        return 6;
    }

    public void writeCustomDataToTag(CompoundTag nbttagcompound)
    {
        super.writeCustomDataToTag(nbttagcompound);
        nbttagcompound.put("TypeInt", typeint);
    }

    public void readCustomDataFromTag(CompoundTag nbttagcompound)
    {
        super.readCustomDataFromTag(nbttagcompound);
        typeint = nbttagcompound.getInt("TypeInt");
    }

    public Living getBoogey(double d)
    {
        double d1 = -1D;
        Living entityliving = null;
        List list = level.getEntities(this, boundingBox.expand(d, 4D, d));
        for(int i = 0; i < list.size(); i++)
        {
            EntityBase entity = (EntityBase)list.get(i);
            if((entity instanceof Living) && !(entity instanceof EntityMouse))
            {
                entityliving = (Living)entity;
            }
        }

        return entityliving;
    }

    public void runLikeHell(EntityBase entity)
    {
        double d = x - entity.x;
        double d1 = z - entity.z;
        double d2 = Math.atan2(d, d1);
        d2 += (double)(rand.nextFloat() - rand.nextFloat()) * 0.75D;
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
            int i1 = (i + rand.nextInt(4)) - rand.nextInt(4);
            int j1 = (j + rand.nextInt(3)) - rand.nextInt(3);
            int k1 = (k + rand.nextInt(4)) - rand.nextInt(4);
            if(j1 > 4 && (level.getTileId(i1, j1, k1) == 0 || level.getTileId(i1, j1, k1) == BlockBase.SNOW.id) && level.getTileId(i1, j1 - 1, k1) != 0)
            {
                class_61 pathentity = level.method_189(this, i1, j1, k1, 16F);
                setTarget(pathentity);
                break;
            }
            l++;
        } while(true);
    }

    public boolean climbing()
    {
        return !onGround && isOnLadder();
    }

    public boolean isOnLadder()
    {
        return field_1624;
    }

    public boolean upsideDown()
    {
        return picked;
    }

    public boolean canSpawn()
    {
        int i = MathHelper.floor(x);
        int j = MathHelper.floor(boundingBox.minY);
        int k = MathHelper.floor(z);
        return mocr.mocreaturesGlass.animals.micefreq > 0 && level.canSpawnEntity(boundingBox) && level.method_190(this, boundingBox).size() == 0 && !level.method_218(boundingBox) && level.getTileId(i, j - 1, k) == BlockBase.COBBLESTONE.id && mocr.mocreaturesGlass.animals.mouseinhouse || level.getTileId(i, j - 1, k) == BlockBase.WOOD.id && mocr.mocreaturesGlass.animals.mouseinhouse || level.getTileId(i, j - 1, k) == BlockBase.DIRT.id || level.getTileId(i, j - 1, k) == BlockBase.STONE.id  && mocr.mocreaturesGlass.animals.mouseinhouse || level.getTileId(i, j - 1, k) == BlockBase.GRASS.id;
    }

    protected String getAmbientSound()
    {
        return "mocreatures:micegrunt";
    }

    protected String getHurtSound()
    {
        return "mocreatures:micehurt";
    }

    protected String getDeathSound()
    {
        return "mocreatures:micedying";
    }

    protected int getMobDrops()
    {
        return ItemBase.seeds.id;
    }

    mod_mocreatures mocr = new mod_mocreatures();
    public int typeint;
    public boolean typechosen;
    public boolean picked;
    private boolean fertile;
    private int micetimer;
}
