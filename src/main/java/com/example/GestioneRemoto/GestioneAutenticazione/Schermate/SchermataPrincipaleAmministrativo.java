package com.example.GestioneRemoto.GestioneAutenticazione.Schermate;

import com.example.GestioneRemoto.GestioneAutenticazione.Control.ControlLogin;
import com.example.GestioneRemoto.GestioneTurni.Control.ControlVisualizzaPropostaTurni;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;

import java.sql.ResultSet;
import java.util.List;

public class SchermataPrincipaleAmministrativo extends SchermataPrincipaleDipendente{
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
public void clickTurnazioneTrimestrale(ActionEvent e){
    ControlVisualizzaPropostaTurni controlVisualizzaPropostaTurni = new ControlVisualizzaPropostaTurni();
}
}
