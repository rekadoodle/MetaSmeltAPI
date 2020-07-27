package net.minecraft.server.metasmeltapi;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import net.minecraft.server.*;

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
        Map<Integer, ItemStack> idMap = RECIPES_EFFICIENT.get(input.id);
        if(idMap == null) {
        	idMap = new HashMap<Integer, ItemStack>();
        	RECIPES_EFFICIENT.put(input.id, idMap);
        }
        idMap.put(input.damage, output);
    }

	public ItemStack getSmeltingResult(ItemStack input)
    {
		for(Recipe recipe : RECIPES) {
			if(recipe.INPUT.doMaterialsMatch(input)) {
				return recipe.OUTPUT.cloneItemStack();
			}
		}
        return FurnaceRecipes.getInstance().a(input.id);
    }
	
	public ItemStack getSmeltingResultEfficient(ItemStack input) {
		if(RECIPES_EFFICIENT.containsKey(input.id)) {
			Map<Integer, ItemStack> metaMap = RECIPES_EFFICIENT.get(input.id);
			if(metaMap.containsKey(input.damage)) {
				return metaMap.get(input.damage);
			}
		}
		return FurnaceRecipes.getInstance().a(input.id);
	}
	
	private final List<Recipe> RECIPES = new ArrayList<Recipe>();
	private final Map<Integer, Map<Integer, ItemStack>> RECIPES_EFFICIENT = new HashMap<Integer, Map<Integer, ItemStack>>();
	public static final FurnaceManager INSTANCE = new FurnaceManager();
}
