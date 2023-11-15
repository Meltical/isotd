package com.Controllers;

import java.util.function.Consumer;

import com.Constants;

import javafx.geometry.Point2D;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseButton;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Polygon;
import javafx.util.Pair;

public class TileManagerOld {
    private ImageView[][] tiles = new ImageView[Constants.TILE_COUNT][Constants.TILE_COUNT];
    private Pair<ImageView, Point2D> hoverTile = null;

    public void addTile(ImageView tile, int x, int y) {
        tiles[x][y] = tile;
    }

    public void addHoverTile(ImageView tile, int x, int y) {
        hoverTile = new Pair<ImageView, Point2D>(tile, new Point2D(x, y));
    }

    private void drawTile(Pane root, ImageView sprite, int x, int y) {
        int originX = (y * Constants.TILE_WIDTH / 2) + (x * Constants.TILE_WIDTH / 2)
                + Constants.TILE_WIDTH / 2;
        int originY = (x * Constants.TILE_HEIGHT / 2) - (y * Constants.TILE_HEIGHT / 2)
                + Constants.TILE_HEIGHT * (Constants.TILE_COUNT / 2);

        ImageView tile = sprite;
        tile.setX(originX);
        tile.setY(originY);
        tile.setMouseTransparent(true);
        root.getChildren().add(tile);
    }

    public void onRender(Pane root) {
        for (int i = 0; i < 10; i++) {
            for (int j = 9; j >= 0; j--) {
                if (hoverTile != null && hoverTile.getValue().getX() == i && hoverTile.getValue().getY() == j) {
                    drawTile(root, hoverTile.getKey(), i, j);
                    hoverTile = null;
                } else {
                    if (tiles[i][j] == null)
                        continue;
                    drawTile(root, tiles[i][j], i, j);
                }
            }
        }
    }

    public static Pane createGrid(Consumer<Point2D> onTileClicked, Consumer<Point2D> onTileHover) {
        Pane grid = new Pane();
        grid.setOnDragDetected(
                event -> {
                    if (event.getButton() == MouseButton.PRIMARY) {
                        event.consume();
                        grid.startFullDrag();
                    }
                });
        for (int i = 0; i < 10; i++) {
            for (int j = 9; j >= 0; j--) {

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
