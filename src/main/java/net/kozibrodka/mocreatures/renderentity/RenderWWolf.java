package net.kozibrodka.mocreatures.renderentity;

import net.kozibrodka.mocreatures.entity.EntityWWolf;
import net.minecraft.client.render.entity.LivingEntityRenderer;
import net.minecraft.client.render.entity.model.EntityModel;
import net.minecraft.entity.LivingEntity;

public class RenderWWolf extends LivingEntityRenderer
{

    public RenderWWolf(EntityModel modelbase, EntityModel modelbase1, float f)
    {
        super(modelbase, f);
        setDecorationModel(modelbase1);
    }

    protected boolean a(EntityWWolf entitywwolf, int i)
    {
        bindTexture("/assets/mocreatures/stationapi/textures/mob/wolfb.png");
        return i == 0 && !entitywwolf.wolfboolean;
    }

    protected boolean bindTexture(LivingEntity entityliving, int i, float f)
    {
        return a((EntityWWolf)entityliving, i);
    }
}
