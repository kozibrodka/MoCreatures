// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) braces deadcode 

package net.kozibrodka.mocreatures.modelentity;

import net.minecraft.client.model.ModelPart;
import net.minecraft.client.render.entity.model.QuadrupedEntityModel;

public class ModelBear1 extends QuadrupedEntityModel
{

    public ModelBear1()
    {
        super(12, 0.0F);
        head = new ModelPart(0, 0);
        head.addCuboid(-5F, -8F, -2F, 10, 4, 1, 0.0F);
        head.setPivot(0.0F, 4F, -8F);
        body = new ModelPart(20, 0);
        body.addCuboid(-2F, 9F, -4F, 4, 2, 4, 0.0F);
        body.setPivot(0.0F, 5F, 2.0F);
    }
}
