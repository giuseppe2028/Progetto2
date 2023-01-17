package com.example.GestioneRemoto.GestioneRichieste.Schermate;

import com.example.GestioneRemoto.GestioneRichieste.Control.ControlGestioneRichieste;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import java.net.URL;
import java.time.LocalDate;
import java.util.ResourceBundle;

public class SchermataRichiestaFerie implements Initializable {
    private ControlGestioneRichieste controlGestioneRichieste;
@FXML
Label giorniProibitiLabel;
@FXML
DatePicker dataInizioDatePicker;
@FXML
DatePicker dataFineDatePicker;
public SchermataRichiestaFerie(ControlGestioneRichieste controlGestioneRichieste){
        this.controlGestioneRichieste=controlGestioneRichieste;

    }

    public void clickInvia(ActionEvent e){
        LocalDate dataInizio = dataInizioDatePicker.getValue();;
        LocalDate dataFine = dataFineDatePicker.getValue();

    controlGestioneRichieste.clickInviaFerie(dataInizio, dataFine);
    }
    public void clickIndietro(){
       // controlGestioneRichieste.clickIndietro();

    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
     //   String giorniPro= Daemon.getGiorniProibiti();
        //  giorniProibitiLabel.setText(giorniPro);
    }

}
