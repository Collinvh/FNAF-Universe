package soggy.fnaf.common.item.items;

import net.minecraft.block.AirBlock;
import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.block.Blocks;
import net.minecraft.entity.Entity;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.attribute.EntityAttribute;
import net.minecraft.entity.attribute.EntityAttributes;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.server.network.ServerPlayerEntity;
import net.minecraft.util.Hand;
import net.minecraft.util.Rarity;
import net.minecraft.util.TypedActionResult;
import net.minecraft.util.hit.BlockHitResult;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.ChunkPos;
import net.minecraft.util.math.Direction;
import net.minecraft.util.math.Vec3d;
import net.minecraft.world.LightType;
import net.minecraft.world.RayTraceContext;
import net.minecraft.world.World;
import soggy.fnaf.common.item.FNAFGroups;
import soggy.fnaf.common.item.items.base.FNAFItem;

public class FlashlightItem extends FNAFItem {
    private BlockPos lightPos;
    private boolean on = false;
    private int cooldown;
    public FlashlightItem() {
        super(new Item.Settings().maxCount(1).rarity(Rarity.UNCOMMON).maxDamage(120).group(FNAFGroups.FnafItems), "flashlight");
    }

    @Override
    public boolean canMine(BlockState state, World world, BlockPos pos, PlayerEntity miner) {
        return false;
    }


    @Override
    public void inventoryTick(ItemStack stack, World world, Entity entity, int slot, boolean selected) {
        if (on && world.isClient) {
            if(selected) {
                Direction newDir = Direction.fromRotation(entity.yaw);
                if (lightPos == null)  {
                    if(world.getBlockState(entity.getBlockPos().offset(newDir, 1)) == Blocks.AIR.getDefaultState()) lightPos = entity.getBlockPos().offset(newDir, 2);
                }
                if(lightPos != entity.getBlockPos().offset(newDir)) {
                    updateSource(lightPos, world, entity.getBlockPos(), newDir, true);
                }
            } else {
                on = false;
                updateSource(lightPos, world, null, null, false);
            }
        }
    }

    @Override
    public TypedActionResult<ItemStack> use(World world, PlayerEntity user, Hand hand) {
        if(world.isClient) {
            if (!on) {
                user.getItemCooldownManager().set(this, 75);
                on = true;
            } else {
                on = false;
                updateSource(lightPos, world, null, null, false);
            }
        }
        return TypedActionResult.pass(user.getStackInHand(hand));
    }

    public void updateSource(BlockPos oldPos, World world, BlockPos newPos, Direction dir, boolean newsource) {
        if(world.isClient) {
            if (oldPos != newPos) {
                BlockState defaultstate = world.getBlockState(oldPos);
                world.setBlockState(oldPos, Blocks.GRASS_BLOCK.getDefaultState());
                world.setBlockState(oldPos, defaultstate);
                if(newsource && world.getBlockState(newPos.offset(dir, 1)) == Blocks.AIR.getDefaultState()) {
                    lightPos = newPos.offset(dir, 2);
                    world.getLightingProvider().addLightSource(lightPos, 15);
                }
            }
        }
    }
}
