package soggy.fnaf.common.block.blocks.base;

import net.minecraft.block.BlockWithEntity;
import net.minecraft.block.entity.BlockEntity;
import net.minecraft.item.BlockItem;
import net.minecraft.item.Item;
import net.minecraft.item.ItemGroup;
import net.minecraft.util.Identifier;
import net.minecraft.util.registry.Registry;
import net.minecraft.world.BlockView;
import soggy.fnaf.FNAFUniverse;

public class FNAFBlockWEntity extends BlockWithEntity {
    private final BlockEntity entity;
    public FNAFBlockWEntity(Settings settings, String name, ItemGroup group, BlockEntity entity) {
        super(settings);
        this.entity = entity;
        Registry.register(Registry.BLOCK, new Identifier(FNAFUniverse.MODID, name), this);
        Registry.register(Registry.ITEM, new Identifier(FNAFUniverse.MODID, name), new BlockItem(this, new Item.Settings().group(group)));
    }

    @Override
    public BlockEntity createBlockEntity(BlockView world) {
        return entity;
    }
}
