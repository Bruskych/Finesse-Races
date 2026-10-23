package org.bruskych.finesse_races.client.gui.widgets;

import com.mojang.blaze3d.systems.RenderSystem;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.GuiGraphics;
import net.minecraft.client.gui.components.AbstractWidget;
import net.minecraft.client.gui.narration.NarrationElementOutput;
import net.minecraft.client.resources.sounds.SimpleSoundInstance;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.sounds.SoundEvents;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;
import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Consumer;

/**
 * A custom pagination widget in the form of indicator dots.
 * Automatically wraps dots to a new line when `maxWidth` is exceeded,
 * centers them horizontally within each line, and handles click, hover, and scroll events.
 */
@OnlyIn(Dist.CLIENT)
public class DotPaginationWidget extends AbstractWidget {

    // Texture and Element Size Settings
    private final ResourceLocation texture;
    private final int dotWidth;
    private final int dotHeight;
    private final int spacing;
    private final int maxWidth;

    // UV coordinates and texture file dimensions
    private final int u;
    private final int v;
    private final int textureWidth;
    private final int textureHeight;

    // Pagination state
    private int totalPages;
    private int currentPage;
    private final Consumer<Integer> onPageSelect;

    /**
     * A helper class for storing the local coordinates and index of each point.
     */
    private static class DotBounds {
        int index, x, y;
        DotBounds(int index, int x, int y) {
            this.index = index; this.x = x; this.y = y;
        }
    }

    // Cache of assembled points after grid calculation
    private final List<DotBounds> dots = new ArrayList<>();

    /**
     * Constructor for creating a Dot Pagination Widget.
     */
    public DotPaginationWidget(
            int x, int y, int maxWidth, int totalPages, int currentPage,
            int dotWidth, int dotHeight, int spacing,
            int u, int v, int textureWidth, int textureHeight,
            ResourceLocation texture, Consumer<Integer> onPageSelect
    ) {
        super(x, y, maxWidth, 0, Component.empty());
        this.maxWidth = maxWidth;
        this.totalPages = totalPages;
        this.currentPage = currentPage;
        this.dotWidth = dotWidth;
        this.dotHeight = dotHeight;
        this.spacing = spacing;
        this.u = u;
        this.v = v;
        this.textureWidth = textureWidth;
        this.textureHeight = textureHeight;
        this.texture = texture;
        this.onPageSelect = onPageSelect;

        // We calculate the grid of point locations right at the creation stage
        recalculateLayout();
    }

    /**
     * Updates the current page number.
     */
    public void setCurrentPage(int currentPage) {
        this.currentPage = currentPage;
    }

    /**
     * Calculates the position of each point and handles row wrapping.
     */
    private void recalculateLayout() {
        dots.clear();
        if (totalPages <= 0) {
            this.height = 0;
            return;
        }
        int currentY = 0;
        int rowWidth = 0;

        List<List<DotBounds>> rows = new ArrayList<>();
        List<DotBounds> currentRow = new ArrayList<>();

        // Distributing points into rows when maxWidth is exceeded
        for (int i = 0; i < totalPages; i++) {
            if (rowWidth + dotWidth > maxWidth && !currentRow.isEmpty()) {
                rows.add(currentRow);
                currentRow = new ArrayList<>();
                rowWidth = 0;
                currentY += dotHeight + spacing;
            }
            currentRow.add(new DotBounds(i, 0, currentY));
            rowWidth += dotWidth + (currentRow.size() == 1 ? 0 : spacing);
        }
        if (!currentRow.isEmpty()) rows.add(currentRow);

        // Horizontally CENTERING elements in each row
        for (List<DotBounds> row : rows) {
            int rWidth = row.size() * dotWidth + (row.size() - 1) * spacing;
            int startX = (maxWidth - rWidth) / 2;
            for (int i = 0; i < row.size(); i++) {
                DotBounds dot = row.get(i);
                dot.x = startX + i * (dotWidth + spacing);
                dots.add(dot);
            }
        }
        this.height = currentY + dotHeight;
    }

    /**
     * Renders the widget on the screen, applying hover effects to dots.
     */
    @Override
    public void renderWidget(@NotNull GuiGraphics graphics, int mouseX, int mouseY, float partialTick) {
        // Set the base transparency
        RenderSystem.setShaderColor(1.0F, 1.0F, 1.0F, this.alpha);

        for (DotBounds dot : dots) {
            int globalX = this.getX() + dot.x;
            int globalY = this.getY() + dot.y;

            // Checking whether the mouse cursor is over a specific point
            boolean isHovered =
                    mouseX >= globalX &&
                    mouseX < globalX + dotWidth &&
                    mouseY >= globalY &&
                    mouseY < globalY + dotHeight;

            int vOffset;
            if (dot.index == currentPage) {
                // Active point
                vOffset = 0;
            } else if (isHovered) {
                // Induced inactive point (hovered)
                vOffset = dotHeight * 2;
            } else {
                // Inactive point
                vOffset = dotHeight;
            }

            // Rendering the required state of the point from a texture file
            graphics.blit(texture, globalX, globalY, this.u, this.v + vOffset, dotWidth, dotHeight, textureWidth, textureHeight);
        }
    }

    /**
     * Handles left mouse button clicks to switch to a specific page.
     */
    @Override
    public boolean mouseClicked(double mouseX, double mouseY, int button) {
        if (this.active && this.visible && button == 0) {
            for (DotBounds dot : dots) {
                int globalX = this.getX() + dot.x;
                int globalY = this.getY() + dot.y;

                // Checking if the cursor is within the point's hitbox
                if (
                        mouseX >= globalX &&
                        mouseX < globalX + dotWidth &&
                        mouseY >= globalY &&
                        mouseY < globalY + dotHeight
                ) {
                    if (dot.index != currentPage) {
                        onPageSelect.accept(dot.index);
                        Minecraft.getInstance()
                                .getSoundManager()
                                .play(SimpleSoundInstance.forUI(SoundEvents.UI_BUTTON_CLICK, 1.0F));
                        return true;
                    }
                }
            }
        }
        return false;
    }

    /**
     * Overrides the default vanilla hitbox check to include our hover radius.
     * This forces the game to send mouse events (like scrolling) to this widget
     * even if the cursor is slightly outside the visual dots.
     */
    @Override
    public boolean isMouseOver(double mouseX, double mouseY) {
        int hoverRadius = 4;
        return this.active && this.visible &&
                mouseX >= (double)(this.getX() - hoverRadius) &&
                mouseY >= (double)(this.getY() - hoverRadius) &&
                mouseX < (double)(this.getX() + this.width + hoverRadius) &&
                mouseY < (double)(this.getY() + this.height + hoverRadius);
    }

    /**
     * Handles mouse wheel scrolling to navigate through pages.
     * Triggered only when the cursor is hovering over any dot.
     */
    @Override
    public boolean mouseScrolled(double mouseX, double mouseY, double delta) {
        if (this.isMouseOver(mouseX, mouseY)) {

            // Determine direction: delta > 0 (scroll up), delta < 0 (scroll down)
            int direction = (int) Math.signum(delta);
            // Scroll up = previous, scroll down = next
            int newPage = this.currentPage - direction;

            // It can be scrolled continuously
            if (newPage < 0) {
                newPage = this.totalPages - 1;
            } else if (newPage >= this.totalPages) {
                newPage = 0;
            }

            if (newPage != this.currentPage) {
                onPageSelect.accept(newPage);
                return true;
            }
        }
        return false;
    }

    /**
     * Updates narration data for screen readers (Accessibility).
     */
    @Override
    protected void updateWidgetNarration(@NotNull NarrationElementOutput narrationElementOutput) {
        // Accessibility placeholder. Can be expanded for vision-impaired players.
        this.defaultButtonNarrationText(narrationElementOutput);
    }
}