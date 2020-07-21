package soggy.fnaf.common.block.blocks;

import net.minecraft.block.BlockState;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.passive.BeeEntity;
import net.minecraft.item.ItemGroup;
import net.minecraft.item.ItemStack;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import soggy.fnaf.common.block.blocks.base.FNAFBlock;

public class BeeBlock extends FNAFBlock {
    private int attempts = 5;
    public BeeBlock(Settings settings, String name, ItemGroup group) {
        super(settings, name, group);
    }

    @Override
    public void onPlaced(World world, BlockPos pos, BlockState state, LivingEntity placer, ItemStack itemStack) {
        BeeEntity beee = new BeeEntity(EntityType.BEE, world);
        beee.setPos(pos.getX(), pos.getY() + 1, pos.getZ());
        while(attempts > 0) {
            world.spawnEntity(beee);
            attempts--;
        }
    }
}
