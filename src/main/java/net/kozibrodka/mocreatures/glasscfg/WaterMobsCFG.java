package net.kozibrodka.mocreatures.glasscfg;

import net.glasslauncher.mods.gcapi3.api.ConfigEntry;
import net.kozibrodka.mocreatures.glasscfg.enums.DifficultyEnum;

public class WaterMobsCFG {


    @ConfigEntry(name="Aggresive Dolphins?", description = "dolphins attack sharks")
    public Boolean attackdolphins = true;

    @ConfigEntry(name="Spawn Piranhas?")
    public Boolean spawnpiranha = true;

    @ConfigEntry(name="Dolphin Shallow Bogging", description = "less speed on shallow waters")
    public Boolean shallowdolphin = true;

    @ConfigEntry(name="Spawn Sharks Difficulty")
    public DifficultyEnum sharkSpawnDifficulty = DifficultyEnum.EASY;

    @ConfigEntry(name="Shark Freq", maxValue = 10)
    public Integer sharkfreq = 2;

    @ConfigEntry(name="Squid Freq", maxValue = 10)
    public Integer squidfreq = 3;

    @ConfigEntry(name="Dolphin Freq", maxValue = 10)
    public Integer dolphinfreq = 2;

    @ConfigEntry(name="Fishy Freq", maxValue = 20)
    public Integer fishfreq = 10;

    @ConfigEntry(name="Ray Freq", maxValue = 10)
    public Integer rayfreq = 1;
}
