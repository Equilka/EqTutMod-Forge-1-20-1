package com.equilka.eqtutmod.blocks.entity;

import com.equilka.eqtutmod.init.ModBlockEntityTypeInit;
import com.equilka.eqtutmod.world.inventory.SimpleXpOrbsWellBlockMenu;
import net.minecraft.core.BlockPos;
import net.minecraft.core.NonNullList;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.ContainerHelper;
import net.minecraft.world.MenuProvider;
import net.minecraft.world.SimpleContainer;
import net.minecraft.world.entity.player.Inventory;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.inventory.AbstractContainerMenu;
import net.minecraft.world.inventory.ContainerData;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.block.entity.BaseContainerBlockEntity;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.entity.BlockEntityType;
import net.minecraft.world.level.block.state.BlockState;
import org.jetbrains.annotations.Nullable;

import java.util.Iterator;

public class SimpleXpOrbsWellBlockEntity extends BaseContainerBlockEntity implements MenuProvider {
    protected int storedXp;
    protected int maxXp;
    protected int containerSize;
    protected NonNullList<ItemStack> items;

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
        this(ModBlockEntityTypeInit.SIMPLE_XP_ORBS_WELL_BLOCK_ENTITY.get(), pPos, pBlockState);
    }

    public SimpleXpOrbsWellBlockEntity(BlockEntityType entityType, BlockPos pPos, BlockState pBlockState) {
        super(entityType, pPos, pBlockState);

        this.storedXp = 0;
        this.maxXp = 500;
        this.containerSize = 0;
        this.items = NonNullList.withSize(0, ItemStack.EMPTY);
    }

    @Override
    protected void saveAdditional(CompoundTag pTag) {
        super.saveAdditional(pTag);
        pTag.putInt("storedXp",storedXp);
        pTag.putInt("maxXp", maxXp);
        ContainerHelper.saveAllItems(pTag, this.items);
    }

    @Override
    public void load(CompoundTag pTag) {
        super.load(pTag);
        storedXp = pTag.getInt("storedXp");
        maxXp = pTag.getInt("maxXp");
        this.items = NonNullList.withSize(containerSize, ItemStack.EMPTY);
        ContainerHelper.loadAllItems(pTag, this.items);
    }

    public ContainerData getData() {return data;}

    @Override
    public Component getDisplayName() {
        return Component.translatable("block.eqtutmod.simple_xp_orbs_well_block");
    }

    @Override
    protected Component getDefaultName() {
        return null;
    }

    @Nullable
    @Override
    public AbstractContainerMenu createMenu(int i, Inventory inventory, Player player) {
        return new SimpleXpOrbsWellBlockMenu(i, player.getInventory(), new SimpleContainer(containerSize), this);
    }

    @Override
    protected AbstractContainerMenu createMenu(int i, Inventory inventory) {
        return null;
    }

    @Override
    public int getContainerSize() {
        return containerSize;
    }

    @Override
    public boolean isEmpty() {
        for (ItemStack itemstack : this.items) {
            if (!itemstack.isEmpty()) {
                return false;
            }
        }
        return true;
    }

    @Override
    public ItemStack getItem(int p_58328_) {
        return this.items.get(p_58328_);
    }

    @Override
    public ItemStack removeItem(int p_58330_, int p_58331_) {
        return ContainerHelper.removeItem(this.items, p_58330_, p_58331_);
    }

    @Override
    public ItemStack removeItemNoUpdate(int p_58387_) {
        return ContainerHelper.takeItem(this.items, p_58387_);
    }

    @Override
    public void setItem(int p_58333_, ItemStack p_58334_) {
        ItemStack itemstack = this.items.get(p_58333_);
        this.items.set(p_58333_, p_58334_);
        if (p_58334_.getCount() > this.getMaxStackSize()) {
            p_58334_.setCount(this.getMaxStackSize());
        }
    }

    @Override
    public boolean stillValid(Player player) {
        return false;
    }

    @Override
    public void clearContent() {

    }
}
