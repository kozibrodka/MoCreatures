// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) braces deadcode 

package net.kozibrodka.mocreatures.modelentity;


// Referenced classes of package net.minecraft.src:
//            ModelBiped, ModelRenderer, MathHelper

import net.minecraft.client.model.Cuboid;
import net.minecraft.client.render.entity.model.Biped;
import net.minecraft.util.maths.MathHelper;

public class ModelOgre1 extends Biped
{

    public ModelOgre1()
    {
        super(0.0F, 0.0F);
        field_619 = new Cuboid(0, 0);
        field_619.method_1818(-4F, -25F, -6F, 8, 8, 8, 0.0F);
        field_619.setRotationPoint(0.0F, 0.0F, 0.0F);
        field_626 = new Cuboid(41, 24);
        field_626.method_1818(-4F, -31F, -3F, 8, 6, 1, 0.5F);
        field_626.setRotationPoint(0.0F, 0.0F, 0.0F);
        field_621 = new Cuboid(16, 16);
        field_621.method_1818(-4F, -14F, -2F, 8, 12, 4, 3F);
        field_621.setRotationPoint(0.0F, 0.0F, 0.0F);
        field_622 = new Cuboid(0, 16);
        field_622.method_1818(-7F, -2F, -2F, 1, 1, 1, 0.0F);
        field_622.setRotationPoint(0.0F, 0.0F, 0.0F);
        field_623 = new Cuboid(0, 16);
        field_623.mirror = true;
        field_623.method_1818(0.0F, 0.0F, 0.0F, 1, 1, 1, 0.0F);
        field_623.setRotationPoint(0.0F, 0.0F, 0.0F);
        field_624 = new Cuboid(40, 0);
        field_624.method_1818(-2F, -8F, -2F, 4, 18, 4, 1.5F);
        field_624.setRotationPoint(-4F, 12F, 0.0F);
        field_625 = new Cuboid(40, 0);
        field_625.mirror = true;
        field_625.method_1818(-2F, -8F, -2F, 4, 18, 4, 1.5F);
        field_625.setRotationPoint(4F, 12F, 0.0F);
    }

    public void render(float f, float f1, float f2, float f3, float f4, float f5, boolean flag)
    {
        setAngles(f, f1, f2, f3, f4, f5);
        field_619.method_1815(f5);
        field_621.method_1815(f5);
        field_622.method_1815(f5);
        field_623.method_1815(f5);
        field_624.method_1815(f5);
        field_625.method_1815(f5);
        field_626.method_1815(f5);
    }

    public void setAngles(float f, float f1, float f2, float f3, float f4, float f5)
    {
        field_619.yaw = f3 / 57.29578F;
        field_626.yaw = field_619.yaw;
        field_626.pitch = field_619.pitch;
        field_622.pitch = MathHelper.cos(f * 0.6662F + 3.141593F) * 2.0F * f1 * 0.5F;
        field_623.pitch = MathHelper.cos(f * 0.6662F) * 2.0F * f1 * 0.5F;
        field_622.roll = 0.0F;
        field_623.roll = 0.0F;
        field_624.pitch = MathHelper.cos(f * 0.6662F) * 1.4F * f1;
        field_625.pitch = MathHelper.cos(f * 0.6662F + 3.141593F) * 1.4F * f1;
        field_624.yaw = 0.0F;
        field_625.yaw = 0.0F;
        if(isRiding)
        {
            field_622.pitch += -0.6283186F;
            field_623.pitch += -0.6283186F;
            field_624.pitch = -1.256637F;
            field_625.pitch = -1.256637F;
            field_624.yaw = 0.314159F;
            field_625.yaw = -0.314159F;
        }
        if(field_628)
        {
            field_623.pitch = field_623.pitch * 0.5F - 0.314159F;
        }
        if(field_629)
        {
            field_622.pitch = field_622.pitch * 0.5F - 0.314159F;
        }
        field_622.yaw = 0.0F;
        field_623.yaw = 0.0F;
        if(handSwingProgress > -9990F)
        {
            float f6 = handSwingProgress;
            field_621.yaw = MathHelper.sin(MathHelper.sqrt(f6) * 3.141593F * 2.0F) * 0.2F;
            field_622.rotationPointZ = MathHelper.sin(field_621.yaw) * 5F;
            field_622.rotationPointX = -MathHelper.cos(field_621.yaw) * 5F;
            field_623.rotationPointZ = -MathHelper.sin(field_621.yaw) * 5F;
            field_623.rotationPointX = MathHelper.cos(field_621.yaw) * 5F;
            field_622.yaw += field_621.yaw;
            field_623.yaw += field_621.yaw;
            field_623.pitch += field_621.yaw;
            f6 = 1.0F - handSwingProgress;
            f6 *= f6;
            f6 *= f6;
            f6 = 1.0F - f6;
            float f7 = MathHelper.sin(f6 * 3.141593F);
            float f8 = MathHelper.sin(handSwingProgress * 3.141593F) * -(field_619.pitch - 0.7F) * 0.75F;
            Cuboid modelrenderer = field_622;
            modelrenderer.pitch = (float)((double)modelrenderer.pitch - ((double)f7 * 1.2D + (double)f8));
            field_622.yaw += field_621.yaw * 2.0F;
            field_622.roll = MathHelper.sin(handSwingProgress * 3.141593F) * -0.4F;
        }
        if(field_630)
        {
            field_621.pitch = 0.5F;
            field_624.pitch -= 0.0F;
            field_625.pitch -= 0.0F;
            field_622.pitch += 0.4F;
            field_623.pitch += 0.4F;
            field_624.rotationPointZ = 4F;
            field_625.rotationPointZ = 4F;
            field_624.rotationPointY = 9F;
            field_625.rotationPointY = 9F;
            field_619.rotationPointY = 1.0F;
        } else
        {
            field_621.pitch = 0.0F;
            field_624.rotationPointZ = 0.0F;
            field_625.rotationPointZ = 0.0F;
            field_624.rotationPointY = 12F;
            field_625.rotationPointY = 12F;
            field_619.rotationPointY = 0.0F;
        }
        field_622.roll += MathHelper.cos(f2 * 0.09F) * 0.05F + 0.05F;
        field_623.roll -= MathHelper.cos(f2 * 0.09F) * 0.05F + 0.05F;
        field_622.pitch += MathHelper.sin(f2 * 0.067F) * 0.05F;
        field_623.pitch -= MathHelper.sin(f2 * 0.067F) * 0.05F;
    }
}
