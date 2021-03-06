package net.minecraft.server.metasmeltapi;

import java.util.Map;

import net.minecraft.server.*;

// MetaSmeltAPI v2.1 BUKKIT

public class MetaSmeltAPI {
	
	private static boolean hasInit = false;
	public static HandlerModLoader modloaderHandler;

	public static void addRecipe(ItemStack input, ItemStack output) {
		if(!hasInit) {
			init();
		}
		FurnaceManager.INSTANCE.addSmelting(input, output);
	}
	
	public static ItemStack getResult(ItemStack input) {
		return FurnaceManager.INSTANCE.getSmeltingResult(input);
	}
	
	public static ItemStack getResultEfficient(ItemStack input) {
		return FurnaceManager.INSTANCE.getSmeltingResultEfficient(input);
	}
	
	public static void overrideTileEntityRegister(Class<? extends TileEntity> tileEntityClass, String id) {
		new Utils.EasyField<Map<String, Class<? extends TileEntity>>>(TileEntity.class, "a").get().put(id, tileEntityClass);
		new Utils.EasyField<Map<Class<? extends TileEntity>, String>>(TileEntity.class, "b").get().put(tileEntityClass, id);
	}
	
	public static void init() {
		if(hasInit) {
			return;
		}
		hasInit = true;
		Utils.replaceBlock(new BlockFurnaceMetadataFix(Block.FURNACE, false), Block.FURNACE);
		Utils.replaceBlock(new BlockFurnaceMetadataFix(Block.BURNING_FURNACE, true), Block.BURNING_FURNACE);
		overrideTileEntityRegister(TileEntityFurnaceMetadataFix.class, "Furnace");
		if(Utils.nmsClassExists("ModLoader")) {
			modloaderHandler = (HandlerModLoader) Utils.getHandler("modloader");
		}
		else {
			modloaderHandler = new HandlerModLoader();
		}
	}
}
