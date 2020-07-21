package soggy.fnaf.common.block.blocks.base;

import net.minecraft.block.Block;
import net.minecraft.item.BlockItem;
import net.minecraft.item.Item;
import net.minecraft.item.ItemGroup;
import net.minecraft.util.Identifier;
import net.minecraft.util.registry.Registry;
import soggy.fnaf.FNAFUniverse;

public class FNAFBlock extends Block {
    public FNAFBlock(Settings settings, String name, ItemGroup group) {
        super(settings);
        Registry.register(Registry.BLOCK, new Identifier(FNAFUniverse.MODID, name), this);
        Registry.register(Registry.ITEM, new Identifier(FNAFUniverse.MODID, name), new BlockItem(this, new Item.Settings().group(group)));
    }
}
