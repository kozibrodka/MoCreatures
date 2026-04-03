
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
        attackRange = mod_mocreatures.mocGlass.hostilemobs.ogrerange;
        texture = "/assets/mocreatures/stationapi/textures/mob/ogre.png";
        setBoundingBoxSpacing(1.5F, 4F);
        health = 35;
        bogrefire = false;
        ogrehasenemy = false;
        destroyForce = mod_mocreatures.mocGlass.hostilemobs.ogreStrength;
        fireImmune = false;
        frequencyA = 30;
    }

    protected void initDataTracker()
    {
        super.initDataTracker();
        dataTracker.startTracking(16, (byte) 0); //Attack
    }

    public void writeNbt(NbtCompound nbttagcompound)
    {
        super.writeNbt(nbttagcompound);
        nbttagcompound.putBoolean("OgreAttack", getOgreAttack());
    }

    public void readNbt(NbtCompound nbttagcompound)
    {
        super.readNbt(nbttagcompound);
        setOgreAttack(nbttagcompound.getBoolean("OgreAttack"));
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
        destroyForce = mod_mocreatures.mocGlass.hostilemobs.ogreStrength; ///Czy musi to byc tu?
        attackRange = mod_mocreatures.mocGlass.hostilemobs.ogrerange;
        if(ogrehasenemy && random.nextInt(frequencyA) == 0 && !world.isRemote)
        {
            setOgreAttack(true);
            attackCooldown = 15;
        }
        if(attackCooldown <= 0 && getOgreAttack() && !world.isRemote)
        {
            setOgreAttack(false);
            DestroyingOgre();
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
        Destroyer.DestroyBlast(world, this, x, y + 1.0D, z, destroyForce, bogrefire, mod_mocreatures.mocGlass.hostilemobs.igniteogre, mod_mocreatures.mocGlass.hostilemobs.explodeogre, mod_mocreatures.mocGlass.hostilemobs.explodecaveogre, mod_mocreatures.mocGlass.hostilemobs.explodefireogre);
    }

    public boolean canSpawn()
    {
        return mod_mocreatures.mocGlass.hostilemobs.ogrefreq > 0 && world.difficulty >= mod_mocreatures.mocGlass.hostilemobs.ogreSpawnDifficulty.ordinal() + 1 && super.canSpawn();
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
    
    public int frequencyA;
    public float destroyForce;
    public boolean ogrehasenemy;
    public boolean bogrefire;
    protected double attackRange;

    @Override
    public Identifier getHandlerIdentifier() {
        return Identifier.of(mod_mocreatures.MOD_ID, "Ogre");
    }

    //DISPLAY NAME
    public boolean getOgreAttack()
    {
        return (dataTracker.getByte(16) & 1) != 0;
    }

    public void setOgreAttack(boolean flag)
    {
        if(flag)
        {
            dataTracker.set(16, Byte.valueOf((byte)1));
        } else
        {
            dataTracker.set(16, Byte.valueOf((byte)0));
        }
    }
}
