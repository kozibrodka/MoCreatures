package net.kozibrodka.mocreatures.glasscfg;

import net.glasslauncher.mods.gcapi3.api.ConfigEntry;

public class HunterCreaturesCFG {

    @ConfigEntry(name="Target horses?", description = "hunters attack tamed horses")
    public Boolean attackhorses = false;

    @ConfigEntry(name="Target dogs?", description = "hunters attack tamed dogs")
    public Boolean attackwolves = false;

    @ConfigEntry(name="Spawn near Torches", description = "option to disable spawning of hunters <=8 blocks next to torch")
    public Boolean huntersSpawnOnTorch = true;

    @ConfigEntry(name="Destroy drops?", description = "hunters destroy drops")
    public Boolean destroyitems = false;

    @ConfigEntry(name="Aggressive Cats?", description = "cats attack bigCats")
    public Boolean attackbigcat = true;

    @ConfigEntry(name="BigCat Freq", maxValue = 10)
    public Integer lionfreq = 3;

    @ConfigEntry(name="Bear Freq", maxValue = 10)
    public Integer bearfreq = 2;

    @ConfigEntry(name="PBear Freq", maxValue = 10)
    public Integer pbearfreq = 2;

    @ConfigEntry(name="Boar Freq", maxValue = 10)
    public Integer boarfreq = 3;

    @ConfigEntry(name="Fox Freq", maxValue = 10)
    public Integer foxfreq = 4;

    @ConfigEntry(name="Crocodile Freq", maxValue = 10)
    public Integer crocodilefreq = 2;

    @ConfigEntry(name="Hippo Freq", maxValue = 10)
    public Integer hippofreq = 2;
}
