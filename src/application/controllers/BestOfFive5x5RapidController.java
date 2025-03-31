package application.controllers;

import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.CacheHint;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.GridPane;
import javafx.util.Duration;

import java.util.ArrayList;

public class BestOfFive5x5RapidController extends BestOfFive5x5Controller{
    @FXML
    private Label timeX;

    @FXML
    private Label timeO;

    private int timerX = 8;
    private int timerO = 8;
    private Timeline timer;

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

        timeX.setText(String.valueOf(timerX) + "s");
        timeO.setText(String.valueOf(timerO) + "s");

        startTimer(); // start timer immediately after game start
    }

    private void startTimer(){
        timer = new Timeline(new KeyFrame(Duration.seconds(1), event -> {
            if(playerTurn % 2 == 0){
                timerX--;
                timeX.setText(String.valueOf(timerX)+"s");
            } else {
                timerO--;
                timeO.setText(String.valueOf(timerO)+"s");
            }
            if(timerX <= 0){
                title.setText("O won");
                timer.stop();
                disableAllButtons();
            } else if(timerO <= 0){
                title.setText("X won");
                timer.stop();
                disableAllButtons();
            }
        }));
        // set timer to run indefinitely, without stop auto
        timer.setCycleCount(Timeline.INDEFINITE);
        // start time to begin cd
        timer.play();
    }

    private void disableAllButtons() {
        buttons.forEach(button -> button.setDisable(true));
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
        timerX = 8;
        timerO = 8;
        // update timer label
        timeX.setText(String.valueOf(timerX) + "s");
        timeO.setText(String.valueOf(timerO) + "s");

        if(timer != null){
            timer.stop();
        }
        startTimer();
    }

    @Override
    public void setPlayerSymbol(Button button){
        if (playerTurn % 2 == 0){
            // O's turn to play
            button.setStyle("-fx-text-fill:#2f47fc");
            button.setText("X");
            symbol.setText("O");
            timerX++;
            symbol.setStyle("-fx-text-fill:#fa3f2f");
            timeX.setText(String.valueOf(timerX) + "s");
            timeO.setText(String.valueOf(timerO) + "s");
        }
        else {
            button.setStyle("-fx-text-fill:#fa3f2f");
            button.setText("O");
            symbol.setText("X");
            symbol.setStyle("-fx-text-fill:#2f47fc");
            timerO++;
            timeX.setText(String.valueOf(timerX) + "s");
            timeO.setText(String.valueOf(timerO) + "s");
        }
        playerTurn++;
    }
}
