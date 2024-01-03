// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) braces deadcode 

package net.kozibrodka.mocreatures.modelentity;


import net.minecraft.client.model.ModelPart;
import net.minecraft.client.render.entity.model.EntityModel;

public class ModelLitterBox extends EntityModel
{

    public ModelLitterBox()
    {
        float f = 0.0F;
        Table1 = new ModelPart(30, 0);
        Table1.addCuboid(-8F, 0.0F, 7F, 16, 6, 1, f);
        Table1.setPivot(0.0F, 18F, 0.0F);
        Table3 = new ModelPart(30, 0);
        Table3.addCuboid(-8F, 18F, -8F, 16, 6, 1, f);
        Table3.setPivot(0.0F, 0.0F, 0.0F);
        Table2 = new ModelPart(30, 0);
        Table2.addCuboid(-8F, -3F, 0.0F, 16, 6, 1, f);
        Table2.setPivot(8F, 21F, 0.0F);
        Table2.yaw = 1.5708F;
        Litter = new ModelPart(0, 15);
        Litter.addCuboid(0.0F, 0.0F, 0.0F, 16, 2, 14, f);
        Litter.setPivot(-8F, 21F, -7F);
        Table4 = new ModelPart(30, 0);
        Table4.addCuboid(-8F, -3F, 0.0F, 16, 6, 1, f);
        Table4.setPivot(-9F, 21F, 0.0F);
        Table4.yaw = 1.5708F;
        LitterUsed = new ModelPart(16, 15);
        LitterUsed.addCuboid(0.0F, 0.0F, 0.0F, 16, 2, 14, f);
        LitterUsed.setPivot(-8F, 21F, -7F);
        Bottom = new ModelPart(16, 15);
        Bottom.addCuboid(-10F, 0.0F, -7F, 16, 1, 14, f);
        Bottom.setPivot(2.0F, 23F, 0.0F);
    }

    public void render(float f, float f1, float f2, float f3, float f4, float f5)
    {
        Table1.render(f5);
        Table3.render(f5);
        Table2.render(f5);
        Table4.render(f5);
        Bottom.render(f5);
        if(usedlitter)
        {
            LitterUsed.render(f5);
        } else
        {
            Litter.render(f5);
        }
    }

    ModelPart Table1;
    ModelPart Table3;
    ModelPart Table2;
    ModelPart Litter;
    ModelPart Table4;
    ModelPart Bottom;
    ModelPart LitterUsed;
    public boolean usedlitter;
}
