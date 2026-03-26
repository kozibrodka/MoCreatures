package net.kozibrodka.mocreatures.glasscfg;

import net.glasslauncher.mods.gcapi3.api.ConfigEntry;

public class AnimalsCFG {

    @ConfigEntry(name="Easy Horse Breed")
    public Boolean easybreeding = false;

    @ConfigEntry(name="Spawn Mouse Indoors")
    public Boolean mouseinhouse = true;

    @ConfigEntry(name="Pegasus chance", maxValue = 3)
    public Integer pegasusChanceS = 1;

    @ConfigEntry(name="Horse Freq", maxValue = 10)
    public Integer horsefreq = 5;

    @ConfigEntry(name="Bird Freq", maxValue = 10)
    public Integer birdfreq = 5;

    @ConfigEntry(name="Bunny Freq", maxValue = 10)
    public Integer bunnyfreq = 6;

    @ConfigEntry(name="Duck Freq", maxValue = 10)
    public Integer duckfreq = 6;

    @ConfigEntry(name="Deer freq", maxValue = 10)
    public Integer deerfreq = 6;

    @ConfigEntry(name="Kitty Freq", maxValue = 10)
    public Integer kittyfreq = 4;

    @ConfigEntry(name="Mice Freq", maxValue = 10)
    public Integer micefreq = 6;

    @ConfigEntry(name="Sheep Freq", maxValue = 12)
    public Integer sheepfreq = 12;

    @ConfigEntry(name="Cow Freq", maxValue = 8)
    public Integer cowfreq = 8;

    @ConfigEntry(name="Pig Freq", maxValue = 10)
    public Integer pigfreq = 10;

    @ConfigEntry(name="Chicken Freq", maxValue = 10)
    public Integer chickenfreq = 10;

    @ConfigEntry(name="Elephant Freq", maxValue = 10)
    public Integer elephantfreq = 4;

    @ConfigEntry(name="Zebra Freq", maxValue = 10)
    public Integer zebrafreq = 5;

    @ConfigEntry(name="Giraffe Freq", maxValue = 10)
    public Integer giraffefreq = 4;

    @ConfigEntry(name="Giraffe Freq", maxValue = 10)
    public Integer camelfreq = 3;

    @ConfigEntry(name="Turtle Freq", maxValue = 10)
    public Integer turtlefreq = 2;

}
