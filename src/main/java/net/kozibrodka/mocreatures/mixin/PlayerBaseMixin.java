package net.kozibrodka.mocreatures.mixin;

import net.kozibrodka.mocreatures.entity.*;
import net.minecraft.entity.EntityBase;
import net.minecraft.entity.Living;
import net.minecraft.entity.animal.Wolf;
import net.minecraft.entity.monster.Creeper;
import net.minecraft.entity.monster.Ghast;
import net.minecraft.entity.player.PlayerBase;
import net.minecraft.util.maths.Box;
import net.minecraft.level.Level;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Overwrite;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

import java.util.Iterator;
import java.util.List;


@Mixin(PlayerBase.class)
public class PlayerBaseMixin extends Living{


    @Shadow public String name;

    public PlayerBaseMixin(Level arg) {
        super(arg);
    }

    @Shadow  protected boolean isPvpable() {
        return false;
    }


    @Inject(method = "damage", at = @At(value = "INVOKE", target = "Lnet/minecraft/entity/player/PlayerBase;method_510(Lnet/minecraft/entity/Living;Z)V"))
    private void injected(EntityBase i, int par2, CallbackInfoReturnable<Boolean> cir) {
        Object obj3 = i;
        this.alertBigCat((Living)obj3, false);
    }

    @Inject(method = "attack", at = @At(value = "INVOKE", target = "Lnet/minecraft/entity/player/PlayerBase;method_510(Lnet/minecraft/entity/Living;Z)V"))
    private void injected1(EntityBase par1, CallbackInfo ci) {
        this.alertBigCat((Living)par1, true);
    }

    public void alertBigCat(Living entityliving, boolean flag){
        if((entityliving instanceof Creeper) || (entityliving instanceof Ghast))
        {
            return;
        }
        if(entityliving instanceof Wolf)
        {
            Wolf entitywolf = (Wolf)entityliving;
            if(entitywolf.isTamed() && name.equals(entitywolf.getOwner()))
            {
                return;
            }
        }
        if(entityliving instanceof EntityBigCat)
        {
            EntityBigCat tygrys = (EntityBigCat)entityliving;
            if(tygrys.tamed && name.equals(tygrys.tigerOwner))
            {
                return;
            }
        }
        if((entityliving instanceof EntityHorse && ((EntityHorse)entityliving).tamed) || (entityliving instanceof EntityDolphin && ((EntityDolphin)entityliving).tamed) || (entityliving instanceof EntityShark && ((EntityShark)entityliving).tamed) || (entityliving instanceof EntityKitty && ((EntityKitty)entityliving).kittystate > 2 ))
        {
            return;
        }


        if((entityliving instanceof PlayerBase) && !isPvpable())
        {
            return;
        }
        List list = level.getEntities(EntityBigCat.class, Box.createButWasteMemory(x, y, z, x + 1.0D, y + 1.0D, z + 1.0D).expand(16D, 4D, 16D));
        Iterator iterator = list.iterator();
        do
        {
            if(!iterator.hasNext())
            {
                break;
            }
            EntityBase entity = (EntityBase)iterator.next();
            EntityBigCat entitybigcat = (EntityBigCat)entity;
            if(entitybigcat.tamed && entitybigcat.adult && entitybigcat.method_634() == null && name.equals(entitybigcat.tigerOwner) && (!flag || !entitybigcat.sitting))
            {
                if(!(entityliving instanceof PlayerBase && !entitybigcat.protectFromPlayers))
                {
                    entitybigcat.wstanSzybko();
                    entitybigcat.ustawCel(entityliving);
                }
            }
        } while(true);
    }

}
