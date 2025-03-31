package application.controllers;

import application.utils.Rapid;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;

import java.util.ArrayList;
import java.util.Arrays;

public class Rapid3x3Controller extends Classic3x3Controller {
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
    private Label timeX;

    @FXML
    private Label timeO;

    Rapid rapid = new Rapid(3,3);

    @Override
    public void initialize() {
        buttons = new ArrayList<>(Arrays.asList(button1,button2,button3,button4,button5,button6,button7,button8,button9));
        buttons.forEach(button ->{
            setupButton(button);
            button.setFocusTraversable(false);
        });
        playerTurn = 0;
        symbol.setText("X");
        symbol.setStyle("-fx-text-fill:#2f47fc");

        rapid.startTimer(playerTurn, timeX, timeO, title, buttons); // start timer immediate when game start

    }
    @Override
    @FXML
    public void restartGame(ActionEvent event) {
        buttons.forEach(this::resetButton);
        title.setText("Classic 3x3");
        playerTurn = 0;
        symbol.setText("X");
        symbol.setStyle("-fx-text-fill:#2f47fc");

        // reset timer for both X and O
        rapid.timerX = 3;
        rapid.timerO = 3;
        // update timer label
        timeX.setText(String.valueOf(rapid.timerX)+"s");
        timeO.setText(String.valueOf(rapid.timerO)+"s");

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
            symbol.setStyle("-fx-text-fill:#fa3f2f");
            rapid.timerX++; // add 1s to X when move
            timeX.setText(String.valueOf(rapid.timerX) + "s");
        }
        else if (playerTurn % 2 == 1){
            button.setStyle("-fx-text-fill:#fa3f2f");
            button.setText("O");
            symbol.setText("X");
            symbol.setStyle("-fx-text-fill:#2f47fc");
            rapid.timerO++; // add 1s to O when move
            timeO.setText(String.valueOf(rapid.timerO) + "s");
        }
        playerTurn++;

        // restart timer
        if(rapid.timer != null){
            rapid.timer.stop();
        }
        rapid.startTimer(playerTurn, timeX, timeO, title, buttons);
    }

    public boolean checkIfGameOver(){
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
                rapid.timer.stop();
                rapid.disableAllButtons(buttons);
                return true;
            }
            //O winner
            else if (line.equals("OOO")) {
                title.setText("O won!");
                rapid.timer.stop();
                rapid.disableAllButtons(buttons);
                return true;
            }
        }
        // check for draw game
        boolean allFilled = buttons.stream().allMatch(b -> !b.getText().isEmpty());
        if (allFilled) {
            title.setText("It's a Draw!");
            rapid.timer.stop();
            return true;
        }
        return false;
    }

}
