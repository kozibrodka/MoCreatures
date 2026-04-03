
package net.kozibrodka.mocreatures.entity;

import net.kozibrodka.mocreatures.events.mod_mocreatures;
import net.minecraft.block.Block;
import net.minecraft.entity.Monster;
import net.minecraft.nbt.NbtCompound;
import net.minecraft.util.math.MathHelper;
import net.minecraft.world.World;
import net.modificationstation.stationapi.api.util.Identifier;
import net.modificationstation.stationapi.api.server.entity.MobSpawnDataProvider;

public class EntityFireOgre extends EntityOgre implements MobSpawnDataProvider
    , Monster
{

    public EntityFireOgre(World world)
    {
        super(world);
        attackDamage = 3;
        attackRange = mod_mocreatures.mocGlass.hostilemobs.ogrerange;
        texture = "/assets/mocreatures/stationapi/textures/mob/fireogre.png";
        setBoundingBoxSpacing(1.5F, 4F);
        health = 35;
        bogrefire = true;
        destroyForce = mod_mocreatures.mocGlass.hostilemobs.fogreStrength;
        fireImmune = true;
        frequencyA = 35;
    }

    protected int getDroppedItemId()
    {
        return Block.FIRE.id;
    }

    public void tickMovement()
    {
        getTargetInRange();
        destroyForce = mod_mocreatures.mocGlass.hostilemobs.fogreStrength;
        attackRange = mod_mocreatures.mocGlass.hostilemobs.ogrerange;
        if(ogrehasenemy && random.nextInt(frequencyA) == 0 && !world.isRemote)
        {
            setOgreAttack(true);
            attackCooldown = 15;
        }
        if(attackCooldown <= 0 && getOgreAttack() && !world.isRemote)
        {
            setOgreAttack(false);
            DestroyingOgre();
        }
        if(world.canMonsterSpawn() && !world.isRemote)
        {
            float f = getBrightnessAtEyes(1.0F);
            if(f > 0.5F && world.hasSkyLight(MathHelper.floor(x), MathHelper.floor(y), MathHelper.floor(z)) && random.nextFloat() * 30F < (f - 0.4F) * 2.0F)
            {
                if(health <= 5){
                    damage(null ,5);
                }else{
                    health -= 5;
                }
            }
        }
        super.onLivingUpdate2();
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
        return mod_mocreatures.mocGlass.hostilemobs.fogrefreq > 0 && world.difficulty >= mod_mocreatures.mocGlass.hostilemobs.fogreSpawnDifficulty.ordinal() + 1 && super.d2();
    }

    public int getLimitPerChunk()
    {
        return 2;
    }


    @Override
    public Identifier getHandlerIdentifier() {
        return Identifier.of(mod_mocreatures.MOD_ID, "FireOgre");
    }
}
