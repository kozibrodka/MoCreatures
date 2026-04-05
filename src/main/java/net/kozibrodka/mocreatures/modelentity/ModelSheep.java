package net.kozibrodka.mocreatures.modelentity;

import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.minecraft.client.model.ModelPart;
import net.minecraft.client.render.entity.model.QuadrupedEntityModel;
import net.minecraft.util.math.MathHelper;

@Environment(EnvType.CLIENT)
public class ModelSheep extends QuadrupedEntityModel {
    public ModelSheep() {
        super(12, 0.0F);
        head = new ModelPart(0, 0);
        head.addCuboid(-3.0F, -4.0F, -6.0F, 6, 6, 8, 0.0F);
        head.setPivot(0.0F, 6.0F, -8.0F);
        Collar = new ModelPart(24, 0);
        Collar.addCuboid(-2.5F, 4F, -3F, 5, 4, 1, 0.0F); //z = -3
        Collar.setPivot(0.0F, 4F, -8F);
        body = new ModelPart(28, 8);
        body.addCuboid(-4.0F, -10.0F, -7.0F, 8, 16, 6, 0.0F);
        body.setPivot(0.0F, 5.0F, 2.0F);
    }

    @Override
    public void render(float limbAngle, float limbDistance, float animationProgress, float headYaw, float headPitch, float scale) {
        setAngles(limbAngle, limbDistance, animationProgress, headYaw, headPitch, scale);
        head.render(scale);
        body.render(scale);
        rightHindLeg.render(scale);
        leftHindLeg.render(scale);
        rightFrontLeg.render(scale);
        leftFrontLeg.render(scale);
        if(tamed)
        {
            Collar.render(scale);
        }
    }

    @Override
    public void setAngles(float limbAngle, float limbDistance, float animationProgress, float headYaw, float headPitch, float scale) {

        if(eating) {
            head.pitch = ((headPitch + 60F) / (180F / (float) Math.PI));
            head.yaw = headYaw / (180F / (float) Math.PI);
        }else{
            head.pitch = headPitch / (180F / (float) Math.PI);
            head.yaw = headYaw / (180F / (float) Math.PI);
        }

        body.pitch = ((float)Math.PI / 2F);
        rightHindLeg.pitch = MathHelper.cos(limbAngle * 0.6662F) * 1.4F * limbDistance;
        leftHindLeg.pitch = MathHelper.cos(limbAngle * 0.6662F + (float)Math.PI) * 1.4F * limbDistance;
        rightFrontLeg.pitch = MathHelper.cos(limbAngle * 0.6662F + (float)Math.PI) * 1.4F * limbDistance;
        leftFrontLeg.pitch = MathHelper.cos(limbAngle * 0.6662F) * 1.4F * limbDistance;
        Collar.yaw = head.yaw;
        Collar.pitch = head.pitch;
    }

    ModelPart Collar;
    public boolean tamed;
    public boolean eating;
}
