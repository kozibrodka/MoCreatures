
package net.kozibrodka.mocreatures.renderentity;

import net.kozibrodka.mocreatures.entity.EntityCamel;
import net.kozibrodka.mocreatures.modelentity.ModelCamel;
import net.minecraft.client.render.entity.LivingEntityRenderer;
import net.minecraft.entity.LivingEntity;
import org.lwjgl.opengl.GL11;



public class RenderCamel extends LivingEntityRenderer
{

    public RenderCamel(ModelCamel creepsmodelcamel, float f)
    {
        super(creepsmodelcamel, f);
//        setRenderPassModel(new CREEPSModelCamel());
        modelBipedMain = creepsmodelcamel;
        shadowRadius = f;
    }

    protected void fattenup(EntityCamel creepsentitycamel, float f)
    {
        float scale = 1.75F;
//        GL11.glScalef(2.0F, 2.0F, 2.0F);
        GL11.glScalef(scale, scale, scale);
    }

    protected void applyScale(LivingEntity entityliving, float f)
    {
        fattenup((EntityCamel)entityliving, f);
    }

//    public void doRenderLiving(EntityLiving entityliving, double d, double d1, double d2,
//            float f, float f1)
//    {
//        super.doRenderLiving(entityliving, d, d1, d2, f, f1);
//        float f2 = 1.6F;
//        float f3 = 0.01666667F * f2;
//        float f4 = entityliving.getDistanceToEntity(renderManager.livingPlayer);
//        String s = "";
//        s = (new StringBuilder()).append(s).append(((CREEPSEntityCamel)entityliving).name).toString();
//        if(f4 < 25F && s.length() > 0 && ((CREEPSEntityCamel)entityliving).riddenByEntity == null)
//        {
//            s = (new StringBuilder()).append("\2476").append(s).toString();
//            FontRenderer fontrenderer = getFontRendererFromRenderManager();
//            GL11.glPushMatrix();
//            GL11.glTranslatef((float)d + 0.0F, (float)d1 + 1.1F, (float)d2);
//            GL11.glNormal3f(0.0F, 1.0F, 0.0F);
//            GL11.glRotatef(-renderManager.playerViewY, 0.0F, 1.0F, 0.0F);
//            GL11.glRotatef(renderManager.playerViewX, 1.0F, 0.0F, 0.0F);
//            GL11.glScalef(-f3, -f3, f3);
//            GL11.glDisable(2896 /*GL_LIGHTING*/);
//            GL11.glDepthMask(false);
//            GL11.glDisable(2929 /*GL_DEPTH_TEST*/);
//            GL11.glEnable(3042 /*GL_BLEND*/);
//            GL11.glBlendFunc(770, 771);
//            Tessellator tessellator = Tessellator.instance;
//            char c = '\uFF74';
//            GL11.glDisable(3553 /*GL_TEXTURE_2D*/);
//            tessellator.startDrawingQuads();
//            int i = fontrenderer.getStringWidth(s) / 2;
//            tessellator.setColorRGBA_F(0.0F, 0.0F, 0.0F, 0.25F);
//            tessellator.addVertex(-i - 1, -1 + c, 0.0D);
//            tessellator.addVertex(-i - 1, 8 + c, 0.0D);
//            tessellator.addVertex(i + 1, 8 + c, 0.0D);
//            tessellator.addVertex(i + 1, -1 + c, 0.0D);
//            tessellator.draw();
//            GL11.glEnable(3553 /*GL_TEXTURE_2D*/);
//            fontrenderer.drawString(s, -fontrenderer.getStringWidth(s) / 2, c, 0x20ffffff);
//            GL11.glEnable(2929 /*GL_DEPTH_TEST*/);
//            GL11.glDepthMask(true);
//            fontrenderer.drawString(s, -fontrenderer.getStringWidth(s) / 2, c, -1);
//            GL11.glEnable(2896 /*GL_LIGHTING*/);
//            GL11.glDisable(3042 /*GL_BLEND*/);
//            GL11.glColor4f(1.0F, 1.0F, 1.0F, 1.0F);
//            GL11.glPopMatrix();
//        }
//    }
//
//    public void doRender(Entity entity, double d, double d1, double d2,
//            float f, float f1)
//    {
//        doRenderLiving((EntityLiving)entity, d, d1, d2, f, f1);
//    }

    protected ModelCamel modelBipedMain;
}
