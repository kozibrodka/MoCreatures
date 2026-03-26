package net.kozibrodka.mocreatures.renderentity;

import net.kozibrodka.mocreatures.entity.EntityBear;
import net.minecraft.client.render.entity.LivingEntityRenderer;
import net.minecraft.client.render.entity.model.EntityModel;
import net.minecraft.entity.LivingEntity;

public class RenderBear extends LivingEntityRenderer
{

    public RenderBear(EntityModel modelbase, EntityModel modelbase1, float f)
    {
        super(modelbase, f);
        setDecorationModel(modelbase1);
    }

    protected boolean c(EntityBear entitybear, int i)
    {
        bindTexture("/assets/mocreatures/stationapi/textures/mob/bearb.png");
        return i == 0;
    }

    protected boolean bindTexture(LivingEntity entityliving, int i, float f)
    {
        return c((EntityBear)entityliving, i);
    }
}
