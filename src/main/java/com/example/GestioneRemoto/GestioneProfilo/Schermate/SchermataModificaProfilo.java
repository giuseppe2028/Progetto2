package com.example.GestioneRemoto.GestioneProfilo.Schermate;


import com.example.GestioneRemoto.GestioneProfilo.Control.ControlVisualizzaProfilo;
import com.example.GestioneRemoto.Start;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;

import javafx.scene.control.Button;
import javafx.scene.control.TextField;

import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import javafx.stage.Window;

import java.io.File;
import java.io.IOException;

import java.util.ArrayList;
import java.util.List;

import static jdk.jfr.consumer.EventStream.openFile;


public class SchermataModificaProfilo {
    @FXML
    TextField recapitoField;
    @FXML
    TextField indirizzoField;
    @FXML
    TextField ibanField;
    @FXML
    TextField mailField;
@FXML
Button carica;
@FXML
    ImageView imageView;

    private ControlVisualizzaProfilo controlVisualizzaProfilo;

    public SchermataModificaProfilo(ControlVisualizzaProfilo controlVisualizzaProfilo) {
        this.controlVisualizzaProfilo=controlVisualizzaProfilo;
    }
    public void clickModifica(ActionEvent e){
        controlVisualizzaProfilo.clickModifica();
    }

    public void clickSalva(ActionEvent e) throws IOException {
        Double recapito=  Double.parseDouble(recapitoField.getText());
        String iban= ibanField.getText();
        String indi= indirizzoField.getText();
        String mail= mailField.getText();
        List<Object> datiModificati=new ArrayList<>();
        datiModificati.add(recapito);
        datiModificati.add(iban);
        datiModificati.add(indi);
        datiModificati.add(mail);
        controlVisualizzaProfilo.compila(datiModificati);
        controlVisualizzaProfilo.clickSalva(datiModificati);
    }
    @FXML
    public void clickCarica(ActionEvent event) {
        carica.setOnAction(
                new EventHandler<ActionEvent>() {
                    @Override
                    public void handle(final ActionEvent e) {
                        Stage stage= Start.mainStage;
                        FileChooser fileChooser =new FileChooser();
                        File file = fileChooser.showOpenDialog(stage);
                        if (file != null) {
                            try {
                                openFile(file.toPath());
                                Image image = new Image(file.toURI().toString());
                                imageView.setImage(image);

                            } catch (IOException ex) {
                                throw new RuntimeException(ex);
                            }
                        }
                    }
                });
     /*   FileChooser fileChooser = new FileChooser();
        fileChooser.setTitle("Scegli un'immagine");
        fileChooser.getExtensionFilters().addAll(
                new FileChooser.ExtensionFilter("Immagini", "*.png", "*.jpg", "*.jpeg"));
        File selectedFile = fileChooser.showOpenDialog(carica.getScene().getWindow());
        if (selectedFile != null) {
            Image image = new Image(selectedFile.toURI().toString());
            imageView.setImage(image);*/
        }

    }






