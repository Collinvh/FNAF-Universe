package soggy.fnaf.common.entity.animatronics.fnaf1;

import net.minecraft.entity.EntityType;
import net.minecraft.entity.attribute.DefaultAttributeContainer;
import net.minecraft.entity.attribute.EntityAttributes;
import net.minecraft.entity.mob.HostileEntity;
import net.minecraft.entity.mob.MobEntityWithAi;
import net.minecraft.sound.SoundEvent;
import net.minecraft.sound.SoundEvents;
import net.minecraft.world.World;
import soggy.fnaf.common.entity.animatronics.EntityAnimatronic;

public class FNAFFreddy extends EntityAnimatronic {
    public FNAFFreddy(EntityType<? extends MobEntityWithAi> entityType, World world) {
        super(entityType, world);
    }

    public static DefaultAttributeContainer.Builder create() {
        return HostileEntity.createHostileAttributes().add(EntityAttributes.GENERIC_ATTACK_DAMAGE, 1.25D);
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
