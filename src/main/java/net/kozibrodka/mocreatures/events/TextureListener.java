package net.kozibrodka.mocreatures.events;

import net.kozibrodka.mocreatures.entity.*;
import net.kozibrodka.mocreatures.modelentity.*;
import net.kozibrodka.mocreatures.renderentity.*;
import net.mine_diver.unsafeevents.listener.EventListener;
import net.minecraft.client.render.entity.BipedEntityRenderer;
import net.minecraft.client.render.entity.ChickenRenderer;
import net.minecraft.client.render.entity.PigRenderer;
import net.minecraft.client.render.entity.model.Chicken;
import net.minecraft.client.render.entity.model.Pig;
import net.modificationstation.stationapi.api.client.event.render.entity.EntityRendererRegisterEvent;
import net.modificationstation.stationapi.api.client.event.texture.TextureRegisterEvent;
import net.modificationstation.stationapi.api.client.texture.atlas.Atlases;
import net.modificationstation.stationapi.api.mod.entrypoint.Entrypoint;
import net.modificationstation.stationapi.api.registry.Identifier;
import net.modificationstation.stationapi.api.registry.ModID;
import net.modificationstation.stationapi.api.util.Null;

public class TextureListener {

    @Entrypoint.ModID
    public static final ModID MOD_ID = Null.get();

    @EventListener
    public void registerTextures(TextureRegisterEvent event) {
        mod_mocreatures.horsesaddle.setTexture(Identifier.of(MOD_ID, "item/horsesaddle"));
        mod_mocreatures.haystack.setTexture(Identifier.of(MOD_ID, "item/haystack"));
        mod_mocreatures.sugarlump.setTexture(Identifier.of(MOD_ID, "item/sugarlump"));
        mod_mocreatures.sharkteeth.setTexture(Identifier.of(MOD_ID, "item/sharkteeth"));
        mod_mocreatures.sharkegg.setTexture(Identifier.of(MOD_ID, "item/sharkegg"));
        mod_mocreatures.fishyegg.setTexture(Identifier.of(MOD_ID, "item/fishyegg"));
        mod_mocreatures.bigcatclaw.setTexture(Identifier.of(MOD_ID, "item/bigcatclaw"));
        mod_mocreatures.whip.setTexture(Identifier.of(MOD_ID, "item/whip"));
        mod_mocreatures.medallion.setTexture(Identifier.of(MOD_ID, "item/medallion"));
        mod_mocreatures.litterbox.setTexture(Identifier.of(MOD_ID, "item/boxlitter"));
        mod_mocreatures.woolball.setTexture(Identifier.of(MOD_ID, "item/woolball"));
        mod_mocreatures.rope.setTexture(Identifier.of(MOD_ID, "item/rope"));
        mod_mocreatures.petfood.setTexture(Identifier.of(MOD_ID, "item/petfood"));
        mod_mocreatures.kittybed.setTexture(Identifier.of(MOD_ID, "item/bedkitty"));
        if(mod_mocreatures.mocreaturesGlass.balancesettings.balance_drop) {
            mod_mocreatures.sharkoil.setTexture(Identifier.of(MOD_ID, "item/sharkoil"));
            mod_mocreatures.wildleather.setTexture(Identifier.of(MOD_ID, "item/wildleather"));
            mod_mocreatures.polarleather.setTexture(Identifier.of(MOD_ID, "item/polarleather"));
            mod_mocreatures.greenapple.setTexture(Identifier.of(MOD_ID, "item/greenapple"));
            mod_mocreatures.bigcatfood.setTexture(Identifier.of(MOD_ID, "item/bigcatfood"));
            mod_mocreatures.sharkfood.setTexture(Identifier.of(MOD_ID, "item/sharkfood"));
        }

        if(EggListener.degubEgg) {
            EggListener.egg1.setTexture(Identifier.of(MOD_ID, "item/egg/bear"));
            EggListener.egg2.setTexture(Identifier.of(MOD_ID, "item/egg/bigcat"));
            EggListener.egg3.setTexture(Identifier.of(MOD_ID, "item/egg/bird"));
            EggListener.egg4.setTexture(Identifier.of(MOD_ID, "item/egg/boar"));
            EggListener.egg5.setTexture(Identifier.of(MOD_ID, "item/egg/bunny"));
            EggListener.egg6.setTexture(Identifier.of(MOD_ID, "item/egg/caveogre"));
            EggListener.egg7.setTexture(Identifier.of(MOD_ID, "item/egg/deer"));
            EggListener.egg8.setTexture(Identifier.of(MOD_ID, "item/egg/dolphin"));
            EggListener.egg9.setTexture(Identifier.of(MOD_ID, "item/egg/duck"));
            EggListener.egg10.setTexture(Identifier.of(MOD_ID, "item/egg/fireogre"));
            EggListener.egg11.setTexture(Identifier.of(MOD_ID, "item/egg/fishy"));
            EggListener.egg13.setTexture(Identifier.of(MOD_ID, "item/egg/flamewraith"));
            EggListener.egg14.setTexture(Identifier.of(MOD_ID, "item/egg/fox"));
            EggListener.egg15.setTexture(Identifier.of(MOD_ID, "item/egg/hellrat"));
            EggListener.egg16.setTexture(Identifier.of(MOD_ID, "item/egg/horse"));
            EggListener.egg17.setTexture(Identifier.of(MOD_ID, "item/egg/kitty"));
            EggListener.egg20.setTexture(Identifier.of(MOD_ID, "item/egg/mouse"));
            EggListener.egg21.setTexture(Identifier.of(MOD_ID, "item/egg/ogre"));
            EggListener.egg22.setTexture(Identifier.of(MOD_ID, "item/egg/polarbear"));
            EggListener.egg23.setTexture(Identifier.of(MOD_ID, "item/egg/rat"));
            EggListener.egg24.setTexture(Identifier.of(MOD_ID, "item/egg/shark"));
            EggListener.egg26.setTexture(Identifier.of(MOD_ID, "item/egg/werewolf"));
            EggListener.egg27.setTexture(Identifier.of(MOD_ID, "item/egg/wildwolf"));
            EggListener.egg28.setTexture(Identifier.of(MOD_ID, "item/egg/wraith"));
        }
    }

    @EventListener
    private static void registerEntityRenderers(EntityRendererRegisterEvent event) {
        event.renderers.put(EntityHorse.class, new RenderHorse(new ModelHorse2(), new ModelHorse1()));
        event.renderers.put(EntityFireOgre.class, new RenderFireOgre(new ModelOgre2(), new ModelOgre1(), 1.5F));
        event.renderers.put(EntityCaveOgre.class, new RenderCaveOgre(new ModelOgre2(), new ModelOgre1(), 1.5F));
        event.renderers.put(EntityOgre.class, new RenderOgre(new ModelOgre2(), new ModelOgre1(), 1.5F));
        event.renderers.put(EntityBoar.class, new PigRenderer(new Pig(), new Pig(0.5F), 0.7F));
        event.renderers.put(EntityBear.class, new RenderBear(new ModelBear2(), new ModelBear1(), 0.7F));
        event.renderers.put(EntityDuck.class, new ChickenRenderer(new Chicken(), 0.3F));
        event.renderers.put(EntityBigCat.class, new RenderBigCat(new ModelBigCat2(), new ModelBigCat1(), 0.7F));
        event.renderers.put(EntityDeer.class, new RenderDeer(new ModelDeer(), 0.5F));
        event.renderers.put(EntityWWolf.class, new RenderWWolf(new ModelWolf2(), new ModelWolf1(), 0.7F));
        event.renderers.put(EntityPolarBear.class, new RenderPolarBear(new ModelBear2(), new ModelBear1(), 0.7F));
        event.renderers.put(EntityWraith.class, new BipedEntityRenderer(new ModelWraith(), 0.5F));
        event.renderers.put(EntityFlameWraith.class, new BipedEntityRenderer(new ModelWraith(), 0.5F));
        event.renderers.put(EntityBunny.class, new RenderBunny(new ModelBunny(), 0.3F));
        event.renderers.put(EntityBird.class, new RenderBird(new ModelBird(), 0.3F));
        event.renderers.put(EntityFox.class, new RenderFox(new ModelFox()));
        event.renderers.put(EntityWerewolf.class, new RenderWerewolf(new ModelWereHuman(), new ModelWerewolf(), 0.7F));
        event.renderers.put(EntityShark.class, new RenderShark(new ModelShark(), 0.6F));
        event.renderers.put(EntitySharkEgg.class, new RenderSharkEgg(new ModelSharkEgg(), 0.1F));
        event.renderers.put(EntityDolphin.class, new RenderDolphin(new ModelDolphin(), 0.6F));
        event.renderers.put(EntityFishy.class, new RenderFishy(new ModelFishy(), 0.2F));
        event.renderers.put(EntityFishyEgg.class, new RenderFishyEgg(new ModelSharkEgg(), 0.1F));
        event.renderers.put(EntityKitty.class, new RenderKitty(new ModelKitty(0.0F, 15F), 0.4F));
        event.renderers.put(EntityKittyBed.class, new RenderKittyBed(new ModelKittyBed(), new ModelKittyBed2(), 0.3F));
        event.renderers.put(EntityLitterBox.class, new RenderLitterBox(new ModelLitterBox(), 0.3F));
        event.renderers.put(EntityRat.class, new RenderRat(new ModelRat(), 0.2F));
        event.renderers.put(EntityMouse.class, new RenderMouse(new ModelMouse(), 0.1F));
        event.renderers.put(EntityHellRat.class, new RenderHellRat(new ModelRat(), 0.4F));
    }
}
