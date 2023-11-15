package com.Models;

import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

public abstract class Tile {
    private int x;
    private int y;
    private boolean isWalkable;
    private boolean isBuildable;
    private Image[] sprites;

    public Tile(int x, int y, boolean isWalkable, boolean isBuildable, Image[] sprites) {
        this.x = x;
        this.y = y;
        this.isWalkable = isWalkable;
        this.isBuildable = isBuildable;
        this.sprites = sprites;
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

    public boolean isWalkable() {
        return isWalkable;
    }

    public boolean isBuildable() {
        return isBuildable;
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
