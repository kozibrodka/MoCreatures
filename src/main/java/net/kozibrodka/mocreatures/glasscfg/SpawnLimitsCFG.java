package net.kozibrodka.mocreatures.glasscfg;

import blue.endless.jankson.Comment;
import net.glasslauncher.mods.api.gcapi.api.ConfigName;
import net.glasslauncher.mods.api.gcapi.api.MaxLength;

public class SpawnLimitsCFG {

    @ConfigName("Hostiles")
    @MaxLength(
            value = 1000
    )
    public Integer maxMobsS = 70;

    @ConfigName("Animals")
    @MaxLength(
            value = 1000
    )
    public Integer maxAnimalsS = 30;

    @ConfigName("WaterMobs")
    @MaxLength(
            value = 1000
    )
    public Integer maxWaterMobsS = 25;

}
