package net.kozibrodka.mocreatures.renderentity;
import net.kozibrodka.mocreatures.entity.EntityShark;
import net.kozibrodka.mocreatures.events.mod_mocreatures;
import net.minecraft.client.font.TextRenderer;
import net.minecraft.client.render.Tessellator;
import net.minecraft.client.render.entity.LivingEntityRenderer;
import net.minecraft.client.render.entity.model.EntityModel;
import net.minecraft.entity.LivingEntity;
import org.lwjgl.opengl.GL11;


public class RenderShark extends LivingEntityRenderer
{

    public RenderShark(EntityModel modelbase, float f)
    {
        super(modelbase, f);
    }


    @Override
    public void render(LivingEntity entityliving, double d, double d1, double d2,
                               float f, float f1)
    {
        EntityShark entityshark = (EntityShark)entityliving;
        super.render(entityshark, d, d1, d2, f, f1);
        boolean flag = mod_mocreatures.mocGlass.othersettings.displayname && !entityshark.getName().isEmpty();
        boolean flag1 = mod_mocreatures.mocGlass.othersettings.displayhealth;
        boolean flag2 = mod_mocreatures.mocGlass.othersettings.displayemo;
        if(entityshark.renderName())
        {
            float f2 = 1.6F;
            float f3 = 0.01666667F * f2;
            float f4 = entityshark.getDistance(dispatcher.cameraEntity);
            if(f4 < 16F)
            {
                String s = "";
                s = (new StringBuilder()).append(s).append(entityshark.getName()).toString();
                float f5 = 0.1F;
                TextRenderer fontrenderer = getTextRenderer();
                GL11.glPushMatrix();
                GL11.glTranslatef((float)d + 0.0F, (float)d1 + f5, (float)d2);
                GL11.glNormal3f(0.0F, 1.0F, 0.0F);
                GL11.glRotatef(-dispatcher.yaw, 0.0F, 1.0F, 0.0F);
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
                    tessellator.startQuads();
                    float f6 = entityshark.getHealth();
                    float f7 = entityshark.maxhealth;
                    float f8 = f6 / f7;
                    float f9 = 40F * f8;
                    tessellator.color(0.7F, 0.0F, 0.0F, 1.0F);
                    tessellator.vertex(-20F + f9, -10 + byte0, 0.0D);
                    tessellator.vertex(-20F + f9, -6 + byte0, 0.0D);
                    tessellator.vertex(20D, -6 + byte0, 0.0D);
                    tessellator.vertex(20D, -10 + byte0, 0.0D);
                    tessellator.color(0.0F, 0.7F, 0.0F, 1.0F);
                    tessellator.vertex(-20D, -10 + byte0, 0.0D);
                    tessellator.vertex(-20D, -6 + byte0, 0.0D);
                    tessellator.vertex(f9 - 20F, -6 + byte0, 0.0D);
                    tessellator.vertex(f9 - 20F, -10 + byte0, 0.0D);
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
                    tessellator.startQuads();
                    int i = fontrenderer.getWidth(s) / 2;
                    tessellator.color(0.0F, 0.0F, 0.0F, 0.25F);
                    tessellator.vertex(-i - 1, -1 + byte0, 0.0D);
                    tessellator.vertex(-i - 1, 8 + byte0, 0.0D);
                    tessellator.vertex(i + 1, 8 + byte0, 0.0D);
                    tessellator.vertex(i + 1, -1 + byte0, 0.0D);
                    tessellator.draw();
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
    }

    protected void stretch(EntityShark entityshark)
    {
        GL11.glScalef(entityshark.getAge(), entityshark.getAge(), entityshark.getAge());
    }

    @Override
    protected float getHeadBob(LivingEntity entityliving, float f)
    {
        stretch((EntityShark)entityliving);
        return (float)entityliving.age + f;
    }

}
