package net.kozibrodka.mocreatures.glasscfg;

import net.glasslauncher.mods.gcapi3.api.ConfigEntry;
import net.kozibrodka.mocreatures.glasscfg.enums.DifficultyEnum;

public class HostileMobsCFG {

    @ConfigEntry(name="§6Fire Ogre Explosions Destroy Blocks")
    public Boolean explodefireogre = true;

    @ConfigEntry(name="§6Cave Ogre Explosions Destroy Blocks")
    public Boolean explodecaveogre = true;

    @ConfigEntry(name="§6Ogre Explosions Destroy Blocks")
    public Boolean explodeogre = true;

    @ConfigEntry(name="§6Fire Ogre Ignite Blocks")
    public Boolean igniteogre = true;

    @ConfigEntry(name="§bCave Ogre Drop Diamonds", description = "Disable to get more balanced drops - damaged diamond tools")
    public Boolean caveogrediamond = true;

    @ConfigEntry(name="Spawn Ogre Difficulty")
    public DifficultyEnum ogreSpawnDifficulty = DifficultyEnum.NORMAL;

    @ConfigEntry(name="Spawn FireOgre Difficulty")
    public DifficultyEnum fogreSpawnDifficulty = DifficultyEnum.NORMAL;

    @ConfigEntry(name="Spawn CaveOgre Difficulty")
    public DifficultyEnum cogreSpawnDifficulty = DifficultyEnum.NORMAL;

    @ConfigEntry(name="Spawn WereWolf Difficulty")
    public DifficultyEnum wereSpawnDifficulty = DifficultyEnum.NORMAL;

    @ConfigEntry(name="Spawn Wraith Difficulty")
    public DifficultyEnum wraithSpawnDifficulty = DifficultyEnum.NORMAL;

    @ConfigEntry(name="Spawn FlameWraith Difficulty")
    public DifficultyEnum fwraithSpawnDifficulty = DifficultyEnum.HARD;

    @ConfigEntry(name="Ogre Strength", maxValue = 5.0F)
    public Float ogreStrength = 2.5F;

    @ConfigEntry(name="Fire Ogre Strength", maxValue = 5.0F)
    public Float fogreStrength = 2.0F;

    @ConfigEntry(name="Cave Ogre Strength", maxValue = 5.0F)
    public Float cogreStrength = 3.0F;

    @ConfigEntry(name="Ogre Range", maxValue = 24)
    public Integer ogrerange = 12;

    @ConfigEntry(name="Ogre Freq", maxValue = 10)
    public Integer ogrefreq = 6;

    @ConfigEntry(name="FireOgre Freq", maxValue = 10)
    public Integer fogrefreq = 2;

    @ConfigEntry(name="CaveOgre Freq", maxValue = 10)
    public Integer cogrefreq = 3;

    @ConfigEntry(name="WereWolf Freq", maxValue = 10)
    public Integer werewolffreq = 6;

    @ConfigEntry(name="Wraith Freq", maxValue = 10)
    public Integer wraithfreq = 5;

    @ConfigEntry(name="FlameWraith Freq", maxValue = 10)
    public Integer fwraithfreq = 2;

    @ConfigEntry(name="WildWolf Freq", maxValue = 10)
    public Integer wwolffreq = 8;

    @ConfigEntry(name="Rat Freq", maxValue = 10)
    public Integer ratfreq = 8;

    @ConfigEntry(name="HellRat Freq", maxValue = 10)
    public Integer hellratfreq = 8;

    @ConfigEntry(name="Mummy Freq", maxValue = 10)
    public Integer mummyfreq = 4;

    @ConfigEntry(name="Scorpion Freq", maxValue = 10)
    public Integer scorpionfreq = 4;

}
