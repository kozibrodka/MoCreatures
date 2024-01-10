
package net.kozibrodka.mocreatures.entity;

import net.fabricmc.loader.api.FabricLoader;
import net.kozibrodka.mocreatures.events.KeybindListener;
import net.kozibrodka.mocreatures.events.mod_mocreatures;
import net.kozibrodka.mocreatures.fuelsystem.GuiHorseFuel;
import net.kozibrodka.mocreatures.mixin.LivingAccesor;
import net.kozibrodka.mocreatures.mixin.MonsterBaseAccesor;
import net.kozibrodka.mocreatures.mixin.WalkingBaseAccesor;
import net.kozibrodka.mocreatures.mocreatures.AnimalChest;
import net.kozibrodka.mocreatures.mocreatures.MoCGUI;
import net.kozibrodka.mocreatures.mocreatures.MoCreatureRacial;
import net.minecraft.block.Block;
import net.minecraft.class_61;
import net.minecraft.client.Minecraft;
import net.minecraft.entity.Entity;
import net.minecraft.entity.ItemEntity;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.mob.MobEntity;
import net.minecraft.entity.mob.MonsterEntity;
import net.minecraft.entity.passive.AnimalEntity;
import net.minecraft.entity.passive.WolfEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.inventory.Inventory;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NbtCompound;
import net.minecraft.nbt.NbtList;
import net.minecraft.sound.BlockSoundGroup;
import net.minecraft.util.math.MathHelper;
import net.minecraft.world.World;
import net.modificationstation.stationapi.api.util.Identifier;
import net.modificationstation.stationapi.api.server.entity.MobSpawnDataProvider;
import org.lwjgl.input.Keyboard;

import java.util.List;

public class EntityHorse extends AnimalEntity implements Inventory, MobSpawnDataProvider, MoCreatureRacial
{

    public EntityHorse(World world)
    {
        super(world);
        horseboolean = false;
        setBoundingBoxSpacing(1.4F, 1.6F);
        health = 20;
        rideable = false;
        isjumping = false;
        tamed = false;
        texture = "/assets/mocreatures/stationapi/textures/mob/horseb.png";
        typeint = 0;
        HorseSpeed = 0.80000000000000004D;
        HorseJump = 0.40000000000000002D;
        temper = 100;
        typechosen = false;
        fwingb = 0.0F;
        fwingc = 0.0F;
        fwingh = 1.0F;
        localstack = new ItemStack[27];
        localstackx54 = new ItemStack[54];
        cargoItems = new ItemStack[1];
        maxhealth = 20;
        hasreproduced = false;
        gestationtime = 0;
        eatenpumpkin = false;
        bred = false;
        nightmareInt = 0;
        fireImmune = false;
        adult = true;
        b = 0.35F;
        roped = false;
        roper = null;
        field_1045 = true;
        name = "";
        displayname = false;
        isHorsePublic = false;
        horseOwner = "";
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

    public void setType(int i)
    {
        typeint = i;
        typechosen = false;
        chooseType();
    }

    public void chooseType()
    {
        int i = mocr.mocreaturesGlass.animals.pegasusChanceS;
        if(typeint == 0)
        {
            if(random.nextInt(5) == 0)
            {
                adult = false;
            }
            int j = random.nextInt(100);
            if(j <= 51 - i)
            {
                typeint = 1;
            } else
            if(j <= 86 - i)
            {
                typeint = 2;
            } else
            if(j <= 95 - i)
            {
                typeint = 3;
            } else
            if(j <= 99 - i)
            {
                typeint = 4;
            } else
            {
                typeint = 5;
            }
        }
        if(!typechosen)
        {
            if(typeint == 1)
            {
                HorseSpeed = 0.90000000000000002D;
                texture = "/assets/mocreatures/stationapi/textures/mob/horseb.png";
                maxhealth = 25;
            } else
            if(typeint == 2)
            {
                HorseSpeed = 1.0D;
                temper = 200;
                HorseJump = 0.5D;
                texture = "/assets/mocreatures/stationapi/textures/mob/horsebrownb.png";
                maxhealth = 30;
            } else
            if(typeint == 3)
            {
                HorseSpeed = 1.1000000000000001D;
                temper = 300;
                HorseJump = 0.59999999999999998D;
                texture = "/assets/mocreatures/stationapi/textures/mob/horseblackb.png";
                maxhealth = 35;
            } else
            if(typeint == 4)
            {
                HorseSpeed = 1.3D;
                HorseJump = 0.59999999999999998D;
                temper = 400;
                texture = "/assets/mocreatures/stationapi/textures/mob/horsegoldb.png";
                maxhealth = 40;
            } else
            if(typeint == 5)
            {
                HorseSpeed = 1.2D;
                temper = 500;
                texture = "/assets/mocreatures/stationapi/textures/mob/horsewhiteb.png";
                maxhealth = 40;
            } else
            if(typeint == 6)
            {
                HorseSpeed = 0.90000000000000002D;
                temper = 600;
                texture = "/assets/mocreatures/stationapi/textures/mob/horsepackb.png";
                maxhealth = 40;
            } else
            if(typeint == 7)
            {
                HorseSpeed = 1.3D;
                temper = 700;
                HorseJump = 0.59999999999999998D;
                texture = "/assets/mocreatures/stationapi/textures/mob/horsenightb.png";
                maxhealth = 50;
                fireImmune = true;
            } else
            if(typeint == 8)
            {
                HorseSpeed = 1.3D;
                temper = 800;
                texture = "/assets/mocreatures/stationapi/textures/mob/horsebpb.png";
                maxhealth = 50;
                fireImmune = true;
            }
            health = maxhealth;
        }
        typechosen = true;
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

    public void method_937()
    {
        if(!typechosen && world.isRemote && getType() != 0){
            typechosen = true;
            chooseType(getType());
        }
        if(random.nextInt(300) == 0 && health < maxhealth && field_1041 == 0)
        {
            health++;
        }
        Riding();
        if(typeint == 5 || typeint == 8)
        {
            fwinge = fwingb;
            fwingd = fwingc;
            fwingc = (float)((double)fwingc + (double)(field_1623 ? -1 : 4) * 0.29999999999999999D);
            if(fwingc < 0.0F)
            {
                fwingc = 0.0F;
            }
            if(fwingc > 1.0F)
            {
                fwingc = 1.0F;
            }
            if(!field_1623 && fwingh < 1.0F)
            {
                fwingh = 0.3F;
            }
            fwingh = (float)((double)fwingh * 0.90000000000000002D);
            if(!field_1623 && velocityY < 0.0D)
            {
                velocityY *= 0.59999999999999998D;
            }
            fwingb += fwingh * 2.0F;
        }
        super.method_937();
        if(typeint == 7 && field_1594 != null && nightmareInt > 0 && random.nextInt(2) == 0)
        {
            NightmareEffect();
        }
        if(!adult && random.nextInt(200) == 0)
        {
            b += 0.01F;
            if(b >= 1.0F)
            {
                adult = true;
            }
        }
        if(!ReadyforParenting(this))
        {
            return;
        }
        int i = 0;
        List list = world.getEntities(this, boundingBox.expand(8D, 3D, 8D));
        for(int j = 0; j < list.size(); j++)
        {
            Entity entity = (Entity)list.get(j);
            if(entity instanceof EntityHorse)
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
            if(!(entity1 instanceof EntityHorse) || entity1 == this)
            {
                continue;
            }
            EntityHorse entityhorse = (EntityHorse)entity1;
            if(!ReadyforParenting(this) || !ReadyforParenting(entityhorse))
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
            EntityHorse entityhorse1 = new EntityHorse(world);
            entityhorse1.method_1340(x, y, z);
            world.method_210(entityhorse1);
            world.playSound(this, "mob.chickenplop", 1.0F, (random.nextFloat() - random.nextFloat()) * 0.2F + 1.0F);
            eatenpumpkin = false;
            if(!(mocr.mocreaturesGlass.animals.easybreeding))
            {
                hasreproduced = true;
            }
            entityhorse.eatenpumpkin = false;
            gestationtime = 0;
            entityhorse.gestationtime = 0;
            int l = HorseGenetics(this, entityhorse);
            entityhorse1.bred = true;
            entityhorse1.adult = false;
            entityhorse1.setType(l);
            break;
        }

    }

    protected void method_910()
    {
        if(!field_1026 && field_1594 == null)
        {
            super.method_910();
        }
        if(tamed && field_1595 == null && roper != null)
        {
            float f = roper.method_1351(this);
            if(f > 5F)
            {
                method_429(roper, f);
            }
        }
    }

    private void method_429(Entity entity, float f)
    {
        class_61 pathentity = world.method_192(this, entity, 16F);
        if(pathentity == null && f > 12F)
        {
            int i = MathHelper.floor(entity.x) - 2;
            int j = MathHelper.floor(entity.z) - 2;
            int k = MathHelper.floor(entity.boundingBox.minY);
            for(int l = 0; l <= 4; l++)
            {
                for(int i1 = 0; i1 <= 4; i1++)
                {
                    if((l < 1 || i1 < 1 || l > 3 || i1 > 3) && world.method_1780(i + l, k - 1, j + i1) && !world.method_1780(i + l, k, j + i1) && !world.method_1780(i + l, k + 1, j + i1))
                    {
                        method_1341((float)(i + l) + 0.5F, k, (float)(j + i1) + 0.5F, yaw, pitch);
                        return;
                    }
                }

            }

        } else
        {
            method_635(pathentity);
        }
    }

    private void NightmareEffect()
    {
        int i = MathHelper.floor(x);
        int j = MathHelper.floor(boundingBox.minY);
        int k = MathHelper.floor(z);
        world.setBlock(i - 1, j, k - 1, Block.FIRE.id);
        PlayerEntity entityplayer = (PlayerEntity)field_1594;
        if(entityplayer != null && entityplayer.fire > 0)
        {
            entityplayer.fire = 0;
        }
        nightmareInt--;
    }

    public boolean ReadyforParenting(EntityHorse entityhorse)
    {
        return entityhorse.field_1594 == null && entityhorse.field_1595 == null && entityhorse.tamed && entityhorse.eatenpumpkin && !entityhorse.hasreproduced && entityhorse.adult;
    }

    private int HorseGenetics(EntityHorse entityhorse, EntityHorse entityhorse1)
    {
        if(entityhorse.typeint == entityhorse1.typeint)
        {
            return entityhorse.typeint;
        }
        int i = entityhorse.typeint + entityhorse1.typeint;
        boolean flag = mocr.mocreaturesGlass.animals.easybreeding;
        boolean flag1 = random.nextInt(3) == 0;
        if(i == 7 && (flag || flag1))
        {
            return 6;
        }
        if(i == 9 && (flag || flag1))
        {
            return 7;
        }
        if(i == 10 && (flag || flag1))
        {
            return 5;
        } else
        {
            return i == 12 && (flag || flag1) ? 8 : 0;
        }
    }

    public boolean damage(Entity entity, int i)
    {
        if(field_1594 != null && entity == field_1594)
        {
            return false;
        }
        if(entity instanceof WolfEntity)
        {
            MobEntity entitycreature = (MobEntity)entity;
            ((WalkingBaseAccesor)entitycreature).setTarget(null);

            return false;
        } else
        {
            return super.damage(entity, i);
        }
    }

    public void method_945(float f, float f1)
    {
        if(isSubmergedInWater())
        {
            if(field_1594 != null)
            {
                velocityX += field_1594.velocityX * (HorseSpeed / 2D);
                velocityZ += field_1594.velocityZ * (HorseSpeed / 2D);
                PlayerEntity entityplayer = (PlayerEntity)field_1594;
                if(((LivingAccesor)entityplayer).getJumping() && !isjumping)
                {
                    velocityY += 0.5D;
                    isjumping = true;
                }
                move(velocityX, velocityY, velocityZ);
                if(field_1623)
                {
                    isjumping = false;
                }
                pitch = field_1594.pitch * 0.5F;
                if(random.nextInt(20) == 0)
                {
                    yaw = field_1594.yaw;
                }
                method_1342(yaw, pitch);
                if(!tamed)
                {
                    field_1594 = null;
                }
            }
            double d = y;
            method_1324(f, f1, 0.02F);
            move(velocityX, velocityY, velocityZ);
            velocityX *= 0.80000001192092896D;
            velocityY *= 0.80000001192092896D;
            velocityZ *= 0.80000001192092896D;
            velocityY -= 0.02D;
            if(field_1624 && method_1344(velocityX, ((velocityY + 0.60000002384185791D) - y) + d, velocityZ))
            {
                velocityY = 0.30000001192092901D;
            }
        } else
        if(method_1335())
        {
            if(field_1594 != null)
            {
                velocityX += field_1594.velocityX * (HorseSpeed / 2D);
                velocityZ += field_1594.velocityZ * (HorseSpeed / 2D);
                PlayerEntity entityplayer1 = (PlayerEntity)field_1594;
                if(((LivingAccesor)entityplayer1).getJumping() && !isjumping)
                {
                    velocityY += 0.5D;
                    isjumping = true;
                }
                move(velocityX, velocityY, velocityZ);
                if(field_1623)
                {
                    isjumping = false;
                }
                pitch = field_1594.pitch * 0.5F;
                if(random.nextInt(20) == 0)
                {
                    yaw = field_1594.yaw;
                }
                method_1342(yaw, pitch);
                if(!tamed)
                {
                    field_1594 = null;
                }
            }
            double d1 = y;
            method_1324(f, f1, 0.02F);
            move(velocityX, velocityY, velocityZ);
            velocityX *= 0.5D;
            velocityY *= 0.5D;
            velocityZ *= 0.5D;
            velocityY -= 0.02D;
            if(field_1624 && method_1344(velocityX, ((velocityY + 0.60000002384185791D) - y) + d1, velocityZ))
            {
                velocityY = 0.30000001192092901D;
            }
        } else
        {
            float f2 = 0.91F;
            if(field_1623)
            {
                f2 = 0.5460001F;
                int i = world.getBlockId(MathHelper.floor(x), MathHelper.floor(boundingBox.minY) - 1, MathHelper.floor(z));
                if(i > 0)
                {
                    f2 = Block.BLOCKS[i].slipperiness * 0.91F;
                }
            }
            float f3 = 0.162771F / (f2 * f2 * f2);
            method_1324(f, f1, field_1623 ? 0.1F * f3 : 0.02F);
            f2 = 0.91F;
            if(field_1623)
            {
                f2 = 0.5460001F;
                int j = world.getBlockId(MathHelper.floor(x), MathHelper.floor(boundingBox.minY) - 1, MathHelper.floor(z));
                if(j > 0)
                {
                    f2 = Block.BLOCKS[j].slipperiness * 0.91F;
                }
            }
            if(method_932())
            {
                field_1636 = 0.0F;
                if(velocityY < -0.14999999999999999D)
                {
                    velocityY = -0.14999999999999999D;
                }
            }
            if(field_1594 != null && !tamed)
            {
                if(random.nextInt(5) == 0 && !isjumping)
                {
                    velocityY += 0.40000000000000002D;
                    isjumping = true;
                }
                if(random.nextInt(10) == 0)
                {
                    velocityX += random.nextDouble() / 30D;
                    velocityZ += random.nextDouble() / 10D;
                }
                move(velocityX, velocityY, velocityZ);
                if(random.nextInt(50) == 0)
                {
                    world.playSound(this, "mocreatures:horsemad", 1.0F, 1.0F + (random.nextFloat() - random.nextFloat()) * 0.2F);
                    field_1594.velocityY += 0.90000000000000002D;
                    field_1594.velocityZ -= 0.29999999999999999D;
                    field_1594 = null;
                }
                if(field_1623)
                {
                    isjumping = false;
                }
                if(random.nextInt(temper * 8) == 0)
                {
                    tamed = true;
                    PlayerEntity milosc = (PlayerEntity)field_1594;
                    horseOwner = milosc.name;
                    setName(this);
                }
            }
            if(field_1594 != null && tamed)
            {
                boundingBox.maxY = field_1594.boundingBox.maxY;
                velocityX += field_1594.velocityX * HorseSpeed;
                velocityZ += field_1594.velocityZ * HorseSpeed;
                PlayerEntity entityplayer2 = (PlayerEntity)field_1594;
                if(((LivingAccesor)entityplayer2).getJumping() && !isjumping)
                {
                    velocityY += HorseJump;
                    isjumping = true;
                }
                if(((LivingAccesor)entityplayer2).getJumping() && (typeint == 5 || typeint == 8))
                {
                    velocityY += 0.10000000000000001D;
                }
                move(velocityX, velocityY, velocityZ);
                if(field_1623)
                {
                    isjumping = false;
                }
                prevYaw = yaw = field_1594.yaw;
                pitch = field_1594.pitch * 0.5F;
                method_1342(yaw, pitch);
            }
            move(velocityX, velocityY, velocityZ);
            if(field_1624 && method_932())
            {
                velocityY = 0.20000000000000001D;
            }
            if((typeint == 5 || typeint == 8) && field_1594 != null && tamed)
            {
                velocityY -= 0.080000000000000002D;
                velocityY *= 0.60000000000000009D;
            } else
            {
                velocityY -= 0.080000000000000002D;
                velocityY *= 0.98000001907348633D;
            }
            velocityX *= f2;
            velocityZ *= f2;
        }
        field_1048 = field_1049;
        double d2 = x - prevX;
        double d3 = z - prevZ;
        float f4 = MathHelper.sqrt(d2 * d2 + d3 * d3) * 4F;
        if(f4 > 1.0F)
        {
            f4 = 1.0F;
        }
        field_1049 += (f4 - field_1049) * 0.4F;
        field_1050 += field_1049;
        if(mocr.mocreaturesGlass.balancesettings.horse_fuel && mc.currentScreen == null && Keyboard.isKeyDown(KeybindListener.keyBinding_horseFuel.code) && field_1594 != null)
        {
            openFuelGui();
        }
//        animalKeyDown = Keyboard.isKeyDown(mod_mocreatures.keyBinding_horseFuel.key);
        burnFuel(1);
        addFuel();
    }

    //TODO:EXTRA FUEL
    public boolean isFuelled()
    {
        if(mocr.mocreaturesGlass.balancesettings.horse_fuel)
        {
            return animalFuel > 0;
        }else{
            return true;
        }
    }

    public void burnFuel(int a){
        if(mocr.mocreaturesGlass.balancesettings.horse_fuel)
        {
            animalFuel = animalFuel - a;
        }
    }

    public void addFuel(){
        if(mocr.mocreaturesGlass.balancesettings.horse_fuel)
        {
            if(animalFuel <= 0 && field_1594 != null && !world.isRemote)
            {
                if(cargoItems[0] != null && cargoItems[0].itemId == Item.COAL.id)  //PALIWO
                {
                    animalFuel = 200; //plane.planeFuelAdd
                    removeStack(0, 1);
                }
            }
        }
    }

    public ItemStack removeStack(int i, int j)
    {
        if(cargoItems[i] != null)
        {
            if(cargoItems[i].count <= j)
            {
                ItemStack itemstack = cargoItems[i];
                cargoItems[i] = null;
                return itemstack;
            }
            ItemStack itemstack1 = cargoItems[i].split(j);
            if(cargoItems[i].count == 0)
            {
                cargoItems[i] = null;
            }
            return itemstack1;
        } else
        {
            return null;
        }
    }

    public void openFuelGui(){
        if(!world.isRemote) {
            if (mc.currentScreen instanceof GuiHorseFuel) {
                mc.setScreen(null);
            } else if (field_1594.field_1595 instanceof EntityHorse) {
                mc.setScreen(new GuiHorseFuel(((PlayerEntity)field_1594).inventory, (EntityHorse)field_1594.field_1595));
            }
        }
    }


    public int getBurnTimeRemainingScaled(int i)
    {
        return (animalFuel * i) / 200;//return (planeFuel * i) / plane.planeFuelAdd;
    }

    public int size()
    {
        return 1;
    }

    public ItemStack getStack(int i)
    {
        return cargoItems[i];
    }

    public String getName()
    {
        return "TESTOWY NA RAZIE";
    }

    public int getMaxCountPerStack()
    {
        return 64;
    }

    public void markDirty()
    {
    }

    public void setStack(int i, ItemStack itemstack)
    {
        cargoItems[i] = itemstack;
        if(itemstack != null && itemstack.count > getMaxCountPerStack())
        {
            itemstack.count = getMaxCountPerStack();
        }
    }

    public boolean canPlayerUse(PlayerEntity entityplayer)
    {
        if(dead)
        {
            return false;
        } else
        {
            return entityplayer.method_1352(this) <= 64D;
        }
    }

    //TODO:EXTRA FUEL end

    protected void method_1389(float f)
    {
        int i = (int)Math.ceil(f - 3F);
        if(i > 0 && typeint != 5 && typeint != 8)
        {
            if(typeint >= 3)
            {
                i /= 3;
            }
            if(i > 0)
            {
                damage(this, i);
            }
            if(field_1594 != null && i > 0)
            {
                field_1594.damage(null, i);
            }
            if(typeint == 5 || typeint == 8)
            {
                return;
            }
            int j = world.getBlockId(MathHelper.floor(x), MathHelper.floor(y - 0.20000000298023221D - (double)prevPitch), MathHelper.floor(z));
            if(j > 0)
            {
                BlockSoundGroup stepsound = Block.BLOCKS[j].soundGroup;
                world.playSound(this, stepsound.getSound(), stepsound.method_1976() * 0.5F, stepsound.method_1977() * 0.75F);
            }
        }
    }

    protected float method_915()
    {
        return 0.4F;
    }

    public boolean method_1356()
    {
        return field_1594 == null;
    }

    public boolean method_1323(PlayerEntity entityplayer)
    {
        ItemStack itemstack = entityplayer.inventory.getSelectedItem();
        if(tamed && !isHorsePublic && !entityplayer.name.equals(horseOwner))
        {
            return false;
        }
        if(itemstack != null && tamed && entityplayer.name.equals(horseOwner) && (itemstack.itemId == Item.GOLDEN_SWORD.id || itemstack.itemId == Item.STONE_SWORD.id || itemstack.itemId == Item.WOODEN_SWORD.id || itemstack.itemId == Item.IRON_SWORD.id || itemstack.itemId == Item.DIAMOND_SWORD.id))
        {
            if(isHorsePublic == true){
                isHorsePublic = false;
                for(int var3 = 0; var3 < 7; ++var3) {
                    double var4 = this.random.nextGaussian() * 0.02D;
                    double var6 = this.random.nextGaussian() * 0.02D;
                    double var8 = this.random.nextGaussian() * 0.02D;
                    world.addParticle("heart", this.x + (double) (this.random.nextFloat() * this.spacingXZ * 2.0F) - (double) this.spacingXZ, this.y + 0.5D + (double) (this.random.nextFloat() * this.spacingY), this.z + (double) (this.random.nextFloat() * this.spacingXZ * 2.0F) - (double) this.spacingXZ, var4, var6, var8);
                }
            }else{
                isHorsePublic = true;
                for(int var3 = 0; var3 < 7; ++var3) {
                    double var4 = this.random.nextGaussian() * 0.02D;
                    double var6 = this.random.nextGaussian() * 0.02D;
                    double var8 = this.random.nextGaussian() * 0.02D;
                    world.addParticle("flame", this.x + (double) (this.random.nextFloat() * this.spacingXZ * 2.0F) - (double) this.spacingXZ, this.y + 0.5D + (double) (this.random.nextFloat() * this.spacingY), this.z + (double) (this.random.nextFloat() * this.spacingXZ * 2.0F) - (double) this.spacingXZ, var4, var6, var8);
                }
            }
            return true;
        }
        if(itemstack != null && itemstack.itemId == Item.WHEAT.id)
        {
            if(--itemstack.count == 0)
            {
                entityplayer.inventory.setStack(entityplayer.inventory.selectedSlot, null);
            }
            if((temper -= 25) < 25)
            {
                temper = 25;
            }
            if((health += 5) > maxhealth)
            {
                health = maxhealth;
            }
            world.playSound(this, "mocreatures:eating", 1.0F, 1.0F + (random.nextFloat() - random.nextFloat()) * 0.2F);
            if(!adult && b < 1.0F)
            {
                b += 0.01F;
            }
            return true;
        }
        if(itemstack != null && itemstack.itemId == mod_mocreatures.sugarlump.id)
        {
            if(--itemstack.count == 0)
            {
                entityplayer.inventory.setStack(entityplayer.inventory.selectedSlot, null);
            }
            if((temper -= 50) < 25)
            {
                temper = 25;
            }
            if((health += 10) > maxhealth)
            {
                health = maxhealth;
            }
            world.playSound(this, "mocreatures:eating", 1.0F, 1.0F + (random.nextFloat() - random.nextFloat()) * 0.2F);
            if(!adult && b < 1.0F)
            {
                b += 0.02F;
            }
            return true;
        }
        if(itemstack != null && itemstack.itemId == Item.BREAD.id)
        {
            if(--itemstack.count == 0)
            {
                entityplayer.inventory.setStack(entityplayer.inventory.selectedSlot, null);
            }
            if((temper -= 100) < 25)
            {
                temper = 25;
            }
            if((health += 20) > maxhealth)
            {
                health = maxhealth;
            }
            world.playSound(this, "mocreatures:eating", 1.0F, 1.0F + (random.nextFloat() - random.nextFloat()) * 0.2F);
            if(!adult && b < 1.0F)
            {
                b += 0.03F;
            }
            return true;
        }
        if(itemstack != null && (((mocr.mocreaturesGlass.balancesettings.balance_drop) && itemstack.itemId == mod_mocreatures.greenapple.id)  ||  (((itemstack.itemId == Item.APPLE.id)||(itemstack.itemId == Item.GOLDEN_APPLE.id)) && !mocr.mocreaturesGlass.balancesettings.balance_drop)))
        {
            if(--itemstack.count == 0)
            {
                entityplayer.inventory.setStack(entityplayer.inventory.selectedSlot, null);
            }
            tamed = true;
            health = maxhealth;
            world.playSound(this, "mocreatures:eating", 1.0F, 1.0F + (random.nextFloat() - random.nextFloat()) * 0.2F);
            if(!adult && b < 1.0F)
            {
                b += 0.05F;
            }
            horseOwner = entityplayer.name;
            setName(this);
            return true;
        }
        if(itemstack != null && tamed && itemstack.itemId == Block.CHEST.id && (typeint == 6 || typeint == 8))
        {
            if(chestedhorse)
            {
                return false;
            }
            if(--itemstack.count == 0)
            {
                entityplayer.inventory.setStack(entityplayer.inventory.selectedSlot, null);
            }
            chestedhorse = true;
            world.playSound(this, "mob.chickenplop", 1.0F, (random.nextFloat() - random.nextFloat()) * 0.2F + 1.0F);
            return true;
        }
        if(itemstack != null && tamed && itemstack.itemId == mod_mocreatures.haystack.id)
        {
            if(--itemstack.count == 0)
            {
                entityplayer.inventory.setStack(entityplayer.inventory.selectedSlot, null);
            }
            eatinghaystack = true;
            world.playSound(this, "mocreatures:eating", 1.0F, 1.0F + (random.nextFloat() - random.nextFloat()) * 0.2F);
            health = maxhealth;
            return true;
        }
        if(itemstack != null && (itemstack.itemId == Item.STONE_SHOVEL.id || itemstack.itemId == Block.TORCH.id) && chestedhorse)
        {
            if(typeint == 8)
            {
                localhorsechest = new AnimalChest(localstack, "Dark Pegasus chest", localstack.length);
                entityplayer.method_486(localhorsechest);
                return true;
            }
            if(typeint == 6)
            {
                localhorsechestx54 = new AnimalChest(localstackx54, "Pack Horse chest", localstackx54.length);
                entityplayer.method_486(localhorsechestx54);
                return true;
            }
        }
        if(itemstack != null && (itemstack.itemId == Block.PUMPKIN.id || itemstack.itemId == Item.MUSHROOM_STEW.id || itemstack.itemId == Item.CAKE.id))
        {
            if(hasreproduced || !adult)
            {
                return false;
            }
            if(itemstack.itemId == Item.MUSHROOM_STEW.id)
            {
                itemstack.count--;
                entityplayer.inventory.method_671(new ItemStack(Item.BOWL));
            } else
            if(--itemstack.count == 0)
            {
                entityplayer.inventory.setStack(entityplayer.inventory.selectedSlot, null);
            }
            eatenpumpkin = true;
            health = maxhealth;
            world.playSound(this, "mocreatures:eating", 1.0F, 1.0F + (random.nextFloat() - random.nextFloat()) * 0.2F);
            return true;
        }
        if(itemstack != null && tamed && itemstack.itemId == Item.REDSTONE.id && typeint == 7)
        {
            if(nightmareInt > 500)
            {
                return false;
            }
            if(--itemstack.count == 0)
            {
                entityplayer.inventory.setStack(entityplayer.inventory.selectedSlot, null);
            }
            nightmareInt = 500;
            world.playSound(this, "mocreatures:eating", 1.0F, 1.0F + (random.nextFloat() - random.nextFloat()) * 0.2F);
            return true;
        }
        if(itemstack != null && itemstack.itemId == mod_mocreatures.whip.id && tamed && field_1594 == null)
        {
            eatinghaystack = !eatinghaystack;
            return true;
        }
        if(itemstack != null && field_1594 == null && roper == null && tamed && itemstack.itemId == mod_mocreatures.rope.id)
        {
            if(--itemstack.count == 0)
            {
                entityplayer.inventory.setStack(entityplayer.inventory.selectedSlot, null);
            }
            world.playSound(this, "mocreatures:roping", 1.0F, 1.0F + (random.nextFloat() - random.nextFloat()) * 0.2F);
            roper = entityplayer;
            return true;
        }
        if(roper != null && tamed)
        {
            entityplayer.inventory.method_671(new ItemStack(mod_mocreatures.rope));
            world.playSound(this, "mocreatures:roping", 1.0F, 1.0F + (random.nextFloat() - random.nextFloat()) * 0.2F);
            roper = null;
            return true;
        }
        if(itemstack != null && tamed && (itemstack.itemId == mod_mocreatures.medallion.id || itemstack.itemId == Item.BOOK.id))
        {
            setName(this);
            return true;
        }
        if(itemstack != null && tamed && (itemstack.itemId == Item.DIAMOND_PICKAXE.id || itemstack.itemId == Item.WOODEN_PICKAXE.id || itemstack.itemId == Item.STONE_PICKAXE.id || itemstack.itemId == Item.IRON_PICKAXE.id || itemstack.itemId == Item.GOLDEN_PICKAXE.id))
        {
            displayname = !displayname;
            return true;
        }
        if(rideable && adult)
        {
            entityplayer.yaw = yaw;
            entityplayer.pitch = pitch;
            eatinghaystack = false;
            entityplayer.method_1376(this);
            gestationtime = 0;
            if(typeint == 8)
            {
                entityplayer.incrementStat(mod_mocreatures.WilfFlyingWest);
            }
            return true;
        } else
        {
            return false;
        }
    }

    public void method_938(Entity entity)
    {
        if(field_1024 >= 0 && entity != null)
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
            method_933();
        }
        world.method_279(this, 3F);
        if(chestedhorse && (typeint == 6 || typeint == 8))
        {
            int i = MathHelper.floor(x);
            int j = MathHelper.floor(boundingBox.minY);
            int k = MathHelper.floor(z);
            HorseRemoval(world, i, j, k);
        }
    }

    public void HorseRemoval(World world, int i, int j, int k)
    {
        if(localstack != null)
        {
            localhorsechest = new AnimalChest(localstack, "HorseChest", localstack.length);
            label0:
            for(int l = 0; l < localhorsechest.size(); l++)
            {
                ItemStack itemstack = localhorsechest.getStack(l);
                if(itemstack == null)
                {
                    continue;
                }
                float f = random.nextFloat() * 0.8F + 0.1F;
                float f1 = random.nextFloat() * 0.8F + 0.1F;
                float f2 = random.nextFloat() * 0.8F + 0.1F;
                do
                {
                    if(itemstack.count <= 0)
                    {
                        continue label0;
                    }
                    int i1 = random.nextInt(21) + 10;
                    if(i1 > itemstack.count)
                    {
                        i1 = itemstack.count;
                    }
                    itemstack.count -= i1;
                    ItemEntity entityitem = new ItemEntity(world, (float)i + f, (float)j + f1, (float)k + f2, new ItemStack(itemstack.itemId, i1, itemstack.getDamage()));
                    float f3 = 0.05F;
                    entityitem.velocityX = (float)random.nextGaussian() * f3;
                    entityitem.velocityY = (float)random.nextGaussian() * f3 + 0.2F;
                    entityitem.velocityZ = (float)random.nextGaussian() * f3;
                    world.method_210(entityitem);
                } while(true);
            }
        }
        if(localstackx54 != null)
        {
            localhorsechestx54 = new AnimalChest(localstackx54, "HorseChest", localstackx54.length);
            label0:
            for(int l = 0; l < localhorsechestx54.size(); l++)
            {
                ItemStack itemstack = localhorsechestx54.getStack(l);
                if(itemstack == null)
                {
                    continue;
                }
                float f = random.nextFloat() * 0.8F + 0.1F;
                float f1 = random.nextFloat() * 0.8F + 0.1F;
                float f2 = random.nextFloat() * 0.8F + 0.1F;
                do
                {
                    if(itemstack.count <= 0)
                    {
                        continue label0;
                    }
                    int i1 = random.nextInt(21) + 10;
                    if(i1 > itemstack.count)
                    {
                        i1 = itemstack.count;
                    }
                    itemstack.count -= i1;
                    ItemEntity entityitem = new ItemEntity(world, (float)i + f, (float)j + f1, (float)k + f2, new ItemStack(itemstack.itemId, i1, itemstack.getDamage()));
                    float f3 = 0.05F;
                    entityitem.velocityX = (float)random.nextGaussian() * f3;
                    entityitem.velocityY = (float)random.nextGaussian() * f3 + 0.2F;
                    entityitem.velocityZ = (float)random.nextGaussian() * f3;
                    world.method_210(entityitem);
                } while(true);
            }
        }
    }

    public void writeNbt(NbtCompound nbttagcompound)
    {
        super.writeNbt(nbttagcompound);
        nbttagcompound.putBoolean("Saddle", rideable);
        nbttagcompound.putBoolean("EatingHaystack", eatinghaystack);
        nbttagcompound.putBoolean("Tamed", tamed);
        nbttagcompound.putBoolean("HorseBoolean", horseboolean);
        nbttagcompound.putInt("TypeInt", typeint);
        nbttagcompound.putBoolean("ChestedHorse", chestedhorse);
        nbttagcompound.putBoolean("HasReproduced", hasreproduced);
        nbttagcompound.putBoolean("Bred", bred);
        nbttagcompound.putBoolean("Adult", adult);
        nbttagcompound.putFloat("Age", b);
        nbttagcompound.putString("Name", name);
        nbttagcompound.putBoolean("DisplayName", displayname);
        nbttagcompound.putInt("GestationTime", gestationtime);
        nbttagcompound.putBoolean("EatenPumpkin", eatenpumpkin);
        nbttagcompound.putBoolean("PublicHorse", isHorsePublic);
        nbttagcompound.putString("HorseOwner", horseOwner);
        if(typeint == 8)
        {
            NbtList nbttaglist = new NbtList();
            for(int i = 0; i < localstack.length; i++)
            {
                if(localstack[i] != null)
                {
                    NbtCompound nbttagcompound1 = new NbtCompound();
                    nbttagcompound1.putByte("Slot", (byte)i);
                    localstack[i].writeNbt(nbttagcompound1);
                    nbttaglist.add(nbttagcompound1);
                }
            }

            nbttagcompound.put("Items", nbttaglist);
        }

        if(typeint == 6)
        {
            NbtList nbttaglist = new NbtList();
            for(int i = 0; i < localstackx54.length; i++)
            {
                if(localstackx54[i] != null)
                {
                    NbtCompound nbttagcompound1 = new NbtCompound();
                    nbttagcompound1.putByte("Slot", (byte)i);
                    localstackx54[i].writeNbt(nbttagcompound1);
                    nbttaglist.add(nbttagcompound1);
                }
            }

            nbttagcompound.put("Items", nbttaglist);
        }
        if(mocr.mocreaturesGlass.balancesettings.horse_fuel) {
            NbtList fuelList = new NbtList();
            for (int i = 0; i < cargoItems.length; i++) {
                if (cargoItems[i] != null) {
                    NbtCompound comp2 = new NbtCompound();
                    comp2.putByte("SlotFuel", (byte) i);
                    cargoItems[i].writeNbt(comp2);
                    fuelList.add(comp2);
                }
            }
            nbttagcompound.put("Fuels", fuelList);
        }

    }

    public void readNbt(NbtCompound nbttagcompound)
    {
        super.readNbt(nbttagcompound);
        rideable = nbttagcompound.getBoolean("Saddle");
        tamed = nbttagcompound.getBoolean("Tamed");
        eatinghaystack = nbttagcompound.getBoolean("EatingHaystack");
        bred = nbttagcompound.getBoolean("Bred");
        adult = nbttagcompound.getBoolean("Adult");
        horseboolean = nbttagcompound.getBoolean("HorseBoolean");
        chestedhorse = nbttagcompound.getBoolean("ChestedHorse");
        hasreproduced = nbttagcompound.getBoolean("HasReproduced");
        typeint = nbttagcompound.getInt("TypeInt");
        b = nbttagcompound.getFloat("Age");
        name = nbttagcompound.getString("Name");
        displayname = nbttagcompound.getBoolean("DisplayName");
        gestationtime = nbttagcompound.getInt("GestationTime");
        eatenpumpkin = nbttagcompound.getBoolean("EatenPumpkin");
        isHorsePublic = nbttagcompound.getBoolean("PublicHorse");
        horseOwner = nbttagcompound.getString("HorseOwner");
        if(typeint == 8)
        {
            NbtList nbttaglist = nbttagcompound.getList("Items");
            localstack = new ItemStack[27];
            for(int i = 0; i < nbttaglist.size(); i++)
            {
                NbtCompound nbttagcompound1 = (NbtCompound)nbttaglist.get(i);
                int j = nbttagcompound1.getByte("Slot") & 0xff;
                if(j >= 0 && j < localstack.length)
                {
                    localstack[j] = new ItemStack(nbttagcompound1);
                }
            }

        }

        if(typeint == 6)
        {
            NbtList nbttaglist = nbttagcompound.getList("Items");
            localstackx54 = new ItemStack[54];
            for(int i = 0; i < nbttaglist.size(); i++)
            {
                NbtCompound nbttagcompound1 = (NbtCompound)nbttaglist.get(i);
                int j = nbttagcompound1.getByte("Slot") & 0xff;
                if(j >= 0 && j < localstackx54.length)
                {
                    localstackx54[j] = new ItemStack(nbttagcompound1);
                }
            }
        }

        if(mocr.mocreaturesGlass.balancesettings.horse_fuel) {
            NbtList nbttaglist2 = nbttagcompound.getList("Fuels");
            cargoItems = new ItemStack[1];
            for(int i = 0; i < nbttaglist2.size(); i++)
            {
                NbtCompound nbttagcompound1 = (NbtCompound)nbttaglist2.get(i);
                int k = nbttagcompound1.getByte("SlotFuel") & 0xff;
                if(k >= 0 && k < cargoItems.length)
                {
                    cargoItems[k] = new ItemStack(nbttagcompound1);
                }
            }
        }


    }

    protected boolean method_640()
    {
        return eatinghaystack || field_1594 != null;
    }

    protected String method_911()
    {
        return "mocreatures:horsegrunt";
    }

    protected String method_912()
    {
        return "mocreatures:horsehurt";
    }

    protected String method_913()
    {
        return "mocreatures:horsedying";
    }

    protected void method_933()
    {
        int i = random.nextInt(3);
        for(int j = 0; j < i; j++)
        {
            method_1327(new ItemStack(method_914(), 1, 0), 0.0F);
        }

        if(rideable && mocr.mocreaturesGlass.balancesettings.horse_saddle) {
            int k = random.nextInt(2);
            for (int j = 0; j < k; j++) {
                method_1327(new ItemStack(mod_mocreatures.horsesaddle, 1, 0), 0.0F);
            }
        }
    }

    protected int method_914()
    {
        return Item.LEATHER.id;
    }

    public int method_916()
    {
        return 6;
    }

    public boolean canSpawn()
    {
        return mocr.mocreaturesGlass.animals.horsefreq > 0 && super.canSpawn();
    }

    public boolean renderName()
    {
        return displayname && field_1594 == null;
    }

    public String dajTexture(){
        return texture;
    }

    public static void setName(EntityHorse entityhorse)
    {
        entityhorse.displayname = true;
        mc.setScreen(new MoCGUI(entityhorse, entityhorse.name));
    }

    public static Minecraft mc = Minecraft.class.cast(FabricLoader.getInstance().getGameInstance());
    mod_mocreatures mocr = new mod_mocreatures();
    public String horseOwner;
    public boolean isHorsePublic;
    private int nightmareInt;
    private boolean bred;
    public boolean eatenpumpkin;
    private int gestationtime;
    public boolean hasreproduced;
    public int maxhealth;
    private int temper;
    private double HorseSpeed;
    private double HorseJump;
    public int typeint;
    public boolean typechosen;
    public boolean tamed;
    public boolean horseboolean;
    public boolean rideable;
    public boolean isjumping;
    public float fwingb;
    public float fwingc;
    public float fwingd;
    public float fwinge;
    public float fwingh;
    public boolean chestedhorse;
    private Inventory localhorsechest;
    public ItemStack localstack[];
    private Inventory localhorsechestx54;
    public ItemStack localstackx54[];
    public boolean eatinghaystack;
    public float b;
    public boolean adult;
    public boolean roped;
    public LivingEntity roper;
    public String name;
    public boolean displayname;
    public ItemStack cargoItems[];
    private int animalFuel;
    public boolean animalKeyDown;

    @Override
    public Identifier getHandlerIdentifier() {
        return Identifier.of(mod_mocreatures.MOD_ID, "Horse");
    }
}
