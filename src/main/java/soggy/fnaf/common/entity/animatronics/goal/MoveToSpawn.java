package soggy.fnaf.common.entity.animatronics.goal;

import net.minecraft.entity.ai.goal.Goal;
import net.minecraft.util.math.Vec3d;
import soggy.fnaf.common.entity.animatronics.EntityAnimatronic;

import java.util.Random;

public class MoveToSpawn extends Goal {
    private final EntityAnimatronic animatronic;
    private final float movementSpeed;
    private Random random = new Random();
    public MoveToSpawn(EntityAnimatronic animatronic, float movementSpeed) {
        this.animatronic = animatronic;
        this.movementSpeed = movementSpeed;
    }
    @Override
    public boolean canStart() {
        if(animatronic.roamDuringDay() || animatronic.world.isNight()) {
            return (random.nextBoolean() && random.nextInt(5) > 2);
        } else {
            return false;
        }
    }

    @Override
    public void tick() {
        if(animatronic.isSpawnPosSet()) {
            animatronic.getNavigation().startMovingTo(animatronic.spawnPosX, animatronic.spawnPosY, animatronic.spawnPosZ, 0.8F);
        }
    }
}
