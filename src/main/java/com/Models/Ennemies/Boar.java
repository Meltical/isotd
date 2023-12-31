package com.Models.Ennemies;

import java.io.File;
import java.util.Arrays;
import java.util.List;

import javafx.scene.image.Image;
import javafx.util.Pair;

public class Boar extends Ennemy {
    private static Image[] sprites = null;
    static {
        File folder = new File("src/main/ressources/boar");
        File[] files = folder.listFiles((dir, name) -> name.startsWith("boar"));
        sprites = Arrays.stream(files).map(file -> new Image(file.toURI().toString())).toArray(Image[]::new);
    }

    public Boar(List<Pair<Integer, Integer>> route) {
        super(0, 0, sprites, route, 0.25);
    }
}
