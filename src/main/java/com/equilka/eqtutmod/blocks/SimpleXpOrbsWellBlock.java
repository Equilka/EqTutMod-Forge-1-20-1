package com.equilka.eqtutmod.blocks;

import com.equilka.eqtutmod.blocks.entity.SimpleXpOrbsWellBlockEntity;
import net.minecraft.core.BlockPos;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.sounds.SoundSource;
import net.minecraft.world.*;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.*;
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

public class SimpleXpOrbsWellBlock extends BaseEntityBlock{
    public static final IntegerProperty FILLED = IntegerProperty.create("filled", 0, 4);
    protected int oneQuarter;
    protected boolean isFragile;
    protected VoxelShape voxelShape;

    public SimpleXpOrbsWellBlock() {
        super(Properties.of()
                .mapColor(MapColor.COLOR_LIGHT_GREEN)
                .requiresCorrectToolForDrops()
                .strength(10)
                .sound(SoundType.AMETHYST));

        this.registerDefaultState(this.stateDefinition.any().setValue(FILLED, 0));
        this.oneQuarter = 90;
        this.isFragile = true;
        this.voxelShape = Block.box(0.1, 0.0, 0.1, 15.9, 16.0, 15.9);
    }

    @Override
    protected void createBlockStateDefinition(StateDefinition.Builder<Block, BlockState> builder) {
        builder.add(FILLED);
    }

    @Override
    public InteractionResult use(BlockState pState, Level pLevel, BlockPos pPos, Player pPlayer, InteractionHand pHand, BlockHitResult pHit) {
        if (pLevel.isClientSide())
            return InteractionResult.SUCCESS;

        BlockEntity blockEntity = pLevel.getBlockEntity(pPos);
        if (!(blockEntity instanceof SimpleXpOrbsWellBlockEntity entity))
            return InteractionResult.SUCCESS;

        if (pPlayer.isCrouching()) {
            NetworkHooks.openScreen((ServerPlayer) pPlayer,entity,pPos);
            return InteractionResult.CONSUME;
        }

        int storedXp = entity.getData().get(0);
        int maxXp = entity.getData().get(1);

        int playerXp = pPlayer.totalExperience;
        if (playerXp <= 0)
            return InteractionResult.CONSUME;

        pPlayer.giveExperiencePoints(-playerXp);

        int spaceLeft = maxXp - storedXp;
        int toAdd = Math.min(spaceLeft, playerXp);
        int leftover = playerXp - toAdd;

        int newXp = storedXp + toAdd;
        entity.getData().set(0, newXp);

        pPlayer.giveExperiencePoints(leftover);

        if (newXp >= maxXp && isFragile) {
            pLevel.destroyBlock(pPos, false);
            pPlayer.giveExperiencePoints(newXp);
            return InteractionResult.SUCCESS;
        } else if (newXp <= maxXp && !isFragile) {
            leftover = newXp - maxXp;
            pPlayer.giveExperiencePoints(leftover);
            entity.getData().set(0, newXp - leftover);
        }

        pLevel.setBlock(pPos, pState.setValue(FILLED, maxXp == 0 ? 0 : Math.min(4, (storedXp - 1) / oneQuarter + 1)), 3);
        pLevel.playSound(null, pPos.getX(), pPos.getY(), pPos.getZ(), SoundEvents.ENDER_EYE_DEATH, SoundSource.BLOCKS, 0.5F, pLevel.random.nextFloat() * 0.1F + 0.9F);
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

    @Override
    public void onRemove(BlockState pState, Level pLevel, BlockPos pPos, BlockState pNewState, boolean pMovedByPiston) {
        if (pState.is(pNewState.getBlock()))
            return;

        BlockEntity entity = pLevel.getBlockEntity(pPos);
        if (entity instanceof SimpleXpOrbsWellBlockEntity blockEntity && pLevel instanceof ServerLevel serverLevel) {
            int storedXp = blockEntity.getData().get(0);
            entity.setRemoved();
            popExperience(serverLevel, pPos, storedXp);
        }
    }

    @Override
    public boolean isOcclusionShapeFullBlock(BlockState state, BlockGetter level, BlockPos pos) {
        return true;
    }

    @Override
    public boolean propagatesSkylightDown(BlockState state, BlockGetter reader, BlockPos pos) {
        return true;
    }

    @Override
    public VoxelShape getShape(BlockState pState, BlockGetter pLevel, BlockPos pPos, CollisionContext pContext) {
        return voxelShape;
    }

    public VoxelShape getOcclusionShape(BlockState pState, BlockGetter pLevel, BlockPos pPos) {
        return voxelShape;
    }

    public RenderShape getRenderShape(BlockState pState) {
        return RenderShape.MODEL;
    }

    public boolean useShapeForLightOcclusion(BlockState pState) {
        return true;
    }

    @Override
    public int getLightBlock(BlockState state, BlockGetter worldIn, BlockPos pos) {
        return state.getValue(FILLED);
    }
}
