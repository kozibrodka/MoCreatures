
package net.kozibrodka.mocreatures.renderentity;

import net.kozibrodka.mocreatures.entity.EntityHippo;
import net.kozibrodka.mocreatures.modelentity.ModelHippo;
import net.minecraft.client.render.entity.LivingEntityRenderer;
import net.minecraft.entity.LivingEntity;
import org.lwjgl.opengl.GL11;


public class RenderHippo extends LivingEntityRenderer
{

    public RenderHippo(ModelHippo creepsmodelhippo, float f)
    {
        super(creepsmodelhippo, f);
        setDecorationModel(new ModelHippo());
        modelBipedMain = creepsmodelhippo;
    }

    protected void fattenup(EntityHippo creepsentityhippo, float f)
    {
        GL11.glScalef(2.0F, 2.0F, 2.0F);
    }

    protected void applyScale(LivingEntity entityliving, float f)
    {
        fattenup((EntityHippo)entityliving, f);
    }

    protected ModelHippo modelBipedMain;
}
