package net.minecraft.server.metasmeltapi.references.modloader;

import net.minecraft.server.*;
import net.minecraft.server.metasmeltapi.HandlerModLoader;

public class ConcreteHandler extends HandlerModLoader {

	@Override
	public int burnTime(ItemStack itemstack) {
		return ModLoader.AddAllFuel(itemstack.id);
	}
}
