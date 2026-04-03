package net.kozibrodka.mocreatures.mixin;

import net.kozibrodka.mocreatures.events.mod_mocreatures;
import net.minecraft.entity.SpawnGroup;
import org.spongepowered.asm.mixin.Mixin;

import org.spongepowered.asm.mixin.injection.Constant;
import org.spongepowered.asm.mixin.injection.ModifyConstant;

@Mixin(SpawnGroup.class)
public class LimitsMixin{

    @ModifyConstant(method = "<clinit>", constant = @Constant(intValue = 70))
    private static int setMonsterSpawnCap(int value) {
        return mod_mocreatures.mocGlass.spawnlimits.maxMobsS;
    }

    @ModifyConstant(method = "<clinit>", constant = @Constant(intValue = 15))
    private static int setCreatureSpawnCap(int value) {
        return mod_mocreatures.mocGlass.spawnlimits.maxAnimalsS;
    }

    @ModifyConstant(method = "<clinit>", constant = @Constant(intValue = 5))
    private static int setWaterCreatureSpawnCap(int value) {
        return mod_mocreatures.mocGlass.spawnlimits.maxWaterMobsS;
    }
}
