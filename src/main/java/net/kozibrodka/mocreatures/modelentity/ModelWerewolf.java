// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) braces deadcode 

package net.kozibrodka.mocreatures.modelentity;


import net.minecraft.client.model.Cuboid;
import net.minecraft.client.render.entity.model.EntityModelBase;
import net.minecraft.util.maths.MathHelper;

public class ModelWerewolf extends EntityModelBase
{

    public ModelWerewolf()
    {
        Head = new Cuboid(0, 0);
        Head.method_1818(-0.5F, -8.5F, -8F, 3, 3, 4, 1.6F);
        Head.setRotationPoint(0.0F, 2.0F, -1F);
        RightEar = new Cuboid(15, 0);
        RightEar.method_1818(-0.5F, -8.5F, -8F, 2, 4, 1, 0.0F);
        RightEar.setRotationPoint(3.5F, -4F, 3.5F);
        RightEar.roll = -0.7853981F;
        RightEar.pitch = -0.5235988F;
        LeftEar = new Cuboid(15, 0);
        LeftEar.method_1818(-0.5F, -8.5F, -8F, 2, 4, 1, 0.0F);
        LeftEar.setRotationPoint(-2.5F, -4.5F, 3.5F);
        LeftEar.mirror = true;
        LeftEar.roll = 0.7853981F;
        LeftEar.pitch = -0.5235988F;
        Torso = new Cuboid(0, 7);
        Torso.method_1818(-1.5F, -8F, -3.5F, 5, 4, 5, 2.4F);
        Torso.setRotationPoint(0.0F, 2.0F, 0.0F);
        Torso.pitch = 1.047198F;
        Torso2 = new Cuboid(20, 7);
        Torso2.method_1818(-1.5F, -8F, -3.5F, 5, 4, 5, 2.4F);
        Torso2.setRotationPoint(0.0F, 3.5F, 12F);
        Torso2.pitch = 0.6108652F;
        Abdomen = new Cuboid(0, 16);
        Abdomen.method_1818(-1F, 2.5F, 6F, 4, 5, 4, 2.4F);
        Abdomen.setRotationPoint(0.0F, 2.0F, 0.0F);
        Abdomen.pitch = 0.4363323F;
        RightArm = new Cuboid(48, 0);
        RightArm.method_1818(-8F, 0.0F, 0.0F, 4, 16, 4, 0.0F);
        RightArm.setRotationPoint(0.0F, -6F, 0.0F);
        LeftArm = new Cuboid(48, 0);
        LeftArm.method_1818(6F, 0.0F, 0.0F, 4, 16, 4, 0.0F);
        LeftArm.setRotationPoint(0.0F, -6F, 0.0F);
        LeftArm.mirror = true;
        RightTigh = new Cuboid(32, 20);
        RightTigh.method_1818(0.0F, 0.0F, 0.0F, 4, 8, 4, 0.0F);
        RightTigh.setRotationPoint(-3.5F, 8F, 9F);
        RightTigh.pitch = -0.7853981F;
        LeftTigh = new Cuboid(32, 20);
        LeftTigh.method_1818(0.0F, 0.0F, 0.0F, 4, 8, 4, 0.0F);
        LeftTigh.setRotationPoint(1.5F, 8F, 9F);
        LeftTigh.mirror = true;
        LeftTigh.pitch = -0.7853981F;
        RightLeg = new Cuboid(48, 20);
        RightLeg.method_1818(2.0F, 8F, 0.0F, 4, 8, 4, 0.0F);
        RightLeg.setRotationPoint(-5.5F, 6F, -3F);
        LeftLeg = new Cuboid(48, 20);
        LeftLeg.method_1818(2.0F, 8F, 0.0F, 4, 8, 4, 0.0F);
        LeftLeg.setRotationPoint(-0.5F, 6F, -3F);
        LeftLeg.mirror = true;
        Tail = new Cuboid(9, 22);
        Tail.method_1818(0.0F, -2F, 16F, 2, 2, 8, 1.0F);
        Tail.setRotationPoint(0.0F, 2.0F, 0.0F);
    }

    public void render(float f, float f1, float f2, float f3, float f4, float f5, boolean flag)
    {
        ModelBase(f, f1, f2, f3, f4, f5, flag);
        Head.method_1815(f5);
        RightEar.method_1815(f5);
        LeftEar.method_1815(f5);
        Torso.method_1815(f5);
        Torso2.method_1815(f5);
        Abdomen.method_1815(f5);
        RightArm.method_1815(f5);
        LeftArm.method_1815(f5);
        RightTigh.method_1815(f5);
        LeftTigh.method_1815(f5);
        RightLeg.method_1815(f5);
        LeftLeg.method_1815(f5);
        Tail.method_1815(f5);
    }

    public void ModelBase(float f, float f1, float f2, float f3, float f4, float f5, boolean flag)
    {
        Head.yaw = f3 / 57.29578F / 2.0F;
        Head.pitch = f4 / 57.29578F;
        RightEar.yaw = Head.pitch;
        RightEar.pitch = -0.5235988F - Head.yaw / 4F;
        RightEar.rotationPointX = 3.5F - Head.yaw * 2.0F;
        LeftEar.yaw = Head.pitch;
        LeftEar.pitch = -0.5235988F + Head.yaw / 4F;
        LeftEar.rotationPointX = -2.5F - Head.yaw * 2.0F;
        RightArm.pitch = MathHelper.cos(f * 0.6662F + 3.141593F) * 2.0F * f1 * 0.5F;
        LeftArm.pitch = MathHelper.cos(f * 0.6662F) * 2.0F * f1 * 0.5F;
        RightArm.roll = 0.0F;
        LeftArm.roll = 0.0F;
        RightTigh.pitch = -0.7853981F + (MathHelper.cos(f * 0.6662F) * 1.4F * f1) / 3F;
        LeftTigh.pitch = -0.7853981F + (MathHelper.cos(f * 0.6662F + 3.141593F) * 1.4F * f1) / 3F;
        RightLeg.pitch = (MathHelper.cos(f * 0.6662F) * 1.4F * f1) / 2.0F;
        RightLeg.rotationPointZ = 3F + (MathHelper.cos(f * 0.6662F) * 1.4F * f1) / 10F;
        RightLeg.rotationPointY = 7.5F + MathHelper.cos(f * 0.6662F) * 1.4F * f1;
        LeftLeg.pitch = (MathHelper.cos(f * 0.6662F + 3.141593F) * 1.4F * f1) / 2.0F;
        LeftLeg.rotationPointZ = 3F + (MathHelper.cos(f * 0.6662F + 3.141593F) * 1.4F * f1) / 10F;
        LeftLeg.rotationPointY = 7.5F + MathHelper.cos(f * 0.6662F + 3.141593F) * 1.4F * f1;
        RightLeg.yaw = 0.0F;
        LeftLeg.yaw = 0.0F;
        if(flag)
        {
            Tail.pitch = 0.261799F;
            Tail.rotationPointY = 10F;
            Tail.rotationPointZ = -2F;
            Abdomen.pitch = 1.308997F;
            Abdomen.rotationPointY = 13F;
            Abdomen.rotationPointZ = 0.0F;
            Torso.pitch = 1.919862F;
            Torso.rotationPointY = 4F;
            Torso.rotationPointZ = 1.0F;
            Torso2.pitch = 1.570796F;
            Torso2.rotationPointY = 5F;
            Torso2.rotationPointZ = 8.5F;
            RightArm.rotationPointY = 8F;
            RightArm.rotationPointZ = -6F;
            LeftArm.rotationPointY = 8F;
            LeftArm.rotationPointZ = -6F;
            Head.rotationPointY = 16F;
            Head.rotationPointZ = -7F;
            RightEar.rotationPointY = 12.5F;
            RightEar.rotationPointZ = -6F - Head.yaw * 2.0F;
            LeftEar.rotationPointY = 12F;
            LeftEar.rotationPointZ = -6F - Head.yaw * 2.0F;
        } else
        {
            Tail.pitch = -0.4363323F;
            Tail.rotationPointY = 1.0F;
            Tail.rotationPointZ = -1F;
            Abdomen.pitch = 0.4363323F;
            Abdomen.rotationPointY = 2.0F;
            Abdomen.rotationPointZ = 0.0F;
            Torso.pitch = 1.047198F;
            Torso.rotationPointY = -3F;
            Torso.rotationPointZ = 9F;
            Torso2.pitch = 0.6108652F;
            Torso2.rotationPointY = 3.5F;
            Torso2.rotationPointZ = 12F;
            RightArm.rotationPointY = -7F;
            RightArm.rotationPointZ = 0.0F;
            LeftArm.rotationPointY = -7F;
            LeftArm.rotationPointZ = 0.0F;
            Head.rotationPointY = -0.5F;
            Head.rotationPointZ = 1.5F;
            RightEar.rotationPointY = -4F;
            RightEar.rotationPointZ = 2.5F - Head.yaw * 2.0F;
            LeftEar.rotationPointY = -4.5F;
            LeftEar.rotationPointZ = 2.5F - Head.yaw * 2.0F;
        }
        RightArm.roll += MathHelper.cos(f2 * 0.09F) * 0.05F + 0.05F;
        LeftArm.roll -= MathHelper.cos(f2 * 0.09F) * 0.05F + 0.05F;
        RightArm.pitch += MathHelper.sin(f2 * 0.067F) * 0.05F;
        LeftArm.pitch -= MathHelper.sin(f2 * 0.067F) * 0.05F;
    }

    public Cuboid Head;
    public Cuboid Torso;
    public Cuboid Torso2;
    public Cuboid Abdomen;
    public Cuboid RightArm;
    public Cuboid LeftArm;
    public Cuboid RightTigh;
    public Cuboid LeftTigh;
    public Cuboid RightLeg;
    public Cuboid LeftLeg;
    public Cuboid Tail;
    public Cuboid RightEar;
    public Cuboid LeftEar;
}
