// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) braces deadcode 

package net.kozibrodka.mocreatures.modelentity;


import net.minecraft.client.model.ModelPart;
import net.minecraft.client.render.entity.model.QuadrupedEntityModel;

public class ModelWolf1 extends QuadrupedEntityModel
{

    public ModelWolf1()
    {
        super(10, 0.0F);
        head = new ModelPart(12, 0);
        head.addCuboid(-5F, -5F, -2F, 10, 14, 3, 0.0F);
        head.setPivot(0.0F, 4F, -8F);
        body = new ModelPart(24, 16);
        body.addCuboid(-6F, -11F, -10F, 12, 8, 8, 0.0F);
        body.setPivot(0.0F, 5F, 2.0F);
    }
}
