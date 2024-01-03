// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) braces deadcode 

package net.kozibrodka.mocreatures.modelentity;



import net.minecraft.client.model.ModelPart;
import net.minecraft.client.render.entity.model.EntityModel;

public class ModelSharkEgg extends EntityModel
{

    public ModelSharkEgg()
    {
        Egg = new ModelPart(0, 0);
        Egg.addCuboid(0.0F, 0.0F, 0.0F, 3, 1, 6, 0.0F);
        Egg.setPivot(-2F, 23F, -2F);
    }

    public void render(float f, float f1, float f2, float f3, float f4, float f5)
    {
        setAngles(f, f1, f2, f3, f4, f5);
        Egg.render(f5);
    }

    public ModelPart Egg;
}
