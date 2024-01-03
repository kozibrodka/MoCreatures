// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) braces deadcode 

package net.kozibrodka.mocreatures.modelentity;


import net.minecraft.client.model.ModelPart;
import net.minecraft.client.render.entity.model.EntityModel;
import net.minecraft.util.math.MathHelper;

public class ModelWerewolf extends EntityModel
{

    public ModelWerewolf()
    {
        Head = new ModelPart(0, 0);
        Head.addCuboid(-0.5F, -8.5F, -8F, 3, 3, 4, 1.6F);
        Head.setPivot(0.0F, 2.0F, -1F);
        RightEar = new ModelPart(15, 0);
        RightEar.addCuboid(-0.5F, -8.5F, -8F, 2, 4, 1, 0.0F);
        RightEar.setPivot(3.5F, -4F, 3.5F);
        RightEar.roll = -0.7853981F;
        RightEar.pitch = -0.5235988F;
        LeftEar = new ModelPart(15, 0);
        LeftEar.addCuboid(-0.5F, -8.5F, -8F, 2, 4, 1, 0.0F);
        LeftEar.setPivot(-2.5F, -4.5F, 3.5F);
        LeftEar.mirror = true;
        LeftEar.roll = 0.7853981F;
        LeftEar.pitch = -0.5235988F;
        Torso = new ModelPart(0, 7);
        Torso.addCuboid(-1.5F, -8F, -3.5F, 5, 4, 5, 2.4F);
        Torso.setPivot(0.0F, 2.0F, 0.0F);
        Torso.pitch = 1.047198F;
        Torso2 = new ModelPart(20, 7);
        Torso2.addCuboid(-1.5F, -8F, -3.5F, 5, 4, 5, 2.4F);
        Torso2.setPivot(0.0F, 3.5F, 12F);
        Torso2.pitch = 0.6108652F;
        Abdomen = new ModelPart(0, 16);
        Abdomen.addCuboid(-1F, 2.5F, 6F, 4, 5, 4, 2.4F);
        Abdomen.setPivot(0.0F, 2.0F, 0.0F);
        Abdomen.pitch = 0.4363323F;
        RightArm = new ModelPart(48, 0);
        RightArm.addCuboid(-8F, 0.0F, 0.0F, 4, 16, 4, 0.0F);
        RightArm.setPivot(0.0F, -6F, 0.0F);
        LeftArm = new ModelPart(48, 0);
        LeftArm.addCuboid(6F, 0.0F, 0.0F, 4, 16, 4, 0.0F);
        LeftArm.setPivot(0.0F, -6F, 0.0F);
        LeftArm.mirror = true;
        RightTigh = new ModelPart(32, 20);
        RightTigh.addCuboid(0.0F, 0.0F, 0.0F, 4, 8, 4, 0.0F);
        RightTigh.setPivot(-3.5F, 8F, 9F);
        RightTigh.pitch = -0.7853981F;
        LeftTigh = new ModelPart(32, 20);
        LeftTigh.addCuboid(0.0F, 0.0F, 0.0F, 4, 8, 4, 0.0F);
        LeftTigh.setPivot(1.5F, 8F, 9F);
        LeftTigh.mirror = true;
        LeftTigh.pitch = -0.7853981F;
        RightLeg = new ModelPart(48, 20);
        RightLeg.addCuboid(2.0F, 8F, 0.0F, 4, 8, 4, 0.0F);
        RightLeg.setPivot(-5.5F, 6F, -3F);
        LeftLeg = new ModelPart(48, 20);
        LeftLeg.addCuboid(2.0F, 8F, 0.0F, 4, 8, 4, 0.0F);
        LeftLeg.setPivot(-0.5F, 6F, -3F);
        LeftLeg.mirror = true;
        Tail = new ModelPart(9, 22);
        Tail.addCuboid(0.0F, -2F, 16F, 2, 2, 8, 1.0F);
        Tail.setPivot(0.0F, 2.0F, 0.0F);
    }

    public void render(float f, float f1, float f2, float f3, float f4, float f5, boolean flag)
    {
        ModelBase(f, f1, f2, f3, f4, f5, flag);
        Head.render(f5);
        RightEar.render(f5);
        LeftEar.render(f5);
        Torso.render(f5);
        Torso2.render(f5);
        Abdomen.render(f5);
        RightArm.render(f5);
        LeftArm.render(f5);
        RightTigh.render(f5);
        LeftTigh.render(f5);
        RightLeg.render(f5);
        LeftLeg.render(f5);
        Tail.render(f5);
    }

    public void ModelBase(float f, float f1, float f2, float f3, float f4, float f5, boolean flag)
    {
        Head.yaw = f3 / 57.29578F / 2.0F;
        Head.pitch = f4 / 57.29578F;
        RightEar.yaw = Head.pitch;
        RightEar.pitch = -0.5235988F - Head.yaw / 4F;
        RightEar.pivotX = 3.5F - Head.yaw * 2.0F;
        LeftEar.yaw = Head.pitch;
        LeftEar.pitch = -0.5235988F + Head.yaw / 4F;
        LeftEar.pivotX = -2.5F - Head.yaw * 2.0F;
        RightArm.pitch = MathHelper.cos(f * 0.6662F + 3.141593F) * 2.0F * f1 * 0.5F;
        LeftArm.pitch = MathHelper.cos(f * 0.6662F) * 2.0F * f1 * 0.5F;
        RightArm.roll = 0.0F;
        LeftArm.roll = 0.0F;
        RightTigh.pitch = -0.7853981F + (MathHelper.cos(f * 0.6662F) * 1.4F * f1) / 3F;
        LeftTigh.pitch = -0.7853981F + (MathHelper.cos(f * 0.6662F + 3.141593F) * 1.4F * f1) / 3F;
        RightLeg.pitch = (MathHelper.cos(f * 0.6662F) * 1.4F * f1) / 2.0F;
        RightLeg.pivotZ = 3F + (MathHelper.cos(f * 0.6662F) * 1.4F * f1) / 10F;
        RightLeg.pivotY = 7.5F + MathHelper.cos(f * 0.6662F) * 1.4F * f1;
        LeftLeg.pitch = (MathHelper.cos(f * 0.6662F + 3.141593F) * 1.4F * f1) / 2.0F;
        LeftLeg.pivotZ = 3F + (MathHelper.cos(f * 0.6662F + 3.141593F) * 1.4F * f1) / 10F;
        LeftLeg.pivotY = 7.5F + MathHelper.cos(f * 0.6662F + 3.141593F) * 1.4F * f1;
        RightLeg.yaw = 0.0F;
        LeftLeg.yaw = 0.0F;
        if(flag)
        {
            Tail.pitch = 0.261799F;
            Tail.pivotY = 10F;
            Tail.pivotZ = -2F;
            Abdomen.pitch = 1.308997F;
            Abdomen.pivotY = 13F;
            Abdomen.pivotZ = 0.0F;
            Torso.pitch = 1.919862F;
            Torso.pivotY = 4F;
            Torso.pivotZ = 1.0F;
            Torso2.pitch = 1.570796F;
            Torso2.pivotY = 5F;
            Torso2.pivotZ = 8.5F;
            RightArm.pivotY = 8F;
            RightArm.pivotZ = -6F;
            LeftArm.pivotY = 8F;
            LeftArm.pivotZ = -6F;
            Head.pivotY = 16F;
            Head.pivotZ = -7F;
            RightEar.pivotY = 12.5F;
            RightEar.pivotZ = -6F - Head.yaw * 2.0F;
            LeftEar.pivotY = 12F;
            LeftEar.pivotZ = -6F - Head.yaw * 2.0F;
        } else
        {
            Tail.pitch = -0.4363323F;
            Tail.pivotY = 1.0F;
            Tail.pivotZ = -1F;
            Abdomen.pitch = 0.4363323F;
            Abdomen.pivotY = 2.0F;
            Abdomen.pivotZ = 0.0F;
            Torso.pitch = 1.047198F;
            Torso.pivotY = -3F;
            Torso.pivotZ = 9F;
            Torso2.pitch = 0.6108652F;
            Torso2.pivotY = 3.5F;
            Torso2.pivotZ = 12F;
            RightArm.pivotY = -7F;
            RightArm.pivotZ = 0.0F;
            LeftArm.pivotY = -7F;
            LeftArm.pivotZ = 0.0F;
            Head.pivotY = -0.5F;
            Head.pivotZ = 1.5F;
            RightEar.pivotY = -4F;
            RightEar.pivotZ = 2.5F - Head.yaw * 2.0F;
            LeftEar.pivotY = -4.5F;
            LeftEar.pivotZ = 2.5F - Head.yaw * 2.0F;
        }
        RightArm.roll += MathHelper.cos(f2 * 0.09F) * 0.05F + 0.05F;
        LeftArm.roll -= MathHelper.cos(f2 * 0.09F) * 0.05F + 0.05F;
        RightArm.pitch += MathHelper.sin(f2 * 0.067F) * 0.05F;
        LeftArm.pitch -= MathHelper.sin(f2 * 0.067F) * 0.05F;
    }

    public ModelPart Head;
    public ModelPart Torso;
    public ModelPart Torso2;
    public ModelPart Abdomen;
    public ModelPart RightArm;
    public ModelPart LeftArm;
    public ModelPart RightTigh;
    public ModelPart LeftTigh;
    public ModelPart RightLeg;
    public ModelPart LeftLeg;
    public ModelPart Tail;
    public ModelPart RightEar;
    public ModelPart LeftEar;
}
