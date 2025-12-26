package com.equilka.eqtutmod.blocks.entity;

import com.equilka.eqtutmod.init.ModBlockEntityTypeInit;
import com.equilka.eqtutmod.world.inventory.SimpleXpOrbsWellBlockMenu;
import net.minecraft.core.BlockPos;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.network.chat.Component;
import net.minecraft.world.MenuProvider;
import net.minecraft.world.SimpleContainer;
import net.minecraft.world.entity.player.Inventory;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.inventory.AbstractContainerMenu;
import net.minecraft.world.inventory.ContainerData;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.state.BlockState;
import org.jetbrains.annotations.Nullable;

public class SimpleXpOrbsWellBlockEntity extends BlockEntity implements MenuProvider {
    private int storedXp = 0;
    private int maxXp = 500;

    protected final ContainerData data = new ContainerData() {
        @Override
        public int get(int index) {
            return switch (index) {
                case 0 -> storedXp;
                case 1-> maxXp;
                default -> 0;
            };
        }

        @Override
        public void set(int index, int value) {
            if (index == 0) {
                storedXp = value;
                setChanged();
            }
            if (index == 1) {
                maxXp = value;
                setChanged();
            }
        }

        @Override
        public int getCount() {
            return 2;
        }
    };

    public SimpleXpOrbsWellBlockEntity(BlockPos pPos, BlockState pBlockState) {
        super(ModBlockEntityTypeInit.SIMPLE_XP_ORBS_WELL_BLOCK_ENTITY.get(), pPos, pBlockState);
    }

    @Override
    protected void saveAdditional(CompoundTag pTag) {
        super.saveAdditional(pTag);
        pTag.putInt("storedXp",storedXp);
        pTag.putInt("maxXp", maxXp);
    }

    @Override
    public void load(CompoundTag pTag) {
        super.load(pTag);
        storedXp = pTag.getInt("storedXp");
        maxXp = pTag.getInt("maxXp");
    }

    public ContainerData getData() {return data;}

    @Override
    public Component getDisplayName() {
        return Component.translatable("block.eqtutmod.simple_xp_orbs_well_block");
    }

    @Nullable
    @Override
    public AbstractContainerMenu createMenu(int i, Inventory inventory, Player player) {
        return new SimpleXpOrbsWellBlockMenu(i, player.getInventory(), new SimpleContainer(0), this);
    }
}
