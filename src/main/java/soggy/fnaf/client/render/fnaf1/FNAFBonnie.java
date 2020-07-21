package soggy.fnaf.client.render.fnaf1;

import com.mojang.blaze3d.platform.GlStateManager;
import net.minecraft.client.render.Frustum;
import net.minecraft.client.render.VertexConsumerProvider;
import net.minecraft.client.render.entity.EntityRenderDispatcher;
import net.minecraft.client.util.math.MatrixStack;
import net.minecraft.entity.Entity;
import net.minecraft.entity.mob.MobEntity;
import net.soggymustache.bookworm.client.model.CMFAnimator;
import net.soggymustache.bookworm.client.model.ModelCMF;
import soggy.fnaf.client.model.Models;
import soggy.fnaf.client.render.FNAFRenderer;

public class FNAFBonnie extends FNAFRenderer {
    public FNAFBonnie(EntityRenderDispatcher renderManager) {
        super(renderManager, Models.FNAF1.BONNIE_MODEL, 0.0F, "bonnie", "fnaf1");
        Models.FNAF1.BONNIE_MODEL.setAnimator(Animator::new);
    }


    @Override
    public void render(MobEntity mobEntity, float f, float g, MatrixStack matrixStack, VertexConsumerProvider vertexConsumerProvider, int i) {
        matrixStack.scale(0.75F, 0.75F, 0.75F);
        matrixStack.translate(0, 0.5F, 0);
        super.render(mobEntity, f, g, matrixStack, vertexConsumerProvider, i);
    }

    public static class Animator extends CMFAnimator<soggy.fnaf.common.entity.animatronics.fnaf1.FNAFBonnie> {

        public Animator(ModelCMF model) {
            super(model);
        }

        @Override
        public void setRotationAngles(float limbSwing, float limbSwingAmount, float ageInTicks, float netHeadYaw, float headPitch, float scaleFactor, soggy.fnaf.common.entity.animatronics.fnaf1.FNAFBonnie entityIn) {
            super.setRotationAngles(limbSwing, limbSwingAmount, ageInTicks, netHeadYaw, headPitch, scaleFactor, entityIn);
            getModel().reset();
            entityIn.animator.updateModel(getModel());
        }
    }

}
