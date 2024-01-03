package net.kozibrodka.mocreatures.glasscfg;


import net.glasslauncher.mods.api.gcapi.api.ConfigName;
import net.glasslauncher.mods.api.gcapi.api.MaxLength;
import org.checkerframework.framework.qual.CFComment;

public class HostileMobsCFG {
    @ConfigName("Spawn Ogre Difficulty")
    @CFComment("0 - easy, 1 - normal, 2 - hard")
    @MaxLength(
            value = 2
    )
    public Integer ogreSpawnDifficulty = 1;
    @ConfigName("Spawn FireOgre Difficulty")
    @CFComment("0 - easy, 1 - normal, 2 - hard")
    @MaxLength(
            value = 2
    )
    public Integer fogreSpawnDifficulty = 2;
    @ConfigName("Spawn CaveOgre Difficulty")
    @CFComment("0 - easy, 1 - normal, 2 - hard")
    @MaxLength(
            value = 2
    )
    public Integer cogreSpawnDifficulty = 1;
    @ConfigName("Spawn WereWolf Difficulty")
    @CFComment("0 - easy, 1 - normal, 2 - hard")
    @MaxLength(
            value = 2
    )
    public Integer wereSpawnDifficulty = 1;
    @ConfigName("Spawn Wraith Difficulty")
    @CFComment("0 - easy, 1 - normal, 2 - hard")
    @MaxLength(
            value = 2
    )
    public Integer wraithSpawnDifficulty = 1;
    @ConfigName("Spawn FlameWraith Difficulty")
    @CFComment("0 - easy, 1 - normal, 2 - hard")
    @MaxLength(
            value = 2
    )
    public Integer fwraithSpawnDifficulty = 2;

    @ConfigName("Ogre Freq")
    @MaxLength(
            value = 10
    )
    public Integer ogrefreq = 6;
    @ConfigName("FireOgre Freq")
    @MaxLength(
            value = 10
    )
    public Integer fogrefreq = 2;
    @ConfigName("CaveOgre Freq")
    @MaxLength(
            value = 10
    )
    public Integer cogrefreq = 3;
    @ConfigName("WereWolf Freq")
    @MaxLength(
            value = 10
    )
    public Integer werewolffreq = 6;
    @ConfigName("Wraith Freq")
    @MaxLength(
            value = 10
    )
    public Integer wraithfreq = 5;
    @ConfigName("FlameWraith Freq")
    @MaxLength(
            value = 10
    )
    public Integer fwraithfreq = 2;
    @ConfigName("WildWolf Freq")
    @MaxLength(
            value = 10
    )
    public Integer wwolffreq = 8;
    @ConfigName("Rat Freq")
    @MaxLength(
            value = 10
    )
    public Integer ratfreq = 8;
    @ConfigName("HellRat Freq")
    @MaxLength(
            value = 10
    )
    public Integer hellratfreq = 8;
    @ConfigName("Ogre Range")
    @MaxLength(
            value = 24
    )
    public Integer ogrerange = 12;
    @ConfigName("Ogre Strength")
    @CFComment("maximum 5.0F")
    @MaxLength(
            value = 5
    )
    public Float ogreStrength = 2.5F;
    @ConfigName("Fire Ogre Strength")
    @CFComment("maximum 5.0F")
    @MaxLength(
            value = 5
    )
    public Float fogreStrength = 2.0F;
    @ConfigName("Cave Ogre Strength")
    @CFComment("maximum 5.0F")
    @MaxLength(
            value = 5
    )
    public Float cogreStrength = 3.0F;

    @ConfigName("Fire Ogre Explosions")
    public Boolean explodefireogre = true;
    @ConfigName("Cave Ogre Explosions")
    public Boolean explodecaveogre = true;
    @ConfigName("Ogre Explosions")
    public Boolean explodeogre = true;
    @ConfigName("Fire Ogre Ignite")
    public Boolean igniteogre = true;


}
