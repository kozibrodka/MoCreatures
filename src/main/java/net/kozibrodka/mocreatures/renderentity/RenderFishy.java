package net.kozibrodka.mocreatures.renderentity;

import net.kozibrodka.mocreatures.entity.EntityFishy;
import net.minecraft.client.render.entity.LivingEntityRenderer;
import net.minecraft.client.render.entity.model.EntityModel;
import net.minecraft.entity.LivingEntity;
import org.lwjgl.opengl.GL11;


public class RenderFishy extends LivingEntityRenderer
{

    public RenderFishy(EntityModel modelbase, float f)
    {
        super(modelbase, f);
    }

    public void render(LivingEntity entityliving, double d, double d1, double d2,
                               float f, float f1)
    {
        EntityFishy entityfishy = (EntityFishy)entityliving;
        super.render(entityfishy, d, d1, d2, f, f1);
    }

    protected void stretch(EntityFishy entityfishy)
    {
        GL11.glScalef(entityfishy.getAge(), entityfishy.getAge(), entityfishy.getAge());
    }

    protected float getHeadBob(LivingEntity entityliving, float f)
    {
        EntityFishy entityfishy = (EntityFishy)entityliving;
        if(!entityfishy.getAdult())
        {
            stretch(entityfishy);
        }
        return (float)entityliving.age + f;
    }
}
