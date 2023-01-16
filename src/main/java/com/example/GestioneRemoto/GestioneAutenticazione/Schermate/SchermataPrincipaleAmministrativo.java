package com.example.GestioneRemoto.GestioneAutenticazione.Schermate;

import com.example.GestioneRemoto.GestioneAutenticazione.Control.ControlLogin;

import com.example.GestioneRemoto.GestioneProfilo.Control.ControlVisualizzaProfilo;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;

import java.util.List;

public class SchermataPrincipaleAmministrativo extends SchermataPrincipaleImpiegato {

    public SchermataPrincipaleAmministrativo(ControlLogin controlLogin, List<Object> datiProfilo){
        super(controlLogin,datiProfilo);
    }
    public void clickGestioneDipendenti(){

    }
    public void clickTimbraturaDipendenti(){

    }
    public void clickVisualizzaCalendario(){

    }
    @FXML
    public void clickVisualizzaProfilo(ActionEvent e){
        ControlVisualizzaProfilo controlVisualizzaProfilo=new ControlVisualizzaProfilo();
        controlVisualizzaProfilo.clickVisualizzaProfilo();


    }

}
