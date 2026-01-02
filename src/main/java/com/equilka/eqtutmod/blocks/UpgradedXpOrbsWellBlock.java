package com.equilka.eqtutmod.blocks;

import com.equilka.eqtutmod.blocks.entity.SimpleXpOrbsWellBlockEntity;
import com.equilka.eqtutmod.blocks.entity.UpgradedXpOrbsWellBlockEntity;
import com.equilka.eqtutmod.init.ModBlockEntityTypeInit;
import net.minecraft.core.BlockPos;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.sounds.SoundSource;
import net.minecraft.util.RandomSource;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.BaseEntityBlock;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.RenderShape;
import net.minecraft.world.level.block.SoundType;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.entity.BlockEntityTicker;
import net.minecraft.world.level.block.entity.BlockEntityType;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.StateDefinition;
import net.minecraft.world.level.block.state.properties.IntegerProperty;
import net.minecraft.world.level.gameevent.GameEventListener;
import net.minecraft.world.level.material.MapColor;
import net.minecraft.world.phys.BlockHitResult;
import net.minecraft.world.phys.shapes.CollisionContext;
import net.minecraft.world.phys.shapes.Shapes;
import net.minecraft.world.phys.shapes.VoxelShape;
import net.minecraftforge.network.NetworkHooks;
import org.jetbrains.annotations.Nullable;

public class UpgradedXpOrbsWellBlock extends SimpleXpOrbsWellBlock {
    public UpgradedXpOrbsWellBlock() {
        super();

        this.oneQuarter = 240;
        this.isFragile = false;
        this.voxelShape = Block.box(1.0, 0.0, 1.0, 15.0, 16.0, 15.0);
    }

    @Override
    public @Nullable BlockEntity newBlockEntity(BlockPos pPos, BlockState pState) {
        return new UpgradedXpOrbsWellBlockEntity(pPos, pState);
    }

    @Override
    public @Nullable <T extends BlockEntity> BlockEntityTicker<T> getTicker(Level pLevel, BlockState pState, BlockEntityType<T> pBlockEntityType) {
        if(pLevel.isClientSide())
            return null;
        return createTickerHelper(pBlockEntityType,ModBlockEntityTypeInit.UPGRADED_XP_ORBS_WELL_BLOCK_ENTITY.get(), UpgradedXpOrbsWellBlockEntity::tick);
    }
}
