package net.kozibrodka.mocreatures.fuelsystem;

import net.kozibrodka.mocreatures.entity.EntityHorse;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.inventory.Container;
import net.minecraft.inventory.Inventory;
import net.minecraft.screen.slot.Slot;

public class ContainerHorseFuel extends Container
{

    public ContainerHorseFuel(Inventory iinventory, EntityHorse entityhorse)
    {
        byte var3 = 51;
        horse = entityhorse;
        method_2079(new Slot(entityhorse, 0, 80, 22)); //addSlot(new Slot(entityplane, 0, 8, 53));
//        addSlot(new Slot(entityplane, 2, 44 + 2 * 18, 20));


        for(int i1 = 0; i1 < 3; i1++)
        {
            for(int k1 = 0; k1 < 9; k1++)
            {
                method_2079(new Slot(iinventory, k1 + i1 * 9 + 9, 8 + k1 * 18, i1 * 18 + var3));
            }

        }

        for(int j1 = 0; j1 < 9; j1++)
        {
            method_2079(new Slot(iinventory, j1, 8 + j1 * 18, 58 + var3));
        }

    }

    public boolean method_2094(PlayerEntity entityplayer)
    {
        return horse.canPlayerUse(entityplayer);
    }

    private EntityHorse horse;
}
