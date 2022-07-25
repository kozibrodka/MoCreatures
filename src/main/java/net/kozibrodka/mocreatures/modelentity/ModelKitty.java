// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) braces deadcode 

package net.kozibrodka.mocreatures.modelentity;

import net.minecraft.client.model.Cuboid;
import net.minecraft.client.render.entity.model.Biped;
import net.minecraft.util.maths.MathHelper;
import org.lwjgl.opengl.GL11;


public class ModelKitty extends Biped
{

    public ModelKitty()
    {
        this(0.0F);
    }

    public ModelKitty(float f)
    {
        this(f, 0.0F);
    }

    public ModelKitty(float f, float f1)
    {
        super(f, f1);
        field_619 = new Cuboid(1, 1);
        field_619.method_1818(-2.5F, -3F, -4F, 5, 4, 4, f);
        field_619.setRotationPoint(0.0F, 0.0F + f1, -2F);
        field_620 = new Cuboid(0, 0);
        field_620.method_1818(-4F, -3F, -4F, 1, 1, 1, f + 0.5F);
        field_620.setRotationPoint(0.0F, 0.0F + f1, -2F);
        bipedHeadParts = new Cuboid[9];
        bipedHeadParts[0] = new Cuboid(16, 0);
        bipedHeadParts[0].method_1818(-2F, -5F, -3F, 1, 1, 1, f);
        bipedHeadParts[0].setRotationPoint(0.0F, 0.0F + f1, -2F);
        bipedHeadParts[1] = new Cuboid(16, 0);
        bipedHeadParts[1].mirror = true;
        bipedHeadParts[1].method_1818(1.0F, -5F, -3F, 1, 1, 1, f);
        bipedHeadParts[1].setRotationPoint(0.0F, 0.0F + f1, -2F);
        bipedHeadParts[2] = new Cuboid(20, 0);
        bipedHeadParts[2].method_1818(-2.5F, -4F, -3F, 2, 1, 1, f);
        bipedHeadParts[2].setRotationPoint(0.0F, 0.0F + f1, -2F);
        bipedHeadParts[3] = new Cuboid(20, 0);
        bipedHeadParts[3].mirror = true;
        bipedHeadParts[3].method_1818(0.5F, -4F, -3F, 2, 1, 1, f);
        bipedHeadParts[3].setRotationPoint(0.0F, 0.0F + f1, -2F);
        bipedHeadParts[4] = new Cuboid(40, 0);
        bipedHeadParts[4].method_1818(-4F, -1.5F, -5F, 3, 3, 1, f);
        bipedHeadParts[4].setRotationPoint(0.0F, 0.0F + f1, -2F);
        bipedHeadParts[5] = new Cuboid(40, 0);
        bipedHeadParts[5].mirror = true;
        bipedHeadParts[5].method_1818(1.0F, -1.5F, -5F, 3, 3, 1, f);
        bipedHeadParts[5].setRotationPoint(0.0F, 0.0F + f1, -2F);
        bipedHeadParts[6] = new Cuboid(21, 6);
        bipedHeadParts[6].method_1818(-1F, -1F, -5F, 2, 2, 1, f);
        bipedHeadParts[6].setRotationPoint(0.0F, 0.0F + f1, -2F);
        bipedHeadParts[7] = new Cuboid(50, 0);
        bipedHeadParts[7].method_1818(-2.5F, 0.5F, -1F, 5, 4, 1, f);
        bipedHeadParts[7].setRotationPoint(0.0F, 0.0F + f1, -2F);
        bipedHeadParts[8] = new Cuboid(60, 0);
        bipedHeadParts[8].method_1818(-1.5F, -2F, -4.1F, 3, 1, 1, f);
        bipedHeadParts[8].setRotationPoint(0.0F, 0.0F + f1, -2F);
        field_621 = new Cuboid(20, 0);
        field_621.method_1818(-2.5F, -2F, -0F, 5, 5, 10, f);
        field_621.setRotationPoint(0.0F, 0.0F + f1, -2F);
        field_622 = new Cuboid(0, 9);
        field_622.method_1818(-1F, 0.0F, -1F, 2, 6, 2, f);
        field_622.setRotationPoint(-1.5F, 3F + f1, -1F);
        field_623 = new Cuboid(0, 9);
        field_623.mirror = true;
        field_623.method_1818(-1F, 0.0F, -1F, 2, 6, 2, f);
        field_623.setRotationPoint(1.5F, 3F + f1, -1F);
        field_624 = new Cuboid(8, 9);
        field_624.method_1818(-1F, 0.0F, -1F, 2, 6, 2, f);
        field_624.setRotationPoint(-1.5F, 3F + f1, 7F);
        field_625 = new Cuboid(8, 9);
        field_625.mirror = true;
        field_625.method_1818(-1F, 0.0F, -1F, 2, 6, 2, f);
        field_625.setRotationPoint(1.5F, 3F + f1, 7F);
        bipedTail = new Cuboid(16, 9);
        bipedTail.mirror = true;
        bipedTail.method_1818(-0.5F, -8F, -1F, 1, 8, 1, f);
        bipedTail.setRotationPoint(0.0F, -0.5F + f1, 7.5F);
    }

    public void render(float f, float f1, float f2, float f3, float f4, float f5)
    {
        GL11.glPushMatrix();
        setAngles(f, f1, f2, f3, f4, f5);
        if(isSitting)
        {
            GL11.glTranslatef(0.0F, 0.25F, 0.0F);
            bipedTail.roll = 0.0F;
            bipedTail.pitch = -2.3F;
        }
        field_619.method_1815(f5);
        for(int i = 0; i < 7; i++)
        {
            bipedHeadParts[i].method_1815(f5);
        }

        if(kittystate > 2)
        {
            bipedHeadParts[7].method_1815(f5);
        }
        if(kittystate == 12)
        {
            bipedHeadParts[8].method_1815(f5);
        }
        field_621.method_1815(f5);
        bipedTail.method_1815(f5);
        if(isSitting)
        {
            GL11.glTranslatef(0.0F, 0.0625F, 0.0625F);
            float f6 = -1.570796F;
            field_622.pitch = f6;
            field_623.pitch = f6;
            field_624.pitch = f6;
            field_625.pitch = f6;
            field_624.yaw = 0.1F;
            field_625.yaw = -0.1F;
        }
        field_622.method_1815(f5);
        field_623.method_1815(f5);
        field_624.method_1815(f5);
        field_625.method_1815(f5);
        GL11.glPopMatrix();
    }

    public void setAngles(float f, float f1, float f2, float f3, float f4, float f5)
    {
        field_619.yaw = f3 / 57.29578F;
        field_619.pitch = f4 / 57.29578F;
        for(int i = 0; i < 9; i++)
        {
            bipedHeadParts[i].yaw = field_619.yaw;
            bipedHeadParts[i].pitch = field_619.pitch;
        }

        field_620.yaw = field_619.yaw;
        field_620.pitch = field_619.pitch;
        field_622.pitch = MathHelper.cos(f * 0.6662F + 3.141593F) * 2.0F * f1 * 0.5F;
        field_623.pitch = MathHelper.cos(f * 0.6662F) * 2.0F * f1 * 0.5F;
        field_622.roll = 0.0F;
        field_623.roll = 0.0F;
        field_624.pitch = MathHelper.cos(f * 0.6662F) * 1.4F * f1;
        field_625.pitch = MathHelper.cos(f * 0.6662F + 3.141593F) * 1.4F * f1;
        field_624.yaw = 0.0F;
        field_625.yaw = 0.0F;
        if(field_628)
        {
            field_623.pitch = field_623.pitch * 0.5F - 0.3141593F;
        }
        if(field_629)
        {
            field_622.pitch = field_622.pitch * 0.5F - 0.3141593F;
        }
        if(isSwinging)
        {
            field_622.pitch = -2F + swingProgress;
            field_622.yaw = 2.25F - swingProgress * 2.0F;
        } else
        {
            field_622.yaw = 0.0F;
        }
        field_623.yaw = 0.0F;
        bipedTail.pitch = -0.5F;
        bipedTail.roll = field_625.pitch * 0.625F;
    }

    public boolean isSitting;
    public boolean isSwinging;
    public float swingProgress;
    public Cuboid bipedHeadParts[];
    public static final int parts = 9;
    public Cuboid bipedTail;
    public int kittystate;
}
