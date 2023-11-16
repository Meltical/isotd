package com.Controllers;

import java.util.Arrays;
import java.util.List;

import com.Models.Ennemies.Boar;
import com.Models.Ennemies.Ennemy;
import com.Models.Ennemies.Wolf;

import javafx.util.Pair;

public class EnnemyController {
    private List<Ennemy> wave;
    private List<List<Pair<Integer, Integer>>> routes;

    public EnnemyController(List<List<Pair<Integer, Integer>>> routes) {
        this.routes = routes;
        init();
    }

    private List<Pair<Integer, Integer>> getRandomRoute() {
        if (routes.isEmpty()) {
            return null;
        }
        return routes.get((int) (Math.random() * routes.size()));
    }

    private void init() {
        this.wave = Arrays.asList(new Boar(getRandomRoute()), new Wolf(getRandomRoute()));
    }

    public void onUpdate() {
        wave.forEach(ennemy -> ennemy.move());
    }

    public List<Ennemy> getWave() {
        return wave;
    }
}
