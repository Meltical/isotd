package com.Models;

import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

public abstract class AUnit extends APosition implements IDrawable {
    private Image[] sprites;

    public AUnit(int x, int y, Image[] sprites) {
        super(x, y);
        this.sprites = sprites;
    }

    public ImageView getSprite() {
        if (sprites.length == 1) {
            return new ImageView(sprites[0]);
        }
        int randomIndex = (int) (Math.random() * sprites.length);
        Image image = sprites[randomIndex];
        return new ImageView(image);
    }
}
