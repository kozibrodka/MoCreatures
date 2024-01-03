// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) braces deadcode 

package net.kozibrodka.mocreatures.modelentity;


import net.minecraft.client.model.ModelPart;
import net.minecraft.client.render.entity.model.EntityModel;
import net.minecraft.util.math.MathHelper;

public class ModelFox extends EntityModel
{

    public ModelFox()
    {
        byte byte0 = 8;
        Body = new ModelPart(0, 0);
        Body.addCuboid(0.0F, 0.0F, 0.0F, 6, 6, 12, 0.0F);
        Body.setPivot(-4F, 10F, -6F);
        Head = new ModelPart(0, 20);
        Head.addCuboid(-4F, -4F, -6F, 6, 6, 4, 0.0F);
        Head.setPivot(0.0F, 12F, -4F);
        Snout = new ModelPart(20, 20);
        Snout.addCuboid(-2F, 1.0F, -10F, 2, 2, 4, 0.0F);
        Snout.setPivot(0.0F, 11F, -3.5F);
        Ears = new ModelPart(50, 20);
        Ears.addCuboid(-4F, -4F, -6F, 6, 4, 1, 0.0F);
        Ears.setPivot(0.0F, 9F, -2F);
        Tail = new ModelPart(32, 20);
        Tail.addCuboid(-5F, -5F, -2F, 3, 3, 8, 0.0F);
        Tail.setPivot(2.5F, 15F, 5F);
        Tail.pitch = -0.5235988F;
        Leg1 = new ModelPart(0, 0);
        Leg1.addCuboid(-2F, 0.0F, -2F, 3, byte0, 3, 0.0F);
        Leg1.setPivot(-2F, 24 - byte0, 5F);
        Leg2 = new ModelPart(0, 0);
        Leg2.addCuboid(-2F, 0.0F, -2F, 3, byte0, 3, 0.0F);
        Leg2.setPivot(1.0F, 24 - byte0, 5F);
        Leg3 = new ModelPart(0, 0);
        Leg3.addCuboid(-2F, 0.0F, -2F, 3, byte0, 3, 0.0F);
        Leg3.setPivot(-2F, 24 - byte0, -4F);
        Leg4 = new ModelPart(0, 0);
        Leg4.addCuboid(-2F, 0.0F, -2F, 3, byte0, 3, 0.0F);
        Leg4.setPivot(1.0F, 24 - byte0, -4F);
    }

    public void render(float f, float f1, float f2, float f3, float f4, float f5)
    {
        setAngles(f, f1, f2, f3, f4, f5);
        Body.render(f5);
        Leg1.render(f5);
        Leg2.render(f5);
        Leg3.render(f5);
        Leg4.render(f5);
        Head.render(f5);
        Snout.render(f5);
        Tail.render(f5);
        Ears.render(f5);
    }

    public void setAngles(float f, float f1, float f2, float f3, float f4, float f5)
    {
        Head.yaw = f3 / 57.29578F;
        Head.pitch = f4 / 57.29578F;
        Snout.yaw = f3 / 57.29578F;
        Snout.pitch = f4 / 57.29578F;
        Snout.pivotX = 0.0F + (f3 / 57.29578F) * 0.8F;
        Ears.yaw = f3 / 57.29578F;
        Ears.pitch = f4 / 57.29578F;
        Ears.pivotX = 0.0F + (f3 / 57.29578F) * 2.5F;
        Leg1.pitch = MathHelper.cos(f * 0.6662F) * 1.4F * f1;
        Leg2.pitch = MathHelper.cos(f * 0.6662F + 3.141593F) * 1.4F * f1;
        Leg3.pitch = MathHelper.cos(f * 0.6662F + 3.141593F) * 1.4F * f1;
        Leg4.pitch = MathHelper.cos(f * 0.6662F) * 1.4F * f1;
    }

    public ModelPart Body;
    public ModelPart Leg1;
    public ModelPart Leg2;
    public ModelPart Leg3;
    public ModelPart Leg4;
    public ModelPart Snout;
    public ModelPart Head;
    public ModelPart Tail;
    public ModelPart Ears;
}
