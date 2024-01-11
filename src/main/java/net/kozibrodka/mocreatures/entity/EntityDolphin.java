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
import net.kozibrodka.mocreatures.mocreatures.MoCreatureRacial;
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

public class EntityDolphin extends EntityCustomWM implements MobSpawnDataProvider, MoCreatureRacial
{

    public EntityDolphin(World world)
    {
        super(world);
//        texture = "/assets/mocreatures/stationapi/textures/mob/dolphin.png";
        setBoundingBoxSpacing(1.5F, 0.8F);
        setAge(0.8F + random.nextFloat()); //TODO: to chyba powinno być w setTypeSpawn.
//        adult = false;
//        tamed = false;
        dolphinspeed = 1.3D;
        maxhealth = 30;
        temper = 50;
        field_1045 = true;
//        name = "";
        displayname = false;
//        isDolphinPublic = false;
//        dolphinOwner = "";
    }

    public double speed()
    {
        return dolphinspeed;
    }

    public int tametemper()
    {
        return temper;
    }

    public int getRandomRace()
    {
        int i = random.nextInt(100);
        if(i <= 35)
        {
            return  1;
        } else
        if(i <= 60)
        {
            return 2;
        } else
        if(i <= 85)
        {
            return 3;
        } else
        if(i <= 96)
        {
            return 4;
        } else
        if(i <= 98)
        {
            return 5;
        } else
        {
            return 6;
        }
    }

    public void chooseType(int typeint)
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

    public boolean method_1323(PlayerEntity entityplayer)
    {
        ItemStack itemstack = entityplayer.inventory.getSelectedItem();
        if(getTamed() && !getPublic() && !entityplayer.name.equals(getOwner()))
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
            if(!getAdult())
            {
                setAge(getAge()+0.01F);
            }
            return true;
        }
        if(itemstack != null && itemstack.itemId == Item.COOKED_FISH.id && getTamed() && getAdult())
        {
            if(--itemstack.count == 0)
            {
                entityplayer.inventory.setStack(entityplayer.inventory.selectedSlot, null);
            }
            if((health += 25) > maxhealth)
            {
                health = maxhealth;
            }
            setEaten(true);
            world.playSound(this, "mocreatures:eating", 1.0F, 1.0F + (random.nextFloat() - random.nextFloat()) * 0.2F);
            return true;
        }
        if(itemstack != null && getTamed() && entityplayer.name.equals(getOwner()) && (itemstack.itemId == Item.GOLDEN_SWORD.id || itemstack.itemId == Item.STONE_SWORD.id || itemstack.itemId == Item.WOODEN_SWORD.id || itemstack.itemId == Item.IRON_SWORD.id || itemstack.itemId == Item.DIAMOND_SWORD.id))
        {
            if(getPublic()){
                setPublic(false);
                for(int var3 = 0; var3 < 7; ++var3) {
                    double var4 = this.random.nextGaussian() * 0.02D;
                    double var6 = this.random.nextGaussian() * 0.02D;
                    double var8 = this.random.nextGaussian() * 0.02D;
                    world.addParticle("heart", this.x + (double) (this.random.nextFloat() * this.spacingXZ * 2.0F) - (double) this.spacingXZ, this.y + 0.5D + (double) (this.random.nextFloat() * this.spacingY), this.z + (double) (this.random.nextFloat() * this.spacingXZ * 2.0F) - (double) this.spacingXZ, var4, var6, var8);
                }
            }else{
                setPublic(true);
                for(int var3 = 0; var3 < 7; ++var3) {
                    double var4 = this.random.nextGaussian() * 0.02D;
                    double var6 = this.random.nextGaussian() * 0.02D;
                    double var8 = this.random.nextGaussian() * 0.02D;
                    world.addParticle("flame", this.x + (double) (this.random.nextFloat() * this.spacingXZ * 2.0F) - (double) this.spacingXZ, this.y + 0.5D + (double) (this.random.nextFloat() * this.spacingY), this.z + (double) (this.random.nextFloat() * this.spacingXZ * 2.0F) - (double) this.spacingXZ, var4, var6, var8);
                }
            }
            return true;
        }
        if(itemstack != null && getTamed() && (itemstack.itemId == mod_mocreatures.medallion.id || itemstack.itemId == Item.BOOK.id))
        {
            if(getOwner().equals(""))
            {
                setOwner(entityplayer.name);
            }
            setName(this);
            return true;
        }
        if(itemstack != null && getTamed() && (itemstack.itemId == Item.DIAMOND_PICKAXE.id || itemstack.itemId == Item.WOODEN_PICKAXE.id || itemstack.itemId == Item.STONE_PICKAXE.id || itemstack.itemId == Item.IRON_PICKAXE.id || itemstack.itemId == Item.GOLDEN_PICKAXE.id))
        {
            displayname = !displayname;
            return true;
        }
        if(getAdult())
        {
            entityplayer.yaw = yaw;
            entityplayer.pitch = pitch;
            entityplayer.y = y;
            entityplayer.method_1376(this);
            if(getType() == 6)
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
        if(!typechosen && world.isRemote && getType() != 0){
            typechosen = true;
            chooseType(getType());
        }
        if(!getAdult() && random.nextInt(50) == 0 && !world.isRemote)
        {
            setAge(getAge()+0.01F);
            if(getAge() >= 1.5F)
            {
                setAdult(true);
            }
        }
        if(!hungry && random.nextInt(100) == 0)
        {
            hungry = true;
        }
        Riding();
        if(field_1594 != null && field_1041 == 0 && (!getTamed() || hungry))
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
            setEaten(false);
            entitydolphin.setEaten(false);
            gestationtime = 0;
            entitydolphin.gestationtime = 0;
            int l = Genetics(this, entitydolphin);
            entitydolphin1.setBred(true);
            entitydolphin1.setAge(0.35F);
            entitydolphin1.setAdult(false);
            entitydolphin1.setType(l);
            break;
        }

    }

    public boolean ReadyforParenting(EntityDolphin entitydolphin)
    {
        return entitydolphin.field_1594 == null && entitydolphin.field_1595 == null && entitydolphin.getTamed() && entitydolphin.getEaten() && entitydolphin.getAdult();
    }

    private int Genetics(EntityDolphin entitydolphin, EntityDolphin entitydolphin1)
    {
        if(entitydolphin.getType() == entitydolphin1.getType())
        {
            return entitydolphin.getType();
        }
        int i = entitydolphin.getType() + entitydolphin1.getType();
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
        nbttagcompound.putBoolean("Tamed", getTamed());
        nbttagcompound.putInt("TypeInt", getType());
        nbttagcompound.putBoolean("Adult", getAdult());
        nbttagcompound.putBoolean("Bred", getBred());
        nbttagcompound.putFloat("Age", getAge());
        nbttagcompound.putString("Name", getName());
        nbttagcompound.putBoolean("DisplayName", displayname);
        nbttagcompound.putBoolean("PublicDolphin", getPublic());
        nbttagcompound.putString("DolphinOwner", getOwner());
    }

    public void readNbt(NbtCompound nbttagcompound)
    {
        super.readNbt(nbttagcompound);
        setTamed(nbttagcompound.getBoolean("Tamed"));
        setType(nbttagcompound.getInt("TypeInt"));
        setAdult(nbttagcompound.getBoolean("Adult"));
        setBred(nbttagcompound.getBoolean("Bred"));
        setAge(nbttagcompound.getFloat("Age"));
        setName(nbttagcompound.getString("Name"));
        displayname = nbttagcompound.getBoolean("DisplayName");
        setPublic(nbttagcompound.getBoolean("PublicDolphin"));
        setOwner(nbttagcompound.getString("DolphinOwner"));
    }

    protected Entity method_638()
    {
        if(world.field_213 > 0 && getAge() >= 1.0F && mocr.mocreaturesGlass.watermobs.attackdolphins && random.nextInt(50) == 0)
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

            if (getTamed() && ((EntityShark)entity1).tamed)
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
        if((double)f < 3.5D && entity.boundingBox.maxY > boundingBox.minY && entity.boundingBox.minY < boundingBox.maxY && getAge() >= 1.0F)
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
        if((getTamed() || getBred()) && health > 0)
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
        return !getTamed();
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
        mc.setScreen(new MoCGUI(entitydolphin, entitydolphin.getName()));
    }

    @SuppressWarnings("deprecation")
    public static Minecraft mc = Minecraft.class.cast(FabricLoader.getInstance().getGameInstance());
    mod_mocreatures mocr = new mod_mocreatures();
    public int gestationtime;
    private double dolphinspeed;
    public int maxhealth;
    private int temper;
    public boolean hungry;
    public boolean displayname;

//    public int typeint;
//    public float b;
//    public boolean adult;
//    private boolean eaten;
//    public boolean tamed;
//    public boolean isDolphinPublic;
//    public boolean bred;
//    public String dolphinOwner;
//    public String name;

    public boolean typechosen;

    protected void initDataTracker()
    {
        super.initDataTracker();
        dataTracker.method_1502(16, (byte) 0); //Type
        dataTracker.method_1502(17, (int) 0); //Age
        dataTracker.method_1502(18, (byte) 0); //Adult
        dataTracker.method_1502(19, (byte) 0); //Eaten
        dataTracker.method_1502(20, (byte) 0); //Tamed
        dataTracker.method_1502(21, (byte) 0); //Public
        dataTracker.method_1502(22, (byte) 0); //Bred
        dataTracker.method_1502(30, ""); //Owner
        dataTracker.method_1502(31, ""); //Name
    }

    @Override
    public Identifier getHandlerIdentifier() {
        return Identifier.of(mod_mocreatures.MOD_ID, "Dolphin");
    }

    //TYPE
    public void setTypeSpawn() {
        if(!world.isRemote) {
            setType(getRandomRace());
        }
    }

    public void setType(int type)
    {
        if(!world.isRemote) {
            dataTracker.method_1509(16, (byte) type);
            chooseType(type);
        }
    }

    public int getType()
    {
        return dataTracker.method_1501(16);
    }

    //AGE
    public void setAge(float age)
    {
        dataTracker.method_1509(17, Float.floatToRawIntBits(age));
    }

    public float getAge()
    {
        return Float.intBitsToFloat(dataTracker.method_1508(17));
    }

    //ADULT
    public boolean getAdult()
    {
        return (dataTracker.method_1501(18) & 1) != 0;
    }

    public void setAdult(boolean flag)
    {
        if(flag)
        {
            dataTracker.method_1509(18, (byte) 1);
        } else
        {
            dataTracker.method_1509(18, (byte) 0);
        }
    }

    //EATEN
    public boolean getEaten()
    {
        return (dataTracker.method_1501(19) & 1) != 0;
    }

    public void setEaten(boolean flag)
    {
        if(flag)
        {
            dataTracker.method_1509(19, (byte) 1);
        } else
        {
            dataTracker.method_1509(19, (byte) 0);
        }
    }

    //TAMED //TODO: MOVE TAMED INTO ENTITY CUSTOM WM?
    public boolean getTamed()
    {
        return (dataTracker.method_1501(20) & 1) != 0;
    }

    public void setTamed(boolean flag)
    {
        if(flag)
        {
            dataTracker.method_1509(20, (byte) 1);
        } else
        {
            dataTracker.method_1509(20, (byte) 0);
        }
    }

    //PUBLIC
    public boolean getPublic()
    {
        return (dataTracker.method_1501(21) & 1) != 0;
    }

    public void setPublic(boolean flag)
    {
        if(flag)
        {
            dataTracker.method_1509(21, (byte) 1);
        } else
        {
            dataTracker.method_1509(21, (byte) 0);
        }
    }

    //BRED
    public boolean getBred()
    {
        return (dataTracker.method_1501(22) & 1) != 0;
    }

    public void setBred(boolean flag)
    {
        if(flag)
        {
            dataTracker.method_1509(22, (byte) 1);
        } else
        {
            dataTracker.method_1509(22, (byte) 0);
        }
    }


    //OWNER
    public void setOwner(String owner)
    {
        this.dataTracker.method_1509(30, owner);
    }

    public String getOwner()
    {
        return this.dataTracker.method_1510(30);
    }

    //NAME
    public void setName(String name)
    {
        this.dataTracker.method_1509(31, name);
    }

    public String getName()
    {
        return this.dataTracker.method_1510(31);
    }
}
