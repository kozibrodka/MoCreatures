package net.kozibrodka.mocreatures.renderentity;

import net.kozibrodka.mocreatures.entity.EntityBigCat;
import net.kozibrodka.mocreatures.entity.EntityCollie;
import net.kozibrodka.mocreatures.entity.EntityCrocodile;
import net.kozibrodka.mocreatures.modelentity.ModelCollie;
import net.kozibrodka.mocreatures.modelentity.ModelCrocodile;
import net.minecraft.block.material.Material;
import net.minecraft.client.render.entity.LivingEntityRenderer;
import net.minecraft.client.render.entity.model.EntityModel;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.passive.SheepEntity;
import net.minecraft.util.math.MathHelper;
import org.lwjgl.opengl.GL11;


public class RenderCollie extends LivingEntityRenderer {

    public RenderCollie(ModelCollie entityModel, float f) {
        super(entityModel, f);
        setDecorationModel(entityModel);
    }

//    public void render(EntityCollie wolfEntity, double d, double e, double f, float g, float h) {
//        super.render(wolfEntity, d, e, f, g, h);
//    }

    protected float getHeadBob(LivingEntity entityliving, float f) {
        EntityCollie dog = (EntityCollie)entityliving;
        return dog.getTailAngle();
    }

//    protected void applyScale(EntityCollie entityliving, float f) {
//    }

    protected boolean bindTexture(LivingEntity entityliving, int i, float f) {
        EntityCollie dog = (EntityCollie)entityliving;
        if (dog.isTamed()) {
            this.bindTexture("/assets/mocreatures/stationapi/textures/mob/collie_collar.png");
            float var4 = dog.getBrightnessAtEyes(f);
            int var5 = dog.getColor();
            GL11.glColor3f(var4 * SheepEntity.COLORS[var5][0], var4 * SheepEntity.COLORS[var5][1], var4 * SheepEntity.COLORS[var5][2]);
            return true;
        } else {
            return false;
        }
    }
}
