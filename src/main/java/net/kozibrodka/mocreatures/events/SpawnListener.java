package net.kozibrodka.mocreatures.events;

import net.kozibrodka.mocreatures.entity.*;
import net.mine_diver.unsafeevents.listener.EventListener;
import net.minecraft.world.biome.Biome;
import net.minecraft.world.dimension.NetherDimension;
import net.minecraft.world.dimension.OverworldDimension;
import net.modificationstation.stationapi.api.event.worldgen.biome.BiomeModificationEvent;
import net.modificationstation.stationapi.api.mod.entrypoint.Entrypoint;
import net.modificationstation.stationapi.api.util.Namespace;
import net.modificationstation.stationapi.api.util.Null;

public class SpawnListener {
    @Entrypoint.Namespace
    public static Namespace MOD_ID = Null.get();

    @EventListener
    public void registerEntitySpawn(BiomeModificationEvent event) {

        if (event.world.dimension instanceof OverworldDimension) {
            /// Wszędzie:
            event.biome.addPassiveEntity(EntityHorse.class, mod_mocreatures.mocGlass.animals.horsefreq);
            event.biome.addPassiveEntity(EntityBird.class, mod_mocreatures.mocGlass.animals.birdfreq);
            event.biome.addPassiveEntity(EntityBunny.class, mod_mocreatures.mocGlass.animals.bunnyfreq);
            event.biome.addPassiveEntity(EntityDuck.class, mod_mocreatures.mocGlass.animals.duckfreq);
            event.biome.addPassiveEntity(EntityDeer.class, mod_mocreatures.mocGlass.animals.deerfreq);
            event.biome.addPassiveEntity(EntityKitty.class, mod_mocreatures.mocGlass.animals.kittyfreq);
            event.biome.addPassiveEntity(EntityMouse.class, mod_mocreatures.mocGlass.animals.micefreq);

            event.biome.addPassiveEntity(EntityBear.class, mod_mocreatures.mocGlass.huntercreatures.bearfreq);
            event.biome.addPassiveEntity(EntityBigCat.class, mod_mocreatures.mocGlass.huntercreatures.lionfreq);
            event.biome.addPassiveEntity(EntityBoar.class, mod_mocreatures.mocGlass.huntercreatures.bearfreq);
            event.biome.addPassiveEntity(EntityFox.class, mod_mocreatures.mocGlass.huntercreatures.foxfreq);

            event.biome.addWaterEntity(EntityDolphin.class, mod_mocreatures.mocGlass.watermobs.dolphinfreq);
            event.biome.addWaterEntity(EntityShark.class, mod_mocreatures.mocGlass.watermobs.sharkfreq);
            event.biome.addWaterEntity(EntityFishy.class, mod_mocreatures.mocGlass.watermobs.fishfreq);

            event.biome.addHostileEntity(EntityOgre.class, mod_mocreatures.mocGlass.hostilemobs.ogrefreq);
            event.biome.addHostileEntity(EntityFireOgre.class, mod_mocreatures.mocGlass.hostilemobs.fogrefreq);
            event.biome.addHostileEntity(EntityCaveOgre.class, mod_mocreatures.mocGlass.hostilemobs.cogrefreq);
            event.biome.addHostileEntity(EntityWerewolf.class, mod_mocreatures.mocGlass.hostilemobs.werewolffreq);
            event.biome.addHostileEntity(EntityWraith.class, mod_mocreatures.mocGlass.hostilemobs.wraithfreq);
            event.biome.addHostileEntity(EntityFlameWraith.class, mod_mocreatures.mocGlass.hostilemobs.fwraithfreq);
            event.biome.addHostileEntity(EntityWWolf.class, mod_mocreatures.mocGlass.hostilemobs.wwolffreq);
            event.biome.addHostileEntity(EntityRat.class, mod_mocreatures.mocGlass.hostilemobs.ratfreq);

                if(mod_mocreatures.mocGlass.spawnlimits.extra_mobs) {
                    event.biome.addWaterEntity(EntityRay.class, mod_mocreatures.mocGlass.watermobs.rayfreq);
                    event.biome.addHostileEntity(EntityScorpion.class, mod_mocreatures.mocGlass.hostilemobs.scorpionfreq);
                }
                if(mod_mocreatures.mocGlass.spawnlimits.sheep_farming) {
                    event.biome.addPassiveEntity(EntitySheep.class, mod_mocreatures.mocGlass.animals.sheepfreq);
                }

            if (event.biome.canSnow()) {
                /// Zimowe:
                event.biome.addPassiveEntity(EntityPolarBear.class, mod_mocreatures.mocGlass.huntercreatures.pbearfreq);
            }
            if (event.biome.canSnow() || event.biome == Biome.SWAMPLAND) {
                /// Collie:
                if(mod_mocreatures.mocGlass.spawnlimits.sheep_farming) {
                    event.biome.addPassiveEntity(EntityCollie.class, mod_mocreatures.mocGlass.animals.colliefreq);
                }
            }
            if (!event.biome.canRain()) {
                /// Pustynne:
                if(mod_mocreatures.mocGlass.spawnlimits.extra_mobs) {
                    event.biome.addPassiveEntity(EntityCamel.class, mod_mocreatures.mocGlass.animals.camelfreq);
                    event.biome.addHostileEntity(EntityMummy.class, mod_mocreatures.mocGlass.hostilemobs.mummyfreq);
                }
            }
            if (event.biome == Biome.SAVANNA || event.biome == Biome.SHRUBLAND) {
                /// Afryka:
                if(mod_mocreatures.mocGlass.spawnlimits.extra_mobs) {
                    event.biome.addPassiveEntity(EntityZebra.class, mod_mocreatures.mocGlass.animals.zebrafreq);
                    event.biome.addPassiveEntity(EntityGiraffe.class, mod_mocreatures.mocGlass.animals.giraffefreq);
                    event.biome.addPassiveEntity(EntityHippo.class, mod_mocreatures.mocGlass.huntercreatures.hippofreq);
                    event.biome.addPassiveEntity(EntityElephant.class, mod_mocreatures.mocGlass.animals.elephantfreq);
                    event.biome.addPassiveEntity(EntityCrocodile.class, mod_mocreatures.mocGlass.huntercreatures.crocodilefreq);
                }
            }
            if(!event.biome.canSnow() && event.biome.canRain()){
                /// Poza Śniegiem i Pustynią
                if(mod_mocreatures.mocGlass.spawnlimits.extra_mobs) {
                    event.biome.addPassiveEntity(EntityTurtle.class, mod_mocreatures.mocGlass.animals.turtlefreq);
                }
            }
        }else if (event.world.dimension instanceof NetherDimension) {
            event.biome.addHostileEntity(EntityFireOgre.class, mod_mocreatures.mocGlass.hostilemobs.fogrefreq);
            event.biome.addHostileEntity(EntityHellRat.class, mod_mocreatures.mocGlass.hostilemobs.hellratfreq);
            event.biome.addHostileEntity(EntityFlameWraith.class, mod_mocreatures.mocGlass.hostilemobs.fwraithfreq);
        }else {
            /// non-overworld & non-nether
            /// maybe twilight forest should have some mobs?
            return;
        }







    }
}
