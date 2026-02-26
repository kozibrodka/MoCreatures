package net.kozibrodka.mocreatures.mixin;

import net.minecraft.entity.Entity;

import net.minecraft.entity.LivingEntity;

import net.minecraft.entity.player.PlayerEntity;
import org.objectweb.asm.Opcodes;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Redirect;

@Mixin(Entity.class)
public class MixinEntityBase {

    @Redirect(method = "move", at = @At(value = "FIELD", target = "Lnet/minecraft/entity/Entity;x:D", opcode = Opcodes.PUTFIELD))
    private void fixX(Entity entityBase, double value) {
        if (!entityBase.world.isRemote || entityBase instanceof PlayerEntity || !(entityBase instanceof LivingEntity))
            entityBase.x = value;
    }

    @Redirect(method = "move", at = @At(value = "FIELD", target = "Lnet/minecraft/entity/Entity;y:D", opcode = Opcodes.PUTFIELD))
    private void fixY(Entity entityBase, double value) {
        if (!entityBase.world.isRemote || entityBase instanceof PlayerEntity || !(entityBase instanceof LivingEntity))
            entityBase.y = value;
    }

    @Redirect(method = "move", at = @At(value = "FIELD", target = "Lnet/minecraft/entity/Entity;z:D", opcode = Opcodes.PUTFIELD))
    private void fixZ(Entity entityBase, double value) {
        if (!entityBase.world.isRemote || entityBase instanceof PlayerEntity || !(entityBase instanceof LivingEntity))
            entityBase.z = value;
    }
}
