package application.controllers;


import application.Main;
import application.utils.Route;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.RadioButton;
import javafx.stage.Stage;
import java.io.IOException;

public class GameOptionSceneController {
    private Stage stage;
    private Scene scene;
    @FXML
    private Button Start;
    private Button Back;
    @FXML
    private RadioButton radiobutton1, radiobutton2, radiobutton3, radiobutton4, radiobutton5, radiobutton6;
    @FXML
    private void backButton(ActionEvent event) throws IOException {
        Route.get("menu", event);
    }
    @FXML
    public void startButtonCondition(ActionEvent event) throws IOException {
        if (
                (radiobutton1.isSelected() || radiobutton2.isSelected() || radiobutton3.isSelected()) &&  // One mode must be selected
                        (radiobutton4.isSelected() || radiobutton5.isSelected()) // One win condition must be selected
        ) {
            // Check if Portal mode is selected
            if (radiobutton1.isSelected()) {
                if (radiobutton6.isSelected()) {
                    Route.get("portalRapid", event); // Portal mode with Rapid
                } else {
                    Route.get("portal", event); // Normal Portal mode
                }
            } else {
                Route.get("classic3x3", event); // Default to Classic 3x3
            }
        }
    }
}