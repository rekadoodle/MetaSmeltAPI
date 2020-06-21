package net.minecraft.server.metasmeltapi.references.ic2mp;

import net.minecraft.server.ItemStack;
import net.minecraft.server.ic2.common.TileEntityElecFurnace;
import net.minecraft.server.metasmeltapi.MetaSmeltAPI;

public class TileEntityElecFurnaceMetadataFix extends TileEntityElecFurnace {

	@Override
	public ItemStack getResultFor(ItemStack itemstack)
    {
        return MetaSmeltAPI.getResultEfficient(itemstack);
    }
}
