package com.example.progetto2.Schermate;

import com.example.progetto2.Start;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.stage.Stage;

public class PopupInformazione {
    Stage stage = Start.mainStage;
    @FXML
    Label informazione;
    Label messaggioInformazione;
    public void area(){
        informazione.setText("Informazione");
    }

    public void area(){
        messaggioInformazione.setText("Timbratura effettuata con successo!");
    }



    public PopupInformazione(){
        Util.setScene("/com/example/progetto2/Gestione Timbratura In Loco/FXML/PopUpInformazione.fxml",stage,c->new PopupInformazione());
    }

    public void clickOK(){

    }
}
