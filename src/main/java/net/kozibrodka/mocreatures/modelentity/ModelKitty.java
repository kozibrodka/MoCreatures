// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) braces deadcode 

package net.kozibrodka.mocreatures.modelentity;

import net.minecraft.client.model.ModelPart;
import net.minecraft.client.render.entity.model.BipedEntityModel;
import net.minecraft.util.math.MathHelper;
import org.lwjgl.opengl.GL11;


public class ModelKitty extends BipedEntityModel
{

    public ModelKitty()
    {
        this(0.0F);
    }

    public ModelKitty(float f)
    {
        this(f, 0.0F);
    }

    public ModelKitty(float f, float f1)
    {
        super(f, f1);
        head = new ModelPart(1, 1);
        head.addCuboid(-2.5F, -3F, -4F, 5, 4, 4, f);
        head.setPivot(0.0F, 0.0F + f1, -2F);
        hat = new ModelPart(0, 0);
        hat.addCuboid(-4F, -3F, -4F, 1, 1, 1, f + 0.5F);
        hat.setPivot(0.0F, 0.0F + f1, -2F);
        bipedHeadParts = new ModelPart[9];
        bipedHeadParts[0] = new ModelPart(16, 0);
        bipedHeadParts[0].addCuboid(-2F, -5F, -3F, 1, 1, 1, f);
        bipedHeadParts[0].setPivot(0.0F, 0.0F + f1, -2F);
        bipedHeadParts[1] = new ModelPart(16, 0);
        bipedHeadParts[1].mirror = true;
        bipedHeadParts[1].addCuboid(1.0F, -5F, -3F, 1, 1, 1, f);
        bipedHeadParts[1].setPivot(0.0F, 0.0F + f1, -2F);
        bipedHeadParts[2] = new ModelPart(20, 0);
        bipedHeadParts[2].addCuboid(-2.5F, -4F, -3F, 2, 1, 1, f);
        bipedHeadParts[2].setPivot(0.0F, 0.0F + f1, -2F);
        bipedHeadParts[3] = new ModelPart(20, 0);
        bipedHeadParts[3].mirror = true;
        bipedHeadParts[3].addCuboid(0.5F, -4F, -3F, 2, 1, 1, f);
        bipedHeadParts[3].setPivot(0.0F, 0.0F + f1, -2F);
        bipedHeadParts[4] = new ModelPart(40, 0);
        bipedHeadParts[4].addCuboid(-4F, -1.5F, -5F, 3, 3, 1, f);
        bipedHeadParts[4].setPivot(0.0F, 0.0F + f1, -2F);
        bipedHeadParts[5] = new ModelPart(40, 0);
        bipedHeadParts[5].mirror = true;
        bipedHeadParts[5].addCuboid(1.0F, -1.5F, -5F, 3, 3, 1, f);
        bipedHeadParts[5].setPivot(0.0F, 0.0F + f1, -2F);
        bipedHeadParts[6] = new ModelPart(21, 6);
        bipedHeadParts[6].addCuboid(-1F, -1F, -5F, 2, 2, 1, f);
        bipedHeadParts[6].setPivot(0.0F, 0.0F + f1, -2F);
        bipedHeadParts[7] = new ModelPart(50, 0);
        bipedHeadParts[7].addCuboid(-2.5F, 0.5F, -1F, 5, 4, 1, f);
        bipedHeadParts[7].setPivot(0.0F, 0.0F + f1, -2F);
        bipedHeadParts[8] = new ModelPart(60, 0);
        bipedHeadParts[8].addCuboid(-1.5F, -2F, -4.1F, 3, 1, 1, f);
        bipedHeadParts[8].setPivot(0.0F, 0.0F + f1, -2F);
        body = new ModelPart(20, 0);
        body.addCuboid(-2.5F, -2F, -0F, 5, 5, 10, f);
        body.setPivot(0.0F, 0.0F + f1, -2F);
        rightArm = new ModelPart(0, 9);
        rightArm.addCuboid(-1F, 0.0F, -1F, 2, 6, 2, f);
        rightArm.setPivot(-1.5F, 3F + f1, -1F);
        leftArm = new ModelPart(0, 9);
        leftArm.mirror = true;
        leftArm.addCuboid(-1F, 0.0F, -1F, 2, 6, 2, f);
        leftArm.setPivot(1.5F, 3F + f1, -1F);
        rightLeg = new ModelPart(8, 9);
        rightLeg.addCuboid(-1F, 0.0F, -1F, 2, 6, 2, f);
        rightLeg.setPivot(-1.5F, 3F + f1, 7F);
        leftLeg = new ModelPart(8, 9);
        leftLeg.mirror = true;
        leftLeg.addCuboid(-1F, 0.0F, -1F, 2, 6, 2, f);
        leftLeg.setPivot(1.5F, 3F + f1, 7F);
        bipedTail = new ModelPart(16, 9);
        bipedTail.mirror = true;
        bipedTail.addCuboid(-0.5F, -8F, -1F, 1, 8, 1, f);
        bipedTail.setPivot(0.0F, -0.5F + f1, 7.5F);
    }

    public void render(float f, float f1, float f2, float f3, float f4, float f5)
    {
        GL11.glPushMatrix();
        setAngles(f, f1, f2, f3, f4, f5);
        if(isSitting)
        {
            GL11.glTranslatef(0.0F, 0.25F, 0.0F);
            bipedTail.roll = 0.0F;
            bipedTail.pitch = -2.3F;
        }
        head.render(f5);
        for(int i = 0; i < 7; i++)
        {
            bipedHeadParts[i].render(f5);
        }

        if(kittystate > 2)
        {
            bipedHeadParts[7].render(f5);
        }
        if(kittystate == 12)
        {
            bipedHeadParts[8].render(f5);
        }
        body.render(f5);
        bipedTail.render(f5);
        if(isSitting)
        {
            GL11.glTranslatef(0.0F, 0.0625F, 0.0625F);
            float f6 = -1.570796F;
            rightArm.pitch = f6;
            leftArm.pitch = f6;
            rightLeg.pitch = f6;
            leftLeg.pitch = f6;
            rightLeg.yaw = 0.1F;
            leftLeg.yaw = -0.1F;
        }
        rightArm.render(f5);
        leftArm.render(f5);
        rightLeg.render(f5);
        leftLeg.render(f5);
        GL11.glPopMatrix();
    }

    public void setAngles(float f, float f1, float f2, float f3, float f4, float f5)
    {
        head.yaw = f3 / 57.29578F;
        head.pitch = f4 / 57.29578F;
        for(int i = 0; i < 9; i++)
        {
            bipedHeadParts[i].yaw = head.yaw;
            bipedHeadParts[i].pitch = head.pitch;
        }

        hat.yaw = head.yaw;
        hat.pitch = head.pitch;
        rightArm.pitch = MathHelper.cos(f * 0.6662F + 3.141593F) * 2.0F * f1 * 0.5F;
        leftArm.pitch = MathHelper.cos(f * 0.6662F) * 2.0F * f1 * 0.5F;
        rightArm.roll = 0.0F;
        leftArm.roll = 0.0F;
        rightLeg.pitch = MathHelper.cos(f * 0.6662F) * 1.4F * f1;
        leftLeg.pitch = MathHelper.cos(f * 0.6662F + 3.141593F) * 1.4F * f1;
        rightLeg.yaw = 0.0F;
        leftLeg.yaw = 0.0F;
        if(leftArmPose)
        {
            leftArm.pitch = leftArm.pitch * 0.5F - 0.3141593F;
        }
        if(rightArmPose)
        {
            rightArm.pitch = rightArm.pitch * 0.5F - 0.3141593F;
        }
        if(isSwinging)
        {
            rightArm.pitch = -2F + swingProgress;
            rightArm.yaw = 2.25F - swingProgress * 2.0F;
        } else
        {
            rightArm.yaw = 0.0F;
        }
        leftArm.yaw = 0.0F;
        bipedTail.pitch = -0.5F;
        bipedTail.roll = leftLeg.pitch * 0.625F;
    }

    public boolean isSitting;
    public boolean isSwinging;
    public float swingProgress;
    public ModelPart bipedHeadParts[];
    public static final int parts = 9;
    public ModelPart bipedTail;
    public int kittystate;
}
