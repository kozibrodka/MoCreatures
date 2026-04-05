
package net.kozibrodka.mocreatures.item;

import net.kozibrodka.mocreatures.entity.EntityLitterBox;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.world.World;
import net.modificationstation.stationapi.api.util.Identifier;
import net.modificationstation.stationapi.api.template.item.TemplateItem;


public class ItemLitterBox extends TemplateItem
{

    public ItemLitterBox(Identifier i)
    {
        super(i);
        maxCount = 16;
    }

    @Override
    public ItemStack use(ItemStack itemstack, World world, PlayerEntity entityplayer)
    {
        if(world.isRemote){
            return itemstack;
        }
        itemstack.count--;
        EntityLitterBox entitylitterbox = new EntityLitterBox(world);
        entitylitterbox.setPosition(entityplayer.x, entityplayer.y, entityplayer.z);
        world.spawnEntity(entitylitterbox);
        entitylitterbox.velocityY += world.random.nextFloat() * 0.05F;
        entitylitterbox.velocityX += (world.random.nextFloat() - world.random.nextFloat()) * 0.3F;
        entitylitterbox.velocityZ += (world.random.nextFloat() - world.random.nextFloat()) * 0.3F;
        return itemstack;
    }
}
