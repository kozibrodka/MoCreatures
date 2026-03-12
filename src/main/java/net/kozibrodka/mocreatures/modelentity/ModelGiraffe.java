package net.kozibrodka.mocreatures.modelentity;


import net.minecraft.client.model.ModelPart;
import net.minecraft.client.render.entity.model.EntityModel;
import net.minecraft.util.math.MathHelper;

public class ModelGiraffe extends EntityModel
{

    public ModelGiraffe()
    {
        this(0.0F);
        neckdirection = 1.0F;
        neckangle = 0.0F;
    }

    public ModelGiraffe(float f)
    {
        this(f, 0.0F);
    }

    public ModelGiraffe(float f, float f1)
    {
        body = new ModelPart(0, 0);
        body.addCuboid(-2.5F, 0.0F, -7F, 5, 5, 14, 3.5F);
        body.setPivot(0.0F, -10F, 0.0F);
        headGiraffe = new ModelPart(30, 20);
        headGiraffe.addCuboid(-3F, -35F, -5F, 6, 6, 16, 0.0F);
        headGiraffe.setPivot(0.0F, -5.5F, -13.8F);
        headGiraffe.pitch = 0.3490658F;
        earL = new ModelPart(0, 23);
        earL.addCuboid(3F, -35F, 10F, 1, 2, 4, 0.0F);
        earL.setPivot(-14.5F, -8.5F, -13.8F);
        earL.pitch = 0.3490658F;
        earL.yaw = 0.4490658F;
        earL.roll = 0.4490658F;
        earR = new ModelPart(0, 23);
        earR.addCuboid(3F, -35F, 8F, 1, 2, 4, 0.0F);
        earR.setPivot(8F, -5.5F, -13.8F);
        earR.pitch = 0.3490658F;
        earR.yaw = -0.4490658F;
        earR.roll = -0.4490658F;
        neck = new ModelPart(0, 0);
        neck.addCuboid(-1F, -20F, 0.0F, 2, 25, 4, 1.5F);
        neck.setPivot(0.0F, -15F, -11F);
        neck.pitch = 0.3490658F;
        mane = new ModelPart(54, 12);
        mane.addCuboid(-1F, -22F, 4F, 1, 17, 3, 0.5F);
        mane.setPivot(0.0F, -15F, -11F);
        mane.pitch = 0.3490658F;
        hornL = new ModelPart(40, 20);
        hornL.addCuboid(-0.5F, -39F, 0.0F, 1, 6, 1, 0.0F);
        hornL.setPivot(1.8F, -8F, -11F);
        hornL.pitch = 0.1490658F;
        hornLtop = new ModelPart(40, 20);
        hornLtop.addCuboid(-0.5F, -39F, 0.0F, 1, 1, 1, 0.5F);
        hornLtop.setPivot(1.8F, -8F, -11F);
        hornLtop.pitch = 0.1490658F;
        hornR = new ModelPart(40, 20);
        hornR.addCuboid(-0.5F, -39F, 0.0F, 1, 6, 1, 0.0F);
        hornR.setPivot(-1.8F, -8F, -11F);
        hornR.mirror = true;
        hornR.pitch = 0.1490658F;
        hornRtop = new ModelPart(40, 20);
        hornRtop.addCuboid(-0.5F, -39F, 0.0F, 1, 1, 1, 0.5F);
        hornRtop.setPivot(-1.8F, -8F, -11F);
        hornRtop.mirror = true;
        hornRtop.pitch = 0.1490658F;
        tail2 = new ModelPart(40, 19);
        tail2.addCuboid(-1F, -10F, 6F, 1, 3, 1, 1.25F);
        tail2.setPivot(0.0F, 12F, 15F);
        tail2.pitch = 0.5235988F;
        tail = new ModelPart(42, 0);
        tail.addCuboid(-2F, -10F, 6F, 3, 15, 3, -0.25F);
        tail.setPivot(0.0F, 2.0F, 8F);
        tail.pitch = 0.5235988F;
        leg1 = new ModelPart(0, 9);
        leg1.addCuboid(-0.5F, 0.0F, -0.5F, 1, 22, 1, 1.5F);
        leg1.setPivot(3F, 0.0F, 8F);
        leg2 = new ModelPart(0, 9);
        leg2.addCuboid(-0.5F, 0.0F, -0.5F, 1, 22, 1, 1.5F);
        leg2.setPivot(-3F, 0.0F, 8F);
        leg3 = new ModelPart(0, 9);
        leg3.addCuboid(-0.5F, 0.0F, -0.5F, 1, 22, 1, 1.5F);
        leg3.setPivot(3F, 0.0F, -8F);
        leg4 = new ModelPart(0, 9);
        leg4.addCuboid(-0.5F, 0.0F, -0.5F, 1, 22, 1, 1.5F);
        leg4.setPivot(-3F, 0.0F, -8F);
    }

    public void render(float f, float f1, float f2, float f3, float f4, float f5)
    {
        setAngles(f, f1, f2, f3, f4, f5);
        body.render(f5);
        tail.render(f5);
        leg1.render(f5);
        leg2.render(f5);
        leg3.render(f5);
        leg4.render(f5);
        headGiraffe.render(f5);
        neck.render(f5);
        hornR.render(f5);
        hornRtop.render(f5);
        hornL.render(f5);
        hornLtop.render(f5);
        mane.render(f5);
        earL.render(f5);
        earR.render(f5);
        tail2.render(f5);
    }

    public void setAngles(float f, float f1, float f2, float f3, float f4, float f5)
    {
        leg1.pitch = MathHelper.cos(f * 0.6662F) * 1.4F * f1;
        leg2.pitch = MathHelper.cos(f * 0.6662F + 3.141593F) * 1.4F * f1;
        leg3.pitch = MathHelper.cos(f * 0.6662F + 3.141593F) * 1.4F * f1;
        leg4.pitch = MathHelper.cos(f * 0.6662F) * 1.4F * f1;
        neckoffset = MathHelper.cos(f * 0.6662F) * 0.04F * f1;
        neck.pitch = 0.3490658F + neckoffset;
        headGiraffe.pitch = 0.3490658F + neckoffset;
        earL.pitch = 0.3490658F + neckoffset;
        earR.pitch = 0.3490658F + neckoffset;
        mane.pitch = 0.3490658F + neckoffset;
        hornL.pitch = 0.1490658F + neckoffset;
        hornLtop.pitch = 0.1490658F + neckoffset;
        hornR.pitch = 0.1490658F + neckoffset;
        hornRtop.pitch = 0.1490658F + neckoffset;
    }

    public ModelPart body;
    public ModelPart tail;
    public ModelPart leg1;
    public ModelPart leg2;
    public ModelPart leg3;
    public ModelPart leg4;
    public ModelPart headGiraffe;
    public ModelPart neck;
    public ModelPart hornR;
    public ModelPart hornRtop;
    public ModelPart hornL;
    public ModelPart hornLtop;
    public ModelPart mane;
    public ModelPart earL;
    public ModelPart earR;
    public ModelPart tail2;
    private float neckangle;
    private float neckdirection;
    private float neckoffset;
}

