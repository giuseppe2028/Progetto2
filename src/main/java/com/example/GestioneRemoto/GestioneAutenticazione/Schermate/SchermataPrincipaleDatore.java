package com.example.GestioneRemoto.GestioneAutenticazione.Schermate;

import com.example.GestioneRemoto.GestioneAutenticazione.Control.ControlLogin;
import com.example.GestioneRemoto.FileDiSistema.DatePicker;
import com.example.GestioneRemoto.GestioneProfilo.Control.ControlVisualizzaProfilo;
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

import java.sql.SQLException;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;

public class SchermataPrincipaleDatore implements Runnable{

    List<Object> lista;

    ControlLogin controlLogin;
    public SchermataPrincipaleDatore(ControlLogin controlLogin, ArrayList<Object> lista){
        this.controlLogin = controlLogin;
        this.lista = lista;


    }
    @FXML
    Label nomeCognome;
    @FXML
    Label matricola;
    @FXML
    Label ruolo;
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

    private Timer timer = new Timer();



    @FXML
    public void initialize(){
        String stringNomeCognome =lista.get(1).toString() +" " +  lista.get(2).toString();
        nomeCognome.setText(stringNomeCognome);
        String stringMatricola =  lista.get(0).toString();
        String stringRuolo = lista.get(6).toString();
        nomeCognome.setText(stringNomeCognome);
        matricola.setText(stringMatricola);
        ruolo.setText(stringRuolo);

    }


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
    public void clickLogout(ActionEvent e){
        controlLogin.clickLogout(e);
    }

    @FXML
    public void clickVisualizzaProfilo(ActionEvent e){
        System.out.println("cccc");
        ControlVisualizzaProfilo controlVisualizzaProfilo1=new ControlVisualizzaProfilo();
        controlVisualizzaProfilo1.clickVisualizzaProfilo();

    }
    @FXML
    public void clickGestioneFestivitaFerie(){

    }
    @FXML
    public void clickCalendarioComplessivo(){

    }
    @FXML
    public void clickGestioneDipendenti(){

    }
}
