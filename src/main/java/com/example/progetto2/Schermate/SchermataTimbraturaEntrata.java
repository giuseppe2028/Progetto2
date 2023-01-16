package com.example.progetto2.Schermate;

import com.example.progetto2.Control.ControlTimbratura;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;


import java.io.IOException;
import java.time.LocalDate;
import java.time.LocalTime;

public class SchermataTimbraturaEntrata {
    @FXML
    TextField nomeField;

    @FXML
    TextField cognomeField;

    @FXML
    TextField matricolaField;

    private LocalTime time;
    private LocalDate date;


ControlTimbratura controlTimbratura;
    public SchermataTimbraturaEntrata (ControlTimbratura controlTimbratura, LocalTime time, LocalDate date){

        this.controlTimbratura=controlTimbratura;
        this.time = time;
        this.date = date;
    }
    @FXML
    public void compila(){
        String nome= nomeField.getText();
       //System.out.println(nomeField.getText() + cognomeField.getText()+matricolaField.getText());
        String cognome = cognomeField.getText();
        int matricola = Integer.parseInt(matricolaField.getText());
        controlTimbratura.compila(nome,cognome, matricola );

    }


    @FXML
    public void clickTimbraEntrata(ActionEvent event) throws IOException {
        compila();
        controlTimbratura.clickTimbraEntrata1();


    }


    public void show(LocalTime time, LocalDate date){
    }

    @FXML
    public void clickIndietro(ActionEvent event) throws IOException{
        controlTimbratura.clickIndietro();
    }


}
