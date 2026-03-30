package net.kozibrodka.mocreatures.mixin;

import net.modificationstation.stationapi.api.client.gui.screen.achievement.AchievementPage;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Unique;
import org.spongepowered.asm.mixin.gen.Accessor;

import java.util.ArrayList;
import java.util.List;

@Mixin(AchievementPage.class)
public interface AchievementPageAccessor {

    @Accessor
    static List<AchievementPage> getPAGES() {
        throw new AssertionError();
    }
}
