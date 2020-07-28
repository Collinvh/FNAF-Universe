package soggy.fnaf.common.item.items.base;

import net.minecraft.item.Item;
import net.minecraft.util.Identifier;
import net.minecraft.util.registry.Registry;
import soggy.fnaf.FNAFUniverse;

public class FNAFItem extends Item {
    public FNAFItem(Settings settings, String name) {
        super(settings);
        Registry.register(Registry.ITEM, new Identifier(FNAFUniverse.MODID, name), this);
    }


}
