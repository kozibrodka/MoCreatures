

package net.kozibrodka.mocreatures.item;

import net.kozibrodka.mocreatures.entity.EntityHorse;
import net.minecraft.entity.LivingEntity;
import net.minecraft.item.ItemStack;
import net.modificationstation.stationapi.api.template.item.TemplateItem;
import net.modificationstation.stationapi.api.util.Identifier;

public class HorseSaddle extends TemplateItem
{

    public HorseSaddle(Identifier i)
    {
        super(i);
        maxCount = 32;
    }

    public void useOnEntity(ItemStack itemstack, LivingEntity entityliving)
    {
        if(entityliving instanceof EntityHorse)
        {
            EntityHorse entityhorse = (EntityHorse)entityliving;
            if(!entityhorse.getSaddled() && entityhorse.getAdult())
            {
                entityhorse.setSaddled(true);
                itemstack.count--;
            }
        }
    }

    public boolean postHit(ItemStack stack, LivingEntity target, LivingEntity attacker)
    {
        useOnEntity(stack, target);
        return false;
    }
}
