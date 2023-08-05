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
import net.minecraft.item.ItemBase;
import net.minecraft.level.Level;
import net.minecraft.util.io.CompoundTag;
import net.minecraft.util.maths.MathHelper;
import net.modificationstation.stationapi.api.registry.Identifier;
import net.modificationstation.stationapi.api.server.entity.MobSpawnDataProvider;

import java.util.List;

public class EntityDeer extends AnimalBase implements MobSpawnDataProvider
{

    public EntityDeer(Level world)
    {
        super(world);
        texture = "/assets/mocreatures/stationapi/textures/mob/deer.png";
        edad = 0.75F;
        setSize(0.9F, 1.3F);
        health = 10;
        adult = true;
        movementSpeed = 1.7F;
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
            if(i <= 20)
            {
                typeint = 1;
            } else
            if(i <= 70)
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
                texture = "/assets/mocreatures/stationapi/textures/mob/deer.png";
                health = 15;
            } else
            if(typeint == 2)
            {
                texture = "/assets/mocreatures/stationapi/textures/mob/deerf.png";
                health = 15;
            } else
            {
                texture = "/assets/mocreatures/stationapi/textures/mob/deerb.png";
                health = 5;
                adult = false;
            }
        }
        setMySpeed(false);
        typechosen = true;
    }

    public void setMySpeed(boolean flag)
    {
        float f = 1.0F;
        if(typeint == 1)
        {
            f = 1.7F;
        } else
        if(typeint == 2)
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

    protected void handleFallDamage(float f)
    {
    }

    public void updateDespawnCounter()
    {
        super.updateDespawnCounter();
        if(typeint == 3 && !adult && rand.nextInt(250) == 0)
        {
            edad += 0.01F;
            if(edad >= 1.3F)
            {
                adult = true;
                int i = rand.nextInt(1);
                setType(i);
            }
        }
        if(rand.nextInt(5) == 0)
        {
            Living entityliving = getBoogey(10D);
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

    public Living getBoogey(double d)
    {
        double d1 = -1D;
        Living entityliving = null;
        List list = level.getEntities(this, boundingBox.expand(d, 4D, d));
        for(int i = 0; i < list.size(); i++)
        {
            EntityBase entity = (EntityBase)list.get(i);
            if((entity instanceof Living) && !(entity instanceof EntityDeer) && ((double)entity.width >= 0.5D || (double)entity.height >= 0.5D))
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

    protected void tickHandSwing()
    {
        if(movementSpeed > 2.0F && onGround && rand.nextInt(30) == 0 && (velocityX > 0.10000000000000001D || velocityZ > 0.10000000000000001D || velocityX < -0.10000000000000001D || velocityZ < -0.10000000000000001D))
        {
            velocityY = 0.59999999999999998D;
        }
        super.tickHandSwing();
    }

    public void writeCustomDataToTag(CompoundTag nbttagcompound)
    {
        super.writeCustomDataToTag(nbttagcompound);
        nbttagcompound.put("TypeInt", typeint);
        nbttagcompound.put("Adult", adult);
        nbttagcompound.put("Edad", edad);
    }

    public void readCustomDataFromTag(CompoundTag nbttagcompound)
    {
        super.readCustomDataFromTag(nbttagcompound);
        adult = nbttagcompound.getBoolean("Adult");
        typeint = nbttagcompound.getInt("TypeInt");
        edad = nbttagcompound.getFloat("Edad");
    }

    protected String getAmbientSound()
    {
        if(!adult)
        {
            return "mocreatures:deerbgrunt";
        } else
        {
            return "mocreatures:deerfgrunt";
        }
    }

    protected String getHurtSound()
    {
        return "mocreatures:deerhurt";
    }

    protected String getDeathSound()
    {
        return "mocreatures:deerdying";
    }

    protected int getMobDrops()
    {
        return ItemBase.rawPorkchop.id;
    }

    public float dajszybkosc(){
        return movementSpeed;
    }

    public int typeint;
    public boolean typechosen;
    public boolean adult;
    public float edad;
    public boolean tamed;

    @Override
    public Identifier getHandlerIdentifier() {
        return Identifier.of(mod_mocreatures.MOD_ID, "Deer");
    }
}
