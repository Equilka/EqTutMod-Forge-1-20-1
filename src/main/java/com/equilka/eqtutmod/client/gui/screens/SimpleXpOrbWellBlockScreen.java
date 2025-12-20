package com.equilka.eqtutmod.client.gui.screens;

import com.equilka.eqtutmod.world.inventory.SimpleXpOrbWellBlockMenu;
import net.minecraft.client.gui.GuiGraphics;
import net.minecraft.client.gui.screens.inventory.AbstractContainerScreen;
import net.minecraft.network.chat.Component;
import net.minecraft.world.entity.player.Inventory;

public class SimpleXpOrbWellBlockScreen extends AbstractContainerScreen<SimpleXpOrbWellBlockMenu> {
    public SimpleXpOrbWellBlockScreen(SimpleXpOrbWellBlockMenu pMenu, Inventory pPlayerInventory, Component pTitle) {
        super(pMenu, pPlayerInventory, pTitle);
    }

    @Override
    protected void renderLabels(GuiGraphics gfx, int mouseX, int mouseY) {
        gfx.drawString(font, "XP: " + menu.getStoredXp(), 10, 10, 0xFFFFFF);
    }

    @Override
    protected void renderBg(GuiGraphics pGuiGraphics, float pPartialTick, int pMouseX, int pMouseY) {
        renderBackground(pGuiGraphics);
    }
}
