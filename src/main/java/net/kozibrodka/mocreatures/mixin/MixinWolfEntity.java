package net.kozibrodka.mocreatures.mixin;

import net.kozibrodka.mocreatures.events.mod_mocreatures;
import net.kozibrodka.mocreatures.mocreatures.MoCTools;
import net.minecraft.entity.passive.AnimalEntity;
import net.minecraft.entity.passive.WolfEntity;
import net.minecraft.world.World;
import org.spongepowered.asm.mixin.Mixin;


@Mixin(WolfEntity.class)
public class MixinWolfEntity extends AnimalEntity {

    public MixinWolfEntity(World world) {
        super(world);
    }

    @Override
    public boolean canSpawn()
    {
        return !MoCTools.isNearTorch(this) && super.canSpawn(); /// Wolf to też HunterCreature - atakuje owce.
    }
}
