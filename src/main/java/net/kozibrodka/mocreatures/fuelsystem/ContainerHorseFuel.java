package net.kozibrodka.mocreatures.fuelsystem;

import net.kozibrodka.mocreatures.entity.EntityHorse;
import net.minecraft.container.ContainerBase;
import net.minecraft.container.slot.Slot;
import net.minecraft.entity.player.PlayerBase;
import net.minecraft.inventory.InventoryBase;

public class ContainerHorseFuel extends ContainerBase
{

    public ContainerHorseFuel(InventoryBase iinventory, EntityHorse entityhorse)
    {
        byte var3 = 51;
        horse = entityhorse;
        addSlot(new Slot(entityhorse, 0, 80, 22)); //addSlot(new Slot(entityplane, 0, 8, 53));
//        addSlot(new Slot(entityplane, 2, 44 + 2 * 18, 20));


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

    public boolean canUse(PlayerBase entityplayer)
    {
        return horse.canPlayerUse(entityplayer);
    }

    private EntityHorse horse;
}
