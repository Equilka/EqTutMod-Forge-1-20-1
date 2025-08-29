package com.equilka.eqtutmod.blocks;

import com.equilka.eqtutmod.init.ModItemInit;
import net.minecraft.core.BlockPos;
import net.minecraft.network.chat.Component;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.entity.item.ItemEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.*;
import net.minecraft.world.item.context.BlockPlaceContext;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.StateDefinition;
import net.minecraft.world.level.block.state.properties.BooleanProperty;
import net.minecraft.world.level.block.state.properties.EnumProperty;
import net.minecraft.world.level.block.state.properties.IntegerProperty;
import net.minecraft.world.phys.BlockHitResult;
import org.jetbrains.annotations.Nullable;

import java.util.List;

public class MojBlock extends Block {
    public static final BooleanProperty ACTIVATED = BooleanProperty.create("activated");
    public static final EnumProperty<DyeColor> COLOR = EnumProperty.create("color", DyeColor.class);
    public static final IntegerProperty CLICKS = IntegerProperty.create("clicks", 0, 5);

    public MojBlock() {
        super(BlockBehaviour.Properties.copy(Blocks.GREEN_WOOL));
    }

    @Override
    public void appendHoverText(ItemStack pStack, @Nullable BlockGetter pLevel, List<Component> pTooltip, TooltipFlag pFlag) {
        pTooltip.add(Component.literal(Component.translatable("tooltip.item.eqtutmod.moj_block").getString()));
    }

    @Override
    public void neighborChanged(BlockState state, Level level, BlockPos pos, Block block, BlockPos fromPos, boolean b) {
        if (!level.isClientSide() && level.hasNeighborSignal(pos)) {
            level.setBlock(pos, state.setValue(ACTIVATED, true), 3);
        } else if (!level.isClientSide() && !level.hasNeighborSignal(pos)) {
            level.setBlock(pos, state.setValue(ACTIVATED, false), 3);
        }
    }

    @Override
    public InteractionResult use(BlockState pState, Level pLevel, BlockPos pPos, Player pPlayer, InteractionHand pHand, BlockHitResult pHit) {
        if (!pLevel.isClientSide()) {
            DyeColor dyeColor = pState.getValue(COLOR);

            if (pPlayer.getMainHandItem().getItem() instanceof DyeItem dye)
                dyeColor = dye.getDyeColor();

            if (dyeColor != pState.getValue(COLOR) && (dyeColor == DyeColor.GREEN || dyeColor == DyeColor.BLUE || dyeColor == DyeColor.RED)) {
                pLevel.setBlock(pPos, pState.setValue(COLOR, dyeColor), 3);
            } else if (!pState.getValue(ACTIVATED)){
                int clicks = pState.getValue(CLICKS) + 1;

                if (clicks >= 5) {
                    pLevel.setBlock(pPos, pState.setValue(CLICKS, 0), 3);
                    pLevel.addFreshEntity(new ItemEntity(pLevel, pPos.getX(), pPos.above().getY(), pPos.getZ(),
                            new ItemStack(DyeItem.byColor(dyeColor))
                    ));
                } else {
                    pLevel.setBlock(pPos, pState.setValue(CLICKS, clicks), 3);
                }
            }

        }
        return InteractionResult.SUCCESS;
    }

    @Override
    public BlockState getStateForPlacement(BlockPlaceContext ctx) {
        boolean powered = ctx.getLevel().hasNeighborSignal(ctx.getClickedPos());
        return this.defaultBlockState()
                .setValue(ACTIVATED, powered)
                .setValue(COLOR, DyeColor.GREEN)
                .setValue(CLICKS, 0);
    }

    @Override
    protected void createBlockStateDefinition(StateDefinition.Builder<Block, BlockState> builder) {
        builder.add(ACTIVATED);
        builder.add(COLOR);
        builder.add(CLICKS);
    }
}
