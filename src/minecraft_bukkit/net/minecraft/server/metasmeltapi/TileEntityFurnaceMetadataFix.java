package net.minecraft.server.metasmeltapi;

import org.bukkit.craftbukkit.inventory.CraftItemStack;
import org.bukkit.event.inventory.FurnaceBurnEvent;
import org.bukkit.event.inventory.FurnaceSmeltEvent;

import net.minecraft.server.*;

public class TileEntityFurnaceMetadataFix extends TileEntityFurnace {
	
	public TileEntityFurnaceMetadataFix() {
		this(3);
	}
	
	public TileEntityFurnaceMetadataFix(int slotCount) {
		this.furnaceItemStacks = new ItemStack[slotCount];
		parentFurnaceItemStacksField.set(this, furnaceItemStacks);
	}
	
	@Override
	public int getSize()
    {
        return furnaceItemStacks.length;
    }

	@Override
    public ItemStack getItem(int i)
    {
        return furnaceItemStacks[i];
    }

	@Override
    public ItemStack splitStack(int i, int j)
    {
        checkRecipeOnTick = true;
        if(furnaceItemStacks[i] != null)
        {
            if(furnaceItemStacks[i].count <= j)
            {
                ItemStack itemstack = furnaceItemStacks[i];
                furnaceItemStacks[i] = null;
                return itemstack;
            }
            ItemStack itemstack1 = furnaceItemStacks[i].a(j);
            if(furnaceItemStacks[i].count == 0)
            {
                furnaceItemStacks[i] = null;
            }
            return itemstack1;
        } else
        {
            return null;
        }
    }
	
	@Override
	public void update()
    {
        checkRecipeOnTick = true;
        super.update();
    }

	@Override
    public void setItem(int i, ItemStack itemstack)
    {
        checkRecipeOnTick = true;
        furnaceItemStacks[i] = itemstack;
        if(itemstack != null && itemstack.count > getMaxStackSize())
        {
            itemstack.count = getMaxStackSize();
        }
    }
	
	@Override
	public void a(NBTTagCompound nbttagcompound) {
		super.a(nbttagcompound);
		furnaceItemStacks = parentFurnaceItemStacksField.get(this);
		ticksForCurrentFuel = getItemBurnTime(furnaceItemStacks[this.getFuelSlot()]);
		checkRecipeOnTick = true;
	}

	@Override
	public void b(NBTTagCompound nbttagcompound) {
		parentFurnaceItemStacksField.set(this, furnaceItemStacks);
		super.b(nbttagcompound);
	}

	@Override
	public void g_()
    {
        boolean lastTickBurning = isBurning();
        if(lastTickBurning)
        {
        	burnTime--;
        }
        if(!world.isStatic)
        {
            boolean inventoryChanged = false;
            if(checkRecipeOnTick || (!isBurning() && lastTickBurning)) {
            	checkRecipeOnTick = false;
            	if(cooking = canSmelt()) {
            		if(!isBurning())
                    {
            			final CraftItemStack bukkitfuel = new CraftItemStack(this.furnaceItemStacks[this.getFuelSlot()]);
                        final FurnaceBurnEvent furnaceBurnEvent = new FurnaceBurnEvent(this.world.getWorld().getBlockAt(this.x, this.y, this.z), bukkitfuel, this.getItemBurnTime(this.furnaceItemStacks[this.getFuelSlot()]));
                        this.world.getServer().getPluginManager().callEvent(furnaceBurnEvent);
                        if (furnaceBurnEvent.isCancelled()) {
                        	cooking = false;
                            return;
                        }
                        ticksForCurrentFuel = burnTime = furnaceBurnEvent.getBurnTime();
                        if(isBurning() && furnaceBurnEvent.isBurning())
                        {
                			ItemStack fuel = furnaceItemStacks[this.getFuelSlot()];
                        	inventoryChanged = true;
                            if(fuel != null)
                            {
                                if(fuel.getItem().i())
                                {
                                	furnaceItemStacks[this.getFuelSlot()] = new ItemStack(fuel.getItem().h());
                                } else
                                {
                                	furnaceItemStacks[this.getFuelSlot()].count--;
                                }
                                if(fuel.count == 0)
                                {
                                	furnaceItemStacks[this.getFuelSlot()] = null;
                                }
                            }
                        }
                    }
            	}
            }
            if(isBurning() && cooking) {
            	cookTime++;
                if(cookTime == requiredTime)
                {
                	cookTime = 0;
                    burn();
                    inventoryChanged = true;
                    cooking = false;
                }
            }
            else
            {
            	cookTime = 0;
            }
            if(lastTickBurning != isBurning())
            {
            	inventoryChanged = true;
            	this.updateBlockState(isBurning());
            }
            if(inventoryChanged)
            {
            	checkRecipeOnTick = true;
            	update();
            }
        }
    }

	protected boolean canSmelt()
    {
        if(this.inputSlotsEmpty())
        {
            return false;
        }
        ItemStack result = this.getResultItemStack();
        ItemStack output = furnaceItemStacks[this.getOutputSlot()];
        if(result == null)
        {
            return false;
        }
        if(output == null)
        {
            return true;
        }
        if(!output.doMaterialsMatch(result))
        {
            return false;
        }
        if(output.count < getMaxStackSize() && output.count < output.getMaxStackSize())
        {
            return true;
        }
        return output.count < result.getMaxStackSize();
    }
	
	protected int getItemBurnTime(ItemStack itemstack)
    {
        if(itemstack == null)
        {
            return 0;
        }
        int i = itemstack.getItem().id;
        if(i < 256 && Block.byId[i].material == Material.WOOD)
        {
            return 300;
        }
        if(i == Item.STICK.id)
        {
            return 100;
        }
        if(i == Item.COAL.id)
        {
            return 1600;
        }
        if(i == Item.LAVA_BUCKET.id)
        {
            return 20000;
        }
        if(i == Block.SAPLING.id)
        {
            return 100;
        } else
        {
            return MetaSmeltAPI.modloaderHandler.burnTime(itemstack);
        }
    }

	@Override
    public void burn()
    {
        if(!canSmelt())
        {
            return;
        }
        ItemStack result = this.getResultItemStack();
        ItemStack output = furnaceItemStacks[this.getOutputSlot()];
        
        final CraftItemStack bukkitsource = new CraftItemStack(furnaceItemStacks[0]);
        final CraftItemStack bukkitresult = new CraftItemStack(result.cloneItemStack());
        final FurnaceSmeltEvent furnaceSmeltEvent = new FurnaceSmeltEvent(this.world.getWorld().getBlockAt(this.x, this.y, this.z), bukkitsource, bukkitresult);
        this.world.getServer().getPluginManager().callEvent(furnaceSmeltEvent);
        if (furnaceSmeltEvent.isCancelled()) {
            return;
        }
        final org.bukkit.inventory.ItemStack oldResult = furnaceSmeltEvent.getResult();
        result = new ItemStack(oldResult.getTypeId(), oldResult.getAmount(), oldResult.getDurability());
        
        if(output == null)
        {
        	furnaceItemStacks[this.getOutputSlot()] = result.cloneItemStack();
        } 
        else if(output.doMaterialsMatch(result))
        {
        	furnaceItemStacks[this.getOutputSlot()].count += result.count;
        }
        this.reduceInputSlots(1);
    }
	
	protected void updateBlockState(boolean isActive) {
		BlockFurnace.a(isActive, world, x, y, z);
	}
	
	protected int getFuelSlot() {
		return this.furnaceItemStacks.length - 2;
	}
	
	protected int getOutputSlot() {
		return this.furnaceItemStacks.length - 1;
	}
	
	protected ItemStack getResultItemStack() {
		return FurnaceManager.INSTANCE.getSmeltingResult(furnaceItemStacks[0]);
	}
	
	protected boolean inputSlotsEmpty() {
		return furnaceItemStacks[0] == null;
	}
	
	protected void reduceInputSlots(int amount) {
		if(furnaceItemStacks[0].getItem().i())
        {
            furnaceItemStacks[0] = new ItemStack(furnaceItemStacks[0].getItem().h());
        } else
        {
            furnaceItemStacks[0].count -= amount;
        }
        if(furnaceItemStacks[0].count <= 0)
        {
            furnaceItemStacks[0] = null;
        }
	}
	
	protected ItemStack furnaceItemStacks[] = new ItemStack[3];
	protected boolean checkRecipeOnTick = false;
	protected boolean cooking = false;
	public int requiredTime = 200;
	private static Utils.EasyField<ItemStack[]> parentFurnaceItemStacksField = new Utils.EasyField<ItemStack[]>(TileEntityFurnace.class, "items");
}
