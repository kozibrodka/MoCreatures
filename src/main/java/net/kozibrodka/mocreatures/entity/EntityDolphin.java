// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) braces deadcode 

package net.kozibrodka.mocreatures.entity;

import net.fabricmc.loader.api.FabricLoader;
import net.kozibrodka.mocreatures.events.mod_mocreatures;
import net.kozibrodka.mocreatures.mixin.EntityBaseAccesor;
import net.kozibrodka.mocreatures.mixin.LivingAccesor;
import net.kozibrodka.mocreatures.mixin.MonsterBaseAccesor;
import net.kozibrodka.mocreatures.mocreatures.MoCGUI;
import net.minecraft.client.Minecraft;
import net.minecraft.entity.EntityBase;
import net.minecraft.entity.Item;
import net.minecraft.entity.Living;
import net.minecraft.entity.monster.MonsterBase;
import net.minecraft.entity.player.PlayerBase;
import net.minecraft.item.ItemBase;
import net.minecraft.item.ItemInstance;
import net.minecraft.level.Level;
import net.minecraft.util.io.CompoundTag;
import net.modificationstation.stationapi.api.registry.Identifier;
import net.modificationstation.stationapi.api.server.entity.MobSpawnDataProvider;

import java.util.List;

public class EntityDolphin extends EntityCustomWM implements MobSpawnDataProvider
{

    public EntityDolphin(Level world)
    {
        super(world);
        texture = "/assets/mocreatures/stationapi/textures/mob/dolphin.png";
        setSize(1.5F, 0.8F);
        b = 0.8F + rand.nextFloat();
        adult = false;
        tamed = false;
        dolphinspeed = 1.3D;
        maxhealth = 30;
        temper = 50;
        field_1045 = true;
        name = "";
        displayname = false;
        isDolphinPublic = false;
        dolphinOwner = "";
    }

    public void setTame()
    {
        tamed = true;
    }

    public double speed()
    {
        return dolphinspeed;
    }

    public int tametemper()
    {
        return temper;
    }

    public boolean istamed()
    {
        return tamed;
    }

    public void setType(int i)
    {
        typeint = i;
        typechosen = false;
        chooseType();
    }

    public void chooseType()
    {
        if(typeint == 0)
        {
            int i = rand.nextInt(100);
            if(i <= 35)
            {
                typeint = 1;
            } else
            if(i <= 60)
            {
                typeint = 2;
            } else
            if(i <= 85)
            {
                typeint = 3;
            } else
            if(i <= 96)
            {
                typeint = 4;
            } else
            if(i <= 98)
            {
                typeint = 5;
            } else
            {
                typeint = 6;
            }
        }
        if(!typechosen)
        {
            if(typeint == 1)
            {
                texture = "/assets/mocreatures/stationapi/textures/mob/dolphin.png";
                dolphinspeed = 1.5D;
                temper = 50;
            } else
            if(typeint == 2)
            {
                texture = "/assets/mocreatures/stationapi/textures/mob/dolphin2.png";
                dolphinspeed = 2.5D;
                temper = 100;
            } else
            if(typeint == 3)
            {
                texture = "/assets/mocreatures/stationapi/textures/mob/dolphin3.png";
                dolphinspeed = 3.5D;
                temper = 150;
            } else
            if(typeint == 4)
            {
                texture = "/assets/mocreatures/stationapi/textures/mob/dolphin4.png";
                dolphinspeed = 4.5D;
                temper = 200;
            } else
            if(typeint == 5)
            {
                texture = "/assets/mocreatures/stationapi/textures/mob/dolphin5.png";
                dolphinspeed = 5.5D;
                temper = 250;
            } else
            if(typeint == 6)
            {
                texture = "/assets/mocreatures/stationapi/textures/mob/dolphin6.png";
                dolphinspeed = 6.5D;
                temper = 300;
            }
        }
        typechosen = true;
    }

    public boolean interact(PlayerBase entityplayer)
    {
        ItemInstance itemstack = entityplayer.inventory.getHeldItem();
        if(tamed && !isDolphinPublic && !entityplayer.name.equals(dolphinOwner))
        {
            return false;
        }
        if(itemstack != null && itemstack.itemId == ItemBase.rawFish.id)
        {
            if(--itemstack.count == 0)
            {
                entityplayer.inventory.setInventoryItem(entityplayer.inventory.selectedHotbarSlot, null);
            }
            if((temper -= 25) < 1)
            {
                temper = 1;
            }
            if((health += 15) > maxhealth)
            {
                health = maxhealth;
            }
            level.playSound(this, "mocreatures:eating", 1.0F, 1.0F + (rand.nextFloat() - rand.nextFloat()) * 0.2F);
            if(!adult)
            {
                b += 0.01F;
            }
            return true;
        }
        if(itemstack != null && itemstack.itemId == ItemBase.cookedFish.id && tamed && adult)
        {
            if(--itemstack.count == 0)
            {
                entityplayer.inventory.setInventoryItem(entityplayer.inventory.selectedHotbarSlot, null);
            }
            if((health += 25) > maxhealth)
            {
                health = maxhealth;
            }
            eaten = true;
            level.playSound(this, "mocreatures:eating", 1.0F, 1.0F + (rand.nextFloat() - rand.nextFloat()) * 0.2F);
            return true;
        }
        if(itemstack != null && tamed && entityplayer.name.equals(dolphinOwner) && (itemstack.itemId == ItemBase.goldSword.id || itemstack.itemId == ItemBase.stoneSword.id || itemstack.itemId == ItemBase.woodSword.id || itemstack.itemId == ItemBase.ironSword.id || itemstack.itemId == ItemBase.diamondSword.id))
        {
            if(isDolphinPublic == true){
                isDolphinPublic = false;
                for(int var3 = 0; var3 < 7; ++var3) {
                    double var4 = this.rand.nextGaussian() * 0.02D;
                    double var6 = this.rand.nextGaussian() * 0.02D;
                    double var8 = this.rand.nextGaussian() * 0.02D;
                    level.addParticle("heart", this.x + (double) (this.rand.nextFloat() * this.width * 2.0F) - (double) this.width, this.y + 0.5D + (double) (this.rand.nextFloat() * this.height), this.z + (double) (this.rand.nextFloat() * this.width * 2.0F) - (double) this.width, var4, var6, var8);
                }
            }else{
                isDolphinPublic = true;
                for(int var3 = 0; var3 < 7; ++var3) {
                    double var4 = this.rand.nextGaussian() * 0.02D;
                    double var6 = this.rand.nextGaussian() * 0.02D;
                    double var8 = this.rand.nextGaussian() * 0.02D;
                    level.addParticle("flame", this.x + (double) (this.rand.nextFloat() * this.width * 2.0F) - (double) this.width, this.y + 0.5D + (double) (this.rand.nextFloat() * this.height), this.z + (double) (this.rand.nextFloat() * this.width * 2.0F) - (double) this.width, var4, var6, var8);
                }
            }
            return true;
        }
        if(itemstack != null && tamed && (itemstack.itemId == mod_mocreatures.medallion.id || itemstack.itemId == ItemBase.book.id))
        {
            if(dolphinOwner.equals(""))
            {
                dolphinOwner = entityplayer.name;
            }
            setName(this);
            return true;
        }
        if(itemstack != null && tamed && (itemstack.itemId == ItemBase.diamondPickaxe.id || itemstack.itemId == ItemBase.woodPickaxe.id || itemstack.itemId == ItemBase.stonePickaxe.id || itemstack.itemId == ItemBase.ironPickaxe.id || itemstack.itemId == ItemBase.goldPickaxe.id))
        {
            displayname = !displayname;
            return true;
        }
        if(adult)
        {
            entityplayer.yaw = yaw;
            entityplayer.pitch = pitch;
            entityplayer.y = y;
            entityplayer.startRiding(this);
            if(typeint == 6)
            {
                entityplayer.incrementStat(mod_mocreatures.RobertMaklowicz);
            }
            return true;
        } else
        {
            return false;
        }
    }

    public void Riding()
    {
        if(passenger != null && (passenger instanceof PlayerBase))
        {
            PlayerBase entityplayer = (PlayerBase)passenger;
            List list = level.getEntities(this, boundingBox.expand(1.0D, 0.0D, 1.0D));
            if(list != null)
            {
                for(int i = 0; i < list.size(); i++)
                {
                    EntityBase entity = (EntityBase)list.get(i);
                    if(entity.removed)
                    {
                        continue;
                    }
                    entity.onPlayerCollision(entityplayer);
                    if(!(entity instanceof MonsterBase))
                    {
                        continue;
                    }
                    float f = distanceTo(entity);
                    if(f < 2.0F && rand.nextInt(10) == 0)
                    {
                        damage(entity, ((MonsterBaseAccesor)entity).getAttackDamage());
                    }
                }

            }
            if(entityplayer.method_1373())
            {
                entityplayer.startRiding(null);
            }
        }
    }

    public boolean method_1356()
    {
        return passenger == null;
    }

    public void updateDespawnCounter()
    {
        super.updateDespawnCounter();
        if(!adult && rand.nextInt(50) == 0)
        {
            b += 0.01F;
            if(b >= 1.5F)
            {
                adult = true;
            }
        }
        if(!hungry && rand.nextInt(100) == 0)
        {
            hungry = true;
        }
        Riding();
        if(passenger != null && deathTime == 0 && (!tamed || hungry))
        {
            Item entityitem = getClosestFish(this, 12D);
            if(entityitem != null)
            {
                MoveToNextEntity(entityitem);
                Item entityitem1 = getClosestFish(this, 2D);
                if(rand.nextInt(20) == 0 && entityitem1 != null && deathTime == 0)
                {
                    entityitem1.remove();
                    if((temper -= 25) < 1)
                    {
                        temper = 1;
                    }
                    health = maxhealth;
                }
            }
        }
        if(!ReadyforParenting(this))
        {
            return;
        }
        int i = 0;
        List list = level.getEntities(this, boundingBox.expand(8D, 2D, 8D));
        for(int j = 0; j < list.size(); j++)
        {
            EntityBase entity = (EntityBase)list.get(j);
            if(entity instanceof EntityDolphin)
            {
                i++;
            }
        }

        if(i > 1)
        {
            return;
        }
        List list1 = level.getEntities(this, boundingBox.expand(4D, 2D, 4D));
        for(int k = 0; k < list.size(); k++)
        {
            EntityBase entity1 = (EntityBase)list1.get(k);
            if(!(entity1 instanceof EntityDolphin) || entity1 == this)
            {
                continue;
            }
            EntityDolphin entitydolphin = (EntityDolphin)entity1;
            if(!ReadyforParenting(this) || !ReadyforParenting(entitydolphin))
            {
                continue;
            }
            if(rand.nextInt(100) == 0)
            {
                gestationtime++;
            }
            if(gestationtime <= 50)
            {
                continue;
            }
            EntityDolphin entitydolphin1 = new EntityDolphin(level);
            entitydolphin1.setPosition(x, y, z);
            level.spawnEntity(entitydolphin1);
            level.playSound(this, "mob.chickenplop", 1.0F, (rand.nextFloat() - rand.nextFloat()) * 0.2F + 1.0F);
            eaten = false;
            entitydolphin.eaten = false;
            gestationtime = 0;
            entitydolphin.gestationtime = 0;
            int l = Genetics(this, entitydolphin);
            entitydolphin1.bred = true;
            entitydolphin1.b = 0.35F;
            entitydolphin1.adult = false;
            entitydolphin1.setType(l);
            break;
        }

    }

    public boolean ReadyforParenting(EntityDolphin entitydolphin)
    {
        return entitydolphin.passenger == null && entitydolphin.vehicle == null && entitydolphin.tamed && entitydolphin.eaten && entitydolphin.adult;
    }

    private int Genetics(EntityDolphin entitydolphin, EntityDolphin entitydolphin1)
    {
        if(entitydolphin.typeint == entitydolphin1.typeint)
        {
            return entitydolphin.typeint;
        }
        int i = entitydolphin.typeint + entitydolphin1.typeint;
        boolean flag = rand.nextInt(3) == 0;
        boolean flag1 = rand.nextInt(10) == 0;
        if(i < 5 && flag)
        {
            return i;
        }
        if((i == 5 || i == 6) && flag1)
        {
            return i;
        } else
        {
            return 0;
        }
    }

    public void writeCustomDataToTag(CompoundTag nbttagcompound)
    {
        super.writeCustomDataToTag(nbttagcompound);
        nbttagcompound.put("Tamed", tamed);
        nbttagcompound.put("TypeInt", typeint);
        nbttagcompound.put("Adult", adult);
        nbttagcompound.put("Bred", bred);
        nbttagcompound.put("Age", b);
        nbttagcompound.put("Name", name);
        nbttagcompound.put("DisplayName", displayname);
        nbttagcompound.put("PublicDolphin", isDolphinPublic);
        nbttagcompound.put("DolphinOwner", dolphinOwner);
    }

    public void readCustomDataFromTag(CompoundTag nbttagcompound)
    {
        super.readCustomDataFromTag(nbttagcompound);
        tamed = nbttagcompound.getBoolean("Tamed");
        typeint = nbttagcompound.getInt("TypeInt");
        adult = nbttagcompound.getBoolean("Adult");
        bred = nbttagcompound.getBoolean("Bred");
        b = nbttagcompound.getFloat("Age");
        name = nbttagcompound.getString("Name");
        displayname = nbttagcompound.getBoolean("DisplayName");
        isDolphinPublic = nbttagcompound.getBoolean("PublicDolphin");
        dolphinOwner = nbttagcompound.getString("DolphinOwner");
    }

    protected EntityBase getAttackTarget()
    {
        if(level.difficulty > 0 && b >= 1.0F && mocr.mocreaturesGlass.watermobs.attackdolphins && rand.nextInt(50) == 0)
        {
            Living entityliving = FindTarget(this, 12D);
            if(entityliving != null && ((EntityBaseAccesor)entityliving).getField_1612())
            {
                return entityliving;
            }
        }
        return null;
    }

    public Living FindTarget(EntityBase entity, double d)
    {
        double d1 = -1D;
        Living entityliving = null;
        List list = level.getEntities(this, boundingBox.expand(d, d, d));
        for(int i = 0; i < list.size(); i++)
        {
            EntityBase entity1 = (EntityBase)list.get(i);
            if(!(entity1 instanceof EntityShark) || !(mocr.mocreaturesGlass.watermobs.attackdolphins))
            {
                continue;
            }

            if (tamed && ((EntityShark)entity1).tamed)
            {
                continue;
            }

            double d2 = entity1.squaredDistanceTo(entity.x, entity.y, entity.z);
            if((d < 0.0D || d2 < d * d) && (d1 == -1D || d2 < d1) && ((Living)entity1).method_928(entity))
            {
                d1 = d2;
                entityliving = (Living)entity1;
            }
        }

        return entityliving;
    }

    public boolean damage(EntityBase entitybase, int i)
    {
        if(super.damage(entitybase, i) && level.difficulty > 0)
        {
            if(passenger == entitybase || vehicle == entitybase)
            {
                return true;
            }
            if(entitybase != this)
            {
                entity = entitybase;
            }
            return true;
        } else
        {
            return false;
        }
    }

    protected void tryAttack(EntityBase entity, float f)
    {
        if((double)f < 3.5D && entity.boundingBox.maxY > boundingBox.minY && entity.boundingBox.minY < boundingBox.maxY && b >= 1.0F)
        {
            attackTime = 20;
            entity.damage(this, 5);
        }
    }

    protected int getMobDrops()
    {
        return ItemBase.rawFish.id;
    }

    public boolean canSpawn()
    {
        return mocr.mocreaturesGlass.watermobs.dolphinfreq > 0 && super.canSpawn();
    }

    public void remove()
    {
        if((tamed || bred) && health > 0)
        {
            return;
        } else
        {
            super.remove();
            return;
        }
    }

    protected boolean method_940()
    {
        return !tamed;
    }

    protected String getAmbientSound()
    {
        return "mocreatures:dolphin";
    }

    protected String getHurtSound()
    {
        return "mocreatures:dolphinhurt";
    }

    protected String getDeathSound()
    {
        return "mocreatures:dolphindying";
    }

    protected float getSoundVolume()
    {
        return 0.4F;
    }

    protected String getUpsetSound()
    {
        return "mocreatures:dolphinupset";
    }

    public boolean renderName()
    {
        return displayname && passenger == null;
    }

        public static void setName(EntityDolphin entitydolphin)
    {
        entitydolphin.displayname = true;
        mc.openScreen(new MoCGUI(entitydolphin, entitydolphin.name));
    }

    @SuppressWarnings("deprecation")
    public static Minecraft mc = Minecraft.class.cast(FabricLoader.getInstance().getGameInstance());
    mod_mocreatures mocr = new mod_mocreatures();
    public String dolphinOwner;
    public boolean isDolphinPublic;
    public int gestationtime;
    public boolean bred;
    public float b;
    public boolean adult;
    public boolean tamed;
    public int typeint;
    private double dolphinspeed;
    public int maxhealth;
    private int temper;
    private boolean eaten;
    public boolean typechosen;
    public boolean hungry;
    public String name;
    public boolean displayname;

    @Override
    public Identifier getHandlerIdentifier() {
        return Identifier.of(mod_mocreatures.MOD_ID, "Dolphin");
    }
}
