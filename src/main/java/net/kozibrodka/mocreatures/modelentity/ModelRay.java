package net.kozibrodka.mocreatures.modelentity;
import net.minecraft.client.model.ModelPart;
import net.minecraft.client.render.entity.model.EntityModel;
import net.minecraft.util.math.MathHelper;
import net.minecraft.entity.Entity;

public class ModelRay extends EntityModel {
	public int typeInt;
	public boolean attacking;
	ModelPart Tail;
	ModelPart Body;
	ModelPart Right;
	ModelPart Left;
	ModelPart BodyU;
	ModelPart RWingA;
	ModelPart RWingB;
	ModelPart RWingC;
	ModelPart RWingD;
	ModelPart RWingE;
	ModelPart RWingF;
	ModelPart BodyTail;
	ModelPart RWingG;
	ModelPart LWingA;
	ModelPart LWingB;
	ModelPart LWingC;
	ModelPart LWingD;
	ModelPart LWingE;
	ModelPart LWingF;
	ModelPart LWingG;
	ModelPart LEye;
	ModelPart REye;

	public ModelRay() {
//		this.textureWidth = 64;
//		this.textureHeight = 32;
		this.Body = new ModelPart(26, 0);
		this.Body.addCuboid(-4.0F, -1.0F, 0.0F, 8, 2, 11);
		this.Body.setPivot(0.0F, 22.0F, -5.0F);
		this.Right = new ModelPart(10, 26);
		this.Right.addCuboid(-0.5F, -1.0F, -4.0F, 1, 2, 4);
		this.Right.setPivot(-3.0F, 22.0F, -4.8F);
		this.Left = new ModelPart(0, 26);
		this.Left.addCuboid(-0.5F, -1.0F, -4.0F, 1, 2, 4);
		this.Left.setPivot(3.0F, 22.0F, -4.8F);
		this.BodyU = new ModelPart(0, 11);
		this.BodyU.addCuboid(-3.0F, -1.0F, 0.0F, 6, 1, 8);
		this.BodyU.setPivot(0.0F, 21.0F, -4.0F);
		this.Tail = new ModelPart(30, 15);
		this.Tail.addCuboid(-0.5F, -0.5F, 1.0F, 1, 1, 16);
		this.Tail.setPivot(0.0F, 22.0F, 8.0F);
		this.BodyTail = new ModelPart(0, 20);
		this.BodyTail.addCuboid(-1.8F, -0.5F, -3.2F, 5, 1, 5);
		this.BodyTail.setPivot(0.0F, 22.0F, 7.0F);
		this.setRotation(this.BodyTail, 0.0F, 1.0F, 0.0F);
		this.RWingA = new ModelPart(0, 0);
		this.RWingA.addCuboid(-3.0F, -0.5F, -5.0F, 3, 1, 10);
		this.RWingA.setPivot(-4.0F, 22.0F, 1.0F);
		this.RWingB = new ModelPart(2, 2);
		this.RWingB.addCuboid(-6.0F, -0.5F, -4.0F, 3, 1, 8);
		this.RWingB.setPivot(-4.0F, 22.0F, 1.0F);
		this.RWingC = new ModelPart(5, 4);
		this.RWingC.addCuboid(-8.0F, -0.5F, -3.0F, 2, 1, 6);
		this.RWingC.setPivot(-4.0F, 22.0F, 1.0F);
		this.RWingD = new ModelPart(6, 5);
		this.RWingD.addCuboid(-10.0F, -0.5F, -2.5F, 2, 1, 5);
		this.RWingD.setPivot(-4.0F, 22.0F, 1.0F);
		this.RWingE = new ModelPart(7, 6);
		this.RWingE.addCuboid(-12.0F, -0.5F, -2.0F, 2, 1, 4);
		this.RWingE.setPivot(-4.0F, 22.0F, 1.0F);
		this.RWingF = new ModelPart(8, 7);
		this.RWingF.addCuboid(-14.0F, -0.5F, -1.5F, 2, 1, 3);
		this.RWingF.setPivot(-4.0F, 22.0F, 1.0F);
		this.RWingG = new ModelPart(9, 8);
		this.RWingG.addCuboid(-16.0F, -0.5F, -1.0F, 2, 1, 2);
		this.RWingG.setPivot(-4.0F, 22.0F, 1.0F);
		this.LWingA = new ModelPart(0, 0);
		this.LWingA.addCuboid(0.0F, -0.5F, -5.0F, 3, 1, 10);
		this.LWingA.setPivot(4.0F, 22.0F, 1.0F);
		this.LWingA.mirror = true;
		this.LWingB = new ModelPart(2, 2);
		this.LWingB.addCuboid(3.0F, -0.5F, -4.0F, 3, 1, 8);
		this.LWingB.setPivot(4.0F, 22.0F, 1.0F);
		this.LWingB.mirror = true;
		this.LWingC = new ModelPart(5, 4);
		this.LWingC.addCuboid(6.0F, -0.5F, -3.0F, 2, 1, 6);
		this.LWingC.setPivot(4.0F, 22.0F, 1.0F);
		this.LWingC.mirror = true;
		this.LWingD = new ModelPart(6, 5);
		this.LWingD.addCuboid(8.0F, -0.5F, -2.5F, 2, 1, 5);
		this.LWingD.setPivot(4.0F, 22.0F, 1.0F);
		this.LWingD.mirror = true;
		this.LWingE = new ModelPart(7, 6);
		this.LWingE.addCuboid(10.0F, -0.5F, -2.0F, 2, 1, 4);
		this.LWingE.setPivot(4.0F, 22.0F, 1.0F);
		this.LWingE.mirror = true;
		this.LWingF = new ModelPart(8, 7);
		this.LWingF.addCuboid(12.0F, -0.5F, -1.5F, 2, 1, 3);
		this.LWingF.setPivot(4.0F, 22.0F, 1.0F);
		this.LWingF.mirror = true;
		this.LWingG = new ModelPart(9, 8);
		this.LWingG.addCuboid(14.0F, -0.5F, -1.0F, 2, 1, 2);
		this.LWingG.setPivot(4.0F, 22.0F, 1.0F);
		this.LWingG.mirror = true;
		this.LEye = new ModelPart(0, 0);
		this.LEye.addCuboid(-3.0F, -2.0F, 1.0F, 1, 1, 2);
		this.LEye.setPivot(0.0F, 21.0F, -4.0F);
		this.REye = new ModelPart(0, 3);
		this.REye.addCuboid(2.0F, -2.0F, 1.0F, 1, 1, 2);
		this.REye.setPivot(0.0F, 21.0F, -4.0F);
	}

	public void render(float f, float f1, float f2, float f3, float f4, float f5) {
		super.render(f, f1, f2, f3, f4, f5);
		this.setAngles(f, f1, f2, f3, f4, f5);
		this.Tail.render(f5);
		this.Body.render(f5);
		this.BodyU.render(f5);
		this.BodyTail.render(f5);
		this.RWingA.render(f5);
		this.RWingB.render(f5);
		this.LWingA.render(f5);
		this.LWingB.render(f5);
		if(this.typeInt == 1) {
			this.Right.render(f5);
			this.Left.render(f5);
			this.RWingC.render(f5);
			this.LWingC.render(f5);
			this.RWingD.render(f5);
			this.RWingE.render(f5);
			this.RWingF.render(f5);
			this.RWingG.render(f5);
			this.LWingD.render(f5);
			this.LWingE.render(f5);
			this.LWingF.render(f5);
			this.LWingG.render(f5);
		} else {
			this.REye.render(f5);
			this.LEye.render(f5);
		}

	}

	private void setRotation(ModelPart model, float x, float y, float z) {
		model.pitch = x;
		model.yaw = y;
		model.roll = z;
	}

	public void setAngles(float f, float f1, float f2, float f3, float f4, float f5) {
		super.setAngles(f, f1, f2, f3, f4, f5);
		float rotF = MathHelper.cos(f * 0.6662F) * 1.5F * f1;
		float f6 = 20.0F;
		this.Tail.yaw = rotF;
		this.RWingA.roll = rotF;
		this.LWingA.roll = -rotF;
		rotF += rotF / f6;
		this.RWingB.roll = rotF;
		this.LWingB.roll = -rotF;
		rotF += rotF / f6;
		this.RWingC.roll = rotF;
		this.LWingC.roll = -rotF;
		rotF += rotF / f6;
		this.RWingD.roll = rotF;
		this.LWingD.roll = -rotF;
		rotF += rotF / f6;
		this.RWingE.roll = rotF;
		this.LWingE.roll = -rotF;
		rotF += rotF / f6;
		this.RWingF.roll = rotF;
		this.LWingF.roll = -rotF;
		rotF += rotF / f6;
		this.RWingG.roll = rotF;
		this.LWingG.roll = -rotF;
		if(this.attacking) {
			this.Tail.pitch = 0.5F;
		} else {
			this.Tail.pitch = 0.0F;
		}

	}
}
