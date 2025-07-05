package net.kozibrodka.mocreatures.renderentity;
import net.kozibrodka.mocreatures.entity.EntityHorse;
import net.kozibrodka.mocreatures.events.mod_mocreatures;
import net.kozibrodka.mocreatures.modelentity.ModelHorse1;
import net.minecraft.client.font.TextRenderer;
import net.minecraft.client.render.Tessellator;
import net.minecraft.client.render.entity.LivingEntityRenderer;
import net.minecraft.client.render.entity.model.EntityModel;
import net.minecraft.entity.Entity;
import net.minecraft.entity.LivingEntity;
import net.minecraft.util.math.MathHelper;
import org.lwjgl.opengl.GL11;


public class RenderHorse extends LivingEntityRenderer
{

    public RenderHorse(EntityModel modelbase, EntityModel modelbase1)
    {
        super(modelbase1, 0.5F);
        setDecorationModel(modelbase);
        modelhorse1 = (ModelHorse1)modelbase1;
    }

    protected boolean bindTexture(LivingEntity entityliving, int i, float f)
    {
        return setWoolColorAndRender((EntityHorse)entityliving, i);
    }

    protected float getWingRotation(EntityHorse entityhorse, float f)
    {
        float f1 = entityhorse.fwinge + (entityhorse.fwingb - entityhorse.fwinge) * f;
        float f2 = entityhorse.fwingd + (entityhorse.fwingc - entityhorse.fwingd) * f;
        if(!entityhorse.getAdult())
        {
            stretch(entityhorse);
        }
        return (MathHelper.sin(f1) + 1.0F) * f2;
    }

    protected void applyScale(LivingEntity entityliving, float f)
    {
        EntityHorse entityhorse = (EntityHorse)entityliving;
        modelhorse1.chested = entityhorse.getChested();
    }

    public void render(Entity entity, double d, double d1, double d2,
                         float f, float f1)
    {
        doRenderHorse((EntityHorse)entity, d, d1, d2, f, f1);
    }

    public void doRenderHorse(EntityHorse entityhorse, double d, double d1, double d2, 
            float f, float f1)
    {
        super.render(entityhorse, d, d1, d2, f, f1);
        boolean flag = mocr.mocreaturesGlass.othersettings.displayname && !entityhorse.getName().isEmpty();
        boolean flag1 = mocr.mocreaturesGlass.othersettings.displayhealth;
        boolean flag2 = mocr.mocreaturesGlass.othersettings.displayemo;
        if(entityhorse.renderName())
        {
            float f2 = 1.6F;
            float f3 = 0.01666667F * f2;
            float f5 = entityhorse.getDistance(dispatcher.cameraEntity);
            if(f5 < 16F)
            {
                String s = "";
                s = (new StringBuilder()).append(s).append(entityhorse.getName()).toString();
                float f7 = 0.1F;
                TextRenderer fontrenderer = getTextRenderer();
                GL11.glPushMatrix();
                GL11.glTranslatef((float)d + 0.0F, (float)d1 + f7, (float)d2);
                GL11.glNormal3f(0.0F, 1.0F, 0.0F);
                GL11.glRotatef(-dispatcher.yaw, 0.0F, 1.0F, 0.0F);
                GL11.glScalef(-f3, -f3, f3);
                GL11.glDisable(2896 /*GL_LIGHTING*/);
                Tessellator tessellator1 = Tessellator.INSTANCE;
                byte byte0 = -80;
                if(flag1)
                {
                    GL11.glDisable(3553 /*GL_TEXTURE_2D*/);
                    if(!flag)
                    {
                        byte0 += 8;
                    }
                    tessellator1.startQuads();
                    float f8 = entityhorse.health;
                    float f9 = entityhorse.maxhealth;
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
        if(entityhorse.roper != null)
        {
            Tessellator tessellator = Tessellator.INSTANCE;
            float f4 = ((entityhorse.roper.prevYaw + (entityhorse.roper.yaw - entityhorse.roper.prevYaw) * f1 * 0.5F) * 3.141593F) / 180F;
            float f6 = ((entityhorse.roper.prevPitch + (entityhorse.roper.pitch - entityhorse.roper.prevPitch) * f1 * 0.5F) * 3.141593F) / 180F;
            double d3 = MathHelper.sin(f4);
            double d4 = MathHelper.cos(f4);
            double d5 = MathHelper.sin(f6);
            double d6 = MathHelper.cos(f6);
            double d7 = (entityhorse.roper.prevX + (entityhorse.roper.x - entityhorse.roper.prevX) * (double)f1) - d4 * 0.69999999999999996D - d3 * 0.5D * d6;
            double d8 = (entityhorse.roper.prevY + (entityhorse.roper.y - entityhorse.roper.prevY) * (double)f1) - d5 * 0.5D;
            double d9 = ((entityhorse.roper.prevZ + (entityhorse.roper.z - entityhorse.roper.prevZ) * (double)f1) - d3 * 0.69999999999999996D) + d4 * 0.5D * d6;
            double d10 = entityhorse.prevX + (entityhorse.x - entityhorse.prevX) * (double)f1;
            double d11 = entityhorse.prevY + (entityhorse.y - entityhorse.prevY) * (double)f1 + 0.25D;
            double d12 = entityhorse.prevZ + (entityhorse.z - entityhorse.prevZ) * (double)f1;
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

    protected void stretch(EntityHorse entityhorse)
    {
        GL11.glScalef(entityhorse.getAge(), entityhorse.getAge(), entityhorse.getAge());
    }

    protected float getHeadBob(LivingEntity entityliving, float f)
    {
        return getWingRotation((EntityHorse)entityliving, f);
    }

    protected boolean setWoolColorAndRender(EntityHorse entityhorse, int i)
    {
        if(entityhorse.dajTexture() == "/assets/mocreatures/stationapi/textures/mob/horseb.png")
        {
            if(!entityhorse.getSaddled())
            {
                bindTexture("/assets/mocreatures/stationapi/textures/mob/horsea.png");
            } else
            {
                bindTexture("/assets/mocreatures/stationapi/textures/mob/horseasaddle.png");
            }
        } else
        if(entityhorse.dajTexture() == "/assets/mocreatures/stationapi/textures/mob/horsebrownb.png")
        {
            if(!entityhorse.getSaddled())
            {
                bindTexture("/assets/mocreatures/stationapi/textures/mob/horsebrowna.png");
            } else
            {
                bindTexture("/assets/mocreatures/stationapi/textures/mob/horsebrownsaddle.png");
            }
        } else
        if(entityhorse.dajTexture() == "/assets/mocreatures/stationapi/textures/mob/horseblackb.png")
        {
            if(!entityhorse.getSaddled())
            {
                bindTexture("/assets/mocreatures/stationapi/textures/mob/horseblacka.png");
            } else
            {
                bindTexture("/assets/mocreatures/stationapi/textures/mob/horseblacksaddle.png");
            }
        } else
        if(entityhorse.dajTexture() == "/assets/mocreatures/stationapi/textures/mob/horsegoldb.png")
        {
            if(!entityhorse.getSaddled())
            {
                bindTexture("/assets/mocreatures/stationapi/textures/mob/horsegolda.png");
            } else
            {
                bindTexture("/assets/mocreatures/stationapi/textures/mob/horsegoldsaddle.png");
            }
        } else
        if(entityhorse.dajTexture() == "/assets/mocreatures/stationapi/textures/mob/horsewhiteb.png")
        {
            if(!entityhorse.getSaddled())
            {
                bindTexture("/assets/mocreatures/stationapi/textures/mob/horsewhitea.png");
            } else
            {
                bindTexture("/assets/mocreatures/stationapi/textures/mob/horsewhitesaddle.png");
            }
        } else
        if(entityhorse.dajTexture() == "/assets/mocreatures/stationapi/textures/mob/horsepackb.png")
        {
            if(!entityhorse.getSaddled())
            {
                bindTexture("/assets/mocreatures/stationapi/textures/mob/horsepacka.png");
            } else
            {
                bindTexture("/assets/mocreatures/stationapi/textures/mob/horsepacksaddle.png");
            }
        } else
        if(entityhorse.dajTexture() == "/assets/mocreatures/stationapi/textures/mob/horsenightb.png")
        {
            if(!entityhorse.getSaddled())
            {
                bindTexture("/assets/mocreatures/stationapi/textures/mob/horsenighta.png");
            } else
            {
                bindTexture("/assets/mocreatures/stationapi/textures/mob/horsenightsaddle.png");
            }
        } else
        if(entityhorse.dajTexture() == "/assets/mocreatures/stationapi/textures/mob/horsebpb.png")
        {
            if(!entityhorse.getSaddled())
            {
                bindTexture("/assets/mocreatures/stationapi/textures/mob/horsebpa.png");
            } else
            {
                bindTexture("/assets/mocreatures/stationapi/textures/mob/horsebpsaddle.png");
            }
        }
        return i == 0 && !entityhorse.horseboolean;
    }

    mod_mocreatures mocr = new mod_mocreatures();

    public ModelHorse1 modelhorse1;
}
