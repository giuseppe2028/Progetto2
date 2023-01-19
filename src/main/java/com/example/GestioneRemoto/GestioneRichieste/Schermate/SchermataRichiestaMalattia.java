package com.example.GestioneRemoto.GestioneRichieste.Schermate;

import com.example.GestioneRemoto.GestioneRichieste.Control.ControlGestioneRichieste;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;

import java.time.LocalDate;
import java.util.Date;

public class SchermataRichiestaMalattia {
    @FXML
    DatePicker dataIn;
    @FXML
            DatePicker dataFi;
    @FXML
    Label motivazioneL;
    ControlGestioneRichieste controlGestioneRichieste;
    public SchermataRichiestaMalattia(ControlGestioneRichieste controlGestioneRichieste){
        this.controlGestioneRichieste=controlGestioneRichieste;
    }
    public void clickIndietro(ActionEvent e){
        controlGestioneRichieste.clickIndietro();
    }
    /*
public void clickInvia(ActionEvent e ){
    LocalDate dataInizio= dataIn.getValue();
    LocalDate dataFine= dataFi.getValue();
    String motivazione= motivazioneL.getText();
        controlGestioneRichieste.clickInviaMalattia(dataInizio, dataFine, motivazione);
}*/
}
