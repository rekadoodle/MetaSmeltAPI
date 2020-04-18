package net.minecraft.src.metasmeltapi;

import java.util.ArrayList;
import java.util.List;

import net.minecraft.src.*;

public class FurnaceManager {
	
	public class Recipe {
		
		public Recipe(ItemStack input, ItemStack output) {
			this.INPUT = input;
			this.OUTPUT = output;
		}

		public final ItemStack INPUT;
		public final ItemStack OUTPUT;
	}
	
	public void addSmelting(ItemStack input, ItemStack output)
    {
        RECIPES.add(new Recipe(input, output));
    }

	public ItemStack getSmeltingResult(ItemStack input)
    {
		for(Recipe recipe : RECIPES) {
			if(recipe.INPUT.isItemEqual(input)) {
				return recipe.OUTPUT.copy();
			}
		}
        return FurnaceRecipes.smelting().getSmeltingResult(input.itemID);
    }
	
	private final List<Recipe> RECIPES = new ArrayList<Recipe>();
	public static final FurnaceManager INSTANCE = new FurnaceManager();
}
