package com.example.progetto2.Control;

import com.example.progetto2.FileDiSistema.Daemon;
import com.example.progetto2.FileDiSistema.DatePicker;
import com.example.progetto2.FileDiSistema.Util;
import com.example.progetto2.Schermate.*;
import com.example.progetto2.Start;
import javafx.fxml.FXML;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.sql.Date;
import java.time.Duration;
import java.time.LocalDate;
import java.time.LocalTime;


public class ControlTimbratura {
    Stage stage = Start.mainStage;
    private LocalTime time;
    private LocalDate date;
    public ControlTimbratura(){

      time =   DatePicker.checkTime();

      date =  DatePicker.checkDate();

}

    private String nome;
    private String cognome;
    private int matricola;

@FXML
public void compila(String nome, String cognome, int matricola){
    this.nome=nome;
    this.cognome=cognome;
    this.matricola=matricola;
    System.out.println("Compilato : " + nome + cognome + matricola);
}

@FXML
public void clickTimbraEntrata(){
    Util.setScene("/com/example/progetto2/GestioneTimbraturaInLoco/FXML/SchermataTimbraturaEntrata.fxml", stage, c-> new SchermataTimbraturaEntrata(this, time, date));
}

@FXML
public void clickTimbraEntrata1() {
    Stage stage1 = new Stage();
    System.out.println("Click: " + nome + cognome + matricola);
    if (Daemon.verifyDati(nome,cognome,matricola)){
        System.out.printf("suca forte");
        LocalTime inizioTurno = Daemon.getInizioTurno(matricola, Date.valueOf(date).toLocalDate());
    Duration margin = Duration.ofMinutes(10);

    //da vedere come fare il .isEqual

        if (inizioTurno.isBefore(time.minus(margin)) || (time.compareTo(inizioTurno) == 0) && (time.isBefore(inizioTurno.plus(margin)))) {
            Daemon.insertTimbratura(date, time, matricola );
            //PopupInformazione popupInformazione= new PopupInformazione("Timbratura effettuata con successo!");
            Util.setScene("/com/example/progetto2/GestioneTimbraturaInLoco/FXML/PopUpInformazione.fxml",stage1,c->new PopupInformazione("CIAo"));

            }
        else if ((inizioTurno.plus(margin).isBefore(time)) || (time.compareTo(inizioTurno.plus(margin))==0) && time.minus(margin).isBefore(inizioTurno)) {
            PopupErrore popupErrore = new PopupErrore("Non è possibile effettuare la timbratura");
            Util.setScene("/com/example/progetto2/GestioneTimbraturaInLoco/FXML/PopUpErrore.fxml",stage, c->new PopupErrore("Dati inseriti non corretti"));

        }
    }
    else {
        PopupErrore popupErrore = new PopupErrore("Dati inseriti errati");
    }

    //qui dentro devo mettere pure tutti i metodi al Daemon


}
  @FXML
    public void clickTimbraUscita(){
        Util.setScene("/com/example/progetto2/GestioneTimbraturaInLoco/FXML/SchermataTimbraturaUscita.fxml", stage, c-> new SchermataTimbraturaUscita(this, time, date));
    }

@FXML
    public void clickTimbraUscita1(){
        Boolean esito= Daemon.verifyDati(nome, cognome, matricola);
        System.out.printf("cc"+ esito);

        if (Daemon.verifyDati(nome,cognome,matricola)){
            LocalTime fineTurno = Daemon.getFineTurno(matricola, Date.valueOf(date).toLocalDate());
            Duration margin = Duration.ofMinutes(10);

            if (fineTurno.isBefore(time.minus(margin)) || (time.compareTo(fineTurno) == 0) && (time.isBefore(fineTurno.plus(margin)))){
                Daemon.insertTimbratura(date, time, matricola);
                PopupInformazione popupInformazione = new PopupInformazione("Timbratura effettuata con successo!");
            }

            else if((fineTurno.plus(margin).isBefore(time)) || (time.compareTo(fineTurno.plus(margin)) == 0) && time.isBefore(fineTurno)){
                PopupErrore popupErrore = new PopupErrore("Impossibile effettuare la timbratura dell'orario di fine turno");
            }

        }

        else{
            PopupErrore popupErrore = new PopupErrore("Dati inseriti errati");
        }
    }

    @FXML
    public void clickIndietro(){
        Util.setScene("/com/example/progetto2/GestioneTimbraturaInLoco/FXML/SchermataTimbraturaInLoco.fxml", stage);
    }





}
