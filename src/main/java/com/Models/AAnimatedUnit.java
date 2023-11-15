package com.Models;

import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

public abstract class AAnimatedUnit extends APosition implements IDrawable {
    private Image[] sprites;
    private int spriteIndex = 0;

    public AAnimatedUnit(int x, int y, Image[] sprites) {
        super(x, y);
        this.sprites = sprites;
    }

    public ImageView getSprite() {
        if (sprites.length == 1) {
            return new ImageView(sprites[0]);
        }
        spriteIndex = (spriteIndex + 1) % sprites.length;
        Image image = sprites[spriteIndex];
        return new ImageView(image);
    }
}
