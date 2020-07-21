package soggy.fnaf.common.entity.animatronics;

import net.fabricmc.fabric.api.object.builder.v1.entity.FabricDefaultAttributeRegistry;
import net.fabricmc.fabric.api.object.builder.v1.entity.FabricEntityTypeBuilder;
import net.minecraft.entity.EntityDimensions;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.SpawnGroup;
import net.minecraft.util.Identifier;
import net.minecraft.util.registry.Registry;
import soggy.fnaf.FNAFUniverse;
import soggy.fnaf.common.entity.animatronics.fnaf1.*;

public class FNAFEntities {
    public static class FNAF1 {
        public static EntityType<FNAFBonnie> FNAF_BONNIE = Registry.register(
                Registry.ENTITY_TYPE,
                new Identifier(FNAFUniverse.MODID, "bonnie"),
                FabricEntityTypeBuilder.create(SpawnGroup.CREATURE, FNAFBonnie::new).trackable(64, 3).dimensions(EntityDimensions.fixed(1F, 1.8F)).build()
        );
        public static EntityType<FNAFChica> FNAF_CHICA = Registry.register(
                Registry.ENTITY_TYPE,
                new Identifier(FNAFUniverse.MODID, "chica"),
                FabricEntityTypeBuilder.create(SpawnGroup.CREATURE, FNAFChica::new).trackable(64, 3).dimensions(EntityDimensions.fixed(1F, 1.8F)).build()
        );
        public static EntityType<FNAFFreddy> FNAF_FREDDY = Registry.register(
                Registry.ENTITY_TYPE,
                new Identifier(FNAFUniverse.MODID, "freddy"),
                FabricEntityTypeBuilder.create(SpawnGroup.CREATURE, FNAFFreddy::new).trackable(64, 3).dimensions(EntityDimensions.fixed(1F, 1.8F)).build()
        );
        public static EntityType<FNAFFoxy> FNAF_FOXY = Registry.register(
                Registry.ENTITY_TYPE,
                new Identifier(FNAFUniverse.MODID, "foxy"),
                FabricEntityTypeBuilder.create(SpawnGroup.CREATURE, FNAFFoxy::new).trackable(64, 3).dimensions(EntityDimensions.fixed(1F, 1.8F)).build()
        );
        public static EntityType<FNAFGoldenFreddy> FNAF_GFREDDY = Registry.register(
                Registry.ENTITY_TYPE,
                new Identifier(FNAFUniverse.MODID, "golden_freddy"),
                FabricEntityTypeBuilder.create(SpawnGroup.CREATURE, FNAFGoldenFreddy::new).trackable(64, 3).dimensions(EntityDimensions.fixed(1F, 1.8F)).build()
        );
        public static void register() {
            FabricDefaultAttributeRegistry.register(FNAF_BONNIE, FNAFBonnie.create());
            FabricDefaultAttributeRegistry.register(FNAF_CHICA, FNAFChica.create());
            FabricDefaultAttributeRegistry.register(FNAF_FOXY, FNAFFoxy.create());
            FabricDefaultAttributeRegistry.register(FNAF_FREDDY, FNAFFreddy.create());
            FabricDefaultAttributeRegistry.register(FNAF_GFREDDY, FNAFGoldenFreddy.create());
        }
    }

    public static void register() {
        FNAF1.register();
    }
}
