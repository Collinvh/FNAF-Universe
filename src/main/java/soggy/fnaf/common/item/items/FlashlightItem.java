package soggy.fnaf.common.item.items;

import com.google.common.collect.Maps;
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

import java.util.Map;
import java.util.Objects;

public class FlashlightItem extends FNAFItem {
    private Map<PlayerEntity, Boolean> on = Maps.newHashMap();
    private Map<PlayerEntity, BlockPos> pos = Maps.newHashMap();
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
        if (on.containsKey(entity) && world.isClient) {
            if (selected) {
                Direction newDir = Direction.fromRotation(entity.yaw);
                if (!pos.containsKey(entity)) {
                    if (world.getBlockState(entity.getBlockPos().offset(newDir, 1)) == Blocks.AIR.getDefaultState())
                        pos.put((PlayerEntity) entity, entity.getBlockPos().offset(newDir, 2));
                }
                if (pos.get(entity) != entity.getBlockPos().offset(newDir, 2)) {
                    BlockPos oldPos = pos.get(entity);
                    BlockState defaultState = world.getBlockState(oldPos);
                    world.setBlockState(oldPos, Blocks.GRASS_BLOCK.getDefaultState());
                    world.setBlockState(oldPos, defaultState);
                    addSource(world, entity.getBlockPos().offset(newDir, 2), 15);
                    pos.remove(entity);
                    pos.put((PlayerEntity) entity, entity.getBlockPos().offset(newDir, 2));
                }
            } else {
                on.remove(entity);
                BlockPos oldPos = pos.get(entity);
                BlockState defaultState = world.getBlockState(oldPos);
                world.setBlockState(oldPos, Blocks.GRASS_BLOCK.getDefaultState());
                world.setBlockState(oldPos, defaultState);
                pos.remove(entity);
            }
        }
    }

    @Override
    public TypedActionResult<ItemStack> use(World world, PlayerEntity user, Hand hand) {
        if (world.isClient) {
            if (!on.containsKey(user)) {
                user.getItemCooldownManager().set(this, 75);
                on.put(user, true);
            } else {
                on.remove(user);
                BlockPos oldPos = pos.get(user);
                BlockState defaultState = world.getBlockState(oldPos);
                world.setBlockState(oldPos, Blocks.GRASS_BLOCK.getDefaultState());
                world.setBlockState(oldPos, defaultState);
                pos.remove(user);
            }
        }
        return TypedActionResult.pass(user.getStackInHand(hand));
    }

    public void addSource(World world, BlockPos pos, int lightLevel) {
        world.getLightingProvider().addLightSource(pos, lightLevel);
    }
}
