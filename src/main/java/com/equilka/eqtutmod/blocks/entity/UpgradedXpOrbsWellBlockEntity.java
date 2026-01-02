package com.equilka.eqtutmod.blocks.entity;

import com.equilka.eqtutmod.init.ModBlockEntityTypeInit;
import com.equilka.eqtutmod.world.inventory.SimpleXpOrbsWellBlockMenu;
import com.equilka.eqtutmod.world.inventory.UpgradedXpOrbsWellBlockMenu;
import net.minecraft.core.BlockPos;
import net.minecraft.core.NonNullList;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.network.chat.Component;
import net.minecraft.world.MenuProvider;
import net.minecraft.world.SimpleContainer;
import net.minecraft.world.entity.player.Inventory;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.inventory.AbstractContainerMenu;
import net.minecraft.world.inventory.ContainerData;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.state.BlockState;
import org.jetbrains.annotations.Nullable;

import java.util.Random;

public class UpgradedXpOrbsWellBlockEntity extends SimpleXpOrbsWellBlockEntity {
    public UpgradedXpOrbsWellBlockEntity(BlockPos pPos, BlockState pBlockState) {
        super(ModBlockEntityTypeInit.UPGRADED_XP_ORBS_WELL_BLOCK_ENTITY.get(), pPos, pBlockState);

        this.storedXp = 0;
        this.maxXp = 1000;
        this.containerSize = 2;
        this.items = NonNullList.withSize(2, ItemStack.EMPTY);
    }
    @Override
    public Component getDisplayName() {
        return Component.translatable("block.eqtutmod.upgraded_xp_orbs_well_block");
    }

    @Nullable
    @Override
    public AbstractContainerMenu createMenu(int i, Inventory inventory, Player player) {
        return new UpgradedXpOrbsWellBlockMenu(i, player.getInventory(), this, this.data);
    }

    public static void tick(Level level, BlockPos blockPos, BlockState blockState, UpgradedXpOrbsWellBlockEntity entity) {
        if (level.isClientSide) return;

        ItemStack input = entity.items.get(0);
        ItemStack output = entity.items.get(1);

        int orbs = level.random.nextInt(3, 11);
        int currentXp = entity.data.get(0);

        if (input.is(Items.EXPERIENCE_BOTTLE)){
            if (!output.isEmpty() && !output.is(Items.GLASS_BOTTLE) || output.getCount() >= 64)
                return;

            if (currentXp + orbs >= entity.maxXp || output.getCount() >= 64)
                return;

            entity.data.set(0, currentXp + orbs);
            input.shrink(1);
            entity.items.set(0, input.isEmpty() ? ItemStack.EMPTY : input);
            entity.items.set(1, new ItemStack(Items.GLASS_BOTTLE, output.getCount() + 1));
        } else if (input.is(Items.GLASS_BOTTLE)){
            if (!output.isEmpty() && !output.is(Items.EXPERIENCE_BOTTLE))
                return;

            if (orbs >= currentXp || output.getCount() >= 64)
                return;

            entity.data.set(0, currentXp - orbs);
            input.shrink(1);
            entity.items.set(0, input.isEmpty() ? ItemStack.EMPTY : input);
            entity.items.set(1, new ItemStack(Items.EXPERIENCE_BOTTLE, output.getCount() + 1));
        }
    }
}
