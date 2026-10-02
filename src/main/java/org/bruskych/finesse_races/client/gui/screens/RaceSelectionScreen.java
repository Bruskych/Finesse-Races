package org.bruskych.finesse_races.client.gui.screens;

import net.minecraft.client.gui.GuiGraphics;
import net.minecraft.client.gui.components.Button;
import net.minecraft.client.gui.components.ImageButton;
import net.minecraft.client.gui.screens.Screen;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.ResourceLocation;

import org.bruskych.finesse_races.client.gui.widgets.AbilityCardWidget;
import org.bruskych.finesse_races.common.network.FRNetwork;
import org.bruskych.finesse_races.common.network.RaceSelectionC2SPacket;
import org.bruskych.finesse_races.gameplay.abilities.core.RaceAbility;
import org.bruskych.finesse_races.gameplay.races.core.AbstractRace;
import org.bruskych.finesse_races.gameplay.races.core.RaceManager;
import org.bruskych.remedy_core.client.gui.GuiRenderHelper;
import org.bruskych.remedy_core.client.gui.widgets.PanelWidget;
import org.bruskych.finesse_races.core.FinesseRaces;

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

    private List<AbstractRace> availableRaces;
    private int currentRaceIndex = 0;

    private int leftPageIndex = 0;
    private int centerPageIndex = 0;

    private ImageButton leftPrevBtn, leftNextBtn;
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
        this.panelHeight = Math.max(150, Math.min(maxPanelHeight, this.height - 50));

        int totalWidth = (this.panelWidth * 3) + (PANEL_GAP * 2);
        int startX = (this.width - totalWidth) / 2;
        int startY = (this.height - this.panelHeight) / 2 - 10;

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

        // Global previous race button (Left side)
        this.addRenderableWidget(new ImageButton(
                startX - navBtnSize - sideNavMargin, navBtnY, navBtnSize, navBtnSize,
                184, 0, navBtnSize, TEXTURE_BUTT, 256, 256,
                btn -> switchRace(-1)
        ));

        // Global next race button (Right side)
        this.addRenderableWidget(new ImageButton(
                startX + totalWidth + sideNavMargin, navBtnY, navBtnSize, navBtnSize,
                204, 0, navBtnSize, TEXTURE_BUTT, 256, 256,
                btn -> switchRace(1)
        ));

        Component confirmText = Component.translatable("gui.finesse_races.button.choose_race");
        Component postponeText = Component.translatable("gui.finesse_races.button.postpone_the_choice");

        int rawConfirmWidth = this.font.width(confirmText) + 20;
        int rawPostponeWidth = this.font.width(postponeText) + 20;
        int finalBtnWidth = Math.max(rawConfirmWidth, rawPostponeWidth);

        int bottomGap = 10;
        int buttonHeight = 20;

        int bottomGroupWidth = finalBtnWidth + bottomGap + finalBtnWidth;
        int bottomStartX = startX + (totalWidth - bottomGroupWidth) / 2;
        int bottomY = startY + this.panelHeight + margin;

        int currentX = bottomStartX;

        // Confirm button - Send selected race packet to server
        this.addRenderableWidget(Button.builder(confirmText, btn -> {
            if (!availableRaces.isEmpty()) {
                AbstractRace selectedRace = availableRaces.get(currentRaceIndex);
                FRNetwork.sendToServer(new RaceSelectionC2SPacket(selectedRace.getId()));
                this.onClose();
            }
        }).bounds(currentX, bottomY, finalBtnWidth, buttonHeight).build());

        currentX += finalBtnWidth + bottomGap;

        // Postpone button - Close choice screen
        this.addRenderableWidget(Button.builder(postponeText, btn -> this.onClose())
                .bounds(currentX, bottomY, finalBtnWidth, buttonHeight).build());

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

        boolean canLeftPrev = leftPageIndex > 0;
        boolean canLeftNext = leftPageIndex < allPages.size() - 1;

        // Left panel - Previous page button
        leftPrevBtn = new ImageButton(
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

        // Left panel - Next page button
        leftNextBtn = new ImageButton(
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

            int cardWidth = this.panelWidth - (margin * 2);
            int innerPadding = 5;
            int textWidth = cardWidth - (innerPadding * 2);
            int currentRelY = margin + btnSize + 6;

            for (RaceAbility ability : currentAbilities) {
                int linesCount = this.font.split(ability.getDescription(), textWidth).size();
                int textHeight = linesCount * this.font.lineHeight;
                int cardHeight = innerPadding + 9 + 3 + textHeight + innerPadding;

                // Creating a full-fledged card widget
                AbilityCardWidget cardWidget = new AbilityCardWidget(
                        leftPanel.getX() + margin,
                        leftPanel.getY() + currentRelY,
                        cardWidth, cardHeight,
                        ability, TEXTURE_BACK
                );
                leftPanel.addChild(margin, currentRelY, cardWidget);

                currentRelY += cardHeight + 4;
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
    }

    /**
     * Main render loop: draws panels, header plates, text, and ability cards
     */
    @Override
    public void render(GuiGraphics graphics, int mouseX, int mouseY, float partialTick) {
        this.renderBackground(graphics);

        // Panels render all their child widgets (including our new AbilityCardWidgets) automatically!
        leftPanel.render(graphics, mouseX, mouseY, partialTick);
        centerPanel.render(graphics, mouseX, mouseY, partialTick);
        rightPanel.render(graphics, mouseX, mouseY, partialTick);

        int gapBetweenButtonHeader = margin - 5;
        int headerHeight = btnSize;
        int headerRelX = margin + btnSize + gapBetweenButtonHeader;
        int headerWidth = this.panelWidth - (margin * 2) - (btnSize * 2) - (gapBetweenButtonHeader * 2);

        GuiRenderHelper.renderNineSlice(
                graphics, TEXTURE_BACK,
                leftPanel.getX() + headerRelX,
                leftPanel.getY() + margin,
                headerWidth, headerHeight,
                17, 0,
                CORNER_PLATE_SIZE, CENTER_SIZE
        );

        GuiRenderHelper.renderNineSlice(
                graphics, TEXTURE_BACK,
                centerPanel.getX() + headerRelX,
                centerPanel.getY() + margin,
                headerWidth, headerHeight,
                17, 17,
                CORNER_PLATE_SIZE, CENTER_SIZE
        );

        super.render(graphics, mouseX, mouseY, partialTick);

        if (!availableRaces.isEmpty()) {
            List<List<RaceAbility>> allPages = getAllAbilityPages();
            renderPageText(graphics, leftPanel.getX() + headerRelX, leftPanel.getY() + margin, headerWidth, headerHeight, leftPageIndex, allPages.size());
        }
    }

    /**
     * Renders localized page counter text centered inside header plates
     */
    private void renderPageText(GuiGraphics graphics, int x, int y, int width, int height, int pageIndex, int totalPages) {
        int maxPages = Math.max(1, totalPages);
        int currentPage = Math.min(pageIndex + 1, maxPages);

        Component pageComponent = Component.translatable("gui.finesse_races.page", currentPage, maxPages);

        int strWidth = this.font.width(pageComponent);
        int textX = x + (width - strWidth) / 2;
        int textY = y + (height - this.font.lineHeight) / 2 + 1;

        graphics.drawString(this.font, pageComponent, textX, textY, VANILLA_WHITE, false);
    }

    /**
     * Renders ability cards with dynamic background height based on wrapped description
     */
    private void renderAbilityCards(GuiGraphics graphics, int panelInnerX, int startY, List<RaceAbility> abilities) {
        int cardWidth = this.panelWidth - (margin * 2);
        int innerPadding = 5;
        int textWidth = cardWidth - (innerPadding * 2);
        int currentY = startY;

        for (RaceAbility ability : abilities) {
            int titleColor = switch (ability.getCategory()) {
                case BUFF -> VANILLA_DARK;
                case DEBUFF -> VANILLA_DARK;
                case NEUTRAL -> VANILLA_DARK;
            };

            int linesCount = this.font.split(ability.getDescription(), textWidth).size();
            int textHeight = linesCount * this.font.lineHeight;
            int cardHeight = innerPadding + 9 + 3 + textHeight + innerPadding;

            GuiRenderHelper.renderNineSlice(
                    graphics, TEXTURE_BACK,
                    panelInnerX, currentY,
                    cardWidth, cardHeight,
                    0, 51,
                    CORNER_PLATE_SIZE, CENTER_SIZE
            );

            graphics.drawString(
                    this.font,
                    ability.getTitle(),
                    panelInnerX + innerPadding,
                    currentY + innerPadding,
                    titleColor, false
            );

            graphics.drawWordWrap(
                    this.font,
                    ability.getDescription(),
                    panelInnerX + innerPadding,
                    currentY + innerPadding + 12,
                    textWidth, VANILLA_DARK
            );

            currentY += cardHeight + 4;
        }
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
        int maxAvailableHeight = this.panelHeight - (margin * 2) - btnSize - 10;

        List<RaceAbility> currentPage = new ArrayList<>();
        int currentHeight = 0;

        for (RaceAbility ability : allAbilities) {
            int linesCount = this.font.split(ability.getDescription(), textWidth).size();
            int cardHeight = (innerPadding * 2) + 12 + (linesCount * this.font.lineHeight);

            if (!currentPage.isEmpty() && (currentHeight + cardHeight + 4) > maxAvailableHeight) {
                pages.add(currentPage);
                currentPage = new ArrayList<>();
                currentHeight = 0;
            }

            currentPage.add(ability);
            currentHeight += cardHeight + 4;
        }

        if (!currentPage.isEmpty()) {
            pages.add(currentPage);
        }

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
        return true;
    }
}