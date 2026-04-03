package net.kozibrodka.mocreatures.mixin;

import com.llamalad7.mixinextras.injector.wrapoperation.WrapOperation;
import net.minecraft.block.Block;
import net.minecraft.block.SpongeBlock;
import net.minecraft.block.material.Material;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import net.minecraft.world.World;
import com.llamalad7.mixinextras.injector.wrapoperation.Operation;


@Mixin(SpongeBlock.class)
public class SpongeMixin extends Block {
    public SpongeMixin(int i) {
        super(i, Material.SPONGE);
    }
/// CONFLICTS to be tested.
    @WrapOperation(method = "onPlaced", at = @At(value = "INVOKE", target = "Lnet/minecraft/world/World;getMaterial(III)Lnet/minecraft/block/material/Material;"))
    public Material wrapOp1(World instance, int x, int y, int z, Operation<Material> original) {
            if (instance.getMaterial(x, y, z) == Material.WATER) {
                instance.setBlock(x, y, z, 0);
            }
        return original.call(instance, x, y, z);
    }
}
