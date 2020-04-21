package net.minecraft.server.metasmeltapi;

import java.util.Map;

import net.minecraft.server.Block;
import net.minecraft.server.ItemStack;
import net.minecraft.server.TileEntity;

public class MetaSmeltAPI {
	
	private static boolean hasInit = false;

	public static void addRecipe(ItemStack input, ItemStack output) {
		if(!hasInit) {
			init();
		}
		FurnaceManager.INSTANCE.addSmelting(input, output);
	}
	
	@SuppressWarnings({ "unchecked", "rawtypes" })
	public static void init() {
		if(hasInit) {
			return;
		}
		hasInit = true;
		Utils.replaceBlock(new BlockFurnaceMetadataFix(Block.FURNACE, false), Block.FURNACE);
		Utils.replaceBlock(new BlockFurnaceMetadataFix(Block.BURNING_FURNACE, true), Block.BURNING_FURNACE);
		new Utils.EasyField<Map>(TileEntity.class, "a").get().put("Furnace", TileEntityFurnaceMetadataFix.class);
		new Utils.EasyField<Map>(TileEntity.class, "b").get().put(TileEntityFurnaceMetadataFix.class, "Furnace");
	}
}
