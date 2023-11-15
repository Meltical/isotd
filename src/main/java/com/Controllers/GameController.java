package com.Controllers;

import com.Constants;
import com.Models.Ennemies.Boar;

import javafx.application.Platform;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;

public class GameController implements Runnable {
    private TileController tileController;
    private int width;
    private int height;
    private Pane root;
    private boolean paused = false;
    private Thread gameThread;

    public GameController(Pane root, int width, int height) {
        this.root = root;
        this.width = width;
        this.height = height;
        this.tileController = new TileController(this.root, width, height);

        gameThread = new Thread(this);
        gameThread.start();
    }

    public TileController getTileController() {
        return tileController;
    }

    @Override
    public void run() {
        ImageView sprite = null;
        var ennemy = new Boar(0, 0);
        while (!paused) {
            if (sprite != null) {
                final ImageView spriteToRemove = sprite;
                Platform.runLater(() -> root.getChildren().remove(spriteToRemove));
            }

            int originX = (ennemy.getY() * Constants.TILE_WIDTH / 2) + (ennemy.getX() * Constants.TILE_WIDTH / 2)
                    + Constants.TILE_WIDTH / 2;
            int originY = (ennemy.getX() * Constants.TILE_HEIGHT / 2) - (ennemy.getY() * Constants.TILE_HEIGHT / 2)
                    + Constants.TILE_HEIGHT * (Constants.TILE_COUNT / 2);

            sprite = ennemy.getSprite();
            sprite.setX(originX);
            sprite.setY(originY);
            sprite.setMouseTransparent(true);
            final ImageView spriteToAdd = sprite;
            Platform.runLater(() -> root.getChildren().add(spriteToAdd));

            try {
                Thread.sleep(125);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
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
