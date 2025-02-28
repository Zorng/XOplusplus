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
    @FXML
    ArrayList<Button> buttons;

    @FXML
    private GridPane boardGrid;

    public void initialize() {
        buttons = new ArrayList<>();

        for (int row = 0; row < 25; row++) {
            for (int col = 0; col < 25; col++) {
                Button btn = new Button("");
                btn.setMaxSize(Double.MAX_VALUE, Double.MAX_VALUE);
                btn.setStyle("-fx-font-size: 14;");
                boardGrid.add(btn, col, row);
                buttons.add(btn);
            }
        }

        setupButtons();
        playerTurn = 0;
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
        title.setText("Classic 5x5");
        playerTurn = 0;
        symbol.setText("X");
        symbol.setStyle("-fx-text-fill:#2f47fc");
    }

    public void resetButton(Button button){
        button.setDisable(false);
        button.setText("");
    }

    public void setPlayerSymbol(Button button) {
        if(!button.getText().isEmpty()) return;

        if(playerTurn % 2 == 0){
            button.setText("X");
            symbol.setText("X");
            symbol.setStyle("-fx-text-fill:#fa3f2f");
        }
        else if (playerTurn % 2 == 1){
            button.setText("O");
            symbol.setText("O");
            symbol.setStyle("-fx-text-fill:#2f47fc");
        }
        playerTurn++;
    }
    public boolean checkIfGameOver(ArrayList<Button> buttons) {
        int gridSize = 25;
        int winCondition = 5;

        String[][] grid = new String[gridSize][gridSize];
        for (int i = 0; i < gridSize*gridSize; i++) {
            grid[i / gridSize][i % gridSize] = buttons.get(i).getText();
        }

        for(int row = 0; row < gridSize; row++){
            for(int col = 0; col < gridSize; col++){
                String symbol = grid[row][col];
                if(symbol.isEmpty()) continue;

                if ( checkDirection(grid, row, col, 1, 0, winCondition, symbol) || // Horizontal
                     checkDirection(grid, row, col, 0, 1, winCondition, symbol) || // Vertical
                     checkDirection(grid, row, col, 1, 1, winCondition, symbol) || // Diagonal ↘
                     checkDirection(grid, row, col, 1, -1, winCondition, symbol)) { // Diagonal ↙
                    return true;
                }
            }
        }
        return false;
    }
    private boolean checkDirection(String[][] grid, int row, int col, int rowDir, int colDir, int winCondition, String symbol) {
        int count = 0;
        for(int i = 0; i <winCondition; i++){
            int newRow = row + i * rowDir;
            int newCol = col + i * colDir;

            if(newRow < 0 || newRow >= grid.length || newCol < 0 || newCol >= grid[0].length){
                return false;
            }
            if(!grid[newRow][newCol].equals(symbol)){
                return false;
            }
            count++;
        }
        return count == winCondition;
    }

    private void setupButtons() {
        for (Button button : buttons) {
                button.setOnAction(event -> {
                    setPlayerSymbol(button);
                    button.setDisable(true);

                    if (checkIfGameOver(buttons)) {
                        title.setText("Game Over");
                        for (Button btn : buttons) {
                            btn.setDisable(true);
                        }
                    }
            });
        }
    }
}
