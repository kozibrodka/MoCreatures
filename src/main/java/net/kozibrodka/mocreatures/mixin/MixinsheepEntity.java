package net.kozibrodka.mocreatures.mixin;

import net.kozibrodka.mocreatures.events.mod_mocreatures;
import net.kozibrodka.mocreatures.mocreatures.MoCTools;
import net.minecraft.entity.passive.AnimalEntity;
import net.minecraft.entity.passive.SheepEntity;
import net.minecraft.world.World;
import org.spongepowered.asm.mixin.Mixin;

@Mixin(SheepEntity.class)
public class MixinsheepEntity extends AnimalEntity {

    public MixinsheepEntity(World world) {
        super(world);
    }
    /// na 99% niepotrzebne

//    @Override
//    public boolean canSpawn()
//    {
//        return !mod_mocreatures.mocreaturesGlass.spawnlimits.sheep_farming && super.canSpawn();
//    }
}
