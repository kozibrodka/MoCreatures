package net.kozibrodka.mocreatures.mixin;

import net.kozibrodka.mocreatures.entity.*;
import net.minecraft.entity.Entity;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.mob.CreeperEntity;
import net.minecraft.entity.mob.GhastEntity;
import net.minecraft.entity.passive.WolfEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.util.math.Box;
import net.minecraft.world.World;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Overwrite;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

import java.util.Iterator;
import java.util.List;


@Mixin(PlayerEntity.class)
public class PlayerBaseMixin extends LivingEntity{


    @Shadow public String name;

    public PlayerBaseMixin(World arg) {
        super(arg);
    }

    @Shadow  protected boolean method_498() {
        return false;
    }


    @Inject(method = "damage", at = @At(value = "INVOKE", target = "Lnet/minecraft/entity/player/PlayerEntity;method_510(Lnet/minecraft/entity/LivingEntity;Z)V"))
    private void injected(Entity i, int par2, CallbackInfoReturnable<Boolean> cir) {
        Object obj3 = i;
        this.alertBigCat((LivingEntity)obj3, false);
    }

    @Inject(method = "attack", at = @At(value = "INVOKE", target = "Lnet/minecraft/entity/player/PlayerEntity;method_510(Lnet/minecraft/entity/LivingEntity;Z)V"))
    private void injected1(Entity par1, CallbackInfo ci) {
        this.alertBigCat((LivingEntity)par1, true);
    }

    public void alertBigCat(LivingEntity entityliving, boolean flag){
        if((entityliving instanceof CreeperEntity) || (entityliving instanceof GhastEntity))
        {
            return;
        }
        if(entityliving instanceof WolfEntity)
        {
            WolfEntity entitywolf = (WolfEntity)entityliving;
            if(entitywolf.method_425() && name.equals(entitywolf.method_432()))
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


        if((entityliving instanceof PlayerEntity) && !method_498())
        {
            return;
        }
        List list = world.method_175(EntityBigCat.class, Box.createCached(x, y, z, x + 1.0D, y + 1.0D, z + 1.0D).expand(16D, 4D, 16D));
        Iterator iterator = list.iterator();
        do
        {
            if(!iterator.hasNext())
            {
                break;
            }
            Entity entity = (Entity)iterator.next();
            EntityBigCat entitybigcat = (EntityBigCat)entity;
            if(entitybigcat.tamed && entitybigcat.adult && entitybigcat.getTarget() == null && name.equals(entitybigcat.tigerOwner) && (!flag || !entitybigcat.sitting))
            {
                if(!(entityliving instanceof PlayerEntity && !entitybigcat.protectFromPlayers))
                {
                    entitybigcat.wstanSzybko();
                    entitybigcat.ustawCel(entityliving);
                }
            }
        } while(true);
    }

}
