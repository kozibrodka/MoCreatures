package net.kozibrodka.mocreatures.item;


import net.kozibrodka.mocreatures.entity.*;
import net.kozibrodka.mocreatures.events.TextureListener;
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


        huj.method_1341(i, j, k, arg3.field_214.nextFloat() * 360F, 0.0F);
        huj.method_1340(i, j + 1, k);
        arg3.method_210(huj);
        --arg.count;
        if(ide == 7){
            ((EntityDeer)huj).setType(3);
        }

        return false;
    }

    public int ide;
    Entity huj;
}
