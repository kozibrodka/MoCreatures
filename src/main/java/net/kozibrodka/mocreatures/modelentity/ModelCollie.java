package net.kozibrodka.mocreatures.modelentity;

import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.kozibrodka.mocreatures.entity.EntityCollie;
import net.minecraft.client.model.ModelPart;
import net.minecraft.client.render.entity.model.EntityModel;
import net.minecraft.entity.LivingEntity;
import net.minecraft.util.math.MathHelper;
import org.lwjgl.opengl.GL11;

public class ModelCollie extends EntityModel {
    public ModelPart head;
    public ModelPart torso;
    public ModelPart rightHindLeg;
    public ModelPart leftHindLeg;
    public ModelPart rightFrontLeg;
    public ModelPart leftFrontLeg;
    ModelPart field_2095;
    ModelPart field_2096;
    ModelPart field_2097;
    ModelPart tail;
    ModelPart neck;

    public ModelCollie() {
        float var1 = 0.0F;
        float var2 = 13.5F;
        this.head = new ModelPart(0, 0);
        this.head.addCuboid(-3.0F, -3.0F, -2.0F, 6, 6, 4, var1);
        this.head.setPivot(-1.0F, var2, -7.0F);
        this.torso = new ModelPart(18, 14);
        this.torso.addCuboid(-4.0F, -2.0F, -3.0F, 6, 9, 6, var1);
        this.torso.setPivot(0.0F, 14.0F, 2.0F);
        this.neck = new ModelPart(21, 0);
        this.neck.addCuboid(-4.0F, -3.0F, -3.0F, 8, 6, 7, var1);
        this.neck.setPivot(-1.0F, 14.0F, 2.0F);
        this.rightHindLeg = new ModelPart(0, 18);
        this.rightHindLeg.addCuboid(-1.0F, 0.0F, -1.0F, 2, 8, 2, var1);
        this.rightHindLeg.setPivot(-2.5F, 16.0F, 7.0F);
        this.leftHindLeg = new ModelPart(0, 18);
        this.leftHindLeg.addCuboid(-1.0F, 0.0F, -1.0F, 2, 8, 2, var1);
        this.leftHindLeg.setPivot(0.5F, 16.0F, 7.0F);
        this.rightFrontLeg = new ModelPart(0, 18);
        this.rightFrontLeg.addCuboid(-1.0F, 0.0F, -1.0F, 2, 8, 2, var1);
        this.rightFrontLeg.setPivot(-2.5F, 16.0F, -4.0F);
        this.leftFrontLeg = new ModelPart(0, 18);
        this.leftFrontLeg.addCuboid(-1.0F, 0.0F, -1.0F, 2, 8, 2, var1);
        this.leftFrontLeg.setPivot(0.5F, 16.0F, -4.0F);
        this.tail = new ModelPart(9, 18);
        this.tail.addCuboid(-1.0F, 0.0F, -1.0F, 2, 8, 2, var1);
        this.tail.setPivot(-1.0F, 12.0F, 8.0F);
        this.field_2095 = new ModelPart(16, 14);
        this.field_2095.addCuboid(-3.0F, -5.0F, 0.0F, 2, 2, 1, var1);
        this.field_2095.setPivot(-1.0F, var2, -7.0F);
        this.field_2096 = new ModelPart(16, 14);
        this.field_2096.addCuboid(1.0F, -5.0F, 0.0F, 2, 2, 1, var1);
        this.field_2096.setPivot(-1.0F, var2, -7.0F);
        this.field_2097 = new ModelPart(0, 10);
        this.field_2097.addCuboid(-2.0F, 0.0F, -5.0F, 3, 3, 4, var1);
        this.field_2097.setPivot(-0.5F, var2, -7.0F);
    }

    public void render(float limbAngle, float limbDistance, float animationProgress, float headYaw, float headPitch, float scale) {
        super.render(limbAngle, limbDistance, animationProgress, headYaw, headPitch, scale);
        this.setAngles(limbAngle, limbDistance, animationProgress, headYaw, headPitch, scale);
        this.head.renderForceTransform(scale);
        this.torso.render(scale);
        this.rightHindLeg.render(scale);
        this.leftHindLeg.render(scale);
        this.rightFrontLeg.render(scale);
        this.leftFrontLeg.render(scale);
        this.field_2095.renderForceTransform(scale);
        this.field_2096.renderForceTransform(scale);
        this.field_2097.renderForceTransform(scale);
        this.tail.renderForceTransform(scale);
        this.neck.render(scale);
    }

    public void animateModel(LivingEntity entity, float limbAngle, float limbDistance, float tickDelta) {
        EntityCollie var5 = (EntityCollie)entity;
        if (var5.isAngry()) {
            this.tail.yaw = 0.0F;
        } else {
            this.tail.yaw = MathHelper.cos(limbAngle * 0.6662F) * 1.4F * limbDistance;
        }

        if (var5.isInSittingPose()) {
            this.neck.setPivot(-1.0F, 16.0F, -3.0F);
            this.neck.pitch = 1.2566371F;
            this.neck.yaw = 0.0F;
            this.torso.setPivot(0.0F, 18.0F, 0.0F);
            this.torso.pitch = ((float)Math.PI / 4F);
            this.tail.setPivot(-1.0F, 21.0F, 6.0F);
            this.rightHindLeg.setPivot(-2.5F, 22.0F, 2.0F);
            this.rightHindLeg.pitch = ((float)Math.PI * 1.5F);
            this.leftHindLeg.setPivot(0.5F, 22.0F, 2.0F);
            this.leftHindLeg.pitch = ((float)Math.PI * 1.5F);
            this.rightFrontLeg.pitch = 5.811947F;
            this.rightFrontLeg.setPivot(-2.49F, 17.0F, -4.0F);
            this.leftFrontLeg.pitch = 5.811947F;
            this.leftFrontLeg.setPivot(0.51F, 17.0F, -4.0F);
        } else {
            this.torso.setPivot(0.0F, 14.0F, 2.0F);
            this.torso.pitch = ((float)Math.PI / 2F);
            this.neck.setPivot(-1.0F, 14.0F, -3.0F);
            this.neck.pitch = this.torso.pitch;
            this.tail.setPivot(-1.0F, 12.0F, 8.0F);
            this.rightHindLeg.setPivot(-2.5F, 16.0F, 7.0F);
            this.leftHindLeg.setPivot(0.5F, 16.0F, 7.0F);
            this.rightFrontLeg.setPivot(-2.5F, 16.0F, -4.0F);
            this.leftFrontLeg.setPivot(0.5F, 16.0F, -4.0F);
            this.rightHindLeg.pitch = MathHelper.cos(limbAngle * 0.6662F) * 1.4F * limbDistance;
            this.leftHindLeg.pitch = MathHelper.cos(limbAngle * 0.6662F + (float)Math.PI) * 1.4F * limbDistance;
            this.rightFrontLeg.pitch = MathHelper.cos(limbAngle * 0.6662F + (float)Math.PI) * 1.4F * limbDistance;
            this.leftFrontLeg.pitch = MathHelper.cos(limbAngle * 0.6662F) * 1.4F * limbDistance;
        }

        float var6 = var5.getBegAnimationProgress(tickDelta) + var5.getShakeAnimationProgress(tickDelta, 0.0F);
        this.head.roll = var6;
        this.field_2095.roll = var6;
        this.field_2096.roll = var6;
        this.field_2097.roll = var6;
        this.neck.roll = var5.getShakeAnimationProgress(tickDelta, -0.08F);
        this.torso.roll = var5.getShakeAnimationProgress(tickDelta, -0.16F);
        this.tail.roll = var5.getShakeAnimationProgress(tickDelta, -0.2F);
        if (var5.isFurWet()) {
            float var7 = var5.getBrightnessAtEyes(tickDelta) * var5.getFurBrightnessMultiplier(tickDelta);
            GL11.glColor3f(var7, var7, var7);
        }

    }

    public void setAngles(float limbAngle, float limbDistance, float animationProgress, float headYaw, float headPitch, float scale) {
        super.setAngles(limbAngle, limbDistance, animationProgress, headYaw, headPitch, scale);
        this.head.pitch = headPitch / (180F / (float)Math.PI);
        this.head.yaw = headYaw / (180F / (float)Math.PI);
        this.field_2095.yaw = this.head.yaw;
        this.field_2095.pitch = this.head.pitch;
        this.field_2096.yaw = this.head.yaw;
        this.field_2096.pitch = this.head.pitch;
        this.field_2097.yaw = this.head.yaw;
        this.field_2097.pitch = this.head.pitch;
        this.tail.pitch = animationProgress;
    }
}

