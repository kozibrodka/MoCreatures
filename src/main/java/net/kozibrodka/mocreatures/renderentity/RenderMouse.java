package net.kozibrodka.mocreatures.renderentity;
import net.kozibrodka.mocreatures.entity.EntityMouse;
import net.minecraft.client.render.entity.LivingEntityRenderer;
import net.minecraft.client.render.entity.model.EntityModelBase;
import net.minecraft.entity.Living;
import org.lwjgl.opengl.GL11;


public class RenderMouse extends LivingEntityRenderer
{

    public RenderMouse(EntityModelBase modelbase, float f)
    {
        super(modelbase, f);
    }

    public void method_822(Living entityliving, double d, double d1, double d2,
                               float f, float f1)
    {
        EntityMouse entitymouse = (EntityMouse)entityliving;
        if(!entitymouse.typechosen)
        {
            entitymouse.chooseType();
        }
        super.method_822(entitymouse, d, d1, d2, f, f1);
    }

    protected void method_823(Living entityliving, float f)
    {
        EntityMouse entitymouse = (EntityMouse)entityliving;
        if(entitymouse.upsideDown())
        {
            upsideDown(entityliving);
        }
        if(entitymouse.climbing())
        {
            rotateAnimal(entityliving);
        }
    }

    protected void stretch(Living entityliving)
    {
        float f = 0.6F;
        GL11.glScalef(f, f, f);
    }

    protected void upsideDown(Living entityliving)
    {
        GL11.glRotatef(-90F, -1F, 0.0F, 0.0F);
        GL11.glTranslatef(-0.55F, -0.4F, -0.7F);
    }

    protected void rotateAnimal(Living entityliving)
    {
        GL11.glRotatef(90F, -1F, 0.0F, 0.0F);
    }

    protected float method_828(Living entityliving, float f)
    {
        stretch(entityliving);
        return (float)entityliving.field_1645 + f;
    }
}
