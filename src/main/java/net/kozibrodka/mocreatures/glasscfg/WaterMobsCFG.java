package net.kozibrodka.mocreatures.glasscfg;

import net.glasslauncher.mods.api.gcapi.api.ConfigName;
import net.glasslauncher.mods.api.gcapi.api.MaxLength;
import org.checkerframework.framework.qual.CFComment;

public class WaterMobsCFG {

    @ConfigName("Shark Freq")
    @MaxLength(
            value = 10
    )
    public Integer sharkfreq = 2;
    @ConfigName("Spawn Sharks Difficulty")
    @CFComment("0 - easy, 1 - normal, 2 - hard")
    @MaxLength(
            value = 2
    )
    public Integer sharkSpawnDifficulty = 0;
    @ConfigName("Squid Freq")
    @MaxLength(
            value = 10  //oryginal 3
    )
    public Integer squidfreq = 1;
    @ConfigName("Dolphin Freq")
    @MaxLength(
            value = 10
    )
    public Integer dolphinfreq = 2;
    @ConfigName("Fishy Freq")
    @MaxLength(
            value = 20
    )
    public Integer fishfreq = 10;
    @ConfigName("Aggresive Dolphins?")
    @CFComment("dolphins attack sharks")
    public Boolean attackdolphins = true;
    @ConfigName("Spawn Piranhas?")
    public Boolean spawnpiranha = true;
}
