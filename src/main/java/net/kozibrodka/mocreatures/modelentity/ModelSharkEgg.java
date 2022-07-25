// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) braces deadcode 

package net.kozibrodka.mocreatures.modelentity;



import net.minecraft.client.model.Cuboid;
import net.minecraft.client.render.entity.model.EntityModelBase;

public class ModelSharkEgg extends EntityModelBase
{

    public ModelSharkEgg()
    {
        Egg = new Cuboid(0, 0);
        Egg.method_1818(0.0F, 0.0F, 0.0F, 3, 1, 6, 0.0F);
        Egg.setRotationPoint(-2F, 23F, -2F);
    }

    public void render(float f, float f1, float f2, float f3, float f4, float f5)
    {
        setAngles(f, f1, f2, f3, f4, f5);
        Egg.method_1815(f5);
    }

    public Cuboid Egg;
}
