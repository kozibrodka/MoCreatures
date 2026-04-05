
package net.kozibrodka.mocreatures.renderentity;

import net.kozibrodka.mocreatures.entity.EntityElephant;
import net.kozibrodka.mocreatures.entity.EntityWerewolf;
import net.kozibrodka.mocreatures.modelentity.ModelElephant;
import net.minecraft.client.render.entity.LivingEntityRenderer;
import net.minecraft.client.render.entity.model.EntityModel;
import net.minecraft.entity.LivingEntity;
import org.lwjgl.opengl.GL11;


public class RenderElephant extends LivingEntityRenderer
{

    public RenderElephant(EntityModel modelbase, float f, float f1)
    {
        super(modelbase, f * f1);
        setDecorationModel(new ModelElephant());
        scale = f1;
    }

    protected boolean setWoolColorAndRender(EntityElephant entityelephan, int i)
    {
        if(!entityelephan.getMad())
        {
            entityelephan.ustawTexture("/assets/mocreatures/stationapi/textures/mob/elephant.png");
        } else
        {
            entityelephan.ustawTexture("/assets/mocreatures/stationapi/textures/mob/elephantangry.png");
        }
        return i == 0;
    }

    @Override
    protected boolean bindTexture(LivingEntity entityliving, int i, float f)
    {
        return setWoolColorAndRender((EntityElephant)entityliving, i);
    }

    protected void preRenderScale(EntityElephant entityelephant, float f)
    {
        float fAge = entityelephant.getAge();
        GL11.glScalef(fAge, fAge, fAge);
    }

    @Override
    protected void applyScale(LivingEntity entityliving, float f)
    {
        preRenderScale((EntityElephant)entityliving, f);
    }

    private float scale;
}