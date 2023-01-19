package com.example.GestioneRemoto.GestioneImpiegato.Control;

import com.example.GestioneRemoto.Contenitori.Periodi;
import com.example.GestioneRemoto.FileDiSistema.Daemon;
import com.example.GestioneRemoto.FileDiSistema.Util;
import com.example.GestioneRemoto.GestioneImpiegato.Schermate.SchermataFestivitaFerie;
import com.example.GestioneRemoto.Start;
import javafx.stage.Stage;

import java.util.List;

public class ControlFestivitaFerie {
    private List<Periodi> periodi;
    Stage stage = Start.mainStage;
    public ControlFestivitaFerie(){
        periodi = Daemon.getPeriodi();

        Util.setSpecificScene("/com/example/GestioneRemoto/GestioneImpiegato/FXML/SchermataFesitivitaFerie.fxml", stage,c->new SchermataFestivitaFerie(this,periodi));
    }
}
