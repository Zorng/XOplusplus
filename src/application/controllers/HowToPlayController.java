package application.controllers;

import application.utils.Route;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;

import java.io.IOException;

public class HowToPlayController {
    @FXML
    public void backToMain(ActionEvent event) throws IOException {
        Route.get("menu", event);
    }
}
