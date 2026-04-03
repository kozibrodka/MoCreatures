
package net.kozibrodka.mocreatures.entity;

import net.kozibrodka.mocreatures.events.mod_mocreatures;
import net.kozibrodka.mocreatures.mocreatures.MoCTools;
import net.minecraft.block.material.Material;
import net.minecraft.entity.Entity;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NbtCompound;
import net.minecraft.world.World;
import net.modificationstation.stationapi.api.util.Identifier;
import net.modificationstation.stationapi.api.server.entity.MobSpawnDataProvider;

public class EntityPolarBear extends EntityBear implements MobSpawnDataProvider
{

    public EntityPolarBear(World world)
    {
        super(world);
        texture = "/assets/mocreatures/stationapi/textures/mob/polarbear.png";
        attackRange = 5.0D;
        health = 30;
    }

    protected Entity getTargetInRange()
    {
        if(world.difficulty > 0)
        {
            if(world.difficulty > 1){
                this.attackRange = 8.0D;
            }else{
                this.attackRange = 5.0D;
            }
            PlayerEntity entityplayer = world.getClosestPlayer(this, attackRange);
            if(entityplayer != null)
            {
                return entityplayer;
            }
            if(random.nextInt(20) == 0)
            {
                LivingEntity entityliving = getClosestTarget(this, 8D);
                return entityliving;
            }
        }
        return null;
    }

    protected void attack(Entity entity, float f)
    {
        if((double)f < 2.5D && entity.boundingBox.maxY > boundingBox.minY && entity.boundingBox.minY < boundingBox.maxY)
        {
            attackCooldown = 20;
            if(world.difficulty > 1){
                this.force = 5;
            }else{
                this.force = 3;
            }
            entity.damage(this, force);
            if(!(entity instanceof PlayerEntity))
            {
                destroyDrops(this, 3D);
            }
        }
    }

    protected void dropItems()
    {
        int i = random.nextInt(3);
        for(int j = 0; j < i; j++)
        {
            dropItem(new ItemStack(getDroppedItemId(), 1, 0), 0.0F);
        }
        int k = random.nextInt(2);
        for (int j = 0; j < k; j++) {
            if(world.difficulty > 0) {
                dropItem(new ItemStack(mod_mocreatures.polarleather.id, 1, 0), 0.0F);
            }
        }
    }

    public int getLimitPerChunk()
    {
        return 2;
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
        return MoCTools.NearMaterialWithDistance(this, Double.valueOf(1.0D), Material.SNOW_LAYER) && mod_mocreatures.mocGlass.huntercreatures.pbearfreq > 0 && !MoCTools.isNearTorch(this) && super.cS2();
    }

    @Override
    public Identifier getHandlerIdentifier() {
        return Identifier.of(mod_mocreatures.MOD_ID, "PolarBear");
    }
}
