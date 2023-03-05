// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) braces deadcode 

package net.kozibrodka.mocreatures.entity;

import net.fabricmc.loader.api.FabricLoader;
import net.kozibrodka.mocreatures.events.mod_mocreatures;
import net.kozibrodka.mocreatures.mixin.EntityBaseAccesor;
import net.kozibrodka.mocreatures.mocreatures.MoCGUI;
import net.minecraft.client.Minecraft;
import net.minecraft.entity.EntityBase;
import net.minecraft.entity.Item;
import net.minecraft.entity.Living;
import net.minecraft.entity.animal.Wolf;
import net.minecraft.entity.player.PlayerBase;
import net.minecraft.item.ItemBase;
import net.minecraft.item.ItemInstance;
import net.minecraft.level.Level;
import net.minecraft.util.io.CompoundTag;

import java.util.List;


public class EntityShark extends EntityCustomWM
{

    public EntityShark(Level world)
    {
        super(world);
        texture = "/assets/mocreatures/stationapi/textures/mob/shark.png";
        setSize(1.8F, 1.3F);
        b = 1.0F + rand.nextFloat();
        adult = false;
        tamed = false;
        health = 25;
        maxhealth = 25;
        name = "";
        displayname = false;
        sharkOwner = "";
        protectFromPlayers = true;
    }

    public void updateDespawnCounter()
    {
        super.updateDespawnCounter();
        if(!adult && rand.nextInt(50) == 0)
        {
            b += 0.01F;
            if(b >= 2.0F)
            {
                adult = true;
            }
        }
    }

    protected void tickHandSwing(){
        if(this.entity instanceof Living){
            PlayerBase uciekinier = level.getClosestPlayerTo(this, 16D);
            if(uciekinier == null && entity.isAlive()){
                if(rand.nextInt(30) == 0)
                {
                    entity = null;
                }
            }
        }
        super.tickHandSwing();
    }

    protected EntityBase getAttackTarget()
    {
        if(level.difficulty > 0 && b >= 1.0F)
        {
            PlayerBase entityplayer = level.getClosestPlayerTo(this, 16D);
            if(entityplayer != null && ((EntityBaseAccesor)entityplayer).getField_1612() && !tamed)
            {
                return entityplayer;
            }
            if(rand.nextInt(30) == 0)
            {
                Living entityliving = FindTarget(this, 16D);
                if(entityliving != null && ((EntityBaseAccesor)entityliving).getField_1612())
                {
                    return entityliving;
                }
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
            if(!(entity1 instanceof Living) || (entity1 instanceof EntityShark) || (entity1 instanceof EntitySharkEgg) || (entity1 instanceof PlayerBase) || (entity1 instanceof Wolf) && !mocr.mocreaturesGlass.huntercreatures.attackwolves || (entity1 instanceof EntityHorse) && !mocr.mocreaturesGlass.huntercreatures.attackhorses || (entity1 instanceof EntityDolphin) && (tamed || !mocr.mocreaturesGlass.watermobs.attackdolphins) || (entity1 instanceof EntityHorse) && tamed && ((EntityHorse) entity1).tamed || (entity1 instanceof EntityBigCat) && tamed && ((EntityBigCat) entity1).tamed)
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
            if(tamed && entitybase instanceof PlayerBase)
            {
                PlayerBase gracz = (PlayerBase)entitybase;
                if(!gracz.name.equals(sharkOwner) && protectFromPlayers)
                {
                    entity = entitybase;
                }
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
            if(!(entity instanceof PlayerBase))
            {
                destroyDrops(this, 3D);
            }
        }
    }

    public void writeCustomDataToTag(CompoundTag nbttagcompound)
    {
        super.writeCustomDataToTag(nbttagcompound);
        nbttagcompound.put("Tamed", tamed);
        nbttagcompound.put("Adult", adult);
        nbttagcompound.put("Age", b);
        nbttagcompound.put("Name", name);
        nbttagcompound.put("DisplayName", displayname);
    }

    public void readCustomDataFromTag(CompoundTag nbttagcompound)
    {
        super.readCustomDataFromTag(nbttagcompound);
        tamed = nbttagcompound.getBoolean("Tamed");
        adult = nbttagcompound.getBoolean("Adult");
        b = nbttagcompound.getFloat("Age");
        name = nbttagcompound.getString("Name");
        displayname = nbttagcompound.getBoolean("DisplayName");
    }

    protected boolean method_940()
    {
        return !tamed;
    }

    public boolean Despawn()
    {
        return !tamed;
    }

    protected void getDrops()
    {
        int i = rand.nextInt(100);
        if(i < 90)
        {
            int j = rand.nextInt(3) + 1;
            for(int l = 0; l < j; l++)
            {
                dropItem(new ItemInstance(mod_mocreatures.sharkteeth, 1, 0), 0.0F);
            }
            if(mocr.mocreaturesGlass.balancesettings.balance_drop) {
                int j1 = rand.nextInt(2);
                for (int l1 = 0; l1 < j1; l1++) {
                    dropItem(new ItemInstance(mod_mocreatures.sharkoil, 1, 0), 0.0F);
                }
            }

        } else
        if(level.difficulty > 0 && b > 1.5F)
        {
            int k = rand.nextInt(3) + 1;
            for(int i1 = 0; i1 < k; i1++)
            {
                dropItem(new ItemInstance(mod_mocreatures.sharkegg, 1, 0), 0.0F);
            }

        }
    }

    public void destroyDrops(EntityBase entity, double d)
    {
        List list = level.getEntities(entity, entity.boundingBox.expand(d, d, d));
        for(int i = 0; i < list.size(); i++)
        {
            EntityBase entity1 = (EntityBase)list.get(i);
            if(!(entity1 instanceof Item))
            {
                continue;
            }
            Item entityitem = (Item)entity1;
            if(entityitem != null && entityitem.age < 50 && mocr.mocreaturesGlass.huntercreatures.destroyitems)
            {
                entityitem.remove();
            }
        }

    }

    public boolean interact(PlayerBase entityplayer)
    {
        ItemInstance itemstack = entityplayer.inventory.getHeldItem();
        if(tamed && !protectFromPlayers && !entityplayer.name.equals(sharkOwner))
        {
            return false;
        }
        if(itemstack != null && tamed && entityplayer.name.equals(sharkOwner) && (itemstack.itemId == ItemBase.goldSword.id || itemstack.itemId == ItemBase.stoneSword.id || itemstack.itemId == ItemBase.woodSword.id || itemstack.itemId == ItemBase.ironSword.id || itemstack.itemId == ItemBase.diamondSword.id))
        {
            if(protectFromPlayers == true){
                protectFromPlayers = false;
                for(int var3 = 0; var3 < 7; ++var3) {
                    double var4 = this.rand.nextGaussian() * 0.02D;
                    double var6 = this.rand.nextGaussian() * 0.02D;
                    double var8 = this.rand.nextGaussian() * 0.02D;
                    level.addParticle("heart", this.x + (double) (this.rand.nextFloat() * this.width * 2.0F) - (double) this.width, this.y + 0.5D + (double) (this.rand.nextFloat() * this.height), this.z + (double) (this.rand.nextFloat() * this.width * 2.0F) - (double) this.width, var4, var6, var8);
                }
            }else{
                protectFromPlayers = true;
                for(int var3 = 0; var3 < 7; ++var3) {
                    double var4 = this.rand.nextGaussian() * 0.02D;
                    double var6 = this.rand.nextGaussian() * 0.02D;
                    double var8 = this.rand.nextGaussian() * 0.02D;
                    level.addParticle("flame", this.x + (double) (this.rand.nextFloat() * this.width * 2.0F) - (double) this.width, this.y + 0.5D + (double) (this.rand.nextFloat() * this.height), this.z + (double) (this.rand.nextFloat() * this.width * 2.0F) - (double) this.width, var4, var6, var8);
                }
            }
            return true;
        }
        if(itemstack !=null && entityplayer.name.equals(sharkOwner) && tamed && itemstack.itemId == mod_mocreatures.sharkfood.id)
        {
            if(--itemstack.count == 0)
            {
                entityplayer.inventory.setInventoryItem(entityplayer.inventory.selectedHotbarSlot, null);
            }
            if(health + 15 > maxhealth)
            {
                health = maxhealth;
            }else{
                health += 15;
            }
            return true;
        }
        if(itemstack != null && tamed && (itemstack.itemId == mod_mocreatures.medallion.id || itemstack.itemId == ItemBase.book.id))
        {
            setName(this);
            return true;
        }
        if(itemstack != null && tamed && (itemstack.itemId == ItemBase.diamondPickaxe.id || itemstack.itemId == ItemBase.woodPickaxe.id || itemstack.itemId == ItemBase.stonePickaxe.id || itemstack.itemId == ItemBase.ironPickaxe.id || itemstack.itemId == ItemBase.goldPickaxe.id))
        {
            displayname = !displayname;
            return true;
        } else
        {
            return false;
        }
    }

    public void remove()
    {
        if(tamed && health > 0)
        {
            return;
        } else
        {
            super.remove();
            return;
        }
    }

    protected String getHurtSound()
    {
        return "mocreatures:sharkhurt";
    }

    protected String getDeathSound()
    {
        return "mocreatures:sharkhurt";
    }


    public boolean renderName()
    {
        return displayname;
    }

    public boolean canSpawn()
    {
        return mocr.mocreaturesGlass.watermobs.sharkfreq > 0 && level.difficulty >= mocr.mocreaturesGlass.watermobs.sharkSpawnDifficulty + 1 && super.canSpawn();
    }

    public static void setName(EntityShark entityshark)
    {
        entityshark.displayname = true;
        mc.openScreen(new MoCGUI(entityshark, entityshark.name));
    }

    @SuppressWarnings("deprecation")
    public static Minecraft mc = Minecraft.class.cast(FabricLoader.getInstance().getGameInstance());
    mod_mocreatures mocr = new mod_mocreatures();
    public boolean protectFromPlayers;
    public String sharkOwner;
    public String name;
    public boolean displayname;
    public float b;
    public boolean adult;
    public boolean tamed;
    public int maxhealth;
}
