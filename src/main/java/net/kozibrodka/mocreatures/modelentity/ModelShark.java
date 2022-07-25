// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) braces deadcode 

package net.kozibrodka.mocreatures.modelentity;



import net.minecraft.client.model.Cuboid;
import net.minecraft.client.render.entity.model.EntityModelBase;
import net.minecraft.util.maths.MathHelper;

public class ModelShark extends EntityModelBase
{

    public ModelShark()
    {
        Body = new Cuboid(6, 6);
        Body.method_1818(0.0F, 0.0F, 0.0F, 6, 8, 18, 0.0F);
        Body.setRotationPoint(-4F, 17F, -10F);
        UHead = new Cuboid(0, 0);
        UHead.method_1818(0.0F, 0.0F, 0.0F, 5, 2, 8, 0.0F);
        UHead.setRotationPoint(-3.5F, 21F, -16.5F);
        UHead.pitch = 0.5235988F;
        DHead = new Cuboid(44, 0);
        DHead.method_1818(0.0F, 0.0F, 0.0F, 5, 2, 5, 0.0F);
        DHead.setRotationPoint(-3.5F, 21.5F, -13.5F);
        DHead.pitch = -0.261799F;
        RHead = new Cuboid(0, 3);
        RHead.method_1818(0.0F, 0.0F, 0.0F, 1, 6, 6, 0.0F);
        RHead.setRotationPoint(-3.45F, 21.3F, -13.85F);
        RHead.pitch = 0.7853981F;
        LHead = new Cuboid(0, 3);
        LHead.method_1818(0.0F, 0.0F, 0.0F, 1, 6, 6, 0.0F);
        LHead.setRotationPoint(0.45F, 21.3F, -13.8F);
        LHead.pitch = 0.7853981F;
        PTail = new Cuboid(36, 8);
        PTail.method_1818(0.0F, 0.0F, 0.0F, 4, 6, 10, 0.0F);
        PTail.setRotationPoint(-3F, 18F, 8F);
        UpperFin = new Cuboid(6, 12);
        UpperFin.method_1818(0.0F, 0.0F, 0.0F, 1, 4, 8, 0.0F);
        UpperFin.setRotationPoint(-1.5F, 17F, -1F);
        UpperFin.pitch = 0.7853981F;
        UpperTailFin = new Cuboid(6, 12);
        UpperTailFin.method_1818(0.0F, 0.0F, 0.0F, 1, 4, 8, 0.0F);
        UpperTailFin.setRotationPoint(-1.5F, 18F, 16F);
        UpperTailFin.pitch = 0.5235988F;
        LowerTailFin = new Cuboid(8, 14);
        LowerTailFin.method_1818(0.0F, 0.0F, 0.0F, 1, 4, 6, 0.0F);
        LowerTailFin.setRotationPoint(-1.5F, 21F, 18F);
        LowerTailFin.pitch = -0.7853981F;
        LeftFin = new Cuboid(18, 0);
        LeftFin.method_1818(0.0F, 0.0F, 0.0F, 8, 1, 4, 0.0F);
        LeftFin.setRotationPoint(2.0F, 24F, -5F);
        LeftFin.yaw = -0.5235988F;
        LeftFin.roll = 0.5235988F;
        RightFin = new Cuboid(18, 0);
        RightFin.method_1818(0.0F, 0.0F, 0.0F, 8, 1, 4, 0.0F);
        RightFin.setRotationPoint(-10F, 27.5F, -1F);
        RightFin.yaw = 0.5235988F;
        RightFin.roll = -0.5235988F;
    }

    public void render(float f, float f1, float f2, float f3, float f4, float f5)
    {
        ModelBase(f, f1, f2, f3, f4, f5);
        Body.method_1815(f5);
        PTail.method_1815(f5);
        UHead.method_1815(f5);
        DHead.method_1815(f5);
        RHead.method_1815(f5);
        LHead.method_1815(f5);
        UpperFin.method_1815(f5);
        UpperTailFin.method_1815(f5);
        LowerTailFin.method_1815(f5);
        LeftFin.method_1815(f5);
        RightFin.method_1815(f5);
    }

    public void ModelBase(float f, float f1, float f2, float f3, float f4, float f5)
    {
        UpperTailFin.yaw = MathHelper.cos(f * 0.6662F) * f1;
        LowerTailFin.yaw = MathHelper.cos(f * 0.6662F) * f1;
    }

    public Cuboid LHead;
    public Cuboid RHead;
    public Cuboid UHead;
    public Cuboid DHead;
    public Cuboid RTail;
    public Cuboid LTail;
    public Cuboid PTail;
    public Cuboid Body;
    public Cuboid UpperFin;
    public Cuboid UpperTailFin;
    public Cuboid LowerTailFin;
    public Cuboid RightFin;
    public Cuboid LeftFin;
}
