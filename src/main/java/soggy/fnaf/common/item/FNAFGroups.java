package soggy.fnaf.common.item;

import net.fabricmc.fabric.api.client.itemgroup.FabricItemGroupBuilder;
import net.minecraft.block.Blocks;
import net.minecraft.item.ItemGroup;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;
import net.minecraft.util.Identifier;

import static soggy.fnaf.FNAFUniverse.MODID;

public class FNAFGroups {
    public static final ItemGroup FnafMain = FabricItemGroupBuilder.create(
            new Identifier(MODID, "main"))
            .icon(() -> new ItemStack(Items.ACACIA_LEAVES.asItem())).build();
    public static final ItemGroup FnafItems = FabricItemGroupBuilder.create(
            new Identifier(MODID, "items"))
            .icon(() -> new ItemStack(Items.ACACIA_BOAT.asItem())).build();
    public static final ItemGroup FnafEntities = FabricItemGroupBuilder.create(
            new Identifier(MODID, "entities"))
            .icon(() -> new ItemStack(Items.COD_SPAWN_EGG.asItem())).build();
    public static final ItemGroup FnafBlocks = FabricItemGroupBuilder.create(
            new Identifier(MODID, "blocks"))
            .icon(() -> new ItemStack(Blocks.ACACIA_BUTTON.asItem())).build();
}
