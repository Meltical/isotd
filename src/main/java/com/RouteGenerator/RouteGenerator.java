package com.RouteGenerator;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Random;

import javafx.util.Pair;

public class RouteGenerator {
    public static List<Pair<Integer, Integer>> generateRoute(int startX, int startY, int endX, int endY) {
        List<Pair<Integer, Integer>> route = new ArrayList<>();
        Random rand = new Random();

        List<Pair<Integer, Integer>> obstacles = new ArrayList<>();
        for (int i = 0; i <= rand.nextInt(10) + 20; i++) {
            int obstacleX = rand.nextInt(endX - 1) + 1;
            int obstacleY = rand.nextInt(endY - 1) + 1;
            obstacles.add(new Pair<>(obstacleX, obstacleY));
        }

        ArrayList<Tile> tiles = new ArrayList<>();
        for (int i = 0; i <= endX; i++) {
            for (int j = 0; j <= endY; j++) {
                if (obstacles.contains(new Pair<>(i, j))) {
                    continue;
                }
                Tile t = new Tile(i, j);
                tiles.add(t);
            }
        }
        Grid grid = new Grid(endX + 1, endY + 1, tiles);

        for (Tile t : grid.getTiles()) {
            t.calculateNeighbours(grid);
        }
        AStarAlgorithm aStar = new AStarAlgorithm(grid);
        aStar.setStart(grid.find(startX, startY));
        aStar.setEnd(grid.find(endX, endY));

        aStar.solve();
        aStar.getPath().forEach(n -> route.add(new Pair<>(((Tile) n).getX(), ((Tile) n).getY())));
        Collections.reverse(route);

        return route;
    }
}
