package com.example.progetto2.Schermate;

import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;
import java.time.LocalDate;
import java.time.LocalTime;

public class SchermataTimbraturaEntrata {
    private LocalTime time;
    private LocalDate date;
    public SchermataTimbraturaEntrata (LocalTime time, LocalDate date){
        this.time = time;
        this.date = date;
    }

    public void compila(){

    }

    private Stage stage;
    private Scene scene;
    private Parent root;


    public void clickTimbra(ActionEvent event) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/com/example/progetto2/Gestione Timbratura In Loco/FXML/PopUpInformazione.fxml"));
        root = fxmlLoader.load();
        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.show();




    }


}
