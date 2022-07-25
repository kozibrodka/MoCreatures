package net.kozibrodka.mocreatures.renderentity;
import net.kozibrodka.mocreatures.entity.EntityShark;
import net.kozibrodka.mocreatures.events.mod_mocreatures;
import net.minecraft.client.render.Tessellator;
import net.minecraft.client.render.TextRenderer;
import net.minecraft.client.render.entity.LivingEntityRenderer;
import net.minecraft.client.render.entity.model.EntityModelBase;
import net.minecraft.entity.Living;
import org.lwjgl.opengl.GL11;


public class RenderShark extends LivingEntityRenderer
{

    public RenderShark(EntityModelBase modelbase, float f)
    {
        super(modelbase, f);
    }

    public void method_822(Living entityliving, double d, double d1, double d2,
                               float f, float f1)
    {
        EntityShark entityshark = (EntityShark)entityliving;
        super.method_822(entityshark, d, d1, d2, f, f1);
        boolean flag = mocr.mocreaturesGlass.othersettings.displayname && !entityshark.name.isEmpty();
        boolean flag1 = mocr.mocreaturesGlass.othersettings.displayhealth;
        boolean flag2 = mocr.mocreaturesGlass.othersettings.displayemo;
        if(entityshark.renderName())
        {
            float f2 = 1.6F;
            float f3 = 0.01666667F * f2;
            float f4 = entityshark.distanceTo(dispatcher.entity);
            if(f4 < 16F)
            {
                String s = "";
                s = (new StringBuilder()).append(s).append(entityshark.name).toString();
                float f5 = 0.1F;
                TextRenderer fontrenderer = method_2023();
                GL11.glPushMatrix();
                GL11.glTranslatef((float)d + 0.0F, (float)d1 + f5, (float)d2);
                GL11.glNormal3f(0.0F, 1.0F, 0.0F);
                GL11.glRotatef(-dispatcher.field_2497, 0.0F, 1.0F, 0.0F);
                GL11.glScalef(-f3, -f3, f3);
                GL11.glDisable(2896 /*GL_LIGHTING*/);
                Tessellator tessellator = Tessellator.INSTANCE;
                byte byte0 = -50;
                if(flag1)
                {
                    GL11.glDisable(3553 /*GL_TEXTURE_2D*/);
                    if(!flag)
                    {
                        byte0 += 8;
                    }
                    tessellator.start();
                    float f6 = entityshark.health;
                    float f7 = entityshark.maxhealth;
                    float f8 = f6 / f7;
                    float f9 = 40F * f8;
                    tessellator.colour(0.7F, 0.0F, 0.0F, 1.0F);
                    tessellator.addVertex(-20F + f9, -10 + byte0, 0.0D);
                    tessellator.addVertex(-20F + f9, -6 + byte0, 0.0D);
                    tessellator.addVertex(20D, -6 + byte0, 0.0D);
                    tessellator.addVertex(20D, -10 + byte0, 0.0D);
                    tessellator.colour(0.0F, 0.7F, 0.0F, 1.0F);
                    tessellator.addVertex(-20D, -10 + byte0, 0.0D);
                    tessellator.addVertex(-20D, -6 + byte0, 0.0D);
                    tessellator.addVertex(f9 - 20F, -6 + byte0, 0.0D);
                    tessellator.addVertex(f9 - 20F, -10 + byte0, 0.0D);
                    tessellator.draw();
                    GL11.glEnable(3553 /*GL_TEXTURE_2D*/);
                }
                if(flag)
                {
                    GL11.glDepthMask(false);
                    GL11.glDisable(2929 /*GL_DEPTH_TEST*/);
                    GL11.glEnable(3042 /*GL_BLEND*/);
                    GL11.glBlendFunc(770, 771);
                    GL11.glDisable(3553 /*GL_TEXTURE_2D*/);
                    tessellator.start();
                    int i = fontrenderer.getTextWidth(s) / 2;
                    tessellator.colour(0.0F, 0.0F, 0.0F, 0.25F);
                    tessellator.addVertex(-i - 1, -1 + byte0, 0.0D);
                    tessellator.addVertex(-i - 1, 8 + byte0, 0.0D);
                    tessellator.addVertex(i + 1, 8 + byte0, 0.0D);
                    tessellator.addVertex(i + 1, -1 + byte0, 0.0D);
                    tessellator.draw();
                    GL11.glEnable(3553 /*GL_TEXTURE_2D*/);
                    fontrenderer.drawText(s, -fontrenderer.getTextWidth(s) / 2, byte0, 0x20ffffff);
                    GL11.glEnable(2929 /*GL_DEPTH_TEST*/);
                    GL11.glDepthMask(true);
                    fontrenderer.drawText(s, -fontrenderer.getTextWidth(s) / 2, byte0, -1);
                    GL11.glDisable(3042 /*GL_BLEND*/);
                    GL11.glColor4f(1.0F, 1.0F, 1.0F, 1.0F);
                }
                GL11.glEnable(2896 /*GL_LIGHTING*/);
                GL11.glPopMatrix();
            }
        }
    }

    public void doRenderLiving2(Living entityliving, double d, double d1, double d2,
            float f, float f1)
    {
        EntityShark entityshark = (EntityShark)entityliving;
        super.method_822(entityshark, d, d1, d2, f, f1);
        if(entityshark.renderName())
        {
            float f2 = 1.6F;
            float f3 = 0.01666667F * f2;
            float f4 = entityliving.distanceTo(dispatcher.entity);
            String s = "";
            s = (new StringBuilder()).append(s).append(entityshark.name).toString();
            if(f4 < 12F && s.length() > 0)
            {
                TextRenderer fontrenderer = method_2023();
                GL11.glPushMatrix();
                GL11.glTranslatef((float)d + 0.0F, (float)d1 + 0.2F, (float)d2);
                GL11.glNormal3f(0.0F, 1.0F, 0.0F);
                GL11.glRotatef(-dispatcher.field_2497, 0.0F, 1.0F, 0.0F);
                GL11.glScalef(-f3, -f3, f3);
                GL11.glDisable(2896 /*GL_LIGHTING*/);
                GL11.glDepthMask(false);
                GL11.glDisable(2929 /*GL_DEPTH_TEST*/);
                GL11.glEnable(3042 /*GL_BLEND*/);
                GL11.glBlendFunc(770, 771);
                Tessellator tessellator = Tessellator.INSTANCE;
                byte byte0 = -50;
                GL11.glDisable(3553 /*GL_TEXTURE_2D*/);
                tessellator.start();
                int i = fontrenderer.getTextWidth(s) / 2;
                tessellator.colour(0.0F, 0.0F, 0.0F, 0.25F);
                tessellator.addVertex(-i - 1, -1 + byte0, 0.0D);
                tessellator.addVertex(-i - 1, 8 + byte0, 0.0D);
                tessellator.addVertex(i + 1, 8 + byte0, 0.0D);
                tessellator.addVertex(i + 1, -1 + byte0, 0.0D);
                if(mocr.mocreaturesGlass.othersettings.displayhealth)
                {
                    float f5 = entityshark.health;
                    float f6 = entityshark.maxhealth;
                    float f7 = f5 / f6;
                    float f8 = 40F * f7;
                    tessellator.colour(0.7F, 0.0F, 0.0F, 1.0F);
                    tessellator.addVertex(-20F + f8, -10 + byte0, 0.0D);
                    tessellator.addVertex(-20F + f8, -6 + byte0, 0.0D);
                    tessellator.addVertex(20D, -6 + byte0, 0.0D);
                    tessellator.addVertex(20D, -10 + byte0, 0.0D);
                    tessellator.colour(0.0F, 0.7F, 0.0F, 1.0F);
                    tessellator.addVertex(-20D, -10 + byte0, 0.0D);
                    tessellator.addVertex(-20D, -6 + byte0, 0.0D);
                    tessellator.addVertex(f8 - 20F, -6 + byte0, 0.0D);
                    tessellator.addVertex(f8 - 20F, -10 + byte0, 0.0D);
                }
                tessellator.draw();
                GL11.glEnable(3553 /*GL_TEXTURE_2D*/);
                fontrenderer.drawText(s, -fontrenderer.getTextWidth(s) / 2, byte0, 0x20ffffff);
                GL11.glEnable(2929 /*GL_DEPTH_TEST*/);
                GL11.glDepthMask(true);
                fontrenderer.drawText(s, -fontrenderer.getTextWidth(s) / 2, byte0, -1);
                GL11.glEnable(2896 /*GL_LIGHTING*/);
                GL11.glDisable(3042 /*GL_BLEND*/);
                GL11.glColor4f(1.0F, 1.0F, 1.0F, 1.0F);
                GL11.glPopMatrix();
            }
        }
    }

    protected void stretch(EntityShark entityshark)
    {
        GL11.glScalef(entityshark.b, entityshark.b, entityshark.b);
    }

    protected float method_828(Living entityliving, float f)
    {
        stretch((EntityShark)entityliving);
        return (float)entityliving.field_1645 + f;
    }

    mod_mocreatures mocr = new mod_mocreatures();
}
