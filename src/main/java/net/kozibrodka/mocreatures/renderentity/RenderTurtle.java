package net.kozibrodka.mocreatures.renderentity;

import net.kozibrodka.mocreatures.entity.EntityTurtle;
import net.kozibrodka.mocreatures.events.mod_mocreatures;
import net.kozibrodka.mocreatures.modelentity.ModelTurtle;
import net.minecraft.block.material.Material;
import net.minecraft.client.font.TextRenderer;
import net.minecraft.client.render.Tessellator;
import net.minecraft.client.render.entity.LivingEntityRenderer;
import net.minecraft.entity.Entity;
import net.minecraft.entity.LivingEntity;
import org.lwjgl.opengl.GL11;

public class RenderTurtle extends LivingEntityRenderer {
	public ModelTurtle turtly;

	public RenderTurtle(ModelTurtle modelbase, float f) {
		super(modelbase, f);
		this.turtly = modelbase;
	}

	@Override
	protected void applyScale(LivingEntity entityliving, float f) {
		EntityTurtle entityturtle = (EntityTurtle)entityliving;
		this.turtly.upsidedown = entityturtle.getUpsideDown();
		/// alternatywnie, niepłynna animacja ale skordynowana z serwerem
//		this.turtly.swingProgress = entityturtle.getSwingProgress();
		this.turtly.swingProgress = entityturtle.swingProgress;
		this.turtly.isHiding = entityturtle.getHiding();
		if(entityturtle.getHiding()) {
			this.adjustHeight(entityturtle, 0.15F * entityturtle.getAge());
		} else if(!entityturtle.getHiding() && !entityturtle.getUpsideDown() && !entityturtle.isInFluid(Material.WATER)) {
			this.adjustHeight(entityturtle, 0.05F * entityturtle.getAge());
		}

		if(entityturtle.getUpsideDown()) {
			this.rotateAnimal(entityturtle);
		}

		this.stretch(entityturtle);
	}

	protected void rotateAnimal(EntityTurtle entityturtle) {
//		float f = entityturtle.getSwingProgress() * 10.0F * (float)entityturtle.getFlipDirection();
		float f = entityturtle.swingProgress * 10.0F * (float)entityturtle.getFlipDirection();
//		float f2 = entityturtle.getSwingProgress() / 30.0F * (float)entityturtle.getFlipDirection();
		float f2 = entityturtle.swingProgress / 30.0F * (float)entityturtle.getFlipDirection();
		GL11.glRotatef(180.0F + f, 0.0F, 0.0F, -1.0F);
		GL11.glTranslatef(0.0F - f2, 0.5F * entityturtle.getAge(), 0.0F);
	}

	protected void adjustHeight(LivingEntity entityliving, float height) {
		GL11.glTranslatef(0.0F, height, 0.0F);
	}

	protected void stretch(EntityTurtle entityturtle) {
		float f = entityturtle.getAge();
		GL11.glScalef(f, f, f);
	}

	@Override
	public void render(Entity entity, double d, double d1, double d2,
					   float f, float f1)
	{
		doRenderLiving((EntityTurtle)entity, d, d1, d2, f, f1);
	}

	public void doRenderLiving(LivingEntity entityliving, double d, double d1, double d2, float f, float f1) {
		EntityTurtle entityturtle = (EntityTurtle)entityliving;
		super.render(entityturtle, d, d1, d2, f, f1);
		boolean flag = mod_mocreatures.mocGlass.othersettings.displayname && !entityturtle.getName().isEmpty();
		boolean flag1 = mod_mocreatures.mocGlass.othersettings.displayhealth;
		if(entityturtle.renderName()) {
			float f2 = 1.6F;
			float f3 = 0.01666667F * f2;
			float f4 = entityturtle.getDistance(dispatcher.cameraEntity);
			if(f4 < 16.0F) {
				String s = "";
				s = s + entityturtle.getName();
				float f5 = 0.1F;
				TextRenderer fontrenderer = this.getTextRenderer();
				GL11.glPushMatrix();
				GL11.glTranslatef((float)d + 0.0F, (float)d1 + f5, (float)d2);
				GL11.glNormal3f(0.0F, 1.0F, 0.0F);
				GL11.glRotatef(-this.dispatcher.yaw, 0.0F, 1.0F, 0.0F);
				GL11.glScalef(-f3, -f3, f3);
				GL11.glDisable(GL11.GL_LIGHTING);
				Tessellator tessellator = Tessellator.INSTANCE;
				byte byte0 = (byte)((int)(-15.0F + -15.0F * entityturtle.getAge()));
				if(flag1) {
					GL11.glDisable(GL11.GL_TEXTURE_2D);
					if(!flag) {
						byte0 = (byte)(byte0 + 8);
					}

					tessellator.startQuads();
					float i = (float)entityturtle.getHealth();
					float f7 = (float)entityturtle.maxhealth;
					float f8 = i / f7;
					float f9 = 40.0F * f8;
					tessellator.color(0.7F, 0.0F, 0.0F, 1.0F);
					tessellator.vertex((double)(-20.0F + f9), (double)(-10 + byte0), 0.0D);
					tessellator.vertex((double)(-20.0F + f9), (double)(-6 + byte0), 0.0D);
					tessellator.vertex(20.0D, (double)(-6 + byte0), 0.0D);
					tessellator.vertex(20.0D, (double)(-10 + byte0), 0.0D);
					tessellator.color(0.0F, 0.7F, 0.0F, 1.0F);
					tessellator.vertex(-20.0D, (double)(-10 + byte0), 0.0D);
					tessellator.vertex(-20.0D, (double)(-6 + byte0), 0.0D);
					tessellator.vertex((double)(f9 - 20.0F), (double)(-6 + byte0), 0.0D);
					tessellator.vertex((double)(f9 - 20.0F), (double)(-10 + byte0), 0.0D);
					tessellator.draw();
					GL11.glEnable(GL11.GL_TEXTURE_2D);
				}

				if(flag) {
					GL11.glDepthMask(false);
					GL11.glDisable(GL11.GL_DEPTH_TEST);
					GL11.glEnable(GL11.GL_BLEND);
					GL11.glBlendFunc(GL11.GL_SRC_ALPHA, GL11.GL_ONE_MINUS_SRC_ALPHA);
					GL11.glDisable(GL11.GL_TEXTURE_2D);
					tessellator.startQuads();
					int i1 = fontrenderer.getWidth(s) / 2;
					tessellator.color(0.0F, 0.0F, 0.0F, 0.25F);
					tessellator.vertex((double)(-i1 - 1), (double)(-1 + byte0), 0.0D);
					tessellator.vertex((double)(-i1 - 1), (double)(8 + byte0), 0.0D);
					tessellator.vertex((double)(i1 + 1), (double)(8 + byte0), 0.0D);
					tessellator.vertex((double)(i1 + 1), (double)(-1 + byte0), 0.0D);
					tessellator.draw();
					GL11.glEnable(GL11.GL_TEXTURE_2D);
					fontrenderer.draw(s, -fontrenderer.getWidth(s) / 2, byte0, 553648127);
					GL11.glEnable(GL11.GL_DEPTH_TEST);
					GL11.glDepthMask(true);
					fontrenderer.draw(s, -fontrenderer.getWidth(s) / 2, byte0, -1);
					GL11.glDisable(GL11.GL_BLEND);
					GL11.glColor4f(1.0F, 1.0F, 1.0F, 1.0F);
				}

				GL11.glEnable(GL11.GL_LIGHTING);
				GL11.glPopMatrix();
			}
		}

	}
}
