
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

//    public void render(Tessellator tessellator, float f, float f1, float f2, float f3, float f4, float f5)
//    {
//        float f6 = ((float)(textureId % 16) + prevU / 14F) / 16F;
//        float f7 = f6 + 0.01560938F;
//        float f8 = ((float)(textureId / 16) + prevV / 14F) / 16F;
//        float f9 = f8 + 0.01560938F;
//
//        float f10 = 0.1F * scale;
//        float f11 = (float)((prevX + (x - prevX) * (double)f) - xOffset) * 2.0F;
//        float f12 = (float)((prevY + (y - prevY) * (double)f) - yOffset);
//        float f13 = (float)((prevZ + (z - prevZ) * (double)f) - zOffset) * 2.0F;
//        float f14 = getBrightnessAtEyes(f);
//        tessellator.color(f14 * red, f14 * green, f14 * blue);
//        tessellator.vertex(f11 - f1 * f10 - f4 * f10, f12 - f2 * f10, f13 - f3 * f10 - f5 * f10, f6, f9);
//        tessellator.vertex((f11 - f1 * f10) + f4 * f10, f12 + f2 * f10, (f13 - f3 * f10) + f5 * f10, f6, f8);
//        tessellator.vertex(f11 + f1 * f10 + f4 * f10, f12 + f2 * f10, f13 + f3 * f10 + f5 * f10, f7, f8);
//        tessellator.vertex((f11 + f1 * f10) - f4 * f10, f12 - f2 * f10, (f13 + f3 * f10) - f5 * f10, f7, f9);
//    }
}
