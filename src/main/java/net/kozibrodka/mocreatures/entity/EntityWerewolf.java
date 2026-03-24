
package net.kozibrodka.mocreatures.entity;

import net.fabricmc.api.EnvType;
import net.fabricmc.loader.FabricLoader;
import net.kozibrodka.mocreatures.events.mod_mocreatures;
import net.minecraft.entity.Entity;
import net.minecraft.entity.Monster;
import net.minecraft.entity.mob.MonsterEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NbtCompound;
import net.minecraft.util.math.MathHelper;
import net.minecraft.world.World;
import net.modificationstation.stationapi.api.server.entity.HasTrackingParameters;
import net.modificationstation.stationapi.api.util.Identifier;
import net.modificationstation.stationapi.api.server.entity.MobSpawnDataProvider;
import net.modificationstation.stationapi.api.util.TriState;

public class EntityWerewolf extends MonsterEntity
    implements Monster, MobSpawnDataProvider
{

    public EntityWerewolf(World world)
    {
        super(world);
        texture = "/assets/mocreatures/stationapi/textures/mob/werehuman.png";
        setBoundingBoxSpacing(0.9F, 1.3F);
        setHumanForm(true); //TODO: czy dobre miejsce?
        health = 15;
        transforming = false;
        tcounter = 0;
//        hunched = false;
        isUndead = true;
    }

    protected Entity getTargetInRange()
    {
        if(getHumanForm())
        {
            return null;
        }
        PlayerEntity entityplayer = world.getClosestPlayer(this, 16D);
        if(entityplayer != null && canSee(entityplayer))
        {
            return entityplayer;
        } else
        {
            return null;
        }
    }

    protected void tickLiving()
    {
        if(!transforming)
        {
            super.tickLiving();
        }
    }

    protected void attack(Entity entity, float f)
    {
        if(getHumanForm())
        {
            this.target = null;
            return;
        }
        if(f > 2.0F && f < 6F && random.nextInt(15) == 0)
        {
            if(onGround)
            {
                setHunched(true);
                double d = entity.x - x;
                double d1 = entity.z - z;
                float f1 = MathHelper.sqrt(d * d + d1 * d1);
                velocityX = (d / (double)f1) * 0.5D * 0.80000001192092896D + velocityX * 0.20000000298023221D;
                velocityZ = (d1 / (double)f1) * 0.5D * 0.80000001192092896D + velocityZ * 0.20000000298023221D;
                velocityY = 0.40000000596046448D;
            }
        } else
        {
            super.attack(entity, f);
        }
    }

    public boolean damage(Entity entity, int i)
    {
        if(!getHumanForm() && entity != null && (entity instanceof PlayerEntity))
        {
            PlayerEntity entityplayer = (PlayerEntity)entity;
            ItemStack itemstack = entityplayer.getHand();
            if(itemstack != null)
            {
                i = 1;
                if(itemstack.itemId == Item.GOLDEN_HOE.id)
                {
                    i = 6;
                }
                if(itemstack.itemId == Item.GOLDEN_SHOVEL.id)
                {
                    i = 7;
                }
                if(itemstack.itemId == Item.GOLDEN_PICKAXE.id)
                {
                    i = 8;
                }
                if(itemstack.itemId == Item.GOLDEN_AXE.id)
                {
                    i = 9;
                }
                if(itemstack.itemId == Item.GOLDEN_SWORD.id)
                {
                    i = 10;
                }
            }
        }
        return super.damage(entity, i);
    }

    public void tickMovement()
    {
        super.tickMovement();
        if(world.isRemote){
            return;
        }
        if((IsNight() && getHumanForm() || !IsNight() && !getHumanForm()) && random.nextInt(250) == 0)
        {
            transforming = true;
        }
        if(getHumanForm() && target != null)
        {
            target = null;
        }
        if(target != null && !getHumanForm() && target.x - x > 3D && target.z - z > 3D)
        {
            setHunched(true);
        }
        if(getHunched() && random.nextInt(50) == 0)
        {
            setHunched(false);
        }
        if(transforming && random.nextInt(3) == 0)
        {
            tcounter++;
            if(tcounter % 2 == 0)
            {
                x += 0.29999999999999999D;
                y += tcounter / 30;
                damage(this, 1);
            }
            if(tcounter % 2 != 0)
            {
                x -= 0.29999999999999999D;
            }
            if(tcounter == 10)
            {
                world.playSound(this, "mocreatures:weretransform", 1.0F, (random.nextFloat() - random.nextFloat()) * 0.2F + 1.0F);
                sendSound(world, "mocreatures:weretransform", 1.0F, (random.nextFloat() - random.nextFloat()) * 0.2F + 1.0F);
            }
            if(tcounter > 30)
            {
                Transform();
                tcounter = 0;
                transforming = false;
            }
        }
        if(random.nextInt(300) == 0)
        {
            despawnCounter -= 100 * world.difficulty;
            if(despawnCounter < 0)
            {
                despawnCounter = 0;
            }
        }
    }

    public boolean IsNight()
    {
        return !world.canMonsterSpawn();
    }

    public void travel(float f, float f1)
    {
        if(!getHumanForm() && onGround)
        {
            velocityX *= 1.2D;
            velocityZ *= 1.2D;
        }
        super.travel(f, f1);
    }

    private void Transform()
    {
        if(deathTime > 0)
        {
            return;
        }
        int i = MathHelper.floor(x);
        int j = MathHelper.floor(boundingBox.minY) + 1;
        int k = MathHelper.floor(z);
        float f = 0.1F;
        for(int l = 0; l < 30; l++)
        {
            double d = (float)i + world.random.nextFloat();
            double d1 = (float)j + world.random.nextFloat();
            double d2 = (float)k + world.random.nextFloat();
            double d3 = d - (double)i;
            double d4 = d1 - (double)j;
            double d5 = d2 - (double)k;
            double d6 = MathHelper.sqrt(d3 * d3 + d4 * d4 + d5 * d5);
            d3 /= d6;
            d4 /= d6;
            d5 /= d6;
            double d7 = 0.5D / (d6 / (double)f + 0.10000000000000001D);
            d7 *= world.random.nextFloat() * world.random.nextFloat() + 0.3F;
            d3 *= d7;
            d4 *= d7;
            d5 *= d7;
            world.addParticle("explode", (d + (double)i * 1.0D) / 2D, (d1 + (double)j * 1.0D) / 2D, (d2 + (double)k * 1.0D) / 2D, d3, d4, d5);
            sendParticle(world, "explode", (d + (double)i * 1.0D) / 2D, (d1 + (double)j * 1.0D) / 2D, (d2 + (double)k * 1.0D) / 2D, d3, d4, d5);
        }

        if(getHumanForm())
        {
            setHumanForm(false);
            health = 40;
            transforming = false;
        } else
        {
            setHumanForm(true);
            health = 15;
            transforming = false;
        }
    }

    protected void initDataTracker()
    {
        super.initDataTracker();
        dataTracker.startTracking(16, (byte) 0); //HumanForm
        dataTracker.startTracking(17, (byte) 0); //Hunched
    }

    public void writeNbt(NbtCompound nbttagcompound)
    {
        super.writeNbt(nbttagcompound);
        nbttagcompound.putBoolean("HumanForm", getHumanForm());
    }

    public void readNbt(NbtCompound nbttagcompound)
    {
        super.readNbt(nbttagcompound);
        setHumanForm(nbttagcompound.getBoolean("HumanForm"));
    }

    protected String getRandomSound()
    {
        if(getHumanForm())
        {
            return "mocreatures:werehumangrunt";
        } else
        {
            return "mocreatures:werewolfgrunt";
        }
    }

    protected String getHurtSound()
    {
        if(getHumanForm())
        {
            return "mocreatures:werehumanhurt";
        } else
        {
            return "mocreatures:werewolfhurt";
        }
    }

    protected String getDeathSound()
    {
        if(getHumanForm())
        {
            return "mocreatures:werehumandying";
        } else
        {
            return "mocreatures:werewolfdying";
        }
    }

    protected int getDroppedItemId()
    {
        int i = random.nextInt(12);
        if(getHumanForm())
        {
            switch(i)
            {
            case 0: // '\0'
                return Item.WOODEN_SHOVEL.id;

            case 1: // '\001'
                return Item.WOODEN_AXE.id;

            case 2: // '\002'
                return Item.WOODEN_SWORD.id;

            case 3: // '\003'
                return Item.WOODEN_HOE.id;

            case 4: // '\004'
                return Item.WOODEN_PICKAXE.id;
            }
            return Item.STICK.id;
        }
        switch(i)
        {
        case 0: // '\0'
            return Item.IRON_HOE.id;

        case 1: // '\001'
            return Item.IRON_SHOVEL.id;

        case 2: // '\002'
            return Item.IRON_AXE.id;

        case 3: // '\003'
            return Item.IRON_PICKAXE.id;

        case 4: // '\004'
            return Item.IRON_SWORD.id;

        case 5: // '\005'
            return Item.STONE_HOE.id;

        case 6: // '\006'
            return Item.STONE_SHOVEL.id;

        case 7: // '\007'
            return Item.STONE_AXE.id;

        case 8: // '\b'
            return Item.STONE_PICKAXE.id;

        case 9: // '\t'
            return Item.STONE_SWORD.id;
        }
        if(mocr.mocreaturesGlass.balancesettings.balance_drop){
            return mod_mocreatures.greenapple.id;
        }else{
            return Item.GOLDEN_APPLE.id;
        }
    }

    public void onKilledBy(Entity entity)
    {
        if(scoreAmount > 0 && entity != null)
        {
            entity.updateKilledAchievement(this, scoreAmount);
        }
        if(entity != null)
        {
            entity.onKilledOther(this);
        }
        killedByOtherEntity = true;
        if(!world.isRemote)
        {
            for(int i = 0; i < 2; i++)
            {
                int j = getDroppedItemId();
                if(j > 0)
                {
                    dropItem(j, 1);
                }
            }

        }
        if(FabricLoader.INSTANCE.getEnvironmentType() == EnvType.CLIENT) {
            world.getSkyColor(this, 3F);
        }
        world.broadcastEntityEvent(this, (byte)3);
    }

    public int getLimitPerChunk()
    {
        return 1;
    }

    public void markDead()
    {
        super.markDead();
    }

    public boolean canSpawn()
    {
        return mocr.mocreaturesGlass.hostilemobs.werewolffreq > 0 && world.difficulty >= mocr.mocreaturesGlass.hostilemobs.wereSpawnDifficulty.ordinal() + 1 && super.canSpawn();
    }

    public void ustawTexture(String tex){
        this.texture = tex;
    }

    mod_mocreatures mocr = new mod_mocreatures();
//    public boolean humanform; ///
    private boolean transforming;
    private int tcounter;
//    public boolean hunched; ///
    public boolean isUndead;

    @Override
    public Identifier getHandlerIdentifier() {
        return Identifier.of(mod_mocreatures.MOD_ID, "WereWolf");
    }

    public void sendParticle(World world, String name, double x, double y, double z, double i, double j, double k){
        if (net.fabricmc.loader.FabricLoader.INSTANCE.getEnvironmentType() == EnvType.SERVER){
            mocr.particlePacket(world,name,x,y,z,i,j,k);
        }
    }

    public void sendSound(World world, String name, float vol, float pit){
        if (net.fabricmc.loader.FabricLoader.INSTANCE.getEnvironmentType() == EnvType.SERVER){
            mocr.voicePacket(world, name, this.id, vol, pit);
        }
    }

    //HUMANFORM
    public boolean getHumanForm()
    {
        return (dataTracker.getByte(16) & 1) != 0;
    }

    public void setHumanForm(boolean flag)
    {
        if(flag)
        {
            dataTracker.set(16, Byte.valueOf((byte)1));
        } else
        {
            dataTracker.set(16, Byte.valueOf((byte)0));
        }
    }

    //HUNCHED
    public boolean getHunched()
    {
        return (dataTracker.getByte(17) & 1) != 0;
    }

    public void setHunched(boolean flag)
    {
        if(flag)
        {
            dataTracker.set(17, Byte.valueOf((byte)1));
        } else
        {
            dataTracker.set(17, Byte.valueOf((byte)0));
        }
    }
}
