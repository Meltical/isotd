package com.Views;

import java.util.function.Consumer;

import com.Constants;
import com.Controllers.AssetManager;
import com.Controllers.TileManagerOld;
import com.Views.Components.CustomButton;

import javafx.geometry.Point2D;
import javafx.geometry.Rectangle2D;
import javafx.scene.Scene;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.stage.Modality;
import javafx.stage.Stage;

public class EditScene extends Scene {
    private Pane root = null;
    private ImageView currentSprite = null;
    private TileManagerOld tileManager = null;

    public EditScene() {
        super(new Pane(), Constants.WINDOW_WIDTH, Constants.WINDOW_HEIGHT);
        tileManager = new TileManagerOld();
        root = (Pane) getRoot();
        Pane grid = TileManagerOld.createGrid(onTileClicked, onTileHover);
        root.getChildren().add(grid);
        addTilesButton(root);
    }

    private final void onRender() {
        root.getChildren().removeIf(node -> node instanceof ImageView);
        tileManager.onRender(root);
    }

    private final Consumer<Point2D> onTileClicked = (Point2D point) -> {
        if (currentSprite != null) {
            ImageView cloneSprite = new ImageView(currentSprite.getImage());
            cloneSprite.setViewport(currentSprite.getViewport());
            tileManager.addTile(cloneSprite, (int) point.getX(), (int) point.getY());
            onRender();
        }
    };

    private final Consumer<Point2D> onTileHover = (Point2D point) -> {
        if (currentSprite != null) {
            ImageView cloneSprite = new ImageView(currentSprite.getImage());
            cloneSprite.setViewport(currentSprite.getViewport());
            tileManager.addHoverTile(cloneSprite, (int) point.getX(), (int) point.getY());
            onRender();
        }
    };

    private void addTilesButton(Pane root) {
        CustomButton tilesButton = new CustomButton("Tiles");
        tilesButton.setTranslateX(20);
        tilesButton.setTranslateY(10);

        tilesButton.setOnAction(e -> {
            ImageView tileSheet = new ImageView(AssetManager.TileSheet);
            final Stage dialog = new Stage();
            dialog.initModality(Modality.APPLICATION_MODAL);
            VBox dialogVbox = new VBox();
            dialogVbox.getChildren().add(tileSheet);
            Scene dialogScene = new Scene(dialogVbox);
            dialog.setScene(dialogScene);
            dialog.show();

            tileSheet.setOnMouseClicked(event -> {
                double x = Math.floor(event.getX() / 32) * 32;
                double y = Math.floor(event.getY() / 32) * 32;
                ImageView sprite = AssetManager.getInstance()
                        .getSprite(new Rectangle2D(x, y, 32, 32));
                currentSprite = sprite;
                dialog.close();
            });
        });

        root.getChildren().add(tilesButton);
    }
}
