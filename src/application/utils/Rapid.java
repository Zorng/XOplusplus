package application.utils;

import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.util.Duration;

import java.util.ArrayList;

public class Rapid {
    public int timerX;
    public int timerO;
    public Timeline timer;

    public Rapid(int timerX, int timerO) {
        this.timerX = timerX;
        this.timerO = timerO;
    }
    public void startTimer(int inPlayerTurn, Label inTimeX, Label inTimeO, Label inTitle, ArrayList<Button> btns) {
        timer = new Timeline(new KeyFrame(Duration.seconds(1), event -> {
            if(inPlayerTurn % 2 == 0){
                timerX--;
                inTimeX.setText(String.valueOf(timerX)+"s");
            } else {
                timerO--;
                inTimeO.setText(String.valueOf(timerO)+"s");
            }
            if(timerX <= 0){
                inTitle.setText("O won");
                timer.stop();
                disableAllButtons(btns);
            } else if(timerO <= 0){
                inTitle.setText("X won");
                timer.stop();
                disableAllButtons(btns);
            }
        }));
        // set timer to run indefinitely, without stop auto
        timer.setCycleCount(Timeline.INDEFINITE);
        // start time to begin cd
        timer.play();
    }
    public void disableAllButtons(ArrayList<Button> buttons) {
        buttons.forEach(button -> button.setDisable(true));
    }
}
