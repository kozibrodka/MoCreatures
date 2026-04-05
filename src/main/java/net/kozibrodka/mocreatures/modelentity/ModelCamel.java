package net.kozibrodka.mocreatures.modelentity;


import net.minecraft.client.model.ModelPart;
import net.minecraft.client.render.entity.model.EntityModel;
import net.minecraft.util.math.MathHelper;

public class ModelCamel extends EntityModel
{

    public ModelCamel()
    {
        this(0.0F);
    }

    public ModelCamel(float f)
    {
        this(f, 0.0F);
    }

    public ModelCamel(float f, float f1)
    {
        float f2 = 0.0F;
        body = new ModelPart(36, 0);
        body.addCuboid(-1F, -3F, -4.5F, 2, 6, 9, 3.9F);
        body.setPivot(0.0F, 4F, 0.0F);
        leg1 = new ModelPart(38, 15);
        leg1.addCuboid(-1.5F, 0.0F, -1.5F, 3, 14, 3, -0.25F);
        leg1.setPivot(3F, 10F, -6F);
        leg2 = new ModelPart(38, 15);
        leg2.addCuboid(-1.5F, 0.0F, -1.5F, 3, 14, 3, -0.25F);
        leg2.setPivot(-3F, 10F, -6F);
        leg3 = new ModelPart(38, 15);
        leg3.addCuboid(-1.5F, 0.0F, -2F, 3, 14, 3, -0.25F);
        leg3.setPivot(3F, 10F, 7F);
        leg4 = new ModelPart(38, 15);
        leg4.addCuboid(-1.5F, 0.0F, -2F, 3, 14, 3, -0.25F);
        leg4.setPivot(-3F, 10F, 7F);
        hump1 = new ModelPart(28, 0);
        hump1.addCuboid(-4F, -5F, -4F, 8, 5, 8, 0.45F);
        hump1.setPivot(0.0F, -2F, 0.0F);
        neck1 = new ModelPart(36, 0);
        neck1.addCuboid(-2.5F, -2F, 0.0F, 5, 5, 9, f2);
        neck1.setPivot(0.0F, 2.0F, -7F);
        neck1.pitch = -2.53136F;
        tail = new ModelPart(0, 22);
        tail.addCuboid(-1F, -1F, -7.5F, 2, 2, 8, f2);
        tail.setPivot(0.0F, 4F, 8F);
        tail.pitch = 2.16973F;
        tail.yaw = -0.0452F;
        neck2 = new ModelPart(32, 0);
        neck2.addCuboid(-2.5F, -2F, 0.0F, 5, 5, 11, -0.5F);
        neck2.setPivot(0.0F, 7F, -12F);
        neck2.pitch = 2.53136F;
        headCamel = new ModelPart(0, 0);
        headCamel.addCuboid(-2.5F, -2F, -8F, 5, 5, 8, f2);
        headCamel.setPivot(0.0F, 0.0F, -19F);
        earR = new ModelPart(0, 0);
        earR.addCuboid(-2.5F, -4F, -1.5F, 1, 2, 1, f2);
        earR.setPivot(0.0F, 0.0F, -19F);
        earL = new ModelPart(0, 3);
        earL.addCuboid(1.5F, -4F, -1.5F, 1, 2, 1, f2);
        earL.setPivot(0.0F, 0.0F, -19F);
        mouth = new ModelPart(20, 25);
        mouth.addCuboid(-2F, 0.5F, -9F, 4, 1, 4, f2);
        mouth.setPivot(0.0F, 0.0F, -19F);
        mouth.pitch = 0.29382F;
    }

    @Override
    public void render(float f, float f1, float f2, float f3, float f4, float f5)
    {
        setAngles(f, f1, f2, f3, f4, f5);
        body.render(f5);
        leg1.render(f5);
        leg2.render(f5);
        leg3.render(f5);
        leg4.render(f5);
        hump1.render(f5);
        neck1.render(f5);
        tail.render(f5);
        neck2.render(f5);
        headCamel.render(f5);
        earR.render(f5);
        earL.render(f5);
        mouth.render(f5);
    }

    @Override
    public void setAngles(float f, float f1, float f2, float f3, float f4, float f5)
    {
        headCamel.yaw = f3 / 57.29578F;
        headCamel.pitch = f4 / 57.29578F;
        mouth.yaw = earR.yaw = earL.yaw = headCamel.yaw;
        earR.pitch = earL.pitch = headCamel.pitch;
        mouth.pitch = headCamel.pitch + 0.29382F;
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
        leg1.pitch = MathHelper.cos(f * 0.6662F) * 1.4F * f1;
        leg2.pitch = MathHelper.cos(f * 0.6662F + 3.141593F) * 1.4F * f1;
        leg3.pitch = MathHelper.cos(f * 0.6662F + 3.141593F) * 1.4F * f1;
        leg4.pitch = MathHelper.cos(f * 0.6662F) * 1.4F * f1;
    }

    public ModelPart body;
    public ModelPart leg1;
    public ModelPart leg2;
    public ModelPart leg3;
    public ModelPart leg4;
    public ModelPart hump1;
    public ModelPart neck1;
    public ModelPart tail;
    public ModelPart neck2;
    public ModelPart headCamel;
    public ModelPart earR;
    public ModelPart earL;
    public ModelPart mouth;
    public float tailwag;
    public int taildirection;
}
