package net.kozibrodka.mocreatures.glasscfg;

import net.glasslauncher.mods.gcapi3.api.ConfigCategory;

public class MocreaturesCFG {

    @ConfigCategory(name="§5Spawn Limits")
    public SpawnLimitsCFG spawnlimits = new SpawnLimitsCFG();

    @ConfigCategory(name="§aAnimals")
    public AnimalsCFG animals = new AnimalsCFG();  //BYC moze wyjebac vanilla zwierzatka

    @ConfigCategory(name="§6Hunter Creatures")
    public HunterCreaturesCFG huntercreatures = new HunterCreaturesCFG();

    @ConfigCategory(name="§4Hostile Mobs")
    public HostileMobsCFG hostilemobs = new HostileMobsCFG();

    @ConfigCategory(name="§bWater Mobs")
    public WaterMobsCFG watermobs = new WaterMobsCFG();

    @ConfigCategory(name="§9Other Settings")
    public OtherSettingsCFG othersettings = new OtherSettingsCFG();

    @ConfigCategory(name="§eBalance Settings (Extra)")
    public BalanceCFG balancesettings = new BalanceCFG();

}
