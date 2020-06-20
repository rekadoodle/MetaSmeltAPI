package net.minecraft.src.metasmeltapi.references.hmi;

import net.minecraft.src.mod_HowManyItems;
import net.minecraft.src.metasmeltapi.references.modloader.mod_MetaSmeltAPI;

public class ConcreteHandler {
	
	@SuppressWarnings("deprecation")
	public ConcreteHandler() {
		mod_HowManyItems.addModTab(new TabSmeltingMetadata(mod_MetaSmeltAPI.BASEMOD));
	}
	
}
