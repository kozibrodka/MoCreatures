package net.kozibrodka.mocreatures.mixin;


import net.kozibrodka.mocreatures.entity.*;
import net.kozibrodka.mocreatures.events.mod_mocreatures;
import net.minecraft.entity.EntityEntry;
import net.minecraft.level.biome.Biome;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Constant;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.ModifyConstant;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

import java.util.List;

@Mixin(Biome.class)
public class MocreaturesMixin{

    @Shadow protected List creatures;
    @Shadow protected List monsters;
    @Shadow protected List waterCreatures;


    @Inject(method = "<init>", at = @At("TAIL"))
    private void Injected(CallbackInfo ci) {
        mod_mocreatures mocr = new mod_mocreatures();
        this.creatures.add(new EntityEntry(EntityBear.class, mocr.mocreaturesGlass.huntercreatures.bearfreq));
//        this.creatures.add(new EntityEntry(EntityBigCat.class, mocr.mocreaturesGlass.huntercreatures.lionfreq)); //TODO GUI
        this.creatures.add(new EntityEntry(EntityBird.class, mocr.mocreaturesGlass.animals.birdfreq));
        this.creatures.add(new EntityEntry(EntityBoar.class, mocr.mocreaturesGlass.huntercreatures.boarfreq));
        this.creatures.add(new EntityEntry(EntityBunny.class, mocr.mocreaturesGlass.animals.bunnyfreq));
        this.creatures.add(new EntityEntry(EntityDeer.class, mocr.mocreaturesGlass.animals.deerfreq));
        this.creatures.add(new EntityEntry(EntityDuck.class, mocr.mocreaturesGlass.animals.duckfreq));
        this.creatures.add(new EntityEntry(EntityFox.class, mocr.mocreaturesGlass.huntercreatures.foxfreq));
//        this.creatures.add(new EntityEntry(EntityHorse.class, mocr.mocreaturesGlass.animals.horsefreq)); //TODO GUI
//        this.creatures.add(new EntityEntry(EntityKitty.class, mocr.mocreaturesGlass.animals.kittyfreq)); //TODO GUI
        this.creatures.add(new EntityEntry(EntityMouse.class, mocr.mocreaturesGlass.animals.micefreq));
        this.creatures.add(new EntityEntry(EntityPolarBear.class, mocr.mocreaturesGlass.huntercreatures.pbearfreq));

        this.waterCreatures.add(new EntityEntry(EntityShark.class, mocr.mocreaturesGlass.watermobs.sharkfreq));
//        this.waterCreatures.add(new EntityEntry(EntityDolphin.class, mocr.mocreaturesGlass.watermobs.dolphinfreq)); //TODO GUI
        this.waterCreatures.add(new EntityEntry(EntityFishy.class, mocr.mocreaturesGlass.watermobs.fishfreq));

        this.monsters.add(new EntityEntry(EntityWWolf.class, mocr.mocreaturesGlass.hostilemobs.wwolffreq));
        this.monsters.add(new EntityEntry(EntityWraith.class, mocr.mocreaturesGlass.hostilemobs.wraithfreq));
        this.monsters.add(new EntityEntry(EntityFlameWraith.class, mocr.mocreaturesGlass.hostilemobs.fwraithfreq));
        this.monsters.add(new EntityEntry(EntityWerewolf.class, mocr.mocreaturesGlass.hostilemobs.werewolffreq));
        this.monsters.add(new EntityEntry(EntityRat.class, mocr.mocreaturesGlass.hostilemobs.ratfreq));
        this.monsters.add(new EntityEntry(EntityOgre.class, mocr.mocreaturesGlass.hostilemobs.ogrefreq));
        this.monsters.add(new EntityEntry(EntityCaveOgre.class, mocr.mocreaturesGlass.hostilemobs.cogrefreq));
        this.monsters.add(new EntityEntry(EntityFireOgre.class, mocr.mocreaturesGlass.hostilemobs.fogrefreq));

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

