package application.controllers;

import application.utils.Route;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import java.io.IOException;

public class GameOptionSceneController {
    @FXML
    private ToggleGroup winCondition;
    @FXML
    private ToggleGroup mode;

    @FXML
    private Label portalCondition;
    @FXML
    private Label bo5Condition;
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
        bo5Condition.setStyle("visibility: collapse;");
        mode.selectedToggleProperty().addListener((observable, oldValue, newValue) -> {
            if (newValue == radiobutton1) {
                portalCondition.setStyle("visibility: true;");
                winCondition.getToggles().forEach(toggle -> {
                    ((RadioButton) toggle).setStyle("visibility: hidden;");
                });
                bo5Condition.setStyle("visibility: hidden;");
            } else if (newValue == radiobutton2) {
                portalCondition.setStyle("visibility: hidden;");
                winCondition.getToggles().forEach(toggle -> {
                    ((RadioButton) toggle).setStyle("visibility: hidden;");
                });
                bo5Condition.setStyle("visibility: true;");
            }
            else {
                bo5Condition.setStyle("visibility: hidden;");
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
                scene = isRapid ? "bo55x5Rapid" : "bestofFive5x5";
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

