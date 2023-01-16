package com.example.progetto2.Schermate;

import com.example.progetto2.Control.ControlTimbratura;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;
import java.time.LocalDate;
import java.time.LocalTime;

public class SchermataTimbraturaInLoco {
    private LocalTime time;
    private LocalDate date;
private  ControlTimbratura controlTimbratura;

public SchermataTimbraturaInLoco(){
    controlTimbratura=new ControlTimbratura();
}
    @FXML
    public void clickTimbraEntrata1(ActionEvent e ) throws IOException{
      controlTimbratura.clickTimbraEntrata();
    }

    public void show(){
    }
    @FXML
    public void clickTimbraUscita1(ActionEvent e) throws IOException{
        controlTimbratura.clickTimbraUscita();
    }




}
