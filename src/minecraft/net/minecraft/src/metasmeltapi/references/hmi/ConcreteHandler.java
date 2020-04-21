package net.minecraft.src.metasmeltapi.references.hmi;

import net.minecraft.src.BaseMod;
import net.minecraft.src.mod_HowManyItems;

public class ConcreteHandler {
	
	@SuppressWarnings("deprecation")
	public ConcreteHandler() {
		
		mod_HowManyItems.addModTab(new TabSmeltingMetadata(new BaseMod() {

			@Override
			public String Version() {
				// TODO Auto-generated method stub
				return null;
			}
			
		}));
	}
	
}
