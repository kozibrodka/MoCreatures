package net.kozibrodka.mocreatures.fuelsystem;
import net.kozibrodka.mocreatures.entity.EntityHorse;
import net.minecraft.client.gui.screen.ingame.HandledScreen;
import net.minecraft.entity.player.PlayerInventory;
import org.lwjgl.opengl.GL11;

public class GuiHorseFuel extends HandledScreen
{

    public GuiHorseFuel(PlayerInventory inventoryplayer, EntityHorse entityplane)
    {
        super(new ContainerHorseFuel(inventoryplayer, entityplane));
        horse = entityplane;
        this.backgroundHeight = 133;
    }

    protected void drawForeground()
    {
        textRenderer.draw(horse.getName(), 8, 6, 0x404040);
        textRenderer.draw("Inventory", 8, (backgroundHeight - 96) + 2, 0x404040);
    }

    protected void drawBackground(float f)
    {
        int i = minecraft.textureManager.getTextureId("/assets/mocreatures/stationapi/textures/mob/stomach.png");
        GL11.glColor4f(1.0F, 1.0F, 1.0F, 1.0F);
        minecraft.textureManager.bindTexture(i);
        int j = (width - backgroundWidth) / 2;
        int k = (height - backgroundHeight) / 2;
        drawTexture(j, k, 0, 0, backgroundWidth, backgroundHeight);
        if(horse.isFuelled())
        {
            int l = horse.getBurnTimeRemainingScaled(12);
            drawTexture(j + 80, (k + 17) - l, 176, 12 - l, 14, l + 2);
        }
    }

    private EntityHorse horse;
}
