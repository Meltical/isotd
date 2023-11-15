package com.Models.Tiles;

import com.Models.AUnit;

import javafx.scene.image.Image;

public abstract class Tile extends AUnit {
    private boolean isWalkable;
    private boolean isBuildable;

    public Tile(int x, int y, boolean isWalkable, boolean isBuildable, Image[] sprites) {
        super(x, y, sprites);
        this.isWalkable = isWalkable;
        this.isBuildable = isBuildable;
    }

    public boolean isWalkable() {
        return isWalkable;
    }

    public boolean isBuildable() {
        return isBuildable;
    }
}
