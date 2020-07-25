package soggy.fnaf.common.entity.animatronics;

import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.ai.control.MoveControl;
import net.minecraft.entity.ai.goal.*;
import net.minecraft.entity.damage.DamageSource;
import net.minecraft.entity.mob.MobEntityWithAi;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.sound.SoundEvent;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.MathHelper;
import net.minecraft.util.math.Vec3d;
import net.minecraft.util.shape.VoxelShapes;
import net.minecraft.world.Difficulty;
import net.minecraft.world.World;
import soggy.fnaf.common.entity.animatronics.goal.FindPlayerGoal;
import soggy.fnaf.common.entity.animatronics.goal.MoveToSpawn;

import java.util.Objects;
import java.util.Random;

/**
 * @author George (SoggyMustache)
 */
public abstract class EntityAnimatronic extends MobEntityWithAi {
    //public EnderDragonPart[] bodyparts;
    //public EnderDragonPart bodyMainBox = new EnderDragonPart(this, "bodyMain", 0.8F, 2.2F);

    protected int ventTime = 0;

    private EntityAnimatronic.AIFight AIFight;
    private WanderAroundGoal wander;
    private boolean spawnPosSet = false;
    public static double spawnPosX;
    public static double spawnPosY;
    public static double spawnPosZ;
    protected Random rand = new Random();
    public int scareTimer;
    public boolean isScaring=false;
    public float movementSpeed;

    public EntityAnimatronic(EntityType<? extends MobEntityWithAi> entityType, World world) {
        super(entityType, world);
        this.moveControl = new MoveControl(this);
        this.setMovementSpeed(movementSpeed);
    }

    @Override
    protected void initGoals() {
            this.goalSelector.add(0, new MoveToSpawn(this, 0.8F));
            this.goalSelector.add(7, wander = new WanderAroundGoal(this, this.getMovementSpeed(), 3));
            this.goalSelector.add(2, AIFight = new EntityAnimatronic.AIFight(this));
            this.goalSelector.add(0, new SwimGoal(this));
            this.goalSelector.add(0, new AttackGoal(this));
            this.goalSelector.add(8, new LookAroundGoal(this));
            this.goalSelector.add(8, new LookAtEntityGoal(this, PlayerEntity.class, 1));
            this.goalSelector.add(1, new FindPlayerGoal(this, 5));
            this.goalSelector.add(0, new FollowTargetGoal(this, PlayerEntity.class, true));
            this.goalSelector.add(0, new MoveToSpawn(this, 0.8F));
    }



    public boolean isSpawnPosSet() {
        return spawnPosSet;
    }

    @Override
    public void writeCustomDataToTag(CompoundTag tag) {
        super.writeCustomDataToTag(tag);
        if(spawnPosSet) {
            tag.putDouble("spawnPosX", this.getZ());
            tag.putDouble("spawnPosY", this.getY());
            tag.putDouble("spawnPosZ", this.getX());
            tag.putBoolean("spawnPosSet", true);
        }
    }

    @Override
    public void readCustomDataFromTag(CompoundTag tag) {
        super.readCustomDataFromTag(tag);
        if(tag.getBoolean("spawnPosSet")) {
            spawnPosX = tag.getDouble("spawnPosX");
            spawnPosY = tag.getDouble("spawnPosY");
            spawnPosZ = tag.getDouble("spawnPosZ");
            spawnPosSet = tag.getBoolean("spawnPosSet");
        }
    }

    @Override
    public boolean cannotDespawn() {
        return true;
    }

    @Override
    public void tick() {
        if(world.isClient) {
            //this.bodyMainBox.updatePositionAndAngles(this.serverX, this.serverY + 0.8F, this.serverZ, 0, 0);
        }

        if(!roamDuringDay()) {
            if (world.isNight()) {
                this.setMovementSpeed(movementSpeed);
            } else {
                this.setMovementSpeed(0);
            }
        }
        super.tick();
        if(shouldDespawnOnPeaceful()) {
            if(!this.world.isClient && this.world.getDifficulty() == Difficulty.PEACEFUL) {
                this.kill();
            }
        }

        Entity ridingEntity = this.getVehicle();
        if(this.hasVehicle() && this.getVehicle() instanceof PlayerEntity) {
            if(ridingEntity.hasPassenger(this)) {
                this.setRotation(ridingEntity.getHeadYaw(), ridingEntity.pitch);
                float angle = -(0.0174533F * ((PlayerEntity) ridingEntity).yaw) + 195;
                this.setPos(ridingEntity.trackedX + -(0.4F * MathHelper.sin((float) (Math.PI + angle))), ridingEntity.trackedY + 1.4D, ridingEntity.trackedZ + -(0.4F * MathHelper.cos((float) (Math.PI + angle))));

                if(((PlayerEntity) ridingEntity).isDead()) {
                    this.updatePassengerForDismount(this);
                }
            }
        }

        if(!spawnPosSet) {
            this.spawnPosX = this.getX();
            this.spawnPosY = this.getY();
            this.spawnPosZ = this.getZ();
        }
    }

    public boolean shouldDespawnOnPeaceful() {
        return true;
    }

    public abstract boolean jumpScares();

    public abstract boolean roamDuringDay();

    public abstract SoundEvent jumpscareSound();

    public abstract float setAnimatronicAttackDamage();

    public boolean isInVent() {
        return this.world.getBlockState(new BlockPos(this.getPos()).add(0, 1, 0)).getCollisionShape(world, new BlockPos(this.getPos()).add(0, 1, 0)) != VoxelShapes.empty();
    }

    public class AIFight extends MeleeAttackGoal {
        private final EntityAnimatronic animatronic;
        public AIFight(EntityAnimatronic animatronic) {
            super(EntityAnimatronic.this, 1.7D, true);
            this.animatronic = animatronic;
        }

        @Override
        public void tick() {
            if(jumpScares()) {
                PlayerEntity playerEntity = this.animatronic.world.getClosestPlayer(this.mob, 3.0D);
                if(playerEntity != null && !playerEntity.isCreative() && !playerEntity.hasPassengers()) {
                    if (roamDuringDay()) {
                        this.mob.startRiding(playerEntity, true);
                        playerEntity.playSound(jumpscareSound(), 1.0F, 1.0F);
                    } else if (!playerEntity.world.isDay()) {
                        this.mob.startRiding(playerEntity, true);
                        playerEntity.playSound(jumpscareSound(), 1.0F, 1.0F);
                    }
                }
                if(this.mob.getVehicle() != null) isScaring = this.mob.getVehicle() instanceof PlayerEntity;

                if(this.mob.getEntityWorld() == Objects.requireNonNull(this.mob.getServer()).getOverworld()) {
                    if(isScaring && scareTimer > 8) {
                        this.mob.getVehicle().damage(DamageSource.mob(this.mob), 5);
                    }

                    if (isScaring) {
                        ++scareTimer;
                    } else {
                        scareTimer = 0;
                    }
                }
            }
            else if(isInVent()) {
                ++ventTime;
            }
            else {
                ventTime = 0;
            }
            super.tick();
        }
    }
}
