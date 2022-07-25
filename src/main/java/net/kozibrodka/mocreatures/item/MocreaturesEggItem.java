package net.kozibrodka.mocreatures.item;


import net.kozibrodka.mocreatures.entity.*;
import net.minecraft.entity.EntityBase;
import net.minecraft.entity.player.PlayerBase;
import net.minecraft.item.ItemInstance;
import net.minecraft.level.Level;
import net.modificationstation.stationapi.api.registry.Identifier;
import net.modificationstation.stationapi.api.template.item.TemplateItemBase;

public class MocreaturesEggItem extends TemplateItemBase {
    public MocreaturesEggItem(Identifier identifier, int i) {
        super(identifier);
        this.maxStackSize = 16;
        ide = i;
    }


    public boolean useOnTile(ItemInstance arg, PlayerBase arg2, Level arg3, int i, int j, int k, int l) {


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


        huj.setPositionAndAngles(i, j, k, arg3.rand.nextFloat() * 360F, 0.0F);
        huj.setPosition(i, j + 1, k);
        arg3.spawnEntity(huj);
        --arg.count;

        return false;
    }

    public int ide;
    EntityBase huj;
}
