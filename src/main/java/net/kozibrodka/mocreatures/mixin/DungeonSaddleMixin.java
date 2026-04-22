package net.kozibrodka.mocreatures.mixin;

import net.fabricmc.loader.api.FabricLoader;
import net.kozibrodka.mocreatures.events.mod_mocreatures;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.world.gen.feature.DungeonFeature;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(DungeonFeature.class)
public class DungeonSaddleMixin {

    /// Mixin usunięty z Configu. /// Rezygnuje z kombinowania z RECIPE siodla w AETHER - i tak eather dodaje Świnie + Krowa + Moa
    /// ktore są lepsze niż słabe konie. Fuel System jest spoko dla balansu.
    ///
    /// Do pomysłu zawsze można wrócić. Jedynie nie wiem jak zmodyfikować recipe w Aether stąd.

    @Inject(method = "getRandomChestItem", at = @At(value = "RETURN", ordinal = 0), cancellable = true)
    private void injected(CallbackInfoReturnable<ItemStack> cir) {
        if(FabricLoader.getInstance().isModLoaded("aether")) {
            cir.setReturnValue(new ItemStack(mod_mocreatures.horsesaddle));
        } else {
            cir.setReturnValue(new ItemStack(Item.SADDLE));
        }
    }

}
