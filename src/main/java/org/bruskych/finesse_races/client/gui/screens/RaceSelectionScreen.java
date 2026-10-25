package org.bruskych.finesse_races.client.gui.screens;

import net.minecraft.client.gui.GuiGraphics;
import net.minecraft.client.gui.components.ImageButton;
import net.minecraft.client.gui.screens.Screen;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.ResourceLocation;

import org.bruskych.finesse_races.client.gui.widgets.AbilityCardWidget;
import org.bruskych.finesse_races.client.gui.widgets.HeaderPlateWidget;
import org.bruskych.finesse_races.client.gui.widgets.StoryCardWidget;
import org.bruskych.finesse_races.common.network.FRNetwork;
import org.bruskych.finesse_races.common.network.RaceSelectionC2SPacket;
import org.bruskych.finesse_races.gameplay.abilities.core.RaceAbility;
import org.bruskych.finesse_races.gameplay.races.core.AbstractRace;
import org.bruskych.finesse_races.gameplay.races.core.RaceManager;
import org.bruskych.finesse_races.client.gui.widgets.DotPaginationWidget;
import org.bruskych.finesse_races.core.FinesseRaces;
import org.bruskych.remedy_core.client.gui.widgets.NineSliceButton;
import org.bruskych.remedy_core.client.gui.widgets.PanelWidget;
import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

public class RaceSelectionScreen extends Screen {

    private static final ResourceLocation TEXTURE_BACK = ResourceLocation.fromNamespaceAndPath(
            FinesseRaces.MOD_ID, "textures/gui/background.png"
    );
    private static final ResourceLocation TEXTURE_BUTT = ResourceLocation.fromNamespaceAndPath(
            FinesseRaces.MOD_ID, "textures/gui/buttons.png"
    );

    private static final int PANEL_GAP = 7;
    private static final int CORNER_PLATE_SIZE = 4;
    private static final int CORNER_SIZE = 8;
    private static final int CENTER_SIZE = 1;

    private int panelWidth;
    private int panelHeight;

    private PanelWidget leftPanel;
    private PanelWidget centerPanel;
    private PanelWidget rightPanel;

    int btnSize = 12;
    int margin = 8;

    int VANILLA_WHITE = 0xFFFFFF;
    int VANILLA_DARK = 0x404040;

    private DotPaginationWidget paginationWidget;
    private List<AbstractRace> availableRaces;
    private int currentRaceIndex = 0;

    private int leftPageIndex = 0;
    private int centerPageIndex = 0;

    private ImageButton centerPrevBtn, centerNextBtn;

    /**
     * Constructor: sets the title of the screen
     */
    public RaceSelectionScreen() {
        super(Component.translatable("gui.finesse_races.gui"));
    }

    /**
     * Called when the screen opens or resizes to initialize widgets and layouts
     */
    @Override
    protected void init() {
        super.init();

        this.availableRaces = RaceManager
                .getAllRaces()
                .stream()
                .sorted(Comparator.comparingInt(AbstractRace::getDisplayOrder))
                .toList();

        int navBtnSize = 20;
        int maxPanelWidth = 170;
        int maxPanelHeight = 250;
        int sideNavMargin = 10;

        int reservedSideSpace = (navBtnSize + sideNavMargin) * 2 + 20;
        int availableWidth = this.width - reservedSideSpace;

        this.panelWidth = Math.max(110, Math.min(maxPanelWidth, (availableWidth - (PANEL_GAP * 2)) / 3));
        this.panelHeight = Math.max(150, Math.min(maxPanelHeight, this.height - 85));

        int totalWidth = (this.panelWidth * 3) + (PANEL_GAP * 2);
        int startX = (this.width - totalWidth) / 2;
        int startY = (this.height - this.panelHeight) / 2 - 15;

        leftPanel = new PanelWidget(
                TEXTURE_BACK, startX, startY,
                this.panelWidth, this.panelHeight,
                0, 0,
                CORNER_SIZE, CENTER_SIZE
        );

        centerPanel = new PanelWidget(
                TEXTURE_BACK, startX + this.panelWidth + PANEL_GAP, startY,
                this.panelWidth, this.panelHeight,
                0, 17,
                CORNER_SIZE, CENTER_SIZE
        );

        rightPanel = new PanelWidget(
                TEXTURE_BACK, startX + (this.panelWidth + PANEL_GAP) * 2, startY,
                this.panelWidth, this.panelHeight,
                0, 34,
                CORNER_SIZE, CENTER_SIZE
        );

        int navBtnY = startY + (this.panelHeight / 2) - (navBtnSize / 2);

        // Global previous race button (Left side) "<<<"
        this.addRenderableWidget(new ImageButton(
                startX - navBtnSize - sideNavMargin, navBtnY, navBtnSize, navBtnSize,
                216, 0, navBtnSize, TEXTURE_BUTT, 256, 256,
                btn -> switchRace(-1)
        ));

        // Global next race button (Right side) ">>>"
        this.addRenderableWidget(new ImageButton(
                startX + totalWidth + sideNavMargin, navBtnY, navBtnSize, navBtnSize,
                236, 0, navBtnSize, TEXTURE_BUTT, 256, 256,
                btn -> switchRace(1)
        ));

        // Create Pagination
        int dotWidth = 12;
        int dotHeight = 8;
        int dotSpacing = 4;
        int paginationY = startY + this.panelHeight + margin;

        this.paginationWidget = new DotPaginationWidget(
                startX, paginationY, totalWidth,
                availableRaces.size(), currentRaceIndex,
                dotWidth, dotHeight, dotSpacing, 24, 0,
                256, 256, TEXTURE_BUTT,
                selectedRaceIndex -> {
                    this.currentRaceIndex = selectedRaceIndex;
                    this.leftPageIndex = 0;
                    updatePanelButtons();
                    this.paginationWidget.setCurrentPage(this.currentRaceIndex);
                }
        );
        this.addRenderableWidget(paginationWidget);

        // BUTTON - SELECT RACE
        Component confirmText = Component.translatable("gui.finesse_races.button.choose_race");

        int calculatedBtnWidth = Math.max(120, this.font.width(confirmText) + 20);
        int buttonHeight = 20;

        // X + Y coords
        int bottomStartX = startX + (totalWidth - calculatedBtnWidth) / 2;
        int bottomY = paginationWidget.getY() + paginationWidget.getHeight() + margin;

        this.addRenderableWidget(NineSliceButton.builder(
                confirmText, TEXTURE_BUTT, btn -> {
                    if (!availableRaces.isEmpty()) {
                        AbstractRace selectedRace = availableRaces.get(currentRaceIndex);
                        FRNetwork.sendToServer(new RaceSelectionC2SPacket(selectedRace.getId()));
                        this.onClose();
                    }
                })
                .bounds(bottomStartX, bottomY, calculatedBtnWidth, buttonHeight)
                .sliceParams(9, 2).uv(196, 0)
                .textColors(VANILLA_WHITE, 0x555555)
                .build());

        updatePanelButtons();
    }

    /**
     * Updates internal panel pagination buttons and their active/disabled textures
     */
    private void updatePanelButtons() {
        leftPanel.clearChildren();
        centerPanel.clearChildren();
        rightPanel.clearChildren();

        List<List<RaceAbility>> allPages = getAllAbilityPages();

        int gapBetweenButtonHeader = margin - 2;
        int headerHeight = btnSize;
        int headerRelX = margin + btnSize + gapBetweenButtonHeader;
        int headerWidth = this.panelWidth - (margin * 2) - (btnSize * 2) - (gapBetweenButtonHeader * 2);
        int subtitleGap = 4;
        int subtitleRelY = margin + headerHeight + subtitleGap;
        int cardWidth = this.panelWidth - (margin * 2);

        // PLATE "PAGE 1 / 2"
        int maxPages = Math.max(1, allPages.size());
        int currentPageNum = Math.min(leftPageIndex + 1, maxPages);
        Component pageComponent = Component.translatable("gui.finesse_races.page", currentPageNum, maxPages);

        leftPanel.addChild(headerRelX, margin, new HeaderPlateWidget(
                leftPanel.getX() + headerRelX, leftPanel.getY() + margin,
                headerWidth, headerHeight, pageComponent,
                TEXTURE_BACK, 17, 0, true, VANILLA_WHITE
        ));

        // PLATE "ABILITIES"
        Component abilitiesText = Component.translatable("gui.finesse_races.abilities");
        leftPanel.addChild(headerRelX, subtitleRelY, new HeaderPlateWidget(
                leftPanel.getX() + headerRelX, leftPanel.getY() + subtitleRelY,
                headerWidth, headerHeight, abilitiesText,
                TEXTURE_BACK, 17, 0, true, VANILLA_WHITE
        ));

        if (!availableRaces.isEmpty()) {
            AbstractRace currentRace = availableRaces.get(currentRaceIndex);

            // PLATE "NAME RACE"
            centerPanel.addChild(headerRelX, margin, new HeaderPlateWidget(
                    centerPanel.getX() + headerRelX, centerPanel.getY() + margin,
                    headerWidth, headerHeight, currentRace.getDisplayName(),
                    TEXTURE_BACK, currentRace.getPlateU(), currentRace.getPlateV(), true, VANILLA_WHITE
            ));

            // PLATE "RACE STORY"
            Component storyText = Component.translatable("gui.finesse_races.race_story");
            centerPanel.addChild(headerRelX, subtitleRelY, new HeaderPlateWidget(
                    centerPanel.getX() + headerRelX, centerPanel.getY() + subtitleRelY,
                    headerWidth, headerHeight, storyText,
                    TEXTURE_BACK, 17, 17, true, VANILLA_WHITE
            ));

            // CARD "STORY"
            int storyCardY = subtitleRelY + headerHeight + 4;
            int maxStoryHeight = this.panelHeight - storyCardY - margin;

            StoryCardWidget storyWidget = new StoryCardWidget(
                    centerPanel.getX() + margin,
                    centerPanel.getY() + storyCardY,
                    cardWidth, maxStoryHeight,
                    currentRace,
                    TEXTURE_BACK
            );
            centerPanel.addChild(margin, storyCardY, storyWidget);
        }

        boolean canLeftPrev = leftPageIndex > 0;
        boolean canLeftNext = leftPageIndex < allPages.size() - 1;

        // Left panel - Previous page button "<<<"
        ImageButton leftPrevBtn = new ImageButton(
                0, 0, btnSize, btnSize,
                0, 0, btnSize,
                TEXTURE_BUTT, 256, 256,
                btn -> {
                    if (leftPageIndex > 0) {
                        leftPageIndex--;
                        updatePanelButtons();
                    }
                }
        );
        leftPrevBtn.active = canLeftPrev;
        leftPanel.addChild(margin, margin, leftPrevBtn);

        // Left panel - Next page button ">>>"
        ImageButton leftNextBtn = new ImageButton(
                0, 0, btnSize, btnSize,
                12, 0, btnSize,
                TEXTURE_BUTT, 256, 256,
                btn -> {
                    if (leftPageIndex < allPages.size() - 1) {
                        leftPageIndex++;
                        updatePanelButtons();
                    }
                }
        );
        leftNextBtn.active = canLeftNext;
        leftPanel.addChild(leftPanel.getWidth() - btnSize - margin, margin, leftNextBtn);

        // Adding cards to the left panel (Abilities)
        if (!allPages.isEmpty() && leftPageIndex < allPages.size()) {
            List<RaceAbility> currentAbilities = allPages.get(leftPageIndex);
            int currentRelY = subtitleRelY + headerHeight + 4;

            for (RaceAbility ability : currentAbilities) {
                int maxAllowedHeight = this.panelHeight - currentRelY - margin;

                AbilityCardWidget cardWidget = new AbilityCardWidget(
                        leftPanel.getX() + margin,
                        leftPanel.getY() + currentRelY,
                        cardWidth, maxAllowedHeight, ability, TEXTURE_BACK
                );
                leftPanel.addChild(margin, currentRelY, cardWidget);

                currentRelY += cardWidget.getHeight() + 4;
            }
        }
    }

    /**
     * Switches the selected race index and resets pagination
     */
    private void switchRace(int direction) {
        if (availableRaces.isEmpty()) return;
        currentRaceIndex += direction;

        if (currentRaceIndex < 0) {
            currentRaceIndex = availableRaces.size() - 1;
        } else if (currentRaceIndex >= availableRaces.size()) {
            currentRaceIndex = 0;
        }

        leftPageIndex = 0;
        centerPageIndex = 0;
        updatePanelButtons();

        // Update Pagination
        if (this.paginationWidget != null) {
            this.paginationWidget.setCurrentPage(currentRaceIndex);
        }
    }

    /**
     * Main render loop: draws panels, header plates, text, and ability cards
     */
    @Override
    public void render(@NotNull GuiGraphics graphics, int mouseX, int mouseY, float partialTick) {
        this.renderBackground(graphics);

        // Панели сами отрисуют свои виджеты (плашки, кнопки и карточки способностей)
        leftPanel.render(graphics, mouseX, mouseY, partialTick);
        centerPanel.render(graphics, mouseX, mouseY, partialTick);
        rightPanel.render(graphics, mouseX, mouseY, partialTick);

        super.render(graphics, mouseX, mouseY, partialTick);
    }

    /**
     * Splits ALL race abilities into paginated lists sorted by category priority (BUFF -> NEUTRAL -> DEBUFF)
     */
    private List<List<RaceAbility>> getAllAbilityPages() {
        List<List<RaceAbility>> pages = new ArrayList<>();
        if (availableRaces.isEmpty()) return pages;

        AbstractRace currentRace = availableRaces.get(currentRaceIndex);

        // Sort abilities: BUFF (0) -> NEUTRAL (1) -> DEBUFF (2)
        List<RaceAbility> allAbilities = currentRace.getAbilities().stream()
                .sorted(Comparator.comparingInt(a -> a.getCategory().getPriority()))
                .toList();

        if (allAbilities.isEmpty()) return pages;

        int cardWidth = this.panelWidth - (margin * 2);
        int innerPadding = 5;
        int textWidth = cardWidth - (innerPadding * 2);

        // Bottom margin
        int reservedTopSpace = margin + btnSize + 4 + btnSize + 6;
        int maxAvailableHeight = this.panelHeight - reservedTopSpace - margin;

        List<RaceAbility> currentPage = new ArrayList<>();
        int currentHeight = 0;

        for (RaceAbility ability : allAbilities) {
            int baseHeight = AbilityCardWidget.estimateBaseHeight(ability, cardWidth, this.font);
            int minRequiredHeight = baseHeight + (this.font.lineHeight * 2);
            if (!currentPage.isEmpty() && (currentHeight + minRequiredHeight) > maxAvailableHeight) {
                pages.add(currentPage);
                currentPage = new ArrayList<>();
                currentHeight = 0;
            }

            int totalLines = this.font.split(ability.getDescription(), textWidth).size();
            int requiredFullHeight = baseHeight + (totalLines * this.font.lineHeight);
            int heightToGive = Math.min(requiredFullHeight, maxAvailableHeight - currentHeight);

            currentPage.add(ability);
            currentHeight += heightToGive + 4;
        }
        pages.add(currentPage);

        return pages;
    }

    /**
     * Handles mouse click events for interactive panel widgets
     */
    @Override
    public boolean mouseClicked(double mouseX, double mouseY, int button) {
        if (leftPanel.mouseClicked(mouseX, mouseY, button) ||
                centerPanel.mouseClicked(mouseX, mouseY, button) ||
                rightPanel.mouseClicked(mouseX, mouseY, button)) {
            return true;
        }
        return super.mouseClicked(mouseX, mouseY, button);
    }

    /**
     * Determines if pressing ESC should close the screen
     */
    @Override
    public boolean shouldCloseOnEsc() {
        return false;
    }
}