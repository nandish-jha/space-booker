package com.space_booker;

import com.space_booker.view.MainUI;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.stage.Stage;

/*
The main application class

Sets up the stage and scene and starts execution
All setup of MVC happens in MainUI (com.space_booker.view.MainUI)
*/
public class SpaceBookerApp extends Application {

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage stage) {
        MainUI mainUI = new MainUI();

        Scene scene = new Scene(mainUI);

        stage.setTitle("Space Booker");
        stage.setScene(scene);
        stage.setWidth(700);
        stage.setHeight(600);

        stage.show();
    }
}
