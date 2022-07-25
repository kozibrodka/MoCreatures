package net.kozibrodka.mocreatures.renderentity;

import net.kozibrodka.mocreatures.entity.EntityBird;
import net.minecraft.client.render.entity.LivingEntityRenderer;
import net.minecraft.client.render.entity.model.EntityModelBase;
import net.minecraft.entity.Living;
import net.minecraft.util.maths.MathHelper;

public class RenderBird extends LivingEntityRenderer
{

    public RenderBird(EntityModelBase modelbase, float f)
    {
        super(modelbase, f);
    }

    public void method_822(Living entityliving, double d, double d1, double d2,
                               float f, float f1)
    {
        EntityBird entitybird = (EntityBird)entityliving;
        if(!entitybird.typechosen)
        {
            entitybird.chooseType();
        }
        super.method_822(entityliving, d, d1, d2, f, f1);
    }

    protected float method_828(Living entityliving, float f)
    {
        EntityBird entitybird = (EntityBird)entityliving;
        float f1 = entitybird.winge + (entitybird.wingb - entitybird.winge) * f;
        float f2 = entitybird.wingd + (entitybird.wingc - entitybird.wingd) * f;
        return (MathHelper.sin(f1) + 1.0F) * f2;
    }
}
