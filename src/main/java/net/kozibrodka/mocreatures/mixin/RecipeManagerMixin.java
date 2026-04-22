package net.kozibrodka.mocreatures.mixin;

import net.kozibrodka.mocreatures.events.mod_mocreatures;
import net.minecraft.block.Block;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.recipe.CraftingRecipe;
import net.minecraft.recipe.CraftingRecipeManager;
import net.minecraft.recipe.ShapedRecipe;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

import java.util.List;

@Mixin(CraftingRecipeManager.class)

public class RecipeManagerMixin {

    @Shadow
    private List recipes;

    @Inject(method = "<init>", at = @At(value = "TAIL"))
    private void injectEatherSaddle(CallbackInfo ci) {

        for (int i = 0; i < recipes.size(); i++) {
            CraftingRecipe recipe = (CraftingRecipe) recipes.get(i);
            int recipeItemId = recipe.getOutput().itemId;

            if (recipeItemId == Item.SADDLE.asItem().id) {
                    ItemStack[] inputArray = new ItemStack[9];
                    inputArray[0] = new ItemStack(Item.LEATHER, 1);
                    inputArray[1] = new ItemStack(Item.LEATHER, 1);
                    inputArray[2] = new ItemStack(Item.LEATHER, 1);
                    inputArray[3] = new ItemStack(Item.LEATHER, 1);
                    inputArray[4] = new ItemStack(Item.STRING, 1);
                    inputArray[5] = new ItemStack(Item.LEATHER, 1);
                    recipes.set(i, new ShapedRecipe(3, 3, inputArray, new ItemStack(mod_mocreatures.aersaddle, 1)));
            }
        }
    }
}
