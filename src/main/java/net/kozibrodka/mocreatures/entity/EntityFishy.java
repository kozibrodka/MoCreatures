// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) braces deadcode 

package net.kozibrodka.mocreatures.entity;

import net.kozibrodka.mocreatures.events.mod_mocreatures;
import net.kozibrodka.mocreatures.mixin.EntityBaseAccesor;
import net.kozibrodka.mocreatures.mocreatures.MoCreatureRacial;
import net.minecraft.entity.Entity;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.passive.WolfEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NbtCompound;
import net.minecraft.world.World;
import net.modificationstation.stationapi.api.util.Identifier;
import net.modificationstation.stationapi.api.server.entity.MobSpawnDataProvider;

import java.util.List;

public class EntityFishy extends EntityCustomWM implements MobSpawnDataProvider, MoCreatureRacial
{

    public EntityFishy(World world)
    {
        super(world);
        texture = "/assets/mocreatures/stationapi/textures/mob/fishy1.png";
        setBoundingBoxSpacing(0.3F, 0.3F);
        health = 4;
        typeint = 0;
        typechosen = false;
        b = 1.0F;
        adult = false;
        tamed = false;
        field_1045 = true;
    }

    public void setTame()
    {
        tamed = true;
    }

    public boolean istamed()
    {
        return tamed;
    }

    public void method_937()
    {
        super.method_937();
        if(!typechosen && world.isRemote && getType() != 0){
            typechosen = true;
            chooseType(getType());
        }
        if(!adult && random.nextInt(100) == 0)
        {
            b += 0.02F;
            if(b >= 1.0F)
            {
                adult = true;
            }
        }
        if(!ReadyforParenting(this))
        {
            return;
        }
        int i = 0;
        List list = world.getEntities(this, boundingBox.expand(4D, 3D, 4D));
        for(int j = 0; j < list.size(); j++)
        {
            Entity entity = (Entity)list.get(j);
            if(entity instanceof EntityFishy)
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
            if(!(entity1 instanceof EntityFishy) || entity1 == this)
            {
                continue;
            }
            EntityFishy entityfishy = (EntityFishy)entity1;
            if(!ReadyforParenting(this) || !ReadyforParenting(entityfishy) || typeint != entityfishy.typeint)
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
            int l = random.nextInt(3) + 1;
            for(int i1 = 0; i1 < l; i1++)
            {
                EntityFishy entityfishy1 = new EntityFishy(world);
                entityfishy1.method_1340(x, y, z);
                world.method_210(entityfishy1);
                world.playSound(this, "mob.chickenplop", 1.0F, (random.nextFloat() - random.nextFloat()) * 0.2F + 1.0F);
                eaten = false;
                entityfishy.eaten = false;
                gestationtime = 0;
                entityfishy.gestationtime = 0;
                entityfishy1.tamed = true;
                entityfishy1.b = 0.2F;
                entityfishy1.adult = false;
                entityfishy1.setType(typeint);
            }

            break;
        }

    }

    public boolean ReadyforParenting(EntityFishy entityfishy)
    {
        return entityfishy.tamed && entityfishy.eaten && entityfishy.adult;
    }

    protected void method_910(){
        if(this.target instanceof PlayerEntity){
            PlayerEntity uciekinier = world.method_186(this, 16D);
            if(uciekinier == null && target.isAlive()){
                if(random.nextInt(30) == 0)
                {
                    target = null;
                }
            }
        }
        super.method_910();
    }

    protected Entity method_638()
    {
        if(world.field_213 > 0 && b >= 1.0F && typeint == 10)
        {
            PlayerEntity entityplayer = world.method_186(this, 16D);
            if(entityplayer != null && ((EntityBaseAccesor)entityplayer).getField_1612() && !tamed)
            {
                return entityplayer;
            }
            if(random.nextInt(30) == 0)
            {
                LivingEntity entityliving = FindTarget(this, 16D);
                if(entityliving != null && ((EntityBaseAccesor)entityliving).getField_1612())
                {
                    return entityliving;
                }
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
            if(!(entity1 instanceof LivingEntity) || (entity1 instanceof EntityCustomWM) || (entity1 instanceof EntitySharkEgg) || (entity1 instanceof EntityFishyEgg) || (entity1 instanceof PlayerEntity) || (entity1 instanceof EntityHorse) && !mocr.mocreaturesGlass.huntercreatures.attackhorses || (entity1 instanceof WolfEntity) && !mocr.mocreaturesGlass.huntercreatures.attackwolves)
            {
                continue;
            }
            double d2 = entity1.method_1347(entity.x, entity.y, entity.z);
            if((d < 0.0D || d2 < d * d) && (d1 == -1D || d2 < d1) && ((LivingEntity)entity1).method_928(entity))
            {
                d1 = d2;
                entityliving = (LivingEntity)entity1;
            }
        }

        return entityliving;
    }

    public void markDead()
    {
        if(tamed && health > 0)
        {
            return;
        } else
        {
            super.markDead();
            return;
        }
    }

    public boolean damage(Entity entityBase, int i)
    {
        if(super.damage(entityBase, i))
        {
            if(field_1594 == entityBase || field_1595 == entityBase)
            {
                return true;
            }
            if(entityBase != this)
            {
                target = entityBase;
            }
            return true;
        } else
        {
            return false;
        }
    }

    protected void method_637(Entity entity, float f)
    {
        if((double)f < 2D && entity.boundingBox.maxY > boundingBox.minY && entity.boundingBox.minY < boundingBox.maxY)
        {
            field_1042 = 20;
            entity.damage(this, 1);
        }
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
            int i = random.nextInt(100);
            if(i <= 9)
            {
                typeint = 1;
            } else
            if(i <= 19)
            {
                typeint = 2;
            } else
            if(i <= 29)
            {
                typeint = 3;
            } else
            if(i <= 39)
            {
                typeint = 4;
            } else
            if(i <= 49)
            {
                typeint = 5;
            } else
            if(i <= 59)
            {
                typeint = 6;
            } else
            if(i <= 69)
            {
                typeint = 7;
            } else
            if(i <= 79)
            {
                typeint = 8;
            } else
            if(i <= 89)
            {
                typeint = 9;
            } else
            {
                typeint = 10;
            }
            if(mocr.mocreaturesGlass.watermobs.spawnpiranha && typeint == 10)
            {
                typeint = 1;
            }
        }
        if(!typechosen)
        {
            if(typeint == 1)
            {
                texture = "/assets/mocreatures/stationapi/textures/mob/fishy1.png";
            } else
            if(typeint == 2)
            {
                texture = "/assets/mocreatures/stationapi/textures/mob/fishy2.png";
            } else
            if(typeint == 3)
            {
                texture = "/assets/mocreatures/stationapi/textures/mob/fishy3.png";
            } else
            if(typeint == 4)
            {
                texture = "/assets/mocreatures/stationapi/textures/mob/fishy4.png";
            } else
            if(typeint == 5)
            {
                texture = "/assets/mocreatures/stationapi/textures/mob/fishy5.png";
            } else
            if(typeint == 6)
            {
                texture = "/assets/mocreatures/stationapi/textures/mob/fishy6.png";
            } else
            if(typeint == 7)
            {
                texture = "/assets/mocreatures/stationapi/textures/mob/fishy7.png";
            } else
            if(typeint == 8)
            {
                texture = "/assets/mocreatures/stationapi/textures/mob/fishy8.png";
            } else
            if(typeint == 9)
            {
                texture = "/assets/mocreatures/stationapi/textures/mob/fishy9.png";
            } else
            if(typeint == 10)
            {
                texture = "/assets/mocreatures/stationapi/textures/mob/fishy10.png";
            }
        }
        typechosen = true;
    }

    protected void method_933()
    {
        int i = random.nextInt(100);
        if(i < 70 && adult)
        {
            method_1327(new ItemStack(Item.RAW_FISH.id, 1, 0), 0.0F);
        } else
        {
            int j = random.nextInt(2) + 1;
            for(int k = 0; k < j; k++)
            {
                method_1327(new ItemStack(mod_mocreatures.fishyegg, 1, 0), 0.0F);
            }

        }
    }

    public void writeNbt(NbtCompound nbttagcompound)
    {
        super.writeNbt(nbttagcompound);
        nbttagcompound.putBoolean("Tamed", tamed);
        nbttagcompound.putInt("TypeInt", typeint);
        nbttagcompound.putFloat("Age", b);
        nbttagcompound.putBoolean("Adult", adult);
    }

    public void readNbt(NbtCompound nbttagcompound)
    {
        super.readNbt(nbttagcompound);
        tamed = nbttagcompound.getBoolean("Tamed");
        typeint = nbttagcompound.getInt("TypeInt");
        b = nbttagcompound.getFloat("Age");
        adult = nbttagcompound.getBoolean("Adult");
    }

    public boolean canSpawn()
    {
        return mocr.mocreaturesGlass.watermobs.fishfreq > 0 && super.canSpawn();
    }

    mod_mocreatures mocr = new mod_mocreatures();

    public int typeint;
    public boolean typechosen;
    public float b;
    public boolean adult;
    public boolean tamed;
    public int gestationtime;
    public boolean eaten;
    public boolean hungry;

    @Override
    public Identifier getHandlerIdentifier() {
        return Identifier.of(mod_mocreatures.MOD_ID, "Fishy");
    }
}
