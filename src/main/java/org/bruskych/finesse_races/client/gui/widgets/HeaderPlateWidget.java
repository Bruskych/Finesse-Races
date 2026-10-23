package org.bruskych.finesse_races.client.gui.widgets;

import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.Font;
import net.minecraft.client.gui.GuiGraphics;
import net.minecraft.client.gui.components.AbstractWidget;
import net.minecraft.client.gui.narration.NarrationElementOutput;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.ResourceLocation;
import org.bruskych.remedy_core.client.gui.GuiRenderHelper;
import org.jetbrains.annotations.NotNull;

/**
 * A reusable GUI widget that renders a decorated plate (using a 9-slice background) containing text.
 */
public class HeaderPlateWidget extends AbstractWidget {

    private static final int CORNER_PLATE_SIZE = 4;
    private static final int CENTER_SIZE = 1;

    private final ResourceLocation textureBack;
    private final int u;
    private final int v;
    private final boolean autoScroll;
    private final int textColor;

    /**
     * Constructs a new HeaderPlateWidget.
     *
     * @param x           The X coordinate of the widget on the screen.
     * @param y           The Y coordinate of the widget on the screen.
     * @param width       The width of the plate.
     * @param height      The height of the plate.
     * @param message     The text component to display inside the plate.
     * @param textureBack The texture file containing the plate graphics.
     * @param u           The horizontal starting position (U) of the texture on the sprite sheet.
     * @param v           The vertical starting position (V) of the texture on the sprite sheet.
     * @param autoScroll  If true, text that is too long will scroll horizontally (vanilla style).
     *                    If false, the text will be centered statically.
     * @param textColor   The color of the text.
     */
    public HeaderPlateWidget(int x, int y, int width, int height, Component message,
                             ResourceLocation textureBack, int u, int v,
                             boolean autoScroll, int textColor
    ) {
        super(x, y, width, height, message);
        this.textureBack = textureBack;
        this.u = u;
        this.v = v;
        this.autoScroll = autoScroll;
        this.textColor = textColor;
    }

    /**
     * Renders the widget on the screen.
     * Draws the 9-slice background first, and then renders the text on top of it.
     *
     * @param graphics    The GuiGraphics object used for drawing.
     * @param mouseX      The current X coordinate of the mouse cursor.
     * @param mouseY      The current Y coordinate of the mouse cursor.
     * @param partialTick The partial tick time for smooth animations.
     */
    @Override
    protected void renderWidget(@NotNull GuiGraphics graphics, int mouseX, int mouseY, float partialTick) {
        GuiRenderHelper.renderNineSlice(
                graphics, this.textureBack,
                this.getX(), this.getY(),
                this.width, this.height,
                this.u, this.v,
                CORNER_PLATE_SIZE, CENTER_SIZE
        );

        Font font = Minecraft.getInstance().font;
        int marginX = 2;

        if (this.autoScroll) {
            GuiRenderHelper.renderScrollingString(
                    graphics, font, this.getMessage(),
                    this.getX() + marginX, this.getY(),
                    this.getX() + this.width - marginX, this.getY() + this.height,
                    this.textColor, true
            );
        } else {
            int textWidth = font.width(this.getMessage());
            int textX = this.getX() + (this.width - textWidth) / 2;
            int textY = this.getY() + (this.height - font.lineHeight) / 2 + 1;
            graphics.drawString(font, this.getMessage(), textX, textY, this.textColor, false);
        }
    }

    /**
     * Updates the narration output for accessibility features (e.g., screen readers).
     *
     * @param output The narration output builder.
     */
    @Override
    protected void updateWidgetNarration(@NotNull NarrationElementOutput output) {
        this.defaultButtonNarrationText(output);
    }
}