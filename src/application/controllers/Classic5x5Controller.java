package application.controllers;

import application.utils.Route;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.GridPane;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.ResourceBundle;

public class Classic5x5Controller {

    @FXML
    private Label title;

    @FXML
    private Label symbol;

    private int playerTurn;

    @FXML
    private Button backButton;

    ArrayList<Button> buttons;

    @FXML
    private GridPane boardGrid;

    public void initialize() {
        for (int row = 0; row < 25; row++) {
            for (int col = 0; col < 25; col++) {
                Button btn = new Button("Button");
                btn.setMaxSize(Double.MAX_VALUE, Double.MAX_VALUE);
                btn.setStyle("-fx-font-size: 14;");
                boardGrid.add(btn, col, row);
            }
        }
    }

    @FXML
    void backToOption(ActionEvent event) throws IOException {
        Route.get("gameOption", event);
    }

    @FXML
    void restartGame(ActionEvent event) {
        buttons.forEach(this::resetButton);
        title.setText("Classic 5x5");
        playerTurn = 0;
        symbol.setText("X");
        symbol.setStyle("-fx-text-fill:#2f47fc");
    }

    public void resetButton(Button button){
        button.setDisable(false);
        button.setText("");
    }

}
