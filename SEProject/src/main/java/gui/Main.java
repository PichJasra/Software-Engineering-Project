package gui;

import javafx.application.Application;
import javafx.stage.Stage;

public class Main extends Application {

    @Override
    public void start(Stage window) {
        MenuScene menuScene = new MenuScene(window);
        menuScene.display();
    }

    public static void main(String[] args) {
        launch(args);
    }
}
