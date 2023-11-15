package com.Models.Tiles;

import java.io.File;

import javafx.scene.image.Image;

public class Stone extends Tile {
    public Stone(int x, int y) {
        super(x, y, false, true,
                new Image[] {
                        new Image(new File("src/main/ressources/stone.png").toURI().toString())
                });
    }
}
