package soggy.fnaf.common.entity.animatronics.goal;

import net.minecraft.entity.EntityPose;
import net.minecraft.entity.ai.goal.Goal;
import net.minecraft.entity.player.PlayerEntity;
import soggy.fnaf.common.entity.animatronics.EntityAnimatronic;

import java.util.Random;

public class FindPlayerGoal extends Goal {
    private final EntityAnimatronic animatronic;
    private final Random random = new Random();
    private final int chance;
    private boolean isRunning = false;
    private int runningTime = 0;
    private float movementSpeed;
    private PlayerEntity target;
    public FindPlayerGoal(EntityAnimatronic animatronic, int chance) {
        this.animatronic = animatronic;
        this.movementSpeed = animatronic.getMovementSpeed();
        this.chance = chance;
    }
    @Override
    public boolean canStart() {
        return(random.nextInt(60) < chance);
    }

    @Override
    public void start() {
        PlayerEntity entity = animatronic.world.getClosestPlayer(animatronic, 80);
        target = entity;
        if(entity != null && !entity.isCreative()) {
           if(animatronic.roamDuringDay()) {
               wander(entity);
           } else if(entity.world.isNight()) {
               wander(entity);
           }
        }
    }

    public void wander(PlayerEntity entity) {
        animatronic.getNavigation().startMovingTo(entity, movementSpeed);
        isRunning = true;
        if(animatronic.canSee(entity)) {
            movementSpeed = movementSpeed + 0.1F;
        } else {
            movementSpeed = animatronic.getMovementSpeed();
        }
    }

    @Override
    public void tick() {
        super.tick();
        if(isRunning) {
            runningTime++;
        } else runningTime = 0;
    }

    @Override
    public void stop() {
        if(target.isCreative());
    }

    @Override
    public boolean shouldContinue() {
        return(random.nextInt(4) > 2 || !(runningTime > 10));
    }
}
