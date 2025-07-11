package net.kozibrodka.mocreatures.mixin;

import net.minecraft.entity.Entity;
import net.minecraft.entity.MobEntity;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.gen.Accessor;

@Mixin(MobEntity.class)
public interface WalkingBaseAccesor {
    @Accessor("target")
    Entity getTarget();

    @Accessor("target")
    void setTarget(Entity entity);
}
