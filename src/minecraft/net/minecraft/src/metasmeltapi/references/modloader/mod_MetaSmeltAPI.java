package net.minecraft.src.metasmeltapi.references.modloader;

import net.minecraft.src.BaseMod;
import net.minecraft.src.ModLoader;
import net.minecraft.src.metasmeltapi.Utils;

public class mod_MetaSmeltAPI extends BaseMod {
	
	public static final BaseMod BASEMOD = ConcreteHandler.basemod;

	@Override
	public String Version() {
		return null;
	}
	
	@Override
	public void ModsLoaded() {
		if(nmsIsModLoaded("mod_HowManyItems")) {
			Utils.getHandler("hmi");
		}
	}

	private boolean nmsIsModLoaded(String modName) {
		return ModLoader.isModLoaded(modName) || ModLoader.isModLoaded("net.minecraft.src." + modName);
	}

}
