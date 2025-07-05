package net.kozibrodka.mocreatures.renderentity;
import net.kozibrodka.mocreatures.entity.EntityDeer;
import net.minecraft.client.render.entity.LivingEntityRenderer;
import net.minecraft.client.render.entity.model.EntityModel;
import net.minecraft.entity.LivingEntity;
import org.lwjgl.opengl.GL11;


public class RenderDeer extends LivingEntityRenderer
{

    public RenderDeer(EntityModel modelbase, float f)
    {
        super(modelbase, f);
    }

    public void render(LivingEntity entityliving, double d, double d1, double d2,
                               float f, float f1)
    {
        EntityDeer entitydeer = (EntityDeer)entityliving;
//        bindTexture(entitydeer.getMyTexture(entitydeer.getType()));
//        bindTexture("/assets/mocreatures/stationapi/textures/mob/deer.png");
//        if(!entitydeer.typechosen)
//        {
//            entitydeer.setType(entitydeer.getRandomRace());
//        }
        super.render(entitydeer, d, d1, d2, f, f1);
    }

    protected void stretch(EntityDeer entitydeer)
    {
        float f = entitydeer.getAge();
        float f1 = 0.0F;
        if(entitydeer.getType() == 1)
        {
            f1 = 1.7F;
        } else
        if(entitydeer.getType() == 2)
        {
            f1 = 1.3F;
        } else
        {
            f1 = f;
        }
        if(entitydeer.getAdult())
        {
            f = 1.0F;
        }
        GL11.glScalef(f1, f1, f1);
    }

    protected float getHeadBob(LivingEntity entityliving, float f)
    {
        EntityDeer entitydeer = (EntityDeer)entityliving;
        stretch(entitydeer);
        return (float)entityliving.age + f;
    }

    protected void rotateDeer(EntityDeer entitydeer)
    {
        if(!entitydeer.onGround && entitydeer.dajszybkosc() > 2.0F)
        {
            if(entitydeer.velocityY > 0.5D)
            {
                GL11.glRotatef(20F, -1F, 0.0F, 0.0F);
            } else
            if(entitydeer.velocityY < -0.5D)
            {
                GL11.glRotatef(-20F, -1F, 0.0F, 0.0F);
            } else
            {
                GL11.glRotatef((float)(entitydeer.velocityY * 40D), -1F, 0.0F, 0.0F);
            }
        }
    }

    protected void applyScale(LivingEntity entityliving, float f)
    {
        rotateDeer((EntityDeer)entityliving);
    }
}
