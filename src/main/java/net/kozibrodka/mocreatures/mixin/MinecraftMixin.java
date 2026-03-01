package net.kozibrodka.mocreatures.mixin;

import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.kozibrodka.mocreatures.entity.EntityHorse;
import net.kozibrodka.mocreatures.events.KeybindListener;
import net.kozibrodka.mocreatures.network.HorseFuelOpenGUIPacket;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.screen.Screen;
import net.minecraft.entity.player.ClientPlayerEntity;
import net.modificationstation.stationapi.api.network.packet.PacketHelper;
import org.lwjgl.input.Keyboard;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

/**
 * @author TheSharkHour
 * @since 01/25/2026
 * <p>Client-side mixin for the Minecraft class. Handles controls in the 'tick' method.</p>
 */
@Environment(EnvType.CLIENT)
@Mixin(value = Minecraft.class)
public abstract class MinecraftMixin {

    @Shadow
    public ClientPlayerEntity player;

    @Shadow
    public Screen currentScreen;

    @Inject(method = "tick", at = @At(value = "INVOKE", target = "Lorg/lwjgl/input/Keyboard;next()Z"))
    private void mocreatures_tick(CallbackInfo ci) {
        if (player == null || player.dead || !(player.vehicle instanceof EntityHorse)) return;

        if (Keyboard.getEventKeyState() && currentScreen == null) {
            if (Keyboard.getEventKey() == net.kozibrodka.mocreatures.events.KeybindListener.keyBinding_horseFuel.code) {
                HorseFuelOpenGUIPacket openGUI = new HorseFuelOpenGUIPacket();
                PacketHelper.send(openGUI);
            }
        }
    }
}
