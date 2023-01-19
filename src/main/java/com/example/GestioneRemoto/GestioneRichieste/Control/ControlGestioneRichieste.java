package com.example.GestioneRemoto.GestioneRichieste.Control;


import com.example.GestioneRemoto.Contenitori.Richiesta;
import com.example.GestioneRemoto.FileDiSistema.Daemon;
import com.example.GestioneRemoto.FileDiSistema.Util;
import com.example.GestioneRemoto.GestioneRichieste.Schermate.*;
import com.example.GestioneRemoto.Start;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.geometry.Insets;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.control.skin.TableRowSkin;
import javafx.scene.layout.Background;
import javafx.scene.layout.HBox;
import javafx.scene.paint.Color;
import javafx.stage.Stage;
import javafx.util.Callback;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;




public class ControlGestioneRichieste {
    ObservableList<Richiesta> richiesteList;
    @FXML
    private TableView<Richiesta> richiesteTableView;
    @FXML
    private TableColumn<Richiesta, Integer> idCol;
    @FXML
    private TableColumn<Richiesta, Date> dataiCol;
    @FXML
    private TableColumn<Richiesta, Date> datafCol;
    @FXML
    private TableColumn<Richiesta, String> tipoCol ;
    @FXML
    private TableColumn<Richiesta, String> statoCol ;
    @FXML
    private TableColumn<Richiesta, String> motCol;
    @FXML
    private TableColumn<Richiesta, String> allCol;
    @FXML
    private TableColumn<Richiesta, String> svolgCol;
    @FXML
    private TableColumn<Richiesta, String> oraInCol;
    @FXML
    private TableColumn<Richiesta, String> oraFCol;
    @FXML
    private TableColumn<Richiesta, String> matrCol;
    @FXML
    private TableColumn<Richiesta, String> turnoOCol;
    @FXML
    private TableColumn<Richiesta, String> turnoDCol;
    @FXML
    private TableColumn<Richiesta, String> dataTOCol;
    @FXML
    private TableColumn<Richiesta, String> dataTDCol;

    @FXML
    private TableColumn editCol;

    public void clickCongedoLutto() {
        Util.setScene("/com/example/GestioneRemoto/GestioneRichieste/FXML/SchermataCongedoLutto.fxml", stage, c->new SchermataCongedoLutto(this));
    }

    public void clickCongedoParentale() {
        Util.setScene("/com/example/GestioneRemoto/GestioneRichieste/FXML/SchermataCongedoParentale.fxml", stage, c->new SchermataCongedoParentale(this));
    }

    public void clickRichiestaPermesso() {
        Util.setScene("/com/example/GestioneRemoto/GestioneRichieste/FXML/SchermataRichiestaPermesso.fxml", stage, c-> new SchermataRichiestaPermesso(this));
    }


    public class CustomTableRowSkin<T> extends TableRowSkin<T> {
            public CustomTableRowSkin(TableRow<T> tableRow) {
                super(tableRow);
            }

        }
    private Stage stage = Start.mainStage;
    public void clickGestioneRichieste() {
        Util.setScene("/com/example/GestioneRemoto/GestioneRichieste/FXML/SchermataGestioneRichieste.fxml", stage, c-> new SchermataGestioneRichieste(this) );
    }


    /*
    public void clickInviaFerie(LocalDate dI, LocalDate dF){
       int matricola= EntityUtente.getMatricola();
        Period periodo = Period.between(dI, dF);
        int giorniInseriti = periodo.getDays();
        List<LocalDate> giorniProibiti= Daemon.getGiorniProibiti();
        for(LocalDate date: giorniProibiti){
             if (date.isAfter(dI) && date.isBefore(dF)) {
             Alert a= new Alert(Alert.AlertType.ERROR);
             a.setContentText("Il periodo selezionato corrisponde con i giorni proibiti");
             a.showAndWait();
    }
}

        int giorniFerie =Daemon.getGiorniFerie(matricola);
        if (giorniFerie>= giorniInseriti){
         Daemon.insertRichiestaFerie(matricola, dI, dF);
         Daemon.updateGiorniFerie(matricola, giorniInseriti);
         Alert a= new Alert(Alert.AlertType.INFORMATION);
         a.setContentText("Richiesta accettata");
         a.showAndWait();
        }else{
         Alert a= new Alert(Alert.AlertType.ERROR);
        a.setContentText("Non hai giorni sufficienti per effettuare la richiesta!");
        a.showAndWait();
    }

    }
*/
    public void clickRichiestaFerie(){
        List<LocalDate> giorniPro= Daemon.getGiorniProibiti();
        System.out.println( giorniPro.size());
        List<LocalDate> dateI= new ArrayList<>();
        List<LocalDate> dateF= new ArrayList<>();
        for(int i=0; i<giorniPro.size(); ++i) {
            if (i % 2 == 0) {

                dateI.add(giorniPro.get(i));
            }else{

                    dateF.add(giorniPro.get(i));

                }

            }



        Util.setSpecificScene("/com/example/GestioneRemoto/GestioneRichieste/FXML/SchermataRichiestaFerie.fxml", stage, c-> new SchermataRichiestaFerie(this, dateI, dateF));
    }
    /*
    public void clickRichiestaPermesso() {
      Util.setScene("/com/example/GestioneRemoto/GestioneRichieste/FXML/SchermataRichiestaPermesso.fxml", stage, c-> new SchermataRichiestaPermesso(this));
    }
   public void clickInviaPermesso(LocalDate data, String oraInizio, String minutiInizio, String oraFine, String minutiFine){
        int matricola= EntityUtente.getMatricola();
        //TODO aggiungere i controlli sulla data
        int orePermesso= Daemon.getOrePermesso(matricola);
        LocalDateTime inizio = LocalDateTime.of(data, LocalTime.of(Integer.parseInt(oraInizio), Integer.parseInt(minutiInizio)));
        LocalDateTime fine = LocalDateTime.of(data, LocalTime.of(Integer.parseInt(oraFine), Integer.parseInt(minutiFine)));
        Duration duration = Duration.between(inizio, fine);
        long minutiInseriti = duration.toMinutes();
        System.out.println("I minuti inseriti sono: " + minutiInseriti);
        if(orePermesso>= minutiInseriti){
            Alert a= new Alert(Alert.AlertType.INFORMATION);
            a.setContentText("Richiesta accettata");
            a.showAndWait();
        }else{
            Alert a= new Alert(Alert.AlertType.ERROR);
            a.setContentText("Non hai ore sufficienti per effettuare la richiesta!");
            a.showAndWait();
        }

    }
*/
    public void clickRichiestaSciopero(){
        Util.setScene("/com/example/GestioneRemoto/GestioneRichieste/FXML/SchermataRichiestaSciopero.fxml", stage, c-> new SchermataRichiestaSciopero(this));
    }/*
    public void clickInviaSciopero(LocalDate data, String motivazione, String svolgimento){
        int matricola= EntityUtente.getMatricola();
      //int matricolaDatore=  Daemon.getMatricolaDatore();
      //TODO implementare l'invio della mail al datore
        Alert a= new Alert(Alert.AlertType.INFORMATION);
        a.setContentText("Richiesta inoltrata");
        a.showAndWait();
        //TODO inserire la richiesta in sciopero DBMS




    }

    public void clickCongedoParentale(){
        Util.setScene("/com/example/GestioneRemoto/GestioneRichieste/FXML/SchermataCongedoParentale.fxml", stage, c-> new SchermataCongedoParentale(this));
    }

    public void clickInviaParentale(LocalDate dataInizio, LocalDate dataFine){
        int matricola= EntityUtente.getMatricola();
        //TODO inserire nel DBMS la richiesta ed il popup

    }
    public void clickCongedoLutto(){
        Util.setScene("/com/example/GestioneRemoto/GestioneRichieste/FXML/SchermataCongedoLutto.fxml", stage, c-> new SchermataCongedoLutto(this));
    }
    public void clickInviaLutto(LocalDate dataInizio, LocalDate dataFine) {
        int matricola=EntityUtente.getMatricola();
        //TODO inserire nel DBMS la richiesta ed il popup
    }
    */
    public void clickRichiestaMaternita(){
        Util.setScene("/com/example/GestioneRemoto/GestioneRichieste/FXML/SchermataRichiestaMaternita.fxml", stage, c-> new SchermataRichiestaMaternita(this));
    }
    /*
    public void clickInviaMaternita(LocalDate dataInizio, LocalDate dataFine){
        int matricola=EntityUtente.getMatricola();

    }*/
    public void clickRichiestaMalattia(){
        Util.setScene("/com/example/GestioneRemoto/GestioneRichieste/FXML/SchermataRichiestaMalattia.fxml", stage, c-> new SchermataRichiestaMalattia(this));
    }/*
    public void clickInviaMalattia(LocalDate dataInizio, LocalDate dataFine, String motivazione) {
        int matricola= EntityUtente.getMatricola();
        //TODO query al DBMS

    }

public void clickRichiestaCambio(){
        int matricola= EntityUtente.getMatricola();
        List<Object> turni= Daemon.getTurni(matricola);

        Util.setScene("/com/example/GestioneRemoto/GestioneRichieste/FXML/SchermataRichiestaCambioTurno.fxml", stage, c-> new SchermataRichiestaCambioTurno(this, turni, matricola));
}

    public void clickConferma(LocalDate turnoOrigine, LocalDate turnoDestinazione, String turnoDesiderato, int matricola) {
        //TODO query per servizio ed altre cose, poi l'invio della mail ed il popup inform.
    }

*/

    public void clickIndietro(){

    }

}
