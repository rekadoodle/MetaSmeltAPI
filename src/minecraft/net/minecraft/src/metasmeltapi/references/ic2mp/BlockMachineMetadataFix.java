package net.minecraft.src.metasmeltapi.references.ic2mp;

import net.minecraft.src.ic2.common.*;
import net.minecraft.src.metasmeltapi.Utils;

public class BlockMachineMetadataFix extends BlockMachine {

	public BlockMachineMetadataFix(int id) {
		super(Utils.clearBlockID(id));
	}
	
	@Override
	public TileEntityBlock getBlockEntity(int metadata)
    {
        switch(metadata)
        {
        case 1:
            return new TileEntityIronFurnaceMetadataFix();
        case 2:
            return new TileEntityElecFurnaceMetadataFix();
        case 13:
            return new TileEntityInductionMetadataFix();
        default:
            return super.getBlockEntity(metadata);
        }
    }

}
