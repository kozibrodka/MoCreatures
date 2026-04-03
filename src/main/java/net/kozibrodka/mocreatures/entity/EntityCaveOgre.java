// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) braces deadcode 

package net.kozibrodka.mocreatures.entity;

import net.kozibrodka.mocreatures.events.mod_mocreatures;
import net.minecraft.block.Block;
import net.minecraft.entity.Entity;
import net.minecraft.entity.Monster;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NbtCompound;
import net.minecraft.util.math.MathHelper;
import net.minecraft.world.World;
import net.modificationstation.stationapi.api.util.Identifier;
import net.modificationstation.stationapi.api.server.entity.MobSpawnDataProvider;

public class EntityCaveOgre extends EntityOgre
    implements Monster, MobSpawnDataProvider
{

    public EntityCaveOgre(World world)
    {
        super(world);
        attackDamage = 3;
        attackRange = mod_mocreatures.mocGlass.hostilemobs.ogrerange;
        texture = "/assets/mocreatures/stationapi/textures/mob/caveogre.png";
        setBoundingBoxSpacing(1.5F, 4F);
        health = 50;
        bogrefire = false;
        destroyForce = mod_mocreatures.mocGlass.hostilemobs.cogreStrength;
        fireImmune = false;
        frequencyA = 35;
    }

    public void tickMovement()
    {
        getTargetInRange();
        destroyForce = mod_mocreatures.mocGlass.hostilemobs.cogreStrength;
        attackRange = mod_mocreatures.mocGlass.hostilemobs.ogrerange;
        if(ogrehasenemy && random.nextInt(frequencyA) == 0 && !world.isRemote)
        {
            setOgreAttack(true);
            attackCooldown = 15;
        }
        if(attackCooldown <= 0 && getOgreAttack() && !world.isRemote)
        {
            setOgreAttack(false);
            DestroyingOgre();
        }
        if(world.canMonsterSpawn() && !world.isRemote)
        {
            float f = getBrightnessAtEyes(1.0F);
            if(f > 0.5F && world.hasSkyLight(MathHelper.floor(x), MathHelper.floor(y), MathHelper.floor(z)) && random.nextFloat() * 30F < (f - 0.4F) * 2.0F)
            {
                if(health <= 5){
                    damage(null ,5);
                }else{
                    health -= 5;
                }
            }
        }
        super.onLivingUpdate2();
    }

    public boolean maxNumberReached()
    {
        int i = 0;
        for(int j = 0; j < world.entities.size(); j++)
        {
            Entity entity = (Entity)world.entities.get(j);
            if(entity instanceof EntityCaveOgre)
            {
                i++;
            }
        }

        return false;
    }

    public void writeNbt(NbtCompound nbttagcompound)
    {
        super.writeNbt(nbttagcompound);
    }

    public void readNbt(NbtCompound nbttagcompound)
    {
        super.readNbt(nbttagcompound);
    }

    public boolean canSpawn()
    {
        return mod_mocreatures.mocGlass.hostilemobs.cogrefreq > 0 && world.difficulty >= mod_mocreatures.mocGlass.hostilemobs.cogreSpawnDifficulty.ordinal() + 1 && !world.hasSkyLight(MathHelper.floor(x), MathHelper.floor(y), MathHelper.floor(z)) && y < 50D && super.d2();
    }

    protected int getDroppedItemId()
    {
        return Item.DIAMOND.id;
    }

    protected void dropItems()
    {
        if(mod_mocreatures.mocGlass.hostilemobs.caveogrediamond){
            int i = random.nextInt(3);
            for(int j = 0; j < i; j++)
            {
                dropItem(new ItemStack(getDroppedItemId(), 1, 0), 0.0F);
            }
        }else{
            int i = random.nextInt(15);
            int baseDura = 1200;
            int randomDura = 360;
            int betaDura = 100; ///armor durability, minimalnie ~10 chyba moge przestrzelić, ale chuj...
            switch (i){
                case 0:
                    dropItem(new ItemStack(Item.DIAMOND_PICKAXE.id, 1, random.nextInt(randomDura) + baseDura), 0.0F);
                    break;
                case 1:
                    dropItem(new ItemStack(Item.DIAMOND_AXE.id, 1, random.nextInt(randomDura) + baseDura), 0.0F);
                    break;
                case 2:
                    dropItem(new ItemStack(Item.DIAMOND_SHOVEL.id, 1, random.nextInt(randomDura) + baseDura), 0.0F);
                    break;
                case 3:
                    dropItem(new ItemStack(Item.DIAMOND_SWORD.id, 1, random.nextInt(randomDura) + baseDura), 0.0F);
                    break;
                case 4:
                    dropItem(new ItemStack(Item.DIAMOND_HOE.id, 1, random.nextInt(randomDura) + baseDura), 0.0F);
                    break;
                case 5:
                    dropItem(new ItemStack(Item.DIAMOND_HELMET.id, 1, random.nextInt(109) + 253 - betaDura), 0.0F); //363
                    break;
                case 6:
                    dropItem(new ItemStack(Item.DIAMOND_CHESTPLATE.id, 1, random.nextInt(169) + 358 - betaDura), 0.0F); //528
                    break;
                case 7:
                    dropItem(new ItemStack(Item.DIAMOND_LEGGINGS.id, 1, random.nextInt(149) + 345 - betaDura), 0.0F); //495
                    break;
                case 8:
                    dropItem(new ItemStack(Item.DIAMOND_BOOTS.id, 1, random.nextInt(129) + 299 - betaDura), 0.0F); //429
                    break;
                case 9,10,11:
                    dropItem(new ItemStack(Block.COBWEB.id, 1, 0), 0.0F);
                    break;
            }

        }
    }


    @Override
    public Identifier getHandlerIdentifier() {
        return Identifier.of(mod_mocreatures.MOD_ID, "CaveOgre");
    }
}
