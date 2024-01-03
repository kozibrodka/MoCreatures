// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) braces deadcode 

package net.kozibrodka.mocreatures.entity;

import net.kozibrodka.mocreatures.events.mod_mocreatures;
import net.minecraft.class_65;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.Item;
import net.minecraft.nbt.NbtCompound;
import net.minecraft.util.math.MathHelper;
import net.minecraft.world.World;
import net.modificationstation.stationapi.api.server.entity.MobSpawnDataProvider;
import net.modificationstation.stationapi.api.util.Identifier;

public class EntityFlameWraith extends EntityWraith
    implements class_65, MobSpawnDataProvider
{

    public EntityFlameWraith(World world)
    {
        super(world);
        texture = "/assets/mocreatures/stationapi/textures/mob/flamewraith.png";
        setBoundingBoxSpacing(1.5F, 1.5F);
        fireImmune = true;
        burningTime = 30;
        health = 15;
        movementSpeed = 1.1F;
    }

    protected int method_914()
    {
        return Item.REDSTONE.id;
    }

    public void method_937()
    {
        if(random.nextInt(40) == 0)
        {
            fire = 2;
        }
        if(world.method_220())
        {
            float f = method_1394(1.0F);
            if(f > 0.5F && world.method_249(MathHelper.floor(x), MathHelper.floor(y), MathHelper.floor(z)) && random.nextFloat() * 30F < (f - 0.4F) * 2.0F)
            {
                health -= 2;
            }
        }
        super.method_937();
    }

    protected void method_637(Entity entity, float f)
    {
        if((double)f < 2.5D && entity.boundingBox.maxY > boundingBox.minY && entity.boundingBox.minY < boundingBox.maxY)
        {
            field_1042 = 20;
            entity.damage(this, 2);
            entity.fire = burningTime;
        }
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
        return mocr.mocreaturesGlass.hostilemobs.fwraithfreq > 0 && world.field_213 >= mocr.mocreaturesGlass.hostilemobs.fwraithSpawnDifficulty + 1 && super.d2();
    }

    mod_mocreatures mocr = new mod_mocreatures();
    protected int burningTime;

    @Override
    public Identifier getHandlerIdentifier() {
        return Identifier.of(mod_mocreatures.MOD_ID, "FlameWraith");
    }
}
