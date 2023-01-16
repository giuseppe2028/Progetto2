package com.example.GestioneRemoto.GestioneAutenticazione.Schermate;

import com.example.GestioneRemoto.GestioneAutenticazione.Control.ControlLogin;

import com.example.GestioneRemoto.GestioneProfilo.Control.ControlVisualizzaProfilo;
import com.example.GestioneRemoto.GestioneProfilo.Control.ControlVisualizzaStipendio;
import com.example.GestioneRemoto.GestioneRichieste.Control.ControlGestioneRichieste;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;

import java.util.List;

public class SchermataPrincipaleAmministrativo extends SchermataPrincipaleImpiegato {

    public SchermataPrincipaleAmministrativo(ControlLogin controlLogin, List<Object> datiProfilo){
        super(controlLogin,datiProfilo);
    }
    @FXML
    public void clickGestioneRichieste(ActionEvent e){
        ControlGestioneRichieste controlGestioneRichieste= new ControlGestioneRichieste();
        controlGestioneRichieste.clickGestioneRichieste();
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
    @FXML
    public void clickVisualizzaStipendio(ActionEvent e){
        ControlVisualizzaStipendio controlVisualizzaStipendio= new ControlVisualizzaStipendio();
        controlVisualizzaStipendio.clickVisualizzaStipendio();
    }

}
