// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) braces deadcode 

package net.kozibrodka.mocreatures.entity;

import net.kozibrodka.mocreatures.events.mod_mocreatures;
import net.minecraft.class_65;
import net.minecraft.entity.Entity;
import net.minecraft.entity.mob.MonsterEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NbtCompound;
import net.minecraft.util.math.MathHelper;
import net.minecraft.world.World;
import net.modificationstation.stationapi.api.util.Identifier;
import net.modificationstation.stationapi.api.server.entity.MobSpawnDataProvider;

public class EntityWerewolf extends MonsterEntity
    implements class_65, MobSpawnDataProvider
{

    public EntityWerewolf(World world)
    {
        super(world);
        wereboolean = false;
        texture = "/assets/mocreatures/stationapi/textures/mob/werehuman.png";
        setBoundingBoxSpacing(0.9F, 1.3F);
        humanform = true;
        health = 15;
        transforming = false;
        tcounter = 0;
        hunched = false;
        isUndead = true;
    }

    protected Entity method_638()
    {
        if(humanform)
        {
            return null;
        }
        PlayerEntity entityplayer = world.method_186(this, 16D);
        if(entityplayer != null && method_928(entityplayer))
        {
            return entityplayer;
        } else
        {
            return null;
        }
    }

    protected void method_910()
    {
        if(this.target instanceof PlayerEntity){
            PlayerEntity uciekinier = world.method_186(this, 16D);
            if(uciekinier == null && target.isAlive()){
                if(random.nextInt(30) == 0)
                {
                    target = null;
                }
            }
        }
        if(!transforming)
        {
            super.method_910();
        }
    }

    protected void method_637(Entity entity, float f)
    {
        if(humanform)
        {
            this.target = null;
            return;
        }
        if(f > 2.0F && f < 6F && random.nextInt(15) == 0)
        {
            if(field_1623)
            {
                hunched = true;
                double d = entity.x - x;
                double d1 = entity.z - z;
                float f1 = MathHelper.sqrt(d * d + d1 * d1);
                velocityX = (d / (double)f1) * 0.5D * 0.80000001192092896D + velocityX * 0.20000000298023221D;
                velocityZ = (d1 / (double)f1) * 0.5D * 0.80000001192092896D + velocityZ * 0.20000000298023221D;
                velocityY = 0.40000000596046448D;
            }
        } else
        {
            super.method_637(entity, f);
        }
    }

    public boolean damage(Entity entity, int i)
    {
        if(!humanform && entity != null && (entity instanceof PlayerEntity))
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

    public void method_937()
    {
        super.method_937();
        if((IsNight() && humanform || !IsNight() && !humanform) && random.nextInt(250) == 0)
        {
            transforming = true;
        }
        if(humanform && target != null)
        {
            target = null;
        }
        if(target != null && !humanform && target.x - x > 3D && target.z - z > 3D)
        {
            hunched = true;
        }
        if(hunched && random.nextInt(50) == 0)
        {
            hunched = false;
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
            field_1059 -= 100 * world.field_213;
            if(field_1059 < 0)
            {
                field_1059 = 0;
            }
        }
    }

    public boolean IsNight()
    {
        return !world.method_220();
    }

    public void method_945(float f, float f1)
    {
        if(!humanform && field_1623)
        {
            velocityX *= 1.2D;
            velocityZ *= 1.2D;
        }
        super.method_945(f, f1);
    }

    private void Transform()
    {
        if(field_1041 > 0)
        {
            return;
        }
        int i = MathHelper.floor(x);
        int j = MathHelper.floor(boundingBox.minY) + 1;
        int k = MathHelper.floor(z);
        float f = 0.1F;
        for(int l = 0; l < 30; l++)
        {
            double d = (float)i + world.field_214.nextFloat();
            double d1 = (float)j + world.field_214.nextFloat();
            double d2 = (float)k + world.field_214.nextFloat();
            double d3 = d - (double)i;
            double d4 = d1 - (double)j;
            double d5 = d2 - (double)k;
            double d6 = MathHelper.sqrt(d3 * d3 + d4 * d4 + d5 * d5);
            d3 /= d6;
            d4 /= d6;
            d5 /= d6;
            double d7 = 0.5D / (d6 / (double)f + 0.10000000000000001D);
            d7 *= world.field_214.nextFloat() * world.field_214.nextFloat() + 0.3F;
            d3 *= d7;
            d4 *= d7;
            d5 *= d7;
            world.addParticle("explode", (d + (double)i * 1.0D) / 2D, (d1 + (double)j * 1.0D) / 2D, (d2 + (double)k * 1.0D) / 2D, d3, d4, d5);
        }

        if(humanform)
        {
            humanform = false;
            health = 40;
            transforming = false;
        } else
        {
            humanform = true;
            health = 15;
            transforming = false;
        }
    }

    public void writeNbt(NbtCompound nbttagcompound)
    {
        super.writeNbt(nbttagcompound);
        nbttagcompound.putBoolean("HumanForm", humanform);
    }

    public void readNbt(NbtCompound nbttagcompound)
    {
        super.readNbt(nbttagcompound);
        humanform = nbttagcompound.getBoolean("HumanForm");
    }

    protected String method_911()
    {
        if(humanform)
        {
            return "mocreatures:werehumangrunt";
        } else
        {
            return "mocreatures:werewolfgrunt";
        }
    }

    protected String method_912()
    {
        if(humanform)
        {
            return "mocreatures:werehumanhurt";
        } else
        {
            return "mocreatures:werewolfhurt";
        }
    }

    protected String method_913()
    {
        if(humanform)
        {
            return "mocreatures:werehumandying";
        } else
        {
            return "mocreatures:werewolfdying";
        }
    }

    protected int method_914()
    {
        int i = random.nextInt(12);
        if(humanform)
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
            return Item.STONE_HATCHET.id;

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

    public void method_938(Entity entity)
    {
        if(field_1024 > 0 && entity != null)
        {
            entity.method_1391(this, field_1024);
        }
        if(entity != null)
        {
            entity.method_1390(this);
        }
        field_1045 = true;
        if(!world.isRemote)
        {
            for(int i = 0; i < 2; i++)
            {
                int j = method_914();
                if(j > 0)
                {
                    method_1339(j, 1);
                }
            }

        }
        world.method_279(this, 3F);
    }

    public int method_916()
    {
        return 1;
    }

    public void markDead()
    {
        super.markDead();
    }

    public boolean canSpawn()
    {
        return mocr.mocreaturesGlass.hostilemobs.werewolffreq > 0 && world.field_213 >= mocr.mocreaturesGlass.hostilemobs.wereSpawnDifficulty + 1 && super.canSpawn();
    }

    public void ustawTexture(String tex){
        this.texture = tex;
    }

    mod_mocreatures mocr = new mod_mocreatures();
    public boolean wereboolean;
    public boolean humanform;
    private boolean transforming;
    private int tcounter;
    public boolean hunched;
    public boolean isUndead;

    @Override
    public Identifier getHandlerIdentifier() {
        return Identifier.of(mod_mocreatures.MOD_ID, "WereWolf");
    }
}
