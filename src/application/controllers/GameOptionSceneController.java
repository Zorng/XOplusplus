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
                        (radiobutton4.isSelected() || radiobutton5.isSelected()) &&  // One win condition must be selected
                        (!radiobutton6.isSelected() || radiobutton6.isSelected())  // Rapid is optional (either selected or not)
        ) {
            // Check if Rapid mode is selected
            if (radiobutton6.isSelected()) {
                // If Rapid mode is selected, route to rapid3x3 scene
                Route.get("rapid3x3", event);
            } else {
                // If Rapid mode is not selected, route to classic3x3 scene
                Route.get("classic3x3", event);
            }
        }
    }
}
