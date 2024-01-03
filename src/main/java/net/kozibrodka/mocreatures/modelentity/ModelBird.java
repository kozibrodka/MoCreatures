// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) braces deadcode 

package net.kozibrodka.mocreatures.modelentity;

import net.minecraft.client.model.ModelPart;
import net.minecraft.client.render.entity.model.EntityModel;
import net.minecraft.util.math.MathHelper;

public class ModelBird extends EntityModel
{

    public ModelBird()
    {
        byte byte0 = 16;
        head = new ModelPart(0, 0);
        head.addCuboid(-1.5F, -3F, -2F, 3, 3, 3, 0.0F);
        head.setPivot(0.0F, -1 + byte0, -4F);
        beak = new ModelPart(14, 0);
        beak.addCuboid(-0.5F, -1.5F, -3F, 1, 1, 2, 0.0F);
        beak.setPivot(0.0F, -1 + byte0, -4F);
        body = new ModelPart(0, 9);
        body.addCuboid(-2F, -4F, -3F, 4, 8, 4, 0.0F);
        body.setPivot(0.0F, 0 + byte0, 0.0F);
        body.pitch = 1.047198F;
        leftleg = new ModelPart(26, 0);
        leftleg.addCuboid(-1F, 0.0F, -4F, 3, 4, 3);
        leftleg.setPivot(-2F, 3 + byte0, 1.0F);
        rightleg = new ModelPart(26, 0);
        rightleg.addCuboid(-1F, 0.0F, -4F, 3, 4, 3);
        rightleg.setPivot(1.0F, 3 + byte0, 1.0F);
        rwing = new ModelPart(24, 13);
        rwing.addCuboid(-1F, 0.0F, -3F, 1, 5, 5);
        rwing.setPivot(-2F, -2 + byte0, 0.0F);
        lwing = new ModelPart(24, 13);
        lwing.addCuboid(0.0F, 0.0F, -3F, 1, 5, 5);
        lwing.setPivot(2.0F, -2 + byte0, 0.0F);
        tail = new ModelPart(0, 23);
        tail.addCuboid(-6F, 5F, 2.0F, 4, 1, 4, 0.0F);
        tail.setPivot(4F, -3 + byte0, 0.0F);
        tail.pitch = 0.261799F;
    }

    public void render(float f, float f1, float f2, float f3, float f4, float f5)
    {
        setAngles(f, f1, f2, f3, f4, f5);
        head.render(f5);
        beak.render(f5);
        body.render(f5);
        leftleg.render(f5);
        rightleg.render(f5);
        rwing.render(f5);
        lwing.render(f5);
        tail.render(f5);
    }

    public void setAngles(float f, float f1, float f2, float f3, float f4, float f5)
    {
        head.pitch = -(f4 / 2.0F / 57.29578F);
        head.yaw = f3 / 2.0F / 57.29578F;
        beak.yaw = head.yaw;
        leftleg.pitch = MathHelper.cos(f * 0.6662F) * f1;
        rightleg.pitch = MathHelper.cos(f * 0.6662F + 3.141593F) * f1;
        rwing.roll = f2;
        lwing.roll = -f2;
    }

    public ModelPart head;
    public ModelPart body;
    public ModelPart leftleg;
    public ModelPart rightleg;
    public ModelPart rwing;
    public ModelPart lwing;
    public ModelPart beak;
    public ModelPart tail;
}
