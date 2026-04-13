
package net.kozibrodka.mocreatures.entity;

import net.kozibrodka.mocreatures.events.mod_mocreatures;
import net.kozibrodka.mocreatures.mocreatures.MoCreatureRacial;
import net.minecraft.item.Item;
import net.minecraft.world.World;
import net.modificationstation.stationapi.api.server.entity.MobSpawnDataProvider;
import net.modificationstation.stationapi.api.util.Identifier;

public class EntityHellRat extends EntityRat implements MobSpawnDataProvider
{

    public EntityHellRat(World world)
    {
        super(world);
        setBoundingBoxSpacing(0.7F, 0.7F);
        health = 20;
        attackDamage = 2;
        fireImmune = true;
    }

    @Override
    public void setTypeSpawn()
    {
        if(!world.isRemote){
            setType(4);
        }
    }

    @Override
    protected int getDroppedItemId()
    {
        return Item.REDSTONE.id;
    }

    @Override
    public boolean canSpawn()
    {
        return mod_mocreatures.mocGlass.hostilemobs.hellratfreq > 0 && super.canSpawn2();
    }

    @Override
    public Identifier getHandlerIdentifier() {
        return Identifier.of(mod_mocreatures.MOD_ID, "HellRat");
    }


}
