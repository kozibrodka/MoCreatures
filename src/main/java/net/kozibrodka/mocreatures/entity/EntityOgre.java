// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) braces deadcode 

package net.kozibrodka.mocreatures.entity;

import net.kozibrodka.mocreatures.events.mod_mocreatures;
import net.kozibrodka.mocreatures.mocreatures.Destroyer;
import net.minecraft.block.Block;
import net.minecraft.entity.Entity;
import net.minecraft.entity.Monster;
import net.minecraft.entity.mob.MonsterEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NbtCompound;
import net.minecraft.world.World;
import net.modificationstation.stationapi.api.util.Identifier;
import net.modificationstation.stationapi.api.server.entity.MobSpawnDataProvider;

public class EntityOgre extends MonsterEntity
    implements Monster, MobSpawnDataProvider
{

    public EntityOgre(World world)
    {
        super(world);
        attackDamage = 3;
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

    protected String getRandomSound()
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

    protected int getDroppedItemId()
    {
        return Block.OBSIDIAN.id;
    }

    protected void tickLiving(){
        if(this.target instanceof PlayerEntity){
            PlayerEntity uciekinier = world.getClosestPlayer(this, 16D);
            if(uciekinier == null && target.isAlive()){
                if(random.nextInt(30) == 0)
                {
                    target = null;
                }
            }
        }
        super.tickLiving();
    }

    protected Entity getTargetInRange()
    {
        float f = getBrightnessAtEyes(1.0F);
        if(f < 0.5F)
        {
            PlayerEntity entityplayer = world.getClosestPlayer(this, attackRange);
            if(entityplayer != null && world.difficulty > 0)
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
            if(passenger == entityBase || vehicle == entityBase)
            {
                return true;
            }
            if(entityBase != this && world.difficulty > 0)
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

    public void tickMovement()
    {
        destroyForce = mocr.mocreaturesGlass.hostilemobs.ogreStrength;
        attackRange = mocr.mocreaturesGlass.hostilemobs.ogrerange;
        if(ogrehasenemy && random.nextInt(frequencyA) == 0)
        {
            ogreattack = true;
            attackCooldown = 15;
        }
        super.tickMovement();
    }

    public void onLivingUpdate2()
    {
        super.tickMovement();
    }

    protected void attack(Entity entity, float f)
    {
        if((double)f < 2.5D && entity.boundingBox.maxY > boundingBox.minY && entity.boundingBox.minY < boundingBox.maxY && world.difficulty > 0)
        {
            entity.damage(this, attackDamage);
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
        return mocr.mocreaturesGlass.hostilemobs.ogrefreq > 0 && world.difficulty >= mocr.mocreaturesGlass.hostilemobs.ogreSpawnDifficulty.ordinal() + 1 && super.canSpawn();
    }

    public boolean d2()
    {
        return super.canSpawn();
    }

    public int getLimitPerChunk()
    {
        return 3;
    }

    protected void dropItems()
    {
        int i = random.nextInt(3) + 1;
        for(int j = 0; j < i; j++)
        {
            dropItem(new ItemStack(getDroppedItemId(), 1, 0), 0.0F);
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
