// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) braces deadcode 

package net.kozibrodka.mocreatures.modelentity;

import net.minecraft.client.model.Cuboid;
import net.minecraft.client.render.entity.model.AnimalQuadrupedModelBase;

public class ModelBear1 extends AnimalQuadrupedModelBase
{

    public ModelBear1()
    {
        super(12, 0.0F);
        cuboid1 = new Cuboid(0, 0);
        cuboid1.method_1818(-5F, -8F, -2F, 10, 4, 1, 0.0F);
        cuboid1.setRotationPoint(0.0F, 4F, -8F);
        cuboid2 = new Cuboid(20, 0);
        cuboid2.method_1818(-2F, 9F, -4F, 4, 2, 4, 0.0F);
        cuboid2.setRotationPoint(0.0F, 5F, 2.0F);
    }
}
