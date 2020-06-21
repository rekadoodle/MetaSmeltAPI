package net.minecraft.server.metasmeltapi.references.modloader;

import java.util.LinkedList;

import net.minecraft.server.*;
import net.minecraft.server.metasmeltapi.HandlerModLoader;
import net.minecraft.server.metasmeltapi.Utils;

public class ConcreteHandler extends HandlerModLoader {

	public ConcreteHandler() {
		new Utils.EasyField<LinkedList<BaseMod>>(ModLoader.class, "modList").get().add(new mod_MetaSmeltAPI());
	}
	
	@Override
	public int burnTime(ItemStack itemstack) {
		return ModLoader.AddAllFuel(itemstack.id);
	}
}
