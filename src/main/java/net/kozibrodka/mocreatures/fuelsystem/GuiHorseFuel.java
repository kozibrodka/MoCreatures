package net.kozibrodka.mocreatures.fuelsystem;
import net.kozibrodka.mocreatures.entity.EntityHorse;
import net.minecraft.client.gui.screen.container.ContainerBase;
import net.minecraft.entity.player.PlayerInventory;
import org.lwjgl.opengl.GL11;

public class GuiHorseFuel extends ContainerBase
{

    public GuiHorseFuel(PlayerInventory inventoryplayer, EntityHorse entityplane)
    {
        super(new ContainerHorseFuel(inventoryplayer, entityplane));
        horse = entityplane;
        this.containerHeight = 133;
    }

    protected void renderForeground()
    {
        textManager.drawText(horse.name, 8, 6, 0x404040);
        textManager.drawText("Inventory", 8, (containerHeight - 96) + 2, 0x404040);
    }

    protected void renderContainerBackground(float f)
    {
        int i = minecraft.textureManager.getTextureId("/assets/mocreatures/stationapi/textures/mob/stomach.png");
        GL11.glColor4f(1.0F, 1.0F, 1.0F, 1.0F);
        minecraft.textureManager.bindTexture(i);
        int j = (width - containerWidth) / 2;
        int k = (height - containerHeight) / 2;
        blit(j, k, 0, 0, containerWidth, containerHeight);
        if(horse.isFuelled())
        {
            int l = horse.getBurnTimeRemainingScaled(12);
            blit(j + 80, (k + 17) - l, 176, 12 - l, 14, l + 2);
        }
    }

    private EntityHorse horse;
}
