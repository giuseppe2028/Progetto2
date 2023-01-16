package com.example.progetto2;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Label;

import java.io.IOException;

public class SceneController {

    @FXML
    Label nome;
    Label cognome;
    Label matricola;

    public void timbra(ActionEvent event) throws IOException {
        nome.setText("Nome");
        cognome.setText("Cognome");
        matricola.setText("Matricola");
    }
}
