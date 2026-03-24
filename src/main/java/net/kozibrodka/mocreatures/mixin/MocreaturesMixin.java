package net.kozibrodka.mocreatures.mixin;


import net.kozibrodka.mocreatures.entity.*;
import net.kozibrodka.mocreatures.events.mod_mocreatures;
import net.minecraft.world.biome.Biome;
import net.minecraft.world.biome.EntitySpawnGroup;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Constant;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.ModifyConstant;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

import java.util.List;

@Mixin(Biome.class)
public class MocreaturesMixin {

    @Shadow protected List spawnableMonsters;
    @Shadow protected List spawnablePassive;
    @Shadow protected List spawnableWaterCreatures;

    @Inject(method = "<init>", at = @At("TAIL"))
    private void Injected(CallbackInfo ci) {
        mod_mocreatures mocr = new mod_mocreatures();
        this.spawnablePassive.add(new EntitySpawnGroup(EntityBear.class, mocr.mocreaturesGlass.huntercreatures.bearfreq));
//        this.spawnablePassive.add(new EntitySpawnGroup(EntityBigCat.class, mocr.mocreaturesGlass.huntercreatures.lionfreq));
//        this.spawnablePassive.add(new EntitySpawnGroup(EntityBird.class, mocr.mocreaturesGlass.animals.birdfreq));
        this.spawnablePassive.add(new EntitySpawnGroup(EntityBoar.class, mocr.mocreaturesGlass.huntercreatures.boarfreq));
//        this.spawnablePassive.add(new EntitySpawnGroup(EntityBunny.class, mocr.mocreaturesGlass.animals.bunnyfreq));
        this.spawnablePassive.add(new EntitySpawnGroup(EntityDeer.class, mocr.mocreaturesGlass.animals.deerfreq));
        this.spawnablePassive.add(new EntitySpawnGroup(EntityDuck.class, mocr.mocreaturesGlass.animals.duckfreq));
//        this.spawnablePassive.add(new EntitySpawnGroup(EntityFox.class, mocr.mocreaturesGlass.huntercreatures.foxfreq));
//        this.spawnablePassive.add(new EntitySpawnGroup(EntityHorse.class, mocr.mocreaturesGlass.animals.horsefreq));
//        this.spawnablePassive.add(new EntitySpawnGroup(EntityKitty.class, mocr.mocreaturesGlass.animals.kittyfreq));
//        this.spawnablePassive.add(new EntitySpawnGroup(EntityMouse.class, mocr.mocreaturesGlass.animals.micefreq));
        this.spawnablePassive.add(new EntitySpawnGroup(EntityPolarBear.class, mocr.mocreaturesGlass.huntercreatures.pbearfreq));
//
//        this.spawnableWaterCreatures.add(new EntitySpawnGroup(EntityShark.class, mocr.mocreaturesGlass.watermobs.sharkfreq));
//        this.spawnableWaterCreatures.add(new EntitySpawnGroup(EntityDolphin.class, mocr.mocreaturesGlass.watermobs.dolphinfreq));
//        this.spawnableWaterCreatures.add(new EntitySpawnGroup(EntityFishy.class, mocr.mocreaturesGlass.watermobs.fishfreq));
//
//        this.spawnableMonsters.add(new EntitySpawnGroup(EntityWWolf.class, mocr.mocreaturesGlass.hostilemobs.wwolffreq));
//        this.spawnableMonsters.add(new EntitySpawnGroup(EntityWraith.class, mocr.mocreaturesGlass.hostilemobs.wraithfreq));
//        this.spawnableMonsters.add(new EntitySpawnGroup(EntityFlameWraith.class, mocr.mocreaturesGlass.hostilemobs.fwraithfreq));
//        this.spawnableMonsters.add(new EntitySpawnGroup(EntityWerewolf.class, mocr.mocreaturesGlass.hostilemobs.werewolffreq));
//        this.spawnableMonsters.add(new EntitySpawnGroup(EntityRat.class, mocr.mocreaturesGlass.hostilemobs.ratfreq));
//        this.spawnableMonsters.add(new EntitySpawnGroup(EntityOgre.class, mocr.mocreaturesGlass.hostilemobs.ogrefreq));
//        this.spawnableMonsters.add(new EntitySpawnGroup(EntityCaveOgre.class, mocr.mocreaturesGlass.hostilemobs.cogrefreq));
//        this.spawnableMonsters.add(new EntitySpawnGroup(EntityFireOgre.class, mocr.mocreaturesGlass.hostilemobs.fogrefreq));

    }

    @ModifyConstant(method = "<init>", constant = @Constant(intValue = 10, ordinal=7))
    private int Squid(int value) {
        mod_mocreatures mocr = new mod_mocreatures();
        return mocr.mocreaturesGlass.watermobs.squidfreq;
    }

    @ModifyConstant(method = "<init>", constant = @Constant(intValue = 8))
    private int Cow(int value) {
        mod_mocreatures mocr = new mod_mocreatures();
        return mocr.mocreaturesGlass.animals.cowfreq;
    }

    @ModifyConstant(method = "<init>", constant = @Constant(intValue = 10, ordinal=6))
    private int Chicken(int value) {
        mod_mocreatures mocr = new mod_mocreatures();
        return mocr.mocreaturesGlass.animals.chickenfreq;
    }

    @ModifyConstant(method = "<init>", constant = @Constant(intValue = 10, ordinal=5))
    private int Pig(int value) {
        mod_mocreatures mocr = new mod_mocreatures();
        return mocr.mocreaturesGlass.animals.pigfreq;
    }

    @ModifyConstant(method = "<init>", constant = @Constant(intValue = 12))
    private int Sheep(int value) {
        mod_mocreatures mocr = new mod_mocreatures();
        return mocr.mocreaturesGlass.animals.sheepfreq;
    }

}

