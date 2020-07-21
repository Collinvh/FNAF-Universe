package soggy.fnaf.client.model;

import net.minecraft.util.Identifier;
import net.soggymustache.bookworm.client.model.ModelCMF;
import soggy.fnaf.FNAFUniverse;

public class Models {
    public static class FNAF1 {
        public static final ModelCMF BONNIE_MODEL = create("fnaf1", "bonnie");
        public static final ModelCMF CHICA_MODEL = create("fnaf1", "chica");
        public static final ModelCMF FREDDY_MODEL = create("fnaf1", "freddy");
        public static final ModelCMF FOXY_MODEL = create("fnaf1", "foxy");
        public static final ModelCMF GOLDEN_FREDDY = create("fnaf1", "golden_freddy");
    }
    public static class FNAF2 {

    }
    public static class FNAF3 {

    }
    public static class FNAF4 {

    }

    public static ModelCMF create(String version, String name) {
        return new ModelCMF(new Identifier(FNAFUniverse.MODID, "models/animatronics/" + version + "/" + name + "/" + name + ".tbl"));
    }
}
