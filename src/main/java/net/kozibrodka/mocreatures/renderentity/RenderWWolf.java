package net.kozibrodka.mocreatures.renderentity;

import net.kozibrodka.mocreatures.entity.EntityWWolf;
import net.minecraft.client.render.entity.LivingEntityRenderer;
import net.minecraft.client.render.entity.model.EntityModelBase;
import net.minecraft.entity.Living;

public class RenderWWolf extends LivingEntityRenderer
{

    public RenderWWolf(EntityModelBase modelbase, EntityModelBase modelbase1, float f)
    {
        super(modelbase, f);
        setModel(modelbase1);
    }

    protected boolean a(EntityWWolf entitywwolf, int i)
    {
        bindTexture("/assets/mocreatures/stationapi/textures/mob/wolfb.png");
        return i == 0 && !entitywwolf.wolfboolean;
    }

    protected boolean render(Living entityliving, int i, float f)
    {
        return a((EntityWWolf)entityliving, i);
    }
}
