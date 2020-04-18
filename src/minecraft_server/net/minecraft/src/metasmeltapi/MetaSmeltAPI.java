package net.minecraft.src.metasmeltapi;

import java.util.Map;

import net.minecraft.src.Block;
import net.minecraft.src.ItemStack;
import net.minecraft.src.TileEntity;

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
		Utils.replaceBlock(new BlockFurnaceMetadataFix(Block.stoneOvenIdle, false), Block.stoneOvenIdle);
		Utils.replaceBlock(new BlockFurnaceMetadataFix(Block.stoneOvenActive, true), Block.stoneOvenActive);
		new Utils.EasyField<Map>(TileEntity.class, "classToNameMap").get().put(TileEntityFurnaceMetadataFix.class, "Furnace");
		if(Utils.classExists("ModLoader")) {
			Utils.getHandler("modloader");
		}
	}
}
