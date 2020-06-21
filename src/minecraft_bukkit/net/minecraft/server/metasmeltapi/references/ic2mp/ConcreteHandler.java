package net.minecraft.server.metasmeltapi.references.ic2mp;

import net.minecraft.server.mod_IC2Mp;
import net.minecraft.server.metasmeltapi.MetaSmeltAPI;

public class ConcreteHandler {

	public ConcreteHandler() {
		mod_IC2Mp.blockMachine = new BlockMachineMetadataFix(mod_IC2Mp.getBlockIdFor("blockMachine", 250));
		MetaSmeltAPI.overrideTileEntityRegister(TileEntityIronFurnaceMetadataFix.class, "Iron Furnace");
		MetaSmeltAPI.overrideTileEntityRegister(TileEntityElecFurnaceMetadataFix.class, "Electric Furnace");
		MetaSmeltAPI.overrideTileEntityRegister(TileEntityInductionMetadataFix.class, "Induction Furnace");
	}
}
