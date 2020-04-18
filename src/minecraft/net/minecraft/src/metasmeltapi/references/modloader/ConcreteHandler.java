package net.minecraft.src.metasmeltapi.references.modloader;

import net.minecraft.src.ModLoader;
import net.minecraft.src.metasmeltapi.Utils;

public class ConcreteHandler {

	public ConcreteHandler() {
		System.out.println("Ping!");
		if(ModLoader.isModLoaded("mod_HowManyItems")) {
			Utils.getHandler("hmi");
		}
	}
}
