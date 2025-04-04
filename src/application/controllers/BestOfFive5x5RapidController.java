package application.controllers;

import application.utils.Rapid;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.CacheHint;
import javafx.scene.control.Button;
import javafx.scene.control.Label;

import java.util.ArrayList;

public class BestOfFive5x5RapidController extends BestOfFive5x5Controller{
    @FXML
    private Label timeX;

    @FXML
    private Label timeO;

    Rapid rapid = new Rapid(8,8);

    public void initialize() {
        buttons = new ArrayList<>();
        for (int row = 0; row < 25; row++) {
            for (int col = 0; col < 25; col++) {
                Button btn = new Button("");
                btn.setMaxSize(Double.MAX_VALUE, Double.MAX_VALUE);
                btn.setStyle("-fx-font-size: 14;");
                boardGrid.add(btn, col, row);
                buttons.add(btn);
                setupButton(btn);
            }
        }
        buttons.forEach(btn -> {
            btn.getStyleClass().add("tile");

        });
        boardGrid.setCache(true);
        boardGrid.setCacheHint(CacheHint.SPEED);
        playerTurn = 0;
        symbol.setText("X");
        symbol.setStyle("-fx-text-fill:#2f47fc");

        timeX.setText(String.valueOf(rapid.timerX) + "s");
        timeO.setText(String.valueOf(rapid.timerO) + "s");

        rapid.startTimer(playerTurn, timeX, timeO, title, buttons); // start timer immediate when game start
    }



    @Override
    @FXML
    public void restartGame(ActionEvent event) {
        buttons.forEach(this::resetButton);
        title.setText("Portal");
        playerTurn = 0;
        symbol.setText("X");
        symbol.setStyle("-fx-text-fill:#2f47fc");

        // reset timer for both X and O
        rapid.timerX = 8;
        rapid.timerO = 8;
        // update timer label
        timeX.setText(String.valueOf(rapid.timerX) + "s");
        timeO.setText(String.valueOf(rapid.timerO) + "s");

        if(rapid.timer != null){
            rapid.timer.stop();
        }
        rapid.startTimer(playerTurn, timeX, timeO, title, buttons);
    }

    @Override
    public void setPlayerSymbol(Button button){
        if (playerTurn % 2 == 0){
            // O's turn to play
            button.setStyle("-fx-text-fill:#2f47fc");
            button.setText("X");
            symbol.setText("O");
            rapid.timerX++;
            symbol.setStyle("-fx-text-fill:#fa3f2f");
            timeX.setText(String.valueOf(rapid.timerX) + "s");
            timeO.setText(String.valueOf(rapid.timerO) + "s");
        }
        else {
            button.setStyle("-fx-text-fill:#fa3f2f");
            button.setText("O");
            symbol.setText("X");
            symbol.setStyle("-fx-text-fill:#2f47fc");
            rapid.timerO++;
            timeX.setText(String.valueOf(rapid.timerX) + "s");
            timeO.setText(String.valueOf(rapid.timerO) + "s");
        }
        playerTurn++;
        // restart timer
        if(rapid.timer != null){
            rapid.timer.stop();
        }
        rapid.startTimer(playerTurn, timeX, timeO, title, buttons);
    }
}
