package com.example.GestioneRemoto.GestioneRichieste.Schermate;

import com.example.GestioneRemoto.FileDiSistema.Util;
import com.example.GestioneRemoto.GestioneRichieste.Control.ControlGestioneRichieste;
import com.example.GestioneRemoto.Start;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.image.Image;
import javafx.stage.FileChooser;
import javafx.stage.Stage;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.sql.PreparedStatement;
import java.time.DayOfWeek;
import java.time.LocalDate;
import java.util.Date;

import static jdk.jfr.consumer.EventStream.openFile;

public class SchermataRichiestaMalattia {
    @FXML
    Button certificato;
    private File file;

    @FXML
    DatePicker dataIn;
    @FXML
    DatePicker dataFi;
    @FXML
    TextField motivazioneL;
    ControlGestioneRichieste controlGestioneRichieste;

    public SchermataRichiestaMalattia(ControlGestioneRichieste controlGestioneRichieste) {
        this.controlGestioneRichieste = controlGestioneRichieste;
    }
    public void initialize(){
        dataIn.setDayCellFactory(picker -> new DateCell() {
            @Override
            public void updateItem(LocalDate dateC, boolean empty) {
                super.updateItem(dateC, empty);
                    if ((dateC.getDayOfWeek() == DayOfWeek.SUNDAY)||dateC.isBefore(LocalDate.now())) {
                        setDisable(true);

                    }
                }

        });
        dataIn.setOnAction(e->{
            LocalDate selectedDate = dataIn.getValue();
            dataFi.setValue(selectedDate);
            dataFi.setDayCellFactory(picker -> new DateCell() {
                @Override
                public void updateItem(LocalDate dateC, boolean empty) {
                    super.updateItem(dateC, empty);

                        if ((dateC.getDayOfWeek() == DayOfWeek.SUNDAY)||dateC.isBefore(LocalDate.now())||dateC.isBefore(selectedDate)) {
                            setDisable(true);

                        }

                }
            });
        });
    }


    public void clickIndietro(ActionEvent e) {
        Util.ritorno("/com/example/GestioneRemoto/GestioneRichieste/FXML/SchermataGestioneRichieste.fxml");
    }

        @FXML
        public void clickCertificato(ActionEvent e) {

                            FileChooser fileChooser =new FileChooser();
                            file = fileChooser.showOpenDialog(Start.mainStage);

                        }

    public void clickInvia(ActionEvent e) throws IOException {
        LocalDate dataInizio = dataIn.getValue();
        LocalDate dataFine = dataFi.getValue();
       String motivazione= motivazioneL.getText();
        FileInputStream inputStream = new FileInputStream(file);
        controlGestioneRichieste.clickInviaMalattia(dataInizio,dataFine,motivazione,inputStream);

    }
}