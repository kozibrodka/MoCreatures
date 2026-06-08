package net.kozibrodka.mocreatures.glasscfg;

import net.glasslauncher.mods.gcapi3.api.ConfigEntry;

public class SpawnLimitsCFG {

    @ConfigEntry(name="§5Extra mobs", description = "Mobs from never (>2.12.2) versions + Savanna mobs", requiresRestart = true)
    public Boolean extra_mobs = false;

    @ConfigEntry(name="§aSheep Grazing", description = "Sheeps are tamable (Feed it with wheat then put Bell on) and regrow wool (grass needed), use rope (or collie dog) to navigate them", requiresRestart = true)
    public Boolean sheep_farming = false;

    @ConfigEntry(name="Hostiles", maxValue = 1000, description = "requires game restart", requiresRestart = true)
    public Integer maxMobsS = 70;

    @ConfigEntry(name="Animals", maxValue = 1000, description = "requires game restart", requiresRestart = true)
    public Integer maxAnimalsS = 30;

    @ConfigEntry(name="WaterMobs", maxValue = 1000, description = "requires game restart", requiresRestart = true)
    public Integer maxWaterMobsS = 25;
}
