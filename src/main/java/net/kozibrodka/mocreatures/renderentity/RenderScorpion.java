package net.kozibrodka.mocreatures.renderentity;

import net.kozibrodka.mocreatures.entity.EntityScorpion;
import net.kozibrodka.mocreatures.modelentity.ModelScorpion;
import net.minecraft.client.render.entity.LivingEntityRenderer;
import net.minecraft.entity.LivingEntity;
import org.lwjgl.opengl.GL11;

public class RenderScorpion extends LivingEntityRenderer {
	public ModelScorpion scorpy;

	public RenderScorpion(ModelScorpion modelbase, float f) {
		super(modelbase, f);
		this.scorpy = modelbase;
	}

//	public void doRenderLiving(LivingEntity entityliving, double d, double d1, double d2, float f, float f1) {
//		MoCEntityScorpion entityscorpion = (MoCEntityScorpion)entityliving;
//		super.doRender(entityscorpion, d, d1, d2, f, f1);
//	}

	protected void applyScale(LivingEntity entityliving, float f) {
        EntityScorpion entityscorpion = (EntityScorpion)entityliving;
		this.scorpy.attacking = entityscorpion.swingingTail();
		this.scorpy.isSwinging = entityscorpion.getIsSwinging();
		this.scorpy.swingProgress = entityscorpion.swingProgress;
		if(entityscorpion.getClimbing()) {   // entityscorpion.climbing()
			this.rotateAnimal(entityscorpion);
		}

		if(entityscorpion.getAdult()){
			this.adjustHeight(entityscorpion);
		}

		this.stretch(entityscorpion);

	}

//	protected void upsideDown(LivingEntity entityliving) {
//		GL11.glRotatef(-90.0F, -1.0F, 0.0F, 0.0F);
//		if(mod_mocreatures.mc.isMultiplayerWorld() && entityliving.ridingEntity == mod_mocreatures.mc.thePlayer) {
//			GL11.glTranslatef(-0.55F, -1.9F, -0.7F);
//		} else {
//            GL11.glTranslatef(-1.555F, -0.5F, -0.5F);
//        }
//	}

	protected void adjustHeight(LivingEntity entityliving) {
		GL11.glTranslatef(0.0F, -0.2F, 0.0F);
	}

	protected void rotateAnimal(LivingEntity entityliving) {
		GL11.glRotatef(90.0F, -1.0F, 0.0F, 0.0F);
	}

	protected void stretch(EntityScorpion entityscorpion) {
//		float f = 1.1F;
		float f = entityscorpion.getAge();
		GL11.glScalef(f, f, f);
	}
}
