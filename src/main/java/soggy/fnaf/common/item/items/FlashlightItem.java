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

public class FlashlightItem extends FNAFItem {
    private Map<PlayerEntity, BlockPos> light = Maps.newHashMap();
    private Map<PlayerEntity, Boolean> isOn = Maps.newHashMap();
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
        if (isOn.containsKey(entity) && world.isClient) {
            if (isOn.get(entity)) {
                light.put((PlayerEntity) entity, new BlockPos(0,0,0));
                if(light.get(entity) != entity.getBlockPos()) {
                    world.setBlockState(light.get(entity), Blocks.GRASS.getDefaultState());
                    world.setBlockState(light.get(entity), world.getBlockState(light.get(entity)));
                    light.remove(light.get(entity));
                    light.put((PlayerEntity) entity, entity.getBlockPos());
                    world.getLightingProvider().addLightSource(light.get(entity), 15);
                }
            } else {
                isOn.put((PlayerEntity) entity, false);
                removeSource((PlayerEntity) entity, light.get(entity), world, null, null, false);
            }
        }
    }

    @Override
    public TypedActionResult<ItemStack> use(World world, PlayerEntity user, Hand hand) {
        if(world.isClient) {
            if (!isOn.containsKey(user)) {
                user.getItemCooldownManager().set(this, 75);
                isOn.put(user, true);
            } else {
                isOn.remove(user);
                removeSource(user, light.get(user), world, null, null, false);
            }
        }
        return TypedActionResult.pass(user.getStackInHand(hand));
    }

    public void removeSource(PlayerEntity user, BlockPos oldPos, World world, BlockPos newPos, Direction dir, boolean newsource) {
        if(world.isClient) {
            if (oldPos != newPos) {
                BlockState defaultstate = world.getBlockState(oldPos);
                world.setBlockState(oldPos, Blocks.GRASS_BLOCK.getDefaultState());
                world.setBlockState(oldPos, defaultstate);
                if(newsource && world.getBlockState(newPos.offset(dir, 1)) == Blocks.AIR.getDefaultState()) {
                    light.remove(oldPos);
                    light.put(user, newPos);
                    world.getLightingProvider().addLightSource(newPos, 15);
                }
            }
        }
    }
}
