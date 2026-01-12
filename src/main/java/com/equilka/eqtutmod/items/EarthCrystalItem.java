package com.equilka.eqtutmod.items;

import com.equilka.eqtutmod.world.entity.EarthCrystalEntity;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResultHolder;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Rarity;
import net.minecraft.world.item.UseAnim;
import net.minecraft.world.level.Level;

public class EarthCrystalItem extends Item {
    public EarthCrystalItem() {
        super(new Item.Properties()
                .rarity(Rarity.EPIC)
                .stacksTo(1));
    }

    @Override
    public InteractionResultHolder<ItemStack> use(Level pLevel, Player pPlayer, InteractionHand pUsedHand) {
        pPlayer.startUsingItem(pUsedHand);
        return super.use(pLevel, pPlayer, pUsedHand);
    }

    @Override
    public int getUseDuration(ItemStack pStack) {
        return 20;
    }

    @Override
    public UseAnim getUseAnimation(ItemStack pStack) {
        return UseAnim.CROSSBOW;
    }

    @Override
    public void releaseUsing(ItemStack pStack, Level pLevel, LivingEntity pLivingEntity, int pTimeCharged) {
        super.releaseUsing(pStack, pLevel, pLivingEntity, pTimeCharged);
        if (pLivingEntity instanceof Player player && !pLevel.isClientSide) {
            EarthCrystalEntity entity = new EarthCrystalEntity(player.level(), player.getUUID());
            entity.moveTo(player.position());

            pLevel.addFreshEntity(entity);
            pStack.shrink(1);
        }
    }
}

