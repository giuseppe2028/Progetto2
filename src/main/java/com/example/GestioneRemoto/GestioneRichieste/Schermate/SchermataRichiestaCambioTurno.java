package com.example.GestioneRemoto.GestioneRichieste.Schermate;

import com.example.GestioneRemoto.FileDiSistema.Daemon;
import com.example.GestioneRemoto.GestioneRichieste.Control.ControlGestioneRichieste;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;

import java.time.LocalDate;
import java.util.List;

public class SchermataRichiestaCambioTurno {
    @FXML
    Label turnoOri;
    @FXML
    DatePicker dataOrigine;
    @FXML
    DatePicker dataDestinazione;
    @FXML
    ChoiceBox<String> turno;
int matricola;
    private final List<Object> turni;
    ControlGestioneRichieste controlGestioneRichieste;
public SchermataRichiestaCambioTurno(ControlGestioneRichieste controlGestioneRichieste, List<Object> turni, int matricola){
    this.controlGestioneRichieste=controlGestioneRichieste;
    this.turni=turni;
    this.matricola=matricola;
}

public LocalDate selezionaTurnoOrigine(){
    LocalDate turnoOrigine= dataOrigine.getValue();
    //restituisce il turno di quella data
    String turno= Daemon.getTurno(turnoOrigine, matricola);
    turnoOri.setText(turno);
    return turnoOrigine;
}

public LocalDate selezionaTurnoDestinazione(){
    LocalDate turnoDestinazione= dataDestinazione.getValue();
    return turnoDestinazione;
}
public String selezionaTurnoDesiderato(){
    //TODO devo settare il choice box per selezionare il turno "mattina" o "pomeriggio"
    String turnoDesiderato= turno.getValue();
    return turnoDesiderato;
}

public void clickConferma(ActionEvent e){
    LocalDate turnoOrigine= selezionaTurnoOrigine();
    LocalDate turnoDestinazione= selezionaTurnoDestinazione();
    String turnoDesiderato= selezionaTurnoDesiderato();


    controlGestioneRichieste.clickConferma(turnoOrigine, turnoDestinazione, turnoDesiderato, matricola);

}


}
