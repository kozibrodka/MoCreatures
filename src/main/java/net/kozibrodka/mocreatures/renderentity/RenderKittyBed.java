package net.kozibrodka.mocreatures.renderentity;
import net.kozibrodka.mocreatures.entity.EntityKittyBed;
import net.kozibrodka.mocreatures.modelentity.ModelKittyBed;
import net.kozibrodka.mocreatures.modelentity.ModelKittyBed2;
import net.kozibrodka.mocreatures.events.mod_mocreatures;
import net.minecraft.client.render.entity.LivingEntityRenderer;
import net.minecraft.entity.LivingEntity;
import org.lwjgl.opengl.GL11;


public class RenderKittyBed extends LivingEntityRenderer
{

    public RenderKittyBed(ModelKittyBed modelkittybed, ModelKittyBed2 modelkittybed2, float f)
    {
        super(modelkittybed, f);
        kittybed = modelkittybed;
        method_815(modelkittybed2);
    }

    protected void method_823(LivingEntity entityliving, float f)
    {
        EntityKittyBed entitykittybed = (EntityKittyBed)entityliving;
        mycolor = entitykittybed.sheetcolour;
        kittybed.hasMilk = entitykittybed.hasMilk;
        kittybed.hasFood = entitykittybed.hasFood;
        kittybed.pickedUp = entitykittybed.pickedUp;
        kittybed.milklevel = entitykittybed.milklevel;
    }

    protected boolean method_825(LivingEntity entityliving, int i, float f)
    {
        return setWoolColorAndRender(entityliving, i, f);
    }

    protected boolean setWoolColorAndRender(LivingEntity entityliving, int i, float f)
    {
        bindTexture("/assets/mocreatures/stationapi/textures/mob/kittybed.png");
        float f1 = 0.35F;
        int j = mod_mocreatures.colorize(mycolor);
        GL11.glColor3f(f1 * fleeceColorTable[j][0], f1 * fleeceColorTable[j][1], f1 * fleeceColorTable[j][2]);
        return true;
    }

    public ModelKittyBed kittybed;
    private int mycolor;
    public static final float fleeceColorTable[][] = {
        {
            1.0F, 1.0F, 1.0F
        }, {
            0.95F, 0.7F, 0.2F
        }, {
            0.9F, 0.5F, 0.85F
        }, {
            0.6F, 0.7F, 0.95F
        }, {
            0.9F, 0.9F, 0.2F
        }, {
            0.5F, 0.8F, 0.1F
        }, {
            0.95F, 0.7F, 0.8F
        }, {
            0.3F, 0.3F, 0.3F
        }, {
            0.6F, 0.6F, 0.6F
        }, {
            0.3F, 0.6F, 0.7F
        }, {
            0.7F, 0.4F, 0.9F
        }, {
            0.2F, 0.4F, 0.8F
        }, {
            0.5F, 0.4F, 0.3F
        }, {
            0.4F, 0.5F, 0.2F
        }, {
            0.8F, 0.3F, 0.3F
        }, {
            0.1F, 0.1F, 0.1F
        }
    };

}
