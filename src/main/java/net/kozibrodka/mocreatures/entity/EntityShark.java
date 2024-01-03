// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) braces deadcode 

package net.kozibrodka.mocreatures.entity;

import net.fabricmc.loader.api.FabricLoader;
import net.kozibrodka.mocreatures.events.mod_mocreatures;
import net.kozibrodka.mocreatures.mixin.EntityBaseAccesor;
import net.kozibrodka.mocreatures.mocreatures.MoCGUI;
import net.minecraft.client.Minecraft;
import net.minecraft.entity.Entity;
import net.minecraft.entity.ItemEntity;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.passive.WolfEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NbtCompound;
import net.minecraft.world.World;
import net.modificationstation.stationapi.api.util.Identifier;
import net.modificationstation.stationapi.api.server.entity.MobSpawnDataProvider;

import java.util.List;


public class EntityShark extends EntityCustomWM implements MobSpawnDataProvider
{

    public EntityShark(World world)
    {
        super(world);
        texture = "/assets/mocreatures/stationapi/textures/mob/shark.png";
        setBoundingBoxSpacing(1.8F, 1.3F);
        b = 1.0F + random.nextFloat();
        adult = false;
        tamed = false;
        health = 25;
        maxhealth = 25;
        name = "";
        displayname = false;
        sharkOwner = "";
        protectFromPlayers = true;
    }

    public void method_937()
    {
        super.method_937();
        if(!adult && random.nextInt(50) == 0)
        {
            b += 0.01F;
            if(b >= 2.0F)
            {
                adult = true;
            }
        }
    }

    protected void method_910(){
        if(this.target instanceof LivingEntity){
            PlayerEntity uciekinier = world.method_186(this, 16D);
            if(uciekinier == null && target.isAlive()){
                if(random.nextInt(30) == 0)
                {
                    target = null;
                }
            }
        }
        super.method_910();
    }

    protected Entity method_638()
    {
        if(world.field_213 > 0 && b >= 1.0F)
        {
            PlayerEntity entityplayer = world.method_186(this, 16D);
            if(entityplayer != null && ((EntityBaseAccesor)entityplayer).getField_1612() && !tamed)
            {
                return entityplayer;
            }
            if(random.nextInt(30) == 0)
            {
                LivingEntity entityliving = FindTarget(this, 16D);
                if(entityliving != null && ((EntityBaseAccesor)entityliving).getField_1612())
                {
                    return entityliving;
                }
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
            if(!(entity1 instanceof LivingEntity) || (entity1 instanceof EntityShark) || (entity1 instanceof EntitySharkEgg) || (entity1 instanceof PlayerEntity) || (entity1 instanceof WolfEntity) && !mocr.mocreaturesGlass.huntercreatures.attackwolves || (entity1 instanceof EntityHorse) && !mocr.mocreaturesGlass.huntercreatures.attackhorses || (entity1 instanceof EntityDolphin) && (tamed || !mocr.mocreaturesGlass.watermobs.attackdolphins) || (entity1 instanceof EntityHorse) && tamed && ((EntityHorse) entity1).tamed || (entity1 instanceof EntityBigCat) && tamed && ((EntityBigCat) entity1).tamed)
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
            if(tamed && entitybase instanceof PlayerEntity)
            {
                PlayerEntity gracz = (PlayerEntity)entitybase;
                if(!gracz.name.equals(sharkOwner) && protectFromPlayers)
                {
                    target = entitybase;
                }
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
            if(!(entity instanceof PlayerEntity))
            {
                destroyDrops(this, 3D);
            }
        }
    }

    public void writeNbt(NbtCompound nbttagcompound)
    {
        super.writeNbt(nbttagcompound);
        nbttagcompound.putBoolean("Tamed", tamed);
        nbttagcompound.putBoolean("Adult", adult);
        nbttagcompound.putFloat("Age", b);
        nbttagcompound.putString("Name", name);
        nbttagcompound.putBoolean("DisplayName", displayname);
    }

    public void readNbt(NbtCompound nbttagcompound)
    {
        super.readNbt(nbttagcompound);
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

    protected void method_933()
    {
        int i = random.nextInt(100);
        if(i < 90)
        {
            int j = random.nextInt(3) + 1;
            for(int l = 0; l < j; l++)
            {
                method_1327(new ItemStack(mod_mocreatures.sharkteeth, 1, 0), 0.0F);
            }
            if(mocr.mocreaturesGlass.balancesettings.balance_drop) {
                int j1 = random.nextInt(2);
                for (int l1 = 0; l1 < j1; l1++) {
                    method_1327(new ItemStack(mod_mocreatures.sharkoil, 1, 0), 0.0F);
                }
            }

        } else
        if(world.field_213 > 0 && b > 1.5F)
        {
            int k = random.nextInt(3) + 1;
            for(int i1 = 0; i1 < k; i1++)
            {
                method_1327(new ItemStack(mod_mocreatures.sharkegg, 1, 0), 0.0F);
            }

        }
    }

    public void destroyDrops(Entity entity, double d)
    {
        List list = world.getEntities(entity, entity.boundingBox.expand(d, d, d));
        for(int i = 0; i < list.size(); i++)
        {
            Entity entity1 = (Entity)list.get(i);
            if(!(entity1 instanceof ItemEntity))
            {
                continue;
            }
            ItemEntity entityitem = (ItemEntity)entity1;
            if(entityitem != null && entityitem.itemAge < 50 && mocr.mocreaturesGlass.huntercreatures.destroyitems)
            {
                entityitem.markDead();
            }
        }

    }

    public boolean method_1323(PlayerEntity entityplayer)
    {
        ItemStack itemstack = entityplayer.inventory.getSelectedItem();
        if(tamed && !protectFromPlayers && !entityplayer.name.equals(sharkOwner))
        {
            return false;
        }
        if(itemstack != null && tamed && entityplayer.name.equals(sharkOwner) && (itemstack.itemId == Item.GOLDEN_SWORD.id || itemstack.itemId == Item.STONE_SWORD.id || itemstack.itemId == Item.WOODEN_SWORD.id || itemstack.itemId == Item.IRON_SWORD.id || itemstack.itemId == Item.DIAMOND_SWORD.id))
        {
            if(protectFromPlayers == true){
                protectFromPlayers = false;
                for(int var3 = 0; var3 < 7; ++var3) {
                    double var4 = this.random.nextGaussian() * 0.02D;
                    double var6 = this.random.nextGaussian() * 0.02D;
                    double var8 = this.random.nextGaussian() * 0.02D;
                    world.addParticle("heart", this.x + (double) (this.random.nextFloat() * this.spacingXZ * 2.0F) - (double) this.spacingXZ, this.y + 0.5D + (double) (this.random.nextFloat() * this.spacingY), this.z + (double) (this.random.nextFloat() * this.spacingXZ * 2.0F) - (double) this.spacingXZ, var4, var6, var8);
                }
            }else{
                protectFromPlayers = true;
                for(int var3 = 0; var3 < 7; ++var3) {
                    double var4 = this.random.nextGaussian() * 0.02D;
                    double var6 = this.random.nextGaussian() * 0.02D;
                    double var8 = this.random.nextGaussian() * 0.02D;
                    world.addParticle("flame", this.x + (double) (this.random.nextFloat() * this.spacingXZ * 2.0F) - (double) this.spacingXZ, this.y + 0.5D + (double) (this.random.nextFloat() * this.spacingY), this.z + (double) (this.random.nextFloat() * this.spacingXZ * 2.0F) - (double) this.spacingXZ, var4, var6, var8);
                }
            }
            return true;
        }
        if(itemstack !=null && entityplayer.name.equals(sharkOwner) && tamed && itemstack.itemId == mod_mocreatures.sharkfood.id)
        {
            if(--itemstack.count == 0)
            {
                entityplayer.inventory.setStack(entityplayer.inventory.selectedSlot, null);
            }
            if(health + 15 > maxhealth)
            {
                health = maxhealth;
            }else{
                health += 15;
            }
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
        } else
        {
            return false;
        }
    }

    public void markDead()
    {
        if(tamed && health > 0)
        {
            return;
        } else
        {
            super.markDead();
            return;
        }
    }

    protected String method_912()
    {
        return "mocreatures:sharkhurt";
    }

    protected String method_913()
    {
        return "mocreatures:sharkhurt";
    }


    public boolean renderName()
    {
        return displayname;
    }

    public boolean canSpawn()
    {
        return mocr.mocreaturesGlass.watermobs.sharkfreq > 0 && world.field_213 >= mocr.mocreaturesGlass.watermobs.sharkSpawnDifficulty + 1 && super.canSpawn();
    }

    public static void setName(EntityShark entityshark)
    {
        entityshark.displayname = true;
        mc.setScreen(new MoCGUI(entityshark, entityshark.name));
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

    @Override
    public Identifier getHandlerIdentifier() {
        return Identifier.of(mod_mocreatures.MOD_ID, "Shark");
    }
}
