package com.example.progetto2.FileDiSistema;

import javafx.scene.control.Alert;

public class ErrorMex {

    Alert messaggio = new Alert(Alert.AlertType.ERROR);
    public ErrorMex(String message){
        messaggio.setContentText(message);
        messaggio.show();
    }

}
