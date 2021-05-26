/**
 * This is the class, where the players can input their names
 */

package gui;

import com.fasterxml.jackson.databind.ObjectMapper;
import javafx.application.Platform;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import org.tinylog.Logger;


public class NameScene {
    private Stage window;

    NameScene(Stage window) {
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
        VBox layout = new VBox(30);
        layout.setPadding(new Insets(0,0,30,0));
        layout.setAlignment(Pos.CENTER);
        addButtons(layout);
        return layout;
    }

    /**
     * This method is the same in all scene classes. It adds the buttons, textfields and other stuff the user can interact.
     * @param layout
     */
    private void addButtons(VBox layout) {
        Button okButton = new Button("Start");
        Label l1= new Label("Player one name");
        TextField playerone = new TextField();
        Label l2= new Label("Player two name");
        TextField playertwo = new TextField();
        layout.getChildren().addAll(l1,playerone,l2,playertwo,okButton);


        okButton.setOnAction(event -> {

            GameScene gameScene = new GameScene(this.window);
            Logger.debug("Started game ", gameScene);
            gameScene.display();
        });
    }

    private void prepareStage() {
        window.setTitle("Enter your name");
        window.setResizable(false);
        window.setOnCloseRequest(event -> Platform.exit());
    }
}