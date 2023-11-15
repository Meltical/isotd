package com.Controllers;

import java.util.function.Consumer;

import com.Constants;
import com.Models.Tiles.Empty;
import com.Models.Tiles.Road;
import com.Models.Tiles.Stone;
import com.Models.Tiles.Tile;
import com.RouteGenerator.RouteGenerator;

import javafx.geometry.Point2D;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseButton;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Polygon;

public class TileController {
    private Tile[][] tiles;
    private Tile hoverTile = null;
    private int width;
    private int height;
    private Pane root;

    public TileController(Pane root, int width, int height) {
        this.root = root;
        this.width = width;
        this.height = height;
        this.tiles = new Tile[width][height];
        initTiles();
        onRender();
    }

    private void initTiles() {
        var roadCoords = RouteGenerator.generateRoute(0, 0, width - 1, height - 1);
        for (var coord : roadCoords) {
            tiles[coord.getKey()][coord.getValue()] = new Road(coord.getKey(), coord.getValue());
            if (coord.getKey() > 0 && tiles[coord.getKey() - 1][coord.getValue()] == null) {
                tiles[coord.getKey() - 1][coord.getValue()] = new Stone(coord.getKey() - 1, coord.getValue());
            }
            if (coord.getKey() < width - 1 && tiles[coord.getKey() + 1][coord.getValue()] == null) {
                tiles[coord.getKey() + 1][coord.getValue()] = new Stone(coord.getKey() + 1, coord.getValue());
            }
            if (coord.getValue() > 0 && tiles[coord.getKey()][coord.getValue() - 1] == null) {
                tiles[coord.getKey()][coord.getValue() - 1] = new Stone(coord.getKey(), coord.getValue() - 1);
            }
            if (coord.getValue() < height - 1 && tiles[coord.getKey()][coord.getValue() + 1] == null) {
                tiles[coord.getKey()][coord.getValue() + 1] = new Stone(coord.getKey(), coord.getValue() + 1);
            }
        }
        for (int i = 0; i < width; i++) {
            for (int j = 0; j < height; j++) {
                if (tiles[i][j] == null) {
                    tiles[i][j] = new Empty(i, j);
                }
            }
        }
    }

    public void addTile(Tile tile, int x, int y) {
        tiles[x][y] = tile;
    }

    public void setHoverTile(Tile tile) {
        hoverTile = tile;
    }

    private void drawTile(Tile tile) {
        int originX = (tile.getY() * Constants.TILE_WIDTH / 2) + (tile.getX() * Constants.TILE_WIDTH / 2)
                + Constants.TILE_WIDTH / 2;
        int originY = (tile.getX() * Constants.TILE_HEIGHT / 2) - (tile.getY() * Constants.TILE_HEIGHT / 2)
                + Constants.TILE_HEIGHT * (Constants.TILE_COUNT / 2);

        ImageView sprite = tile.getSprite();
        sprite.setX(originX);
        sprite.setY(originY);
        sprite.setMouseTransparent(true);
        root.getChildren().add(sprite);
    }

    public void onRender() {
        for (int i = 0; i < width; i++) {
            for (int j = height - 1; j >= 0; j--) {
                if (hoverTile != null && hoverTile.getX() == i && hoverTile.getY() == j) {
                    drawTile(hoverTile);
                    hoverTile = null;
                } else {
                    if (tiles[i][j] == null)
                        continue;
                    drawTile(tiles[i][j]);
                }
            }
        }
    }

    public Pane createGrid(Consumer<Point2D> onTileClicked, Consumer<Point2D> onTileHover) {
        Pane grid = new Pane();
        grid.setOnDragDetected(
                event -> {
                    if (event.getButton() == MouseButton.PRIMARY) {
                        event.consume();
                        grid.startFullDrag();
                    }
                });

        for (int i = 0; i < width; i++) {
            for (int j = height - 1; j >= 0; j--) {

                int originX = (j * Constants.TILE_WIDTH / 2) + (i * Constants.TILE_WIDTH / 2)
                        + Constants.TILE_WIDTH;
                int originY = (i * Constants.TILE_HEIGHT / 2) - (j * Constants.TILE_HEIGHT / 2)
                        + Constants.TILE_HEIGHT * (1 + Constants.TILE_COUNT / 2);

                Polygon tile = new Polygon(
                        originX, originY,
                        originX + Constants.TILE_WIDTH / 2, originY + Constants.TILE_HEIGHT / 2,
                        originX, originY + Constants.TILE_HEIGHT,
                        originX - Constants.TILE_WIDTH / 2, originY + Constants.TILE_HEIGHT / 2);

                tile.setFill(Color.WHITE);
                tile.setStrokeWidth(1);
                tile.setStroke(Color.BLACK);

                final int iCopy = i;
                final int jCopy = j;

                tile.setOnMouseClicked(event -> onTileClicked.accept(new Point2D(iCopy, jCopy)));
                tile.setOnMouseDragOver(event -> onTileClicked.accept(new Point2D(iCopy, jCopy)));
                tile.setOnMouseEntered(event -> onTileHover.accept(new Point2D(iCopy, jCopy)));

                grid.getChildren().add(tile);
            }
        }
        return grid;
    }
}
