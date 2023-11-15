package com;

import com.Views.GameScene;

import javafx.application.Application;
import javafx.application.Platform;
import javafx.stage.Stage;

public class MainApp extends Application {

    @Override
    public void start(Stage primaryStage) throws Exception {
        Platform.setImplicitExit(true);
        primaryStage.setTitle("isoTD");
        // primaryStage.setScene(new EditScene());
        primaryStage.setScene(new GameScene());
        primaryStage.show();
        primaryStage.setOnCloseRequest((ae) -> {
            Platform.exit();
            System.exit(0);
        });
    }

    public static void main(String[] args) {
        launch(args);
    }
}
