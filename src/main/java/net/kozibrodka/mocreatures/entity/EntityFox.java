// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) braces deadcode 

package net.kozibrodka.mocreatures.entity;

import net.kozibrodka.mocreatures.events.mod_mocreatures;
import net.minecraft.entity.Entity;
import net.minecraft.entity.ItemEntity;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.mob.MonsterEntity;
import net.minecraft.entity.passive.AnimalEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NbtCompound;
import net.minecraft.world.World;
import net.modificationstation.stationapi.api.util.Identifier;
import net.modificationstation.stationapi.api.server.entity.MobSpawnDataProvider;

import java.util.List;

public class EntityFox extends AnimalEntity implements MobSpawnDataProvider
{

    public EntityFox(World world)
    {
        super(world);
        foxboolean = false;
        texture = "/assets/mocreatures/stationapi/textures/mob/fox.png";
        setBoundingBoxSpacing(0.9F, 1.3F);
        health = 15;
        force = 2;
        attackRange = 4D;
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
        if(random.nextInt(80) == 0 && world.field_213 > 0)
        {
            LivingEntity entityliving = getClosestTarget(this, 8D);
            return entityliving;
        } else
        {
            return null;
        }
    }

    public LivingEntity getClosestTarget(Entity entity, double d)
    {
        double d1 = -1D;
        LivingEntity entityliving = null;
        List list = world.getEntities(this, boundingBox.expand(d, d, d));
        for(int i = 0; i < list.size(); i++)
        {
            Entity entity1 = (Entity)list.get(i);
            if(!(entity1 instanceof LivingEntity) || entity1 == entity || entity1 == entity.field_1594 || entity1 == entity.field_1595 || (entity1 instanceof PlayerEntity) || (entity1 instanceof MonsterEntity) || spacingY <= entity1.spacingY || spacingXZ <= entity1.spacingXZ)
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
        nbttagcompound.putBoolean("FoxBoolean", foxboolean);
    }

    public void readNbt(NbtCompound nbttagcompound)
    {
        super.readNbt(nbttagcompound);
        foxboolean = nbttagcompound.getBoolean("FoxBoolean");
    }

    protected float method_915()
    {
        return 0.3F;
    }

    protected String method_911()
    {
        return "mocreatures:foxcall";
    }

    protected String method_912()
    {
        return "mocreatures:foxhurt";
    }

    protected String method_913()
    {
        return "mocreatures:foxdying";
    }

    protected void method_933()
    {
        int i = random.nextInt(3);
        for(int j = 0; j < i; j++)
        {
            method_1327(new ItemStack(method_914(), 1, 0), 0.0F);
        }
        if(mocr.mocreaturesGlass.balancesettings.balance_drop) {
            int a = random.nextInt(10);
            if (a < 8) {
                int k = random.nextInt(2);
                for (int j = 0; j < k; j++) {
                    method_1327(new ItemStack(mod_mocreatures.wildleather, 1, 0), 0.0F);
                }
            }
        }
    }

    protected int method_914()
    {
        return Item.LEATHER.id;
    }

    public int method_916()
    {
        return 1;
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

    public boolean canSpawn()
    {
        return mocr.mocreaturesGlass.huntercreatures.foxfreq > 0 && super.canSpawn();
    }

    mod_mocreatures mocr = new mod_mocreatures();
    protected double attackRange;
    public boolean foxboolean;
    protected int force;

    @Override
    public Identifier getHandlerIdentifier() {
        return Identifier.of(mod_mocreatures.MOD_ID, "Fox");
    }
}
