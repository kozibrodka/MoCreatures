
package net.kozibrodka.mocreatures.renderentity;

import net.kozibrodka.mocreatures.modelentity.ModelGiraffe;
import net.minecraft.client.render.entity.LivingEntityRenderer;
import net.minecraft.entity.Entity;
import net.minecraft.entity.LivingEntity;


public class RenderGiraffe extends LivingEntityRenderer
{

    public RenderGiraffe(ModelGiraffe creepsmodelrocketgiraffe, float f)
    {
        super(creepsmodelrocketgiraffe, f);
        setDecorationModel(new ModelGiraffe());
    }


    public void render(Entity entity, double d, double d1, double d2,
                         float f, float f1) //TODO: wtf?
    {
        render((LivingEntity)entity, d, d1, d2, f, f1);
    }
}
