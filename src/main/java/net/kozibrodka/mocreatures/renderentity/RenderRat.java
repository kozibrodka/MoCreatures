package net.kozibrodka.mocreatures.renderentity;
import net.kozibrodka.mocreatures.entity.EntityRat;
import net.minecraft.client.render.entity.LivingEntityRenderer;
import net.minecraft.client.render.entity.model.EntityModelBase;
import net.minecraft.entity.Living;
import org.lwjgl.opengl.GL11;


public class RenderRat extends LivingEntityRenderer
{

    public RenderRat(EntityModelBase modelbase, float f)
    {
        super(modelbase, f);
    }

    public void method_822(Living entityliving, double d, double d1, double d2,
                               float f, float f1)
    {
        EntityRat entityrat = (EntityRat)entityliving;
        if(!entityrat.typechosen)
        {
            entityrat.chooseType();
        }
        super.method_822(entityrat, d, d1, d2, f, f1);
    }

    protected void method_823(Living entityliving, float f)
    {
        EntityRat entityrat = (EntityRat)entityliving;
        if(entityrat.climbing())
        {
            rotateAnimal(entityliving);
        }
    }

    protected void rotateAnimal(Living entityliving)
    {
        GL11.glRotatef(90F, -1F, 0.0F, 0.0F);
    }

    protected void stretch(Living entityliving)
    {
        float f = 0.8F;
        GL11.glScalef(f, f, f);
    }

    protected float method_828(Living entityliving, float f)
    {
        stretch(entityliving);
        return (float)entityliving.field_1645 + f;
    }
}
