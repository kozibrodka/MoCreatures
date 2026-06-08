// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) braces deadcode 

package net.kozibrodka.mocreatures.modelentity;


import net.minecraft.client.model.ModelPart;
import net.minecraft.client.render.entity.model.QuadrupedEntityModel;

public class ModelWolf2 extends QuadrupedEntityModel
{

    public ModelWolf2()
    {
        super(10, 0.0F);
        head = new ModelPart(0, 0);
        head.addCuboid(-4F, -2F, -6F, 8, 8, 6, 0.0F);
        head.setPivot(0.0F, 4F, -8F);
        snout = new ModelPart(8, 15);
        snout.addCuboid(-2F, 2.0F, -11F, 4, 4, 6, 0.0F);
        snout.setPivot(0.0F, 4F, -8F);
        body = new ModelPart(28, 6);
        body.addCuboid(-5F, -8F, -9F, 10, 16, 6, 0.0F);
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
