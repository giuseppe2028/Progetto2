package com.example.GestioneRemoto.GestioneRichieste.Control;

import com.example.GestioneRemoto.FileDiSistema.Daemon;
import com.example.GestioneRemoto.FileDiSistema.Util;
import com.example.GestioneRemoto.GestioneRichieste.Schermate.SchermataGestioneRichieste;
import com.example.GestioneRemoto.GestioneRichieste.Schermate.SchermataRichiestaFerie;
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

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Date;


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

    public void clickRichiestaFerie() {
        Util.setScene("/com/example/GestioneRemoto/GestioneRichieste/FXML/SchermataRichiestaFerie.fxml", stage, c-> new SchermataRichiestaFerie(this));
    }

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
        richiesteList = FXCollections.observableArrayList();
        ResultSet rs1 = Daemon.getRichiesta();


        while (rs1.next()) {
            richiesteList.add(new SchermataGestioneRichieste.Richiesta(rs1.getInt("ID_richiesta"),
                    rs1.getString("categoria"), rs1.getString("motivazione"),
                    rs1.getDate("data_inizio"), rs1.getDate("data_fine"),
                    rs1.getString("stato")));
        }

        idCol.setCellValueFactory(new PropertyValueFactory<>("ID_richiesta"));
        catCol.setCellValueFactory(new PropertyValueFactory<>("categoria"));
        motCol.setCellValueFactory(new PropertyValueFactory<>("motivazione"));
        dataInCol.setCellValueFactory(new PropertyValueFactory<>("data_inizio"));
        dataFineCol.setCellValueFactory(new PropertyValueFactory<>("data_fine"));
        statoCol.setCellValueFactory(new PropertyValueFactory<>("stato"));




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
}
