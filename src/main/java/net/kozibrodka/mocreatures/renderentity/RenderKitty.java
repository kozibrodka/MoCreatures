package net.kozibrodka.mocreatures.renderentity;

import net.fabricmc.loader.api.FabricLoader;
import net.kozibrodka.mocreatures.entity.EntityKitty;
import net.kozibrodka.mocreatures.modelentity.ModelKitty;
import net.kozibrodka.mocreatures.events.mod_mocreatures;
import net.minecraft.client.Minecraft;
import net.minecraft.client.render.Tessellator;
import net.minecraft.client.render.TextRenderer;
import net.minecraft.client.render.entity.BipedEntityRenderer;
import net.minecraft.entity.Living;
import org.lwjgl.opengl.GL11;

public class RenderKitty extends BipedEntityRenderer
{

    public RenderKitty(ModelKitty modelkitty, float f)
    {
        super(modelkitty, f);
        pussy1 = modelkitty;
    }

    protected void method_823(Living entityliving, float f)
    {
        EntityKitty entitykitty = (EntityKitty)entityliving;
        pussy1.isSitting = entitykitty.isSitting;
        pussy1.isSwinging = entitykitty.isSwinging;
        pussy1.swingProgress = entitykitty.handSwingProgress;
        pussy1.kittystate = entitykitty.kittystate;
        if(entitykitty.kittystate == 20)
        {
            onTheSide(entityliving);
        }
        if(entitykitty.climbingTree())
        {
            rotateAnimal(entityliving);
        }
        if(entitykitty.upsideDown())
        {
            upsideDown(entityliving);
        }
        if(entitykitty.onMaBack())
        {
            onMaBack(entityliving);
        }
    }

    public void doRenderLiving2(Living entityliving, double d, double d1, double d2,
            float f, float f1)
    {
        EntityKitty entitykitty = (EntityKitty)entityliving;
        if(!entitykitty.typechosen)
        {
            entitykitty.chooseType();
        }
        super.method_822(entitykitty, d, d1, d2, f, f1);
    }

    protected void stretch(EntityKitty entitykitty)
    {
        GL11.glScalef(entitykitty.edad, entitykitty.edad, entitykitty.edad);
    }

    protected float method_828(Living entityliving, float f)
    {
        EntityKitty entitykitty = (EntityKitty)entityliving;
        if(!entitykitty.adult)
        {
            stretch(entitykitty);
        }
        return (float)entityliving.field_1645 + f;
    }

    public void method_822(Living entityliving, double d, double d1, double d2,
            float f, float f1)
    {
        EntityKitty entitykitty = (EntityKitty)entityliving;
        if(!entitykitty.typechosen)
        {
            entitykitty.chooseType();
        }
        super.method_822(entitykitty, d, d1, d2, f, f1);
        boolean flag = mocr.mocreaturesGlass.othersettings.displayname && !entitykitty.name.isEmpty();
        boolean flag1 = mocr.mocreaturesGlass.othersettings.displayhealth;
        boolean flag2 = mocr.mocreaturesGlass.othersettings.displayemo;
        if(entitykitty.renderName())
        {
            float f2 = 1.6F;
            float f3 = 0.01666667F * f2;
            float f4 = entityliving.distanceTo(dispatcher.entity);
            String s = "";
            s = (new StringBuilder()).append(s).append(entitykitty.name).toString();
            if(f4 < 12F)
            {
                float f5 = 0.2F;
                if(pussy1.isSitting)
                {
                    f5 = 0.4F;
                }
                TextRenderer fontrenderer = method_2023();
                GL11.glPushMatrix();
                GL11.glTranslatef((float)d + 0.0F, (float)d1 - f5, (float)d2);
                GL11.glNormal3f(0.0F, 1.0F, 0.0F);
                GL11.glRotatef(-dispatcher.field_2497, 0.0F, 1.0F, 0.0F);
                GL11.glScalef(-f3, -f3, f3);
                GL11.glDisable(2896 /*GL_LIGHTING*/);
                Tessellator tessellator = Tessellator.INSTANCE;
                byte byte0 = -48;
                if(flag2 && ((EntityKitty)entityliving).displayemo)
                {
                    GL11.glBindTexture(3553 /*GL_TEXTURE_2D*/, mc.textureManager.getTextureId(((EntityKitty)entityliving).getEmoticon()));
                    int i = -83;
                    if(!flag)
                    {
                        i += 8;
                    }
                    if(!flag1)
                    {
                        i += 8;
                    }
                    int k = 32;
                    int l = (k / 2) * -1;
                    float f9 = 0.0F;
                    float f11 = 1.0F / (float)k;
                    float f12 = 1.0F / (float)k;
                    tessellator.start();
                    tessellator.vertex(l, i + k, f9, 0.0D, (float)k * f12);
                    tessellator.vertex(l + k, i + k, f9, (float)k * f11, (float)k * f12);
                    tessellator.vertex(l + k, i, f9, (float)k * f11, 0.0D);
                    tessellator.vertex(l, i, f9, 0.0D, 0.0D);
                    tessellator.draw();
                }
                if(flag1)
                {
                    if(!flag)
                    {
                        byte0 += 8;
                    }
                    GL11.glDisable(3553 /*GL_TEXTURE_2D*/);
                    tessellator.start();
                    float f6 = entitykitty.health;
                    float f7 = entitykitty.maxhealth;
                    float f8 = f6 / f7;
                    float f10 = 30F * f8;
                    tessellator.colour(0.7F, 0.0F, 0.0F, 1.0F);
                    tessellator.addVertex(-15F + f10, -8 + byte0, 0.0D);
                    tessellator.addVertex(-15F + f10, -4 + byte0, 0.0D);
                    tessellator.addVertex(15D, -4 + byte0, 0.0D);
                    tessellator.addVertex(15D, -8 + byte0, 0.0D);
                    tessellator.colour(0.0F, 0.7F, 0.0F, 1.0F);
                    tessellator.addVertex(-15D, -8 + byte0, 0.0D);
                    tessellator.addVertex(-15D, -4 + byte0, 0.0D);
                    tessellator.addVertex(f10 - 15F, -4 + byte0, 0.0D);
                    tessellator.addVertex(f10 - 15F, -8 + byte0, 0.0D);
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
                    int j = fontrenderer.getTextWidth(s) / 2;
                    tessellator.colour(0.0F, 0.0F, 0.0F, 0.25F);
                    tessellator.addVertex(-j - 1, -1 + byte0, 0.0D);
                    tessellator.addVertex(-j - 1, 8 + byte0, 0.0D);
                    tessellator.addVertex(j + 1, 8 + byte0, 0.0D);
                    tessellator.addVertex(j + 1, -1 + byte0, 0.0D);
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

    protected void rotateAnimal(Living entityliving)
    {
        if(!entityliving.onGround)
        {
            GL11.glRotatef(90F, -1F, 0.0F, 0.0F);
        }
    }

    protected void upsideDown(Living entityliving)
    {
        GL11.glRotatef(180F, 0.0F, 0.0F, -1F);
        GL11.glTranslatef(-0.35F, 0.0F, -0.55F);
    }

    protected void onTheSide(Living entityliving)
    {
        GL11.glRotatef(90F, 0.0F, 0.0F, -1F);
        GL11.glTranslatef(0.2F, 0.0F, -0.2F);
    }

    protected void onMaBack(Living entityliving)
    {
        GL11.glRotatef(90F, 0.0F, 0.0F, -1F);
        GL11.glTranslatef(0.1F, 0.2F, -0.2F);
    }

    @SuppressWarnings("deprecation")
    public static Minecraft mc = Minecraft.class.cast(FabricLoader.getInstance().getGameInstance());
    mod_mocreatures mocr = new mod_mocreatures();

    public ModelKitty pussy1;
}
