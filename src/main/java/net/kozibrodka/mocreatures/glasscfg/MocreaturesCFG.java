package net.kozibrodka.mocreatures.glasscfg;

import net.glasslauncher.mods.gcapi3.api.ConfigCategory;
import net.glasslauncher.mods.gcapi3.api.ConfigEntry;

public class MocreaturesCFG {

    @ConfigCategory(name="§5Spawn Limits" , multiplayerSynced = true)
    public SpawnLimitsCFG spawnlimits = new SpawnLimitsCFG();

    @ConfigCategory(name="§aAnimals", multiplayerSynced = true)
    public AnimalsCFG animals = new AnimalsCFG();

    @ConfigCategory(name="§6Hunter Creatures", multiplayerSynced = true)
    public HunterCreaturesCFG huntercreatures = new HunterCreaturesCFG();

    @ConfigCategory(name="§4Hostile Mobs", multiplayerSynced = true)
    public HostileMobsCFG hostilemobs = new HostileMobsCFG();

    @ConfigCategory(name="§bWater Mobs", multiplayerSynced = true)
    public WaterMobsCFG watermobs = new WaterMobsCFG();

    @ConfigCategory(name="§9Other Settings")
    public OtherSettingsCFG othersettings = new OtherSettingsCFG();



}
