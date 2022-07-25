// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) braces deadcode 

package net.kozibrodka.mocreatures.modelentity;


// Referenced classes of package net.minecraft.src:
//            ModelBiped, ModelRenderer, MathHelper

import net.minecraft.client.model.Cuboid;
import net.minecraft.client.render.entity.model.Biped;
import net.minecraft.util.maths.MathHelper;

public class ModelWraith extends Biped
{

    public ModelWraith()
    {
        super(12F, 0.0F);
        field_628 = false;
        field_629 = false;
        field_630 = false;
        field_619 = new Cuboid(0, 40);
        field_619.method_1818(-4F, -8F, -4F, 1, 1, 1, 0.0F);
        field_619.setRotationPoint(0.0F, 0.0F, 0.0F);
        field_620 = new Cuboid(0, 0);
        field_620.method_1818(-5F, -8F, -4F, 8, 8, 8, 0.0F);
        field_620.setRotationPoint(0.0F, 0.0F, 0.0F);
        field_621 = new Cuboid(36, 0);
        field_621.method_1818(-6F, 0.0F, -2F, 10, 20, 4, 0.0F);
        field_621.setRotationPoint(0.0F, 0.0F, 0.0F);
        field_622 = new Cuboid(16, 16);
        field_622.method_1818(-5F, -2F, -2F, 4, 12, 4, 0.0F);
        field_622.setRotationPoint(-5F, 2.0F, 0.0F);
        field_623 = new Cuboid(16, 16);
        field_623.mirror = true;
        field_623.method_1818(-1F, -2F, -2F, 4, 12, 4, 0.0F);
        field_623.setRotationPoint(5F, 2.0F, 0.0F);
        field_624 = new Cuboid(0, 16);
        field_624.method_1818(-2F, 0.0F, -2F, 2, 2, 2, 0.0F);
        field_624.setRotationPoint(-2F, 12F, 0.0F);
        field_625 = new Cuboid(0, 16);
        field_625.mirror = true;
        field_625.method_1818(-2F, 0.0F, -2F, 2, 2, 2, 0.0F);
        field_625.setRotationPoint(2.0F, 12F, 0.0F);
    }

    public void setAngles(float f, float f1, float f2, float f3, float f4, float f5)
    {
        super.setAngles(f, f1, f2, f3, f4, f5);
        float f6 = MathHelper.sin(handSwingProgress * 3.141593F);
        float f7 = MathHelper.sin((1.0F - (1.0F - handSwingProgress) * (1.0F - handSwingProgress)) * 3.141593F);
        field_622.roll = 0.0F;
        field_623.roll = 0.0F;
        field_622.yaw = -(0.1F - f6 * 0.6F);
        field_623.yaw = 0.1F - f6 * 0.6F;
        field_622.pitch = -1.570796F;
        field_623.pitch = -1.570796F;
        field_622.pitch -= f6 * 1.2F - f7 * 0.4F;
        field_623.pitch -= f6 * 1.2F - f7 * 0.4F;
        field_622.roll += MathHelper.cos(f2 * 0.09F) * 0.05F + 0.05F;
        field_623.roll -= MathHelper.cos(f2 * 0.09F) * 0.05F + 0.05F;
        field_622.pitch += MathHelper.sin(f2 * 0.067F) * 0.05F;
        field_623.pitch -= MathHelper.sin(f2 * 0.067F) * 0.05F;
    }
}
