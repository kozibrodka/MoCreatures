
package net.kozibrodka.mocreatures.entity;

import net.kozibrodka.mocreatures.events.mod_mocreatures;
import net.kozibrodka.mocreatures.mocreatures.MoCreatureRacial;
import net.minecraft.entity.Entity;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.mob.MonsterEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.projectile.ArrowEntity;
import net.minecraft.item.Item;
import net.minecraft.nbt.NbtCompound;
import net.minecraft.util.math.Box;
import net.minecraft.world.World;
import net.modificationstation.stationapi.api.util.Identifier;
import net.modificationstation.stationapi.api.server.entity.MobSpawnDataProvider;

import java.util.Iterator;
import java.util.List;

public class EntityRat extends MonsterEntity implements MobSpawnDataProvider, MoCreatureRacial
{

    public EntityRat(World world)
    {
        super(world);
        setBoundingBoxSpacing(0.5F, 0.5F);
        health = 10;
        attackDamage = 1;
        typechosen = false;
    }

    public void chooseType(int type)
    {
            if(type == 1)
            {
                texture = "/assets/mocreatures/stationapi/textures/mob/blackrat.png";
            } else
            if(type == 2)
            {
                texture = "/assets/mocreatures/stationapi/textures/mob/lightrat.png";
            } else
            if(type == 3)
            {
                texture = "/assets/mocreatures/stationapi/textures/mob/whiterat.png";
            }
            if(type == 4)
            {
                texture = "/assets/mocreatures/stationapi/textures/mob/hellrat.png";
            }
    }

    public int getRandomRace()
    {
        if(fireImmune){
            return 4;
        }
        int i = random.nextInt(100);
        if(i <= 65)
        {
            return  1;
        } else
        if(i <= 98)
        {
            return 2;
        } else
        {
            return 3;
        }
    }

    @Override
    public boolean damage(Entity entityBase, int i)
    {
        if(super.damage(entityBase, i))
        {
            if(entityBase instanceof PlayerEntity)
            {
                target = entityBase;
            }
            if((entityBase instanceof ArrowEntity) && ((ArrowEntity)entityBase).owner != null)
            {
                entityBase = ((ArrowEntity)entityBase).owner;
            }
            if(entityBase instanceof LivingEntity)
            {
                List list = world.collectEntitiesByClass(EntityRat.class, Box.createCached(x, y, z, x + 1.0D, y + 1.0D, z + 1.0D).expand(16D, 4D, 16D));
                Iterator iterator = list.iterator();
                do
                {
                    if(!iterator.hasNext())
                    {
                        break;
                    }
                    Entity entity1 = (Entity)iterator.next();
                    EntityRat entityrat = (EntityRat)entity1;
                    if(entityrat != null && entityrat.target == null)
                    {
                        entityrat.target = entityBase;
                    }
                } while(true);
            }
            return true;
        } else
        {
            return false;
        }
    }

    @Override
    protected void attack(Entity entityBase, float f)
    {
        float f1 = getBrightnessAtEyes(1.0F);
        if(f1 > 0.5F && random.nextInt(100) == 0)
        {
            target = null;
        } else
        {
            super.attack(entityBase, f);
        }
    }

    @Override
    public void tickMovement() {
        super.tickMovement();
        if(!typechosen && world.isRemote && getType() != 0){
            typechosen = true;
            chooseType(getType());
        }
        if(world.isRemote){
            return;
        }
        setClimbing(climbing());
    }

    @Override
    protected Entity getTargetInRange()
    {
        float f = getBrightnessAtEyes(1.0F);
        if(f < 0.5F)
        {
            return world.getClosestPlayer(this, 16D);
        } else
        {
            return null;
        }
    }

    @Override
    public int getLimitPerChunk()
    {
        return 5;
    }

    @Override
    protected void initDataTracker() {
        super.initDataTracker();
        dataTracker.startTracking(16, (byte) 0); //Type
        dataTracker.startTracking(17, (byte) 0); //Climbing
    }

    @Override
    public void writeNbt(NbtCompound nbttagcompound)
    {
        super.writeNbt(nbttagcompound);
        nbttagcompound.putInt("TypeInt", getType());
    }

    @Override
    public void readNbt(NbtCompound nbttagcompound)
    {
        super.readNbt(nbttagcompound);
        setType(nbttagcompound.getInt("TypeInt"));
    }

    @Override
    public boolean canSpawn()
    {
        return mod_mocreatures.mocGlass.hostilemobs.ratfreq > 0 && super.canSpawn();
    }

    public boolean canSpawn2()
    {
        return super.canSpawn();
    }

    @Override
    protected String getRandomSound()
    {
        return "mocreatures:ratgrunt";
    }

    @Override
    protected String getHurtSound()
    {
        return "mocreatures:rathurt";
    }

    @Override
    protected String getDeathSound()
    {
        return "mocreatures:ratdying";
    }

    @Override
    protected int getDroppedItemId()
    {
        return Item.COAL.id;
    }

    @Override
    public boolean isOnLadder()
    {
        return horizontalCollision;
    }

    public boolean climbing()
    {
        return !onGround && isOnLadder();
    }

    public boolean typechosen;

    @Override
    public Identifier getHandlerIdentifier() {
        return Identifier.of(mod_mocreatures.MOD_ID, "Rat");
    }

    //TYPE
    @Override
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

    //Climbing
    public boolean getClimbing()
    {
        return (dataTracker.getByte(17) & 1) != 0;
    }

    public void setClimbing(boolean flag)
    {
        if(flag)
        {
            dataTracker.set(17, (byte) 1);
        } else
        {
            dataTracker.set(17, (byte) 0);
        }
    }
}
