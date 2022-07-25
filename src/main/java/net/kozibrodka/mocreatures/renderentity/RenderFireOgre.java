package net.kozibrodka.mocreatures.renderentity;

import net.kozibrodka.mocreatures.entity.EntityOgre;
import net.kozibrodka.mocreatures.modelentity.ModelOgre2;
import net.minecraft.client.render.entity.model.EntityModelBase;

public class RenderFireOgre extends RenderOgre
{

    public RenderFireOgre(ModelOgre2 modelogre2, EntityModelBase modelbase, float f)
    {
        super(modelogre2, modelbase, f);
        setModel(modelbase);
        tempOgre = modelogre2;
    }

    protected boolean a(EntityOgre entityogre, int i)
    {
        bindTexture("/assets/mocreatures/stationapi/textures/mob/fireogreb.png");
        return i == 0 && !entityogre.ogreboolean;
    }

    private ModelOgre2 tempOgre;
}
