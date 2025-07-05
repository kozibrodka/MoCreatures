// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) braces deadcode 

package net.kozibrodka.mocreatures.entity;

import net.kozibrodka.mocreatures.events.mod_mocreatures;
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

public class EntityRat extends MonsterEntity implements MobSpawnDataProvider
{

    public EntityRat(World world)
    {
        super(world);
        texture = "/assets/mocreatures/stationapi/textures/mob/blackrat.png";
        setBoundingBoxSpacing(0.5F, 0.5F);
        health = 10;
        attackDamage = 1;
    }

    public void chooseType()
    {
        if(typeint == 0)
        {
            int i = random.nextInt(100);
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

    protected void attack(Entity entityBase, float f)
    {
        float f1 = getBrightnessAtEyes(1.0F);
        if(f1 > 0.5F && random.nextInt(100) == 0)
        {
            target = null;
            return;
        } else
        {
            super.attack(entityBase, f);
            return;
        }
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
            return world.getClosestPlayer(this, 16D);
        } else
        {
            return null;
        }
    }

    public int getLimitPerChunk()
    {
        return 5;
    }

    public void writeNbt(NbtCompound nbttagcompound)
    {
        super.writeNbt(nbttagcompound);
        nbttagcompound.putInt("TypeInt", typeint);
    }

    public void readNbt(NbtCompound nbttagcompound)
    {
        super.readNbt(nbttagcompound);
        typeint = nbttagcompound.getInt("TypeInt");
    }

    public boolean canSpawn()
    {
        return mocr.mocreaturesGlass.hostilemobs.ratfreq > 0 && super.canSpawn();
    }

    protected String getRandomSound()
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

    protected int getDroppedItemId()
    {
        return Item.COAL.id;
    }

    public boolean isOnLadder()
    {
        return horizontalCollision;
    }

    public boolean climbing()
    {
        return !onGround && isOnLadder();
    }

    mod_mocreatures mocr = new mod_mocreatures();
    public int typeint;
    public boolean typechosen;

    @Override
    public Identifier getHandlerIdentifier() {
        return Identifier.of(mod_mocreatures.MOD_ID, "Rat");
    }
}
