package net.kozibrodka.mocreatures.entity;

import net.fabricmc.api.EnvType;
import net.fabricmc.loader.FabricLoader;
import net.kozibrodka.mocreatures.events.mod_mocreatures;
import net.kozibrodka.mocreatures.network.JokeyPacket;
import net.minecraft.entity.Entity;
import net.minecraft.entity.passive.AnimalEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NbtCompound;
import net.minecraft.util.math.MathHelper;
import net.minecraft.world.World;
import net.modificationstation.stationapi.api.network.packet.PacketHelper;
import net.modificationstation.stationapi.api.server.entity.MobSpawnDataProvider;
import net.modificationstation.stationapi.api.util.Identifier;

public class EntityGiraffe extends AnimalEntity implements MobSpawnDataProvider {

    public EntityGiraffe(World world)
    {
        super(world);
        texture = "/assets/mocreatures/stationapi/textures/mob/giraffe.png";
        setBoundingBoxSpacing(1.5F, 4.0F);
        health = 45;
        movementSpeed = 0.65F; ///wolna
        knockCooldown = 0;
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
        if(knockCooldown > 0){
            knockCooldown --;
        }
        super.tickLiving();
    }

    protected void attack(Entity entity, float f)
    {
        if(onGround)
        {
            double d = entity.x - x;
            double d1 = entity.z - z;
            float f1 = MathHelper.sqrt(d * d + d1 * d1);
            velocityX = (d / (double)f1) * 0.40000000000000002D * 0.10000000192092896D + velocityX * 0.18000000098023225D;
            velocityZ = (d1 / (double)f1) * 0.40000000000000002D * 0.14000000192092896D + velocityZ * 0.18000000098023225D;
        }
        if((double)f < 2.5D && entity.boundingBox.maxY > boundingBox.minY && entity.boundingBox.minY < boundingBox.maxY)
        {
            attackCooldown = 10;
            if(MathHelper.abs((float)this.velocityZ) < 0.05 && MathHelper.abs((float)this.velocityX) < 0.05){

                if (FabricLoader.INSTANCE.getEnvironmentType() == EnvType.SERVER && entity instanceof PlayerEntity && knockCooldown == 0){
                    knockCooldown = 20;
                    PacketHelper.sendTo((PlayerEntity) entity, new JokeyPacket(2, this.velocityX, this.velocityZ));
                } else {
                    entity.velocityX += (this.velocityX * 10.0D);
                    entity.velocityZ += (this.velocityZ * 10.0D);
                    if(entity.velocityY < 0.3D){
                        entity.velocityY += 0.2D;
                    }
                }
            }
            entity.damage(this, 2);
        }
    }


    public boolean damage(Entity entitybase, int i)
    {
        if(super.damage(entitybase, i))
        {
            if(passenger == entitybase || vehicle == entitybase)
            {
                return true;
            }
            if(entitybase != this && world.difficulty > 0)
            {
                target = entitybase;
            }
            return true;
        } else
        {
            return false;
        }
    }

    public void writeNbt(NbtCompound nbttagcompound)
    {
        super.writeNbt(nbttagcompound);
    }

    public void readNbt(NbtCompound nbttagcompound)
    {
        super.readNbt(nbttagcompound);
    }

    public int getLimitPerChunk()
    {
        return 3;
    }

    protected int getDroppedItemId()
    {
        return Item.RAW_PORKCHOP.id;
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
                dropItem(new ItemStack(Item.APPLE.id, 1, 0), 0.0F);
        }
    }

    protected String getRandomSound()
    {
        return "mocreatures:giraffe";
    }

    protected String getHurtSound()
    {
        return "mocreatures:giraffe";
    }

    protected String getDeathSound()
    {
        return "mocreatures:giraffedead";
    }

    public boolean canSpawn()
    {
        return mod_mocreatures.mocGlass.animals.giraffefreq > 0 && super.canSpawn();
    }

    private int knockCooldown;

    @Override
    public Identifier getHandlerIdentifier() {return Identifier.of(mod_mocreatures.MOD_ID, "Giraffe");}
}
