package soggy.fnaf.common.item.items;

import net.minecraft.entity.Entity;
import net.minecraft.entity.EquipmentSlot;
import net.minecraft.item.ArmorItem;
import net.minecraft.item.ArmorMaterial;
import net.minecraft.item.ArmorMaterials;
import net.minecraft.item.ItemStack;
import net.minecraft.util.Identifier;
import net.minecraft.util.registry.Registry;
import net.minecraft.world.World;
import soggy.fnaf.FNAFUniverse;

public class FreddyMaskItem extends ArmorItem {
    public FreddyMaskItem(ArmorMaterial material, EquipmentSlot slot, Settings settings) {
        super(material, slot, settings);
        Registry.register(Registry.ITEM, new Identifier(FNAFUniverse.MODID, "freddy_mask"), this);
    }

    @Override
    public void inventoryTick(ItemStack stack, World world, Entity entity, int slot, boolean selected) {

    }
}
