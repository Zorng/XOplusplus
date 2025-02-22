package application.controllers;


import application.Main;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.RadioButton;
import javafx.stage.Stage;
import java.io.IOException;

public class Gameoptionscene {
    private Stage stage;
    private Scene scene;
    @FXML
    private Button Start;
    private Button Back;
    @FXML
    private RadioButton radiobutton1, radiobutton2, radiobutton3, radiobutton4, radiobutton5, radiobutton6;
    @FXML
    private void Backbutton(ActionEvent event) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(Main.class.getResource("resources/views/demo.fxml"));
        stage = (Stage)((Node)event.getSource()).getScene().getWindow();
        scene = new Scene(fxmlLoader.load());
        stage.setScene(scene);
        stage.show();
    }
    @FXML
    public void startbuttoncondition(ActionEvent event) throws IOException {
        if (
                (radiobutton1.isSelected() || radiobutton2.isSelected() || radiobutton3.isSelected()) && // One mode must be selected
                        (radiobutton4.isSelected() || radiobutton5.isSelected()) && // One win condition must be selected
                        (!radiobutton6.isSelected() || radiobutton6.isSelected()) // Rapid is optional (either selected or not)
        ) {
            Switchscene(event, "resources/views/gamestage.fxml");
        }
    }

    private void Switchscene(ActionEvent event, String fxmlfile) throws IOException {
        try{
            FXMLLoader fxmlLoader = new FXMLLoader(Main.class.getResource(fxmlfile));
            Stage stage = (Stage)((Node)event.getSource()).getScene().getWindow();
            Scene scene = new Scene(fxmlLoader.load());
            stage.setScene(scene);
            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
            System.out.println("can not load fxml file");
        }
    }

}
