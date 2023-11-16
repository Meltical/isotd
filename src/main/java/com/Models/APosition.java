package com.Models;

public abstract class APosition {
    private double x;
    private double y;

    public void setX(double x) {
        this.x = x;
    }

    public void setY(double y) {
        this.y = y;
    }

    public double getX() {
        return x;
    }

    public double getY() {
        return y;
    }

    public APosition(double x, double y) {
        this.x = x;
        this.y = y;
    }
}
