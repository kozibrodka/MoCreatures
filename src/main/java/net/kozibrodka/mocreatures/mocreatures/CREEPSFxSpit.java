
package net.kozibrodka.mocreatures.mocreatures;


import net.minecraft.client.particle.Particle;
import net.minecraft.client.render.Tessellator;
import net.minecraft.world.World;
import org.lwjgl.opengl.GL11;

public class CREEPSFxSpit extends Particle
{

    public CREEPSFxSpit(World world, double d, double d1, double d2,
                        int i)
    {
        super(world, d, d1, d2, 0.0D, 0.0D, 0.0D);
        textureId = i;
        setBoundingBoxSpacing(0.3F, 0.3F);
        red = 1.0F;
        blue = 1.0F;
        green = 1.0F;
        gravityStrength = 6.55F;
        scale *= 0.3F;
    }

    public int getGroup()
    {
        return 2;
    }

    @Override
    public void render(Tessellator tessellator, float f, float f1, float f2, float f3, float f4, float f5) {
        GL11.glBindTexture(3553, textureId);
        float textureX = 0;
        float f7 = textureX + 1;
        float textureY = 0;
        float f9 = textureY + 1;
        float adjustedScale = 0.1F * scale;
        float f11 = (float) ((prevX + (x - prevX) * (double) f) - xOffset);
        float f12 = (float) ((prevY + (y - prevY) * (double) f) - yOffset);
        float f13 = (float) ((prevZ + (z - prevZ) * (double) f) - zOffset);
        float f14 = getBrightnessAtEyes(f) * 1.25F;
        tessellator.color(red * f14, green * f14, blue * f14);
        tessellator.vertex(f11 - f1 * adjustedScale - f4 * adjustedScale, f12 - f2 * adjustedScale, f13 - f3 * adjustedScale - f5 * adjustedScale, f7, f9);
        tessellator.vertex((f11 - f1 * adjustedScale) + f4 * adjustedScale, f12 + f2 * adjustedScale, (f13 - f3 * adjustedScale) + f5 * adjustedScale, f7, textureY);
        tessellator.vertex(f11 + f1 * adjustedScale + f4 * adjustedScale, f12 + f2 * adjustedScale, f13 + f3 * adjustedScale + f5 * adjustedScale, textureX, textureY);
        tessellator.vertex((f11 + f1 * adjustedScale) - f4 * adjustedScale, f12 - f2 * adjustedScale, (f13 + f3 * adjustedScale) - f5 * adjustedScale, textureX, f9);
    }

}
