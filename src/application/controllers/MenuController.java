package application.controllers;

import application.utils.Route;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import java.io.IOException;

public class MenuController {

    @FXML
    private VBox Footer;

    @FXML
    private HBox Header;

    @FXML
    private VBox Main;

    @FXML
    private VBox Menu;

    @FXML
    private Label appName;

    @FXML
    private Button creditButton;

    @FXML
    private Button exitButton;

    @FXML
    private VBox exitOptionContainer;

    @FXML
    private Button htpButton;

    @FXML
    private VBox menuOptionsContainer;

    @FXML
    private Button playButton;

    @FXML
    void playButtonListener(ActionEvent event) throws IOException {
        Route.get("demo", event);
        // Route::get("demo.fxml");

    }
    void exitButtonListener(ActionEvent event) {

    }

}
