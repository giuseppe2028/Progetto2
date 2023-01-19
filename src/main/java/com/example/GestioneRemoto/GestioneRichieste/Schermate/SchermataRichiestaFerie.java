package com.example.GestioneRemoto.GestioneRichieste.Schermate;

import com.example.GestioneRemoto.GestioneRichieste.Control.ControlGestioneRichieste;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.DateCell;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.layout.AnchorPane;

import java.net.URL;
import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.Month;
import java.time.Year;
import java.util.Date;
import java.util.List;
import java.util.ResourceBundle;

public class SchermataRichiestaFerie  {
    private ControlGestioneRichieste controlGestioneRichieste;
    List<LocalDate> dataI;
    List<LocalDate> dataF;
    @FXML
    AnchorPane rettangolo1;
@FXML
Label giorniProibitiLabel;
@FXML
DatePicker dataInizioDatePicker;
@FXML
DatePicker dataFineDatePicker;
public SchermataRichiestaFerie(ControlGestioneRichieste controlGestioneRichieste, List<LocalDate> dataI, List<LocalDate> dataF){
        this.controlGestioneRichieste=controlGestioneRichieste;
        this.dataI=dataI;
        this.dataF=dataF;

    }
    @FXML
    public void initialize() {
        int y = 25;
        for (int i = 0; i < dataI.size(); ++i) {

            String data = String.valueOf(dataI.get(i));
            Label a = new Label();
            a.setText(data);
            a.setLayoutX(64);
            a.setLayoutY(y);
            y += 17;
            rettangolo1.getChildren().add(a);
        }
        int z = 25;
        for (int j = 0; j < dataF.size(); ++j) {

            String data = String.valueOf(dataF.get(j));
            Label a = new Label();
            a.setText(data);
            a.setLayoutX(260);
            a.setLayoutY(z);
            z += 17;

            rettangolo1.getChildren().add(a);
        }



        for (int k = 0; k < dataI.size(); ++k) {

   /* int month = dataI.get(k).getMonthValue();
    System.out.println(dataI.get(k));
    System.out.println(month);
    int year = dataI.get(k).getYear();
    System.out.println(year);
    int day = dataI.get(k).getDayOfMonth();
    int month1= dataF.get(k).getMonthValue();
    int year1=dataF.get(k).getYear();
    int day1= dataF.get(k).getDayOfMonth();

     start = LocalDate.of(year, month, day);
    end = LocalDate.of(year1, month1, day1);
*/
            LocalDate finalStart = dataI.get(k);
            LocalDate finalEnd = dataF.get(k);
            System.out.println(finalStart);
          /*  dataInizioDatePicker.setOnAction(event -> {
        LocalDate date = dataInizioDatePicker.getValue();
        if (date.isBefore(finalStart) || date.isAfter(finalEnd)) {
            dataInizioDatePicker.setValue(dataInizioDatePicker.getValue());

        }*/
            dataInizioDatePicker.setDayCellFactory(picker -> new DateCell() {

                @Override
                public void updateItem(LocalDate date, boolean empty) {
                    super.updateItem(date, empty);
                    setDisable(empty || date.getDayOfWeek() == DayOfWeek.SUNDAY|| !(date.isBefore(finalStart) || date.isAfter(finalEnd))||date.isBefore(LocalDate.now()));
                }
            });


  //  });

        }
    }
     /*
    public void clickInvia(ActionEvent e){
        LocalDate dataInizio = dataInizioDatePicker.getValue();;
        LocalDate dataFine = dataFineDatePicker.getValue();

    controlGestioneRichieste.clickInviaFerie(dataInizio, dataFine);
    }
    public void clickIndietro(){
       // controlGestioneRichieste.clickIndietro();

    }
*/

    }


