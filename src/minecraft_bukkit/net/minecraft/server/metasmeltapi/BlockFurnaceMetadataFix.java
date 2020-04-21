package net.minecraft.server.metasmeltapi;

import net.minecraft.server.*;

public class BlockFurnaceMetadataFix extends BlockFurnace {

	public BlockFurnaceMetadataFix(Block block, boolean isActive) {
		super(Utils.clearBlockID(block), isActive);
		this.c(3.5F);
		this.a(Block.h);
		this.a("furnace");
		this.g();
		if(isActive) {
			this.a(0.875F);
		}
	}
	
	@Override
	protected TileEntity a_()
    {
        return new TileEntityFurnaceMetadataFix();
    }

}
