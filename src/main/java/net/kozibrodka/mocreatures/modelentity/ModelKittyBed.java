// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) braces deadcode 

package net.kozibrodka.mocreatures.modelentity;


import net.minecraft.client.model.ModelPart;
import net.minecraft.client.render.entity.model.EntityModel;

public class ModelKittyBed extends EntityModel
{

    public ModelKittyBed()
    {
        float f = 0.0F;
        TableL = new ModelPart(30, 8);
        TableL.addCuboid(-8F, 0.0F, 7F, 16, 6, 1, f);
        TableL.setPivot(0.0F, 18F, 0.0F);
        TableR = new ModelPart(30, 8);
        TableR.addCuboid(-8F, 18F, -8F, 16, 6, 1, f);
        TableR.setPivot(0.0F, 0.0F, 0.0F);
        Table_B = new ModelPart(30, 0);
        Table_B.addCuboid(-8F, -3F, 0.0F, 16, 6, 1, f);
        Table_B.setPivot(8F, 21F, 0.0F);
        Table_B.yaw = 1.5708F;
        FoodT = new ModelPart(14, 0);
        FoodT.addCuboid(1.0F, 1.0F, 1.0F, 4, 1, 4, f);
        FoodT.setPivot(-16F, 22F, 0.0F);
        FoodTraySide = new ModelPart(0, 0);
        FoodTraySide.addCuboid(-16F, 21F, 5F, 5, 3, 1, f);
        FoodTraySide.setPivot(0.0F, 0.0F, 0.0F);
        FoodTraySideB = new ModelPart(0, 0);
        FoodTraySideB.addCuboid(-15F, 21F, 0.0F, 5, 3, 1, f);
        FoodTraySideB.setPivot(0.0F, 0.0F, 0.0F);
        FoodTraySideC = new ModelPart(0, 0);
        FoodTraySideC.addCuboid(-3F, -1F, 0.0F, 5, 3, 1, f);
        FoodTraySideC.setPivot(-16F, 22F, 2.0F);
        FoodTraySideC.yaw = 1.5708F;
        FoodTraySideD = new ModelPart(0, 0);
        FoodTraySideD.addCuboid(-3F, -1F, 0.0F, 5, 3, 1, f);
        FoodTraySideD.setPivot(-11F, 22F, 3F);
        FoodTraySideD.yaw = 1.5708F;
        Milk = new ModelPart(14, 9);
        Milk.addCuboid(0.0F, 0.0F, 0.0F, 4, 1, 4, f);
        Milk.setPivot(-15F, 21F, 1.0F);
        PetFood = new ModelPart(0, 9);
        PetFood.addCuboid(0.0F, 0.0F, 0.0F, 4, 1, 4, f);
        PetFood.setPivot(-15F, 21F, 1.0F);
        Bottom = new ModelPart(16, 15);
        Bottom.addCuboid(-10F, 0.0F, -7F, 16, 1, 14, f);
        Bottom.setPivot(2.0F, 23F, 0.0F);
    }

    public void render(float f, float f1, float f2, float f3, float f4, float f5)
    {
        TableL.render(f5);
        TableR.render(f5);
        Table_B.render(f5);
        Bottom.render(f5);
        if(!pickedUp)
        {
            FoodT.render(f5);
            FoodTraySide.render(f5);
            FoodTraySideB.render(f5);
            FoodTraySideC.render(f5);
            FoodTraySideD.render(f5);
            if(hasMilk)
            {
                Milk.pivotY = 21F + milklevel;
                Milk.render(f5);
            }
            if(hasFood)
            {
                PetFood.pivotY = 21F + milklevel;
                PetFood.render(f5);
            }
        }
    }

    ModelPart TableL;
    ModelPart TableR;
    ModelPart Table_B;
    ModelPart FoodT;
    ModelPart FoodTraySide;
    ModelPart FoodTraySideB;
    ModelPart FoodTraySideC;
    ModelPart FoodTraySideD;
    ModelPart Milk;
    ModelPart PetFood;
    ModelPart Bottom;
    public boolean hasMilk;
    public boolean hasFood;
    public boolean pickedUp;
    public float milklevel;
}
