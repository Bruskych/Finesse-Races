package org.bruskych.finesse_races.client.gui.widgets;

import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.Font;
import net.minecraft.client.gui.GuiGraphics;
import net.minecraft.client.gui.components.AbstractWidget;
import net.minecraft.client.gui.narration.NarrationElementOutput;
import net.minecraft.client.sounds.SoundManager;
import net.minecraft.client.resources.sounds.SimpleSoundInstance;
import net.minecraft.client.gui.screens.inventory.AbstractContainerScreen;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.util.FormattedCharSequence;
import net.minecraft.world.item.ItemStack;

import org.bruskych.finesse_races.core.FinesseRaces;
import org.bruskych.finesse_races.gameplay.abilities.core.RaceAbility;
import org.bruskych.remedy_core.client.gui.GuiRenderHelper;
import org.jetbrains.annotations.NotNull;

import java.util.List;

/**
 * A graphical user interface (GUI) widget that displays a "card" for a specific race ability.
 * It renders the ability's title, category icon, description (with pagination if needed),
 * and optional display items (e.g., items required or produced by the ability).
 * Extends Minecraft's {@link AbstractWidget} to handle rendering and mouse interactions.
 */
public class AbilityCardWidget extends AbstractWidget {

    private static final int CORNER_PLATE_SIZE = 4;
    private static final int CENTER_SIZE = 1;

    // Size configuration for various GUI elements
    private static final int BTN_SIZE = 12;
    private static final int ITEM_SIZE = 18;
    private static final int MAX_DISPLAY_ITEMS = 10;

    private static final ResourceLocation TEXTURE_BUTT = ResourceLocation.fromNamespaceAndPath(
            FinesseRaces.MOD_ID, "textures/gui/buttons.png"
    );
    private static final ResourceLocation TEXTURE_WIDGETS = ResourceLocation.fromNamespaceAndPath(
            FinesseRaces.MOD_ID, "textures/gui/widgets.png"
    );

    int VANILLA_WHITE = 0xFFFFFF;
    int VANILLA_DARK = 0x404040;

    private final RaceAbility ability;
    private final ResourceLocation textureBack;

    private final List<FormattedCharSequence> allLines;
    private final List<ItemStack> displayItems;

    private int currentPage = 0;
    private final int totalPages;
    private final boolean hasWidgetRow;
    private final int maxLinesPerPage;

    /**
     * Constructs a new AbilityCardWidget.
     * Calculates required dimensions, handles text wrapping, and determines pagination
     * based on the provided maximum allowed height.
     *
     * @param x                The X coordinate of the widget on the screen.
     * @param y                The Y coordinate of the widget on the screen.
     * @param width            The total width of the widget.
     * @param maxAllowedHeight The maximum height this widget is allowed to occupy.
     * @param ability          The {@link RaceAbility} data object to display.
     * @param textureBack      The ResourceLocation of the background texture.
     */
    public AbilityCardWidget(int x, int y, int width, int maxAllowedHeight, RaceAbility ability, ResourceLocation textureBack) {
        super(x, y, width, 0, ability.getTitle()); // Height is calculated dynamically below
        this.ability = ability;
        this.textureBack = textureBack;

        Font font = Minecraft.getInstance().font;
        int innerPadding = 5;
        int textWidth = width - (innerPadding * 2);

        // Split the description text into lines that fit the widget width
        this.allLines = font.split(ability.getDescription(), textWidth);
        this.displayItems = ability.getDisplayItems().stream().limit(MAX_DISPLAY_ITEMS).toList();

        // Calculate the base height required for static elements (paddings, title, items row)
        int baseHeight = estimateBaseHeight(ability, width, font);

        // Determine if we need to render the top row containing items and navigation buttons
        this.hasWidgetRow = (baseHeight > innerPadding + 12 + innerPadding);

        // Calculate remaining vertical space strictly for text description
        int availableTextHeight = maxAllowedHeight - baseHeight;

        // Determine how many lines of text can fit in the remaining space (minimum 1 line)
        int maxPossibleLines = Math.max(1, availableTextHeight / font.lineHeight);

        // Apply pagination logic based on calculations
        this.maxLinesPerPage = Math.min(this.allLines.size(), maxPossibleLines);
        this.totalPages = (int) Math.ceil((double) this.allLines.size() / Math.max(1, this.maxLinesPerPage));

        // Finalize the actual height of the widget
        this.height = baseHeight + (this.maxLinesPerPage * font.lineHeight);
    }

    /**
     * Estimates the height of the card's static elements (excluding the description text).
     * This includes padding, titles, and the item display row if present.
     *
     * @param ability   The ability data to estimate space for.
     * @param cardWidth The total width of the card.
     * @param font      The Minecraft font renderer.
     * @return The estimated height in pixels for the static elements.
     */
    public static int estimateBaseHeight(RaceAbility ability, int cardWidth, Font font) {
        int innerPadding = 5;
        int textWidth = cardWidth - (innerPadding * 2);
        int gapToTitle = 8;
        int base = innerPadding + 12 + innerPadding; // Padding + Title height + Bottom padding

        List<ItemStack> items = ability.getDisplayItems().stream().limit(MAX_DISPLAY_ITEMS).toList();
        int totalLinesEstimate = font.split(ability.getDescription(), textWidth).size();

        // If items are present OR if the text is long enough to likely require pagination buttons
        if (!items.isEmpty() || totalLinesEstimate > 4) {
            if (!items.isEmpty()) {

                // Calculate how many items can fit in a single row between navigation buttons
                int maxItemsPerRow = Math.max(1, (textWidth - (BTN_SIZE * 2) - 8) / ITEM_SIZE);
                int rows = (int) Math.ceil((double) items.size() / maxItemsPerRow);
                base += Math.max(BTN_SIZE, rows * ITEM_SIZE) + gapToTitle;
            } else {

                // Add space just for navigation buttons
                base += BTN_SIZE + gapToTitle;
            }
        }
        return base;
    }

    /**
     * Renders the widget on the screen.
     * This includes drawing the 9-slice background, navigation buttons,
     * item slots, ability title, category icon, and the paginated description text.
     * It also handles rendering tooltips for hovered items and icons.
     *
     * @param graphics    The GuiGraphics object used for rendering.
     * @param mouseX      The current X coordinate of the mouse.
     * @param mouseY      The current Y coordinate of the mouse.
     * @param partialTick The partial tick time for smooth animations.
     */
    @Override
    protected void renderWidget(@NotNull GuiGraphics graphics, int mouseX, int mouseY, float partialTick) {
        Font font = Minecraft.getInstance().font;
        int innerPadding = 5;

        // Render the background using a 9-slice technique to prevent stretching
        GuiRenderHelper.renderNineSlice(
                graphics, this.textureBack,
                this.getX(), this.getY(),
                this.width, this.height,
                0, 51,
                CORNER_PLATE_SIZE, CENTER_SIZE
        );

        int currentY = this.getY() + innerPadding;
        ItemStack hoveredItem = null;
        net.minecraft.network.chat.Component hoveredCategoryTooltip = null;

        // Render Top Row (Navigation Buttons and Display Items)
        if (this.hasWidgetRow) {
            int btnOffset = (ITEM_SIZE - BTN_SIZE) / 2;
            int gapToTitle = 8;

            // Render 'Previous Page' Left Button
            boolean canPrev = currentPage > 0;
            int leftBtnX = this.getX() + innerPadding;
            int vLeft = canPrev ? (isHovered(mouseX, mouseY, leftBtnX, currentY + btnOffset, BTN_SIZE, BTN_SIZE) ? 12 : 0) : 24;
            graphics.blit(TEXTURE_BUTT, leftBtnX, currentY + btnOffset, 0, vLeft, BTN_SIZE, BTN_SIZE);

            // Render 'Next Page' Right Button
            boolean canNext = currentPage < totalPages - 1;
            int rightBtnX = this.getX() + this.width - innerPadding - BTN_SIZE;
            int vRight = canNext ? (isHovered(mouseX, mouseY, rightBtnX, currentY + btnOffset, BTN_SIZE, BTN_SIZE) ? 12 : 0) : 24;
            graphics.blit(TEXTURE_BUTT, rightBtnX, currentY + btnOffset, 12, vRight, BTN_SIZE, BTN_SIZE);

            // Render Display Items
            if (!displayItems.isEmpty()) {
                // Calculate available horizontal space for items
                int maxSpaceX = this.width - (innerPadding * 2) - (BTN_SIZE * 2) - 8;
                int maxItemsPerRow = Math.max(1, maxSpaceX / ITEM_SIZE);

                int currentItemY = currentY;
                int drawnInRow = 0;

                int itemsInThisRow = Math.min(displayItems.size(), maxItemsPerRow);
                int rowWidth = itemsInThisRow * ITEM_SIZE;

                // Center the items in the row
                int currentItemX = this.getX() + (this.width - rowWidth) / 2;

                for (int i = 0; i < displayItems.size(); i++) {
                    // Wrap to next line if row is full
                    if (drawnInRow >= maxItemsPerRow) {
                        drawnInRow = 0;
                        currentItemY += ITEM_SIZE;
                        int remaining = displayItems.size() - i;
                        itemsInThisRow = Math.min(remaining, maxItemsPerRow);
                        rowWidth = itemsInThisRow * ITEM_SIZE;
                        currentItemX = this.getX() + (this.width - rowWidth) / 2;
                    }

                    // Render item background slot
                    graphics.blit(TEXTURE_WIDGETS, currentItemX, currentItemY, 0, 0, 18, 18);

                    // Render the actual item stack
                    ItemStack itemStack = displayItems.get(i);
                    graphics.renderItem(itemStack, currentItemX + 1, currentItemY + 1);

                    // Track hovered item for tooltip rendering later
                    if (isHovered(mouseX, mouseY, currentItemX, currentItemY, ITEM_SIZE, ITEM_SIZE)) {
                        hoveredItem = itemStack;
                        AbstractContainerScreen.renderSlotHighlight(graphics, currentItemX + 1, currentItemY + 1, 0);
                    }
                    currentItemX += ITEM_SIZE;
                    drawnInRow++;
                }

                // Adjust Y coordinate for the next elements
                int rows = (int) Math.ceil((double) displayItems.size() / maxItemsPerRow);
                currentY += Math.max(BTN_SIZE, rows * ITEM_SIZE) + gapToTitle;
            } else {
                currentY += BTN_SIZE + gapToTitle;
            }
        }

        // Render Headline & Category Icon (Only visible on the first page)
        if (this.currentPage == 0) {
            int titleColor = switch (this.ability.getCategory()) {
                case BENEFICIAL, HARMFUL, NEUTRAL -> VANILLA_DARK;
            };

            // Draw Ability Title
            graphics.drawString(font, this.ability.getTitle(), this.getX() + innerPadding, currentY, titleColor, false);

            // Determine Icon position and type based on Ability Category
            int titleWidth = font.width(this.ability.getTitle());
            int iconX = this.getX() + innerPadding + titleWidth + 4;
            int iconU;
            net.minecraft.network.chat.Component categoryText;

            switch (this.ability.getCategory()) {
                case BENEFICIAL -> {
                    iconU = 26;
                    categoryText = net.minecraft.network.chat.Component.translatable("ability.finesse_races.category.beneficial");
                }
                case HARMFUL -> {
                    iconU = 18;
                    categoryText = net.minecraft.network.chat.Component.translatable("ability.finesse_races.category.harmful");
                }
                default -> {
                    iconU = 34;
                    categoryText = net.minecraft.network.chat.Component.translatable("ability.finesse_races.category.neutral");
                }
            }

            // Draw Category Icon
            graphics.blit(TEXTURE_WIDGETS, iconX, currentY, iconU, 0, 8, 8);

            // Track hovered icon for tooltip
            if (isHovered(mouseX, mouseY, iconX - 4, currentY - 4, 16, 16)) {
                hoveredCategoryTooltip = categoryText;
            }

            // Advance Y coordinate only if title was drawn
            currentY += 12;
        }

        // Render Text Description
        int startLine = currentPage * this.maxLinesPerPage;
        int endLine = Math.min(startLine + this.maxLinesPerPage, allLines.size());

        for (int i = startLine; i < endLine; i++) {
            boolean isLastVisibleLine = (i == endLine - 1);
            boolean hasMorePages = (currentPage < totalPages - 1);

            // If it's the last line of the current page and there are more pages, append "..."
            if (isLastVisibleLine && hasMorePages) {
                int dotsWidth = font.width("...");
                int dotsX = this.getX() + this.width - innerPadding - dotsWidth;

                // Scissor testing to cut off text that overlaps with the "..."
                graphics.enableScissor(this.getX(), currentY, dotsX, currentY + font.lineHeight);
                graphics.drawString(font, allLines.get(i), this.getX() + innerPadding, currentY, VANILLA_DARK, false);
                graphics.disableScissor();

                // Draw the actual "..." indicator
                graphics.drawString(font, "...", dotsX, currentY, VANILLA_DARK, false);
            } else {
                // Normal line rendering
                graphics.drawString(font, allLines.get(i), this.getX() + innerPadding, currentY, VANILLA_DARK, false);
            }
            currentY += font.lineHeight;
        }

        // Render Tooltips (Drawn last with high Z-index to appear above everything)
        graphics.pose().pushPose();
        graphics.pose().translate(0, 0, 500);

        if (hoveredItem != null) {
            graphics.renderTooltip(font, hoveredItem, mouseX, mouseY);
        } else if (hoveredCategoryTooltip != null) {
            graphics.renderTooltip(font, hoveredCategoryTooltip, mouseX, mouseY);
        }
        graphics.pose().popPose();
    }

    /**
     * Handles mouse click events within the widget.
     * Specifically listens for left-clicks (button 0) to process pagination
     * navigation (Previous/Next page buttons).
     *
     * @param mouseX The X coordinate of the mouse cursor.
     * @param mouseY The Y coordinate of the mouse cursor.
     * @param button The mouse button pressed (0 = left click).
     * @return True if the click was intercepted and handled, false otherwise.
     */
    @Override
    public boolean mouseClicked(double mouseX, double mouseY, int button) {
        // Only handle Left Click (button 0) and if navigation widget is present
        if (!hasWidgetRow || button != 0) return false;

        int innerPadding = 5;
        int btnOffset = (ITEM_SIZE - BTN_SIZE) / 2;
        int btnY = this.getY() + innerPadding + btnOffset;

        // Check Left Button Click (Previous Page)
        int leftBtnX = this.getX() + innerPadding;
        if (currentPage > 0 && isHovered(mouseX, mouseY, leftBtnX, btnY, BTN_SIZE, BTN_SIZE)) {
            currentPage--;
            playDownSound(Minecraft.getInstance().getSoundManager());
            return true;
        }

        // Check Right Button Click (Next Page)
        int rightBtnX = this.getX() + this.width - innerPadding - BTN_SIZE;
        if (currentPage < totalPages - 1 && isHovered(mouseX, mouseY, rightBtnX, btnY, BTN_SIZE, BTN_SIZE)) {
            currentPage++;
            playDownSound(Minecraft.getInstance().getSoundManager());
            return true;
        }

        return false;
    }

    /**
     * Helper method to determine if the mouse cursor is hovering over a specific rectangular area.
     *
     * @param mouseX The current X coordinate of the mouse.
     * @param mouseY The current Y coordinate of the mouse.
     * @param x      The starting X coordinate of the rectangle.
     * @param y      The starting Y coordinate of the rectangle.
     * @param w      The width of the rectangle.
     * @param h      The height of the rectangle.
     * @return True if the mouse is within the specified bounds, false otherwise.
     */
    private boolean isHovered(double mouseX, double mouseY, int x, int y, int w, int h) {
        return mouseX >= x && mouseX < x + w && mouseY >= y && mouseY < y + h;
    }

    /**
     * Plays the standard UI button click sound when the widget's navigation buttons are pressed.
     */
    @Override
    public void playDownSound(SoundManager handler) {
        handler.play(SimpleSoundInstance.forUI(SoundEvents.UI_BUTTON_CLICK, 1.0F));
    }

    /**
     * Updates the narration output for accessibility features (e.g., screen readers).
     */
    @Override
    protected void updateWidgetNarration(@NotNull NarrationElementOutput narrationElementOutput) {
        this.defaultButtonNarrationText(narrationElementOutput);
    }

    /**
     * Invoked when the widget is clicked.
     * This is intentionally left blank because the click processing is handled
     * manually in {@link #mouseClicked(double, double, int)} to support multiple
     * hitboxes (left and right navigation buttons) within the same widget.
     */
    @Override
    public void onClick(double mouseX, double mouseY) {
        // Implementation handled in mouseClicked, but method required by AbstractWidget
    }

    /**
     * Retrieves the race ability data object associated with this card widget.
     */
    public RaceAbility getAbility() {
        return this.ability;
    }
}