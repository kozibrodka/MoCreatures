// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) braces deadcode 

package net.kozibrodka.mocreatures.modelentity;

import net.minecraft.client.model.ModelPart;
import net.minecraft.client.render.entity.model.QuadrupedEntityModel;

public class ModelBigCat1 extends QuadrupedEntityModel
{

    public ModelBigCat1()
    {
        super(12, 0.0F);
        head = new ModelPart(20, 0);
        head.addCuboid(-7F, -8F, -2F, 14, 14, 8, 0.0F);
        head.setPivot(0.0F, 4F, -8F);
        body = new ModelPart(20, 0);
        body.addCuboid(-6F, -11F, -8F, 12, 10, 10, 0.0F);
        body.setPivot(0.0F, 5F, 2.0F);
    }
}
