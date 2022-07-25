// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) braces deadcode 

package net.kozibrodka.mocreatures.renderentity;


// Referenced classes of package net.minecraft.src:
//            RenderLiving, EntityBear, ModelBase, EntityLiving

import net.kozibrodka.mocreatures.entity.EntityBear;
import net.minecraft.client.render.entity.LivingEntityRenderer;
import net.minecraft.client.render.entity.model.EntityModelBase;
import net.minecraft.entity.Living;

public class RenderBear extends LivingEntityRenderer
{

    public RenderBear(EntityModelBase modelbase, EntityModelBase modelbase1, float f)
    {
        super(modelbase, f);
        setModel(modelbase1);
    }

    protected boolean c(EntityBear entitybear, int i)
    {
        bindTexture("/assets/mocreatures/stationapi/textures/mob/bearb.png");
        return i == 0 && !entitybear.bearboolean;
    }

    protected boolean render(Living entityliving, int i, float f)
    {
        return c((EntityBear)entityliving, i);
    }
}
