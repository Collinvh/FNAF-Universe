package soggy.fnaf.common.item;

import net.minecraft.entity.EquipmentSlot;
import net.minecraft.item.Item;
import net.minecraft.item.SpawnEggItem;
import net.minecraft.util.Identifier;
import net.minecraft.util.Rarity;
import net.minecraft.util.registry.Registry;
import soggy.fnaf.FNAFUniverse;
import soggy.fnaf.common.entity.animatronics.FNAFEntities;
import soggy.fnaf.common.item.items.FlashlightItem;
import soggy.fnaf.common.item.items.FreddyMaskItem;
import soggy.fnaf.common.item.items.base.OverridenArmorMaterials;

import java.util.ArrayList;
import java.util.List;

public class FNAFItemInit {
    public static final List<Item> ITEMS = new ArrayList<>();
    public static final FreddyMaskItem MASK = new FreddyMaskItem(OverridenArmorMaterials.FREDDY, EquipmentSlot.HEAD, new Item.Settings().group(FNAFGroups.FnafItems).rarity(Rarity.EPIC));
    public static void init() {
        SpawnEggs.register();
        registerItem(new FlashlightItem());
        registerItem(MASK);
    }

    public static void registerItem(Item item) {
        ITEMS.add(item);
    }

    public static class SpawnEggs {
        public static final SpawnEggItem BONNIE = new SpawnEggItem(FNAFEntities.FNAF1.FNAF_BONNIE, 0x524480, 0xF2E9E1, new Item.Settings().group(FNAFGroups.FnafEntities));
        public static final SpawnEggItem CHICA = new SpawnEggItem(FNAFEntities.FNAF1.FNAF_CHICA, 0x8D7B36, 0x764420, new Item.Settings().group(FNAFGroups.FnafEntities));
        public static final SpawnEggItem FREDDY = new SpawnEggItem(FNAFEntities.FNAF1.FNAF_FREDDY, 0x632B0A, 0x0F1110, new Item.Settings().group(FNAFGroups.FnafEntities));
        public static final SpawnEggItem FOXY = new SpawnEggItem(FNAFEntities.FNAF1.FNAF_FOXY, 0x8D4D44, 0x66493A, new Item.Settings().group(FNAFGroups.FnafEntities));
        public static final SpawnEggItem GFREDDY = new SpawnEggItem(FNAFEntities.FNAF1.FNAF_GFREDDY, 0x524480, 0xF2E9E1, new Item.Settings().group(FNAFGroups.FnafEntities));

        public static void register() {
            registerItem(BONNIE, "spawnegg_bonnie");
            registerItem(CHICA, "spawnegg_chica");
            registerItem(FOXY, "spawnegg_foxy");
            registerItem(FREDDY, "spawnegg_freddy");
            registerItem(GFREDDY, "spawnegg_gfreddy");
        }
        public static void registerItem(Item item, String name) {
            ITEMS.add(item);
            Registry.register(Registry.ITEM, new Identifier(FNAFUniverse.MODID, name), item);
        }
    }
}
