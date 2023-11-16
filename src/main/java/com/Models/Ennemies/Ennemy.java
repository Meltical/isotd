package com.Models.Ennemies;

import java.util.List;

import com.Models.AAnimatedUnit;
import com.Models.IMovable;

import javafx.scene.image.Image;
import javafx.util.Pair;

public abstract class Ennemy extends AAnimatedUnit implements IMovable {
    private List<Pair<Integer, Integer>> route;
    private double speed;
    private int currentIndex = 0;

    public Ennemy(double x, double y, Image[] sprites, List<Pair<Integer, Integer>> route, double speed) {
        super(x, y, sprites);
        this.route = route;
        this.speed = speed;
    }

    public Ennemy(double x, double y, Image[] sprites, List<Pair<Integer, Integer>> route, double speed,
            double animationSpeed) {
        super(x, y, sprites, animationSpeed);
        this.route = route;
        this.speed = speed;
    }

    public void move() {
        if (currentIndex >= route.size()) {
            return;
        }
        Pair<Integer, Integer> nextPosition = route.get(currentIndex);
        double nextX = nextPosition.getKey();
        double nextY = nextPosition.getValue();
        double deltaX = nextX - getX();
        double deltaY = nextY - getY();
        double distance = Math.sqrt(deltaX * deltaX + deltaY * deltaY);
        if (distance <= speed) {
            setX(nextX);
            setY(nextY);
            currentIndex++;
        } else {
            double ratio = speed / distance;
            double moveX = ratio * deltaX;
            double moveY = ratio * deltaY;
            setX(getX() + moveX);
            setY(getY() + moveY);
        }
    }
}
