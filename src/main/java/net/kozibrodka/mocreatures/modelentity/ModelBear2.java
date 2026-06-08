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
        snout = new ModelPart(28, 0); /// move 23->28 to fix back of head
        snout.addCuboid(-2F, 0.0F, -9F, 4, 4, 6, 0.0F);
        snout.setPivot(0.0F, 4F, -8F);
        body = new ModelPart(32, 10);
        body.addCuboid(-4F, -8F, -5F, 8, 14, 8, 3F);
        body.setPivot(0.0F, 5F, 2.0F);
    }

    @Override
    public void render(float f, float f1, float f2, float f3, float f4, float f5)
    {
        super.render(f, f1, f2, f3, f4, f5);
        snout.render(f5);
    }

    @Override
    public void setAngles(float f, float f1, float f2, float f3, float f4, float f5)
    {
        super.setAngles(f, f1, f2, f3, f4, f5);
        snout.yaw = head.yaw;
        snout.pitch = head.pitch;
    }

    ModelPart snout;
}
