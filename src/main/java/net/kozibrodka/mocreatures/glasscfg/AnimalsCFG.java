package net.kozibrodka.mocreatures.glasscfg;

import net.glasslauncher.mods.gcapi3.api.ConfigEntry;

public class AnimalsCFG {

    @ConfigEntry(
            name="Horse Freq",
            description = "maximum 10",
            maxLength = 10
    )
    public Integer horsefreq = 5;

    @ConfigEntry(name="Easy Horse Breed")
    public Boolean easybreeding = false;

    @ConfigEntry(name="Spawn Mouse Indoors")
    public Boolean mouseinhouse = true;

    @ConfigEntry(
            name="Pegasus chance",
            description = "maximum 3",
            maxLength = 3
    )
    public Integer pegasusChanceS = 1;

    @ConfigEntry(
            name="Bird Freq",
            description = "maximum 10",
            maxLength = 10
    )
    public Integer birdfreq = 5;

    @ConfigEntry(
            name="Bunny Freq",
            description = "maximum 10",
            maxLength = 10
    )
    public Integer bunnyfreq = 6;

    @ConfigEntry(
            name="Duck Freq",
            description = "maximum 10",
            maxLength = 10
    )
    public Integer duckfreq = 6;

    @ConfigEntry(
            name="Deer freq",
            description = "maximum 10",
            maxLength = 10
    )
    public Integer deerfreq = 6;

    @ConfigEntry(
            name="Kitty Freq",
            description = "maximum 10",
            maxLength = 10
    )
    public Integer kittyfreq = 4;

    @ConfigEntry(
            name="Mice Freq",
            description = "maximum 10",
            maxLength = 10
    )
    public Integer micefreq = 6;

    @ConfigEntry(
            name="Sheep Freq",
            description = "maximum 12",
            maxLength = 12
    )
    public Integer sheepfreq = 12;

    @ConfigEntry(
            name="Cow Freq",
            description = "maximum 8",
            maxLength = 8
    )
    public Integer cowfreq = 8;

    @ConfigEntry(
            name="Pig Freq",
            description = "maximum 10",
            maxLength = 10
    )
    public Integer pigfreq = 10;

    @ConfigEntry(
            name="Chicken Freq",
            description = "maximum 10",
            maxLength = 10
    )
    public Integer chickenfreq = 10;
}
