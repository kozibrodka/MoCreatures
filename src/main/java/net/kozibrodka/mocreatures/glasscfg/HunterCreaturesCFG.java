package net.kozibrodka.mocreatures.glasscfg;


import net.glasslauncher.mods.api.gcapi.api.ConfigName;
import net.glasslauncher.mods.api.gcapi.api.MaxLength;
import org.checkerframework.framework.qual.CFComment;

public class HunterCreaturesCFG {

    @ConfigName("Target horses?")
    @CFComment("hunters attack horses")
    public Boolean attackhorses = false;
    @ConfigName("Target dogs?")
    @CFComment("hunters attack dogs")
    public Boolean attackwolves = false;
    @ConfigName("Destroy drops?")
    @CFComment("hunters destroy drops")
    public Boolean destroyitems = false;
    @ConfigName("Aggressive Cats?")
    @CFComment("cats attack bigCats")
    public Boolean attackbigcat = true;

    @ConfigName("BigCat Freq")
    @CFComment("maximum 10")
    @MaxLength(
            value = 10
    )
    public Integer lionfreq = 3;
    @ConfigName("Bear Freq")
    @CFComment("maximum 10")
    @MaxLength(
            value = 10
    )
    public Integer bearfreq = 2;
    @ConfigName("PBear Freq")
    @CFComment("maximum 10")
    @MaxLength(
            value = 10
    )
    public Integer pbearfreq = 2;
    @ConfigName("Boar Freq")
    @CFComment("maximum 10")
    @MaxLength(
            value = 10
    )
    public Integer boarfreq = 3;
    @ConfigName("Fox Freq")
    @CFComment("maximum 10")
    @MaxLength(
            value = 10
    )
    public Integer foxfreq = 4;

}
