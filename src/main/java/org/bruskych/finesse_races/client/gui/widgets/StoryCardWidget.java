package org.bruskych.finesse_races.client.gui.widgets;

import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.Font;
import net.minecraft.client.gui.GuiGraphics;
import net.minecraft.client.gui.components.AbstractWidget;
import net.minecraft.client.gui.narration.NarrationElementOutput;
import net.minecraft.client.sounds.SoundManager;
import net.minecraft.client.resources.sounds.SimpleSoundInstance;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.util.FormattedCharSequence;

import org.bruskych.finesse_races.core.FinesseRaces;
import org.bruskych.finesse_races.gameplay.races.core.AbstractRace;
import org.bruskych.remedy_core.client.gui.GuiRenderHelper;
import org.jetbrains.annotations.NotNull;

import java.util.List;

/**
 * A graphical user interface (GUI) widget that displays a paginated "Story Card" for a specific race.
 * It renders the race's name centered between navigation arrows, followed by the paginated story text.
 * Extends Minecraft's {@link AbstractWidget} to handle rendering and mouse interactions.
 */
public class StoryCardWidget extends AbstractWidget {

    private static final int CORNER_PLATE_SIZE = 4;
    private static final int CENTER_SIZE = 1;

    // Size configuration for GUI elements
    private static final int BTN_SIZE = 12;

    private static final ResourceLocation TEXTURE_BUTT = ResourceLocation.fromNamespaceAndPath(
            FinesseRaces.MOD_ID, "textures/gui/buttons.png"
    );

    int VANILLA_DARK = 0x404040;

    private final AbstractRace race;
    private final ResourceLocation textureBack;

    private final List<FormattedCharSequence> allLines;

    // Dynamic number of text lines based on available screen space
    private final int maxLinesPerPage;

    private int currentPage = 0;
    private final int totalPages;

    /**
     * Constructs a new StoryCardWidget.
     *
     * @param x                The X coordinate of the widget on the screen.
     * @param y                The Y coordinate of the widget on the screen.
     * @param width            The total width of the widget.
     * @param maxAllowedHeight The maximum height this widget is allowed to occupy.
     * @param race             The {@link AbstractRace} data object containing the story.
     * @param textureBack      The ResourceLocation of the background texture.
     */
    public StoryCardWidget(int x, int y, int width, int maxAllowedHeight, AbstractRace race, ResourceLocation textureBack) {
        super(x, y, width, 0, race.getDisplayName());
        this.race = race;
        this.textureBack = textureBack;

        Font font = Minecraft.getInstance().font;
        int innerPadding = 5;
        int textWidth = width - (innerPadding * 2);

        // Split the story text into lines that fit the widget width
        this.allLines = font.split(race.getDisplayStory(), textWidth);

        // Calculate the base height required for static elements (paddings, title row)
        int baseHeight = estimateBaseHeight();

        // Calculate remaining vertical space strictly for text description
        int availableTextHeight = maxAllowedHeight - baseHeight;

        // Determine how many lines of text can fit in the remaining space (minimum 1 line)
        int maxPossibleLines = Math.max(1, availableTextHeight / font.lineHeight);

        // Apply pagination logic
        this.maxLinesPerPage = Math.min(this.allLines.size(), maxPossibleLines);
        this.totalPages = (int) Math.ceil((double) this.allLines.size() / Math.max(1, this.maxLinesPerPage));

        // Finalize the actual height of the widget
        this.height = baseHeight + (this.maxLinesPerPage * font.lineHeight);
    }

    /**
     * Estimates the height of the card's static elements (header row and paddings).
     *
     * @return The estimated height in pixels.
     */
    public static int estimateBaseHeight() {
        int innerPadding = 5;
        int gapToStory = 8;
        // Padding Top + Button Size + Gap + Padding Bottom
        return innerPadding + BTN_SIZE + gapToStory + innerPadding;
    }

    /**
     * Renders the widget on the screen.
     * Draws the 9-slice background, navigation buttons, and the paginated story text.
     *
     * @param graphics    The GuiGraphics object used for drawing.
     * @param mouseX      The current X coordinate of the mouse cursor.
     * @param mouseY      The current Y coordinate of the mouse cursor.
     * @param partialTick The partial tick time for smooth animations.
     */
    @Override
    protected void renderWidget(@NotNull GuiGraphics graphics, int mouseX, int mouseY, float partialTick) {
        Font font = Minecraft.getInstance().font;
        int innerPadding = 5;

        // Render the 9-slice background
        GuiRenderHelper.renderNineSlice(
                graphics, this.textureBack,
                this.getX(), this.getY(),
                this.width, this.height,
                0, 51,
                CORNER_PLATE_SIZE, CENTER_SIZE
        );

        int currentY = this.getY() + innerPadding;
        int gapToStory = 8;

        // Render Top Row (Navigation Buttons and Race Name)
        boolean canPrev = currentPage > 0;
        int leftBtnX = this.getX() + innerPadding;
        int vLeft = canPrev ? (isHovered(mouseX, mouseY, leftBtnX, currentY, BTN_SIZE, BTN_SIZE) ? 12 : 0) : 24;
        graphics.blit(TEXTURE_BUTT, leftBtnX, currentY, 0, vLeft, BTN_SIZE, BTN_SIZE);

        boolean canNext = currentPage < totalPages - 1;
        int rightBtnX = this.getX() + this.width - innerPadding - BTN_SIZE;
        int vRight = canNext ? (isHovered(mouseX, mouseY, rightBtnX, currentY, BTN_SIZE, BTN_SIZE) ? 12 : 0) : 24;
        graphics.blit(TEXTURE_BUTT, rightBtnX, currentY, 12, vRight, BTN_SIZE, BTN_SIZE);

        currentY += BTN_SIZE + gapToStory;

        // Render Story Text Description
        int startLine = currentPage * this.maxLinesPerPage;
        int endLine = Math.min(startLine + this.maxLinesPerPage, allLines.size());

        for (int i = startLine; i < endLine; i++) {
            boolean isLastVisibleLine = (i == endLine - 1);
            boolean hasMorePages = (currentPage < totalPages - 1);

            // Append "..." if the story continues on the next page
            if (isLastVisibleLine && hasMorePages) {
                int dotsWidth = font.width("...");
                int dotsX = this.getX() + this.width - innerPadding - dotsWidth;

                graphics.enableScissor(this.getX(), currentY, dotsX, currentY + font.lineHeight);
                graphics.drawString(font, allLines.get(i), this.getX() + innerPadding, currentY, VANILLA_DARK, false);
                graphics.disableScissor();

                graphics.drawString(font, "...", dotsX, currentY, VANILLA_DARK, false);
            } else {
                graphics.drawString(font, allLines.get(i), this.getX() + innerPadding, currentY, VANILLA_DARK, false);
            }
            currentY += font.lineHeight;
        }
    }

    /**
     * Handles mouse click events within the widget.
     * Specifically listens for left-clicks (button 0) to process pagination navigation.
     *
     * @param mouseX The X coordinate of the mouse cursor.
     * @param mouseY The Y coordinate of the mouse cursor.
     * @param button The mouse button pressed (0 = left click).
     * @return True if the click was intercepted and handled, false otherwise.
     */
    @Override
    public boolean mouseClicked(double mouseX, double mouseY, int button) {
        if (button != 0) return false;

        int innerPadding = 5;
        int btnY = this.getY() + innerPadding;

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
     *
     * @param handler The SoundManager instance handling game audio.
     */
    @Override
    public void playDownSound(SoundManager handler) {
        handler.play(SimpleSoundInstance.forUI(SoundEvents.UI_BUTTON_CLICK, 1.0F));
    }

    /**
     * Updates the narration output for accessibility features (e.g., screen readers).
     *
     * @param narrationElementOutput The narration output builder.
     */
    @Override
    protected void updateWidgetNarration(@NotNull NarrationElementOutput narrationElementOutput) {
        this.defaultButtonNarrationText(narrationElementOutput);
    }

    /**
     * Invoked when the widget is clicked.
     * Intentionally left blank because click processing is handled manually
     * in {@link #mouseClicked(double, double, int)} to support multiple hitboxes.
     *
     * @param mouseX The X coordinate of the mouse cursor.
     * @param mouseY The Y coordinate of the mouse cursor.
     */
    @Override
    public void onClick(double mouseX, double mouseY) {
        // Empty
    }
}