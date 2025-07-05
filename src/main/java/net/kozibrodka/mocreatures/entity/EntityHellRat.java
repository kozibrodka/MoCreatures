
package net.kozibrodka.mocreatures.entity;

import net.kozibrodka.mocreatures.events.mod_mocreatures;
import net.minecraft.item.Item;
import net.minecraft.world.World;
import net.modificationstation.stationapi.api.server.entity.MobSpawnDataProvider;

public class EntityHellRat extends EntityRat implements MobSpawnDataProvider
{

    public EntityHellRat(World world)
    {
        super(world);
        texture = "/assets/mocreatures/stationapi/textures/mob/hellrat.png";
        setBoundingBoxSpacing(0.7F, 0.7F);
        health = 20;
        attackDamage = 2;
        fireImmune = true;
    }

    public void chooseType()
    {
        texture = "/assets/mocreatures/stationapi/textures/mob/hellrat.png";
    }

    protected int getDroppedItemId()
    {
        return Item.REDSTONE.id;
    }

    public boolean canSpawn()
    {
        return mocr.mocreaturesGlass.hostilemobs.hellratfreq > 0 && super.canSpawn();
    }

    mod_mocreatures mocr = new mod_mocreatures();
}
