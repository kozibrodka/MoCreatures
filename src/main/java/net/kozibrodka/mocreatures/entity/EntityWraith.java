// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) braces deadcode 

package net.kozibrodka.mocreatures.entity;

import net.kozibrodka.mocreatures.events.mod_mocreatures;
import net.minecraft.entity.Monster;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.Item;
import net.minecraft.nbt.NbtCompound;
import net.minecraft.util.math.MathHelper;
import net.minecraft.world.World;
import net.modificationstation.stationapi.api.server.entity.MobSpawnDataProvider;
import net.modificationstation.stationapi.api.util.Identifier;

public class EntityWraith extends EntityFlyerMob
    implements Monster, MobSpawnDataProvider
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

    protected String getRandomSound()
    {
        return "mocreatures:wraith";
    }

    protected String getHurtSound()
    {
        return "mocreatures:wraithhurt";
    }

    protected String getDeathSound()
    {
        return "mocreatures:wraithdying";
    }

    protected int getDroppedItemId()
    {
        return Item.GUNPOWDER.id;
    }

    public void tickMovement()
    {
        if(world.difficulty == 1)
        {
            attackStrength = 2;
        } else
        if(world.difficulty > 1)
        {
            attackStrength = 3;
        }
        if(world.canMonsterSpawn())
        {
            float f = getBrightnessAtEyes(1.0F);
            if(f > 0.5F && world.hasSkyLight(MathHelper.floor(x), MathHelper.floor(y), MathHelper.floor(z)) && random.nextFloat() * 30F < (f - 0.4F) * 2.0F)
            {
                fireTicks = 300;
            }
        }
        super.tickMovement();
    }

    protected void tickLiving(){
        if(this.target instanceof PlayerEntity){
            PlayerEntity uciekinier = world.getClosestPlayer(this, 20D);
            if(uciekinier == null && target.isAlive()){
                if(random.nextInt(30) == 0)
                {
                    target = null;
                }
            }
        }
        super.tickLiving();
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
        return mocr.mocreaturesGlass.hostilemobs.wraithfreq > 0 && world.difficulty >= mocr.mocreaturesGlass.hostilemobs.wraithSpawnDifficulty.ordinal() + 1 && super.canSpawn();
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
