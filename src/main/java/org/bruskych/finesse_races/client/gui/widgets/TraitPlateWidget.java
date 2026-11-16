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
 * A custom GUI widget that displays a race trait as a stylized plate.
 * It contains a background plate, an icon, and a text description.
 */
public class TraitPlateWidget extends AbstractWidget {

    private static final int CORNER_PLATE_SIZE = 4;
    private static final int CENTER_SIZE = 1;

    private final ResourceLocation textureBack;
    private final ResourceLocation textureWidgets;

    private final int iconU;
    private final int iconV;
    private final int textColor;

    /**
     * Constructs a new TraitPlateWidget.
     *
     * @param x              The x-coordinate of the widget.
     * @param y              The y-coordinate of the widget.
     * @param width          The total width of the widget.
     * @param height         The total height of the widget.
     * @param message        The text component (trait name/description) to display.
     * @param textureBack    The resource location of the background texture.
     * @param textureWidgets The resource location containing the icons.
     * @param iconU          The U coordinate of the icon in the texture file.
     * @param iconV          The V coordinate of the icon in the texture file.
     * @param textColor      The color of the text.
     */
    public TraitPlateWidget(int x, int y, int width, int height, Component message,
                            ResourceLocation textureBack, ResourceLocation textureWidgets,
                            int iconU, int iconV, int textColor) {
        super(x, y, width, height, message);
        this.textureBack = textureBack;
        this.textureWidgets = textureWidgets;
        this.iconU = iconU;
        this.iconV = iconV;
        this.textColor = textColor;
    }

    /**
     * Renders the widget on the screen.
     * Draws the Nine-Slice background, the icon, and the text.
     */
    @Override
    protected void renderWidget(@NotNull GuiGraphics graphics, int mouseX, int mouseY, float partialTick) {
        // PLATE (BACK)
        GuiRenderHelper.renderNineSlice(
                graphics, this.textureBack,
                this.getX(), this.getY(),
                this.width, this.height,
                35, 43,
                CORNER_PLATE_SIZE, CENTER_SIZE
        );

        int iconSize = 12;
        int innerMargin = (this.height - iconSize) / 2;

        // TRAIT ICON
        graphics.blit(this.textureWidgets,
                this.getX() + innerMargin,
                this.getY() + innerMargin,
                this.iconU, this.iconV,
                iconSize, iconSize,
                256, 256
        );

        Font font = Minecraft.getInstance().font;

        // TEXT
        int textStartX = this.getX() + innerMargin + iconSize + innerMargin;
        int textY = this.getY() + (this.height - font.lineHeight) / 2 + 1;
        graphics.drawString(font, this.getMessage(), textStartX, textY, this.textColor, false);
    }

    /**
     * Updates the narration for accessibility features.
     */
    @Override
    protected void updateWidgetNarration(@NotNull NarrationElementOutput output) {
        this.defaultButtonNarrationText(output);
    }

    /**
     * Prevents the widget from acting like a button.
     * Returning false stops it from consuming clicks and playing the click sound.
     */
    @Override
    public boolean mouseClicked(double mouseX, double mouseY, int button) {
        return false;
    }
}