package com.example.GestioneRemoto.GestioneRichieste.Schermate;

import com.example.GestioneRemoto.Contenitori.Richiesta;
import com.example.GestioneRemoto.FileDiSistema.Daemon;
import com.example.GestioneRemoto.FileDiSistema.EntityUtente;
import com.example.GestioneRemoto.GestioneRichieste.Control.ControlGestioneRichieste;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.geometry.Insets;
import javafx.scene.control.Button;
import javafx.scene.control.TableCell;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.Background;
import javafx.scene.layout.HBox;
import javafx.scene.paint.Color;
import javafx.util.Callback;

import java.net.URL;
import java.sql.Blob;
import java.sql.SQLException;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.Date;
import java.util.List;
import java.util.ResourceBundle;

public class SchermataGestioneRichieste implements Initializable {
    private final ControlGestioneRichieste controlGestioneRichieste;
    ObservableList<Richiesta> richiesteList;
    @FXML
    private TableView<Richiesta> richiesteTableView;
    @FXML
    private TableColumn<Richiesta, Integer> idCol;
    @FXML
    private TableColumn<Richiesta, Integer> matrCol;
@FXML
        private TableColumn<Richiesta, LocalTime> oraInCol;
    @FXML
    private TableColumn<Richiesta, LocalTime> oraFCol;

    @FXML
    private TableColumn<Richiesta, String> turnoOCol;
    @FXML
    private TableColumn<Richiesta, String> turnoDCol;
    @FXML
    private TableColumn<Richiesta, LocalDate> dataTOCol;
    @FXML
    private TableColumn<Richiesta, LocalDate> dataTDCol;

    @FXML
    private TableColumn<Richiesta, String> svolgCol;
 //   @FXML
   // private TableColumn<Richiesta, Blob> allCol;
    @FXML
    private TableColumn<Richiesta, String> tipoCol;
    @FXML
    private TableColumn<Richiesta, String> motCol;
    @FXML
    private TableColumn<Richiesta, String> catCol;
    @FXML
    private TableColumn<Richiesta, Date> dataiCol;
    @FXML
    private TableColumn<Richiesta, Date> datafCol;
    @FXML
    private TableColumn<Richiesta, Integer> statoCol;
    @FXML
    private TableColumn editCol;
    Richiesta richiesta;
   public SchermataGestioneRichieste(ControlGestioneRichieste controlGestioneRichieste){
    this.controlGestioneRichieste=controlGestioneRichieste;

   }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        try {
           loadDate();
        } catch (SQLException e) {
            System.out.println("err");
        }

    }
    public void loadDate() throws SQLException {
        richiesteList = FXCollections.observableArrayList();
        List<Richiesta> richieste= Daemon.getRichieste(EntityUtente.getMatricola());
        for (int i = 0; i<richieste.size();i++) {
            richiesteList.add(new Richiesta(richieste.get(i).getId(),richieste.get(i).getCategoria(),richieste.get(i).getStato(), richieste.get(i).getData_inizio(),richieste.get(i).getData_fine(),richieste.get(i).getOra_inizio(),richieste.get(i).getOra_fine(),
                    richieste.get(i).getSvolgimento(),richieste.get(i).getMotivazione(), richieste.get(i).getTipologia() , richieste.get(i).getMatricola_destinazione(),richieste.get(i).getTipo_turno_origine(),
                    richieste.get(i).getTipo_turno_destinazione(), richieste.get(i).getData_turno_origine(),richieste.get(i).getData_turno_destinazione()));

        }

        idCol.setCellValueFactory(new PropertyValueFactory<>("id"));
        catCol.setCellValueFactory(new PropertyValueFactory<>("categoria"));
        statoCol.setCellValueFactory(new PropertyValueFactory<>("stato"));
        dataiCol.setCellValueFactory(new PropertyValueFactory<>("data_inizio"));
        datafCol.setCellValueFactory(new PropertyValueFactory<>("data_fine"));
        oraInCol.setCellValueFactory(new PropertyValueFactory<>("ora_inizio"));
        oraFCol.setCellValueFactory(new PropertyValueFactory<>("ora_fine"));
        svolgCol.setCellValueFactory(new PropertyValueFactory<>("svolgimento"));
        motCol.setCellValueFactory(new PropertyValueFactory<>("motivazione"));
        tipoCol.setCellValueFactory(new PropertyValueFactory<>("tipologia"));
        //allCol.setCellValueFactory(new PropertyValueFactory<>("allegato"));
        matrCol.setCellValueFactory(new PropertyValueFactory<>("matricola_destinazione"));
        turnoOCol.setCellValueFactory(new PropertyValueFactory<>("tipo_turno_origine"));
        turnoDCol.setCellValueFactory(new PropertyValueFactory<>("tipo_turno_destinazione"));
        dataTOCol.setCellValueFactory(new PropertyValueFactory<>("data_turno_origine"));
        dataTDCol.setCellValueFactory(new PropertyValueFactory<>("data_turno_destinazione"));



        richiesteTableView.setItems(richiesteList);



        Callback<TableColumn<Richiesta, String>, TableCell<Richiesta, String>> cellFactory = /*(TableColumn<Richieste, String>*/ (param) -> {

            final TableCell<Richiesta, String> cell = new TableCell<>() {

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

                             richiesta =  richiesteTableView.getSelectionModel().getSelectedItem();
                            try {
                                Daemon.delete(richiesta.getId());
                                updateTable();

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

    public void clickRichiestaFerie(ActionEvent e){
       controlGestioneRichieste.clickRichiestaFerie();
    }
    public void clickRichiestaPermesso(ActionEvent e){
       controlGestioneRichieste.clickRichiestaPermesso();
    }
    public void clickRichiestaSciopero(ActionEvent e){
       controlGestioneRichieste.clickRichiestaSciopero();
    }
    public void clickCongedoParentale(ActionEvent e){
       controlGestioneRichieste.clickCongedoParentale();
    }
    public void clickCongedoLutto(ActionEvent e ){
       controlGestioneRichieste.clickCongedoLutto();
    }
public void clickRichiestaMaternita(ActionEvent e) {
    String sesso = EntityUtente.getSesso();
    if (sesso.equals("F")) {

        controlGestioneRichieste.clickRichiestaMaternita();
    } else {
        System.out.println("Sei maschio AHAHAHAHA");
    }
}
public void clickRichiestaMalattia(ActionEvent e){
       controlGestioneRichieste.clickRichiestaMalattia();
}/*
public void clickRichiestaCambio(ActionEvent e){
       controlGestioneRichieste.clickRichiestaCambio();
}*/
    public void updateTable() throws SQLException {
        richiesteList.clear();
        loadDate();
        richiesteTableView.setItems(richiesteList);
        richiesteTableView.refresh();
    }

}
