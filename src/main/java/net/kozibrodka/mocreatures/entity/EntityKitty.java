// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) braces deadcode 

package net.kozibrodka.mocreatures.entity;

import net.fabricmc.loader.api.FabricLoader;
import net.kozibrodka.mocreatures.events.mod_mocreatures;
import net.kozibrodka.mocreatures.mocreatures.MoCGUI;
import net.kozibrodka.mocreatures.mocreatures.MoCreatureRacial;
import net.minecraft.block.Block;
import net.minecraft.block.Material;
import net.minecraft.class_61;
import net.minecraft.client.Minecraft;
import net.minecraft.entity.Entity;
import net.minecraft.entity.ItemEntity;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.mob.MonsterEntity;
import net.minecraft.entity.passive.AnimalEntity;
import net.minecraft.entity.passive.WolfEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NbtCompound;
import net.minecraft.util.math.Box;
import net.minecraft.util.math.MathHelper;
import net.minecraft.world.World;
import net.modificationstation.stationapi.api.util.Identifier;
import net.modificationstation.stationapi.api.server.entity.MobSpawnDataProvider;

import java.util.List;

public class EntityKitty extends AnimalEntity implements MobSpawnDataProvider, MoCreatureRacial
{

    public EntityKitty(World world)
    {
        super(world);
        setBoundingBoxSpacing(0.7F, 0.5F);
        texture = "/assets/mocreatures/stationapi/textures/mob/pussycata.png";
        field_1045 = true;
        adult = true;
        edad = 0.4F;
        inBed = false;
        sleepy = false;
        kittystate = 1;
        hungry = false;
        kittytimer = 0;
        health = 15;
        madtimer = random.nextInt(5);
        maxhealth = 15;
        name = "";
        displayname = false;
        onTree = false;
        foundTree = false;
        displayemo = false;
    }

    public boolean renderName()
    {
        return displayname && kittystate != 14 && kittystate != 15 && kittystate > 1;
    }

    public boolean upsideDown()
    {
        return kittystate == 14;
    }

    public boolean onMaBack()
    {
        return kittystate == 15;
    }

    public boolean climbingTree()
    {
        return kittystate == 16 && method_932();
    }

    public double method_1385()
    {
        if(field_1595 instanceof PlayerEntity)
        {
            if(kittystate == 10)
            {
                return (double)(eyeHeight - 1.1F);
            }
            if(upsideDown())
            {
                return (double)(eyeHeight - 1.7F);
            }
            if(onMaBack())
            {
                return (double)(eyeHeight - 1.5F);
            }
        }
        return (double)eyeHeight;
    }

    public int getRandomRace(){
        int i = random.nextInt(100);
        if(i <= 15)
        {
            return 1;
        } else
        if(i <= 30)
        {
            return 2;
        } else
        if(i <= 45)
        {
            return 3;
        } else
        if(i <= 60)
        {
            return 4;
        } else
        if(i <= 70)
        {
            return 5;
        } else
        if(i <= 80)
        {
            return 6;
        } else
        if(i <= 90)
        {
            return 7;
        } else
        {
            return 8;
        }
    }

    public void chooseType(int typeint)
    {
            if(typeint == 1)
            {
                texture = "/assets/mocreatures/stationapi/textures/mob/pussycata.png";
            } else
            if(typeint == 2)
            {
                texture = "/assets/mocreatures/stationapi/textures/mob/pussycatb.png";
            } else
            if(typeint == 3)
            {
                texture = "/assets/mocreatures/stationapi/textures/mob/pussycatc.png";
            } else
            if(typeint == 4)
            {
                texture = "/assets/mocreatures/stationapi/textures/mob/pussycatd.png";
            } else
            if(typeint == 5)
            {
                texture = "/assets/mocreatures/stationapi/textures/mob/pussycate.png";
            } else
            if(typeint == 6)
            {
                texture = "/assets/mocreatures/stationapi/textures/mob/pussycatf.png";
            } else
            if(typeint == 7)
            {
                texture = "/assets/mocreatures/stationapi/textures/mob/pussycatg.png";
            }
            if(typeint == 8)
            {
                texture = "/assets/mocreatures/stationapi/textures/mob/pussycath.png";
            }
    }

    public boolean method_1323(PlayerEntity entityplayer)
    {
        ItemStack itemstack = entityplayer.inventory.getSelectedItem();
        if(kittystate == 2 && itemstack != null && itemstack.itemId == mod_mocreatures.medallion.id)
        {
            if(--itemstack.count == 0)
            {
                entityplayer.inventory.setStack(entityplayer.inventory.selectedSlot, null);
            }
            changeKittyState(3);
            health = maxhealth;
            setName(this);
            return true;
        }
        if(kittystate == 7 && itemstack != null && itemstack.itemId == Item.CAKE.id)
        {
            if(--itemstack.count == 0)
            {
                entityplayer.inventory.setStack(entityplayer.inventory.selectedSlot, null);
            }
            world.playSound(this, "moreatures:kittyeatingf", 1.0F, 1.0F + (random.nextFloat() - random.nextFloat()) * 0.2F);
            changeKittyState(9);
            return true;
        }
        if(kittystate == 11 && itemstack != null && itemstack.itemId == mod_mocreatures.woolball.id)
        {
            if(--itemstack.count == 0)
            {
                entityplayer.inventory.setStack(entityplayer.inventory.selectedSlot, null);
            }
            kittystate = 8;
            ItemEntity entityitem = new ItemEntity(world, x, y + 1.0D, z, new ItemStack(mod_mocreatures.woolball, 1));
            entityitem.pickupDelay = 30;
            entityitem.itemAge = -10000;
            world.method_210(entityitem);
            entityitem.velocityY += world.field_214.nextFloat() * 0.05F;
            entityitem.velocityX += (world.field_214.nextFloat() - world.field_214.nextFloat()) * 0.3F;
            entityitem.velocityZ += (world.field_214.nextFloat() - world.field_214.nextFloat()) * 0.3F;
            target = entityitem;
            return true;
        }
        if(kittystate == 13 && itemstack != null && (itemstack.itemId == Item.RAW_FISH.id || itemstack.itemId == Item.COOKED_FISH.id))
        {
            if(--itemstack.count == 0)
            {
                entityplayer.inventory.setStack(entityplayer.inventory.selectedSlot, null);
            }
            world.playSound(this, "kittyeatingf", 1.0F, 1.0F + (random.nextFloat() - random.nextFloat()) * 0.2F);
            changeKittyState(7);
            return true;
        }
        if(itemstack != null && kittystate > 2 && (itemstack.itemId == Item.DIAMOND_PICKAXE.id || itemstack.itemId == Item.WOODEN_PICKAXE.id || itemstack.itemId == Item.STONE_PICKAXE.id || itemstack.itemId == Item.IRON_PICKAXE.id || itemstack.itemId == Item.GOLDEN_PICKAXE.id))
        {
            displayname = !displayname;
            return true;
        }
        if(itemstack != null && kittystate > 2 && (itemstack.itemId == mod_mocreatures.medallion.id || itemstack.itemId == Item.BOOK.id))
        {
            setName(this);
            return true;
        }
        if(itemstack != null && kittystate > 2 && pickable() && itemstack.itemId == mod_mocreatures.rope.id)
        {
            changeKittyState(14);
            method_1376(entityplayer);
            return true;
        }
        if(itemstack != null && kittystate > 2 && whipeable() && itemstack.itemId == mod_mocreatures.whip.id)
        {
            isSitting = !isSitting;
            return true;
        }
        if(itemstack == null && kittystate == 10 && field_1595 != null)
        {
            field_1595 = null;
            return true;
        }
        if(itemstack == null && kittystate > 2 && pickable())
        {
            changeKittyState(15);
            method_1376(entityplayer);
            return true;
        }
        if(itemstack == null && kittystate == 15)
        {
            changeKittyState(7);
            return true;
        } else
        {
            return false;
        }
    }

    private boolean pickable()
    {
        return kittystate != 13 && kittystate != 14 && kittystate != 15 && kittystate != 19 && kittystate != 20 && kittystate != 21;
    }

    public boolean whipeable()
    {
        return kittystate != 13;
    }

    public void method_937()
    {
        if(!typechosen && world.isRemote && getType() != 0){
            typechosen = true;
            chooseType(getType());
        }
        if(!adult && kittystate != 10)
        {
            kittystate = 10;
        }
        if(kittystate != 12)
        {
            super.method_937();
        }
        if(random.nextInt(200) == 0)
        {
            displayemo = !displayemo;
        }
        if(!adult && random.nextInt(200) == 0)
        {
            edad += 0.005F;
            if(edad >= 1.0F)
            {
                adult = true;
                field_1045 = false;
            }
        }
        if(!hungry && !isSitting && random.nextInt(100) == 0)
        {
            hungry = true;
        }
label0:
        switch(kittystate)
        {
        case -1:
        case 23: // '\027'
            break;

        case 1: // '\001'
            if(random.nextInt(10) == 0)
            {
                LivingEntity entityliving = getBoogey(6D, true);
                if(entityliving != null)
                {
                    runLikeHell(entityliving);
                }
                break;
            }
            if(!hungry || random.nextInt(10) != 0)
            {
                break;
            }
            ItemEntity entityitem = getClosestItem(this, 10D, Item.COOKED_FISH.id, Item.COOKED_FISH.id);
            if(entityitem == null)
            {
                break;
            }
            float f = entityitem.method_1351(this);
            if(f > 2.0F)
            {
                getMyOwnPath(entityitem, f);
            }
            if(f < 2.0F && entityitem != null && field_1041 == 0)
            {
                entityitem.markDead();
                world.playSound(this, "mocreatures:kittyeatingf", 1.0F, 1.0F + (random.nextFloat() - random.nextFloat()) * 0.2F);
                hungry = false;
                kittystate = 2;
            }
            break;

        case 2: // '\002'
            LivingEntity entityliving1 = getBoogey(6D, false);
            if(entityliving1 != null)
            {
                runLikeHell(entityliving1);
            }
            break;

        case 3: // '\003'
            kittytimer++;
            if(kittytimer > 500)
            {
                if(random.nextInt(200) == 0)
                {
                    changeKittyState(13);
                    break;
                }
                if(random.nextInt(500) == 0)
                {
                    changeKittyState(7);
                    break;
                }
            }
            if(random.nextInt(20) != 0)
            {
                break;
            }
            EntityKittyBed entitykittybed = (EntityKittyBed)getKittyStuff(this, 18D, false);
            if(entitykittybed == null || entitykittybed.field_1594 != null || !entitykittybed.hasMilk && !entitykittybed.hasFood)
            {
                break;
            }
            float f5 = entitykittybed.method_1351(this);
            if(f5 > 2.0F)
            {
                getMyOwnPath(entitykittybed, f5);
            }
            if(f5 < 2.0F)
            {
                changeKittyState(4);
                method_1376(entitykittybed);
                isSitting = true;
            }
            break;

        case 4: // '\004'
            if(field_1595 != null)
            {
                EntityKittyBed entitykittybed1 = (EntityKittyBed)field_1595;
                if(entitykittybed1 != null && !entitykittybed1.hasMilk && !entitykittybed1.hasFood)
                {
                    health = maxhealth;
                    changeKittyState(5);
                }
            } else
            {
                health = maxhealth;
                changeKittyState(5);
            }
            if(random.nextInt(2500) == 0)
            {
                health = maxhealth;
                changeKittyState(7);
            }
            break;

        case 5: // '\005'
            kittytimer++;
            if(kittytimer > 2000 && random.nextInt(1000) == 0)
            {
                changeKittyState(13);
                break;
            }
            if(random.nextInt(20) != 0)
            {
                break;
            }
            EntityLitterBox entitylitterbox = (EntityLitterBox)getKittyStuff(this, 18D, true);
            if(entitylitterbox == null || entitylitterbox.field_1594 != null || entitylitterbox.usedlitter)
            {
                break;
            }
            float f6 = entitylitterbox.method_1351(this);
            if(f6 > 2.0F)
            {
                getMyOwnPath(entitylitterbox, f6);
            }
            if(f6 < 2.0F)
            {
                changeKittyState(6);
                method_1376(entitylitterbox);
            }
            break;

        case 6: // '\006'
            kittytimer++;
            if(kittytimer <= 300)
            {
                break;
            }
            world.playSound(this, "mocreatures:kittypoo", 1.0F, 1.0F + (random.nextFloat() - random.nextFloat()) * 0.2F);
            EntityLitterBox entitylitterbox1 = (EntityLitterBox)field_1595;
            if(entitylitterbox1 != null)
            {
                entitylitterbox1.usedlitter = true;
                entitylitterbox1.littertime = 0;
            }
            changeKittyState(7);
            break;

        case 7: // '\007'
            if(isSitting)
            {
                break;
            }
            if(random.nextInt(20) == 0)
            {
                PlayerEntity entityplayer = world.method_186(this, 12D);
                if(entityplayer != null)
                {
                    ItemStack itemstack = entityplayer.inventory.getSelectedItem();
                    if(itemstack != null && itemstack.itemId == mod_mocreatures.woolball.id)
                    {
                        changeKittyState(11);
                        break;
                    }
                }
            }
            if(field_1612 && random.nextInt(500) == 0)
            {
                changeKittyState(13);
                break;
            }
            if(random.nextInt(500) == 0 && !world.method_220())
            {
                changeKittyState(12);
                break;
            }
            if(random.nextInt(2000) == 0)
            {
                changeKittyState(3);
                break;
            }
            if(random.nextInt(4000) == 0)
            {
                changeKittyState(16);
            }
            break;

        case 8: // '\b'
            if(field_1612)
            {
                changeKittyState(13);
                break;
            }
            if(target != null && (target instanceof ItemEntity))
            {
                float f1 = method_1351(target);
                if(f1 < 1.5F)
                {
                    swingArm();
                    if(random.nextInt(10) == 0)
                    {
                        kittySmack(this, target);
                    }
                }
            }
            if(target == null || random.nextInt(1000) == 0)
            {
                changeKittyState(7);
            }
            break;

        case 9: // '\t'
            kittytimer++;
            if(random.nextInt(50) == 0)
            {
                List list = world.getEntities(this, boundingBox.expand(16D, 6D, 16D));
                int j = 0;
                do
                {
                    if(j >= list.size())
                    {
                        break;
                    }
                    Entity entity = (Entity)list.get(j);
                    if((entity instanceof EntityKitty) && (entity instanceof EntityKitty) && ((EntityKitty)entity).kittystate == 9)
                    {
                        changeKittyState(18);
                        entity = entity;
                        ((EntityKitty)entity).changeKittyState(18);
                        ((EntityKitty)entity).target = this;
                        break;
                    }
                    j++;
                } while(true);
            }
            if(kittytimer > 2000)
            {
                changeKittyState(7);
            }
            break;

        case 10: // '\n'
            if(adult)
            {
                changeKittyState(7);
                break;
            }
            if(random.nextInt(50) == 0)
            {
                List list1 = world.getEntities(this, boundingBox.expand(16D, 6D, 16D));
                for(int k = 0; k < list1.size(); k++)
                {
                    Entity entity1 = (Entity)list1.get(k);
                    if(!(entity1 instanceof EntityKitty) || ((EntityKitty)entity1).kittystate != 21)
                    {
                        continue;
                    }
                    float f9 = method_1351(entity1);
                    if(f9 > 12F)
                    {
                        target = entity1;
                    }
                }

            }
            if(target == null && random.nextInt(100) == 0)
            {
                int i = random.nextInt(10);
                if(i < 7)
                {
                    target = getClosestItem(this, 10D, -1, -1);
                } else
                {
                    target = world.method_186(this, 18D);
                }
            }
            if(target != null && random.nextInt(400) == 0)
            {
                target = null;
            }
            if(target != null && (target instanceof ItemEntity))
            {
                float f2 = method_1351(target);
                if(f2 < 1.5F)
                {
                    swingArm();
                    if(random.nextInt(10) == 0)
                    {
                        kittySmack(this, target);
                    }
                }
            }
            if(target != null && (target instanceof EntityKitty) && random.nextInt(20) == 0)
            {
                float f3 = method_1351(target);
                if(f3 < 2.0F)
                {
                    swingArm();
                    method_635(null);
                }
            }
            if(target == null || !(target instanceof PlayerEntity))
            {
                break;
            }
            float f4 = method_1351(target);
            if(f4 < 2.0F && random.nextInt(20) == 0)
            {
                swingArm();
            }
            break;

        case 11: // '\013'
            PlayerEntity entityplayer1 = world.method_186(this, 18D);
            if(entityplayer1 == null || random.nextInt(10) != 0)
            {
                break;
            }
            ItemStack itemstack1 = entityplayer1.inventory.getSelectedItem();
            if(itemstack1 == null || itemstack1 != null && itemstack1.itemId != mod_mocreatures.woolball.id)
            {
                changeKittyState(7);
                break;
            }
            float f8 = entityplayer1.method_1351(this);
            if(f8 > 5F)
            {
                method_429(entityplayer1, f8);
            }
            break;

        case 12: // '\f'
            kittytimer++;
            if(world.method_220() || kittytimer > 500 && random.nextInt(500) == 0)
            {
                changeKittyState(7);
                break;
            }
            isSitting = true;
            if(random.nextInt(80) == 0 || !field_1623)
            {
                super.method_937();
            }
            break;

        case 13: // '\r'
            hungry = false;
            target = world.method_186(this, 18D);
            if(target != null)
            {
                float f7 = method_1351(target);
                if(f7 < 1.5F)
                {
                    swingArm();
                    if(random.nextInt(20) == 0)
                    {
                        madtimer--;
                        target.damage(this, 1);
                        if(madtimer < 1)
                        {
                            changeKittyState(7);
                            madtimer = random.nextInt(5);
                        }
                    }
                }
                if(random.nextInt(1000) == 0)
                {
                    changeKittyState(7);
                }
            } else
            {
                changeKittyState(7);
            }
            break;

        case 14: // '\016'
            if(field_1623)
            {
                changeKittyState(13);
                break;
            }
            if(random.nextInt(50) == 0)
            {
                swingArm();
            }
            if(field_1595 == null)
            {
                break;
            }
            yaw = field_1595.yaw + 90F;
            PlayerEntity entityplayer2 = (PlayerEntity)field_1595;
            if(entityplayer2 == null)
            {
                break;
            }
            ItemStack itemstack2 = entityplayer2.inventory.getSelectedItem();
            if(itemstack2 != null && itemstack2.itemId != mod_mocreatures.rope.id)
            {
                changeKittyState(13);
            }
            break;

        case 15: // '\017'
            if(field_1623)
            {
                changeKittyState(7);
            }
            if(field_1595 != null)
            {
                yaw = field_1595.yaw + 90F;
            }
            break;

        case 16: // '\020'
            kittytimer++;
            if(kittytimer > 500 && !onTree)
            {
                changeKittyState(7);
            }
            if(!onTree)
            {
                if(!foundTree && random.nextInt(50) == 0)
                {
                    int ai[] = ReturnNearestMaterialCoord(this, Material.WOOD, Double.valueOf(18D));
                    if(ai[0] != -1)
                    {
                        int i1 = 0;
                        do
                        {
                            if(i1 >= 20)
                            {
                                break;
                            }
                            int k1 = world.getBlockId(ai[0], ai[1] + i1, ai[2]);
                            if(k1 != 0 && Block.BLOCKS[k1].material != Material.WOOD && k1 != 0 && Block.BLOCKS[k1].material == Material.LEAVES)
                            {
                                foundTree = true;
                                treeCoord[0] = ai[0];
                                treeCoord[1] = ai[1];
                                treeCoord[2] = ai[2];
                                break;
                            }
                            i1++;
                        } while(true);
                    }
                }
                if(!foundTree || random.nextInt(10) != 0)
                {
                    break;
                }
                class_61 pathentity = world.method_189(this, treeCoord[0], treeCoord[1], treeCoord[2], 24F);
                if(pathentity != null)
                {
                    method_635(pathentity);
                }
                Double double1 = Double.valueOf(method_1347(treeCoord[0], treeCoord[1], treeCoord[2]));
                if(double1.doubleValue() < 7D)
                {
                    onTree = true;
                }
                break;
            }
            if(!onTree)
            {
                break;
            }
            int l = treeCoord[0];
            int j1 = treeCoord[1];
            int l1 = treeCoord[2];
            faceTreeTop(l, j1, l1, 30F);
            if(j1 - MathHelper.floor(y) > 2)
            {
                velocityY += 0.029999999999999999D;
            }
            boolean flag = false;
            boolean flag1 = false;
            if(x < (double)l)
            {
                int j2 = l - MathHelper.floor(x);
                velocityX += 0.01D;
            } else
            {
                int k2 = MathHelper.floor(x) - l;
                velocityX -= 0.01D;
            }
            if(z < (double)l1)
            {
                int j3 = l1 - MathHelper.floor(z);
                velocityZ += 0.01D;
            } else
            {
                int k3 = MathHelper.floor(x) - l1;
                velocityZ -= 0.01D;
            }
            if(field_1623 || !field_1624 || !field_1625)
            {
                break;
            }
            int i4 = 0;
            do
            {
                if(i4 >= 30)
                {
                    break label0;
                }
                int j4 = world.getBlockId(treeCoord[0], treeCoord[1] + i4, treeCoord[2]);
                if(j4 == 0)
                {
                    method_1341(treeCoord[0], treeCoord[1] + i4, treeCoord[2], yaw, pitch);
                    changeKittyState(17);
                    treeCoord[0] = -1;
                    treeCoord[1] = -1;
                    treeCoord[2] = -1;
                    break label0;
                }
                i4++;
            } while(true);

        case 17: // '\021'
            PlayerEntity entityplayer3 = world.method_186(this, 2D);
            if(entityplayer3 != null)
            {
                changeKittyState(7);
            }
            break;

        case 18: // '\022'
            if(target == null || !(target instanceof EntityKitty))
            {
                changeKittyState(9);
                break;
            }
            EntityKitty entitykitty = (EntityKitty)target;
            if(entitykitty != null && entitykitty.kittystate == 18)
            {
                if(random.nextInt(50) == 0)
                {
                    swingArm();
                }
                float f10 = method_1351(entitykitty);
                if(f10 < 5F)
                {
                    kittytimer++;
                }
                if(kittytimer > 500 && random.nextInt(50) == 0)
                {
                    ((EntityKitty)target).changeKittyState(7);
                    changeKittyState(19);
                }
            } else
            {
                changeKittyState(9);
            }
            break;

        case 19: // '\023'
            if(random.nextInt(20) != 0)
            {
                break;
            }
            EntityKittyBed entitykittybed2 = (EntityKittyBed)getKittyStuff(this, 18D, false);
            if(entitykittybed2 == null || entitykittybed2.field_1594 != null)
            {
                break;
            }
            float f11 = entitykittybed2.method_1351(this);
            if(f11 > 2.0F)
            {
                getMyOwnPath(entitykittybed2, f11);
            }
            if(f11 < 2.0F)
            {
                changeKittyState(20);
                method_1376(entitykittybed2);
            }
            break;

        case 20: // '\024'
            if(field_1595 == null)
            {
                changeKittyState(19);
                break;
            }
            yaw = 180F;
            kittytimer++;
            if(kittytimer <= 1000)
            {
                break;
            }
            int i2 = random.nextInt(3) + 1;
            for(int l2 = 0; l2 < i2; l2++)
            {
                EntityKitty entitykitty1 = new EntityKitty(world);
                entitykitty1.method_1340(x, y, z);
                world.method_210(entitykitty1);
                world.playSound(this, "mob.chickenplop", 1.0F, (random.nextFloat() - random.nextFloat()) * 0.2F + 1.0F);
                entitykitty1.adult = false;
                entitykitty1.changeKittyState(10);
                damage(null, 1);
            }

            changeKittyState(21);
            break;

        case 21: // '\025'
            kittytimer++;
            if(kittytimer > 2000)
            {
                List list2 = world.getEntities(this, boundingBox.expand(24D, 8D, 24D));
                int i3 = 0;
                for(int l3 = 0; l3 < list2.size(); l3++)
                {
                    Entity entity2 = (Entity)list2.get(l3);
                    if((entity2 instanceof EntityKitty) && ((EntityKitty)entity2).kittystate == 10)
                    {
                        i3++;
                    }
                }

                if(i3 < 1)
                {
                    changeKittyState(7);
                    break;
                }
                kittytimer = 1000;
            }
            if(target != null && (target instanceof PlayerEntity) && random.nextInt(300) == 0)
            {
                target = null;
            }
            break;

        case 0: // '\0'
        case 22: // '\026'
        default:
            changeKittyState(7);
            break;
        }
    }

    private void changeKittyState(int i)
    {
        kittystate = i;
        method_1376(null);
        isSitting = false;
        kittytimer = 0;
        onTree = false;
        foundTree = false;
        target = null;
    }

    public boolean method_932()
    {
        if(kittystate == 16)
        {
            return field_1624 && onTree;
        } else
        {
            return super.method_932();
        }
    }

    public void faceTreeTop(int i, int j, int k, float f)
    {
        double d = (double)i - x;
        double d1 = (double)k - z;
        double d2 = (double)j - y;
        double d3 = MathHelper.sqrt(d * d + d1 * d1);
        float f1 = (float)((Math.atan2(d1, d) * 180D) / 3.1415927410125728D) - 90F;
        float f2 = (float)((Math.atan2(d2, d3) * 180D) / 3.1415927410125728D);
        pitch = -b(pitch, f2, f);
        yaw = b(yaw, f1, f);
    }

    private float b(float f, float f1, float f2)
    {
        float f3 = f1;
        for(f3 = f1 - f; f3 < -180F; f3 += 360F) { }
        for(; f3 >= 180F; f3 -= 360F) { }
        if(f3 > f2)
        {
            f3 = f2;
        }
        if(f3 < -f2)
        {
            f3 = -f2;
        }
        return f + f3;
    }

    public int[] ReturnNearestMaterialCoord(Entity entity, Material material, Double double1)
    {
        Box axisalignedbb = entity.boundingBox.expand(double1.doubleValue(), double1.doubleValue(), double1.doubleValue());
        int i = MathHelper.floor(axisalignedbb.minX);
        int j = MathHelper.floor(axisalignedbb.maxX + 1.0D);
        int k = MathHelper.floor(axisalignedbb.minY);
        int l = MathHelper.floor(axisalignedbb.maxY + 1.0D);
        int i1 = MathHelper.floor(axisalignedbb.minZ);
        int j1 = MathHelper.floor(axisalignedbb.maxZ + 1.0D);
        for(int k1 = i; k1 < j; k1++)
        {
            for(int l1 = k; l1 < l; l1++)
            {
                for(int i2 = i1; i2 < j1; i2++)
                {
                    int j2 = world.getBlockId(k1, l1, i2);
                    if(j2 != 0 && Block.BLOCKS[j2].material == material)
                    {
                        return (new int[] {
                            k1, l1, i2
                        });
                    }
                }

            }

        }

        return (new int[] {
            -1, 0, 0
        });
    }

    protected Entity method_638()
    {
        if(world.field_213 > 0 && kittystate != 8 && kittystate != 10 && kittystate != 15 && kittystate != 18 && kittystate != 19 && !method_640() && hungry)
        {
            LivingEntity entityliving = getClosestTarget(this, 10D);
            return entityliving;
        } else
        {
            return null;
        }
    }

    public LivingEntity getClosestTarget(Entity entity, double d)
    {
        double d1 = -1D;
        LivingEntity entityliving = null;
        List list = world.getEntities(this, boundingBox.expand(d, d, d));
        for(int i = 0; i < list.size(); i++)
        {
            Entity entity1 = (Entity)list.get(i);
            if(!(entity1 instanceof LivingEntity) || (entity1 instanceof EntityKitty) || (entity1 instanceof PlayerEntity) || (entity1 instanceof MonsterEntity) || (entity1 instanceof EntityKittyBed) || (entity1 instanceof EntityLitterBox) || (entity1 instanceof EntityHorse) && !mocr.mocreaturesGlass.huntercreatures.attackhorses || (entity1 instanceof WolfEntity) && !mocr.mocreaturesGlass.huntercreatures.attackwolves || (entity1 instanceof EntityBigCat) && !mocr.mocreaturesGlass.huntercreatures.attackbigcat || (entity1 instanceof EntityBigCat) && ((EntityBigCat) entity1).getTamed() && kittystate > 2 || (entity1 instanceof EntityDolphin) && ((EntityDolphin) entity1).getTamed() && kittystate > 2 || (entity1 instanceof EntityShark) && ((EntityShark)entity1).tamed && kittystate > 2 || (double)entity1.spacingXZ > 0.5D && (double)entity1.spacingY > 0.5D)
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

    public ItemEntity getClosestItem(Entity entity, double d, int i, int j)
    {
        double d1 = -1D;
        ItemEntity entityitem = null;
        List list = world.getEntities(this, boundingBox.expand(d, d, d));
        for(int k = 0; k < list.size(); k++)
        {
            Entity entity1 = (Entity)list.get(k);
            if(!(entity1 instanceof ItemEntity))
            {
                continue;
            }
            ItemEntity entityitem1 = (ItemEntity)entity1;
            if(i != -1 && j != -1 && entityitem1.stack.itemId != i && j != 0 && entityitem1.stack.itemId != j)
            {
                continue;
            }
            double d2 = entityitem1.method_1347(entity.x, entity.y, entity.z);
            if((d < 0.0D || d2 < d * d) && (d1 == -1D || d2 < d1))
            {
                d1 = d2;
                entityitem = entityitem1;
            }
        }

        return entityitem;
    }

    private void getMyOwnPath(Entity entity, float f)
    {
        class_61 pathentity = world.method_192(this, entity, 16F);
        if(pathentity != null)
        {
            method_635(pathentity);
        }
    }

    public boolean damage(Entity entity, int i)
    {
        if(super.damage(entity, i))
        {
            if(entity != this)
            {
                if(kittystate == 10)
                {
                    List list = world.getEntities(this, boundingBox.expand(16D, 6D, 16D));
                    for(int j = 0; j < list.size(); j++)
                    {
                        Entity entity1 = (Entity)list.get(j);
                        if((entity1 instanceof EntityKitty) && ((EntityKitty)entity1).kittystate == 21)
                        {
                            ((EntityKitty)entity1).target = entity;
                            return true;
                        }
                    }

                    return true;
                }
                if(entity instanceof PlayerEntity)
                {
                    if(kittystate < 2)
                    {
                        entity = entity;
                        kittystate = -1;
                    }
                    if(kittystate == 19 || kittystate == 20 || kittystate == 21)
                    {
                        entity = entity;
                        isSitting = false;
                        return true;
                    }
                    if(kittystate > 1 && kittystate != 10 && kittystate != 19 && kittystate != 20 && kittystate != 21)
                    {
                        kittystate = 13;
                        isSitting = false;
                    }
                    return true;
                }
                entity = entity;
            }
            return true;
        } else
        {
            return false;
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

    public LivingEntity getKittyStuff(Entity entity, double d, boolean flag)
    {
        double d1 = -1D;
        Object obj = null;
        List list = world.getEntities(entity, boundingBox.expand(d, d, d));
        for(int i = 0; i < list.size(); i++)
        {
            Entity entity1 = (Entity)list.get(i);
            if(flag)
            {
                if(!(entity1 instanceof EntityLitterBox))
                {
                    continue;
                }
                EntityLitterBox entitylitterbox = (EntityLitterBox)entity1;
                if(entitylitterbox.usedlitter)
                {
                    continue;
                }
                double d2 = entity1.method_1347(entity.x, entity.y, entity.z);
                if((d < 0.0D || d2 < d * d) && (d1 == -1D || d2 < d1) && entitylitterbox.method_928(entity))
                {
                    d1 = d2;
                    obj = entitylitterbox;
                }
                continue;
            }
            if(!(entity1 instanceof EntityKittyBed))
            {
                continue;
            }
            EntityKittyBed entitykittybed = (EntityKittyBed)entity1;
            double d3 = entity1.method_1347(entity.x, entity.y, entity.z);
            if((d < 0.0D || d3 < d * d) && (d1 == -1D || d3 < d1) && entitykittybed.method_928(entity))
            {
                d1 = d3;
                obj = entitykittybed;
            }
        }

        return ((LivingEntity) (obj));
    }

    public void tick()
    {
        super.tick();
        if(isSwinging)
        {
            field_1035 += 0.2F;
            if(field_1035 > 2.0F)
            {
                isSwinging = false;
                field_1035 = 0.0F;
            }
        }
    }

    protected boolean method_640()
    {
        return isSitting || kittystate == 6 || kittystate == 16 && onTree || kittystate == 12 || kittystate == 17 || kittystate == 14 || kittystate == 20 || kittystate == 23;
    }

    public void swingArm()
    {
        if(!isSwinging)
        {
            isSwinging = true;
            field_1035 = 0.0F;
        }
    }

    protected void method_637(Entity entity, float f)
    {
        if(f > 2.0F && f < 6F && random.nextInt(30) == 0 && field_1623)
        {
            double d = entity.x - x;
            double d1 = entity.z - z;
            float f1 = MathHelper.sqrt(d * d + d1 * d1);
            velocityX = (d / (double)f1) * 0.5D * 0.80000000000000004D + velocityX * 0.20000000000000001D;
            velocityZ = (d1 / (double)f1) * 0.5D * 0.80000000000000004D + velocityZ * 0.20000000000000001D;
            velocityY = 0.40000000000000002D;
        }
        if((double)f < 2D && entity.boundingBox.maxY > boundingBox.minY && entity.boundingBox.minY < boundingBox.maxY)
        {
            field_1042 = 20;
            if(kittystate != 18 && kittystate != 10)
            {
                swingArm();
            }
            if(kittystate == 13 && (entity instanceof PlayerEntity) || kittystate == 8 && (entity instanceof ItemEntity) || kittystate == 18 && (entity instanceof EntityKitty) || kittystate == 10)
            {
                return;
            }
            entity.damage(this, 1);
        }
    }

    public LivingEntity getBoogey(double d, boolean flag)
    {
        double d1 = -1D;
        LivingEntity entityliving = null;
        List list = world.getEntities(this, boundingBox.expand(d, 4D, d));
        for(int i = 0; i < list.size(); i++)
        {
            Entity entity = (Entity)list.get(i);
            if((entity instanceof LivingEntity) && !(entity instanceof EntityDeer) && !(entity instanceof EntityHorse) && ((double)entity.spacingXZ >= 0.5D || (double)entity.spacingY >= 0.5D) && (flag || !(entity instanceof PlayerEntity)))
            {
                entityliving = (LivingEntity)entity;
            }
        }

        return entityliving;
    }

    public void runLikeHell(Entity entity)
    {
        double d = x - entity.x;
        double d1 = z - entity.z;
        double d2 = Math.atan2(d, d1);
        d2 += (double)(random.nextFloat() - random.nextFloat()) * 0.75D;
        double d3 = x + Math.sin(d2) * 8D;
        double d4 = z + Math.cos(d2) * 8D;
        int i = MathHelper.floor(d3);
        int j = MathHelper.floor(boundingBox.minY);
        int k = MathHelper.floor(d4);
        int l = 0;
        do
        {
            if(l >= 16)
            {
                break;
            }
            int i1 = (i + random.nextInt(4)) - random.nextInt(4);
            int j1 = (j + random.nextInt(3)) - random.nextInt(3);
            int k1 = (k + random.nextInt(4)) - random.nextInt(4);
            if(j1 > 4 && (world.getBlockId(i1, j1, k1) == 0 || world.getBlockId(i1, j1, k1) == Block.SNOW.id) && world.getBlockId(i1, j1 - 1, k1) != 0)
            {
                class_61 pathentity = world.method_189(this, i1, j1, k1, 16F);
                method_635(pathentity);
                break;
            }
            l++;
        } while(true);
    }

    public void writeNbt(NbtCompound nbttagcompound)
    {
        super.writeNbt(nbttagcompound);
        nbttagcompound.putInt("TypeInt", typeint);
        nbttagcompound.putBoolean("Adult", adult);
        nbttagcompound.putBoolean("Sitting", isSitting);
        nbttagcompound.putFloat("Edad", edad);
        nbttagcompound.putInt("KittyState", kittystate);
        nbttagcompound.putString("Name", name);
        nbttagcompound.putBoolean("DisplayName", displayname);
    }

    public void readNbt(NbtCompound nbttagcompound)
    {
        super.readNbt(nbttagcompound);
        adult = nbttagcompound.getBoolean("Adult");
        typeint = nbttagcompound.getInt("TypeInt");
        edad = nbttagcompound.getFloat("Edad");
        isSitting = nbttagcompound.getBoolean("Sitting");
        kittystate = nbttagcompound.getInt("KittyState");
        name = nbttagcompound.getString("Name");
        displayname = nbttagcompound.getBoolean("DisplayName");
    }

    protected void method_1389(float f)
    {
    }

    protected String method_911()
    {
        if(kittystate == 4)
        {
            if(field_1595 != null)
            {
                EntityKittyBed entitykittybed = (EntityKittyBed)field_1595;
                if(entitykittybed != null && !entitykittybed.hasMilk)
                {
                    return "mocreatures:kittyeatingm";
                }
                if(entitykittybed != null && !entitykittybed.hasFood)
                {
                    return "mocreatures:kittyeatingf";
                }
            }
            return null;
        }
        if(kittystate == 6)
        {
            return "mocreatures:kittylitter";
        }
        if(kittystate == 3)
        {
            return "mocreatures:kittyfood";
        }
        if(kittystate == 10)
        {
            return "mocreatures:kittengrunt";
        }
        if(kittystate == 13)
        {
            return "mocreatures:kittyupset";
        }
        if(kittystate == 17)
        {
            return "mocreatures:kittytrapped";
        }
        if(kittystate == 18 || kittystate == 12)
        {
            return "mocreatures:kittypurr";
        } else
        {
            return "mocreatures:kittygrunt";
        }
    }

    protected String method_912()
    {
        if(kittystate == 10)
        {
            return "mocreatures:kittenhurt";
        } else
        {
            return "mocreatures:kittyhurt";
        }
    }

    protected String method_913()
    {
        if(kittystate == 10)
        {
            return "mocreatures:kittendying";
        } else
        {
            return "mocreatures:kittydying";
        }
    }

    protected int method_914()
    {
        return 0;
    }

    public int method_916()
    {
        return 2;
    }

    protected boolean method_940()
    {
        return kittystate < 3;
    }

    public void markDead()
    {
        if(kittystate > 2 && health > 0)
        {
            return;
        } else
        {
            super.markDead();
            return;
        }
    }

    public boolean canSpawn()
    {
        return mocr.mocreaturesGlass.animals.kittyfreq > 0 && super.canSpawn();
    }

    public void kittySmack(Entity entity, Entity entity1)
    {
        double d = entity.x - entity1.x;
        double d1 = entity.z - entity1.z;
        for(d1 = entity.z - entity1.z; d * d + d1 * d1 < 0.0001D; d1 = (Math.random() - Math.random()) * 0.01D)
        {
            d = (Math.random() - Math.random()) * 0.01D;
        }

        float f = MathHelper.sqrt(d * d + d1 * d1);
        float f1 = 0.25F;
        if(kittystate == 10)
        {
            f1 = 0.1F;
        }
        entity1.velocityX /= 2D;
        entity1.velocityY /= 2D;
        entity1.velocityZ /= 2D;
        entity1.velocityX -= (d / (double)f) * (double)f1;
        entity1.velocityY += 0.300000005960465D;
        entity1.velocityZ -= (d1 / (double)f) * (double)f1;
        if(entity1.velocityY > 0.300000005960465D)
        {
            entity1.velocityY = 0.300000005960465D;
        }
    }

    public String getEmoticon()
    {
        switch(kittystate)
        {
        case -1:
            return "/assets/mocreatures/stationapi/textures/mob/emoticon2.png";

        case 3: // '\003'
            return "/assets/mocreatures/stationapi/textures/mob/emoticon3.png";

        case 4: // '\004'
            return "/assets/mocreatures/stationapi/textures/mob/emoticon4.png";

        case 5: // '\005'
            return "/assets/mocreatures/stationapi/textures/mob/emoticon5.png";

        case 7: // '\007'
            return "/assets/mocreatures/stationapi/textures/mob/emoticon7.png";

        case 8: // '\b'
            return "/assets/mocreatures/stationapi/textures/mob/emoticon8.png";

        case 9: // '\t'
            return "/assets/mocreatures/stationapi/textures/mob/emoticon9.png";

        case 10: // '\n'
            return "/assets/mocreatures/stationapi/textures/mob/emoticon10.png";

        case 11: // '\013'
            return "/assets/mocreatures/stationapi/textures/mob/emoticon11.png";

        case 12: // '\f'
            return "/assets/mocreatures/stationapi/textures/mob/emoticon12.png";

        case 13: // '\r'
            return "/assets/mocreatures/stationapi/textures/mob/emoticon13.png";

        case 16: // '\020'
            return "/assets/mocreatures/stationapi/textures/mob/emoticon16.png";

        case 17: // '\021'
            return "/assets/mocreatures/stationapi/textures/mob/emoticon17.png";

        case 18: // '\022'
            return "/assets/mocreatures/stationapi/textures/mob/emoticon9.png";

        case 19: // '\023'
            return "/assets/mocreatures/stationapi/textures/mob/emoticon19.png";

        case 20: // '\024'
            return "/assets/mocreatures/stationapi/textures/mob/emoticon19.png";

        case 21: // '\025'
            return "/assets/mocreatures/stationapi/textures/mob/emoticon10.png";

        case 0: // '\0'
        case 1: // '\001'
        case 2: // '\002'
        case 6: // '\006'
        case 14: // '\016'
        case 15: // '\017'
        default:
            return "/mob/emoticon1.png";
        }
    }

    public static void setName(EntityKitty entitykitty)
    {
        entitykitty.displayname = true;
        mc.setScreen(new MoCGUI(entitykitty, entitykitty.name));
    }

    @SuppressWarnings("deprecation")
    public static Minecraft mc = Minecraft.class.cast(FabricLoader.getInstance().getGameInstance());
    mod_mocreatures mocr = new mod_mocreatures();
    public boolean isSitting;
    public boolean isSwinging;
    public boolean typechosen;
    public boolean adult;
    public int typeint;
    public float edad;
    public boolean inBed;
    public boolean sleepy;
    public int kittystate;
    public boolean hungry;
    private int kittytimer;
    private int madtimer;
    public int maxhealth;
    public String name;
    public boolean displayname;
    public boolean onTree;
    private boolean foundTree;
    public boolean displayemo;
    private int treeCoord[] = {
        -1, -1, -1
    };
    private int displaycount;

    protected void initDataTracker()
    {
        dataTracker.method_1502(16, (byte) 0); //Type
    }

    @Override
    public Identifier getHandlerIdentifier() {
        return Identifier.of(mod_mocreatures.MOD_ID, "Kitty");
    }

    @Override
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
}
