// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) braces deadcode 

package net.kozibrodka.mocreatures.modelentity;


import net.minecraft.client.model.ModelPart;
import net.minecraft.client.render.entity.model.EntityModel;
import net.minecraft.util.math.MathHelper;

public class ModelFishy extends EntityModel
{

    public ModelFishy()
    {
        Body = new ModelPart(0, 0);
        Body.addCuboid(0.0F, 0.0F, 0.0F, 1, 5, 5, 0.0F);
        Body.setPivot(0.0F, 19F, 0.0F);
        Body.pitch = 0.7853981F;
        Tail = new ModelPart(12, 0);
        Tail.addCuboid(0.0F, 0.0F, 0.0F, 1, 3, 3, 0.0F);
        Tail.setPivot(0.0F, 18.7F, 6F);
        Tail.pitch = 0.7853981F;
    }

    public void render(float f, float f1, float f2, float f3, float f4, float f5)
    {
        setAngles(f, f1, f2, f3, f4, f5);
        Body.render(f5);
        Tail.render(f5);
    }

    public void setAngles(float f, float f1, float f2, float f3, float f4, float f5)
    {
        Tail.yaw = MathHelper.cos(f * 0.6662F) * 1.4F * f1;
    }

    public ModelPart Body;
    public ModelPart UpperFin;
    public ModelPart LowerFin;
    public ModelPart RightFin;
    public ModelPart LeftFin;
    public ModelPart Tail;
}
