package com.example.GestioneRemoto.GestioneProfilo.Schermate;

import com.example.GestioneRemoto.GestioneProfilo.Control.ControlVisualizzaProfilo;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

import java.io.ByteArrayInputStream;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;
import java.sql.Blob;
import java.sql.SQLException;
import java.util.ArrayList;
public class SchermataVisualizzaProfilo  {
    @FXML
    ImageView ImmagineProfilo;
    @FXML
    Label nomeLabel;
    @FXML
    Label cognomeLabel;
    @FXML
    Label matricolaLabel;
    @FXML
    Label dataNascitaLabel;
    @FXML
    Label mailLabel;

    @FXML
    Label cfLabel;
    @FXML
    Label recapitoLabel;
    @FXML
    Label indirizzoLabel;
    @FXML
    Label ruoloLabel;
    @FXML
    Label IbanLabel;
    @FXML
            Label mailPLabel;
    ControlVisualizzaProfilo controlVisualizzaProfilo;
ArrayList<Object> datiProfilo;
    public SchermataVisualizzaProfilo(ControlVisualizzaProfilo controlVisualizzaProfilo, ArrayList<Object> datiProfilo){
this.controlVisualizzaProfilo=controlVisualizzaProfilo;
this.datiProfilo=datiProfilo;

    }
    @FXML
    public void initialize() throws SQLException, FileNotFoundException {

        String matricola= datiProfilo.get(0).toString();
        matricolaLabel.setText(matricola);
        nomeLabel.setText(datiProfilo.get(1).toString());
        cognomeLabel.setText(datiProfilo.get(2).toString());
        cfLabel.setText(datiProfilo.get(3).toString());
        String data=datiProfilo.get(4).toString();
        dataNascitaLabel.setText(data);
       indirizzoLabel.setText(datiProfilo.get(5).toString());
        ruoloLabel.setText(datiProfilo.get(6).toString());
        mailLabel.setText(datiProfilo.get(7).toString());
        IbanLabel.setText(datiProfilo.get(8).toString());
        String recapito= datiProfilo.get(9).toString();
        recapitoLabel.setText(recapito);
       mailPLabel.setText(datiProfilo.get(10).toString());
     // String c= (String) datiProfilo.get(11);
      //ByteArrayInputStream clob= (ByteArrayInputStream) datiProfilo.get(11);
       // byte[] byteArr = clob.getBytes(1, (int) clob.length());
       // byte[] byteArr= clob.readAllBytes();
       // InputStream inputStream= new FileInputStream(c);
       // InputStream inputStream = new ByteArrayInputStream(byteArr);
       // ImmagineProfilo.setImage(new Image(inputStream));





    }

    public void clickModificaPassword(){
controlVisualizzaProfilo.clickModificaPassword();
    }
   /* public void clickIndietro(ActionEvent e){
controlVisualizzaProfilo.clickIndietro();
    }*/
    public void clickModifica(ActionEvent event) {
        controlVisualizzaProfilo.clickModifica();
    }

}
