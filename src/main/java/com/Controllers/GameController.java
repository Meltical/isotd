package com.Controllers;

import java.util.Arrays;

import javafx.application.Platform;
import javafx.scene.layout.Pane;

public class GameController implements Runnable {
    private TileController tileController;
    private EnnemyController ennemyController;
    private GraphicsRenderer graphicsRenderer;

    protected int width;
    protected int height;

    private boolean paused = false;
    private Thread gameThread;

    public GameController(Pane root) {
        this.graphicsRenderer = new GraphicsRenderer(root);
        this.tileController = new TileController(this.graphicsRenderer);
        this.ennemyController = new EnnemyController(Arrays.asList(this.tileController.getEnnemyRoute()));
        gameThread = new Thread(this);
        gameThread.start();
    }

    public TileController getTileController() {
        return tileController;
    }

    @Override
    public void run() {
        long lastUpdateTime = System.currentTimeMillis();
        long lastDrawTime = System.currentTimeMillis();
        long updateInterval = 1000 / 8; // update at 8fps
        long drawInterval = 1000 / 24; // draw at 24fps

        while (!paused) {
            long currentTime = System.currentTimeMillis();

            if (currentTime - lastUpdateTime >= updateInterval) {
                this.ennemyController.onUpdate();
                lastUpdateTime = currentTime;
            }

            if (currentTime - lastDrawTime >= drawInterval) {
                Platform.runLater(() -> {
                    for (var ennemy : this.ennemyController.getWave()) {
                        if (ennemy.shouldDrawNextFrame(currentTime))
                            this.graphicsRenderer.draw(ennemy);
                    }
                });
                lastDrawTime = currentTime;
            }

            try {
                Thread.sleep(1000 / 60);
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
