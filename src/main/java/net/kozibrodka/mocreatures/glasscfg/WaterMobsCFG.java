package net.kozibrodka.mocreatures.glasscfg;

import net.glasslauncher.mods.gcapi3.api.ConfigEntry;
import net.kozibrodka.mocreatures.glasscfg.enums.DifficultyEnum;

public class WaterMobsCFG {

    @ConfigEntry(
            name="Shark Freq",
            maxLength = 10
    )
    public Integer sharkfreq = 2;

    @ConfigEntry(name="Spawn Sharks Difficulty")
    public DifficultyEnum sharkSpawnDifficulty = DifficultyEnum.EASY;

    @ConfigEntry(
            name="Squid Freq",
            maxLength = 10  //oryginal 3
    )
    public Integer squidfreq = 1;

    @ConfigEntry(
            name="Dolphin Freq",
            maxLength = 10
    )
    public Integer dolphinfreq = 2;

    @ConfigEntry(
            name="Fishy Freq",
            maxLength = 20
    )
    public Integer fishfreq = 10;

    @ConfigEntry(
            name="Aggresive Dolphins?",
            description = "dolphins attack sharks"
    )
    public Boolean attackdolphins = true;

    @ConfigEntry(name="Spawn Piranhas?")
    public Boolean spawnpiranha = true;
}
