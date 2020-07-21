package soggy.fnaf.common.entity.animatronics.fnaf1;

import net.minecraft.entity.EntityType;
import net.minecraft.entity.ai.control.MoveControl;
import net.minecraft.entity.attribute.DefaultAttributeContainer;
import net.minecraft.entity.attribute.EntityAttributes;
import net.minecraft.entity.mob.HostileEntity;
import net.minecraft.entity.mob.MobEntity;
import net.minecraft.entity.mob.MobEntityWithAi;
import net.minecraft.sound.SoundEvent;
import net.minecraft.sound.SoundEvents;
import net.minecraft.world.World;
import net.soggymustache.bookworm.client.animation.lerp.AnimationHandler;
import net.soggymustache.bookworm.util.BookwormUtils;
import soggy.fnaf.client.model.Poses;
import soggy.fnaf.common.entity.animatronics.EntityAnimatronic;

public class FNAFBonnie extends EntityAnimatronic {
    public static int walking = 0, idle = 1;
    public AnimationHandler animator = new AnimationHandler();
    public FNAFBonnie(EntityType<? extends MobEntityWithAi> entityType, World world) {
        super(entityType, world);
        this.setMovementSpeed(0.7F);
        if(world.isClient) {
            animator.addAnimation(walking, Poses.FNAF1.Bonny.WALK);
        }
    }

    public static DefaultAttributeContainer.Builder create() {
        return MobEntity.createMobAttributes().add(EntityAttributes.GENERIC_MOVEMENT_SPEED, 0.23000000417232513D).add(EntityAttributes.GENERIC_ATTACK_DAMAGE, 50D);
    }


    @Override
    public void tick() {
        if(this.world.isClient) animator.onEntityUpdate(this);
        super.tick();
        if(this.world.isClient)
            if (BookwormUtils.isEntityMoving(this)) if (!animator.isPlaying(walking)) animator.play(walking);
            else if (animator.isPlaying(walking)) animator.stopAnimation();
    }

    @Override
    public boolean jumpScares() {
        return true;
    }

    @Override
    public boolean roamDuringDay() {
        return false;
    }

    @Override
    public SoundEvent jumpscareSound() {
        return SoundEvents.WEATHER_RAIN;
    }

    @Override
    public float setAnimatronicAttackDamage() {
        return 15;
    }
}
