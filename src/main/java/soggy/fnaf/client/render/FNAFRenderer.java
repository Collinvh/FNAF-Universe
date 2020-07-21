package soggy.fnaf.client.render;

import net.minecraft.client.render.entity.EntityRenderDispatcher;
import net.minecraft.entity.Entity;
import net.minecraft.entity.mob.MobEntity;
import net.minecraft.util.Identifier;
import net.soggymustache.bookworm.client.animation.part.BookwormModelBase;
import net.soggymustache.bookworm.client.render.BookwormEntityRenderer;
import soggy.fnaf.FNAFUniverse;

public class FNAFRenderer<T extends MobEntity> extends BookwormEntityRenderer {
    private String name;
    private String version;
    public FNAFRenderer(EntityRenderDispatcher renderManager, BookwormModelBase model, float f, String name, String version) {
        super(renderManager, model, f);
        this.name = name;
        this.version = version;
    }

    @Override
    public Identifier getTexture(Entity entity) {
        return new Identifier(FNAFUniverse.MODID,"textures/animatronics/" + version + "/" + name + ".png");
    }
}
