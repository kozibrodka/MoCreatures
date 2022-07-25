// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) braces deadcode 

package net.kozibrodka.mocreatures.modelentity;


import net.minecraft.client.model.Cuboid;
import net.minecraft.client.render.entity.model.EntityModelBase;
import net.minecraft.util.maths.MathHelper;

public class ModelHorse2 extends EntityModelBase
{

    public ModelHorse2()
    {
        Body = new Cuboid(0, 0);
        Body.method_1818(-5F, 0.0F, -10F, 10, 10, 20, 0.0F);
        Body.setRotationPoint(0.0F, 2.0F, 0.0F);
        Tail = new Cuboid(40, 0);
        Tail.method_1818(-2F, 0.0F, -2F, 4, 12, 4, 0.0F);
        Tail.setRotationPoint(0.0F, 2.0F, 8F);
        Tail.pitch = 0.5235988F;
        Leg1 = new Cuboid(0, 0);
        Leg1.method_1818(-2F, 0.0F, -2F, 4, 12, 4, 0.0F);
        Leg1.setRotationPoint(3F, 12F, 8F);
        Leg2 = new Cuboid(0, 0);
        Leg2.method_1818(-2F, 0.0F, -2F, 4, 12, 4, 0.0F);
        Leg2.setRotationPoint(-3F, 12F, 8F);
        Leg3 = new Cuboid(0, 0);
        Leg3.method_1818(-2F, 0.0F, -2F, 4, 12, 4, 0.0F);
        Leg3.setRotationPoint(3F, 12F, -8F);
        Leg4 = new Cuboid(0, 0);
        Leg4.method_1818(-2F, 0.0F, -2F, 4, 12, 4, 0.0F);
        Leg4.setRotationPoint(-3F, 12F, -8F);
    }

    public void render(float f, float f1, float f2, float f3, float f4, float f5)
    {
        setAngles(f, f1, f2, f3, f4, f5);
        Body.method_1815(f5);
        Tail.method_1815(f5);
        Leg1.method_1815(f5);
        Leg2.method_1815(f5);
        Leg3.method_1815(f5);
        Leg4.method_1815(f5);
    }

    public void setAngles(float f, float f1, float f2, float f3, float f4, float f5)
    {
        Leg1.pitch = MathHelper.cos(f * 0.6662F) * 1.4F * f1;
        Leg2.pitch = MathHelper.cos(f * 0.6662F + 3.141593F) * 1.4F * f1;
        Leg3.pitch = MathHelper.cos(f * 0.6662F + 3.141593F) * 1.4F * f1;
        Leg4.pitch = MathHelper.cos(f * 0.6662F) * 1.4F * f1;
    }

    public Cuboid Body;
    public Cuboid Tail;
    public Cuboid Leg1;
    public Cuboid Leg2;
    public Cuboid Leg3;
    public Cuboid Leg4;
}
