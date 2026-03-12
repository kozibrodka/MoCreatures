
package net.kozibrodka.mocreatures.entity;

import net.kozibrodka.mocreatures.events.mod_mocreatures;
import net.kozibrodka.mocreatures.mocreatures.MoCreatureRacial;
import net.minecraft.item.Item;
import net.minecraft.world.World;
import net.modificationstation.stationapi.api.server.entity.MobSpawnDataProvider;
import net.modificationstation.stationapi.api.util.Identifier;

public class EntityHellRat extends EntityRat implements MobSpawnDataProvider, MoCreatureRacial
{

    public EntityHellRat(World world)
    {
        super(world);
//        texture = "/assets/mocreatures/stationapi/textures/mob/hellrat.png";
        setBoundingBoxSpacing(0.7F, 0.7F);
        health = 20;
        attackDamage = 2;
        fireImmune = true;
    }

//    public void chooseType(int type)
//    {
//        texture = "/assets/mocreatures/stationapi/textures/mob/hellrat.png";
//    }

    protected int getDroppedItemId()
    {
        return Item.REDSTONE.id;
    }

    public boolean canSpawn()
    {
        return mocr.mocreaturesGlass.hostilemobs.hellratfreq > 0 && super.canSpawn2();
    }

    @Override
    public Identifier getHandlerIdentifier() {
        return Identifier.of(mod_mocreatures.MOD_ID, "HellRat");
    }

    mod_mocreatures mocr = new mod_mocreatures();

//    //TYPE
//    public void setTypeSpawn()
//    {
//        if(!world.isRemote){
//            int type = getRandomRace();
//            setType(type);
//        }
//    }
//
//    public void setType(int type)
//    {
//        if(!world.isRemote) {
//            dataTracker.set(16, (byte) type);
//            chooseType(type);
//        }
//    }
}
