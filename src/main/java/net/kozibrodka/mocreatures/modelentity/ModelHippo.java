
package net.kozibrodka.mocreatures.modelentity;

import net.minecraft.client.model.ModelPart;
import net.minecraft.client.render.entity.model.EntityModel;
import net.minecraft.util.math.MathHelper;

public class ModelHippo extends EntityModel
{

    public ModelHippo()
    {
        this(0.0F);
    }

    public ModelHippo(float f)
    {
        this(f, 0.0F);
    }

    public ModelHippo(float f, float f1)
    {
        taildirection = 1;
        float f2 = 0.0F;
        headHippo = new ModelPart(16, 20);
        headHippo.addCuboid(-3F, -3F, -5F, 6, 7, 5, f2);
        headHippo.setPivot(1.0F, 13F, -7F);
        headHippo.yaw = 0.0F;
        snout = new ModelPart(38, 22);
        snout.addCuboid(-4F, -1F, -10F, 8, 5, 5, f2);
        snout.setPivot(1.0F, 13F, -7F);
        body = new ModelPart(12, 0);
        body.addCuboid(-5F, -4F, -8F, 10, 8, 16, 1.35F);
        body.setPivot(1.0F, 14F, 2.0F);
        leg1 = new ModelPart(0, 23);
        leg1.addCuboid(-2F, 0.0F, -2F, 4, 5, 4, f2);
        leg1.setPivot(-2F, 19F, -4F);
        leg2 = new ModelPart(0, 23);
        leg2.addCuboid(-2F, 0.0F, -2F, 4, 5, 4, f2);
        leg2.setPivot(4F, 19F, -4F);
        leg4 = new ModelPart(0, 23);
        leg4.addCuboid(-2F, 0.0F, -2F, 4, 5, 4, f2);
        leg4.setPivot(4F, 19F, 8F);
        leg3 = new ModelPart(0, 23);
        leg3.addCuboid(-2F, 0.0F, -2F, 4, 5, 4, f2);
        leg3.setPivot(-2F, 19F, 8F);
        noseR = new ModelPart(8, 0);
        noseR.addCuboid(2.0F, -1.7F, -9F, 1, 1, 1, f2);
        noseR.setPivot(1.0F, 13F, -7F);
        noseL = new ModelPart(4, 0);
        noseL.addCuboid(-3F, -1.7F, -9F, 1, 1, 1, f2);
        noseL.setPivot(1.0F, 13F, -7F);
        earR = new ModelPart(0, 6);
        earR.addCuboid(2.0F, -5F, -3F, 1, 2, 2, f2);
        earR.setPivot(1.0F, 13F, -7F);
        earL = new ModelPart(0, 2);
        earL.addCuboid(-3F, -5F, -3F, 1, 2, 2, f2);
        earL.setPivot(1.0F, 13F, -7F);
        toothL = new ModelPart(0, 16);
        toothL.addCuboid(-3F, 4F, -10F, 1, 2, 1, f2);
        toothL.setPivot(1.0F, 13F, -7F);
        toothR = new ModelPart(0, 16);
        toothR.addCuboid(2.0F, 4F, -10F, 1, 2, 1, f2);
        toothR.setPivot(1.0F, 13F, -7F);
        eyeL = new ModelPart(0, 0);
        eyeL.addCuboid(-3.5F, -1.7F, -4F, 1, 1, 1, f2);
        eyeL.setPivot(1.0F, 13F, -7F);
        eyeR = new ModelPart(0, 0);
        eyeR.addCuboid(2.5F, -1.7F, -4F, 1, 1, 1, f2);
        eyeR.setPivot(1.0F, 13F, -7F);
        mouth = new ModelPart(8, 0);
        mouth.addCuboid(-2.5F, 1.0F, -10F, 5, 1, 5, f2);
        mouth.setPivot(1.0F, 13F, -7F);
        mouth.pitch = 0.45203F;
        mouth.yaw = 0.0F;
    }

    public void render(float f, float f1, float f2, float f3, float f4, float f5)
    {
        setAngles(f, f1, f2, f3, f4, f5);
        headHippo.render(f5);
        snout.render(f5);
        body.render(f5);
        leg1.render(f5);
        leg2.render(f5);
        leg4.render(f5);
        leg3.render(f5);
        noseR.render(f5);
        noseL.render(f5);
        earR.render(f5);
        earL.render(f5);
        toothL.render(f5);
        toothR.render(f5);
        eyeL.render(f5);
        eyeR.render(f5);
        mouth.render(f5);
    }

    public void setAngles(float f, float f1, float f2, float f3, float f4, float f5)
    {
        headHippo.yaw = f3 / 57.29578F;
        headHippo.pitch = f4 / 57.29578F;
        snout.yaw = noseR.yaw = noseL.yaw = eyeR.yaw = eyeL.yaw = toothL.yaw = toothR.yaw = earL.yaw = earR.yaw = headHippo.yaw;
        snout.pitch = noseR.pitch = noseL.pitch = eyeR.pitch = eyeL.pitch = toothL.pitch = toothR.pitch = earL.pitch = earR.pitch = headHippo.pitch;
        if(taildirection > 0)
        {
            tailwag += 0.0002F;
            if(tailwag > 0.067F)
            {
                taildirection = taildirection * -1;
            }
        } else
        {
            tailwag -= 0.0002F;
            if((double)tailwag < -0.067000000000000004D)
            {
                taildirection = taildirection * -1;
            }
        }
        mouth.pitch = headHippo.pitch + 0.45203F + tailwag;
        mouth.yaw = headHippo.yaw;
        leg1.pitch = MathHelper.cos(f * 0.6662F) * 1.4F * f1;
        leg2.pitch = MathHelper.cos(f * 0.6662F + 3.141593F) * 1.4F * f1;
        leg3.pitch = MathHelper.cos(f * 0.6662F + 3.141593F) * 1.4F * f1;
        leg4.pitch = MathHelper.cos(f * 0.6662F) * 1.4F * f1;
    }

    public ModelPart headHippo;
    public ModelPart snout;
    public ModelPart body;
    public ModelPart leg1;
    public ModelPart leg2;
    public ModelPart leg4;
    public ModelPart leg3;
    public ModelPart noseR;
    public ModelPart noseL;
    public ModelPart earR;
    public ModelPart earL;
    public ModelPart toothL;
    public ModelPart toothR;
    public ModelPart eyeL;
    public ModelPart eyeR;
    public ModelPart mouth;
    public float tailwag;
    public int taildirection;
}

