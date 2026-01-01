package com.equilka.eqtutmod.world.inventory;

import com.equilka.eqtutmod.blocks.entity.SimpleXpOrbsWellBlockEntity;
import com.equilka.eqtutmod.init.ModBlockEntityTypeInit;
import com.equilka.eqtutmod.init.ModBlockInit;
import com.equilka.eqtutmod.init.ModMenuTypeInit;
import io.netty.buffer.Unpooled;
import net.minecraft.network.FriendlyByteBuf;
import net.minecraft.world.Container;
import net.minecraft.world.SimpleContainer;
import net.minecraft.world.entity.player.Inventory;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.inventory.*;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.block.entity.BlockEntity;

public class SimpleXpOrbsWellBlockMenu extends AbstractContainerMenu {
    protected SimpleXpOrbsWellBlockEntity blockEntity;
    protected Container container;

    public SimpleXpOrbsWellBlockMenu(int pContainerId, Inventory pPlayerInventory, FriendlyByteBuf buf) {
        this(pContainerId, pPlayerInventory,
                new SimpleContainer(0),
                (SimpleXpOrbsWellBlockEntity) pPlayerInventory.player.level().getBlockEntity(buf.readBlockPos()));
    }

    public SimpleXpOrbsWellBlockMenu(int containerId, Inventory pPlayerInventory, Container pContainer, SimpleXpOrbsWellBlockEntity blockEntity) {
        super(ModMenuTypeInit.SIMPLE_XP_ORBS_WELL_BLOCK_MENU.get(), containerId);
        this.blockEntity = blockEntity;
        this.container = pContainer;
        container.startOpen(pPlayerInventory.player);

        addDataSlots(blockEntity.getData());

        for (int row = 0; row < 3; ++row) {
            for (int col = 0; col < 9; ++col) {
                this.addSlot(new Slot(pPlayerInventory, col + row * 9 + 9, 8 + col * 18, 80 + row * 18));
            }
        }

        for (int col = 0; col < 9; ++col) {
            this.addSlot(new Slot(pPlayerInventory, col, 8 + col * 18, 138));
        }
    }

    public int getStoredXp() {
        return blockEntity.getData().get(0);
    }

    public int getMaxXp() {
        return blockEntity.getData().get(1);
    }

    @Override
    public ItemStack quickMoveStack(Player pPlayer, int pIndex) {
        return ItemStack.EMPTY;
    }

    @Override
    public boolean stillValid(Player pPlayer) {
        return this.container.stillValid(pPlayer);
    }
}
