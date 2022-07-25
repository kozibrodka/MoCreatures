// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) braces deadcode 

package net.kozibrodka.mocreatures.modelentity;

import net.minecraft.client.model.Cuboid;
import net.minecraft.client.render.entity.model.EntityModelBase;
import net.minecraft.util.maths.MathHelper;

public class ModelBigCat2 extends EntityModelBase
{

    public ModelBigCat2()
    {
        ears = new Cuboid(16, 25);
        ears.method_1818(-4F, -7F, -3F, 8, 4, 1, 0.0F);
        ears.setRotationPoint(0.0F, 4F, -8F);
        head = new Cuboid(0, 0);
        head.method_1818(-4F, -4F, -6F, 8, 8, 6, 0.0F);
        head.setRotationPoint(0.0F, 4F, -8F);
        snout = new Cuboid(14, 14);
        snout.method_1818(-2F, 0.0F, -9F, 4, 4, 6, 0.0F);
        snout.setRotationPoint(0.0F, 4F, -8F);
        Collar = new Cuboid(24, 0);
        Collar.method_1818(-2.5F, 4F, -3F, 5, 4, 1, 0.0F);
        Collar.setRotationPoint(0.0F, 4F, -8F);
        body = new Cuboid(28, 0);
        body.method_1818(-5F, -10F, -7F, 10, 18, 8, 0.0F);
        body.setRotationPoint(0.0F, 5F, 2.0F);
        Tail = new Cuboid(26, 15);
        Tail.method_1818(-5F, -5F, -2F, 3, 3, 14, 0.0F);
        Tail.setRotationPoint(3.5F, 9.3F, 9F);
        Tail.pitch = -0.5235988F;
        leg1 = new Cuboid(0, 16);
        leg1.method_1818(-2F, 0.0F, -2F, 4, 12, 4, 0.0F);
        leg1.setRotationPoint(-3F, 12F, 7F);
        leg2 = new Cuboid(0, 16);
        leg2.method_1818(-2F, 0.0F, -2F, 4, 12, 4, 0.0F);
        leg2.setRotationPoint(3F, 12F, 7F);
        leg3 = new Cuboid(0, 16);
        leg3.method_1818(-2F, 0.0F, -2F, 4, 12, 4, 0.0F);
        leg3.setRotationPoint(-3F, 12F, -5F);
        leg4 = new Cuboid(0, 16);
        leg4.method_1818(-2F, 0.0F, -2F, 4, 12, 4, 0.0F);
        leg4.setRotationPoint(3F, 12F, -5F);
    }

    public void render(float f, float f1, float f2, float f3, float f4, float f5)
    {
        setAngles(f, f1, f2, f3, f4, f5);
        snout.method_1815(f5);
        Tail.method_1815(f5);
        ears.method_1815(f5);
        head.method_1815(f5);
        body.method_1815(f5);
        leg1.method_1815(f5);
        leg2.method_1815(f5);
        leg3.method_1815(f5);
        leg4.method_1815(f5);
        if(tamed)
        {
            Collar.method_1815(f5);
        }
    }

    public void setAngles(float f, float f1, float f2, float f3, float f4, float f5)
    {
        head.pitch = f4 / 57.29578F;
        head.yaw = f3 / 57.29578F;
        leg1.pitch = MathHelper.cos(f * 0.6662F) * 1.4F * f1;
        leg2.pitch = MathHelper.cos(f * 0.6662F + 3.141593F) * 1.4F * f1;
        leg3.pitch = MathHelper.cos(f * 0.6662F + 3.141593F) * 1.4F * f1;
        leg4.pitch = MathHelper.cos(f * 0.6662F) * 1.4F * f1;
        snout.yaw = head.yaw;
        snout.pitch = head.pitch;
        ears.yaw = head.yaw;
        ears.pitch = head.pitch;
        Collar.yaw = head.yaw;
        Collar.pitch = head.pitch;
        if(!sitting)
        {
            body.rotationPointX = 0.0F;
            body.rotationPointY = 5F;
            body.rotationPointZ = 2.0F;
            body.pitch = 1.570796F;
            leg1.rotationPointX = -3F;
            leg1.rotationPointZ = 7F;
            leg2.rotationPointX = 3F;
            leg2.rotationPointZ = 7F;
            leg3.rotationPointX = -3F;
            leg3.rotationPointZ = -5F;
            leg4.rotationPointX = 3F;
            leg4.rotationPointZ = -5F;
            Tail.rotationPointX = 3.5F;
            Tail.rotationPointY = 9.3F;
            Tail.rotationPointZ = 9F;
            Tail.pitch = -0.5235988F;
            Tail.yaw = MathHelper.cos(f * 0.6662F) * 0.7F * f1;
        } else
        {
            body.pitch = 0.8726646F;
            body.rotationPointX = 0.0F;
            body.rotationPointY = 12F;
            body.rotationPointZ = 1.0F;
            leg1.rotationPointX = -5F;
            leg1.rotationPointZ = 0.0F;
            leg2.rotationPointX = 5F;
            leg2.rotationPointZ = 0.0F;
            leg3.rotationPointX = -2F;
            leg3.rotationPointZ = -8F;
            leg4.rotationPointX = 2.0F;
            leg4.rotationPointZ = -8F;
            Tail.rotationPointX = 3.5F;
            Tail.rotationPointY = 22F;
            Tail.rotationPointZ = 8F;
            Tail.pitch = -0.1745329F;
        }
    }

    Cuboid snout;
    Cuboid Tail;
    Cuboid head;
    Cuboid body;
    Cuboid ears;
    Cuboid leg1;
    Cuboid leg2;
    Cuboid leg3;
    Cuboid leg4;
    Cuboid Collar;
    public boolean sitting;
    public boolean tamed;
}
