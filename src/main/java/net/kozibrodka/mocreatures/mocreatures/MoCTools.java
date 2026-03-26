package net.kozibrodka.mocreatures.mocreatures;

import net.kozibrodka.mocreatures.entity.*;
import net.kozibrodka.mocreatures.events.mod_mocreatures;
import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.client.util.ScreenScaler;
import net.minecraft.entity.Entity;
import net.minecraft.entity.ItemEntity;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.MobEntity;
import net.minecraft.entity.ai.pathing.Path;
import net.minecraft.entity.mob.MonsterEntity;
import net.minecraft.entity.passive.WolfEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.util.math.Box;
import net.minecraft.util.math.MathHelper;

import java.util.List;

public class MoCTools {

    public static void destroyDrops(Entity entity, double d) {
        if(mocr.mocreaturesGlass.huntercreatures.destroyitems) {
            List list = entity.world.getEntities(entity, entity.boundingBox.expand(d, d, d));
            for(int i = 0; i < list.size(); ++i) {
                Entity entity1 = (Entity)list.get(i);
                if(entity1 instanceof ItemEntity) {
                    ItemEntity entityitem = (ItemEntity)entity1;
                    if(entityitem.age < 50) {
                        entityitem.markDead();
                    }
                }
            }
        }
    }

    public static void MoveCreatureToXYZ(MobEntity movingEntity, int x, int y, int z, float f) {
        Path pathentity = movingEntity.world.findPath(movingEntity, x, y, z, f);
        if(pathentity != null) {
            movingEntity.setPath(pathentity);
        }

    }

    public static void MoveToWater(MobEntity entity) {
        int[] ai = ReturnNearestMaterialCoord(entity, Material.WATER, Double.valueOf(20.0D), Double.valueOf(2.0D));
        if(ai[0] > -1000) {
            MoveCreatureToXYZ(entity, ai[0], ai[1], ai[2], 24.0F);
        }

    }

    public static int[] ReturnNearestMaterialCoord(Entity entity, Material material, Double double1, Double yOff) {
        double shortestDistance = -1.0D;
        double distance = 0.0D;
        int x = -9999;
        int y = -1;
        int z = -1;
        Box axisalignedbb = entity.boundingBox.expand(double1.doubleValue(), yOff.doubleValue(), double1.doubleValue());
        int i = MathHelper.floor(axisalignedbb.minX);
        int j = MathHelper.floor(axisalignedbb.maxX + 1.0D);
        int k = MathHelper.floor(axisalignedbb.minY);
        int l = MathHelper.floor(axisalignedbb.maxY + 1.0D);
        int i1 = MathHelper.floor(axisalignedbb.minZ);
        int j1 = MathHelper.floor(axisalignedbb.maxZ + 1.0D);

        for(int k1 = i; k1 < j; ++k1) {
            for(int l1 = k; l1 < l; ++l1) {
                for(int i2 = i1; i2 < j1; ++i2) {
                    int j2 = entity.world.getBlockId(k1, l1, i2);
                    if(j2 != 0 && Block.BLOCKS[j2].material == material) {
                        distance = getSqDistanceTo(entity, k1, l1, i2);
                        if(shortestDistance == -1.0D) {
                            x = k1;
                            y = l1;
                            z = i2;
                            shortestDistance = distance;
                        }

                        if(distance < shortestDistance) {
                            x = k1;
                            y = l1;
                            z = i2;
                            shortestDistance = distance;
                        }
                    }
                }
            }
        }

        if(entity.x > (double)x) {
            x -= 2;
        } else {
            x += 2;
        }

        if(entity.z > (double)z) {
            z -= 2;
        } else {
            z += 2;
        }

        return new int[]{x, y, z};
    }

    public static double getSqDistanceTo(Entity entity, int i, int j, int k) {
        double l = entity.x - (double)i;
        double i1 = entity.y - (double)j;
        double j1 = entity.z - (double)k;
        return Math.sqrt(l * l + i1 * i1 + j1 * j1);
    }

    public static float realAngle(float origAngle) {
        return origAngle % 360.0F;
    }

    public static float distanceToSurface(Entity entity) {
        int i = MathHelper.floor(entity.x);
        int j = MathHelper.floor(entity.y);
        int k = MathHelper.floor(entity.z);
        int l = entity.world.getBlockId(i, j, k);
        if(l != 0 && Block.BLOCKS[l].material == Material.WATER) {
            for(int x = 1; x < 10; ++x) { /// Oryginalnie patrzy jedynie x < 5 (5 bloków wody w górę) co powoduje utknięcie na pewnej głębokości.
                l = entity.world.getBlockId(i, j + x, k);
                if(l == 0 || Block.BLOCKS[l].material != Material.WATER) {
                    return (float)x;
                }
            }
        }

        return 0.0F;
    }

    public static boolean NearMaterialWithDistance(Entity entity, Double double1, Material mat)
    {
        Box axisalignedbb = entity.boundingBox.expand(double1.doubleValue(), double1.doubleValue(), double1.doubleValue());
        int i = MathHelper.floor(axisalignedbb.minX);
        int j = MathHelper.floor(axisalignedbb.maxX + 1.0D);
        int k = MathHelper.floor(axisalignedbb.minY);
        int l = MathHelper.floor(axisalignedbb.maxY + 1.0D);
        int i1 = MathHelper.floor(axisalignedbb.minZ);
        int j1 = MathHelper.floor(axisalignedbb.maxZ + 1.0D);
        for(int k1 = i; k1 < j; k1++)
        {
            for(int l1 = k; l1 < l; l1++)
            {
                for(int i2 = i1; i2 < j1; i2++)
                {
                    int j2 = entity.world.getBlockId(k1, l1, i2);
                    if(j2 != 0 && Block.BLOCKS[j2].material == mat)
                    {
                        return true;
                    }
                }

            }

        }

        return false;
    }

    public static boolean isNearTorch(Entity entity) {
        if(mocr.mocreaturesGlass.huntercreatures.huntersSpawnOnTorch){
            return false;
        }else{
            return isNearBlockName(entity, Double.valueOf(8.0D), "tile.torch");
        }
    }

    public static boolean isNearTorch(Entity entity, Double dist) {
        return isNearBlockName(entity, dist, "tile.torch");
    }

    public static boolean isNearBlockName(Entity entity, Double dist, String blockName) {
        Box axisalignedbb = entity.boundingBox.expand(dist.doubleValue(), dist.doubleValue() / 2.0D, dist.doubleValue());
        int i = MathHelper.floor(axisalignedbb.minX);
        int j = MathHelper.floor(axisalignedbb.maxX + 1.0D);
        int k = MathHelper.floor(axisalignedbb.minY);
        int l = MathHelper.floor(axisalignedbb.maxY + 1.0D);
        int i1 = MathHelper.floor(axisalignedbb.minZ);
        int j1 = MathHelper.floor(axisalignedbb.maxZ + 1.0D);

        for(int k1 = i; k1 < j; ++k1) {
            for(int l1 = k; l1 < l; ++l1) {
                for(int i2 = i1; i2 < j1; ++i2) {
                    int j2 = entity.world.getBlockId(k1, l1, i2);
                    if(j2 != 0) {
                        String nameToCheck = "";
                        nameToCheck = Block.BLOCKS[j2].getTranslationKey();
                        if(nameToCheck != null && nameToCheck != "" && nameToCheck.equals(blockName)) {
                            return true;
                        }
                    }
                }
            }
        }

        return false;
    }

    public static void disorientEntity(Entity entity) {
        double rotD = 0.0D;
        double motD = 0.0D;
        double d = entity.world.random.nextGaussian();
        double d1 = 0.1D * d;
        motD = 0.2D * d1 + 0.8D * motD;
        entity.velocityX += motD;
        entity.velocityZ += motD;
        double d2 = 0.78D * d;
        rotD = 0.125D * d2 + 0.875D * rotD;
        entity.yaw = (float)((double)entity.yaw + rotD);
        entity.pitch = (float)((double)entity.pitch + rotD);
    }

    public static void slowEntity(Entity entity) {
        entity.velocityX *= 0.8D;
        entity.velocityZ *= 0.8D;
    }

    public static ItemEntity getClosestItem(Entity entity, double d, int i, int j)
    {
        double d1 = -1D;
        ItemEntity entityitem = null;
        List list = entity.world.getEntities(entity, entity.boundingBox.expand(d, d, d));
        for(int k = 0; k < list.size(); k++)
        {
            Entity entity1 = (Entity)list.get(k);
            if(!(entity1 instanceof ItemEntity))
            {
                continue;
            }
            ItemEntity entityitem1 = (ItemEntity)entity1;
            if(entityitem1.stack.itemId != i && j != 0 && entityitem1.stack.itemId != j)
            {
                continue;
            }
            double d2 = entityitem1.getSquaredDistance(entity.x, entity.y, entity.z);
            if((d < 0.0D || d2 < d * d) && (d1 == -1D || d2 < d1))
            {
                d1 = d2;
                entityitem = entityitem1;
            }
        }

        return entityitem;
    }

    public static boolean entitiesToIgnore(Entity hunter, Entity victim) {
        return !(victim instanceof LivingEntity) || (victim instanceof MonsterEntity) && !(hunter instanceof EntityBigCat) || victim == hunter || victim == hunter.passenger || victim == hunter.vehicle || (victim instanceof PlayerEntity) || (victim instanceof EntityKittyBed) || (victim instanceof EntityLitterBox) || (victim instanceof WolfEntity) && ((WolfEntity)victim).isTamed() && !mocr.mocreaturesGlass.huntercreatures.attackwolves || (victim instanceof EntityHorse) && ((EntityHorse)victim).getTamed() && !mocr.mocreaturesGlass.huntercreatures.attackhorses || (victim instanceof EntityDolphin) && ((EntityDolphin)victim).getTamed();
    }
    /// ^ nie obejmuje tamed kotów - jebać koty, trzeba trzymać w domu...

    public static boolean entitiesTamedIgnore(Entity hunter, Entity victim) {
        return (hunter instanceof MoCreatureNamed && victim instanceof MoCreatureNamed && ((MoCreatureNamed) hunter).getTamed() && ((MoCreatureNamed) victim).getTamed());
    }

    static mod_mocreatures mocr = new mod_mocreatures();
}
