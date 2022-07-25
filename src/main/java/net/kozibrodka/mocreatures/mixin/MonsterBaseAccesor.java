package net.kozibrodka.mocreatures.mixin;

import net.minecraft.entity.monster.MonsterBase;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.gen.Accessor;

@Mixin(MonsterBase.class)
public interface MonsterBaseAccesor {
    @Accessor
    int getAttackDamage();
}
