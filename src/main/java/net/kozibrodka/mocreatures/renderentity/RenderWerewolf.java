package net.kozibrodka.mocreatures.renderentity;
import net.kozibrodka.mocreatures.entity.EntityWerewolf;
import net.kozibrodka.mocreatures.modelentity.ModelWereHuman;
import net.kozibrodka.mocreatures.modelentity.ModelWerewolf;
import net.minecraft.client.render.entity.LivingEntityRenderer;
import net.minecraft.client.render.entity.model.EntityModel;
import net.minecraft.entity.LivingEntity;
import org.lwjgl.opengl.GL11;


public class RenderWerewolf extends LivingEntityRenderer
{

    public RenderWerewolf(ModelWereHuman modelwerehuman, EntityModel modelbase, float f)
    {
        super(modelbase, f);
        setDecorationModel(modelwerehuman);
        tempWerewolf = (ModelWerewolf)modelbase;
    }

    protected boolean setWoolColorAndRender(EntityWerewolf entitywerewolf, int i)
    {
        if(!entitywerewolf.humanform)
        {
            entitywerewolf.ustawTexture("/assets/mocreatures/stationapi/textures/mob/werewolf.png");
            bindTexture("/assets/mocreatures/stationapi/textures/mob/wereblank.png");
        } else
        {
            entitywerewolf.ustawTexture("/assets/mocreatures/stationapi/textures/mob/wereblank.png");
            bindTexture("/assets/mocreatures/stationapi/textures/mob/werehuman.png");
        }
        return i == 0 && !entitywerewolf.wereboolean;
    }

    protected boolean bindTexture(LivingEntity entityliving, int i, float f)
    {
        return setWoolColorAndRender((EntityWerewolf)entityliving, i);
    }

    public void render(LivingEntity entityliving, double d, double d1, double d2,
            float f, float f1)
    {
        EntityWerewolf entitywerewolf = (EntityWerewolf)entityliving;
        if(entitywerewolf.humanform)
        {
            super.render(entityliving, d, d1, d2, f, f1);
            return;
        }
        boolean flag = entitywerewolf.hunched;
        GL11.glPushMatrix();
        GL11.glDisable(2884 /*GL_CULL_FACE*/);
        model.handSwingProgress = getHandSwingProgress(entityliving, f1);
        model.riding = entityliving.hasVehicle();
        if(decorationModel != null)
        {
            decorationModel.riding = model.riding;
        }
        try
        {
            float f2 = entityliving.lastBodyYaw + (entityliving.bodyYaw - entityliving.lastBodyYaw) * f1;
            float f3 = entityliving.prevYaw + (entityliving.yaw - entityliving.prevYaw) * f1;
            float f4 = entityliving.prevPitch + (entityliving.pitch - entityliving.prevPitch) * f1;
            applyTranslation(entityliving, d, d1, d2);
            float f5 = getHeadBob(entityliving, f1);
            applyHandSwingRotation(entityliving, f5, f2, f1);
            float f6 = 0.0625F;
            GL11.glEnable(32826 /*GL_RESCALE_NORMAL_EXT*/);
            GL11.glScalef(-1F, -1F, 1.0F);
            applyScale(entityliving, f1);
            GL11.glTranslatef(0.0F, -24F * f6 - 0.007813F, 0.0F);
            float f7 = entityliving.lastWalkAnimationSpeed + (entityliving.walkAnimationSpeed - entityliving.lastWalkAnimationSpeed) * f1;
            float f8 = entityliving.walkAnimationProgress - entityliving.walkAnimationSpeed * (1.0F - f1);
            if(f7 > 1.0F)
            {
                f7 = 1.0F;
            }
            bindDownloadedTexture(entityliving.skinUrl, entityliving.getTexture());
            GL11.glEnable(3008 /*GL_ALPHA_TEST*/);
            tempWerewolf.render(f8, f7, f5, f3 - f2, f4, f6, flag);
            for(int i = 0; i < 4; i++)
            {
                if(bindTexture(entityliving, i, f1))
                {
                    decorationModel.render(f8, f7, f5, f3 - f2, f4, f6);
                    GL11.glDisable(3042 /*GL_BLEND*/);
                    GL11.glEnable(3008 /*GL_ALPHA_TEST*/);
                }
            }

            renderMore(entityliving, f1);
            float f9 = entityliving.getBrightnessAtEyes(f1);
            int j = getOverlayColor(entityliving, f9, f1);
            if((j >> 24 & 0xff) > 0 || entityliving.hurtTime > 0 || entityliving.deathTime > 0)
            {
                GL11.glDisable(3553 /*GL_TEXTURE_2D*/);
                GL11.glDisable(3008 /*GL_ALPHA_TEST*/);
                GL11.glEnable(3042 /*GL_BLEND*/);
                GL11.glBlendFunc(770, 771);
                GL11.glDepthFunc(514);
                if(entityliving.hurtTime > 0 || entityliving.deathTime > 0)
                {
                    GL11.glColor4f(f9, 0.0F, 0.0F, 0.4F);
                    tempWerewolf.render(f8, f7, f5, f3 - f2, f4, f6, flag);
                    for(int k = 0; k < 4; k++)
                    {
                        if(bindTexture(entityliving, k, f1))
                        {
                            GL11.glColor4f(f9, 0.0F, 0.0F, 0.4F);
                            decorationModel.render(f8, f7, f5, f3 - f2, f4, f6);
                        }
                    }

                }
                if((j >> 24 & 0xff) > 0)
                {
                    float f10 = (float)(j >> 16 & 0xff) / 255F;
                    float f11 = (float)(j >> 8 & 0xff) / 255F;
                    float f12 = (float)(j & 0xff) / 255F;
                    float f13 = (float)(j >> 24 & 0xff) / 255F;
                    GL11.glColor4f(f10, f11, f12, f13);
                    tempWerewolf.render(f8, f7, f5, f3 - f2, f4, f6, flag);
                    for(int l = 0; l < 4; l++)
                    {
                        if(bindTexture(entityliving, l, f1))
                        {
                            GL11.glColor4f(f10, f11, f12, f13);
                            decorationModel.render(f8, f7, f5, f3 - f2, f4, f6);
                        }
                    }

                }
                GL11.glDepthFunc(515);
                GL11.glDisable(3042 /*GL_BLEND*/);
                GL11.glEnable(3008 /*GL_ALPHA_TEST*/);
                GL11.glEnable(3553 /*GL_TEXTURE_2D*/);
            }
            GL11.glDisable(32826 /*GL_RESCALE_NORMAL_EXT*/);
        }
        catch(Exception exception)
        {
            exception.printStackTrace();
        }
        GL11.glEnable(2884 /*GL_CULL_FACE*/);
        GL11.glPopMatrix();
        renderNameTag(entityliving, d, d1, d2);
    }

    private ModelWerewolf tempWerewolf;
}
