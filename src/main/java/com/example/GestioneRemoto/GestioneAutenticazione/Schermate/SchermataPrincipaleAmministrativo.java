package com.example.GestioneRemoto.GestioneAutenticazione.Schermate;

import com.example.GestioneRemoto.FileDiSistema.Daemon;
import com.example.GestioneRemoto.FileDiSistema.EntityUtente;
import com.example.GestioneRemoto.GestioneAutenticazione.Control.ControlLogin;

import com.example.GestioneRemoto.GestioneImpiegato.Control.ControlGestioneImpiegati;
import com.example.GestioneRemoto.GestioneProfilo.Control.ControlVisualizzaProfilo;
import com.example.GestioneRemoto.GestioneProfilo.Control.ControlVisualizzaStipendio;
import com.example.GestioneRemoto.GestioneRichieste.Control.ControlGestioneRichieste;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

import java.io.IOException;
import java.io.InputStream;
import java.util.List;

public class SchermataPrincipaleAmministrativo extends SchermataPrincipaleImpiegato {
@FXML
    ImageView ImmagineProfilo;
    public SchermataPrincipaleAmministrativo(ControlLogin controlLogin, List<Object> datiProfilo){
        super(controlLogin,datiProfilo);
    }

    @Override
    public void initialize() throws IOException {
        String stringNomeCognome =  datiProfilo.get(1).toString() +" "+ datiProfilo.get(2).toString();
        nomeCognome.setText(stringNomeCognome);
        String stringMatricola = datiProfilo.get(0).toString();
        String stringRuolo =datiProfilo.get(6).toString();
        nomeCognome.setText(stringNomeCognome);
        matricola.setText(stringMatricola);
        ruolo.setText(stringRuolo);
        Image im;
        InputStream is= Daemon.getFotoProfilo(EntityUtente.getMatricola());
        im = new Image(is);
        ImmagineProfilo.setImage(im);
        is.close();

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
    @FXML
    public void clickGestioneImpiegati(ActionEvent e){
        ControlGestioneImpiegati controlGestioneImpiegati = new ControlGestioneImpiegati();

    }


}
