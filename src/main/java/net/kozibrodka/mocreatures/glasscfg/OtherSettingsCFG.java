package net.kozibrodka.mocreatures.glasscfg;

import net.glasslauncher.mods.gcapi3.api.ConfigEntry;

public class OtherSettingsCFG {

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
}
