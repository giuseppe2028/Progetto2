package com.example.GestioneRemoto.FileDiSistema;

import javafx.fxml.FXML;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.stage.Stage;

public class PreviousSceneController {


    public static void goBack(Button backButton, Stage previousStage) {
        // Nascondi la finestra attuale
        backButton.getScene().getWindow().hide();

        // Mostra la finestra precedente
        // Assumendo che la finestra precedente sia stata salvata in una variabile chiamata previousStage
        previousStage.show();
    }
}