// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) braces deadcode 

package net.kozibrodka.mocreatures.entity;

import net.kozibrodka.mocreatures.events.mod_mocreatures;
import net.minecraft.class_65;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.Item;
import net.minecraft.nbt.NbtCompound;
import net.minecraft.util.math.MathHelper;
import net.minecraft.world.World;
import net.modificationstation.stationapi.api.server.entity.MobSpawnDataProvider;
import net.modificationstation.stationapi.api.util.Identifier;

public class EntityWraith extends EntityFlyerMob
    implements class_65, MobSpawnDataProvider
{

    public EntityWraith(World world)
    {
        super(world);
        texture = "/assets/mocreatures/stationapi/textures/mob/wraith.png";
        setBoundingBoxSpacing(1.5F, 1.5F);
        fireImmune = false;
        attackStrength = 3;
        health = 10;
        movementSpeed = 1.3F;
    }

    protected String method_911()
    {
        return "mocreatures:wraith";
    }

    protected String method_912()
    {
        return "mocreatures:wraithhurt";
    }

    protected String method_913()
    {
        return "mocreatures:wraithdying";
    }

    protected int method_914()
    {
        return Item.GUNPOWDER.id;
    }

    public void method_937()
    {
        if(world.field_213 == 1)
        {
            attackStrength = 2;
        } else
        if(world.field_213 > 1)
        {
            attackStrength = 3;
        }
        if(world.method_220())
        {
            float f = method_1394(1.0F);
            if(f > 0.5F && world.method_249(MathHelper.floor(x), MathHelper.floor(y), MathHelper.floor(z)) && random.nextFloat() * 30F < (f - 0.4F) * 2.0F)
            {
                fire = 300;
            }
        }
        super.method_937();
    }

    protected void method_910(){
        if(this.target instanceof PlayerEntity){
            PlayerEntity uciekinier = world.method_186(this, 20D);
            if(uciekinier == null && target.isAlive()){
                if(random.nextInt(30) == 0)
                {
                    target = null;
                }
            }
        }
        super.method_910();
    }

    public void markDead()
    {
        super.markDead();
    }

    public void writeNbt(NbtCompound nbttagcompound)
    {
        super.writeNbt(nbttagcompound);
    }

    public void readNbt(NbtCompound nbttagcompound)
    {
        super.readNbt(nbttagcompound);
    }

    public boolean canSpawn()
    {
        return mocr.mocreaturesGlass.hostilemobs.wraithfreq > 0 && world.field_213 >= mocr.mocreaturesGlass.hostilemobs.wraithSpawnDifficulty + 1 && super.canSpawn();
    }

    public boolean d2()
    {
        return super.canSpawn();
    }

    mod_mocreatures mocr = new mod_mocreatures();

    @Override
    public Identifier getHandlerIdentifier() {
        return Identifier.of(mod_mocreatures.MOD_ID, "Wraith");
    }
}
