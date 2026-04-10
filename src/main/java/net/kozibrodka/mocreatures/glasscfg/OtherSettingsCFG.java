package net.kozibrodka.mocreatures.glasscfg;

import net.glasslauncher.mods.gcapi3.api.ConfigEntry;

public class OtherSettingsCFG {

    @ConfigEntry(name="SugarLump is food item", multiplayerSynced = true)
    public Boolean sugar_lump = true;

    @ConfigEntry(name="Easy saddle recipe", description = "horse saddle can be crafted from leather and iron", multiplayerSynced = true)
    public Boolean easy_saddle_recipe = true;

    @ConfigEntry(name="Easy medallion recipe", description = "medallion recipe doesn't require diamond", multiplayerSynced = true)
    public Boolean easy_medallion_recipe = true;

    @ConfigEntry(name="Show Pet Name?")
    public Boolean displayname = true;

    @ConfigEntry(name="Show Pet Health?")
    public Boolean displayhealth = true;

    @ConfigEntry(name="Show Pet Icons?")
    public Boolean displayemo = true;

    @ConfigEntry(name="Static K.Bed?", multiplayerSynced = true)
    public Boolean staticbed = true;

    @ConfigEntry(name="Static Litter?", multiplayerSynced = true)
    public Boolean staticlitter = true;

    @ConfigEntry(name="Apple, Golden Apple tame Horses", description = "green apple tame them by default", multiplayerSynced = true)
    public Boolean apple_tame = false;

    @ConfigEntry(name="dev Spawn Eggs (cheating!!!)", multiplayerSynced = true)
    public Boolean spawn_eggs = false;
}
