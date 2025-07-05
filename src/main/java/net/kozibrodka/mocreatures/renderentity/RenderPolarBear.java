package net.kozibrodka.mocreatures.renderentity;

import net.kozibrodka.mocreatures.entity.EntityBear;
import net.minecraft.client.render.entity.LivingEntityRenderer;
import net.minecraft.client.render.entity.model.EntityModel;
import net.minecraft.entity.LivingEntity;

public class RenderPolarBear extends LivingEntityRenderer
{

    public RenderPolarBear(EntityModel modelbase, EntityModel modelbase1, float f)
    {
        super(modelbase, f);
        setDecorationModel(modelbase1);
    }

    protected boolean a(EntityBear entitybear, int i)
    {
        bindTexture("/assets/mocreatures/stationapi/textures/mob/polarbearb.png");
        return i == 0 && !entitybear.bearboolean;
    }

    protected boolean bindTexture(LivingEntity entityliving, int i, float f)
    {
        return a((EntityBear)entityliving, i);
    }
}
