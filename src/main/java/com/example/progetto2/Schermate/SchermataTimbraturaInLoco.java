package com.example.progetto2.Schermate;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

public class SchermataTimbraturaInLoco {
    private Stage stage;
    private Scene scene;
    private Parent root;

    @FXML
    public void clickTimbraEntrata(ActionEvent e ) throws IOException{
            FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/com/example/progetto2/Gestione Timbratura In Loco/FXML/SchermataTimbraturaEntrata.fxml"));
            root = fxmlLoader.load();
            Scene scene = new Scene(root);
            stage.setScene(scene);
            stage.show();
    }

    public void show(){

    }

    public void clickTimbraUscita(ActionEvent e){

    }

}
