
package net.kozibrodka.mocreatures.entity;


import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.kozibrodka.mocreatures.events.mod_mocreatures;
import net.kozibrodka.mocreatures.mocreatures.MoCreatureRacial;
import net.minecraft.block.Block;
import net.minecraft.entity.Entity;
import net.minecraft.entity.mob.MonsterEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NbtCompound;
import net.minecraft.util.math.MathHelper;
import net.minecraft.world.World;
import net.modificationstation.stationapi.api.server.entity.MobSpawnDataProvider;
import net.modificationstation.stationapi.api.util.Identifier;

public class EntityMummy extends MonsterEntity implements MobSpawnDataProvider
{

    public EntityMummy(World world)
    {
        super(world);
        movementSpeed = 0.4F;
        attackDamage = 3;
        health = random.nextInt(6) + 25;
        if(!world.isRemote){
            setTypeSpawn();
        }
    }

    @Override
    protected void initDataTracker()
    {
        super.initDataTracker();
        dataTracker.startTracking(16, (byte) 0); //Type
    }

    @Override
    public void writeNbt(NbtCompound nbttagcompound)
    {
        super.writeNbt(nbttagcompound);
        nbttagcompound.putInt("TypeInt", getType());
    }

    @Override
    public void readNbt(NbtCompound nbttagcompound)
    {
        super.readNbt(nbttagcompound);
        setType(nbttagcompound.getInt("TypeInt"));
    }

    @Override
    protected void attack(Entity entity, float f)
    {
        double d = entity.x - x;
        double d1 = entity.z - z;
        float f1 = MathHelper.sqrt(d * d + d1 * d1);
        velocityX = (d / (double)f1) * 0.40000000000000002D * 0.10000000192092896D + velocityX * 0.18000000098023225D;
        velocityZ = (d1 / (double)f1) * 0.40000000000000002D * 0.070000001920928964D + velocityZ * 0.18000000098023225D;
        if((double)f < 2D && entity.boundingBox.maxY > boundingBox.minY && entity.boundingBox.minY < boundingBox.maxY)
        {
            attackCooldown = 10;
            entity.damage(this, attackDamage);
        }
        super.attack(entity, f);
    }

    @Override
    public boolean canSpawn()
    {
//        int i = MathHelper.floor(x);
//        int j = MathHelper.floor(boundingBox.minY);
//        int k = MathHelper.floor(z);
//        int i1 = world.getBlockId(i, j - 1, k);
        /// Moge dac biome pustynia jedynie, albo zostawić to i dac ją tylko na piasku i w podziemiach, mogę ją również dac jak WIlka bez jaskiń.
//        return (i1 == Block.STONE.id || i1 == Block.SAND.id || i1 == Block.GRAVEL.id || i1 == Block.BEDROCK.id || i1 == Block.SANDSTONE.id) && mocr.mocreaturesGlass.hostilemobs.mummyfreq > 0 && super.canSpawn();
//        return world.hasSkyLight(MathHelper.floor(x), MathHelper.floor(y), MathHelper.floor(z)) && mod_mocreatures.mocGlass.hostilemobs.mummyfreq > 0 && super.canSpawn();
        return y > 60 && mod_mocreatures.mocGlass.hostilemobs.mummyfreq > 0 && super.canSpawn();
    }

    @Override
    public void tickMovement()
    {
        if(world.canMonsterSpawn() && !world.isRemote)
        {
            float f = getBrightnessAtEyes(1.0F);
            if(f > 0.5F && world.hasSkyLight(MathHelper.floor(x), MathHelper.floor(y), MathHelper.floor(z)) && random.nextFloat() * 30F < (f - 0.4F) * 2.0F)
            {
                fireTicks = 300;
            }
        }
        super.tickMovement();
    }

    @Override
    protected String getRandomSound()
    {
        return "mocreatures:mummy";
    }

    @Override
    protected String getHurtSound()
    {
        return "mocreatures:mummyhurt";
    }

    @Override
    protected String getDeathSound()
    {
        return "mocreatures:mummydeath";
    }

    @Override
    protected void dropItems()
    {
        int i = random.nextInt(3);
        for(int j = 0; j < i; j++)
        {
            dropItem(new ItemStack(getDroppedItemId(), 1, 0), 0.0F);
        }

        int i2 = random.nextInt(3);
        for(int j2 = 0; j2 < i2; j2++)
        {
            dropItem(new ItemStack(Block.SAND.id, 1, 0), 0.0F);
        }

    }

    @Override
    protected int getDroppedItemId()
    {
        return Block.SANDSTONE.id;
    }

    @Override
    public Identifier getHandlerIdentifier() {return Identifier.of(mod_mocreatures.MOD_ID, "Mummy");}

    //TYPE
    public void setTypeSpawn()
    {
        if(!world.isRemote){
            int type = getRandomRace();
            setType(type);
        }
    }

    public void setType(int type)
    {
        if(!world.isRemote) {
            dataTracker.set(16, (byte) type);
        }
    }

    public int getType()
    {
        return dataTracker.getByte(16);
    }

    @Override
    @Environment(EnvType.CLIENT)
    public String getTexture() {
        return switch (getType()) {
            case 1 -> "/assets/mocreatures/stationapi/textures/mob/mummy.png";
            case 2 -> "/assets/mocreatures/stationapi/textures/mob/mummy1.png";
            case 3 -> "/assets/mocreatures/stationapi/textures/mob/mummy2.png";
            case 4 -> "/assets/mocreatures/stationapi/textures/mob/mummy3.png";
            default -> "";
        };
    }

    public int getRandomRace(){
        int i = random.nextInt(100);
        if(i <= 39)
        {
            return 1;
        } else
        if(i <= 79)
        {
            return 2;
        } else
        if(i <= 89)
        {
            return 3;
        } else
        {
            return 4;
        }
    }
}
