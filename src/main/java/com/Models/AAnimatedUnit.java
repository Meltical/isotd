package com.Models;

import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

public abstract class AAnimatedUnit extends AUnit {
    private long frameDuration = 1000 / 12; // 12 fps
    private int spriteIndex = 0;
    private long lastDrawTime = 0;
    private ImageView currentSprite = null;

    public ImageView getCurrentSprite() {
        return currentSprite;
    }

    public AAnimatedUnit(double x, double y, Image[] sprites) {
        super(x, y, sprites);
    }

    public AAnimatedUnit(double x, double y, Image[] sprites, double animationSpeed) {
        super(x, y, sprites);
        this.frameDuration /= animationSpeed;
    }

    public boolean shouldDrawNextFrame(long currentTime) {
        if (currentTime - lastDrawTime >= frameDuration) {
            lastDrawTime = currentTime;
            return true;
        }
        return false;
    }

    public ImageView getSprite() {
        if (getSprites().length == 1) {
            return new ImageView(getSprites()[0]);
        }
        spriteIndex = (spriteIndex + 1) % getSprites().length;
        Image image = getSprites()[spriteIndex];
        currentSprite = new ImageView(image);
        return currentSprite;
    }
}
