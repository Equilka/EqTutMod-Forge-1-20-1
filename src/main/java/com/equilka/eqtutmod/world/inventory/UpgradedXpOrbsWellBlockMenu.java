package com.equilka.eqtutmod.world.inventory;

import com.equilka.eqtutmod.blocks.entity.UpgradedXpOrbsWellBlockEntity;
import com.equilka.eqtutmod.blocks.entity.UpgradedXpOrbsWellBlockEntity;
import com.equilka.eqtutmod.init.ModBlockInit;
import com.equilka.eqtutmod.init.ModMenuTypeInit;
import net.minecraft.network.FriendlyByteBuf;
import net.minecraft.world.Container;
import net.minecraft.world.SimpleContainer;
import net.minecraft.world.entity.player.Inventory;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.inventory.*;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;

public class UpgradedXpOrbsWellBlockMenu extends AbstractContainerMenu {
    protected UpgradedXpOrbsWellBlockEntity blockEntity;
    protected ContainerData container;

    public UpgradedXpOrbsWellBlockMenu(int pContainerId, Inventory pPlayerInventory, FriendlyByteBuf buf) {
        this(pContainerId, pPlayerInventory,
                (UpgradedXpOrbsWellBlockEntity) pPlayerInventory.player.level().getBlockEntity(buf.readBlockPos()),
                new SimpleContainerData(2));
    }

    public UpgradedXpOrbsWellBlockMenu(int containerId, Inventory pPlayerInventory, UpgradedXpOrbsWellBlockEntity blockEntity, ContainerData pContainer) {
        super(ModMenuTypeInit.UPGRADED_XP_ORBS_WELL_BLOCK_MENU.get(), containerId);
        this.blockEntity = blockEntity;
        this.container = pContainer;

        addDataSlots(blockEntity.getData());

        this.addSlot(new Slot(blockEntity, 0, 50, 29){
            @Override
            public boolean mayPlace(ItemStack pStack) {
                return pStack.is(Items.GLASS_BOTTLE) || pStack.is(Items.EXPERIENCE_BOTTLE);
            }
        });
        this.addSlot(new Slot(blockEntity, 1, 108, 29));

        for (int row = 0; row < 3; ++row) {
            for (int col = 0; col < 9; ++col) {
                this.addSlot(new Slot(pPlayerInventory, col + row * 9 + 9, 8 + col * 18, 80 + row * 17));
            }
        }

        for (int col = 0; col < 9; ++col) {
            this.addSlot(new Slot(pPlayerInventory, col, 8 + col * 18, 134));
        }
    }

    public int getStoredXp() {
        return blockEntity.getData().get(0);
    }

    public int getMaxXp() {
        return blockEntity.getData().get(1);
    }

    @Override
    public ItemStack quickMoveStack(Player pPlayer, int pIndex) {return ItemStack.EMPTY;}

    @Override
    public boolean stillValid(Player player) {
        return blockEntity.getBlockPos().distSqr(player.blockPosition()) < 64.0D;
    }
}