package net.kozibrodka.mocreatures.glasscfg;

import blue.endless.jankson.Comment;
import net.glasslauncher.mods.api.gcapi.api.ConfigName;
import net.glasslauncher.mods.api.gcapi.api.MaxLength;

public class HunterCreaturesCFG {

    @ConfigName("Target horses?")
    @Comment("hunters attack horses")
    public Boolean attackhorses = false;
    @ConfigName("Target dogs?")
    @Comment("hunters attack dogs")
    public Boolean attackwolves = false;
    @ConfigName("Destroy drops?")
    @Comment("hunters destroy drops")
    public Boolean destroyitems = false;
    @ConfigName("Aggressive Cats?")
    @Comment("cats attack bigCats")
    public Boolean attackbigcat = true;

    @ConfigName("BigCat Freq")
    @Comment("maximum 10")
    @MaxLength(
            value = 10
    )
    public Integer lionfreq = 3;
    @ConfigName("Bear Freq")
    @Comment("maximum 10")
    @MaxLength(
            value = 10
    )
    public Integer bearfreq = 2;
    @ConfigName("PBear Freq")
    @Comment("maximum 10")
    @MaxLength(
            value = 10
    )
    public Integer pbearfreq = 2;
    @ConfigName("Boar Freq")
    @Comment("maximum 10")
    @MaxLength(
            value = 10
    )
    public Integer boarfreq = 3;
    @ConfigName("Fox Freq")
    @Comment("maximum 10")
    @MaxLength(
            value = 10
    )
    public Integer foxfreq = 4;

}
