package net.kozibrodka.mocreatures.mixin;

import net.kozibrodka.mocreatures.events.mod_mocreatures;
import net.minecraft.class_238;
import org.spongepowered.asm.mixin.Mixin;

import org.spongepowered.asm.mixin.injection.Constant;
import org.spongepowered.asm.mixin.injection.ModifyConstant;


@Mixin(class_238.class)
public class LimitsMixin{



    @ModifyConstant(method = "<clinit>", constant = @Constant(intValue = 70))
        private static int field_942(int value) {
        mod_mocreatures mocr = new mod_mocreatures();
        return mocr.mocreaturesGlass.spawnlimits.maxMobsS;
            }

    @ModifyConstant(method = "<clinit>", constant = @Constant(intValue = 15))
        private static int field_943(int value) {
        mod_mocreatures mocr = new mod_mocreatures();
        return mocr.mocreaturesGlass.spawnlimits.maxAnimalsS;
    }

    @ModifyConstant(method = "<clinit>", constant = @Constant(intValue = 5))
        private static int field_944(int value) {
        mod_mocreatures mocr = new mod_mocreatures();
        return mocr.mocreaturesGlass.spawnlimits.maxWaterMobsS;
    }


}



