package com.example.GestioneRemoto.GestioneImpiegato.Control;

import com.example.GestioneRemoto.Contenitori.Impiegati;
import com.example.GestioneRemoto.FileDiSistema.Daemon;
import com.example.GestioneRemoto.FileDiSistema.Util;
import com.example.GestioneRemoto.GestioneImpiegato.Schermate.*;
import com.example.GestioneRemoto.Start;
import javafx.stage.Stage;

import java.sql.SQLException;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;

public class ControlGestioneImpiegati {
    Stage stage = Start.mainStage;
    public ControlGestioneImpiegati(){
        Util.setScene("/com/example/GestioneRemoto/GestioneImpiegato/FXML/SchermataGestioneImpiegati.fxml",stage,c->new SchermataGestioneImpiegati(this));
    }

    public void clickVisualizzaDati(Impiegati impiegato, int servizio) {
        List<Object> imp=  Daemon.getDatiProfilo(impiegato.getMatricola());
        imp.add(servizio);
        Util.setSpecificScene("/com/example/GestioneRemoto/GestioneImpiegato/FXML/SchermataVisualizzaImpiegato.fxml", stage,c->new SchermataVisualizzaImpiegato(this, imp) );
    }
/*
    public void clickVisualizzaStipendio() {
        //TODO query al db, fattelo tu
        Util.setSpecificScene("/com/example/GestioneRemoto/GestioneImpiegato/FXML/SchermataVisualizzaStipendio.fxml", stage, c-> new SchermataVisualizzaStipendio(this));
    }

    public void clickTimbraturaImpiegato() {
        Util.setSpecificScene("/com/example/GestioneRemoto/GestioneImpiegato/FXML/SchermataTimbraturaImpiegato.fxml", stage, c-> new SchermataTimbraturaImpiegato(this));
    }
*/
    public void clickRegistra() {
        Util.setScene("/com/example/GestioneRemoto/GestioneImpiegato/FXML/SchermataRegistrazioneImpiegato.fxml", stage, c-> new SchermataRegistrazioneImpiegato(this));

    }

    public void clickDisattiva() {
        Util.setScene("/com/example/GestioneRemoto/GestioneImpiegato/FXML/SchermataDisattivaImpiegato.fxml", stage, c-> new SchermataDisattivaImpiegato(this));
    }

    public void clickTimbraturaImpiegato(int matricola) {
        Util.setSpecificScene("com/example/GestioneRemoto/GestioneImpiegato/FXML/SchermataTimbraturaImpiegato.fxml", stage, c-> new SchermataTimbraturaImpiegato(this, matricola));
    }
    public void clickIndietro(String fxml){
        Util.ritorno(fxml);
    }

    public void clickConferma(LocalDate data, LocalTime orario, int matricola) throws SQLException {
        Boolean es=Daemon.controlloTimbr(data, matricola);
        if (es) {
            Boolean esito = Daemon.verifyTimbratura(data, orario, matricola);
            if (esito) {
                //todo popup errore
            } else {
                String tipoTurno= Daemon.getTurno(data, matricola);
                LocalDate dataTurno= Daemon.getDataTurno(data, matricola);
                Daemon.insertTimbratura(data, orario, matricola, tipoTurno, dataTurno);
                //todo popup informazione
            }
        }else {
            //todo popup errore
        }
    }
}