package com.Controllers;

import java.io.File;

import javafx.geometry.Rectangle2D;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

public class AssetManager {
    public static final Image TileSheet = new Image(new File("src/main/ressources/spritesheet.png").toURI().toString());
    private static AssetManager instance;

    private AssetManager() {
    }

    public static AssetManager getInstance() {
        if (instance == null) {
            instance = new AssetManager();
        }
        return instance;
    }

    public ImageView getSprite(Rectangle2D viewport) {
        ImageView spriteView = new ImageView(TileSheet);
        spriteView.setViewport(viewport);
        return spriteView;
    }
}
