package com.example.GestioneRemoto.GestioneRichieste.Control;

import com.example.GestioneRemoto.FileDiSistema.Util;
import com.example.GestioneRemoto.GestioneRichieste.Schermate.SchermataRichiesteRicevute;
import com.example.GestioneRemoto.Start;
import javafx.stage.Stage;

public class ControlRichiesteRicevute {
    private Stage stage = Start.mainStage;

    public void clickRichiesteRicevute() {
        Util.setSpecificScene("/com/example/GestioneRemoto/GestioneRichieste/FXML/SchermataRichiesteRicevute.fxml", stage, c-> new SchermataRichiesteRicevute(this));
    }
}
