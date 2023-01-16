package com.example.progetto2.Schermate;

import com.example.progetto2.Control.ControlTimbratura;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.io.IOException;
import java.time.LocalDate;
import java.time.LocalTime;

public class SchermataTimbraturaUscita {

    @FXML
    TextField nomeField;

    @FXML
    TextField cognomeField;

    @FXML
    TextField matricolaField;
    private LocalTime time;
    private LocalDate date;
    ControlTimbratura controlTimbratura;
    public SchermataTimbraturaUscita(ControlTimbratura controlTimbratura, LocalTime time, LocalDate date){
        this.controlTimbratura = controlTimbratura;
        this.time = time;
        this.date = date;

    }
    @FXML
    public void compila(){
        String nome= nomeField.getText();
        String cognome = cognomeField.getText();
        int matricola = Integer.parseInt(matricolaField.getText());
        controlTimbratura.compila(nome,cognome, matricola );
    }


    @FXML
    public void clickTimbraUscita(ActionEvent event) throws IOException {
        controlTimbratura.clickTimbraUscita1();

    }


    public void show(LocalTime time, LocalDate date){

    }
@FXML
    public void clickIndietro(ActionEvent event) throws IOException{
        controlTimbratura.clickIndietro();
    }



}
