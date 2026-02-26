package net.kozibrodka.mocreatures.mocreatures;

import net.fabricmc.loader.api.FabricLoader;
import net.minecraft.client.Minecraft;
import net.minecraft.entity.LivingEntity;

public class MoGuiOpener {

    public static Minecraft mc = Minecraft.class.cast(FabricLoader.getInstance().getGameInstance());

    public void openTameGui(LivingEntity entityliving, String name){
        mc.setScreen(new MoCGUI(entityliving, name));
    }
}
