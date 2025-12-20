package com.equilka.eqtutmod.blocks.entity;

import com.equilka.eqtutmod.init.ModBlockEntityTypeInit;
import net.minecraft.core.BlockPos;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.state.BlockState;

public class SimpleXpOrbsWellBlockEntity extends BlockEntity {
    private int StoredXp = 0;
    private int MaxXp = 1000;
    public SimpleXpOrbsWellBlockEntity(BlockPos pPos, BlockState pBlockState) {
        super(ModBlockEntityTypeInit.SIMPLE_XP_ORBS_WELL_BLOCK_ENTITY.get(), pPos, pBlockState);
    }

    @Override
    protected void saveAdditional(CompoundTag pTag) {
        super.saveAdditional(pTag);
        pTag.putInt("StoredXp",StoredXp);
    }



    @Override
    public void load(CompoundTag pTag) {
        super.load(pTag);
        StoredXp = pTag.getInt("StoredXp");
    }

    public void addXp(int value) {
        StoredXp += value;
    }

    public int getStoredXp() {
        return StoredXp;
    }

    public int getMaxXp() { return MaxXp; }
}
