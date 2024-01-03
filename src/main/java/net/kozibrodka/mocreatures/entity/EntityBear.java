package net.kozibrodka.mocreatures.entity;


import net.kozibrodka.mocreatures.events.mod_mocreatures;
import net.minecraft.entity.Entity;
import net.minecraft.entity.ItemEntity;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.mob.MonsterEntity;
import net.minecraft.entity.passive.AnimalEntity;
import net.minecraft.entity.passive.WolfEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NbtCompound;
import net.minecraft.world.World;
import net.modificationstation.stationapi.api.server.entity.MobSpawnDataProvider;
import net.modificationstation.stationapi.api.util.Identifier;

import java.util.List;


public class EntityBear extends AnimalEntity implements MobSpawnDataProvider
{

    public EntityBear(World world)
    {
        super(world);
        bearboolean = false;
        texture = "/assets/mocreatures/stationapi/textures/mob/bear.png";
        setBoundingBoxSpacing(0.9F, 1.3F);
        health = 25;
        force = 5;
        attackRange = 16D;
    }

    public void method_937()
    {
        super.method_937();
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
        if(world.field_213 > 0)
        {
            float f = method_1394(1.0F);
            if(f < 0.5F)
            {
                PlayerEntity entityplayer = world.method_186(this, attackRange);
                if(entityplayer != null)
                {
                    return entityplayer;
                }
            }
            if(random.nextInt(80) == 0)
            {
                LivingEntity entityliving = getClosestTarget(this, 10D);
                return entityliving;
            }
        }
        return null;
    }

    public LivingEntity getClosestTarget(Entity entity, double d)
    {
        double d1 = -1D;
        LivingEntity entityliving = null;
        List list = world.getEntities(this, boundingBox.expand(d, d, d));
        for(int i = 0; i < list.size(); i++)
        {
            Entity entity1 = (Entity)list.get(i);
            if(!(entity1 instanceof LivingEntity) || entity1 == entity || entity1 == entity.field_1594 || entity1 == entity.field_1595 || (entity1 instanceof PlayerEntity) || (entity1 instanceof MonsterEntity) || (entity1 instanceof EntityBear) || (entity1 instanceof EntityBigCat) || (entity1 instanceof EntityKittyBed) || (entity1 instanceof EntityLitterBox) || (entity1 instanceof WolfEntity) && !mocr.mocreaturesGlass.huntercreatures.attackwolves || (entity1 instanceof EntityHorse) && !mocr.mocreaturesGlass.huntercreatures.attackhorses)
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

    public boolean damage(Entity entitybase, int i)
    {
        if(super.damage(entitybase, i))
        {
            if(field_1594 == entitybase || field_1595 == entitybase)
            {
                return true;
            }
            if(entitybase != this && world.field_213 > 0)
            {
                target = entitybase;
            }
            return true;
        } else
        {
            return false;
        }
    }

    protected void method_637(Entity entity, float f)
    {
        if((double)f < 2.5D && entity.boundingBox.maxY > boundingBox.minY && entity.boundingBox.minY < boundingBox.maxY)
        {
            field_1042 = 20;
            entity.damage(this, force);
            if(!(entity instanceof PlayerEntity))
            {
                destroyDrops(this, 3D);
            }
        }
    }

    public void writeNbt(NbtCompound nbttagcompound)
    {
        super.writeNbt(nbttagcompound);
        nbttagcompound.putBoolean("BearBoolean", bearboolean);
    }

    public void readNbt(NbtCompound nbttagcompound)
    {
        super.readNbt(nbttagcompound);
        bearboolean = nbttagcompound.getBoolean("BearBoolean");
    }

    protected String method_911()
    {
        return "mocreatures:beargrunt";
    }

    protected String method_912()
    {
        return "mocreatures:bearhurt";
    }

    protected String method_913()
    {
        return "mocreatures:beardying";
    }

    protected void method_933()
    {
        int i = random.nextInt(3);
        for(int j = 0; j < i; j++)
        {
            method_1327(new ItemStack(method_914(), 1, 0), 0.0F);
        }
        if(mocr.mocreaturesGlass.balancesettings.balance_drop) {
            int k = random.nextInt(2);
            for (int j = 0; j < k; j++) {
                method_1327(new ItemStack(mod_mocreatures.wildleather, 1, 0), 0.0F);
            }
        }
    }

    protected int method_914()
    {
        return Item.RAW_FISH.id;
    }

    public int method_916()
    {
        return 2;
    }

    public void destroyDrops(Entity entity, double d)
    {
        List list = world.getEntities(entity, entity.boundingBox.expand(d, d, d));
        for(int i = 0; i < list.size(); i++)
        {
            Entity entity1 = (Entity)list.get(i);
            if(!(entity1 instanceof ItemEntity))
            {
                continue;
            }
            ItemEntity entityitem = (ItemEntity)entity1;
            if(entityitem != null && entityitem.itemAge < 50 && mocr.mocreaturesGlass.huntercreatures.destroyitems)
            {
                entityitem.markDead();
            }
        }

    }

    public void markDead()
    {
        super.markDead();
    }

    public boolean cS2()
    {
        return super.canSpawn();
    }

    public boolean canSpawn()
    {
        return mocr.mocreaturesGlass.huntercreatures.bearfreq > 0 && super.canSpawn();
    }

    mod_mocreatures mocr = new mod_mocreatures();
    protected double attackRange;
    public boolean bearboolean;
    protected int force;

    @Override
    public Identifier getHandlerIdentifier() {
        return Identifier.of(mod_mocreatures.MOD_ID, "Bear");
    }
}

