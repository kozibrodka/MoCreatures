// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) braces deadcode 

package net.kozibrodka.mocreatures.modelentity;


import net.minecraft.client.model.Cuboid;
import net.minecraft.client.render.entity.model.EntityModelBase;
import net.minecraft.util.maths.MathHelper;

public class ModelMouse extends EntityModelBase
{

    public ModelMouse()
    {
        Head = new Cuboid(0, 0);
        Head.method_1818(-1.5F, -1F, -6F, 3, 4, 6, 0.0F);
        Head.setRotationPoint(0.0F, 19F, -9F);
        EarR = new Cuboid(16, 26);
        EarR.method_1818(-3.5F, -3F, -2F, 3, 3, 1, 0.0F);
        EarR.setRotationPoint(0.0F, 19F, -9F);
        EarL = new Cuboid(24, 26);
        EarL.method_1818(0.5F, -3F, -1F, 3, 3, 1, 0.0F);
        EarL.setRotationPoint(0.0F, 19F, -10F);
        WhiskerR = new Cuboid(20, 20);
        WhiskerR.method_1818(-4.5F, -1F, -7F, 3, 3, 1, 0.0F);
        WhiskerR.setRotationPoint(0.0F, 19F, -9F);
        WhiskerL = new Cuboid(24, 20);
        WhiskerL.method_1818(1.5F, -1F, -6F, 3, 3, 1, 0.0F);
        WhiskerL.setRotationPoint(0.0F, 19F, -9F);
        Tail = new Cuboid(56, 0);
        Tail.method_1818(-0.5F, 0.0F, -1F, 1, 14, 1, 0.0F);
        Tail.setRotationPoint(0.0F, 20F, 3F);
        Tail.pitch = 1.570796F;
        FrontL = new Cuboid(0, 18);
        FrontL.method_1818(-2F, 0.0F, -3F, 2, 1, 4, 0.0F);
        FrontL.setRotationPoint(3F, 23F, -7F);
        FrontR = new Cuboid(0, 18);
        FrontR.method_1818(0.0F, 0.0F, -3F, 2, 1, 4, 0.0F);
        FrontR.setRotationPoint(-3F, 23F, -7F);
        RearL = new Cuboid(0, 18);
        RearL.method_1818(-2F, 0.0F, -4F, 2, 1, 4, 0.0F);
        RearL.setRotationPoint(4F, 23F, 2.0F);
        RearR = new Cuboid(0, 18);
        RearR.method_1818(0.0F, 0.0F, -4F, 2, 1, 4, 0.0F);
        RearR.setRotationPoint(-4F, 23F, 2.0F);
        BodyF = new Cuboid(20, 0);
        BodyF.method_1818(-3F, -3F, -7F, 6, 6, 12, 0.0F);
        BodyF.setRotationPoint(0.0F, 20F, -2F);
    }

    public void render(float f, float f1, float f2, float f3, float f4, float f5)
    {
        super.render(f, f1, f2, f3, f4, f5);
        ModelBase(f, f1, f2, f3, f4, f5);
        Head.method_1815(f5);
        EarR.method_1815(f5);
        EarL.method_1815(f5);
        WhiskerR.method_1815(f5);
        WhiskerL.method_1815(f5);
        Tail.method_1815(f5);
        FrontL.method_1815(f5);
        FrontR.method_1815(f5);
        RearL.method_1815(f5);
        RearR.method_1815(f5);
        BodyF.method_1815(f5);
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

    public Cuboid Head;
    public Cuboid EarR;
    public Cuboid EarL;
    public Cuboid WhiskerR;
    public Cuboid WhiskerL;
    public Cuboid Tail;
    public Cuboid FrontL;
    public Cuboid FrontR;
    public Cuboid RearL;
    public Cuboid RearR;
    public Cuboid BodyF;
}
