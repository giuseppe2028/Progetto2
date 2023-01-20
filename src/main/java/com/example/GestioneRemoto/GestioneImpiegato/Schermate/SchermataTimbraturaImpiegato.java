package com.example.GestioneRemoto.GestioneImpiegato.Schermate;

import com.example.GestioneRemoto.GestioneImpiegato.Control.ControlGestioneImpiegati;
import javafx.fxml.FXML;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.DatePicker;

import java.time.LocalDate;
import java.time.LocalTime;

public class SchermataTimbraturaImpiegato {
    @FXML
    private ChoiceBox<String> ore;
    @FXML
    private ChoiceBox<String> minuti;
    @FXML
    private DatePicker calendario;
    ControlGestioneImpiegati controlGestioneImpiegati;
    public SchermataTimbraturaImpiegato(ControlGestioneImpiegati controlGestioneImpiegati){
        this.controlGestioneImpiegati=controlGestioneImpiegati;
    }

    @FXML
    public void initialize() {
for(int i = 0; i<60; i++){
    if(i<10){
        minuti.getItems().add(0+""+i);
    }else{
        minuti.getItems().add(String.valueOf(i));
    }
}
        for(int i = 0; i<24; i++){
            if(i<10){
                ore.getItems().add(0+""+i);
            }else{
                ore.getItems().add(String.valueOf(i));
            }
        }

    }
    public void compila(){
        //prendo la data
        LocalDate dataArrivo = calendario.getValue();
        String oreArrivo = ore.getValue();
        String minutiArrivo = minuti.getValue();
        LocalTime oraArrivo = LocalTime.of(Integer.parseInt(oreArrivo),Integer.parseInt(minutiArrivo),00);
        
        //prendo l'ora
    }


}
