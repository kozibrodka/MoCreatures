// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) braces deadcode 

package net.kozibrodka.mocreatures.entity;

import net.kozibrodka.mocreatures.events.mod_mocreatures;
import net.minecraft.class_65;
import net.minecraft.entity.Entity;
import net.minecraft.entity.ItemEntity;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.mob.MonsterEntity;
import net.minecraft.entity.passive.CowEntity;
import net.minecraft.entity.passive.WolfEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.Item;
import net.minecraft.nbt.NbtCompound;
import net.minecraft.util.math.MathHelper;
import net.minecraft.world.World;
import net.modificationstation.stationapi.api.util.Identifier;
import net.modificationstation.stationapi.api.server.entity.MobSpawnDataProvider;

import java.util.List;


public class EntityWWolf extends MonsterEntity
    implements class_65, MobSpawnDataProvider
{

    public EntityWWolf(World world)
    {
        super(world);
        wolfboolean = false;
        texture = "/assets/mocreatures/stationapi/textures/mob/wolfa.png";
        setBoundingBoxSpacing(0.9F, 1.3F);
        field_547 = 1;
    }

    public void method_937()
    {
        if(world.field_213 == 1)
        {
            field_547 = 3;
        } else
        if(world.field_213 > 1)
        {
            field_547 = 5;
        }
        super.method_937();
    }

    public int method_916()
    {
        return 6;
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
            double d = 16D;
            return world.method_186(this, d);
        }
        if(random.nextInt(80) == 0)
        {
            LivingEntity entityliving = getClosestTarget(this, 10D);
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
            if(!(entity1 instanceof LivingEntity) || entity1 == entity || entity1 == entity.field_1594 || entity1 == entity.field_1595 || (entity1 instanceof PlayerEntity) || (entity1 instanceof MonsterEntity) || (entity1 instanceof EntityBigCat) || (entity1 instanceof EntityBear) || (entity1 instanceof CowEntity) || (entity1 instanceof WolfEntity) && !mocr.mocreaturesGlass.huntercreatures.attackwolves || (entity1 instanceof EntityHorse) && !mocr.mocreaturesGlass.huntercreatures.attackhorses)
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

    protected void method_637(Entity entity, float f)
    {
        if((double)f < 2.5D && entity.boundingBox.maxY > boundingBox.minY && entity.boundingBox.minY < boundingBox.maxY)
        {
            field_1042 = 20;
            entity.damage(this, field_547);
            if(!(entity instanceof PlayerEntity))
            {
                destroyDrops(this, 3D);
            }
        }
    }

    public void writeNbt(NbtCompound nbttagcompound)
    {
        super.writeNbt(nbttagcompound);
        nbttagcompound.putBoolean("WolfBoolean", wolfboolean);
    }

    public void readNbt(NbtCompound nbttagcompound)
    {
        super.readNbt(nbttagcompound);
        wolfboolean = nbttagcompound.getBoolean("WolfBoolean");
    }

    protected String method_911()
    {
        return "mocreatures:wolfgrunt";
    }

    protected String method_912()
    {
        return "mocreatures:wolfhurt";
    }

    protected String method_913()
    {
        return "mocreatures:wolfdeath";
    }

    protected int method_914()
    {
        return Item.LEATHER.id;
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
        return world.method_249(MathHelper.floor(x), MathHelper.floor(y), MathHelper.floor(z)) && mocr.mocreaturesGlass.hostilemobs.wwolffreq > 0 && super.canSpawn();
    }

    mod_mocreatures mocr = new mod_mocreatures();
    public boolean wolfboolean;

    @Override
    public Identifier getHandlerIdentifier() {
        return Identifier.of(mod_mocreatures.MOD_ID, "WildWolf");
    }
}
