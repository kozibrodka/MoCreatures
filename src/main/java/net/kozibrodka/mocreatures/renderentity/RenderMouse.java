package net.kozibrodka.mocreatures.renderentity;
import net.kozibrodka.mocreatures.entity.EntityMouse;
import net.minecraft.client.render.entity.LivingEntityRenderer;
import net.minecraft.client.render.entity.model.EntityModel;
import net.minecraft.entity.LivingEntity;
import org.lwjgl.opengl.GL11;


public class RenderMouse extends LivingEntityRenderer
{

    public RenderMouse(EntityModel modelbase, float f)
    {
        super(modelbase, f);
    }

    public void render(LivingEntity entityliving, double d, double d1, double d2,
                               float f, float f1)
    {
        EntityMouse entitymouse = (EntityMouse)entityliving;
        super.render(entitymouse, d, d1, d2, f, f1);
    }

    protected void applyScale(LivingEntity entityliving, float f)
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

    protected void stretch(LivingEntity entityliving)
    {
        float f = 0.6F;
        GL11.glScalef(f, f, f);
    }

    protected void upsideDown(LivingEntity entityliving)
    {
        GL11.glRotatef(-90F, -1F, 0.0F, 0.0F);
        GL11.glTranslatef(-0.55F, -0.4F, -0.7F);
    }

    protected void rotateAnimal(LivingEntity entityliving)
    {
        GL11.glRotatef(90F, -1F, 0.0F, 0.0F);
    }

    protected float getHeadBob(LivingEntity entityliving, float f)
    {
        stretch(entityliving);
        return (float)entityliving.age + f;
    }
}
