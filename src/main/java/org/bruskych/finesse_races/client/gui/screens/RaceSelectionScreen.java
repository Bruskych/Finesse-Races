package org.bruskych.finesse_races.client.gui.screens;

import net.minecraft.client.gui.GuiGraphics;
import net.minecraft.client.gui.components.ImageButton;
import net.minecraft.client.gui.screens.Screen;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.ResourceLocation;

import org.bruskych.remedy_core.client.gui.widgets.PanelWidget;
import org.bruskych.finesse_races.core.FinesseRaces;

public class RaceSelectionScreen extends Screen {

    private static final ResourceLocation TEXTURE_BACK = ResourceLocation.fromNamespaceAndPath(
            FinesseRaces.MOD_ID, "textures/gui/background.png"
    );
    private static final ResourceLocation TEXTURE_BUTT = ResourceLocation.fromNamespaceAndPath(
            FinesseRaces.MOD_ID, "textures/gui/buttons.png"
    );

    private static final int PANEL_WIDTH = 170;
    private static final int PANEL_HEIGHT = 250;
    private static final int PANEL_GAP = 7;
    private static final int CORNER_SIZE = 8;
    private static final int CENTER_SIZE = 1;

    private PanelWidget leftPanel;
    private PanelWidget centerPanel;
    private PanelWidget rightPanel;

    int btnSize = 16;
    int margin = 10;

    // Задаёт название экрана выбора расы
    public RaceSelectionScreen() {
        super(Component.translatable("gui.finesse_races.gui"));
    }

    @Override
    protected void init() {
        super.init();

        int totalWidth = (PANEL_WIDTH * 3) + (PANEL_GAP * 2);
        int startX = (this.width - totalWidth) / 2;
        int startY = (this.height - PANEL_HEIGHT) / 2;

        // Левая панель (Минусы расы)
        leftPanel = new PanelWidget(
                TEXTURE_BACK, startX, startY,
                PANEL_WIDTH, PANEL_HEIGHT,
                0, 0,
                CORNER_SIZE, CENTER_SIZE
        );

        // Лево - Кнопка "Назад"
        leftPanel.addChild(margin, margin,
                new ImageButton(
                        0, 0, btnSize, btnSize,
                        224, 0, 16,
                        TEXTURE_BUTT, 256, 256,
                        btn -> {}
                )
        );

        // Лево - Кнопка "Вперед"
        leftPanel.addChild(leftPanel.getWidth() - btnSize - margin, margin,
                new ImageButton(
                        0, 0, btnSize, btnSize,
                        240, 0, 16,
                        TEXTURE_BUTT, 256, 256,
                        btn -> {}
                )
        );

        // Центральная панель (Плюсы расы)
        centerPanel = new PanelWidget(
                TEXTURE_BACK, startX + PANEL_WIDTH + PANEL_GAP, startY,
                PANEL_WIDTH, PANEL_HEIGHT,
                0, 17,
                CORNER_SIZE, CENTER_SIZE
        );

        // Центр - Кнопка "Назад"
        centerPanel.addChild(margin, margin,
                new ImageButton(
                        0, 0, btnSize, btnSize,
                        224, 0, 16,
                        TEXTURE_BUTT, 256, 256,
                        btn -> {}
                )
        );

        // Центр - Кнопка "Вперед"
        centerPanel.addChild(leftPanel.getWidth() - btnSize - margin, margin,
                new ImageButton(
                        0, 0, btnSize, btnSize,
                        240, 0, 16,
                        TEXTURE_BUTT, 256, 256,
                        btn -> {}
                )
        );

        // Правая панель (Вид расы)
        rightPanel = new PanelWidget(
                TEXTURE_BACK, startX + (PANEL_WIDTH + PANEL_GAP) * 2, startY,
                PANEL_WIDTH, PANEL_HEIGHT,
                0, 34,
                CORNER_SIZE, CENTER_SIZE
        );
    }

    @Override
    public void render(GuiGraphics graphics, int mouseX, int mouseY, float partialTick) {
        this.renderBackground(graphics);

        leftPanel.render(graphics, mouseX, mouseY, partialTick);
        centerPanel.render(graphics, mouseX, mouseY, partialTick);
        rightPanel.render(graphics, mouseX, mouseY, partialTick);

        super.render(graphics, mouseX, mouseY, partialTick);
    }

    @Override
    public boolean mouseClicked(double mouseX, double mouseY, int button) {
        if (leftPanel.mouseClicked(mouseX, mouseY, button) ||
                centerPanel.mouseClicked(mouseX, mouseY, button) ||
                rightPanel.mouseClicked(mouseX, mouseY, button)) {
            return true;
        }
        return super.mouseClicked(mouseX, mouseY, button);
    }

    @Override
    public boolean shouldCloseOnEsc() {
        // Запрещает закрывать Screen при помощи клавиши ESC (true = разрешить)
        return false;
    }
}