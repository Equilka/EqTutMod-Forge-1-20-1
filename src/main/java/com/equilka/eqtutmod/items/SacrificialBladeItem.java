package com.equilka.eqtutmod.items;

import com.equilka.eqtutmod.init.ModItemInit;
import com.google.common.collect.Multimap;
import com.mojang.blaze3d.shaders.Effect;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.network.chat.Component;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.damagesource.DamageSources;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.ai.attributes.Attribute;
import net.minecraft.world.entity.ai.attributes.AttributeModifier;
import net.minecraft.world.entity.animal.Animal;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.*;
import net.minecraft.world.item.crafting.Ingredient;
import net.minecraft.world.level.Level;
import org.jetbrains.annotations.Nullable;

import java.util.List;

public class SacrificialBladeItem extends SwordItem {
    private static final String STORED_SOUL = "StoredSoul";
    private static final String SOUL_POWER = "SoulPower";

    public SacrificialBladeItem() {
        super(new Tier() {
            public int getUses() {
                return 502;
            }

            public float getSpeed() {
                return 1.6f;
            }

            public float getAttackDamageBonus() {
                return 0f;
            }

            public int getLevel() {
                return 1;
            }

            public int getEnchantmentValue() {
                return 16;
            }

            public Ingredient getRepairIngredient() {
                return Ingredient.of(new ItemStack(ModItemInit.CURSEDCORE.get()));
            }
        }, 6, -2.4f, new Item.Properties().rarity(Rarity.EPIC));
    }

    @Override
    public boolean hurtEnemy(ItemStack pStack, LivingEntity pTarget, LivingEntity pAttacker) {
        if (pAttacker instanceof Player player && !player.level().isClientSide) {
            CompoundTag tag = pStack.getTag();

            if (tag == null || !tag.contains(STORED_SOUL)) {
                if (pTarget instanceof Animal) {
                    pTarget.addEffect(new MobEffectInstance(MobEffects.WITHER, 99999, 2));

                    CompoundTag newTag = pStack.getOrCreateTag();
                    newTag.putString(STORED_SOUL, pTarget.getName().getString());
                    newTag.putInt(SOUL_POWER, 12);
                    player.displayClientMessage(Component.translatable("system_message.eqtutmod.soul.accepted"), true);
                }
            } else {
                int power = tag.getInt(SOUL_POWER);
                pTarget.addEffect(new MobEffectInstance(MobEffects.HARM, 3, 2));
                pAttacker.addEffect(new MobEffectInstance(MobEffects.MOVEMENT_SPEED, 60, 1));

                if (power > 0) {
                    tag.putInt(SOUL_POWER, power - 1);
                } else {
                    tag.remove(STORED_SOUL);
                    player.displayClientMessage(Component.translatable("system_message.eqtutmod.soul.withered"), true);
                }
            }
        }
        return super.hurtEnemy(pStack, pTarget, pAttacker);
    }

    @Override
    public void appendHoverText(ItemStack pStack, @Nullable Level pLevel, List<Component> pTooltipComponents, TooltipFlag pIsAdvanced) {
        CompoundTag tag = pStack.getTag();
        if (tag.contains(STORED_SOUL)) {
            pTooltipComponents.add(Component.literal(Component.translatable("tooltip.item.eqtutmod.sacrificial_blade_item").getString()));
            pTooltipComponents.add(Component.literal(tag.getString(STORED_SOUL)));
        }
    }

    @Override
    public boolean isFoil(ItemStack pStack) {
        CompoundTag tag = pStack.getTag();
        return tag != null && tag.contains(STORED_SOUL);
    }
}
