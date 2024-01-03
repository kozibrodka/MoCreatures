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
        method_815(modelwerehuman);
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

    protected boolean method_825(LivingEntity entityliving, int i, float f)
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
        model.handWingProgress = method_820(entityliving, f1);
        model.riding = entityliving.method_1360();
        if(field_910 != null)
        {
            field_910.riding = model.riding;
        }
        try
        {
            float f2 = entityliving.field_1013 + (entityliving.field_1012 - entityliving.field_1013) * f1;
            float f3 = entityliving.prevYaw + (entityliving.yaw - entityliving.prevYaw) * f1;
            float f4 = entityliving.prevPitch + (entityliving.pitch - entityliving.prevPitch) * f1;
            method_826(entityliving, d, d1, d2);
            float f5 = method_828(entityliving, f1);
            method_824(entityliving, f5, f2, f1);
            float f6 = 0.0625F;
            GL11.glEnable(32826 /*GL_RESCALE_NORMAL_EXT*/);
            GL11.glScalef(-1F, -1F, 1.0F);
            method_823(entityliving, f1);
            GL11.glTranslatef(0.0F, -24F * f6 - 0.007813F, 0.0F);
            float f7 = entityliving.field_1048 + (entityliving.field_1049 - entityliving.field_1048) * f1;
            float f8 = entityliving.field_1050 - entityliving.field_1049 * (1.0F - f1);
            if(f7 > 1.0F)
            {
                f7 = 1.0F;
            }
            method_2027(entityliving.skinUrl, entityliving.method_1314());
            GL11.glEnable(3008 /*GL_ALPHA_TEST*/);
            tempWerewolf.render(f8, f7, f5, f3 - f2, f4, f6, flag);
            for(int i = 0; i < 4; i++)
            {
                if(method_825(entityliving, i, f1))
                {
                    field_910.render(f8, f7, f5, f3 - f2, f4, f6);
                    GL11.glDisable(3042 /*GL_BLEND*/);
                    GL11.glEnable(3008 /*GL_ALPHA_TEST*/);
                }
            }

            method_827(entityliving, f1);
            float f9 = entityliving.method_1394(f1);
            int j = method_817(entityliving, f9, f1);
            if((j >> 24 & 0xff) > 0 || entityliving.hurtTime > 0 || entityliving.field_1041 > 0)
            {
                GL11.glDisable(3553 /*GL_TEXTURE_2D*/);
                GL11.glDisable(3008 /*GL_ALPHA_TEST*/);
                GL11.glEnable(3042 /*GL_BLEND*/);
                GL11.glBlendFunc(770, 771);
                GL11.glDepthFunc(514);
                if(entityliving.hurtTime > 0 || entityliving.field_1041 > 0)
                {
                    GL11.glColor4f(f9, 0.0F, 0.0F, 0.4F);
                    tempWerewolf.render(f8, f7, f5, f3 - f2, f4, f6, flag);
                    for(int k = 0; k < 4; k++)
                    {
                        if(method_825(entityliving, k, f1))
                        {
                            GL11.glColor4f(f9, 0.0F, 0.0F, 0.4F);
                            field_910.render(f8, f7, f5, f3 - f2, f4, f6);
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
                        if(method_825(entityliving, l, f1))
                        {
                            GL11.glColor4f(f10, f11, f12, f13);
                            field_910.render(f8, f7, f5, f3 - f2, f4, f6);
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
        method_821(entityliving, d, d1, d2);
    }

    private ModelWerewolf tempWerewolf;
}
