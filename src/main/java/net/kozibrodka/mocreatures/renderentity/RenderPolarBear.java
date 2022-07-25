package net.kozibrodka.mocreatures.renderentity;

import net.kozibrodka.mocreatures.entity.EntityBear;
import net.minecraft.client.render.entity.LivingEntityRenderer;
import net.minecraft.client.render.entity.model.EntityModelBase;
import net.minecraft.entity.Living;

public class RenderPolarBear extends LivingEntityRenderer
{

    public RenderPolarBear(EntityModelBase modelbase, EntityModelBase modelbase1, float f)
    {
        super(modelbase, f);
        setModel(modelbase1);
    }

    protected boolean a(EntityBear entitybear, int i)
    {
        bindTexture("/assets/mocreatures/stationapi/textures/mob/polarbearb.png");
        return i == 0 && !entitybear.bearboolean;
    }

    protected boolean render(Living entityliving, int i, float f)
    {
        return a((EntityBear)entityliving, i);
    }
}
