package com.Controllers;

import javafx.scene.layout.Pane;

public class GameController {
    private TileController tileController;
    private int width;
    private int height;
    private Pane root;

    public GameController(Pane root, int width, int height) {
        this.root = root;
        this.width = width;
        this.height = height;
        this.tileController = new TileController(root, width, height);
    }

    public int getWidth() {
        return width;
    }

    public int getHeight() {
        return height;
    }

    public TileController getTileController() {
        return tileController;
    }

    // Review with selected Tower
    // private final Consumer<Point2D> onTileClicked = (Point2D point) -> {
    // if (currentSprite != null) {
    // ImageView cloneSprite = new ImageView(currentSprite.getImage());
    // cloneSprite.setViewport(currentSprite.getViewport());
    // tileManager.addTile(cloneSprite, (int) point.getX(), (int) point.getY());
    // onRender();
    // }
    // };

    // private final Consumer<Point2D> onTileHover = (Point2D point) -> {
    // if (currentSprite != null) {
    // ImageView cloneSprite = new ImageView(currentSprite.getImage());
    // cloneSprite.setViewport(currentSprite.getViewport());
    // tileManager.addHoverTile(cloneSprite, (int) point.getX(), (int)
    // point.getY());
    // onRender();
    // }
    // };
}
