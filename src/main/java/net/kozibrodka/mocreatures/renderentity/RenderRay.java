package net.kozibrodka.mocreatures.renderentity;

import net.kozibrodka.mocreatures.entity.EntityRay;
import net.kozibrodka.mocreatures.modelentity.ModelRay;
import net.minecraft.client.render.entity.LivingEntityRenderer;
import net.minecraft.client.render.entity.model.EntityModel;
import net.minecraft.entity.LivingEntity;
import org.lwjgl.opengl.GL11;

public class RenderRay extends LivingEntityRenderer {
	private ModelRay tempRay;
	float depth = 0.0F;

	public RenderRay(EntityModel modelbase, float f) {
		super(modelbase, f);
		this.tempRay = (ModelRay)modelbase;
	}

	protected void applyScale(LivingEntity entityliving, float f) {

        EntityRay entityray = (EntityRay)entityliving;
        this.tempRay.typeInt = entityray.getType();

        if(!entityray.isSwimming()) {
            this.depth = 0.09F;
        } else if(this.tempRay.typeInt == 1) {
            this.depth = 0.15F;
        } else {
            this.depth = 0.25F;
        }

        this.tempRay.attacking = entityray.getAttacking();

//		GL11.glTranslatef(0.0F, this.depth, 0.0F); //TODO: zanóżenie pod nowy system aqua??
		this.stretch((EntityRay)entityliving);
	}
//
//	public void render(LivingEntity entityliving, double d, double d1, double d2, float f, float f1) {
//        EntityRay entityray = (EntityRay)entityliving; ///TODO to nie musi byc to, apply scale przenies
//		this.tempRay.typeInt = entityray.getType();
//		if(!entityray.isSwimming()) {
//			this.depth = 0.09F;
//		} else if(this.tempRay.typeInt == 1) {
//			this.depth = 0.15F;
//		} else {
//			this.depth = 0.25F;
//		}
//
//		this.tempRay.attacking = entityray.getAttacking();
//		super.render(entityliving, d, d1, d2, f, f1);
//	}

	protected void stretch(EntityRay entitymantray) {
        float f = entitymantray.getAge();
		GL11.glScalef(f,f,f);
	}
}
