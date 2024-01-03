// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) braces deadcode 

package net.kozibrodka.mocreatures.modelentity;

import net.minecraft.client.model.ModelPart;
import net.minecraft.client.render.entity.model.EntityModel;
import net.minecraft.util.math.MathHelper;

public class ModelBigCat2 extends EntityModel
{

    public ModelBigCat2()
    {
        ears = new ModelPart(16, 25);
        ears.addCuboid(-4F, -7F, -3F, 8, 4, 1, 0.0F);
        ears.setPivot(0.0F, 4F, -8F);
        head = new ModelPart(0, 0);
        head.addCuboid(-4F, -4F, -6F, 8, 8, 6, 0.0F);
        head.setPivot(0.0F, 4F, -8F);
        snout = new ModelPart(14, 14);
        snout.addCuboid(-2F, 0.0F, -9F, 4, 4, 6, 0.0F);
        snout.setPivot(0.0F, 4F, -8F);
        Collar = new ModelPart(24, 0);
        Collar.addCuboid(-2.5F, 4F, -3F, 5, 4, 1, 0.0F);
        Collar.setPivot(0.0F, 4F, -8F);
        body = new ModelPart(28, 0);
        body.addCuboid(-5F, -10F, -7F, 10, 18, 8, 0.0F);
        body.setPivot(0.0F, 5F, 2.0F);
        Tail = new ModelPart(26, 15);
        Tail.addCuboid(-5F, -5F, -2F, 3, 3, 14, 0.0F);
        Tail.setPivot(3.5F, 9.3F, 9F);
        Tail.pitch = -0.5235988F;
        leg1 = new ModelPart(0, 16);
        leg1.addCuboid(-2F, 0.0F, -2F, 4, 12, 4, 0.0F);
        leg1.setPivot(-3F, 12F, 7F);
        leg2 = new ModelPart(0, 16);
        leg2.addCuboid(-2F, 0.0F, -2F, 4, 12, 4, 0.0F);
        leg2.setPivot(3F, 12F, 7F);
        leg3 = new ModelPart(0, 16);
        leg3.addCuboid(-2F, 0.0F, -2F, 4, 12, 4, 0.0F);
        leg3.setPivot(-3F, 12F, -5F);
        leg4 = new ModelPart(0, 16);
        leg4.addCuboid(-2F, 0.0F, -2F, 4, 12, 4, 0.0F);
        leg4.setPivot(3F, 12F, -5F);
    }

    public void render(float f, float f1, float f2, float f3, float f4, float f5)
    {
        setAngles(f, f1, f2, f3, f4, f5);
        snout.render(f5);
        Tail.render(f5);
        ears.render(f5);
        head.render(f5);
        body.render(f5);
        leg1.render(f5);
        leg2.render(f5);
        leg3.render(f5);
        leg4.render(f5);
        if(tamed)
        {
            Collar.render(f5);
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
            body.pivotX = 0.0F;
            body.pivotY = 5F;
            body.pivotZ = 2.0F;
            body.pitch = 1.570796F;
            leg1.pivotX = -3F;
            leg1.pivotZ = 7F;
            leg2.pivotX = 3F;
            leg2.pivotZ = 7F;
            leg3.pivotX = -3F;
            leg3.pivotZ = -5F;
            leg4.pivotX = 3F;
            leg4.pivotZ = -5F;
            Tail.pivotX = 3.5F;
            Tail.pivotY = 9.3F;
            Tail.pivotZ = 9F;
            Tail.pitch = -0.5235988F;
            Tail.yaw = MathHelper.cos(f * 0.6662F) * 0.7F * f1;
        } else
        {
            body.pitch = 0.8726646F;
            body.pivotX = 0.0F;
            body.pivotY = 12F;
            body.pivotZ = 1.0F;
            leg1.pivotX = -5F;
            leg1.pivotZ = 0.0F;
            leg2.pivotX = 5F;
            leg2.pivotZ = 0.0F;
            leg3.pivotX = -2F;
            leg3.pivotZ = -8F;
            leg4.pivotX = 2.0F;
            leg4.pivotZ = -8F;
            Tail.pivotX = 3.5F;
            Tail.pivotY = 22F;
            Tail.pivotZ = 8F;
            Tail.pitch = -0.1745329F;
        }
    }

    ModelPart snout;
    ModelPart Tail;
    ModelPart head;
    ModelPart body;
    ModelPart ears;
    ModelPart leg1;
    ModelPart leg2;
    ModelPart leg3;
    ModelPart leg4;
    ModelPart Collar;
    public boolean sitting;
    public boolean tamed;
}
