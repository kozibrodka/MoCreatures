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
import net.minecraft.entity.Entity;
import net.minecraft.entity.ItemEntity;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.mob.MonsterEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NbtCompound;
import net.minecraft.world.World;
import net.modificationstation.stationapi.api.util.Identifier;
import net.modificationstation.stationapi.api.server.entity.MobSpawnDataProvider;

import java.util.List;

public class EntityDolphin extends EntityCustomWM implements MobSpawnDataProvider
{

    public EntityDolphin(World world)
    {
        super(world);
        texture = "/assets/mocreatures/stationapi/textures/mob/dolphin.png";
        setBoundingBoxSpacing(1.5F, 0.8F);
        b = 0.8F + random.nextFloat();
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
            int i = random.nextInt(100);
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

    public boolean method_1323(PlayerEntity entityplayer)
    {
        ItemStack itemstack = entityplayer.inventory.getSelectedItem();
        if(tamed && !isDolphinPublic && !entityplayer.name.equals(dolphinOwner))
        {
            return false;
        }
        if(itemstack != null && itemstack.itemId == Item.RAW_FISH.id)
        {
            if(--itemstack.count == 0)
            {
                entityplayer.inventory.setStack(entityplayer.inventory.selectedSlot, null);
            }
            if((temper -= 25) < 1)
            {
                temper = 1;
            }
            if((health += 15) > maxhealth)
            {
                health = maxhealth;
            }
            world.playSound(this, "mocreatures:eating", 1.0F, 1.0F + (random.nextFloat() - random.nextFloat()) * 0.2F);
            if(!adult)
            {
                b += 0.01F;
            }
            return true;
        }
        if(itemstack != null && itemstack.itemId == Item.COOKED_FISH.id && tamed && adult)
        {
            if(--itemstack.count == 0)
            {
                entityplayer.inventory.setStack(entityplayer.inventory.selectedSlot, null);
            }
            if((health += 25) > maxhealth)
            {
                health = maxhealth;
            }
            eaten = true;
            world.playSound(this, "mocreatures:eating", 1.0F, 1.0F + (random.nextFloat() - random.nextFloat()) * 0.2F);
            return true;
        }
        if(itemstack != null && tamed && entityplayer.name.equals(dolphinOwner) && (itemstack.itemId == Item.GOLDEN_SWORD.id || itemstack.itemId == Item.STONE_SWORD.id || itemstack.itemId == Item.WOODEN_SWORD.id || itemstack.itemId == Item.IRON_SWORD.id || itemstack.itemId == Item.DIAMOND_SWORD.id))
        {
            if(isDolphinPublic == true){
                isDolphinPublic = false;
                for(int var3 = 0; var3 < 7; ++var3) {
                    double var4 = this.random.nextGaussian() * 0.02D;
                    double var6 = this.random.nextGaussian() * 0.02D;
                    double var8 = this.random.nextGaussian() * 0.02D;
                    world.addParticle("heart", this.x + (double) (this.random.nextFloat() * this.spacingXZ * 2.0F) - (double) this.spacingXZ, this.y + 0.5D + (double) (this.random.nextFloat() * this.spacingY), this.z + (double) (this.random.nextFloat() * this.spacingXZ * 2.0F) - (double) this.spacingXZ, var4, var6, var8);
                }
            }else{
                isDolphinPublic = true;
                for(int var3 = 0; var3 < 7; ++var3) {
                    double var4 = this.random.nextGaussian() * 0.02D;
                    double var6 = this.random.nextGaussian() * 0.02D;
                    double var8 = this.random.nextGaussian() * 0.02D;
                    world.addParticle("flame", this.x + (double) (this.random.nextFloat() * this.spacingXZ * 2.0F) - (double) this.spacingXZ, this.y + 0.5D + (double) (this.random.nextFloat() * this.spacingY), this.z + (double) (this.random.nextFloat() * this.spacingXZ * 2.0F) - (double) this.spacingXZ, var4, var6, var8);
                }
            }
            return true;
        }
        if(itemstack != null && tamed && (itemstack.itemId == mod_mocreatures.medallion.id || itemstack.itemId == Item.BOOK.id))
        {
            if(dolphinOwner.equals(""))
            {
                dolphinOwner = entityplayer.name;
            }
            setName(this);
            return true;
        }
        if(itemstack != null && tamed && (itemstack.itemId == Item.DIAMOND_PICKAXE.id || itemstack.itemId == Item.WOODEN_PICKAXE.id || itemstack.itemId == Item.STONE_PICKAXE.id || itemstack.itemId == Item.IRON_PICKAXE.id || itemstack.itemId == Item.GOLDEN_PICKAXE.id))
        {
            displayname = !displayname;
            return true;
        }
        if(adult)
        {
            entityplayer.yaw = yaw;
            entityplayer.pitch = pitch;
            entityplayer.y = y;
            entityplayer.method_1376(this);
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
        if(field_1594 != null && (field_1594 instanceof PlayerEntity))
        {
            PlayerEntity entityplayer = (PlayerEntity)field_1594;
            List list = world.getEntities(this, boundingBox.expand(1.0D, 0.0D, 1.0D));
            if(list != null)
            {
                for(int i = 0; i < list.size(); i++)
                {
                    Entity entity = (Entity)list.get(i);
                    if(entity.dead)
                    {
                        continue;
                    }
                    entity.onPlayerInteraction(entityplayer);
                    if(!(entity instanceof MonsterEntity))
                    {
                        continue;
                    }
                    float f = method_1351(entity);
                    if(f < 2.0F && random.nextInt(10) == 0)
                    {
                        damage(entity, ((MonsterBaseAccesor)entity).getField_547());
                    }
                }

            }
            if(entityplayer.method_1373())
            {
                entityplayer.method_1376(null);
            }
        }
    }

    public boolean method_1356()
    {
        return field_1594 == null;
    }

    public void method_937()
    {
        super.method_937();
        if(!adult && random.nextInt(50) == 0)
        {
            b += 0.01F;
            if(b >= 1.5F)
            {
                adult = true;
            }
        }
        if(!hungry && random.nextInt(100) == 0)
        {
            hungry = true;
        }
        Riding();
        if(field_1594 != null && field_1041 == 0 && (!tamed || hungry))
        {
            ItemEntity entityitem = getClosestFish(this, 12D);
            if(entityitem != null)
            {
                MoveToNextEntity(entityitem);
                ItemEntity entityitem1 = getClosestFish(this, 2D);
                if(random.nextInt(20) == 0 && entityitem1 != null && field_1041 == 0)
                {
                    entityitem1.markDead();
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
        List list = world.getEntities(this, boundingBox.expand(8D, 2D, 8D));
        for(int j = 0; j < list.size(); j++)
        {
            Entity entity = (Entity)list.get(j);
            if(entity instanceof EntityDolphin)
            {
                i++;
            }
        }

        if(i > 1)
        {
            return;
        }
        List list1 = world.getEntities(this, boundingBox.expand(4D, 2D, 4D));
        for(int k = 0; k < list.size(); k++)
        {
            Entity entity1 = (Entity)list1.get(k);
            if(!(entity1 instanceof EntityDolphin) || entity1 == this)
            {
                continue;
            }
            EntityDolphin entitydolphin = (EntityDolphin)entity1;
            if(!ReadyforParenting(this) || !ReadyforParenting(entitydolphin))
            {
                continue;
            }
            if(random.nextInt(100) == 0)
            {
                gestationtime++;
            }
            if(gestationtime <= 50)
            {
                continue;
            }
            EntityDolphin entitydolphin1 = new EntityDolphin(world);
            entitydolphin1.method_1340(x, y, z);
            world.method_210(entitydolphin1);
            world.playSound(this, "mob.chickenplop", 1.0F, (random.nextFloat() - random.nextFloat()) * 0.2F + 1.0F);
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
        return entitydolphin.field_1594 == null && entitydolphin.field_1595 == null && entitydolphin.tamed && entitydolphin.eaten && entitydolphin.adult;
    }

    private int Genetics(EntityDolphin entitydolphin, EntityDolphin entitydolphin1)
    {
        if(entitydolphin.typeint == entitydolphin1.typeint)
        {
            return entitydolphin.typeint;
        }
        int i = entitydolphin.typeint + entitydolphin1.typeint;
        boolean flag = random.nextInt(3) == 0;
        boolean flag1 = random.nextInt(10) == 0;
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

    public void writeNbt(NbtCompound nbttagcompound)
    {
        super.writeNbt(nbttagcompound);
        nbttagcompound.putBoolean("Tamed", tamed);
        nbttagcompound.putInt("TypeInt", typeint);
        nbttagcompound.putBoolean("Adult", adult);
        nbttagcompound.putBoolean("Bred", bred);
        nbttagcompound.putFloat("Age", b);
        nbttagcompound.putString("Name", name);
        nbttagcompound.putBoolean("DisplayName", displayname);
        nbttagcompound.putBoolean("PublicDolphin", isDolphinPublic);
        nbttagcompound.putString("DolphinOwner", dolphinOwner);
    }

    public void readNbt(NbtCompound nbttagcompound)
    {
        super.readNbt(nbttagcompound);
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

    protected Entity method_638()
    {
        if(world.field_213 > 0 && b >= 1.0F && mocr.mocreaturesGlass.watermobs.attackdolphins && random.nextInt(50) == 0)
        {
            LivingEntity entityliving = FindTarget(this, 12D);
            if(entityliving != null && ((EntityBaseAccesor)entityliving).getField_1612())
            {
                return entityliving;
            }
        }
        return null;
    }

    public LivingEntity FindTarget(Entity entity, double d)
    {
        double d1 = -1D;
        LivingEntity entityliving = null;
        List list = world.getEntities(this, boundingBox.expand(d, d, d));
        for(int i = 0; i < list.size(); i++)
        {
            Entity entity1 = (Entity)list.get(i);
            if(!(entity1 instanceof EntityShark) || !(mocr.mocreaturesGlass.watermobs.attackdolphins))
            {
                continue;
            }

            if (tamed && ((EntityShark)entity1).tamed)
            {
                continue;
            }

            double d2 = entity1.method_1347(entity.x, entity.y, entity.z);
            if((d < 0.0D || d2 < d * d) && (d1 == -1D || d2 < d1) && ((LivingEntity)entity1).method_928(entity))
            {
                d1 = d2;
                entityliving = (LivingEntity)entity1;
            }
        }

        return entityliving;
    }

    public boolean damage(Entity entitybase, int i)
    {
        if(super.damage(entitybase, i) && world.field_213 > 0)
        {
            if(field_1594 == entitybase || field_1595 == entitybase)
            {
                return true;
            }
            if(entitybase != this)
            {
                target = entitybase;
            }
            return true;
        } else
        {
            return false;
        }
    }

    protected void method_637(Entity entity, float f)
    {
        if((double)f < 3.5D && entity.boundingBox.maxY > boundingBox.minY && entity.boundingBox.minY < boundingBox.maxY && b >= 1.0F)
        {
            field_1042 = 20;
            entity.damage(this, 5);
        }
    }

    protected int method_914()
    {
        return Item.RAW_FISH.id;
    }

    public boolean canSpawn()
    {
        return mocr.mocreaturesGlass.watermobs.dolphinfreq > 0 && super.canSpawn();
    }

    public void markDead()
    {
        if((tamed || bred) && health > 0)
        {
            return;
        } else
        {
            super.markDead();
            return;
        }
    }

    protected boolean method_940()
    {
        return !tamed;
    }

    protected String method_911()
    {
        return "mocreatures:dolphin";
    }

    protected String method_912()
    {
        return "mocreatures:dolphinhurt";
    }

    protected String method_913()
    {
        return "mocreatures:dolphindying";
    }

    protected float method_915()
    {
        return 0.4F;
    }

    protected String getUpsetSound()
    {
        return "mocreatures:dolphinupset";
    }

    public boolean renderName()
    {
        return displayname && field_1594 == null;
    }

        public static void setName(EntityDolphin entitydolphin)
    {
        entitydolphin.displayname = true;
        mc.setScreen(new MoCGUI(entitydolphin, entitydolphin.name));
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
