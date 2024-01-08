package net.kozibrodka.mocreatures.mixin;


import net.kozibrodka.mocreatures.entity.*;
import net.kozibrodka.mocreatures.events.mod_mocreatures;
import net.minecraft.class_153;
import net.minecraft.class_288;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Constant;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.ModifyConstant;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

import java.util.List;

@Mixin(class_153.class)
public class MocreaturesMixin{

    @Shadow protected List field_894;
    @Shadow protected List field_893;
    @Shadow protected List field_895;


    @Inject(method = "<init>", at = @At("TAIL"))
    private void Injected(CallbackInfo ci) {
        mod_mocreatures mocr = new mod_mocreatures();
//        this.field_894.add(new class_288(EntityBear.class, mocr.mocreaturesGlass.huntercreatures.bearfreq));
        this.field_894.add(new class_288(EntityBigCat.class, mocr.mocreaturesGlass.huntercreatures.lionfreq)); //TODO GUI
//        this.field_894.add(new class_288(EntityBird.class, mocr.mocreaturesGlass.animals.birdfreq));
//        this.field_894.add(new class_288(EntityBoar.class, mocr.mocreaturesGlass.huntercreatures.boarfreq));
//        this.field_894.add(new class_288(EntityBunny.class, mocr.mocreaturesGlass.animals.bunnyfreq));
        this.field_894.add(new class_288(EntityDeer.class, mocr.mocreaturesGlass.animals.deerfreq));
//        this.field_894.add(new class_288(EntityDuck.class, mocr.mocreaturesGlass.animals.duckfreq));
//        this.field_894.add(new class_288(EntityFox.class, mocr.mocreaturesGlass.huntercreatures.foxfreq));
//        this.field_894.add(new class_288(EntityHorse.class, mocr.mocreaturesGlass.animals.horsefreq)); //TODO GUI
//        this.field_894.add(new class_288(EntityKitty.class, mocr.mocreaturesGlass.animals.kittyfreq)); //TODO GUI
//        this.field_894.add(new class_288(EntityMouse.class, mocr.mocreaturesGlass.animals.micefreq));
//        this.field_894.add(new class_288(EntityPolarBear.class, mocr.mocreaturesGlass.huntercreatures.pbearfreq));

//        this.field_895.add(new class_288(EntityShark.class, mocr.mocreaturesGlass.watermobs.sharkfreq));
//        this.field_895.add(new class_288(EntityDolphin.class, mocr.mocreaturesGlass.watermobs.dolphinfreq)); //TODO GUI
//        this.field_895.add(new class_288(EntityFishy.class, mocr.mocreaturesGlass.watermobs.fishfreq));

//        this.field_893.add(new class_288(EntityWWolf.class, mocr.mocreaturesGlass.hostilemobs.wwolffreq));
//        this.field_893.add(new class_288(EntityWraith.class, mocr.mocreaturesGlass.hostilemobs.wraithfreq));
//        this.field_893.add(new class_288(EntityFlameWraith.class, mocr.mocreaturesGlass.hostilemobs.fwraithfreq));
//        this.field_893.add(new class_288(EntityWerewolf.class, mocr.mocreaturesGlass.hostilemobs.werewolffreq));
//        this.field_893.add(new class_288(EntityRat.class, mocr.mocreaturesGlass.hostilemobs.ratfreq));
//        this.field_893.add(new class_288(EntityOgre.class, mocr.mocreaturesGlass.hostilemobs.ogrefreq));
//        this.field_893.add(new class_288(EntityCaveOgre.class, mocr.mocreaturesGlass.hostilemobs.cogrefreq));
//        this.field_893.add(new class_288(EntityFireOgre.class, mocr.mocreaturesGlass.hostilemobs.fogrefreq));

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

