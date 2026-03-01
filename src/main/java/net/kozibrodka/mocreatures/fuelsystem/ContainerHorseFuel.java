package net.kozibrodka.mocreatures.fuelsystem;

import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.kozibrodka.mocreatures.entity.EntityHorse;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.inventory.Inventory;
import net.minecraft.screen.ScreenHandler;
import net.minecraft.screen.ScreenHandlerListener;
import net.minecraft.screen.slot.Slot;

public class ContainerHorseFuel extends ScreenHandler
{
    public ContainerHorseFuel(Inventory iinventory, EntityHorse entityhorse)
    {
        byte var3 = 51;
        horse = entityhorse;
        addSlot(new Slot(entityhorse, 0, 80, 22));

        for(int i1 = 0; i1 < 3; i1++)
        {
            for(int k1 = 0; k1 < 9; k1++)
            {
                addSlot(new Slot(iinventory, k1 + i1 * 9 + 9, 8 + k1 * 18, i1 * 18 + var3));
            }

        }

        for(int j1 = 0; j1 < 9; j1++)
        {
            addSlot(new Slot(iinventory, j1, 8 + j1 * 18, 58 + var3));
        }

    }

    public boolean canUse(PlayerEntity entityplayer)
    {
        return horse.canPlayerUse(entityplayer);
    }

    private EntityHorse horse;

    private int animalFuel;
    private int fuelDuration;

    @Environment(EnvType.SERVER)
    @Override
    public void addListener(ScreenHandlerListener listener) {
        super.addListener(listener);
        listener.onPropertyUpdate(this, 0, horse.animalFuel);
        listener.onPropertyUpdate(this, 1, horse.fuelDuration);
    }

    @Override
    public void sendContentUpdates() {
        super.sendContentUpdates();

        for (Object listener : listeners) {
            ScreenHandlerListener shl = (ScreenHandlerListener) listener;
            if (this.animalFuel != horse.animalFuel) shl.onPropertyUpdate(this, 0, horse.animalFuel);
            if (this.fuelDuration != horse.fuelDuration) shl.onPropertyUpdate(this, 1, horse.fuelDuration);
        }

        this.animalFuel = horse.animalFuel;
        this.fuelDuration = horse.fuelDuration;
    }

    @Environment(EnvType.CLIENT)
    @Override
    public void setProperty(int id, int value) {
        if (id == 0) horse.animalFuel = value;
        if (id == 1) horse.fuelDuration = value;
    }
}
