// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) braces deadcode 

package net.kozibrodka.mocreatures.modelentity;


import net.minecraft.client.model.ModelPart;
import net.minecraft.client.render.entity.model.EntityModel;
import net.minecraft.util.math.MathHelper;

public class ModelHorse1 extends EntityModel
{

    public ModelHorse1()
    {
        Head = new ModelPart(0, 0);
        Head.addCuboid(-2.5F, 0.0F, -5.5F, 5, 5, 11, 0.0F);
        Head.setPivot(0.0F, -5.5F, -13.8F);
        Head.pitch = 0.3490658F;
        Unicorn = new ModelPart(50, 0);
        Unicorn.addCuboid(-2.5F, 0.0F, -5.5F, 0, 8, 3, 0.0F);
        Unicorn.setPivot(2.5F, -15F, -11F);
        Unicorn.pitch = 0.3490658F;
        Ear1 = new ModelPart(0, 0);
        Ear1.addCuboid(-0.5F, 0.0F, -1F, 1, 2, 2, 0.0F);
        Ear1.setPivot(1.8F, -8F, -11F);
        Ear1.pitch = 0.3490658F;
        Ear2 = new ModelPart(0, 0);
        Ear2.addCuboid(-0.5F, 0.0F, -1F, 1, 2, 2, 0.0F);
        Ear2.setPivot(-1.8F, -8F, -11F);
        Ear2.mirror = true;
        Ear2.pitch = 0.3490658F;
        Neck = new ModelPart(0, 10);
        Neck.addCuboid(-2F, 0.0F, -4F, 4, 14, 8, 0.0F);
        Neck.setPivot(0.0F, -5F, -12F);
        Neck.pitch = 0.5235988F;
        RightWing = new ModelPart(34, 0);
        RightWing.addCuboid(0.0F, 0.0F, -5F, 1, 20, 12, 0.5F);
        RightWing.setPivot(-6.6F, 2.5F, 0.0F);
        LeftWing = new ModelPart(34, 0);
        LeftWing.addCuboid(-1F, 0.0F, -5F, 1, 20, 12, 0.5F);
        LeftWing.setPivot(6.6F, 2.5F, 0.0F);
        LeftWing.mirror = true;
        Bag1 = new ModelPart(22, 0);
        Bag1.addCuboid(0.0F, 0.0F, 0.0F, 8, 8, 3, -0.5F);
        Bag1.setPivot(-7.5F, 3F, 10F);
        Bag1.yaw = 1.570796F;
        Bag2 = new ModelPart(22, 0);
        Bag2.addCuboid(0.0F, 0.0F, 0.0F, 8, 8, 3, -0.5F);
        Bag2.setPivot(4.5F, 3F, 10F);
        Bag2.yaw = 1.570796F;
    }

    public void render(float f, float f1, float f2, float f3, float f4, float f5)
    {
        setAngles(f, f1, f2, f3, f4, f5);
        Head.render(f5);
        Ear1.render(f5);
        Ear2.render(f5);
        Neck.render(f5);
        Unicorn.render(f5);
        RightWing.render(f5);
        LeftWing.render(f5);
        if(chested)
        {
            Bag1.render(f5);
            Bag2.render(f5);
        }
    }

    public void setAngles(float f, float f1, float f2, float f3, float f4, float f5)
    {
        RightWing.roll = f2;
        LeftWing.roll = -f2;
        Bag1.pitch = (MathHelper.cos(f * 0.6662F) * 1.4F * f2) / 10F;
        Bag2.pitch = (MathHelper.cos(f * 0.6662F) * 1.4F * f2) / 10F;
    }

    public ModelPart Head;
    public ModelPart Ear1;
    public ModelPart Ear2;
    public ModelPart Neck;
    public ModelPart RightWing;
    public ModelPart LeftWing;
    public ModelPart Unicorn;
    public ModelPart Bag1;
    public ModelPart Bag2;
    public boolean chested;
}
