// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) braces deadcode 

package net.kozibrodka.mocreatures.entity;

import net.kozibrodka.mocreatures.events.mod_mocreatures;
import net.kozibrodka.mocreatures.mocreatures.Destroyer;
import net.minecraft.block.BlockBase;
import net.minecraft.entity.EntityBase;
import net.minecraft.entity.monster.MonsterBase;
import net.minecraft.entity.monster.MonsterEntityType;
import net.minecraft.entity.player.PlayerBase;
import net.minecraft.item.ItemInstance;
import net.minecraft.level.Level;
import net.minecraft.util.io.CompoundTag;

public class EntityOgre extends MonsterBase
    implements MonsterEntityType
{

    public EntityOgre(Level world)
    {
        super(world);
        attackDamage = 3;
        attackRange = mocr.mocreaturesGlass.hostilemobs.ogrerange;
        ogreboolean = false;
        texture = "/assets/mocreatures/stationapi/textures/mob/ogre.png";
        setSize(1.5F, 4F);
        health = 35;
        bogrefire = false;
        ogreattack = false;
        ogrehasenemy = false;
        destroyForce = mocr.mocreaturesGlass.hostilemobs.ogreStrength;
        immuneToFire = false;
        frequencyA = 30;
    }

    public void writeCustomDataToTag(CompoundTag nbttagcompound)
    {
        super.writeCustomDataToTag(nbttagcompound);
        nbttagcompound.put("OgreBoolean", ogreboolean);
        nbttagcompound.put("OgreAttack", ogreattack);
    }

    public void readCustomDataFromTag(CompoundTag nbttagcompound)
    {
        super.readCustomDataFromTag(nbttagcompound);
        ogreboolean = nbttagcompound.getBoolean("OgreBoolean");
        ogreattack = nbttagcompound.getBoolean("OgreAttack");
    }

    protected String getAmbientSound()
    {
        return "mocreatures:ogre";
    }

    protected String getHurtSound()
    {
        return "mocreatures:ogrehurt";
    }

    protected String getDeathSound()
    {
        return "mocreatures:ogredying";
    }

    protected int getMobDrops()
    {
        return BlockBase.OBSIDIAN.id;
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
            PlayerBase entityplayer = level.getClosestPlayerTo(this, attackRange);
            if(entityplayer != null && level.difficulty > 0)
            {
                ogrehasenemy = true;
                return entityplayer;
            }
        }
        ogrehasenemy = false;
        return null;
    }

    public boolean damage(EntityBase entityBase, int i)
    {
        if(super.damage(entityBase, i))
        {
            if(passenger == entityBase || vehicle == entityBase)
            {
                return true;
            }
            if(entityBase != this && level.difficulty > 0)
            {
                entity = entityBase;
                ogrehasenemy = true;
            }
            return true;
        } else
        {
            return false;
        }
    }

    public void updateDespawnCounter()
    {
        destroyForce = mocr.mocreaturesGlass.hostilemobs.ogreStrength;
        attackRange = mocr.mocreaturesGlass.hostilemobs.ogrerange;
        if(ogrehasenemy && rand.nextInt(frequencyA) == 0)
        {
            ogreattack = true;
            attackTime = 15;
        }
        super.updateDespawnCounter();
    }

    public void onLivingUpdate2()
    {
        super.updateDespawnCounter();
    }

    protected void tryAttack(EntityBase entity, float f)
    {
        if((double)f < 2.5D && entity.boundingBox.maxY > boundingBox.minY && entity.boundingBox.minY < boundingBox.maxY && level.difficulty > 0)
        {
            entity.damage(this, attackDamage);
        }
    }

    public void DestroyingOgre()
    {
        Destroyer.DestroyBlast(level, this, x, y + 1.0D, z, destroyForce, bogrefire, mocr.mocreaturesGlass.hostilemobs.igniteogre, mocr.mocreaturesGlass.hostilemobs.explodeogre, mocr.mocreaturesGlass.hostilemobs.explodecaveogre, mocr.mocreaturesGlass.hostilemobs.explodefireogre);
    }

    public void remove()
    {
        super.remove();
    }

    public boolean canSpawn()
    {
        return mocr.mocreaturesGlass.hostilemobs.ogrefreq > 0 && level.difficulty >= mocr.mocreaturesGlass.hostilemobs.ogreSpawnDifficulty + 1 && super.canSpawn();
    }

    public boolean d2()
    {
        return super.canSpawn();
    }

    public int getLimitPerChunk()
    {
        return 3;
    }

    protected void getDrops()
    {
        int i = rand.nextInt(3) + 1;
        for(int j = 0; j < i; j++)
        {
            dropItem(new ItemInstance(getMobDrops(), 1, 0), 0.0F);
        }

    }

    mod_mocreatures mocr = new mod_mocreatures();
    public int frequencyA;
    public float destroyForce;
    public boolean ogrehasenemy;
    public boolean ogreattack;
    public boolean bogrefire;
    public boolean ogreboolean;
    protected double attackRange;
}
