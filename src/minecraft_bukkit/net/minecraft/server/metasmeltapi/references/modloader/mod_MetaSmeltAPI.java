package net.minecraft.server.metasmeltapi.references.modloader;

import net.minecraft.server.BaseMod;
import net.minecraft.server.ModLoader;
import net.minecraft.server.metasmeltapi.Utils;

public class mod_MetaSmeltAPI extends BaseMod {

	@Override
	public String Version() {
		return null;
	}
	
	@Override
	public void ModsLoaded() {
		if(nmsIsModLoaded("mod_IC2Mp")) {
			Utils.getHandler("ic2mp");
		}
	}

	private boolean nmsIsModLoaded(String modName) {
		return ModLoader.isModLoaded(modName) || ModLoader.isModLoaded("net.minecraft.server." + modName);
	}

}
