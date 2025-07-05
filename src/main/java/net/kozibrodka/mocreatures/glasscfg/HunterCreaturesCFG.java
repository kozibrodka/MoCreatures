package net.kozibrodka.mocreatures.glasscfg;

import net.glasslauncher.mods.gcapi3.api.ConfigEntry;

public class HunterCreaturesCFG {

    @ConfigEntry(
            name="Target horses?",
            description = "hunters attack horses"
    )
    public Boolean attackhorses = false;

    @ConfigEntry(
            name="Target dogs?",
            description = "hunters attack dogs"
    )
    public Boolean attackwolves = false;

    @ConfigEntry(
            name="Destroy drops?",
            description = "hunters destroy drops"
    )
    public Boolean destroyitems = false;

    @ConfigEntry(
            name="Aggressive Cats?",
            description = "cats attack bigCats"
    )
    public Boolean attackbigcat = true;

    @ConfigEntry(
            name="BigCat Freq",
            description = "maximum 10",
            maxLength = 10
    )
    public Integer lionfreq = 3;

    @ConfigEntry(
            name="Bear Freq",
            description = "maximum 10",
            maxLength = 10
    )
    public Integer bearfreq = 2;

    @ConfigEntry(
            name="PBear Freq",
            description = "maximum 10",
            maxLength = 10
    )
    public Integer pbearfreq = 2;

    @ConfigEntry(
            name="Boar Freq",
            description = "maximum 10",
            maxLength = 10
    )
    public Integer boarfreq = 3;

    @ConfigEntry(
            name="Fox Freq",
            description = "maximum 10",
            maxLength = 10
    )
    public Integer foxfreq = 4;
}
