package net.kozibrodka.mocreatures.renderentity;

import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.fabricmc.loader.api.FabricLoader;
import net.kozibrodka.mocreatures.entity.EntityBigCat;
import net.kozibrodka.mocreatures.entity.EntityHorse;
import net.kozibrodka.mocreatures.entity.EntitySheep;
import net.kozibrodka.mocreatures.modelentity.ModelBigCat2;
import net.kozibrodka.mocreatures.modelentity.ModelSheep;
import net.minecraft.client.Minecraft;
import net.minecraft.client.font.TextRenderer;
import net.minecraft.client.render.Tessellator;
import net.minecraft.client.render.entity.LivingEntityRenderer;
import net.minecraft.client.render.entity.model.EntityModel;
import net.minecraft.client.render.entity.model.SheepEntityModel;
import net.minecraft.client.render.entity.model.SheepWoolEntityModel;
import net.minecraft.entity.Entity;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.passive.SheepEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.util.math.MathHelper;
import org.lwjgl.opengl.GL11;

import java.util.Objects;

@Environment(EnvType.CLIENT)
public class RenderSheep extends LivingEntityRenderer {

    public ModelSheep sheepmodel1;
    public static Minecraft mc = Minecraft.class.cast(FabricLoader.getInstance().getGameInstance());

    public RenderSheep(ModelSheep model, SheepWoolEntityModel furModel, float shadowRadius) {
        super(model, shadowRadius);
        this.setDecorationModel(furModel);
        sheepmodel1 = model;
    }

    @Override
    protected void applyScale(LivingEntity entityliving, float f)
    {
        EntitySheep sheep1 = (EntitySheep)entityliving;
        sheepmodel1.tamed = sheep1.getTamed();
        sheepmodel1.eating = sheep1.getIsEating();
    }

    @Override
    protected boolean bindTexture(LivingEntity entityliving, int i, float f) {
        EntitySheep sheepEntity = (EntitySheep)entityliving;
        if (i == 0 && !sheepEntity.isSheared()) {
            this.bindTexture("/mob/sheep_fur.png");
            float var4 = sheepEntity.getBrightnessAtEyes(f);
            int var5 = sheepEntity.getColor();
            GL11.glColor3f(var4 * SheepEntity.COLORS[var5][0], var4 * SheepEntity.COLORS[var5][1], var4 * SheepEntity.COLORS[var5][2]);
            return true;
        } else {
            return false;
        }
    }

    @Override
    public void render(Entity entity, double d, double d1, double d2,
                       float f, float f1)
    {
        doRenderSheep((EntitySheep)entity, d, d1, d2, f, f1);
    }

    public void doRenderSheep(EntitySheep entitysheep, double d, double d1, double d2,
                              float f, float f1)
    {
        super.render(entitysheep, d, d1, d2, f, f1);
        if(entitysheep.roper != null)
        {
            double localRoperY = entitysheep.roper.y;
            double localRoperPrevY = entitysheep.roper.prevY;
            if(!Objects.equals(mc.player.name, ((PlayerEntity) entitysheep.roper).name)){
                localRoperY += 1.62D;
                localRoperPrevY += 1.62D;
            }
            /// rope too low fix ^
            Tessellator tessellator = Tessellator.INSTANCE;
            d1 -= 0.4D; ///niżej linka ogólnie
            float f4 = ((entitysheep.roper.prevYaw + (entitysheep.roper.yaw - entitysheep.roper.prevYaw) * f1 * 0.5F) * 3.141593F) / 180F;
            float f6 = ((entitysheep.roper.prevPitch + (entitysheep.roper.pitch - entitysheep.roper.prevPitch) * f1 * 0.5F) * 3.141593F) / 180F;
            double d3 = MathHelper.sin(f4);
            double d4 = MathHelper.cos(f4);
            double d5 = MathHelper.sin(f6);
            double d6 = MathHelper.cos(f6);
            double d7 = (entitysheep.roper.prevX + (entitysheep.roper.x - entitysheep.roper.prevX) * (double)f1) - d4 * 0.69999999999999996D - d3 * 0.5D * d6;
            double d8 = (localRoperPrevY + (localRoperY - localRoperPrevY) * (double)f1) - d5 * 0.5D;
            double d9 = ((entitysheep.roper.prevZ + (entitysheep.roper.z - entitysheep.roper.prevZ) * (double)f1) - d3 * 0.69999999999999996D) + d4 * 0.5D * d6;
            double d10 = entitysheep.prevX + (entitysheep.x - entitysheep.prevX) * (double)f1;
            double d11 = entitysheep.prevY + (entitysheep.y - entitysheep.prevY) * (double)f1 + 0.25D;
            double d12 = entitysheep.prevZ + (entitysheep.z - entitysheep.prevZ) * (double)f1;
            double d13 = (float)(d7 - d10);
            double d14 = (float)(d8 - d11);
            double d15 = (float)(d9 - d12);
            GL11.glDisable(3553 /*GL_TEXTURE_2D*/);
            GL11.glDisable(2896 /*GL_LIGHTING*/);
            for(double d16 = 0.0D; d16 < 0.029999999999999999D; d16 += 0.01D)
            {
                tessellator.start(3);
                tessellator.color(0.5F, 0.4F, 0.3F, 1.0F);
                int j = 16;
                for(int k = 0; k <= j; k++)
                {
                    float f12 = (float)k / (float)j;
                    tessellator.vertex(d + d13 * (double)f12 + d16, d1 + d14 * (double)(f12 * f12 + f12) * 0.5D + (double)(((float)j - (float)k) / ((float)j * 0.75F) + 0.125F), d2 + d15 * (double)f12);
                }

                tessellator.draw();
            }

            GL11.glEnable(2896 /*GL_LIGHTING*/);
            GL11.glEnable(3553 /*GL_TEXTURE_2D*/);
        }
    }
}
