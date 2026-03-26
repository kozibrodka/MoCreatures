package net.kozibrodka.mocreatures.glasscfg;

import net.glasslauncher.mods.gcapi3.api.ConfigEntry;

public class BalanceCFG {

    @ConfigEntry(name="Rework mob drops?")
    public Boolean balance_drop = false;

/// Kategorie są troche zjebane IMO.

    @ConfigEntry(name="Horse Fuel system")
    public Boolean horse_fuel = false;

    @ConfigEntry(name="Horse drop saddle")
    public Boolean horse_saddle_drop = false;

    @ConfigEntry(name="Easy saddle recipe", description = "horse saddle can be crafter from leather and iron")
    public Boolean easy_saddle_recipe = true;

    @ConfigEntry(name="Easy medallion recipe", description = "medallion doesnt require diamond")
    public Boolean easy_medallion_recipe = true;

    @ConfigEntry(name="SugarLump is food item")
    public Boolean sugar_lump = true;

    @ConfigEntry(name="Apple, Golden Apple tame Horses", description = "green apple tame them by default")
    public Boolean apple_tame = false;

    @ConfigEntry(name="dev Spawn Eggs (cheating!!!)")
    public Boolean spawn_eggs = false;
}
