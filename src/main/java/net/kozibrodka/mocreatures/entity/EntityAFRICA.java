//package net.kozibrodka.mocreatures.entity;
//
//import net.kozibrodka.mocreatures.events.mod_mocreatures;
//import net.minecraft.entity.passive.AnimalEntity;
//import net.minecraft.item.Item;
//import net.minecraft.nbt.NbtCompound;
//import net.minecraft.world.World;
//import net.modificationstation.stationapi.api.server.entity.MobSpawnDataProvider;
//import net.modificationstation.stationapi.api.util.Identifier;
//
//public class EntityAFRICA extends AnimalEntity implements MobSpawnDataProvider {
//
//    public EntityAFRICA(World world)
//    {
//        super(world);
//        texture = "/assets/mocreatures/stationapi/textures/mob/XXXXXXXXXXXX.png";
//        setBoundingBoxSpacing(0.9F, 1.3F);
//        health = 10; ///
//
//    }
//
//
//    public void writeNbt(NbtCompound nbttagcompound)
//    {
//        super.writeNbt(nbttagcompound);
//    }
//
//    public void readNbt(NbtCompound nbttagcompound)
//    {
//        super.readNbt(nbttagcompound);
//    }
//
//    public int getLimitPerChunk()
//    {
//        return 0;
//    }
//
//    protected int getDroppedItemId()
//    {
//        return Item.XXXXXXXXXXX.id;
//    }
//
//    protected String getRandomSound()
//    {
//        return "mocreatures:XXXXXXXXXXXX";
//    }
//
//    protected String getHurtSound()
//    {
//        return "mocreatures:XXXXXXXXXXX";
//    }
//
//    protected String getDeathSound()
//    {
//        return "mocreatures:XXXXXXXXXX";
//    }
//
//    public boolean canSpawn()
//    {
//        return mod_mocreatures.mocreaturesGlass.huntercreatures.XXXXXXXX > 0 && super.canSpawn();
//    }
//
//    @Override
//    public Identifier getHandlerIdentifier() {return Identifier.of(mod_mocreatures.MOD_ID, "XXXXXXXXX");}
//}
