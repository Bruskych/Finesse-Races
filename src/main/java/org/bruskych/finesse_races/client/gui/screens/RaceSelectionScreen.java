package org.bruskych.finesse_races.client.gui.screens;

import net.minecraft.client.gui.GuiGraphics;
import net.minecraft.client.gui.components.Button;
import net.minecraft.client.gui.components.ImageButton;
import net.minecraft.client.gui.screens.Screen;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.ResourceLocation;

import org.bruskych.remedy_core.client.gui.GuiRenderHelper;
import org.bruskych.remedy_core.client.gui.widgets.PanelWidget;
import org.bruskych.finesse_races.core.FinesseRaces;

public class RaceSelectionScreen extends Screen {

    private static final ResourceLocation TEXTURE_BACK = ResourceLocation.fromNamespaceAndPath(
            FinesseRaces.MOD_ID, "textures/gui/background.png"
    );
    private static final ResourceLocation TEXTURE_BUTT = ResourceLocation.fromNamespaceAndPath(
            FinesseRaces.MOD_ID, "textures/gui/buttons.png"
    );

    private static final int PANEL_GAP = 7;
    private static final int CORNER_PLATE_SIZE = 5;
    private static final int CORNER_SIZE = 8;
    private static final int CENTER_SIZE = 1;

    private int panelWidth;
    private int panelHeight;

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

        int maxPanelWidth = 170;
        int maxPanelHeight = 250;
        int navBtnSize = 20;
        int sideNavMargin = 10;

        // Хранит доступную ширину
        int reservedSideSpace = (navBtnSize + sideNavMargin) * 2 + 20;
        int availableWidth = this.width - reservedSideSpace;

        // Резиновая ширина и высота
        this.panelWidth = Math.max(110, Math.min(maxPanelWidth, (availableWidth - (PANEL_GAP * 2)) / 3));
        this.panelHeight = Math.max(150, Math.min(maxPanelHeight, this.height - 50));

        // Центрирование общей конструкции
        int totalWidth = (this.panelWidth * 3) + (PANEL_GAP * 2);
        int startX = (this.width - totalWidth) / 2;

        // Чуть приподнимаем вверх, чтобы внизу точно влезли кнопки подтверждения
        int startY = (this.height - this.panelHeight) / 2 - 10;

        // Левая панель
        leftPanel = new PanelWidget(
                TEXTURE_BACK, startX, startY,
                this.panelWidth, this.panelHeight,
                0, 0,
                CORNER_SIZE, CENTER_SIZE
        );

        // Лево - Кнопка назад
        leftPanel.addChild(margin, margin, new ImageButton(
                0, 0, btnSize, btnSize,
                224, 0, 16,
                TEXTURE_BUTT, 256, 256,
                btn -> {
                    // TODO: Логика кнопки
                })
        );

        // Лево - Кнопка вперед
        leftPanel.addChild(leftPanel.getWidth() - btnSize - margin, margin, new ImageButton(
                0, 0, btnSize, btnSize,
                240, 0, 16,
                TEXTURE_BUTT, 256, 256,
                btn -> {
                    // TODO: Логика кнопки
                })
        );

        // Центральная панель
        centerPanel = new PanelWidget(
                TEXTURE_BACK, startX + this.panelWidth + PANEL_GAP, startY,
                this.panelWidth, this.panelHeight,
                0, 17,
                CORNER_SIZE, CENTER_SIZE
        );

        // Центр - Кнопка назад
        centerPanel.addChild(margin, margin, new ImageButton(
                0, 0, btnSize, btnSize,
                224, 0, 16,
                TEXTURE_BUTT, 256, 256,
                btn -> {
                    // TODO: Логика кнопки
                })
        );

        // Центр - Кнопка вперед
        centerPanel.addChild(leftPanel.getWidth() - btnSize - margin, margin, new ImageButton(
                0, 0, btnSize, btnSize,
                240, 0, 16,
                TEXTURE_BUTT, 256, 256,
                btn -> {
                    // TODO: Логика кнопки
                })
        );

        // Правая панель
        rightPanel = new PanelWidget(
                TEXTURE_BACK, startX + (this.panelWidth + PANEL_GAP) * 2, startY,
                this.panelWidth, this.panelHeight,
                0, 34,
                CORNER_SIZE, CENTER_SIZE
        );

        // Хранит центр панели (по вертикали)
        int navBtnY = startY + (this.panelHeight / 2) - (navBtnSize / 2);

        // Кнопка назад
        this.addRenderableWidget(new ImageButton(
                startX - navBtnSize - sideNavMargin, navBtnY, navBtnSize, navBtnSize,
                184, 0, 20, TEXTURE_BUTT, 256, 256,
                btn -> {
                    // TODO: Логика влево
                }
        ));

        // Кнопка вперед
        this.addRenderableWidget(new ImageButton(
                startX + totalWidth + sideNavMargin, navBtnY, navBtnSize, navBtnSize,
                204, 0, 20, TEXTURE_BUTT, 256, 256,
                btn -> {
                    // TODO: Логика вправо
                }
        ));

        Component confirmText = Component.translatable("gui.finesse_races.button.choose_race");
        Component postponeText = Component.translatable("gui.finesse_races.button.postpone_the_choice");

        // Динамическая ширина кнопок (внизу screen)
        int rawConfirmWidth = this.font.width(confirmText) + 20;
        int rawPostponeWidth = this.font.width(postponeText) + 20;

        int finalBtnWidth = Math.max(rawConfirmWidth, rawPostponeWidth);

        int bottomGap = 10;
        int buttonHeight = 20;

        // Общая ширина группы
        int bottomGroupWidth = finalBtnWidth + bottomGap + finalBtnWidth;
        int bottomStartX = startX + (totalWidth - bottomGroupWidth) / 2;
        int bottomY = startY + this.panelHeight + margin;

        int currentX = bottomStartX;

        // Кнопка - Выбрать расу
        this.addRenderableWidget(Button.builder(confirmText, btn -> {
            // TODO: Применить выбранную расу на игрока
        }).bounds(currentX, bottomY, finalBtnWidth, buttonHeight).build());

        currentX += finalBtnWidth + bottomGap;

        // Кнопка - Отложить
        this.addRenderableWidget(Button.builder(postponeText, btn -> {
            // TODO: Закрыть этот экран, открыть экран с доп. подтверждением
        }).bounds(currentX, bottomY, finalBtnWidth, buttonHeight).build());
    }

    @Override
    public void render(GuiGraphics graphics, int mouseX, int mouseY, float partialTick) {
        this.renderBackground(graphics);

        // Рисует панели
        leftPanel.render(graphics, mouseX, mouseY, partialTick);
        centerPanel.render(graphics, mouseX, mouseY, partialTick);
        rightPanel.render(graphics, mouseX, mouseY, partialTick);

        // Параметры плашки (фон текста сверху 1+2 панелей)
        int gapBetweenButtonHeader = 4;
        int headerHeight = btnSize;

        // Ширина плашки
        int headerRelX = margin + btnSize + gapBetweenButtonHeader;
        int headerWidth = this.panelWidth - (margin * 2) - (btnSize * 2) - (gapBetweenButtonHeader * 2);

        // Левая плашка
        GuiRenderHelper.renderNineSlice(
                graphics, TEXTURE_BACK,
                leftPanel.getX() + headerRelX,
                leftPanel.getY() + margin,
                headerWidth, headerHeight,
                17, 0,
                CORNER_PLATE_SIZE, CENTER_SIZE
        );

        // Центральная плашка
        GuiRenderHelper.renderNineSlice(
                graphics, TEXTURE_BACK,
                centerPanel.getX() + headerRelX,
                centerPanel.getY() + margin,
                headerWidth, headerHeight,
                17, 17,
                CORNER_PLATE_SIZE, CENTER_SIZE
        );

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
        // TODO: Сделать false в будущем
        // Запрещает закрывать Screen при помощи клавиши ESC (true = разрешить)
        return true;
    }
}