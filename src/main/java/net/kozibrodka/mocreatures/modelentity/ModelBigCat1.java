// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) braces deadcode 

package net.kozibrodka.mocreatures.modelentity;

import net.minecraft.client.model.Cuboid;
import net.minecraft.client.render.entity.model.AnimalQuadrupedModelBase;

public class ModelBigCat1 extends AnimalQuadrupedModelBase
{

    public ModelBigCat1()
    {
        super(12, 0.0F);
        cuboid1 = new Cuboid(20, 0);
        cuboid1.method_1818(-7F, -8F, -2F, 14, 14, 8, 0.0F);
        cuboid1.setRotationPoint(0.0F, 4F, -8F);
        cuboid2 = new Cuboid(20, 0);
        cuboid2.method_1818(-6F, -11F, -8F, 12, 10, 10, 0.0F);
        cuboid2.setRotationPoint(0.0F, 5F, 2.0F);
    }
}
