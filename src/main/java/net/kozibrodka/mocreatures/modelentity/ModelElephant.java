package net.kozibrodka.mocreatures.modelentity;

import net.minecraft.client.model.ModelPart;
import net.minecraft.client.render.entity.model.QuadrupedEntityModel;

public class ModelElephant extends QuadrupedEntityModel
{

    public ModelElephant()
    {
        super(12, 0.0F);
        head = new ModelPart(0, 0);
        head.addCuboid(-4F, -5F, -10F, 8, 7, 9, 0.0F);
        head.setPivot(0.0F, 7F, -4F);
        head.pitch = 0.0F;
        head.yaw = 0.0F;
        head.roll = 0.0F;
        head.mirror = true;
        body = new ModelPart(22, 5);
        body.addCuboid(-5F, -10F, -7F, 10, 16, 11, 0.0F);
        body.setPivot(0.0F, 9F, 3F);
        body.pitch = 1.570796F;
        body.yaw = 0.0F;
        body.roll = 0.0F;
        body.mirror = true;
        rightHindLeg = new ModelPart(0, 16);
        rightHindLeg.addCuboid(-2F, 0.0F, -2F, 3, 8, 3, 0.0F);
        rightHindLeg.setPivot(-3F, 16F, 8F);
        rightHindLeg.pitch = 0.0F;
        rightHindLeg.yaw = 0.0F;
        rightHindLeg.roll = 0.0F;
        rightHindLeg.mirror = false;
        leftHindLeg = new ModelPart(0, 16);
        leftHindLeg.addCuboid(-2F, 0.0F, -2F, 3, 8, 3, 0.0F);
        leftHindLeg.setPivot(4F, 16F, 8F);
        leftHindLeg.pitch = 0.0F;
        leftHindLeg.yaw = 0.0F;
        leftHindLeg.roll = 0.0F;
        leftHindLeg.mirror = false;
        rightFrontLeg = new ModelPart(0, 16);
        rightFrontLeg.addCuboid(-2F, 0.0F, -2F, 3, 8, 3, 0.0F);
        rightFrontLeg.setPivot(-3F, 16F, -5F);
        rightFrontLeg.pitch = 0.0F;
        rightFrontLeg.yaw = 0.0F;
        rightFrontLeg.roll = 0.0F;
        rightFrontLeg.mirror = false;
        leftFrontLeg = new ModelPart(0, 16);
        leftFrontLeg.addCuboid(-2F, 0.0F, -2F, 3, 8, 3, 0.0F);
        leftFrontLeg.setPivot(4F, 16F, -5F);
        leftFrontLeg.pitch = 0.0F;
        leftFrontLeg.yaw = 0.0F;
        leftFrontLeg.roll = 0.0F;
        leftFrontLeg.mirror = false;
        trunk1 = new ModelPart(14, 14);
        trunk1.addCuboid(-1F, -2F, -11F, 2, 16, 2, 0.0F);
        trunk1.setPivot(0.0F, 7F, -4F);
        trunk1.pitch = 0.0F;
        trunk1.yaw = 0.0F;
        trunk1.roll = 0.0F;
        trunk1.mirror = true;
    }

    public void render(float f, float f1, float f2, float f3, float f4, float f5)
    {
        super.render(f, f1, f2, f3, f4, f5);
        setAngles(f, f1, f2, f3, f4, f5);
        trunk1.render(f5);
    }

    public void setAngles(float f, float f1, float f2, float f3, float f4, float f5)
    {
        super.setAngles(f, f1, f2, f3, f4, f5);
        trunk1.yaw = head.yaw;
        trunk1.pitch = head.pitch;
    }

    ModelPart trunk1;
}