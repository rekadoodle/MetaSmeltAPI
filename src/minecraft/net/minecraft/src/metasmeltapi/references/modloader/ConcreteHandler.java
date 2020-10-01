package net.minecraft.src.metasmeltapi.references.modloader;


import java.util.LinkedList;

import net.minecraft.src.*;
import net.minecraft.src.metasmeltapi.HandlerModLoader;
import net.minecraft.src.metasmeltapi.Utils;

public class ConcreteHandler extends HandlerModLoader {

	public static final BaseMod BASEMOD = new mod_MetaSmeltAPI();
	
	public ConcreteHandler() {
		new Utils.EasyField<LinkedList<BaseMod>>(ModLoader.class, "modList").get().add(BASEMOD);
	}
	
	@Override
	public int burnTime(ItemStack itemstack) {
		return ModLoader.AddAllFuel(itemstack.itemID);
	}
}
