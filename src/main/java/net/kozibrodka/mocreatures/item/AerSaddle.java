package net.kozibrodka.mocreatures.item;

import com.matthewperiut.aether.entity.living.EntityFlyingCow;
import com.matthewperiut.aether.entity.living.EntityMoa;
import com.matthewperiut.aether.entity.living.EntityPhyg;
import net.fabricmc.loader.api.FabricLoader;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.passive.PigEntity;
import net.minecraft.item.ItemStack;
import net.modificationstation.stationapi.api.template.item.TemplateItem;
import net.modificationstation.stationapi.api.util.Identifier;

public class AerSaddle extends TemplateItem {
    public AerSaddle(Identifier identifier) {
        super(identifier);
        this.maxCount = 1;
    }

    @Override
    public void useOnEntity(ItemStack stack, LivingEntity entity) {
        if (entity instanceof PigEntity) {
            PigEntity var3 = (PigEntity)entity;
            if (!var3.isSaddled()) {
                var3.setSaddled(true);
                --stack.count;
            }
        }
        if(FabricLoader.getInstance().isModLoaded("aether")){
            if(entity instanceof EntityPhyg eaPig && !eaPig.getSaddled){
                eaPig.getSaddled = true;
                --stack.count;
            }
            if(entity instanceof EntityFlyingCow aeCow && !aeCow.getSaddled){
                aeCow.getSaddled = true;
                --stack.count;
            }
            if(entity instanceof EntityMoa aeMoa && !aeMoa.saddled && aeMoa.grown && !aeMoa.baby){
                aeMoa.saddled = true;
                aeMoa.grown = false;
                --stack.count;
            }
        }
    }

    @Override
    public boolean postHit(ItemStack stack, LivingEntity target, LivingEntity attacker) {
        this.useOnEntity(stack, target);
        return true;
    }
}
