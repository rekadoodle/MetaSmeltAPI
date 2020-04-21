package net.minecraft.src.metasmeltapi.references.modloader;


import net.minecraft.src.*;
import net.minecraft.src.metasmeltapi.HandlerModLoader;
import net.minecraft.src.metasmeltapi.Utils;

public class ConcreteHandler extends HandlerModLoader {
	
	@Override
	public int burnTime(ItemStack itemstack) {
		return ModLoader.AddAllFuel(itemstack.itemID);
	}
}
