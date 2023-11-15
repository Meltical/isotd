package com.Models;

public abstract class APosition {
    private int x;
    private int y;

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

    public APosition(int x, int y) {
        this.x = x;
        this.y = y;
    }
}
