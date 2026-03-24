package net.kozibrodka.mocreatures.renderentity;

import net.kozibrodka.mocreatures.entity.EntityCrocodile;
import net.kozibrodka.mocreatures.modelentity.ModelCrocodile;
import net.minecraft.block.material.Material;
import net.minecraft.client.render.entity.LivingEntityRenderer;
import net.minecraft.entity.LivingEntity;
import net.minecraft.util.math.MathHelper;
import org.lwjgl.opengl.GL11;

public class RenderCrocodile extends LivingEntityRenderer {
	public ModelCrocodile croc;

	public RenderCrocodile(ModelCrocodile modelbase, float f) {
		super(modelbase, f);
		this.croc = modelbase;
	}

//	public void doRenderLiving(EntityLiving entityliving, double d, double d1, double d2, float f, float f1) {
//		MoCEntityCrocodile entitycrocodile = (MoCEntityCrocodile)entityliving;
//		super.doRender(entitycrocodile, d, d1, d2, f, f1);
//	}

	protected void applyScale(LivingEntity entityliving, float f) {
		EntityCrocodile entitycrocodile = (EntityCrocodile)entityliving;
		if(entitycrocodile.world.isRemote){
			this.croc.biteProgress = entitycrocodile.biteProgress;
		}else{
			this.croc.biteProgress = entitycrocodile.getBiteProgress();
		}
//		this.croc.biteProgress = entitycrocodile.biteProgress;
		this.croc.swimming = entitycrocodile.isSwimming();
		this.croc.resting = entitycrocodile.getIsSitting();
		if(entitycrocodile.isSpinning()) {
			this.spinCroc(entitycrocodile, (LivingEntity)entitycrocodile.passenger);
		}

		this.stretch(entitycrocodile);
		if(entitycrocodile.getIsSitting() && !entitycrocodile.isInFluid(Material.WATER)) {
			this.adjustHeight(entitycrocodile, 0.2F);
		}

	}

	protected void rotateAnimal(EntityCrocodile entitycrocodile) {
	}

	protected void adjustHeight(LivingEntity entityliving, float FHeight) {
		GL11.glTranslatef(0.0F, FHeight, 0.0F);
	}

	protected void spinCroc(EntityCrocodile croc, LivingEntity prey) {
		int intSpin = croc.spinInt;
		byte direction = 1;
		if(intSpin > 40) {
			intSpin -= 40;
			direction = -1;
		}

		int intEndSpin = intSpin;
		if(intSpin >= 20) {
			intEndSpin = 20 - (intSpin - 20);
		}

		if(intEndSpin == 0) {
			intEndSpin = 1;
		}

		float f3 = ((float)intEndSpin - 1.0F) / 20.0F * 1.6F;
		f3 = MathHelper.sqrt(f3);
		if(f3 > 1.0F) {
			f3 = 1.0F;
		}

		f3 *= (float)direction;
		GL11.glRotatef(f3 * 90.0F, 0.0F, 0.0F, 1.0F);
		if(prey != null) {
			prey.deathTime = intEndSpin;
		}

	}

	protected void stretch(EntityCrocodile entitycrocodile) {
		float f = entitycrocodile.getAge();
		GL11.glScalef(f, f, f);
	}
}
