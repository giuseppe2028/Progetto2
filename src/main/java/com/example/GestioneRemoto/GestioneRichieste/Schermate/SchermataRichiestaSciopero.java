package com.example.GestioneRemoto.GestioneRichieste.Schermate;

import com.example.GestioneRemoto.FileDiSistema.EntityUtente;
import com.example.GestioneRemoto.GestioneRichieste.Control.ControlGestioneRichieste;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;

import java.time.LocalDate;

public class SchermataRichiestaSciopero {
    ControlGestioneRichieste controlGestioneRichieste;
    @FXML
    DatePicker dataPicker;
    @FXML
    Label motivazioneL;
    @FXML
    Label svolgimentoL;
    public SchermataRichiestaSciopero(ControlGestioneRichieste controlGestioneRichieste){
        this.controlGestioneRichieste=controlGestioneRichieste;
    }
public void clickInvia(ActionEvent e){
       LocalDate data= dataPicker.getValue();
       String motivazione= motivazioneL.getText();
       String svolgimento= svolgimentoL.getText();
        int matricola= EntityUtente.getMatricola();
        controlGestioneRichieste.clickInviaSciopero(data, motivazione,svolgimento, matricola);


}
public void clickIndietro(ActionEvent e){

}
}
