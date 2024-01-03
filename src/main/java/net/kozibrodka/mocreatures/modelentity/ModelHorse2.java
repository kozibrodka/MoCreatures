// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) braces deadcode 

package net.kozibrodka.mocreatures.modelentity;


import net.minecraft.client.model.ModelPart;
import net.minecraft.client.render.entity.model.EntityModel;
import net.minecraft.util.math.MathHelper;

public class ModelHorse2 extends EntityModel
{

    public ModelHorse2()
    {
        Body = new ModelPart(0, 0);
        Body.addCuboid(-5F, 0.0F, -10F, 10, 10, 20, 0.0F);
        Body.setPivot(0.0F, 2.0F, 0.0F);
        Tail = new ModelPart(40, 0);
        Tail.addCuboid(-2F, 0.0F, -2F, 4, 12, 4, 0.0F);
        Tail.setPivot(0.0F, 2.0F, 8F);
        Tail.pitch = 0.5235988F;
        Leg1 = new ModelPart(0, 0);
        Leg1.addCuboid(-2F, 0.0F, -2F, 4, 12, 4, 0.0F);
        Leg1.setPivot(3F, 12F, 8F);
        Leg2 = new ModelPart(0, 0);
        Leg2.addCuboid(-2F, 0.0F, -2F, 4, 12, 4, 0.0F);
        Leg2.setPivot(-3F, 12F, 8F);
        Leg3 = new ModelPart(0, 0);
        Leg3.addCuboid(-2F, 0.0F, -2F, 4, 12, 4, 0.0F);
        Leg3.setPivot(3F, 12F, -8F);
        Leg4 = new ModelPart(0, 0);
        Leg4.addCuboid(-2F, 0.0F, -2F, 4, 12, 4, 0.0F);
        Leg4.setPivot(-3F, 12F, -8F);
    }

    public void render(float f, float f1, float f2, float f3, float f4, float f5)
    {
        setAngles(f, f1, f2, f3, f4, f5);
        Body.render(f5);
        Tail.render(f5);
        Leg1.render(f5);
        Leg2.render(f5);
        Leg3.render(f5);
        Leg4.render(f5);
    }

    public void setAngles(float f, float f1, float f2, float f3, float f4, float f5)
    {
        Leg1.pitch = MathHelper.cos(f * 0.6662F) * 1.4F * f1;
        Leg2.pitch = MathHelper.cos(f * 0.6662F + 3.141593F) * 1.4F * f1;
        Leg3.pitch = MathHelper.cos(f * 0.6662F + 3.141593F) * 1.4F * f1;
        Leg4.pitch = MathHelper.cos(f * 0.6662F) * 1.4F * f1;
    }

    public ModelPart Body;
    public ModelPart Tail;
    public ModelPart Leg1;
    public ModelPart Leg2;
    public ModelPart Leg3;
    public ModelPart Leg4;
}
