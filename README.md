# MetaSmeltAPI
Use this to add furnace recipes with metadata to Minecraft b1.7.3

## Development Setup
1. Setup an MCP workspace.
2. Extract the contents of the repo into the root of your MCP workspace folder.
3. Delete the references package or a number of its subpackages if you don't have each dependency setup.

## Usage
```java
import net.minecraft.src.metasmeltapi.MetaSmeltAPI;

MetaSmeltAPI.addRecipe(new ItemStack(Block.cloth, 1, 3), new ItemStack(Block.doorWood));
```
