// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) braces deadcode 

package net.kozibrodka.mocreatures.entity;

import net.kozibrodka.mocreatures.events.mod_mocreatures;
import net.minecraft.entity.EntityBase;
import net.minecraft.entity.monster.MonsterBase;
import net.minecraft.entity.monster.MonsterEntityType;
import net.minecraft.entity.player.PlayerBase;
import net.minecraft.item.ItemBase;
import net.minecraft.item.ItemInstance;
import net.minecraft.level.Level;
import net.minecraft.util.io.CompoundTag;
import net.minecraft.util.maths.MathHelper;

public class EntityWerewolf extends MonsterBase
    implements MonsterEntityType
{

    public EntityWerewolf(Level world)
    {
        super(world);
        wereboolean = false;
        texture = "/assets/mocreatures/stationapi/textures/mob/werehuman.png";
        setSize(0.9F, 1.3F);
        humanform = true;
        health = 15;
        transforming = false;
        tcounter = 0;
        hunched = false;
        isUndead = true;
    }

    protected EntityBase getAttackTarget()
    {
        if(humanform)
        {
            return null;
        }
        PlayerBase entityplayer = level.getClosestPlayerTo(this, 16D);
        if(entityplayer != null && method_928(entityplayer))
        {
            return entityplayer;
        } else
        {
            return null;
        }
    }

    protected void tickHandSwing()
    {
        if(this.entity instanceof PlayerBase){
            PlayerBase uciekinier = level.getClosestPlayerTo(this, 16D);
            if(uciekinier == null && entity.isAlive()){
                if(rand.nextInt(30) == 0)
                {
                    entity = null;
                }
            }
        }
        if(!transforming)
        {
            super.tickHandSwing();
        }
    }

    protected void tryAttack(EntityBase entity, float f)
    {
        if(humanform)
        {
            this.entity = null;
            return;
        }
        if(f > 2.0F && f < 6F && rand.nextInt(15) == 0)
        {
            if(onGround)
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
            super.tryAttack(entity, f);
        }
    }

    public boolean damage(EntityBase entity, int i)
    {
        if(!humanform && entity != null && (entity instanceof PlayerBase))
        {
            PlayerBase entityplayer = (PlayerBase)entity;
            ItemInstance itemstack = entityplayer.getHeldItem();
            if(itemstack != null)
            {
                i = 1;
                if(itemstack.itemId == ItemBase.goldHoe.id)
                {
                    i = 6;
                }
                if(itemstack.itemId == ItemBase.goldShovel.id)
                {
                    i = 7;
                }
                if(itemstack.itemId == ItemBase.goldPickaxe.id)
                {
                    i = 8;
                }
                if(itemstack.itemId == ItemBase.goldAxe.id)
                {
                    i = 9;
                }
                if(itemstack.itemId == ItemBase.goldSword.id)
                {
                    i = 10;
                }
            }
        }
        return super.damage(entity, i);
    }

    public void updateDespawnCounter()
    {
        super.updateDespawnCounter();
        if((IsNight() && humanform || !IsNight() && !humanform) && rand.nextInt(250) == 0)
        {
            transforming = true;
        }
        if(humanform && entity != null)
        {
            entity = null;
        }
        if(entity != null && !humanform && entity.x - x > 3D && entity.z - z > 3D)
        {
            hunched = true;
        }
        if(hunched && rand.nextInt(50) == 0)
        {
            hunched = false;
        }
        if(transforming && rand.nextInt(3) == 0)
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
                level.playSound(this, "mocreatures:weretransform", 1.0F, (rand.nextFloat() - rand.nextFloat()) * 0.2F + 1.0F);
            }
            if(tcounter > 30)
            {
                Transform();
                tcounter = 0;
                transforming = false;
            }
        }
        if(rand.nextInt(300) == 0)
        {
            despawnCounter -= 100 * level.difficulty;
            if(despawnCounter < 0)
            {
                despawnCounter = 0;
            }
        }
    }

    public boolean IsNight()
    {
        return !level.isDaylight();
    }

    public void travel(float f, float f1)
    {
        if(!humanform && onGround)
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
            double d = (float)i + level.rand.nextFloat();
            double d1 = (float)j + level.rand.nextFloat();
            double d2 = (float)k + level.rand.nextFloat();
            double d3 = d - (double)i;
            double d4 = d1 - (double)j;
            double d5 = d2 - (double)k;
            double d6 = MathHelper.sqrt(d3 * d3 + d4 * d4 + d5 * d5);
            d3 /= d6;
            d4 /= d6;
            d5 /= d6;
            double d7 = 0.5D / (d6 / (double)f + 0.10000000000000001D);
            d7 *= level.rand.nextFloat() * level.rand.nextFloat() + 0.3F;
            d3 *= d7;
            d4 *= d7;
            d5 *= d7;
            level.addParticle("explode", (d + (double)i * 1.0D) / 2D, (d1 + (double)j * 1.0D) / 2D, (d2 + (double)k * 1.0D) / 2D, d3, d4, d5);
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

    public void writeCustomDataToTag(CompoundTag nbttagcompound)
    {
        super.writeCustomDataToTag(nbttagcompound);
        nbttagcompound.put("HumanForm", humanform);
    }

    public void readCustomDataFromTag(CompoundTag nbttagcompound)
    {
        super.readCustomDataFromTag(nbttagcompound);
        humanform = nbttagcompound.getBoolean("HumanForm");
    }

    protected String getAmbientSound()
    {
        if(humanform)
        {
            return "mocreatures:werehumangrunt";
        } else
        {
            return "mocreatures:werewolfgrunt";
        }
    }

    protected String getHurtSound()
    {
        if(humanform)
        {
            return "mocreatures:werehumanhurt";
        } else
        {
            return "mocreatures:werewolfhurt";
        }
    }

    protected String getDeathSound()
    {
        if(humanform)
        {
            return "mocreatures:werehumandying";
        } else
        {
            return "mocreatures:werewolfdying";
        }
    }

    protected int getMobDrops()
    {
        int i = rand.nextInt(12);
        if(humanform)
        {
            switch(i)
            {
            case 0: // '\0'
                return ItemBase.woodShovel.id;

            case 1: // '\001'
                return ItemBase.woodAxe.id;

            case 2: // '\002'
                return ItemBase.woodSword.id;

            case 3: // '\003'
                return ItemBase.woodHoe.id;

            case 4: // '\004'
                return ItemBase.woodPickaxe.id;
            }
            return ItemBase.stick.id;
        }
        switch(i)
        {
        case 0: // '\0'
            return ItemBase.ironHoe.id;

        case 1: // '\001'
            return ItemBase.ironShovel.id;

        case 2: // '\002'
            return ItemBase.ironAxe.id;

        case 3: // '\003'
            return ItemBase.ironPickaxe.id;

        case 4: // '\004'
            return ItemBase.ironSword.id;

        case 5: // '\005'
            return ItemBase.stoneHoe.id;

        case 6: // '\006'
            return ItemBase.stoneShovel.id;

        case 7: // '\007'
            return ItemBase.stoneAxe.id;

        case 8: // '\b'
            return ItemBase.stonePickaxe.id;

        case 9: // '\t'
            return ItemBase.stoneSword.id;
        }
        return mod_mocreatures.greenapple.id;
    }

    public void onKilledBy(EntityBase entity)
    {
        if(field_1024 > 0 && entity != null)
        {
            entity.onKilledOther(this, field_1024);
        }
        if(entity != null)
        {
            entity.handleKilledEntity(this);
        }
        field_1045 = true;
        if(!level.isServerSide)
        {
            for(int i = 0; i < 2; i++)
            {
                int j = getMobDrops();
                if(j > 0)
                {
                    dropItem(j, 1);
                }
            }

        }
        level.method_279(this, 3F);
    }

    public int getLimitPerChunk()
    {
        return 1;
    }

    public void remove()
    {
        super.remove();
    }

    public boolean canSpawn()
    {
        return mocr.mocreaturesGlass.hostilemobs.werewolffreq > 0 && level.difficulty >= mocr.mocreaturesGlass.hostilemobs.wereSpawnDifficulty + 1 && super.canSpawn();
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
}
