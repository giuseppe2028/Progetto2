package com.example.GestioneRemoto.GestioneProfilo.Schermate;


import com.example.GestioneRemoto.GestioneProfilo.Control.ControlVisualizzaProfilo;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.TextField;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class SchermataModificaProfilo {
    @FXML
    TextField recapitoField;
    @FXML
    TextField indirizzoField;
    @FXML
    TextField ibanField;
    @FXML
    TextField mailField;


    private ControlVisualizzaProfilo controlVisualizzaProfilo;
    public SchermataModificaProfilo(ControlVisualizzaProfilo controlVisualizzaProfilo) {
        this.controlVisualizzaProfilo=controlVisualizzaProfilo;
    }
    public void clickModifica(ActionEvent e){
        controlVisualizzaProfilo.clickModifica();
    }

    public void clickSalva(ActionEvent e) throws IOException {
        Double recapito=  Double.parseDouble(recapitoField.getText());
        String iban= ibanField.getText();
        String indi= indirizzoField.getText();
        String mail= mailField.getText();
        List<Object> datiModificati=new ArrayList<>();
        datiModificati.add(recapito);
        datiModificati.add(iban);
        datiModificati.add(indi);
        datiModificati.add(mail);
        controlVisualizzaProfilo.compila(datiModificati);
        controlVisualizzaProfilo.clickSalva(datiModificati);
    }



}