package net.kozibrodka.mocreatures.mixin;

import net.kozibrodka.mocreatures.entity.EntityFireOgre;
import net.kozibrodka.mocreatures.entity.EntityFlameWraith;
import net.kozibrodka.mocreatures.entity.EntityHellRat;
import net.kozibrodka.mocreatures.events.mod_mocreatures;
import net.minecraft.entity.EntityEntry;
import net.minecraft.level.biome.Hell;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(Hell.class)
public class MocreaturesHellMixin extends MocreaturesMixin{


    @Inject(method = "<init>", at = @At("TAIL"))
    private void Injected(CallbackInfo ci) {
        mod_mocreatures mocr = new mod_mocreatures();
        this.monsters.add(new EntityEntry(EntityFireOgre.class, mocr.mocreaturesGlass.hostilemobs.fogrefreq));
        this.monsters.add(new EntityEntry(EntityHellRat.class, mocr.mocreaturesGlass.hostilemobs.hellratfreq));
        this.monsters.add(new EntityEntry(EntityFlameWraith.class, mocr.mocreaturesGlass.hostilemobs.fwraithfreq));
    }
}
