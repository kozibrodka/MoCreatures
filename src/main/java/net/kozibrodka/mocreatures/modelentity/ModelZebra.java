package net.kozibrodka.mocreatures.modelentity;

import net.minecraft.client.model.ModelPart;
import net.minecraft.client.render.entity.model.EntityModel;
import net.minecraft.util.math.MathHelper;

public class ModelZebra extends EntityModel
{

    public ModelZebra()
    {
        this(0.0F);
    }

    public ModelZebra(float f)
    {
        this(f, 0.0F);
    }

    public ModelZebra(float f, float f1)
    {
        taildirection = 1;
        leg1 = new ModelPart(16, 22);
        leg1.addCuboid(-1F, 0.0F, -1F, 2, 8, 2, 0.0F);
        leg1.setPivot(-1.5F, 16F, -4F);
        leg1.pitch = 0.0F;
        leg1.yaw = 0.0F;
        leg1.roll = 0.0F;
        leg1.mirror = false;
        leg2 = new ModelPart(16, 22);
        leg2.addCuboid(-1F, 0.0F, -1F, 2, 8, 2, 0.0F);
        leg2.setPivot(2.5F, 16F, -4F);
        leg2.pitch = 0.0F;
        leg2.yaw = 0.0F;
        leg2.roll = 0.0F;
        leg2.mirror = false;
        leg3 = new ModelPart(16, 22);
        leg3.addCuboid(-1F, 0.0F, -1F, 2, 8, 2, 0.0F);
        leg3.setPivot(2.5F, 16F, 7F);
        leg3.pitch = 0.0F;
        leg3.yaw = 0.0F;
        leg3.roll = 0.0F;
        leg3.mirror = false;
        leg4 = new ModelPart(16, 22);
        leg4.addCuboid(-1F, 0.0F, -1F, 2, 8, 2, 0.0F);
        leg4.setPivot(-1.5F, 16F, 7F);
        leg4.pitch = 0.0F;
        leg4.yaw = 0.0F;
        leg4.roll = 0.0F;
        leg4.mirror = false;
        body = new ModelPart(24, 10);
        body.addCuboid(0.0F, 0.0F, 0.0F, 6, 8, 14, 0.0F);
        body.setPivot(-2.5F, 8F, -6F);
        body.pitch = 0.0F;
        body.yaw = 0.0F;
        body.roll = 0.0F;
        body.mirror = false;
        mane2 = new ModelPart(50, 0);
        mane2.addCuboid(0.0F, 0.0F, 0.0F, 2, 7, 3, 0.0F);
        mane2.setPivot(-0.5F, 3F, -8.5F);
        mane2.pitch = 0.4089647F;
        mane2.yaw = 0.0F;
        mane2.roll = 0.0F;
        mane2.mirror = false;
        neck = new ModelPart(24, 11);
        neck.addCuboid(-1.5F, 0.0F, 0.0F, 3, 6, 3, 0.0F);
        neck.setPivot(0.5F, 8F, -9.6F);
        neck.pitch = 0.7063936F;
        neck.yaw = 0.0F;
        neck.roll = 0.0F;
        neck.mirror = false;
        zebraHead = new ModelPart(0, 0);
        zebraHead.addCuboid(-3F, -6F, -3F, 5, 5, 6, 0.0F);
        zebraHead.setPivot(1.0F, 9F, -10F);
        zebraHead.pitch = 0.0F;
        zebraHead.yaw = 0.0F;
        zebraHead.roll = 0.0F;
        zebraHead.mirror = false;
        eyeL = new ModelPart(7, 18);
        eyeL.addCuboid(0.0F, 0.0F, 0.0F, 1, 1, 1, 0.0F);
        eyeL.setPivot(2.33F, 4F, -12F);
        eyeL.pitch = 0.0F;
        eyeL.yaw = 0.0F;
        eyeL.roll = 0.0F;
        eyeL.mirror = false;
        snout = new ModelPart(0, 11);
        snout.addCuboid(0.0F, 0.0F, 0.0F, 3, 3, 4, 0.0F);
        snout.setPivot(-1F, 4.5F, -17F);
        snout.pitch = 0.0F;
        snout.yaw = 0.0F;
        snout.roll = 0.0F;
        snout.mirror = false;
        earL = new ModelPart(7, 20);
        earL.addCuboid(0.0F, 0.0F, 0.0F, 1, 2, 2, 0.0F);
        earL.setPivot(2.0F, 1.0F, -10F);
        earL.pitch = 0.0F;
        earL.yaw = 0.0F;
        earL.roll = 0.0F;
        earL.mirror = false;
        mane1 = new ModelPart(26, 0);
        mane1.addCuboid(0.0F, 0.0F, 0.0F, 2, 2, 5, 0.0F);
        mane1.setPivot(-0.5F, 1.0F, -10.5F);
        mane1.pitch = -0.1323696F;
        mane1.yaw = 0.0F;
        mane1.roll = 0.0F;
        mane1.mirror = false;
        tail = new ModelPart(0, 25);
        tail.addCuboid(-0.5F, 0.0F, 0.0F, 1, 6, 1, 0.0F);
        tail.setPivot(0.5F, 10F, 7F);
        tail.pitch = 0.5089647F;
        tail.yaw = 0.0F;
        tail.roll = 0.0F;
        tail.mirror = false;
        eyeR = new ModelPart(0, 18);
        eyeR.addCuboid(0.0F, 0.0F, 0.0F, 1, 1, 1, 0.0F);
        eyeR.setPivot(-2.3F, 4F, -12F);
        eyeR.pitch = 0.0F;
        eyeR.yaw = 0.0F;
        eyeR.roll = 0.0F;
        eyeR.mirror = false;
        earR = new ModelPart(0, 20);
        earR.addCuboid(0.0F, 0.0F, 0.0F, 1, 2, 2, 0.0F);
        earR.setPivot(-2F, 1.0F, -10F);
        earR.pitch = 0.0F;
        earR.yaw = 0.0F;
        earR.roll = 0.0F;
        earR.mirror = false;
        tail2 = new ModelPart(14, 11);
        tail2.addCuboid(-0.5F, 5.5F, 0.0F, 1, 3, 1, 0.35F);
        tail2.setPivot(0.5F, 10F, 7F);
        tail2.pitch = 0.5235988F;
        tail2.yaw = 0.0F;
        tail2.roll = 0.0F;
        tail2.mirror = false;
    }

    public void render(float f, float f1, float f2, float f3, float f4, float f5)
    {
        setAngles(f, f1, f2, f3, f4, f5);
        leg1.render(f5);
        leg2.render(f5);
        leg3.render(f5);
        leg4.render(f5);
        body.render(f5);
        mane2.render(f5);
        neck.render(f5);
        zebraHead.render(f5);
        eyeL.render(f5);
        snout.render(f5);
        earL.render(f5);
        mane1.render(f5);
        tail.render(f5);
        eyeR.render(f5);
        earR.render(f5);
        tail2.render(f5);
    }

    public void setAngles(float f, float f1, float f2, float f3, float f4, float f5)
    {
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
        tail.yaw = tail2.yaw = MathHelper.cos(f2 * 0.6662F + 3.141593F) * 0.24F;
        leg1.pitch = MathHelper.cos(f * 0.6662F) * 1.4F * f1;
        leg2.pitch = MathHelper.cos(f * 0.6662F + 3.141593F) * 1.4F * f1;
        leg3.pitch = MathHelper.cos(f * 0.6662F + 3.141593F) * 1.4F * f1;
        leg4.pitch = MathHelper.cos(f * 0.6662F) * 1.4F * f1;
    }

    public ModelPart leg1;
    public ModelPart leg2;
    public ModelPart leg3;
    public ModelPart leg4;
    public ModelPart body;
    public ModelPart mane2;
    public ModelPart neck;
    public ModelPart zebraHead;
    public ModelPart eyeL;
    public ModelPart snout;
    public ModelPart earL;
    public ModelPart mane1;
    public ModelPart tail;
    public ModelPart eyeR;
    public ModelPart earR;
    public float tailwag;
    public int taildirection;
    public ModelPart tail2;
}
