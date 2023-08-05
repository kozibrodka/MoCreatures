package net.kozibrodka.mocreatures.events;

import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.mine_diver.unsafeevents.listener.EventListener;
import net.minecraft.client.options.KeyBinding;
import net.modificationstation.stationapi.api.client.event.option.KeyBindingRegisterEvent;
import net.modificationstation.stationapi.api.mod.entrypoint.Entrypoint;
import net.modificationstation.stationapi.api.registry.ModID;
import net.modificationstation.stationapi.api.util.Null;
import org.lwjgl.input.Keyboard;

import java.util.List;

public class KeybindListener {

    @Entrypoint.ModID
    public static final ModID MOD_ID = Null.get();

    @EventListener
    public void registerKeyBindings(KeyBindingRegisterEvent event) {
        List<KeyBinding> list = event.keyBindings;
        list.add(keyBinding_horseFuel = new KeyBinding("Horse Fuel...", Keyboard.KEY_H));
    }
    public static KeyBinding keyBinding_horseFuel;
}
