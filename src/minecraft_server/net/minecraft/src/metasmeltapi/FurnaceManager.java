package net.minecraft.src.metasmeltapi;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

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
        Map<Integer, ItemStack> idMap = RECIPES_EFFICIENT.get(input.itemID);
        if(idMap == null) {
        	idMap = new HashMap<Integer, ItemStack>();
        	RECIPES_EFFICIENT.put(input.itemID, idMap);
        }
        idMap.put(input.getItemDamage(), output);
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
	
	public ItemStack getSmeltingResultEfficient(ItemStack input) {
		if(RECIPES_EFFICIENT.containsKey(input.itemID)) {
			Map<Integer, ItemStack> metaMap = RECIPES_EFFICIENT.get(input.itemID);
			if(metaMap.containsKey(input.getItemDamage())) {
				return metaMap.get(input.getItemDamage());
			}
		}
		return FurnaceRecipes.smelting().getSmeltingResult(input.itemID);
	}
	
	private final List<Recipe> RECIPES = new ArrayList<Recipe>();
	private final Map<Integer, Map<Integer, ItemStack>> RECIPES_EFFICIENT = new HashMap<Integer, Map<Integer, ItemStack>>();
	public static final FurnaceManager INSTANCE = new FurnaceManager();
}
