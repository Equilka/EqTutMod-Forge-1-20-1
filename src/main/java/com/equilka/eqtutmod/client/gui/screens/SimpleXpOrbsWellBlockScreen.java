package com.equilka.eqtutmod.client.gui.screens;

import com.equilka.eqtutmod.EqTutMod;
import com.equilka.eqtutmod.world.inventory.SimpleXpOrbsWellBlockMenu;
import net.minecraft.client.gui.GuiGraphics;
import net.minecraft.client.gui.screens.inventory.AbstractContainerScreen;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.entity.player.Inventory;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;

@OnlyIn(Dist.CLIENT)
public class SimpleXpOrbsWellBlockScreen extends AbstractContainerScreen<SimpleXpOrbsWellBlockMenu> {
    private static final ResourceLocation TEXTURE =
            new ResourceLocation(EqTutMod.MODID, "textures/gui/simple_xp_orbs_well_block_gui.png");

    public SimpleXpOrbsWellBlockScreen(SimpleXpOrbsWellBlockMenu pMenu, Inventory pPlayerInventory, Component pTitle) {
        super(pMenu, pPlayerInventory, pTitle);
        this.imageWidth = 175;
        this.imageHeight = 166;
    }

    @Override
    protected void renderBg(GuiGraphics pGuiGraphics, float partialTicks, int mouseX, int mouseY) {
        this.renderBackground(pGuiGraphics);
        int maxXp = menu.getMaxXp();
        int storedXp = menu.getStoredXp();

        int barWidth = 105;
        int progress = maxXp == 0 ? 0 : (storedXp * barWidth) / maxXp;

        pGuiGraphics.blit(TEXTURE, leftPos, topPos, 0, 0, imageWidth, imageHeight - 9, imageWidth, imageHeight);
        pGuiGraphics.drawString(font, Component.translatable("block.eqtutmod.simple_xp_orbs_well_block.ui.orbs_count").getString() + " " + storedXp,
                leftPos + 8, topPos + 15, 0x3C3C3C, false);

        if (progress > 0) {
            pGuiGraphics.blit(TEXTURE,
                    leftPos + 37, topPos + 66,
                    0, 245,
                    progress, 6
            );
        }
    }
}
