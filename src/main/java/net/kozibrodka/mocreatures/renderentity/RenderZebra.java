
package net.kozibrodka.mocreatures.renderentity;

import net.kozibrodka.mocreatures.entity.EntityZebra;
import net.kozibrodka.mocreatures.modelentity.ModelZebra;
import net.minecraft.client.render.entity.LivingEntityRenderer;
import net.minecraft.entity.LivingEntity;
import org.lwjgl.opengl.GL11;


public class RenderZebra extends LivingEntityRenderer
{

    public RenderZebra(ModelZebra creepsmodelzebra, float f)
    {
        super(creepsmodelzebra, f);
        modelBipedMain = creepsmodelzebra;
    }

    protected void fattenup(EntityZebra creepsentityzebra, float f)
    {
        GL11.glScalef(2.0F, 2.0F, 2.0F);
    }

    @Override
    protected void applyScale(LivingEntity entityliving, float f)
    {
        fattenup((EntityZebra)entityliving, f);
    }

    protected ModelZebra modelBipedMain;
}

