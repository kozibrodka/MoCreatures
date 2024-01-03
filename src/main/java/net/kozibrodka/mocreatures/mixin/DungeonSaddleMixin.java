package net.kozibrodka.mocreatures.mixin;

import net.fabricmc.loader.api.FabricLoader;
import net.kozibrodka.mocreatures.events.mod_mocreatures;
import net.minecraft.class_30;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(class_30.class)
public class DungeonSaddleMixin {

    @Inject(method = "method_115", at = @At(value = "RETURN", ordinal = 0), cancellable = true)
    private void injected(CallbackInfoReturnable<ItemStack> cir) {
        if(FabricLoader.getInstance().isModLoaded("aether"))        //FabricLoader.getInstance().isModLoaded("aether")
        {
            cir.setReturnValue(new ItemStack(mod_mocreatures.horsesaddle));
        }else
        {
            cir.setReturnValue(new ItemStack(Item.SADDLE));
        }
    }

}
