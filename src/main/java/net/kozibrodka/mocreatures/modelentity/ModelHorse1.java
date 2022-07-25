// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) braces deadcode 

package net.kozibrodka.mocreatures.modelentity;


import net.minecraft.client.model.Cuboid;
import net.minecraft.client.render.entity.model.EntityModelBase;
import net.minecraft.util.maths.MathHelper;

public class ModelHorse1 extends EntityModelBase
{

    public ModelHorse1()
    {
        Head = new Cuboid(0, 0);
        Head.method_1818(-2.5F, 0.0F, -5.5F, 5, 5, 11, 0.0F);
        Head.setRotationPoint(0.0F, -5.5F, -13.8F);
        Head.pitch = 0.3490658F;
        Unicorn = new Cuboid(50, 0);
        Unicorn.method_1818(-2.5F, 0.0F, -5.5F, 0, 8, 3, 0.0F);
        Unicorn.setRotationPoint(2.5F, -15F, -11F);
        Unicorn.pitch = 0.3490658F;
        Ear1 = new Cuboid(0, 0);
        Ear1.method_1818(-0.5F, 0.0F, -1F, 1, 2, 2, 0.0F);
        Ear1.setRotationPoint(1.8F, -8F, -11F);
        Ear1.pitch = 0.3490658F;
        Ear2 = new Cuboid(0, 0);
        Ear2.method_1818(-0.5F, 0.0F, -1F, 1, 2, 2, 0.0F);
        Ear2.setRotationPoint(-1.8F, -8F, -11F);
        Ear2.mirror = true;
        Ear2.pitch = 0.3490658F;
        Neck = new Cuboid(0, 10);
        Neck.method_1818(-2F, 0.0F, -4F, 4, 14, 8, 0.0F);
        Neck.setRotationPoint(0.0F, -5F, -12F);
        Neck.pitch = 0.5235988F;
        RightWing = new Cuboid(34, 0);
        RightWing.method_1818(0.0F, 0.0F, -5F, 1, 20, 12, 0.5F);
        RightWing.setRotationPoint(-6.6F, 2.5F, 0.0F);
        LeftWing = new Cuboid(34, 0);
        LeftWing.method_1818(-1F, 0.0F, -5F, 1, 20, 12, 0.5F);
        LeftWing.setRotationPoint(6.6F, 2.5F, 0.0F);
        LeftWing.mirror = true;
        Bag1 = new Cuboid(22, 0);
        Bag1.method_1818(0.0F, 0.0F, 0.0F, 8, 8, 3, -0.5F);
        Bag1.setRotationPoint(-7.5F, 3F, 10F);
        Bag1.yaw = 1.570796F;
        Bag2 = new Cuboid(22, 0);
        Bag2.method_1818(0.0F, 0.0F, 0.0F, 8, 8, 3, -0.5F);
        Bag2.setRotationPoint(4.5F, 3F, 10F);
        Bag2.yaw = 1.570796F;
    }

    public void render(float f, float f1, float f2, float f3, float f4, float f5)
    {
        setAngles(f, f1, f2, f3, f4, f5);
        Head.method_1815(f5);
        Ear1.method_1815(f5);
        Ear2.method_1815(f5);
        Neck.method_1815(f5);
        Unicorn.method_1815(f5);
        RightWing.method_1815(f5);
        LeftWing.method_1815(f5);
        if(chested)
        {
            Bag1.method_1815(f5);
            Bag2.method_1815(f5);
        }
    }

    public void setAngles(float f, float f1, float f2, float f3, float f4, float f5)
    {
        RightWing.roll = f2;
        LeftWing.roll = -f2;
        Bag1.pitch = (MathHelper.cos(f * 0.6662F) * 1.4F * f2) / 10F;
        Bag2.pitch = (MathHelper.cos(f * 0.6662F) * 1.4F * f2) / 10F;
    }

    public Cuboid Head;
    public Cuboid Ear1;
    public Cuboid Ear2;
    public Cuboid Neck;
    public Cuboid RightWing;
    public Cuboid LeftWing;
    public Cuboid Unicorn;
    public Cuboid Bag1;
    public Cuboid Bag2;
    public boolean chested;
}
