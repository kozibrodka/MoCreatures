package net.kozibrodka.mocreatures.mixin;

import net.minecraft.entity.Entity;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.gen.Accessor;

@Mixin(Entity.class)
public interface EntityBaseAccesor {
    @Accessor("submergedInWater")
    boolean getSubmergedInWater();

    @Accessor
    void setFallDistance(float fallDistance);
}
