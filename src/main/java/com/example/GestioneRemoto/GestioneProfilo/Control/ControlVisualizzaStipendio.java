package com.example.GestioneRemoto.GestioneProfilo.Control;

import com.example.GestioneRemoto.FileDiSistema.Util;
import com.example.GestioneRemoto.GestioneProfilo.Schermate.SchermataVisualizzaStipendio;
import com.example.GestioneRemoto.Start;
import javafx.stage.Stage;

public class ControlVisualizzaStipendio {
    private Stage stage = Start.mainStage;
    public void clickVisualizzaStipendio() {
        SchermataVisualizzaStipendio schermataVisualizzaStipendio= Util.setSpecificScene("/com/example/GestioneRemoto/GestioneProfilo/FXML/SchermataVisualizzaStipendio.fxml", stage,c-> new SchermataVisualizzaStipendio(this) );
    }
}
