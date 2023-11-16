package com.Controllers;

import com.Constants;
import com.Models.AAnimatedUnit;
import com.Models.AUnit;

import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;
import javafx.util.Pair;

public class GraphicsRenderer {
    private Pane root = null;

    public GraphicsRenderer(Pane root) {
        this.root = root;
    }

    private Pair<Double, Double> getScreenCoordinates(double x, double y) {
        double originX = (y * Constants.TILE_WIDTH / 2) + (x * Constants.TILE_WIDTH / 2) + Constants.TILE_WIDTH / 2;
        double originY = (x * Constants.TILE_HEIGHT / 2) - (y * Constants.TILE_HEIGHT / 2)
                + Constants.TILE_HEIGHT * (Constants.TILE_COUNT_HEIGHT / 2);
        return new Pair<Double, Double>(originX, originY);
    }

    private void draw(double x, double y, ImageView sprite) {
        sprite.setX(x);
        sprite.setY(y);
        sprite.setMouseTransparent(true);
        this.root.getChildren().add(sprite);
    }

    public void draw(AUnit unit) {
        var coord = getScreenCoordinates(unit.getX(), unit.getY());
        draw(coord.getKey(), coord.getValue(), unit.getSprite());
    }

    public void draw(AAnimatedUnit unit) {
        var currentSprite = unit.getCurrentSprite();
        if (currentSprite != null)
            root.getChildren().remove(currentSprite);

        var coord = getScreenCoordinates(unit.getX(), unit.getY());
        draw(coord.getKey(), coord.getValue(), unit.getSprite());
    }
}
