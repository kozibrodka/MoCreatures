// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) braces deadcode 

package net.kozibrodka.mocreatures.modelentity;


import net.minecraft.client.model.Cuboid;
import net.minecraft.client.render.entity.model.EntityModelBase;
import net.minecraft.util.maths.MathHelper;

public class ModelFishy extends EntityModelBase
{

    public ModelFishy()
    {
        Body = new Cuboid(0, 0);
        Body.method_1818(0.0F, 0.0F, 0.0F, 1, 5, 5, 0.0F);
        Body.setRotationPoint(0.0F, 19F, 0.0F);
        Body.pitch = 0.7853981F;
        Tail = new Cuboid(12, 0);
        Tail.method_1818(0.0F, 0.0F, 0.0F, 1, 3, 3, 0.0F);
        Tail.setRotationPoint(0.0F, 18.7F, 6F);
        Tail.pitch = 0.7853981F;
    }

    public void render(float f, float f1, float f2, float f3, float f4, float f5)
    {
        setAngles(f, f1, f2, f3, f4, f5);
        Body.method_1815(f5);
        Tail.method_1815(f5);
    }

    public void setAngles(float f, float f1, float f2, float f3, float f4, float f5)
    {
        Tail.yaw = MathHelper.cos(f * 0.6662F) * 1.4F * f1;
    }

    public Cuboid Body;
    public Cuboid UpperFin;
    public Cuboid LowerFin;
    public Cuboid RightFin;
    public Cuboid LeftFin;
    public Cuboid Tail;
}
