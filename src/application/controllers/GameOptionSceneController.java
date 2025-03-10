package application.controllers;


import application.Main;
import application.utils.Route;
import javafx.beans.value.ObservableValue;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.stage.Stage;

import javax.swing.*;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;
import java.io.IOException;

public class GameOptionSceneController {
    private Stage stage;
    private Scene scene;
    @FXML
    private Button Start;
    private Button Back;
    @FXML
    private ToggleGroup winCondition;
    @FXML
    private ToggleGroup mode;

    @FXML
    private Label portalCondition;
    @FXML
    private RadioButton radiobutton1, radiobutton2, radiobutton3, radiobutton4, radiobutton5, radiobutton6;

    @FXML
    private void backButton(ActionEvent event) throws IOException {
        Route.get("menu", event);
    }

    public void initialize() {
        winCondition.getToggles().forEach(toggle -> {
            ((RadioButton) toggle).setStyle("visibility: collapse;");
        });
        mode.selectedToggleProperty().addListener((observable, oldValue, newValue) -> {
            if (newValue == radiobutton1) {
                portalCondition.setStyle("visibility: true;");
                winCondition.getToggles().forEach(toggle -> {
                    ((RadioButton) toggle).setStyle("visibility: hidden;");
                });
            } else {
                portalCondition.setStyle("visibility: hidden;");
                winCondition.getToggles().forEach(toggle -> {
                    ((RadioButton) toggle).setStyle("visibility: visible;");
                });
            }
        });
    }


    @FXML
    public void startButtonCondition(ActionEvent event) throws IOException {

        RadioButton selectedMode = (RadioButton) mode.getSelectedToggle();
        RadioButton selectedWinCondition = (RadioButton) winCondition.getSelectedToggle();


        if (selectedMode != null && selectedWinCondition != null) {

            boolean isRapid = radiobutton6.isSelected();

            String modeText = selectedMode.getText();
            String winConditionText = selectedWinCondition.getText();

            String scene = null;


            if (modeText.equals("Portal")) {

                scene = isRapid ? "portalRapid" : "portal";

            } else if (modeText.equals("Best of 5")) {
                if (winConditionText.equals("3-to win")) {
                    scene = isRapid ? "bo53x3Rapid" : "bo53x3";
                } else if (winConditionText.equals("5-to win")) {
                    scene = isRapid ? "bo55x5Rapid" : "bo55x5";
                }
            } else if (modeText.equals("Classic")) {
                if (winConditionText.equals("3-to win")) {
                    scene = isRapid ? "classic3x3Rapid" : "classic3x3";
                } else if (winConditionText.equals("5-to win")) {
                    scene = isRapid ? "classic5x5Rapid" : "classic5x5";
                }
            }

            // If a valid scene is found, navigate to it
            if (scene != null) {
                Route.get(scene, event);
            }
        }
    }

}

