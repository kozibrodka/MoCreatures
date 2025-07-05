package net.kozibrodka.mocreatures.renderentity;

import net.kozibrodka.mocreatures.entity.EntityBigCat;
import net.kozibrodka.mocreatures.events.mod_mocreatures;
import net.kozibrodka.mocreatures.modelentity.ModelBigCat1;
import net.kozibrodka.mocreatures.modelentity.ModelBigCat2;
import net.minecraft.client.font.TextRenderer;
import net.minecraft.client.render.Tessellator;
import net.minecraft.client.render.entity.LivingEntityRenderer;
import net.minecraft.entity.LivingEntity;
import net.minecraft.util.math.MathHelper;
import org.lwjgl.opengl.GL11;

public class RenderBigCat extends LivingEntityRenderer
{

    public RenderBigCat(ModelBigCat2 modelbigcat2, ModelBigCat1 modelbigcat1, float f)
    {
        super(modelbigcat2, f);
        setDecorationModel(modelbigcat1);
        bigcat1 = modelbigcat2;
    }

    protected void applyScale(LivingEntity entityliving, float f)
    {
        EntityBigCat entitybigcat = (EntityBigCat)entityliving;
        bigcat1.sitting = entitybigcat.getSitting();
        bigcat1.tamed = entitybigcat.getTamed();
    }

//    protected boolean a(EntityBigCat entitybigcat, int i)
//    {
//        if(entitybigcat.getType() == 2 && entitybigcat.getAdult())
//        {
//            bindTexture("/assets/mocreatures/stationapi/textures/mob/lionb.png");
//        } else
//        {
//            bindTexture("/assets/mocreatures/stationapi/textures/mob/lionc.png");
//        }
//        return i == 0;
//    }

//    protected boolean method_825(LivingEntity entityliving, int i, float f)
//    {
//        return a((EntityBigCat)entityliving, i);
//    }

    public void render(LivingEntity entityliving, double d, double d1, double d2,
            float f, float f1)
    {
        EntityBigCat entitybigcat = (EntityBigCat)entityliving;
        super.render(entitybigcat, d, d1, d2, f, f1);
        boolean flag = mocr.mocreaturesGlass.othersettings.displayname && !entitybigcat.getName().isEmpty();
        boolean flag1 = mocr.mocreaturesGlass.othersettings.displayhealth;
        boolean flag2 = mocr.mocreaturesGlass.othersettings.displayemo;
        if(entitybigcat.renderName())
        {
            float f2 = 1.6F;
            float f3 = 0.01666667F * f2;
            float f5 = entitybigcat.getDistance(dispatcher.cameraEntity);
            if(f5 < 16F)
            {
                String s = "";
                s = (new StringBuilder()).append(s).append(entitybigcat.getName()).toString();
                float f7 = 0.1F;
                TextRenderer fontrenderer = getTextRenderer();
                GL11.glPushMatrix();
                GL11.glTranslatef((float)d + 0.0F, (float)d1 + f7, (float)d2);
                GL11.glNormal3f(0.0F, 1.0F, 0.0F);
                GL11.glRotatef(-dispatcher.yaw, 0.0F, 1.0F, 0.0F);
                GL11.glScalef(-f3, -f3, f3);
                GL11.glDisable(2896 /*GL_LIGHTING*/);
                Tessellator tessellator1 = Tessellator.INSTANCE;
                byte byte0 = -60;
                if(flag1)
                {
                    GL11.glDisable(3553 /*GL_TEXTURE_2D*/);
                    if(!flag)
                    {
                        byte0 += 8;
                    }
                    tessellator1.startQuads();
                    float f8 = entitybigcat.health;
                    float f9 = entitybigcat.maxhealth;
                    float f10 = f8 / f9;
                    float f11 = 40F * f10;
                    tessellator1.color(0.7F, 0.0F, 0.0F, 1.0F);
                    tessellator1.vertex(-20F + f11, -10 + byte0, 0.0D);
                    tessellator1.vertex(-20F + f11, -6 + byte0, 0.0D);
                    tessellator1.vertex(20D, -6 + byte0, 0.0D);
                    tessellator1.vertex(20D, -10 + byte0, 0.0D);
                    tessellator1.color(0.0F, 0.7F, 0.0F, 1.0F);
                    tessellator1.vertex(-20D, -10 + byte0, 0.0D);
                    tessellator1.vertex(-20D, -6 + byte0, 0.0D);
                    tessellator1.vertex(f11 - 20F, -6 + byte0, 0.0D);
                    tessellator1.vertex(f11 - 20F, -10 + byte0, 0.0D);
                    tessellator1.draw();
                    GL11.glEnable(3553 /*GL_TEXTURE_2D*/);
                }
                if(flag)
                {
                    GL11.glDepthMask(false);
                    GL11.glDisable(2929 /*GL_DEPTH_TEST*/);
                    GL11.glEnable(3042 /*GL_BLEND*/);
                    GL11.glBlendFunc(770, 771);
                    GL11.glDisable(3553 /*GL_TEXTURE_2D*/);
                    tessellator1.startQuads();
                    int i = fontrenderer.getWidth(s) / 2;
                    tessellator1.color(0.0F, 0.0F, 0.0F, 0.25F);
                    tessellator1.vertex(-i - 1, -1 + byte0, 0.0D);
                    tessellator1.vertex(-i - 1, 8 + byte0, 0.0D);
                    tessellator1.vertex(i + 1, 8 + byte0, 0.0D);
                    tessellator1.vertex(i + 1, -1 + byte0, 0.0D);
                    tessellator1.draw();
                    GL11.glEnable(3553 /*GL_TEXTURE_2D*/);
                    fontrenderer.draw(s, -fontrenderer.getWidth(s) / 2, byte0, 0x20ffffff);
                    GL11.glEnable(2929 /*GL_DEPTH_TEST*/);
                    GL11.glDepthMask(true);
                    fontrenderer.draw(s, -fontrenderer.getWidth(s) / 2, byte0, -1);
                    GL11.glDisable(3042 /*GL_BLEND*/);
                    GL11.glColor4f(1.0F, 1.0F, 1.0F, 1.0F);
                }
                GL11.glEnable(2896 /*GL_LIGHTING*/);
                GL11.glPopMatrix();
            }
        }
        if(entitybigcat.roper != null)
        {
            Tessellator tessellator = Tessellator.INSTANCE;
            if(entitybigcat.getAdult())
            {
                entitybigcat.setAge(1.0F); //TODO: does it work?
            }
            d1 -= 0.40000000000000002D / (double)entitybigcat.getAge();
            float f4 = ((entitybigcat.roper.prevYaw + (entitybigcat.roper.yaw - entitybigcat.roper.prevYaw) * f1 * 0.5F) * 3.141593F) / 180F;
            float f6 = ((entitybigcat.roper.prevPitch + (entitybigcat.roper.pitch - entitybigcat.roper.prevPitch) * f1 * 0.5F) * 3.141593F) / 180F;
            double d3 = MathHelper.sin(f4);
            double d4 = MathHelper.cos(f4);
            double d5 = MathHelper.sin(f6);
            double d6 = MathHelper.cos(f6);
            double d7 = (entitybigcat.roper.prevX + (entitybigcat.roper.x - entitybigcat.roper.prevX) * (double)f1) - d4 * 0.69999999999999996D - d3 * 0.5D * d6;
            double d8 = (entitybigcat.roper.prevY + (entitybigcat.roper.y - entitybigcat.roper.prevY) * (double)f1) - d5 * 0.5D;
            double d9 = ((entitybigcat.roper.prevZ + (entitybigcat.roper.z - entitybigcat.roper.prevZ) * (double)f1) - d3 * 0.69999999999999996D) + d4 * 0.5D * d6;
            double d10 = entitybigcat.prevX + (entitybigcat.x - entitybigcat.prevX) * (double)f1;
            double d11 = entitybigcat.prevY + (entitybigcat.y - entitybigcat.prevY) * (double)f1 + 0.25D;
            double d12 = entitybigcat.prevZ + (entitybigcat.z - entitybigcat.prevZ) * (double)f1;
            double d13 = (float)(d7 - d10);
            double d14 = (float)(d8 - d11);
            double d15 = (float)(d9 - d12);
            GL11.glDisable(3553 /*GL_TEXTURE_2D*/);
            GL11.glDisable(2896 /*GL_LIGHTING*/);
            for(double d16 = 0.0D; d16 < 0.029999999999999999D; d16 += 0.01D)
            {
                tessellator.start(3);
                tessellator.color(0.5F, 0.4F, 0.3F, 1.0F);
                int j = 16;
                for(int k = 0; k <= j; k++)
                {
                    float f12 = (float)k / (float)j;
                    tessellator.vertex(d + d13 * (double)f12 + d16, d1 + d14 * (double)(f12 * f12 + f12) * 0.5D + (double)(((float)j - (float)k) / ((float)j * 0.75F) + 0.125F), d2 + d15 * (double)f12);
                }

                tessellator.draw();
            }

            GL11.glEnable(2896 /*GL_LIGHTING*/);
            GL11.glEnable(3553 /*GL_TEXTURE_2D*/);
        }
    }

    protected void stretch(EntityBigCat entitybigcat)
    {
        float f = entitybigcat.getAge();
        if(entitybigcat.getAdult())
        {
            f = 1.0F;
        }
        GL11.glScalef(f * entitybigcat.widthF, f * entitybigcat.heightF, f * entitybigcat.lengthF);
    }

    protected float getHeadBob(LivingEntity entityliving, float f)
    {
        EntityBigCat entitybigcat = (EntityBigCat)entityliving;
        stretch(entitybigcat);
        return (float)entityliving.age + f;
    }

    mod_mocreatures mocr = new mod_mocreatures();

    public ModelBigCat2 bigcat1;
}
