package net.kozibrodka.mocreatures.mixin;

import net.kozibrodka.mocreatures.mocreatures.MocTick;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.hud.InGameHud;
import org.lwjgl.opengl.GL11;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(InGameHud.class)
public class InGameHudMixin {

    /// This mixin might be a little to crazy

    @Inject(method = "render(FZII)V", at = @At(value = "INVOKE", target = "Lnet/minecraft/client/gui/hud/InGameHud;drawTexture(IIIIII)V", ordinal = 6)) //6
    private void injected2(float screenOpen, boolean mouseX, int mouseY, int par4, CallbackInfo ci) {
        if(MocTick.poisoned){
            GL11.glBindTexture(3553, this.minecraft.textureManager.getTextureId("/assets/mocreatures/stationapi/textures/particle/poison.png"));
        }else if(MocTick.freezed){
            GL11.glBindTexture(3553, this.minecraft.textureManager.getTextureId("/assets/mocreatures/stationapi/textures/particle/freeze.png"));
        }else{
            GL11.glBindTexture(3553, this.minecraft.textureManager.getTextureId("/gui/icons.png"));
        }
//        GL11.glBindTexture(3553, this.minecraft.textureManager.getTextureId("/assets/mocreatures/stationapi/textures/particle/icons.png"));
//        GL11.glBindTexture(3553, this.minecraft.textureManager.getTextureId("/gui/icons.png"));
    }


    @Inject(method = "render(FZII)V", at = @At(value = "INVOKE", target = "Lnet/minecraft/client/gui/hud/InGameHud;drawTexture(IIIIII)V", ordinal = 3))
    private void injected1(float screenOpen, boolean mouseX, int mouseY, int par4, CallbackInfo ci) {
        GL11.glBindTexture(3553, this.minecraft.textureManager.getTextureId("/gui/icons.png"));
    }
    @Inject(method = "render(FZII)V", at = @At(value = "INVOKE", target = "Lnet/minecraft/client/gui/hud/InGameHud;drawTexture(IIIIII)V", ordinal = 4))
    private void injected11(float screenOpen, boolean mouseX, int mouseY, int par4, CallbackInfo ci) {
        GL11.glBindTexture(3553, this.minecraft.textureManager.getTextureId("/gui/icons.png"));
    }
    @Inject(method = "render(FZII)V", at = @At(value = "INVOKE", target = "Lnet/minecraft/client/gui/hud/InGameHud;drawTexture(IIIIII)V", ordinal = 5))
    private void injected111(float screenOpen, boolean mouseX, int mouseY, int par4, CallbackInfo ci) {
        GL11.glBindTexture(3553, this.minecraft.textureManager.getTextureId("/gui/icons.png"));
    }

    @Inject(method = "render(FZII)V", at = @At(value = "INVOKE", target = "Lnet/minecraft/entity/player/ClientPlayerEntity;isInFluid(Lnet/minecraft/block/material/Material;)Z"))
    private void injected3(float screenOpen, boolean mouseX, int mouseY, int par4, CallbackInfo ci) {
        GL11.glBindTexture(3553, this.minecraft.textureManager.getTextureId("/gui/icons.png"));
    }

    @Shadow
    Minecraft minecraft;
}
