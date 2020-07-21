package soggy.fnaf.common.block;

import com.mojang.datafixers.types.Type;
import net.minecraft.block.Block;
import net.minecraft.block.entity.BlockEntityType;
import net.minecraft.util.Identifier;
import net.minecraft.util.registry.Registry;
import soggy.fnaf.FNAFUniverse;
import soggy.fnaf.common.block.blocks.base.FNAFBlockEntity;

import java.util.Set;
import java.util.function.Supplier;

public class FNAFBlockEntityType<T extends FNAFBlockEntity> extends BlockEntityType {
    //public static final BlockEntityType<? extends TestBlockEntity> TEST;

    static {
//        TEST = create("test", TestBlockEntity::new, FNAFBlockInit.TEST);
    }

    static <T extends FNAFBlockEntity> BlockEntityType<? extends T> create(String name, Supplier<? extends T> supplier, Block... blocks) {
        return Registry.register(Registry.BLOCK_ENTITY_TYPE, new Identifier(FNAFUniverse.MODID, name), BlockEntityType.Builder.create(supplier, blocks).build(null));
    }

    public FNAFBlockEntityType(Supplier supplier, Set<Block> set, Type type) {
        super(supplier, set, type);
    }
}
