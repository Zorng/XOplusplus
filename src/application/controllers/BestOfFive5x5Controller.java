package application.controllers;

import application.utils.Route;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.CacheHint;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.layout.GridPane;

import java.io.IOException;
import java.util.ArrayList;

public class BestOfFive5x5Controller {

    @FXML
    private Label title;

    @FXML
    private ScrollPane scrollPane;

    @FXML
    private Label symbol;

    private int playerTurn;

    @FXML
    private Button backButton;
    @FXML
    ArrayList<Button> buttons;

    @FXML
    private GridPane boardGrid;

    private double pointerX, pointerY;
    private double initialTranslateX, initialTranslateY;
    @FXML
    private int playerOneWin = 0;
    private int playerTwoWin = 0;
    @FXML
    private Label player1WinCounter;
    @FXML
    private Label player2WinCounter;

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


        buttons.forEach(btn -> {
            btn.getStyleClass().add("tile");

        });

        boardGrid.setCache(true);
        boardGrid.setCacheHint(CacheHint.SPEED);

        setupButtons();
        playerTurn = 0;
        symbol.setText("X");
        symbol.setStyle("-fx-text-fill:#2f47fc");

//        boardGrid.setOnMouseClicked((MouseEvent event) -> {
//            pointerX = event.getSceneX();
//            pointerY = event.getSceneY();
//            initialTranslateX = boardGrid.getTranslateX();
//            initialTranslateY = boardGrid.getTranslateY();
//        });

//        boardGrid.setOnMouseDragged((MouseEvent event) -> {
//            double deltaX = event.getSceneX() - pointerX;
//            double deltaY = event.getSceneY() - pointerY;
//            boardGrid.setTranslateX(initialTranslateX + deltaX);
//            boardGrid.setTranslateY(initialTranslateY + deltaY);
//        });

//        boardGrid.setOnTouchPressed((TouchEvent event) -> {
//            pointerX = event.getTouchPoint().getSceneX();
//            pointerY = event.getTouchPoint().getSceneY();
//            initialTranslateX = boardGrid.getTranslateX();
//            initialTranslateY = boardGrid.getTranslateY();
//        });
//
//        boardGrid.setOnTouchMoved((TouchEvent event) -> {
//            double deltaX = event.getTouchPoint().getSceneX() - pointerX;
//            double deltaY = event.getTouchPoint().getSceneY() - pointerY;
//            boardGrid.setTranslateX(initialTranslateX + deltaX);
//            boardGrid.setTranslateY(initialTranslateY + deltaY);
//        });
    }

    @FXML
    void backToOption(ActionEvent event) throws IOException {
        Route.get("gameOption", event);
    }

    @FXML
    void restartGame(ActionEvent event) {
        buttons.forEach(this::resetButton);
        title.setText("BO5 5-TO-WIN");
        playerTurn = 0;
        symbol.setText("X");
        symbol.setStyle("-fx-text-fill:#2f47fc");
    }

    public void resetButton(Button button){
        button.setDisable(false);
        button.setMouseTransparent(false);
        button.setText("");
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
                button.setMouseTransparent(true);
                if (checkIfGameOver(buttons)) {
                    int winningPlayer = (playerTurn - 1 ) % 2 == 0 ? 1 : 2;
                    if(winningPlayer == 1){
                        title.setText("Player 1 win!");
                    } else {
                        title.setText("Player 2 win!");
                    }
                    winCounter(winningPlayer);
                    for (Button btn : buttons) {
                        btn.setDisable(true);
                    }
                }
            });
        }
    }
    private void updateWinCounterDisplay() {
        player1WinCounter.setText("Player 1 Wins: " + playerOneWin);
        player2WinCounter.setText("Player 2 Wins: " + playerTwoWin);
    }
    private void resetBo5(){
        playerOneWin = 0;
        playerTwoWin = 0;
        updateWinCounterDisplay();
    }
    private void winCounter(int winningPlayer) {
        if(winningPlayer == 1){
            playerOneWin++;
        }
        else if(winningPlayer == 2){
            playerTwoWin++;
        }
        updateWinCounterDisplay();

        if (playerOneWin == 3)
        {
            title.setText("Player 1 win the series!");
            resetBo5();
        } else if (playerTwoWin == 3) {
            title.setText("Player 2 win the series!");
            resetBo5();
        }
    }
}
