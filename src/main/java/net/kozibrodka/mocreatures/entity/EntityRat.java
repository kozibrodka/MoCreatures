// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) braces deadcode 

package net.kozibrodka.mocreatures.entity;

import net.kozibrodka.mocreatures.events.mod_mocreatures;
import net.minecraft.entity.EntityBase;
import net.minecraft.entity.Living;
import net.minecraft.entity.monster.MonsterBase;
import net.minecraft.entity.player.PlayerBase;
import net.minecraft.entity.projectile.Arrow;
import net.minecraft.item.ItemBase;
import net.minecraft.level.Level;
import net.minecraft.util.io.CompoundTag;
import net.minecraft.util.maths.Box;
import net.modificationstation.stationapi.api.util.Identifier;
import net.modificationstation.stationapi.api.server.entity.MobSpawnDataProvider;

import java.util.Iterator;
import java.util.List;

public class EntityRat extends MonsterBase implements MobSpawnDataProvider
{

    public EntityRat(Level world)
    {
        super(world);
        texture = "/assets/mocreatures/stationapi/textures/mob/blackrat.png";
        setSize(0.5F, 0.5F);
        health = 10;
        attackDamage = 1;
    }

    public void chooseType()
    {
        if(typeint == 0)
        {
            int i = rand.nextInt(100);
            if(i <= 65)
            {
                typeint = 1;
            } else
            if(i <= 98)
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
                texture = "/assets/mocreatures/stationapi/textures/mob/blackrat.png";
            } else
            if(typeint == 2)
            {
                texture = "/assets/mocreatures/stationapi/textures/mob/lightrat.png";
            } else
            if(typeint == 3)
            {
                texture = "/assets/mocreatures/stationapi/textures/mob/whiterat.png";
            }
        }
        typechosen = true;
    }

    public boolean damage(EntityBase entityBase, int i)
    {
        if(super.damage(entityBase, i))
        {
            if(entityBase instanceof PlayerBase)
            {
                entity = entityBase;
            }
            if((entityBase instanceof Arrow) && ((Arrow)entityBase).owner != null)
            {
                entityBase = ((Arrow)entityBase).owner;
            }
            if(entityBase instanceof Living)
            {
                List list = level.getEntities(EntityRat.class, Box.createButWasteMemory(x, y, z, x + 1.0D, y + 1.0D, z + 1.0D).expand(16D, 4D, 16D));
                Iterator iterator = list.iterator();
                do
                {
                    if(!iterator.hasNext())
                    {
                        break;
                    }
                    EntityBase entity1 = (EntityBase)iterator.next();
                    EntityRat entityrat = (EntityRat)entity1;
                    if(entityrat != null && entityrat.entity == null)
                    {
                        entityrat.entity = entityBase;
                    }
                } while(true);
            }
            return true;
        } else
        {
            return false;
        }
    }

    protected void tryAttack(EntityBase entityBase, float f)
    {
        float f1 = getBrightnessAtEyes(1.0F);
        if(f1 > 0.5F && rand.nextInt(100) == 0)
        {
            entity = null;
            return;
        } else
        {
            super.tryAttack(entityBase, f);
            return;
        }
    }

    protected void tickHandSwing(){
        if(this.entity instanceof PlayerBase){
            PlayerBase uciekinier = level.getClosestPlayerTo(this, 16D);
            if(uciekinier == null && entity.isAlive()){
                if(rand.nextInt(30) == 0)
                {
                    entity = null;
                }
            }
        }
        super.tickHandSwing();
    }

    protected EntityBase getAttackTarget()
    {
        float f = getBrightnessAtEyes(1.0F);
        if(f < 0.5F)
        {
            return level.getClosestPlayerTo(this, 16D);
        } else
        {
            return null;
        }
    }

    public int getLimitPerChunk()
    {
        return 5;
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

    public boolean canSpawn()
    {
        return mocr.mocreaturesGlass.hostilemobs.ratfreq > 0 && super.canSpawn();
    }

    protected String getAmbientSound()
    {
        return "mocreatures:ratgrunt";
    }

    protected String getHurtSound()
    {
        return "mocreatures:rathurt";
    }

    protected String getDeathSound()
    {
        return "mocreatures:ratdying";
    }

    protected int getMobDrops()
    {
        return ItemBase.coal.id;
    }

    public boolean method_932()
    {
        return field_1624;
    }

    public boolean climbing()
    {
        return !onGround && method_932();
    }

    mod_mocreatures mocr = new mod_mocreatures();
    public int typeint;
    public boolean typechosen;

    @Override
    public Identifier getHandlerIdentifier() {
        return Identifier.of(mod_mocreatures.MOD_ID, "Rat");
    }
}
