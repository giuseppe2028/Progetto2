package com.example.GestioneRemoto.GestioneRichieste.Schermate;

import com.example.GestioneRemoto.GestioneRichieste.Control.ControlGestioneRichieste;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;

import java.net.URL;
import java.sql.SQLException;
import java.util.Date;
import java.util.ResourceBundle;

public class SchermataGestioneRichieste implements Initializable {
    private final ControlGestioneRichieste controlGestioneRichieste;
    ObservableList<Richiesta> richiesteList;
    @FXML
    private TableView<Richiesta> richiesteTableView;
    @FXML
    private TableColumn<Richiesta, Integer> idCol;
    @FXML
    private TableColumn<Richiesta, String> catCol;
    @FXML
    private TableColumn<Richiesta, String> motCol;
    @FXML
    private TableColumn<Richiesta, Date> dataInCol;
    @FXML
    private TableColumn<Richiesta, Date> dataFineCol;
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
            controlGestioneRichieste.loadDate(richiesteList);
        } catch (SQLException e) {
            System.out.println("err");
        }

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
public void clickRichiestaMaternita(ActionEvent e){
       controlGestioneRichieste.clickRichiestaMaternita();
}
public void clickRichiestaMalattia(ActionEvent e){
       controlGestioneRichieste.clickRichiestaMalattia();
}
public void clickRichiestaCambio(ActionEvent e){
       controlGestioneRichieste.clickRichiestaCambio();
}
    public static class Richiesta {


        Integer ID_richiesta;
        String categoria;
        String motivazione;
        Date data_inizio;
        Date data_fine;
        String stato;

        public Richiesta(Integer ID_richiesta, String categoria, String motivazione, Date data_inizio,
                         Date data_fine, String stato) {
            this.ID_richiesta = ID_richiesta;
            this.categoria = categoria;
            this.motivazione = motivazione;
            this.data_inizio = data_inizio;
            this.data_fine = data_fine;
            this.stato = stato;

        }


        public void setID_richiesta(Integer ID_richiesta) {
            this.ID_richiesta = ID_richiesta;
        }

        public Integer getID_richiesta() {
            return ID_richiesta;
        }

        public void setCategoria(String categoria) {
            this.categoria = categoria;
        }

        public String getCategoria() {
            return categoria;
        }

        public void setMotivazione(String motivazione) {
            this.motivazione = motivazione;
        }

        public String getMotivazione() {
            return motivazione;
        }

        public void setData_inizio(Date data_inizio) {
            this.data_inizio = data_inizio;
        }

        public Date getData_inizio() {
            return data_inizio;
        }

        public void setData_fine(Date data_fine) {
            this.data_fine = data_fine;
        }

        public Date getData_fine() {
            return data_fine;
        }

        public void setStato(String stato) {
            this.stato = stato;
        }

        public String getStato() {
            return stato;
        }
    }
}
