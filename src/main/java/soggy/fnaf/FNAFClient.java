package soggy.fnaf;

import net.fabricmc.api.ClientModInitializer;
import net.fabricmc.fabric.api.client.rendereregistry.v1.EntityRendererRegistry;
import net.minecraft.client.MinecraftClient;
import soggy.fnaf.client.render.fnaf1.*;
import soggy.fnaf.common.entity.animatronics.FNAFEntities;

public class FNAFClient implements ClientModInitializer {
    public static MinecraftClient minecraft = MinecraftClient.getInstance();
    @Override
    public void onInitializeClient() {
        EntityRendererRegistry.INSTANCE.register(FNAFEntities.FNAF1.FNAF_BONNIE, (dispatcher, context) -> new FNAFBonnie(dispatcher));
        EntityRendererRegistry.INSTANCE.register(FNAFEntities.FNAF1.FNAF_CHICA, (dispatcher, context) -> new FNAFChica(dispatcher));
        EntityRendererRegistry.INSTANCE.register(FNAFEntities.FNAF1.FNAF_FOXY, (dispatcher, context) -> new FNAFFoxy(dispatcher));
        EntityRendererRegistry.INSTANCE.register(FNAFEntities.FNAF1.FNAF_FREDDY, (dispatcher, context) -> new FNAFFreddy(dispatcher));
        EntityRendererRegistry.INSTANCE.register(FNAFEntities.FNAF1.FNAF_GFREDDY, (dispatcher, context) -> new FNAFGoldenFreddy(dispatcher));
    }
}
