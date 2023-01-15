package com.example.GestioneRemoto.GestioneAutenticazione.Schermate;

import com.example.GestioneRemoto.GestioneAutenticazione.Control.ControlLogin;
import com.example.GestioneRemoto.FileDiSistema.DatePicker;
import com.example.GestioneRemoto.Start;
import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.MenuButton;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Timer;
import java.util.TimerTask;


//TODO implementare la classe schermata Principale Dipendente
public class SchermataPrincipaleDipendente implements Runnable {

    List<Object> datiProfilo;
    @FXML
    Label nomeCognome;
    @FXML
    Label matricola;
    @FXML
    Label ruolo;

    ControlLogin controlLogin;

    public SchermataPrincipaleDipendente(ControlLogin controlLogin, List<Object> datiProfilo){

        this.datiProfilo = datiProfilo;
        this.controlLogin = controlLogin;

        //setto le label e le imageview:

       /* String stringNomeCognome;
        try {
            stringNomeCognome = rs.getString("nome") +" " + rs.getString("cognome");
            nomeCognome.setText(stringNomeCognome);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }*/
/*
        try {
            stringNomeCognome = rs.getString("nome") +" " + rs.getString("cognome");
            String stringMatricola = rs.getString("matricola");
            String stringRuolo = rs.getString("ruolo");
            nomeCognome.setText(stringNomeCognome);
            matricola.setText(stringMatricola);
            ruolo.setText(stringRuolo);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
 */

    }

    String data;
    @FXML
    ImageView Iconlogout;
    @FXML
    ImageView IconNotification;
    @FXML
    Label dataCorrente;

    @FXML
    private ImageView ImmagineProfilo;

    @FXML
    private AnchorPane bottone;

    @FXML
    private Button notifiche;

    @FXML
    private Button gestioneProfilo;

    @FXML
    private Button timbratura;
    @FXML
    private ImageView IconGestioneProfilo;
    @FXML
    private ImageView Iconatimbratura;
    @FXML
    private Button logout;

    @FXML
    private AnchorPane rettangoloUP;

    @FXML
    private AnchorPane sfondoDropDownButton;
    @FXML
    MenuButton menuButtonGestioneProfilo = new MenuButton();
    @FXML
    MenuButton menuButtonGestioneTimbratura = new MenuButton();
    Stage stage = Start.mainStage;

    private  Timer timer = new Timer();

    @FXML
    public void initialize(){

        String stringNomeCognome = (String) datiProfilo.get(1) + (String) datiProfilo.get(2);
            nomeCognome.setText(stringNomeCognome);
            String stringMatricola = (String) datiProfilo.get(0);
            String stringRuolo = (String) datiProfilo.get(7);
            nomeCognome.setText(stringNomeCognome);
            matricola.setText(stringMatricola);
            ruolo.setText(stringRuolo);


    }
    @Override

public void run() {
      int x = 0;
    timer.scheduleAtFixedRate(new TimerTask() {
        @Override
        public void run() {

            String oraCorrente;
            int secondo = DatePicker.getSecond();
            int minuto = DatePicker.getMinute();
            int ora = DatePicker.getHour();
            int giorno = DatePicker.getDay();
            int mese = DatePicker.getMonth();
            int anno = DatePicker.getYear();
            String input = giorno+"-"+mese+"-"+anno+"T"+ora + ":" + minuto +":" + secondo;
            SimpleDateFormat inputFormat = new SimpleDateFormat("dd-MM-yyyy'T'HH:mm:ss");
            try {
                Date date = inputFormat.parse(input);
                DateFormat outputFormat = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");
                oraCorrente = outputFormat.format(date);
            } catch (ParseException e) {
                throw new RuntimeException(e);
            }
            Platform.runLater(()->{

                dataCorrente.setText(oraCorrente);

            });
        }
    },0,1000);

}


    @FXML
    public void clickGestioneProfilo(ActionEvent e){

    }@FXML
    public void clickGestioneTimbratura(){

    }
    @FXML
    public void clickDropDownButton(ActionEvent e){

    }
    @FXML
    public void clickvisualizzaProfilo(ActionEvent e){

    }
    @FXML
    public void clickLogout(ActionEvent e){
        controlLogin.clickLogout(e);
    }
    @FXML
    public void clickVisualizzaCalendario(ActionEvent e){

    }
    @FXML
    public void clickGestioneRichieste(ActionEvent e){

    }
    @FXML
    public void clickTimbraturaRemotoEntrata(ActionEvent e){

    }


}
