package soggy.fnaf.client.render.fnaf1;

import net.minecraft.client.render.VertexConsumerProvider;
import net.minecraft.client.render.entity.EntityRenderDispatcher;
import net.minecraft.client.util.math.MatrixStack;
import net.minecraft.entity.mob.MobEntity;
import soggy.fnaf.client.model.Models;
import soggy.fnaf.client.render.FNAFRenderer;

public class FNAFFoxy extends FNAFRenderer {
    public FNAFFoxy(EntityRenderDispatcher renderManager) {
        super(renderManager, Models.FNAF1.FOXY_MODEL, 0.0F, "foxy", "fnaf1");
    }


    @Override
    public void render(MobEntity mobEntity, float f, float g, MatrixStack matrixStack, VertexConsumerProvider vertexConsumerProvider, int i) {
        matrixStack.scale(0.65F, 0.65F, 0.65F);
        matrixStack.translate(0, 0.68F, 0);
        super.render(mobEntity, f, g, matrixStack, vertexConsumerProvider, i);
    }

}
