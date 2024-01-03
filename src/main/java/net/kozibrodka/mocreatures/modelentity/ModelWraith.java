// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) braces deadcode 

package net.kozibrodka.mocreatures.modelentity;


import net.minecraft.client.model.ModelPart;
import net.minecraft.client.render.entity.model.BipedEntityModel;
import net.minecraft.util.math.MathHelper;

public class ModelWraith extends BipedEntityModel
{

    public ModelWraith()
    {
        super(12F, 0.0F);
        leftArmPose = false;
        rightArmPose = false;
        sneaking = false;
        head = new ModelPart(0, 40);
        head.addCuboid(-4F, -8F, -4F, 1, 1, 1, 0.0F);
        head.setPivot(0.0F, 0.0F, 0.0F);
        hat = new ModelPart(0, 0);
        hat.addCuboid(-5F, -8F, -4F, 8, 8, 8, 0.0F);
        hat.setPivot(0.0F, 0.0F, 0.0F);
        body = new ModelPart(36, 0);
        body.addCuboid(-6F, 0.0F, -2F, 10, 20, 4, 0.0F);
        body.setPivot(0.0F, 0.0F, 0.0F);
        rightArm = new ModelPart(16, 16);
        rightArm.addCuboid(-5F, -2F, -2F, 4, 12, 4, 0.0F);
        rightArm.setPivot(-5F, 2.0F, 0.0F);
        leftArm = new ModelPart(16, 16);
        leftArm.mirror = true;
        leftArm.addCuboid(-1F, -2F, -2F, 4, 12, 4, 0.0F);
        leftArm.setPivot(5F, 2.0F, 0.0F);
        rightLeg = new ModelPart(0, 16);
        rightLeg.addCuboid(-2F, 0.0F, -2F, 2, 2, 2, 0.0F);
        rightLeg.setPivot(-2F, 12F, 0.0F);
        leftLeg = new ModelPart(0, 16);
        leftLeg.mirror = true;
        leftLeg.addCuboid(-2F, 0.0F, -2F, 2, 2, 2, 0.0F);
        leftLeg.setPivot(2.0F, 12F, 0.0F);
    }

    public void setAngles(float f, float f1, float f2, float f3, float f4, float f5)
    {
        super.setAngles(f, f1, f2, f3, f4, f5);
        float f6 = MathHelper.sin(handWingProgress * 3.141593F);
        float f7 = MathHelper.sin((1.0F - (1.0F - handWingProgress) * (1.0F - handWingProgress)) * 3.141593F);
        rightArm.roll = 0.0F;
        leftArm.roll = 0.0F;
        rightArm.yaw = -(0.1F - f6 * 0.6F);
        leftArm.yaw = 0.1F - f6 * 0.6F;
        rightArm.pitch = -1.570796F;
        leftArm.pitch = -1.570796F;
        rightArm.pitch -= f6 * 1.2F - f7 * 0.4F;
        leftArm.pitch -= f6 * 1.2F - f7 * 0.4F;
        rightArm.roll += MathHelper.cos(f2 * 0.09F) * 0.05F + 0.05F;
        leftArm.roll -= MathHelper.cos(f2 * 0.09F) * 0.05F + 0.05F;
        rightArm.pitch += MathHelper.sin(f2 * 0.067F) * 0.05F;
        leftArm.pitch -= MathHelper.sin(f2 * 0.067F) * 0.05F;
    }
}
