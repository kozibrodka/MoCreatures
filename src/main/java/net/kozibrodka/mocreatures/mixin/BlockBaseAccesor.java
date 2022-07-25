package net.kozibrodka.mocreatures.mixin;

import net.minecraft.block.BlockBase;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.gen.Accessor;

@Mixin(BlockBase.class)
public interface BlockBaseAccesor {
    @Accessor
    float getHardness();
}
