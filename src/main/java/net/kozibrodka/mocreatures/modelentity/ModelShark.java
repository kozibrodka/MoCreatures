// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) braces deadcode 

package net.kozibrodka.mocreatures.modelentity;



import net.minecraft.client.model.ModelPart;
import net.minecraft.client.render.entity.model.EntityModel;
import net.minecraft.util.math.MathHelper;

public class ModelShark extends EntityModel
{

    public ModelShark()
    {
        Body = new ModelPart(6, 6);
        Body.addCuboid(0.0F, 0.0F, 0.0F, 6, 8, 18, 0.0F);
        Body.setPivot(-4F, 17F, -10F);
        UHead = new ModelPart(0, 0);
        UHead.addCuboid(0.0F, 0.0F, 0.0F, 5, 2, 8, 0.0F);
        UHead.setPivot(-3.5F, 21F, -16.5F);
        UHead.pitch = 0.5235988F;
        DHead = new ModelPart(44, 0);
        DHead.addCuboid(0.0F, 0.0F, 0.0F, 5, 2, 5, 0.0F);
        DHead.setPivot(-3.5F, 21.5F, -13.5F);
        DHead.pitch = -0.261799F;
        RHead = new ModelPart(0, 3);
        RHead.addCuboid(0.0F, 0.0F, 0.0F, 1, 6, 6, 0.0F);
        RHead.setPivot(-3.45F, 21.3F, -13.85F);
        RHead.pitch = 0.7853981F;
        LHead = new ModelPart(0, 3);
        LHead.addCuboid(0.0F, 0.0F, 0.0F, 1, 6, 6, 0.0F);
        LHead.setPivot(0.45F, 21.3F, -13.8F);
        LHead.pitch = 0.7853981F;
        PTail = new ModelPart(36, 8);
        PTail.addCuboid(0.0F, 0.0F, 0.0F, 4, 6, 10, 0.0F);
        PTail.setPivot(-3F, 18F, 8F);
        UpperFin = new ModelPart(6, 12);
        UpperFin.addCuboid(0.0F, 0.0F, 0.0F, 1, 4, 8, 0.0F);
        UpperFin.setPivot(-1.5F, 17F, -1F);
        UpperFin.pitch = 0.7853981F;
        UpperTailFin = new ModelPart(6, 12);
        UpperTailFin.addCuboid(0.0F, 0.0F, 0.0F, 1, 4, 8, 0.0F);
        UpperTailFin.setPivot(-1.5F, 18F, 16F);
        UpperTailFin.pitch = 0.5235988F;
        LowerTailFin = new ModelPart(8, 14);
        LowerTailFin.addCuboid(0.0F, 0.0F, 0.0F, 1, 4, 6, 0.0F);
        LowerTailFin.setPivot(-1.5F, 21F, 18F);
        LowerTailFin.pitch = -0.7853981F;
        LeftFin = new ModelPart(18, 0);
        LeftFin.addCuboid(0.0F, 0.0F, 0.0F, 8, 1, 4, 0.0F);
        LeftFin.setPivot(2.0F, 24F, -5F);
        LeftFin.yaw = -0.5235988F;
        LeftFin.roll = 0.5235988F;
        RightFin = new ModelPart(18, 0);
        RightFin.addCuboid(0.0F, 0.0F, 0.0F, 8, 1, 4, 0.0F);
        RightFin.setPivot(-10F, 27.5F, -1F);
        RightFin.yaw = 0.5235988F;
        RightFin.roll = -0.5235988F;
    }

    public void render(float f, float f1, float f2, float f3, float f4, float f5)
    {
        ModelBase(f, f1, f2, f3, f4, f5);
        Body.render(f5);
        PTail.render(f5);
        UHead.render(f5);
        DHead.render(f5);
        RHead.render(f5);
        LHead.render(f5);
        UpperFin.render(f5);
        UpperTailFin.render(f5);
        LowerTailFin.render(f5);
        LeftFin.render(f5);
        RightFin.render(f5);
    }

    public void ModelBase(float f, float f1, float f2, float f3, float f4, float f5)
    {
        UpperTailFin.yaw = MathHelper.cos(f * 0.6662F) * f1;
        LowerTailFin.yaw = MathHelper.cos(f * 0.6662F) * f1;
    }

    public ModelPart LHead;
    public ModelPart RHead;
    public ModelPart UHead;
    public ModelPart DHead;
    public ModelPart RTail;
    public ModelPart LTail;
    public ModelPart PTail;
    public ModelPart Body;
    public ModelPart UpperFin;
    public ModelPart UpperTailFin;
    public ModelPart LowerTailFin;
    public ModelPart RightFin;
    public ModelPart LeftFin;
}
