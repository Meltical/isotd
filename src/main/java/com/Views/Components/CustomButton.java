package com.Views.Components;

import javafx.scene.control.Button;
import javafx.scene.effect.DropShadow;

public class CustomButton extends Button {

    public CustomButton(String text) {
        super(text);
        setStyle("-fx-background-color: #0077C0; -fx-text-fill: white;");
        setOnMouseEntered(e -> setEffect(new DropShadow()));
        setOnMouseExited(e -> setEffect(null));
        setOnMousePressed(e -> setStyle("-fx-background-color: #005EA3; -fx-text-fill: white;"));
        setOnMouseReleased(e -> setStyle("-fx-background-color: #0077C0; -fx-text-fill: white;"));
    }
}
