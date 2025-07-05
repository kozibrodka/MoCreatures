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
//        this.spawnablePassive.add(new class_288(EntityBear.class, mocr.mocreaturesGlass.huntercreatures.bearfreq));
//        this.spawnablePassive.add(new class_288(EntityBigCat.class, mocr.mocreaturesGlass.huntercreatures.lionfreq)); //TODO GUI
//        this.spawnablePassive.add(new class_288(EntityBird.class, mocr.mocreaturesGlass.animals.birdfreq));
//        this.spawnablePassive.add(new class_288(EntityBoar.class, mocr.mocreaturesGlass.huntercreatures.boarfreq));
//        this.spawnablePassive.add(new class_288(EntityBunny.class, mocr.mocreaturesGlass.animals.bunnyfreq));
        this.spawnablePassive.add(new EntitySpawnGroup(EntityDeer.class, mocr.mocreaturesGlass.animals.deerfreq));
//        this.spawnablePassive.add(new class_288(EntityDuck.class, mocr.mocreaturesGlass.animals.duckfreq));
//        this.spawnablePassive.add(new class_288(EntityFox.class, mocr.mocreaturesGlass.huntercreatures.foxfreq));
//        this.spawnablePassive.add(new class_288(EntityHorse.class, mocr.mocreaturesGlass.animals.horsefreq)); //TODO GUI
//        this.spawnablePassive.add(new class_288(EntityKitty.class, mocr.mocreaturesGlass.animals.kittyfreq)); //TODO GUI
//        this.spawnablePassive.add(new class_288(EntityMouse.class, mocr.mocreaturesGlass.animals.micefreq));
//        this.spawnablePassive.add(new class_288(EntityPolarBear.class, mocr.mocreaturesGlass.huntercreatures.pbearfreq));

//        this.spawnableWaterCreatures.add(new class_288(EntityShark.class, mocr.mocreaturesGlass.watermobs.sharkfreq));
//        this.spawnableWaterCreatures.add(new class_288(EntityDolphin.class, mocr.mocreaturesGlass.watermobs.dolphinfreq)); //TODO GUI
//        this.spawnableWaterCreatures.add(new class_288(EntityFishy.class, mocr.mocreaturesGlass.watermobs.fishfreq));

//        this.spawnableMonsters.add(new class_288(EntityWWolf.class, mocr.mocreaturesGlass.hostilemobs.wwolffreq));
//        this.spawnableMonsters.add(new class_288(EntityWraith.class, mocr.mocreaturesGlass.hostilemobs.wraithfreq));
//        this.spawnableMonsters.add(new class_288(EntityFlameWraith.class, mocr.mocreaturesGlass.hostilemobs.fwraithfreq));
//        this.spawnableMonsters.add(new class_288(EntityWerewolf.class, mocr.mocreaturesGlass.hostilemobs.werewolffreq));
//        this.spawnableMonsters.add(new class_288(EntityRat.class, mocr.mocreaturesGlass.hostilemobs.ratfreq));
//        this.spawnableMonsters.add(new class_288(EntityOgre.class, mocr.mocreaturesGlass.hostilemobs.ogrefreq));
//        this.spawnableMonsters.add(new class_288(EntityCaveOgre.class, mocr.mocreaturesGlass.hostilemobs.cogrefreq));
//        this.spawnableMonsters.add(new class_288(EntityFireOgre.class, mocr.mocreaturesGlass.hostilemobs.fogrefreq));

    }

    @ModifyConstant(method = "<init>", constant = @Constant(intValue = 10, ordinal=7))
    private static int Squid(int value) {
        mod_mocreatures mocr = new mod_mocreatures();
        return mocr.mocreaturesGlass.watermobs.squidfreq;
    }

    @ModifyConstant(method = "<init>", constant = @Constant(intValue = 8))
    private static int Cow(int value) {
        mod_mocreatures mocr = new mod_mocreatures();
        return mocr.mocreaturesGlass.animals.cowfreq;
    }

    @ModifyConstant(method = "<init>", constant = @Constant(intValue = 10, ordinal=6))
    private static int Chicken(int value) {
        mod_mocreatures mocr = new mod_mocreatures();
        return mocr.mocreaturesGlass.animals.chickenfreq;
    }

    @ModifyConstant(method = "<init>", constant = @Constant(intValue = 10, ordinal=5))
    private static int Pig(int value) {
        mod_mocreatures mocr = new mod_mocreatures();
        return mocr.mocreaturesGlass.animals.pigfreq;
    }

    @ModifyConstant(method = "<init>", constant = @Constant(intValue = 12))
    private static int Sheep(int value) {
        mod_mocreatures mocr = new mod_mocreatures();
        return mocr.mocreaturesGlass.animals.sheepfreq;
    }

}

