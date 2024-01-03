// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) braces deadcode 

package net.kozibrodka.mocreatures.modelentity;


import net.minecraft.client.model.ModelPart;
import net.minecraft.client.render.entity.model.EntityModel;
import net.minecraft.util.math.MathHelper;

public class ModelBunny extends EntityModel
{

    public ModelBunny()
    {
        byte byte0 = 16;
        a = new ModelPart(0, 0);
        a.addCuboid(-2F, -1F, -4F, 4, 4, 6, 0.0F);
        a.setPivot(0.0F, -1 + byte0, -4F);
        onGround = new ModelPart(14, 0);
        onGround.addCuboid(-2F, -5F, -3F, 1, 4, 2, 0.0F);
        onGround.setPivot(0.0F, -1 + byte0, -4F);
        g2 = new ModelPart(14, 0);
        g2.addCuboid(1.0F, -5F, -3F, 1, 4, 2, 0.0F);
        g2.setPivot(0.0F, -1 + byte0, -4F);
        h = new ModelPart(20, 0);
        h.addCuboid(-4F, 0.0F, -3F, 2, 3, 2, 0.0F);
        h.setPivot(0.0F, -1 + byte0, -4F);
        r2 = new ModelPart(20, 0);
        r2.addCuboid(2.0F, 0.0F, -3F, 2, 3, 2, 0.0F);
        r2.setPivot(0.0F, -1 + byte0, -4F);
        b = new ModelPart(0, 10);
        b.addCuboid(-3F, -4F, -3F, 6, 8, 6, 0.0F);
        b.setPivot(0.0F, 0 + byte0, 0.0F);
        b2 = new ModelPart(0, 24);
        b2.addCuboid(-2F, 4F, -2F, 4, 3, 4, 0.0F);
        b2.setPivot(0.0F, 0 + byte0, 0.0F);
        e1 = new ModelPart(24, 16);
        e1.addCuboid(-2F, 0.0F, -1F, 2, 2, 2);
        e1.setPivot(3F, 3 + byte0, -3F);
        e2 = new ModelPart(24, 16);
        e2.addCuboid(0.0F, 0.0F, -1F, 2, 2, 2);
        e2.setPivot(-3F, 3 + byte0, -3F);
        l1 = new ModelPart(16, 24);
        l1.addCuboid(-2F, 0.0F, -4F, 2, 2, 4);
        l1.setPivot(3F, 3 + byte0, 4F);
        n2 = new ModelPart(16, 24);
        n2.addCuboid(0.0F, 0.0F, -4F, 2, 2, 4);
        n2.setPivot(-3F, 3 + byte0, 4F);
    }

    public void render(float f, float f1, float f2, float f3, float f4, float f5)
    {
        setAngles(f, f1, f2, f3, f4, f5);
        a.render(f5);
        onGround.render(f5);
        g2.render(f5);
        h.render(f5);
        r2.render(f5);
        b.render(f5);
        b2.render(f5);
        e1.render(f5);
        e2.render(f5);
        l1.render(f5);
        n2.render(f5);
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

    public ModelPart a;
    public ModelPart b;
    public ModelPart b2;
    public ModelPart e1;
    public ModelPart e2;
    public ModelPart l1;
    public ModelPart n2;
    public ModelPart onGround;
    public ModelPart g2;
    public ModelPart h;
    public ModelPart r2;
}
