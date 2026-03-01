package net.kozibrodka.mocreatures.glasscfg;

import net.glasslauncher.mods.gcapi3.api.ConfigEntry;

public class BalanceCFG {

    @ConfigEntry(name="Rework mob drops?")
    public Boolean balance_drop = false;
    @ConfigEntry(name="SugarLump is food item")
    public Boolean sugar_lump = true;
    @ConfigEntry(name="Apple, Golden Apple tame Horses and Dolphins")
    public Boolean apple_tame = false;
    @ConfigEntry(name="Horse drop saddle")
    public Boolean horse_saddle_drop = false;
    @ConfigEntry(name="Horse Fuel system")
    public Boolean horse_fuel = false;
    @ConfigEntry(name="Spawn Eggs (cheating!!!)")
    public Boolean spawn_eggs = false;
}
