// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) braces deadcode 

package net.kozibrodka.mocreatures.modelentity;


import net.minecraft.client.model.ModelPart;
import net.minecraft.client.render.entity.model.EntityModel;
import net.minecraft.util.math.MathHelper;

public class ModelDolphin extends EntityModel
{

    public ModelDolphin()
    {
        Body = new ModelPart(4, 6);
        Body.addCuboid(0.0F, 0.0F, 0.0F, 6, 8, 18, 0.0F);
        Body.setPivot(-4F, 17F, -10F);
        UHead = new ModelPart(0, 0);
        UHead.addCuboid(0.0F, 0.0F, 0.0F, 5, 7, 8, 0.0F);
        UHead.setPivot(-3.5F, 18F, -16.5F);
        DHead = new ModelPart(50, 0);
        DHead.addCuboid(0.0F, 0.0F, 0.0F, 3, 3, 4, 0.0F);
        DHead.setPivot(-2.5F, 21.5F, -20.5F);
        PTail = new ModelPart(34, 9);
        PTail.addCuboid(0.0F, 0.0F, 0.0F, 5, 5, 10, 0.0F);
        PTail.setPivot(-3.5F, 19F, 8F);
        UpperFin = new ModelPart(4, 12);
        UpperFin.addCuboid(0.0F, 0.0F, 0.0F, 1, 4, 8, 0.0F);
        UpperFin.setPivot(-1.5F, 18F, -4F);
        UpperFin.pitch = 0.7853981F;
        LTailFin = new ModelPart(34, 0);
        LTailFin.addCuboid(0.0F, 0.0F, 0.0F, 4, 1, 8, 0.3F);
        LTailFin.setPivot(-2F, 21.5F, 18F);
        LTailFin.yaw = 0.7853981F;
        RTailFin = new ModelPart(34, 0);
        RTailFin.addCuboid(0.0F, 0.0F, 0.0F, 4, 1, 8, 0.3F);
        RTailFin.setPivot(-3F, 21.5F, 15F);
        RTailFin.yaw = -0.7853981F;
        LeftFin = new ModelPart(14, 0);
        LeftFin.addCuboid(0.0F, 0.0F, 0.0F, 8, 1, 4, 0.0F);
        LeftFin.setPivot(2.0F, 24F, -7F);
        LeftFin.yaw = -0.5235988F;
        LeftFin.roll = 0.5235988F;
        RightFin = new ModelPart(14, 0);
        RightFin.addCuboid(0.0F, 0.0F, 0.0F, 8, 1, 4, 0.0F);
        RightFin.setPivot(-10F, 27.5F, -3F);
        RightFin.yaw = 0.5235988F;
        RightFin.roll = -0.5235988F;
    }

    public void render(float f, float f1, float f2, float f3, float f4, float f5)
    {
        setAngles(f, f1, f2, f3, f4, f5);
        Body.render(f5);
        PTail.render(f5);
        UHead.render(f5);
        DHead.render(f5);
        UpperFin.render(f5);
        LTailFin.render(f5);
        RTailFin.render(f5);
        LeftFin.render(f5);
        RightFin.render(f5);
    }

    public void setAngles(float f, float f1, float f2, float f3, float f4, float f5)
    {
        RTailFin.pitch = MathHelper.cos(f * 0.6662F) * f1;
        LTailFin.pitch = MathHelper.cos(f * 0.6662F) * f1;
    }

    public ModelPart UHead;
    public ModelPart DHead;
    public ModelPart RTail;
    public ModelPart LTail;
    public ModelPart PTail;
    public ModelPart Body;
    public ModelPart UpperFin;
    public ModelPart RTailFin;
    public ModelPart LTailFin;
    public ModelPart LowerFin;
    public ModelPart RightFin;
    public ModelPart LeftFin;
}
