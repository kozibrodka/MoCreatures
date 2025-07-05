package net.kozibrodka.mocreatures.glasscfg;

import net.glasslauncher.mods.gcapi3.api.ConfigEntry;

public class BalanceCFG {

    @ConfigEntry(name="Rework mob drops?")
    public Boolean balance_drop = false;
    @ConfigEntry(name="SugarLump is food item")
    public Boolean sugar_lump = true;
    @ConfigEntry(name="Horse salldle balance?")
    public Boolean horse_saddle = false;
    @ConfigEntry(name="Horse Fuel")
    public Boolean horse_fuel = false;
    @ConfigEntry(name="Horse Speed Boost by mouse etc.......")
    public Boolean horse_speed_boost = true;
    @ConfigEntry(name="Spawn Eggs (cheating)")
    public Boolean spawn_eggs = false;
}
