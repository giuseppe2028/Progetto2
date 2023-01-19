package com.example.GestioneRemoto.GestioneRichieste.Schermate;

import com.example.GestioneRemoto.GestioneRichieste.Control.ControlGestioneRichieste;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.DatePicker;

import java.math.BigInteger;
import java.net.URL;
import java.time.LocalDate;
import java.util.ResourceBundle;

public class SchermataRichiestaPermesso implements Initializable {
    private ControlGestioneRichieste controlGestioneRichieste;
    @FXML
    ChoiceBox<String> oraInizio;
    @FXML
    ChoiceBox<String> minutoInizio;
    @FXML
    ChoiceBox<String> oraFine;
    @FXML
    ChoiceBox<String> minutoFine;
    @FXML
    DatePicker dataPicker;
    private String[] oraIn= {"08", "09", "10", "11", "12", "13","14", "15", "16", "17", "18", "19", "20"};


    private String[] oraFin= {"08", "09", "10", "11", "12", "13","14", "15", "16", "17", "18", "19", "20"};
    private String[] minutoIn= {"00","05", "10", "15", "20", "25", "30", "35", "40", "45", "50", "55"};
    private String[] minutoFin= {"00","05", "10", "15", "20", "25", "30", "35", "40", "45", "50", "55"};
    public SchermataRichiestaPermesso(ControlGestioneRichieste controlGestioneRichieste){
        this.controlGestioneRichieste=controlGestioneRichieste;


    }
    public void initialize(URL url, ResourceBundle resourceBundle) {
        oraInizio.getItems().addAll(oraIn);
        minutoInizio.getItems().addAll(minutoIn);
        oraInizio.setOnAction(e -> updateEndHourChoiceBox());
        updateEndHourChoiceBox();
    }

    private void updateEndHourChoiceBox() {
        String startHour = oraInizio.getValue();
        if (startHour == null) {
            return;
        }
        int startHourInt = Integer.parseInt(startHour);
        ObservableList<String> endHours = FXCollections.observableArrayList();
        for (int i = startHourInt + 1; i <= oraFin.length; i++) {
            String hour = String.valueOf(i);
            endHours.add(hour);
        }
        oraFine.setItems(endHours);
        if (!endHours.isEmpty()) {
            oraFine.setValue(endHours.get(0));
        }
    }

/*
    public void clickInvia(ActionEvent e){
LocalDate data= dataPicker.getValue();
String oraIni=  oraInizio.getValue();
String minuIni= minutoInizio.getValue();
String oraFin= oraFine.getValue();
String minutFin= minutoFine.getValue();

        controlGestioneRichieste.clickInviaPermesso(data, oraIni, minuIni, oraFin, minutFin);
    }
    public void clickIndietro(){
        // controlGestioneRichieste.clickIndietro();

    }*/
}
