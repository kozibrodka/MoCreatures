// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) braces deadcode 

package net.kozibrodka.mocreatures.modelentity;


// Referenced classes of package net.minecraft.src:
//            ModelQuadruped, ModelRenderer

import net.minecraft.client.model.Cuboid;
import net.minecraft.client.render.entity.model.AnimalQuadrupedModelBase;

public class ModelWolf2 extends AnimalQuadrupedModelBase
{

    public ModelWolf2()
    {
        super(10, 0.0F);
        cuboid1 = new Cuboid(0, 0);
        cuboid1.method_1818(-4F, -2F, -6F, 8, 8, 6, 0.0F);
        cuboid1.setRotationPoint(0.0F, 4F, -8F);
        b = new Cuboid(8, 15);
        b.method_1818(-2F, 2.0F, -11F, 4, 4, 6, 0.0F);
        b.setRotationPoint(0.0F, 4F, -8F);
        cuboid2 = new Cuboid(28, 6);
        cuboid2.method_1818(-5F, -8F, -9F, 10, 16, 6, 0.0F);
        cuboid2.setRotationPoint(0.0F, 5F, 2.0F);
    }

    public void render(float f, float f1, float f2, float f3, float f4, float f5)
    {
        super.render(f, f1, f2, f3, f4, f5);
        b.method_1815(f5);
    }

    public void setAngles(float f, float f1, float f2, float f3, float f4, float f5)
    {
        super.setAngles(f, f1, f2, f3, f4, f5);
        b.yaw = cuboid1.yaw;
        b.pitch = cuboid1.pitch;
    }

    Cuboid b;
}
