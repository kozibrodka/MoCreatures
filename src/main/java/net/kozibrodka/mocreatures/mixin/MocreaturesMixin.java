package net.kozibrodka.mocreatures.mixin;


import net.kozibrodka.mocreatures.events.mod_mocreatures;
import net.minecraft.world.biome.Biome;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.*;

import java.util.List;

@Mixin(Biome.class)
public class MocreaturesMixin {


    @Shadow protected List spawnablePassive;

    @ModifyConstant(method = "<init>", constant = @Constant(intValue = 10, ordinal=7))
    private int Squid(int value) {
        return mod_mocreatures.mocGlass.watermobs.squidfreq;
    }

    @ModifyConstant(method = "<init>", constant = @Constant(intValue = 8))
    private int Cow(int value) {
        return mod_mocreatures.mocGlass.animals.cowfreq;
    }

    @ModifyConstant(method = "<init>", constant = @Constant(intValue = 10, ordinal=6))
    private int Chicken(int value) {
        return mod_mocreatures.mocGlass.animals.chickenfreq;
    }

    @ModifyConstant(method = "<init>", constant = @Constant(intValue = 10, ordinal=5))
    private int Pig(int value) {
        return mod_mocreatures.mocGlass.animals.pigfreq;
    }

    @ModifyConstant(method = "<init>", constant = @Constant(intValue = 12))
    private int Sheep(int value) {
        if(mod_mocreatures.mocGlass.spawnlimits.sheep_farming){
            return 0;
        }else{
            return mod_mocreatures.mocGlass.animals.sheepfreq;
        }
    }

}

