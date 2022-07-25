// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) braces deadcode 

package net.kozibrodka.mocreatures.entity;

import net.kozibrodka.mocreatures.events.mod_mocreatures;
import net.minecraft.item.ItemBase;
import net.minecraft.level.Level;

public class EntityHellRat extends EntityRat
{

    public EntityHellRat(Level world)
    {
        super(world);
        texture = "/assets/mocreatures/stationapi/textures/mob/hellrat.png";
        setSize(0.7F, 0.7F);
        health = 20;
        attackDamage = 2;
        immuneToFire = true;
    }

    public void chooseType()
    {
        texture = "/assets/mocreatures/stationapi/textures/mob/hellrat.png";
    }

    protected int getMobDrops()
    {
        return ItemBase.redstoneDust.id;
    }

    public boolean canSpawn()
    {
        return mocr.mocreaturesGlass.hostilemobs.hellratfreq > 0 && super.canSpawn();
    }

    mod_mocreatures mocr = new mod_mocreatures();
}
