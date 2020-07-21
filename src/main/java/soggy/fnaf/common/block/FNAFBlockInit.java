package soggy.fnaf.common.block;

import net.minecraft.block.AbstractBlock;
import net.minecraft.block.Block;
import net.minecraft.block.Material;
import soggy.fnaf.common.block.blocks.BeeBlock;
import soggy.fnaf.common.block.blocks.base.FNAFBlock;
import soggy.fnaf.common.item.FNAFGroups;

import java.util.ArrayList;
import java.util.List;

public class FNAFBlockInit {
    public static final List<Block> BLOCKS = new ArrayList<>();
    public static void init() {
        registerBlock(new FNAFBlock(AbstractBlock.Settings.of(Material.AGGREGATE).strength(5), "test", FNAFGroups.FnafBlocks));
        registerBlock(new BeeBlock(AbstractBlock.Settings.of(Material.AGGREGATE).strength(5), "beez", FNAFGroups.FnafBlocks));
    }

    public static void registerBlock(Block block) {
        BLOCKS.add(block);
    }
}
