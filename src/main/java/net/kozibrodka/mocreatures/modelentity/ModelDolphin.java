// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) braces deadcode 

package net.kozibrodka.mocreatures.modelentity;


import net.minecraft.client.model.Cuboid;
import net.minecraft.client.render.entity.model.EntityModelBase;
import net.minecraft.util.maths.MathHelper;

public class ModelDolphin extends EntityModelBase
{

    public ModelDolphin()
    {
        Body = new Cuboid(4, 6);
        Body.method_1818(0.0F, 0.0F, 0.0F, 6, 8, 18, 0.0F);
        Body.setRotationPoint(-4F, 17F, -10F);
        UHead = new Cuboid(0, 0);
        UHead.method_1818(0.0F, 0.0F, 0.0F, 5, 7, 8, 0.0F);
        UHead.setRotationPoint(-3.5F, 18F, -16.5F);
        DHead = new Cuboid(50, 0);
        DHead.method_1818(0.0F, 0.0F, 0.0F, 3, 3, 4, 0.0F);
        DHead.setRotationPoint(-2.5F, 21.5F, -20.5F);
        PTail = new Cuboid(34, 9);
        PTail.method_1818(0.0F, 0.0F, 0.0F, 5, 5, 10, 0.0F);
        PTail.setRotationPoint(-3.5F, 19F, 8F);
        UpperFin = new Cuboid(4, 12);
        UpperFin.method_1818(0.0F, 0.0F, 0.0F, 1, 4, 8, 0.0F);
        UpperFin.setRotationPoint(-1.5F, 18F, -4F);
        UpperFin.pitch = 0.7853981F;
        LTailFin = new Cuboid(34, 0);
        LTailFin.method_1818(0.0F, 0.0F, 0.0F, 4, 1, 8, 0.3F);
        LTailFin.setRotationPoint(-2F, 21.5F, 18F);
        LTailFin.yaw = 0.7853981F;
        RTailFin = new Cuboid(34, 0);
        RTailFin.method_1818(0.0F, 0.0F, 0.0F, 4, 1, 8, 0.3F);
        RTailFin.setRotationPoint(-3F, 21.5F, 15F);
        RTailFin.yaw = -0.7853981F;
        LeftFin = new Cuboid(14, 0);
        LeftFin.method_1818(0.0F, 0.0F, 0.0F, 8, 1, 4, 0.0F);
        LeftFin.setRotationPoint(2.0F, 24F, -7F);
        LeftFin.yaw = -0.5235988F;
        LeftFin.roll = 0.5235988F;
        RightFin = new Cuboid(14, 0);
        RightFin.method_1818(0.0F, 0.0F, 0.0F, 8, 1, 4, 0.0F);
        RightFin.setRotationPoint(-10F, 27.5F, -3F);
        RightFin.yaw = 0.5235988F;
        RightFin.roll = -0.5235988F;
    }

    public void render(float f, float f1, float f2, float f3, float f4, float f5)
    {
        setAngles(f, f1, f2, f3, f4, f5);
        Body.method_1815(f5);
        PTail.method_1815(f5);
        UHead.method_1815(f5);
        DHead.method_1815(f5);
        UpperFin.method_1815(f5);
        LTailFin.method_1815(f5);
        RTailFin.method_1815(f5);
        LeftFin.method_1815(f5);
        RightFin.method_1815(f5);
    }

    public void setAngles(float f, float f1, float f2, float f3, float f4, float f5)
    {
        RTailFin.pitch = MathHelper.cos(f * 0.6662F) * f1;
        LTailFin.pitch = MathHelper.cos(f * 0.6662F) * f1;
    }

    public Cuboid UHead;
    public Cuboid DHead;
    public Cuboid RTail;
    public Cuboid LTail;
    public Cuboid PTail;
    public Cuboid Body;
    public Cuboid UpperFin;
    public Cuboid RTailFin;
    public Cuboid LTailFin;
    public Cuboid LowerFin;
    public Cuboid RightFin;
    public Cuboid LeftFin;
}
