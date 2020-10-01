package net.minecraft.src.metasmeltapi.references.hmi;

import net.minecraft.src.mod_HowManyItems;

public class ConcreteHandler {
	
	@SuppressWarnings("deprecation")
	public ConcreteHandler() {
		mod_HowManyItems.addModTab(new TabSmeltingMetadata(net.minecraft.src.metasmeltapi.references.modloader.ConcreteHandler.BASEMOD));
	}
	
}
