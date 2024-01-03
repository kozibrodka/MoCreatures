// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) braces deadcode 

package net.kozibrodka.mocreatures.modelentity;


import net.minecraft.client.model.ModelPart;
import net.minecraft.client.render.entity.model.BipedEntityModel;
import net.minecraft.util.math.MathHelper;

public class ModelOgre1 extends BipedEntityModel
{

    public ModelOgre1()
    {
        super(0.0F, 0.0F);
        head = new ModelPart(0, 0);
        head.addCuboid(-4F, -25F, -6F, 8, 8, 8, 0.0F);
        head.setPivot(0.0F, 0.0F, 0.0F);
        ears = new ModelPart(41, 24);
        ears.addCuboid(-4F, -31F, -3F, 8, 6, 1, 0.5F);
        ears.setPivot(0.0F, 0.0F, 0.0F);
        body = new ModelPart(16, 16);
        body.addCuboid(-4F, -14F, -2F, 8, 12, 4, 3F);
        body.setPivot(0.0F, 0.0F, 0.0F);
        rightArm = new ModelPart(0, 16);
        rightArm.addCuboid(-7F, -2F, -2F, 1, 1, 1, 0.0F);
        rightArm.setPivot(0.0F, 0.0F, 0.0F);
        leftArm = new ModelPart(0, 16);
        leftArm.mirror = true;
        leftArm.addCuboid(0.0F, 0.0F, 0.0F, 1, 1, 1, 0.0F);
        leftArm.setPivot(0.0F, 0.0F, 0.0F);
        rightLeg = new ModelPart(40, 0);
        rightLeg.addCuboid(-2F, -8F, -2F, 4, 18, 4, 1.5F);
        rightLeg.setPivot(-4F, 12F, 0.0F);
        leftLeg = new ModelPart(40, 0);
        leftLeg.mirror = true;
        leftLeg.addCuboid(-2F, -8F, -2F, 4, 18, 4, 1.5F);
        leftLeg.setPivot(4F, 12F, 0.0F);
    }

    public void render(float f, float f1, float f2, float f3, float f4, float f5, boolean flag)
    {
        setAngles(f, f1, f2, f3, f4, f5);
        head.render(f5);
        body.render(f5);
        rightArm.render(f5);
        leftArm.render(f5);
        rightLeg.render(f5);
        leftLeg.render(f5);
        ears.render(f5);
    }

    public void setAngles(float f, float f1, float f2, float f3, float f4, float f5)
    {
        head.yaw = f3 / 57.29578F;
        ears.yaw = head.yaw;
        ears.pitch = head.pitch;
        rightArm.pitch = MathHelper.cos(f * 0.6662F + 3.141593F) * 2.0F * f1 * 0.5F;
        leftArm.pitch = MathHelper.cos(f * 0.6662F) * 2.0F * f1 * 0.5F;
        rightArm.roll = 0.0F;
        leftArm.roll = 0.0F;
        rightLeg.pitch = MathHelper.cos(f * 0.6662F) * 1.4F * f1;
        leftLeg.pitch = MathHelper.cos(f * 0.6662F + 3.141593F) * 1.4F * f1;
        rightLeg.yaw = 0.0F;
        leftLeg.yaw = 0.0F;
        if(riding)
        {
            rightArm.pitch += -0.6283186F;
            leftArm.pitch += -0.6283186F;
            rightLeg.pitch = -1.256637F;
            leftLeg.pitch = -1.256637F;
            rightLeg.yaw = 0.314159F;
            leftLeg.yaw = -0.314159F;
        }
        if(leftArmPose)
        {
            leftArm.pitch = leftArm.pitch * 0.5F - 0.314159F;
        }
        if(rightArmPose)
        {
            rightArm.pitch = rightArm.pitch * 0.5F - 0.314159F;
        }
        rightArm.yaw = 0.0F;
        leftArm.yaw = 0.0F;
        if(handWingProgress > -9990F)
        {
            float f6 = handWingProgress;
            body.yaw = MathHelper.sin(MathHelper.sqrt(f6) * 3.141593F * 2.0F) * 0.2F;
            rightArm.pivotZ = MathHelper.sin(body.yaw) * 5F;
            rightArm.pivotX = -MathHelper.cos(body.yaw) * 5F;
            leftArm.pivotZ = -MathHelper.sin(body.yaw) * 5F;
            leftArm.pivotX = MathHelper.cos(body.yaw) * 5F;
            rightArm.yaw += body.yaw;
            leftArm.yaw += body.yaw;
            leftArm.pitch += body.yaw;
            f6 = 1.0F - handWingProgress;
            f6 *= f6;
            f6 *= f6;
            f6 = 1.0F - f6;
            float f7 = MathHelper.sin(f6 * 3.141593F);
            float f8 = MathHelper.sin(handWingProgress * 3.141593F) * -(head.pitch - 0.7F) * 0.75F;
            ModelPart modelrenderer = rightArm;
            modelrenderer.pitch = (float)((double)modelrenderer.pitch - ((double)f7 * 1.2D + (double)f8));
            rightArm.yaw += body.yaw * 2.0F;
            rightArm.roll = MathHelper.sin(handWingProgress * 3.141593F) * -0.4F;
        }
        if(sneaking)
        {
            body.pitch = 0.5F;
            rightLeg.pitch -= 0.0F;
            leftLeg.pitch -= 0.0F;
            rightArm.pitch += 0.4F;
            leftArm.pitch += 0.4F;
            rightLeg.pivotZ = 4F;
            leftLeg.pivotZ = 4F;
            rightLeg.pivotY = 9F;
            leftLeg.pivotY = 9F;
            head.pivotY = 1.0F;
        } else
        {
            body.pitch = 0.0F;
            rightLeg.pivotZ = 0.0F;
            leftLeg.pivotZ = 0.0F;
            rightLeg.pivotY = 12F;
            leftLeg.pivotY = 12F;
            head.pivotY = 0.0F;
        }
        rightArm.roll += MathHelper.cos(f2 * 0.09F) * 0.05F + 0.05F;
        leftArm.roll -= MathHelper.cos(f2 * 0.09F) * 0.05F + 0.05F;
        rightArm.pitch += MathHelper.sin(f2 * 0.067F) * 0.05F;
        leftArm.pitch -= MathHelper.sin(f2 * 0.067F) * 0.05F;
    }
}
