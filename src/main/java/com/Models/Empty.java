package com.Models;

import java.io.File;
import java.util.Arrays;

import javafx.scene.image.Image;

public class Empty extends Tile {
    private static Image[] sprites = null;
    static {
        File folder = new File("src/main/ressources/empty");
        File[] files = folder.listFiles((dir, name) -> name.startsWith("empty"));
        sprites = Arrays.stream(files).map(file -> new Image(file.toURI().toString())).toArray(Image[]::new);
    }

    public Empty(int x, int y) {
        super(x, y, false, false, sprites);
    }
}
