// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) braces deadcode 

package net.kozibrodka.mocreatures.modelentity;

import net.minecraft.client.model.Cuboid;
import net.minecraft.client.render.entity.model.EntityModelBase;
import net.minecraft.util.maths.MathHelper;

public class ModelBird extends EntityModelBase
{

    public ModelBird()
    {
        byte byte0 = 16;
        head = new Cuboid(0, 0);
        head.method_1818(-1.5F, -3F, -2F, 3, 3, 3, 0.0F);
        head.setRotationPoint(0.0F, -1 + byte0, -4F);
        beak = new Cuboid(14, 0);
        beak.method_1818(-0.5F, -1.5F, -3F, 1, 1, 2, 0.0F);
        beak.setRotationPoint(0.0F, -1 + byte0, -4F);
        body = new Cuboid(0, 9);
        body.method_1818(-2F, -4F, -3F, 4, 8, 4, 0.0F);
        body.setRotationPoint(0.0F, 0 + byte0, 0.0F);
        body.pitch = 1.047198F;
        leftleg = new Cuboid(26, 0);
        leftleg.method_1817(-1F, 0.0F, -4F, 3, 4, 3);
        leftleg.setRotationPoint(-2F, 3 + byte0, 1.0F);
        rightleg = new Cuboid(26, 0);
        rightleg.method_1817(-1F, 0.0F, -4F, 3, 4, 3);
        rightleg.setRotationPoint(1.0F, 3 + byte0, 1.0F);
        rwing = new Cuboid(24, 13);
        rwing.method_1817(-1F, 0.0F, -3F, 1, 5, 5);
        rwing.setRotationPoint(-2F, -2 + byte0, 0.0F);
        lwing = new Cuboid(24, 13);
        lwing.method_1817(0.0F, 0.0F, -3F, 1, 5, 5);
        lwing.setRotationPoint(2.0F, -2 + byte0, 0.0F);
        tail = new Cuboid(0, 23);
        tail.method_1818(-6F, 5F, 2.0F, 4, 1, 4, 0.0F);
        tail.setRotationPoint(4F, -3 + byte0, 0.0F);
        tail.pitch = 0.261799F;
    }

    public void render(float f, float f1, float f2, float f3, float f4, float f5)
    {
        setAngles(f, f1, f2, f3, f4, f5);
        head.method_1815(f5);
        beak.method_1815(f5);
        body.method_1815(f5);
        leftleg.method_1815(f5);
        rightleg.method_1815(f5);
        rwing.method_1815(f5);
        lwing.method_1815(f5);
        tail.method_1815(f5);
    }

    public void setAngles(float f, float f1, float f2, float f3, float f4, float f5)
    {
        head.pitch = -(f4 / 2.0F / 57.29578F);
        head.yaw = f3 / 2.0F / 57.29578F;
        beak.yaw = head.yaw;
        leftleg.pitch = MathHelper.cos(f * 0.6662F) * f1;
        rightleg.pitch = MathHelper.cos(f * 0.6662F + 3.141593F) * f1;
        rwing.roll = f2;
        lwing.roll = -f2;
    }

    public Cuboid head;
    public Cuboid body;
    public Cuboid leftleg;
    public Cuboid rightleg;
    public Cuboid rwing;
    public Cuboid lwing;
    public Cuboid beak;
    public Cuboid tail;
}
