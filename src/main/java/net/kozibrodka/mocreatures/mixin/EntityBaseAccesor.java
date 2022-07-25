package net.kozibrodka.mocreatures.mixin;

import net.minecraft.entity.EntityBase;
import net.minecraft.level.Level;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.gen.Accessor;

@Mixin(EntityBase.class)
public interface EntityBaseAccesor {
    @Accessor
    boolean getField_1612();

    @Accessor
    void setFallDistance(float fallDistance);

}
