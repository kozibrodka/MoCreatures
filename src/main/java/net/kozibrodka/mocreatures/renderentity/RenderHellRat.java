package net.kozibrodka.mocreatures.renderentity;

import net.minecraft.client.render.entity.model.EntityModelBase;
import net.minecraft.entity.Living;
import org.lwjgl.opengl.GL11;


public class RenderHellRat extends RenderRat
{

    public RenderHellRat(EntityModelBase modelbase, float f)
    {
        super(modelbase, f);
    }

    protected void stretch(Living entityliving)
    {
        float f = 1.3F;
        GL11.glScalef(f, f, f);
    }
}
