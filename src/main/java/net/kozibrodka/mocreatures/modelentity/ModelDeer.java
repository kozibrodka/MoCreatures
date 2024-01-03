// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) braces deadcode 

package net.kozibrodka.mocreatures.modelentity;

import net.minecraft.client.model.ModelPart;
import net.minecraft.client.render.entity.model.EntityModel;
import net.minecraft.util.math.MathHelper;

public class ModelDeer extends EntityModel
{

    public ModelDeer()
    {
        Head = new ModelPart(0, 0);
        Head.addCuboid(-1.5F, -6F, -9.5F, 3, 3, 6, 0.0F);
        Head.setPivot(1.0F, 11.5F, -4.5F);
        Neck = new ModelPart(0, 9);
        Neck.addCuboid(-2F, -2F, -6F, 4, 4, 6, 0.0F);
        Neck.setPivot(1.0F, 11.5F, -4.5F);
        Neck.pitch = -0.7853981F;
        LEar = new ModelPart(0, 0);
        LEar.addCuboid(-4F, -7.5F, -5F, 2, 3, 1, 0.0F);
        LEar.setPivot(1.0F, 11.5F, -4.5F);
        LEar.roll = 0.7853981F;
        REar = new ModelPart(0, 0);
        REar.addCuboid(2.0F, -7.5F, -5F, 2, 3, 1, 0.0F);
        REar.setPivot(1.0F, 11.5F, -4.5F);
        REar.roll = -0.7853981F;
        LeftAntler = new ModelPart(54, 0);
        LeftAntler.addCuboid(0.0F, -14F, -7F, 1, 8, 4, 0.0F);
        LeftAntler.setPivot(1.0F, 11.5F, -4.5F);
        LeftAntler.roll = 0.2094395F;
        RightAntler = new ModelPart(54, 0);
        RightAntler.addCuboid(0.0F, -14F, -7F, 1, 8, 4, 0.0F);
        RightAntler.setPivot(1.0F, 11.5F, -4.5F);
        RightAntler.roll = -0.2094395F;
        Body = new ModelPart(24, 12);
        Body.addCuboid(-2F, -3F, -6F, 6, 6, 14, 0.0F);
        Body.setPivot(0.0F, 13F, 0.0F);
        Leg1 = new ModelPart(9, 20);
        Leg1.addCuboid(-1F, 0.0F, -1F, 2, 8, 2, 0.0F);
        Leg1.setPivot(3F, 16F, -4F);
        Leg2 = new ModelPart(0, 20);
        Leg2.addCuboid(-1F, 0.0F, -1F, 2, 8, 2, 0.0F);
        Leg2.setPivot(-1F, 16F, -4F);
        Leg3 = new ModelPart(9, 20);
        Leg3.addCuboid(-1F, 0.0F, -1F, 2, 8, 2, 0.0F);
        Leg3.setPivot(3F, 16F, 6F);
        Leg4 = new ModelPart(0, 20);
        Leg4.addCuboid(-1F, 0.0F, -1F, 2, 8, 2, 0.0F);
        Leg4.setPivot(-1F, 16F, 6F);
        Tail = new ModelPart(50, 20);
        Tail.addCuboid(-1.5F, -1F, 0.0F, 3, 2, 4, 0.0F);
        Tail.setPivot(1.0F, 11F, 7F);
        Tail.pitch = 0.7854F;
    }

    public void render(float f, float f1, float f2, float f3, float f4, float f5)
    {
        setAngles(f, f1, f2, f3, f4, f5);
        Body.render(f5);
        Neck.render(f5);
        Head.render(f5);
        Leg1.render(f5);
        Leg2.render(f5);
        Leg3.render(f5);
        Leg4.render(f5);
        Tail.render(f5);
        LEar.render(f5);
        REar.render(f5);
        LeftAntler.render(f5);
        RightAntler.render(f5);
    }

    public void setAngles(float f, float f1, float f2, float f3, float f4, float f5)
    {
        Leg1.pitch = MathHelper.cos(f * 0.6662F) * 1.4F * f1;
        Leg2.pitch = MathHelper.cos(f * 0.6662F + 3.141593F) * 1.4F * f1;
        Leg3.pitch = MathHelper.cos(f * 0.6662F + 3.141593F) * 1.4F * f1;
        Leg4.pitch = MathHelper.cos(f * 0.6662F) * 1.4F * f1;
    }

    public ModelPart Body;
    public ModelPart Neck;
    public ModelPart Head;
    public ModelPart Leg1;
    public ModelPart Leg2;
    public ModelPart Leg3;
    public ModelPart Leg4;
    public ModelPart Tail;
    public ModelPart LEar;
    public ModelPart REar;
    public ModelPart LeftAntler;
    public ModelPart RightAntler;
}
