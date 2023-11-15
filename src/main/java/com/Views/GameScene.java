package com.Views;

import com.Constants;
import com.Controllers.GameController;

import javafx.scene.Scene;
import javafx.scene.layout.Pane;

public class GameScene extends Scene {
    private Pane root = null;
    private GameController gameController = null;

    public GameScene() {
        super(new Pane(), Constants.WINDOW_WIDTH, Constants.WINDOW_HEIGHT);
        root = (Pane) getRoot();
        gameController = new GameController(root, Constants.TILE_COUNT, Constants.TILE_COUNT);
    }
}
