package net.kozibrodka.mocreatures.mixin;

import net.fabricmc.loader.api.FabricLoader;
import net.minecraft.client.Minecraft;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(
        value = Minecraft.SessionCheckThread.class,
        priority = 0
)
class MixinFixThreadCheckHasPaid {
    @Inject(
            method = "run",
            at = @At("HEAD"),
            cancellable = true,
            remap = false,
            require = 0
    )
    private void run(CallbackInfo info) {
        if (FabricLoader.getInstance().isDevelopmentEnvironment()) {
            info.cancel();
        }
    }
}
