package net.kozibrodka.mocreatures.mixin;

import net.minecraft.entity.mob.CreeperEntity;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.gen.Accessor;

@Mixin(CreeperEntity.class)
public interface CreeperAccesor {
    @Accessor
    void setField_350(int currentFuseTime);
}
