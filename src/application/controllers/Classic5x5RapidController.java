package application.controllers;

import application.utils.Rapid;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.CacheHint;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.GridPane;

import java.util.ArrayList;

public class Classic5x5RapidController extends Classic5x5Controller {
    @FXML
    private GridPane boardGrid;

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
        boardGrid.setCache(true);
        boardGrid.setCacheHint(CacheHint.SPEED);
        buttons.forEach(btn -> {
            btn.getStyleClass().add("tile");
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
        title.setText("Classic 5x5");
        playerTurn = 0;
        symbol.setText("X");
        symbol.setStyle("-fx-text-fill:#2f47fc");

        // reset timer for both X and O
        rapid.timerX = 8;
        rapid.timerO = 8;
        // update timer label
        timeX.setText(String.valueOf(rapid.timerX)+"s");
        timeO.setText(String.valueOf(rapid.timerO)+"s");

        if(rapid.timer != null){
            rapid.timer.stop();
        }
        rapid.startTimer(playerTurn, timeX, timeO, title, buttons);
    }

    boolean checkIfGameOver() {
        int gridSize = 25;
        int winCondition = 5;

        String[][] grid = new String[gridSize][gridSize];
        for (int i = 0; i < gridSize * gridSize; i++) {
            grid[i / gridSize][i % gridSize] = buttons.get(i).getText();
        }

        for (int row = 0; row < gridSize; row++) {
            for (int col = 0; col < gridSize; col++) {
                String symbol = grid[row][col];
                if (symbol.isEmpty()) continue;

                if (checkDirection(grid, row, col, 1, 0, winCondition, symbol) || // Horizontal
                        checkDirection(grid, row, col, 0, 1, winCondition, symbol) || // Vertical
                        checkDirection(grid, row, col, 1, 1, winCondition, symbol) || // Diagonal ↘
                        checkDirection(grid, row, col, 1, -1, winCondition, symbol)) { // Diagonal ↙
                    return true;
                }
            }
        }
        return false;
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

    private boolean checkDirection(String[][] grid, int row, int col, int rowDir, int colDir, int winCondition, String symbol) {
        int count = 0;
        for (int i = 0; i < winCondition; i++) {
            int newRow = row + i * rowDir;
            int newCol = col + i * colDir;

            if (newRow < 0 || newRow >= grid.length || newCol < 0 || newCol >= grid[0].length) {
                return false;
            }
            if (!grid[newRow][newCol].equals(symbol)) {
                return false;
            }
            count++;
        }
        return count == winCondition;
    }
}
