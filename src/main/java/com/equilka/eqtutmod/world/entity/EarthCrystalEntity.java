package com.equilka.eqtutmod.world.entity;

import com.equilka.eqtutmod.init.ModEntityInit;
import com.equilka.eqtutmod.init.ModItemInit;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.network.protocol.Packet;
import net.minecraft.network.protocol.game.ClientGamePacketListener;
import net.minecraft.network.syncher.EntityDataAccessor;
import net.minecraft.network.syncher.EntityDataSerializers;
import net.minecraft.network.syncher.SynchedEntityData;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.sounds.SoundSource;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.entity.*;
import net.minecraft.world.entity.monster.Enemy;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;
import net.minecraft.world.phys.AABB;
import net.minecraft.world.phys.Vec3;
import net.minecraftforge.network.NetworkHooks;

import javax.annotation.Nullable;
import java.util.List;
import java.util.UUID;

public class EarthCrystalEntity extends Entity {
    private static final EntityDataAccessor<Boolean> STATE =
            SynchedEntityData.defineId(EarthCrystalEntity.class, EntityDataSerializers.BOOLEAN);

    public final AnimationState activeAnimation = new AnimationState();
    public final AnimationState unactiveAnimation = new AnimationState();

    @Nullable
    private UUID parentPlayer;
    private int ticksForPush;
    private int animationDuration = 0;

    public EarthCrystalEntity(EntityType<EarthCrystalEntity> pEntityType, Level pLevel) {
        super(pEntityType, pLevel);
        this.setBoundingBox(new AABB(12, 12, 12, 0, 0, 0));
    }

    public EarthCrystalEntity(Level level, UUID parentPlayer) {
        this(ModEntityInit.EARTH_CRYSTAL.get(), level);
        this.parentPlayer = parentPlayer;
        this.ticksForPush = 0;
    }

    @Override
    protected void defineSynchedData() {
        this.entityData.define(STATE, false);
    }

    public boolean getState() {
        return this.entityData.get(STATE);
    }

    private void updateState() {
        this.entityData.set(STATE, parentPlayer != null && level().getPlayerByUUID(parentPlayer) != null);
    }

    @Override
    protected void readAdditionalSaveData(CompoundTag compoundTag) {
        if (compoundTag.hasUUID("ParentPlayer")) {
            parentPlayer = compoundTag.getUUID("ParentPlayer");
        }
        ticksForPush = compoundTag.getInt("TicksForPush");
    }

    @Override
    protected void addAdditionalSaveData(CompoundTag compoundTag) {
        if (parentPlayer != null) {
            compoundTag.putUUID("ParentPlayer", parentPlayer);
        }
        compoundTag.putInt("TicksForPush", ticksForPush);
    }

    @Override
    public void tick() {
        if (level().isClientSide) {
            handleAnimations();
            return;
        }

        updateState();
        if (getState())
            ticksForPush++;

        if (ticksForPush >= 20) {
            pushEntities();
            ticksForPush = 0;
        }

        orbitAroundParent();
        super.tick();
    }

    private void handleAnimations() {
        if (this.animationDuration <= 0) {
            this.animationDuration = 80;
            if (getState())
                this.activeAnimation.start(this.tickCount);
            else
                this.unactiveAnimation.start(this.tickCount);
        } else {
            --this.animationDuration;
        }
    }

    @Override
    public boolean canCollideWith(Entity entity) {
        return true;
    }

    @Override
    public boolean isPickable() {
        return true;
    }

    @Override
    public InteractionResult interact(Player pPlayer, InteractionHand pHand) {
        ItemStack itemInHand = pPlayer.getItemInHand(pHand);

        if (!level().isClientSide && itemInHand.isEmpty()) {
            ItemStack item = new ItemStack(ModItemInit.EARTHCRYSTAL.get(), 1);
            pPlayer.setItemInHand(pHand, item);
            this.discard();
        }
        return InteractionResult.CONSUME;
    }

    private void orbitAroundParent() {
        if (parentPlayer == null) {
            fall();
            return;
        }

        Player parent = level().getPlayerByUUID(parentPlayer);
        if (parent == null) {
            fall();
            return;
        }

        Vec3 offset = new Vec3(0.5, 1.5, 0);

        float yaw = (float) Math.toRadians(parent.yHeadRot);
        double sin = Math.sin(yaw);
        double cos = Math.cos(yaw);

        double dx = offset.x * cos - offset.z * sin;
        double dz = offset.x * sin + offset.z * cos;
        double dy = offset.y;

        Vec3 targetPos = parent.position().add(dx, dy, dz);
        this.setPos(targetPos);
    }

    private void pushEntities() {
        List<LivingEntity> enemies = level().getEntitiesOfClass(LivingEntity.class,
                getBoundingBox().inflate(3), e -> e instanceof Enemy);

        for (LivingEntity enemy : enemies) {
            Vec3 push = enemy.position().subtract(position()).normalize().scale(0.5);
            enemy.push(push.x, 0.2, push.z);
            level().playSound(null, enemy, SoundEvents.SHIELD_BLOCK, SoundSource.HOSTILE,
                    level().random.nextFloat(), level().random.nextFloat());
        }
    }

    public void fall() {
        this.setDeltaMovement(this.getDeltaMovement().add(0.0, -0.04, 0.0));
        if (!this.onGround() || this.getDeltaMovement().horizontalDistanceSqr() > 9.999999747378752E-6 || (this.tickCount + this.getId()) % 4 == 0)
            this.move(MoverType.SELF, this.getDeltaMovement());
    }

    @Override
    public Packet<ClientGamePacketListener> getAddEntityPacket() {
        return NetworkHooks.getEntitySpawningPacket(this);
    }
}
