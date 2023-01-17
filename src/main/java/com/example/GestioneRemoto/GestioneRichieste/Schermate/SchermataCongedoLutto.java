package com.example.GestioneRemoto.GestioneRichieste.Schermate;

import com.example.GestioneRemoto.FileDiSistema.EntityUtente;
import com.example.GestioneRemoto.GestioneRichieste.Control.ControlGestioneRichieste;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.DatePicker;

import java.time.LocalDate;

public class SchermataCongedoLutto {
    @FXML
    DatePicker dataIn;
    @FXML
            DatePicker dataFi;
    ControlGestioneRichieste controlGestioneRichieste;
public SchermataCongedoLutto(ControlGestioneRichieste controlGestioneRichieste){
    this.controlGestioneRichieste= controlGestioneRichieste;
}
public void clickInvia(ActionEvent e ){
    int matricola= EntityUtente.getMatricola();
    LocalDate dataInizio= dataIn.getValue();
    LocalDate dataFine= dataFi.getValue();

    controlGestioneRichieste.clickInviaLutto(matricola, dataInizio, dataFine);
}
}
