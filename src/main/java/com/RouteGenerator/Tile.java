package com.RouteGenerator;

import java.util.ArrayList;

import javafx.geometry.Point2D;

public class Tile extends Node {

    private int x, y;
    public static int TILE_SIZE = 20;

    public Tile(int x, int y) {
        this.x = x;
        this.y = y;
        setValid(true);
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

    @Override
    public void calculateNeighbours(Network network) {

        Grid grid = (Grid) network;

        ArrayList<Node> nodes = new ArrayList<>();

        int minX = 0;
        int minY = 0;
        int maxX = grid.getWidth() - 1;
        int maxY = grid.getHeight() - 1;

        if (x > minX) {
            Tile tile = (grid.find(x - 1, y)); // west
            if (tile != null)
                nodes.add(tile);
        }

        if (x < maxX) {
            Tile tile = (grid.find(x + 1, y)); // east
            if (tile != null)
                nodes.add(tile);
        }

        if (y > minY) {
            Tile tile = (grid.find(x, y - 1)); // north
            if (tile != null)
                nodes.add(tile);
        }

        if (y < maxY) {
            Tile tile = (grid.find(x, y + 1)); // south
            if (tile != null)
                nodes.add(tile);
        }

        // if (x > minX && y > minY) {
        // nodes.add(grid.find(x - 1, y - 1)); // northwest
        // }

        // if (x < maxX && y < maxY) {
        // nodes.add(grid.find(x + 1, y + 1)); // southeast
        // }

        // if (x < maxX && y > minY) {
        // nodes.add(grid.find(x + 1, y - 1)); // northeast
        // }

        // if (x > minY && y < maxY) {
        // nodes.add(grid.find(x - 1, y + 1)); // southwest
        // }

        setNeighbours(nodes);

    }

    @Override
    public double heuristic(Node dest) {
        return distanceTo(dest);
    }

    @Override
    public double distanceTo(Node dest) {
        Tile d = (Tile) dest;
        return new Point2D(x, y).distance(new Point2D(d.x, d.y));
    }

}