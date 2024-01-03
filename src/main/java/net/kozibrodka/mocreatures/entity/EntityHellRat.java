
package net.kozibrodka.mocreatures.entity;

import net.kozibrodka.mocreatures.events.mod_mocreatures;
import net.minecraft.item.ItemBase;
import net.minecraft.level.Level;
import net.modificationstation.stationapi.api.server.entity.MobSpawnDataProvider;

public class EntityHellRat extends EntityRat implements MobSpawnDataProvider
{

    public EntityHellRat(Level world)
    {
        super(world);
        texture = "/assets/mocreatures/stationapi/textures/mob/hellrat.png";
        setSize(0.7F, 0.7F);
        health = 20;
        attackDamage = 2;
        immuneToFire = true;
    }

    public void chooseType()
    {
        texture = "/assets/mocreatures/stationapi/textures/mob/hellrat.png";
    }

    protected int getMobDrops()
    {
        return ItemBase.redstoneDust.id;
    }

    public boolean canSpawn()
    {
        return mocr.mocreaturesGlass.hostilemobs.hellratfreq > 0 && super.canSpawn();
    }

    mod_mocreatures mocr = new mod_mocreatures();
}
