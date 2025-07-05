package net.kozibrodka.mocreatures.mixin;

import net.kozibrodka.mocreatures.entity.EntityFireOgre;
import net.kozibrodka.mocreatures.entity.EntityFlameWraith;
import net.kozibrodka.mocreatures.entity.EntityHellRat;
import net.kozibrodka.mocreatures.events.mod_mocreatures;
import net.minecraft.world.biome.EntitySpawnGroup;
import net.minecraft.world.biome.HellBiome;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(HellBiome.class)
public class MocreaturesHellMixin extends MocreaturesMixin{

    @Inject(method = "<init>", at = @At("TAIL"))
    private void Injected(CallbackInfo ci) {
        mod_mocreatures mocr = new mod_mocreatures();
        this.spawnableWaterCreatures.add(new EntitySpawnGroup(EntityFireOgre.class, mocr.mocreaturesGlass.hostilemobs.fogrefreq));
        this.spawnableWaterCreatures.add(new EntitySpawnGroup(EntityHellRat.class, mocr.mocreaturesGlass.hostilemobs.hellratfreq));
        this.spawnableWaterCreatures.add(new EntitySpawnGroup(EntityFlameWraith.class, mocr.mocreaturesGlass.hostilemobs.fwraithfreq));
    }
}
