package com.equilka.eqtutmod.blocks;

import com.equilka.eqtutmod.blocks.entity.SimpleXpOrbsWellBlockEntity;
import net.minecraft.core.BlockPos;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.BaseEntityBlock;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.entity.BlockEntityTicker;
import net.minecraft.world.level.block.entity.BlockEntityType;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.gameevent.GameEventListener;
import net.minecraft.world.level.material.MapColor;
import net.minecraft.world.phys.BlockHitResult;
import org.jetbrains.annotations.Nullable;

public class SimpleXpOrbsWellBlock extends BaseEntityBlock {
    public SimpleXpOrbsWellBlock() {
        super(BlockBehaviour.Properties.of()
                .mapColor(MapColor.COLOR_LIGHT_GREEN)
                .requiresCorrectToolForDrops());
    }

    @Override
    public InteractionResult use(BlockState pState, Level pLevel, BlockPos pPos, Player pPlayer, InteractionHand pHand, BlockHitResult pHit) {
        SimpleXpOrbsWellBlockEntity entity = (SimpleXpOrbsWellBlockEntity) pLevel.getBlockEntity(pPos);
        int maxXp = entity.getMaxXp();
        int storedXp = entity.getStoredXp();
        int playerXp = pPlayer.totalExperience;
        int returnedXp = (storedXp + playerXp) % maxXp;
        int addedXp = storedXp + playerXp - returnedXp;

        pPlayer.totalExperience = returnedXp;
        if (maxXp < playerXp) {
            pLevel.destroyBlock(pPos, false);
            popExperience((ServerLevel) pLevel, pPos, addedXp);
            return InteractionResult.SUCCESS;
        }

        entity.addXp(addedXp);
        return InteractionResult.SUCCESS;
    }

    @Override
    public @Nullable BlockEntity newBlockEntity(BlockPos pPos, BlockState pState) {
        return new SimpleXpOrbsWellBlockEntity(pPos, pState);
    }

    @Override
    public @Nullable <T extends BlockEntity> BlockEntityTicker<T> getTicker(Level pLevel, BlockState pState, BlockEntityType<T> pBlockEntityType) {
        return super.getTicker(pLevel, pState, pBlockEntityType);
    }

    @Override
    public @Nullable <T extends BlockEntity> GameEventListener getListener(ServerLevel pLevel, T pBlockEntity) {
        return super.getListener(pLevel, pBlockEntity);
    }
}
