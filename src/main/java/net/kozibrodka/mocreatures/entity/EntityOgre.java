// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) braces deadcode 

package net.kozibrodka.mocreatures.entity;

import net.kozibrodka.mocreatures.events.mod_mocreatures;
import net.kozibrodka.mocreatures.mocreatures.Destroyer;
import net.minecraft.block.Block;
import net.minecraft.class_65;
import net.minecraft.entity.Entity;
import net.minecraft.entity.mob.MonsterEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NbtCompound;
import net.minecraft.world.World;
import net.modificationstation.stationapi.api.util.Identifier;
import net.modificationstation.stationapi.api.server.entity.MobSpawnDataProvider;

public class EntityOgre extends MonsterEntity
    implements class_65, MobSpawnDataProvider
{

    public EntityOgre(World world)
    {
        super(world);
        field_547 = 3;
        attackRange = mocr.mocreaturesGlass.hostilemobs.ogrerange;
        ogreboolean = false;
        texture = "/assets/mocreatures/stationapi/textures/mob/ogre.png";
        setBoundingBoxSpacing(1.5F, 4F);
        health = 35;
        bogrefire = false;
        ogreattack = false;
        ogrehasenemy = false;
        destroyForce = mocr.mocreaturesGlass.hostilemobs.ogreStrength;
        fireImmune = false;
        frequencyA = 30;
    }

    public void writeNbt(NbtCompound nbttagcompound)
    {
        super.writeNbt(nbttagcompound);
        nbttagcompound.putBoolean("OgreBoolean", ogreboolean);
        nbttagcompound.putBoolean("OgreAttack", ogreattack);
    }

    public void readNbt(NbtCompound nbttagcompound)
    {
        super.readNbt(nbttagcompound);
        ogreboolean = nbttagcompound.getBoolean("OgreBoolean");
        ogreattack = nbttagcompound.getBoolean("OgreAttack");
    }

    protected String method_911()
    {
        return "mocreatures:ogre";
    }

    protected String method_912()
    {
        return "mocreatures:ogrehurt";
    }

    protected String method_913()
    {
        return "mocreatures:ogredying";
    }

    protected int method_914()
    {
        return Block.OBSIDIAN.id;
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
        float f = method_1394(1.0F);
        if(f < 0.5F)
        {
            PlayerEntity entityplayer = world.method_186(this, attackRange);
            if(entityplayer != null && world.field_213 > 0)
            {
                ogrehasenemy = true;
                return entityplayer;
            }
        }
        ogrehasenemy = false;
        return null;
    }

    public boolean damage(Entity entityBase, int i)
    {
        if(super.damage(entityBase, i))
        {
            if(field_1594 == entityBase || field_1595 == entityBase)
            {
                return true;
            }
            if(entityBase != this && world.field_213 > 0)
            {
                target = entityBase;
                ogrehasenemy = true;
            }
            return true;
        } else
        {
            return false;
        }
    }

    public void method_937()
    {
        destroyForce = mocr.mocreaturesGlass.hostilemobs.ogreStrength;
        attackRange = mocr.mocreaturesGlass.hostilemobs.ogrerange;
        if(ogrehasenemy && random.nextInt(frequencyA) == 0)
        {
            ogreattack = true;
            field_1042 = 15;
        }
        super.method_937();
    }

    public void onLivingUpdate2()
    {
        super.method_937();
    }

    protected void method_637(Entity entity, float f)
    {
        if((double)f < 2.5D && entity.boundingBox.maxY > boundingBox.minY && entity.boundingBox.minY < boundingBox.maxY && world.field_213 > 0)
        {
            entity.damage(this, field_547);
        }
    }

    public void DestroyingOgre()
    {
        Destroyer.DestroyBlast(world, this, x, y + 1.0D, z, destroyForce, bogrefire, mocr.mocreaturesGlass.hostilemobs.igniteogre, mocr.mocreaturesGlass.hostilemobs.explodeogre, mocr.mocreaturesGlass.hostilemobs.explodecaveogre, mocr.mocreaturesGlass.hostilemobs.explodefireogre);
    }

    public void markDead()
    {
        super.markDead();
    }

    public boolean canSpawn()
    {
        return mocr.mocreaturesGlass.hostilemobs.ogrefreq > 0 && world.field_213 >= mocr.mocreaturesGlass.hostilemobs.ogreSpawnDifficulty + 1 && super.canSpawn();
    }

    public boolean d2()
    {
        return super.canSpawn();
    }

    public int method_916()
    {
        return 3;
    }

    protected void method_933()
    {
        int i = random.nextInt(3) + 1;
        for(int j = 0; j < i; j++)
        {
            method_1327(new ItemStack(method_914(), 1, 0), 0.0F);
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

    @Override
    public Identifier getHandlerIdentifier() {
        return Identifier.of(mod_mocreatures.MOD_ID, "Ogre");
    }
}
