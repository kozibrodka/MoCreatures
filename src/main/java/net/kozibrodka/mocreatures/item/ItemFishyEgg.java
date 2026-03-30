

package net.kozibrodka.mocreatures.item;

import net.kozibrodka.mocreatures.entity.EntityFishyEgg;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.world.World;
import net.modificationstation.stationapi.api.util.Identifier;
import net.modificationstation.stationapi.api.template.item.TemplateItem;

public class ItemFishyEgg extends TemplateItem
{

    public ItemFishyEgg(Identifier i)
    {
        super(i);
        maxCount = 16;
    }

    public ItemStack use(ItemStack itemstack, World world, PlayerEntity entityplayer)
    {
        if(!world.isRemote)
        {
            itemstack.count--;
            EntityFishyEgg entityfishyegg = new EntityFishyEgg(world);
            entityfishyegg.setPosition(entityplayer.x, entityplayer.y, entityplayer.z);
            world.spawnEntity(entityfishyegg);
            entityfishyegg.velocityY += world.random.nextFloat() * 0.05F;
            entityfishyegg.velocityX += (world.random.nextFloat() - world.random.nextFloat()) * 0.3F;
            entityfishyegg.velocityZ += (world.random.nextFloat() - world.random.nextFloat()) * 0.3F;
        }
        return itemstack;
    }
}
