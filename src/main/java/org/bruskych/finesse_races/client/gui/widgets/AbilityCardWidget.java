package org.bruskych.finesse_races.client.gui.widgets;

import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.Font;
import net.minecraft.client.gui.GuiGraphics;
import net.minecraft.client.gui.components.AbstractWidget;
import net.minecraft.client.gui.narration.NarrationElementOutput;
import net.minecraft.client.sounds.SoundManager;
import net.minecraft.resources.ResourceLocation;
import org.bruskych.finesse_races.gameplay.abilities.core.RaceAbility;
import org.bruskych.remedy_core.client.gui.GuiRenderHelper;

public class AbilityCardWidget extends AbstractWidget {

    private static final int CORNER_PLATE_SIZE = 4;
    private static final int CENTER_SIZE = 1;

    int VANILLA_WHITE = 0xFFFFFF;
    int VANILLA_DARK = 0x404040;

    private final RaceAbility ability;
    private final ResourceLocation textureBack;

    public AbilityCardWidget(int x, int y, int width, int height, RaceAbility ability, ResourceLocation textureBack) {
        super(x, y, width, height, ability.getTitle());
        this.ability = ability;
        this.textureBack = textureBack;
    }

    @Override
    protected void renderWidget(GuiGraphics graphics, int mouseX, int mouseY, float partialTick) {
        Font font = Minecraft.getInstance().font;
        int innerPadding = 5;
        int textWidth = this.width - (innerPadding * 2);

        // Card background render
        GuiRenderHelper.renderNineSlice(
                graphics, this.textureBack,
                this.getX(), this.getY(),
                this.width, this.height,
                0, 51,
                CORNER_PLATE_SIZE, CENTER_SIZE
        );

        // Heading colors
        int titleColor = switch (this.ability.getCategory()) {
            case BENEFICIAL -> VANILLA_DARK;
            case HARMFUL -> VANILLA_DARK;
            case NEUTRAL -> VANILLA_DARK;
        };

        // Headline and description
        graphics.drawString(
                font,
                this.ability.getTitle(),
                this.getX() + innerPadding,
                this.getY() + innerPadding,
                titleColor, false
        );
        int descriptionY = this.getY() + innerPadding + 12;
        for (var line : font.split(this.ability.getDescription(), textWidth)) {
            graphics.drawString(
                    font, line,
                    this.getX() + innerPadding,
                    descriptionY,
                    VANILLA_DARK, false
            );
            descriptionY += font.lineHeight;
        }
    }

    // Click action
    @Override
    public void onClick(double mouseX, double mouseY) {
        // TODO: Animation?
    }

    // Playing the standard button click sound
    @Override
    public void playDownSound(SoundManager handler) {
        // TODO: Empty
    }

    // Mouse click handling
    @Override
    public boolean mouseClicked(double mouseX, double mouseY, int button) {
        return false;
    }

    @Override
    protected void updateWidgetNarration(NarrationElementOutput narrationElementOutput) {
        this.defaultButtonNarrationText(narrationElementOutput);
    }

    public RaceAbility getAbility() {
        return this.ability;
    }
}