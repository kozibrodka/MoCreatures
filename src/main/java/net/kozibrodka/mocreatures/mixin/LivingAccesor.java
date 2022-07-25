package net.kozibrodka.mocreatures.mixin;

import net.minecraft.entity.Living;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.gen.Accessor;

@Mixin(Living.class)
public interface LivingAccesor {
    @Accessor
    boolean getJumping();
}
