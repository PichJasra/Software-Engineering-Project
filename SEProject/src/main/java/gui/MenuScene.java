/**
 * This is the main menu.
 */
package gui;

import javafx.application.Platform;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Cursor;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import org.tinylog.Logger;

import java.awt.*;
import java.net.URI;
import java.util.Arrays;


class MenuScene {
    private Stage window;
    final private String helpURL = "https://en.wikipedia.org/wiki/Fox_games#Fox_and_Hounds";

    MenuScene(Stage window) {
        this.window = window;
    }

    void display() {
        prepareStage();
        VBox layout = prepareLayout();
        Scene scene = new Scene(layout, 333, 333);
        window.setScene(scene);
        window.show();
        GuiUtils.centerWindow(window);

    }

    private VBox prepareLayout() {
        Label label = new Label("Fox Catcher");
        label.setStyle("-fx-font-family: 'Lucida Handwriting'; -fx-font-size: 43px; -fx-font-style: oblique;");
        VBox layout = new VBox(30);
        layout.setPadding(new Insets(0,0,30,0));
        layout.setAlignment(Pos.CENTER);
        layout.getChildren().add(label);
        addButtons(layout);
        return layout;
    }

    private void addButtons(VBox layout) {
        Button newGameButton = new Button("New Game");
        Button helpButton = new Button("Help");
        helpButton.getStyleClass().add("button-help");
        Button[] buttons = {helpButton};
        GuiUtils.setButtonHeightAndBindSizes(50, Arrays.asList(buttons), newGameButton);
        layout.getChildren().addAll(newGameButton, helpButton);

        /**
         * When we click the new game button, it takes us to the NameScene scene.
         */
        newGameButton.setOnAction(event -> {
            NameScene nameScene = new NameScene(this.window);
            nameScene.display();
            /*GameScene gameScene = new GameScene(this.window);
            Logger.debug("Started game ", gameScene);
            gameScene.display();*/
        });

        helpButton.setOnAction(event -> openHelpInBrowser(helpButton));
    }

    private void openHelpInBrowser(Button button) {
        button.setCursor(Cursor.WAIT);
        if (Desktop.isDesktopSupported() && Desktop.getDesktop().isSupported(Desktop.Action.BROWSE)) {
            try {
                Desktop.getDesktop().browse(new URI(helpURL));
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    private void prepareStage() {
        window.setTitle("Fox Catcher");
        window.setResizable(false);
        window.setOnCloseRequest(event -> Platform.exit());
    }
}
