package com.Models;

import java.io.File;
import java.util.Arrays;

import javafx.scene.image.Image;

public class Road extends Tile {
    private static Image[] sprites = null;
    static {
        File folder = new File("src/main/ressources/road");
        File[] files = folder.listFiles((dir, name) -> name.startsWith("road"));
        sprites = Arrays.stream(files).map(file -> new Image(file.toURI().toString())).toArray(Image[]::new);
    }

    public Road(int x, int y) {
        super(x, y, true, false, sprites);
    }
}
