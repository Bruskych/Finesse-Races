package org.bruskych.finesse_races.client.gui.screens;

import net.minecraft.client.gui.GuiGraphics;
import net.minecraft.client.gui.components.Button;
import net.minecraft.client.gui.screens.Screen;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.ResourceLocation;
import org.bruskych.finesse_races.core.FinesseRaces;
import org.bruskych.remedy_core.client.gui.GuiRenderHelper;

public class RaceSelectionScreen extends Screen {

    private static final int PANEL_WIDTH = 185;
    private static final int PANEL_HEIGHT = 275;
    private static final int PANEL_GAP = 7;
    private static final int CORNER_SIZE = 8;
    private static final int CENTER_SIZE = 1;

    private static final ResourceLocation TEXTURE = ResourceLocation.fromNamespaceAndPath(
            FinesseRaces.MOD_ID,
            "textures/gui/background.png"
    );

    public RaceSelectionScreen() {
        super(Component.translatable("gui.finesse_races.gui"));
    }

    @Override
    protected void init() {
        super.init();

        int totalWidth = PANEL_WIDTH * 3 + PANEL_GAP * 2;

        int startX = (this.width - totalWidth) / 2;
        int startY = (this.height - PANEL_HEIGHT) / 2;

        int buttonWidth = 80;
        int buttonHeight = 20;

        int buttonX = startX
                + PANEL_WIDTH
                + PANEL_GAP
                + (PANEL_WIDTH - buttonWidth) / 2;

        int buttonY = startY + PANEL_HEIGHT + 10;

        this.addRenderableWidget(
                Button.builder(
                        Component.translatable("gui.finesse_races.choose"),
                        button -> this.onClose()
                ).bounds(
                        buttonX,
                        buttonY,
                        buttonWidth,
                        buttonHeight
                ).build()
        );
    }

    @Override
    public void render(GuiGraphics graphics, int mouseX, int mouseY, float partialTick) {
        this.renderBackground(graphics);

        int totalWidth = PANEL_WIDTH * 3 + PANEL_GAP * 2;

        int startX = (this.width - totalWidth) / 2;
        int startY = (this.height - PANEL_HEIGHT) / 2;

        // Левая панель
        GuiRenderHelper.renderNineSlice(
                graphics,
                TEXTURE,
                startX,
                startY,
                PANEL_WIDTH,
                PANEL_HEIGHT,
                0,
                0,
                CORNER_SIZE,
                CENTER_SIZE
        );

        // Центральная панель
        GuiRenderHelper.renderNineSlice(
                graphics,
                TEXTURE,
                startX + PANEL_WIDTH + PANEL_GAP,
                startY,
                PANEL_WIDTH,
                PANEL_HEIGHT,
                0,
                17,
                CORNER_SIZE,
                CENTER_SIZE
        );

        // Правая панель
        GuiRenderHelper.renderNineSlice(
                graphics,
                TEXTURE,
                startX + (PANEL_WIDTH + PANEL_GAP) * 2,
                startY,
                PANEL_WIDTH,
                PANEL_HEIGHT,
                0,
                34,
                CORNER_SIZE,
                CENTER_SIZE
        );

        super.render(graphics, mouseX, mouseY, partialTick);
    }

    @Override
    public boolean shouldCloseOnEsc() {
        // Запрещает закрывать Screen при помощи клавиши ESC.
        return false;
    }
}