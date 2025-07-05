package net.kozibrodka.mocreatures.glasscfg;

import net.glasslauncher.mods.gcapi3.api.ConfigEntry;

public class SpawnLimitsCFG {

    @ConfigEntry(
            name="Hostiles",
            maxLength = 1000
    )
    public Integer maxMobsS = 70;

    @ConfigEntry(
            name="Animals",
            maxLength = 1000
    )
    public Integer maxAnimalsS = 30;

    @ConfigEntry(
            name="WaterMobs",
            maxLength = 1000
    )
    public Integer maxWaterMobsS = 25;
}
