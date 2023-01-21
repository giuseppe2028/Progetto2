package com.example.GestioneRemoto.GestioneImpiegato.Control;

import com.example.GestioneRemoto.Contenitori.Richiesta;
import com.example.GestioneRemoto.FileDiSistema.Daemon;
import com.example.GestioneRemoto.FileDiSistema.EntityUtente;
import com.example.GestioneRemoto.FileDiSistema.Util;
import com.example.GestioneRemoto.GestioneImpiegato.Schermate.SchermataRichiesteSciopero;
import com.example.GestioneRemoto.Start;
import javafx.stage.Stage;

import java.util.ArrayList;
import java.util.List;

public class ControlRichiesteSciopero {
    private Stage stage = Start.mainStage;
    int matricola= EntityUtente.getMatricola();

    public void clickRichiestaSciopero() {
        List<Richiesta> richiesteSciopero= new ArrayList<>();
        richiesteSciopero= Daemon.getRichiesteSciopero(matricola);
        Util.setSpecificScene("/com/example/GestioneRemoto/GestioneImpiegato/FXML/SchermataRichiesteSciopero.fxml", stage, c-> new SchermataRichiesteSciopero(this));
    }
}
