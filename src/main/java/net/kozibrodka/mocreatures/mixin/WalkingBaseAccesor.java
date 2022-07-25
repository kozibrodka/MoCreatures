package net.kozibrodka.mocreatures.mixin;

import net.minecraft.entity.EntityBase;
import net.minecraft.entity.WalkingBase;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.gen.Accessor;

@Mixin(WalkingBase.class)
public interface WalkingBaseAccesor {
    @Accessor
    EntityBase getEntity();

    @Accessor
    void setEntity(EntityBase entity);
}
