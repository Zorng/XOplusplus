package application.controllers;

import application.utils.Route;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.stage.Stage;

import java.io.IOException;

public class MenuController {
    @FXML
    private Button exitButton;

    @FXML
    public void playButtonListener(ActionEvent event) throws IOException {
        Route.get("gameOption", event);
    }

    @FXML
    public void howButtonListener(ActionEvent event) throws IOException {
        Route.get("howToPlay", event);
    }
    @FXML
    public void exitButtonListener(ActionEvent event) {
        Stage stage = (Stage) exitButton.getScene().getWindow();
        stage.close();
    }
}
