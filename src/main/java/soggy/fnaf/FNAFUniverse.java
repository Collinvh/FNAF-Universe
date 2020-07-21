package soggy.fnaf;

import net.fabricmc.api.ModInitializer;
import soggy.fnaf.common.block.FNAFBlockInit;
import soggy.fnaf.common.entity.animatronics.FNAFEntities;
import soggy.fnaf.common.item.FNAFItemInit;

public class FNAFUniverse implements ModInitializer {
	public static final String MODID = "fnaf";
	//public static final DamageSource fnafScare = new DamageSource("animatronic").setDamageBypassesArmor();
	@Override
	public void onInitialize() {
		FNAFItemInit.init();
		FNAFBlockInit.init();
		FNAFEntities.register();
	}
}
