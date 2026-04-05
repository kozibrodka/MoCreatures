
package net.kozibrodka.mocreatures.entity;

import net.kozibrodka.mocreatures.events.mod_mocreatures;
import net.minecraft.entity.Entity;
import net.minecraft.entity.Monster;
import net.minecraft.item.Item;
import net.minecraft.nbt.NbtCompound;
import net.minecraft.util.math.MathHelper;
import net.minecraft.world.World;
import net.modificationstation.stationapi.api.server.entity.MobSpawnDataProvider;
import net.modificationstation.stationapi.api.util.Identifier;

public class EntityFlameWraith extends EntityWraith
    implements Monster, MobSpawnDataProvider
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

    @Override
    protected int getDroppedItemId()
    {
        return Item.REDSTONE.id;
    }

    @Override
    public void tickMovement()
    {
        if(random.nextInt(40) == 0) //&& !world.isRemote
        {
            fireTicks = 2;
        }
        if(world.canMonsterSpawn() && !world.isRemote) // && !world.isRemote
        {
            float f = getBrightnessAtEyes(1.0F);
            if(f > 0.5F && world.hasSkyLight(MathHelper.floor(x), MathHelper.floor(y), MathHelper.floor(z)) && random.nextFloat() * 30F < (f - 0.4F) * 2.0F)
            {
                if(health <= 2){
                    damage(null ,2);
                }else{
                    health -= 2;
                }
            }
        }
        super.tickMovement();
    }

    @Override
    protected void attack(Entity entity, float f)
    {
        if((double)f < 2.5D && entity.boundingBox.maxY > boundingBox.minY && entity.boundingBox.minY < boundingBox.maxY)
        {
            attackCooldown = 20;
            entity.damage(this, 2);
            entity.fireTicks = burningTime;
        }
    }

    @Override
    public void writeNbt(NbtCompound nbttagcompound)
    {
        super.writeNbt(nbttagcompound);
    }

    @Override
    public void readNbt(NbtCompound nbttagcompound)
    {
        super.readNbt(nbttagcompound);
    }

    @Override
    public boolean canSpawn()
    {
        return mod_mocreatures.mocGlass.hostilemobs.fwraithfreq > 0 && world.difficulty >= mod_mocreatures.mocGlass.hostilemobs.fwraithSpawnDifficulty.ordinal() + 1 && super.d2();
    }

    protected int burningTime;

    @Override
    public Identifier getHandlerIdentifier() {
        return Identifier.of(mod_mocreatures.MOD_ID, "FlameWraith");
    }
}
