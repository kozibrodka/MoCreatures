package net.kozibrodka.mocreatures.mixin;

import net.minecraft.entity.monster.Creeper;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.gen.Accessor;

@Mixin(Creeper.class)
public interface CreeperAccesor {
    @Accessor
    void setCurrentFuseTime(int currentFuseTime);
}
