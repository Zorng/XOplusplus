package application.controllers;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.CacheHint;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.GridPane;

import java.util.ArrayList;

public class BestOfFive5x5Controller extends GameController {
    @FXML
    GridPane boardGrid;
    @FXML
    int playerOneWin = 0;
    int playerTwoWin = 0;
    @FXML
    Label player1WinCounter;
    @FXML
    Label player2WinCounter;

    int lastRow, lastCol;

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
    }

    @FXML
    public void restartGame(ActionEvent event) {
        buttons.forEach(this::resetButton);
        title.setText("BO5 5-TO-WIN");
        playerTurn = 0;
        symbol.setText("X");
        symbol.setStyle("-fx-text-fill:#2f47fc");
    }


    @Override
    public boolean checkIfGameOver() {
        int gridSize = 25;
        int winCondition = 5;

        String[][] grid = new String[gridSize][gridSize];
        for (int i = 0; i < gridSize * gridSize; i++) {
            grid[i / gridSize][i % gridSize] = buttons.get(i).getText();
        }

        String symbol = grid[lastRow][lastCol];
        if (symbol.isEmpty()) return false;

        return checkDirection(grid, lastRow, lastCol, 1, 0, winCondition, symbol) || // Horizontal
                checkDirection(grid, lastRow, lastCol, 0, 1, winCondition, symbol) || // Vertical
                checkDirection(grid, lastRow, lastCol, 1, 1, winCondition, symbol) || // Diagonal ↘
                checkDirection(grid, lastRow, lastCol, 1, -1, winCondition, symbol);  // Diagonal ↙
    }

    private boolean checkDirection(String[][] grid, int row, int col, int rowDir, int colDir, int winCondition, String symbol) {
        int count = 1;

        // Check backward
        int newRow = row - rowDir;
        int newCol = col - colDir;
        while (newRow >= 0 && newCol >= 0 && newRow < grid.length && newCol < grid[0].length && grid[newRow][newCol].equals(symbol)) {
            count++;
            newRow -= rowDir;
            newCol -= colDir;
        }

        // Check forward
        newRow = row + rowDir;
        newCol = col + colDir;
        while (newRow >= 0 && newCol >= 0 && newRow < grid.length && newCol < grid[0].length && grid[newRow][newCol].equals(symbol)) {
            count++;
            newRow += rowDir;
            newCol += colDir;
        }

        return count >= winCondition;
    }
    @Override
    void setupButton(Button button) {
        button.setOnAction(event -> {
            setPlayerSymbol(button);
            button.setMouseTransparent(true);
            lastCol = GridPane.getColumnIndex(button);
            lastRow = GridPane.getRowIndex(button);
            if (checkIfGameOver()) {
                int winningPlayer = (playerTurn - 1) % 2 == 0 ? 1 : 2;
                if (winningPlayer == 1) {
                    title.setText("Player 1 win!");
                } else {
                    title.setText("Player 2 win!");
                }
                winCounter(winningPlayer);
            }
        });
    }

    private void updateWinCounterDisplay() {
        player1WinCounter.setText("Player 1 Wins: " + playerOneWin);
        player2WinCounter.setText("Player 2 Wins: " + playerTwoWin);
    }

    private void resetBo5() {
        playerOneWin = 0;
        playerTwoWin = 0;
        updateWinCounterDisplay();
    }

    private void winCounter(int winningPlayer) {
        if (winningPlayer == 1) {
            playerOneWin++;
        } else if (winningPlayer == 2) {
            playerTwoWin++;
        }
        updateWinCounterDisplay();

        if (playerOneWin == 3) {
            title.setText("Player 1 win the series!");
            resetBo5();
            disableBoard();
        } else if (playerTwoWin == 3) {
            title.setText("Player 2 win the series!");
            resetBo5();
            disableBoard();
        }
    }

    private void disableBoard() {
        // Disable all buttons once the series ends
        for (Button btn : buttons) {
            btn.setDisable(true);
        }
    }
}
