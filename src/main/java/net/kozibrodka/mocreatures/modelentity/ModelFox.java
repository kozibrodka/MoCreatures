// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) braces deadcode 

package net.kozibrodka.mocreatures.modelentity;


import net.minecraft.client.model.Cuboid;
import net.minecraft.client.render.entity.model.EntityModelBase;
import net.minecraft.util.maths.MathHelper;

public class ModelFox extends EntityModelBase
{

    public ModelFox()
    {
        byte byte0 = 8;
        Body = new Cuboid(0, 0);
        Body.method_1818(0.0F, 0.0F, 0.0F, 6, 6, 12, 0.0F);
        Body.setRotationPoint(-4F, 10F, -6F);
        Head = new Cuboid(0, 20);
        Head.method_1818(-4F, -4F, -6F, 6, 6, 4, 0.0F);
        Head.setRotationPoint(0.0F, 12F, -4F);
        Snout = new Cuboid(20, 20);
        Snout.method_1818(-2F, 1.0F, -10F, 2, 2, 4, 0.0F);
        Snout.setRotationPoint(0.0F, 11F, -3.5F);
        Ears = new Cuboid(50, 20);
        Ears.method_1818(-4F, -4F, -6F, 6, 4, 1, 0.0F);
        Ears.setRotationPoint(0.0F, 9F, -2F);
        Tail = new Cuboid(32, 20);
        Tail.method_1818(-5F, -5F, -2F, 3, 3, 8, 0.0F);
        Tail.setRotationPoint(2.5F, 15F, 5F);
        Tail.pitch = -0.5235988F;
        Leg1 = new Cuboid(0, 0);
        Leg1.method_1818(-2F, 0.0F, -2F, 3, byte0, 3, 0.0F);
        Leg1.setRotationPoint(-2F, 24 - byte0, 5F);
        Leg2 = new Cuboid(0, 0);
        Leg2.method_1818(-2F, 0.0F, -2F, 3, byte0, 3, 0.0F);
        Leg2.setRotationPoint(1.0F, 24 - byte0, 5F);
        Leg3 = new Cuboid(0, 0);
        Leg3.method_1818(-2F, 0.0F, -2F, 3, byte0, 3, 0.0F);
        Leg3.setRotationPoint(-2F, 24 - byte0, -4F);
        Leg4 = new Cuboid(0, 0);
        Leg4.method_1818(-2F, 0.0F, -2F, 3, byte0, 3, 0.0F);
        Leg4.setRotationPoint(1.0F, 24 - byte0, -4F);
    }

    public void render(float f, float f1, float f2, float f3, float f4, float f5)
    {
        setAngles(f, f1, f2, f3, f4, f5);
        Body.method_1815(f5);
        Leg1.method_1815(f5);
        Leg2.method_1815(f5);
        Leg3.method_1815(f5);
        Leg4.method_1815(f5);
        Head.method_1815(f5);
        Snout.method_1815(f5);
        Tail.method_1815(f5);
        Ears.method_1815(f5);
    }

    public void setAngles(float f, float f1, float f2, float f3, float f4, float f5)
    {
        Head.yaw = f3 / 57.29578F;
        Head.pitch = f4 / 57.29578F;
        Snout.yaw = f3 / 57.29578F;
        Snout.pitch = f4 / 57.29578F;
        Snout.rotationPointX = 0.0F + (f3 / 57.29578F) * 0.8F;
        Ears.yaw = f3 / 57.29578F;
        Ears.pitch = f4 / 57.29578F;
        Ears.rotationPointX = 0.0F + (f3 / 57.29578F) * 2.5F;
        Leg1.pitch = MathHelper.cos(f * 0.6662F) * 1.4F * f1;
        Leg2.pitch = MathHelper.cos(f * 0.6662F + 3.141593F) * 1.4F * f1;
        Leg3.pitch = MathHelper.cos(f * 0.6662F + 3.141593F) * 1.4F * f1;
        Leg4.pitch = MathHelper.cos(f * 0.6662F) * 1.4F * f1;
    }

    public Cuboid Body;
    public Cuboid Leg1;
    public Cuboid Leg2;
    public Cuboid Leg3;
    public Cuboid Leg4;
    public Cuboid Snout;
    public Cuboid Head;
    public Cuboid Tail;
    public Cuboid Ears;
}
