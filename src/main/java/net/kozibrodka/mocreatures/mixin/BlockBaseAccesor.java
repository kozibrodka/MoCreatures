package net.kozibrodka.mocreatures.mixin;

import net.minecraft.block.Block;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.gen.Accessor;

@Mixin(Block.class)
public interface BlockBaseAccesor {
    @Accessor("hardness")
    float getHardness();
}
