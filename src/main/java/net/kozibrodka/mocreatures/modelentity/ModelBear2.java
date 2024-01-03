// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) braces deadcode 

package net.kozibrodka.mocreatures.modelentity;

import net.minecraft.client.model.ModelPart;
import net.minecraft.client.render.entity.model.QuadrupedEntityModel;

public class ModelBear2 extends QuadrupedEntityModel
{

    public ModelBear2()
    {
        super(12, 0.8F);
        head = new ModelPart(0, 0);
        head.addCuboid(-4F, -4F, -6F, 8, 8, 6, 0.0F);
        head.setPivot(0.0F, 4F, -8F);
        b = new ModelPart(23, 0);
        b.addCuboid(-2F, 0.0F, -9F, 4, 4, 6, 0.0F);
        b.setPivot(0.0F, 4F, -8F);
        body = new ModelPart(32, 10);
        body.addCuboid(-4F, -8F, -5F, 8, 14, 8, 3F);
        body.setPivot(0.0F, 5F, 2.0F);
    }

    public void render(float f, float f1, float f2, float f3, float f4, float f5)
    {
        super.render(f, f1, f2, f3, f4, f5);
        b.render(f5);
    }

    public void setAngles(float f, float f1, float f2, float f3, float f4, float f5)
    {
        super.setAngles(f, f1, f2, f3, f4, f5);
        b.yaw = head.yaw;
        b.pitch = head.pitch;
    }

    ModelPart b;
}
