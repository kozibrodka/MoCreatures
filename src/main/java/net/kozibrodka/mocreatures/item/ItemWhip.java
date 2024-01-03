// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) braces deadcode 

package net.kozibrodka.mocreatures.item;

import net.kozibrodka.mocreatures.entity.EntityBigCat;
import net.kozibrodka.mocreatures.entity.EntityBunny;
import net.kozibrodka.mocreatures.entity.EntityHorse;
import net.kozibrodka.mocreatures.entity.EntityKitty;
import net.kozibrodka.mocreatures.events.mod_mocreatures;
import net.minecraft.block.Block;
import net.minecraft.block.entity.SignBlockEntity;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.world.World;
import net.modificationstation.stationapi.api.util.Identifier;
import net.modificationstation.stationapi.api.template.item.TemplateItem;

import java.util.List;

public class ItemWhip extends TemplateItem
{

    public ItemWhip(Identifier i)
    {
        super(i);
        maxCount = 1;
        setMaxDamage(24);
    }

    public ItemStack onItemRightClick2(ItemStack itemstack, World world, PlayerEntity entityplayer)
    {
        return itemstack;
    }

    public boolean useOnBlock(ItemStack itemstack, PlayerEntity entityplayer, World world, int i, int j, int k, int l)
    {
        int i1 = 0;
        int j1 = world.getBlockId(i, j, k);
        int k1 = world.getBlockId(i, j + 1, k);
        if(l != 0 && k1 == 0 && j1 != 0 && j1 != Block.SIGN.id)
        {
            whipFX(world, i, j, k);
            world.playSound(entityplayer, "mocreatures:whip", 0.5F, 0.4F / (random.nextFloat() * 0.4F + 0.8F));
            itemstack.damage(1, entityplayer);
            List list = world.getEntities(entityplayer, entityplayer.boundingBox.expand(12D, 12D, 12D));
            for(int l1 = 0; l1 < list.size(); l1++)
            {
                Entity entity = (Entity)list.get(l1);
                if(entity instanceof EntityBigCat)
                {
                    EntityBigCat entitybigcat = (EntityBigCat)entity;
                    if(entitybigcat.tamed && entityplayer.name.equals(entitybigcat.tigerOwner))
                    {
                        entitybigcat.sitting = !entitybigcat.sitting;
                        i1++;
                    } else
                    if(world.field_213 > 0 && entitybigcat.adult)
                    {
                        entitybigcat.ustawCel(entityplayer);
                    }
                }
                if(entity instanceof EntityHorse)
                {
                    EntityHorse entityhorse = (EntityHorse)entity;
                    if((entityhorse.tamed && entityplayer.name.equals(entityhorse.horseOwner) || (entityhorse.tamed && entityhorse.isHorsePublic)))
                    {
                        entityhorse.eatinghaystack = !entityhorse.eatinghaystack;
                    }
                }
                if(!(entity instanceof EntityKitty))
                {
                    continue;
                }
                EntityKitty entitykitty = (EntityKitty)entity;
                if(entitykitty.kittystate > 2 && entitykitty.whipeable())
                {
                    entitykitty.isSitting = !entitykitty.isSitting;
                }
            }

            if(i1 > 6)
            {
                entityplayer.incrementStat(mod_mocreatures.Indiana);
            }
            return true;
        }
        if(l != 0 && (k1 == Block.SIGN.id || j1 == Block.SIGN.id) && j1 != 0)
        {
            SignBlockEntity tileentitysign = (SignBlockEntity)world.method_1777(i, j + 1, k);
            if(tileentitysign == null)
            {
                tileentitysign = (SignBlockEntity)world.method_1777(i, j, k);
            }
            if(tileentitysign != null)
            {
                int i2 = 0;
                List list1 = world.method_291();
                for(int j2 = 0; j2 < list1.size(); j2++)
                {
                    Entity entity1 = (Entity)list1.get(j2);
                    if(entity1 instanceof EntityBunny)
                    {
                        EntityBunny entitybunny = (EntityBunny)entity1;
                        i2++;
                        entitybunny.markDead();
                    }
                }

                String s = String.valueOf(i2);
                tileentitysign.texts[0] = "";
                tileentitysign.texts[1] = "R.I.P.";
                tileentitysign.texts[2] = (new StringBuilder()).append(s).append(" Bunnies").toString();
                tileentitysign.texts[3] = "";
                if(i2 > 69)
                {
                    entityplayer.incrementStat(mod_mocreatures.BunnyKilla);
                }
                whipFX(world, i, j, k);
                world.playSound(entityplayer, "mocreatures:whip", 0.5F, 0.4F / (random.nextFloat() * 0.4F + 0.8F));
                itemstack.damage(1, entityplayer);
                return true;
            }
        }
        return false;
    }

    public void whipFX(World world, int i, int j, int k)
    {
        double d = (float)i + 0.5F;
        double d1 = (float)j + 1.0F;
        double d2 = (float)k + 0.5F;
        double d3 = 0.2199999988079071D;
        double d4 = 0.27000001072883606D;
        world.addParticle("smoke", d - d4, d1 + d3, d2, 0.0D, 0.0D, 0.0D);
        world.addParticle("flame", d - d4, d1 + d3, d2, 0.0D, 0.0D, 0.0D);
        world.addParticle("smoke", d + d4, d1 + d3, d2, 0.0D, 0.0D, 0.0D);
        world.addParticle("flame", d + d4, d1 + d3, d2, 0.0D, 0.0D, 0.0D);
        world.addParticle("smoke", d, d1 + d3, d2 - d4, 0.0D, 0.0D, 0.0D);
        world.addParticle("flame", d, d1 + d3, d2 - d4, 0.0D, 0.0D, 0.0D);
        world.addParticle("smoke", d, d1 + d3, d2 + d4, 0.0D, 0.0D, 0.0D);
        world.addParticle("flame", d, d1 + d3, d2 + d4, 0.0D, 0.0D, 0.0D);
        world.addParticle("smoke", d, d1, d2, 0.0D, 0.0D, 0.0D);
        world.addParticle("flame", d, d1, d2, 0.0D, 0.0D, 0.0D);
    }

    public boolean isFull3D()
    {
        return true;
    }
}
