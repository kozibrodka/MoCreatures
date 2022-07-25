package net.kozibrodka.mocreatures.mixin;

import net.fabricmc.loader.api.FabricLoader;
import net.kozibrodka.mocreatures.events.mod_mocreatures;
import net.minecraft.item.ItemBase;
import net.minecraft.item.ItemInstance;
import net.minecraft.level.structure.Dungeon;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(Dungeon.class)
public class DungeonSaddleMixin {

    @Inject(method = "getRandomChestItem", at = @At(value = "RETURN", ordinal = 0), cancellable = true)
    private void injected(CallbackInfoReturnable<ItemInstance> cir) {
        if(FabricLoader.getInstance().isModLoaded("aether"))        //FabricLoader.getInstance().isModLoaded("aether")
        {
            cir.setReturnValue(new ItemInstance(mod_mocreatures.horsesaddle));
        }else
        {
            cir.setReturnValue(new ItemInstance(ItemBase.saddle));
        }
    }

}
