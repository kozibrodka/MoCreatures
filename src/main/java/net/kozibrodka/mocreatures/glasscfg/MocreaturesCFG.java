package net.kozibrodka.mocreatures.glasscfg;

import net.glasslauncher.mods.api.gcapi.api.ConfigCategory;

public class MocreaturesCFG {

    @ConfigCategory("§5Spawn Limits")
    public SpawnLimitsCFG spawnlimits = new SpawnLimitsCFG();

    @ConfigCategory("§aAnimals")
    public AnimalsCFG animals = new AnimalsCFG();

    @ConfigCategory("§6Hunter Creatures")
    public HunterCreaturesCFG huntercreatures = new HunterCreaturesCFG();

    @ConfigCategory("§4Hostile Mobs")
    public HostileMobsCFG hostilemobs = new HostileMobsCFG();

    @ConfigCategory("§bWater Mobs")
    public WaterMobsCFG watermobs = new WaterMobsCFG();

    @ConfigCategory("§9Other Settings")
    public OtherSettingsCFG othersettings = new OtherSettingsCFG();

}
