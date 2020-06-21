package net.minecraft.src.metasmeltapi.references.ic2mp;

import net.minecraft.src.ItemStack;
import net.minecraft.src.ic2.common.TileEntityInduction;
import net.minecraft.src.metasmeltapi.MetaSmeltAPI;

public class TileEntityInductionMetadataFix extends TileEntityInduction {

	@Override
	public ItemStack getResultFor(ItemStack itemstack)
    {
        return MetaSmeltAPI.getResultEfficient(itemstack);
    }
}
