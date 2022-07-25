// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) braces deadcode 

package net.kozibrodka.mocreatures.modelentity;


import net.minecraft.client.model.Cuboid;
import net.minecraft.client.render.entity.model.EntityModelBase;

public class ModelKittyBed2 extends EntityModelBase
{

    public ModelKittyBed2()
    {
        float f = 0.0F;
        Sheet = new Cuboid(0, 15);
        Sheet.method_1818(0.0F, 0.0F, 0.0F, 16, 3, 14, f);
        Sheet.setRotationPoint(-8F, 21F, -7F);
    }

    public void render(float f, float f1, float f2, float f3, float f4, float f5)
    {
        Sheet.method_1815(f5);
    }

    Cuboid Sheet;
}
