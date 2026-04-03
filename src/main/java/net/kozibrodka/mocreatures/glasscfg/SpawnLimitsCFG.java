package net.kozibrodka.mocreatures.glasscfg;

import net.glasslauncher.mods.gcapi3.api.ConfigEntry;

public class SpawnLimitsCFG {

    @ConfigEntry(name="§5Extra mobs", description = "requires game restart")
    public Boolean extra_mobs = false;

    @ConfigEntry(name="§aSheep Grazing", description = "requires game restart")
    public Boolean sheep_farming = false;

    @ConfigEntry(name="Hostiles", maxValue = 1000, description = "requires game restart")
    public Integer maxMobsS = 70;

    @ConfigEntry(name="Animals", maxValue = 1000, description = "requires game restart")
    public Integer maxAnimalsS = 30;

    @ConfigEntry(name="WaterMobs", maxValue = 1000, description = "requires game restart")
    public Integer maxWaterMobsS = 25;
}
