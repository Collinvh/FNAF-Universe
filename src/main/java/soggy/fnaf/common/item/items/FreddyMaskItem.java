package soggy.fnaf.common.item.items;

import net.minecraft.entity.Entity;
import net.minecraft.entity.EquipmentSlot;
import net.minecraft.entity.mob.MobEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ArmorItem;
import net.minecraft.item.ArmorMaterial;
import net.minecraft.item.ArmorMaterials;
import net.minecraft.item.ItemStack;
import net.minecraft.util.Hand;
import net.minecraft.util.Identifier;
import net.minecraft.util.TypedActionResult;
import net.minecraft.util.registry.Registry;
import net.minecraft.world.World;
import soggy.fnaf.FNAFUniverse;
import soggy.fnaf.common.item.FNAFItemInit;

public class FreddyMaskItem extends ArmorItem {
    public FreddyMaskItem(ArmorMaterial material, EquipmentSlot slot, Settings settings) {
        super(material, slot, settings);
        Registry.register(Registry.ITEM, new Identifier(FNAFUniverse.MODID, "freddy_mask"), this);
    }

    @Override
    public void inventoryTick(ItemStack stack, World world, Entity entity, int slot, boolean selected) {
        /*PlayerEntity user = (PlayerEntity) entity;
        ItemStack itemStack = user.getStackInHand(user.getActiveHand());
        EquipmentSlot equipmentSlot = MobEntity.getPreferredEquipmentSlot(itemStack);
        ItemStack itemStack2 = user.getEquippedStack(equipmentSlot);
        if(user.getEquippedStack(equipmentSlot) == itemStack) {
            //user.
        }*/
        super.inventoryTick(stack, world, entity, slot, selected);
    }
}
