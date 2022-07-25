// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) braces deadcode 

package net.kozibrodka.mocreatures.modelentity;


import net.minecraft.client.model.Cuboid;
import net.minecraft.client.render.entity.model.EntityModelBase;
import net.minecraft.util.maths.MathHelper;

public class ModelBunny extends EntityModelBase
{

    public ModelBunny()
    {
        byte byte0 = 16;
        a = new Cuboid(0, 0);
        a.method_1818(-2F, -1F, -4F, 4, 4, 6, 0.0F);
        a.setRotationPoint(0.0F, -1 + byte0, -4F);
        onGround = new Cuboid(14, 0);
        onGround.method_1818(-2F, -5F, -3F, 1, 4, 2, 0.0F);
        onGround.setRotationPoint(0.0F, -1 + byte0, -4F);
        g2 = new Cuboid(14, 0);
        g2.method_1818(1.0F, -5F, -3F, 1, 4, 2, 0.0F);
        g2.setRotationPoint(0.0F, -1 + byte0, -4F);
        h = new Cuboid(20, 0);
        h.method_1818(-4F, 0.0F, -3F, 2, 3, 2, 0.0F);
        h.setRotationPoint(0.0F, -1 + byte0, -4F);
        r2 = new Cuboid(20, 0);
        r2.method_1818(2.0F, 0.0F, -3F, 2, 3, 2, 0.0F);
        r2.setRotationPoint(0.0F, -1 + byte0, -4F);
        b = new Cuboid(0, 10);
        b.method_1818(-3F, -4F, -3F, 6, 8, 6, 0.0F);
        b.setRotationPoint(0.0F, 0 + byte0, 0.0F);
        b2 = new Cuboid(0, 24);
        b2.method_1818(-2F, 4F, -2F, 4, 3, 4, 0.0F);
        b2.setRotationPoint(0.0F, 0 + byte0, 0.0F);
        e1 = new Cuboid(24, 16);
        e1.method_1817(-2F, 0.0F, -1F, 2, 2, 2);
        e1.setRotationPoint(3F, 3 + byte0, -3F);
        e2 = new Cuboid(24, 16);
        e2.method_1817(0.0F, 0.0F, -1F, 2, 2, 2);
        e2.setRotationPoint(-3F, 3 + byte0, -3F);
        l1 = new Cuboid(16, 24);
        l1.method_1817(-2F, 0.0F, -4F, 2, 2, 4);
        l1.setRotationPoint(3F, 3 + byte0, 4F);
        n2 = new Cuboid(16, 24);
        n2.method_1817(0.0F, 0.0F, -4F, 2, 2, 4);
        n2.setRotationPoint(-3F, 3 + byte0, 4F);
    }

    public void render(float f, float f1, float f2, float f3, float f4, float f5)
    {
        setAngles(f, f1, f2, f3, f4, f5);
        a.method_1815(f5);
        onGround.method_1815(f5);
        g2.method_1815(f5);
        h.method_1815(f5);
        r2.method_1815(f5);
        b.method_1815(f5);
        b2.method_1815(f5);
        e1.method_1815(f5);
        e2.method_1815(f5);
        l1.method_1815(f5);
        n2.method_1815(f5);
    }

    public void setAngles(float f, float f1, float f2, float f3, float f4, float f5)
    {
        a.pitch = -(f4 / 57.29578F);
        a.yaw = f3 / 57.29578F;
        onGround.pitch = a.pitch;
        onGround.yaw = a.yaw;
        g2.pitch = a.pitch;
        g2.yaw = a.yaw;
        h.pitch = a.pitch;
        h.yaw = a.yaw;
        r2.pitch = a.pitch;
        r2.yaw = a.yaw;
        b.pitch = 1.570796F;
        b2.pitch = 1.570796F;
        e1.pitch = MathHelper.cos(f * 0.6662F) * 1.0F * f1;
        l1.pitch = MathHelper.cos(f * 0.6662F + 3.141593F) * 1.2F * f1;
        e2.pitch = MathHelper.cos(f * 0.6662F) * 1.0F * f1;
        n2.pitch = MathHelper.cos(f * 0.6662F + 3.141593F) * 1.2F * f1;
    }

    public Cuboid a;
    public Cuboid b;
    public Cuboid b2;
    public Cuboid e1;
    public Cuboid e2;
    public Cuboid l1;
    public Cuboid n2;
    public Cuboid onGround;
    public Cuboid g2;
    public Cuboid h;
    public Cuboid r2;
}
