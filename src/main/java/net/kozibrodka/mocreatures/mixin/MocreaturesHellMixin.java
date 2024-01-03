package net.kozibrodka.mocreatures.mixin;

import net.kozibrodka.mocreatures.entity.EntityFireOgre;
import net.kozibrodka.mocreatures.entity.EntityFlameWraith;
import net.kozibrodka.mocreatures.entity.EntityHellRat;
import net.kozibrodka.mocreatures.events.mod_mocreatures;
import net.minecraft.class_288;
import net.minecraft.class_422;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(class_422.class)
public class MocreaturesHellMixin extends MocreaturesMixin{


    @Inject(method = "<init>", at = @At("TAIL"))
    private void Injected(CallbackInfo ci) {
        mod_mocreatures mocr = new mod_mocreatures();
        this.field_895.add(new class_288(EntityFireOgre.class, mocr.mocreaturesGlass.hostilemobs.fogrefreq));
        this.field_895.add(new class_288(EntityHellRat.class, mocr.mocreaturesGlass.hostilemobs.hellratfreq));
        this.field_895.add(new class_288(EntityFlameWraith.class, mocr.mocreaturesGlass.hostilemobs.fwraithfreq));
    }
}
