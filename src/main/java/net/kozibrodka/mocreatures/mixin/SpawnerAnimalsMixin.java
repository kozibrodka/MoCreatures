package net.kozibrodka.mocreatures.mixin;

import net.kozibrodka.mocreatures.entity.EntityDeer;
import net.kozibrodka.mocreatures.mocreatures.MoCreatureRacial;
import net.minecraft.class_567;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.passive.SheepEntity;
import net.minecraft.world.World;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(class_567.class)
public class SpawnerAnimalsMixin {

    @Inject(method = "method_1872", at = @At(value = "TAIL"), cancellable = true)
    private static void Injected(LivingEntity arg, World arg2, float f, float g, float h, CallbackInfo ci){
        if (arg instanceof MoCreatureRacial) {
//            System.out.println("SPAWNIE MOCR RACIAL");
            ((MoCreatureRacial)arg).setTypeSpawn();
//            ((SheepEntity)arg).method_2046(SheepEntity.method_2044(arg2.field_214));
        }
    }
}
