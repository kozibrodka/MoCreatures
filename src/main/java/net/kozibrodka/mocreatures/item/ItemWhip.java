

package net.kozibrodka.mocreatures.item;

import net.fabricmc.api.EnvType;
import net.fabricmc.loader.FabricLoader;
import net.kozibrodka.mocreatures.entity.*;
import net.kozibrodka.mocreatures.events.mod_mocreatures;
import net.kozibrodka.mocreatures.mixin.AchievementPageAccessor;
import net.minecraft.achievement.Achievement;
import net.minecraft.block.Block;
import net.minecraft.block.entity.SignBlockEntity;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.util.IntHashMap;
import net.minecraft.world.ServerWorld;
import net.minecraft.world.World;
import net.modificationstation.stationapi.api.client.gui.screen.achievement.AchievementPage;
import net.modificationstation.stationapi.api.util.Identifier;
import net.modificationstation.stationapi.api.template.item.TemplateItem;

import java.util.List;

import static net.kozibrodka.mocreatures.events.mod_mocreatures.MOD_ID;

public class ItemWhip extends TemplateItem
{

    public ItemWhip(Identifier i)
    {
        super(i);
        maxCount = 1;
        setMaxDamage(24);
    }

    public ItemStack use(ItemStack itemstack, World world, PlayerEntity entityplayer)
    {
        return itemstack;
    }

    public boolean useOnBlock(ItemStack itemstack, PlayerEntity entityplayer, World world, int i, int j, int k, int l)
    {

        if(world.isRemote){
            entityplayer.swingHand();
            return false;
        }
        int i1 = 0;
        int j1 = world.getBlockId(i, j, k);
        int k1 = world.getBlockId(i, j + 1, k);
        if(l != 0 && k1 == 0 && j1 != 0 && j1 != Block.SIGN.id)
        {
            whipFX(world, i, j, k);
            world.playSound(entityplayer, "mocreatures:whip", 0.5F, 0.4F / (random.nextFloat() * 0.4F + 0.8F));
            sendSound(world,"mocreatures:whip", entityplayer.x, entityplayer.y, entityplayer.z, 0.5F, 0.4F / (random.nextFloat() * 0.4F + 0.8F));
            itemstack.damage(1, entityplayer);
            List list = world.getEntities(entityplayer, entityplayer.boundingBox.expand(12D, 12D, 12D));
            for(int l1 = 0; l1 < list.size(); l1++)
            {
                Entity entity = (Entity)list.get(l1);
                if(entity instanceof EntityBigCat)
                {
                    EntityBigCat entitybigcat = (EntityBigCat)entity;
                    if(entitybigcat.getTamed() && entityplayer.name.equals(entitybigcat.getOwner()))
                    {
                        entitybigcat.setSitting(!entitybigcat.getSitting());
                        i1++;
                    } else
                    if(world.difficulty > 0 && entitybigcat.getAdult())
                    {
                        entitybigcat.target = entityplayer;
                    }
                }
                if(entity instanceof EntityHorse)
                {
                    EntityHorse entityhorse = (EntityHorse)entity;
                    if((entityhorse.getTamed() && entityplayer.name.equals(entityhorse.getOwner()) || (entityhorse.getTamed() && !entityhorse.getProtect())))
                    {
                        entityhorse.setSitting(!entityhorse.getSitting());
                    }
                }
                /// ŻÓŁW wyłączony na razie - żeby śmiesznie chodził
//                if(entity instanceof EntityTurtle)
//                {
//                    EntityTurtle entityturtle = (EntityTurtle)entity;
//                    if((entityturtle.getTamed() && entityplayer.name.equals(entityturtle.getOwner()) || (entityturtle.getTamed() && !entityturtle.getProtect())))
//                    {
//                        entityturtle.setSitting(!entityturtle.getSitting());
//                    }
//                }
                if(!(entity instanceof EntityKitty))
                {
                    continue;
                }
                EntityKitty entitykitty = (EntityKitty)entity;
                if(entitykitty.getKittyState() > 2 && entitykitty.whipeable())
                {
                    entitykitty.setSitting(!entitykitty.getSitting());
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
                SignBlockEntity tileentitysign = (SignBlockEntity)world.getBlockEntity(i, j + 1, k);
                int tempY = j + 1;
                if(tileentitysign == null)
                {
                    tileentitysign = (SignBlockEntity)world.getBlockEntity(i, j, k);
                    tempY = j;
                }
                if(tileentitysign != null)
                {
                    int i2 = 0;
                    List list1 = world.entities;
                    for(int j2 = 0; j2 < list1.size(); j2++)
                    {
                        if(list1.get(j2) instanceof EntityBunny entitybunny)
                        {
                            i2++;
                            entitybunny.markDead();
                        }
                    }
                    String s = String.valueOf(i2);
                    tileentitysign.texts[0] = "";
                    tileentitysign.texts[1] = "R.I.P.";
                    tileentitysign.texts[2] = (new StringBuilder()).append(s).append(" Bunnies").toString();
                    tileentitysign.texts[3] = "";
                    world.blockUpdate(i, tempY, k, Block.SIGN.id);
                    if(i2 > 69)
                    {
                        entityplayer.incrementStat(mod_mocreatures.BunnyKilla);
                    }
                    whipFX(world, i, j, k);
                    world.playSound(entityplayer, "mocreatures:whip", 0.5F, 0.4F / (random.nextFloat() * 0.4F + 0.8F));
                    sendSound(world,"mocreatures:whip", entityplayer.x, entityplayer.y, entityplayer.z, 0.5F, 0.4F / (random.nextFloat() * 0.4F + 0.8F));
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

        if (net.fabricmc.loader.FabricLoader.INSTANCE.getEnvironmentType() == EnvType.SERVER){
            mocr.particlePacket(world,"smoke", d - d4, d1 + d3, d2, 0.0D, 0.0D, 0.0D);
            mocr.particlePacket(world,"flame", d - d4, d1 + d3, d2, 0.0D, 0.0D, 0.0D);
            mocr.particlePacket(world,"smoke", d + d4, d1 + d3, d2, 0.0D, 0.0D, 0.0D);
            mocr.particlePacket(world,"flame", d + d4, d1 + d3, d2, 0.0D, 0.0D, 0.0D);
            mocr.particlePacket(world,"smoke", d, d1 + d3, d2 - d4, 0.0D, 0.0D, 0.0D);
            mocr.particlePacket(world,"flame", d, d1 + d3, d2 - d4, 0.0D, 0.0D, 0.0D);
            mocr.particlePacket(world,"smoke", d, d1 + d3, d2 + d4, 0.0D, 0.0D, 0.0D);
            mocr.particlePacket(world,"flame", d, d1 + d3, d2 + d4, 0.0D, 0.0D, 0.0D);
            mocr.particlePacket(world,"smoke", d, d1, d2, 0.0D, 0.0D, 0.0D);
            mocr.particlePacket(world,"flame", d, d1, d2, 0.0D, 0.0D, 0.0D);
        }
    }

    public void sendSound(World world, String name, double x, double y, double z, float g, float h){
        if (FabricLoader.INSTANCE.getEnvironmentType() == EnvType.SERVER){
            mocr.voicePacket(world, name,x,y,z,g,h);
        }
    }

    mod_mocreatures mocr = new mod_mocreatures();

    public boolean isFull3D()
    {
        return true;
    }
}
