// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) braces deadcode 

package net.kozibrodka.mocreatures.modelentity;

import net.minecraft.client.model.Cuboid;
import net.minecraft.client.render.entity.model.EntityModelBase;
import net.minecraft.util.maths.MathHelper;

public class ModelDeer extends EntityModelBase
{

    public ModelDeer()
    {
        Head = new Cuboid(0, 0);
        Head.method_1818(-1.5F, -6F, -9.5F, 3, 3, 6, 0.0F);
        Head.setRotationPoint(1.0F, 11.5F, -4.5F);
        Neck = new Cuboid(0, 9);
        Neck.method_1818(-2F, -2F, -6F, 4, 4, 6, 0.0F);
        Neck.setRotationPoint(1.0F, 11.5F, -4.5F);
        Neck.pitch = -0.7853981F;
        LEar = new Cuboid(0, 0);
        LEar.method_1818(-4F, -7.5F, -5F, 2, 3, 1, 0.0F);
        LEar.setRotationPoint(1.0F, 11.5F, -4.5F);
        LEar.roll = 0.7853981F;
        REar = new Cuboid(0, 0);
        REar.method_1818(2.0F, -7.5F, -5F, 2, 3, 1, 0.0F);
        REar.setRotationPoint(1.0F, 11.5F, -4.5F);
        REar.roll = -0.7853981F;
        LeftAntler = new Cuboid(54, 0);
        LeftAntler.method_1818(0.0F, -14F, -7F, 1, 8, 4, 0.0F);
        LeftAntler.setRotationPoint(1.0F, 11.5F, -4.5F);
        LeftAntler.roll = 0.2094395F;
        RightAntler = new Cuboid(54, 0);
        RightAntler.method_1818(0.0F, -14F, -7F, 1, 8, 4, 0.0F);
        RightAntler.setRotationPoint(1.0F, 11.5F, -4.5F);
        RightAntler.roll = -0.2094395F;
        Body = new Cuboid(24, 12);
        Body.method_1818(-2F, -3F, -6F, 6, 6, 14, 0.0F);
        Body.setRotationPoint(0.0F, 13F, 0.0F);
        Leg1 = new Cuboid(9, 20);
        Leg1.method_1818(-1F, 0.0F, -1F, 2, 8, 2, 0.0F);
        Leg1.setRotationPoint(3F, 16F, -4F);
        Leg2 = new Cuboid(0, 20);
        Leg2.method_1818(-1F, 0.0F, -1F, 2, 8, 2, 0.0F);
        Leg2.setRotationPoint(-1F, 16F, -4F);
        Leg3 = new Cuboid(9, 20);
        Leg3.method_1818(-1F, 0.0F, -1F, 2, 8, 2, 0.0F);
        Leg3.setRotationPoint(3F, 16F, 6F);
        Leg4 = new Cuboid(0, 20);
        Leg4.method_1818(-1F, 0.0F, -1F, 2, 8, 2, 0.0F);
        Leg4.setRotationPoint(-1F, 16F, 6F);
        Tail = new Cuboid(50, 20);
        Tail.method_1818(-1.5F, -1F, 0.0F, 3, 2, 4, 0.0F);
        Tail.setRotationPoint(1.0F, 11F, 7F);
        Tail.pitch = 0.7854F;
    }

    public void render(float f, float f1, float f2, float f3, float f4, float f5)
    {
        setAngles(f, f1, f2, f3, f4, f5);
        Body.method_1815(f5);
        Neck.method_1815(f5);
        Head.method_1815(f5);
        Leg1.method_1815(f5);
        Leg2.method_1815(f5);
        Leg3.method_1815(f5);
        Leg4.method_1815(f5);
        Tail.method_1815(f5);
        LEar.method_1815(f5);
        REar.method_1815(f5);
        LeftAntler.method_1815(f5);
        RightAntler.method_1815(f5);
    }

    public void setAngles(float f, float f1, float f2, float f3, float f4, float f5)
    {
        Leg1.pitch = MathHelper.cos(f * 0.6662F) * 1.4F * f1;
        Leg2.pitch = MathHelper.cos(f * 0.6662F + 3.141593F) * 1.4F * f1;
        Leg3.pitch = MathHelper.cos(f * 0.6662F + 3.141593F) * 1.4F * f1;
        Leg4.pitch = MathHelper.cos(f * 0.6662F) * 1.4F * f1;
    }

    public Cuboid Body;
    public Cuboid Neck;
    public Cuboid Head;
    public Cuboid Leg1;
    public Cuboid Leg2;
    public Cuboid Leg3;
    public Cuboid Leg4;
    public Cuboid Tail;
    public Cuboid LEar;
    public Cuboid REar;
    public Cuboid LeftAntler;
    public Cuboid RightAntler;
}
