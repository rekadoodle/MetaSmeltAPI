package net.minecraft.src.metasmeltapi;

import java.util.Map;

import net.minecraft.src.*;

//MetaSmeltAPI v2.0 SERVER

public class MetaSmeltAPI {
	
	private static boolean hasInit = false;
	public static HandlerModLoader modloaderHandler;

	public static void addRecipe(ItemStack input, ItemStack output) {
		if(!hasInit) {
			init();
		}
		FurnaceManager.INSTANCE.addSmelting(input, output);
	}
	
	public static void init() {
		if(hasInit) {
			return;
		}
		hasInit = true;
		Utils.replaceBlock(new BlockFurnaceMetadataFix(Block.stoneOvenIdle, false), Block.stoneOvenIdle);
		Utils.replaceBlock(new BlockFurnaceMetadataFix(Block.stoneOvenActive, true), Block.stoneOvenActive);
		new Utils.EasyField<Map<String, Class<? extends TileEntity>>>(TileEntity.class, "nameToClassMap", "a").get().put("Furnace", TileEntityFurnaceMetadataFix.class);
		new Utils.EasyField<Map<Class<? extends TileEntity>, String>>(TileEntity.class, "classToNameMap", "b").get().put(TileEntityFurnaceMetadataFix.class, "Furnace");
		if(Utils.nmsClassExists("ModLoader")) {
			modloaderHandler = (HandlerModLoader) Utils.getHandler("modloader");
		}
		else {
			modloaderHandler = new HandlerModLoader();
		}
	}
}
