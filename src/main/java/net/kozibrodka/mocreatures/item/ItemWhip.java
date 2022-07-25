// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) braces deadcode 

package net.kozibrodka.mocreatures.item;

import net.kozibrodka.mocreatures.entity.EntityBigCat;
import net.kozibrodka.mocreatures.entity.EntityBunny;
import net.kozibrodka.mocreatures.entity.EntityHorse;
import net.kozibrodka.mocreatures.entity.EntityKitty;
import net.kozibrodka.mocreatures.events.mod_mocreatures;
import net.minecraft.block.BlockBase;
import net.minecraft.entity.EntityBase;
import net.minecraft.entity.player.PlayerBase;
import net.minecraft.item.ItemInstance;
import net.minecraft.level.Level;
import net.minecraft.tileentity.TileEntitySign;
import net.modificationstation.stationapi.api.registry.Identifier;
import net.modificationstation.stationapi.api.template.item.TemplateItemBase;

import java.util.List;

public class ItemWhip extends TemplateItemBase
{

    public ItemWhip(Identifier i)
    {
        super(i);
        maxStackSize = 1;
        setDurability(24);
    }

    public ItemInstance onItemRightClick2(ItemInstance itemstack, Level world, PlayerBase entityplayer)
    {
        return itemstack;
    }

    public boolean useOnTile(ItemInstance itemstack, PlayerBase entityplayer, Level world, int i, int j, int k, int l)
    {
        int i1 = 0;
        int j1 = world.getTileId(i, j, k);
        int k1 = world.getTileId(i, j + 1, k);
        if(l != 0 && k1 == 0 && j1 != 0 && j1 != BlockBase.STANDING_SIGN.id)
        {
            whipFX(world, i, j, k);
            world.playSound(entityplayer, "mocreatures:whip", 0.5F, 0.4F / (rand.nextFloat() * 0.4F + 0.8F));
            itemstack.applyDamage(1, entityplayer);
            List list = world.getEntities(entityplayer, entityplayer.boundingBox.expand(12D, 12D, 12D));
            for(int l1 = 0; l1 < list.size(); l1++)
            {
                EntityBase entity = (EntityBase)list.get(l1);
                if(entity instanceof EntityBigCat)
                {
                    EntityBigCat entitybigcat = (EntityBigCat)entity;
                    if(entitybigcat.tamed && entityplayer.name.equals(entitybigcat.tigerOwner))
                    {
                        entitybigcat.sitting = !entitybigcat.sitting;
                        i1++;
                    } else
                    if(world.difficulty > 0 && entitybigcat.adult)
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
        if(l != 0 && (k1 == BlockBase.STANDING_SIGN.id || j1 == BlockBase.STANDING_SIGN.id) && j1 != 0)
        {
            TileEntitySign tileentitysign = (TileEntitySign)world.getTileEntity(i, j + 1, k);
            if(tileentitysign == null)
            {
                tileentitysign = (TileEntitySign)world.getTileEntity(i, j, k);
            }
            if(tileentitysign != null)
            {
                int i2 = 0;
                List list1 = world.getEntities();
                for(int j2 = 0; j2 < list1.size(); j2++)
                {
                    EntityBase entity1 = (EntityBase)list1.get(j2);
                    if(entity1 instanceof EntityBunny)
                    {
                        EntityBunny entitybunny = (EntityBunny)entity1;
                        i2++;
                        entitybunny.remove();
                    }
                }

                String s = String.valueOf(i2);
                tileentitysign.lines[0] = "";
                tileentitysign.lines[1] = "R.I.P.";
                tileentitysign.lines[2] = (new StringBuilder()).append(s).append(" Bunnies").toString();
                tileentitysign.lines[3] = "";
                if(i2 > 69)
                {
                    entityplayer.incrementStat(mod_mocreatures.BunnyKilla);
                }
                whipFX(world, i, j, k);
                world.playSound(entityplayer, "mocreatures:whip", 0.5F, 0.4F / (rand.nextFloat() * 0.4F + 0.8F));
                itemstack.applyDamage(1, entityplayer);
                return true;
            }
        }
        return false;
    }

    public void whipFX(Level world, int i, int j, int k)
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
