package net.kozibrodka.mocreatures.mixin;

import net.minecraft.entity.mob.MonsterEntity;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.gen.Accessor;

@Mixin(MonsterEntity.class)
public interface MonsterBaseAccesor {
    @Accessor
    int getField_547();
}
