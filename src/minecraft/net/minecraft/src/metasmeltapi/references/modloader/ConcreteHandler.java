package net.minecraft.src.metasmeltapi.references.modloader;


import net.minecraft.src.*;
import net.minecraft.src.metasmeltapi.HandlerModLoader;
import net.minecraft.src.metasmeltapi.Utils;

public class ConcreteHandler extends HandlerModLoader {

	public ConcreteHandler() {
		if(nmsIsModLoaded("mod_HowManyItems")) {
			Utils.getHandler("hmi");
		}
	}
	
	private boolean nmsIsModLoaded(String modName) {
		return ModLoader.isModLoaded(modName) || ModLoader.isModLoaded("net.minecraft.src." + modName);
	}
	
	@Override
	public int burnTime(ItemStack itemstack) {
		return ModLoader.AddAllFuel(itemstack.itemID);
	}
}
