package net.minecraft.src.metasmeltapi.references.ic2mp;

import net.minecraft.src.ItemStack;
import net.minecraft.src.ic2.common.TileEntityElecFurnace;
import net.minecraft.src.metasmeltapi.MetaSmeltAPI;

public class TileEntityElecFurnaceMetadataFix extends TileEntityElecFurnace {

	@Override
	public ItemStack getResultFor(ItemStack itemstack)
    {
        return MetaSmeltAPI.getResultEfficient(itemstack);
    }
}
