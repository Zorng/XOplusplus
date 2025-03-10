package application.controllers;

import application.utils.Route;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.ResourceBundle;

public class PortalController implements Initializable {

    @FXML
    private Button backButton;

    @FXML
    private Button btn0;

    @FXML
    private Button btn1;

    @FXML
    private Button btn10;

    @FXML
    private Button btn11;

    @FXML
    private Button btn12;

    @FXML
    private Button btn13;

    @FXML
    private Button btn14;

    @FXML
    private Button btn15;

    @FXML
    private Button btn16;

    @FXML
    private Button btn17;

    @FXML
    private Button btn18;

    @FXML
    private Button btn19;

    @FXML
    private Button btn2;

    @FXML
    private Button btn20;

    @FXML
    private Button btn21;

    @FXML
    private Button btn22;

    @FXML
    private Button btn23;

    @FXML
    private Button btn24;

    @FXML
    private Button btn25;

    @FXML
    private Button btn26;

    @FXML
    private Button btn27;

    @FXML
    private Button btn28;

    @FXML
    private Button btn29;

    @FXML
    private Button btn3;

    @FXML
    private Button btn30;

    @FXML
    private Button btn31;

    @FXML
    private Button btn32;

    @FXML
    private Button btn33;

    @FXML
    private Button btn34;

    @FXML
    private Button btn35;

    @FXML
    private Button btn4;

    @FXML
    private Button btn5;

    @FXML
    private Button btn6;

    @FXML
    private Button btn7;

    @FXML
    private Button btn8;

    @FXML
    private Button btn9;

    @FXML
    private Label symbol;

    @FXML
    private Label title;

    private int playerTurn;

    ArrayList<Button> buttons;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        buttons = new ArrayList<>(Arrays.asList(
                btn0,  btn1,  btn2,  btn3,  btn4,  btn5,
                btn6,  btn7,  btn8,  btn9,  btn10, btn11,
                btn12, btn13, btn14, btn15, btn16, btn17,
                btn18, btn19, btn20, btn21, btn22, btn23,
                btn24, btn25, btn26, btn27, btn28, btn29,
                btn30, btn31, btn32, btn33, btn34, btn35));
        buttons.forEach(button ->{
            setupButton(button);
            button.setFocusTraversable(false);
            button.getStyleClass().add("tile");
        });
        playerTurn = 0;
        title.setText("Portal");
        symbol.setText("X");
        symbol.setStyle("-fx-text-fill:#2f47fc");

    }

    @FXML
    void backToOption(ActionEvent event) throws IOException {
        Route.get("gameOption", event);
    }

    @FXML
    void restartGame(ActionEvent event) {
        buttons.forEach(this::resetButton);
        title.setText("Portal");
        playerTurn = 0;
        symbol.setText("X");
        symbol.setStyle("-fx-text-fill:#2f47fc");
    }



    public void resetButton(Button button){
        button.setDisable(false);
        button.setMouseTransparent(false);
        button.setText("");
    }

    private void setupButton(Button button) {
        button.setOnMouseClicked(mouseEvent -> {
            setPlayerSymbol(button);
            button.setMouseTransparent(true);
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


        }
        else {
            button.setStyle("-fx-text-fill:#fa3f2f");
            button.setText("O");
            symbol.setText("X");
            symbol.setStyle("-fx-text-fill:#2f47fc");

        }
        playerTurn++;
    }

    public boolean checkIfGameIsOver(){

        StringBuilder line = new StringBuilder();

        //check vertical portal
        for(int j = 0; j < 6; j++) {
            //check vertical x|x|x| | |x
            for(int i = j; i < j + 13; i += 6) {
                line.append(buttons.get(i).getText());
            }
            line.append(buttons.get(j+30).getText());
            if(isLine(line.toString())) {
                return true;
            }
            line.setLength(0);
            //check vertical |x|x| | |x|x|
            line.append(buttons.get(j).getText());
            line.append(buttons.get(j+6).getText());
            line.append(buttons.get(j+24).getText());
            line.append(buttons.get(j+30).getText());
            if(isLine(line.toString())) {
                return true;
            }
            line.setLength(0);

            //check vertical |x| | |x|x|x|
            line.append(buttons.get(j).getText());
            for(int i = j + 18; i < j + 31; i += 6) {
                line.append(buttons.get(i).getText());
            }
            if(isLine(line.toString())) {
                return true;
            }
            line.setLength(0);
        }

        //check classic 4x4 horizontal
        for(int l = 0; l < 13; l+=6) {
            for(int k = 0; k < 3; k++) {
                for(int j = l; j < 19+l; j +=6) {
                    for(int i = j + k; i < 4 + j + k; i++) {
                        line.append(buttons.get(i).getText());
                    }
                    if(isLine(line.toString())) {
                        return true;
                    }
                    line.setLength(0);
                }
            }
        }

        //check classic 4x4 vertical

        for(int k = 0; k < 13; k+=6) {
            for(int l = 0; l < 3; l++) {
                for(int j = l; j < 4+l; j++) {
                    for(int i = j + k; i < 19 + k + j; i += 6) {
                        line.append(buttons.get(i).getText());
                    }
                    if(isLine(line.toString())) {
                        return true;
                    }
                    line.setLength(0);
                }
            }
        }


        //check classic diagonal
        for(int k = 0; k < 13; k+=6) {
            for(int j = k; j < 3 + k; j++) {
                for(int i = j; i < 22 + j; i += 7) {
                    line.append(buttons.get(i).getText());
                }
                if(isLine(line.toString())) {
                    return true;
                }
                line.setLength(0);

                for(int i = 3 + j; i < 19 + j; i += 5) {
                    line.append(buttons.get(i).getText());
                }
                if(isLine(line.toString())) {
                    return true;
                }
                line.setLength(0);
            }
        }

        //check all horizontal portal
        for(int j = 0; j < 31; j+=6) {
            // check x|x|x| | |x
            for(int i = j; i < j+3; i++) {
                line.append(buttons.get(i).getText());
            }
            line.append(buttons.get(j+5).getText());
            if(isLine(line.toString())) {
                return true;
            }
            line.setLength(0);

            //check |x|x| | |x|x|
            line.append(buttons.get(j).getText());
            line.append(buttons.get(j+1).getText());
            line.append(buttons.get(j+4).getText());
            line.append(buttons.get(j+5).getText());
            if(isLine(line.toString())) {
                return true;
            }
            line.setLength(0);

            //check |x| | |x|x|x|
            line.append(buttons.get(j).getText());
            for(int i = j+3; i < j + 6; i++) {
                line.append(buttons.get(i).getText());
            }
            if(isLine(line.toString())) {
                return true;
            }
            line.setLength(0);
        }

        //check diagonal
        for(int i = 0; i < 18; i++) {
            switch (i) {
                case 0: {
                    line.append(buttons.get(0).getText());
                    line.append(buttons.get(7).getText());
                    line.append(buttons.get(14).getText());
                    line.append(buttons.get(35).getText());
                    if (isLine(line.toString())) {
                        return true;
                    }
                    line.setLength(0);
                    break;
                }
                case 1: {
                    line.append(buttons.get(0).getText());
                    line.append(buttons.get(7).getText());
                    line.append(buttons.get(28).getText());
                    line.append(buttons.get(35).getText());
                    if (isLine(line.toString())) {
                        return true;
                    }
                    line.setLength(0);
                    break;
                }
                case 2: {
                    line.append(buttons.getFirst().getText());
                    line.append(buttons.get(21).getText());
                    line.append(buttons.get(28).getText());
                    line.append(buttons.get(35).getText());
                    if (isLine(line.toString())) {
                        return true;
                    }
                    line.setLength(0);
                    break;
                }
                case 3: {
                    line.append(buttons.get(5).getText());
                    line.append(buttons.get(10).getText());
                    line.append(buttons.get(15).getText());
                    line.append(buttons.get(30).getText());
                    if (isLine(line.toString())) {
                        return true;
                    }
                    line.setLength(0);
                    break;
                }
                case 4: {
                    line.append(buttons.get(5).getText());
                    line.append(buttons.get(10).getText());
                    line.append(buttons.get(25).getText());
                    line.append(buttons.get(30).getText());
                    if (isLine(line.toString())) {
                        return true;
                    }
                    line.setLength(0);
                    break;
                }
                case 5: {
                    line.append(buttons.get(5).getText());
                    line.append(buttons.get(15).getText());
                    line.append(buttons.get(20).getText());
                    line.append(buttons.get(25).getText());
                    if (isLine(line.toString())) {
                        return true;
                    }
                    break;
                }
                case 6: {
                    line.append(buttons.get(1).getText());
                    line.append(buttons.get(15).getText());
                    line.append(buttons.get(22).getText());
                    line.append(buttons.get(29).getText());
                    if (isLine(line.toString())) {
                        return true;
                    }
                    line.setLength(0);
                    break;
                }
                case 7: {
                    line.append(buttons.get(1).getText());
                    line.append(buttons.get(8).getText());
                    line.append(buttons.get(22).getText());
                    line.append(buttons.get(29).getText());
                    if (isLine(line.toString())) {
                        return true;
                    }
                    line.setLength(0);
                    break;
                }
                case 8: {
                    line.append(buttons.get(1).getText());
                    line.append(buttons.get(8).getText());
                    line.append(buttons.get(15).getText());
                    line.append(buttons.get(29).getText());
                    if (isLine(line.toString())) {
                        return true;
                    }
                    line.setLength(0);
                    break;
                }
                case 9: {
                    line.append(buttons.get(6).getText());
                    line.append(buttons.get(20).getText());
                    line.append(buttons.get(27).getText());
                    line.append(buttons.get(34).getText());
                    if (isLine(line.toString())) {
                        return true;
                    }
                    line.setLength(0);
                    break;
                }
                case 10: {
                    line.append(buttons.get(6).getText());
                    line.append(buttons.get(13).getText());
                    line.append(buttons.get(27).getText());
                    line.append(buttons.get(34).getText());
                    if (isLine(line.toString())) {
                        return true;
                    }
                    line.setLength(0);
                    break;
                }
                case 11: {
                    line.append(buttons.get(6).getText());
                    line.append(buttons.get(13).getText());
                    line.append(buttons.get(20).getText());
                    line.append(buttons.get(34).getText());
                    if (isLine(line.toString())) {
                        return true;
                    }
                    line.setLength(0);
                    break;
                }
                case 12: {
                    line.append(buttons.get(4).getText());
                    line.append(buttons.get(14).getText());
                    line.append(buttons.get(19).getText());
                    line.append(buttons.get(24).getText());
                    if (isLine(line.toString())) {
                        return true;
                    }
                    line.setLength(0);
                    break;
                }
                case 13: {
                    line.append(buttons.get(4).getText());
                    line.append(buttons.get(9).getText());
                    line.append(buttons.get(19).getText());
                    line.append(buttons.get(24).getText());
                    if (isLine(line.toString())) {
                        return true;
                    }
                    line.setLength(0);
                    break;
                }
                case 14: {
                    line.append(buttons.get(4).getText());
                    line.append(buttons.get(9).getText());
                    line.append(buttons.get(14).getText());
                    line.append(buttons.get(24).getText());
                    if (isLine(line.toString())) {
                        return true;
                    }
                    line.setLength(0);
                    break;
                }
                case 15: {
                    line.append(buttons.get(11).getText());
                    line.append(buttons.get(21).getText());
                    line.append(buttons.get(26).getText());
                    line.append(buttons.get(31).getText());
                    if (isLine(line.toString())) {
                        return true;
                    }
                    line.setLength(0);
                    break;
                }
                case 16: {
                    line.append(buttons.get(11).getText());
                    line.append(buttons.get(16).getText());
                    line.append(buttons.get(26).getText());
                    line.append(buttons.get(31).getText());
                    if (isLine(line.toString())) {
                        return true;
                    }
                    line.setLength(0);
                    break;
                }
                case 17: {
                    line.append(buttons.get(11).getText());
                    line.append(buttons.get(16).getText());
                    line.append(buttons.get(21).getText());
                    line.append(buttons.get(31).getText());
                    if (isLine(line.toString())) {
                        return true;
                    }
                    line.setLength(0);
                    break;
                }
                default: {
                    break;
                }
            }
        }
        line.setLength(0);
        return false;
    }

    private boolean isLine (String input) {
        if(input.equals("XXXX")) {
            title.setText("X won!");
            return true;
        } else if (input.equals("OOOO")) {
            title.setText("O won!");
            return true;
        }
        return false;
    }

}
