package com.example.GestioneRemoto.GestioneRichieste.Schermate;

import com.example.GestioneRemoto.FileDiSistema.EntityUtente;
import com.example.GestioneRemoto.FileDiSistema.Util;
import com.example.GestioneRemoto.GestioneRichieste.Control.ControlGestioneRichieste;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.DatePicker;

import java.time.LocalDate;

public class SchermataCongedoParentale {
    @FXML
    DatePicker dataI;
    @FXML
    DatePicker dataF;
    ControlGestioneRichieste controlGestioneRichieste;
    public SchermataCongedoParentale(ControlGestioneRichieste controlGestioneRichieste){
        this.controlGestioneRichieste=controlGestioneRichieste;
    }
    public void clickIndietro(ActionEvent e)
    {
        Util.ritorno("/com/example/GestioneRemoto/GestioneRichieste/FXML/SchermataGestioneRichieste.fxml");
    }
    /*
    public void clickInvia(ActionEvent e ){

        LocalDate dataInizio= dataI.getValue();
        LocalDate dataFine= dataF.getValue();
        controlGestioneRichieste.clickInviaParentale(dataInizio, dataFine);
    }*/
}
