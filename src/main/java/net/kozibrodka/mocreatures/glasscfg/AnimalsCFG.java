package net.kozibrodka.mocreatures.glasscfg;


import net.glasslauncher.mods.api.gcapi.api.ConfigName;
import net.glasslauncher.mods.api.gcapi.api.MaxLength;
import org.checkerframework.framework.qual.CFComment;

public class AnimalsCFG {

    @ConfigName("Horse Freq")
    @CFComment("maximum 10")
    @MaxLength(
            value = 10
    )
    public Integer horsefreq = 5;

    @ConfigName("Easy Horse Breed")
    public Boolean easybreeding = false;

    @ConfigName("Spawn Mouse Indoors")
    public Boolean mouseinhouse = true;

    @ConfigName("Pegasus chance")
    @CFComment("maximum 3")
    @MaxLength(
            value = 3
    )
    public Integer pegasusChanceS = 1;

    @ConfigName("Bird Freq")
    @CFComment("maximum 10")
    @MaxLength(
            value = 10
    )
    public Integer birdfreq = 5;

    @ConfigName("Bunny Freq")
    @CFComment("maximum 10")
    @MaxLength(
            value = 10
    )
    public Integer bunnyfreq = 6;

    @ConfigName("Duck Freq")
    @CFComment("maximum 10")
    @MaxLength(
            value = 10
    )
    public Integer duckfreq = 6;

    @ConfigName("Deer freq")
    @CFComment("maximum 10")
    @MaxLength(
            value = 10
    )
    public Integer deerfreq = 6;

    @ConfigName("Kitty Freq")
    @CFComment("maximum 10")
    @MaxLength(
            value = 10
    )
    public Integer kittyfreq = 4;

    @ConfigName("Mice Freq")
    @CFComment("maximum 10")
    @MaxLength(
            value = 10
    )
    public Integer micefreq = 6;
    @ConfigName("Sheep Freq")
    @CFComment("maximum 12")
    @MaxLength(
            value = 12
    )
    public Integer sheepfreq = 12;
    @ConfigName("Cow Freq")
    @CFComment("maximum 8")
    @MaxLength(
            value = 8
    )
    public Integer cowfreq = 8;
    @ConfigName("Pig Freq")
    @CFComment("maximum 10")
    @MaxLength(
            value = 10
    )
    public Integer pigfreq = 10;
    @ConfigName("Chicken Freq")
    @CFComment("maximum 10")
    @MaxLength(
            value = 10
    )
    public Integer chickenfreq = 10;

}
