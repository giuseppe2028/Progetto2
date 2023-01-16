package com.example.progetto2;

import com.example.progetto2.Control.ControlTimbratura;
import com.example.progetto2.FileDiSistema.Daemon;
import com.example.progetto2.FileDiSistema.DatePicker;
import com.example.progetto2.FileDiSistema.Util;
import com.example.progetto2.Schermate.SchermataTimbraturaInLoco;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

import static javafx.application.Application.launch;

public class Start extends Application {
    public static Stage mainStage;



    @Override
    public void start(Stage stage) throws IOException {
        //new SchermataTimbraturaInLoco();
        DatePicker datePicker = new DatePicker();
        Thread orologio = new Thread(datePicker);
        orologio.start();
        mainStage = stage;
        /*Parent root;
        //FXMLLoader loader = new FXMLLoader();
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/com/example/progetto2/GestioneTimbraturaInLoco/FXML/SchermataTimbraturaInLoco.fxml"));
        //ControlTimbratura controlTimbratura = new ControlTimbratura();
        //fxmlLoader.setController(controlTimbratura);
        root = fxmlLoader.load();
        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.show();*/
        Util.setScene("/com/example/progetto2/GestioneTimbraturaInLoco/FXML/SchermataTimbraturaInLoco.fxml",stage,c->new SchermataTimbraturaInLoco());
        new Daemon();
    }

    public static void main(String[] args) {
        launch();}
}


//TODO Finire di sistemare il tutto aggiungendo i costruttori e levando i metodi show, (per dubbi guardare il codice dell'anno scorso)
//TODO in conseguenza al punto 1, aggiustare il fatto che la boundary non manda la mail, ma essa va passata alla control
//TODO aggiustare questo errore, in fase invio mail: WARNING: expected resource not found: /META-INF/javamail.default.address.map


