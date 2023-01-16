package com.example.progetto2.Schermate;

import com.example.progetto2.FileDiSistema.Util;
import com.example.progetto2.Start;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.stage.Stage;

public class PopupErrore {
    Stage stage = Start.mainStage;
    @FXML
    Label errore;
    @FXML
    Label messaggioErrore;
    String message;

    //public void area(){ errore.setText("Errore");}

    //public void rettangolo(){messaggioErrore.setText("");}
    public PopupErrore(String message){
        this.message = message;
        //Util.setScene("/com/example/progetto2/GestioneTimbraturaInLoco/FXML/PopUpErrore.fxml",stage, c->new PopupErrore("Dati inseriti non corretti"));
    }

    @FXML
    public void initialize(){
        messaggioErrore.setText("Dati inseriti non corretti");

    }

    @FXML
    public void clickOK(){

    }
}
