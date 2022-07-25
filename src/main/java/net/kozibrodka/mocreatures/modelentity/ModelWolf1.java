// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) braces deadcode 

package net.kozibrodka.mocreatures.modelentity;


// Referenced classes of package net.minecraft.src:
//            ModelQuadruped, ModelRenderer

import net.minecraft.client.model.Cuboid;
import net.minecraft.client.render.entity.model.AnimalQuadrupedModelBase;

public class ModelWolf1 extends AnimalQuadrupedModelBase
{

    public ModelWolf1()
    {
        super(10, 0.0F);
        cuboid1 = new Cuboid(12, 0);
        cuboid1.method_1818(-5F, -5F, -2F, 10, 14, 3, 0.0F);
        cuboid1.setRotationPoint(0.0F, 4F, -8F);
        cuboid2 = new Cuboid(24, 16);
        cuboid2.method_1818(-6F, -11F, -10F, 12, 8, 8, 0.0F);
        cuboid2.setRotationPoint(0.0F, 5F, 2.0F);
    }
}
