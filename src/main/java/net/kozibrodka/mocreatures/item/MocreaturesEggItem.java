package net.kozibrodka.mocreatures.item;


import net.kozibrodka.mocreatures.entity.*;
import net.kozibrodka.mocreatures.events.TextureListener;
import net.kozibrodka.mocreatures.mocreatures.MoCreatureRacial;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.world.World;
import net.modificationstation.stationapi.api.util.Identifier;
import net.modificationstation.stationapi.api.template.item.TemplateItem;

public class MocreaturesEggItem extends TemplateItem {
    public MocreaturesEggItem(Identifier identifier, int i) {
        super(identifier);
        this.maxCount = 16;
        ide = i;
    }

    public boolean useOnBlock(ItemStack arg, PlayerEntity arg2, World arg3, int i, int j, int k, int l) {

        if(arg3.isRemote){
            return true;
        }

        if(ide == 1){
            huj = new EntityBear(arg3);
        }
        if(ide == 2){
            huj = new EntityBigCat(arg3);
        }
        if(ide == 3){
            huj = new EntityBird(arg3);
        }
        if(ide == 4){
            huj = new EntityBoar(arg3);
        }
        if(ide == 5){
            huj = new EntityBunny(arg3);
        }
        if(ide == 6){
            huj = new EntityCaveOgre(arg3);
        }
        if(ide == 7){
            huj = new EntityDeer(arg3);
        }
        if(ide == 8){
            huj = new EntityDolphin(arg3);
        }
        if(ide == 9){
            huj = new EntityDuck(arg3);
        }
        if(ide == 10){
            huj = new EntityFireOgre(arg3);
        }
        if(ide == 11){
            huj = new EntityFishy(arg3);
        }
        if(ide == 12){
            huj = new EntityFishyEgg(arg3);
        }
        if(ide == 13){
            huj = new EntityFlameWraith(arg3);
        }
        if(ide == 14){
            huj = new EntityFox(arg3);
        }
        if(ide == 15){
            huj = new EntityHellRat(arg3);
        }
        if(ide == 16){
            huj = new EntityHorse(arg3);
        }
        if(ide == 17){
            huj = new EntityKitty(arg3);
        }
        if(ide == 18){
            huj = new EntityKittyBed(arg3);
        }
        if(ide == 19){
            huj = new EntityLitterBox(arg3);
        }
        if(ide == 20){
            huj = new EntityMouse(arg3);
        }
        if(ide == 21){
            huj = new EntityOgre(arg3);
        }
        if(ide == 22){
            huj = new EntityPolarBear(arg3);
        }
        if(ide == 23){
            huj = new EntityRat(arg3);
        }
        if(ide == 24){
            huj = new EntityShark(arg3);
            huj.setPositionAndAnglesKeepPrevAngles(i, j, k, arg3.random.nextFloat() * 360F, 0.0F);
            huj.setPosition(i, j + 1, k);
            arg3.spawnEntity(huj);
            ((EntityShark)huj).setType(1);
            ((EntityShark)huj).setAge(1.0F + random.nextFloat());;
            --arg.count;
            return false; ///anty megalodon from spawn-egg
        }
        if(ide == 25){
            huj = new EntitySharkEgg(arg3, "null");
        }
        if(ide == 26){
            huj = new EntityWerewolf(arg3);
        }
        if(ide == 27){
            huj = new EntityWraith(arg3);
        }
        if(ide == 28){
            huj = new EntityWWolf(arg3);
        }
        if(ide == 60){
            huj = new EntityZebra(arg3);
        }
        if(ide == 61){
            huj = new EntityElephant(arg3);
        }
        if(ide == 62){
            huj = new EntityHippo(arg3);
        }
        if(ide == 63){
            huj = new EntityGiraffe(arg3);
        }
//        if(ide == 64){
//            huj = new XXXX(arg3);
//        }
//        if(ide == 65){
//            huj = new XXXX(arg3);
//        }

        if(ide >= 30 && ide < 40){
            huj = new EntityHorse(arg3);
            huj.setPositionAndAnglesKeepPrevAngles(i, j, k, arg3.random.nextFloat() * 360F, 0.0F);
            huj.setPosition(i, j + 1, k);
            arg3.spawnEntity(huj);
            --arg.count;
            ((EntityHorse)huj).setType(ide - 30 + 1);
            ((EntityHorse)huj).setAdult(true);
            ((EntityHorse) huj).health = ((EntityHorse) huj).maxhealth;
            return false;
        }

        if(ide >= 40 && ide < 50){
            huj = new EntityBigCat(arg3);
            huj.setPositionAndAnglesKeepPrevAngles(i, j, k, arg3.random.nextFloat() * 360F, 0.0F);
            huj.setPosition(i, j + 1, k);
            arg3.spawnEntity(huj);
            --arg.count;
            ((EntityBigCat)huj).setType(ide - 40 + 1);
            if (random.nextInt(4) == 0) {
                ((EntityBigCat)huj).setAdult(false);
                ((EntityBigCat)huj).killedByOtherEntity = true;
            }else{
                ((EntityBigCat)huj).setAdult(true);
                ((EntityBigCat)huj).setAge(1.0F);
            }
            ((EntityBigCat) huj).health = ((EntityBigCat) huj).maxhealth;
            return false;
        }

        if(ide >= 50 && ide < 60){
            huj = new EntityDolphin(arg3);
            huj.setPositionAndAnglesKeepPrevAngles(i, j, k, arg3.random.nextFloat() * 360F, 0.0F);
            huj.setPosition(i, j + 1, k);
            arg3.spawnEntity(huj);
            --arg.count;
            ((EntityDolphin)huj).setType(ide - 50 + 1);
            ((EntityDolphin) huj).setAge((0.8F + random.nextFloat()));
            if(((EntityDolphin) huj).getAge() < 1.5F){
                ((EntityDolphin) huj).setAge(1.7F);
            }
            ((EntityDolphin)huj).setAdult(true);
            ((EntityDolphin) huj).health = ((EntityDolphin) huj).maxhealth;
            return false;
        }


        huj.setPositionAndAnglesKeepPrevAngles(i, j, k, arg3.random.nextFloat() * 360F, 0.0F);
        huj.setPosition(i, j + 1, k);
        arg3.spawnEntity(huj);
        --arg.count;
        if(huj instanceof MoCreatureRacial){
            //LOADING PROPS by EGG-SPAWN
            ((MoCreatureRacial)huj).setTypeSpawn();
        }

        return false;
    }

    public int ide;
    Entity huj;
}
