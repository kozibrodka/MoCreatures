package net.kozibrodka.mocreatures.mocreatures;

import net.minecraft.entity.LivingEntity;

public interface MoCreatureNamed {
    void setName(String name);

    String getName();

    boolean getTamed();

    String getOwner();

}
