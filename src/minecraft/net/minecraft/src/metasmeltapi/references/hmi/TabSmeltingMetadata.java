package net.minecraft.src.metasmeltapi.references.hmi;

import java.util.List;

import net.minecraft.src.hmi.tabs.TabSmelting;
import net.minecraft.src.*;
import net.minecraft.src.metasmeltapi.FurnaceManager;
import net.minecraft.src.metasmeltapi.Utils;

public class TabSmeltingMetadata extends TabSmelting {
	
	private final List<FurnaceManager.Recipe> recipeList = new Utils.EasyField<List<FurnaceManager.Recipe>>(FurnaceManager.class, "recipes").get(FurnaceManager.INSTANCE);

	public TabSmeltingMetadata(BaseMod tabCreator) {
		super(tabCreator);
	}

	@Override
	public void updateRecipesWithoutClear(ItemStack filter, Boolean getUses) {
		for(FurnaceManager.Recipe recipe : recipeList) {
			if(filter == null || (!getUses && recipe.OUTPUT.isItemEqual(filter) || (getUses && recipe.INPUT.isItemEqual(filter)))) {
				this.recipes.add(new ItemStack[] {recipe.OUTPUT, recipe.INPUT});
			}
		}
		super.updateRecipesWithoutClear(filter, getUses);
	}
}
