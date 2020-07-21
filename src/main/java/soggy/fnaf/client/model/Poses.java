package soggy.fnaf.client.model;

import net.minecraft.util.Identifier;
import net.soggymustache.bookworm.client.animation.lerp.Animation;
import net.soggymustache.bookworm.client.model.ModelCMF;
import soggy.fnaf.FNAFUniverse;

public class Poses {
    public static class FNAF1 {
        public static class Bonny {
            public static final ModelCMF WALK_1 = create("fnaf1", "bonnie", "walk_1");
            public static final ModelCMF WALK_2 = create("fnaf1", "bonnie", "walk_2");
            public static final ModelCMF WALK_3 = create("fnaf1", "bonnie", "walk_3");
            public static final ModelCMF WALK_4 = create("fnaf1", "bonnie", "walk_4");
            public static final Animation WALK = new Animation(Models.FNAF1.BONNIE_MODEL, WALK_1, WALK_2,WALK_3, WALK_4, Models.FNAF1.BONNIE_MODEL);
        }
        public static class Freddy {
            public static final ModelCMF WALK_1 = create("fnaf1", "freddy", "walk_1");
            public static final ModelCMF WALK_2 = create("fnaf1", "freddy", "walk_2");
            public static final ModelCMF WALK_3 = create("fnaf1", "freddy", "walk_3");
            public static final ModelCMF WALK_4 = create("fnaf1", "freddy", "walk_4");
            public static final Animation WALK = new Animation(Models.FNAF1.FREDDY_MODEL, WALK_1, WALK_2,WALK_3, WALK_4, Models.FNAF1.FREDDY_MODEL);
        }
        public static class Foxy {
            public static final ModelCMF WALK_1 = create("fnaf1", "foxy", "walk_1");
            public static final ModelCMF WALK_2 = create("fnaf1", "foxy", "walk_2");
            public static final ModelCMF WALK_3 = create("fnaf1", "foxy", "walk_3");
            public static final ModelCMF WALK_4 = create("fnaf1", "foxy", "walk_4");
            public static final Animation WALK = new Animation(Models.FNAF1.FOXY_MODEL, WALK_1, WALK_2,WALK_3, WALK_4, Models.FNAF1.FOXY_MODEL);
        }
        public static class Chica {
            public static final ModelCMF WALK_1 = create("fnaf1", "chica", "walk_1");
            public static final ModelCMF WALK_2 = create("fnaf1", "chica", "walk_2");
            public static final ModelCMF WALK_3 = create("fnaf1", "chica", "walk_3");
            public static final ModelCMF WALK_4 = create("fnaf1", "chica", "walk_4");
            public static final Animation WALK = new Animation(Models.FNAF1.CHICA_MODEL, WALK_1, WALK_2,WALK_3, WALK_4, Models.FNAF1.CHICA_MODEL);
        }
        public static class GFreddy {
        }
    }
    public static class FNAF2 {

    }
    public static class FNAF3 {

    }
    public static class FNAF4 {

    }

    public static ModelCMF create(String version, String name, String pose) {
        return new ModelCMF(new Identifier(FNAFUniverse.MODID, "models/animatronics/" + version + "/" + name + "/poses/" + pose + ".tbl"));
    }
}
