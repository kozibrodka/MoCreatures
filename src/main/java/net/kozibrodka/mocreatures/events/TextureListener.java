package net.kozibrodka.mocreatures.events;

import net.mine_diver.unsafeevents.listener.EventListener;
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
            EggListener.egg1.setTexture(Identifier.of(MOD_ID, "egg/bear"));
            EggListener.egg2.setTexture(Identifier.of(MOD_ID, "egg/bigcat"));
            EggListener.egg3.setTexture(Identifier.of(MOD_ID, "egg/bird"));
            EggListener.egg4.setTexture(Identifier.of(MOD_ID, "egg/boar"));
            EggListener.egg5.setTexture(Identifier.of(MOD_ID, "egg/bunny"));
            EggListener.egg6.setTexture(Identifier.of(MOD_ID, "egg/caveogre"));
            EggListener.egg7.setTexture(Identifier.of(MOD_ID, "egg/deer"));
            EggListener.egg8.setTexture(Identifier.of(MOD_ID, "egg/dolphin"));
            EggListener.egg9.setTexture(Identifier.of(MOD_ID, "egg/duck"));
            EggListener.egg10.setTexture(Identifier.of(MOD_ID, "egg/fireogre"));
            EggListener.egg11.setTexture(Identifier.of(MOD_ID, "egg/fishy"));
            EggListener.egg13.setTexture(Identifier.of(MOD_ID, "egg/flamewraith"));
            EggListener.egg14.setTexture(Identifier.of(MOD_ID, "egg/fox"));
            EggListener.egg15.setTexture(Identifier.of(MOD_ID, "egg/hellrat"));
            EggListener.egg16.setTexture(Identifier.of(MOD_ID, "egg/horse"));
            EggListener.egg17.setTexture(Identifier.of(MOD_ID, "egg/kitty"));
            EggListener.egg20.setTexture(Identifier.of(MOD_ID, "egg/mouse"));
            EggListener.egg21.setTexture(Identifier.of(MOD_ID, "egg/ogre"));
            EggListener.egg22.setTexture(Identifier.of(MOD_ID, "egg/polarbear"));
            EggListener.egg23.setTexture(Identifier.of(MOD_ID, "egg/rat"));
            EggListener.egg24.setTexture(Identifier.of(MOD_ID, "egg/shark"));
            EggListener.egg26.setTexture(Identifier.of(MOD_ID, "egg/werewolf"));
            EggListener.egg27.setTexture(Identifier.of(MOD_ID, "egg/wildwolf"));
            EggListener.egg28.setTexture(Identifier.of(MOD_ID, "egg/wraith"));
        }
    }
}
