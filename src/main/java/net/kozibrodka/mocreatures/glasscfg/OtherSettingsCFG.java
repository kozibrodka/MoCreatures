package net.kozibrodka.mocreatures.glasscfg;

import net.glasslauncher.mods.gcapi3.api.ConfigEntry;

public class OtherSettingsCFG {

    @ConfigEntry(name="§5Extra mobs")
    public Boolean extra_mobs = true;

    @ConfigEntry(name="§aSheep Farming")
    public Boolean sheep_farming = true;

    @ConfigEntry(name="SugarLump is food item")
    public Boolean sugar_lump = true;

    @ConfigEntry(name="Easy saddle recipe", description = "horse saddle can be crafted from leather and iron")
    public Boolean easy_saddle_recipe = true;

    @ConfigEntry(name="Easy medallion recipe", description = "medallion recipe doesn't require diamond")
    public Boolean easy_medallion_recipe = true;

    @ConfigEntry(name="Show Pet Name?")
    public Boolean displayname = true;

    @ConfigEntry(name="Show Pet Health?")
    public Boolean displayhealth = true;

    @ConfigEntry(name="Show Pet Icons?")
    public Boolean displayemo = true;

    @ConfigEntry(name="Static K.Bed?")
    public Boolean staticbed = true;

    @ConfigEntry(name="Static Litter?")
    public Boolean staticlitter = true;

    @ConfigEntry(name="Apple, Golden Apple tame Horses", description = "green apple tame them by default")
    public Boolean apple_tame = false;

    @ConfigEntry(name="dev Spawn Eggs (cheating!!!)")
    public Boolean spawn_eggs = false;
}
