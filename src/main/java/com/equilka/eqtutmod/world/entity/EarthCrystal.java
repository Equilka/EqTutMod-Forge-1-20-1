package com.equilka.eqtutmod.world.entity;

import com.equilka.eqtutmod.init.ModEntityInit;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.monster.Enemy;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.level.Level;
import net.minecraft.world.phys.AABB;

public class EarthCrystal extends Entity {
    private Player parentPlayer;

    public EarthCrystal(EntityType<?> pEntityType, Level pLevel) {
        super(pEntityType, pLevel);

        this.setBoundingBox(new AABB(10, 10, 10, 0, 0, 0));
    }

    public EarthCrystal(Level level, Player player) {
        this(ModEntityInit.EARTH_CRYSTAL.get(), level);

        this.parentPlayer = player;
    }

    @Override
    protected void defineSynchedData() {

    }

    @Override
    protected void readAdditionalSaveData(CompoundTag compoundTag) {

    }

    @Override
    protected void addAdditionalSaveData(CompoundTag compoundTag) {

    }

    @Override
    public boolean canCollideWith(Entity pEntity) {
        return canEntityCollide(this);
    }

    public static boolean canEntityCollide(Entity pCollidedEntity) {
        return pCollidedEntity instanceof Enemy;
    }

    @Override
    public boolean canBeCollidedWith() {
        return true;
    }

    @Override
    public void tick() {
        super.tick();
    }


}
