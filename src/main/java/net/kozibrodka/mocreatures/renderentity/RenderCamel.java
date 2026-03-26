
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
        modelBipedMain = creepsmodelcamel;
        shadowRadius = f;
    }

    protected void fattenup(EntityCamel creepsentitycamel, float f)
    {
        float scale = 1.75F;
        GL11.glScalef(scale, scale, scale);
    }

    protected void applyScale(LivingEntity entityliving, float f)
    {
        fattenup((EntityCamel)entityliving, f);
    }

    protected ModelCamel modelBipedMain;
}
