package soggy.fnaf.client.render.fnaf1;

import net.minecraft.client.render.VertexConsumerProvider;
import net.minecraft.client.render.entity.EntityRenderDispatcher;
import net.minecraft.client.util.math.MatrixStack;
import net.minecraft.entity.mob.MobEntity;
import soggy.fnaf.client.model.Models;
import soggy.fnaf.client.render.FNAFRenderer;

public class FNAFFreddy extends FNAFRenderer {
    public FNAFFreddy(EntityRenderDispatcher renderManager) {
        super(renderManager, Models.FNAF1.FREDDY_MODEL, 0.0F, "freddy", "fnaf1");
    }


    @Override
    public void render(MobEntity mobEntity, float f, float g, MatrixStack matrixStack, VertexConsumerProvider vertexConsumerProvider, int i) {
        matrixStack.scale(0.65F, 0.65F, 0.65F);
        matrixStack.translate(0, 0.70F, 0);
        super.render(mobEntity, f, g, matrixStack, vertexConsumerProvider, i);
    }

}
