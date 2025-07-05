package net.kozibrodka.mocreatures.renderentity;

import net.kozibrodka.mocreatures.entity.EntityLitterBox;
import net.kozibrodka.mocreatures.modelentity.ModelLitterBox;
import net.minecraft.client.render.entity.LivingEntityRenderer;
import net.minecraft.entity.LivingEntity;

public class RenderLitterBox extends LivingEntityRenderer
{

    public RenderLitterBox(ModelLitterBox modellitterbox, float f)
    {
        super(modellitterbox, f);
        litterbox = modellitterbox;
    }

    protected void applyScale(LivingEntity entityliving, float f)
    {
        EntityLitterBox entitylitterbox = (EntityLitterBox)entityliving;
        litterbox.usedlitter = entitylitterbox.usedlitter;
    }

    public ModelLitterBox litterbox;
}
