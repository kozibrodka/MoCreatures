// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) braces deadcode 

package net.kozibrodka.mocreatures.modelentity;


import net.minecraft.client.model.ModelPart;
import net.minecraft.client.render.entity.model.EntityModel;

public class ModelKittyBed2 extends EntityModel
{

    public ModelKittyBed2()
    {
        float f = 0.0F;
        Sheet = new ModelPart(0, 15);
        Sheet.addCuboid(0.0F, 0.0F, 0.0F, 16, 3, 14, f);
        Sheet.setPivot(-8F, 21F, -7F);
    }

    public void render(float f, float f1, float f2, float f3, float f4, float f5)
    {
        Sheet.render(f5);
    }

    ModelPart Sheet;
}
