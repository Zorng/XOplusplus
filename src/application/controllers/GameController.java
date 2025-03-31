package application.controllers;


import application.utils.Route;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;

import java.io.IOException;
import java.util.ArrayList;

public abstract class GameController {
    ArrayList<Button> buttons;

    @FXML
    public Label symbol;
    @FXML
    public Label title;
    int playerTurn;

    abstract public void initialize();

    @FXML
    abstract public void restartGame(ActionEvent event);

    @FXML
    public void backToOption(ActionEvent event) throws IOException {
        Route.get("gameOption", event);
    }

    void resetButton(Button button){
        button.getStyleClass().add("tile");
        button.setDisable(false);
        button.setMouseTransparent(false);
        button.setText("");
    }

    void setupButton(Button button) {
        button.setOnMouseClicked(mouseEvent -> {
            setPlayerSymbol(button);
            button.setMouseTransparent(true);
            if(checkIfGameOver()) {
                buttons.forEach(element -> {
                    element.setDisable(true);
                    title.setText("Game Over");
                });
            }
        });
    }

    public void setPlayerSymbol(Button button){
        if (playerTurn % 2 == 0){
            button.setStyle("-fx-text-fill:#2f47fc");
            button.setText("X");
            symbol.setText("O");
            symbol.setStyle("-fx-text-fill:#fa3f2f");
        }
        else {
            button.setStyle("-fx-text-fill:#fa3f2f");
            button.setText("O");
            symbol.setText("X");
            symbol.setStyle("-fx-text-fill:#2f47fc");
        }
        playerTurn++;
    }

    abstract boolean checkIfGameOver();
}
