package net.kozibrodka.mocreatures.mixin;

import net.kozibrodka.mocreatures.mocreatures.MocTick;
import net.minecraft.client.Minecraft;
import net.minecraft.client.render.GameRenderer;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(GameRenderer.class)
public class GameRendererMixin {

    @Shadow
    protected Minecraft client;

    @Inject(method = "onFrameUpdate", at = @At(value = "RETURN"))
    private void mocr_mod_tick(CallbackInfo ci) {
        if(client.world != null) {
            this.mocr_mod_tick(this.client);
        }
    }

    public void mocr_mod_tick(Minecraft minecraft)
    {
        mod1.MocreaturesTickInGame(minecraft);
    }

    MocTick mod1 = new MocTick();
}
