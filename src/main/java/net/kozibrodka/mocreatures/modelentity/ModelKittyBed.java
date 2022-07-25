// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) braces deadcode 

package net.kozibrodka.mocreatures.modelentity;


import net.minecraft.client.model.Cuboid;
import net.minecraft.client.render.entity.model.EntityModelBase;

public class ModelKittyBed extends EntityModelBase
{

    public ModelKittyBed()
    {
        float f = 0.0F;
        TableL = new Cuboid(30, 8);
        TableL.method_1818(-8F, 0.0F, 7F, 16, 6, 1, f);
        TableL.setRotationPoint(0.0F, 18F, 0.0F);
        TableR = new Cuboid(30, 8);
        TableR.method_1818(-8F, 18F, -8F, 16, 6, 1, f);
        TableR.setRotationPoint(0.0F, 0.0F, 0.0F);
        Table_B = new Cuboid(30, 0);
        Table_B.method_1818(-8F, -3F, 0.0F, 16, 6, 1, f);
        Table_B.setRotationPoint(8F, 21F, 0.0F);
        Table_B.yaw = 1.5708F;
        FoodT = new Cuboid(14, 0);
        FoodT.method_1818(1.0F, 1.0F, 1.0F, 4, 1, 4, f);
        FoodT.setRotationPoint(-16F, 22F, 0.0F);
        FoodTraySide = new Cuboid(0, 0);
        FoodTraySide.method_1818(-16F, 21F, 5F, 5, 3, 1, f);
        FoodTraySide.setRotationPoint(0.0F, 0.0F, 0.0F);
        FoodTraySideB = new Cuboid(0, 0);
        FoodTraySideB.method_1818(-15F, 21F, 0.0F, 5, 3, 1, f);
        FoodTraySideB.setRotationPoint(0.0F, 0.0F, 0.0F);
        FoodTraySideC = new Cuboid(0, 0);
        FoodTraySideC.method_1818(-3F, -1F, 0.0F, 5, 3, 1, f);
        FoodTraySideC.setRotationPoint(-16F, 22F, 2.0F);
        FoodTraySideC.yaw = 1.5708F;
        FoodTraySideD = new Cuboid(0, 0);
        FoodTraySideD.method_1818(-3F, -1F, 0.0F, 5, 3, 1, f);
        FoodTraySideD.setRotationPoint(-11F, 22F, 3F);
        FoodTraySideD.yaw = 1.5708F;
        Milk = new Cuboid(14, 9);
        Milk.method_1818(0.0F, 0.0F, 0.0F, 4, 1, 4, f);
        Milk.setRotationPoint(-15F, 21F, 1.0F);
        PetFood = new Cuboid(0, 9);
        PetFood.method_1818(0.0F, 0.0F, 0.0F, 4, 1, 4, f);
        PetFood.setRotationPoint(-15F, 21F, 1.0F);
        Bottom = new Cuboid(16, 15);
        Bottom.method_1818(-10F, 0.0F, -7F, 16, 1, 14, f);
        Bottom.setRotationPoint(2.0F, 23F, 0.0F);
    }

    public void render(float f, float f1, float f2, float f3, float f4, float f5)
    {
        TableL.method_1815(f5);
        TableR.method_1815(f5);
        Table_B.method_1815(f5);
        Bottom.method_1815(f5);
        if(!pickedUp)
        {
            FoodT.method_1815(f5);
            FoodTraySide.method_1815(f5);
            FoodTraySideB.method_1815(f5);
            FoodTraySideC.method_1815(f5);
            FoodTraySideD.method_1815(f5);
            if(hasMilk)
            {
                Milk.rotationPointY = 21F + milklevel;
                Milk.method_1815(f5);
            }
            if(hasFood)
            {
                PetFood.rotationPointY = 21F + milklevel;
                PetFood.method_1815(f5);
            }
        }
    }

    Cuboid TableL;
    Cuboid TableR;
    Cuboid Table_B;
    Cuboid FoodT;
    Cuboid FoodTraySide;
    Cuboid FoodTraySideB;
    Cuboid FoodTraySideC;
    Cuboid FoodTraySideD;
    Cuboid Milk;
    Cuboid PetFood;
    Cuboid Bottom;
    public boolean hasMilk;
    public boolean hasFood;
    public boolean pickedUp;
    public float milklevel;
}
