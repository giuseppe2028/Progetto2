package com.example.GestioneRemoto.GestioneAutenticazione.Schermate;

import com.example.GestioneRemoto.GestioneAutenticazione.Control.ControlLogin;
import com.example.GestioneRemoto.Start;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Hyperlink;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

import java.io.IOException;

public class SchermataLogin {
    private Stage stage = Start.mainStage;
    //test
    @FXML
    private Button bottoneLogin;

    @FXML
    private TextField matricola;

    @FXML
    private TextField mail;


    @FXML
    private AnchorPane rettangolo;

    @FXML
    private AnchorPane sfondo;
    @FXML
    private PasswordField passwordField;
    private ControlLogin controlLogin;

    public SchermataLogin() {
        controlLogin = new ControlLogin();
    }

    @FXML
    Hyperlink passwordDimenticata = new Hyperlink();


    @FXML
    public void clickRecuperaPass(ActionEvent e) throws IOException {
        controlLogin.clickRecuperaPassword(e);
    }
//DIRE NELL'ODD CHE NON METTIAMO IL METODO INSERSCI CREDENZIALI PER NON PREMERE IL TASTO INVIO
    @FXML
    public void clickLogin(ActionEvent e) throws IOException {
       controlLogin.create(Integer.parseInt(matricola.getText()), passwordField.getText());
        controlLogin.clickLogin(e);
    }
    @FXML
    public void clickCalendario(ActionEvent e){
        // Util.setScene("/com/example/progettogaga/Calendario/Calendario.fxml", stage, c -> new Calendario());
    }
    @FXML
    public void show(){
        //TODO implementare show
    }
}