package net.kozibrodka.mocreatures.modelentity;
import net.minecraft.entity.Entity;
import net.minecraft.client.model.ModelPart;
import net.minecraft.client.render.entity.model.EntityModel;
import net.minecraft.util.math.MathHelper;

public class ModelTurtle extends EntityModel {
	ModelPart Shell = new ModelPart(28, 0);
	ModelPart ShellUp;
	ModelPart ShellTop;
	ModelPart Belly;
	ModelPart Leg1;
	ModelPart Leg2;
	ModelPart Leg3;
	ModelPart Leg4;
	ModelPart Head;
	ModelPart Tail;
	public boolean isHiding;
	public boolean upsidedown;
	public float swingProgress;

	public ModelTurtle() {
		this.Shell.addCuboid(0.0F, 0.0F, 0.0F, 9, 1, 9);
		this.Shell.setPivot(-4.5F, 19.0F, -4.5F);
		this.ShellUp = new ModelPart(0, 22);
		this.ShellUp.addCuboid(0.0F, 0.0F, 0.0F, 8, 2, 8);
		this.ShellUp.setPivot(-4.0F, 17.0F, -4.0F);
		this.ShellTop = new ModelPart(40, 10);
		this.ShellTop.addCuboid(0.0F, 0.0F, 0.0F, 6, 1, 6);
		this.ShellTop.setPivot(-3.0F, 16.0F, -3.0F);
		this.Belly = new ModelPart(0, 12);
		this.Belly.addCuboid(0.0F, 0.0F, 0.0F, 8, 1, 8);
		this.Belly.setPivot(-4.0F, 20.0F, -4.0F);
		this.Leg1 = new ModelPart(0, 0);
		this.Leg1.addCuboid(-1.0F, 0.0F, -1.0F, 2, 3, 2);
		this.Leg1.setPivot(3.5F, 20.0F, -3.5F);
		this.Leg2 = new ModelPart(0, 9);
		this.Leg2.addCuboid(-1.0F, 0.0F, -1.0F, 2, 3, 2);
		this.Leg2.setPivot(-3.5F, 20.0F, -3.5F);
		this.Leg3 = new ModelPart(0, 0);
		this.Leg3.addCuboid(-1.0F, 0.0F, -1.0F, 2, 3, 2);
		this.Leg3.setPivot(3.5F, 20.0F, 3.5F);
		this.Leg4 = new ModelPart(0, 9);
		this.Leg4.addCuboid(-1.0F, 0.0F, -1.0F, 2, 3, 2);
		this.Leg4.setPivot(-3.5F, 20.0F, 3.5F);
		this.Head = new ModelPart(10, 0);
		this.Head.addCuboid(-1.5F, -1.0F, -4.0F, 3, 2, 4);
		this.Head.setPivot(0.0F, 20.0F, -4.5F);
		this.Tail = new ModelPart(0, 5);
		this.Tail.addCuboid(-1.0F, -1.0F, 0.0F, 2, 1, 3);
		this.Tail.setPivot(0.0F, 21.0F, 4.0F);
	}

	@Override
	public void render(float f, float f1, float f2, float f3, float f4, float f5) {
		super.render(f, f1, f2, f3, f4, f5);
		this.setAngles(f, f1, f2, f3, f4, f5);
		this.Shell.render(f5);
		this.ShellUp.render(f5);
		this.ShellTop.render(f5);
		this.Belly.render(f5);
		this.Leg1.render(f5);
		this.Leg2.render(f5);
		this.Leg3.render(f5);
		this.Leg4.render(f5);
		this.Head.render(f5);
		this.Tail.render(f5);
	}

	@Override
	public void setAngles(float f, float f1, float f2, float f3, float f4, float f5) {
		super.setAngles(f, f1, f2, f3, f4, f5);
		this.Leg1.pitch = MathHelper.cos(f * 0.6662F) * 1.4F * f1;
		this.Leg2.pitch = MathHelper.cos(f * 0.6662F + 3.141593F) * 1.4F * f1;
		this.Leg3.pitch = MathHelper.cos(f * 0.6662F + 3.141593F) * 1.4F * f1;
		this.Leg4.pitch = MathHelper.cos(f * 0.6662F) * 1.4F * f1;
		this.Tail.yaw = MathHelper.cos(f * 0.6662F) * 0.7F * f1;
		if(this.upsidedown) {
			float f25 = this.swingProgress;
			float f26 = f25;
			if(f25 >= 0.8F) {
				f26 = 0.6F - (f25 - 0.8F);
			}

			if(f26 > 1.6F) {
				f26 = 1.6F;
			}

			if(f26 < -1.6F) {
				f26 = -1.6F;
			}

			this.Leg1.pitch = 0.0F - f26;
			this.Leg2.pitch = 0.0F + f26;
			this.Leg3.pitch = 0.0F + f26;
			this.Leg4.pitch = 0.0F - f26;
			this.Tail.yaw = 0.0F - f26;
		} else {
			this.Leg1.pitch = MathHelper.cos(f * 0.6662F) * 1.4F * f1;
			this.Leg2.pitch = MathHelper.cos(f * 0.6662F + 3.141593F) * 1.4F * f1;
			this.Leg3.pitch = MathHelper.cos(f * 0.6662F + 3.141593F) * 1.4F * f1;
			this.Leg4.pitch = MathHelper.cos(f * 0.6662F) * 1.4F * f1;
			this.Tail.yaw = MathHelper.cos(f * 0.6662F) * 0.7F * f1;
		}

		if(this.isHiding) {
			this.Head.pitch = 0.0F;
			this.Head.yaw = 0.0F;
			this.Leg1.pivotX = 2.9F;
			this.Leg1.pivotY = 18.5F;
			this.Leg1.pivotZ = -2.9F;
			this.Leg2.pivotX = -2.9F;
			this.Leg2.pivotY = 18.5F;
			this.Leg2.pivotZ = -2.9F;
			this.Leg3.pivotX = 2.9F;
			this.Leg3.pivotY = 18.5F;
			this.Leg3.pivotZ = 2.9F;
			this.Leg4.pivotX = -2.9F;
			this.Leg4.pivotY = 18.5F;
			this.Leg4.pivotZ = 2.9F;
			this.Head.pivotY = 19.5F;
			this.Head.pivotZ = -1.0F;
			this.Tail.pivotZ = 2.0F;
		} else {
			this.Head.pitch = f4 / 57.29578F;
			this.Head.yaw = f3 / 57.29578F;
			this.Leg1.pivotX = 3.5F;
			this.Leg1.pivotY = 20.0F;
			this.Leg1.pivotZ = -3.5F;
			this.Leg2.pivotX = -3.5F;
			this.Leg2.pivotY = 20.0F;
			this.Leg2.pivotZ = -3.5F;
			this.Leg3.pivotX = 3.5F;
			this.Leg3.pivotY = 20.0F;
			this.Leg3.pivotZ = 3.5F;
			this.Leg4.pivotX = -3.5F;
			this.Leg4.pivotY = 20.0F;
			this.Leg4.pivotZ = 3.5F;
			this.Head.pivotY = 20.0F;
			this.Head.pivotZ = -4.5F;
			this.Tail.pivotZ = 4.0F;
		}

	}
}
