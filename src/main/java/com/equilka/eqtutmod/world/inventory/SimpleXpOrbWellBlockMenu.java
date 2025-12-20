package com.equilka.eqtutmod.world.inventory;

import com.equilka.eqtutmod.blocks.SimpleXpOrbsWellBlock;
import com.equilka.eqtutmod.blocks.entity.SimpleXpOrbsWellBlockEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.inventory.AbstractContainerMenu;
import net.minecraft.world.inventory.MenuType;
import net.minecraft.world.item.ItemStack;
import org.jetbrains.annotations.Nullable;

public class SimpleXpOrbWellBlockMenu extends AbstractContainerMenu {
    private final SimpleXpOrbsWellBlockEntity block;

    protected SimpleXpOrbWellBlockMenu(@Nullable MenuType<?> pMenuType, int pContainerId, SimpleXpOrbsWellBlockEntity block) {
        super(pMenuType, pContainerId);
        this.block = block;
    }


    public int getStoredXp() {
        return block.getStoredXp();
    }

    @Override
    public ItemStack quickMoveStack(Player pPlayer, int pIndex) {
        return null;
    }

    @Override
    public boolean stillValid(Player pPlayer) {
        return false;
    }
}
