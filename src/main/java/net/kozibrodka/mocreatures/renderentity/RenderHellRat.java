package net.kozibrodka.mocreatures.renderentity;

import net.minecraft.client.render.entity.model.EntityModel;
import net.minecraft.entity.LivingEntity;
import org.lwjgl.opengl.GL11;


public class RenderHellRat extends RenderRat
{

    public RenderHellRat(EntityModel modelbase, float f)
    {
        super(modelbase, f);
    }

    protected void stretch(LivingEntity entityliving)
    {
        float f = 1.3F;
        GL11.glScalef(f, f, f);
    }
}
