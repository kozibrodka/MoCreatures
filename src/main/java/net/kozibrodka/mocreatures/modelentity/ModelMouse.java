// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) braces deadcode 

package net.kozibrodka.mocreatures.modelentity;


import net.minecraft.client.model.ModelPart;
import net.minecraft.client.render.entity.model.EntityModel;
import net.minecraft.util.math.MathHelper;

public class ModelMouse extends EntityModel
{

    public ModelMouse()
    {
        Head = new ModelPart(0, 0);
        Head.addCuboid(-1.5F, -1F, -6F, 3, 4, 6, 0.0F);
        Head.setPivot(0.0F, 19F, -9F);
        EarR = new ModelPart(16, 26);
        EarR.addCuboid(-3.5F, -3F, -2F, 3, 3, 1, 0.0F);
        EarR.setPivot(0.0F, 19F, -9F);
        EarL = new ModelPart(24, 26);
        EarL.addCuboid(0.5F, -3F, -1F, 3, 3, 1, 0.0F);
        EarL.setPivot(0.0F, 19F, -10F);
        WhiskerR = new ModelPart(20, 20);
        WhiskerR.addCuboid(-4.5F, -1F, -7F, 3, 3, 1, 0.0F);
        WhiskerR.setPivot(0.0F, 19F, -9F);
        WhiskerL = new ModelPart(24, 20);
        WhiskerL.addCuboid(1.5F, -1F, -6F, 3, 3, 1, 0.0F);
        WhiskerL.setPivot(0.0F, 19F, -9F);
        Tail = new ModelPart(56, 0);
        Tail.addCuboid(-0.5F, 0.0F, -1F, 1, 14, 1, 0.0F);
        Tail.setPivot(0.0F, 20F, 3F);
        Tail.pitch = 1.570796F;
        FrontL = new ModelPart(0, 18);
        FrontL.addCuboid(-2F, 0.0F, -3F, 2, 1, 4, 0.0F);
        FrontL.setPivot(3F, 23F, -7F);
        FrontR = new ModelPart(0, 18);
        FrontR.addCuboid(0.0F, 0.0F, -3F, 2, 1, 4, 0.0F);
        FrontR.setPivot(-3F, 23F, -7F);
        RearL = new ModelPart(0, 18);
        RearL.addCuboid(-2F, 0.0F, -4F, 2, 1, 4, 0.0F);
        RearL.setPivot(4F, 23F, 2.0F);
        RearR = new ModelPart(0, 18);
        RearR.addCuboid(0.0F, 0.0F, -4F, 2, 1, 4, 0.0F);
        RearR.setPivot(-4F, 23F, 2.0F);
        BodyF = new ModelPart(20, 0);
        BodyF.addCuboid(-3F, -3F, -7F, 6, 6, 12, 0.0F);
        BodyF.setPivot(0.0F, 20F, -2F);
    }

    public void render(float f, float f1, float f2, float f3, float f4, float f5)
    {
        super.render(f, f1, f2, f3, f4, f5);
        ModelBase(f, f1, f2, f3, f4, f5);
        Head.render(f5);
        EarR.render(f5);
        EarL.render(f5);
        WhiskerR.render(f5);
        WhiskerL.render(f5);
        Tail.render(f5);
        FrontL.render(f5);
        FrontR.render(f5);
        RearL.render(f5);
        RearR.render(f5);
        BodyF.render(f5);
    }

    public void ModelBase(float f, float f1, float f2, float f3, float f4, float f5)
    {
        Head.pitch = -(f4 / 57.29578F);
        Head.yaw = f3 / 57.29578F;
        EarR.pitch = Head.pitch;
        EarR.yaw = Head.yaw;
        EarL.pitch = Head.pitch;
        EarL.yaw = Head.yaw;
        WhiskerR.pitch = Head.pitch;
        WhiskerR.yaw = Head.yaw;
        WhiskerL.pitch = Head.pitch;
        WhiskerL.yaw = Head.yaw;
        FrontL.pitch = MathHelper.cos(f * 0.6662F) * 0.6F * f1;
        RearL.pitch = MathHelper.cos(f * 0.6662F + 3.141593F) * 0.8F * f1;
        RearR.pitch = MathHelper.cos(f * 0.6662F) * 0.6F * f1;
        FrontR.pitch = MathHelper.cos(f * 0.6662F + 3.141593F) * 0.8F * f1;
        Tail.yaw = FrontL.pitch * 0.625F;
    }

    public ModelPart Head;
    public ModelPart EarR;
    public ModelPart EarL;
    public ModelPart WhiskerR;
    public ModelPart WhiskerL;
    public ModelPart Tail;
    public ModelPart FrontL;
    public ModelPart FrontR;
    public ModelPart RearL;
    public ModelPart RearR;
    public ModelPart BodyF;
}
