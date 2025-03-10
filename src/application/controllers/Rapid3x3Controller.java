package application.controllers;

import application.utils.Route;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.util.Duration;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.ResourceBundle;

public class Rapid3x3Controller implements Initializable {
    @FXML
    private Button button1;

    @FXML
    private Button button2;

    @FXML
    private Button button3;

    @FXML
    private Button button4;

    @FXML
    private Button button5;

    @FXML
    private Button button6;

    @FXML
    private Button button7;

    @FXML
    private Button button8;

    @FXML
    private Button button9;

    @FXML
    private Label title;

    @FXML
    private Label symbol;

    @FXML
    private Label timeX;

    @FXML
    private Label timeO;

    private int timerX = 3;
    private int timerO = 3;
    private Timeline timer;

    private int playerTurn;

    ArrayList<Button> buttons;


    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        buttons = new ArrayList<>(Arrays.asList(button1,button2,button3,button4,button5,button6,button7,button8,button9));

        buttons.forEach(button ->{
            setupButton(button);
            button.setFocusTraversable(false);
        });
        playerTurn = 0;
        symbol.setText("X");
        symbol.setStyle("-fx-text-fill:#2f47fc");

        startTimer(); // start timer immediate when game start

    }

    // timer method
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

    @FXML
    private Button backButton;

    @FXML
    void backToOption(ActionEvent event) throws IOException {
        Route.get("gameOption", event);
    }

    @FXML
    void restartGame(ActionEvent event) {
        buttons.forEach(this::resetButton);
        title.setText("Classic 3x3");
        playerTurn = 0;
        symbol.setText("X");
        symbol.setStyle("-fx-text-fill:#2f47fc");

        // reset timer for both X and O
        timerX = 3;
        timerO = 3;
        // update timer label
        timeX.setText(String.valueOf(timerX)+"s");
        timeO.setText(String.valueOf(timerO)+"s");

        if(timer != null){
            timer.stop();
        }
        startTimer();
    }

    public void resetButton(Button button){
        button.setDisable(false);
        button.setText("");
    }

    private void setupButton(Button button) {
        button.setOnMouseClicked(mouseEvent -> {
            setPlayerSymbol(button);
            button.setDisable(true);
            if(checkIfGameIsOver()) {
                buttons.forEach(element -> {
                    element.setDisable(true);
                });
            }
        });
    }

    public void setPlayerSymbol(Button button){
        if (playerTurn % 2 == 0){
            // O's turn to play
            button.setStyle("-fx-text-fill:#2f47fc");
            button.setText("X");
            symbol.setText("O");
            symbol.setStyle("-fx-text-fill:#fa3f2f");
            timerX++; // add 1s to X when move
            timeX.setText(String.valueOf(timerX) + "s");
        }
        else if (playerTurn % 2 == 1){
            button.setStyle("-fx-text-fill:#fa3f2f");
            button.setText("O");
            symbol.setText("X");
            symbol.setStyle("-fx-text-fill:#2f47fc");
            timerO++; // add 1s to O when move
            timeO.setText(String.valueOf(timerO) + "s");
        }
        playerTurn++;

        // restart timer
        if(timer != null){
            timer.stop();
        }
        startTimer();
    }


    public boolean checkIfGameIsOver(){
        for (int a = 0; a < 8; a++) {
            String line = switch (a) {
                case 0 -> button1.getText() + button2.getText() + button3.getText();
                case 1 -> button4.getText() + button5.getText() + button6.getText();
                case 2 -> button7.getText() + button8.getText() + button9.getText();
                case 3 -> button1.getText() + button5.getText() + button9.getText();
                case 4 -> button3.getText() + button5.getText() + button7.getText();
                case 5 -> button1.getText() + button4.getText() + button7.getText();
                case 6 -> button2.getText() + button5.getText() + button8.getText();
                case 7 -> button3.getText() + button6.getText() + button9.getText();
                default -> null;
            };

            //X winner
            if (line.equals("XXX")) {
                title.setText("X won!");
                timer.stop();
                disableAllButtons();
                return true;

            }
            //O winner
            else if (line.equals("OOO")) {
                title.setText("O won!");
                timer.stop();
                disableAllButtons();
                return true;
            }
        }

        // check for draw game
        boolean allFilled = buttons.stream().allMatch(b -> !b.getText().isEmpty());
        if (allFilled) {
            title.setText("It's a Draw!");
            timer.stop();
            return true;
        }

        return false;
    }
    private void disableAllButtons() {
        buttons.forEach(button -> button.setDisable(true));
    }
}
