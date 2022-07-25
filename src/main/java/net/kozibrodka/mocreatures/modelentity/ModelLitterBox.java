// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) braces deadcode 

package net.kozibrodka.mocreatures.modelentity;


import net.minecraft.client.model.Cuboid;
import net.minecraft.client.render.entity.model.EntityModelBase;

public class ModelLitterBox extends EntityModelBase
{

    public ModelLitterBox()
    {
        float f = 0.0F;
        Table1 = new Cuboid(30, 0);
        Table1.method_1818(-8F, 0.0F, 7F, 16, 6, 1, f);
        Table1.setRotationPoint(0.0F, 18F, 0.0F);
        Table3 = new Cuboid(30, 0);
        Table3.method_1818(-8F, 18F, -8F, 16, 6, 1, f);
        Table3.setRotationPoint(0.0F, 0.0F, 0.0F);
        Table2 = new Cuboid(30, 0);
        Table2.method_1818(-8F, -3F, 0.0F, 16, 6, 1, f);
        Table2.setRotationPoint(8F, 21F, 0.0F);
        Table2.yaw = 1.5708F;
        Litter = new Cuboid(0, 15);
        Litter.method_1818(0.0F, 0.0F, 0.0F, 16, 2, 14, f);
        Litter.setRotationPoint(-8F, 21F, -7F);
        Table4 = new Cuboid(30, 0);
        Table4.method_1818(-8F, -3F, 0.0F, 16, 6, 1, f);
        Table4.setRotationPoint(-9F, 21F, 0.0F);
        Table4.yaw = 1.5708F;
        LitterUsed = new Cuboid(16, 15);
        LitterUsed.method_1818(0.0F, 0.0F, 0.0F, 16, 2, 14, f);
        LitterUsed.setRotationPoint(-8F, 21F, -7F);
        Bottom = new Cuboid(16, 15);
        Bottom.method_1818(-10F, 0.0F, -7F, 16, 1, 14, f);
        Bottom.setRotationPoint(2.0F, 23F, 0.0F);
    }

    public void render(float f, float f1, float f2, float f3, float f4, float f5)
    {
        Table1.method_1815(f5);
        Table3.method_1815(f5);
        Table2.method_1815(f5);
        Table4.method_1815(f5);
        Bottom.method_1815(f5);
        if(usedlitter)
        {
            LitterUsed.method_1815(f5);
        } else
        {
            Litter.method_1815(f5);
        }
    }

    Cuboid Table1;
    Cuboid Table3;
    Cuboid Table2;
    Cuboid Litter;
    Cuboid Table4;
    Cuboid Bottom;
    Cuboid LitterUsed;
    public boolean usedlitter;
}
