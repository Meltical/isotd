package com.Models.Ennemies;

import com.Models.AAnimatedUnit;

import javafx.scene.image.Image;

public abstract class Ennemy extends AAnimatedUnit {

    public Ennemy(int x, int y, Image[] sprites) {
        super(x, y, sprites);
    }

}
