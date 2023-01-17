package com.example.GestioneRemoto.GestioneRichieste.Control;

import com.example.GestioneRemoto.Contenitori.Richieste;

import java.time.*;

import com.example.GestioneRemoto.FileDiSistema.Daemon;
import com.example.GestioneRemoto.FileDiSistema.EntityUtente;
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
import java.util.Date;
import java.util.List;




public class ControlGestioneRichieste {
    ObservableList<SchermataGestioneRichieste.Richiesta> richiesteList;
    @FXML
    private TableView<SchermataGestioneRichieste.Richiesta> richiesteTableView;
    @FXML
    private TableColumn<SchermataGestioneRichieste.Richiesta, Integer> idCol;
    @FXML
    private TableColumn<SchermataGestioneRichieste.Richiesta, String> catCol;
    @FXML
    private TableColumn<SchermataGestioneRichieste.Richiesta, String> motCol;
    @FXML
    private TableColumn<SchermataGestioneRichieste.Richiesta, Date> dataInCol;
    @FXML
    private TableColumn<SchermataGestioneRichieste.Richiesta, Date> dataFineCol;
    @FXML
    private TableColumn<SchermataGestioneRichieste.Richiesta, Integer> statoCol;
    @FXML
    private TableColumn editCol;



    public class CustomTableRowSkin<T> extends TableRowSkin<T> {
            public CustomTableRowSkin(TableRow<T> tableRow) {
                super(tableRow);
            }

        }
    private Stage stage = Start.mainStage;
    public void clickGestioneRichieste() {
        SchermataGestioneRichieste schermataGestioneRichieste= Util.setSpecificScene("/com/example/GestioneRemoto/GestioneRichieste/FXML/SchermataGestioneRichieste.fxml", stage, c-> new SchermataGestioneRichieste(this) );
    }

    public void loadDate(ObservableList<SchermataGestioneRichieste.Richiesta> richiesteList) throws SQLException {
        richiesteList =FXCollections.observableArrayList();
        List<Richieste> richieste= Daemon.getRichieste(1001);

        for (int i = 0; i < richiesteList.size(); i++) {
            SchermataGestioneRichieste.Richiesta r = richiesteList.get(i);
            System.out.println(richiesteList.get(i));
        }

        idCol.setCellValueFactory(new PropertyValueFactory<>(richiesteList.get(0).toString()));
        catCol.setCellValueFactory(new PropertyValueFactory<>(richiesteList.get(1).toString()));
        motCol.setCellValueFactory(new PropertyValueFactory<>(richiesteList.get(2).toString()));
        dataInCol.setCellValueFactory(new PropertyValueFactory<>(richiesteList.get(3).toString()));
        dataFineCol.setCellValueFactory(new PropertyValueFactory<>(richiesteList.get(4).toString()));
        statoCol.setCellValueFactory(new PropertyValueFactory<>(richiesteList.get(5).toString()));




        richiesteTableView.setItems(richiesteList);
        richiesteTableView.getItems();

        Callback<TableColumn<SchermataGestioneRichieste.Richiesta, String>, TableCell<SchermataGestioneRichieste.Richiesta, String>> cellFactory = /*(TableColumn<Richieste, String>*/ (param) -> {

            final TableCell<SchermataGestioneRichieste.Richiesta, String> cell = new TableCell<>() {

                @Override
                public void updateItem(String item, boolean empty) {
                    //se la cella è vuota AND almeno un item è null non setta i bottoni
                    super.updateItem(item, empty);
                    if (empty) {
                        setGraphic(null);
                        setText(null);
                    } else {

                        richiesteTableView.setBackground(Background.fill(Color.WHITE));
                        final Button eliminaButton = new Button("elimina");

                        eliminaButton.setBackground(Background.fill(Color.AZURE));

                        eliminaButton.setOnAction((ActionEvent event) -> {

                            SchermataGestioneRichieste.Richiesta richiesta = richiesteTableView.getSelectionModel().getSelectedItem();
                            try {
                                Daemon.delete(richiesta.getID_richiesta());
                            } catch (SQLException e) {
                                throw new RuntimeException(e);
                            }


                        });

                        HBox managebtn = new HBox(eliminaButton);
                        managebtn.setStyle("-fx-alignment: center");
                        HBox.setMargin(eliminaButton, new Insets(2, 2, 0, 3));

                        setGraphic(managebtn);
                        setText(null);
                    }

                }


            };

            return cell;
        };
        editCol.setCellFactory(cellFactory);
        richiesteTableView.setItems(richiesteList);


    }
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

    public void clickRichiestaFerie(){
        Util.setSpecificScene("/com/example/GestioneRemoto/GestioneRichieste/SchermataRichiestaFerie.fxml", stage, c-> new SchermataRichiestaFerie(this));
    }
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

    public void clickRichiestaSciopero(){
        Util.setScene("/com/example/GestioneRemoto/GestioneRichieste/FXML/SchermataRichiestaSciopero.fxml", stage, c-> new SchermataRichiestaSciopero(this));
    }
    public void clickInviaSciopero(LocalDate data, String motivazione, String svolgimento){
        int matricola= EntityUtente.getMatricola();
      int matricolaDatore=  Daemon.getMatricolaDatore();
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
    public void clickRichiestaMaternita(){
        Util.setScene("/com/example/GestioneRemoto/GestioneRichieste/FXML/SchermataRichiestaMaternita.fxml", stage, c-> new SchermataRichiestaMaternita(this));
    }
    public void clickInviaMaternita(LocalDate dataInizio, LocalDate dataFine){
        int matricola=EntityUtente.getMatricola();

    }
    public void clickRichiestaMalattia(){
        Util.setScene("/com/example/GestioneRemoto/GestioneRichieste/FXML/SchermataRichiestaMalattia.fxml", stage, c-> new SchermataRichiestaMalattia(this));
    }
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



}
